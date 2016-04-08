<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamArchFcltySpecInqire.jsp
 * @Description : 건축시설 제원 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.03.05  김영길          최초 생성
 *
 * author 김영길
 * since 2015.03.05
 *
 * Copyright (C) 2015 by LFIT  All right reserved.
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

var fcltyMaintMngListPopup;
var fcltyMaintMngListGrid;
var fcltyMaintMngDataList = [];

<%
/**
 * @FUNCTION NAME : GamArchFcltySpecInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamArchFcltySpecInqireModule() {}

GamArchFcltySpecInqireModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectArchFcltySpecInqireList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",	width:60,		sortable:false,		align:"center"},
					{display:"항만시설 명",			name:"prtFcltyNm",				width:200,		sortable:false,		align:"left"},
					{display:"시설물 관리 그룹",	name:"fcltsMngGroupNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설물 분류",			name:"gisPrtFcltyCdNm",			width:100,		sortable:false,		align:"left"},
					{display:"소재지",	 			name:"loc",						width:150,		sortable:false,		align:"left"},
					{display:"준공 일자",	 		name:"bldDt",					width:80,		sortable:false,		align:"center"},
					{display:"기초 형식",	 		name:"baseFmt",					width:150,		sortable:false,		align:"left"},
					{display:"구조 형식",	 		name:"strctFmt",				width:150,		sortable:false,		align:"left"},
					{display:"연면적",	 			name:"ar",						width:80,		sortable:false,		align:"right"},
					{display:"건축 면적",		 	name:"archAr",					width:80,		sortable:false,		align:"right"},
					{display:"대지 면적",	 		name:"plotAr",					width:80,		sortable:false,		align:"right"},
					{display:"시설 면적",	 		name:"prtFcltyAr",				width:80,		sortable:false,		align:"right"},
					{display:"구분",	 			name:"prtFcltySeNm",			width:80,		sortable:false,		align:"left"},
					{display:"설치 일자",	 		name:"prtFcltyInstlDt",			width:80,		sortable:false,		align:"center"},
					{display:"변경 일자",	 		name:"prtFcltyChangeDt",		width:80,		sortable:false,		align:"center"},
					{display:"만료 일자",	 		name:"prtFcltyExprDt",			width:80,		sortable:false,		align:"center"},
					{display:"규격",				name:"prtFcltyStndrd",			width:80,		sortable:false,		align:"left"},
					{display:"단위",	 			name:"prtFcltyUnit",			width:80,		sortable:false,		align:"left"},
					{display:"수량",	 			name:"prtPrtFcltyCnt",			width:80,		sortable:false,		align:"left"},
					{display:"담당자",	 			name:"prtPrtFcltyMnger",		width:80,		sortable:false,		align:"left"},
					{display:"관리 업체",	 		name:"prtFcltyMngEntrpsNm",		width:150,		sortable:false,		align:"left"},
					{display:"설계 공사 명",	 	name:"planCnstNm",				width:150,		sortable:false,		align:"left"},
					{display:"설계 수행 회사",	 	name:"planExcCmpny",			width:150,		sortable:false,		align:"left"},
					{display:"설계 시작 일자",	 	name:"planBeginDt",				width:100,		sortable:false,		align:"center"},
					{display:"설계 종료 일자",		name:"planEndDt",				width:100,		sortable:false,		align:"center"},
					{display:"시공 공사 명",	 	name:"cnstrctCnstNm",			width:150,		sortable:false,		align:"left"},
					{display:"시공 수행 회사",	 	name:"cnstrctExcCmpny",			width:150,		sortable:false,		align:"left"},
					{display:"시공 시작 일자",	 	name:"cnstrctBeginDt",			width:100,		sortable:false,		align:"center"},
					{display:"시공 종료 일자",		name:"cnstrctEndDt",			width:100,		sortable:false,		align:"center"},
					{display:"하자 만료 일자",	 	name:"flawEndDt",				width:100,		sortable:false,		align:"center"},
					{display:"시설물 관리 번호",	name:"fcltsMngNo",				width:130,		sortable:false,		align:"left"},
					{display:"자산 명",	 			name:"gisAssetsNm",				width:200,		sortable:false,		align:"left"},
					{display:"자산 위치",	 		name:"gisAssetsLocNm",			width:200,		sortable:false,		align:"left"},
					{display:"유지보수기간",	 		name:"mntnRprCnstDt",			width:500,		sortable:false,		align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumAr').val($.number(data.sumAr));
			module.$('#sumArchAr').val($.number(data.sumArchAr));
			module.$('#sumPlotAr').val($.number(data.sumPlotAr));
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#sFcltsMngGroupNo", "#sFcltsMngGroupNm");
	});

	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._atchFileDirLoad = false;
	this._atchFilePreview = false;

};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNm
 * @DESCRIPTION   : 시설물 관리 그룹 명을 구한다.
 * @PARAMETER     :
 *   1. argFcltsMngGroupNoVariableName - 시설물 관리 그룹 번호 변수 명
 *   2. argFcltsMngGroupNmVariableName - 시설물 관리 그룹 명 변수 명
**/
%>
GamArchFcltySpecInqireModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectArchFcltySpecInqireFcltsMngGroupNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				sFcltsMngGroupNm = result.sFcltsMngGroupNm;
				if (argFcltsMngGroupNmVariableName != null && argFcltsMngGroupNmVariableName != "") {
					module.$(argFcltsMngGroupNmVariableName).val(result.sFcltsMngGroupNm);
				}
			}
			return sFcltsMngGroupNm;
		});
	}
	if (argFcltsMngGroupNmVariableName != null && argFcltsMngGroupNmVariableName != "") {
		this.$(argFcltsMngGroupNmVariableName).val(sFcltsMngGroupNm);
	}
	return sFcltsMngGroupNm;

};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamArchFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchFcltsMngGroupNo':
			if (msg == 'ok') {
				this.$('#sFcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#sFcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#sFcltsClCd').focus();
			}
			break;
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
GamArchFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
	    case 'btnSpecFirstData':
	    	this.firstData();
			break;
	    case 'btnSpecPrevData':
	    	this.prevData();
			break;
	    case 'btnSpecNextData':
	    	this.nextData();
			break;
	    case 'btnSpecLastData':
	    	this.lastData();
			break;
		case 'popupSearchFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showArchFcltsMngGroup.do', null);
			break;
	    case 'btnFcltyMaintMngList':
			this.showFcltyMaintMngList(this.$('#btnFcltyMaintMngList')[0]);
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
GamArchFcltySpecInqireModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = true;
	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
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
GamArchFcltySpecInqireModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
		this.buildFcltyMaintMngList();
	}
};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.selectData = function() {

	if (this._mainmode == 'query') {
		this.$('#sFcltsMngNo').val("");
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		if (this._params != null && this._params.action != null && this._params.action == "prtFcltyInqire") {
			if (this._params.fcltsMngNo != null && this._params.fcltsMngNo != "") {
				var fcltsMngNo = this._params.fcltsMngNo;
				this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:fcltsMngNo}]);
				this._mainmode = 'modify';
				this._mainKeyValue = fcltsMngNo;
				this.$("#mainTab").tabs("option", {active: 1});
				this._params.action = "";
				this._params.fcltsMngNo = "";
			}
		}
		return;
	}
};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.getFcltsMngGroupNoNm = function() {

	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.firstData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		return;
	}
	var firstRowIndex = 0;
	var row = rows[firstRowIndex];
	var firstFcltsMngNo = row["fcltsMngNo"];
	if (firstFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:firstFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = firstFcltsMngNo;
		this.makeFormValues('#detailForm', rows[firstRowIndex]);
		this.makeDivValues('#detailForm', rows[firstRowIndex]);
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (fcltsMngNo == row["fcltsMngNo"]) {
			prevRowIndex = i - 1;
			break;
		}
	}
	if (prevRowIndex < 0) {
		alert("첫번째 자료 입니다!");
		return;
	}
	if (prevRowIndex >= gridRowCount) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	var row = rows[prevRowIndex];
	var prevFcltsMngNo = row["fcltsMngNo"];
	if (prevFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:prevFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = prevFcltsMngNo;
		this.makeFormValues('#detailForm', rows[prevRowIndex]);
		this.makeDivValues('#detailForm', rows[prevRowIndex]);
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (fcltsMngNo == row["fcltsMngNo"]) {
			nextRowIndex = i + 1;
			break;
		}
	}
	if (nextRowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	if (nextRowIndex >= gridRowCount) {
		alert("마지막 자료 입니다!");
		return;
	}
	var row = rows[nextRowIndex];
	var nextFcltsMngNo = row["fcltsMngNo"];
	if (nextFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:nextFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = nextFcltsMngNo;
		this.makeFormValues('#detailForm', rows[nextRowIndex]);
		this.makeDivValues('#detailForm', rows[nextRowIndex]);
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.lastData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var lastRowIndex = gridRowCount - 1;
	var row = rows[lastRowIndex];
	var lastFcltsMngNo = row["fcltsMngNo"];
	if (lastFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:lastFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = lastFcltsMngNo;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamArchFcltySpecInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#mainGrid").flexRowCount();
			break;
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			var clone =	this.$('#mainGrid').clone();
			$(clone).find('th,td').each(function() {
				if($(this).css('display')=='none') {
					$(this).remove();
				}
				else {
					$(this).css('border-left', '1px solid black');
					$(this).css('border-top', '1px solid black');
					$(this).css('border-right', '1px solid black');
					$(this).css('border-bottom', '1px solid black');
				}
			});
			clone.find("tr:eq(0)").remove();
			clone.find("tr:eq(1)").remove();
			clone.find("tr:eq(0) td").css({"font-size":"15px","font-weight":"bold","background-color":"#BDBDBD","height":"35px"});
			clone.table2excel({
				filename: "건축시설목록",
			});
			break;
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
GamArchFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
			}
			break;
	}
};

