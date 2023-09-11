package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Order;
import com.myspring.xixi.service.OrderService;
import com.myspring.xixi.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 惠普
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2022-06-16 11:19:40
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

	@Override
	public List<Map<String, Object>> getOrderFromShop(Long shopId) {
		return baseMapper.getOrderInShop(shopId);
	}
}




