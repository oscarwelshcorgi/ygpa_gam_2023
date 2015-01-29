<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupFuelCode.jsp
  * @Description : 연료 코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.29  heroine          최초 생성
  *
  * author ACEWOLF
  * since 2014.11.28
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
 **/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamPopupFuelCodeModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupFuelCodeModule() {}

GamPopupFuelCodeModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupFuelCodeModule.prototype.loadComplete = function() {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/selectFuelCodeList.do',
		dataType : "json",
		colModel : [
					{display:'연료 코드', 	name:'fuelCd',		width:120, 		sortable:false,		align:'center'},
					{display:'연료 명',		name:'fuelNm',		width:200, 		sortable:false,		align:'left'},
					{display:'관리 년도',	name:'mngYear',		width:120, 		sortable:false,		align:'center'}
					],
		height: "315"
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
GamPopupFuelCodeModule.prototype.onButtonClick = function(buttonId) {

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
GamPopupFuelCodeModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupFuelCodeModule.prototype.loadData = function() {

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
GamPopupFuelCodeModule.prototype.processOk = function() {

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
GamPopupFuelCodeModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupFuelCodeModule();

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
						<th>관리 년도</th>
						<td>
							<select id="sMngYear">
								<c:forEach items="${yearsList}" var="yearListItem">
									<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
								</c:forEach>
							</select>
						</td>
						<th>연료 코드</th>
						<td><input id="sFuelCd" type="text" style="width: 80px;" title="연료 코드" maxlength="4" /></td>
						<th>연료 명</th>
						<td><input id="sFuelNm" type="text" style="width: 80px;" title="연료 명" maxlength="20" /></td>
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
