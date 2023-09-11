package com.myspring.xixi.mapper;

import com.myspring.xixi.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author 惠普
* @description 针对表【t_order】的数据库操作Mapper
* @createDate 2022-06-16 11:19:40
* @Entity com.myspring.xixi.domain.Order
*/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

	@MapKey("id")
	List<Map<String, Object>> getOrderInShop(@Param("shopId") Long shopId);

}




