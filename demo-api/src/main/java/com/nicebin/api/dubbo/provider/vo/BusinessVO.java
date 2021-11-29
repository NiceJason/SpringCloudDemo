package com.nicebin.api.dubbo.provider.vo;

import lombok.Data;

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
public class BusinessVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long businessId;

    private String name;
}
