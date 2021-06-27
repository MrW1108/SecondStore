package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.Commodity;
import com.example.SecondStore.entity.Orders;
import com.example.SecondStore.entity.ShoppingCart;
import com.example.SecondStore.service.CommodityService;
import com.example.SecondStore.service.ShoppingCartService;
import com.example.SecondStore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderService orderService;

    @GetMapping("{userId}")
    public R getShopCraftById(@PathVariable Serializable userId, @RequestParam("page") long page) {
        return success(this.shoppingCartService.page(new Page<>(page, 10), userId));
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart oldShoppingCart = shoppingCartService.getShoppingCartByCommodityId(shoppingCart);
        Commodity commodity = commodityService.getById(shoppingCart.getCommodityId());
        if (commodity == null) return failed("非法参数");

        if (oldShoppingCart == null) {
//            if (shoppingCart.getQuantity() > commodity.getInventory()) {
//                return failed("库存不足");
//            }
            return success(this.shoppingCartService.save(shoppingCart));
        }

        int quantity = shoppingCart.getQuantity() + oldShoppingCart.getQuantity();
//        if (quantity > commodity.getInventory()) {
//            return failed("库存不足");
//        }
        oldShoppingCart.setQuantity(quantity);
        return update(oldShoppingCart);
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCart.getQuantity() <= 0) {
            return success(this.shoppingCartService.removeById(shoppingCart.getId()));
        }
        return success(this.shoppingCartService.updateById(shoppingCart));
    }

    @PostMapping("delete")
    public  R delete(@RequestBody List<ShoppingCart> shoppingCarts){
        if(shoppingCarts.size()>0){
            for (ShoppingCart shoppingCart : shoppingCarts) {
                this.shoppingCartService.removeById(shoppingCart.getId());
            }
            return success(true);
        }
        return failed("操作失败");
    }

    @PostMapping("checkOut")
    public R checkOutShoppingCart(@RequestBody List<ShoppingCart> shoppingCarts) {
        List<Orders> orders = new ArrayList<>();
        List<Commodity> commodities = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            Commodity commodity = commodityService.getById(shoppingCart.getCommodityId());
            int inventory = commodity.getInventory() - shoppingCart.getQuantity();
            if (inventory < 0) return failed("库存不足");
            commodity.setInventory(inventory);
            Orders order = new Orders();
            order.setCommodityId(commodity.getId());
            order.setQuantity(shoppingCart.getQuantity());
            if (commodity.getDiscount() == null || commodity.getDiscount() == 0.0) {
                order.setAmount(commodity.getPrice() * shoppingCart.getQuantity());
            }
            else {
                double amount = commodity.getDiscount() * commodity.getPrice() * shoppingCart.getQuantity();
                amount = new BigDecimal(amount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                order.setAmount(amount);
            }
            order.setUserId(shoppingCart.getUserId());
            order.setShopId(commodity.getShopId());
            orders.add(order);
            commodities.add(commodity);
        }
        orderService.saveBatch(orders);
        commodityService.updateBatchById(commodities);
        return success(orders);
    }
}