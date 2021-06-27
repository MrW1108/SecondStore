package com.example.SecondStore.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 中间用户(MiddleCount)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 10:55:25
 */
@Data
public class MiddleCount implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private Integer userId;
    
    private Integer sellerId;
    
    private Integer orderId;
    
    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;
    
}