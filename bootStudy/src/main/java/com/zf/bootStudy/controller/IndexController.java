package com.zf.bootStudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zf.bootStudy.service.AsyncService;

import io.swagger.annotations.Api;
@Api(value="用户controller",tags={"用户注册登录页面接口"})
@Controller
public class IndexController {
	@Autowired
	private AsyncService asyncService;
	@ResponseBody
	@GetMapping("/async")
	public void async(){
	    asyncService.executeAsync();
	}
	//接收并处理index请求
	@RequestMapping("/index")
	public String index(){
		return "index.html";
	}
	
	//显示login页面
	@RequestMapping("/login")
	public String login(){
		return "login.html";
	}
	
	//显示register页面
	@RequestMapping("/register")
	public String register(){
		return "register.html";
	}
	
}
