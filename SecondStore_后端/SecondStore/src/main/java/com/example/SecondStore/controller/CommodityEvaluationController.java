package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.Commodity;
import com.example.SecondStore.entity.CommodityEvaluation;
import com.example.SecondStore.service.CommodityEvaluationService;
import com.example.SecondStore.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 商品评价(CommodityEvaluation)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("commodityEvaluation")
public class CommodityEvaluationController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private CommodityEvaluationService commodityEvaluationService;

    @Autowired
    private CommodityService commodityService;

    /**
     * 新增数据
     *
     * @param commodityEvaluation 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody CommodityEvaluation commodityEvaluation) {
        boolean save = this.commodityEvaluationService.save(commodityEvaluation);
        if(save){
            Commodity commodity = commodityService.getById(commodityEvaluation.getCommodityId());
            commodity.setEvaluationCount(commodityEvaluationService.evaluationCount(commodity.getId()));
            commodity.setScore(commodityEvaluationService.calculateScore(commodity.getId()));
            commodityService.updateById(commodity);
            return success(true);
        }else {
            return failed("评论失败");
        }
    }

    @GetMapping("/commodity/{commodityId}")
    public R getCommodityEvaluation(@PathVariable Serializable commodityId, @RequestParam("page") long page) {
        return success(this.commodityEvaluationService.getEvaluationById(new Page<>(page, 20),commodityId));
    }
}