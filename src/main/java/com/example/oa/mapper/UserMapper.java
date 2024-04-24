package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.entity.Resources;
import com.example.oa.entity.Route;
import com.example.oa.entity.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
public interface UserMapper extends BaseMapper<User> {
    List<Resources> getResourcesById(Integer userId);

    List<Route> getRoutePathById(Integer id);
}
