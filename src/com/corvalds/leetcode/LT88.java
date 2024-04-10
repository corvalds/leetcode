package com.corvalds.leetcode;

/**
 * 问题简述：合并两个有序数组<br/>
 * 问题思路：从后往前向nums1数组写入当前较大的数，直到某一个数组的数字全部写入到nums1中，如果nums1有剩余，则不需要处理，因为本身就是要往nums1中写入数字，如果nums2有剩余，则直接将nums2的数字覆盖到nums1中剩余位置即可<br/>
 * 问题链接：<a href="https://leetcode.com/problems/merge-sorted-array/description/">LT-88</a>
 */
public class LT88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        m--;
        n--;

        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[p--] = nums1[m--];
            } else {
                nums1[p--] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }
}
