package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题简述：给定一个数组和一个目标值，组合数组中的元素，使该组合的累加和等于目标值，一个数组的元素可以重复出现在组合中<br/>
 * 问题思路：采取回溯算法思路，每次目标值都减去数组中的某个元素，直至目标值为负或者为0，当目标值为负时，意味着之前遍历的数组元素的组合累加和不等于目标值；当目标值为0时，意味着之前遍历的数组元素的组合累加和等于目标值，可以作为结果，但此时有一个去重问题需要处理，基本的思路每次计算目标值减去数组元素后，必须保证再次扣减的元素大于等于当前的数组元素，相当于在多个重复的组合中选择了一个按升序有序的组合<br/>
 * 问题链接：<a href="https://leetcode.cn/problems/n-queens-ii/description">LT-39</a>
 */
public class LT39 {

    private List<List<Integer>> resultList;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.resultList = new ArrayList<>();
        Arrays.sort(candidates);
        this.traverse(candidates, target, 0, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int[] candidates, int target, int currentIdx, List<Integer> currentPath) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            resultList.add(new ArrayList<>(currentPath));
            return;
        }

        for (int i = currentIdx; i < candidates.length; i++) {
            currentPath.add(candidates[i]);
            traverse(candidates, target - candidates[i], i, currentPath);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
