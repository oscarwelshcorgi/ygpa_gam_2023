<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/css/egovframework/com/cmm/uat/uia/com.css" type="text/css"></link>
<title>YGPA 로그인</title>
<SCRIPT LANGUAGE="JavaScript">
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

function actionLogin() {

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
	if (form.checkId.checked)
		expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
	else
		expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
	setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
	form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
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

#wrapper {
	display: table;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
	margin-bottom: auto;
	vertical-align: middle;
}

#loginBox {
	width: 400px;
	height: 300px;
	border:outset 1px #CCCCCC;
	/* Webkit (Safari/Chrome) */ -webkit-box-shadow: 10px 10px 5px 5px #CCCCCC;
	/* Mozilla Firefox */ -moz-box-shadow: 10px 10px 5px 5px #CCCCCC;
	/* Proposed W3C Markup */ box-shadow: 10px 10px 5px 5px #CCCCCC;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
	margin-bottom: auto;
	vertical-align: middle;
}

#loginAction {
	position: relative;
	left: 50px;
	width: 300px;
	height: 106px;
}

#idBox {
	position: relative;
	left: 20px;
	top: 20px;
}

#passwordBox {
	position: relative;
	left: 20px;
	top: 24px;
}

#userAction {
	position: relative;
	left: 20px;
	top: 24px;
}

#idBox input,#passwordBox input {
	width: 100px;
	height: 20px;
	border:solid 1px #666666;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright:5px;
	-moz-border-radius-bottomleft:5px;
	-moz-border-radius-bottomright:5px;
	-webkit-border-top-left-radius:5px;
	-webkit-border-top-right-radius:5px;
	-webkit-border-bottom-left-radius:5px;
	-webkit-border-bottom-right-radius:5px;
	border-top-left-radius:5px;
	border-top-right-radius:5px;
	border-bottom-left-radius:5px;
	border-bottom-right-radius:5px;
}

#loginButton {
	position: relative;
	left: 200px;
	top: -48px;
}

#loginButton a {
	-moz-box-shadow:inset 0px 1px 0px 0px #dcecfb;
	-webkit-box-shadow:inset 0px 1px 0px 0px #dcecfb;
	box-shadow:inset 0px 1px 0px 0px #dcecfb;
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #bddbfa), color-stop(1, #80b5ea) );
	background:-moz-linear-gradient( center top, #bddbfa 5%, #80b5ea 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#bddbfa', endColorstr='#80b5ea');
	background-color:#bddbfa;
	-webkit-border-top-left-radius:0px;
	-moz-border-radius-topleft:0px;
	border-top-left-radius:0px;
	-webkit-border-top-right-radius:0px;
	-moz-border-radius-topright:0px;
	border-top-right-radius:0px;
	-webkit-border-bottom-right-radius:0px;
	-moz-border-radius-bottomright:0px;
	border-bottom-right-radius:0px;
	-webkit-border-bottom-left-radius:0px;
	-moz-border-radius-bottomleft:0px;
	border-bottom-left-radius:0px;
	text-indent:0;
	border:1px solid #84bbf3;
	display:inline-block;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	font-style:normal;
	height:70px;
	line-height:70px;
	width:80px;
	text-decoration:none;
	text-align:center;
	text-shadow:1px 1px 0px #528ecc;
}
#loginButton a:hover {
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #80b5ea), color-stop(1, #bddbfa) );
	background:-moz-linear-gradient( center top, #80b5ea 5%, #bddbfa 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#80b5ea', endColorstr='#bddbfa');
	background-color:#80b5ea;
}
#loginButton a:active {
	position:relative;
	top:1px;
}

#welcome h2 {
text-align: center;
color: #06B8FE;
  font-weight: bold;
  font-size: 25px;
}
  
#welcome h3 {
text-align: center;
color: black;
  font-weight: bold;
  font-size: 20px;
}

#footer p {
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 5px;
  color: black;
  font-size: 12px;
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

<div id="wrapper">
	<div id="loginBox">
		<div id="welcome">
			<h2>여수광양항만공사 자산관리시스템</h2>
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
</div>
</form>

</body>
</html>