package com.zyj.rpc;

import com.test.common.until.ReflectionUtils;
import com.zyj.rpc.proto.Request;

/**
 * 实例调用
 * @author:77
 * @date: 2020/2/27 0027
 * @time: 14:16
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance instance, Request request) {
       return ReflectionUtils.invoke(instance.getTarget(), instance.getMethod(), request.getParmeters());
    }
}
