package com.example.springtest.dao;

import com.example.springtest.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * desc
 *
 * @author nexus 2022/02/15 15:53
 */
@Mapper
public interface UserMapper {
    List<User> selectUser();
}
