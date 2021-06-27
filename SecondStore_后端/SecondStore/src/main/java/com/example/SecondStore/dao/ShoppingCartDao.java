package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.SecondStore.entity.ShoppingCart;
import com.example.SecondStore.entity.vo.ShoppingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
    @Select("select * from shopping_cart s join commodity c on c.id = s.commodity_id where s.user_id = #{userId}")
    List<ShoppingInfo> getShoppingCartByUserId(IPage<ShoppingInfo> page, Serializable userId);
}