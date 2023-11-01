package org.leetcode.no_206;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 206. 反转链表
 * @author thread
 * @date 2023/11/1 23:40
 */
public class No_206_ReverseList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static Stream<Arguments> arguments() {
        // 链表 1,2,3,4,5
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5)))));
        return Stream.of(Arguments.of(head));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void reverseList_A(ListNode listNode) {
        // 反转之前
        System.out.println(listNode);

        // 反转链表
        ListNode prev = null;
        ListNode head = listNode;
        while (head.next != null) {
            ListNode next = head.next;
            ListNode next2 = next.next;
            head.next = prev;
            next.next = head;
            prev = next;
            head = next2;
        }
        head.next = prev;

        System.out.println(head);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void reverseList_B(ListNode head) {
        // 反转之前
        System.out.println(head);

        // 反转链表
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        System.out.println(prev);
    }

}
