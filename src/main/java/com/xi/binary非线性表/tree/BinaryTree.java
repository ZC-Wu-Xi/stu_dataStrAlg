package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/3 20:09
 * @description 二叉树的前中后遍历、遍历查找(存储时未使用左小右大)
 */
public class BinaryTree {
    public Node root;

    /**
     * 前序遍历
     */
    public void preSelect() {
        if (root != null) {
            this.root.preSelect(); // 调用node的前序遍历
        } else {
            System.out.println("空的二叉树");
        }
    }

    /**
     * 中序遍历
     */
    public void infixSelect() {
        if (root != null) {
            this.root.infixSelect(); // 调用node的中序遍历
        } else {
            System.out.println("空的二叉树");
        }
    }

    /**
     * 后序遍历
     */
    public void postSelect() {
        if (root != null) {
            this.root.postSelect(); // 调用node的后序遍历
        } else {
            System.out.println("空的二叉树");
        }
    }

    /**
     * 前序遍历查找
     */
    public Node preSearch(int no) {
        Node node = null;
        if (root != null) {
            node = this.root.preSearch(no); // 调用node的后序遍历
        } else {
            System.out.println("空的二叉树");
        }
        return node;
    }

    /**
     * 中序遍历查找
     */
    public Node infixSearch(int no) {
        Node node = null;
        if (root != null) {
            node = this.root.infixSearch(no); // 调用node的后序遍历
        } else {
            System.out.println("空的二叉树");
        }
        return node;
    }

    /**
     * 后序遍历查找
     */
    public Node postSearch(int no) {
        Node node = null;
        if (root != null) {
            node = this.root.postSearch(no); // 调用node的后序遍历
        } else {
            System.out.println("空的二叉树");
        }
        return node;
    }
}
