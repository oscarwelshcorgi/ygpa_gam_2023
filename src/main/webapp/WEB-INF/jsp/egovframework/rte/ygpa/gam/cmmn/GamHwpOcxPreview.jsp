<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamHtldPrintNoticeIssue.jsp
  * @Description : 고지서 출력 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.19  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.03.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
    <style>
/*       html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
 */

 body {
    margin: 0;
    padding: 0;
    background-color: #FAFAFA;
    font: 12pt "Tahoma";
}

* {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
}

</style>
	<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
	<script>
	var BasePath = "C:/Users/Public/";

	var pos;

	function _GetBasePath()
	{
		//BasePath를 구한다.
		var loc = unescape(document.location.href);
		var lowercase = loc.toLowerCase(loc);
		if (lowercase.indexOf("http://") == 0) // Internet
		{
			return loc.substr(0,loc.lastIndexOf("/") + 1);//BasePath 생성
		}
		else // local
		{
			var path;
			path = loc.replace(/.{2,}:\/{2,}/, ""); // file:/// 를 지워버린다.
			return path.substr(0,path.lastIndexOf("/") + 1);//BasePath 생성
		}
	}
	function InitToolBarJS()
	{
		HwpControl.HwpCtrl.SetToolBar(0, "FileSave, FilePreview, Print, Separator, Undo, Redo, Separator, Cut, Copy, Paste,"
		+"Separator, ParaNumberBullet, MultiColumn, SpellingCheck, HwpDic, Separator, PictureInsertDialog");

		HwpControl.HwpCtrl.SetToolBar(1, "StyleCombo, CharShapeLanguage, CharShapeTypeFace, CharShapeHeight,"
		+"CharShapeBold, CharShapeItalic, CharShapeUnderline, ParagraphShapeAlignJustify, ParagraphShapeAlignLeft,"
		+"ParagraphShapeAlignCenter, ParagraphShapeAlignRight, Separator, ParaShapeLineSpacing,"
		+"ParagraphShapeDecreaseLeftMargin, ParagraphShapeIncreaseLeftMargin");
		HwpControl.HwpCtrl.ShowToolBar(true);

	}
	function initScan()
	{
		HwpControl.HwpCtrl.InitScan(0xff, 0x70, 0, 0, 0, 0);
	}
	var lastPos=0;
	function replaceText() {
		var TextSet;
		var ret;
		var retmsg;
		var txt;
		var search_txt=null;
		TextSet = HwpControl.HwpCtrl.CreateSet("GetText");
		txt = "";
		ret = HwpControl.HwpCtrl.GetTextBySet(TextSet);
		switch(ret)
		{
		case 0:
			retmsg = "텍스트정보 없음";
			break;
		case 1:
		 	retmsg = "리스트의 끝";
			break;
		case 2:
			retmsg = "일반 텍스트";
			txt = TextSet.Item("Text");
			search_txt=TextSet.Item("Text");
			break;
		case 3:
			retmsg = "다음 문단";
			txt = TextSet.Item("Text");
			search_txt=TextSet.Item("Text");
			break;
		case 4:
			retmsg = "제어문자 내부로 들어감";
			search_txt=TextSet.Item("Text");
			txt = "{\n";
			break;
		case 5:
			retmsg = "제어 문자를 빠져 나옴";
			search_txt=TextSet.Item("Text");
			txt = "}\n";
			break;
		case 101:
			retmsg = "초기화 안됨. (InitScan() 실패 또는 InitScan()를 실행하지 않은 경우.)";
			break;
		case 102:
			retmsg = "텍스트 변환 실패";
			break;
		}
		if(search_txt!=null && search_txt!="") {
			var v=search_txt.match(/#\d+#/);
			if(v!=null && v!="") {
				console.log('found arg : '+v);
				var varName=v[0].substring(1,v[0].length-1);
				var p=HwpControl.HwpCtrl.GetPosBySet();
				lastPos=p.Item("Pos");
			}
			else {
				console.log('not found arg : '+search_txt);
			}
		}
		else console.log(retmsg + ":" + TextSet.Item("Text"));
		HwpControl.HwpCtrl.MovePos(201, 0, 0);
		return ret;
	}
	/* function GetText()
	{
		var TextSet;
		var ret;
		var retmsg;
		var txt;
		TextSet = HwpControl.HwpCtrl.CreateSet("GetText");
		txt = "";
		ret = HwpControl.HwpCtrl.GetTextBySet(TextSet);
		switch(ret)
		{
		case 0:
			retmsg = "텍스트정보 없음";
			break;
		case 1:
		 	retmsg = "리스트의 끝";
			break;
		case 2:
			retmsg = "일반 텍스트";
			txt = TextSet.Item("Text");
			var v=txt.match(/#\d+#/);
			if(v!=null && v!="") {
				console.log('found arg : '+v);
			}
			break;
		case 3:
			retmsg = "다음 문단";
			txt = TextSet.Item("Text");
			break;
		case 4:
			retmsg = "제어문자 내부로 들어감";
			txt = "{\n";
			break;
		case 5:
			retmsg = "제어 문자를 빠져 나옴";
			txt = "}\n";
			break;
		case 101:
			retmsg = "초기화 안됨. (InitScan() 실패 또는 InitScan()를 실행하지 않은 경우.)";
			break;
		case 102:
			retmsg = "텍스트 변환 실패";
			break;
		}
		HwpControl.HwpCtrl.MovePos(201, 0, 0);
		return ret;

	} */
	function ReleaseScan()
	{
		HwpControl.HwpCtrl.ReleaseScan();
	}
	function MoveToScanPos()
	{
		HwpControl.HwpCtrl.MovePos(201, 0, 0);
		HwpControl.HwpCtrl.focus();
	}
	function ClearMsg()
	{
		HwpControl.msg.value="";
		HwpControl.txtarea.value="";
	}
	function GetPos()
	{
		pos = HwpControl.HwpCtrl.GetPosBySet();
		HwpControl.list.value=pos.Item("List");
		HwpControl.para.value=pos.Item("Para");
		HwpControl.pos.value=pos.Item("Pos");
	}
	function SetPos()
	{
		pos = HwpControl.HwpCtrl.CreateSet("ListParaPos");
		pos.SetItem("List", 0 + new Number(HwpControl.list.value));
		pos.SetItem("Para", 0 + new Number(HwpControl.para.value));
		pos.SetItem("Pos", 0 + new Number(HwpControl.pos.value));
		HwpControl.HwpCtrl.SetPosBySet(pos);
		HwpControl.HwpCtrl.focus();
	}

	function putField(item, value) {
		if(HwpControl.HwpCtrl.FieldExist(item)) {
			HwpControl.HwpCtrl.PutFieldText(item, value);
		}
	}

	function putImage(item, value) {
		if(HwpControl.HwpCtrl.FieldExist(item)) {
			HwpControl.HwpCtrl.InsertPicture(item, value);
		}
	}

	$( window ).load(function() {
		HwpControl.HwpCtrl.SetClientName("DEBUG");
		InitToolBarJS();
		if(!HwpControl.HwpCtrl.Open("http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/tmpl/${hwpTemplateName}' />"))
		{
			alert("템플릿을 찾을 수 없습니다.");
		}
		initScan();
		for(var i=0; i<1000; i++) {
			var ret = replaceText();
			if(ret==1) {
				HwpControl.HwpCtrl.ReleaseScan();
				var formItems = $('#userData').children();
				for(var j=0; j<formItems.length; j++) {
					var item = formItems[j];
					switch($(item).data('type')) {
					case 'TEXT':
						putField($(item).attr('name'), $(item).val());
						break;
					case 'PICTURE':
						putImage($(item).attr('name'), "http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/' />"+$(item).val());
						break;
					}
				}
				break;
			}
			console.log("step "+i+" : " + ret);
		}
	});
	</script>
  </head>
  <body>
<div class="page">
<SCRIPT language="JavaScript">

//_GetBasePath()가 작동하지 않으면, OnStart()함수의 BasePath=_GetBasePath();를 지우고, 이 예제 파일이 있는 곳을 지정해 준다.
</SCRIPT>
<form name="HwpControl">
    <OBJECT id=HwpCtrl style="lef: 0px; top: 0px;" height="600" width="100%" align="center"
	classid=CLSID:BD9C32DE-3155-4691-8972-097D53B10052>
	<PARAM NAME="_Version" VALUE="65536">
	<PARAM NAME="_ExtentX" VALUE="21167">
	<PARAM NAME="_ExtentY" VALUE="15875">
	<PARAM NAME="_StockProps" VALUE="0">
	<PARAM NAME="FILENAME" VALUE="${hwpTemplateName}"></OBJECT>
</form>
<form id="userData">
	<c:forEach var="userItem" items="${varItems }">
		<input name="${userItem.name }" value="${userItem.value }" data-type="${userItem.type }"/>
	</c:forEach>
</form>
</div>
  </body>
</html>