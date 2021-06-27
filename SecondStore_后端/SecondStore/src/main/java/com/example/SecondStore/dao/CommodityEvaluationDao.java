package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.SecondStore.entity.CommodityEvaluation;
import com.example.SecondStore.entity.vo.CommodityEvaluationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 商品评价(CommodityEvaluation)表数据库访问层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Mapper
public interface CommodityEvaluationDao extends BaseMapper<CommodityEvaluation> {
    @Select("select avg(score) from commodity_evaluation where commodity_id = #{commodityId}")
    Double calculateScore(int commodityId);

    @Select("select count(*) from commodity_evaluation where commodity_id = #{commodityId}")
    Integer evaluationCount(int commodityId);

    @Select("select * from commodity_evaluation c join user u on c.user_id = u.id where c.commodity_id = #{commodityId}")
    List<CommodityEvaluationInfo> getCommodityEvaluationById(IPage<CommodityEvaluationInfo> page, Serializable commodityId);
}