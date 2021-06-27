package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.Shop;
import com.example.SecondStore.entity.ShopEvaluation;
import com.example.SecondStore.service.ShopEvaluationService;
import com.example.SecondStore.service.ShopService;
import com.example.SecondStore.service.impl.ShopServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 店铺评价(ShopEvaluation)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("shopEvaluation")
public class ShopEvaluationController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopEvaluationService shopEvaluationService;

    /**
     * 新增数据
     *
     * @param shopEvaluation 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ShopEvaluation shopEvaluation) {
        boolean save = this.shopEvaluationService.save(shopEvaluation);
        ShopService shopService = new ShopServiceImpl();
        Shop shop = shopService.getById(shopEvaluation.getShopId());
        shop.setScore(shopEvaluationService.calculateScore(shopEvaluation.getShopId()));
        shopService.updateById(shop);
        return success(save);
    }

    @GetMapping("{shopId}")
    public R getShopEvaluation(@PathVariable Serializable shopId, @RequestParam("page") long page) {
        return success(this.shopEvaluationService.page(new Page<>(page, 15),
                new LambdaQueryWrapper<ShopEvaluation>().eq(ShopEvaluation::getShopId, shopId)));
    }
}