package com.xi.binary非线性表.huffmanTree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/10 16:18
 * @description
 */
public class Node implements Comparable<Node> {
    // 权值
    public int value;
    // 左节点
    public Node left;
    // 右节点
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value; // 按照value权值从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

