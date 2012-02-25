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
<link rel=stylesheet href="/vol/css/login.css" type="text/css">
 </head>
 <script type="text/javascript" src="/vol/js/jquery-1.6.js"></script> 
 <script type="text/javascript" src="/vol/js/jquery-1.6.2.min.js"></script> 
 <script type="text/javascript" src="/vol/js/jquery.timers.js"></script>
 <title>Download files</title>
  <body>
  <div class="wrapper">
  <div class="main content clearfix">
  <div class="product-info mail">
	<div class="product-headers">
  		<h1 class="greytext">Output files</h1>
	</div>
  	 <div id="nofiles"></div>		
	  <ul id="ulfiles" class="features">
	  </ul> 
	</div>
  </div>
  </div>
  <script>
  $(function() {
	  function getFiles() {
		  $.post("getFiles.action", function(ret) {
			  var jsonObj = eval("("+ret+")");	
			  if(jsonObj.length == 0) {
				  $('#nofiles').html("There are no output files");
			  }
			  $.each(jsonObj, function(n, value) {
				  var path = value.path;
			  	  var url = "";
				  url += "<li>";
				  if(getExt(path) == 'txt') {
					  url += "<img src='/vol/images/txt.png' alt=''>";
				  }else if(getExt(path) == 'csv') {
					  url += "<img src='/vol/images/csv.png' alt=''>";
				  }else {
					  url += "<img src='/vol/images/xls.png' alt=''>";
				  }
				  url += "<p><a style='font-size:10px' href='download.action?path="+path+"'>"
				   + getFileName(path) +
				  "</a></p>";
				  url +="</li>";
				  $("#ulfiles").append(url);
			  });
		  });
	  }
	  
	  function getExt(file) {
		  var str = file.split('.');
		  return str[1];
	  }
	  
	  function getFileName(file) {
		  var str = file.split('/');
		  var fileName = str[str.length-1];
		  fileName = fileName.split('.')[0];
		  return fileName;
	  }
	  
	  $(document).ready(getFiles);
  });
  </script>
</body>
</html>