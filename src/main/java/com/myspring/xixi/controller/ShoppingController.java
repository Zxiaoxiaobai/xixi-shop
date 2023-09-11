package com.myspring.xixi.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Cart;
import com.myspring.xixi.domain.Goods;
import com.myspring.xixi.service.CartService;
import com.myspring.xixi.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.*;

@RestController
@CrossOrigin
public class ShoppingController {

    @Autowired
    CartService cartService;

    @Autowired
    GoodsService goodsService;

    @PostMapping("/cart")
    public Result addGoodToCart(@RequestBody Map<String, Object> map){
        Cart cart = new Cart();
        cart.setUserId(((Integer) map.get("userId")).longValue());
        cart.setGoodId(((Integer) map.get("goodId")).longValue());
        cart.setNum((Integer) map.get("num"));
        cartService.save(cart);
        return Result.success(null);
    }

    @GetMapping("/cart/{userId}")
    public Result getCart(@PathVariable Long userId){
        List<Cart> cartByUserId = cartService.getCartByUserId(userId);
        List<Goods> cartList = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        cartByUserId.forEach(cart -> {
            Goods good = goodsService.getById(cart.getGoodId());
            cartList.add(good);
            nums.add(cart.getNum());
        });
        Map<String, Object> map = new HashMap<>();
        map.put("goods", cartList);
        map.put("nums", nums);
        return Result.success(map);
    }

    @PostMapping("/deleteCart")
    public Result deleteGoodFromCart(@RequestBody List<Cart> list){
        list.forEach(cart -> {
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", cart.getUserId())
                    .eq("good_id", cart.getGoodId());
            cartService.remove(queryWrapper);
        });
        return Result.success(null);
    }

    @PostMapping("/changeNum")
    public Result changeNum(@RequestBody Map<String, Object> map){
        Long userId = ((Integer) map.get("userId")).longValue();
        Long[] goodIds = Convert.toLongArray(map.get("goodId"));
        Integer[] nums = Convert.toIntArray(map.get("nums"));
        System.out.println(userId);
        System.out.println(Arrays.toString(goodIds));
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", userId).eq("good_id", goodIds[i]);
            updateWrapper.set("num", nums[i]);
            cartService.update(updateWrapper);
        }
        return Result.success(null);
    }
}
