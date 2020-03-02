package com.zyj.rpc.codec;

/**
 * 序列化
 * 把对象转化成二进制数组
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:37
 */
public interface Encoder {
    byte[] encode(Object obj);
}
