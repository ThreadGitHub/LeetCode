package org.leetcode.no_122;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * </p>
 * @author thread
 * @date 2023/10/29 13:12
 */
public class No_122_MaxProfit {
    @ParameterizedTest
    @CsvSource({
            "'[7,1,5,3,6,4]'",
            "'[1,2,3,4,5]'",
            "'[7,6,4,3,1]'"
    })
    public void maxProfit(@ConvertWith(ArrayConver.class) int[] prices) {
        // 当前比较小的价格
        int minPrice = prices[0];
        // 累计利润
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            // 判断明天价格是不是小了
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            }
            // 判断明天是不是涨了 计算利润
            else {
                profit += prices[i] - minPrice;
                minPrice = prices[i];
            }
        }
        System.out.println(profit);
    }
}
