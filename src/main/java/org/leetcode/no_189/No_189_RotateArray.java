package org.leetcode.no_189;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 189. 轮转数组
 * <p>
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class No_189_RotateArray {
    /**
     * 第一种解法：挨个转数
     * 会超出时间限制 循环次数太多 k*n次
     */
    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4', 3",
            "'1,2,3,4,5,6,7', 3",
            "'1,2,3,4,5,6,7', 2"
    })
    public void rotateArray_A(@ConvertWith(ArrayConver.class) int[] nums, int k) {
        // 打印输入参数
        System.out.println(Arrays.toString(nums) + "\t" + k);

        // 找到要移动几位 5位的数组移动5位相当于没动 所以这里取余数移动几位
        k = k % nums.length;
        int length = nums.length;

        for (int i = 0;i < k;i++) {
            int temp = nums[length-1];
            for (int j = length-2 ; j >= 0; j--) {
                nums[j+1] = nums[j];
            }
            nums[0] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 第二种解法 使用额外数组
     * @param nums
     * @param k
     */
    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4', 3",
            "'1,2,3,4,5,6,7', 3",
            "'1,2,3,4,5,6,7', 2"
    })
    public void rotateArray_B(@ConvertWith(ArrayConver.class) int[] nums, int k) {
        // 打印输入参数
        System.out.println(Arrays.toString(nums) + "\t" + k);

        // 找到要移动几位 5位的数组移动5位相当于没动 所以这里取余数移动几位
        k = k % nums.length;
        int length = nums.length;

        // 先把K个元素取出来
        int[] tempNums = new int[k];
        int index = 0;
        for (int i = length-k; i < length; i++) {
            tempNums[index++] = nums[i];
        }
        
        // 移动元素
        for (int i = length-k-1; i >= 0; i--) {
            nums[i+k] = nums[i];
        }

        // 赋值移动的k元素到开头
        for (int i = 0; i < k; i++) {
            nums[i] = tempNums[i];
        }
        System.out.println(Arrays.toString(nums));
    }
}
