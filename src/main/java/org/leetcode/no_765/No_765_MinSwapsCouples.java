package org.leetcode.no_765;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 765. 情侣牵手
 * @author thread
 * @date 2023/11/11 14:16
 */
public class No_765_MinSwapsCouples {
    public static void main(String[] args) {
        System.out.println(0 ^ 1);
        System.out.println(1 ^ 1);

        System.out.println(2 ^ 1);
        System.out.println(3 ^ 1);

        System.out.println(4 ^ 1);
        System.out.println(5 ^ 1);

        System.out.println(6 ^ 1);
        System.out.println(7 ^ 1);
    }

    /**
     * 贪心解法
     * @param row
     */
    @ParameterizedTest
    @CsvSource({
            "'[6,2,1,7,4,5,3,8,0,9]'",
            "'[0,2,4,6,7,1,3,5]'",
            "'[2,0,5,4,3,1]'",
            "'[0,2,1,3]'",
            "'[3,2,0,1]'",
            "'[5,3,4,2,1,0]'"
    })
    public void minSwapsCouples_A(@ConvertWith(ArrayConver.class) int[] row) {
        // 数组长度
        int n = row.length;
        // 统计交换次数
        int cnt = 0;

        // 建立索引映射
        int[] indexs = new int[n];
        for (int i = 0;i < n;i++) indexs[row[i]] = i;

        for (int i = 0; i < n; i+=2) {
            int numA = row[i];
            int numB = row[i+1];
            // 不是连续的就交换
            if (numA != (numB ^ 1)) {
                int temp = numB;
                // 交换数组值
                swap(i + 1, indexs[numA ^ 1], row);
                // 交换 索引表
                swap(temp, numA ^ 1, indexs);
                cnt++;
            }
        }

        System.out.println(Arrays.toString(row));
        System.out.println("交换次数：" + cnt);
    }

    public void swap(int x, int y, int[] row) {
        int temp = row[x];
        row[x] = row[y];
        row[y] = temp;
    }
}
