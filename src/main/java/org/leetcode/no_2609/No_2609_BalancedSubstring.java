package org.leetcode.no_2609;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 2609. 最长平衡子字符串
 * 1. 遇到0加一
 *     条件：1的次数=0 否则 设为0
 *     每一次重置1为0时, 取 0和1次数最大值
 * 2. 遇到1加一
 *      条件：小于 0 的次数
 * @author thread
 * @date 2023/11/8 00:05
 */
public class No_2609_BalancedSubstring {
    @ParameterizedTest
    @ValueSource(strings = {
            "00101",
            "101",
            "001",
            "01000111",
            "00111",
            "111",
            "10"
    })
    public void findTheLongestBalancedSubstring_A(String s) {
        // 累计每次的平衡子字符串的数量
        int count = 0;
        // 记录0的出现次数
        int p1 = 0;
        // 记录1的出现次数
        int p2 = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 累计0的次数
            if (c == '0') {
                // 如果0还在1前面
                if (p2 == 0) {
                    p1++;
                } else {
                    // 如果出现了0在1后面的情况 那么就计算平衡子字符串的长度
                    count = Math.max(p2 + p2, count);
                    // 重新计数
                    p2 = 0;
                    p1 = 1;
                }
            }

            // 累计1的次数
            if (c == '1' && p2 < p1){
                p2++;
            }

            // 应对 000111 这种末尾全是 1 的情况
            if (i == s.length()-1 && c == '1' && p2 > 0) {
                // 如果出现了0在1后面的情况 那么就计算平衡子字符串的长度
                count = Math.max(p2 + p2, count);
            }
        }

        System.out.println(count);
    }

    /**
     * 参考 宫水三叶 解法
     * https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string/solutions/2517437/gong-shui-san-xie-on-shi-jian-o1-kong-ji-i8e7/
     * @param s
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "00101",
            "101",
            "001",
            "01000111",
            "00111",
            "111",
            "10"
    })
    public void findTheLongestBalancedSubstring_B(String s) {
        int i = 0;
        int count = 0;
        while (i < s.length()) {
            int a = 0, b = 0;
            // 循环0的次数
            while (i < s.length() && s.charAt(i) == '0' && ++a > 0) i++;

            // 循环1的次数
            while (i < s.length() && s.charAt(i) == '1' && ++b > 0) i++;

            count = Math.max(count, Math.min(a, b) * 2);
        }
        System.out.println(count);
    }
}
