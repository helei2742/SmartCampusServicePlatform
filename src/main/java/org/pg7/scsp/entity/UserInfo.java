package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@TableName("tb_user_info")
@ApiModel(value = "UserInfo对象", description = "")
@Data
public class UserInfo {



      private Integer userId;

      @ApiModelProperty("身份证号")
      private String chinaId;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("手机号")
      private String phone;

      @ApiModelProperty("家庭住址")
      private String homeAddress;

      @ApiModelProperty("宿舍地址")
      private String dormitory;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer deleted;

}
