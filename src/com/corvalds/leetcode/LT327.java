package com.corvalds.leetcode;


/**
 * 问题简述：统计累计和在指定区间的子数组数<br/>
 * 问题思路：定义一个数组sums，其中sums[i]代表着nums[0...i]的累加和，并对sums进行归并排序，在归并排序时，通过对左右子数组的元素进行统计得到答案<br/>
 * 问题链接：<a href="https://leetcode.com/problems/count-of-range-sum">LT-327</a>
 */
public class LT327 {

    private int res = 0;

    private long[] tmpSumArray;

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sums = new long[nums.length + 1];
        tmpSumArray = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        this.mergeSort(sums, 0, sums.length - 1, lower, upper);
        return this.res;
    }

    private void mergeSort(long[] sums, int lo, int hi, int lower, int upper) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        this.mergeSort(sums, lo, mid, lower, upper);
        this.mergeSort(sums, mid + 1, hi, lower, upper);

        for (int i = lo; i <= hi; i++) {
            tmpSumArray[i] = sums[i];
        }

        // 合并两个子数组和的子数组前，统计是否存在子数组和在目标区间内
        int start = mid + 1, end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while (start <= hi && tmpSumArray[start] - tmpSumArray[i] < lower) {
                start++;
            }
            while (end <= hi && tmpSumArray[end] - tmpSumArray[i] <= upper) {
                end++;
            }
            this.res = this.res + (end - start);
        }

        // 合并两个子数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i > mid) {
                sums[p] = tmpSumArray[j++];
            } else if (j > hi) {
                sums[p] = tmpSumArray[i++];
            } else if (tmpSumArray[i] <= tmpSumArray[j]) {
                sums[p] = tmpSumArray[i++];
            } else {
                sums[p] = tmpSumArray[j++];
            }
        }
    }
}
