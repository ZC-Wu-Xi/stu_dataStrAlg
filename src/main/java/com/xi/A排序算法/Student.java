package com.xi.A排序算法;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Wu
 * @date 2024/8/24 12:26:58
 * @description 排序算法介绍
 * 使用官方提供的Comparable接口进行排序
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    private Integer stuId;
    private String stuName;
    private Integer stuAge;

    /**
     * 年龄比较
     * @param stu the object to be compared.
     * @return int
     * 返回值三种情况
     *  >0当前对象大于传过来的对象
     *  =0当前对象等于传过来的对象
     *  <0当前对象小于传过来的对象
     */
    @Override
    public int compareTo(Student stu) {
        return this.stuAge-stu.stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName=" + stuName +
                ", stuAge=" + stuAge +
                '}';
    }
}
