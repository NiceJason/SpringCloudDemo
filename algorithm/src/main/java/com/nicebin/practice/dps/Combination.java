package com.nicebin.practice.dps;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author DiaoJianBin
 * @Description 排列组合
 *              1.可重复完全排列
 *              2.不可重复排列
 *              3.不可重复不完全排列
 *              1,2,3参考：https://www.cnblogs.com/zzlback/p/10947064.html
 *
 * @Date 2021/3/16 16:17
 */
public class Combination {
    private Stack<Integer> result = new Stack<>();

    /**
     * 可重复完全排列，如1,2,3,4中任选4个数字组合，列出所有组合可能
     */
    @Test
    public void combination1(){
        int[] number = {1,2,3,4};

     }

    /**
     * 可重复完全排列算法，递归算法
     * @param number 选择池
     * @param times 重复选择次数，从0开始
     * @param now 当前选择次数，从0开始
     */
     private void combination1Algorithm(int[] number,int times,int now){
        if(now>times){
           return;
        }

     }
}
