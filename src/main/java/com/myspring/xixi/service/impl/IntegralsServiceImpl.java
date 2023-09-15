package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Integrals;
import com.myspring.xixi.mapper.IntegralsMapper;
import com.myspring.xixi.service.IntegralsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegralsServiceImpl extends ServiceImpl<IntegralsMapper, Integrals>
        implements IntegralsService {
    //    @Autowired
//    IntegralMapper integralMapper;
    @Override
    public List<Integrals> getUpIntegral(){
        QueryWrapper<Integrals> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pass", 0L);
        return baseMapper.selectList(queryWrapper);
    }
}
