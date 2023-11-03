package org.leetcode.no_117;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * @author thread
 * @date 2023/11/3 10:49
 */
public class No_117_TreeConnect {
    @ParameterizedTest
    @MethodSource("arguments")
    public void connect_A(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        if (null != root) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            // 处于一层的节点数
            int size = queue.size();
            // 上一个节点
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                // 向下找 下一层元素
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 组成链表
                if (i > 0) {
                    prev.next = node;
                }
                prev = node;
            }
        }

        System.out.println(root);
    }

    public static Stream<Arguments> arguments() {
        // 示例一
        //root = [1,2,3,4,5,null,7]
        Node root_A = new Node(1, new Node(2, new Node(4), new Node(5)),
                new Node(3,null, new Node(7)));
        return Stream.of(Arguments.of(root_A));
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
        public Node(int _val, Node _left, Node _right) {
            this(_val, _left, _right, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
