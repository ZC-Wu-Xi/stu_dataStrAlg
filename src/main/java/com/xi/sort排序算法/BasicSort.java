package com.xi.sort排序算法;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ZC_Wu 汐
 * @date 2024/8/25 12:36:32
 * @description 基数排序|桶子法 升序 案例一：用集合写，案例二：用二维数组写
 *  首先确定最大值的位数；
 *  依次取出每个待排序数据的个位数的值，每个待排序数据按照个位数的值放入指定的桶(0-9九个桶对应个位数的值)中，所有值都放入桶中后将桶中的值依次拿出来并清空桶中数据，拿出来的值形成的只按个位数排序的值；
 *  然后对拿出的值进行十位、百位...的相同操作，直至排到待排序数据的最大位数，此时取出的值就是拍好的值
 */
public class BasicSort {
    //测试数据
    private static final int[] array1 = {53, 3, 542, 728, 14, 214};
    private static final int[] array2 = {53, 542, 3, 63, 14, 214, 154, 748, 751, 1616};

    public static void main(String[] args) {
        System.out.println("案例一: 基数排序，集合方式");
        int[] sort1 = sort1(array1);// 测试数据：array1，array2
        System.out.println("排序后：" + Arrays.toString(sort1));
        System.out.println("---------------------------------------------");

        System.out.println("案例二：基数排序，二维数组方式");
        int[] sort2 = sort2(array2);// 测试数据：array1，array2
        System.out.println("排序后：" + Arrays.toString(sort2));
    }

    /**
     * 基数排序 升序
     * 案例一：用集合写
     * @param array
     * @return
     */
    public static int[] sort1(int[] array) {
        System.out.println("原数据：" + Arrays.toString(array));
        // 1. 找出最大值
        int max = 0;
        for (int temp : array) {
            if (temp > max) {
                max = temp;
            }
        }

        // 2. 计算最大值的位数
        int maxDigit = 0;// 位数
        while (max != 0) {
            max /= 10;// 除以10
            maxDigit++;
        }

        // 3. 创建0-9的桶 (创建一个集合，里面放10个桶，每个桶都是一个集合， 约定每个桶存放与该桶索引相同的指定位数值的待排序数据)
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        // 4. 从个位数到最高位数进行"最高位数"次排序
        int moid = 10;// 定义取模数 从个位数开始取
        int div = 1;// 定义除数 从个位数开始取
        for (int i = 0; i < maxDigit; i++,moid*=10,div*=10) {// 控制一共取多少次位数进行排序, 每取一个值取模数和除数都*10
            // 4.1 取指定位数的值,将值放入指定的桶中
            for (int j = 0; j < array.length; j++) {// 对待排序的数据依次处理
                int num = array[j] % moid / div;// 取指定位数的值 eg: 42个位数的值：42%10/1=2, 42十位数的值：42%100/10=4
                buckets.get(num).add(array[j]);// 将值放入指定的桶中
            }
            // 4.2
            int index = 0;
            for (int j = 0; j < buckets.size(); j++) {// 对每个桶进行操作
                ArrayList<Integer> list = buckets.get(j);// 从0号(约定索引就是编号)桶开始获取对应桶内当前的值
                for (int k = 0; k < list.size(); k++) {// 对桶内每个数据排序
                    array[index++] = list.get(k);// 将桶内的值放入原待排序数组中，实现该位数的排序
                }
                buckets.get(j).clear();// 清空已取出数据的桶，留给更高位排序时使用
            }
            System.out.println("第" + (i+1) + "位排序完后：" + Arrays.toString(array));
        }
        return array;
    }

    /**
     * 基数排序 升序
     * 案例二：用二维数组写
     * @param array
     * @return
     */
    public static int[] sort2(int[] array) {
        System.out.println("原数据：" + Arrays.toString(array));
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxDigit = (max + "").length();// 获取最大数的位数

        int[][] buckets = new int[10][array.length];// 二维数组 初始化十个桶
        int[] bucketsElementCount = new int[10];// 0-9号桶内的元素数

        int moid = 10;// 定义取模数 从个位数开始取
        int div = 1;// 定义除数 从个位数开始取
        // 从个位数到最高位数进行"最高位数"次排序
        for (int i = 0; i < maxDigit; i++,moid*=10,div*=10) {// 控制一共取多少次位数进行排序, 每取一个值取模数和除数都*10
            // 第一次比较 个位数
            for (int j = 0; j < array.length; j++) {
                int locationElement = array[j] % moid / div;// 个位数的值
                buckets[locationElement][bucketsElementCount[locationElement]] = array[j];// 将元素放入桶中 在这个桶的指定索引(索引为当前元素数)处放入该元素
                bucketsElementCount[locationElement]++;// 这个桶中的元素数+1
            }
            int index = 0;
            // 取出桶中元素
            for (int j = 0; j < buckets.length; j++) {// 对每个桶进行操作
                for (int k = 0; k < bucketsElementCount[j]; k++) {// 桶内有几个元素就取几次
                    array[index++] = buckets[j][k];// 取出桶内元素
                }
                bucketsElementCount[j] = 0;// 取出后桶内元素为0
            }
            System.out.println("第" + (i+1) + "位排序完后：" + Arrays.toString(array));
        }
        return array;
    }


}
