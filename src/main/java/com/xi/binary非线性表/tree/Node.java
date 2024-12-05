package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/3 19:54
 * @description 二叉树的前中后遍历、遍历查找(存储时未使用左小右大)
 */
public class Node {
    public int no; // 键
    public String name; // 值
    public Node left;
    public Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历：先输出父结点， 再遍历左子树和右子树
     */
    public void preSelect() {
        // 中
        System.out.println(this);
        // 左
        if (this.left != null) {
            this.left.preSelect();
        }
        // 右
        if (this.right != null) {
            this.right.preSelect();
        }
    }

    /**
     * 中序遍历： 先遍历左子树， 再遍历父结点， 再遍历右子树
     */
    public void infixSelect() {
        // 左
        if (this.left != null) {
            this.left.infixSelect();
        }
        // 中
        System.out.println(this);
        // 右
        if (this.right != null) {
            this.right.infixSelect();
        }
    }

    /**
     * 后序遍历： 先遍历左子树， 再遍历右子树， 最后遍历父结点
     */
    public void postSelect() {
        // 左
        if (this.left != null) {
            this.left.postSelect();
        }
        // 右
        if (this.right != null) {
            this.right.postSelect();
        }
        // 中
        System.out.println(this);
    }

    /**
     * 根据节点no前序遍历查找
     * @param no 查找的节点no
     * @return 查找出的节点
     */
    public Node preSearch(int no) {
        // 父节点
        if (this.no == no) {
            return this;
        }

        Node node = null;

        // 左节点
        if (this.left != null) {
            node = this.left.preSearch(no);
        }
        if (node != null) {
            return node;
        }

        // 右节点
        if (this.right != null) {
            node = this.right.preSearch(no);
        }
        return node;
    }

    /**
     * 根据节点no中序遍历查找
     * @param no 查找的节点no
     * @return 查找出的节点
     */
    public Node infixSearch(int no) {
        Node node = null;
        // 左节点
        if (this.left != null) {
            node = this.left.infixSearch(no);
        }
        if (node != null) {
            return node;
        }

        // 父节点
        if (this.no == no) {
            return this;
        }

        // 右节点
        if (this.right != null) {
            node = this.right.infixSearch(no);
        }
        return node;
    }

    /**
     * 根据节点no后序遍历查找
     * @param no 查找的节点no
     * @return 查找出的节点
     */
    public Node postSearch(int no) {
        Node node = null;
        // 左节点
        if (this.left != null) {
            node = this.left.postSearch(no);
        }
        if (node != null) {
            return node;
        }
        // 右节点
        if (this.right != null) {
            node = this.right.postSearch(no);
        }
        if (node != null) {
            return node;
        }
        // 父节点
        if (this.no == no) {
            return this;
        }

        return node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name=" + name +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
