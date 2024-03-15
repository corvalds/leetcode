package com.corvalds.leetcode;

import java.util.Random;

/**
 * 问题简述：寻找第K大的数字<br/>
 * 问题思路：借鉴快速排序的思路，在每个循环中，当确定好一个数字的位置后，通过比较其左右区间的大小来决定应该从哪一个区间继续查找，知道当前数字的位置为k<br/>
 * 问题链接：<a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LT-215</a>
 */
public class LT215 {

    public int findKthLargest(int[] nums, int k) {
        // 需要先随机打乱数组确保快速选择算法的平均时间复杂度
        shuffle(nums);
        int p = -1;
        int lo = 0, hi = nums.length - 1;
        while (p != (nums.length - k)) {
            p = partition(nums, lo, hi);
            if (p < (nums.length - k)) {
                // 当前已定位的数字排名比k小，需要到右区间去找更大的数字
                lo = p + 1;
            } else if (p > (nums.length - k)) {
                // 当前已定位的数字排名比k大，需要到左区间去找更小的数字
                hi = p - 1;
            }
        }

        return nums[nums.length - k];
    }

    private void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            swap(nums, i, i + random.nextInt(nums.length - i));
        }
    }

    private int partition(int[] nums, int lo, int hi) {
        int p = lo;
        // 使用两个指针相对而行，保证维护区间[p+1, i), (j, hi]的数字均小于nums[p]
        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i <= hi && nums[i] <= nums[p]) {
                i++;
            }

            while (j >= lo + 1 && nums[j] > nums[p]) {
                j--;
            }

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }

        swap(nums, p, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        // [2, 1] 1
        System.out.println((new LT215()).findKthLargest(new int[]{2, 1}, 1));
    }
}
