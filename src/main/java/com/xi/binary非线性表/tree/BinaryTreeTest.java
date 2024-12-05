package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/3 20:13
 * @description 二叉树的前中后遍历、遍历查找(这里存储时未使用左小右大)
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        Node root = new Node(1, "小王");
        Node node2 = new Node(2, "小李");
        Node node3 = new Node(3, "小周");
        Node node4 = new Node(4, "小邓");
        Node node5 = new Node(5, "小刘");

        root.left = node2;
        node2.left = node3;

        root.right = node4;
        node4.right = node5;
        /*
                  1
                ↙   ↘
              2       4
            ↙           ↘
          3               5
         */

        binaryTree.root = root;

        // 前序遍历
        binaryTree.preSelect(); // 1，2，3，4，5
        // 中序遍历
        binaryTree.infixSelect(); // 3, 2, 1, 4, 5
        // 后序遍历
        binaryTree.postSelect(); // 3, 2, 5, 4, 1
        // 前中后序遍历查找
        System.out.println("binaryTree.preSearch(2) = " + binaryTree.preSearch(2)); // binaryTree.preSearch(2) = Node{no=2, name=小李, left=Node{no=3, name=小周, left=null, right=null}, right=null}
        System.out.println("binaryTree.infixSearch(3) = " + binaryTree.infixSearch(3)); // binaryTree.infixSearch(3) = Node{no=3, name=小周, left=null, right=null}
        System.out.println("binaryTree.postSearch(2) = " + binaryTree.postSearch(2)); // binaryTree.postSearch(2) = Node{no=2, name=小李, left=Node{no=3, name=小周, left=null, right=null}, right=null}
    }
}
