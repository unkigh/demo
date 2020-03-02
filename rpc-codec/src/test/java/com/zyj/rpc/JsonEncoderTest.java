package com.zyj.rpc;

import com.zyj.rpc.codec.JsonEncoder;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * 序列化测试
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:54
 */
class JsonEncoderTest {

    @Test
    void encode() throws CloneNotSupportedException {
        JsonEncoder encoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("test");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

    }
}