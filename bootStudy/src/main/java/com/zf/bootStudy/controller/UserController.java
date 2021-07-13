package com.zf.bootStudy.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zf.bootStudy.config.RedisConfig;
import com.zf.bootStudy.entity.User;
import com.zf.bootStudy.enums.Constant;
import com.zf.bootStudy.service.UserService;
import com.zf.bootStudy.util.RedisUtil;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="用户controller",tags={"用户登录注册操作接口"})
@Controller
public class UserController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	@Resource
    private RedisUtil redisUtil;
	@Resource
    private RedisConfig redisConfig;
	@Autowired
    private RedisTemplate redisTemplate;
	//用户登录
	@ApiOperation(value="用户登录",tags={"获取用户信息"}) 
	@RequestMapping("/userLogin")
	public String userLogin(User user,Model model){
		System.out.println(user);
		//select * from user where phone = ? and password = ?
		boolean success = userService.findUserByPhoneAndPassword(user);
		 List<User> list= Lists.newLinkedList();
		//登录成功，在数据库中找到了对应的数据
		if(success){
			model.addAttribute("user", user);
			ListOperations<String,User> ulist= redisConfig.listOperations(redisTemplate);
			final String key=Constant.RedisListPrefix+20;
			list=ulist.range(key,0,ulist.size(key));
			System.out.println(list);
			model.addAttribute("ulist", list);
			//返回首页
			return "redirect:/index";			
		}else{
			//给一点错误信息
			model.addAttribute("error", "用户名或者密码错误！！");
			return "login.html";
		}
		
	}
	
	
	//用户注册
	@RequestMapping("/userResigter")
	public String userResigter(User user){
		System.out.println(user);
		
		userService.save(user);
		@SuppressWarnings("unchecked")
		ListOperations<String,User> ulist= redisConfig.listOperations(redisTemplate);
		ulist.leftPush(Constant.RedisListPrefix+user.getId(),user);
		return "redirect:/index";
	}
	
	
	
	@RequestMapping("/sms")
	@ResponseBody
	public String smsCode(String phone){
		System.out.println(phone);
		
		//保护机制，如果用户已经注册过了，我们就不用给他发短信了
		//通过phone查找用户，看其是否注册过，如果注册过了，我们就不用给他发短信
		//如果没有注册，我们才需要给他发短信
		//select * from user where phone = ? 
		//表示该用户存不存在
		boolean success = userService.findUserByPhone(phone);
		String json = null;
		//该用户没有注册
		if(success){
			
			json = "{\"message\":"+true+"}";	
		}
		//用户以及注册过了
		else
		{
			json = "{\"message\":"+false+"}";
		}
		
		return json;
	}

	/*//发送短信
	private String SMS(String phone)
	{
		//手机
		String phoneNumber = phone;
		//短信的内容
        //参数
		String[] params = new String[1];
		//验证码
		String code = "";
		Random r = new Random();
		for(int i = 0;i<6;i++)
		{
			code += r.nextInt(10);
		}
		//将Code放入Session中
		session.setAttribute("sms", code);
		params[0] = code;
		System.out.println(code);
		//签名
		String sign = "";

		//拿到发送短信的核心类
		SmsSingleSender ssender = new SmsSingleSender(1400142856, "21306d751cfdf61ed433e506da242522");
		//发送短信
		try {
            int templateId = 200461;
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, sign, "", "");
			System.out.println(result);
		} catch (JSONException | HTTPException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return code;
	}*/

}
