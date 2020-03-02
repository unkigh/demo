package com.zyj.rpc;

import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.proto.Request;
import com.zyj.rpc.proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 * 注册和查询
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 15:24
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public<T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescriptor key = ServiceDescriptor.from(interfaceClass, method);
            services.put(key, instance);
            log.info("register service: {} {}", instance.getClass(), key.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor service = request.getService();
        return services.get(service);
    }
}
