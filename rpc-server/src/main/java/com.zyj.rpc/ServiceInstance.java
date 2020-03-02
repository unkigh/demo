package com.zyj.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 表示一个具体服务
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 15:17
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;//服务由哪个对象提供
    private Method method;//那对象的方法
}
