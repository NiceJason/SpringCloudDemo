package com.nicebin.practice.dfs;

import org.junit.Test;

import java.util.*;

/**
 * @auther: NiceBin
 * @description: 无向图
 *               1.走迷宫，找出最短路径
 *                  参考 https://blog.csdn.net/seagal890/article/details/79826353
 * @date: 2021/3/16 21:08
 */
public class UndirectedGraph {

    //迷宫x边界(竖)
    private int mapX;
    //迷宫y边界（横）
    private int mapY;
    //迷宫出口的x坐标
    private int exitX;
    //迷宫出口的y坐标
    private int exitY;
    //最短路径，因为最短路径可能不只有1条，所以要用列表记录
    private List<String> shortPath = new ArrayList<>();
    //最短路径长度
    private int shortPathLengh = 9999;

    //总共路径
    private List<String> allPath = new ArrayList<>();

    private Stack<String> recordPath = new Stack<>();

    /**
     * 走迷宫，迷宫看成是一个二维数组
     * 其中0为可以通过，1为障碍物
     */
    @Test
    public void maze(){
        // 初始化一个迷宫地图
        // 0: 表示通路
        // 1:表示死路
        int[][] maze = {
                {0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };

        int[][] maze2 = {
                {0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };

        mapX = maze.length-1;
        mapY = maze[0].length-1;
        exitX = 8;
        exitY = 8;
        findExit(maze,0,0);

        printPath("maze总共路径",allPath);
        printPath("maze最短路径为",shortPath);
        recordPath.clear();
        shortPath.clear();
        allPath.clear();


        findExit(maze2,0,0);
        printPath("maze2总共路径",allPath);
        printPath("maze2最短路径为",shortPath);

    }

    /**
     * 找出口的算法，递归算法
     * 每次调用该函数，就相当于从当前坐标尝试往四周走
     */
    private void findExit(int[][] map,int x,int y){
        //出边界了或者有障碍物（一定要先判定x,y是否越界了），或者这个路走过了
        if(x<0 || y<0 || x>mapX || y>mapY ||map[x][y] !=0){
            return;
        }

        //记录走过的路
        map[x][y] = 2;
        recordPath.add("("+x+","+y+")");

        //到终点了
        if(x == exitX && y==exitY){
            String path = recordPath.toString();
            allPath.add(path);
            if(path.length()<shortPathLengh){
                shortPathLengh = path.length();
                shortPath.clear();
                shortPath.add(path);
            }else if(path.length() == shortPathLengh){
                shortPath.add(path);
            }
        }

        //分别尝试往四周走
        //往左走
        findExit(map,x,y-1);

        //往右走
        findExit(map,x,y+1);

        //往上走
        findExit(map,x-1,y);

        //往下走
        findExit(map,x+1,y);

        recordPath.pop();
        map[x][y] =0;
    }

    /**
     * 打印出路径
     * @param msg
     */
    private void printPath(String msg,List<String> path){
        System.out.println(msg);
        for (String s :
                path) {
            System.out.println(s);
        }
    }
}
