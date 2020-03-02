package com.test.alibaba;

import com.test.common.until.PublicClass;
import com.test.common.until.ReflectionUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

/**
 * @author:77
 * @date: 2020/2/25 0025
 * @time: 9:04
 */
@SpringBootTest
public class TestReflection {
    @Test
    public void reflection() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(PublicClass.class);
        StringBuffer methods = new StringBuffer();
        for (Method publicMethod : publicMethods) {
            methods.append(publicMethod.getName()).append(" ");
        }
        System.out.println(methods);
    }
}
