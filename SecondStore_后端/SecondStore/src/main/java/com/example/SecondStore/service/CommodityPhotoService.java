package com.example.SecondStore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SecondStore.entity.CommodityPhoto;

import java.io.Serializable;
import java.util.List;

/**
 * 商品图片(CommodityPhoto)表服务接口
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
public interface CommodityPhotoService extends IService<CommodityPhoto> {
    List<CommodityPhoto> getPhotoByCommodityId(Serializable commodityId);
}