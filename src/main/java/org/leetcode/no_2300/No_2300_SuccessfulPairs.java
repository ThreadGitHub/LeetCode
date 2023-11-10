package org.leetcode.no_2300;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 2300. 咒语和药水的成功对数
 * @author thread
 * @date 2023/11/10 12:49
 */
public class No_2300_SuccessfulPairs {
    /**
     * 参考 【宫水三叶】经典二分运用题
     * https://leetcode.cn/problems/successful-pairs-of-spells-and-potions/solutions/2520333/gong-shui-san-xie-jing-dian-er-fen-yun-y-86y0/
     * @param spells
     * @param potions
     * @param success
     */
    @ParameterizedTest
    @CsvSource({
            "'[5,1,3]', '[1,2,3,4,5]', 7",
            "'[3,1,2]', '[8,5,8]', 16"
    })
    public void successfulPairs_A(@ConvertWith(ArrayConver.class) int[] spells,
                                  @ConvertWith(ArrayConver.class) int[] potions, long success) {
        int[] result = new int[spells.length];

        // 先对数组b排序
        Arrays.sort(potions);

        for (int i = 0; i <spells.length; i++) {
            int spellNum = spells[i];
            int left = 0, right = potions.length-1;

            while (left < right) {
                // 计算中间位置索引
                int index = left + right >> 1;

                // 比较数值
                if ((long) potions[index] * spellNum >= success) {
                    right = index;
                } else {
                    left = index + 1;
                }
            }

            // 判断当前位置是否满足条件
            if ((long) spellNum * potions[right] >= success) {
                result[i] = potions.length- right;
            }
        }

        System.out.println(Arrays.toString(result));
    }

    /**
     * 双数组排序解法，【要求结果要和spells索引对应，所以这个解不行】
     * @param spells
     * @param potions
     * @param success
     */
    @ParameterizedTest
    @CsvSource({
            "'[5,1,3]', '[1,2,3,4,5]', 7",
            "'[3,1,2]', '[8,5,8]', 16"
    })
    public void successfulPairs_B(@ConvertWith(ArrayConver.class) int[] spells,
                                  @ConvertWith(ArrayConver.class) int[] potions, long success) {
        int[] result = new int[spells.length];
        // 对双数组排序
        Arrays.sort(spells);
        Arrays.sort(potions);

        int lastIndex = potions.length-1;
        for (int i = 0; i < spells.length; i++) {
            int spellNum = spells[i];

            // 找最小的符合条件的元素下标
            for (int j = 0; j <= lastIndex; j++) {
                if ((long) spellNum * potions[j] >= success) {
                    lastIndex = j;
                    break;
                }
            }

            if ((long) spellNum * potions[lastIndex] >= success) {
                // 计算符合条件的元素数量
                result[i] = potions.length - lastIndex;
            }
        }

        System.out.println(Arrays.toString(result));
    }
}
