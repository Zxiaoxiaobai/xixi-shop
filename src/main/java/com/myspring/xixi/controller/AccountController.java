package com.myspring.xixi.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myspring.xixi.common.dto.LoginDTO;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.domain.User;
import com.myspring.xixi.service.IntegralService;
import com.myspring.xixi.service.UserService;
import com.myspring.xixi.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    IntegralService integralService;

//    @PostMapping("/save")
//    public Result save(@Validated @RequestBody User user){
//
//        return Result.success(user);
//    }

    @PostMapping("/transaction/user/login")
    public Result login(String username ,String password){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if(user != null) {
            if(!user.getPassword().equals(password)) {
                return Result.fail("用户名或密码错误！");
            }
//            if(user.getPass() == 0){
//                return Result.fail("账户正在审核中！");
//            } else
//            if(user.getPass() == -1){
//                return Result.fail("此账户已被锁定！");
//            }
        } else {
            return Result.fail("用户名或密码错误！");
        }
//        String jwt = jwtUtils.generateToken(user.getId());
//        response.setHeader("Authorization", jwt);
//        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("sex", user.getSex())
                .put("telephone", user.getTelephone())
                .put("email", user.getEmail())
                .put("city", user.getCity())
                .put("bank", user.getBankaccount())
                .put("level", user.getLevel())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result register(String username,String password ){
        User user = userService.getOne(new QueryWrapper<User>().eq("username",username));
        if(user != null){
            return Result.fail(300,"用户已存在！",null);
        } else {
            //user.setPassword(SecureUtil.md5(password));
            User user1 =new User();
            user1.setUsername(username);
            user1.setPassword(password);
            user1.setCreated(LocalDateTime.now());
            user1.setLevel(0);
            user1.setPass(0);
            userService.save(user1);
            User result = userService.getOne(new QueryWrapper<User>().eq("username", username));
            Integral integral = new Integral();
            integral.setUserId(result.getId());
            integralService.save(integral);
            return Result.success("提交成功", null);
        }
    }

    @RequiresAuthentication
    @PostMapping("/users")
    public Result getUsers(@RequestBody LoginDTO loginDTO){
        if(loginDTO.getLevel() == 1){
            return Result.success(userService.getAllUser());
        } else {
            return Result.fail("无权查看信息");
        }
    }

    @PostMapping("/userPass")
    public Result cpass(@RequestBody LoginDTO loginDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginDTO.getId());
        User user = userService.getOne(queryWrapper);
        if(user.getPass() == 1){
            user.setPass(-1);
        } else {
            user.setPass(1);
        }
        boolean update = userService.update(user, queryWrapper);
        return Result.success(user);
    }
}
