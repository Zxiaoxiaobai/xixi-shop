package com.myspring.xixi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/error/404")
	public String handlerError404(Model model){
		model.addAttribute("code", 404);
		model.addAttribute("msg", "页面出错了");
		return "errorPage";
	}

	@RequestMapping("/error/500")
	public String handlerError500(Model model){
		model.addAttribute("code", 500);
		model.addAttribute("msg", "服务器出错了");
		return "errorPage";
	}
}
