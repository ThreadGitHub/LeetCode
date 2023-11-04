package org.leetcode.demo.bit_math;

import org.junit.jupiter.api.Test;

/**
 * 位运算的测试类
 * @author thread
 * @date 2023/11/4 14:17
 */
public class BitMathTest {
    @Test
    public void printBitNum() {
        // 打印2进制
        BitMath.printBit(0);
        BitMath.printBit(1);
        BitMath.printBit(-1);
        BitMath.printBit(-2);
        System.out.println("------------------------");

        // 计算一个数的相反数
        int a = 5;
        int b = ~a + 1;
        a = -5;
        BitMath.printBit(a);
        int c = ~a + 1;
        BitMath.printBit(c);
        System.out.println(a + "\t" + b + "\t" + c);
        System.out.println("------------------------");

        // 与运算
        BitMath.printBit(3);
        BitMath.printBit(99);
        BitMath.printBit(3 & 99);
        System.out.println("------------------------");

        // 取反+1 = 自己本身
        BitMath.printBit(Integer.MIN_VALUE);
        BitMath.printBit(~Integer.MIN_VALUE+1);
    }
}
