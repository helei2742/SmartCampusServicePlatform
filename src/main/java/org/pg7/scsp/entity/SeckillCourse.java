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
 * @since 2022-06-28
 */
@Data
@TableName("tb_seckill_course")
@ApiModel(value = "SeckillCourse对象", description = "")
public class SeckillCourse{


      @ApiModelProperty("关联的课程id")
      private Integer courseId;

      @ApiModelProperty("剩余课程")
      private Integer stock;

      @ApiModelProperty("选课开始时间")
      private LocalDateTime startTime;

      @ApiModelProperty("选课结束时间")
      private LocalDateTime endTime;

      @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
      private Integer deleted;

      @ApiModelProperty("乐观锁更新版本")
      private Integer version;

}
