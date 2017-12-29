package sort;

import java.util.HashSet;

/**
 * Created with leetcode
 * Author: corvalds@gmail.com
 * Date: 2017/11/8
 * Time: 18:14
 * Description: TODO
 */
public class IntersectionOf2Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean cmp = nums1.length < nums2.length;
        int[] st = cmp?nums1:nums2, lg = cmp?nums2:nums1, ret;
        int i = 0, lastElement = Integer.MAX_VALUE;
        HashSet<Integer> tmpRet = new HashSet<>();

        //将两个输入数组排序
        sort(st);
        sort(lg);
        //遍历长度较短的数组
        for (int ste: st) {
            if (ste == lastElement)
                continue;
            lastElement = ste;
            //用二分法在长数组中找到交集元素
            if(findByDivide(ste, lg))
                tmpRet.add(ste);
        }
        ret = new int[tmpRet.size()];
        for (int rete: tmpRet)
            ret[i++] = rete;

        return ret;
    }

    private void sort(int[] a) {
        int[] aux = new int[a.length];

        mergeSort(a, aux, 0, a.length - 1);
    }

    private void mergeSort(int[] a, int[] aux, int lo, int hi) {
        int mid = (hi - lo) / 2 + lo;

        if (lo >= hi)
            return;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, hi);
    }

    private void merge(int[] a, int[] aux, int lo, int hi) {
        int mid = (hi - lo) / 2 + lo;
        int x = lo, y = mid + 1;

        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        for (int i = lo; i <= hi; i++)
            if (x > mid)
                a[i] = aux[y++];
            else if (y > hi)
                a[i] = aux[x++];
            else if (aux[x] < aux[y])
                a[i] = aux[x++];
            else
                a[i] = aux[y++];
    }

    public static boolean findByDivide(int a, int[] b) {
        int lo = 0, hi = b.length - 1, mid;

        if (a == b[lo] || a == b[hi])
            return true;
        if (a < b[lo] || a > b[hi])
            return false;
        while (true) {
            mid = (hi - lo) / 2 + lo;
            if (a == b[mid])
                return true;
            else if (hi - mid == 1)
                return false;
            else if (a < b[mid])
                hi = mid;
            else if (a > b[mid])
                lo = mid;
        }
    }

    public static void main(String[] args) {
        IntersectionOf2Arrays test = new IntersectionOf2Arrays();
        int[] tData1 = {1, 2, 2, 1}, tData2 = {2, 2}, ret = test.intersection(tData1, tData2);

        for (int e: ret)
            System.out.println(e);
    }
}
