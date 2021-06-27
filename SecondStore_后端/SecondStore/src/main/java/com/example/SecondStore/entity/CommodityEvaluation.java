package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 商品评价(CommodityEvaluation)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 10:55:25
 */
@Data
public class CommodityEvaluation implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String evaluation;
    
    private Integer commodityId;
    
    //0~5星级
    private Integer score;
    
    private Integer userId;
    
}