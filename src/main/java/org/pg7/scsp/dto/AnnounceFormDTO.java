package org.pg7.scsp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnounceFormDTO {
    private int id;
    private String auth;

    private String title;
    private String detail;
    private String signature;

    private LocalDateTime createTime;
}
