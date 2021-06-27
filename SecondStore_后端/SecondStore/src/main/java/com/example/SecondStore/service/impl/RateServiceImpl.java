package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.RateDao;
import com.example.SecondStore.entity.Rate;
import com.example.SecondStore.service.RateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (Rate)表服务实现类
 *
 * @author bwmgd
 * @since 2021-05-14 14:14:12
 */
@Slf4j
@Service("rateService")
public class RateServiceImpl extends ServiceImpl<RateDao, Rate> implements RateService {

}