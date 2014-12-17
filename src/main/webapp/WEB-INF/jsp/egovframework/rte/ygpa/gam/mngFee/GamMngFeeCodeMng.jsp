<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamMngFeeCodeMng.jsp
 * @Description : 관리비 시설코드 관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  Lee          최초 생성
 *
 * author Lee
 * since 2014.09.22
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
 * @FUNCTION NAME : GamMngFeeGubunMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamMngFeeCodeMngModule() {}

GamMngFeeCodeMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectMngFeeCodeMng.do',
		dataType : 'json',
		colModel : [
					{display:'시설 코드', 	name:'mngFeeFcltyCd',		width:80, 		sortable:false,		align:'center'},
					{display:'시설 명', 	name:'mngFeeFcltyNm',		width:180, 		sortable:false,		align:'left'},
					{display:'시설 구분', 	name:'mngFeeFcltySeNm',		width:110, 		sortable:false,		align:'left'},
					{display:'업무 구분', 	name:'mngFeeJobSeNm',		width:110, 		sortable:false,		align:'left'},
					{display:'등록자', 		name:'regUsr',				width:100, 		sortable:false,		align:'center'},
					{display:'등록일시', 	name:'registDt',			width:150, 		sortable:false,		align:'center'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.mngFeeJobSe;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.mngFeeJobSe;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#mngFeeJobSe').on('change',{module:this}, function(event){
		event.data.module.getNewMngFeeFcltyCd();
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
GamMngFeeCodeMngModule.prototype.onButtonClick = function(buttonId) {

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
GamMngFeeCodeMngModule.prototype.onSubmit = function() {

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
GamMngFeeCodeMngModule.prototype.loadData = function() {

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
 GamMngFeeCodeMngModule.prototype.refreshData = function() {

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
GamMngFeeCodeMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/mngFee/gamSelectMngFeeCodeMngPk.do', searchVO, function(module, result){
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
GamMngFeeCodeMngModule.prototype.selectData = function() {

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
GamMngFeeCodeMngModule.prototype.addData = function() {

	var mngFeeJobSe = this.$('#sMngFeeJobSe').val();
	var mngFeeJobSeNm = "";
	if (mngFeeJobSe == "M") {
		mngFeeJobSeNm = "[M]:마린센터";
	} else if (mngFeeJobSe == "E") {
		mngFeeJobSeNm = "[E]:전기시설";
	}
	this.$('#mngFeeFcltyCd').val("");
	this.$('#mngFeeJobSe').val(mngFeeJobSe);
	this.$('#mngFeeJobSeNm').val(mngFeeJobSeNm);
	this.getNewMngFeeFcltyCd();
	this.$('#mngFeeFcltySe').val("");
	this.$('#mngFeeFcltyNm').val("");
	this.enableDetailInputItem();
	this.$('#mngFeeJobSe').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltySe = this.$('#mngFeeFcltySe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	var mngFeeFcltyNm = this.$('#mngFeeFcltyNm').val();
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	if (mngFeeFcltySe == "" || mngFeeFcltySe.length != 2) {
		alert('시설 구분이 부정확합니다.');
		this.$("#mngFeeFcltySe").focus();
		return;
	}
	if (mngFeeFcltyCd == "" || mngFeeFcltyCd.length != 4) {
		alert('시설 코드가 부정확합니다.');
		this.$("#mngFeeFcltyCd").focus();
		return;
	}
	if (mngFeeFcltyNm == "") {
		alert('시설 명이 부정확합니다.');
		this.$("#mngFeeFcltyNm").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = mngFeeFcltyCd + mngFeeJobSe;
		this.doAction('/mngFee/gamInsertMngFeeCodeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateMngFeeCodeMng.do', inputVO, function(module, result) {
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
GamMngFeeCodeMngModule.prototype.deleteData = function() {

	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	if (mngFeeJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	if (mngFeeFcltyCd == "") {
		alert('시설 코드가 부정확합니다.');
		this.$("#mngFeeFcltyCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteMngFeeCodeMng.do', deleteVO, function(module, result) {
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
GamMngFeeCodeMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelMngFeeCodeMng.do');

};

<%
/**
 * @FUNCTION NAME : getNewMngFeeFcltyCd
 * @DESCRIPTION   : 새로운 시설 코드를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.getNewMngFeeFcltyCd = function() {

	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamSelectMngFeeCodeMngMaxFcltyCd.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#mngFeeFcltyCd').val(result.sMaxFcltyCd);
		}
	});

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.enableListButtonItem = function() {

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
GamMngFeeCodeMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#mngFeeJobSe').enable();
		this.$('#mngFeeFcltyCd').enable();
		this.$('#mngFeeFcltySe').enable();
		this.$('#mngFeeFcltyNm').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#mngFeeJobSe').disable();
			this.$('#mngFeeFcltyCd').disable();
			this.$('#mngFeeFcltySe').enable();
			this.$('#mngFeeFcltyNm').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#mngFeeJobSe').disable();
			this.$('#mngFeeFcltyCd').disable();
			this.$('#mngFeeFcltySe').disable();
			this.$('#mngFeeFcltyNm').disable();
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
GamMngFeeCodeMngModule.prototype.disableDetailInputItem = function() {

	this.$('#mngFeeJobSe').disable();
	this.$('#mngFeeFcltyCd').disable();
	this.$('#mngFeeFcltySe').disable();
	this.$('#mngFeeFcltyNm').disable();
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
GamMngFeeCodeMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamMngFeeCodeMngModule();

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
							<th>시설 코드</th>
							<td>
								<input type="text" size="5" id="sMngFeeFcltyCd">
							</td>
							<th>시설 명</th>
							<td>
								<input type="text" size="15" id="sMngFeeFcltyNm">
							</td>
							<th>업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
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
		</div>
	</div>
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">관리비 시설코드</a></li>
				<li><a href="#detailTab" class="emdTab">관리비 시설코드 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:20%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:20%; height:25;">업무 구분</th>
								<td>
									<select id="mngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
								<th style="width:20%; height:25;">시설 구분</th>
								<td>
									<select id="mngFeeFcltySe">
										<option value="">선택</option>
										<c:forEach items="${mngFeeFcltySeList}" var="mngFeeFcltySeListListItem">
											<option value="${mngFeeFcltySeListListItem.mngFeeFcltySe }">${mngFeeFcltySeListListItem.mngFeeFcltySeNm }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:25;">시설 코드</th>
								<td><input type="text" size="35" id="mngFeeFcltyCd" maxlength="4"/></td>
								<th style="width:20%; height:25;">시설 명</th>
								<td><input type="text" size="35" id="mngFeeFcltyNm" maxlength="20"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:25;">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
								<th style="width:20%; height:25;">등록일시</th>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th style="width:20%; height:25;">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
								<th style="width:20%; height:25;">수정일시</th>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
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
