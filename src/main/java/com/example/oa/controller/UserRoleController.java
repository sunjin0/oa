package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.PageResult;
import com.example.oa.entity.R;
import com.example.oa.entity.UserRole;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.UserRoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
@CrossOrigin("*")
@RestController
@Slf4j
@Api(tags = "用户角色信息 API", description = "提供用户角色信息相关的 Rest API")
@RequestMapping("/v1/api/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleServiceImpl userRoleService;

    @ApiImplicitParam(dataType = "UserRole", name = "user", value = "用户实体", required = true)
    @ApiOperation(value = "分页查询用户角色信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody UserRole userRole) {
        if (userRole == null) {
            return R.OK(Constant.Parameter_Error);
        }
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        Page<UserRole> page = new Page<>(userRole.getCurrent(), userRole.getSize());
        Page<UserRole> pages = userRoleService.page(page, queryWrapper);
        List<UserRole> roleResources = pages.getRecords();
        //填充属性
        PageResult<UserRole> result = new PageResult<>(userRole.getCurrent(), userRole.getSize(), pages.getTotal(), roleResources);
        return R.OK(result);
    }

    @ApiImplicitParam(dataType = "UserRole")
    @ApiOperation(value = "添加用户角色信息")
    @PostMapping("/add")
    public String save(@RequestBody UserRole userRole) {
        try {
            if (userRole == null) {
                return R.OK(Constant.Parameter_Error);
            }
            userRoleService.save(userRole);
            return R.OK(Constant.Save_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "用户角色Id")
    @ApiOperation(value = "删除用户角色信息")
    @PostMapping("/delete")
    public String delete(@RequestBody UserRole userRole) {
        try {
            if (userRole == null) {
                return R.OK(Constant.Parameter_Error);
            }
            userRoleService.removeById(userRole.getId());
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(dataType = "UserRole")
    @ApiOperation(value = "修改用户角色信息")
    @PostMapping("/update")
    public String update(@RequestBody UserRole userRole) {
        try {
            if (userRole == null) {
                return R.OK(Constant.Parameter_Error);
            }
            userRoleService.updateById(userRole);
            return R.OK(Constant.Modify_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
