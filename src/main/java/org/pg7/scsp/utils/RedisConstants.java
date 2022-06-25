package org.pg7.scsp.utils;

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
}
