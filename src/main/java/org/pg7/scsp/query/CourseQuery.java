package org.pg7.scsp.query;

import lombok.Data;

@Data
public class CourseQuery extends BaseQuery{
    private Integer queryType;

    private Integer teacherId;

    private Integer userId;

}
