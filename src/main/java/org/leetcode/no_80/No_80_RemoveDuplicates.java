package org.leetcode.no_80;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 80. 删除有序数组中的重复项 II
 * <p>
 *     给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * </p>
 * <p>
 *     不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * </p>
 * @author thread
 * @date 2023/10/28 14:33
 */
public class No_80_RemoveDuplicates {
    /**
     * 双指针解法
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[1,1,1,2,2,3]'",
            "'[0,0,1,1,1,1,2,3,3]'"
    })
    public void removeDuplicates(@ConvertWith(ArrayConver.class) int[] nums) {
        int[] copy = nums;
        int left = 0;
        // 等于1表示有一个值相同
        int count = 1;
        for(int p2 = 1;p2 < copy.length;p2++) {
            // 等于赋值并累加count count<2表示最多增加2个相同的值
            if (nums[left] == copy[p2] && count < 2) {
                nums[++left] = copy[p2];
                count++;
            }
            // 不等于赋值 并 设置count=1
            else if (nums[left] != copy[p2]){
                nums[++left] = copy[p2];
                count=1;
            }
        }
        // +1因为数组下标从0开始
        int result = left+1;
        System.out.println(result);
    }
}
