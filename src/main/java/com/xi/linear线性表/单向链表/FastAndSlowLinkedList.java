package com.xi.linear线性表.单向链表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/15 14:43:27
 * @description 快慢指针 找中间值 判断是否存在环
 */
public class FastAndSlowLinkedList {
    public static void main(String[] args) {
        Node<String> n1 = new Node("1", null);
        Node<String> n2 = new Node("2", null);
        Node<String> n3 = new Node("3", null);
        Node<String> n4 = new Node("4", null);
        Node<String> n5 = new Node("5", null);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println("中间值:" + getMid(n1));

        System.out.println(isCircle(n1));
        n5.next = n3;
        System.out.println(isCircle(n1));

    }

    /**
     * 使用快慢指针找链表的中间值(未考虑含环形的链表)
     * @param first
     * @return
     */
    public static String getMid(Node first) {
        Node<String> slow = first;// 慢指针
        Node<String> fast = first;// 快指针

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 跳出循环时快指针已经走完了，慢指针指向的位置就是中间值
        return slow.item;
    }

    /**
     * 判断链表是否存在环
     * @param first
     * @return
     */
    public static boolean isCircle(Node first) {
        Node<String> slow = first;
        Node<String> fast = first;
        boolean flag = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            flag = slow.equals(fast);
            if (flag) {
                break;
            }
        }
        return flag;
    }
}
