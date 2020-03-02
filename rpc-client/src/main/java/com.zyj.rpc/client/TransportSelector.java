package com.zyj.rpc.client;

import com.zyj.rpc.proto.Peer;
import com.zyj.rpc.transport.TransportClient;

import java.util.List;

/**
 * 路由.选择哪个server去链接
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 9:12
 */
public interface TransportSelector {

     /**
      * 初始化selector
      * @param perrs 可以链接的server端点信息
      * @param count client可以与server监理多少个链接
      * @param clazz client实现class
      */
     void init(List<Peer> perrs, int count, Class<? extends TransportClient> clazz);

     /**
      * 选择一个transport与server做交互
      * @return 网络cloient；
      */
    TransportClient select();

     /**
      * 释放用网的client
      * @param client
      */
    void release(TransportClient client);

    void close();
}
