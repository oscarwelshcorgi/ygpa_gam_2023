<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltsMngGroupMng.jsp
  * @Description : 시설물 관리 그룹 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.10  	 김종민          화면단 최초 생성
  *
  * author 김종민
  * since 2014.12.10
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
 * @FUNCTION NAME : GamFcltsMngGroupMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsMngGroupMngModule() {}

GamFcltsMngGroupMngModule.prototype = new EmdModule(900, 600);

GamFcltsMngGroupMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectFcltsMngGroupMng.do',
		dataType : "json",
		colModel : [
					{display:"관리 그룹 번호",	name:"fcltsMngGroupNo",   	width:100,		sortable:true, align:"center"},
					{display:"관리 그룹 명",	name:"fcltsMngGroupNm", 	width:150,		sortable:true, align:"left"},
					{display:"시설물 내용",		name:"fcltsCn", 			width:150,		sortable:true, align:"left"},
					{display:"시설물 구조",		name:"fcltsStrct", 			width:150,		sortable:true, align:"left"},
					{display:"준공 일자",		name:"bldDt", 				width:80,		sortable:true, align:"center"},
					{display:"건축 갯수",		name:"archFcltsCnt", 		width:80,		sortable:true, align:"right"},
					{display:"토목 갯수",		name:"cvlEngFcltsCnt", 		width:80,		sortable:true, align:"right"},
					{display:"기계 갯수",		name:"mechFcltsCnt", 		width:80,		sortable:true, align:"right"},
					{display:"전기 갯수",		name:"elctyFcltsCnt", 		width:80,		sortable:true, align:"right"},
					{display:"정보통신 갯수",	name:"infoCommFcltsCnt", 	width:100,		sortable:true, align:"right"},
					{display:"기타 갯수",		name:"etcFcltsCnt", 		width:80,		sortable:true, align:"right"},
					{display:"위치",			name:"loc",					width:200,		sortable:true, align:"left"},
					{display:"소유자",			name:"owner", 				width:100,		sortable:true, align:"left"},
					{display:"공사 시작 일자",	name:"cnstBeginDt", 		width:100,		sortable:true, align:"center"},
					{display:"공사 종료 일자",	name:"cnstEndDt", 			width:100,		sortable:true, align:"center"},
					{display:"시공자",			name:"cnstrtr", 			width:150,		sortable:true, align:"left"},
					{display:"시공 금액",		name:"cnstrctAmt", 			width:100,		sortable:true, align:"right"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumArchFcltsCnt').val(data.sumArchFcltsCnt);
			module.$('#sumCvlEngFcltsCnt').val(data.sumCvlEngFcltsCnt);
			module.$('#sumMechFcltsCnt').val(data.sumMechFcltsCnt);
			module.$('#sumElctyFcltsCnt').val(data.sumElctyFcltsCnt);
			module.$('#sumInfoCommFcltsCnt').val(data.sumInfoCommFcltsCnt);
			module.$('#sumEtcFcltsCnt').val(data.sumEtcFcltsCnt);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltsMngGroupMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnRemove':
			this.deleteData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
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
GamFcltsMngGroupMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
	this._mainKeyValue = '';
	this.loadData();
	this.enableListButtonItem();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : refreshData
 * @DESCRIPTION   : DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
 GamFcltsMngGroupMngModule.prototype.refreshData = function() {

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltsMngGroupMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/code/gamSelectFcltsMngGroupMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.selectData = function() {

	if (this._mode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		return;
	} else if (this._mode != 'insert' && this._mode != 'modify') {
		return;
	}
	var mainKeyValue = this._mainKeyValue;
	if (mainKeyValue == "") {
		return;
	}
	this._mode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.addData = function() {

	this.$('#fcltsMngGroupNo').val("");
	this.$('#fcltsMngGroupNm').val("");
	this.$('#loc').val("");
	this.$('#owner').val("");
	this.$('#bldDt').val("");
	this.$('#cnstBeginDt').val("");
	this.$('#cnstEndDt').val("");
	this.$('#cnstrtr').val("");
	this.$('#cnstrctAmt').val("0");
	this.$('#fcltsCn').val("");
	this.$('#fcltsStrct').val("");
	this.$('#archFcltsCnt').val("0");
	this.$('#cvlEngFcltsCnt').val("0");
	this.$('#mechFcltsCnt').val("0");
	this.$('#elctyFcltsCnt').val("0");
	this.$('#infoCommFcltsCnt').val("0");
	this.$('#etcFcltsCnt').val("0");
	this.$('#rm').val("");
	this.enableDetailInputItem();
	this.$('#fcltsMngGroupNo').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var fcltsMngGroupNm = this.$('#fcltsMngGroupNm').val();
	var owner = this.$('#owner').val();
	var bldDt = this.$('#bldDt').val();
	var fcltsCn = this.$('#fcltsCn').val();
	var fcltsStrct = this.$('#fcltsStrct').val();
	var cnstBeginDt = this.$('#cnstBeginDt').val();
	var cnstEndDt = this.$('#cnstEndDt').val();
	var cnstrctAmt = Number(this.$('#cnstrctAmt').val().replace(/,/gi, ""));
	var archFcltsCnt = Number(this.$('#archFcltsCnt').val().replace(/,/gi, ""));
	var cvlEngFcltsCnt = Number(this.$('#cvlEngFcltsCnt').val().replace(/,/gi, ""));
	var mechFcltsCnt = Number(this.$('#mechFcltsCnt').val().replace(/,/gi, ""));
	var elctyFcltsCnt = Number(this.$('#elctyFcltsCnt').val().replace(/,/gi, ""));
	var infoCommFcltsCnt = Number(this.$('#infoCommFcltsCnt').val().replace(/,/gi, ""));
	var etcFcltsCnt = Number(this.$('#etcFcltsCnt').val().replace(/,/gi, ""));
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹 번호가 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
		return;
	}
	if (fcltsMngGroupNm == "") {
		alert('시설물 관리 그룹 명이 부정확합니다.');
		this.$("#fcltsMngGroupNm").focus();
		return;
	}
	if (owner == "") {
		alert('소유자가 부정확합니다.');
		this.$("#owner").focus();
		return;
	}
	if (fcltsCn == "") {
		alert('시설물 내용이 부정확합니다.');
		this.$("#fcltsCn").focus();
		return;
	}
	if (fcltsStrct == "") {
		alert('시설물 구조가 부정확합니다.');
		this.$("#fcltsStrct").focus();
		return;
	}
	if (bldDt > "2999-12-31" || bldDt <= "19000101") {
		alert('준공 일자가 부정확합니다.');
		this.$("#bldDt").focus();
		return;
	}
	if (cnstBeginDt != "" || cnstEndDt != "") {
		if (cnstBeginDt > "2999-12-31" || cnstBeginDt <= "19000101") {
			alert('공사 시작 일자가 부정확합니다.');
			this.$("#cnstBeginDt").focus();
			return;
		}
		if (cnstEndDt > "2999-12-31" || cnstEndDt <= "19000101") {
			alert('공사 종료 일자가 부정확합니다.');
			this.$("#cnstEndDt").focus();
			return;
		}
		if (cnstBeginDt > cnstEndDt) {
			alert('공사 시작 일자가 공사 종료 일자보다 큽니다.');
			this.$("#cnstBeginDt").focus();
			return;
		}
	}
	if (cnstrctAmt > 9999999999999999 || cnstrctAmt < 0) {
		alert('시공 금액이 부정확합니다.');
		this.$("#cnstrctAmt").focus();
		return;
	}
	if (archFcltsCnt > 99999 || archFcltsCnt < 0) {
		alert('건축 시설물 갯수가 부정확합니다.');
		this.$("#archFcltsCnt").focus();
		return;
	}
	if (cvlEngFcltsCnt > 99999 || cvlEngFcltsCnt < 0) {
		alert('토목 시설물 갯수가 부정확합니다.');
		this.$("#cvlEngFcltsCnt").focus();
		return;
	}
	if (mechFcltsCnt > 99999 || mechFcltsCnt < 0) {
		alert('기계 시설물 갯수가 부정확합니다.');
		this.$("#mechFcltsCnt").focus();
		return;
	}
	if (elctyFcltsCnt > 99999 || elctyFcltsCnt < 0) {
		alert('전기 시설물 갯수가 부정확합니다.');
		this.$("#elctyFcltsCnt").focus();
		return;
	}
	if (infoCommFcltsCnt > 99999 || infoCommFcltsCnt < 0) {
		alert('정보통신 시설물 갯수가 부정확합니다.');
		this.$("#infoCommFcltsCnt").focus();
		return;
	}
	if (etcFcltsCnt > 99999 || etcFcltsCnt < 0) {
		alert('기타 시설물 갯수가 부정확합니다.');
		this.$("#etcFcltsCnt").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = fcltsMngGroupNo;
		this.doAction('/code/gamInsertFcltsMngGroupMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/code/gamUpdateFcltsMngGroupMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.deleteData = function() {

	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹 번호가 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamDeleteFcltsMngGroupMng.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mode = 'query';
				module._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/code/gamExcelDownloadFcltsMngGroupMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : enableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#fcltsMngGroupNo').enable();
		this.$('#fcltsMngGroupNm').enable();
		this.$('#loc').enable();
		this.$('#owner').enable();
		this.$('#bldDt').enable();
		this.$('#cnstBeginDt').enable();
		this.$('#cnstEndDt').enable();
		this.$('#cnstrtr').enable();
		this.$('#cnstrctAmt').enable();
		this.$('#fcltsCn').enable();
		this.$('#fcltsStrct').enable();
		this.$('#archFcltsCnt').enable();
		this.$('#cvlEngFcltsCnt').enable();
		this.$('#mechFcltsCnt').enable();
		this.$('#elctyFcltsCnt').enable();
		this.$('#infoCommFcltsCnt').enable();
		this.$('#etcFcltsCnt').enable();
		this.$('#rm').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#fcltsMngGroupNo').disable();
			this.$('#fcltsMngGroupNm').enable();
			this.$('#loc').enable();
			this.$('#owner').enable();
			this.$('#bldDt').enable();
			this.$('#cnstBeginDt').enable();
			this.$('#cnstEndDt').enable();
			this.$('#cnstrtr').enable();
			this.$('#cnstrctAmt').enable();
			this.$('#fcltsCn').enable();
			this.$('#fcltsStrct').enable();
			this.$('#archFcltsCnt').enable();
			this.$('#cvlEngFcltsCnt').enable();
			this.$('#mechFcltsCnt').enable();
			this.$('#elctyFcltsCnt').enable();
			this.$('#infoCommFcltsCnt').enable();
			this.$('#etcFcltsCnt').enable();
			this.$('#rm').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#fcltsMngGroupNo').disable();
			this.$('#fcltsMngGroupNm').disable();
			this.$('#loc').disable();
			this.$('#owner').disable();
			this.$('#bldDt').disable();
			this.$('#cnstBeginDt').disable();
			this.$('#cnstEndDt').disable();
			this.$('#cnstrtr').disable();
			this.$('#cnstrctAmt').disable();
			this.$('#fcltsCn').disable();
			this.$('#fcltsStrct').disable();
			this.$('#archFcltsCnt').disable();
			this.$('#cvlEngFcltsCnt').disable();
			this.$('#mechFcltsCnt').disable();
			this.$('#elctyFcltsCnt').disable();
			this.$('#infoCommFcltsCnt').disable();
			this.$('#etcFcltsCnt').disable();
			this.$('#rm').disable();
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsMngGroupNo').disable();
	this.$('#fcltsMngGroupNm').disable();
	this.$('#loc').disable();
	this.$('#owner').disable();
	this.$('#bldDt').disable();
	this.$('#cnstBeginDt').disable();
	this.$('#cnstEndDt').disable();
	this.$('#cnstrtr').disable();
	this.$('#cnstrctAmt').disable();
	this.$('#fcltsCn').disable();
	this.$('#fcltsStrct').disable();
	this.$('#archFcltsCnt').disable();
	this.$('#cvlEngFcltsCnt').disable();
	this.$('#mechFcltsCnt').disable();
	this.$('#elctyFcltsCnt').disable();
	this.$('#infoCommFcltsCnt').disable();
	this.$('#etcFcltsCnt').disable();
	this.$('#rm').disable();
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
GamFcltsMngGroupMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mode=="insert") {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			break;
	}

};

var module_instance = new GamFcltsMngGroupMngModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>시설물 관리 그룹 번호</th>
							<td>
								<input id="sFcltsMngGroupNo" type="text" size="15" maxlength="14"/>
							</td>
							<th>시설물 관리 그룹 명</th>
							<td>
								<input id="sFcltsMngGroupNm" type="text" size="30" maxlength="80"/>
							</td>
							<td>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">시설물 관리 그룹 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리 그룹 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">총건축갯수</th>
								<td><input type="text" size="13" id="sumArchFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총토목갯수</th>
								<td><input type="text" size="13" id="sumCvlEngFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총기계갯수</th>
								<td><input type="text" size="13" id="sumMechFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총전기갯수</th>
								<td><input type="text" size="13" id="sumElctyFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총통신갯수</th>
								<td><input type="text" size="13" id="suminfoCommFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총기타갯수</th>
								<td><input type="text" size="13" id="sumEtcFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<th style="width=10%; height=20; text-align:center;">자료수</th>
							<td>
								<input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled" />
							</td>
							<td style="text-align:right;">
								<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
								<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
								<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
					</table>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel">
							<tr>
								<th style="width:15%; height:18;">시설물 관리 그룹 번호</th>
								<td><input type="text" id="fcltsMngGroupNo" size="46" maxlength="14"/></td>
								<th style="width:15%; height:18;">시설물 관리 그룹 명</th>
								<td><input type="text" id="fcltsMngGroupNm" size="46" maxlength="80"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">소유자 / 준공일자</th>
								<td>
									<input type="text" id="owner" size="22" maxlength="60"/>
									<input type="text" id="bldDt" size="22" class="emdcal"/>
								</td>
								<th style="width:15%; height:18;"></th>
								<td><input type="text" id="loc" size="46" maxlength="150"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">시설물 내용</th>
								<td colspan="3"><input type="text" id="fcltsCn" size="90" maxlength="1000"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">시설물 구조</th>
								<td colspan="3"><input type="text" id="fcltsStrct" size="90" maxlength="1000"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">건축 갯수 / 토목 갯수</th>
								<td>
									<input type="text" class="ygpaNumber" id="archFcltsCnt" size="22" maxlength="6"/>
									<input type="text" class="ygpaNumber" id="cvlEngFcltsCnt" size="22" maxlength="6"/>
								</td>
								<th style="width:15%; height:18;">기계 갯수 / 전기 갯수</th>
								<td>
									<input type="text" class="ygpaNumber" id="mechFcltsCnt" size="22" maxlength="6"/>
									<input type="text" class="ygpaNumber" id="elctyFcltsCnt" size="22" maxlength="6"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">정보통신 / 기타 갯수</th>
								<td>
									<input type="text" class="ygpaNumber" id="infoCommFcltsCnt" size="22" maxlength="6"/>
									<input type="text" class="ygpaNumber" id="etcFcltsCnt" size="22" maxlength="6"/>
								</td>
								<th style="width:15%; height:18;">공사 기간</th>
								<td>
									<input type="text" size="18" id="cnstBeginDt" class="emdcal"/> ~
									<input type="text" size="18" id="cnstEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">시공자</th>
								<td><input type="text" id="cnstrtr" size="46" maxlength="60"/></td>
								<th style="width:15%; height:18;">시공금액</th>
								<td><input type="text" class="ygpaNumber" id="cnstrctAmt" size="46" maxlength="20"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">비고</th>
								<td colspan="3"><input type="text" id="rm" size="119" maxlength="1000"/></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
								<button id="btnSave" class="buttonSave">저장</button>
								<button id="btnRemove" class="buttonDelete">삭제</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
