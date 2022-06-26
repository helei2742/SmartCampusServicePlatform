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
 * @since 2022-06-26
 */
@Data
@TableName("tb_course")
@ApiModel(value = "Course对象", description = "")
public class Course {


      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程名")
      private String courseName;

      @ApiModelProperty("学分")
      private Float credit;

      @ApiModelProperty("课程详情")
      private String detail;

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
