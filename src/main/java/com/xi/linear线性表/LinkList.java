package com.xi.linear线性表;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/12 20:25:00
 * @description 链表-单向链表 案例一  包含反转链表
 */
public class LinkList<T> implements Iterable<T>{

    private Node head;// 链表头节点
    private int N;// 链表的长度

    public static void main(String[] args) {
        LinkList<Object> ll = new LinkList<>();

        ll.insert("a");
        ll.insert("b");
        ll.insert("c");
        ll.insert("d");
        ll.insert("e");

        System.out.println(ll.get(2));
        System.out.println(ll.remove(2));
        ll.insert("f", 2);
        Iterator<Object> iterator = ll.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        ll.reverse();// 反转链表
        for (Object obj : ll) {
            System.out.print(obj + ",");
        }



    }
    public LinkList() {
        head = new Node(null, null);
        N = 0;
    }

    /**
     * 迭代的方法
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator<T> {


        private Node n;
        public LIterator() {
            this.n = head;
        }

        /**
         * 判断是否还具备下一个元素 向外部提供遍历的方式
         * @return
         */
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        /**
         * 获取下一个元素 向外部提供遍历的方式
         * @return
         */
        @Override
        public T next() {
            n = n.next;
            return n.item;
        }

    }
    /**
     * 内部类 链表节点
     */
    private class Node {
        public T item;// 存储数据
        public Node next;// 下一节点

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 清空线性表元素
     */
    public void clear() {
        head.next = null;
        N = 0;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEntry() {
        return N == 0;
    }

    /**
     * 线性表长度
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 返回线性表中的指定位置元素
     * @param i
     * @return
     */
    public T get(int i) {
        if (i < 0 | i > N-1) {
            throw new RuntimeException("索引不合法");
        }
        Node n = head.next;

        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * 往线性表后插入一个元素
     * @param t
     */
    public void insert(T t) {
        Node n = head;
        // 找最后一个元素
        while (n.next != null) {
            n = n.next;
        }
        // 创建一个节点
        Node node = new Node(t, null);
        // 插入
        n.next = node;
        N++;
    }

    /**
     * 往线性表指定位置插入一个元素
     * @param t
     * @param i
     */
    public void insert(T t, int i) {
        if (i < 0 | i > N) {
            throw new RuntimeException("索引不合法");
        }

        Node n = head;

        for (int index = 0; index < i; index++) {
            n = n.next;
        }

        // 原插入位置元素
        Node current = n.next;
        // 原位置元素移到插入元素的后面
        Node node = new Node(t, current);
        // 插入新节点
        n.next = node;
        N++;
    }

    /**
     * 删除指定位置的元素并返回
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 | i > N) {
            throw new RuntimeException("索引不合法");
        }

        Node n = head;

        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        Node current = n.next;// 删除的节点
        n.next = current.next;
        N--;
        return current.item;
    }

    /**
     * 查询第一次出现指定元素的位置，没有则返回-1
     * @param t
     * @return
     */
    public int indexOf(T t) {
        Node node = head;
        for (int index = 0; index < N - 1; index++) {
            node = node.next;
            if (node.item.equals(t)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "LinkList{" +
                "head=" + head +
                ", N=" + N +
                '}';
    }

    /**
     * 反转链表 （一般来说双链表不需反转，单连表反转）
     */
    public void reverse() {
        if (N != 0) {
            reverse(head.next);
        }

    }

    public Node reverse(Node current) {
        if (current.next == null) {
            head.next = current;
            return current;
        }

        Node pre = reverse(current.next);
        pre.next = current;
        current.next = null;
        return current;


    }
}


