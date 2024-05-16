package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题简述：给定一个数量k和目标值n，找出所有由[1, 9]区间数字组成（每个数字在组合中只能出现1次）、只包含k个数字、数字累加和等于n的所有组合，这些组合不允许重复<br/>
 * 问题思路：采取回溯算法思路，每次目标值都减去数组中的某个元素，直至目标值为负或者为0，当目标值为负时，意味着之前遍历的数组元素的组合累加和不等于目标值；当目标值为0时，意味着之前遍历的数组元素的组合累加和等于目标值且操作过的数字数量等于k，可以作为结果，但此时有一个去重问题需要处理，基本的思路先，每次目标值减去数组元素后，下次减去的只能是当前操作的数组元素后面的元素（相当于缩小选择集）<br/>
 * 问题链接：<a href="https://leetcode.com/problems/combination-sum-iii/">LT-216</a>
 */
public class LT216 {
    private List<List<Integer>> resultList;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.resultList = new ArrayList<>();
        this.traverse(k, n, 1, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int k, int n, int currentIdx, List<Integer> currentIntList) {
        if (currentIntList.size() > k) {
            return;
        }
        if (n < 0) {
            return;
        }
        if (n == 0 && currentIntList.size() == k) {
            this.resultList.add(new ArrayList<>(currentIntList));
            return;
        }

        for (int i = currentIdx; i <= 9; i++) {
            currentIntList.add(i);
            this.traverse(k, n - i, i + 1, currentIntList);
            currentIntList.remove(currentIntList.size() - 1);
        }
    }
}
