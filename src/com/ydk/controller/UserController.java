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
			if(validator.equals(validatorSrc)){//��֤����֤����ȱ
				User user = userService.findUserByUserName(username);
				if(user!=null && "1".equals(user.getIsvalidate())){
					//��password���бȽ�
					if(password.equalsIgnoreCase(user.getPassword())){
						return "user/main";
					}
				}
			}
		}
		String msg="�û���Ϣ��д����";
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
				c2.add(Calendar.HOUR, -1);//��һСʱ
				if(c1.after(c2)){//���1Сʱ�ھ���
					User user = userService.findUserByUserName(username);
					if(user==null){
						msg = "�û������ڣ�������ע��";
						map.put("msg", msg);
						return "/index";
					}
					if("1".equals(user.getIsvalidate())){//�Ƿ�ע���
						msg = "�û��Ѿ���֤����ֱ�ӵ�¼";
						map.put("msg", msg);
						return "user/login";
					}
					user.setIsvalidate("1");
					userService.updateUserIsValidate(user);
					msg = "ע��ɹ������¼";
					map.put("msg", msg);
					return "user/login";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		msg = "�û������ڣ�������ע��";
		map.put("msg", msg);
		return "redirect:user/register";
	}
	
	
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response, @RequestParam String username,@RequestParam String password,
			@RequestParam String email,@RequestParam String validator,@RequestParam String repassword,ModelMap map){
		String validatorSrc = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String msg="";
		//���㲻Ϊ��
		if(StringUtil.isNotEmpty(username)&& 
				StringUtil.isNotEmpty(password)&&StringUtil.isNotEmpty(repassword)&& StringUtil.isNotEmpty(email)
					&&StringUtil.isNotEmpty(validator)&& password.equals(repassword)&& validator.equals(validatorSrc)){
			//���㳤�� �Լ�email��ʽ
			if(RegexUtil.isEmail(email) && StringUtil.checkLength(password, Constant.MIN_LENGTH, Constant.MAX_LENGTH)
					&& StringUtil.checkLength(username, Constant.MIN_LENGTH, Constant.MAX_LENGTH)){
				//��user���뵽���ݿ��У������ü���Ϊ��
				User user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				userService.registerUser(user);
//				Constants.KAPTCHA_SESSION_KEY;
				//����url��ַ
				String url = "http://localhost:8080/Blog/user/active.do?username="+username+"&date="+System.currentTimeMillis();
				String urlSbf = "<a href='http://localhost:8080/Blog/user/active.do?username="+username+"&date="+System.currentTimeMillis()+"'>����˴�</a>";
				//�����ʼ�
				String emailContent = Constant.EMAIL_CONTENT_START+urlSbf.toString()+"���������Է������ӣ�"+url;
				EmailUtil.sendEmail(Constant.EMAIL_HOST, Constant.EMAIL_PORT, true, Constant.EMAIL_USERNAME, Constant.EMAIL_PASSWORD, Constant.EMAIL_USERNAME, email, Constant.EMAIL_SUBJECT, emailContent);
				msg = Constant.REGISTER_SUCCESS;
				map.put("msg", msg);
				//�����ʼ���׺ѡ����Ӧ���ʼ���ַ
				return "user/emailRemind";
			}
		}else{
			msg=Constant.ERROR_MES;
		}
		map.put("msg", msg);
		return "user/emailRemind";
	}
	
	/**
	 * json���ݣ���֤��֤��
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
				 JsonUtil.jsonResponse(response,1,"��֤����ȷ","");
		         return;
			}
		}
		JsonUtil.jsonResponse(response, 0, "��֤�����", "");
	}
	
	/**
	 * ��֤�û�Ψһ��
	 */
	@RequestMapping(value="/validateUsername",method=RequestMethod.POST)
	public void validateUsername(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		if(StringUtil.isNotEmpty(username) && StringUtil.checkLength(username, 6, 20)){
			User user = userService.findUserByUserName(username);
			if(user!=null){
				JsonUtil.jsonResponse(response, 0, "�û��Ѿ�����", "");
				return;
			}
			JsonUtil.jsonResponse(response, 1, "����ʹ��", "");
		}
	}
	
	/**
	 * ������
	 * @return
	 * @throws Throwable 
	 */
	public String publishBlog(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		//��Ҫ������ͼƬ�� ϣ�����ܿ��Ի�ͼ���ж����ֵı༭��Ҫ��������html��ʽ���������ӵ�
		System.gc();
		Runtime.getRuntime().gc();
		finalize();
		return null;
	}
	
	/**
	 * ���Ͷ���
	 */
	@RequestMapping(value="/sendSMS",method=RequestMethod.POST)
	public void sendSMS(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String phoneNo = request.getParameter("phoneNo");  
		String code = MathUtil.randomStr(6);//����6λ�����
		model.addAttribute("code",code);//����sessino��
		String result = SMSUtil.sendSMS(code, phoneNo);
		if(!StringUtil.isEmpty(result) && "1".equals(result.trim())){
			//Ҳ��Ҫ�������ݿ�
			JsonUtil.jsonResponse(response, 1, "�ѷ��ͣ�����ն���", "");
		}else{
			JsonUtil.jsonResponse(response, 0, "���Ͷ���ʧ�ܣ����Ժ�����", "");
		}
	}
}






