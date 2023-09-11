package com.myspring.xixi.mapper;

import com.myspring.xixi.domain.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author 惠普
* @description 针对表【t_business】的数据库操作Mapper
* @createDate 2022-04-15 18:38:41
* @Entity com.myspring.xixi.domain.Business
*/
@Repository
public interface BusinessMapper extends BaseMapper<Business> {
    String getShopNameById(@Param("id") Long id);
}




