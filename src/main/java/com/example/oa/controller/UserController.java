package com.example.oa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.oa.common.Constant;
import com.example.oa.entity.PageResult;
import com.example.oa.entity.R;
import com.example.oa.entity.User;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.UserServiceImpl;
import com.example.oa.utils.AesUtil;
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
@Api(tags = "用户信息 API", description = "提供用户信息相关的 Rest API")
@RequestMapping("/v1/api/admin")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @ApiImplicitParam(value = "用户Id")
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/user/{userId}")
    public String getUserDetail(@PathVariable Integer userId) {

        User user = userService.getById(userId);
        user.setPassword("");
        return R.OK(user);
    }

    @ApiImplicitParam(dataType = "User")
    @ApiOperation(value = "分页获取用户信息")
    @PostMapping("/query")
    public String queryPage(@RequestBody User user) {

        Page<User> page = new Page<>(user.getCurrent(), user.getSize());
        QueryWrapper<User> userQueryWrapper = getUserQueryWrapper(user);
        Page<User> pages = userService.page(page, userQueryWrapper);
        List<User> users = pages.getRecords();
        users.forEach(u -> u.setPassword("*****"));
        PageResult<User> result = new PageResult<>(user.getCurrent(), user.getSize(), pages.getTotal(), users);

        return R.OK(result);
    }

    private static QueryWrapper<User> getUserQueryWrapper(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (user.getId() != null) {
            userQueryWrapper.eq("id", user.getId());
        }
        if (user.getUserName() != null) {
            userQueryWrapper.like("user_name", user.getUserName());
        }
        if (user.getEnable() != null) {
            userQueryWrapper.eq("enable", user.getEnable());
        }
        if (user.getLocked() != null) {
            userQueryWrapper.eq("locked", user.getLocked());

        }
        return userQueryWrapper;
    }

    @ApiImplicitParam(dataType = "User")
    @ApiOperation(value = "添加用户信息")
    @PostMapping("/add")
    public String save(@RequestBody User user) throws Exception {
        try {
            if (user == null) {
                return R.Worm(Constant.Parameter_Error);
            }
            user.setPassword(AesUtil.Encrypt(user.getPassword()));
            userService.save(user);
            return R.OK(Constant.Save_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @ApiImplicitParam(value = "用户Id")
    @ApiOperation(value = "删除用户信息")
    @PostMapping("/delete/{userId}")
    public String delete(@PathVariable Integer userId) {
        if (userId == null) {
            return R.OK(Constant.Parameter_Error);
        }
        userService.removeById(userId);
        return R.OK(Constant.Delete_Successfully);
    }

    @ApiImplicitParam(dataType = "User")
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    public String update(@RequestBody User user) {
        try {
            if (user == null) {
                return R.OK(Constant.Parameter_Error);
            }
            userService.updateById(user);
            return R.OK(Constant.Modify_Successfully);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

}
