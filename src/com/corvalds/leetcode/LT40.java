package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 问题简述：给定一个数组和一个目标值，组合数组中的元素，使该组合的累加和等于目标值，一个数组的元素在组合中只能出现一次<br/>
 * 问题思路：采取回溯算法思路，每次目标值都减去数组中的某个元素，直至目标值为负或者为0，当目标值为负时，意味着之前遍历的数组元素的组合累加和不等于目标值；当目标值为0时，意味着之前遍历的数组元素的组合累加和等于目标值，可以作为结果，但此时有一个去重问题需要处理，基本的思路先对数组进行排序，每次目标值减去数组元素后，下次减去的只能是当前操作的数组元素后面的元素（相当于缩小选择集），同时这里需要注意如果后一个元素和当前元素相同，则不需要操作，否则会导致重复组合<br/>
 * 问题链接：<a href="https://leetcode.com/problems/combination-sum-ii/description/">LT-40</a>
 */
public class LT40 {
    private List<List<Integer>> resultList;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.resultList = new ArrayList<>();
        Arrays.sort(candidates);
        this.traverse(candidates, target, 0, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int[] candidates, int target, int currentIdx, List<Integer> currentIntegerList) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            this.resultList.add(new ArrayList<>(currentIntegerList));
            return;
        }

        for (int i = currentIdx; i < candidates.length; i++) {
            if (i - 1 >= currentIdx && candidates[i - 1] == candidates[i]) {
                continue;
            }
            currentIntegerList.add(candidates[i]);
            this.traverse(candidates, target - candidates[i], i + 1, currentIntegerList);
            currentIntegerList.remove(currentIntegerList.size() - 1);
        }
    }
}
