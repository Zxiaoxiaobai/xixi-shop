package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.UserService;
import com.myspring.xixi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-04-05 13:22:16
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.selectList(null);
    }

    public String getUserName(Long id){
        return userMapper.getUsernameById(id);
    }

}




