<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String pageVal = request.getParameter("pageurl");
	String processWidth = "590";
//System.out.println("pageVal<<<<<===>"+pageVal);
%>	
<%
/**
 * 마이플랫폼 설치 체크  화면
 */ 
 
%>
<html xmlns:spring="http://www.springframework.org/tags">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/install/image/style.css'/>" type="text/css" />   
<script type="text/javascript" src="<c:url value='/install/checkBrowser.js'/>"></script>
<script type="text/javascript" src="<c:url value='/install/Mi_ocx.js'/>"></script>
<SCRIPT LANGUAGE="JavaScript">
	function fn_check_module()
	{  
		try{
			if ( document.MiInstaller == undefined ) return false;
			if ( document.MiInstaller.Version == undefined ) return false;
			
			var chkMsg = document.MiInstaller.Version;
			return true;
		}catch(e){  
			return false;
		}
	} 
</SCRIPT> 
<SCRIPT LANGUAGE="JavaScript">
<!--

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
		
	var processWidth = <%=processWidth%>;
	
	var pageURL = <%=pageVal%>;
	var ctxRoot = "<c:url value='/'/>";
	if(pageURL == null || pageURL == "null") pageURL = ctxRoot+"main_miplatform.do";
	var proMsg, procMsg;
	var IsError = false;
	var TotalVersionFileCnt;
	var TotalItemCnt;
	var progressColor = "red";	// set to progress bar color
	var pg_cell_At = 0,pg1_cell_At = 0; 
	var prj_key         = "YGAM";
	var prj_startxml    = "http://" + window.location.host + "<c:url value='/miplatform/egovframework/ygpa/'/>" + startXml;
	var prj_comppath    = "%UserApp%TOBESOFT\\" + prj_key + "\\component";
	var prj_update_url  = "http://" + window.location.host + ctxRoot + "install/330/update_cfg.xml";


		
	function fn_load()
	{
		var pageURL = "<%=pageVal%>";
		if(pageURL == null || pageURL == "null") pageURL = "<c:url value='/main_miplatform.do'/>";
		if ( fn_check_module() )
		{		

			var MiInstaller = document.getElementById("MiInstaller");
			var InstallDiv = document.getElementById("InstallDiv");

			
			MiInstaller.DeviceType = "Win32U";	
			MiInstaller.Version = "3.3";
			
			MiInstaller.Launch = true;	
			MiInstaller.Width  = 1280;
			MiInstaller.Height	= 1024;
			MiInstaller.Retry = 1;
			MiInstaller.Timeout = 300;
			MiInstaller.GlobalVal = "";
			MiInstaller.OnlyOne = true;

			MiInstaller.Key           = prj_key;
			MiInstaller.ComponentPath = prj_comppath;
			MiInstaller.StartXml      = prj_startxml;
			MiInstaller.UpdateURL =  prj_update_url;

			var Bcnt = MiInstaller.ExistVersionUpCnt();
			if (Bcnt )
			{				
				InstallDiv.style.display="block";
				MiInstaller.StartDownLoad();
			} else { 		
				fn_run();
			}  
		}else{  
			var InstallDiv = document.getElementById("InstallDiv");
			
			InstallDiv.style.display="block";
			if ( checkBrowser.browser == "Explorer")
			{
				MiInstaller.StartDownLoad();
			}else{
				var MiInstaller = document.getElementById("MiInstaller");			
				if(MiInstaller.Key == null)
				{		
					var err_msg = document.getElementById("err_msg");
					var t_err = document.getElementById("t_err");
					err_msg.innerHTML += '인터넷 익스플로어를 사용하지 않는 사용자는 <br>';
					err_msg.innerHTML += '아래 플러그인을 설치하시고 브라우저를  <br>';
					err_msg.innerHTML += '<font class="f2" color="red">다시 시작하여 주시기 바랍니다. </font><br>';
					err_msg.innerHTML += '<a href="http://' + window.location.host + ctxRoot+'install/330/MiPlatform_InstallerPlugin330.exe"><font class="f2" color="red">Plugin Download</font></a>';
					t_err.className = "show";				 
				} else {
					MiInstaller.StartDownLoad();
				} 	
			}

			
		}			
	}


	function fn_run()
	{	
		if ( IsError ) return ;
		window.location  = ctxRoot+"form_miplatform.do?pageurl=" + pageURL;
	}
		
	function progress_update(obj,cur_cnt) {
		if ( obj.id == "progress" ) obj.width = cur_cnt;
		else obj.width = cur_cnt;
	}
	
 	function progress_clear(obj) {
		obj.width = 0;
		if ( obj.id == "progress" ) pg_cell_At = 0;
		else pg1_cell_At = 0;		
	}
	
	function OnStartDownLoad(VersionFileName,DownFileName,Type,TotalCnt,CurIndex)
	{
		var progress = document.getElementById("progress");
		var Distproc = document.getElementById("Distproc");
		var tot_item = document.getElementById("tot_item");
		var item_nm = document.getElementById("item_nm");	
		var prc_msg = document.getElementById("prc_msg");	

		if ( Type == 1 ) //EVENTCONFIGhttps://www.google.co.kr/search?q=%EB%8F%99%EC%A0%81%EC%9C%BC%EB%A1%9C+ie+activex+%EC%8B%A4%ED%96%89+%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8&newwindow=1&biw=1920&bih=952&ei=UdsNUt-pKcTskgW7p4DgDg&start=10&sa=N&ved=0CKABEKsf
		{
			//progress_clear(progress);
			//progress_clear(progress1);
		}
		else if ( Type == 2 ) 
		{
			progress.width = 0;
			Distproc.width = 0;
			tot_item.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
			//tot_ditem.innerHTML = "&nbsp;";
		
		} 
		else if ( Type == 3 ) //EVENTDOWNLOAD
		{
			GcurIndx = CurIndex;
			Gtotcnt = TotalCnt;
			tot_item.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
			
			var tpos = DownFileName.lastIndexOf("/") + 1;
			var Fname = DownFileName.substr(tpos,DownFileName.length - tpos);

			item_nm.innerHTML = "&nbsp;" + Fname;
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;파일 다운로드 중....";
		}
		else if ( Type == 4 ) //EVENTDISTRIBUTE
		{
			//tot_ditem.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
			
			var tpos = DownFileName.lastIndexOf("/") + 1;
			var Fname = DownFileName.substr(tpos,DownFileName.length - tpos);

			item_nm.innerHTML = "&nbsp;" + Fname;
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;실제 경로 배포 중....";
		}	
	}

	
	function OnEndDownLoad(VersionFileName,DownFileName,Type,TotalCnt,CurIndex)
	{

		var progress = document.getElementById("progress");
		var Distproc = document.getElementById("Distproc");
		var t_err = document.getElementById("t_err");
		//var reinstall = document.getElementById("reinstall");	
		var prc_msg = document.getElementById("prc_msg");	
//alert(Type);
		if ( Type == 1 ) //EVENTCONFIG
		{
			//alert(VersionFileName + "," + DownFileName);
			
			if ( !IsError ) {

				TotalVersionFileCnt = TotalCnt;
				progress_update(progress,processWidth);
				progress_update(Distproc,processWidth);
				if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;설치 완료";
		
				fn_run();
				//history.back();
			}
			
		}
		else if ( Type == 3 )//EVENTDOWNLOAD
		{
			
			
		    var before_At = pg_cell_At;
			pg_cell_At = parseInt( ( (CurIndex)/TotalCnt ) * processWidth );
			progress_update(progress,pg_cell_At);
			if ( IsError ) {
				t_err.className = "show";
					//reinstall.className = "show";
			}		
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;파일 다운로드 완료....";
		}
		else if ( Type == 4 )//EVENTDISTRIBUTE
		{
		    var before_At = pg1_cell_At;
			  	pg1_cell_At = parseInt( ( (CurIndex)/TotalCnt ) * processWidth );
					progress_update(Distproc,pg1_cell_At);
					
			if ( IsError ) {
					t_err.className = "show";
					//reinstall.className = "show";
			}		
			
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;실제 경로 배포 완료";
		}
			
	}

	function OnError(ItemName,ErrorCode,ErrorMsg)
	{
		if( (ErrorCode != "113") && (ErrorCode != 113) )
		{
			var err_msg = document.getElementById("err_msg");
			var t_err = document.getElementById("t_err");
			//var reinstall = document.getElementById("reinstall");	
			var prc_msg = document.getElementById("prc_msg");	
				
			IsError = true;
			err_msg.innerHTML += '<font class="f2" color="red">' + ItemName + '&nbsp;다운&nbsp;실패&nbsp; -- ErrorCode:' + ErrorCode + ' ' + ErrorMsg + "<br>" +
					'<img src="<c:url value="image/bu_comment02.gif"/>" width="14" height="11">&nbsp;&nbsp;<font color="black">수동설치시 <a href="<c:url value="install/MiPlatform_SetupDeploy320U_200907.exe"/>"><b>여기</b></a> 를 클릭하시기 바랍니다.</font>';
			
			t_err.className = "show";
			//reinstall.className = "show";
			var MiInstaller = document.getElementById("MiInstaller");
			MiInstaller.stop();
		}
	}  

