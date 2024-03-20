package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：实现一个不含括号的四则运算计算器<br/>
 * 问题思路：将加号和减号视为数字的一部分，对于这类带符号的数字直接按从左至右的顺序放入一个栈中，当碰到乘号和除号时，则从栈中取出数字进行运算<br/>
 * 问题链接：<a href="https://leetcode.com/problems/basic-calculator-ii/description/">LT-227</a>
 */
public class LT227 {

    public int calculate(String s) {
        char[] charArray = s.toCharArray();
        // sign变量记录上一个符号，默认为+号
        char sign = '+';
        int currentNum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= charArray.length; i++) {
            // 循环需要注意边界条件i = charArray.length的场景，之所以需要在i = charArray.length时还要进入循环是为了能够正常的进行最后一个运算符的运算
            char c = i < charArray.length ? charArray[i] : '#';
            if (c == ' ') {
                continue;
            }
            if (i < charArray.length && isDigital(c)) {
                currentNum = currentNum * 10 + (c - '0');
                continue;
            }

            // 当前字符为运算符时，根据前一个运算符sign的决定数字currentNum的情况
            switch (sign) {
                case '+':
                    stack.addFirst(currentNum);
                    break;
                case '-':
                    stack.addFirst(-1 * currentNum);
                    break;
                case '*':
                    stack.addFirst(stack.removeFirst() * currentNum);
                    break;
                case '/':
                    stack.addFirst(stack.removeFirst() / currentNum);
                    break;
            }
            currentNum = 0;
            sign = c;
        }

        // 累加栈中元素
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.removeFirst();
        }
        return sum;
    }

    private boolean isDigital(char c) {
        return c != '+' && c != '-' && c != '*' && c != '/';
    }
}
