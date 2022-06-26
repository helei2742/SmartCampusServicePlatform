package org.pg7.scsp.controller;

public interface BaseController {
    default boolean checkPermission(String auth){
        //TODO 默认权限校验
        return true;
    }

}
