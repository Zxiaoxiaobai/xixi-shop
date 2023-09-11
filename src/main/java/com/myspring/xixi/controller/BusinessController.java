package com.myspring.xixi.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myspring.xixi.common.dto.ChangePassDTO;
import com.myspring.xixi.common.dto.LoginDTO;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Bank;
import com.myspring.xixi.domain.Business;
import com.myspring.xixi.service.BankService;
import com.myspring.xixi.service.BusinessService;
import com.myspring.xixi.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin
public class BusinessController {

    @Autowired
    UserService userService;

    @Autowired
    BusinessService businessService;

    @Autowired
    BankService bankService;

//    @PostMapping("/uploadUi")
    public Result br(@RequestParam("files") MultipartFile file) throws IOException {
        System.out.println("========================");
        System.out.println(file.toString());
        byte[] b = file.getBytes();
        String str = Base64.getEncoder().encodeToString(b);
        String data = "data:image/jpeg;base64," + str;
        return Result.success(data);
    }

    @PostMapping("/uploadUi")
    public Result register(Business business, MultipartFile SPic, MultipartFile IPic) throws IOException {
        Business business1 = businessService.getOne(new QueryWrapper<Business>().eq("shop_name", business.getShopName()));
        if(business1 != null){
            return Result.fail("此店铺名称已被使用！");
        }
        business.setPicShop(SPic.getBytes());
        business.setPicId(IPic.getBytes());
        business.setPass(0);
        business.setCreated(LocalDateTime.now());
        business.setLevel(5);
        businessService.save(business);
//        String str = Base64.getEncoder().encodeToString(pic1.getBytes());
//        String data = "data:image/jpeg;base64," + str;
        return Result.success("提交成功", null);
    }

    @GetMapping("/business")
    public Result getBusiness(@RequestParam("userId") Long id){
        Business business = businessService.getOne(new QueryWrapper<Business>().eq("user_id", id));
        if(business == null){
            return Result.success("未注册店铺！！！", null);
        } else {
            if(business.getPass() == 0){
                return Result.success("店铺申请中!!！", null);
            } else if(business.getPass() == -1) {
                return Result.success("店铺已禁用！！！", null);
            }
        }
        business.setPicId(null);
        business.setPicShop(null);
        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bank_account", business.getBankAccount());
        Bank bank = bankService.getOne(queryWrapper);
        Map<String, Object> map = BeanUtil.beanToMap(business);
        map.put("money", bank.getMoney());
        return Result.success(map);
    }

    @RequiresAuthentication
    @PostMapping("/businesses")
    public Result businesses(@RequestBody LoginDTO loginDTO){
        if(loginDTO.getLevel() == 1){
            List<Business> allBusiness = businessService.getAllBusiness();
            List<Map<String, Object>> shopInfo = new ArrayList<>();
            allBusiness.forEach(business -> {
                Map<String, Object> map = BeanUtil.beanToMap(business);
                String userName = userService.getUserName(business.getUserId());
                map.remove("userId");
                map.put("username", userName);
                map.put("created", LocalDateTimeUtil.parseDate(map.get("created").toString(), DateTimeFormatter.ISO_DATE_TIME));
                map.put("picShop", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString((byte[])map.get("picShop")));
                map.put("picId", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString((byte[])map.get("picId")));
                shopInfo.add(map);
            });
            return Result.success(shopInfo);
        } else {
            return Result.fail("无权查看信息！");
        }
    }

    @PostMapping("/businessPass")
    public Result bPass(@RequestBody ChangePassDTO changePassDTO){
        Business business = businessService.getById(changePassDTO.getId());
        if(business.getPass() == 1){
            business.setPass(-1);
            changePassDTO.setPass(-1);
        } else {
            business.setPass(1);
            changePassDTO.setPass(1);
        }
        boolean update = businessService.saveOrUpdate(business);
        return Result.success(changePassDTO);
    }
}
