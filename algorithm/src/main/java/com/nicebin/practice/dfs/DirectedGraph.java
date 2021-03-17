package com.nicebin.practice.dfs;

import org.junit.Test;

import java.util.HashMap;

/**
 * @auther: NiceBin
 * @description: 有向图求闭环
 *               例如微服务的调用依赖看有没有闭环
 * @date: 2021/3/16 21:00
 */
public class DirectedGraph {

    /**
     * 微服务依赖查询，看有没闭环，有的话路径怎样
     * 如 A->B，B->C，C->D，C->A
     * 则有闭环 A->B->C->A
     *
     * 思路：将所有的点横竖放好，做一个映射，形成一个二维数组
     *      有路的话，该坐标为1
     *      没有为0
     *      从而转换成无向图的思路去做
     *
     * 结果：这种思路不对，看打印出来的迷宫图就知道了
     */
    @Test
    public void dependencySearch(){
        String dependency= "[A-B],[B-C],[B-D],[C-D],[D-E],[E-G],[G-A],[G-C],[Y-Z]";
//        String dependency= "[A-B],[B-C],[C-D],[D-E],[E-G],[G-A]";
        HashMap<Character,Integer> mapping = new HashMap<>();
        int[][] maze = mapping(dependency,mapping);
        printMaze(maze);
    }

    /**
     * 将点映射成二维数组
     */
    private int[][] mapping(String dependency, HashMap<Character,Integer> map){
        //当前映射的下标
        int index = 0;

        String[] dependencys = dependency.split(",");
        for(int i =0 ;i<dependencys.length;i++){
            char start  = dependencys[i].charAt(1);
            char end = dependencys[i].charAt(3);
            //第一次存放
            if(!map.containsKey(start)){
                map.put(start,index);
                index++;
            }
            //第一次存放
            if(!map.containsKey(end)){
                map.put(end,index);
                index++;
            }
        }

        //构建迷宫
        int[][] maze = new int[index][index];
        //设置路
        for(int i =0;i<dependencys.length;i++){
            char start  = dependencys[i].charAt(1);
            char end = dependencys[i].charAt(3);
            maze[map.get(start)][map.get(end)] = 1;
        }

        return maze;
    }

    /**
     * 打印迷宫，单纯为了看看迷宫生成的对不对
     * @param maze
     */
    private void printMaze(int[][] maze){
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }
    }
}
