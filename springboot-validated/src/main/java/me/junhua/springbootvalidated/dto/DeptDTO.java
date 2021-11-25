package me.junhua.springbootvalidated.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.junhua.springbootvalidated.config.ValidationConfig;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author ljhua
 * @since 2021-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(groups = ValidationConfig.Update.class, message = "id不能为空")
    private Long id;

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 子部门数目
     */
    private Integer subCount;

    /**
     * 名称
     */
    @NotNull(groups = {ValidationConfig.Update.class, ValidationConfig.Create.class}, message = "名称不能为空")
    private String name;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updateBy;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;


}
