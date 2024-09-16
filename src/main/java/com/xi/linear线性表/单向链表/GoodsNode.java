package com.xi.linear线性表.单向链表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/13 12:29:34
 * @description 链表-单向链表案例二
 * 根据带有头部的单链表， 实现商品增删改查， 并且也可以针对商品已编号进行排序， 完成排行榜
 */
public class GoodsNode {
    public int gId;
    public String gName;
    public double gPrice;
    public GoodsNode next;
    public GoodsNode(int gId, String gName, double gPrice) {
        this.gId = gId;
        this.gName = gName;
        this.gPrice = gPrice;
    }

    @Override
    public String toString() {
        return "GoodsNode{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", next=" + next +
                '}';
    }
}
