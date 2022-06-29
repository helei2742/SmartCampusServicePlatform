package org.pg7.scsp.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2022-06-29
 */
@Data
@TableName("tb_user_test_record")
@ApiModel(value = "UserTestRecord对象", description = "")
public class UserTestRecord {


      private Long id;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("考试id")
      private Integer testId;

      @ApiModelProperty("考试分数")
      private Float score;

      @ApiModelProperty("用户的考试状态，1:未开考, 2:未参加, 3:已参加, 4:考试已取消")
      private Integer status;

      @ApiModelProperty("创建日期")
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @ApiModelProperty("修改日期")
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
      private Integer deleted;

}
