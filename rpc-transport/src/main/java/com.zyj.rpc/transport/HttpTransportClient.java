package com.zyj.rpc.transport;


import com.zyj.rpc.proto.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 14:11
 */
public class HttpTransportClient implements TransportClient{

    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true); //输出
            httpConn.setDoInput(true); //输入
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.connect();

            IOUtils.copy(data, httpConn.getOutputStream());

            int code = httpConn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return httpConn.getInputStream();
            }else{
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //    他是一个短连接
    @Override
    public void close() {

    }
}
