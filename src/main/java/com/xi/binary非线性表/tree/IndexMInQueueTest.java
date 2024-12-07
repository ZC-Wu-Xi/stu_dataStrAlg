package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/7 17:17
 * @description
 */
public class IndexMInQueueTest {
    public static void main(String[] args) {
        String[] string = {"S", "R", "T", "O", "U", "P", "A", "C", "E"};
        IndexMinQueue<String> minQueue = new IndexMinQueue<>(string.length+1);
        for (int i = 0; i < string.length; i++) {
            minQueue.insert(i, string[i]);
        }

        // items[pq[index]]: A C E O P R S T U
        System.out.println(minQueue.size()); // 9
        minQueue.delete(3); // 删除了"O"
        System.out.println(minQueue.size()); // 8
        while (!minQueue.isEmpty()) {
//            System.out.println("删除并返回的最小值：" + string[minQueue.delMin()]);
            System.out.print(string[minQueue.delMin()] + " "); // A C E P R S T U
        }
    }
}
