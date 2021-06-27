package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.ShopDao;
import com.example.SecondStore.entity.Shop;
import com.example.SecondStore.service.ShopService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 店铺(Shop)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("shopService")
public class ShopServiceImpl extends ServiceImpl<ShopDao, Shop> implements ShopService {
}