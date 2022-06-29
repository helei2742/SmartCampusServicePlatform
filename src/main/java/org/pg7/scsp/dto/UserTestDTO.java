package org.pg7.scsp.dto;

import lombok.Data;
import org.pg7.scsp.entity.CourseTest;

@Data
public class UserTestDTO {

    private Long id;

    private Integer userId;

    private Integer testId;

    private Float score;

    private Integer status;

    private CourseTest courseTest;
}
