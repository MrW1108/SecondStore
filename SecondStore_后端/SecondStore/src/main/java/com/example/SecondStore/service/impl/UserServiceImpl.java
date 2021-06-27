package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.UserDao;
import com.example.SecondStore.entity.User;
import com.example.SecondStore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 普通用户(User)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public User register(Serializable name) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getName, name));
    }

    @Override
    public User login(Serializable name, Serializable password) {
        Map<SFunction<User, ?>, Serializable> map = new HashMap<>();
        map.put(User::getName, name);
        map.put(User::getPassword, password);
        return getOne(new LambdaQueryWrapper<User>().allEq(map));
    }
}