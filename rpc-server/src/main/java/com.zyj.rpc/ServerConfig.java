package com.zyj.rpc;

import com.zyj.rpc.codec.Decoder;
import com.zyj.rpc.codec.Encoder;
import com.zyj.rpc.codec.JsonDecoder;
import com.zyj.rpc.codec.JsonEncoder;
import com.zyj.rpc.transport.HttpTransportServer;
import com.zyj.rpc.transport.TransportServer;
import lombok.Data;

/**
 * Server配置
 * @author:77
 * @date: 2020/2/26 0026
 * @time: 15:07
 */
@Data
public class ServerConfig {
    private Class<? extends TransportServer> httpClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3000;

}
