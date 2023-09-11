package com.myspring.xixi.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.myspring.xixi.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 惠普
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-04-05 13:22:16
* @Entity com.myspring.xixi.domain.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {
    String getUsernameById(@Param("id") Long id);
}




