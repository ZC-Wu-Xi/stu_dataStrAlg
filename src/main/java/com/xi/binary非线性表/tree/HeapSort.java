package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/5 20:17
 * @description 堆的排序
 */
public class HeapSort {
    /*
    这里设计两个数组：
        源数组：构造堆的元素所存放的数组
        堆数组：实现堆结构的数组
     */

    /**
     * 根据源数组创建堆数组
     * @param heap 堆数组(正常的数组，从0开始存)
     * @param source 源数组(实现堆的数组，从1开始存)
     */
    public static void createHeap(Comparable[] heap, Comparable[] source) {
        // 拷贝数组，从score数组的0索引处开始拷贝，拷贝source.length的长度，拷贝到heap的1~1+source.length索引
        System.arraycopy(source, 0, heap, 1, source.length);

        // 每下沉一次都会对该要下沉节点索引及其子树生成一个堆结构
        // 直到对堆的根节点进行下沉，此时整个堆结构就生成了
        for (int i = (heap.length) / 2; i > 0; i--) { // 对堆的倒数第二层的有子节点的最右边的节点开始下沉，
                                                      // 继续对各个节点节点(从右往左从下往上)进行下沉。
                                                      // 直到对堆的根节点进行下沉，此时整个堆结构就生成了
            // 下沉调整位置
            sink(heap, i, heap.length - 1);

        }
    }

    /**
     * 下沉调整位置
     * @param heap   堆数组
     * @param target 目标索引(要下沉的节点索引)
     * @param range  调整范围(截止到到节点位置)
     *  下沉会对该要下沉节点索引处及其子树生成一个堆结构
     */
    public static void sink(Comparable[] heap, int target, int range) {
        int max;
        while (target * 2 <= range) { // 当前节点有左子节点
            if (target * 2 + 1 <= range) { // 当前节点有右子节点
                if (less(heap, target * 2, target * 2 + 1)) { // 左子节点小于右子节点
                    max = target * 2 + 1;
                } else { // 左子节点大于右子节点
                    max = target * 2;
                }
            } else { // 最大值为左节点
                max = target * 2;
            }

            if (!less(heap, target, max)) { // 当前节点大于等于最大值
                break;
            }
        }
    }

    /**
     * 判断堆中i索引处的元素是否小于j处的元素
     * @return
     */
    public static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    /**
     * 交换堆中i索引处的元素和j索引处的元素
     * @param heap
     * @param i
     * @param j
     */
    public static void exch(Comparable[] heap, int i, int j) {
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
