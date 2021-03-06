package org.pg7.scsp.dto;

import lombok.Data;
import org.pg7.scsp.entity.CourseTime;
import org.pg7.scsp.entity.User;

import java.util.List;

@Data
public class CourseDTO {
    private Integer id;

    private String courseName;

    private String semester;

    private Integer majorId;

    private Float credit;

    private String detail;
    private String location;
    private Integer startWeek;
    private Integer endWeek;

    private List<CourseTime> courseTimeList;

    private List<User> teacherList;

}
