package com.corvalds.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现一个保证元素遍历顺序的HashMap数据结构：
 * 整体思路：使用一个双向链表按输入顺序串联起整个键值对
 * 之所以要使用双向链表，是为了保证删除键的性能保持在O(1)的水平，如果是一个单向链表，删除元素则需要O(n)的时间
 */
public class MyLinkedHashMap<K, V> {

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> preNode, nextNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head, tail;

    public MyLinkedHashMap() {
        this.map = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.nextNode = this.tail;
        this.tail.preNode = this.head;
    }

    public V get(K key) {
        if (this.map.containsKey(key)) {
            return this.map.get(key).value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (this.map.containsKey(key)) {
            this.map.get(key).value = value;
            return;
        }

        Node<K, V> newNode = new Node<>(key, value);
        this.tail.preNode.nextNode = newNode;
        newNode.preNode = this.tail.preNode;
        newNode.nextNode = this.tail;
        this.tail.preNode = newNode;

        this.map.put(key, newNode);
    }

    public V remove(K key) {
        if (!this.map.containsKey(key)) {
            return null;
        }

        Node<K, V> removingNode = this.map.remove(key);
        removingNode.preNode.nextNode = removingNode.nextNode;
        removingNode.nextNode.preNode = removingNode.preNode;
        removingNode.preNode = null;
        removingNode.nextNode = null;
        return removingNode.value;
    }

    public List<K> keySets() {
        Node<K, V> iteratorNode = this.head.nextNode;
        List<K> keySet = new ArrayList<>();
        while (iteratorNode != null && iteratorNode != this.tail) {
            keySet.add(iteratorNode.key);
            iteratorNode = iteratorNode.nextNode;
        }
        return keySet;
    }

    public int size() {
        return this.map.size();
    }

}
