package com.example.SecondStore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SecondStore.entity.Kind;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类别(Kind)表数据库访问层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Mapper
public interface KindDao extends BaseMapper<Kind> {

}