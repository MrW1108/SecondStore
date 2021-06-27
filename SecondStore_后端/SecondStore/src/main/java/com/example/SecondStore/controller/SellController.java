package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.Sell;
import com.example.SecondStore.service.SellService;
import com.example.SecondStore.entity.Shop;
import com.example.SecondStore.service.ShopService;
import com.example.SecondStore.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * 商家(Sell)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("sell")
public class SellController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private SellService sellService;
    @Autowired
    private ShopService shopService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(@RequestParam("page") long page, @RequestParam(name = "status", required = false) Serializable status) {
        LambdaQueryWrapper<Sell> wrapper = new LambdaQueryWrapper<>();
        if (status != null) wrapper.eq(Sell::getStatus, status);
        return success(this.sellService.page(new Page<>(page, 20), wrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sellService.getById(id));
    }


    @PostMapping("/register")
    public R insert(@RequestParam("idCardUp") MultipartFile idCardUp,
                    @RequestParam("idCardBack") MultipartFile idCardBack,
                    @RequestParam("license") MultipartFile license,
                    @RequestParam("sell") String sellJson
    ) {
        try {
            Sell sell = new ObjectMapper().readValue(sellJson, Sell.class);
            if (sellService.register(sell.getName()) != null) {
                return failed("用户名重复");
            }
            try {
                sell.setIdCardUp(Util.SaveImage(idCardUp));
                sell.setIdCardBack(Util.SaveImage(idCardBack));
                sell.setLicense(Util.SaveImage(license));
            } catch (IOException e) {
                log.info(e.getMessage());
                return failed(e.getMessage());
            }
            log.info(sell.getName());
            this.sellService.save(sell);
            Shop shop = new Shop();
            shop.setSellerId(sell.getId());
            shop.setName(sell.getName());
            if (shopService.save(shop)) return success(shop.getId());
            return failed("error");
        } catch (IOException e) {
            log.error(e.getMessage());
            return failed(e.getMessage());
        }
    }

    @PostMapping
    public R selectSellList(@RequestBody Map<String, String> map) {
        return success(this.sellService.list(new QueryWrapper<Sell>().allEq(map)));
    }

    /**
     * 修改数据
     *
     * @param sell 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody Sell sell) {
        return success(this.sellService.updateById(sell));
    }

    @PostMapping("delete")
    public R delete(@RequestBody Sell sell) {
        return success(this.sellService.removeById(sell.getId()));
    }

    @PostMapping("login")
    public R login(@RequestBody Sell sell) {
        if ((sell = sellService.login(sell.getName(), sell.getPassword())) != null) {
            if(sell.getStatus() == 1) return success(sell);
            else if(sell.getStatus() == 0) return failed("审核中,请耐心等待！");
            else return failed("该账号审核失败，请注册新账号！");
        }
        return failed("用户名或密码错误");
    }
}