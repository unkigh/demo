package com.zyj.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 序列化实现
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:48
 */
public class JsonEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
