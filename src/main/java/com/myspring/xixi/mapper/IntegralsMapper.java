package com.myspring.xixi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.domain.Integrals;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntegralsMapper extends BaseMapper<Integrals> {


}
