<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="/vol/css/login.css" type="text/css">
 </head>
 <script type="text/javascript" src="/vol/js/jquery-1.6.js"></script>
  <body>
  <div class="wrapper">
  <div class="main content clearfix">
  <div class="sign-in">
<div class="signin-box">
  <h2>Login
  &nbsp;&nbsp;&nbsp;&nbsp;
  <a style="font-size:10px" target="_top" href="<%=request.getContextPath()%>/register.jsp" id="link-forgot-passwd">
  		create an account
  		</a>
  </h2>
  
  <form method="post" id="loginform">
	<label>
  		<strong class="email-label">Username</strong> 
  		<input type="text" value="" id="username" name="username">
	</label>
	<label>
  		<strong class="passwd-label">Password</strong>
  		<input type="password" id="Passwd" name="Passwd">
	</label>
	<div id="error" class="errormsg"></div>
  	<input type="button" value="Sign in" id="signIn" name="signIn" class="g-button">
  	
  </form>
  <ul>
  	<li>
  		
  	</li>
  </ul>
</div>
</div>
  <div class="product-info mail">
	<div class="product-headers">
  		<h1 class="greytext">On-line Validator</h1>
	</div>
  	<p>
  	Introduction to on-line validator will be added later.
	</p> 
	</div>
  </div>
  </div>
  <script>
  	$(function() {
  		$('#signIn').click(function() {
  			var params = {
  	  				"user.userName" : $('#username').val(),
  	  				"user.password" : $('#Passwd').val()
  	  		};
  	  		$.post("login.action", params, function(result) {
  	  			if(result == 'ok') {
  	  				location.href="<%=request.getContextPath()%>/upload.jsp";
  	  			}else {
  	  				$('#error').html("username or password is not correct");
  	  			}
  	  		});
  		}); 
  	});
  </script>
</body>
</html>