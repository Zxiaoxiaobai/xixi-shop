package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Business;
import com.myspring.xixi.service.BusinessService;
import com.myspring.xixi.mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_business】的数据库操作Service实现
* @createDate 2022-04-15 18:38:41
*/
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business>
    implements BusinessService{

    @Autowired
    BusinessMapper businessMapper;


    @Override
    public List<Business> getAllBusiness() {
        return businessMapper.selectList(null);
    }

    @Override
    public String getShopName(Long belongId) {
        return baseMapper.getShopNameById(belongId);
    }
}




