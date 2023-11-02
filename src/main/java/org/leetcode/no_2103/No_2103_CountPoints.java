package org.leetcode.no_2103;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 2103. 环和杆
 * @author thread
 * @date 2023/11/2 11:46
 */
public class No_2103_CountPoints {
    @ParameterizedTest
    @ValueSource(strings = {
            "B0B6G0R6R0R6G9", "B0R0G0R9R0B0G0", "G4", "B9R9G0R4G6R8R2R9G9"
    })
    public void countPoints_A(String rings) {
        // 记录杆子拥有的圆环数
        int[][] hashArray = new int[10][3];
        // 累计杆子数
        int count = 0;

        for (int i = 0; i < rings.length(); i+=2) {
            String subStr = rings.substring(i, i + 2);

            // 默认R
            int colorIndex = 0;
            if (subStr.charAt(0) == 'G') {
                colorIndex = 1;
            } else if(subStr.charAt(0) == 'B') {
                colorIndex = 2;
            }

            // 累加计数
            int index = new Integer(subStr.charAt(1) + "");
            if (hashArray[index][colorIndex] == 0) {
                hashArray[index][colorIndex]++;

                // 统计同时拥有的杆子数
                if (hashArray[index][0] + hashArray[index][1] + hashArray[index][2] == 3) {
                    count++;
                }
            }
        }

        System.out.println("同时拥有三个颜色的杆子数：" + count);
    }
}
