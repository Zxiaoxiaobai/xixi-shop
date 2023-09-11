package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Cart;
import com.myspring.xixi.service.CartService;
import com.myspring.xixi.mapper.CartMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_cart】的数据库操作Service实现
* @createDate 2022-06-08 18:42:57
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }
}




