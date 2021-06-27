package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 店铺评价(ShopEvaluation)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 13:28:12
 */
@Data
public class ShopEvaluation implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String evaluation;
    
    private Integer shopId;
    
    //0~5星级
    private Integer score;
    
    private Integer userId;
    
}