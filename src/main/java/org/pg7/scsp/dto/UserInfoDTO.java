package org.pg7.scsp.dto;

import lombok.Data;
import org.pg7.scsp.entity.Collage;
import org.pg7.scsp.entity.Major;

@Data
public class UserInfoDTO {

    private int userId;
    private String idNumber;
    private String trueName;
    private String ebg;
    private String icon;
    private String chinaId;
    private String email;
    private String phone;
    private String homeAddress;
    private String dormitory;
//    private int majorId;
//    private String majorName;
//    private int collageId;
//    private String collageName;
    private Major major;
    private Collage collage;

}
