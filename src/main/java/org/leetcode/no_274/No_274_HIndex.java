package org.leetcode.no_274;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 274. H 指数
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * </p>
 *
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且每篇论文 至少 被引用 h 次。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * </p>
 * @author thread
 * @date 2023/10/29 13:41
 */
public class No_274_HIndex {
    /**
     * 排序法 从前 -> 后推进 解法
     * 自己想出来的
     * @param citations
     */
    @ParameterizedTest
    @CsvSource({
            "'[3,0,6,1,5]'",
            "'[1,3,1]'",
            "'[11,15]'",
            "'[4,4,0,0]'",
            "'[1,7,9,4]'"
    })
    public void hIndex_A(@ConvertWith(ArrayConver.class) int[] citations) {
        // 排序
        Arrays.sort(citations);
        System.out.println(Arrays.toString(citations));

        // h指数
        int hIndexNum = -1;
        for (int i = 0; i < citations.length; i++) {
            // 比较如果 当前值 <= 后面的数组元素数 那么就是一个H值
            if (citations[i] <= citations.length - i) {
                hIndexNum = citations[i];
            }
            // 如果当前值 >= 后面的数组元素数 那么H值=后面元素数 至少H值是后面元素数
            else if (citations.length - i > hIndexNum){
                hIndexNum = citations.length - i;
            }
        }

        System.out.println(hIndexNum);
    }

    /**
     * [官方解法] 排序法 从后 -> 前推进 解法
     * 还是官方🐮批
     * @param citations
     */
    @ParameterizedTest
    @CsvSource({
            "'[3,0,6,1,5]'",
            "'[1,3,1]'",
            "'[11,15]'",
            "'[4,4,0,0]'",
            "'[1,7,9,4]'"
    })
    public void hIndex_B(@ConvertWith(ArrayConver.class) int[] citations) {
        // 排序
        Arrays.sort(citations);
        System.out.println(Arrays.toString(citations));

        int h = 0, i = citations.length-1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }

        System.out.println(h);
    }
}
