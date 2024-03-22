package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：删除已排序列表中的重复元素<br/>
 * 问题思路：使用三个指针，第一个指针指向节点（含之前的节点）代表已经处理过的元素，其中不会存在重复元素，第二、三个指针则用于检测重复元素，当第二、三个指针值相同时，说明发现重复元素，此时循环删除第三个指针指向元素直至元素不重复或为空，然后再利用第一个指针删除第二个指针指向元素，如果第二、三指针值不相同，则不存在重复元素，全部指针都往前移动一个元素<br/>
 * 问题链接：<a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/">LT-82</a>
 */
public class LT82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        // 创建一个虚拟节点方便操作
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode firstNode = dummy;
        ListNode secondNode = head;
        ListNode thirdNode = head.next;

        while (thirdNode != null) {
            boolean removeSecondNode = false;
            while (thirdNode != null && secondNode.val == thirdNode.val) {
                removeSecondNode = true;
                // 存在重复，循环删除除第一个重复外的元素
                secondNode.next = thirdNode.next;
                thirdNode = thirdNode.next;
            }
            if (removeSecondNode) {
                // 存在重复，删除重复的第一个元素，此时第一个指针不能移动，因为删除该节点后重复的第一个元素后，剩余元素还未进行判断
                firstNode.next = secondNode.next;
            } else {
                // 不存在重复，此时第一个元素确定可以往前移动
                firstNode = firstNode.next;
            }

            secondNode = firstNode.next;
            thirdNode = secondNode != null ? secondNode.next : null;
        }

        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
