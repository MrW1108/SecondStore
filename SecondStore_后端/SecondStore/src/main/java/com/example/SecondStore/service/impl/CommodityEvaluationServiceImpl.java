package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.CommodityEvaluationDao;
import com.example.SecondStore.entity.CommodityEvaluation;
import com.example.SecondStore.entity.vo.CommodityEvaluationInfo;
import com.example.SecondStore.service.CommodityEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 商品评价(CommodityEvaluation)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("commodityEvaluationService")
public class CommodityEvaluationServiceImpl extends ServiceImpl<CommodityEvaluationDao, CommodityEvaluation> implements CommodityEvaluationService {
    @Override
    public Double calculateScore(int commodityId) {
        return baseMapper.calculateScore(commodityId);
    }

    @Override
    public Integer evaluationCount(int commodityId) {
        return baseMapper.evaluationCount(commodityId);
    }

    @Override
    public IPage<CommodityEvaluationInfo> getEvaluationById(IPage<CommodityEvaluationInfo> page, Serializable commodityId) {
        return page.setRecords(this.baseMapper.getCommodityEvaluationById(page, commodityId));
    }
}