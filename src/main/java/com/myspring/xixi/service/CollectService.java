package com.myspring.xixi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myspring.xixi.domain.Collect;

import java.util.List;

public interface CollectService extends IService<Collect> {
    public List<Collect> GetAllGoodsId(Integer uuid);
}
