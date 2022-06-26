package org.pg7.scsp.query;

import lombok.Data;

@Data
public class NewsQuery extends BaseQuery{
    private int queryType;

    private int id;

    private int collageId;
}
