package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Evaluates;
import com.myspring.xixi.service.EvaluatesService;
import com.myspring.xixi.mapper.EvaluatesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_evaluates】的数据库操作Service实现
* @createDate 2022-06-08 15:11:23
*/
@Service
public class EvaluatesServiceImpl extends ServiceImpl<EvaluatesMapper, Evaluates>
    implements EvaluatesService{

    @Override
    public List<Evaluates> getEvaluateByUserId(Long userId) {
        QueryWrapper<Evaluates> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Integer> getGoodEvaluate(Long shopId) {
        return baseMapper.getGoodEvaluate(shopId);
    }
}




