package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.Commodity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(Commodity)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface CommodityService extends IService<Commodity> {
    List<Commodity> getCommoditiesByShopId(Serializable shopId,Serializable status);
    List<Commodity> getCommoditiesByKindId(Serializable kindId);
}