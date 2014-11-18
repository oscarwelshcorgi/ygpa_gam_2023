<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamMngFeeGubunMng.jsp
 * @Description : 관리비 시설구분 관리
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
function GamMngFeeGubunMngModule() {}

GamMngFeeGubunMngModule.prototype = new EmdModule(600, 500);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamMngFeeGubunMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '<c:url value="/mngFee/gamSelectMngFeeGubunMng.do" />',
		dataType : 'json',
		colModel : [
					{display:'시설구분', 		name:'mngFeeFcltySe',		width:70, 		sortable:false,		align:'center'},
                    {display:'시설구분 명', 	name:'mngFeeFcltySeNm',		width:150, 		sortable:false,		align:'left'},
					{display:'등록자', 			name:'regUsr',				width:100, 		sortable:false,		align:'center'},
                    {display:'등록일시', 		name:'registDt',			width:150, 		sortable:false,		align:'center'}
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
GamMngFeeGubunMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'btnIdCheck':
			this.checkId();
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
GamMngFeeGubunMngModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamMngFeeGubunMngModule.prototype.loadData = function() {

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
GamMngFeeGubunMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$("#btnIdCheck").disable({disableClass:"ui-state-disabled"});
	this.$('#mngFeeFcltySe').attr('readonly', 'readonly');
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
GamMngFeeGubunMngModule.prototype.addData = function() {

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
GamMngFeeGubunMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	if (this.$('#mngFeeFcltySe').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('<c:url value="/mngFee/gamInsertMngFeeGubunMng.do" />', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('<c:url value="/mngFee/gamUpdateMngFeeGubunMng.do" />', inputVO, function(module, result) {
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
GamMngFeeGubunMngModule.prototype.deleteData = function() {
	var row = this.$('#mainGrid').selectedRows();

	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#mngFeeFcltySe').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('<c:url value="/mngFee/gamDeleteMngFeeGubunMng.do" />', row[0], function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}
};

<%
/**
 * @FUNCTION NAME : checkId
 * @DESCRIPTION   : 구분코드 중복 체크 한다..
 * @PARAMETER     : NONE
**/
%>
GamMngFeeGubunMngModule.prototype.checkId = function() {

	if (this.$("#mngFeeFcltySe").val() == "") {
		this.$("#mngFeeFcltySe").focus();
		alert("코드를 입력하십시오.");
		return;
	}
	this.doAction('<c:url value="/mngFee/gamcheckSeFeeGubunMng.do" />', {checkSe : this.$("#mngFeeFcltySe").val()}, function(module, result) {
		if (result.resultCode == 0) {
			if (result.checkSeCnt != "0") {
				alert("이미 사용중인 코드가 존재합니다.");
				module.$("#mngFeeFcltySe").focus();
				module.$("#mngFeeFcltySe").val("");
			} else {
				if (confirm("해당 코드를 사용하시겠습니까?")) {
					module.$("#mngFeeFcltySe").val(result.checkSe);
					module.$("#mngFeeFcltySe").attr("readonly","readonly");
				} else {
					module.$("#mngFeeFcltySe").val("");
					module.$("#mngFeeFcltySe").focus();
				}
			}
		}
	});

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
GamMngFeeGubunMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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
				this.$("#btnIdCheck").removeClass("ui-state-disabled");
				this.$("#btnIdCheck").enable();
			}
			break;
	}

};

var module_instance = new GamMngFeeGubunMngModule();

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
							<th>시설 구분</th>
							<td>
								<input type="text" size="10" id="sMngFeeFcltySe" maxlength="2">
							</td>
							<th>시설 구분 명</th>
							<td>
								<input type="text" size="10" id="sMngFeeFcltySeNm" maxlength="20">
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
				<li><a href="#listTab" class="emdTab">관리비 시설구분</a></li>
				<li><a href="#detailTab" class="emdTab">관리비 시설구분 상세</a></li>
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
						<input type="hidden" id="oldMngFeeFcltySe"/>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="20%" height="18">시설 구분</th>
								<td><input type="text" size="20" id="mngFeeFcltySe" maxlength="2"/></td>
								<td><button id="btnIdCheck">중복체크</button>
							</tr>
							<tr>
								<th width="20%" height="18">시설 구분 명</th>
								<td colspan="2"><input type="text" size="65" id="mngFeeFcltySeNm" maxlength="20"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th width="20%" height="18">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
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
