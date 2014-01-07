<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<HTML>
<HEAD>
<TITLE>YGPA Asset Management System</TITLE>
<%
	String pageVal = request.getParameter("pageurl");
	
	//System.out.println("22222======================pageVal" + pageVal);
	System.out.println("jsp session=============>" + request.getSession().getId());
%>	
<%
/**
	마이플랫폼 화면 
 */ 
 
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<link rel="stylesheet" href="<c:url value='/install/image/style.css' />" type="text/css" />
<script type="text/javascript" src="<c:url value='/install/checkBrowser.js'/>"></script>
<script type="text/javascript" src="<c:url value='/install/Mi_ocx.js'/>"></script>
<SCRIPT LANGUAGE="JavaScript">
function fn_load()
{
	var hostip = window.location.host;
	var pos22 = hostip.indexOf(".");
	if(pos22<0) {
		pos22 = hostip.indexOf(":");
		if(pos22<0) pos22=hostip.length;
	}
	var hostarea = hostip.substr(0,pos22);
	
	var startXml = "YGAM_MAIN.xml";
	if ( hostarea == 192 ) {
		startXml = "YGAM_MAIN.xml";			// 서버 버전
	}else if ( hostarea == 127 || hostarea == 'localhost' ) {
		startXml = "YGAM_MAIN_DEVELOP.xml";	// 개발자 버전
	}else {
		startXml = "YGAM_MAIN_OUTSIDE.xml";	// 외부 접속
	}
		
	var pageURL = "<%=pageVal%>";
	//if(pageURL == null || pageURL == "null") pageURL ="http://" + window.location.host + "/main_miplatform.do"
	if(pageURL == null || pageURL == "null") pageURL = "<c:url value='/main_miplatform.do'/>";
	
		//pageURL = "MAIN::IPMain.xml";
		
//		pageURL = "http://" + window.location.host + "/ipa/iplom/uat/uia/IPLoginUsr.do";//"MAIN::IPLogin.xml";
	var MiPlatformCtrl = document.getElementById("MiPlatformCtrl");
	
	MiPlatformCtrl.AutoSize = true;
	MiPlatformCtrl.Key = "YGAM";
	MiPlatformCtrl.Version = "3.3";
	MiPlatformCtrl.StartXml = "http://" + window.location.host + "<c:url value='/miplatform/egovframework/ygpa/'/>" + startXml;


	//alert("http://" + window.location.host + "/main_miplatform.do");
	MiPlatformCtrl.InitURL = "http://" + window.location.host + pageURL;
	//alert("http://" + window.location.host + pageURL);

	//alert(MiPlatformCtrl.InitUrl);
	//alert(MiPlatformCtrl.InitUrl);
	MiPlatformCtrl.DoRun();
	//MiPlatformCtrl.Do();

 
	 
}

function fn_run()
{

	var hostip = window.location.host;
	var pos22 = hostip.indexOf(".");
	if(pos22<0) {
		pos22 = hostip.indexOf(":");
		if(pos22<0) pos22=hostip.length;
	}
	var hostarea = hostip.substr(0,pos22);
	
	var startXml = "YGAM_MAIN.xml";
	if ( hostarea == 192 ) {
		startXml = "YGAM_MAIN.xml";			// 서버 버전
	}else if ( hostarea == 127 || hostarea == 'localhost' ) {
		startXml = "YGAM_MAIN_DEVELOP.xml";	// 개발자 버전
	}else {
		startXml = "YGAM_MAIN_OUTSIDE.xml";	// 외부 접속
	}
	
	var prj_key         = "YGAM";
	var prj_startxml    = "http://" + window.location.host + "<c:url value='/miplatform/egovframework/com/ygpa/'/>"+startXml;
	var prj_comppath    = "%UserApp%TOBESOFT\\" + prj_key + "\\component";
	var prj_update_url  = "http://" + window.location.host + "<c:url value='/install/330/update_cfg.xml'/>";

	var MiInstaller = document.getElementById("MiInstaller");
	MiInstaller.DeviceType = "Win32U";	
	MiInstaller.Version = "3.3";
	
	MiInstaller.Launch = true;	
	MiInstaller.Width  = 1024;
	MiInstaller.Height	= 910;
	MiInstaller.Retry = 1;
	MiInstaller.Timeout = 300;
	MiInstaller.GlobalVal = "";
	MiInstaller.OnlyOne = true;

	MiInstaller.Key           = prj_key;
	MiInstaller.ComponentPath = prj_comppath;
	MiInstaller.StartXml      = prj_startxml;
	MiInstaller.UpdateURL =  prj_update_url;
	MiInstaller.GlobalVal = "<%=request.getSession().getId()%>";
	MiInstaller.Run();
}

