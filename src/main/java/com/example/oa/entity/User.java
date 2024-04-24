package com.example.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@AllArgsConstructor
@ApiModel("用户实体")
@NoArgsConstructor
public class User extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "是否启用")
    private Integer enable = 1;
    @ApiModelProperty(value = "是否锁定")
    private Integer locked = 0;
    @TableField(exist = false)
    private String info;

}
