package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：整数转化为罗马数字<br/>
 * 问题思路：构建罗马字母和数字映射关系，按大至小的顺序一直尝试减去离剩余数字最近的罗马字母直至剩余数字为0<br/>
 * 问题链接：<a href="https://leetcode.com/problems/integer-to-roman">LT-12</a>
 */
public class LT12 {

    public static List<RomanNumeralMapItem> ITEM_LIST = new LinkedList<>();

    static {
        ITEM_LIST.add(new RomanNumeralMapItem("M", 1000));
        ITEM_LIST.add(new RomanNumeralMapItem("CM", 900));
        ITEM_LIST.add(new RomanNumeralMapItem("D", 500));
        ITEM_LIST.add(new RomanNumeralMapItem("CD", 400));
        ITEM_LIST.add(new RomanNumeralMapItem("C", 100));
        ITEM_LIST.add(new RomanNumeralMapItem("XC", 90));
        ITEM_LIST.add(new RomanNumeralMapItem("L", 50));
        ITEM_LIST.add(new RomanNumeralMapItem("XL", 40));
        ITEM_LIST.add(new RomanNumeralMapItem("X", 10));
        ITEM_LIST.add(new RomanNumeralMapItem("IX", 9));
        ITEM_LIST.add(new RomanNumeralMapItem("V", 5));
        ITEM_LIST.add(new RomanNumeralMapItem("IV", 4));
        ITEM_LIST.add(new RomanNumeralMapItem("I", 1));
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int remainNum = num;
        for (RomanNumeralMapItem numMapItem : ITEM_LIST) {
            while (remainNum >= numMapItem.number) {
                sb.append(numMapItem.romanLetter);
                remainNum = remainNum - numMapItem.number;
            }
            if (remainNum == 0) {
                break;
            }
        }
        return sb.toString();
    }

    public static class RomanNumeralMapItem {
        public String romanLetter;
        public int number;

        public RomanNumeralMapItem(String romanLetter, int number) {
            this.romanLetter = romanLetter;
            this.number = number;
        }
    }
}
