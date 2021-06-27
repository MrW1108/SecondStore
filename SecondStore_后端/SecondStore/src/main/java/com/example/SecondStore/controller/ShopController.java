package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.SecondStore.entity.Shop;
import com.example.SecondStore.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 店铺(Shop)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("shop")
public class ShopController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopService shopService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/shopId{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.shopService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param shop 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Shop shop) {
        return success(this.shopService.save(shop));
    }
}