//-->
</SCRIPT>



<SCRIPT language=JavaScript for=MiInstaller event=OnConfirm(ItemName)>
	var a;
	a  = "OnConfirm=>Item=";
	a += ItemName;	
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=MiInstaller EVENT=OnProgress(ItemName,prgress,progressMax,StatusText)>
	if(ItemName != "")	{
		if( progressMax > 0 && prgress > 0 )	{
		        var before_At = pg_cell_At;

		        pg_cell_At = parseInt( ( ( ( GcurIndx - 1)/Gtotcnt ) * 590 ) +  ( ( ( 1/Gtotcnt ) * 590 ) * (prgress/progressMax) ) );
		        if ( pg_cell_At > 590 ) pg_cell_At = 590;
						progress.width = pg_cell_At;
		}
	}
</SCRIPT>   

<SCRIPT language=JavaScript for=MiInstaller event=OnStartDownLoad(VersionFileName,DownFileName,Type,TotalCnt,CurIndex)>

		if ( Type == 1 ) //EVENTCONFIG
		{
			//progress_clear(progress);
			//progress_clear(progress1);
		}
		else if ( Type == 2 ) 
		{
				progress.width = 0;
				Distproc.width = 0;
				tot_item.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
				//tot_ditem.innerHTML = "&nbsp;";
		
		} 
		else if ( Type == 3 ) //EVENTDOWNLOAD
		{
			GcurIndx = CurIndex;
			Gtotcnt = TotalCnt;
			tot_item.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
			
			var tpos = DownFileName.lastIndexOf("/") + 1;
			var Fname = DownFileName.substr(tpos,DownFileName.length - tpos);

			item_nm.innerHTML = "&nbsp;" + Fname;
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;파일 다운로드 중....";
		}
		else if ( Type == 4 ) //EVENTDISTRIBUTE
		{
			//tot_ditem.innerHTML = "&nbsp;" + CurIndex + "/" + TotalCnt;		
			
			var tpos = DownFileName.lastIndexOf("/") + 1;
			var Fname = DownFileName.substr(tpos,DownFileName.length - tpos);

			item_nm.innerHTML = "&nbsp;" + Fname;
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;실제 경로 배포 중....";
		}	
		
