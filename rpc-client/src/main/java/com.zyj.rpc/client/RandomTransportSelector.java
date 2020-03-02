package com.zyj.rpc.client;

import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.proto.Peer;
import com.zyj.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机选择网络端点
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 9:35
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    /**
     * 已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    /**
     * 初始化selector
     *
     * @param perrs 可以链接的server端点信息
     * @param count client可以与server监理多少个链接
     * @param clazz client实现class
     */
    @Override
    public synchronized void init(List<Peer> perrs, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        //创建连接
        for (Peer perr : perrs) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(perr);
                clients.add(client);
            }
            log.info("connect server: {}", perr);
        }
    }

    /**
     * 选择一个transport与server做交互
     *
     * @return 网络cloient；
     */
    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    /**
     * 释放用完的client
     *
     * @param client
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized   void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
