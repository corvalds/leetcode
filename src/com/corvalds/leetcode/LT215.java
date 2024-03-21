package com.corvalds.leetcode;

import java.util.Random;

/**
 * 问题简述：寻找第K大的数字<br/>
 * 问题思路：借鉴快速排序的思路，在每个循环中，当确定好一个数字的位置后，通过比较其左右区间的大小来决定应该从哪一个区间继续查找，知道当前数字的位置为k<br/>
 * 问题链接：<a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LT-215</a>
 */
public class LT215 {

    public int findKthLargest(int[] nums, int k) {
        // 使用快速选择算法进行第k大元素选择
        // 1. 先对数组进行乱序排列
        shuffle(nums);
        // 2. 借鉴快速排序思想，不断迭代尝试直到找到第k大的元素
        k = nums.length - k;
        int p = -1;
        int lo = 0, hi = nums.length - 1;
        while (p != k) {
            p = partition(nums, lo, hi);
            if (p > k) {
                hi = p - 1;
            }
            if (p < k) {
                lo = p + 1;
            }
        }

        return nums[k];
    }

    private void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            swap(nums, i, random.nextInt(nums.length - i));
        }
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];

        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i <= hi && nums[i] <= pivot) {
                i++;
            }
            while (j >= lo && nums[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, lo, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
