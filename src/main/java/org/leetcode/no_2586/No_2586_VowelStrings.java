package org.leetcode.no_2586;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.HashMap;
import java.util.Map;

/**
 * 2586. 统计范围内的元音字符串数
 * @author thread
 * @date 2023/11/7 12:11
 */
public class No_2586_VowelStrings {
    @ParameterizedTest
    @CsvSource({
            "'[are,amy,u]', 0, 2",
            "'[hey,aeo,mu,ooo,artro]', 1, 4"
    })
    public void vowelStrings_A(@ConvertWith(ArrayConver.class) String[] words, int left, int right) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("a", true);
        map.put("e", true);
        map.put("i", true);
        map.put("o", true);
        map.put("u", true);
        int count = 0;
        for (int i = left; i <= right; i++) {
            String str = words[i];
            if (isVowel(str.charAt(0)) && isVowel(str.charAt(str.length()-1))) {
                count++;
            }
        }
        System.out.println(count);
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' ||
                c == 'o' || c == 'u';
    }
}
