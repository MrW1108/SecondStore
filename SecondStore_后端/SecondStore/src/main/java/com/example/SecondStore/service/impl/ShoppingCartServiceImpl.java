package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.ShoppingCartDao;
import com.example.SecondStore.entity.ShoppingCart;
import com.example.SecondStore.entity.vo.ShoppingInfo;
import com.example.SecondStore.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService {
    @Override
    public ShoppingCart getShoppingCartByCommodityId(ShoppingCart shopCraft) {
        Map<SFunction<ShoppingCart, ?>, Serializable> map = new HashMap<>();
        map.put(ShoppingCart::getCommodityId, shopCraft.getCommodityId());
        map.put(ShoppingCart::getUserId, shopCraft.getUserId());
        return getOne(new LambdaQueryWrapper<ShoppingCart>().allEq(map));
    }

    @Override
    public IPage<ShoppingInfo> page(IPage<ShoppingInfo> page, Serializable userId) {
        return page.setRecords(this.baseMapper.getShoppingCartByUserId(page, userId));
    }
}