package com.xi.linear线性表.队列;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/24 19:23
 * @description 数组实现队列 循环队列
 */
public class ArrayQueue {
    // 定义数组
    private int[] array;
    // 数组大小
    private int maxSize;
    // 头指针
    private int headPoint;// 队列头部，指向队头元素
    // 尾指针
    private int lastPoint;// 队列尾部，指向队尾元素的下一个位置

    public static void main(String[] args) {
        ArrayQueue circularQueue = new ArrayQueue(5);
        circularQueue.addQueue(10);
        circularQueue.addQueue(11);
        circularQueue.addQueue(12);
        circularQueue.addQueue(13);
        circularQueue.addQueue(14);

        circularQueue.addQueue(15);

        circularQueue.list(); // 显示队列元素
        System.out.println();

        System.out.println(circularQueue.getQueue()); // 取出元素
        System.out.println(circularQueue.getQueue()); // 取出元素
        circularQueue.addQueue(16);
        circularQueue.addQueue(17);
        circularQueue.addQueue(18);
        circularQueue.addQueue(19);
//        circularQueue.list();
        System.out.println(circularQueue.getQueue());
        System.out.println(circularQueue.getQueue());
        System.out.println(circularQueue.getQueue());
        System.out.println(circularQueue.isEmpty());
        circularQueue.list(); // 显示剩余元素

    }

    public ArrayQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("队列大小必须是正整数");
        }
        this.maxSize = maxSize;
        array = new int[maxSize];
        headPoint = 0;
        lastPoint = -1;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return headPoint == lastPoint;// 头尾相遇，队列为空
    }

    /**
     * 队列是否已满
     * @return
     */
    public boolean isFull() {
        if (lastPoint == -1) {
            return false;
        }
        // 当队尾的下一个位置为头部时，队列满
        return (lastPoint + 1) % (maxSize) == headPoint;
    }

    /**
     * 往队列添加元素
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满...");
            return;
        }
        lastPoint = (lastPoint + 1) % maxSize; // 更新尾指针
        array[lastPoint] = n; // 将元素加入队尾

    }

    /**
     * 取出一个元素
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
//            return -1;
        }
        int value = array[headPoint]; // 获取队头元素
        headPoint = (headPoint + 1) % (maxSize); // 更新头指针
        return value;
    }

    /**
     * 查看队列中的元素
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
        }


        for (int i = headPoint; i != lastPoint+1; i = (i + 1) % (maxSize+1)) {
            System.out.printf("array[%d]=%d \n", i, array[i]);
        }
    }

    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
        }
        return array[headPoint];
    }
}
