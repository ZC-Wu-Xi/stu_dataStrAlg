package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/7 11:16:36
 * @description 排序算法 升序
 * 第一次从待排序的数据元素中选出最小（或最大） 的一个元素， 存放在序列的起始位置，
 * 然后再从剩余的未排序元素中寻找到最小（大） 元素， 然后放到已排序的序列的末尾。
 * 以此类推， 直到全部待排序的数据元素的个数为零。 选择排序是不稳定的排序方法。
 */
public class SelectSort {
    //测试数据
//    private static final int[] array = {2, 4, 1, 4, 3};
    private static final int[] array = {1, 4, 6, 3, 4, 2, 7, 5, 2};

    public static void main(String[] args) {
        selectSort(array);
        System.out.println("array = " + Arrays.toString(array));

    }

    /**
     * 选择排序 升序
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int minIndex = i;// 遍历部分的最小值索引
            int min = array[minIndex];

            for (int j = i+1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[minIndex];
                }
            }

            if (i != minIndex) {
                // 最小值和基准值交换位置
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
