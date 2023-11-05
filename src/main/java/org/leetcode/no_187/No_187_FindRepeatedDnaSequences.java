package org.leetcode.no_187;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列
 * @author thread
 * @date 2023/11/5 09:56
 */
public class No_187_FindRepeatedDnaSequences {
    /**
     * hash表解法
     * @param s
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
            "AAAAAAAAAAAAA",
            "AAAAAAAAAAA"
    })
    public void findRepeatedDnaSequences_A(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i+10);
            // 记录字符串出现次数
            map.put(subStr, (map.getOrDefault(subStr, 0) + 1));
            // 只记录次数 大于等于2 的字符串到List
            if (map.get(subStr) == 2) {
                result.add(subStr);
            }
        }
        System.out.println(result);
    }
}
