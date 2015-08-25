<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		$(function(){
			validateChange();
			validateEmail();
			validateUser();
			validatePass();
			validateRePass();
			validateCode();
			//submit
			sendMes();
			validatePhoneNo();
		});
		
		//点击切换验证码
		function validateChange(){
			$("#validateImg").bind("click",function(){
				$(this).attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
			});
		}
		
		//用户名验证
		function validateUser(){
			$("#userInput").bind("focus",function(){
				$("#userMes").html("");
			}).bind("blur",function(){
				var username = $.trim($(this).val());
				if(username.length<6 || username.length>20){
					$("#userMes").html("用户名6-20个字符之间");
					return;
				}
				$.ajax({
					async:false,//异步请求
					type:"post",
					url:"user/validateUsername.do",
					data:{
						"username":username
					},
					cache:false,
					dataType:"json",
					success:function(data){
						if(data.code==1){
						}else{
							$("#userMes").html(data.msg);
						}
					}
				});
			});
		}
		
		//密码验证
		function validatePass(){
			$("#passInput").bind("focus",function(){
				$("#passMes").html("");
			}).bind("blur",function(){
				var password = $.trim($(this).val());
				if(password.length<6 || password.length>20){
					$("#passMes").html("密码6-20个字符之间");
				}
			});
		}
		//重复密码验证
		function validateRePass(){
			$("#repassInput").bind("focus",function(){
				$("#repassMes").html("");
			}).bind("blur",function(){
				var rePassword = $.trim($(this).val());
				if(rePassword.length<6 || rePassword.length>20){
					$("#repassMes").html("重复密码6-20个字符之间");
				}
			});
		}
		
		//email
		function validateEmail(){
			$("#emailInput").bind("focus",function(){
				$("#emailMes").html("");
			}).bind("blur",function(){
				var emailInput = $.trim($(this).val());
				if(emailInput.length!=null && emailInput.length>0){
					//验证邮箱格式
					var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				    if (!reg.test(emailInput)) {
				       $("#emailMes").html("邮箱格式不正确");
				       return;
				    }
				}else{
					$("#emailMes").html("邮箱不能为空");
				}
			});
		}
		
		//验证码
		function validateCode(){
			$("#validatorInput").bind("focus",function(){
				$("#validateMes").html("");
			}).bind("blur",function(){
				var validate = $.trim($(this).val());
				if(validate.length==null || validate.length!=4){
					$("#validateMes").html("验证码长度为4");
					return;
				}
				$.ajax({
					async:false,//一部请求
					type:"post",
					url:"user/validateCode.do",
					data:{
						"validateCode":validate
					},
					cache:false,
					dataType:"json",
					success:function(data){
						if(data.code==1){
						}else{
							$("#validateMes").html(data.msg);
						}
					}
				});
				
			});
		}
		
		var count = 120 ;//间隔
		var curCount;//当前剩余秒
		var codeLength = 6;//验证码的长度
		var code="";//验证码
		var InterValObj;//timger变量，控制时间
		
		function sendMes(){
			curCount = count;
			$("#btnSendCode").bind("click",function(){
				var phoneNo = $("#phoneNoInput").val();
				if(phoneNo != ""){
					//产生验证码
					for(var i = 0; i < codeLength; i++){
						code += parseInt(Math.random()*9).toString();
					}
					
					//设置button效果
					$(this).attr("disabled","true");
					$(this).val(curCount+"秒后重新发送");
					InterValObj = setInterval(setRemainTime,1000);//启动计时器
					
					$.ajax({
						type:"post",
						cache:false,
						dataType:"json",
						url:"user/sendSMS.do",
						data:{
							"phoneNo":phoneNo
						},
						success:function(data){
							alert(data.msg);
						}
					});
				}
			});
		}
		
		function validatePhoneNo(){
			$("#phoneNoInput").blur(function(){
				$("#phoneNoMes").html("");
				var phoneNo = $(this).val();
				var reg = /^1[3|5|7|8|][0-9]{9}$/;
				if(!reg.test(phoneNo)){
					$("#phoneNoMes").html("手机格式不正确");
				}
			});
		}
		
		function setRemainTime(){
			if(curCount == 0){
				clearInterval(InterValObj);//停止计时器
				$("#btnSendCode").removeAttr("disabled");//启动按钮
				$("#btnSendCode").val("重新发送验证码");
				code = "";//清除验证码
			}else{
				curCount -- ;
				$("#btnSendCode").val(curCount+"秒后重新发送");
			}
		}
	</script>
  </head>
  <body>
  	<form action="user/register.do" method="post">
	    <table>
	    	<tr>
	    		<td>登录名</td>
	    		<td>
	    			<input type="text" name="username" id="userInput"/>
	    			<span id="userMes"  style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>密码</td>
	    		<td>
	    			<input type="password" name="password" id="passInput" />
	    			<span id="passMes" style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>密码确认</td>
	    		<td>
	    			<input type="password" name="repassword" id="repassInput" />
	    			<span id="repassMes" style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>邮箱</td>
	    		<td>
	    			<input type="text" name="email" id="emailInput" />
	    			<span id="emailMes" style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>手机号码</td>
	    		<td>
	    			<input type="text" name="phoneNo" id="phoneNoInput" />
	    			<input type="text" name="validateCodeSMS" id="validateCodeSMS" size="6" maxlength="6"/>
	    			<span><input type="button" id="btnSendCode" name="btnSendCode" value="免费获取验证码" /></span>
	    			<span id="phoneNoMes" style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>验证码</td>
	    		<td>
	    			<input type="text" name="validator" id="validatorInput" size="4" maxlength="4"/>
	    			<img alt="validate code" src="Kaptcha.jpg" style="cursor: pointer;" id="validateImg" />
	    			<span id="validateMes" style="color:red"></span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2">
		    		<input type="submit" value="注册" />
		    		<input type="reset" value="重置" />
		    	</td>
	    		</td>
	    	</tr>
	    </table>
    </form>
  </body>
</html>
