package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Receive;
import com.myspring.xixi.service.ReceiveService;
import com.myspring.xixi.mapper.ReceiveMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_receive】的数据库操作Service实现
* @createDate 2022-06-08 17:10:49
*/
@Service
public class ReceiveServiceImpl extends ServiceImpl<ReceiveMapper, Receive>
    implements ReceiveService{

    @Override
    public List<Receive> getAddressByUserId(Long id) {
        QueryWrapper<Receive> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return baseMapper.selectList(queryWrapper);
    }
}




