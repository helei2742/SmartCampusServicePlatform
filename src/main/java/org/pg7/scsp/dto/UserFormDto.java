package org.pg7.scsp.dto;

import lombok.Data;

@Data
public class UserFormDto {
    private Integer userId;

    private String email;
    private String phone;
    private String homeAddress;


}
