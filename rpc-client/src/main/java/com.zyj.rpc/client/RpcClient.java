package com.zyj.rpc.client;

import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.codec.Decoder;
import com.zyj.rpc.codec.Encoder;
import com.zyj.rpc.proto.Request;
import com.zyj.rpc.proto.Response;
import com.zyj.rpc.proto.ServiceDescriptor;
import com.zyj.rpc.transport.TransportClient;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 11:13
 */
@Log4j
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectortClass());

        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     *
     * @param clazz 接口
     * @param <T>
     * @return 接口的子类对象
     */
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    //调用远程服务的代理类
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Request request = new Request();
                        request.setService(ServiceDescriptor.from(clazz, method));
                        request.setParmeters(args);
                        //网络传输调用远程服务
                        Response resp = invokeRemote(request);

                        if(resp == null || resp.getCode() != 0) {
                            throw new IllegalStateException("fail to invoke remote:" + resp);
                        }

                        return resp.getData();
                    }
                }
        );
    }


    public Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] encode = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(encode));
            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMsg(e.getMessage());
        } finally {
            if(client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}
