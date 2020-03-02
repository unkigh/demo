package com.zyj.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 处理请求的头
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 14:09
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResp);
}
