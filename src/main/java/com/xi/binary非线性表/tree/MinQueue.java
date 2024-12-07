package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/7 14:18
 * @description 最小优先队列：可以获取并删除队列中最小的值
 * 让堆中存放数据元素的数组满足如下特性：
 * 1. 最小的元素放在数组的索引1处。
 * 2. 每个结点的数据总是小于等于它的两个子结点的数据。
 *
 * 最大优先队列的实现我们写在了同级目录下的Heap.java中
 */
public class MinQueue<T extends Comparable<T>> {
    private T[] items;
    private int N;

    /**
     * 构造函数
     * @param capacity 数组容量
     */
    public MinQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    /**
     * i处元素是否小于j处元素
     * @param i
     * @param j
     * @return
     */
    public boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 交换i处元素和j处元素
     * @param i
     * @param j
     */
    public void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * 删除并返回最小值
     * @return 最小值
     */
    public T delMin() {
        // 找到最小值
        T min = items[1];

        // 交换根节点和N处节点位置
        exch(1, N);

        // 删除N(最小值)处节点
        items[N] = null;
        N--;
        sink(1);

        return min;
    }

    /**
     * 插入节点
     * @param t
     */
    public void insert(T t) {
        items[++N] = t;
        // 上浮
        swim(N);
    }

    /**
     * 上浮
     * @param k 当前节点
     */
    private void swim(int k) {
        while (k > 1) { // 当前在父节点下面
            if (less(k, k / 2)) { // 当前节点小于父节点
                exch(k, k / 2); // 交换当前节点和父节点
            }
            k = k / 2; // 当前节点等于父节点，继续上浮
        }
    }

    /**
     * 下沉
     * @param k 当前节点
     */
    private void sink(int k) {
        // 如果没有子节点，那么就不再继续下沉了
        while (2 * k <= N) { // 有左子节点
            // 找出节点中比较小的值
            int min = 2 * k; // 默认左节点最小
            if (2 * k + 1 <= N && less(2 * k + 1, 2 * k)) { // 有右节点，且右节点小于左节点
                int temp = 2 * k + 1; // 最小值为右节点
            }
            if (less(k, min)) { // 当前节点小于最小值，那么就不需要下沉了
                break;
            }
            exch(min, k); // 交换当前节点和最小值
            k = min; // 当前节点等于最小值，继续下沉
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}
