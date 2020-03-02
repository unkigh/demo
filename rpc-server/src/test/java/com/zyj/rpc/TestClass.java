package com.zyj.rpc;

import org.junit.jupiter.api.Test;

/**
 * @author:77
 * @date: 2020/2/27 0027
 * @time: 10:39
 */
public class TestClass implements TestInterface{
    @Override
    public void a() {


    }
    public void init() {

    }

    @Test
    public void b() {
        String a = "a";
        String b = "b";
        a = b;
        b = "c";

        System.out.println(a);
        System.out.println(b);
    }
}
