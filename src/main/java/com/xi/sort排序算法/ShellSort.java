package com.xi.sort排序算法;

import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/7 18:15:32
 * @description 希尔排序 升序
 * 希尔排序是把记录按下标的一定增量分组， 对每组使用直接插入排序算法排序；
 * 随着增量(分组个数)逐渐减少， 每组包含的元素越来越多，
 * 当增量减至 1 时， 整个文件恰被分成一组， 算法便终止。
 */
public class ShellSort {
    //测试数据
//    private static final int[] array = {2, 4, 1, 4, 3};
    private static final int[] array = {1, 4, 6, 3, 4, 2, 7, 5, 2};
    // 1,2,6,3,2,4,7,5,4
    // 1,2,2,3,4,4,6,5,7

    public static void main(String[] args) {
        shellSort(array);
        System.out.println("排序后数组：" + Arrays.toString(array));
    }

    /**
     * 负责分组
     * @param array 待排序数组
     */
    public static void shellSort(int[] array) {
        System.out.println("待排序数据 = " + Arrays.toString(array) + "  数组长度：" + array.length);
        int gap = array.length;//  步长，也就是分组的数量

        while (gap > 1) {// 当length=1时无法再进行分组
            gap /= 2;
            shell(array, gap);// 插入排序
        }

    }

    /**
     * 负责分完组后对每组元素进行插入排序
     * @param array 待排序数组
     * @param gap 组的个数
     *  假设有待排序数组中九个元素
     * 第一次分组(4组)
     * 1. i=4, true, temp=array[4]
     *    j=4-4=0,true, array[0]:array[4]比对;
     *    j=0-4=-4, false, break;
     * 2. i=5, true, temp=array[5]
     *    j=5-4=1,true , array[1]:array[5]比对;
     *    j=1-4=-3, false, break;
     * 3. i=6, true, temp=array[6]
     *    j=6-4=2,true , array[2]:array[6]比对;
     *    j=2-4=-2, false, break;
     * 4. i=7, true, temp=array[7]
     *    j=7-4=3,true , array[3]:array[7]比对;
     *    j=2-4=-2, false, break;
     * 5. i=8, true, temp=array[8]
     *    j=8-4=4,true , array[4]:array[8]比对;
     *    j=4-4=0, true, array[0]:array[8]比对;
     *    j=0-4=-4, false, break;
     * 6. i=9,false
     *
     * 第二次分组(2组)
     * 1. i=2, true, temp=array[2]
     *    j=2-2=0,true , array[0]:array[2]比对;
     *    j=0-2=-2,false, break;
     * 2. i=3, true, temp=array[3]
     *    j=3-2=1,true , array[1]:array[3]比对;
     *    j=1-2=-1, false, break;
     * 3. i=4, true, temp=array[4]
     *    j=4-2=2,true , array[2]:array[4]比对;
     *    j=2-2=0,true , array[0]:array[4]比对;
     *    j=0-2=-2, false, break;
     * 4. i=5, true, temp=array[5]
     *    j=5-2=3,true , array[3]:array[5]比对;
     *    j=3-2=1, true, array[1]:array[5]比对；
     *    j=1-2=-1, false, break;
     * 5. i=6, true, temp=array[6]
     *    j=6-2=4,true , array[4]:array[6]比对;
     *    j=4-2=2, true, array[2]:array[6]比对；
     *    j=2-2=0, true, array[0]:array[6]比对；
     *    j=0-2=-2, false, break;
     * 6. i=7, true, temp=array[7]
     *    j=7-2=5,true , array[5]:array[7]比对;
     *    j=5-2=3, true, array[3]:array[7]比对；
     *    j=3-2=1, true, array[1]:array[7]比对；
     *    j=1-2=-1, false, break;
     * 7. i=8, true, temp=array[8]
     *    j=8-2=6,true , array[6]:array[8]比对;
     *    j=6-2=4, true, array[4]:array[8]比对；
     *    j=4-2=2, true, array[2]:array[8]比对；
     *    j=2-2=0, true, array[0]:array[8]比对；
     *    j=0-2=-2, false, break;
     * 8. i=9, false
     *
     * 第三次分组(1组)
     * 1. i=1, true, temp=array[1]
     *    j=1-1=0,true,array[0]:array[1]比对;
     *    j=0-1=-1, false, break;
     * 2. i=2, true, temp=array[2]
     *    j=2-1=1,true , array[1]:array[2]比对;
     *    j=1-1=0,true,array[0]:array[2]比对;
     *    j=0-1=-1, false, break;
     * 3. i=3, true, temp=array[3]
     *    j=3-1=2,true , array[2]:array[3]比对;
     *    j=2-1=1,true , array[1]:array[3]比对;
     *    j=1-1=0,true,array[0]:array[3]比对;
     *    j=0-1=-1, false, break;
     * 4. i=4, true, temp=array[4]
     *    j=4-1=3,true , array[3]:array[4]比对;
     *    j=3-1=2,true , array[2]:array[4]比对;
     *    j=2-1=1,true , array[1]:array[4]比对;
     *    j=1-1=0,true,array[0]:array[4]比对;
     *    j=0-1=-1, false, break;
     * 5. ...
     */
    public static void shell(int[] array, int gap) {
        if (array == null) {
            return;
        }

        int temp = 0;
        for (int i = gap; i < array.length; i++) {// 分好组
            temp = array[i];
            for (int j = i - gap; j >= 0; j -= gap) {// 组内元素进行排序
                if (array[j] > temp) {
                    array[j+gap] = array[j];
                    array[j] = temp;
                } else {
                    break;
                }
            }
        }
        System.out.println("一组排序后(分组数" + gap + ") = " + Arrays.toString(array));
    }
}
