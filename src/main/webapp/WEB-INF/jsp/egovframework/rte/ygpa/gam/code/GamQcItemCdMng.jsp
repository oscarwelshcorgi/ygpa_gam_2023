<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamQcItemCdMng.jsp
 * @Description : 점검 항목 코드 관리 화면
 * @Modification Information
 *
 *   수정일         수정자               수정내용
 *  ----------     ---------    ---------------------------
 *  2014.12.10  	ACEWOLF          화면단 최초 생성
 *
 * author ACEWOLF
 * since 2014.12.20
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
 * @FUNCTION NAME : GamQcItemCdMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamQcItemCdMngModule() {}

GamQcItemCdMngModule.prototype = new EmdModule(800, 600);

GamQcItemCdMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectQcItemCdMng.do',
		dataType : "json",
		colModel : [
					{display:"단계",				name:"depthSort",		width:50,		sortable:true,	align:"center"},
					{display:"점검 항목 코드",		name:"qcItemCd",		width:100,		sortable:true,	align:"center"},
					{display:"점검 항목 명",		name:"qcItemNm",		width:150,		sortable:true,	align:"left"},
					{display:"시설물 업무 구분",	name:"fcltsJobSeNm",	width:100,		sortable:true,	align:"left"},
					{display:"점검 항목 상위",		name:"qcItemUpperNm",	width:150,		sortable:true,	align:"left"},
					{display:"사용 여부",			name:"useYn",			width:80,		sortable:true,	align:"center"},
					{display:"점검 항목 상세",		name:"qcItemDtls",		width:200,		sortable:true,	align:"left"}
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
		module._mainKeyValue = row.qcItemCd;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.qcItemCd;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#fcltsJobSe').on('change',{module:this}, function(event){
		event.data.module.setFcltsJobSeNm();
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
GamQcItemCdMngModule.prototype.onButtonClick = function(buttonId) {

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
GamQcItemCdMngModule.prototype.onSubmit = function() {

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
GamQcItemCdMngModule.prototype.loadData = function() {

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
GamQcItemCdMngModule.prototype.refreshData = function() {

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
GamQcItemCdMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/code/gamSelectQcItemCdMngPk.do', searchVO, function(module, result){
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
GamQcItemCdMngModule.prototype.selectData = function() {

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
GamQcItemCdMngModule.prototype.addData = function() {

	this.$('#fcltsJobSe').val("");
	this.$('#qcItemCd').val("");
	this.$('#qcItemNm').val("");
	this.$('#qcItemDtls').val("");
	this.$('#depthSort').val("");
	this.$('#qcItemUpperCd').val("");
	this.$('#useYn').val("");
	this.enableDetailInputItem();
	this.$('#fcltsJobSe').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var qcItemCd = this.$('#qcItemCd').val();
	var qcItemNm = this.$('#qcItemNm').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var qcItemUpperCd = this.$('#qcItemUpperCd').val();
	var useYn = this.$('#useYn').val();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		alert('시설물 업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (qcItemCd == "") {
		alert('점검 항목 코드가 부정확합니다.');
		this.$("#qcItemCd").focus();
		return;
	}
	if (qcItemNm == "") {
		alert('점검 항목 명이 부정확합니다.');
		this.$("#qcItemNm").focus();
		return;
	}
	if (depthSort > 5 || depthSort < 0) {
		alert('단계가 부정확합니다.');
		this.$("#depthSort").focus();
		return;
	}
	if (depthSort > 0) {
		if (qcItemUpperCd == "") {
			alert('점검 항목 상위가 부정확합니다.');
			this.$("#qcItemUpperCd").focus();
			return;
		}
	}
	if (useYn != "N" && useYn != "Y") {
		alert('사용 여부가 부정확합니다.');
		this.$("#useYn").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = qcItemCd;
		this.doAction('/code/gamInsertQcItemCdMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/code/gamUpdateQcItemCdMng.do', inputVO, function(module, result) {
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
GamQcItemCdMngModule.prototype.deleteData = function() {

	var qcItemCd = this.$('#qcItemCd').val();
	if (qcItemCd == "") {
		alert('점검 항목 코드가 부정확합니다.');
		this.$("#qcItemCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamDeleteQcItemCdMng.do', deleteVO, function(module, result) {
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
GamQcItemCdMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/code/gamExcelDownloadQcItemCdMng.do');

};

<%
/**
 * @FUNCTION NAME : setFcltsJobSeNm
 * @DESCRIPTION   : 시설물 업무 구분을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.setFcltsJobSeNm = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsJobSeNm = "";
	if (fcltsJobSe == "A") {
		mainMngFeeJobSeNm = "건축시설";
	} else if (fcltsJobSe == "C") {
		mainMngFeeJobSeNm = "토목시설";
	} else if (fcltsJobSe == "M") {
		mainMngFeeJobSeNm = "기계시설";
	} else if (fcltsJobSe == "E") {
		mainMngFeeJobSeNm = "전기시설";
	} else if (fcltsJobSe == "I") {
		mainMngFeeJobSeNm = "정보통신시설";
	}
	this.$('#fcltsJobSeNm').val(fcltsJobSeNm);

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.enableListButtonItem = function() {

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
GamQcItemCdMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#fcltsJobSe').enable();
		this.$('#qcItemCd').enable();
		this.$('#qcItemNm').enable();
		this.$('#qcItemDtls').enable();
		this.$('#depthSort').enable();
		this.$('#qcItemUpperCd').enable();
		this.$('#useYn').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#fcltsJobSe').disable();
			this.$('#qcItemCd').enable();
			this.$('#qcItemNm').enable();
			this.$('#qcItemDtls').enable();
			this.$('#depthSort').enable();
			this.$('#qcItemUpperCd').enable();
			this.$('#useYn').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#fcltsJobSe').disable();
			this.$('#qcItemCd').disable();
			this.$('#qcItemNm').disable();
			this.$('#qcItemDtls').disable();
			this.$('#depthSort').disable();
			this.$('#qcItemUpperCd').disable();
			this.$('#useYn').disable();
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
GamQcItemCdMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsJobSe').disable();
	this.$('#qcItemCd').disable();
	this.$('#qcItemNm').disable();
	this.$('#qcItemDtls').disable();
	this.$('#depthSort').disable();
	this.$('#qcItemUpperCd').disable();
	this.$('#useYn').disable();
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
 GamQcItemCdMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamQcItemCdMngModule();

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
							<th>점검 항목</th>
							<td>
								<input id="sQcItemCd" type="text" size="15" maxlength="14"/>
							</td>
							<th>점검 항목 명</th>
							<td>
								<input id="sQcItemNm" type="text" size="30" maxlength="80"/>
							</td>
							<th>업무 구분</th>
							<td>
								<input id="sFcltsJobSe" type="text" size="30" maxlength="80"/>
							</td>
							<th>단계</th>
							<td>
								<input id="sDepthSort" type="text" size="30" maxlength="80"/>
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
				<li><a href="#listTab" class="emdTab">점검 항목 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">점검 항목 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width=10%; height=20; text-align:center;">자료수</th>
								<td>
									<input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled"/>
								</td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
									<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel">
							<tr>
								<th style="width:15%; height:18;">시설물 업무 구분</th>
								<td>
									<input id="fcltsJobSeNm" type="hidden"/>
									<select id="fcltsJobSe">
										<option value="A">건축시설</option>
										<option value="C">토목시설</option>
										<option value="M">기계시설</option>
										<option value="E">전기시설</option>
										<option value="I">정보통신시설</option>
									</select>
								</td>
								<th style="width:15%; height:18;">단계</th>
								<td>
									<input type="text" id="depthSort" size="35" maxlength="1"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 상위</th>
								<td>
									<input type="text" id="qcItemUpperCd" size="7" maxlength="7"/>
									<input type="text" id="qcItemUpperNm" size="20" maxlength="50"/>
									<button id="popupQcItemUpperCd" class="popupButton">선택</button>
								</td>
								<th style="width:15%; height:18;">사용 여부</th>
								<td>
									<input type="text" id="useYn" size="35" maxlength="1"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 코드</th>
								<td>
									<input type="text" id="qcItemCd" size="35" maxlength="7"/>
								</td>
								<th style="width:15%; height:18;">점검 항목 명</th>
								<td>
									<input type="text" id="qcItemNm" size="35" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 상세</th>
								<td colspan="3">
									<input type="text" id="qcItemDtls" size="90" maxlength="100"/>
								</td>
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
