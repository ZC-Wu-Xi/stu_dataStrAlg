package com.xi.binary非线性表;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/5 19:19
 * @description 二叉树 左小右大
 */
public class BinaryTree<K extends Comparable<K>, V> {
    // 树的根节点
    private Node root;
    // 记录树的元素个数
    private int N;

    public static void main(String[] args) {
        BinaryTree<String, String> binaryTree = new BinaryTree<>();
        binaryTree.put("1", "101");
        binaryTree.put("2", "102");
        binaryTree.put("3", "103");
        binaryTree.put("4", "104");
        binaryTree.put("5", "105");
        System.out.println(binaryTree.size()); // 5
        binaryTree.delete("2");
        binaryTree.delete("22");
        System.out.println(binaryTree.size()); // 4
        System.out.println(binaryTree.get("3")); // 103
    }

    /**
     * 插入一个节点
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * 往指定的树中插入节点
     * @param node
     * @param key
     * @param value
     */
    public Node put(Node node, K key, V value) {
        // 判断要插入的节点是否为空
        if (node == null) {
            N++;
            return new Node(key, value, null, null);
        }

        int cmp = key.compareTo(node.key);
        if (cmp > 0) { // cmp>0,说明key大于目标节点key
            node.right = put(node.right, key, value);

        } else if (cmp < 0) { // cmp<0,说明key小于目标节点key
            node.left = put(node.left, key, value);
        } else { //cmp=0,说明找到目标节点替换原来的值
            node.value = value;
        }
        return node;
    }

    /**
     * 根据 key 查找 value
     * @param key
     * @return
     */
    public V get(K key) {
        return get(root, key);
    }

    /**
     * 从指定的子树中找出指定 key 的 value
     * @param node
     * @param key
     * @return
     */
    public V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) { // 查找的key大于指定子树根节点的key
            return get(node.right, key);
        } else if (cmp < 0) { // 查找的key小于指定根节点的key
            return get(node.left, key);
        } else { // 查找到该值
            return node.value;
        }
    }

    /**
     * 删除 value
     * @param key
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    /**
     * 从指定的子数中删除指定 key 的 value
     * @param node
     * @param key
     * @return
     */
    public Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) { // 删除的节点key大
            node.right = delete(node.right, key);
        } else if (cmp < 0) { // 删除的节点key小
            node.left = delete(node.left, key);
        } else { // 找到需要删除的节点key  此时node就是需要删除的节点
            if (node.left == null) { // 待删除节点没有左子树
                N--;
                return node.right; // ‘删除子树’的右子树就是删除该key节点之后的该子树
            }
            if (node.right == null) { // 待删除节点没有右子树
                N--;
                return node.left; // ‘删除子树’的左子树就是删除该key节点之后的该子树
            }

            // 当前节点的左右子树都存在，从右子树中找最小左节点
            Node minNode = node.right; // 待删除节点的右子树
            while (minNode != null) {
                minNode = minNode.left;
            } // minNode已经是右子树的最小左节点

            // 删除该右子树的最小左子节点
            Node temp = node.right; // 该节点的右子树
            while (temp.left != null) {
                if (temp.left.left == null) { // temp.left为柚子树中的最小左子节点
                    temp.left = null; // 删除该节点(该节点右子树的最小左子节点)
                } else { //  temp.left还有左子节点  temp.left.left != null
                    temp = temp.left; // 临时节点变为左子节点
                }
            } // 成功删除右子树的最小左节点

            // 将右子树的最小右节点插入到删除节点node的位置
            minNode.left = node.left;
            minNode.right = node.right;
            node = minNode; // 以右子树的最小左节点为根节点的删除之后的子树
            N--;
        }
        return node; // 删除之后的节点
    }

    /**
     * 返回树中元素的个数
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 二叉树节点类
     */
    private class Node {
        // 记录节点key
        private K key;
        // 记录节点value
        private V value;
        // 记录左子节点
        private Node left;
        // 记录右子节点
        private Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}

