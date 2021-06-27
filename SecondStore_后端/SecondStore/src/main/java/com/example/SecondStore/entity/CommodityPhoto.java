package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 商品图片(CommodityPhoto)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 18:27:33
 */
@Data
public class CommodityPhoto implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String photo;
    
    private Integer commodityId;
    
}