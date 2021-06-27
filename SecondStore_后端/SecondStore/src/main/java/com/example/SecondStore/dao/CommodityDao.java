package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品(Commodity)表数据库访问层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Mapper
public interface CommodityDao extends BaseMapper<Commodity> {
}