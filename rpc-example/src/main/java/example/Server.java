package example;

import com.zyj.rpc.RPCServer;
import com.zyj.rpc.ServerConfig;

/**
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 14:22
 */
public class Server {
    public static void main(String[] args) {
        RPCServer server = new RPCServer(new ServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
