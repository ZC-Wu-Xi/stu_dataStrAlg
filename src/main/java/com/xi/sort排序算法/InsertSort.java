package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/4 18:03:38
 * @description 插入排序 升序
 */
public class InsertSort {
    //测试数据
    private static final int[] array = {1, 4, 6, 3, 4, 2, 7, 5, 2};

    public static void main(String[] args) {
        insertSort(array);
    }

    /**
     * 插入排序 升序
     * @param array 待排序数组
     */
    public static void insertSort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j-1]) {
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                } else {
                    break;
                }
            }
        }
        System.out.println("array = " + Arrays.toString(array));
    }
}