<%
/**
 * @FUNCTION NAME : showFcltyMaintMngList
 * @DESCRIPTION   : FCLTY MAINT MNG LIST POPUP SHOW
 * @PARAMETER     :
 *   1. argParent - PARENT
**/
%>
GamArchFcltySpecInqireModule.prototype.showFcltyMaintMngList = function(argParent) {

	if (!fcltyMaintMngListPopup) {
		fcltyMaintMngListPopup = new dhtmlXPopup();
		fcltyMaintMngListGrid = fcltyMaintMngListPopup.attachGrid(430,200);
		fcltyMaintMngListGrid.setHeader("순번, 유지보수기간, 유지보수명");
		fcltyMaintMngListGrid.setInitWidths("50,150,220");
		fcltyMaintMngListGrid.setColAlign("center,center,left");
		fcltyMaintMngListGrid.setColTypes("ed,ed,ed");
		fcltyMaintMngListGrid.setColSorting("str,str,str");
		fcltyMaintMngListGrid.init();
	}
	if (fcltyMaintMngListPopup.isVisible()) {
		fcltyMaintMngListPopup.hide();
	} else {
		var x = window.dhx4.absLeft(argParent);
		var y = window.dhx4.absTop(argParent);
		var w = argParent.offsetWidth;
		var h = argParent.offsetHeight;
		fcltyMaintMngListPopup.show(x,y,w,h);
		fcltyMaintMngListGrid.clearAll();
		for (var i=0; i < fcltyMaintMngDataList.length ; i++) {
			var dataValue = fcltyMaintMngDataList[i].mntnRprSeq + ', ' + fcltyMaintMngDataList[i].mntnRprCnstDt + ', ' + fcltyMaintMngDataList[i].mntnRprCnstNm;
			fcltyMaintMngListGrid.addRow(fcltyMaintMngDataList[i].mntnRprSeq, dataValue);
		}
	}

};

