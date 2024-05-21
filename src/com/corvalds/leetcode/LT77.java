package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题简述：给定两个整数n和k，找出所有元素数量为k、组合元素在区间[1, n]内的所有组合，其中元素相同，位置不同的组合视为相同的组合<br/>
 * 问题思路：产生组合的基本思路采用回溯算法，本题的难点在于如何对结果进行去重，本解法采用的思路是，在不断遍历元素产生组合时，当前遍历元素一定大于上一次遍历的元素，这样相当于取多个重复组合中按元素大小升序排列的组合作为结果<br/>
 * 问题链接：<a href="https://leetcode.com/problems/combinations/description/">LT-77</a>
 */
public class LT77 {

    List<List<Integer>> resultList;

    public List<List<Integer>> combine(int n, int k) {
        this.resultList = new ArrayList<>();
        this.traverse(n, 1, k, new ArrayList<>());
        return this.resultList;
    }

    private void traverse(int n, int startIdx, int k, List<Integer> currentList) {
        if (currentList.size() == k) {
            this.resultList.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = startIdx; i <= n; i++) {
            currentList.add(i);
            this.traverse(n, i + 1, k, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
}
