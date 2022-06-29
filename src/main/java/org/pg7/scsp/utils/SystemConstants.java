package org.pg7.scsp.utils;

public class SystemConstants {
    /**
     * 默认分页长度
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final String REDIS_ID_WORKER_KEY_COURSE = "course";

    public static final String REDIS_ID_WORKER_KEY_COURSETEST = "coursetest";

    /**
     * 对应秒杀选课时，订单的状态，等待结算（未更新数据到tb_user_course_record中）
     */
    public static final int SECKILL_COURSE_ORDER_STATUSE_WAIT = 1;
    /**
     * 对应秒杀选课时，订单的状态，已结算（更新数据到了tb_user_course_record中）
     */
    public static final int SECKILL_COURSE_ORDER_STATUSE_SETTLE = 2;
    /**
     * 对应秒杀选课时，订单的状态，已取消（用户选课期间取消选课）
     */
    public static final int SECKILL_COURSE_ORDER_STATUSE_CANCEL = 3;

    /**
     * 对应考试类型， 期末考试
     */
    public static final int COURSE_TEST_TYPE_END = 1;

    /**
     * 对应考试类型， 期中考试
     */
    public static final int COURSE_TEST_TYPE_MID = 2;

    /**
     * 对应考试类型， 补考
     */
    public static final int COURSE_TEST_TYPE_RE = 3;


    /**
     * 对应用户考试的状态， 未开始
     */
    public static final int COURSE_TEST_RECORD_STATUS_UNSTART = 1;
    /**
     * 对应用户考试的状态， 未参加
     */
    public static final int COURSE_TEST_RECORD_STATUS_UNJOIN = 2;
    /**
     * 对应用户考试的状态， 参加
     */
    public static final int COURSE_TEST_RECORD_STATUS_JOINED = 3;
    /**
     * 对应用户考试的状态， 考试被取消
     */
    public static final int COURSE_TEST_RECORD_STATUS_CANCEL = 4;

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
