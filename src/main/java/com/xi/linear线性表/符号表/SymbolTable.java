package com.xi.linear线性表.符号表;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/23 19:56
 * @description 基于链表实现符号表 键值对 无序
 * key ----- val
 */
public class SymbolTable implements Iterable{
    private  Node head;
    private int N;

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.put("key1", "东皇太一");
        symbolTable.put("key2", "貂蝉");
        symbolTable.put("key3", "后翼");

        System.out.println(symbolTable.getVal("key2")); // 貂蝉
        System.out.println(symbolTable.getVal("key4")); // null

        symbolTable.delete("key2");
        System.out.println(symbolTable.getVal("key2")); // null

        symbolTable.forEach((key) -> {
            String keyS = String.valueOf(key);
            System.out.print(key + ":" + symbolTable.getVal(keyS) + " "); // key3:后翼 key1:东皇太一
        });

    }

    public SymbolTable() {
        head = new Node(null,null,null);
        N = 0;
    }

    /**
     * 根据键获取值
     * @param key
     * @return
     */
    public String getVal(String key) {
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
     */
    public void put(String key, String val) {
        Node n = head.next;
        while (n != null) {
            // 已存在当前key，则更新值
            if (n.key.equals(key)) {
                n.val = val;
                return;
            }
            n = n.next;
        }
        // 不存在当前key，则添加键值对到头节点
        Node newNode = new Node(key,val,null);
        newNode.next = head.next;
        head.next = newNode;
        N++;
    }

    /**
     * 根据键删除键值对
     * @param key
     */
    public void delete(String key) {
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
        private String key;
        private String val;
        private Node next;

        public Node(String key, String val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
