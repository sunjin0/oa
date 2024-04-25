package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.oa.common.Constant;
import com.example.oa.entity.*;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;

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
@Slf4j
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
    @Autowired
    private ResourcesServiceImpl resourcesService;

    @ApiImplicitParam(dataType = "Role")
    @ApiOperation(value = "分页查询角色信息")
    @PostMapping("/query")
    public R queryPage(@RequestBody Role role) {
        LinkedList<HashMap<String, Object>> hashMaps = resourcesService.queryResources(role);
        HashMap<String, Object> data = new HashMap<>();
        data.put("routes", routeService.list());
        PageResult<HashMap<String, Object>> result = new PageResult<>(role.getCurrent(), role.getSize(), roleService.count(), hashMaps, data);
        return R.OK(result);
    }

    @ApiImplicitParam(dataType = "Role")
    @ApiOperation(value = "添加角色信息")
    @PostMapping("/add")
    public R save(@RequestBody Role role) {
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
    public R delete(@PathVariable Integer roleId) {
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
    public R update(@RequestBody Role role) {
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
