package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：用队列实现栈<br/>
 * 问题思路：使用两个队列模拟栈，一个队列称为非空队列，一个队列称为辅助队列，非空队列用来实际存储元素，辅助队列用于新增时短暂的存储元素
 *         新增元素时，将新元素放入辅助队列，然后将非空队列的元素不断出队追加至辅助队列中，最后交换非空队列和辅助队列对应的队列对象
 *         查询栈顶元素时，返回非空队列的队头元素
 *         判断是否为空时，判断非空队列是否包含元素
 *         删除元素时，删除非空队列队头元素<br/>
 * 问题链接：<a href="https://leetcode.com/problems/implement-stack-using-queues/description/">LT-225</a>
 */
public class LT225 {
    private Deque<Integer> notEmptyQueue;

    private Deque<Integer> emptyQueue;

    public LT225() {
        notEmptyQueue = new ArrayDeque<>();
        emptyQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        if (notEmptyQueue.isEmpty()) {
            notEmptyQueue.addLast(x);
            return;
        }

        emptyQueue.addLast(x);
        while (!notEmptyQueue.isEmpty()) {
            emptyQueue.addLast(notEmptyQueue.removeFirst());
        }
        Deque<Integer> tmp = emptyQueue;
        emptyQueue = notEmptyQueue;
        notEmptyQueue = tmp;
    }

    public int pop() {
        return notEmptyQueue.removeFirst();
    }

    public int top() {
        return notEmptyQueue.peekFirst();
    }

    public boolean empty() {
        return notEmptyQueue.isEmpty();
    }
}
