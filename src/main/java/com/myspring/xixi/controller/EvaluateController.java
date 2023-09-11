package com.myspring.xixi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Evaluates;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.BusinessService;
import com.myspring.xixi.service.EvaluatesService;
import com.myspring.xixi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class EvaluateController {

	@Autowired
	EvaluatesService evaluatesService;

	@Autowired
	UserService userService;

	@Autowired
	BusinessService businessService;

	@PostMapping("/addEvaluate")
	public Result addEvaluate(@RequestBody Evaluates evaluates){
		if(evaluates.getEvaluate().equals("")){
			evaluates.setEvaluate("买家暂无更多评论");
		}
		evaluates.setCreated(LocalDateTime.now());
		evaluatesService.saveOrUpdate(evaluates);
		return Result.success(null);
	}

	@GetMapping("/getEvaluate/{goodId}")
	public Result getEvaluate(@PathVariable Long goodId){
		List<Evaluates> goods = evaluatesService.list(new QueryWrapper<Evaluates>().eq("good_id", goodId).orderByDesc("created"));
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		int step = 0;
		if(goods.size() != 0){
			for (Evaluates good : goods) {
				Map<String, Object> eMap = new HashMap<>();
				step += good.getStarLevel();
				User user = userService.getById(good.getUserId());
				eMap.put("created", good.getCreated());
				eMap.put("username", user.getUsername());
				eMap.put("msg", good.getEvaluate());
				list.add(eMap);
			}
			BigDecimal rate = new BigDecimal(step);
			rate = rate.divide(new BigDecimal(goods.size()), 1, RoundingMode.HALF_UP);
			map.put("rate", rate);
			map.put("userMsg", list);
			return Result.success(map);
		}else{
			map.put("rate", step);
			map.put("userMsg", list);
			return Result.success(map);
		}
	}

	@GetMapping("/getShopDetail/{shopId}")
	public Result getShopDetail(@PathVariable Long shopId){
		List<Integer> goodEvaluate = evaluatesService.getGoodEvaluate(shopId);
		BigDecimal rate = new BigDecimal(0);
		if(goodEvaluate.size() != 0){
			int step = 0;
			for (Integer integer : goodEvaluate) {
				step += integer;
			}
			rate = new BigDecimal(step);
			rate = rate.divide(new BigDecimal(goodEvaluate.size()), 1, RoundingMode.HALF_UP);
		}
		String shopName = businessService.getShopName(shopId);
		Map<String, Object> map = new HashMap<>();
		map.put("rate", rate);
		map.put("shopName", shopName);
		return Result.success(map);
	}
}
