package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 问题简述：给定一个元素不唯一的数组，输出数组的全排列<br/>
 * 问题思路：本题的难点在于元素不唯一导致结果可能存在重复，解决方案是在每一个回溯遍历层级中，使用一个集合记录已经使用过的元素值，如果再遍历到相同的元素值时，直接忽略，因为对于当前遍历层级来说，同一个元素值的回溯结果一定会相同<br/>
 * 问题链接：<a href="https://leetcode.com/problems/permutations-ii/description/">LT-47</a>
 */
public class LT47 {
    List<List<Integer>> resultList;

    boolean[] inPath;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.resultList = new ArrayList<>();
        this.inPath = new boolean[nums.length];
        this.traverse(nums, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int[] nums, List<Integer> currentPath) {
        if (nums.length == currentPath.size()) {
            this.resultList.add(new ArrayList<>(currentPath));
            return;
        }

        Set<Integer> alreadyUsedIntSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (inPath[i]) {
                continue;
            }
            if (alreadyUsedIntSet.contains(nums[i])) {
                continue;
            }
            alreadyUsedIntSet.add(nums[i]);
            currentPath.add(nums[i]);
            inPath[i] = true;
            this.traverse(nums, currentPath);
            currentPath.remove(currentPath.size() - 1);
            inPath[i] = false;
        }
    }
}
