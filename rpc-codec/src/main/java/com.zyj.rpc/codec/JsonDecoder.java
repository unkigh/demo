package com.zyj.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 反序列化实现
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:50
 */
public class JsonDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
