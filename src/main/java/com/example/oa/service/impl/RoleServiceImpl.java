package com.example.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oa.common.Constant;
import com.example.oa.entity.R;
import com.example.oa.entity.Role;
import com.example.oa.entity.RoleRoute;
import com.example.oa.mapper.RoleMapper;
import com.example.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleRouteServiceImpl roleRouteService;

    @Transactional(rollbackFor = Exception.class)
    public R saveRoleRouteResources(Role role) {
        boolean isSave = save(role);
        for (Integer i : role.getRouteIds()) {
            roleRouteService.save(new RoleRoute(null, role.getId(), i));
        }
        if (isSave) {
            return R.OK(Constant.Save_Successfully);
        }
        return R.Worm(Constant.Parameter_Error);

    }
}
