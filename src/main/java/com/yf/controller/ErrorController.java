package com.yf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	// 403权限不足页面
	@RequestMapping("/error/403")
	public String error() {
		System.out.println("403异常处理");
		return "/error/403";
	}

}
