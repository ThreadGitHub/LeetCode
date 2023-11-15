package org.leetcode.no_2656;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2656. K 个元素的最大和
 * @author thread
 * @date 2023/11/15 10:51
 */
public class No_2656_MaximizeSum {
    @ParameterizedTest
    @CsvSource({
            "'[1,2,3,4,5]', 3",
            "'[5,5,5]', 2"
    })
    public void maximizeSum(@ConvertWith(ArrayConver.class) int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt();
        int result = m * k + (k - 1) * k / 2;
        System.out.println(result);

        System.out.println((k - 1) * k / 2);
    }
}
