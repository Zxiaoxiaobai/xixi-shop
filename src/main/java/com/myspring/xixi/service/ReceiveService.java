package com.myspring.xixi.service;

import com.myspring.xixi.domain.Receive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_receive】的数据库操作Service
* @createDate 2022-06-08 17:10:49
*/
public interface ReceiveService extends IService<Receive> {

    List<Receive> getAddressByUserId(Long id);

}
