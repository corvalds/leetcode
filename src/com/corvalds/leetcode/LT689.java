package com.corvalds.leetcode;

public class LT689 {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] subarrayNums = new int[nums.length - k + 1];
        int subarrayNum = 0;
        for (int i = 0; i < k; i++) {
            subarrayNum += nums[i];
        }
        for (int i = k; i < nums.length; i++) {
            subarrayNums[i - k] = subarrayNum;
            subarrayNum -= nums[i - k];
            subarrayNum += nums[i];
        }
        subarrayNums[nums.length - k] = subarrayNum;

        int[] left = new int[subarrayNums.length];
        left[0] = 0;
        for (int i = 1; i < subarrayNums.length; i++) {
            left[i] = subarrayNums[i] > subarrayNums[left[i - 1]] ? i : left[i - 1];
        }
        int[] right = new int[subarrayNums.length];
        right[subarrayNums.length - 1] = subarrayNums.length - 1;
        for (int j = subarrayNums.length - 2; j >= 0; j--) {
            right[j] = subarrayNums[j] >= subarrayNums[right[j + 1]] ? j : right[j + 1];
        }

        int[] result = new int[3];
        int maxSum = 0;
        for (int i = k; i < subarrayNums.length - k; i++) {
            int currentSum = subarrayNums[left[i - k]] + subarrayNums[i] + subarrayNums[right[i + k]];
            if (currentSum > maxSum) {
                result[0] = left[i - k];
                result[1] = i;
                result[2] = right[i + k];
                maxSum = currentSum;
            }
        }

        return result;
    }
}
