package com.zyj.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:19
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
