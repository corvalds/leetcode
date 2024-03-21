package com.corvalds.leetcode;

/**
 * 问题简述：将链表中的元素两两交换<br/>
 * 问题思路：采用递归的思路，超过两个节点的部分递归完成其交换<br/>
 * 问题链接：<a href="https://leetcode.com/problems/swap-nodes-in-pairs/description/">LT-24</a>
 */
public class LT24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        // 使用两个指针进行操作
        ListNode currentNode = head;
        ListNode nextNode = currentNode.next;
        // 前一个指针指向下一个指针指向的已经转换好的链表
        currentNode.next = swapPairs(nextNode.next);
        // 下一个指针指向的节点改为指向前一个节点
        nextNode.next = currentNode;

        return nextNode;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
