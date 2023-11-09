package org.leetcode.no_2258;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 2258. 逃离火灾
 * @author thread
 * @date 2023/11/9 17:06
 */
public class No_2258_MaximumMinutes {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static Stream<Arguments> arguments() {
        int[][] arrayA = new int[][]{{0,2,0,0,0,0,0},{0,0,0,2,2,1,0},{0,2,0,0,1,2,0},{0,0,2,2,2,0,2},{0,0,0,0,0,0,0}};
        int[][] arrayB = new int[][]{{0,0,0,0},{0,1,2,0},{0,2,0,0}};
        int[][] arrayC = new int[][]{{0,0,0},{2,2,0},{1,2,0}};
        return Stream.of(
                Arguments.of(arrayA, null),
                Arguments.of(arrayB, null),
                Arguments.of(arrayC, null)
        );
    }

    /**
     * 灵神 方法二：直接计算解法
     * https://leetcode.cn/problems/escape-the-spreading-fire/solutions/1460794/er-fen-bfspythonjavacgo-by-endlesscheng-ypp1/
     * @param grid
     */
    @ParameterizedTest
    @MethodSource("arguments")
    public void maximumMinutes_A(int[][] grid) {
        int result = maximumMinutes(grid);
        System.out.println(result);
    }

    public int maximumMinutes(int[][] grid) {
        int[] res = bfs(grid, Collections.singletonList(new int[]{0, 0}));
        int manToHouseTime = res[0], m1 = res[1], m2 = res[2];
        if (manToHouseTime < 0) { // 人无法到安全屋
            return -1;
        }

        List<int[]> firePos = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    firePos.add(new int[]{i, j});
                }
            }
        }
        res = bfs(grid, firePos); // 多个着火点同时跑 BFS
        int fireToHouseTime = res[0], f1 = res[1], f2 = res[2];
        if (fireToHouseTime < 0) { // 火无法到安全屋
            return 1_000_000_000;
        }

        int d = fireToHouseTime - manToHouseTime;
        if (d < 0) { // 火比人先到安全屋
            return -1;
        }

        if (m1 != -1 && m1 + d < f1 || // 安全屋左边相邻格子，人比火先到
                m2 != -1 && m2 + d < f2) { // 安全屋上边相邻格子，人比火先到
            return d; // 图中第一种情况
        }
        return d - 1; // 图中第二种情况
    }

    // 返回的数组包含三个数，分别表示到达安全屋/安全屋左边/安全屋上边的最短时间
    private int[] bfs(int[][] grid, List<int[]> q) {
        int m = grid.length, n = grid[0].length;
        int[][] time = new int[m][n];
        for (int[] t : time) {
            Arrays.fill(t, -1); // -1 表示未访问
        }
        for (int[] p : q) {
            time[p[0]][p[1]] = 0;
        }
        for (int t = 1; !q.isEmpty(); t++) { // 每次循环向外扩展一圈
            List<int[]> tmp = q;
            q = new ArrayList<>();
            for (int[] p : tmp) {
                for (int[] d : DIRS) { // 枚举上下左右四个方向
                    int x = p[0] + d[0], y = p[1] + d[1];
                    if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 0 && time[x][y] < 0) {
                        time[x][y] = t;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return new int[]{time[m - 1][n - 1], time[m - 1][n - 2], time[m - 2][n - 1]};
    }
}
