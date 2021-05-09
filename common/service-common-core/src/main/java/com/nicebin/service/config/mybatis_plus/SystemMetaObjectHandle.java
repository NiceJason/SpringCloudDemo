package com.nicebin.service.config.mybatis_plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author DiaoJianBin
 * @Description Mybatis-plus的字段填充处理器
 *              用来填充Entity类字段属性标明形如
 *              @TableField(fill = FieldFill.INSERT)
 *              的字段
 *              像insert时填充创建日期，updata时更新更新日期
 *              自动添加操作者信息等等
 * @Date 2021/4/15 8:28
 */
@Component
public class SystemMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //填充createTime字段
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        //填充逻辑删除字段
        this.strictInsertFill(metaObject,"deleteFlag",String.class,"0000000000000000000");
        //填充版本号字段
        this.strictInsertFill(metaObject,"version",Integer.class,1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",Date.class,new Date());
    }
}
