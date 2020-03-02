package com.zyj.rpc;

import com.sun.corba.se.spi.activation.ServerManager;
import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.codec.Decoder;
import com.zyj.rpc.codec.Encoder;
import com.zyj.rpc.proto.Request;
import com.zyj.rpc.proto.Response;
import com.zyj.rpc.transport.RequestHandler;
import com.zyj.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.servlet.ServletHandler;
import sun.misc.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author:77
 * @date: 2020/2/27 0027
 * @time: 14:21
 */
@Slf4j
public class RPCServer {
    private ServerConfig config;
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] bytes = IOUtils.readFully(recive, recive.available(),true);
                Request decode = decoder.decode(bytes, Request.class);
                log.info("get request:{}", decode);
                ServiceInstance instance = manager.lookup(decode);
                Object invoke = invoker.invoke(instance, decode);
                resp.setData(invoke);
            } catch (Exception e) {
                resp.setCode(1);
                resp.setMsg("RpcServer errot: " + e.getClass().getName() + " ：" + e.getMessage() );
                log.warn(e.getMessage(), e);
            }finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    toResp.write(outBytes.length);
                    log.info("responese client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager manager;
    private ServiceInvoker invoker;


    public RPCServer(ServerConfig config) {
        this.config = config;

        //net
        this.net = ReflectionUtils.newInstance(
                config.getHttpClass());
        this.net.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(
                config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(
                config.getDecoderClass());

        //service
        this.manager = new ServiceManager();

        this.invoker = new ServiceInvoker();

    }
    public <T> void register(Class<T> clazz, T bean) {
        manager.register(clazz, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.start();
    }

}
