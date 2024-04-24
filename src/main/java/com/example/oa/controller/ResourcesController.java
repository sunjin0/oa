package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.*;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.ResourcesServiceImpl;
import com.example.oa.service.impl.RoleServiceImpl;
import com.example.oa.service.impl.UserRoleServiceImpl;
import com.example.oa.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
@Api(tags = "权限与资源信息 API", description = "提供权限与资源信息相关的 Rest API")
@RequestMapping("/v1/api/resources")
public class ResourcesController {
    @Autowired
    private ResourcesServiceImpl resourcesService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @ApiImplicitParam(dataType = "Resources")
    @ApiOperation(value = "分页查询资源信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody Resources resources) {
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        Page<Resources> page = new Page<>(resources.getCurrent(), resources.getSize());
        if (resources.getId() != null) {
            queryWrapper.eq("id", resources.getId());
        }
        if (resources.getUrls() != null) {
            queryWrapper.like("urls", resources.getUrls());
        }
        Page<Resources> pages = resourcesService.page(page, queryWrapper);
        List<Resources> list = pages.getRecords();
        PageResult<Resources> result = new PageResult<>(resources.getCurrent(), resources.getSize(), pages.getTotal(), list);
       return R.OK(result);
    }

    @ApiImplicitParam(dataType = "User")
    @ApiOperation(value = "分页查询权限与资源信息")
    @PostMapping("/query/list")
    public String queryPages(@RequestBody User user) {
        LinkedList<HashMap<String, Object>> hashMaps = resourcesService.queryPage(user);
        PageResult<HashMap<String, Object>> result = new PageResult<>(user.getCurrent(), user.getSize(), userService.count(), hashMaps, roleService.list());
        return R.OK(result);
    }

    @ApiImplicitParam(dataType = "Resources")
    @ApiOperation(value = "添加资源信息")
    @PostMapping("/add")
    public String save(@RequestBody Resources resources) {
        try {
            if (resources == null) {
                return R.OK(Constant.Parameter_Error);
            }
            resourcesService.save(resources);
            return R.OK(Constant.Save_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "资源Id")
    @ApiOperation(value = "删除资源信息")
    @PostMapping("/delete/{resourcesId}")
    public String delete(@PathVariable Integer resourcesId) {
        if (resourcesId == null) {
            return R.OK(Constant.Parameter_Error);
        }
        resourcesService.removeById(resourcesId);
        return R.OK(Constant.Delete_Successfully);
    }

    @ApiImplicitParam(dataType = "Resources")
    @ApiOperation(value = "修改资源信息")
    @PostMapping("/update")
    public String update(@RequestBody Resources resources) {
        try {
            if (resources == null) {
                return R.OK(Constant.Parameter_Error);
            }
            resourcesService.updateById(resources);
            return R.OK(Constant.Modify_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(dataType = "UserRole")
    @ApiOperation(value = "权限添加")
    @PostMapping("/user-role/add")
    public String save(@RequestBody UserRole userRole) {
        try {
            if (userRole == null) {
                return R.OK(Constant.Parameter_Error);
            }
            ArrayList<UserRole> userRoles = getUserRoles(userRole);
            boolean isSuccess = userRoleService.saveBatch(userRoles);
            return isSuccess ? R.OK(Constant.Save_Successfully) : R.OK(Constant.Already_Has_A_Role);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    private ArrayList<UserRole> getUserRoles(UserRole userRole) {
        List<Integer> roleIds = userRole.getRoleIds();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        if (!roleIds.isEmpty()) {
            roleIds.forEach(roleId -> {
                UserRole userRole1 = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", userRole.getUserId()).eq("role_id", roleId));
                if (userRole1 == null) {
                    userRoles.add(new UserRole(userRole.getUserId(), roleId));
                }
            });
        }
        return userRoles;
    }

    @ApiImplicitParam(value = "用户角色Id")
    @ApiOperation(value = "权限权限")
    @PostMapping("/user-role/delete")
    public String delete(@RequestBody UserRole userRole) {
        try {
            if (userRole == null) {
                return R.OK(Constant.Parameter_Error);
            }
            ArrayList<Integer> ids = new ArrayList<>();
            for (Integer roleId : userRole.getRoleIds()) {
                QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("role_id", roleId).eq("user_id", userRole.getUserId());
                ids.add(userRoleService.getOne(queryWrapper).getId());
            }
            userRoleService.removeByIds(ids);
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
