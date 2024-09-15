package com.xi.linear线性表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/15 19:57:28
 * @description 约瑟夫问题 环形链表 死亡游戏
 * 游戏规则
 *  nums人排成一个圆圈，由编号为startNo的人开始报数，报数到countNum的人被淘汰自杀，
 *  然后再由下一个重新报数，直到存活一人为止，此人就是赢家。
 */
public class DeathCircleLinkedList {

    private DeathPlayer first = new DeathPlayer(-1);// 规定-1为不存在

    public static void main(String[] args) {
        DeathCircleLinkedList DeathCircleLinkedList = new DeathCircleLinkedList();
        int nums = 8;// 参与者数量
        DeathCircleLinkedList.addDeathPlayers(nums);
        DeathCircleLinkedList.showDeathPlayers();
        // 2号玩家开始，数到3自杀，共有nums个玩家参与
        DeathCircleLinkedList.countDeathPlayer(2, 3, nums);
    }

    /**
     * 生成含有nums个节点的环形链表 nums个人开始玩这场游戏
     * @param nums 生成nums个节点的环，其no为1-nums
     */
    public void addDeathPlayers(int nums) {
        System.out.println("参赛者入场");
        DeathPlayer temp = null;
        for (int i = 1; i <= nums; i++) {
            DeathPlayer DeathPlayer = new DeathPlayer(i);
            if (i == 1) {
                first = DeathPlayer;// 第一个节点
                first.next = first;// 构成环
                temp = first;// 临时节点指向第一个男孩
            } else {
                temp.next = DeathPlayer;
                DeathPlayer.next = first;
                temp = DeathPlayer;// 临时变量后移
            }
        }
    }

    /**
     * 查看环形链表 查看所有参与者编号
     */
    public void showDeathPlayers() {
        if (first == null) {
            System.out.println("空链表");
            return;
        }

        DeathPlayer temp = first;
        System.out.print("参与者的编号为:");
        while (true) {
            System.out.print(temp.no + " ");
            if (temp.next == first) {// 遍历完一遍
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 执行淘汰节点 被淘汰成员执行自杀
     * @param startNo 从第几个参与者开始数
     * @param countNum 数了几下
     * @param nums 总的参与者数量
     */
    public void countDeathPlayer(int startNo, int countNum, int nums) {
        System.out.println("死亡游戏规则：" + startNo + "号参与者从1开始报数，报到" + countNum + "的参与者将被淘汰自杀 然后再由下一个重新报数，直到存活一人为止");
        System.out.println("游戏开始");
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        DeathPlayer helper = first;
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }
        // while循环结束后first指向no=1的节点，helper指向no=nums的节点

        // 设置从哪里开始数，把first和helper移动到开始和最后的节点上
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        // 开始淘汰
        while (true) {
            if (helper == first) {// 只剩一名参与者结束游戏
                break;
            }

            // 往后数countNum
            for (int i = 0; i < countNum-1; i++) {
                first = first.next;
                helper = helper.next;//first的前一个
            }

            System.out.printf("编号为%d的参与者被淘汰，自杀了\n", first.no);
            // 删除数到countNum的男孩
            first = first.next;
            helper.next = first;
        }
        System.out.printf("编号为%d的参与者存活，成为了最后的winner", first.no);
    }

}
