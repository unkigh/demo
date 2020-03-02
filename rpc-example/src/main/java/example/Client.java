package example;

import com.zyj.rpc.client.RpcClient;

/**
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 14:22
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int minus = service.minus(2, 1);

        System.out.println(add + "  " + minus);
    }
}
