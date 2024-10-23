package com.xi.linear线性表.符号表;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/23 19:56
 * @description 基于链表实现有序符号表 键值对 有序
 * key ----- val
 */
public class OrderSymbolTable<K, V> implements Iterable{
    private  Node head;
    private int N;

    public static void main(String[] args) {
        OrderSymbolTable<Object, Object> orderObjectOrderSymbolTable = new OrderSymbolTable<>();

        orderObjectOrderSymbolTable.put("6", 123);
        orderObjectOrderSymbolTable.put(2, "edfasf");
        orderObjectOrderSymbolTable.put("b", 123);
        orderObjectOrderSymbolTable.put("1", "阿尔法");
        orderObjectOrderSymbolTable.put("a", "ewfg");
        orderObjectOrderSymbolTable.put("c", "阿尔个人法");
        orderObjectOrderSymbolTable.put("4", "阿尔个人法");
        orderObjectOrderSymbolTable.put("5", "阿尔个人法");
        orderObjectOrderSymbolTable.put("3", "阿尔个人法");

        System.out.println(orderObjectOrderSymbolTable.getVal(2));
        orderObjectOrderSymbolTable.delete(6); // 未删掉 类型不同
        orderObjectOrderSymbolTable.delete(2);

        orderObjectOrderSymbolTable.forEach((key) -> {
            System.out.print(key + ":" + orderObjectOrderSymbolTable.getVal(key) + " ");  // 1:阿尔法 3:阿尔个人法 4:阿尔个人法 5:阿尔个人法 6:123 a:ewfg b:123 c:阿尔个人法
        });

    }

    public OrderSymbolTable() {
        head = new Node(null,null,null);
        N = 0;
    }

    /**
     * 根据键获取值
     * @param key
     * @return
     */
    public V getVal(K key) {
        Node n = head.next;
        while (n != null) {
            if (n.key.equals(key)) {
                return n.val;
            }
            n = n.next;
        }
        return null;
    }

    /**
     * 添加或更新键值对
     * @param key
     * @param val
     * 按key更新或有序添加
     */
    public void put(K key, V val) {
        Node currentNode = head.next;
        Node pre = head;
        // 添加新的键值对 新的key与已经存在的key比较大小
        // 传过来的key > 当前节点的key,寻找下一个节点
        while (currentNode != null && String.valueOf(key).compareTo(String.valueOf(currentNode.key))>0) {
            pre = currentNode;
            currentNode = currentNode.next;
        }
        // 传过来的key = 当前节点的key,更新当前节点的val
        if (currentNode != null && String.valueOf(key).compareTo(String.valueOf(currentNode.key))==0) {
            currentNode.val = val;
            return;
        }
        // 传过来的key < 当前节点的key,更新当前节点的val
        Node newNode = new Node(key, val, currentNode);
        pre.next = newNode;
        N++;
    }

    /**
     * 根据键删除键值对
     * @param key
     */
    public void delete(K key) {
        Node n = head; // 要删除结点的上一节点
        while (n.next != null) {
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;
            }
            n = n.next;
        }
    }

    /**
     * 链表的长度
     * @return
     */
    public int size() {
        return N;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }
    private class MyIterator implements Iterator {
        private Node n = head;
        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            Node node = n.next;
            n = n.next;
            return node.key;
        }
    }

    private class Node {
        private K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
