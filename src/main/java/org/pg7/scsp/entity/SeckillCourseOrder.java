package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-06-28
 */
@Data
@TableName("tb_seckill_course_order")
@ApiModel(value = "SeckillCourseOrder对象", description = "")
public class SeckillCourseOrder{

      @ApiModelProperty("选课期间选课记录")
        private Long id;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("当前状态，1待结算，2已结算，3已取消")
      private Integer status;

      @ApiModelProperty("创建日期")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("修改日期")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @ApiModelProperty("结算日期")
      private LocalDateTime settleTime;

      @ApiModelProperty("取消日期")
      private LocalDateTime cancelTime;
}
