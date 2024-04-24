package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.PageResult;
import com.example.oa.entity.R;
import com.example.oa.entity.Route;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.RouteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 路由表 前端控制器
 * </p>
 *
 * @author sun
 * @date 2024/04/17
 * @since 2024-04-17
 */
@CrossOrigin("*")
@RestController
@Api(tags = "前端路由信息 API", description = "提供前端路由信息相关的 Rest API")
@RequestMapping("/v1/api/admin/route")
public class RouteController {
    @Autowired
    private RouteServiceImpl routeService;


    @ApiImplicitParam(dataType = "Route")
    @ApiOperation(value = "分页获取前端路由信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody Route route) {

        Page<Route> page = new Page<>(route.getCurrent(), route.getSize());
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        if (route.getId() != null) {
            queryWrapper.eq("id", route.getId());
        }
        if (route.getPath() != null) {
            queryWrapper.like("path", route.getPath());
        }
        Page<Route> pages = routeService.page(page, queryWrapper);
        List<Route> users = pages.getRecords();
        PageResult<Route> result = new PageResult<>(route.getCurrent(), route.getSize(), pages.getTotal(), users);

        return R.OK(result);
    }

    @ApiImplicitParam(dataType = "Route")
    @ApiOperation(value = "添加前端路由信息")
    @PostMapping("/add")
    public String save(@RequestBody Route route) {

        try {
            if (route == null) {
                return R.Worm(Constant.Parameter_Error);
            }
            routeService.save(route);
            return R.OK(Constant.Save_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "用户Id")
    @ApiOperation(value = "删除前端路由信息")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        if (id == null) {
            return R.OK(Constant.Parameter_Error);
        }
        routeService.removeById(id);
        return R.OK(Constant.Delete_Successfully);
    }

    @ApiImplicitParam(dataType = "Route")
    @ApiOperation(value = "修改前端路由信息")
    @PostMapping("/update")
    public String update(@RequestBody Route route) {
        try {
            if (route == null) {
                return R.OK(Constant.Parameter_Error);
            }
            routeService.updateById(route);
            return R.OK(Constant.Delete_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
