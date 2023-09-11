package com.myspring.xixi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class IntegralController {

	@Autowired
	IntegralService integralService;

	@PostMapping("/addIntegral")
	public Result addIntegral(@RequestBody Integral integral){
		integralService.save(integral);
		return Result.success(null);
	}

	@GetMapping("/getIntegral/{userId}")
	public Result getIntegral(@PathVariable Long userId){
		QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		Integral integral = integralService.getOne(queryWrapper);
		return Result.success(integral.getNumber());
	}

	@PostMapping("/useIntegral")
	public Result useIntegral(@RequestBody Integral integral){
		QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", integral.getUserId());
		Integral getIntegral = integralService.getOne(queryWrapper);
		getIntegral.setNumber(getIntegral.getNumber() - integral.getNumber());
		integralService.updateById(getIntegral);
		return Result.success(null);
	}
}
