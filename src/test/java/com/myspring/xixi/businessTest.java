package com.myspring.xixi;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.myspring.xixi.domain.Business;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.BusinessService;
import com.myspring.xixi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class businessTest {

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Test
    public void test(){
        List<Business> allBusiness = businessService.getAllBusiness();
        allBusiness.forEach(business -> {
            Map<String, Object> map = BeanUtil.beanToMap(business);
            System.out.println(map.get("created").toString()+"123");
            LocalDate localDate = LocalDateTimeUtil.parseDate(map.get("created").toString(), DateTimeFormatter.ISO_DATE_TIME);
            System.out.println(localDate);
        });
    }
}
