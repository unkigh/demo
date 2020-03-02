package com.zyj.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:27
 */
@Data //getset
@AllArgsConstructor //有参构造方法
@NoArgsConstructor //无参构造方法
public class ServiceDescriptor {
    //类名
    private String clazz;
    //方法名
    private String method;
    //返回类型
    private String returnType;
    //请求参数
    private String[] paramterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterClass = method.getParameterTypes();
        String[] paramterString = new String[parameterClass.length];
        for (int i = 0; i < parameterClass.length; i++) {
            paramterString[i] = parameterClass[i].getName();
        }
        serviceDescriptor.setParamterTypes(paramterString);
        return serviceDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(method, that.method) &&
                Objects.equals(returnType, that.returnType) &&
                Arrays.equals(paramterTypes, that.paramterTypes);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return
                "clazz=" + clazz +
                ", method=" + method +
                ", returnType=" + returnType +
                ", paramterTypes=" + Arrays.toString(paramterTypes);
    }
}
