package org.pg7.scsp.query;

import lombok.Data;

@Data
public class UserQuery extends BaseQuery{
    private Integer userId;
    private String idNumber;
    private Integer queryType;
}
