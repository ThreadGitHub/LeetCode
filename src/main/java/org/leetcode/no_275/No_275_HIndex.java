package org.leetcode.no_275;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 275. H 指数 II
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。
 * </p>
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * </p>
 * @author thread
 * @date 2023/10/30 11:46
 */
public class No_275_HIndex {
    /**
     * 套用 指数1我自己想出来的 解法
     * @param citations
     */
    @ParameterizedTest
    @CsvSource({
            "'[0,1,3,5,6]'",
            "'[1,2,100]'"
    })
    public void hIndex_A(@ConvertWith(ArrayConver.class) int[] citations) {
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            // 如果 值 小于后面的数组元素数 那么就是H指数
            if (citations[i] <= citations.length - i) {
                h = citations[i];
            } else if (h < citations.length - i){
                h = citations.length - i;
                break;
            }
        }
        System.out.println(h);
    }

    /**
     * 套用 “指数1”题 解法
     * @param citations
     */
    @ParameterizedTest
    @CsvSource({
            "'[0,1,3,5,6]'",
            "'[1,2,100]'"
    })
    public void hIndex_B(@ConvertWith(ArrayConver.class) int[] citations) {
        int h = 0;
        int i = citations.length-1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        System.out.println(h);
    }
}
