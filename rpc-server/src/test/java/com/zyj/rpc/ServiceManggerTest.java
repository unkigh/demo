package com.zyj.rpc;

import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.proto.Request;
import com.zyj.rpc.proto.ServiceDescriptor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

/**
 * @author:77
 * @date: 2020/2/27 0027
 * @time: 10:38
 */
class ServiceManggerTest {
    ServiceManager serviceMangger;

    @BeforeEach
    public void init() {
        serviceMangger = new ServiceManager();
    }

    @Test
    void register() {
        TestInterface bean = new TestClass();
        serviceMangger.register(TestInterface.class, bean);
    }

    @Test
    void lookup() {
        TestInterface bean = new TestClass();
        serviceMangger.register(TestInterface.class, bean);

        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setService(serviceDescriptor);
        ServiceInstance instance = serviceMangger.lookup(request);
        assertNotNull(instance);
    }
}