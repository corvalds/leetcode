package com.corvalds.leetcode;

import java.util.Arrays;
import java.util.Random;

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

    // 以下为快速排序版本
//    public int[] sortArray(int[] nums) {
//        // 使用快速排序排序该数组
//        // 在进行排序前，先随机打乱数组，保证快速排序的性能
//        shuffle(nums);
//        // 使用快速排序处理数组
//        quickSort(nums, 0, nums.length - 1);
//        return nums;
//    }
//
//    private void shuffle(int[] nums) {
//        Random random = new Random();
//        for (int i = 0; i < nums.length; i++) {
//            swap(nums, i, random.nextInt(nums.length - i));
//        }
//    }
//
//    private void quickSort(int[] nums, int lo, int hi) {
//        if (lo >= hi) {
//            return;
//        }
//
//        // 切分数组
//        int p = partition(nums, lo, hi);
//        quickSort(nums, lo, p - 1);
//        quickSort(nums, p + 1, hi);
//    }
//
//    private int partition(int[] nums, int lo, int hi) {
//        int pivot = nums[lo];
//
//
//        int i = lo + 1, j = hi;
//        while (i <= j) {
//            // 定位比pivot大的元素，结束循环时[lo, i)区间内元素均小于等于pivot
//            while (i <= hi && nums[i] <= pivot) {
//                i++;
//            }
//            // 定位比pivot小的元素，结束循环时(j, hi]区间内元素均大于pivot
//            while (j >= lo && nums[j] > pivot) {
//                j--;
//            }
//
//            // 交换i、j位置的元素
//            if (i < j) {
//                swap(nums, i, j);
//            }
//        }
//
//        // 交换lo、j位置的元素，将pivot元素放置到正确的位置
//        // 为什么是和j位置的元素交换位置，而不是i？
//        // 因为该程序定义中[lo, i)区间内元素小于等于pivot，(j, hi]区间内元素大于pivot，当整个循环结束时i > j，意味着[lo, i)和(j, hi]两个区间会存在交集[j, i]，又因为条件：[lo, i)区间内元素小于等于pivot，所以得出j位置的元素小于等于pivot
//        swap(nums, lo, j);
//
//        return j;
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }
}
