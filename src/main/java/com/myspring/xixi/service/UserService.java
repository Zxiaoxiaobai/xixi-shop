package com.myspring.xixi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myspring.xixi.domain.Bank;
import com.myspring.xixi.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-04-05 13:22:16
*/
public interface UserService extends IService<User> {

    public List<User> getAllUser();

    public String getUserName(Long id);


}
