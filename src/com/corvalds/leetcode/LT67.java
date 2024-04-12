package com.corvalds.leetcode;

/**
 * 问题简述：二进制求和<br/>
 * 问题思路：从两个字符串最后字符开始操作，两个字符相加后看判断是否大于2，根据大于2的情况决定结果字符串当前位置的字符是什么<br/>
 * 问题链接：<a href="https://leetcode.com/problems/add-binary/description">LT-67</a>
 */
public class LT67 {

    /**
     * TODO 有更简洁的写法，后续重新再写一遍
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        boolean carry = false;

        int i = aArray.length - 1;
        int j = bArray.length - 1;
        while (i >= 0 && j >= 0) {
            int aBit = aArray[i] == '1' ? 1 : 0;
            int bBit = bArray[j] == '1' ? 1 : 0;
            int bitSum = aBit + bBit + (carry ? 1 : 0);

            sb.append(String.valueOf(bitSum % 2));
            carry = bitSum >= 2;

            i--;
            j--;
        }

        while (i >= 0) {
            int bitSum = (aArray[i] == '1' ? 1 : 0) + (carry ? 1 : 0);
            sb.append(String.valueOf(bitSum % 2));
            carry = bitSum >= 2;
            i--;
        }

        while (j >= 0) {
            int bitSum = (bArray[j] == '1' ? 1 : 0) + (carry ? 1 : 0);
            sb.append(String.valueOf(bitSum % 2));
            carry = bitSum >= 2;
            j--;
        }

        if (carry) {
            sb.append("1");
        }

        return sb.reverse().toString();
    }
}