</SCRIPT>
 
<!--ItemSize는 미지정 -->
<SCRIPT language=JavaScript for=MiInstaller event=OnEndDownLoad(VersionFileName,DownFileName,Type,TotalCnt,CurIndex)>

		if ( Type == 1 ) //EVENTCONFIG
		{
			//alert(VersionFileName + "," + DownFileName);
			if ( !IsError ) {

				TotalVersionFileCnt = TotalCnt;
				progress_update(progress,590);
				progress_update(Distproc,590);
				if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;설치 완료";
		
				fn_run();
				//history.back();
			}
			
		}
		else if ( Type == 3 )//EVENTDOWNLOAD
		{
			
		    var before_At = pg_cell_At;
			pg_cell_At = parseInt( ( (CurIndex)/TotalCnt ) * 590 );


			progress_update(progress,pg_cell_At);
			
			if ( IsError ) {
				t_err.className = "show";
				reinstall.className = "show";
			}		
		
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;파일 다운로드 완료....";
		}
		else if ( Type == 4 )//EVENTDISTRIBUTE
		{
		    var before_At = pg1_cell_At;
		    pg1_cell_At = parseInt( ( (CurIndex)/TotalCnt ) * 590 );
			progress_update(Distproc,pg1_cell_At);
					
			if ( IsError ) {
				t_err.className = "show";
				reinstall.className = "show";
			}		
			
			if ( prc_msg != "" && prc_msg != null && prc_msg != "undefined" ) prc_msg.innerHTML = "&nbsp;실제 경로 배포 완료";
		}
