package com.example.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.entity.*;
import com.example.oa.mapper.ResourcesMapper;
import com.example.oa.service.IResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
@Service
@Slf4j
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements IResourcesService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private RouteServiceImpl routeService;
    @Autowired
    private RoleRouteServiceImpl roleRouteService;

    @Transactional(rollbackFor = Exception.class)
    public LinkedList<HashMap<String, Object>> queryPage(User u) {
        log.info(u.toString());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (u.getId() != null) {
            wrapper.eq("id", u.getId());
        }
        if (u.getUserName() != null) {
            wrapper.like("user_name", u.getUserName());
        }
        //获取用户id
        Page<User> userPage = new Page<>(u.getCurrent(), u.getSize());
        List<User> users = userService.page(userPage, wrapper).getRecords();
        LinkedList<HashMap<String, Object>> results = new LinkedList<>();
        for (User user : users) {
            HashMap<String, Object> result = new HashMap<>();
            //根据用户id获取角色信息
            List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            List<Integer> roleIds = Collections.emptyList();
            if (!userRoles.isEmpty()) {
                roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            }
            List<Role> roles = Collections.emptyList();
            if (!roleIds.isEmpty()) {
                roles = roleService.list(new QueryWrapper<Role>().in("id", roleIds));
            }
            //根据角色id获取路由信息
            List<RoleRoute> roleRoutes = Collections.emptyList();
            if (!roles.isEmpty()) {
                roleRoutes = roleRouteService.list(new QueryWrapper<RoleRoute>().in("role_id", roleIds));
            }
            List<Integer> routeIds = Collections.emptyList();
            if (!roleRoutes.isEmpty()) {
                routeIds = roleRoutes.stream().map(RoleRoute::getRouteId).collect(Collectors.toList());
            }
            List<Route> routes = Collections.emptyList();
            if (!routeIds.isEmpty()) {
                routes = routeService.list(new QueryWrapper<Route>().in("id", routeIds));
            }
            List<Resources> resourcesList = Collections.emptyList();
            if (!routes.isEmpty()) {
                resourcesList = list(new QueryWrapper<Resources>().select("distinct description", "urls").in("id", routes.stream().map(Route::getResourcesId).collect(Collectors.toList())));
            }
            result.put("id", user.getId());
            result.put("username", user.getUserName());
            result.put("userRoleId", userRoles.stream().map(UserRole::getId).collect(Collectors.toList()));
            result.put("role", roles);
            result.put("routes", routes);
            result.put("resources", resourcesList);

            results.add(result);
        }
        return results;
    }

    public LinkedList<HashMap<String, Object>> queryResources(Role role) {
        LinkedList<HashMap<String, Object>> hashMaps = new LinkedList<>();
        Page<Role> page = new Page<>(role.getCurrent(), role.getSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (role.getDescription() != null) {
            queryWrapper.like("description", role.getDescription());
        }
        if (role.getName() != null) {
            queryWrapper.like("name", role.getName());
        }
        Page<Role> rolePage = roleService.page(page, queryWrapper);
        List<Role> roles = rolePage.getRecords();
        for (Role role_ : roles) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", role_.getId());
            map.put("name", role_.getName());
            map.put("description", role_.getDescription());
            map.put("resources", getResources(role_.getId()));
            hashMaps.add(map);
        }
        return hashMaps;
    }

    public List<Resources> getResources(Integer roleId) {
        List<RoleRoute> roleRoutes = roleRouteService.list(new QueryWrapper<RoleRoute>().eq("role_id", roleId));
        List<Route> routes = routeService.list(new QueryWrapper<Route>().in("id", roleRoutes.stream().map(RoleRoute::getRouteId).collect(Collectors.toList())));
        return list(new QueryWrapper<Resources>().in("id", routes.stream().map(Route::getResourcesId).collect(Collectors.toSet())));
    }
}
