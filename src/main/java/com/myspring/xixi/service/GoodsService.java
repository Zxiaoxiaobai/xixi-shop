package com.myspring.xixi.service;

import com.myspring.xixi.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_goods】的数据库操作Service
* @createDate 2022-04-21 19:22:58
*/
public interface GoodsService extends IService<Goods> {


    List<Goods> getMyGoods(long longValue);

    List<Goods> getAllGoods();
}
