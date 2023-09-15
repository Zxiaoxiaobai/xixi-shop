package com.myspring.xixi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myspring.xixi.domain.Integrals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntegralsService extends IService<Integrals> {
    public List<Integrals> getUpIntegral();
}
