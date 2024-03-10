package com.corvalds.leetcode;

import java.util.Arrays;

/**
 * 问题简述：数组排序<br/>
 * 问题思路：各类排序算法，最优的应该为快速排序，本解法使用归并排序实现<br/>
 * 问题链接：<a href="https://leetcode.com/problems/sort-an-array">LT-912</a>
 */
public class LT912 {

    private int[] tmpNumArray;

    /**
     * 归并排序解法：
     * 1. 先复制出一个辅助数组
     * 2. 将目标数组拆分成两个长度相同的子数组进行排序求解
     * 3. 合并有序的子数组
     */
    public int[] sortArray(int[] nums) {
        this.tmpNumArray = new int[nums.length];
        this.mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // 防溢出写法
        int mid = lo + (hi - lo) / 2;
        this.mergeSort(nums, lo, mid);
        this.mergeSort(nums, mid + 1, hi);

        // 合并两个子数组
        // 1. 先将子数组的值复制至辅助数组中
        if (hi + 1 - lo >= 0) {
            System.arraycopy(nums, lo, tmpNumArray, lo, hi + 1 - lo);
        }
        // 2. 遍历辅助数组生成新数组
        int p = lo;
        int i = lo;
        int j = mid + 1;
        while (p <= hi) {
            if (i > mid) {
                nums[p++] = tmpNumArray[j++];
            } else if (j > hi) {
                nums[p++] = tmpNumArray[i++];
            } else if (tmpNumArray[i] <= tmpNumArray[j]) {
                nums[p++] = tmpNumArray[i++];
            } else {
                nums[p++] = tmpNumArray[j++];
            }
        }
    }
}
