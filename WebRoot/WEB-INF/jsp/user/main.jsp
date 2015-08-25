<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script charset="utf-8" src="/Blog/kindedit/kindeditor.js"></script>
		
		<script charset="utf-8" src="/Blog/kindedit/lang/zh_CN.js"></script>
		
		<script charset="utf-8" src="/Blog/kindedit/plugins/code/prettify.js"></script>
		
		<script>
		
			KindEditor.ready(function(K) {
			
				var editor1 = K.create('textarea[name="article.content1"]', {
				
				cssPath : 'kindedit/plugins/code/prettify.css',
				
				uploadJson : 'kindedit/jsp/upload_json.jsp',
				
				fileManagerJson : 'kindedit/jsp/file_manager_json.jsp',
				
				allowFileManager : true,
				
				afterCreate : function() {
				
				var self = this;
				
				K.ctrl(document, 13, function() {
				
				self.sync();
				
				document.forms['example'].submit();
				
				});
				
				K.ctrl(self.edit.doc, 13, function() {
				
				self.sync();
				
				document.forms['example'].submit();
				
				});
				
				}
				
				});
				
				prettyPrint();
			});

</script>
  </head>
  
  <body>
    <a href="">完善个人资料</a>
    <a href="">发表博客</a>
	<form name="blogForm" id="blogFormId" method="post" action="writeBlog.do">
		题目：<input  type="text" name="article.title"><br />
		内容：<textarea name="article.content1" cols="100" rows="8"></textarea><br />
		<input type="submit" name="button" value="提交" />(提交快捷键: Ctrl + Enter)
	</form>
  </body>
</html>
