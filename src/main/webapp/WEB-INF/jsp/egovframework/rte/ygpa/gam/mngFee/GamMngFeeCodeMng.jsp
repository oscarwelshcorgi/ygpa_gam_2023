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

GamMngFeeCodeMngModule.prototype = new EmdModule(900, 600);

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
					{display:'관리비 시설 코드', 	name:'mngFeeFcltyCd',	width:110, 		sortable:false,		align:'center'},
					{display:'관리비 시설 명', 		name:'mngFeeFcltyNm',	width:170, 		sortable:false,		align:'left'},
					{display:'관리비 시설 구분', 	name:'mngFeeFcltySeNm',	width:110, 		sortable:false,		align:'left'},
					{display:'관리비 업무 구분', 	name:'mngFeeJobSeNm',	width:110, 		sortable:false,		align:'left'},
					{display:'등록자', 				name:'regUsr',			width:100, 		sortable:false,		align:'center'},
					{display:'등록일시', 			name:'registDt',		width:150, 		sortable:false,		align:'center'}
					],
		showTableToggleBtn : false,
		height : 'auto'
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module.$("#mainTab").tabs("option", {active: 1});
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
GamMngFeeCodeMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
	    	this.addData();
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			this.deleteData();
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

	this.loadData();

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
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$('#mngFeeFcltySe').attr('readonly', 'readonly');
	this.$('#mngFeeFcltyCd').attr('readonly', 'readonly');
	this.makeFormValues('#detailForm', row[0]);
	this.makeDivValues('#detailForm', row[0]);

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamMngFeeCodeMngModule.prototype.addData = function() {

	this._mode="insert";
	this.$("#mainTab").tabs("option", {active: 1});

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
	if (this.$('#mngFeeFcltySe').val() == "" && this.$('#mngFeeFcltyCd').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('/mngFee/gamInsertMngFeeCodeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateMngFeeCodeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
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

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#mngFeeFcltySe').val() == "" && this.$('#mngFeeFcltyCd').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('/mngFee/gamDeleteMngFeeCodeMng.do', row[0], function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

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
			if(this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mngFeeFcltySe').removeAttr('readonly');
				this.$('#mngFeeFcltyCd').removeAttr('readonly');
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
							<th>관리비 시설 코드</th>
							<td>
								<input type="text" size="5" id="sMngFeeFcltyCd">
							</td>
							<th>관리비 시설 명</th>
							<td>
								<input type="text" size="15" id="sMngFeeFcltyNm">
							</td>
							<th>시설 업무 구분</th>
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
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<td style="text-align: right">
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
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
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="20%" height="18">시설 업무 구분</th>
								<td >
									<select id="mngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
									<span data-column-id="mngFeeJobSeNm"></span>
								</td>
								<th width="20%" height="18">시설 구분</th>
								<td >
									<input type="text" size="10" id="mngFeeFcltySe" maxlength="2" />
									<span data-column-id="mngFeeFcltySeNm"></span>
								</td>
							</tr>
							<tr>
								<th width="20%" height="18">시설 코드</th>
								<td ><input type="text" size="35" id="mngFeeFcltyCd" maxlength="4"/></td>
								<th width="20%" height="18">시설명</th>
								<td ><input type="text" size="35" id="mngFeeFcltyNm" maxlength="20"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
								<th width="20%" height="18">등록일시</th>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th width="20%" height="18">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
								<th width="20%" height="18">수정일시</th>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%">
						<tr>
							<td width="100"></td>
							<td style="text-align:right">
								<button data-cmd="btnSave" class="buttonSave">저장</button>
								<button data-cmd="btnDelete" class="buttonDelete">삭제</button>
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
