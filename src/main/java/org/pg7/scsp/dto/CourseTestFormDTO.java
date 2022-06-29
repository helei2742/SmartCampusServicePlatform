package org.pg7.scsp.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseTestFormDTO {
    private Integer id;

    private Integer courseId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String location;

    private Integer testType;
}
