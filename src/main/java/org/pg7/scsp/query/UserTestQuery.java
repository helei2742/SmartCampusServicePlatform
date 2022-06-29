package org.pg7.scsp.query;

import lombok.Data;

@Data
public class UserTestQuery extends BaseQuery{

    private Integer userId;

    private Integer courseId;

    private Integer testType;

    private Integer status;
}
