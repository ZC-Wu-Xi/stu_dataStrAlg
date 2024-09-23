package com.xi.linear线性表.队列;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/23 20:37
 * @description 队列 基于链表实现
 */
public class Queue<T> implements Iterable<T>{
    private Node head;
    private Node last;
    private int N;

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("李彦宏");
        queue.enqueue("马化腾");
        queue.enqueue("马云");

        for (String str : queue) {
            System.out.print(str + " ");
        }
        System.out.println();

        System.out.println(queue.dequeue());
        queue.enqueue("刘强东");

        for (String str : queue) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public Queue() {
        head = new Node(null, null);
        last = null;
        N = 0;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回队列的长度
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 从队列中获取一个元素并删除
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;
        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;

    }

    /**
     * 插入元素
     * @param t
     */
    public void enqueue(T t) {
        if (last == null) {
            last = new Node(t, null);
            head.next = last;
        } else {
            Node currNode = new Node(t, null);
            last.next = currNode;
            last = currNode;
        }
        N++;
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
            return node.item;
        }
    }

    /**
     * 内部类 节点对象
     */
    public class Node {

        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
