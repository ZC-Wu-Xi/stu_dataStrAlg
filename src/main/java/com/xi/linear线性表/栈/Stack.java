package com.xi.linear线性表.栈;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/16 15:39:15
 * @description 栈 链表实现栈
 */
public class Stack<T> implements Iterable<T> {
    private Node head;
    private int N;

    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("武则天");
        stringStack.push("孙尚香");
        stringStack.push("小鲁班");
        stringStack.push("韩信");
        stringStack.pop();

        for (String str : stringStack) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
    public Stack() {
        head = new Node(null, null);
        N = 0;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取栈中元素个数
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 弹栈，获取栈中的第一个元素
     * @return
     */
    public T pop() {
        Node firstNode = head.next;// 需要删除的元素
        if (firstNode == null) {
            return null;
        }
        // 删除第一个节点
        head.next = firstNode.next;
        N--;
        return firstNode.item;
    }

    /**
     * 压栈，放入元素
     * @param t
     */
    public void push(T t) {
        Node firstNode = head.next;
        Node node = new Node(t, firstNode);
        head.next = node;
        N++;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
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

    /**
     * 迭代器
     */
    public class MyIterator implements Iterator {
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
}
