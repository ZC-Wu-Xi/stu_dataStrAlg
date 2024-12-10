package com.xi.binary非线性表.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/10 16:16
 * @description 赫夫曼树
 * 给定一个数列`{13,7,8,29,6,1}`要求转成一颗赫夫曼树
 *
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 29, 6, 1};
        // 创建的赫夫曼树的根节点
        Node root = HuffmanTree.createHuffmanTree(array);
        System.out.println(root);
        /*
        构建出来的赫夫曼树：
                  64
                 /  \
               29    35
                    /  \
                  14    21
                 / \    / \
                7   7  8  13
                   / \
                  1   6
         */

    }

    /**
     * 创建赫夫曼树
     * @param array
     * @return
     */
    public static Node createHuffmanTree(int[] array) {
        ArrayList<Node> nodes = new ArrayList<>();
        // 1. 把数组中的每一个元素构建成每一个节点
        for (int value : array) {
            // 2. 把构建好的每一个节点存放到集合中去
            nodes.add(new Node(value));
        }

        // 循环构建赫夫曼树  拿出两个最小的，放入一个和
        while (nodes.size() > 1) { // 只有一个了
            // 3. 从小到大进行排序
            Collections.sort(nodes); // Node中重写了compareTo方法

            /**
             * 1. 从小到大进行排序
             * 2. 从排好序的树列中取出根结点最小的两颗二叉树
             * 3. 该新的二叉树根结点权值是前面两颗二叉树节点权值之和
             * 4. 将这颗新的二叉树的根结点的权值大小与数列中剩余的元素再次排序
             */
            // 取出权值最小的两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 根节点是两颗二叉树结点的权值之和 构建新的树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从集合中移除已构建为树的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 将构建好的二叉树的父节点添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
