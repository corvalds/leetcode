package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：寻找K个最小数值对<br/>
 * 问题思路：将两个数组的元素元组构建成升序链表，然后按照合并k个升序链表的思路处理<br/>
 * 问题链接：<a href="https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/">LT-373</a>
 */
public class LT373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> resultList = new LinkedList<>();

        // 初始化优先队列
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> (a[0] + a[1])));
        for (int i = 0; i < nums1.length; i++) {
            priorityQueue.add(new int[]{nums1[i], nums2[0], 0});
        }

        // 遍历找出当前最小的元组
        while (k > 0) {
            int[] currentMinTuple = priorityQueue.poll();
            resultList.add(Arrays.asList(currentMinTuple[0], currentMinTuple[1]));
            if (currentMinTuple[2] < nums2.length - 1) {
                priorityQueue.add(new int[]{currentMinTuple[0], nums2[currentMinTuple[2] + 1], currentMinTuple[2] + 1});
            }
            k--;
        }

        return resultList;
    }
}
