package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.CommodityDao;
import com.example.SecondStore.entity.Commodity;
import com.example.SecondStore.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品(Commodity)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("commodityService")
public class CommodityServiceImpl extends ServiceImpl<CommodityDao, Commodity> implements CommodityService {

    @Override
    public List<Commodity> getCommoditiesByShopId(Serializable shopId, Serializable status) {
        Map<SFunction<Commodity, ?>, Serializable> map = new HashMap<>();
        map.put(Commodity::getShopId, shopId);
        map.put(Commodity::getStatus, status);
        return list(new LambdaQueryWrapper<Commodity>().allEq(map));
    }

    @Override
    public List<Commodity> getCommoditiesByKindId(Serializable kindId) {
        Map<SFunction<Commodity, ?>, Serializable> map = new HashMap<>();
        map.put(Commodity::getKindId, kindId);
        map.put(Commodity::getStatus, 1);
        return list(new LambdaQueryWrapper<Commodity>().allEq(map));
    }
}