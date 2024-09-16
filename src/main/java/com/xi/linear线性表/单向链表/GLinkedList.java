package com.xi.linear线性表.单向链表;

/**
 * @author ZC_Wu 汐
 * @date 2024/9/13 19:26:08
 * @description 链表-单向链表案例二
 * 根据带有头部的单链表， 实现商品增删改查， 并且也可以针对商品已编号进行排序， 完成排行榜
 */
public class GLinkedList {
    private GoodsNode node = new GoodsNode(0, "", 0.0);// 头节点

    public static void main(String[] args) {
        GLinkedList gLinkedList = new GLinkedList();

        GoodsNode goodsNode1 = new GoodsNode(1, "苹果", 13);
        GoodsNode goodsNode2 = new GoodsNode(3, "香蕉", 3);
        GoodsNode goodsNode3 = new GoodsNode(2, "葡萄", 22);

        gLinkedList.addByOrder(goodsNode1);
        gLinkedList.addByOrder(goodsNode2);
        gLinkedList.addByOrder(goodsNode3);
        gLinkedList.delNode(1);
        GoodsNode updateGoodsNode = new GoodsNode(3, "香蕉干", 24);
        gLinkedList.updateNode(updateGoodsNode);
        gLinkedList.getNodeByOrder(2);
        System.out.println(gLinkedList);
    }

    /**
     * 添加节点
     * @param goodsNode 添加的节点
     */
    public void add(GoodsNode goodsNode) {
        // 证实是添加节点还是添加的第一个节点
        GoodsNode temp = node;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = goodsNode;
    }

    /**
     * 插入商品 按商品gid进行排序 1,2,4,5,6,7...
     * @param goodsNode
     */
    public void addByOrder(GoodsNode goodsNode) {
        GoodsNode temp = node;

        boolean flag = false;// 新插入节点的商品编号是否已经存在
        while (true) {
            // 最后一个
            if (temp.next == null) {
                break;
            }
            // 下一个已经存在的编号大于新节点编号 插入在该节点的后面
            if (temp.next.gId > goodsNode.gId) {
                break;
            } else if (temp.next.gId == goodsNode.gId) {
                // 已经存在的编号不能重复添加
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.println("该商品已存在，不能重复添加");
        } else {
            // 插入
            goodsNode.next = temp.next;
            temp.next = goodsNode;
        }
    }

    /**
     * 根据编号gid进行修改
     * @param goodsNode
     */
    public void updateNode(GoodsNode goodsNode) {
        if (node.next == null) {
            System.out.println("空链表");
            return;
        }

        GoodsNode temp = node.next;
        boolean flag = false;// 是否找到该gid的商品
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.gId == goodsNode.gId) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.gName = goodsNode.gName;
            temp.gPrice = goodsNode.gPrice;;
        } else {
            System.out.println("未找到该商品的节点");
        }
    }

    @Override
    public String toString() {
        return "GLinkedList{" +
                "node=" + node +
                '}';
    }

    /**
     * 根据编号gid进行删除
     * @param gId
     */
    public void delNode(int gId) {
        GoodsNode temp = node;
        boolean flag = false;// 是否找到该gid的商品
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.gId == gId) {// 找到的temp是该gId商品的前一个商品节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到该商品的节点");
        }
    }

    /**
     * 根据商品gId查询商品
     * @param gId
     * @return
     */
    public GoodsNode getNodeByOrder(int gId) {
        GoodsNode temp = node;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.gId == gId) {
                break;
            }
            temp = temp.next;
        }
        GoodsNode resule = temp.next;
        System.out.println("商品编号：" + resule.gId  + " 商品名称：" + resule.gName + " 商品价格：" + resule.gPrice);
        return resule;
    }
}
