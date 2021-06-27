package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.User;

import java.io.Serializable;

/**
 * 普通用户(User)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface UserService extends IService<User> {
    User register(Serializable name);

    User login(Serializable name, Serializable password);
}