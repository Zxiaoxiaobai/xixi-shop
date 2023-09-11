package com.myspring.xixi;

import com.myspring.xixi.domain.Evaluates;
import com.myspring.xixi.mapper.EvaluatesMapper;
import com.myspring.xixi.mapper.OrderMapper;
import com.myspring.xixi.mapper.UserMapper;
import com.myspring.xixi.service.EvaluatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    EvaluatesMapper evaluatesMapper;

    @Test
    public void test01(){
        String usernameById = userMapper.getUsernameById(1L);
        System.out.println(usernameById);
    }

    @Test
    public void test02(){
        Long shopId = 1L;
        List<Map<String, Object>> orderInShop = orderMapper.getOrderInShop(shopId);
        orderInShop.forEach(System.out::println);
    }

    @Test
    public void test03(){
        Long belongId = 1L;
        List<Integer> goodEvaluate = evaluatesMapper.getGoodEvaluate(belongId);
        BigDecimal rate = new BigDecimal(0);
        if(goodEvaluate.size() != 0){
            int step = 0;
            for (Integer integer : goodEvaluate) {
                step += integer;
            }
            rate = new BigDecimal(step);
            rate = rate.divide(new BigDecimal(goodEvaluate.size()));
        }


        System.out.println(goodEvaluate);
    }
}
