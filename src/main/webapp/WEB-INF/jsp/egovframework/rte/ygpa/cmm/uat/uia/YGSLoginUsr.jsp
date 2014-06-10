<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/uat/uia/com.css' />" type="text/css"></link>
<title>YGPA 로그인</title>
<SCRIPT language="JavaScript">
	function fn_check_module(classid)
	{
		try{
			var obj = new ActiveXObject(classid);
			return true;
		}catch(e){
			return false;
		}
	}

	function fn_run_module()
	{
		try{
			var value = document.SSODaemon.GetSessionIndex();
			return true;
		}catch(e){
			return false;
		}
	}
</SCRIPT>

<script>

function actionLogin(event) {
//	event.preventDefault();
	if (document.loginForm.id.value =="") {
		alert("아이디를 입력하세요");
	} else if (document.loginForm.password.value =="") {
		alert("비밀번호를 입력하세요");
	} else {
		saveid(document.loginForm);
        document.loginForm.action="<c:url value='/uat/uia/YGActionLogin.do'/>";
		//document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
		//document.loginForm.action="<c:url value='/j_spring_security_check'/>";
		document.loginForm.submit();
	}
}

function setCookie (name, value, expires) {
	document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
	var search = Name + "="
	if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
		offset = document.cookie.indexOf(search)
		if (offset != -1) { // 쿠키가 존재하면
			offset += search.length
			// set index of beginning of value
			end = document.cookie.indexOf(";", offset)
			// 쿠키 값의 마지막 위치 인덱스 번호 설정
			if (end == -1)
				end = document.cookie.length
			return unescape(document.cookie.substring(offset, end))
		}
	}
	return "";
}

