package com.example.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
@ApiModel("分页结果")
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    @ApiModelProperty(value = "页数")
    private long current;

    @ApiModelProperty(value = "大小")
    private long size;

    @ApiModelProperty(value = "合计")
    private long total;

    @ApiModelProperty(value = "列表")
    private List<T> list;
    @ApiModelProperty(value = "附带数据")
    private Object other;

    public PageResult(long current, long size, long total, List<T> list) {
        this.current=current;
        this.size=size;
        this.total=total;
        this.list=list;
    }
}
