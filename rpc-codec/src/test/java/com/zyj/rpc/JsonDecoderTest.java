package com.zyj.rpc;

import com.zyj.rpc.codec.JsonDecoder;
import com.zyj.rpc.codec.JsonEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 13:43
 */
class JsonDecoderTest {

    @Test
    void decode() {
        JsonEncoder encoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("test");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);

        JsonDecoder decoder = new JsonDecoder();
        TestBean decode = decoder.decode(bytes, bean.getClass());

        assertEquals(bean, decode);

    }
}