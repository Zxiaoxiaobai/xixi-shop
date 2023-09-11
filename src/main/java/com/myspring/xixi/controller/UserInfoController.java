package com.myspring.xixi.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Bank;
import com.myspring.xixi.domain.Evaluates;
import com.myspring.xixi.domain.Receive;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.BankService;
import com.myspring.xixi.service.EvaluatesService;
import com.myspring.xixi.service.ReceiveService;
import com.myspring.xixi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserInfoController {

    @Autowired
    EvaluatesService evaluatesService;

    @Autowired
    ReceiveService receiveService;

    @Autowired
    UserService userService;

    @Autowired
    BankService bankService;

    @PostMapping("/address")
    public Result addAddress(@RequestBody Receive receive){
        receiveService.save(receive);
        return Result.success(null);
    }

    @GetMapping("/address/{userId}")
    public Result getAddress(@PathVariable Long userId){
        List<Receive> addresses = receiveService.getAddressByUserId(userId);
        return Result.success(addresses);
    }

    @PostMapping("/deleteAddress")
    public Result deleteAddress(@RequestBody Map<String, Long> map){
        receiveService.removeById(map.get("id"));
        return Result.success(null);
    }

    @GetMapping("/getUserInfo/{userId}")
    public Result getUserInfo(@PathVariable Long userId){
        User user = userService.getById(userId);
        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bank_account", user.getBankaccount());
        Bank bank = bankService.getOne(queryWrapper);
        Map<String, Object> map = BeanUtil.beanToMap(user);
        map.put("money", bank.getMoney());
        map.put("bankId", bank.getId());
        return Result.success(map);
    }

    @PostMapping("/reCharge")
    public Result reCharge(@RequestBody Map<String, Object> map){
        UpdateWrapper<Bank> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("bank_account", map.get("bankAccount"));
        updateWrapper.set("money", map.get("money"));
        bankService.update(updateWrapper);
        return Result.success(null);
    }

    @PostMapping("/changeUserInfo")
    public Result changeUserInfo(@RequestBody Map<String, Object> map){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", map.get("id"));
        updateWrapper.set("telephone", map.get("telephone")).set("email", map.get("email"))
                .set("city", map.get("city")).set("bankAccount", map.get("bankAccount"));
        userService.update(updateWrapper);
        UpdateWrapper<Bank> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("id", map.get("bankId"));
        updateWrapper1.set("bank_account", map.get("bankAccount"));
        bankService.update(updateWrapper1);
        return Result.success(null);
    }
}
