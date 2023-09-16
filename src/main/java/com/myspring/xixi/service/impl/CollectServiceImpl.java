package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Collect;
import com.myspring.xixi.mapper.CollectMapper;
import com.myspring.xixi.service.CollectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
        implements CollectService {
    @Override
    public List<Collect> GetAllGoodsId(Integer uuid){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uuid);
        return baseMapper.selectList(queryWrapper);
    }
}
