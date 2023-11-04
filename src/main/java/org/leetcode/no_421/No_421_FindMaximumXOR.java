package org.leetcode.no_421;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. 数组中两个数的最大异或值
 * @author thread
 * @date 2023/11/4 09:10
 */
public class No_421_FindMaximumXOR {
    /**
     * 暴力解法 提交会超时
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[3,10,5,25,2,8]'",
            "'[14,70,53,83,49,91,36,80,92,51,66,70]'"
    })
    public void findMaximumXOR_A(@ConvertWith(ArrayConver.class) int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int temp = 0;
                // 异或 取最大值
                if ((temp = nums[i] ^ nums[j]) > max ) {
                    max = temp;
                }
            }
        }
        System.out.println(max);
    }

    /**
     * 位运算解法
     * 参考解法：
     * https://www.bilibili.com/video/BV1MS4y1Z7wt/?spm_id_from=333.337.search-card.all.click
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[3,10,5,25,2,8]'",
            "'[14,70,53,83,49,91,36,80,92,51,66,70]'"
    })
    public void findMaximumXOR_B(@ConvertWith(ArrayConver.class) int[] nums) {
        int mask = 0;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            // 二进制高位依次加1 例如：100 -> 110 -> 111
            mask = mask | (1 << i);

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }

            int tempMax = result | (1 << i);
            for(int temp : set) {
                if (set.contains(temp ^ tempMax)) {
                    result = tempMax;
                }
            }
        }
        System.out.println(result);
    }
}
