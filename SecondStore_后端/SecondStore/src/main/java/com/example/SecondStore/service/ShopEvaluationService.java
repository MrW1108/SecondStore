package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.ShopEvaluation;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺评价(ShopEvaluation)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface ShopEvaluationService extends IService<ShopEvaluation> {
    List<ShopEvaluation> getEvaluationsByShopId(Serializable shopId);
    Double calculateScore(int shopId);
}