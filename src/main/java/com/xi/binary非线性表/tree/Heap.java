package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/5 19:21
 * @description 堆
 * 堆的特点
 *  - 每个结点大于等于它的两个子结点，但这两个子结点的顺序并没有做规定
 *  - 完全二叉树，除了树的最后一层结点不需要是满的，其它的每一层从左到右都是满的，如果最后一层结点不是满的，那么要求左满右不满
 *  - 一般使用数组实现，0索引处不放节点，从1索引(根节点)处开始(根节点不能为0，如果为0则下面的公式不生效了)
 *  - `a[k]`向上一层，就令` k` 等于 `k/2`,向下一层就令 `k` 等于 `2k` 或 `2k+1`
 */
public class Heap<T extends Comparable<T>> {
    // 实现堆的数组
    private T[] items;

    // 记录堆的大小
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity+1];
        N = 0;
    }

    /**
     * 删除root节点
     * 删除最大的节点
     * @return T 删除的最大的节点
     */
    public T delMax() {
        T max = items[1]; // 取出最大元素(根节点最大)
        exch(1, N); // 将最大元素放到最后 最后一个节点放到根节点
        items[N] = null; // 删除最大的元素
        N--; // 堆大小减1

        // 调整删除后的平衡位置
        sink(1);
        return max;
    }

    /**
     * 新增节点
     * @param t
     */
    public void insert(T t) {
        this.items[++N] = t; // 把插入的节点放到堆的最后
        swim(N); // 上浮操作
    }

    /**
     * 判断索引 i处的元素是否小于j处的元素
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        // i - j < 0 ?
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 交换索引i和j处的元素
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * 上浮操作
     * 插入节点时使用
     * @param k 新加入的节点索引
     */
    private void swim(int k) {
        while (k > 1) { // 如果已经到了根结点，就不需要循环了
            if (less(k / 2, k)) { // 父节点小于子节点
                exch(k / 2, k); // 交换父节点和子节点位置
            }
            k = k / 2; // 新插入的节点索引更新为k/2
        }
    }

    /**
     * 下沉操作
     * 平衡删除k后堆的结构
     * 将最大的值放到最后一层
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) { // k有子节点
            int max;
            if (2 * k + 1 <= N) { // k有右子节点
                if (less(2 * k, 2 * k + 1)) { // 左子节点小于右子节点
                    max = 2 * k + 1; // 最大子节点为右子节点
                } else { // 右子节点小于左子节点
                    max = 2 * k; // 最大子节点为左子节点
                }
            } else { // k只有左子节点
                max = 2 * k; // 最大子节点为左子节点
            }

            if (!less(k, max)) { // 当前节点大于等于最大子节点
                break; // 停止下沉
            }
            // 当前节点小于最大值
            exch(k, max); // 交换当前节点和最大子节点
            k = max; // 更新当前节点为最大子节点
        }
    }

}
