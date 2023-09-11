package com.myspring.xixi.service;

import com.myspring.xixi.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 惠普
* @description 针对表【t_order】的数据库操作Service
* @createDate 2022-06-16 11:19:40
*/
public interface OrderService extends IService<Order> {
	List<Map<String, Object>> getOrderFromShop(Long shopId);
}
