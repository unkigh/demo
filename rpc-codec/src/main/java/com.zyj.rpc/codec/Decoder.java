package com.zyj.rpc.codec;

/**
 * 反序列化
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:38
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
