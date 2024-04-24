package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.PageResult;
import com.example.oa.entity.R;
import com.example.oa.entity.RoleRoute;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.RoleRouteServiceImpl;
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
 * @since 2024-04-18
 */
@CrossOrigin("*")
@RestController
@Api(tags = "角色路由信息 API", description = "提供角色路由信息相关的 Rest API")
@RequestMapping("/v1/api/admin/role-route")
public class RoleRouteController {
    @Autowired
    private RoleRouteServiceImpl roleRouteService;


    @ApiImplicitParam(dataType = "RoleRoute")
    @ApiOperation(value = "分页获取角色路由信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody RoleRoute roleRoute) {
        Page<RoleRoute> page = new Page<>(roleRoute.getCurrent(), roleRoute.getSize());
        QueryWrapper<RoleRoute> queryWrapper = new QueryWrapper<>();
        if (roleRoute.getId() != null) {
            queryWrapper.eq("id", roleRoute.getId());
        }
        if (roleRoute.getRouteId() != null) {
            queryWrapper.like("route_id", roleRoute.getRouteId());
        }
        Page<RoleRoute> pages = roleRouteService.page(page, queryWrapper);
        List<RoleRoute> roleRoutes = pages.getRecords();
        PageResult<RoleRoute> result = new PageResult<>(roleRoute.getCurrent(), roleRoute.getSize(), pages.getTotal(), roleRoutes);

        return R.OK(result);
    }

    @ApiImplicitParam(dataType = "RoleRoute")
    @ApiOperation(value = "添加角色路由信息")
    @PostMapping("/add")
    public String save(@RequestBody RoleRoute roleRoute) {
        try {
            if (roleRoute == null) {
                return R.Worm(Constant.Parameter_Error);
            }
            roleRouteService.save(roleRoute);
            return R.OK(Constant.Save_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "Id")
    @ApiOperation(value = "删除角色路由信息")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        if (id == null) {
            return R.OK(Constant.Parameter_Error);
        }
        roleRouteService.removeById(id);
        return R.OK(Constant.Delete_Successfully);
    }

    @ApiImplicitParam(dataType = "Route")
    @ApiOperation(value = "修改角色路由信息")
    @PostMapping("/update")
    public String update(@RequestBody RoleRoute roleRoute) {
        try {
            if (roleRoute == null) {
                return R.OK(Constant.Parameter_Error);
            }
            roleRouteService.updateById(roleRoute);
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
