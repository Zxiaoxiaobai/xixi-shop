package com.myspring.xixi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myspring.xixi.domain.Bank;
import com.myspring.xixi.service.BankService;
import com.myspring.xixi.mapper.BankMapper;
import org.springframework.stereotype.Service;

/**
* @author 惠普
* @description 针对表【t_bank】的数据库操作Service实现
* @createDate 2022-06-19 23:23:44
*/
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank>
    implements BankService{

}




