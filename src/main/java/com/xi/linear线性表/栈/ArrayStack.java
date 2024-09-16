package com.xi.linear线性表.栈;

import java.util.Iterator;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/16 16:25:24
 * @description 栈 数组实现栈
 * 案例 栈实现计算器 只考虑+ = * /
 * 1. 通过索引遍历表达式
 * 2. 当是数字时，将数字放入数字栈中
 * 3. 当是符号时，
 *      如果符号栈中符号为空时，即空栈，将符号直接放入栈中：
 *      如果符号栈中已经存在符号，则需要对比符号优先级，
 *          如果是小于或者等于则需要取出数字栈中两个数字，再取出符号栈中进行运算求出结果，讲结果再次放入数字栈中，再讲当前符号放入符号栈中，
 *          如果当前符号优先级大于符号栈中符号，则直接放入符号栈。
 *    整个表达式对比完后，就一次取出数字栈中数据和符号栈中符号进行运算，最终获取结果。
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

        int res = arrayStack.res("6+6*2-5");
        System.out.println("计算结果：" + res);


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

    /**
     * 栈的大小
     * @return
     */
    public int length() {
        return stack.length;
    }

    /**
     * 查看栈顶数据
     * @return
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 获取符号的计算优先级（只考虑+=/)
     * @return
     */
    public int pri(int oper) {// 传过来的值会往ASCII码转
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }


    /**
     * 判断是否是运算符
     * @param v
     * @return
     */
    public boolean isOper(char v) {
        return v == '+' || v == '-' ||v == '*' ||v == '/';
    }

    /**
     * 一步计算
     * @param num1 数一
     * @param num2 数二
     * @param oper 运算符
     * @return num1 oper num2的结果
     */
    public int cal(int num1, int num2, int oper) {
        System.out.println("执行运算：" +  num2 + (char)oper + num1);
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 计算
     * @param str 数学算式，可以包含 + = * /
     * @return 结果
     */
    public int res(String str) {
        System.out.println("执行计算：" + str);

        // 数字栈
        ArrayStack numStack = new ArrayStack(10);
        // 符号栈
        ArrayStack symbolStack = new ArrayStack(10);

        // 算式长度
        int length = str.length();

        // 计算结果
        int result = 0;

        // 计算的两个数
        int temp1 = 0;
        int temp2 = 0;
        // 运算符
        int symbolChar = 0;

        String val = "";

        // 遍历表达式
        for (int i = 0; i < length; i++) {
            // 取出每个字符
            char c = str.charAt(i);

            // 判断是否是运算符
            if (isOper(c)) {// 当前字符是运算符
                if (!symbolStack.isEmpty()) {// 符号栈不是空
                    // 比较当前符号与栈顶符号优先级
                    if (symbolStack.pri(c) <=
                            symbolStack.pri(symbolStack.peek())) {// 当前运算符小于等于栈顶符号
                        // 如果当前运算符优先级小于等于符号栈栈顶符号的优先级，那么需要获取数字栈中的两个数字，并获取符号栈栈顶的符号
                        temp1 = numStack.pop();
                        temp2 = numStack.pop();
                        symbolChar = symbolStack.pop();
                        // 开始计算
                        result = cal(temp1, temp2, symbolChar);
                        // 将计算结果再次放入数字栈
                        numStack.push(result);
                        // 运算符栈放入当前运算符
                        symbolStack.push(c);
                    } else {// 当前运算符大于栈顶符号
                        symbolStack.push(c);
                    }
                } else {// 符号栈为空
                    symbolStack.push(c);
                }
            } else {// 当前字符是数字
                // 如果是一个数字，还需考虑多位数的情况 6 66 666
                val+=c;
                if (i == length-1) {// 这是字符串中的最后一个字符
                    numStack.push(Integer.parseInt(val));
                } else {
                    char d = str.substring(i + 1, i + 2).charAt(0);
                    if (isOper(d)) {// 下一个是字符
                        numStack.push(Integer.parseInt(val));
                        val = "";
                    }
                }
            }

        }
        System.out.println("运算符指针走完");

        // 运算符指针走完
        while (true) {
            if (symbolStack.isEmpty()) {// 符号栈为空
                break;
            }
            temp1 = numStack.pop();
            temp2 = numStack.pop();
            symbolChar = symbolStack.pop();

            // 计算结果
            result = cal(temp1, temp2, symbolChar);
            // 将计算结果压入数字栈中
            numStack.push(result);

        }

        // 获取最终结果
        result = numStack.pop();
        return result;
    }
}


