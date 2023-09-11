package com.myspring.xixi.controller;

import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.common.lang.VerifyCode;
import com.myspring.xixi.utils.GetVerifyCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
public class GetVerifyController {

    @GetMapping("/verify")
    public void verify(String num, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = GetVerifyCode.getVerify(num);
        response.setDateHeader("expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("image/jpeg");
        response.getOutputStream().write(verifyCode.getImgBytes());
        response.getOutputStream().flush();
    }

    @GetMapping("/code")
    public Result code(String num) throws IOException {
        VerifyCode verifyCode = GetVerifyCode.getVerify(num);
        return Result.success(verifyCode.getCode());
    }
}
