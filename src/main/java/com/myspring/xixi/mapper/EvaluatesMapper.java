package com.myspring.xixi.mapper;

import com.myspring.xixi.domain.Evaluates;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author 惠普
* @description 针对表【t_evaluates】的数据库操作Mapper
* @createDate 2022-06-08 15:11:23
* @Entity com.myspring.xixi.domain.Evaluates
*/
@Repository
public interface EvaluatesMapper extends BaseMapper<Evaluates> {

	public List<Integer> getGoodEvaluate(Long belongId);
}




