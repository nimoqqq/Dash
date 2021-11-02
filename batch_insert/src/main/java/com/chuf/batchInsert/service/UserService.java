package com.chuf.batchInsert.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuf.batchInsert.mapper.UserMapper;
import com.chuf.batchInsert.model.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 一条一条插入
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserOneByOne(List<User> users) {
        long startTime = System.currentTimeMillis();
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper um = session.getMapper(UserMapper.class);
        for (User user : users) {
            um.addUserOneByOne(user);
        }
        session.flushStatements();
        long endTime = System.currentTimeMillis();
        logger.info("一条条插入 SQL 耗费时间 {}", (endTime - startTime));
    }

    /**
     * 合并成一条 SQL 插入
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    public void addByOneSQL(List<User> users) {
        long startTime = System.currentTimeMillis();
        userMapper.addByOneSQL(users);
        long endTime = System.currentTimeMillis();
        logger.info("合并成一条 SQL 插入耗费时间 {}", (endTime - startTime));
    }
}
