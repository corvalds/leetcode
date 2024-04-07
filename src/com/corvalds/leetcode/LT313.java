package com.corvalds.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 问题简述：寻找第n个超级丑数<br/>
 * 问题思路：和丑数题目的思路类似，均是维护n个有序链表然后逐一获取其最小值<br/>
 * 问题链接：<a href="https://leetcode.com/problems/super-ugly-number/description/">LT-313</a>
 */
public class LT313 {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] superUglyNumArray = new int[n];
        superUglyNumArray[0] = 1;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]));
        for (int prime : primes) {
            queue.add(new int[]{0, prime, superUglyNumArray[0] * prime});
        }

        int numCount = 1;
        while (!queue.isEmpty() && numCount < n) {
            int[] currentSuperUglyNumArray = queue.poll();
            int currentSuperUglyNum = currentSuperUglyNumArray[2];

            if (superUglyNumArray[numCount - 1] != currentSuperUglyNum) {
                superUglyNumArray[numCount++] = currentSuperUglyNum;
            }

            currentSuperUglyNumArray[0]++;
            currentSuperUglyNumArray[2] = superUglyNumArray[currentSuperUglyNumArray[0]] * currentSuperUglyNumArray[1];
            queue.add(currentSuperUglyNumArray);
        }
        return superUglyNumArray[n - 1];
    }
}
