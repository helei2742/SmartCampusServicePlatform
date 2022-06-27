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
@TableName("tb_user_course_record")
@ApiModel(value = "UserCourseRecord对象", description = "")
public class UserCourseRecord {



      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程名")
      private String courseName;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("学期")
      private String semester;

      @ApiModelProperty("成绩")
      private Integer score;

      @ApiModelProperty("第几次选该课程")
      private Integer count;

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
