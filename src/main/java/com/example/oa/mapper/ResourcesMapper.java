package com.example.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.oa.entity.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2024-04-16
 */
public interface ResourcesMapper extends BaseMapper<Resources> {
    List<Resources> getPurviewById(Integer userId);

    Map<String, Object> queryPage(@Param("current") Integer current, @Param("size") Integer size);
}
