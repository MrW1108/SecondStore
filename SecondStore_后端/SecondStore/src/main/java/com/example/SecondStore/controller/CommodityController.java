package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.Commodity;
import com.example.SecondStore.service.CommodityService;
import com.example.SecondStore.entity.Shop;
import com.example.SecondStore.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

/**
 * 商品(Commodity)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("commodity")
public class CommodityController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ShopService shopService;

    @GetMapping
    public R selectAll(@RequestParam("page") long page) {
        return success(this.commodityService.page(new Page<>(page, 10),new QueryWrapper<Commodity>().eq("status", 1)));
    }

    /**
     * 查询未审核商品列表
     *
     * @param map 按关键字map查询
     * @return 商品列表
     */
    @PostMapping("audit")
    public R selectBy(@RequestBody Map<String, String> map) {
        return success(this.commodityService.list(new QueryWrapper<Commodity>().allEq(map)));
    }

    @PostMapping("searchOrder")
    public R searchOrder(@RequestBody Map<String, String[]> map) {
        int page = 1;
        int status = 1;
        if (mapExist(map, "status")) status = Integer.parseInt(map.get("status")[0]);
        QueryWrapper<Commodity> wrapper = new QueryWrapper<Commodity>().eq("status", status);
        if (mapExist(map, "page")) page = Integer.parseInt(map.get("page")[0]);
        if (mapExist(map, "shop_id")) wrapper.eq("shop_id", map.get("shop_id")[0]); //按店铺
        if (mapExist(map, "kind_id")) wrapper.eq("kind_id", map.get("kind_id")[0]); //按类别
        if (mapExist(map, "keyword")) { //多关键词查找
            for (String keyword : map.get("keyword")) {
                wrapper.like("name", keyword);
            }
        }
        if (mapExist(map, "asc")) wrapper.orderByAsc(map.get("asc")); //关键列升序
        if (mapExist(map, "desc")) wrapper.orderByDesc(map.get("desc")); //关键列降序
        return success(this.commodityService.page(new Page<>(page, 10), wrapper)); //返回列表
    }

    private Boolean mapExist(Map<String, String[]> map, String key) { //判断map中是否包含对应字段
        return map.containsKey(key) && map.get(key).length != 0;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.commodityService.getById(id));
    }

    /**
     * 按商品状态查询商品
     *
     * @param page      分页对象
     * @param commodity 查询实体
     * @param status    商品状态
     * @return 商品list
     */
    @GetMapping("shop/status/{status}")
    public R selectByStatus(Page<Commodity> page, Commodity commodity, @PathVariable Serializable status) {
        return success(this.commodityService.page(page, new QueryWrapper<>(commodity).eq("status", status)));
    }

    /**
     * 新增数据
     *
     * @param commodity 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Commodity commodity) {
        Shop shop = shopService.getById(commodity.getShopId());
        log.info(shop.getName());
        commodity.setShopName(shop.getName());
        log.info(commodity.toString());
        if (this.commodityService.save(commodity)) {
            return success(commodity.getId());
        }
        return failed("error");
    }

    /**
     * 修改数据
     *
     * @param commodity 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody Commodity commodity) {
        return success(this.commodityService.updateById(commodity));
    }
}