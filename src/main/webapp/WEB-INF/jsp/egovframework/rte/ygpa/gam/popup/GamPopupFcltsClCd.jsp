<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsClCd.jsp
  * @Description : 시설물 분류 코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.05  김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamPopupFcltsClCdModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupFcltsClCdModule() {}

GamPopupFcltsClCdModule.prototype = new EmdPopupModule(640, 480);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.loadComplete = function(params) {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/selectFcltsClCdInfoList.do',
		dataType: "json",
		colModel : [
					{display:"단계",					name:"depthSort",		width:50,		sortable:true,	align:"center"},
					{display:"시설물 분류 코드",		name:"fcltsClCd",		width:120,		sortable:true,	align:"center"},
					{display:"시설물 분류 코드 명",		name:"fcltsClCdNm",		width:200,		sortable:true,	align:"left"},
					{display:"시설물 업무 구분",		name:"fcltsJobSeNm",	width:120,		sortable:true,	align:"left"},
					{display:"LEAF 여부",				name:"leafYn",			width:80,		sortable:true,	align:"center"}
					],
		height: "320"
	});

	this.$("#mainGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	if (params != null) {
		this.$('#sFcltsJobSe').val(params.sFcltsJobSe);
		this.$('#sDepthSort').val(params.sDepthSort);
		this.$('#sLeafYn').val("");
		var searchOpt=this.makeFormArgs('#searchForm');
		this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
	} else {
		this.$('#sLeafYn').val("Y");
	}

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupFcltsClCdModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnOk':
	    	this.processOk();
			break;
	    case 'btnCancel':
	    	this.processCancel();
			break;
	}

};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.loadData = function() {

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.selectData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (gridRowCount == 0) {
		alert('해당 조건의 자료가 존재하지 않습니다!');
	}

};

<%
/**
 * @FUNCTION NAME : processOk
 * @DESCRIPTION   : OK PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.processOk = function() {

	var row = this.$("#mainGrid").selectedRows();
	if (row.length>0) {
		this.closeDialog("ok", row[0]);
	} else {
		alert("항목을 선택하십시요.");
	}

};

<%
/**
 * @FUNCTION NAME : processCancel
 * @DESCRIPTION   : CANCEL PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupFcltsClCdModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 11. SEARCH AREA (조회조건 영역) -->
		<form id="searchForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>시설물 분류 명</th>
						<td>
							<input id="sFcltsClCdNm" type="text" size="20" maxlength="60"/>
						</td>
						<th>업무 구분</th>
						<td>
							<select id="sFcltsJobSe">
								<option value="" selected>전체시설</option>
								<option value="A">건축시설</option>
								<option value="C">토목시설</option>
								<option value="M">기계시설</option>
								<option value="E">전기시설</option>
								<option value="I">정보통신시설</option>
							</select>
						</td>
						<th>단계</th>
						<td>
							<input id="sLeafYn" type="hidden"/>
							<input id="sDepthSort" type="text" size="1" maxlength="1"/>
						</td>
						<td>
							<button class="buttonSearch">조회</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<!-- 2. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<table id="mainGrid" style="display: none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<button id="btnOk">선택</button>
				<button id="btnCancel">취소</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
