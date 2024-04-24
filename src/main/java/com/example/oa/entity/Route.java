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
import java.util.List;

/**
 * <p>
 * 路由表
 * </p>
 *
 * @author sun
 * @since 2024-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("route")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Route对象", description = "路由表")
public class Route extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "父路由Id")
    private Integer parentId;
    @ApiModelProperty(value = "资源Id", required = true)
    private Integer resourcesId;

    @TableField(exist = false)
    private List<Route> children;


}
