package org.pg7.scsp.utils;

import io.swagger.models.auth.In;

import java.util.concurrent.TimeUnit;

public class RedisConstants {

    public static final String LOGIN_CHECKCODE_KEY = "login:checkcode:";
    /**
     * 单位分钟
     */
    public static final Long LOGIN_CHECKCODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:user:";
    /**
     * 单位fen
     */
    public static final Long LOGIN_USER_TTL = 7L;
    public static final String LOCK_NEWS_KEY = "lock:news:";
    public static final String RANK_NEWS_KEY = "rank:news";


    public static final String CACHE_COURSE_SEMESTER_KEY = "cache:course:semester:";
    /**
     * 单位天
     */
    public static final Integer CACHE_COURSE_SEMESTER_TTL = 7;

    public static final String CACHE_COURSE_NAME_KEY = "cache:course:";

    /**
     * 单位分钟
     */
    public static final Integer CACHE_COURSE_NAME_TTL = 30;

}
