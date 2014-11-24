<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupMngCode.jsp
  * @Description : 관리비 시설 코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.29  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
 **/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamPopupMngCodeModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupMngCodeModule() {}

GamPopupMngCodeModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupMngCodeModule.prototype.loadComplete = function() {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/selectMngCodeList.do',
		dataType : "json",
		colModel : [
					{display:'관리비 시설 코드', 	name:'mngFeeFcltyCd',		width:100, 		sortable:false,		align:'center'},
					{display:'관리비 시설 명',		name:'mngFeeFcltyNm',		width:160, 		sortable:false,		align:'left'},
					{display:'관리비 시설 구분',	name:'mngFeeFcltySeNm',		width:120, 		sortable:false,		align:'left'},
					{display:'관리비 업무 구분',	name:'mngFeeJobSeNm',		width:120, 		sortable:false,		align:'left'}
					],
		height: "300"
	});

	this.$("#mainGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupMngCodeModule.prototype.onButtonClick = function(buttonId) {

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
GamPopupMngCodeModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupMngCodeModule.prototype.loadData = function() {

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : processOk
 * @DESCRIPTION   : OK PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupMngCodeModule.prototype.processOk = function() {

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
GamPopupMngCodeModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupMngCodeModule();

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
						<th>시설 코드</th>
						<td><input id="sMngFeeFcltyCd" type="text" style="width: 80px;" title="시설 코드" maxlength="2" /></td>
						<th>시설 명</th>
						<td><input id="sMngFeeFcltyNm" type="text" style="width: 80px;" title="시설 명" maxlength="20" /></td>
						<th>업무 구분</th>
						<td>
							<select id="sMngFeeJobSe">
								<option value="" selected>전체</option>
								<option value="M">마린센터</option>
								<option value="E">전기시설</option>
							</select>
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
				<button data-cmd="btnOk">선택</button>
				<button data-cmd="cancel">취소</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
