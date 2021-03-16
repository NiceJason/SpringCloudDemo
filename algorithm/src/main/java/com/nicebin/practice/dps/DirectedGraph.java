package com.nicebin.practice.dps;

/**
 * @auther: NiceBin
 * @description: 有向图求闭环
 *               例如微服务的调用依赖看有没有闭环
 *               或者是走迷宫，求路径的问题
 * @date: 2021/3/16 21:00
 */
public class DirectedGraph {

    /**
     * 微服务依赖查询，看有没闭环，有的话路径怎样
     * 如 A->B，B->C，C->D，C->A
     * 则有闭环 A->B->C->A
     *
     * 思路：将所有的点横竖放好，形成一个二维数组
     *      有路的话，该坐标为1
     *      没有为0
     *      从而转换成无向图的思路去做
     */
    public void dependency(){

    }
}
