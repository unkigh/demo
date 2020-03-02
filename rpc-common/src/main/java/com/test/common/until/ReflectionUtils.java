package com.test.common.until;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 反射工具类
 * @author:77
 * @date: 2020/2/24 0024
 * @time: 17:03
 */
public class ReflectionUtils {

    /**
     * 根据class创建对象
     * @param clazz 待创建的对象
     * @param <T> 对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        }catch (Exception e) {
            //抛出运行时异常
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取类的公有方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pubMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())){
                pubMethods.add(method);
            }
        }
        return pubMethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法 名?
     * @param obj 被调用的方法对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回结果
     */
    public static Object invoke(Object obj,
           Method method,
           Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
