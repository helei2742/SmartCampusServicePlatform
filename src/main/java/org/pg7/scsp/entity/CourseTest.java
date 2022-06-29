package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
@Data
@TableName("tb_course_test")
@ApiModel(value = "Test对象", description = "")
public class CourseTest {


      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程名")
      private String courseName;

      @ApiModelProperty("开始日期")
      private LocalDateTime startTime;

      @ApiModelProperty("结束日期")
      private LocalDateTime endTime;

      @ApiModelProperty("考试位置")
      private String location;

      @ApiModelProperty("考试类型, 1期末考试，2期中考试，3补考")
      private Integer testType;

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
