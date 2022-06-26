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
@TableName("tb_news")
@ApiModel(value = "News对象", description = "")
public class News{


      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("公告标题")
      private String title;

      @ApiModelProperty("详情")
      private String detail;

      @ApiModelProperty("署名")
      private String signature;

      @ApiModelProperty("查看人数")
      @TableField(fill = FieldFill.INSERT)
      private Integer seeCount;

      @ApiModelProperty("所属学院id")
      private Integer collegeId;

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
