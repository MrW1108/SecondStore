package com.example.SecondStore.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 订单(Order)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 10:55:25
 */
@Data
public class Orders implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private Integer commodityId;
    
    private Integer quantity;
    
    private Double amount;
    
    private Integer userId;
    
    private Integer shopId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    //0:待支付,1:取消支付,2:等待发货,3:运输中,4:已收货,5:退货审核中,6:已退货,7:拒绝退货,8:已完成
    private Integer status;
    
    //0:能退货,1:不能退货
    private Integer canReturn;
    
}