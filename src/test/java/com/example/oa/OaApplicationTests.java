package com.example.oa;

import com.example.oa.entity.User;
import com.example.oa.service.impl.ResourcesServiceImpl;
import com.example.oa.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OaApplicationTests {
    @Autowired
    private ResourcesServiceImpl resourcesService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void contextLoads() throws Exception {
//        System.out.println(userService.getBaseMapper().getResourcesById(3));
        System.out.println(resourcesService.queryPage(new User().setId(3)));
    }

}