function saveid(form) {
	var expdate = new Date();
	// 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
	if (form.idsave.checked)
		expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
	else
		expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
	setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
	form.idsave.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {


	var message = document.loginForm.message.value;
	if (message != "") {
		alert(message);
	}

	getid(document.loginForm);

	//actionLogin();
	// 포커스
	//document.loginForm.rdoSlctUsr.focus();
}

</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 100px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.container {
  margin: 50px auto;
  width: 640px;
}

.login {
  position: relative;
  margin: 0 auto;
  padding: 20px 20px 20px;
  width: 310px;
}

.login p.submit {
  text-align: right;
}

.login-help {
  margin: 20px 0;
  font-size: 11px;
  color: white;
  text-align: center;
  text-shadow: 0 1px #2a85a1;
}

.login-help a {
  color: #cce7fa;
  text-decoration: none;
}

.login-help a:hover {
  text-decoration: underline;
}

:-moz-placeholder {
  color: #c9c9c9 !important;
  font-size: 13px;
}

::-webkit-input-placeholder {
  color: #ccc;
  font-size: 13px;
}

.container {
  margin: 50px auto;
  width: 640px;
}
.login:before {
  content: '';
  position: absolute;
  top: -8px;
  right: -8px;
  bottom: -8px;
  left: -8px;
  z-index: -1;
  background: rgba(0, 0, 0, 0.08);
  border-radius: 4px;
}

.login h1 {
  margin: -20px -20px 21px;
  line-height: 40px;
  font-size: 15px;
  font-weight: bold;
  color: #555;
  text-align: center;
  text-shadow: 0 1px white;
  background: #f3f3f3;
  border-bottom: 1px solid #cfcfcf;
  border-radius: 3px 3px 0 0;
  background-image: -webkit-linear-gradient(top, whiteffd, #eef2f5);
  background-image: -moz-linear-gradient(top, whiteffd, #eef2f5);
  background-image: -o-linear-gradient(top, whiteffd, #eef2f5);
  background-image: linear-gradient(to bottom, whiteffd, #eef2f5);
  -webkit-box-shadow: 0 1px whitesmoke;
  box-shadow: 0 1px whitesmoke;
}

.login p {
  margin: 20px 0 0;
}

.login p:first-child {
  margin-top: 0;
}

.login input[type=text], .login input[type=password] {
  width: 278px;
}

.login p.remember_me {
  float: left;
  line-height: 31px;
}

.login p.remember_me label {
  font-size: 12px;
  color: #777;
  cursor: pointer;
}

.login p.remember_me input {
  position: relative;
  bottom: 1px;
  margin-right: 4px;
  vertical-align: middle;
}

input {
  font-family: 'Lucida Grande', Tahoma, Verdana, sans-serif;
  font-size: 14px;
}

input[type=text], input[type=password] {
  margin: 5px;
  padding: 0 10px;
  width: 200px;
  height: 34px;
  color: #404040;
  background: white;
  border: 1px solid;
  border-color: #c4c4c4 #d1d1d1 #d4d4d4;
  border-radius: 2px;
  outline: 5px solid #eff4f7;
  -moz-outline-radius: 3px;
  -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
}

input[type=text]:focus, input[type=password]:focus {
  border-color: #7dc9e2;
  outline-color: #dceefc;
  outline-offset: 0;
}

input[type=submit] {
  padding: 0 18px;
  height: 29px;
  font-size: 12px;
  font-weight: bold;
  color: #527881;
  text-shadow: 0 1px #e3f1f1;
  background: #cde5ef;
  border: 1px solid;
  border-color: #b4ccce #b3c0c8 #9eb9c2;
  border-radius: 16px;
  outline: 0;
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
  background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
  -webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
  box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
}

input[type=submit]:active {
  background: #cde5ef;
  border-color: #9eb9c2 #b3c0c8 #b4ccce;
  -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
}

input[type=checkbox],
input[type=radio] {
border: 1px solid #c0c0c0;
margin: 0 0.1em 0 0;
padding: 0;
font-size: 16px;
line-height: 1em;
width: 1.25em;
height: 1.25em;
background: #fff;
background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ededed), to(#fbfbfb));

-webkit-appearance: none;
-webkit-box-shadow: 1px 1px 1px #fff;
-webkit-border-radius: 0.25em;
vertical-align: text-top;
display: inline-block;

}

input[type=radio] {
-webkit-border-radius: 2em; /* Make radios round */
}

input[type=checkbox]:checked::after {
content:"✔";
display:block;
text-align: center;
font-size: 16px;
height: 16px;
line-height: 18px;
}

input[type=radio]:checked::after {
content:"●";
display:block;
height: 16px;
line-height: 15px;
font-size: 20px;
text-align: center;
}

select {
border: 1px solid #D0D0D0;
background: url(http://www.quilor.com/i/select.png) no-repeat right center, -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fbfbfb), to(#ededed));
background: -moz-linear-gradient(19% 75% 90deg,#ededed, #fbfbfb);
-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
-moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
color: #444;
}

body {
  font: 13px/20px "Lucida Grande", Tahoma, Verdana, sans-serif;
  color: #404040;
  background: #0ca3d2;
}

-->
</style>
</head>
<body onload="fnInit();">

<form name="loginForm" action ="<c:url value='/actionLogin.do'/>" method="post">
<input type="hidden" name="message" value="${message}" />
<input type="hidden" name="pageurl" value="${pageurl}" />
<input name="userSe" type="hidden" value="USR"/>
<input name="j_username" type="hidden"/>


<div class="container">
  <div class="login">
    <h1>여수광양항만공사 GIS기반 자산관리시스템</h1>
    <form method="post" action="">
      <p><input name="id"  type="text" placeholder="사번이나 사용자 아이디를 입력 하십시요"/></p>
      <p><input type="password" name="password" value="" placeholder="패스워드를 입력 하십시요"></p>
      <p class="remember_me">
        <label>
         <label>
          <input type="checkbox" name="idsave" id="idsave">
           이 컴퓨터에 사용자의 아이디를 기억합니다.
        </label>
        </label>
      </p>
      <p class="submit"><input type="submit" name="commit" value="로그인" onclick="actionLogin(event);"></p>
    </form>
  </div>

  <div class="login-help">
    <p>사용자 아이디와 패스워드를 입력하여 로그인 합니다. 계정을 분실 하였거나 생성하지 않은 분은 전산실에 문의 바랍니다.</p>
  </div>
</div>

<!-- <div id="wrapper">
	<div id="loginBox">
		<div id="welcome">
			<h2></h2>
			<h3>로그인</h3>
		</div>
		<div id="loginAction">
			<div id="idBox"><span class="label">User ID : </span><input name="id"  type="text" id="id" tabindex="4" maxlength="10" size="12" /></div>
			<div id="passwordBox"><span class="label">Password : </span><input name="password"  type="password" id="userPw" tabindex="4" maxlength="10" size="12" /></div>
			<div id="userAction"><input name="idsave" type="checkbox" id="checkId" title="아이디 기억"/><span class="label">아이디 기억 </span></div>
			<div id="loginButton"><a href="javascript:actionLogin();">로그인</a></div>
		</div>
		<div id="footer">
			<p>사용자 아이디와 패스워드를 입력하여 로그인 합니다. 계정을 분실 하였거나 생성하지 않은 분은 전산실에 문의 바랍니다. (내선 : xxx)</p>
		</div>
	</div>
</div> -->
</form>

</body>
</html>