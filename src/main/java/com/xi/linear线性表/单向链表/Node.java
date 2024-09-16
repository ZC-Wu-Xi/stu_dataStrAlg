package com.xi.linear线性表.单向链表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/12 20:17:16
 * @description 链表 节点类
 */
public class Node<T> {
    public T item;// 存储数据
    public Node next;// 下一节点

    public static void main(String[] args) {
        Node n1 = new Node(1, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(3, null);
        Node n4 = new Node(4, null);
        Node n5 = new Node(5, null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
    }

    public Node(T item, Node next) {
        this.item = item;
        this.next = next;
    }
}
