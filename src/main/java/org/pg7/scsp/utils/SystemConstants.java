package org.pg7.scsp.utils;

public class SystemConstants {
    public static final int DEFAULT_PAGE_SIZE = 20;


    /**
     * 创建时间升序
     */
    public static final int CREATE_TIME_DESC = 1;
    /**
     * 创建时间降序
     */
    public static final int CREATE_TIME_ASC = 2;

    /**
     * 查看人数降序
     */
    public static final int SEECOUNT_DESC = 3;

    /**
     * 学院id对应的新闻，创建时间升序
     */
    public static final int EQ_COLLAGE_CREATE_TIME_ASC = 4;
    /**
     * 学院id对应的新闻，创建时间降序
     */
    public static final int EQ_COLLAGE_CREATE_TIME_DESC = 5;
    /**
     * 学院id对应的新闻，点击量降序
     */
    public static final int EQ_COLLAGE_SEECOUNT_DESC = 6;


    public static final int QUERY_USERINFO_BY_ID = 7;
    public static final int QUERY_USERINFO_BY_IDNUMBER = 8;


    /**
     * 条件分页查询课程全部信息
     */
    public static final Integer CONDITION_QUERY_PAGE_COURSE_ALLINFO = 9;
}
