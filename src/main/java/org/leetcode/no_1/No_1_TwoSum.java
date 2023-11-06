package org.leetcode.no_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 * @author thread
 * @date 2023/11/6 14:55
 */
public class No_1_TwoSum {
    /**
     * 暴力解法
     * @param nums
     * @param target
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,7,11,15]', 9",
            "'[3,2,4]', 6",
            "'[3,3]', 6"
    })
    public void twoSum_A(@ConvertWith(ArrayConver.class) int[] nums, int target) {
        int[] result = new int[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((nums[i] + nums[j]) == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        // 结果
        System.out.println(Arrays.toString(result));
    }

    /**
     * hash表解法
     * @param nums
     * @param target
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,7,11,15]', 9",
            "'[3,2,4]', 6",
            "'[3,3]', 6"
    })
    public void twoSum_B(@ConvertWith(ArrayConver.class) int[] nums, int target) {
        int[] result = new int[2];
        int n = nums.length;
        // hash表存储当前数组位置之前的全部数
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 如果 (target - 当前数) 在 hash表中 直接返回这两个数
            int temp = target - nums[i];
            if (!hashMap.containsKey(temp)) {
                hashMap.put(nums[i], i);
            } else {
                result[0] = hashMap.get(temp);
                result[1] = i;
                break;
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
