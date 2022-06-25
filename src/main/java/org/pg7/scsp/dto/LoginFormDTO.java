package org.pg7.scsp.dto;

import lombok.Data;

@Data
public class LoginFormDTO {
    private String idNumber;
    private String code;
    private String password;
}
