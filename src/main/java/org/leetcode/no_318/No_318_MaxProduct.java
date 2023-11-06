package org.leetcode.no_318;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 318. 最大单词长度乘积
 * @author thread
 * @date 2023/11/6 11:42
 */
public class No_318_MaxProduct {
    /**
     * 暴力解法 会超时
     * @param words
     */
    @ParameterizedTest
    @CsvSource({
            "'[abcw, baz, foo, bar, xtfn, abcdef]'",
            "'[a, ab, abc, d, cd, bcd, abcd]'",
            "'[a, aa, aaa, aaaa]'",
            "'[]'"

    })
    public void maxProduct_A(@ConvertWith(ArrayConver.class) String[] words) {
        int maxLength = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j <words.length; j++) {
                String leftStr = words[i];
                String rightStr = words[j];

                boolean flag = true;
                // 找出是否有重复字符
                for (int n = 0; n < leftStr.length(); n++) {
                    char c = leftStr.charAt(n);
                    if (rightStr.indexOf(c) != -1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println(leftStr + "\t" + rightStr);
                    maxLength = Math.max(maxLength, leftStr.length() * rightStr.length());
                }
            }
        }
        System.out.println(maxLength);
    }

    /**
     * 位运算解法 参考 【宫水三叶】解法
     * https://leetcode.cn/problems/maximum-product-of-word-lengths/solutions/1105955/gong-shui-san-xie-jian-dan-wei-yun-suan-cqtxq/?envType=daily-question&envId=2023-11-06
     * @param words
     */
    @ParameterizedTest
    @CsvSource({
            "'[abcw, baz, foo, bar, xtfn, abcdef]'",
            "'[a, ab, abc, d, cd, bcd, abcd]'",
            "'[a, aa, aaa, aaaa]'",
            "'[]'"
    })
    public void maxProduct_B(@ConvertWith(ArrayConver.class) String[] words) {
        int n = words.length;
        // 把每个字符串转换为一个二进制数，26个小写字母对应数组里的每一位
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int t = 0;
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                int bitIndex = c - 'a';
                // 通过 或 运算，把每个字符的值 写到二进制数的每一位
                t |= 1 << bitIndex;
            }
            masks[i] = t;
        }

        // 位运算比较每个字符串 取最大值
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // &运算两个都是1才为1, 等于0说明没有没有共同字母
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }

        System.out.println(max);
    }
}
