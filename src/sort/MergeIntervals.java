package sort;

import java.util.*;

/**
 * Created with leetcode
 * Author: corvalds@gmail.com
 * Date: 2017/12/22
 * Time: 18:31
 * Description: TODO
 */
public class MergeIntervals {
    protected class Interval {
        int start;
        int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private class Node {
        Interval i;
        int interval;
        Node left, right;

        public Node(Interval i) {
            this.i = i;
            left = null;
            right = null;
            interval = i.end - i.start;
        }

        public int compareTo(Node n) {
            if (n == null)
                throw new NullPointerException();
            if (i.end < n.i.start)
                return -1;
            if (i.start > n.i.end)
                return 1;
            else
                return 0;
        }
    }

    private class IntervalNodeComparetor implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.interval - o1.interval;
        }
    }

    private class StartNodeComparetor implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.i.start - o2.i.start;
        }
    }

    private void put(Node tree, Node newNode) {
        int cmp = tree.compareTo(newNode);
        if (cmp == 0) {
            tree.i = merge(tree.i, newNode.i);
        } else if (cmp > 0) {
            if (tree.left != null)
                put(tree.left, newNode);
            else
                tree.left = newNode;
        } else {
            if (tree.right != null)
                put(tree.right, newNode);
            else
                tree.right = newNode;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size(), cluster = 0;
        Node root;
        List<Interval> ret = new LinkedList<>();
        Node[] tmp = new Node[size];

        if (cluster >= size)
            return ret;
        while (cluster < size)
            tmp[cluster] = new Node(intervals.get(cluster++));
        Arrays.sort(tmp, new IntervalNodeComparetor());
        Arrays.sort(tmp, new StartNodeComparetor());
        for (Node n: tmp)
            System.out.print(n.interval + "[" + n.i.start + ", " + n.i.end+ "]  ");
        System.out.println();
        root = tmp[0];
        for (int i = 1; i < size; i++)
            put(root, tmp[i]);
        iterate(root, ret);

        return ret;
    }

    private Interval merge(Interval s, Interval e) {
        Interval front = s.start < e.start?s:e;
        Interval backend = s.end < e.end?e:s;
        return new Interval(front.start, backend.end);
    }

    private void iterate(Node tree, List<Interval> list) {
        if (tree == null)
            return;
        iterate(tree.left, list);
        list.add(tree.i);
        iterate(tree.right, list);
    }

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        List<Interval> intervals1 = new LinkedList<>();
        List<Interval> intervals2 = new LinkedList<>();
        List<Interval> intervals3 = new LinkedList<>();
        List<Interval> ret1, ret2, ret3;

        intervals1.add(m.new Interval(1, 3));
        intervals1.add(m.new Interval(2, 6));
        intervals1.add(m.new Interval(8, 10));
        intervals1.add(m.new Interval(15, 18));

        intervals2.add(m.new Interval(2, 3));
        intervals2.add(m.new Interval(4, 5));
        intervals2.add(m.new Interval(6, 7));
        intervals2.add(m.new Interval(1, 10));
        intervals2.add(m.new Interval(19, 20));
        intervals2.add(m.new Interval(18, 60));

        intervals3.add(m.new Interval(2, 3));
        intervals3.add(m.new Interval(4, 5));
        intervals3.add(m.new Interval(6, 7));
        intervals3.add(m.new Interval(1, 10));

        ret1 = m.merge(intervals1);
        ret2 = m.merge(intervals2);
        ret3 = m.merge(intervals3);

        for (Interval i: ret1) {
            System.out.print("[" + i.start + ", " + i.end + "] ");
        }
        System.out.println();
        for (Interval i: ret2) {
            System.out.print("[" + i.start + ", " + i.end + "]  ");
        }
        System.out.println();
        for (Interval i: ret3) {
            System.out.print("[" + i.start + ", " + i.end + "]  ");
        }
    }
}
