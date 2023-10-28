package org.leetcode.no_88;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.leetcode.utils.ArrayConver;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * <p>给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。</p>
 * <p>请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。</p>
 * <p>最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。</p>
 *<p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * </p>
 * @author thread
 * @date 2023/10/28 12:09
 */
public class No_88_Merge {
    @ParameterizedTest
    @CsvSource({
            "'[1,2,3,0,0,0]', 3, '[2,5,6]', 3",
            "'[1]', 1, '[]', 0"
    })
    public void merge(@ConvertWith(ArrayConver.class) int[] nums1, int m,
                      @ConvertWith(ArrayConver.class) int[] nums2, int n) {
        int[] result = new int[m+n];
        int p1 = 0, p2 = 0, index = 0;
        while(true) {
            // 跳出循环
            if(p1 >= m && p2 >= n) break;

            // 两个数组并行取值情况
            if (p1 < m && p2 < n) {
                // 两个数组把相同下标的值取出 比较取最小的放入数组
                if (nums1[p1] > nums2[p2]) {
                    result[index] = nums2[p2];
                    p2++;
                } else {
                    result[index] = nums1[p1];
                    p1++;
                }
            }
            // nums1数组还没遍历完的情况 直接追加到数组末尾
            else if (p1 < m) {
                result[index] = nums1[p1];
                p1++;
            }
            // nums2数组还没遍历完的情况 直接追加到数组末尾
            else if (p2 < n) {
                result[index] = nums2[p2];
                p2++;
            }

            index++;
        }

        // 处理后的数据赋值到nums1数组中
        for(int i = 0;i < result.length;i++) {
            nums1[i] = result[i];
        }

        System.out.println(Arrays.toString(nums1));
    }
}
