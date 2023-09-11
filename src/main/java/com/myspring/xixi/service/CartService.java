package com.myspring.xixi.service;

import com.myspring.xixi.domain.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_cart】的数据库操作Service
* @createDate 2022-06-08 18:42:57
*/
public interface CartService extends IService<Cart> {

    List<Cart> getCartByUserId(Long userId);
}
