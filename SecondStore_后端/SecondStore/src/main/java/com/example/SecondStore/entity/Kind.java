package com.example.SecondStore.entity;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 类别(Kind)表实体类
 *
 * @author bwmgd
 * @since 2021-05-11 10:55:25
 */
@Data
public class Kind implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    
    private String name;
    
}