package com.chuf.batchInsert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuf.batchInsert.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer addUserOneByOne(User user);

    void addByOneSQL(@Param("users") List<User> users);
}
