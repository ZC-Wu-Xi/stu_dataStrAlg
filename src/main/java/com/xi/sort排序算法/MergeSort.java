package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/8 11:20:34
 * @description
 */
public class MergeSort {
    //测试数据
    private static final int[] array = {5, 2, 4, 5, 1, 7, 8, 3};

    public static void main(String[] args) {
        System.out.println("待排序数据：" + Arrays.toString(array) + " 比对数据为 左边：左指针到中指针 和 右边：中指针+1到右指针");
        int[] temp = new int[array.length];
        mSort(array, 0, array.length - 1, temp );
        System.out.println("array = " + Arrays.toString(array));;

    }

    /**
     * 向下分解
     * @param array 待排序数组
     * @param left 左指针
     * @param right 右指针
     * @param temp 临时数组
     */
    public static void mSort(int[] array, int left, int right, int[] temp) {
        System.out.println("mSort()  left" + left + "<" + "right" + right + "?" + (left<right));
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左进行分解
            mSort(array, left, mid, temp);
            // 向右进行分解
            mSort(array, mid + 1, right, temp);
            // 归并
            sort(array, left, right, mid, temp);
        }
    }

    /**
     * 归并
     * @param array 待排序数组
     * @param left 左指针
     * @param right 右指针()
     * @param mid 中间指针(mid+1为右序列开始比对的元素)
     * @param temp 临时数组
     */
    public static void sort(int[] array, int left, int right, int mid, int[] temp) {
        int i = left;// 指向前面的序列开始位置
        int j = mid + 1;// 指向后面的序列开始位置
        int t = 0;// 记录临时数组的下标

        // 进行比对
        while (i <= mid && j <= right) {// 左边序列和右边序列都不超过各自索引时循环 比对数据的索引范围为left-right
            if (array[i] <= array[j]) {// 左边小
                temp[t] = array[i];
                t++;// 临时数组下标右移
                i++;// 左指针右移
            } else {// 右边小
                temp[t] = array[j];
                t++;// 临时数组下标右移
                j++;// 右指针右移
            }
        }// 循环结束后有可能左边或右边只有一边指针走到最后，另一边元素需要平移到临时数组

        // 将左边有序序列剩余的元素平移到临时数组后面
        while (i <= mid) {
            temp[t] = array[i];
            t++;// 临时数组下标右移
            i++;// 左指针右移
        }
        // 将右边有序序列剩余的元素平移到临时数组后面
        while (j <= right) {
            temp[t] = array[j];
            t++;// 临时数组下标右移
            j++;// 右指针右移
        }

        // 把临时数组(已排序完)中的元素拷贝到array中去
        t = 0;
        int index = left;
        while (index <= right) {
            array[index] = temp[t];
            index++;
            t++;
        }
        System.out.println("sort() 归并排序左指针：" + left + " 中指针：" + mid + "  右指针：" + right + " 比对数据的索引范围：" + left + "-" + right + " 排序后数组:" + Arrays.toString(array));
    }
}
