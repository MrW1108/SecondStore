package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.SellDao;
import com.example.SecondStore.entity.Sell;
import com.example.SecondStore.service.SellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 商家(Sell)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("sellService")
public class SellServiceImpl extends ServiceImpl<SellDao, Sell> implements SellService {

    @Override
    public Sell register(Serializable name) {
        return getOne(new LambdaQueryWrapper<Sell>().eq(Sell::getName, name));
    }

    @Override
    public Sell login(Serializable name, Serializable password) {
        Map<SFunction<Sell,?>, Serializable> map = new HashMap<>();
        map.put(Sell::getName, name);
        map.put(Sell::getPassword, password);
        return getOne(new LambdaQueryWrapper<Sell>().allEq(map));
    }
}