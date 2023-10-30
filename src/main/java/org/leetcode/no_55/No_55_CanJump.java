package org.leetcode.no_55;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 55. 跳跃游戏
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * </p>
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false
 * </p>
 * @author thread
 * @date 2023/10/30 14:18
 */
public class No_55_CanJump {
    /**
     * 我的解法就做两件事：
     *
     * 判断当前元素能不能跳到终点
     * 判断元素值是不是0 并且 判断前面的元素能不能跳过0元素位置：
     *      能：继续循环
     *      不能：直接GG跳不到终点的
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,3,1,1,4]'",
            "'[3,2,1,0,4]'",
            "'[1,3,2]'",
            "'[3,0,8,2,0,0,1]'"
    })
    public void canJump_A(@ConvertWith(ArrayConver.class) int[] nums) {
        boolean result = false;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // 记录0元素之前的元素最大能跳到的下标位置
            int temp;
            if ((temp = i + nums[i]) > max) max = temp;

            // 判断当前元素的步数能不能跳到末尾
            if (i + nums[i] >= nums.length-1) {
                result = true;
                break;
            }
            // 如果元素是0 并且 0前面元素步数不能跳过0这个元素 那么就GG了跳不到末尾
            else if (nums[i] == 0 && max <= i) {
                max = 0;
                break;
            }
        }

        System.out.println(result);
    }

    /**
     * 官方题解 找最大可到达位置
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,3,1,1,4]'",
            "'[3,2,1,0,4]'",
            "'[1,3,2]'",
            "'[3,0,8,2,0,0,1]'"
    })
    public void canJump_B(@ConvertWith(ArrayConver.class) int[] nums) {
        boolean result = false;
        // 最大可到位置
        int maxStep = 0;
        // 下标
        int i = 0;
        while(i < nums.length) {
            // 如果追上了最大可到达位置就说明跳不到末尾了
            if(i > maxStep){
                result = false;
                break;
            } else result = true;

            // 更新最大可到达位置
            if (i + nums[i] > maxStep){
                maxStep = i + nums[i];
            }
            i++;
        }

        System.out.println(result);
    }
}
