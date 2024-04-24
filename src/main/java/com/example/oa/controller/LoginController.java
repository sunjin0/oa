package com.example.oa.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.oa.aop.NotToken;
import com.example.oa.common.Constant;
import com.example.oa.entity.R;
import com.example.oa.entity.User;
import com.example.oa.exceptionHandler.ServerException;
import com.example.oa.service.impl.UserServiceImpl;
import com.example.oa.utils.AesUtil;
import com.example.oa.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@CrossOrigin("*")
@Slf4j
@Api(tags = "登陆 API")
@RequestMapping("/v1/api")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;


    @NotToken
    @ApiImplicitParam(dataType = "User")
    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public String login(@RequestBody @Valid User u) {
      try {
          QueryWrapper<User> queryWrapper = new QueryWrapper<>();
          queryWrapper.eq("user_name", u.getUserName());

          User user = userService.getOne(queryWrapper);
          if (user == null) {
              return R.OK("201", Constant.Wrong_Account_Or_Password);
          }
          String password = AesUtil.Decrypt(user.getPassword());
          if (!u.getPassword().equals(password)) {
              return R.OK("201", Constant.Wrong_Account_Or_Password);
          }
          user.setPassword("");
          HashMap<String, Object> result = new HashMap<>();
          result.put("token", JwtUtil.createToken(JSON.toJSONString(user)));
          result.put("path", userService.getRoutePathById(user.getId()));
          return R.OK(result);
      }catch (Exception e){
          throw new ServerException(e.getMessage());
      }
    }
}
