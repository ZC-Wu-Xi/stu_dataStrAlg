package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/8/25 16:30:05
 * @description 冒泡排序
 */
public class BubblingSort {
    //测试数据
    private static final int[] array1 = {4, 5, 6, 3, 2, 1};
    private static final int[] array2 = {1, 2, 3, 4, 1, 4, 6};

    public static void main(String[] args) {
        System.out.println("案例一：冒泡排序");
        int[] sort1 = sort1(array1);
        System.out.println("排序后：" + Arrays.toString(sort1));
        System.out.println("---------------------------------------------");

        System.out.println("案例二：冒泡排序 优化，如果上一轮都是有序的将不在进行后面轮次的比对");
        int[] sort2 = sort2(array2);
        System.out.println("排序后：" + Arrays.toString(sort2));
    }


    /**
     * 冒泡排序 升序
     * 案例一
     * @param array
     * @return
     */
    public static int[] sort1(int[] array) {
        System.out.println("排序前：" + Arrays.toString(array));
        int temp = 0;// 第三方变量 用于冒泡时交换位置
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {// 每次比较完了会少一个待排序序列
                if (array[j] > array[j+1]) {// 前面的比后面大，交换位置
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            System.out.println("第" + (i+1) + "轮排序结果：" + Arrays.toString(array));
        }
        return array;
    }

    /**
     * 冒泡排序 升序
     * 案例二 优化，如果上一轮都是有序的将不在进行后面轮次的比对
     * @param array
     * @return
     */
    public static int[] sort2(int[] array) {
        System.out.println("排序前：" + Arrays.toString(array));
        int temp = 0;// 第三方变量 用于冒泡时交换位置
        for (int i = 0; i < array.length-1; i++) {
            boolean flag = false;// 经过一轮排序是否交换过位置
            for (int j = 0; j < array.length-1-i; j++) {// 每次比较完了会少一个待排序序列
                if (array[j+1] < array[j]) {// 后面的小于前面的，交换位置
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;// 标记这一轮比对交换过位置
                }
            }
            System.out.println("第" + (i+1) + "轮排序结果：" + Arrays.toString(array));
            if (flag == false) {// 这一轮没有交换过位置，不再进行后面轮次的比对
                break;
            }
        }
        return array;
    }

}
