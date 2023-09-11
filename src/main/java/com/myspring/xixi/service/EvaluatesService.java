package com.myspring.xixi.service;

import com.myspring.xixi.domain.Evaluates;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 惠普
* @description 针对表【t_evaluates】的数据库操作Service
* @createDate 2022-06-08 15:11:23
*/
public interface EvaluatesService extends IService<Evaluates> {
    List<Evaluates> getEvaluateByUserId(Long userId);
    List<Integer> getGoodEvaluate(Long shopId);
}