function OnNavigateComplete(strReqID,strServiceID,strUrl)
{
	MiPlatformCtrl.SetGlobalVariableValue( "G_IsBrowser", "Y");
	MiPlatformCtrl.SetGlobalVariableValue( "YGAMSES", "<%=request.getSession().getId()%>");
	MiPlatformCtrl.SetGlobalVariableValue( "JSESSIONID", "<%=request.getSession().getId()%>");
	MiPlatformCtrl.SetGlobalVariableValue( "PREFIX", "<c:url value='/'/>");
}
function OnUserNotify(NotifyID,strMsg){
	if ( NotifyID == 1 )
	{
		window.location = "http://" + window.location.host;
	}else if ( NotifyID == -8888 )
	{
		window.location = "http://" + window.location.host;
	}else if ( NotifyID == -9999 )
	{
		window.location = "http://" + window.location.host + "/ssoLogout.do";
	}else if ( NotifyID == -3 )
	{
		window.location = "http://" + window.location.host + "/ssoLogout.do";
	}else if ( NotifyID == -9090 )
	{
		fn_run();
	}
}

</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=MiPlatformCtrl EVENT=NavigateComplete( pDisp, strReqID, strServiceID, strURL )>
	MiPlatformCtrl.SetGlobalVariableValue( "G_IsBrowser", "Y");
	MiPlatformCtrl.SetGlobalVariableValue( "YGAMSES", "<%=request.getSession().getId()%>");
	MiPlatformCtrl.SetGlobalVariableValue( "JSESSIONID", "<%=request.getSession().getId()%>");
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=MiPlatformCtrl EVENT="UserNotify(NotifyID,strMsg)">
if ( NotifyID == 1 )
{
	window.location = "http://" + window.location.host;
}else if ( NotifyID == -8888 )
{
	window.location = "http://" + window.location.host;
}else if ( NotifyID == -9999 )
{
	window.location = "http://" + window.location.host + "/ssoLogout.do";
}else if ( NotifyID == -3 )
{
	window.location = "http://" + window.location.host + "/ssoLogout.do";
}else if ( NotifyID == -9090 )
{
	fn_run();
}
</SCRIPT>

</HEAD>

<BODY leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" scroll="auto" OnLoad="fn_load();">
<TABLE cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
	<TR>
	<TD align="center" valign="middle"> 
	<TABLE width="100%" height="100%" cellpadding="0" cellspacing="0" border="1">
		<TR>
		<TD> 
		<script language="JavaScript">
			if ( checkBrowser.browser == "Explorer"){
				IE_CreateMiPlatformCtrl_XU("MiPlatformCtrl");
		//		return;
				//document.write(' <object classid="clsid:EC3500BB-63AF-45E4-9CBE-C126C77A28B5" id="MiPlatformCtrl" border="0" width="1024px" height="768px" visible="true" > ');
				//document.write(' </object>    ');		
			}else{
				ST_CreateMiPlatformCtrlPlugin330_XU("MiPlatformCtrl");
		//		return;
			}
		
		</script> 
		<SCRIPT LANGUAGE="JavaScript">
			if ( checkBrowser.browser == "Explorer"){
				IE_CreateMiInstlr("MiInstaller","Win32U","3.3","YGAM");
			}else{
				ST_CreateMiInstlr("MiInstaller","Win32U","3.3","YGAM");
			}
		</SCRIPT>		
		</TD>
		</TR>
	</TABLE>
	</TD>
	</TR>

</TABLE>
</BODY>
</HTML>