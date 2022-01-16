package com.example.springtest.dao;

import com.example.springtest.entity.ResourceLock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description
 *
 * @author junqing.zhang@hand-china.com 2021/12/28 18:54
 */
@Mapper
public interface ResourceLockMapper {

    List<ResourceLock> selectResourceLock(@Param("resourceName") String resourceName);

    void insert(@Param("dto") ResourceLock dto);

    void delete(@Param("resourceName") String resourceName);
}
