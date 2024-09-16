package com.xi.linear线性表.栈;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/16 16:25:24
 * @description 栈 数组实现栈
 */
public class ArrayStack {
    // 栈
    private int[] stack;
    // 大小
    private int maxStack;
    //top指针 始终指向栈顶
    private int top = -1;

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(6);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);

        arrayStack.show();

        arrayStack.push(7);

        arrayStack.pop();
        arrayStack.push(8);
        arrayStack.show();
        arrayStack.pop();
        arrayStack.pop();





    }

    public ArrayStack(int maxStack) {
        this.maxStack = maxStack;
        this.stack = new int[maxStack];
    }

    /**
     * 判断栈是否已满
     * @return
     */
    public boolean isFull() {
        return top == maxStack-1;
    }

    /**
     * 判断是否为空栈
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压栈
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈已满，添加数据失败");
            return;
        }

        top++;
        stack[top] = val;
    }

    /**
     * 弹栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("空栈无法返回");
           throw new RuntimeException("空栈无法返回");
        }
        int val = stack[top];
        System.out.println("弹栈元素：" + val);
        top--;
        return val;
    }

    /**
     * 遍历栈中元素
     */
    public void show() {
        System.out.print("栈中的元素：");
        for (int i = 0; i < stack.length; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}


