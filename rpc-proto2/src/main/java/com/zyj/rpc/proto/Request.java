package com.zyj.rpc.proto;

import lombok.Data;

/**
 * 表示RPC的一个请求
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 11:31
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Request {
    private ServiceDescriptor service;
    private Object[] parmeters;
}
