package org.leetcode.no_45;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 45. 跳跃游戏 II
 * @author thread
 * @date 2023/10/31 09:45
 */
public class No_45_Jump {
    /**
     * 自己想的 找最近末尾的数解法
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[10,9,8,7,6,5,4,3,2,1,1,0]'",
            "'[2,3,1,1,4]'",
            "'[2,3,0,1,4]'",
            "'[0]'",
            "'[1,2,1,1,1]'"
    })
    public void jump_A(@ConvertWith(ArrayConver.class) int[] nums) {
        int jumpStep = 0;
        int i = 0;
        while (i < nums.length && nums.length > 1) {
            // 最大跳的步数
            int maxStep = i + nums[i];
            jumpStep++;

            // 如果能跳到末尾就直接退出
            if (maxStep >= nums.length - 1) {
                break;
            }

            // 找出最接近末尾的数
            int maxNum = 0;
            int maxIndex = 0;
            for (int j = i+1; j <=maxStep; j++) {
                int temp = 0;
                if ((temp = j + nums[j]) >= maxNum) {
                    maxNum = temp;
                    maxIndex = j;
                }
            }

            // 跳到接近末尾的数位置
            i = maxIndex;
        }
        System.out.println(jumpStep);
    }

    /**
     * 官方题解 一次循环找最接近末尾的数
     * @param nums
     */
    @ParameterizedTest
    @CsvSource({
            "'[10,9,8,7,6,5,4,3,2,1,1,0]'",
            "'[2,3,1,1,4]'",
            "'[2,3,0,1,4]'",
            "'[0]'",
            "'[1,2,1,1,1]'"
    })
    public void jump_B(@ConvertWith(ArrayConver.class) int[] nums) {
        // 最大跳到的位置
        int maxPosition = 0;
        // 每一步跳的结束位置
        int end = 0;
        // 跳跃步数
        int jumpStep = 0;
        // nums.length-1 是因为到了结尾不需要再跳了
        for (int i = 0; i < nums.length-1; i++) {
            // 计算当前位置最大能跳到的位置
            maxPosition = Math.max(maxPosition, i+nums[i]);

            // 到达了当前每一跳的终点
            if (i == end) {
                end = maxPosition;
                jumpStep++;
            }
        }

        System.out.println(jumpStep);
    }
}
