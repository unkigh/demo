package com.zyj.rpc.transport;

/**
 * 1、启动、监听
 * 2、接受请求 并处理
 * 3、关闭监听
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 14:08
 */
public interface TransportServer {
        void init(int port, RequestHandler handler);

        void start();

        void stop();
}
