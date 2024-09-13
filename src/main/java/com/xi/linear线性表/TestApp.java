package com.xi.linear线性表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/12 20:19:42
 * @description
 */
public class TestApp {
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
}
