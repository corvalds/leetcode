package com.corvalds.leetcode;

/**
 * 问题简述：两数相加<br/>
 * 问题思路：不断遍历两条链表，将相同位的数字相加并使用一个变量记录进位，直到两条链表都遍历完成且无进位时才停止循环，如果过程中一条链表已经遍历完成，则直接将其当前位视为0即可<br/>
 * 问题链接：<a href="https://leetcode.com/problems/add-two-numbers/description/">LT-2</a>
 */
public class LT2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Idx = l1;
        ListNode l2Idx = l2;
        ListNode dummy = new ListNode();

        int carry = 0;
        ListNode resultIdx = dummy;
        while (l1Idx != null || l2Idx != null || carry != 0) {
            int l1NodeVal = l1Idx == null ? 0 : l1Idx.val;
            int l2NodeVal = l2Idx == null ? 0 : l2Idx.val;
            int nodeValSum = l1NodeVal + l2NodeVal + carry;
            resultIdx.next = new ListNode(nodeValSum % 10);
            carry = nodeValSum / 10;

            resultIdx = resultIdx.next;
            l1Idx = l1Idx == null ? null : l1Idx.next;
            l2Idx = l2Idx == null ? null : l2Idx.next;
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
