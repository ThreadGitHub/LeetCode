package org.leetcode.utils;

/**
 * 位运算工具类
 * @author thread
 * @date 2023/11/4 15:12
 */
public class BitMath {
    /**
     * 打印 int 值每一个二进制位
     * @param num
     */
    public static void printBit(int num) {
        // 一个 int 值 4字节 * 8 = 32位
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? '0' : '1');
        }
        System.out.println();
    }
}
