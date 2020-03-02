package example;

/**
 * @author:77
 * @date: 2020/3/2 0002
 * @time: 14:29
 */
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
