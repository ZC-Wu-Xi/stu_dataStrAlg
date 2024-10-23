package com.xi.linear线性表.队列;

import com.xi.linear线性表.栈.Stack;


/**
 * @author ZC_Wu 汐
 * @date 2024/10/18 10:13
 * @description 基于栈实现队列
 * 需要两个栈
 */
public class StackQueue {

    public Stack<Integer> getStack1() {
        return stack1;
    }

    public void setStack1(Stack<Integer> stack1) {
        this.stack1 = stack1;
    }

    public Stack<Integer> getStack2() {
        return stack2;
    }

    public void setStack2(Stack<Integer> stack2) {
        this.stack2 = stack2;
    }

    private Stack<Integer> stack1; // 用来添加数据
    private Stack<Integer> stack2; // 用来获取数据

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.insertQueue(1);
        stackQueue.insertQueue(2);
        stackQueue.insertQueue(3);
        stackQueue.insertQueue(4);
        stackQueue.insertQueue(5);
        stackQueue.insertQueue(6);

        while (!stackQueue.getStack1().isEmpty() || !stackQueue.getStack2().isEmpty()) {
            System.out.println(stackQueue.getQueue());
        }
    }

    public StackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * 往队列添加数据
     * @param x
     */
    public void insertQueue(int x) {
        stack1.push(x);
    }

    /**
     * 获取队列元素
     * @return
     */
    public int getQueue() {
//        if (stack1.isEmpty()) {
//            throw new RuntimeException("1号栈为空");
//        }
        if (!stack2.isEmpty()) { // 2号栈不为空，直接从2号栈弹出
            return stack2.pop();
        } else {
            int size = stack1.size();
            for (int i = 0; i < size; i++) { // 把1号栈中所有元素依次弹出并压到2号栈中
                int temp = stack1.pop();
                stack2.push(temp);
            }
            // 弹出2号栈中元素，成功用栈实现队列
            return stack2.pop();
        }
    }
}
