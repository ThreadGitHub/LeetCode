package org.leetcode.no_27;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 27. 移除元素
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * </p>
 * @author thread
 * @date 2023/10/28 12:22
 */
public class No_27_RemoveElement {
    /**
     * 双指针解法
     * @param nums
     * @param val
     */
    @ParameterizedTest
    @CsvSource({
            "'[3,2,2,3]', 3",
            "'[0,1,2,2,3,0,4,2]', 2"
    })
    public void removeElement_A(@ConvertWith(ArrayConver.class) int[] nums, int val) {
        int left = 0;
        for(int right = 0;right < nums.length;right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        System.out.println(Arrays.toString(nums) + "\t" + left);
    }
}
