package com.nicebin.api.dubbo.provider.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
public class BusinessDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 公司名称
     */
    @NotNull(message = "name不能为空")
    private String name;
}
