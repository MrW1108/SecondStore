package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 商品(Commodity)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 18:44:15
 */
@Data
public class Commodity implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private Integer shopId;
    
    private String shopName;
    
    private String photoOne;
    
    private Integer inventory;
    
    private Integer kindId;
    
    private Double price;
    
    private Integer degree;
    
    private Double discount;
    
    //0:不许议价,1:准许议价
    private Integer bargain;
    
    private String size;
    
    private String instruction;
    
    //0:待审核,1:发布,2:锁定,3:下架
    private Integer status;
    
    //平均分数(5分制)
    private Double score;
    
    private Integer evaluationCount;
    
}