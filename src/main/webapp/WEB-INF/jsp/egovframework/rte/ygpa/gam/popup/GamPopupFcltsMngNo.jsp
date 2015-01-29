<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsMngNo.jsp
  * @Description : 시설물 관리번호 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.11  김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.11
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
 * @FUNCTION NAME : GamPopupFcltsMngNoModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupFcltsMngNoModule() {}

GamPopupFcltsMngNoModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsMngNoModule.prototype.loadComplete = function(params) {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/selectFcltsMngNoInfoList.do',
		dataType : "json",
		colModel : [
					{display:"항만시설명",		name:"prtFcltyNm",				width:150,		sortable:true,		align:"left"},
					{display:"항구분",			name:"gisAssetsPrtAtCodeNm",	width:63,		sortable:true,		align:"center"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNm",			width:140,		sortable:true,		align:"left"},
					{display:"시설구분",		name:"prtFcltySeNm",			width:90,		sortable:true,		align:"center"},
					{display:"시설분류",		name:"gisPrtFcltyCdNm",			width:90,		sortable:true,		align:"left"}
					],
			height: "315"
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	if (params != null) {
		var sFcltsJobSe = params['fcltsJobSe'];
		if (sFcltsJobSe != "") {
			this.$('#sFcltsJobSe').val(sFcltsJobSe);
			this.$('#sFcltsJobSe').disable();
		}
		var searchOpt=this.makeFormArgs('#searchForm');
		this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
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
GamPopupFcltsMngNoModule.prototype.onButtonClick = function(buttonId) {


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
GamPopupFcltsMngNoModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsMngNoModule.prototype.loadData = function() {

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
GamPopupFcltsMngNoModule.prototype.selectData = function() {

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
GamPopupFcltsMngNoModule.prototype.processOk = function() {

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
GamPopupFcltsMngNoModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupFcltsMngNoModule();

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
						<th>시설구분</th>
                        <td>
							<select id="sFcltsJobSe">
								<option value="" selected>전체</option>
								<option value="E">전기시설</option>
								<option value="M">기계시설</option>
								<option value="C">토목시설</option>
								<option value="A">건축시설</option>
								<option value="I">정보통신시설</option>
							</select>
                        </td>
						<th>시설명</th>
                        <td>
                        	<input id="sPrtFcltyNm" type="text" size="40" maxlength="80"/>
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
