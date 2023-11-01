package org.leetcode.no_2127;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * 2127. 参加会议的最多员工数
 * @author thread
 * @date 2023/11/1 09:49
 */
public class No_2127_MaximumInvitations {
    /**
     * 参考力扣灵神(灵茶山艾府)的解法 拓扑排序+分类讨论
     * 题解链接：
     * https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/solution/nei-xiang-ji-huan-shu-tuo-bu-pai-xu-fen-c1i1b/
     * 排列方式：
     * 1. 最大的圆环的数量
     * 2. AB圆环+链的长度
     * 解题思路：
     * 1. 统计所有AB圆环+最长链的长度 求和
     * 2. 统计所有大于2的圆环长度 取最大值
     * 比较 1 和 2求最大值 就是最多能一起开会的员工数
     * @param favorite
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,2,1,2]'",
            "'[1,2,0]'",
            "'[3,0,1,4,1]'",
            "'[1,2,3,4,5,6,3,8,9,10,11,8]'"
    })
    public void maximumInvitations_B(@ConvertWith(ArrayConver.class) int[] favorite) {
        // 计算每个员工受喜欢的次数
        int n = favorite.length;
        int[] likesArray = new int[n];
        for (int item : favorite) {
            likesArray[item]++;
        }
        System.out.println("受喜欢的次数：" + Arrays.toString(likesArray));

        // 获取舔狗员工 没有别人喜欢的员工
//        int[] lickDogs = IntStream.range(0, likesArray.length).filter(t-> likesArray[t] == 0).toArray();
//        System.out.println("舔狗员工：" + Arrays.toString(lickDogs));

        // 计算链每一层的深度 数组从1开始
        int[] maxDepthArray = new int[n];
        IntStream.range(0, maxDepthArray.length).forEach(i-> maxDepthArray[i]=1);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (likesArray[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = favorite[x];
            maxDepthArray[y] = maxDepthArray[x] + 1;

            // 当外层计算完深度后 继续向上查找父级
            if (--likesArray[y] == 0) {
                queue.add(y);
            }
        }
        System.out.println("每个元素的最大深度：" + Arrays.toString(maxDepthArray));


        // 普通圆环情况最大值
        int ordinaryCircleMaxCount = 0;
        // 多个双圆环+双链表 的总和
        int doubleCircleMaxCount = 0;

        // 找圆环
        for (int i = 0; i < n; i++) {
            // 舔狗不会组成圆环 直接跳过
            int likeCount = likesArray[i];
            if (likeCount == 0) continue;

            // 圆环的员工数量
            int circleCount = 1;
            // 循环找圆环
            int next = favorite[i];

            HashMap<Integer, Boolean> useMap = new HashMap<>();
            while (next != i && !useMap.containsKey(next)) {
                // 已经在一个圆环中的元素不用再循环找圆环了 直接跳过就可以
                // ！！！重点 大大减少循环找圆环次数
                likesArray[next] = 0;
                circleCount++;
                useMap.put(next, true);
                next = favorite[next];
            }
            System.out.println("["+ i + "] 开始圆环数量：" + circleCount);

            // 根据圆环的数量处理不同的情况
            // 双圆环 + 双链表的情况
            if (circleCount == 2) {
                // 避免重复计算双圆环 下次循环双圆环的另一个元素就不用再加了
                likesArray[i] = 0;
                likesArray[favorite[i]] = 0;

                // 多个双圆环+双链表进行求和
                doubleCircleMaxCount += maxDepthArray[i] + maxDepthArray[favorite[i]];
            }
            // 多个员工组成普通圆环情况
            else {
                // 多个普通圆环取最大值
                ordinaryCircleMaxCount = Math.max(ordinaryCircleMaxCount, circleCount);
            }
        }

        int result = Math.max(doubleCircleMaxCount, ordinaryCircleMaxCount);
        System.out.println("最终最多一起参加会议的人数：" + result);
    }

    /**
     * 思考半天没解出来的代码 就想出来了一个while找圆环 但是没转明白转晕了
     * @param favorite
     */
    @ParameterizedTest
    @CsvSource({
            "'[2,2,1,2]'",
            "'[1,2,0]'",
            "'[3,0,1,4,1]'"
    })
    public void maximumInvitations_A(@ConvertWith(ArrayConver.class) int[] favorite) {
        for (int i = 0; i < favorite.length; i++) {
            System.out.println(i + " favorite \t" + favorite[i]);
        }

        int maximumInvitation = 0;

        for (int i = 0; i < favorite.length; i++) {
            int end = i;
            int count = 1;

            System.out.println(i + "-------");

            // 已经邀请过的人
            HashMap<Integer, Boolean> useMap = new HashMap<>();
            while ((end = favorite[end]) != i && !useMap.containsKey(end)) {
                System.out.println(end);
                useMap.put(end, true);
                count++;
            }

            if ((end == i || useMap.containsKey(end)) && count > maximumInvitation) {
                maximumInvitation = count;
            }

            System.out.println(i + "\t" + end + "\t" + useMap);
        }

        // 2 favorite 	1
        // 1 favorite 	0
        // 0 favorite 	3
        // 3 favorite 	4
        // 4 favorite 	1

        // 2-1-0-3-4-1

        System.out.println(maximumInvitation);
    }
}
