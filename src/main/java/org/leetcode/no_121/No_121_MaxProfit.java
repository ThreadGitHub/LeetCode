package org.leetcode.no_121;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 121. 买卖股票的最佳时机
 * <p>给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。</p>
 * <p>你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。</p>
 * <p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。</p>
 * @author thread
 * @date 2023/10/28 15:55
 */
public class No_121_MaxProfit {
    @ParameterizedTest
    @CsvSource({
            "'[7,1,5,3,6,4]'",
            "'[7,6,4,3,1]'"
    })
    public void maxProfit_A(@ConvertWith(ArrayConver.class) int[] prices) {
        // 记录最大收益
        int maxProfit = 0;
        // 记录最低价格
        int minPrice = Arrays.stream(prices).max().getAsInt();

        for (int i = 0; i < prices.length; i++) {
            // 考虑今天价格是不是比最低价格低
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            // 否则考虑今天获得的收益是不是最大收益
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        System.out.println(maxProfit);
    }
}
