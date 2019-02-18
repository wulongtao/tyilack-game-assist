package com.tyilack.assist;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wulongtao
 */
public class CommandTest {

    @Test
    public void test1() {
        String s = "q 3 2";
        String[] arr = s.split(" ");
        for (String item : arr) {
            System.out.print(item);
        }
    }

}
