package com.example.oa.aop;

import com.alibaba.fastjson.JSON;
import com.example.oa.entity.R;
import com.example.oa.entity.Resources;
import com.example.oa.entity.User;
import com.example.oa.service.impl.UserServiceImpl;
import com.example.oa.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
public class Jurisdiction {
    @Autowired
    private UserServiceImpl userService;

    @Pointcut("execution(* com.example.oa.controller.*.*(..))")
    public void log() {

    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(request.getRequestURI());
        log.info(Arrays.toString(joinPoint.getArgs()));
        // 获取请求头中的token
        String token = request.getHeader("token");
        //没有Token就进入检测是否带有NotToken注解，有就放行，无返回没有权限
        if (token == null) {
            // 获取方法签名
            String methodName = joinPoint.getSignature().getName();
            // 获取方法对象
            Object[] args = joinPoint.getArgs();
            Method method = null;
            try {
                method = joinPoint.getTarget().getClass().getMethod(methodName, getParameterTypes(args));
            } catch (NoSuchMethodException e) {
                log.error(e.getMessage());
            }
            // 判断方法上是否有NotToken注解
            if (method != null && method.isAnnotationPresent(NotToken.class)) {
                // 执行需要的操作
                return joinPoint.proceed();
            } else {
                return R.OK("请登陆后再试！"); // 返回修改后的响应
            }
        }
        //检测是否过期
        boolean isExpired = JwtUtil.isTokenExpired(token.trim());
        if (isExpired) {
            // 在目标方法执行后的逻辑
            return R.OK("403", "无效token"); // 返回修改后的响应
        }
        User user = JSON.parseObject(JwtUtil.getInfo(token), User.class);
        List<Resources> resources = userService.getBaseMapper().getResourcesById(user.getId());
        for (Resources resource : resources) {
            String url = resource.getUrls().trim();
            if (request.getRequestURI().contains(url)) {
                return joinPoint.proceed();
            }
        }
        log.error("没有权限访问...");
        return R.OK("403", "请获取权限后再试！");
    }

    private Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }
}
