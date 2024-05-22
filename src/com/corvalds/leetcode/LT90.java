package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：给定一个数组，该数组中可能存在重复元素，返回所有可能的、不重复的子数组，构成元素相同但位置不同的子数组认为是重复子数组<br/>
 * 问题思路：借鉴LT78的解法，在每层迭代时新增一个逻辑：如果当前遍历到的元素已经被处理过了，则跳过该元素<br/>
 * 问题链接：<a href="https://leetcode.com/problems/subsets-ii/description/">LT-90</a>
 */
public class LT90 {
    List<List<Integer>> result;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.result = new ArrayList<>();
        Arrays.sort(nums);
        this.traverse(nums, 0, new ArrayList<>());
        return this.result;
    }

    private void traverse(int[] nums, int startIdx, List<Integer> currentIntList) {
        this.result.add(new ArrayList<>(currentIntList));

        Set<Integer> alreadyUsedIntSet = new HashSet<>();
        for (int i = startIdx; i < nums.length; i++) {
            if (alreadyUsedIntSet.contains(nums[i])) {
                continue;
            }
            currentIntList.add(nums[i]);
            alreadyUsedIntSet.add(nums[i]);
            this.traverse(nums, i + 1, currentIntList);
            currentIntList.remove(currentIntList.size() - 1);
        }
    }
}
