package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Goods;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.IntegralService;
import com.myspring.xixi.mapper.IntegralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_integral】的数据库操作Service实现
* @createDate 2022-06-20 17:12:35
*/
@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral>
    implements IntegralService{
//    @Autowired
//    IntegralMapper integralMapper;
    public List<Integral> getAllIntegral(){
        return baseMapper.selectList(null);
    }
    public List<Integral> getUpIntegral(){
        QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pass", 0L);
        return baseMapper.selectList(queryWrapper);
    }
}




