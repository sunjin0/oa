package com.example.oa.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.entity.Route;
import com.example.oa.entity.User;
import com.example.oa.mapper.UserMapper;
import com.example.oa.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public String getRoutePathById(Integer id) {
        List<Route> routes = getBaseMapper().getRoutePathById(id);
        return JSON.toJSONString(routes);
    }

}
