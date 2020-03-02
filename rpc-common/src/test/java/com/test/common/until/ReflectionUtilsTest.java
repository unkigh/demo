package com.test.common.until;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 9:08
 */
class ReflectionUtilsTest {

    @Test
    void newInstance() {
        PublicClass t = ReflectionUtils.newInstance(PublicClass.class);
        assertNotNull(t);
    }

    @Test
    void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(PublicClass.class);
        assertEquals(2, methods.length);
        String name = methods[0].getName();
        assertEquals("c", name);
    }

    @Test
    void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(PublicClass.class);
        Method c = methods[0];

        PublicClass publicClass = new PublicClass();
        Object r = ReflectionUtils.invoke(publicClass, c);

        assertEquals("c", r);
    }
}