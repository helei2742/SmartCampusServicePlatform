package org.pg7.scsp.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsFormDTO {

    private int id;
    private String auth;

    private String title;
    private String detail;
    private String signature;
    private Integer seeCount;
    private Integer collegeId;

    private LocalDateTime createTime;
}
