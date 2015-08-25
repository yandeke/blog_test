<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
			validateCode();
			usernameValidate();
			passwordValidate();
			submit();
		});
		
		//点击提交
		function submit(){
			$("#loginBtn").click(function(){
				if(isLength()){
					$("#form").submit();
				}
			});
		}
		
		//点击切换验证码
		function validateChange(){
			$("#validateImg").bind("click",function(){
				$(this).attr("src","Kaptcha.jpg?"+Math.floor(Math.random()*100));
			});
		}
		
		//验证验证码是否正确
		function validateCode(){
			$("#validatorInput").bind("blur",function(){
				var validate = $(this).val();
				if(validate==null || validate.length<=0){
					$("#validatorMes").html("验证码为空");
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
							$("#validatorMes").html(data.msg);
						}
					}
				});
			});
			
			//验证码框聚焦时
			$("#validatorInput").bind("focus",function(){
				$("#validatorMes").html("");
			});
		}
		
		
		
		function usernameValidate(){
			$("#username").bind("blur",function(){
				var username = $(this).val();
				if(username==null || username.length<6 || username.length>20){
					$("#usernameMes").html("用户名长度为6-20位");
				}
			}).bind("focus",function(){
				$("#usernameMes").html("");
			});
		}
		
		function passwordValidate(){
			$("#password").bind("blur",function(){
				var password = $(this).val();
				if(password==null || password.length<6 || password.length>20){
					$("#passwordMes").html("密码长度为6-20位");
				}
			}).bind("focus",function(){
				$("#passwordMes").html("");
			});
		}
		
		function isLength(){
			var $username = $("#username").val();
			var $password = $("#password").val();
			var $validator = $("#validatorInput").val();
			
			if($.trim($username).length<=0 || $.trim($username).length>20){
				return false;
			}
			if($.trim($password).length<=0 || $.trim($password).length>20){
				return false;
			}
			if($.trim($validator).length!=4 || $.trim($("#validatorMes").html()).length>0){
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body>
  ${msg}
    <form action="user/login.do" method="post" id="form">
    	<table>
	    	<tr>
	    		<td>用户名:</td>
		    	<td>
		    		<input type="text" id="username" name="username" /><span id="usernameMes" style=" color:red"></span>
		    	</td>
	    	</tr>
	    	<tr>
		    	<td>密码:</td>
			    <td>
			    	<input type="password" id="password" name="password" /><span id="passwordMes" style="color: red"></span>
			    </td>
		    </tr>
		    <tr>
		    	<td>验证码：</td>
		    	<td>
		    		<input type="text" name="validator" id="validatorInput" maxlength="4" size="4" />
		    		<img alt="validate code" src="Kaptcha.jpg" style="cursor: pointer;" id="validateImg" /><span id="validatorMes" style="color: red"></span>
		    	</td>
		    </tr>
		    
		    <tr>
		    	<td colspan="2">
		    		<input type="button" value="登录" id="loginBtn" />
		    		<input type="reset" value="重置" />
		    	</td>
		    </tr>
    	</table>
    </form>
  </body>
</html>
