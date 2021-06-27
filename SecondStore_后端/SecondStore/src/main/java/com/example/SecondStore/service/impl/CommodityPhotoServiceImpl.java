package com.example.SecondStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SecondStore.dao.CommodityPhotoDao;
import com.example.SecondStore.entity.CommodityPhoto;
import com.example.SecondStore.service.CommodityPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 商品图片(CommodityPhoto)表服务实现类
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@Service("commodityPhotoService")
public class CommodityPhotoServiceImpl extends ServiceImpl<CommodityPhotoDao, CommodityPhoto> implements CommodityPhotoService {

    @Override
    public List<CommodityPhoto> getPhotoByCommodityId(Serializable commodityId) {
        return list(new LambdaQueryWrapper<CommodityPhoto>().eq(CommodityPhoto::getCommodityId, commodityId));
    }
}