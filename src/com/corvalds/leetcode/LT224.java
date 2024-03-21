package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：实现一个含括号的四则运算计算器<br/>
 * 问题思路：将加号和减号视为数字的一部分，对于这类带符号的数字直接按从左至右的顺序放入一个栈中，当碰到乘号和除号时，则从栈中取出数字进行运算，当碰到左括号时进行递归调用，碰到右括号时返回结果<br/>
 * 问题链接：<a href="https://leetcode.com/problems/basic-calculator/description/">LT-224</a>
 */
public class LT224 {

    public int calculate(String s) {
        Deque<Character> stringQueue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            stringQueue.addLast(c);
        }
        return calculate(stringQueue);
    }

    private int calculate(Deque<Character> stringQueue) {
        int currentNum = 0;
        char sign = '+';
        Deque<Integer> numStack = new ArrayDeque<>();

        while (!stringQueue.isEmpty()) {
            char c = stringQueue.removeFirst();

            if (isDigital(c)) {
                currentNum = currentNum * 10 + (c - '0');
            }
            if (c == '(') {
                currentNum = calculate(stringQueue);
            }

            if (stringQueue.isEmpty() || (!isDigital(c) && c != ' ')) {
                switch (sign) {
                    case '+':
                        numStack.addFirst(currentNum);
                        break;
                    case '-':
                        numStack.addFirst(-1 * currentNum);
                        break;
                    case '*':
                        numStack.addFirst(numStack.removeFirst() * currentNum);
                        break;
                    case '/':
                        numStack.addFirst(numStack.removeFirst() / currentNum);
                        break;
                }
                currentNum = 0;
                sign = c;
            }

            if (c == ')') {
                break;
            }
        }

        int sum = 0;
        while (!numStack.isEmpty()) {
            sum += numStack.removeFirst();
        }

        return sum;
    }

    private boolean isDigital(char c) {
        return c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')' && c != ' ';
    }
}
