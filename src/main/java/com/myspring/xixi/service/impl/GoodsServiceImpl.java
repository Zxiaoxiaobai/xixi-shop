package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Goods;
import com.myspring.xixi.service.GoodsService;
import com.myspring.xixi.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_goods】的数据库操作Service实现
* @createDate 2022-04-21 19:22:58
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @Override
    public List<Goods> getMyGoods(long longValue) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_id", longValue);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Goods> getAllGoods() {
        return baseMapper.selectList(null);
    }
}




