package com.myspring.xixi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myspring.xixi.common.dto.OrderDTO;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.*;
import com.myspring.xixi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	ReceiveService receiveService;

	@Autowired
	BusinessService businessService;

	@Autowired
	UserService userService;

	@PostMapping("/addOrder")
	public Result addOrder(@RequestBody List<OrderDTO> list){
		list.forEach(orderDTO -> {
			Order order = new Order();
			order.setUserId(orderDTO.getUserId());
			order.setGoodId(orderDTO.getGoodId());
			order.setNum(orderDTO.getNum());
			order.setPrice(orderDTO.getPrice());
			order.setActualPrice(orderDTO.getActualPrice());
			order.setAddressId(orderDTO.getAddressId());
			order.setCreated(LocalDateTime.now());
			order.setDelived(0);
			order.setGetGood(0);
			order.setReGood(0);
			order.setEvaluate(0);
			orderService.save(order);
			UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("id", orderDTO.getGoodId());
			updateWrapper.set("piece", goodsService.getById(orderDTO.getGoodId()).getPiece() - orderDTO.getNum());
			goodsService.update(updateWrapper);
		});
		return Result.success("提交成功", null);
	}

	@GetMapping("/getOrderUser/{userId}")
	public Result getOrderUser(@PathVariable Long userId){
		List<Order> orders = orderService.list(new QueryWrapper<Order>().eq("user_id", userId).orderByDesc("id"));
		List<Map<String, Object>> list = new ArrayList<>();
		orders.forEach(order -> {
			Goods good = goodsService.getById(order.getGoodId());
			Receive address = receiveService.getById(order.getAddressId());
			Business shop = businessService.getById(good.getBelongId());
			Map<String, Object> map = new HashMap<>();
			map.put("order", order);
			map.put("good", good);
			map.put("shop", shop);
			map.put("address", address);
			list.add(map);
		});
		return Result.success(list);
	}

	@GetMapping("/getOrderShop/{shopId}")
	public Result getOrderShop(@PathVariable Long shopId){
		List<Map<String, Object>> orderFromShop = orderService.getOrderFromShop(shopId);
		orderFromShop.forEach(map -> {
			Order order = orderService.getById((Serializable) map.get("orderId"));
			Goods good = goodsService.getById((Serializable) map.get("goodId"));
			User user = userService.getById((Serializable) map.get("userId"));
			Receive address = receiveService.getById((Serializable) map.get("addressId"));
			map.clear();
			map.put("order", order);
			map.put("good", good);
			map.put("user", user);
			map.put("address", address);
		});
		return Result.success(orderFromShop);
	}

	@PostMapping("/getGood")
	public Result confirmGetGood(@RequestBody OrderDTO order){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", order.getId());
		updateWrapper.set("get_good", 1);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}

	@PostMapping("/evaluate")
	public Result evaluate(@RequestBody OrderDTO orderDTO){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", orderDTO.getId());
		updateWrapper.set("evaluate", 1);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}

	@PostMapping("/deleteOrder")
	public Result deleteOrder(@RequestBody OrderDTO orderDTO){
		boolean b = orderService.removeById(orderDTO.getId());
		return Result.success(b);
	}

	@PostMapping("/delived")
	public Result delived(@RequestBody OrderDTO order){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", order.getId());
		updateWrapper.set("delived", 1);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}

	@PostMapping("/reGood")
	public Result reGood(@RequestBody OrderDTO order){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", order.getId());
		updateWrapper.set("re_good", 1);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}

	@PostMapping("/confirmReGood")
	public Result confirmReGood(@RequestBody OrderDTO order){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", order.getId());
		updateWrapper.set("re_good", 2);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}

	@PostMapping("/reGoodComplete")
	public Result reGoodComplete(@RequestBody OrderDTO order){
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", order.getId());
		updateWrapper.set("re_good", 3);
		boolean update = orderService.update(updateWrapper);
		return Result.success(update);
	}
}
