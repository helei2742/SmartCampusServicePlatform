package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@TableName("tb_announce")
@ApiModel(value = "Announce对象", description = "")
@Data
public class Announce implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("详情")
    private String detail;

    @ApiModelProperty("署名")
    private String signature;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer deleted;
}
