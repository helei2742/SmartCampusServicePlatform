package org.pg7.scsp.dto;

import lombok.Data;
import org.pg7.scsp.entity.CourseTime;
import org.pg7.scsp.entity.User;

import java.util.List;

@Data
public class CourseDTO {
    private Integer id;

    private String courseName;

    private Float credit;

    private String detail;

    private List<CourseTime> courseTimeList;

    private List<User> teacherList;
}
