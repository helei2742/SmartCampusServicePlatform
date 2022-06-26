package org.pg7.scsp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class UserCourseRecord extends User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程名")
      private String courseName;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("选课描述")
      private String described;

      @ApiModelProperty("成绩")
      private Integer score;

      @ApiModelProperty("第几次选该课程")
      private Integer count;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      private Integer deleted;
}
