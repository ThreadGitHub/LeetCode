package org.leetcode.no_1465;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 1465. 切割后面积最大的蛋糕
 * <p>
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中
 * <p>
 * horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离
 * <p>
 * verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * <p>
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积
 * @author thread
 * @date 2023/10/27 21:41
 */
public class No_1465_MaxArea {
    /**
     * 暴力解法
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     */
    @ParameterizedTest
    @CsvSource({
        "5, 4, '[1,2,4]', '[1,3]'",
        "5, 4, '[3,1]', '[1]'",
        "5, 4, '[3]', '[3]'",
        "1000000000, 1000000000, '[2]', '[2]'"
    })
    public void maxArea_A(int h, int w, @ConvertWith(ArrayConver.class) int[] horizontalCuts,
                                        @ConvertWith(ArrayConver.class) int[] verticalCuts) {
        System.out.println(h + "\t" + w + "\t" + Arrays.toString(horizontalCuts) + "\t" + Arrays.toString(verticalCuts));

        // 增加一个元素 h
        int [] horizontalCuts2 = new int[horizontalCuts.length+1];
        for (int i = 0; i < horizontalCuts.length; i++) {
            horizontalCuts2[i] = horizontalCuts[i];
        }
        horizontalCuts2[horizontalCuts2.length-1] = h;
        Arrays.sort(horizontalCuts2);

        // 增加一个元素 w
        int [] verticalCuts2 = new int[verticalCuts.length+1];
        for (int i = 0; i < verticalCuts.length; i++) {
            verticalCuts2[i] = verticalCuts[i];
        }
        verticalCuts2[verticalCuts2.length-1] = w;
        Arrays.sort(verticalCuts2);

        // 最大面积
        long maxArea = 0;

        // 循环高切口
        for (int i = 0; i < horizontalCuts2.length; i++) {
            // 循环宽切口
            for (int j = 0; j < verticalCuts2.length; j++) {
                long height = i == 0 ? horizontalCuts2[i] : horizontalCuts2[i]-horizontalCuts2[i-1];
                long weight = j == 0 ? verticalCuts2[j] : verticalCuts2[j]-verticalCuts2[j-1];

                long area = 0;
                if ((area = height * weight) > maxArea) {
                    maxArea = area;
                }
            }
        }

        int result = (int)(maxArea % ((int) Math.pow(10, 9) + 7));
        System.out.println(result);
    }

    /**
     * 找最大值解法 贪心算法
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     */
    @ParameterizedTest
    @CsvSource({
            "5, 4, '[1,2,4]', '[1,3]'",
            "5, 4, '[3,1]', '[1]'",
            "5, 4, '[3]', '[3]'",
            "1000000000, 1000000000, '[2]', '[2]'"
    })
    public void maxArea_B(int h, int w, @ConvertWith(ArrayConver.class) int[] horizontalCuts,
                                        @ConvertWith(ArrayConver.class) int[] verticalCuts) {
        System.out.println(h + "\t" + w + "\t" + Arrays.toString(horizontalCuts) + "\t" + Arrays.toString(verticalCuts));

        // 对数组进行排序
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        // 找最长的一段高距离
        long maxHeight = horizontalCuts[0];
        for (int i = 0; i < horizontalCuts.length-1; i++) {
            int temp = 0;
            if ((temp = horizontalCuts[i+1] - horizontalCuts[i]) > maxHeight) {
                maxHeight = temp;
            }
        }
        int temp = 0;
        if ((temp = h - horizontalCuts[horizontalCuts.length-1]) > maxHeight) {
            maxHeight = temp;
        }

        // 找最长的一段宽距离
        long maxWidth = verticalCuts[0];
        for (int i = 0; i < verticalCuts.length-1; i++) {
            int temp2 = 0;
            if ((temp2 = verticalCuts[i+1] - verticalCuts[i]) > maxWidth) {
                maxWidth = temp2;
            }
        }
        int temp2 = 0;
        if ((temp2 = w - verticalCuts[verticalCuts.length-1]) > maxWidth) {
            maxWidth = temp2;
        }

        System.out.println(maxHeight + "\t" + maxWidth);

        int result = (int)(maxHeight * maxWidth % ((int) Math.pow(10, 9) + 7));
        System.out.println(result);
    }
}
