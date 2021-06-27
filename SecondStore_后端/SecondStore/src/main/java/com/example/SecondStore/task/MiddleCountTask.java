package com.example.SecondStore.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SecondStore.dao.*;
import com.example.SecondStore.entity.Orders;
import com.example.SecondStore.entity.Sell;
import com.example.SecondStore.entity.User;
import com.example.SecondStore.entity.MiddleCount;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class MiddleCountTask {

    @Resource
    private MiddleCountDao middleCountDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private UserDao userDao;
    @Resource
    private SellDao sellDao;
    @Resource
    private RateDao rateDao;

    //3.添加定时任务
    // @Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0/20 * * * * ?")
    private void configureTasks() {
        QueryWrapper<MiddleCount> creatTime =
                new QueryWrapper<MiddleCount>().le("creat_time", LocalDateTime.now()
                        // .plusHours(-1)
                        .plusSeconds(-20)
                );

        List<MiddleCount> middleCounts = middleCountDao.selectList(creatTime);
        System.out.println(middleCounts);
        if (middleCounts.isEmpty()) return;
        for (MiddleCount middleCount : middleCounts) {
            QueryWrapper<Orders> orderId = new QueryWrapper<Orders>().eq("id", middleCount.getOrderId());
            Orders orders = orderDao.selectOne(orderId);
            orders.setCanReturn(1);
            orderDao.updateById(orders);
            User user = userDao.selectOne(new QueryWrapper<User>().eq("id", middleCount.getUserId()));
            user.setPoints(user.getPoints() + middleCount.getAmount());
            userDao.updateById(user);
            Sell sell = sellDao.selectOne(new QueryWrapper<Sell>().eq("id", middleCount.getSellerId()));
            Integer level = sell.getLevel();
            System.out.println(level);
            sell.setWallet(sell.getWallet() + middleCount.getAmount() *
                    (1 - rateDao.selectById(level).getRate()));
            sellDao.updateById(sell);
            middleCountDao.deleteById(middleCount.getId());
        }
    }
}