</SCRIPT>
<SCRIPT language=JavaScript for=MiInstaller event=OnError(ItemName,ErrorCode,ErrorMsg)>
	if(ErrorCode != "113")
	{
		IsError = true;
		err_msg.innerHTML += '<font class="f2" color="red">' + ItemName + '&nbsp;다운&nbsp;실패&nbsp; -- ErrorCode:' + ErrorCode + ' ' + ErrorMsg + "<br>";
		t_err.className = "show";
		reinstall.className = "show";
		MiInstaller.stop();
	}
</SCRIPT>

</HEAD>


<BODY leftmargin="0" topmargin="0" OnLoad="fn_load();">

<SCRIPT LANGUAGE="JavaScript">
		
	if ( checkBrowser.browser == "Explorer"){
		IE_CreateMiInstlr("MiInstaller","Win32U","3.3","YGAM", ctxRoot);
	}else{
		ST_CreateMiInstlr("MiInstaller","Win32U","3.3","YGAM");
	}
	
</SCRIPT>
<div id="InstallDiv" style="display:none">
<table id="ins_tbl" align="center" border="0" width="600">
  <tr>
    <td><table width="637" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="10"></td>
        </tr>
        <tr> 
          <td><img src="<c:url value='/install/image/insu_down_listbg1.gif'/>" width="637" height="11"></td>
        </tr>
        <tr> 
          <td height="37" bgcolor="#EEEEEE"> <table width="607" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="15"></td>
              </tr>
              <tr> 
                <td><table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="10" rowspan="3"><img src="<c:url value='/install/image/insu_down_img.gif'/>" width="150" height="90"></td>
                      <td width="590" height="35"><img src="<c:url value='/install/image/insu_down_title.gif'/>" width="204" height="27"></td>
                    </tr>
                    <tr> 
                      <td height="45"><img src="<c:url value='/install/image/insu_down_title2.gif'/>" width="329" height="32"></td>
                    </tr>
                  </table></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
              </tr>
              <tr> 
                <td><table width="607" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="12" height="12"><img src="<c:url value='/install/image/insu_down_listbg_sub1.gif'/>" width="12" height="12"></td>
                      <td width="583" height="12" bgcolor="#FFFFFF"></td>
                      <td width="12" height="12"><img src="<c:url value='/install/image/insu_down_listbg_sub2.gif'/>" width="12" height="12"></td>
                    </tr>
                    <tr> 
                      <td rowspan="5" bgcolor="#FFFFFF">&nbsp;</td>
                      <td bgcolor="#FFFFFF">
<table width="570" border="0" align="center" cellpadding="0" cellspacing="0">
<tr> 
                            <td width="10" valign="top"><img src="<c:url value='/install/image/insu_down_list_icon1.gif'/>" width="8" height="11"></td>
                            <td width="570">암호화 프로그램 설치여부를 묻는 보안경고창이 나타나면 반드시 
                              “<strong><font color="#FF7200">예</font></strong>”를 
                              선택하여주시기 바랍니다.<br>
                              “아니오”를 선택하시면 보안을 위해 사용이 제한됩니다.</td>
                          </tr>
                        </table></td>
                      <td rowspan="5" bgcolor="#FFFFFF">&nbsp;</td>
                    </tr>
                    <tr> 
                      <td bgcolor="#FFFFFF">&nbsp;</td>
                    </tr>
                    <tr> 
                      <td bgcolor="#FFFFFF"><table width="570" border="0" align="center" cellpadding="0" cellspacing="0">
