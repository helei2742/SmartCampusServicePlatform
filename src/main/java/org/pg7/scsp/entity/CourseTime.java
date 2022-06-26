package org.pg7.scsp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;
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
@TableName("tb_course_time")
@ApiModel(value = "CourseTime对象", description = "")
public class CourseTime {


      @ApiModelProperty("课程时间对应的序号")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程是星期几")
      private Integer day;

      @ApiModelProperty("开始时间")
      private LocalTime startTime;

      @ApiModelProperty("结束时间")
      private LocalTime endTime;
}
