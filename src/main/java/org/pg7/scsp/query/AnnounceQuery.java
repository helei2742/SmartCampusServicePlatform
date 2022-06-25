package org.pg7.scsp.query;

import lombok.Data;

@Data
public class AnnounceQuery {

    /**
     * 创建时间升序
     */
    public static int CREATETIMEDESC = 1;
    /**
     * 创建时间降序
     */
    public static int CREATETIMEASC = 2;

    private int page;

    private int size;

    private int queryType;
}
