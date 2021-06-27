package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.Sell;

import java.io.Serializable;

/**
 * 商家(Sell)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface SellService extends IService<Sell> {
    Sell register(Serializable name);

    Sell login(Serializable name, Serializable password);
}