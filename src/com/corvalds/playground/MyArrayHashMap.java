package com.corvalds.playground;

import java.util.*;

/**
 * 实现一个能按均匀概率获取元素的HashMap数据结构:
 * 整体思路，在Map内部，额外使用一个数组存储键值对，这样可以利用数组的存储特性，方便生成随机索引，实现均匀概率获取元素
 * 问题1: 在使用数组之后，会存在空洞问题：随着数据变化，数组中的元素可能不会连续
 * 解决方案: 针对这个问题，可以约定每次新增、删除元素时，都从数组的尾部进行，如果删除的元素不在尾部，则交换到最后一位再删除，这样处理后，就能保证数组内容是连续的；
 * 但是这个方案能实施的前提是，程序要知道被删除元素在数组中的具体位置，这样才方便进行交换操作。这里很自然的就能想到使用内部Map来存储Key在数组中的索引，由数组存储实际的键值对
 */
public class MyArrayHashMap<K, V> {

    public static class Node<K, V> {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<K, Integer> map;
    private final List<Node<K, V>> array;

    public MyArrayHashMap() {
        this.map = new HashMap<>();
        this.array = new ArrayList<>();
    }

    public V get(K key) {
        if (this.map.containsKey(key)) {
            return this.array.get(this.map.get(key)).value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (this.map.containsKey(key)) {
            this.array.get(this.map.get(key)).value = value;
            return;
        }
        this.array.add(new Node<>(key, value));
        this.map.put(key, this.array.size() - 1);
    }

    public V remove(K key) {
        if (!this.map.containsKey(key)) {
            return null;
        }
        Integer removingKeyIdx = this.map.get(key);
        Node<K, V> removingNode = this.array.get(removingKeyIdx);
        int lastKeyIdx = this.array.size() - 1;
        Node<K, V> lastNode = this.array.get(lastKeyIdx);

        this.array.set(removingKeyIdx, lastNode);
        this.map.put(lastNode.key, removingKeyIdx);
        this.array.remove(lastKeyIdx);
        this.map.remove(key);
        return removingNode.value;
    }

    public List<K> keySets() {
        return new ArrayList<>(this.map.keySet());
    }

    public int size() {
        return this.map.size();
    }

    public K getRandomKey() {
        Random randomNum = new Random();
        int randomIdx = randomNum.nextInt(this.array.size());
        return this.array.get(randomIdx).key;
    }
}
