package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
@Data
@TableName("tb_major")
@ApiModel(value = "Major对象", description = "")
public class Major{



      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("所属学院id")
      private Integer collegeId;

      @ApiModelProperty("专业名")
      private String name;

      @ApiModelProperty("描述")
      private String detail;

      @ApiModelProperty("管理人名称")
      private String managerName;

      @ApiModelProperty("专业人数")
      private Integer peopleCount;


    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer deleted;
}
