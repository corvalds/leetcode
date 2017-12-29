package sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with leetcode
 * Author: corvalds@gmail.com
 * Date: 2017/11/10
 * Time: 14:45
 * Description: TODO
 */
public class IntersectionOf2Arrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        boolean cmp = nums1.length < nums2.length;
        int[] st = cmp?nums1:nums2, lg = cmp?nums2:nums1, ret;
        int i = 0, sum = 0, stCount, k, stLen = st.length;
        HashMap<Integer, Integer> tmpRet = new HashMap<>();
        HashMap<Integer, Integer> tmpRet2 = new HashMap<>();

        //将两个数组排序（在本实现中，并没有很好地利用了排序的优势）
        Arrays.sort(st);
        Arrays.sort(lg);
        //统计短数组中每个元素出现的次数
        for (k = 0, stCount = 1; k < stLen - 1; k++) {
            if (st[k] == st[k + 1]) {
                stCount++;
                continue;
            }
            tmpRet.put(st[k], stCount);
            stCount = 1;
        }
        //处理边界情况
        if (k >= 1 && st[k] == st[k - 1])
            tmpRet.put(st[k], stCount);
        else if (k >= 0 && stLen > 0)
            tmpRet.put(st[k], 1);
        System.out.println("-----------------1-------------------");
        for (int j: tmpRet.keySet())
            System.out.println("<" + j + ", " + tmpRet.get(j) + ">");
        System.out.println("------------------------------------");
        for (int j: tmpRet.keySet()) {
            int lgCount = findAndStatistics(j, lg);
            int stCount2 = tmpRet.get(j);
            if (lgCount == 0)
                continue;
            if (lgCount < stCount2)
                tmpRet2.put(j, lgCount);
            else
                tmpRet2.put(j, stCount2);
        }
        System.out.println("-----------------2-------------------");
        for (int j: tmpRet2.keySet())
            System.out.println("<" + j + ", " + tmpRet2.get(j) + ">");
        System.out.println("------------------------------------");

        //生成对应数组
        for (int j: tmpRet2.keySet())
            sum += tmpRet2.get(j);
        ret = new int[sum];
        for (int j: tmpRet2.keySet()) {
            int bound = tmpRet2.get(j);
            for (int v = 0; v < bound; v++)
                ret[i++] = j;
        }

        return ret;
    }

    private int findAndStatistics(int a, int[] b) {
        int count = 0, lo = 0, hi = b.length - 1, mid = 0;
        int constantLo = 0, constantHi = b.length - 1;

        if (hi < 0) //当b为空数组
            return count;
        //二分查找
        while (lo <= hi) {
            mid = (hi - lo) / 2 + lo;
            if (a < b[mid]) {
                hi = mid - 1;
            } else if (a > b[mid]) {
                lo = mid + 1;
            } else
                break;
        }
        //没有找到对应的元素
        if (a != b[mid])
            return count;
        //统计对应元素的数量
        for (int i = mid; i >= constantLo && b[i] == a; i--)
            count++;
        for (int i = mid + 1; i <= constantHi && b[i] == a; i++)
            count++;

        return count;
    }

    public static void main(String[] args) {
        //在这种情况下错误
        int[] test1 = {3,9,8,6,1,9}, test2 = {7,2,2,4,7,0,3,4,5};
        IntersectionOf2Arrays2 t = new IntersectionOf2Arrays2();

        for (int i: t.intersect(test1, test2))
            System.out.println(i);
    }
}
