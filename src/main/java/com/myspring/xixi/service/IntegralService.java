package com.myspring.xixi.service;

import com.myspring.xixi.domain.Integral;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myspring.xixi.domain.Integrals;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_integral】的数据库操作Service
* @createDate 2022-06-20 17:12:36
*/
public interface IntegralService extends IService<Integral> {

    public List<Integral> getAllIntegral();
    public List<Integral> getUpIntegral();
}
