package com.nicebin.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author DiaoJianBin
 * @Description 测试资源类，用来假装是一个资源
 * @Date 2021/3/18 14:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResource {
    private String name;
    private int num;

    /**
     * Sentinel在统计热点数据的时候
     * 如果是对象会调用equals来判断是否为同一个对象，以此进行统计
     * 所以要重写equals的方法
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        return obj.hashCode()==this.hashCode();
    }

    /**
     * 根据equal和hashCode逻辑上相等原则
     * 重写了equals就要重写hashCode
     * @return
     */
    public int hashCode(){
        return Integer.parseInt(name)+num;
    }

}
