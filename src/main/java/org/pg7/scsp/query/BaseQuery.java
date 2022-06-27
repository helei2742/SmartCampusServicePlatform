package org.pg7.scsp.query;

import lombok.Data;
import org.pg7.scsp.utils.SystemConstants;

@Data
public class BaseQuery {
    private String auth;

    private int page = 1;

    private int size = SystemConstants.DEFAULT_PAGE_SIZE;
}
