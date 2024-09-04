package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/4 15:58:21
 * @description 快速排序 升序
 * 快速排序是对冒泡排序的一种改进。 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另一部分所有的数据都要小， 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行， 以此达到整个数据变成有序序列
 */
public class QuickSort {
    //测试数据
//    private static final int[] array1 = {2, 6, 7, 1, 4, 6, 5};
    private static final int[] array1 = {2, 4, 1, 4, 3};
    private static final int[] array2 = {1, 4, 6, 3, 4, 2, 7, 5, 2};

    public static void main(String[] args) {
        quickSort(array1, 0, array1.length-1);
        System.out.println("排序后array1 = " + Arrays.toString(array1));
        System.out.println("===============================================");
        quickSort(array2, 0, array2.length-1);
        System.out.println("排序后array2 = " + Arrays.toString(array2));

    }

    /**
     * 快速排序 升序 递归
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // array2 = {1, 4, 6, 3, 4, 2, 7, 5, 2};
        // 控制递归
        if (startIndex >= endIndex) {
            return;
        }
        int pIndex = partition(arr, startIndex, endIndex);// 得到基准元素排序后的位置
        System.out.println("-----: " + Arrays.toString(arr));
        quickSort(arr, startIndex, pIndex-1);
        quickSort(arr, pIndex+1,  endIndex);

    }

    /**
     * 快速排序得到基准元素完成排序后在数组中的位置 会由基准元素分割出两部分数据，左边是比基准元素小的，右边是比基准元素大的
     * @param arr 待排序数组
     * @param startIndex 左指针
     * @param endIndex 右指针
     * @return 返回基准元素的索引位置 这个索引位置就是基准元素排完序后的位置
     */
    public static int partition(int[] arr, int startIndex, int endIndex) {
        int p = arr[startIndex];// 基准元素
        System.out.print("基准元素p=" + p);
        int l = startIndex;// 左指针
        int r = endIndex;// 右指针

        while (l != r) {
            // 右指针执行 右指针元素与基准元素比较，满足右指针元素大于基准元素，右指针左移，直至右指针元素小于基准元素
            while (l < r && arr[r] > p) {
                r--;
            }
            // 左指针执行 左指针元素与基准元素比较，满足左指针元素小于基准元素，左指针右移，直至左指针元素大于基准元素
            while (l < r && arr[l] <= p) {
                l++;
            }

            if (l < r) {
                // 左右指针位置的数据交换位置
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }

        // 基准元素 和 l与r重合元素 交换位置， 交换位置后生成由基准元素分割的两部分数组
        arr[startIndex] = arr[l];
        arr[l] = p;
        System.out.println("  基准元素" + p + "的索引为：" + l);

        // 返回基准元素的索引位置 这个索引位置就是基准元素排完序后的位置
        return l;
    }
}
