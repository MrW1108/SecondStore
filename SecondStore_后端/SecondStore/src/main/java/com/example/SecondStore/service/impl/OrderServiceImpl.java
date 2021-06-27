package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.OrderDao;
import com.example.SecondStore.entity.Orders;
import com.example.SecondStore.service.OrderService;
import com.example.SecondStore.entity.vo.OrdersInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 订单(Order)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements OrderService {
    @Override
    public IPage<OrdersInfo> pageByStatus(IPage<OrdersInfo> page, Serializable status) {
        return page.setRecords(this.baseMapper.getOrdersByStatus(page, status));
    }
}