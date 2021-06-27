package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.SecondStore.entity.Commodity;
import com.example.SecondStore.entity.CommodityPhoto;
import com.example.SecondStore.service.CommodityPhotoService;
import com.example.SecondStore.service.CommodityService;
import com.example.SecondStore.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品图片(CommodityPhoto)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("commodityPhoto")
public class CommodityPhotoController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private CommodityPhotoService commodityPhotoService;
    @Autowired
    private CommodityService commodityService;

    @GetMapping("{commodityId}")
    public R selectByCommodityID(@PathVariable Serializable commodityId) {
        return success(this.commodityPhotoService.getPhotoByCommodityId(commodityId));
    }

    @PostMapping
    public R insert(@RequestParam("file") MultipartFile[] file,
                    @RequestParam("commodityId") Integer commodityId) {
        log.info(commodityId+"sdad");
        log.info(String.valueOf(commodityId));
        log.info(file[0].getName());
        if (commodityId == null) return failed("非法id");
        List<CommodityPhoto> commodityPhotoList = new ArrayList<>();
        try {
            for (MultipartFile p : file) {
                log.info(String.valueOf(p.getSize()));
                CommodityPhoto photo = new CommodityPhoto();
                photo.setCommodityId(commodityId);
                photo.setPhoto(Util.SaveImage(p));
                commodityPhotoList.add(photo);
            }
            Commodity commodity = commodityService.getById(commodityId);
            commodity.setPhotoOne(commodityPhotoList.get(0).getPhoto());
            commodityService.updateById(commodity);
        } catch (IOException e) {
            e.printStackTrace();
            return failed(e.getMessage());
        }
        return success(this.commodityPhotoService.saveBatch(commodityPhotoList));
    }

    /**
     * 修改数据
     *
     * @param commodityPhoto 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody CommodityPhoto commodityPhoto) {
        return success(this.commodityPhotoService.updateById(commodityPhoto));
    }

    @PostMapping("delete")
    public R delete(@RequestBody CommodityPhoto commodityPhoto) {
        return success(this.commodityPhotoService.removeById(commodityPhoto.getId()));
    }
}