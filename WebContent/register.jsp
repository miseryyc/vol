<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<script type="text/javascript" src="/vol/js/jquery-1.6.2.min.js"></script> 
<script type="text/javascript" src="/vol/js/jquery-1.6.js"></script>
<link rel=stylesheet href="/vol/css/register.css" type="text/css">

</head>
<body>
<div id="divd" class="signuponepage main content clearfix">
	<div class="sign-up">
  		<div class="signup-box">
  			<form class="createaccount-form">
  				<div id="gmail-address-form-element" class="form-element gmail-address">
  					<label>
  						<strong>
  						Choose your username
  						</strong>
  						<input type="text" value="" id="username" name="username" maxlength="30">
  					</label>
  				</div>
  				<div id="errormsg_0_user_name" class="errormsg-container">
  					<div class="errormsg" id="user_name_error">
  					</div>
  				</div>
  				<div id="password-form-element" class="form-element">
  					<label>
  						<strong>Create a password</strong>
  						<input type="password" id="Passwd" name="Passwd">
  					</label>
  				</div>
  				<div id="errormsg_1_user_passwd" class="errormsg-container">
  					<div class="errormsg" id="passwd_1_error">
  					</div>
  				</div>
  				<div id="confirm-password-form-element" class="form-element">
  					<label>
  						<strong>Confirm your password</strong>
  						<input type="password" id="PasswdAgain" name="PasswdAgain">
  					</label>
  				</div>
  				<div id="errormsg_0_user_passwd" class="errormsg-container">
  					<div class="errormsg" id="passwd_error">
  					</div>
  				</div>
  				<div id="recovery-email-form-element" class="form-element recovery-email">
  					<label>
  						<strong>email address</strong>
  						<input type="text" value="" id="emailAddress" name="emailAddress">
  					</label>
  				</div>
  				<div id="errormsg_email" class="errormsg-container">
  					<div class="errormsg" id="email_error">
  					</div>
  				</div>
  				<div>
  					<input type="button" value="submit" id="btn_reg" name="btn_reg" class="g-button">
  				</div>	
  			</form>
  		</div>
	</div>
</div>
<script type="text/javascript"> 
  var flag = 0;
  var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
  
  function checkUser() {
	  var userName = $('#username').val();
	  if(userName == "" ) {
		  $('#user_name_error').html("user name required");
		  return false;
	  }else {
		  RegExp = /^[a-zA-Z0-9_\u4e00-\u9fa5]{2,15}$/;
		  if(!RegExp.test(userName)) {
			  $('#user_name_error').html("user name is invaliable");
			  return false;
		  }
	  }
	  return true;
  }
  
  function checkPwd() {
	  if($('#PasswdAgain').val() != $('#Passwd').val()) {
		  $('#passwd_error').html("passwords are not accordant");
		  return false;
		}
	  
	  if($('#Passwd').val() == "") {
		  $('#passwd_1_error').html("password is required");
		  return false;
	  }
	  
	  return true;
  }
  
  function checkEmail() {
	  var email = $('#emailAddress').val();
	  if(email == "") {
		  $('#email_error').html("email is required");
		  return false;
	  }else if(!emailRegExp.test(email)||email.indexOf('.')=='-1'){
		  $('#email_error').html("email address is invaliable");
		  return false;
	  }else {
		  return true;
	  }	
  }
  
  function initValidate() {
	  $('#PasswdAgain').blur(function(){
		  if($(this).val() != $('#Passwd').val()) {
			  $('#passwd_error').html("passwords are not accordant");
			  return false;
		  }else {
			  $('#passwd_error').html("");
			  return true;
		  }
	  });
	  
	  $('#username').blur(function(){
		  var userName = $('#username').val();
		  if(userName == "" ) {
			  $('#user_name_error').html("user name required");
			  return false;
		  }else {
			  RegExp = /^[a-zA-Z0-9_\u4e00-\u9fa5]{2,15}$/;
			  if(!RegExp.test(userName)) {
				  $('#user_name_error').html("user name is invaliable");
				  return false;
			  }else {
				  var url = "checkUser.action";
				  var params = {
						"user.userName":$("#username").val()
				  }; 
				  $.post(url, params, function(res) {
					  if(res == '0') {
						  $('#user_name_error').html("Sorry, someone already has that username. Try another?");
					  	  flag = flag + 1;
						  return false;
					  }else{
						  $('#user_name_error').html("");
						  flag = flag - 1;
						  return true;
					  }
				  });
			  }
		  }
	  });
	  
	  $('#Passwd').blur(function(){
		  if($(this).val() == "") {
			  $('#passwd_1_error').html("password is required");
			  return false;
		  }else {
			  $('#passwd_1_error').html("");
			  return true;
		  }
	  });
	  
	  $('#emailAddress').blur(function(){
		  
		  var email = $(this).val();
		  
		  if(email == "") {
			  $('#email_error').html("email is required");
			  return false;
		  }else if(!emailRegExp.test(email)||email.indexOf('.')=='-1'){
			  $('#email_error').html("email address is invaliable");
			  return false;
		  }else{
		  	$('#email_error').html("");
		  	return true;
		  }
	  });
	  
	  /*//var RecaptchaState = {site:'6LeHF8USAAAAAOa_HdcHcf3ZR_-kUoehaUFfOcZW',
			  				challenge:'03AHJ_VutBD12QQr93lDLB8uPF6ksQb6Rj0WMTn0ZY2xFFTW_sNa7nlS8DovByI4j5IBECq6ITjy2ghmJvq8DD-b9Ot-N5ANLID8gh8Tz2XwVMgwqkiXKWpRMHRFoUV43PG4aeLFTL19W7i21omx6MKccKTXZAiyTAKw',
			  				is_incorrect:false,
			  				programming_error:'',
			  				error_message:'error_message_value',
			  				server:'https://www.google.com/recaptcha/api/',
			  				timeout:1800};
	  
	  $('#recaptcha_response_field').blur(function() {
		  alert($('#recaptcha_challenge_field').val());
		  alert($('#recaptcha_response_field').val());
	  }); */
  }
  
  function register() {
	  $('#btn_reg').click(function() {
		  if(checkUser()&&checkPwd()&&checkEmail()&&flag<=1) {
			  var url = "reg.action";
			  var params = {
					"user.userName":$("#username").val(),
					"user.password":$("#Passwd").val(),
					"user.email":$("#emailAddress").val()
			  };
			  
			  $.post(url, params, function(result){
				  if(result == 'isOk') {
					  location.href="<%=request.getContextPath()%>/regSuc.jsp";
				  }else {
					  location.href="<%=request.getContextPath()%>/regFail.jsp";
				  }
			  });
			  return true;
		  }else{
			  return false;
		  }
	  });	
  }
  
  initValidate();
  register();
</script>
</body>
</html>
