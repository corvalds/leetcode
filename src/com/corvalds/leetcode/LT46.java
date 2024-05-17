package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 问题简述：给定一个元素唯一的数组，输出数组的全排列<br/>
 * 问题思路：标准的回溯算法题目<br/>
 * 问题链接：<a href="https://leetcode.com/problems/permutations/description/">LT-46</a>
 */
public class LT46 {
    List<List<Integer>> resultList;

    Set<Integer> pathSet;

    public List<List<Integer>> permute(int[] nums) {
        this.resultList = new ArrayList<>();
        this.pathSet = new HashSet<>();
        this.traverse(nums, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int[] nums, List<Integer> currentPath) {
        if (currentPath.size() == nums.length) {
            this.resultList.add(new ArrayList<>(currentPath));
            return;
        }

        for (int num : nums) {
            if (pathSet.contains(num)) {
                continue;
            }
            currentPath.add(num);
            pathSet.add(num);
            this.traverse(nums, currentPath);
            currentPath.remove(currentPath.size() - 1);
            pathSet.remove(num);
        }
    }
}
