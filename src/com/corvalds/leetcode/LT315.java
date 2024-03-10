package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：计算比自己小的数<br/>
 * 问题思路：通过归并排序得到左右两个有序子数组，然后将<br/>
 * 问题链接：<a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/description">LT-315</a>
 */
public class LT315 {

    private int[] copyNums;
    private int[] tmp;
    private int[] res;
    private int[] resTmp;

    public List<Integer> countSmaller(int[] nums) {
        this.copyNums = Arrays.copyOf(nums, nums.length);
        this.tmp = new int[nums.length];
        this.res = new int[nums.length];
        this.resTmp = new int[nums.length];

        this.mergeSort(copyNums, 0, copyNums.length - 1);
        Map<Integer, List<Integer>> resMap = new HashMap<>();
        for (int i = 0; i < copyNums.length; i++) {
            List<Integer> resList = resMap.get(copyNums[i]);
            if (resList == null) {
                resMap.put(copyNums[i], new ArrayList<>(Arrays.asList(res[i])));
            } else {
                resList.add(res[i]);
            }
        }

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> resList = resMap.get(nums[i]);
            res.add(resList.remove(0));
        }
        return res;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        this.mergeSort(nums, lo, mid);
        this.mergeSort(nums, mid + 1, hi);

        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
            resTmp[i] = res[i];
        }

        // 结合左右子数组有序的特点统计局部结果
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while (end <= hi && tmp[i] > tmp[end]) {
                end++;
            }
            resTmp[i] = resTmp[i] + (end - (mid + 1));
        }

        // 合并两个子数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i > mid) {
                nums[p] = tmp[j];
                res[p] = resTmp[j++];
            } else if (j > hi) {
                nums[p] = tmp[i];
                res[p] = resTmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                nums[p] = tmp[i];
                res[p] = resTmp[i++];
            } else {
                nums[p] = tmp[j];
                res[p] = resTmp[j++];
            }
        }
    }
}
