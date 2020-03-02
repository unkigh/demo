package com.test.alibaba;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AlibabaApplicationTests {


    @Test
    public void ttt() {
        List<String> list = new ArrayList<String>(2);
        list.add("一");
        list.add("二");
        String[] array = new String[list.size()];
        list.toArray(array);
        int i = 0;
    }


    @Test
    void contextLoads() {

    }

}
