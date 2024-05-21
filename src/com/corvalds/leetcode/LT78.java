package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题简述：给定一个数组，返回所有可能的、不重复的子数组，构成元素相同但位置不同的子数组认为是重复子数组<br/>
 * 问题思路：整体采用回溯算法的思路，去重的思路借鉴题目LT77的解决方案，不同点在于，本题需要在每次进行迭代时就采集结果<br/>
 * 问题链接：<a href="https://leetcode.com/problems/subsets/description/">LT-78</a>
 */
public class LT78 {

    List<List<Integer>> resultList;

    public List<List<Integer>> subsets(int[] nums) {
        this.resultList = new ArrayList<>();
        Arrays.sort(nums);
        this.traverse(nums, 0, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int[] nums, int startIdx, List<Integer> currentIntList) {
        this.resultList.add(new ArrayList<>(currentIntList));

        for (int i = startIdx; i < nums.length; i++) {
            currentIntList.add(nums[i]);
            this.traverse(nums, i + 1, currentIntList);
            currentIntList.remove(currentIntList.size() - 1);
        }
    }
}
