package com.corvalds.leetcode;

import java.util.Arrays;

/**
 * 问题简述：统计数组中存在多少对reverse pairs（reverse pairs定义见问题详情页）<br/>
 * 问题思路：在判断reverse pairs时，最好的情况是在局部已排序的情况下进行，这时可以运用归并算法的思路处理，因为归并算法中，在合并两个有序子数组前，对于左子数组来说，右子数组有序且其中元素在原始数组中一定位于左子数组的右边，符合reverse pairs定义<br/>
 * 问题链接：<a href="https://leetcode.com/problems/reverse-pairs">LT-493</a>
 */
public class LT493 {

    private int res;

    private int[] tmpNumArray;

    public int reversePairs(int[] nums) {
        this.tmpNumArray = new int[nums.length];
        this.res = 0;
        this.mergeSort(Arrays.copyOf(nums, nums.length), 0, nums.length - 1);
        return this.res;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        this.mergeSort(nums, lo, mid);
        this.mergeSort(nums, mid + 1, hi);

        for (int i = lo; i <= hi; i++) {
            tmpNumArray[i] = nums[i];
        }

        // 合并两个子数组前先计算答案
        int rightSubarrayIdx = mid + 1;
        for (int leftSubarrayIdx = lo; leftSubarrayIdx <= mid; leftSubarrayIdx++) {
            // 乘法需要注意防溢出
            while (rightSubarrayIdx <= hi && (tmpNumArray[leftSubarrayIdx] * 1L) > (tmpNumArray[rightSubarrayIdx] * 2L)) {
                rightSubarrayIdx++;
            }
            this.res = this.res + rightSubarrayIdx - (mid + 1);
        }

        // 合并两个有序子数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i > mid) {
                nums[p] = tmpNumArray[j++];
            } else if (j > hi) {
                nums[p] = tmpNumArray[i++];
            } else if (tmpNumArray[i] <= tmpNumArray[j]) {
                // 升序排列
                nums[p] = tmpNumArray[i++];
            } else {
                nums[p] = tmpNumArray[j++];
            }
        }
    }
}
