package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.MiddleCount;

import java.io.Serializable;

/**
 * 中间用户(MiddleCount)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface MiddleCountService extends IService<MiddleCount> {
    MiddleCount getMiddleCountByOrderId(Serializable orderId);
}