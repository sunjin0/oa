package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.*;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.RoleRouteServiceImpl;
import com.example.oa.service.impl.RoleServiceImpl;
import com.example.oa.service.impl.RouteServiceImpl;
import com.example.oa.service.impl.UserRoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sun
 * @date 2024/04/16
 * @since 2024-04-16
 */
@CrossOrigin("*")
@RestController
@Api(tags = "角色信息 API", description = "提供角色信息相关的 Rest API")
@RequestMapping("/v1/api/admin/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private RouteServiceImpl routeService;

    @Autowired
    private RoleRouteServiceImpl roleRouteService;

    @ApiImplicitParam(dataType = "Role")
    @ApiOperation(value = "分页查询角色信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody Role role) {
        Page<Role> page = new Page<>(role.getCurrent(), role.getSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (role.getDescription() != null) {
            queryWrapper.like("description", role.getDescription());
        }
        if (role.getName() != null) {
            queryWrapper.like("name", role.getName());
        }
        Page<Role> pages = roleService.page(page, queryWrapper);
        List<Role> roles = pages.getRecords();
        PageResult<Role> result = new PageResult<>(role.getCurrent(), role.getSize(), pages.getTotal(), roles,routeService.list());
        return R.OK(result);
    }
    @ApiImplicitParam(dataType = "Role")
    @ApiOperation(value = "添加角色信息")
    @PostMapping("/add")
    public String save(@RequestBody Role role) {
        try {
            if (role == null) {
                return R.OK(Constant.Parameter_Error);
            }
            return roleService.saveRoleRouteResources(role);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "角色Id")
    @ApiOperation(value = "删除角色信息")
    @PostMapping("/delete/{roleId}")
    public String delete(@PathVariable Integer roleId) {
        try {
            if (roleId == null) {
                return R.OK(Constant.Parameter_Error);
            }
            roleService.removeById(roleId);
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", roleId);
            userRoleService.remove(queryWrapper);
            QueryWrapper<RoleRoute> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("role_id", roleId);
            roleRouteService.remove(queryWrapper2);
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(dataType = "Role")
    @ApiOperation(value = "修改角色信息")
    @PostMapping("/update")
    public String update(@RequestBody Role role) {
        try {
            if (role == null) {
                return R.OK(Constant.Parameter_Error);
            }
            roleService.updateById(role);
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

}