<%
/**
 * @FUNCTION NAME : buildFcltyMaintMngList
 * @DESCRIPTION   : FCLTY MAINT MNG LIST를 구성한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecInqireModule.prototype.buildFcltyMaintMngList = function() {

	fcltyMaintMngDataList = [];
	this.$('#fcltyMaintMngList option').remove();
	var searchVO = [{ name: 'fcltsMngGroupNo', value: this.$('#fcltsMngGroupNo').val() },
					{ name: 'fcltsJobSe', value: "A" },
	                { name: 'fcltsMngNo', value: this.$('#fcltsMngNo').val() }
	               ];
	this.doAction('/fclty/gamSelectArchFcltySpecInqireMntnRprDtlsList.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length <= 0) {
				module.$('#fcltyMaintMngList').append('<option value="">없음　　　　　　　　</option>');
			}
			$.each(result.resultList, function(){
				module.$('#fcltyMaintMngList').append('<option value="' + this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt + '">' + this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt + '</option>');
				fcltyMaintMngDataList[fcltyMaintMngDataList.length] = { 'mntnRprSeq' : this.mntnRprSeq,
																		'mntnRprCnstDt' : this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt,
																		'mntnRprCnstNm' : this.mntnRprCnstNm };
			});
		} else {
			module.$('#fcltyMaintMngList').append('<option value="">없음　　　　　　　　</option>');
		}
    });

};

var module_instance = new GamArchFcltySpecInqireModule();

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
							<th>항구분</th>
							<td>
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>
							</td>
							<th>시설물 관리 그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="16" maxlength="14"/>
								<input id="sFcltsMngGroupNm" type="text" size="55" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2">
								<input type="hidden" id="sFcltsMngNo"/>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>시설물 분류</th>
							<td>
								<input id="sGisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM066"/>
							</td>
							<th>시설 명</th>
							<td>
								<input id="sPrtFcltyNm" type="text" size="34" maxlength="80"/>
							</td>
							<th>소재지</th>
							<td>
								<input id="sLoc" type="text" size="34" maxlength="150"/>
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
				<li><a href="#listTab" class="emdTab">건축시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">건축시설 제원</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:8%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">연면적</th>
								<td><input type="text" size="18" id="sumAr" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">건물 면적</th>
								<td><input type="text" size="18" id="sumArchAr" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnShowMap" data-role="showMap" data-gis-layer="gisArchFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
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
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">건축시설 일반</th>
								<td style="text-align:right;">
									<input type="hidden" id="fcltySpecExistYn"/>
									&nbsp;시설물관리번호 : &nbsp;
									<input type="text" size="20" id="fcltsMngNo" disabled/>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">GIS 　자산코드</th>
								<td>
									<input type="hidden" id="gisAssetsLocCd"/>
									<input type="hidden" id="gisAssetsLocNm"/>
									<input type="text" size="22" id="gisAssetsNm" disabled/>
								</td>
								<th style="width:10%; height:18px;">항구분／분　류</th>
								<td>
									<input type="hidden" id="prtFcltySeNm"/>
									<input type="text" size="3" id="gisAssetsPrtAtCode" disabled/>
									<input type="text" size="4" id="gisAssetsCd" disabled/>
									<input type="text" size="2" id="gisAssetsSubCd" disabled/>
									<input type="text" size="5" id="gisAssetsPrtAtCodeNm" disabled/>
									<select id="prtFcltySe" disabled>
										<option value="A">건축시설</option>
										<option value="C">토목시설</option>
										<option value="M">기계시설</option>
										<option value="E">전기시설</option>
										<option value="I">통신시설</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">시　설　코　드</th>
								<td>
									<input type="hidden" id="prtFcltyGisCd"/>
									<input type="hidden" id="gisPrtFcltyCdSub"/>
									<input type="hidden" id="gisPrtFcltyCdNm"/>
									<input type="text" size="3" id="gisPrtFcltyCdDisplay" disabled/>-
									<input type="text" size="6" id="gisPrtFcltySeq" disabled/>
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM066" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시　　설　　명</th>
								<td>
									<input type="text" size="33" id="prtFcltyNm" maxlength="80" disabled/>
								</td>
								<th style="width:10%; height:18px;">소　　재　　지</th>
								<td>
									<input type="hidden" id="laCrdnt"/>
									<input type="hidden" id="loCrdnt"/>
									<input type="hidden" id="lat"/>
									<input type="hidden" id="lng"/>
									<input type="hidden" id="gisAssetsLocplcLnm"/>
									<input type="hidden" id="loc"/>
									<input type="text" size="33" id="prtFcltyLoc" disabled/>
								</td>
								<th style="width:10%; height:18px;">규　격／단　위</th>
								<td>
									<input type="text" size="20" id="prtFcltyStndrd" maxlength="80" disabled/>／
									<input type="text" size="10" id="prtFcltyUnit" maxlength="10" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">면　적／수　량</th>
								<td>
									<input type="text" size="11" id="prtFcltyAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>／
									<input type="text" size="11" id="prtPrtFcltyCnt" class="ygpaNumber" maxlength="10" disabled/> 개
								</td>
								<th style="width:10%; height:18px;">설치일／담당자</th>
								<td>
									<input type="text" size="11" id="prtFcltyInstlDt" class="emdcal" disabled/>／
									<input type="text" size="14" id="prtPrtFcltyMnger" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18px;">유지보수　이력</th>
								<td>
									<select id="fcltyMaintMngList">
										<option value="">없음　　　　　　　　</option>
									</select>
									<button id="btnFcltyMaintMngList">조회</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">관　리　업　체</th>
								<td>
									<input type="text" size="2" id="prtFcltyMngEntrpsCd" maxlength="8" disabled/>
									<input type="text" size="17" id="prtFcltyMngEntrpsNm" disabled/>
								</td>
								<th style="width:10%; height:18px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8" disabled/>
									<input type="text" size="61" id="fcltsMngGroupNm" disabled/>
								</td>
							</tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">건축시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecFirstData">처음자료</button>
									<button id="btnSpecPrevData">이전자료</button>
									<button id="btnShowMap" data-role="showMap" data-gis-layer="gisArchFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
									<button id="btnSpecNextData">다음자료</button>
									<button id="btnSpecLastData">마지막자료</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">준　공　일　자</th>
								<td>
									<input type="hidden" id="archFcltsClCd"/>
									<input type="text" size="33" id="bldDt" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18px;">주　사용　용도</th>
								<td colspan="3">
									<input type="text" size="93" id="mainUsagePrpos" maxlength="200" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">기　초　형　식</th>
								<td>
									<input type="text" size="33" id="baseFmt" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">구　조　형　식</th>
								<td colspan="3">
									<input type="text" size="93" id="strctFmt" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">연　　면　　적</th>
								<td>
									<input type="text" size="30" id="ar" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>
								</td>
								<th style="width:10%; height:18px;">대　지　면　적</th>
								<td>
									<input type="text" size="30" id="plotAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>
								</td>
								<th style="width:10%; height:18px;">건　축　면　적</th>
								<td>
									<input type="text" size="30" id="archAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">주차면적／대수</th>
								<td>
									<input type="text" size="11" id="prkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>／
									<input type="text" size="11" id="prkCnt" class="ygpaNumber" maxlength="6" disabled/> 대
								</td>
								<th style="width:10%; height:18px;">옥외면적／대수</th>
								<td>
									<input type="text" size="11" id="osdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>／
									<input type="text" size="11" id="osdPrkCnt" class="ygpaNumber" maxlength="6" disabled/> 대
								</td>
								<th style="width:10%; height:18px;">옥내면적／대수</th>
								<td>
									<input type="text" size="11" id="isdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>／
									<input type="text" size="11" id="isdPrkCnt" class="ygpaNumber" maxlength="6" disabled/> 대
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">승강기운영방식</th>
								<td>
									<input type="text" size="33" id="liftOperMthd" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">승객/비상/화물</th>
								<td>
									<input type="text" size="5" id="liftCntPsngr" class="ygpaNumber" maxlength="6" disabled/> 대／
									<input type="text" size="5" id="liftCntEmgcy" class="ygpaNumber" maxlength="6" disabled/> 대／
									<input type="text" size="5" id="liftCntCargo" class="ygpaNumber" maxlength="6" disabled/> 대
								</td>
								<th style="width:10%; height:18px;">환기공조　방식</th>
								<td>
									<input type="text" size="33" id="vntltnArcndtMthd" maxlength="50" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">전기　인입용량</th>
								<td>
									<input type="text" size="29" id="elctyLeadInCapa" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> kW
								</td>
								<th style="width:10%; height:18px;">변전실　　위치</th>
								<td>
									<input type="text" size="33" id="sbtLoc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">정화조　　형식</th>
								<td>
									<input type="text" size="33" id="spictankFmt" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">오수정화　위치</th>
								<td>
									<input type="text" size="33" id="swgClupfcltyLoc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">물탱크　　위치</th>
								<td>
									<input type="text" size="33" id="wrtTankLoc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">유류저장　위치</th>
								<td>
									<input type="text" size="33" id="oilSavefcltyLoc" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">배기닥트　유무</th>
								<td>
									<select id="exhaustDuctEnnc" disabled>
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">냉방유무／열원</th>
								<td>
									<select id="clngEnnc" disabled>
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
									/
									<input type="text" size="25" id="clngSrc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">난방유무／열원</th>
								<td>
									<select id="htngEnnc" disabled>
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
									/
									<input type="text" size="25" id="htngSrc" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">외　　　　　장</th>
								<td>
									<input type="text" size="33" id="fcg" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">내　　　　　장</th>
								<td>
									<input type="text" size="33" id="itr" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">천　　　　　장</th>
								<td>
									<input type="text" size="33" id="ceil" maxlength="200" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">지　　　　　붕</th>
								<td>
									<input type="text" size="33" id="roof" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">지　붕　방　수</th>
								<td>
									<input type="text" size="33" id="roofWtprf" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">하자　만료일자</th>
								<td>
									<input type="text" size="30" id="flawEndDt" class="emdcal" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">설계　공사　명</th>
								<td>
									<input type="text" size="33" id="planCnstNm" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">설계　수행회사</th>
								<td>
									<input type="text" size="33" id="planExcCmpny" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">설　계　기　간</th>
								<td>
									<input type="text" size="11" id="planBeginDt" class="emdcal" disabled/>∼
									<input type="text" size="11" id="planEndDt" class="emdcal" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시공　공사　명</th>
								<td>
									<input type="text" size="33" id="cnstrctCnstNm" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">시공　수행회사</th>
								<td>
									<input type="text" size="33" id="cnstrctExcCmpny" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">시　공　기　간</th>
								<td>
									<input type="text" size="11" id="cnstrctBeginDt" class="emdcal" disabled/>∼
									<input type="text" size="11" id="cnstrctEndDt" class="emdcal" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">비　　　　　고</th>
								<td colSpan="5">
									<input type="text" size="149" id="rm" maxlength="1000" disabled/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
