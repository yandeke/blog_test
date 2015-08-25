package com.ydk.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.kaptcha.Constants;
import com.ydk.config.Constant;
import com.ydk.model.User;
import com.ydk.service.UserService;
import com.ydk.util.EmailUtil;
import com.ydk.util.JsonUtil;
import com.ydk.util.MathUtil;
import com.ydk.util.RegexUtil;
import com.ydk.util.SMSUtil;
import com.ydk.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	Logger logger = Logger.getLogger(UserController.class);
	@RequestMapping("/login")
	public String loginValidate(HttpServletRequest request,HttpServletResponse response,@RequestParam String username,@RequestParam String password,
			@RequestParam String validator,ModelMap model){
		String validatorSrc = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(StringUtil.isNotEmpty(username)&& StringUtil.isNotEmpty(password)&& StringUtil.isNotEmpty(validator)){
			if(validator.equals(validatorSrc)){//验证码验证功能缺
				User user = userService.findUserByUserName(username);
				if(user!=null && "1".equals(user.getIsvalidate())){
					//将password进行比较
					if(password.equalsIgnoreCase(user.getPassword())){
						return "user/main";
					}
				}
			}
		}
		String msg="用户信息填写错误";
		model.put("msg", msg);
		return "user/login";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "user/login";
	}
	
	@RequestMapping("/toRegister")
	public String toReister(){
		return "user/register";
	}
	
	@RequestMapping("/active")
	public String validate(@RequestParam String username,@RequestParam String date,ModelMap map){
		String msg = "";
		if(StringUtil.isNotEmpty(username)&& StringUtil.isNotEmpty(date)){
			try{
				Calendar c1 = Calendar.getInstance();
				c1.setTime(new Date(Long.parseLong(date)));
				Calendar c2 = Calendar.getInstance();
				c2.add(Calendar.HOUR, -1);//减一小时
				if(c1.after(c2)){//如果1小时内就行
					User user = userService.findUserByUserName(username);
					if(user==null){
						msg = "用户不存在，请重新注册";
						map.put("msg", msg);
						return "/index";
					}
					if("1".equals(user.getIsvalidate())){//是否注册过
						msg = "用户已经验证，请直接登录";
						map.put("msg", msg);
						return "user/login";
					}
					user.setIsvalidate("1");
					userService.updateUserIsValidate(user);
					msg = "注册成功，请登录";
					map.put("msg", msg);
					return "user/login";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		msg = "用户不存在，请重新注册";
		map.put("msg", msg);
		return "redirect:user/register";
	}
	
	
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response, @RequestParam String username,@RequestParam String password,
			@RequestParam String email,@RequestParam String validator,@RequestParam String repassword,ModelMap map){
		String validatorSrc = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String msg="";
		//满足不为空
		if(StringUtil.isNotEmpty(username)&& 
				StringUtil.isNotEmpty(password)&&StringUtil.isNotEmpty(repassword)&& StringUtil.isNotEmpty(email)
					&&StringUtil.isNotEmpty(validator)&& password.equals(repassword)&& validator.equals(validatorSrc)){
			//满足长度 以及email各式
			if(RegexUtil.isEmail(email) && StringUtil.checkLength(password, Constant.MIN_LENGTH, Constant.MAX_LENGTH)
					&& StringUtil.checkLength(username, Constant.MIN_LENGTH, Constant.MAX_LENGTH)){
				//将user插入到数据库中，并设置激活为否
				User user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				userService.registerUser(user);
//				Constants.KAPTCHA_SESSION_KEY;
				//生成url地址
				String url = "http://localhost:8080/Blog/user/active.do?username="+username+"&date="+System.currentTimeMillis();
				String urlSbf = "<a href='http://localhost:8080/Blog/user/active.do?username="+username+"&date="+System.currentTimeMillis()+"'>点击此处</a>";
				//发送邮件
				String emailContent = Constant.EMAIL_CONTENT_START+urlSbf.toString()+"或者您可以访问链接："+url;
				EmailUtil.sendEmail(Constant.EMAIL_HOST, Constant.EMAIL_PORT, true, Constant.EMAIL_USERNAME, Constant.EMAIL_PASSWORD, Constant.EMAIL_USERNAME, email, Constant.EMAIL_SUBJECT, emailContent);
				msg = Constant.REGISTER_SUCCESS;
				map.put("msg", msg);
				//根据邮件后缀选择相应的邮件地址
				return "user/emailRemind";
			}
		}else{
			msg=Constant.ERROR_MES;
		}
		map.put("msg", msg);
		return "user/emailRemind";
	}
	
	/**
	 * json数据，验证验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/validateCode",method=RequestMethod.POST)
	public void validateCode(HttpServletRequest request,HttpServletResponse response){
		String validateCode = request.getParameter("validateCode");
		if(StringUtil.checkLength(validateCode, 4, 4)){//==4
			String validateCodeSrc = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			logger.debug(validateCodeSrc);
			if(StringUtil.isNotEmpty(validateCodeSrc) && validateCodeSrc.equalsIgnoreCase(validateCode)){
				 JsonUtil.jsonResponse(response,1,"验证码正确","");
		         return;
			}
		}
		JsonUtil.jsonResponse(response, 0, "验证码错误", "");
	}
	
	/**
	 * 验证用户唯一性
	 */
	@RequestMapping(value="/validateUsername",method=RequestMethod.POST)
	public void validateUsername(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		if(StringUtil.isNotEmpty(username) && StringUtil.checkLength(username, 6, 20)){
			User user = userService.findUserByUserName(username);
			if(user!=null){
				JsonUtil.jsonResponse(response, 0, "用户已经存在", "");
				return;
			}
			JsonUtil.jsonResponse(response, 1, "可以使用", "");
		}
	}
	
	/**
	 * 发表博客
	 * @return
	 * @throws Throwable 
	 */
	public String publishBlog(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		//需要考虑有图片、 希望还能可以画图、有对文字的编辑，要保存它的html格式，考虑链接等
		System.gc();
		Runtime.getRuntime().gc();
		finalize();
		return null;
	}
	
	/**
	 * 发送短信
	 */
	@RequestMapping(value="/sendSMS",method=RequestMethod.POST)
	public void sendSMS(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String phoneNo = request.getParameter("phoneNo");  
		String code = MathUtil.randomStr(6);//产生6位随机码
		model.addAttribute("code",code);//放在sessino中
		String result = SMSUtil.sendSMS(code, phoneNo);
		if(!StringUtil.isEmpty(result) && "1".equals(result.trim())){
			//也行要存入数据库
			JsonUtil.jsonResponse(response, 1, "已发送，请查收短信", "");
		}else{
			JsonUtil.jsonResponse(response, 0, "发送短信失败，请稍后再试", "");
		}
	}
}






