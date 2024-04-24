package com.example.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "分页参数")
public class PageParams implements Serializable {
    @TableField(exist = false)
    @ApiModelProperty(value = "页数")
    private long current = 1L;
    @TableField(exist = false)
    @ApiModelProperty(value = "大小")
    private long size = 10L;
}
