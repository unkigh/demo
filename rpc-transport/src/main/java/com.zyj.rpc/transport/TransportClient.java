package com.zyj.rpc.transport;

import com.zyj.rpc.proto.Peer;

import java.io.InputStream;

/**
 * 1 创建连接
 * 2 发送数据，等待响应
 * 3 关闭连接
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 14:01
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream io);

    void close();

}
