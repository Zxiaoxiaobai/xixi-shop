package com.myspring.xixi.service;

import com.myspring.xixi.domain.Business;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_business】的数据库操作Service
* @createDate 2022-04-15 18:38:41
*/
public interface BusinessService extends IService<Business> {

    public List<Business> getAllBusiness();

    String getShopName(Long belongId);
}
