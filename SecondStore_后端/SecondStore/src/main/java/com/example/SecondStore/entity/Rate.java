package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * (Rate)表实体类
 *
 * @author bwmgd
 * @since 2021-05-14 14:14:12
 */
@Data
public class Rate implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    //百分比
    private Double rate;
    
}