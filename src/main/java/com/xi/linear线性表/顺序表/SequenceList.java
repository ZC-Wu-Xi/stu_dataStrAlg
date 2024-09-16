package com.xi.linear线性表.顺序表;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/9 17:59:04
 * @description 顺序表数组
 */
public class SequenceList<T> implements Iterable<T> {
    private T[] eles;// 存储元素的数组
    private int N;// 线性表的长度

    public static void main(String[] args) {
        SequenceList<String> stringSequenceList = new SequenceList<>(5);

        stringSequenceList.insert("孙悟空");
        stringSequenceList.insert("猪八戒");
        stringSequenceList.insert("猪八戒");
        stringSequenceList.insert("白骨精");

        stringSequenceList.insert(1, "唐僧");

        stringSequenceList.remove(2);
        String s = stringSequenceList.get(1);

//        Iterator iterator = stringSequenceList.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + ", ");
//        }
        for (String str :
                stringSequenceList) {
            System.out.println(str);
        }
    }

    /**
     * 构造方法
     * @param capacity 容量大小
     */
    public SequenceList(int capacity) {
        this.eles = (T[])new Object[capacity];
        this.N = 0;
    }

    @Override
    public Iterator iterator() {
        return new MyIterable();
    }

    /**
     * 自定如何返回当前顺序表中的元素 向外部提供遍历的方式
     */
    private class MyIterable implements Iterator {

        // 指定指针
        private int cusor;
        public MyIterable() {
            cusor = 0;
        }

        /**
         * 判断是否还具备下一个元素 向外部提供遍历的方式
         * @return
         */
        @Override
        public boolean hasNext() {
            return cusor < N;
        }

        /**
         * 获取下一个元素 向外部提供遍历的方式
         * @return
         */
        @Override
        public Object next() {
            return eles[cusor++];
        }
    }



    /**
     * 清空线性表
     */
    public void clear() {
        this.N = 0;
    }

    /**
     * 判断是否为空
     * @return 返回true为空，返回false为非空
     */
    public boolean isEntry() {
        return this.N == 0;
    }

    /**
     * 获取线性表中的长度
     * @return 线性表的长度
     */
    public int length() {
        return this.N;
    }

    /**
     * 获取指定位置的元素
     * @param i 索引
     * @return 该索引处的元素
     */
    public T get(int i) {
        return eles[i];
    }

    /**
     * 插入元素(往后面追加)
     * @param t 插入的元素
     */
    public void insert(T t) {
        if (N == eles.length) {
            resize(2*eles.length);// 扩容
        }
        eles[N++] = t;// 插入一个元素后长度+1
    }

    /**
     * 往指定位置插入元素
     * @param i 插入的位置
     * @param t 插入的元素
     */
    public void insert(int i, T t) {
        if (N == eles.length) {
            resize(2*eles.length);// 扩容
        }
        for (int index = N; index > i; index--) {// 后面的值依次后移
            eles[index] = eles[index-1];
        }
        eles[i] = t;// 为该索引赋值
        N++;// 插入一个元素后长度+1
    }

    /**
     * 删除并返回指定索引处的元素
     * @param i 删除元素的索引
     * @return 删除的元素
     */
    public T remove(int i) {
        T current = eles[i];
        for (int index = i; index < N-1; index++) {// 后面的值依次前移
            eles[index] = eles[index+1];
        }
        eles[N-1] = null;
        N--;// 删除一个元素后长度-1
        if (N < eles.length/4) {
            resize(eles.length/2);// 不足原来的1/4，缩小容量
        }
        return current;
    }

    /**
     * 返回线性表中首次出现的该元素的索引，没有该元素返回-1
     * @param t 查询的元素
     * @return 元素的位置
     */
    public int indexOf(T t) {
        for (int i = 0; i < N; i++) {
            if (eles[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 修改数组大小
     * @param newSize 新的数组大小
     */
    private void resize(int newSize) {
        T[] temp = eles;

        // 创建新的数组
        eles = (T[])new Object[newSize];

        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }
    }
}
