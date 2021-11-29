package com.nicebin.dubbo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Business implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "businessId", type = IdType.ASSIGN_ID)
    private Long businessId;

    /**
     * 公司名称
     */
    private String name;


}
