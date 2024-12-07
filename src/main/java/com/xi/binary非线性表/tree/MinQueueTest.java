package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/7 14:46
 * @description 最小优先队列：可以获取并删除队列中最小的值
 * 最小优先队列测试
 */
public class MinQueueTest {
    public static void main(String[] args) {
        String[] string = {"S", "R", "T", "O", "U", "P", "A", "C", "E"};
        MinQueue<String> minQueue = new MinQueue<>(string.length);
        for (String str : string) {
            minQueue.insert(str);
        }

        while (!minQueue.isEmpty()) {
            System.out.println("删除并返回的最小值：" + minQueue.delMin());
        }
    }
}
