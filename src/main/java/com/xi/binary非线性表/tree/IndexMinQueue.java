package com.xi.binary非线性表.tree;

import java.util.NoSuchElementException;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/7 16:04
 * @description 索引最小优先队列
 * 在优先队列的基础上实现通过索引访问已存在于优先队列中的对象，并更新它们
 * 使用到三个数组：
 *  - T[] items: 存储队列中的元素
 *      |a|c|b|h|r|e|f|g|d|
 *  - int[] pq: 存放实现堆结构后items元素索引， items[pq[i]] 就是堆数组
 *      |.|0|2|1|8|5|6|7|3|4|
 *  - int[] qp: 存放items中对应元素实现堆结构的索引 qp与items一一对应
 *      |1|3|2|8|9|5|6|7|4|
 *
 * 借助qp数组可以快速找到items里的元素对应实现堆结构的索引，而无需遍历pq数组
 */
public class IndexMinQueue<T extends Comparable<T>> {
    private T[] items; // 存储元素的数组
    private int[] pq; // 存放实现堆结构后items元素索引，items[pq[i]] 就是堆数组，堆有序
    private int[] qp; // 存放items中一一对应元素实现堆结构的索引 qp与items一一对应，记录pq的逆序，pq的索引是qp中的元素；pq的元素是qp中的索引
    private int N;

    public IndexMinQueue(int size) {
        items = (T[]) new Comparable[size + 1];
        pq = new int[size + 1];
        qp = new int[size + 1];
        N = 0;

        // 给qp中默认值全部是-1
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1; // 初始化qp数组，-1表示该索引位置没有元素
        }
    }

    /**
     * 判断索引i对应的元素是否比j处元素小
     * @param i
     * @param j
     * @return
     */
    public boolean less(int i, int j) {
        // 通过qp找到items中对应的元素
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    /**
     * 交换i和j处位置的元素
     * 调整pq里的元素，并调整pq的逆序数组pq
     * @param i
     * @param j
     */
    public void exch(int i, int j) {
        // 将pq堆排序元素数组存放的索引进行交换
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        // 将qp数组一一对应的堆排序索引元素进行交换
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 判断当前k对应的元素是否存在
     * @param k
     * @return
     */
    public boolean contains(int k) {
        return qp[k] != -1; // qp不等于-1就说明有值(初始化时为-1)
    }

    /**
     * 获取最小的索引
     * @return items的最小元素的索引
     */
    public int minIndex() {
        return pq[1];
    }

    /**
     * 在items的索引i处插入元素t
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (contains(i)) {
            throw new IllegalArgumentException("该索引处已存在元素");
        }
        N++;
        items[i] = t;

        pq[N] = i; // 将插入元素的索引i存入pq堆排序数组的最后
        qp[i] = N; // qp数组存放pq的逆序，将插入元素的索引i存入qp数组，qp数组索引i对应的元素是N

        // 上浮
        swim(N); // 调整堆结构，将插入的元素上浮到合适位置
    }

    /**
     * @return pq中最小元素的索引
     */
    public int delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("堆为空");
        }
        int minIndex = pq[1]; // 获取堆中最小元素的索引
        if (N == 1) {
            // 删除items中最小的值
            items[minIndex] = null;
            // 删除qp中索引对应pq[1]位置元素
            qp[pq[1]] = -1;
            // 删除pq中索引1的值
            pq[1] = -1;
            N--;
            return minIndex;
        }

        // 与N位置进行交换
        exch(1, N);

        // 删除qp中索引对应pq[N]位置元素
        qp[pq[N]] = -1;
        // 删除pq中索引N的值
        pq[N] = -1;
        // 删除items中最小的值
        items[minIndex] = null;

        N--;
        sink(1); // 调整堆结构，将交换后的元素下沉到合适位置
        return minIndex;
    }

    /**
     * 删除索引i处的元素
     * @param i 堆排序队列pq中的索引
     */
    public void delete(int i) {
        int k = pq[i]; // 获取索引i对应的堆排序队列pq中的索引
        exch(k, N); // 将索引i对应的元素与最后一个元素交换

        qp[pq[N]] = -1; // 删除qp中索引对应pq[N]位置元素
        pq[N] = -1; // 删除pq中索引N的值
        items[i] = null; // 删除items中最小的值

        N--;

        sink(k); // 调整堆结构，将交换后的元素下沉到合适位置
        swim(k); // 调整堆结构，将交换后的元素上浮到合适位置

    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) { // 当前节点k有子节点
            int min = 2 * k; // 默认左子节点为最小节点
            if (2 * k + 1 <= N && less(2 * k + 1, 2 * k)) { // 右子节点存在且小于左子节点
                min = 2 * k + 1; // 右子节点为最小节点
            }

            if (less(k, min)) { // 当前节点k小于最小子节点
                break; // 跳出循环
            }

            exch(k, min); // 交换位置
            k = min; // 往下走
        }
    }

    /**
     * 上浮
     * @param k
     */
    private void swim(int k) {
        while (k > 1) {
            if (less(k, k / 2)) { // 当前节点小于父节点
                exch(k, k / 2); // 交换位置
            }
            k = k / 2; // 往上走
        }


    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

}
