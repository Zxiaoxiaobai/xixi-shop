package com.myspring.xixi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Bank;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.BankService;
import com.myspring.xixi.service.IntegralService;
import com.myspring.xixi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BankController {

	@Autowired
	BankService bankService;

	@Autowired
	UserService userService;

	@Autowired
	IntegralService integralService;

	@PostMapping("/addBank")
	public Result addBank(@RequestBody Bank bank){
		bankService.save(bank);
		return Result.success(null);
	}

	@GetMapping("/getMoney/{userId}")
	public Result getMoney(@PathVariable Long userId){
		User user = userService.getById(userId);
		QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bank_account", user.getBankaccount());
		Bank bank = bankService.getOne(queryWrapper);
		return Result.success(bank);
	}

	@PostMapping("/changeMoney")
	public Result changeMoney(@RequestBody Map<String, Object> map){
		QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bank_account", map.get("bankAccount"));
		Bank userBank = bankService.getOne(queryWrapper);
		Bank managerBank = bankService.getById(5L);
		userBank.setMoney(userBank.getMoney().subtract(new BigDecimal(String.valueOf(map.get("money")))));
		managerBank.setMoney(managerBank.getMoney().add(new BigDecimal(String.valueOf(map.get("money")))));
		List<Bank> list = new ArrayList<>();
		list.add(userBank);
		list.add(managerBank);
		bankService.updateBatchById(list);
		QueryWrapper<Integral> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("user_id", map.get("userId"));
		Integral integral = integralService.getOne(queryWrapper1);
		integral.setNumber(integral.getNumber() + Integer.parseInt(String.valueOf(map.get("money"))));
		integralService.updateById(integral);
		return Result.success(null);
	}

	@PostMapping("/payMoney")
	public Result payMoney(@RequestBody Map<String, Object> map){
//		double percent = switch ((Integer) map.get("level")){
//			case 5 -> 0.95;
//			case 4 -> 0.96;
//			case 3 -> 0.97;
//			case 2 -> 0.98;
//			case 1 -> 0.99;
//			default -> 0.95;
//		};
		double percent =0.98;
		BigDecimal price = new BigDecimal(String.valueOf(map.get("money")));
		price = price.multiply(new BigDecimal(String.valueOf(percent)));

		QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bank_account", map.get("bankAccount"));
		Bank businessBank = bankService.getOne(queryWrapper);
		Bank managerBank = bankService.getById(5L);
		businessBank.setMoney(businessBank.getMoney().add(price));
		managerBank.setMoney(managerBank.getMoney().subtract(price));
		List<Bank> list = new ArrayList<>();
		list.add(businessBank);
		list.add(managerBank);
		bankService.updateBatchById(list);
		return Result.success(null);
	}

	@PostMapping("/reMoney")
	public Result reMoney(@RequestBody Map<String, Object> map){
		QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bank_account", map.get("bankAccount"));
		Bank userBank = bankService.getOne(queryWrapper);
		Bank managerBank = bankService.getById(5L);
		BigDecimal price = new BigDecimal(String.valueOf(map.get("price")));
		userBank.setMoney(userBank.getMoney().add(price));
		managerBank.setMoney(managerBank.getMoney().subtract(price));
		List<Bank> list = new ArrayList<>();
		list.add(userBank);
		list.add(managerBank);
		bankService.updateBatchById(list);
		return Result.success(null);
	}

}
