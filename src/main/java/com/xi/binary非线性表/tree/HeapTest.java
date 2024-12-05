package com.xi.binary非线性表.tree;

/**
 * @author ZC_Wu 汐
 * @date 2024/12/5 20:01
 * @description 堆测试
 */
public class HeapTest {
    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(20);
        heap.insert("S");
        heap.insert("G");
        heap.insert("I");
        heap.insert("E");
        heap.insert("N");
        heap.insert("H");
        heap.insert("O");
        heap.insert("A");
        heap.insert("T");
        heap.insert("P");
        heap.insert("R");

        String del;
        // 删除最大的 直到删完
        while ((del = heap.delMax()) != null) {
            System.out.println("删除的元素：" + del); // T S R P O N I H G E A
        }
    }
}
