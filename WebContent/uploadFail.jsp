<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vol.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
	User user = (User)session.getAttribute("user");
	if(user == null) {
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
<link rel=stylesheet href="/vol/css/login.css" type="text/css">
 </head>
 <script type="text/javascript" src="/vol/js/jquery-1.6.js"></script>
  <body>
  <div class="wrapper">
  <div class="main content clearfix">
  <div class="product-info mail">
  	<p>
  	Sorry, uploading is failed! please be sure that you uploaded only one dat file.
	</p>
	<p>
	If you try to upload again, please <a href="<%=request.getContextPath()%>/upload.jsp">click here</a>
	</p> 
	</div>
  </div>
  </div>
	
</body>
</html>