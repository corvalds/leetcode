package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：找出第n个丑数<br/>
 * 问题思路：一个丑数乘2、乘3、乘5也均是丑数，维护一个优先队列，每次遍历丑数时将其乘2、乘3、乘5的数去重后加入到优先队列中，然后下一次从优先队列中取出最小的丑数进行遍历<br/>
 * 问题链接：<a href="https://leetcode.com/problems/ugly-number-ii/description/">LT-264</a>
 */
public class LT264 {

    public int nthUglyNumber(int n) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();

        int currentUglyNumber = 1;
        int[] basePrimeFactors = new int[]{2, 3, 5};
        for (int i = 1; i < n; i++) {
            for (int basePrimeFactor : basePrimeFactors) {
                long nextUglyNumberLong = currentUglyNumber * 1L * basePrimeFactor;
                if (nextUglyNumberLong > Integer.MAX_VALUE) {
                    continue;
                }
                int nextUglyNumber = (int) nextUglyNumberLong;
                if (set.contains(nextUglyNumber)) {
                    continue;
                }
                priorityQueue.add(nextUglyNumber);
                set.add(nextUglyNumber);
            }
            currentUglyNumber = priorityQueue.poll();
            set.remove(currentUglyNumber);
        }

        return currentUglyNumber;
    }
}
