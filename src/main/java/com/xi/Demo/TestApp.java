package com.xi.Demo;

/**
 * @author ZC_Wu 汐
 * @date 2024/8/24 12:45:40
 * @description
 */
public class TestApp {
    public static void main(String[] args) {
        Student student1 = new Student(1, "小红", 19);
        Student student2 = new Student(2, "小明", 18);

        System.out.println("年龄大的是：" + getMax(student1, student2));


    }

    public static String getMax(Student stu1, Student stu2) {
        if (stu1.compareTo(stu2) > 0) {
            return stu1.getStuName();
        } else  if (stu1.compareTo(stu2) < 0) {
            return stu2.getStuName();
        } else if (stu1.compareTo(stu2) == 0) {
            return stu1.getStuName() + "和" + stu2.getStuName() + "一样大";
        } else {
            return null;
        }
    }
}
