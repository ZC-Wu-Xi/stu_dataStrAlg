package com.xi.linear线性表.双向链表;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/14 16:49:36
 * @description 链表-双向链表
 */
public class DLinkedList<T> implements Iterable<T> {
    // 头节点
    private Node head;
    // 尾节点
    private Node last;
    // 链表长度
    private int N;

    public static void main(String[] args) {
        DLinkedList<String> stringDLinkedList = new DLinkedList<String>();
        stringDLinkedList.insert("喵喵");
        stringDLinkedList.insert("嗷呜");
        stringDLinkedList.insert("恶龙咆哮");
        stringDLinkedList.insert("哒咩~");
        stringDLinkedList.insert("哎嘿嘿");
        stringDLinkedList.insert("喵帕斯");
        stringDLinkedList.insert("嘟嘟噜");

        System.out.println(stringDLinkedList.length());
        stringDLinkedList.insert(2, "魚~");
        String remove = stringDLinkedList.remove(1);
        System.out.println(remove);
        stringDLinkedList.remove(6);
        System.out.println(stringDLinkedList.getFirst() + "," + stringDLinkedList.getLast());

        stringDLinkedList.reverse();// 反转链表 eg：head->a->b->c==》head->c->b->a

        for (String str : stringDLinkedList) {
            System.out.print(str + " ");
        }

    }

    public DLinkedList() {
        head = new Node(null, null, null);
        last = null;
        N = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    /**
     * 迭代器类
     */
    private class DLLIterator implements Iterator {
        private Node node = head;
        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            node = node.next;
            return node.item;
        }
    }

    /**
     * 节点类
     */
    private class Node {
        public T item;
        public Node pre;// 前驱
        public Node next;// 后继

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 清空链表
     */
    public void clear() {
        last = null;
        head.next = last;
        head.pre = null;
        head.item = null;
        N = 0;
    }

    /**
     * 判断是否为空链表
     * @return
     */
    public boolean isEntry() {
        return N == 0;
    }

    /**
     * 获取链表的长度
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 根据索引获取元素
     * @param i
     * @return
     */
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("索引位置不合法");
        }

        Node temp = head.next;
        for (int index = 0; index < i; index++) {
            temp = temp.next;
        }

        return temp.item;

    }

    /**
     * 在链表后插入节点
     * @param t
     */
    public void insert(T t) {
        // 只有一个头节点
        if (last == null) {
            last = new Node(t, head, null);
            head.next = last;
        } else {
            // 有很多节点
            Node oldLast = last;
            Node node = new Node(t, oldLast, null);
            last = node;
            oldLast.next = last;
        }
        N++;
    }

    /**
     * 在指定索引处插入节点
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (i < 0 || i > N) {
            throw new RuntimeException("索引位置不合法");
        }

        Node temp = head;
        for (int index = 0; index < i; index++) {
            temp = temp.next;
        }

        Node current = temp.next;// 插入位置的节点
        Node node = new Node(t, temp, current);
        if (current != null) {
            current.pre = node;
        } else {
            last = node;
        }
        temp.next = node;
        N++;

    }

    /**
     * 删除指定索引处的节点并返回元素
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 || i > N) {
            throw new RuntimeException("索引位置不合法");
        }

        Node temp = head;
        for (int index = 0; index < i; index++) {
            temp = temp.next;
        }

        Node current = temp.next;// 删除的元素
        Node current_next = current.next;
        if (current_next == null) {// 删除的是最后一个元素
            last = temp;
        }

        temp.next = current_next;
        if (current_next != null) {
            current_next.pre = temp;
        }
        N--;
        return current.item;
    }

    /**
     * 查询指定元素在链表中第一次出现的索引
     * @param t
     * @return 该索引处的元素，没有则为-1
     */
    public int indexOf(T t) {
        Node temp = head;
        for (int index = 0; temp.next != null; index++) {
            temp = temp.next;
            if (temp.next.item.equals(t)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 获取链表的头节点
     * @return head节点d的下一节点元素
     */
    public T getFirst() {
        if (isEntry()) {
            return null;
        }
        return head.next.item;
    }

    /**
     * 获取链表的尾节点
     * @return
     */
    public T getLast() {
        if (isEntry()) {
            return null;
        }
        return last.item;
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
