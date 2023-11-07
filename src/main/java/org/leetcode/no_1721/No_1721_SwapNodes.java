package org.leetcode.no_1721;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 1721.交换链表中的节点
 * @author thread
 * @date 2023/11/07 18:58
 */
public class No_1721_SwapNodes {
    @ParameterizedTest
    @MethodSource("arguments")
    public void swapNodes_A(ListNode head, int k) {
        System.out.println(head + "\t" + k);

        ListNode startNode = null;
        ListNode stopNode = head;
        int leftCount = 0;
        int rightCount = 0;
        ListNode curr = head;
        while (curr != null) {
            // 找左面的节点
            if (++leftCount == k) {
                startNode = curr;
            }

            // 找右面的节点
            if (++rightCount > k) {
                stopNode = stopNode.next;
                --rightCount;
            }

            curr = curr.next;
        }

        //交换值
        int startVal = startNode.val;
        startNode.val = stopNode.val;
        stopNode.val = startVal;

        System.out.println("转换结果：");
        System.out.println(head);
    }

    public static Stream<Arguments> arguments() {
        // head = [1,2,3,4,5], k = 2
        ListNode arg1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        Arguments arguments1 = Arguments.arguments(arg1, 2);

        // head = [7,9,6,6,7,8,3,0,9,5], k = 5
        ListNode arg2 = new ListNode(7, new ListNode(9, new ListNode(6, new ListNode(6, new ListNode(7, new ListNode(8, new ListNode(3, new ListNode(0, new ListNode(9, new ListNode(5, null))))))))));
        Arguments arguments2 = Arguments.arguments(arg2, 5);

        // head = [1], k = 1
        ListNode arg3 = new ListNode(1);
        Arguments arguments3 = Arguments.arguments(arg3, 1);

        // head = [1,2,3], k = 2
        ListNode arg4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        Arguments arguments4 = Arguments.arguments(arg4, 2);
        return Stream.of(arguments1, arguments2, arguments3, arguments4);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