<tr> 
                            <td width="10" valign="top"><img src="<c:url value='/install/image/insu_down_list_icon1.gif'/>" width="8" height="11"></td>
                            <td width="570">윈도우 XP 서비스팩2 사용자께서는 주소 표시줄 아래 경고문구 
                              “ <img src="<c:url value='/install/image/insu_down_list_icon2.gif'/>" width="15" height="15" align="absmiddle">이 
                              사이트에서...여기를 클릭하십<br>
                              시오” 를 선택하시어, Active X컨트롤을 설치하시기 바랍니다.</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr> 
                      <td bgcolor="#FFFFFF">&nbsp;</td>
                    </tr>
                    <tr> 
<td bgcolor="#FFFFFF">
<table width="570" border="0" align="center" cellpadding="0" cellspacing="0">
<tr> 
                            <td width="10" valign="top"><img src="<c:url value='/install/image/insu_down_list_icon1.gif'/>" width="8" height="11"></td>
                            <td width="570">프로그램 설치가 정상적이지 않을 경우에는 <strong><font color="#FF7200">수동설치</font></strong>를 
                              통해 수동설치하시거나 <strong><font color="#FF7200">문의하기</font></strong>를 
                              통해 질문<br>
                              하시면 신속히 답변을 드리겠습니다.</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr> 
                      <td width="12" height="12"><img src="<c:url value='/install/image/insu_down_listbg_sub3.gif'/>" width="12" height="12"></td>
                      <td bgcolor="#FFFFFF"></td>
                      <td width="12" height="12"><img src="<c:url value='/install/image/insu_down_listbg_sub4.gif'/>" width="12" height="12"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr> 
          <td><img src="<c:url value='/install/image/insu_down_listbg2.gif'/>" width="637" height="11"></td>
        </tr>
        <tr>
          <td height="5"></td>
        </tr>
      </table></td>
  </tr>
</table>

<table  id="ins_tbl" align="center" border=0 width="600" >
<tr>
	<td>
	<table >
		<tr>
			<td class="f2"> 파일다운로드 진행 상황</td><td id="comp_cnt" class="f2" align=left NOWRAP>&nbsp;</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td colspan=2 valign="middle" WIDTH="<%=processWidth%>">
	<div  valign="middle" style="font-size:3px;padding:1px;border:1px GROOVE silver;">
	<IMG ID="progress" SRC="<c:url value='/install/image/installbar.jpg'/>" WIDTH="0%" HEIGHT="10" >
	</div>
</td>
</tr>
<tr>
	<td>
	<table>
		<tr>
		<td class="f2" >파일설치 진행 상황</td><td id="tot_item" align=left class="f2" NOWRAP>&nbsp;</td>
		</tr>
	</table>
	</td>		
</tr>
<tr>
	<td colspan=2 valign="middle" WIDTH="<%=processWidth%>">
	<div  style="font-size:3px;padding:1px;border:1px GROOVE silver;">
	<IMG ID="Distproc" SRC="<c:url value='/install/image/installbar.jpg'/>" WIDTH="0%" HEIGHT="10" >
	</div>
	</td>
</tr>
<tr>
	<td>
	<table>
		<tr>
			<td class="f2">대상파일</td><td id="item_nm" class="f2" align=left NOWRAP>&nbsp;</td>
		</tr>
	</table>
	</td>		
</tr>
<tr>
	<td id=prc_msg class="f2" >&nbsp;<td>
</tr>	
</table>
<table class="hide" id=t_err align=center>
	<tr>
		<td class="f2">설치시 에러가 발생한 항목</td>
	</tr>
	<tr>
		<td id=err_msg class="f2">&nbsp;</td>
	</tr>
</table>
</div>
</BODY>   
</HTML>
