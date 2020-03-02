package com.zyj.rpc.client;

import com.zyj.rpc.codec.Decoder;
import com.zyj.rpc.codec.Encoder;
import com.zyj.rpc.codec.JsonDecoder;
import com.zyj.rpc.codec.JsonEncoder;
import com.zyj.rpc.proto.Peer;
import com.zyj.rpc.transport.HttpTransportClient;
import com.zyj.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * client配置
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 10:53
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    //路由
    private Class<? extends RandomTransportSelector> selectortClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );

}
