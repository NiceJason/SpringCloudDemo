package com.nicebin.practice.dps;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author DiaoJianBin
 * @Description 排列组合
 *              1.可重复完全排列
 *              2.不可重复完全排列
 *              3.不可重复不完全排列，如1,2,3中任选2个排列组合
 *              1,2,3参考：https://www.cnblogs.com/zzlback/p/10947064.html
 *
 * @Date 2021/3/16 16:17
 */
public class Combination {

    private Stack<Integer> result = new Stack<>();
    /**
     * 可重复完全排列，如1,2,3中任选3个数字组合，列出所有组合可能
     */
    @Test
    public void combination(){
        int index = 0;

        int[] number = {1,2,3};
        System.out.println("-----可重复完全排列------");
        combination1Algorithm(number,index);
        result.clear();

        System.out.println("-----不可重复完全排列------");
        combination2Algorithm(number,index);
        result.clear();

        System.out.println("-----不可重复不完全排列------");
        combination3Algorithm(number,index,2);
        result.clear();
     }

    /**
     * 可重复完全排列算法，递归算法
     * @param number 基础池
     * @param index 当前基础坐标，从0开始
     */
     private void combination1Algorithm(int[] number, int index){
        if(index == number.length){
            System.out.println(result.toString());
           return;
        }

        for(int i = 0 ;i<number.length;i++){
            result.add(number[i]);
            combination1Algorithm(number, index +1);
            result.pop();
        }
     }

    /**
     * 不可重复完全排列，递归算法
     * 与1相比，就多了个过滤自己
     * @param number 基础池
     * @param index 当前基础坐标，从0开始
     */
     private void  combination2Algorithm(int[] number,int index){
         if(number.length == index){
             System.out.println(result.toString());
             return;
         }

         for(int i = 0; i<number.length;i++){
             if(!result.contains(number[i])){
                 result.add(number[i]);
                 combination2Algorithm(number, index+1);
                 result.pop();
             }
         }
     }

    /**
     * 不可重复不完全排列，递归算法
     * 
     * @param number 基础池
     * @param index 当前基础坐标，从0开始
     * @param size 选择基础池的数量
     */
     private void combination3Algorithm(int[] number,int index,int size){
         if(number.length == index || result.size()==2){
             System.out.println(result.toString());
             return;
         }

         for(int i=0;i<number.length;i++){
             if(!result.contains(number[i])){
                 result.add(number[i]);
                 combination3Algorithm(number, index+1, size);
                 result.pop();
             }
         }
     }
}
