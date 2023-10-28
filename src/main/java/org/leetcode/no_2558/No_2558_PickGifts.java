package org.leetcode.no_2558;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 2558. 从数量最多的堆取走礼物
 * <p>
 * 给你一个整数数组 gifts ，表示各堆礼物的数量。每一秒，你需要执行以下操作：
 * 选择礼物数量最多的那一堆。
 * 如果不止一堆都符合礼物数量最多，从中选择任一堆即可。
 * 选中的那一堆留下平方根数量的礼物（向下取整），取走其他的礼物。
 * 返回在 k 秒后剩下的礼物数量。
 * </p>
 * @author thread
 * @date 2023/10/28 15:11
 */
public class No_2558_PickGifts {
    @ParameterizedTest
    @CsvSource({
            "'[25,64,9,4,100]', 4",
            "'[1,1,1,1]', 4",
            "'[54,6,34,66,63,52,39,62,46,75,28,65,18,37,18,13,33,69,19,40,13,10,43,61,72]', 7"
    })
    public void pickGifts(@ConvertWith(ArrayConver.class) int[] gifts, int k) {
        for (int i = 0; i < k; i++) {
            int max = gifts[0];
            int index = 0;
            for (int j = 0; j < gifts.length; j++) {
                if (gifts[j] > max) {
                    max = gifts[j];
                    index = j;
                }
            }
            // 计算平方根
            gifts[index] = (int)Math.sqrt(max);
        }

        System.out.println(Arrays.toString(gifts));

        long result = Arrays.stream(gifts).asLongStream().sum();
        System.out.println(result);
    }
}
