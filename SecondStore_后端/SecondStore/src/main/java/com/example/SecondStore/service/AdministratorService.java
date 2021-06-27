package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.Administrator;

import java.io.Serializable;

/**
 * 管理员(Administrator)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface AdministratorService extends IService<Administrator> {
    Administrator login(Serializable name, Serializable password);
}