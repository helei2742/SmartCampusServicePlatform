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
 * @since 2022-06-25
 */
@Data
@TableName("tb_user")
@ApiModel(value = "User对象", description = "")
public class User {


      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("学号")
      private String idNumber;

      @ApiModelProperty("密码")
      @TableField(fill = FieldFill.INSERT)
      private String password;

      @ApiModelProperty("真实姓名")
      private String trueName;

      @ApiModelProperty("头像")
      private String icon;

      @ApiModelProperty("专业id")
      private Integer majorId;

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
