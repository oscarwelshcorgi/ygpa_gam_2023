<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupQcItemCd.jsp
  * @Description : 점검 항목 코드 팝업 (Prototype)
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
 * @FUNCTION NAME : GamPopupQcItemCdModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupQcItemCdModule() {}

GamPopupQcItemCdModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupQcItemCdModule.prototype.loadComplete = function(params) {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/gamSelectQcItemCdList.do',
		dataType : "json",
		colModel : [
					{display:"단계",				name:"depthSort",		width:50,		sortable:true,	align:"center"},
					{display:"점검 항목 코드",		name:"qcItemCd",		width:100,		sortable:true,	align:"center"},
					{display:"점검 항목 명",		name:"qcItemNm",		width:180,		sortable:true,	align:"left"},
					{display:"시설물 업무 구분",	name:"fcltsJobSeNm",	width:110,		sortable:true,	align:"left"},
					{display:"사용 여부",			name:"useYn",			width:77,		sortable:true,	align:"center"}
					],
		height: "300"
	});

	this.$("#mainGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	if (params != null) {
       	this.$('#sFcltsJobSe').val(params.sFcltsJobSe);
       	this.$('#sDepthSort').val(params.sDepthSort);
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
GamPopupQcItemCdModule.prototype.onButtonClick = function(buttonId) {

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
GamPopupQcItemCdModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupQcItemCdModule.prototype.loadData = function() {

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
GamPopupQcItemCdModule.prototype.processOk = function() {

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
GamPopupQcItemCdModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupQcItemCdModule();

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
						<th>점검 항목 명</th>
						<td>
							<input id="sQcItemNm" type="text" size="20" maxlength="100"/>
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
