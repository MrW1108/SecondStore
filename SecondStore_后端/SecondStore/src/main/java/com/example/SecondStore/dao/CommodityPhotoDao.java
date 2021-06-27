package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.CommodityPhoto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品图片(CommodityPhoto)表数据库访问层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Mapper
public interface CommodityPhotoDao extends BaseMapper<CommodityPhoto> {

}