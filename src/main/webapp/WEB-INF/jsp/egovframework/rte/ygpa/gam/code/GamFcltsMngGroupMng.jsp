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
**/
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

GamFcltsMngGroupMngModule.prototype = new EmdModule(800, 600);

GamFcltsMngGroupMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectFcltsMngGroupMng.do',
		dataType : "json",
		colModel : [
					{display:"관리 그룹 번호",			name:"fcltsMngGroupNo",			width:105,		sortable:true,	align:"center"},
					{display:"관리 그룹 명",			name:"fcltsMngGroupNm",			width:150,		sortable:true,	align:"left"},
					{display:"종별",					name:"fcltsGbnNm",				width:80,		sortable:true,	align:"center"},
					{display:"구분",					name:"fcltsSeNm",				width:80,		sortable:true,	align:"center"},
					{display:"종류",					name:"fcltsKndNm",				width:80,		sortable:true,	align:"center"},
					{display:"준공 일자",				name:"bldDt",					width:80,		sortable:true,	align:"center"},
					{display:"운영사",					name:"owner",					width:110,		sortable:true,	align:"left"},
					{display:"항구분",					name:"prtAtCodeNm",				width:60,		sortable:true,	align:"left"},
					{display:"위치",					name:"loc",						width:200,		sortable:true,	align:"left"},
					{display:"공사 시작 일자",			name:"cnstBeginDt",				width:100,		sortable:true,	align:"center"},
					{display:"공사 종료 일자",			name:"cnstEndDt",				width:100,		sortable:true,	align:"center"},
					{display:"시공자",					name:"cnstrtr",					width:150,		sortable:true,	align:"left"},
					{display:"시공 금액",				name:"cnstrctAmt",				width:100,		sortable:true,	align:"right"},
					{display:"건축시설 사용 유무",		name:"archFcltyUseYn",			width:150,		sortable:true,	align:"center"},
					{display:"토목시설 사용 유무",		name:"cvlEngFcltyUseYn",		width:150,		sortable:true,	align:"center"},
					{display:"기계시설 사용 유무",		name:"mechFcltyUseYn",			width:150,		sortable:true,	align:"center"},
					{display:"전기시설 사용 유무",		name:"elctyFcltyUseYn",			width:150,		sortable:true,	align:"center"},
					{display:"통신시설 사용 유무",		name:"infoCommFcltyUseYn",		width:150,		sortable:true,	align:"center"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumCnstrctAmt').val(data.sumCnstrctAmt);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#prtAtCode').on('change',{module:this}, function(event){
		var prtAtCodeNm = event.data.module.$('#prtAtCode').find('option:selected').text();
		event.data.module.$('#prtAtCodeNm').val(prtAtCodeNm);
		if (event.data.module._mainmode == 'insert') {
			event.data.module.getNewFcltsMngGroupNo();
		}
	});

	this.$('#fcltsSe').on('change',{module:this}, function(event){
		event.data.module.makeFcltsKndData("");
	});

	this.$('#fcltsKndSelect').on('change',{module:this}, function(event){
		var sFcltsKnd = $(this).val();
		event.data.module.$('#fcltsKnd').val(sFcltsKnd);
	});

	this.$('#bldDt').on('keyup change',{module:this}, function(event){
		if (event.data.module._mainmode == 'insert') {
			event.data.module.getNewFcltsMngGroupNo();
		}
	});

	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : isValidDate
 * @DESCRIPTION   : DATE STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. dateString - DATE STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngGroupMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (dateString == "") {
			return false;
		}
	} else {
		if (dateString == "") {
			return true;
		}
	}
	var year = Number(dateString.substring(0,4));
	var month = Number(dateString.substring(5,7));
	var day = Number(dateString.substring(8,10));
	if (year > 9999 || year < 1900) {
		return false;
	}
	if (month > 12 || month < 1) {
		return false;
	}
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
		if (day > 31 || day < 1) {
			return false;
		}
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		if (day > 30 || day < 1) {
			return false;
		}
	} else if (month == 2) {
		if (day > 29 || day < 1) {
			return false;
		}
	} else {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidDateFromTo
 * @DESCRIPTION   : 기간 DATE STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. startDateString - START DATE STRING
 *   2. endDateString - END DATE STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngGroupMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (startDateString == "" || endDateString == "") {
			return false;
		}
	} else {
		if (startDateString == "" && endDateString == "") {
			return true;
		}
	}
	var startDate = Number(startDateString.replace(/-/gi, ""));
	var endDate = Number(endDateString.replace(/-/gi, ""));
	if (startDate > endDate) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidFirstDate
 * @DESCRIPTION   : FIRST DATE STRING > SECOND DATE STRING을 검사한다.
 * @PARAMETER     :
 *   1. firstDateString - FIRST DATE STRING
 *   2. secondDateString - SECOND DATE STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngGroupMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (firstDateString == "" || secondDateString == "") {
			return false;
		}
	} else {
		if (firstDateString == "" || secondDateString == "") {
			return true;
		}
	}
	var firstDate = Number(firstDateString.replace(/-/gi, ""));
	var secondDate = Number(secondDateString.replace(/-/gi, ""));
	if (firstDate > secondDate) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidAmount
 * @DESCRIPTION   : AMOUNT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. amountValue - AMOUNT VALUE
**/
%>
GamFcltsMngGroupMngModule.prototype.isValidAmount = function(amountValue) {

	if (amountValue > 9999999999999999 || amountValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidYn
 * @DESCRIPTION   : Y/N STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. ynString - Y/N STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngGroupMngModule.prototype.isValidYn = function(ynString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (ynString == "") {
			return false;
		}
	} else {
		if (ynString == "") {
			return true;
		}
	}
	if (ynString != "Y" && ynString != "N") {
		return false;
	}
	return true;

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
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnInsert':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.makeFormValues('#detailForm', {});
			this.makeDivValues('#detailForm', {});
			this.disableDetailInputItem();
			this.addData();
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			if (this._mainmode=="modify") {
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
	    case 'btnFirstData':
	    	this.firstData();
			break;
	    case 'btnPrevData':
	    	this.prevData();
			break;
	    case 'btnNextData':
	    	this.nextData();
			break;
	    case 'btnLastData':
	    	this.lastData();
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

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = true;
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
		this.makeFcltsKndData(this.$('#fcltsKnd').val());
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/code/gamSelectFcltsMngGroupMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				module.makeFcltsKndData(module.$('#fcltsKnd').val());
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

	if (this._mainmode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._mainmode != 'insert' && this._mainmode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngGroupNo = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo}]);
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.firstData = function() {

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
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	if (fcltsMngGroupNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsMngGroupNo;
		this.makeFormValues('#detailForm', rows[firstRowIndex]);
		this.makeDivValues('#detailForm', rows[firstRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.prevData = function() {

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
	var prevRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["fcltsMngGroupNo"];
		if (this._mainKeyValue == keyValue) {
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
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	if (fcltsMngGroupNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsMngGroupNo;
		this.makeFormValues('#detailForm', rows[prevRowIndex]);
		this.makeDivValues('#detailForm', rows[prevRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.nextData = function() {

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
	var nextRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["fcltsMngGroupNo"];
		if (this._mainKeyValue == keyValue) {
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
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	if (fcltsMngGroupNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsMngGroupNo;
		this.makeFormValues('#detailForm', rows[nextRowIndex]);
		this.makeDivValues('#detailForm', rows[nextRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.lastData = function() {

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
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	if (fcltsMngGroupNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsMngGroupNo;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.addData = function() {

	this.$('#prtAtCode').val("622");
	this.$('#prtAtCodeNm').val("광양항");
	this.$('#fcltsMngGroupNo').val("");
	this.$('#fcltsMngGroupNm').val("");
	this.$('#loc').val("");
	this.$('#owner').val("여수광양항만공사");
	this.$('#bldDt').val("");
	this.$('#cnstBeginDt').val("");
	this.$('#cnstEndDt').val("");
	this.$('#cnstrtr').val("");
	this.$('#cnstrctAmt').val("0");
	this.$('#archFcltyUseYn').val("Y");
	this.$('#cvlEngFcltyUseYn').val("Y");
	this.$('#mechFcltyUseYn').val("Y");
	this.$('#elctyFcltyUseYn').val("Y");
	this.$('#infoCommFcltyUseYn').val("Y");
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

	var prtAtCode = this.$('#prtAtCode').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var fcltsMngGroupNm = this.$('#fcltsMngGroupNm').val();
	var owner = this.$('#owner').val();
	var bldDt = this.$('#bldDt').val();
	var cnstBeginDt = this.$('#cnstBeginDt').val();
	var cnstEndDt = this.$('#cnstEndDt').val();
	var cnstrctAmt = Number(this.$('#cnstrctAmt').val().replace(/,/gi, ""));
	var archFcltyUseYn = this.$('#archFcltyUseYn').val();
	var cvlEngFcltyUseYn = this.$('#cvlEngFcltyUseYn').val();
	var mechFcltyUseYn = this.$('#mechFcltyUseYn').val();
	var elctyFcltyUseYn = this.$('#elctyFcltyUseYn').val();
	var infoCommFcltyUseYn = this.$('#infoCommFcltyUseYn').val();
	if (prtAtCode == "") {
		alert('항구분이 부정확합니다.');
		this.$("#prtAtCode").focus();
		return;
	}
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
	if (this.isValidDate(bldDt, false) == false) {
		alert('준공 일자가 부정확합니다.');
		this.$("#bldDt").focus();
		return;
	}
	if (this.isValidDate(cnstBeginDt, false) == false) {
		alert('공사 시작 일자가 부정확합니다.');
		this.$("#cnstBeginDt").focus();
		return;
	}
	if (this.isValidDate(cnstEndDt, false) == false) {
		alert('공사 종료 일자가 부정확합니다.');
		this.$("#cnstEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(cnstBeginDt, cnstEndDt, false) == false) {
		alert('공사 기간이 부정확합니다.');
		this.$("#cnstrctEndDt").focus();
		return;
	}
	if (this.isValidAmount(cnstrctAmt) == false) {
		alert('총공사비가 부정확합니다.');
		this.$("#cnstrctAmt").focus();
		return;
	}
	if (this.isValidYn(archFcltyUseYn, true) == false) {
		alert('건축시설 사용 유무가 부정확합니다.');
		this.$("#archFcltyUseYn").focus();
		return;
	}
	if (this.isValidYn(cvlEngFcltyUseYn, true) == false) {
		alert('토목시설 사용 유무가 부정확합니다.');
		this.$("#cvlEngFcltyUseYn").focus();
		return;
	}
	if (this.isValidYn(mechFcltyUseYn, true) == false) {
		alert('기계시설 사용 유무가 부정확합니다.');
		this.$("#mechFcltyUseYn").focus();
		return;
	}
	if (this.isValidYn(elctyFcltyUseYn, true) == false) {
		alert('전기시설 사용 유무가 부정확합니다.');
		this.$("#elctyFcltyUseYn").focus();
		return;
	}
	if (this.isValidYn(infoCommFcltyUseYn, true) == false) {
		alert('정보통신시설 사용 유무가 부정확합니다.');
		this.$("#infoCommFcltyUseYn").focus();
		return;
	}
	if (this._mainmode == "insert") {
		var inputVO = this.makeFormArgs("#detailForm");
		this._mainKeyValue = fcltsMngGroupNo;
		this.doAction('/code/gamInsertFcltsMngGroupMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		var inputVO = this.makeFormArgs("#detailForm");
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
				module._mainmode = 'query';
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
 * @FUNCTION NAME : getNewReqestSeq
 * @DESCRIPTION   : 새로운 징수 의뢰 순번를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.getNewFcltsMngGroupNo = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#prtAtCode').val() == "" || this.$('#bldDt').val() == "") {
		return;
	}
	this.doAction('/code/gamSelectFcltsMngGroupMngMaxGroupNo.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#fcltsMngGroupNo').val(result.sMaxGroupNo);
		}
	});

};

<%
/**
 * @FUNCTION NAME : makeFcltsKndData
 * @DESCRIPTION   : 시설물 종류를 구성한다.
 * @PARAMETER     :
 *   1. fcltsKnd - 시설물 종류
**/
%>
GamFcltsMngGroupMngModule.prototype.makeFcltsKndData = function(fcltsKnd) {

	var fcltsSe = this.$('#fcltsSe').val();
	this.$('#fcltsKndSelect').empty();
	this.$('#fcltsKndSelect').append("<option value='' selected>선택</option>");
	if (fcltsSe == "1") {
		this.$('#fcltsKndSelect').append("<option value='11'>도로교량</option>");
		this.$('#fcltsKndSelect').append("<option value='12'>복개구조물</option>");
		this.$('#fcltsKndSelect').append("<option value='13'>철도교량</option>");
	} else if (fcltsSe == "2") {
		this.$('#fcltsKndSelect').append("<option value='21'>도로터널</option>");
		this.$('#fcltsKndSelect').append("<option value='22'>지하차도</option>");
		this.$('#fcltsKndSelect').append("<option value='23'>철도터널</option>");
	} else if (fcltsSe == "3") {
		this.$('#fcltsKndSelect').append("<option value='31'>갑문시설</option>");
		this.$('#fcltsKndSelect').append("<option value='32'>계류시설</option>");
	} else if (fcltsSe == "4") {
		this.$('#fcltsKndSelect').append("<option value='41'>다목적댐</option>");
		this.$('#fcltsKndSelect').append("<option value='42'>발전용댐</option>");
		this.$('#fcltsKndSelect').append("<option value='43'>홍수전용댐</option>");
		this.$('#fcltsKndSelect').append("<option value='44'>용수전용댐</option>");
		this.$('#fcltsKndSelect').append("<option value='45'>지방상수도전용댐</option>");
	} else if (fcltsSe == "5") {
		this.$('#fcltsKndSelect').append("<option value='51'>공동주택</option>");
		this.$('#fcltsKndSelect').append("<option value='52'>건축물</option>");
		this.$('#fcltsKndSelect').append("<option value='53'>다중이용건축물</option>");
		this.$('#fcltsKndSelect').append("<option value='54'>철도역시설</option>");
		this.$('#fcltsKndSelect').append("<option value='55'>지하도상가</option>");
	} else if (fcltsSe == "6") {
		this.$('#fcltsKndSelect').append("<option value='61'>하구둑</option>");
		this.$('#fcltsKndSelect').append("<option value='62'>수문 및 통문</option>");
		this.$('#fcltsKndSelect').append("<option value='63'>제방</option>");
		this.$('#fcltsKndSelect').append("<option value='64'>보</option>");
	} else if (fcltsSe == "7") {
		this.$('#fcltsKndSelect').append("<option value='71'>광역상수도</option>");
		this.$('#fcltsKndSelect').append("<option value='72'>공업용수도</option>");
		this.$('#fcltsKndSelect').append("<option value='73'>지방상수도</option>");
		this.$('#fcltsKndSelect').append("<option value='74'>공공하수처리시설</option>");
		this.$('#fcltsKndSelect').append("<option value='75'>폐기물매립시설</option>");
	} else if (fcltsSe == "8") {
		this.$('#fcltsKndSelect').append("<option value='81'>도로옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='82'>철도옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='83'>항만옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='84'>댐옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='85'>건축물옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='86'>폐기물매립옹벽</option>");
		this.$('#fcltsKndSelect').append("<option value='87'>기타옹벽</option>");
	} else if (fcltsSe == "9") {
		this.$('#fcltsKndSelect').append("<option value='91'>도로사면</option>");
		this.$('#fcltsKndSelect').append("<option value='92'>철도사면</option>");
		this.$('#fcltsKndSelect').append("<option value='93'>항만사면</option>");
		this.$('#fcltsKndSelect').append("<option value='94'>댐사면</option>");
		this.$('#fcltsKndSelect').append("<option value='95'>건축물사면</option>");
		this.$('#fcltsKndSelect').append("<option value='96'>기타사면</option>");
	}
	if (fcltsKnd != "") {
		this.$('#fcltsKndSelect').val(fcltsKnd);
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.enableListButtonItem = function() {

	if (this._mainmode == "insert") {
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

	if (this._mainmode == "insert") {
		this.$('#prtAtCode').enable();
		this.$('#fcltsMngGroupNm').enable();
		this.$('#loc').enable();
		this.$('#owner').enable();
		this.$('#bldDt').enable();
		this.$('#cnstBeginDt').enable();
		this.$('#cnstEndDt').enable();
		this.$('#cnstrtr').enable();
		this.$('#cnstrctAmt').enable();
		this.$('#archFcltyUseYn').enable();
		this.$('#cvlEngFcltyUseYn').enable();
		this.$('#mechFcltyUseYn').enable();
		this.$('#elctyFcltyUseYn').enable();
		this.$('#infoCommFcltyUseYn').enable();
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#prtAtCode').disable();
			this.$('#fcltsMngGroupNm').enable();
			this.$('#loc').enable();
			this.$('#owner').enable();
			this.$('#bldDt').disable();
			this.$('#cnstBeginDt').enable();
			this.$('#cnstEndDt').enable();
			this.$('#cnstrtr').enable();
			this.$('#cnstrctAmt').enable();
			this.$('#archFcltyUseYn').enable();
			this.$('#cvlEngFcltyUseYn').enable();
			this.$('#mechFcltyUseYn').enable();
			this.$('#elctyFcltyUseYn').enable();
			this.$('#infoCommFcltyUseYn').enable();
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
			this.$('#btnFirstData').enable();
			this.$('#btnFirstData').removeClass('ui-state-disabled');
			this.$('#btnPrevData').enable();
			this.$('#btnPrevData').removeClass('ui-state-disabled');
			this.$('#btnNextData').enable();
			this.$('#btnNextData').removeClass('ui-state-disabled');
			this.$('#btnLastData').enable();
			this.$('#btnLastData').removeClass('ui-state-disabled');
		} else {
			this.$('#prtAtCode').disable();
			this.$('#fcltsMngGroupNm').disable();
			this.$('#loc').disable();
			this.$('#owner').disable();
			this.$('#bldDt').disable();
			this.$('#cnstBeginDt').disable();
			this.$('#cnstEndDt').disable();
			this.$('#cnstrtr').disable();
			this.$('#cnstrctAmt').disable();
			this.$('#archFcltyUseYn').disable();
			this.$('#cvlEngFcltyUseYn').disable();
			this.$('#mechFcltyUseYn').disable();
			this.$('#elctyFcltyUseYn').disable();
			this.$('#infoCommFcltyUseYn').disable();
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
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

	this.$('#prtAtCode').disable();
	this.$('#fcltsMngGroupNm').disable();
	this.$('#loc').disable();
	this.$('#owner').disable();
	this.$('#bldDt').disable();
	this.$('#cnstBeginDt').disable();
	this.$('#cnstEndDt').disable();
	this.$('#cnstrtr').disable();
	this.$('#cnstrctAmt').disable();
	this.$('#archFcltyUseYn').disable();
	this.$('#cvlEngFcltyUseYn').disable();
	this.$('#mechFcltyUseYn').disable();
	this.$('#elctyFcltyUseYn').disable();
	this.$('#infoCommFcltyUseYn').disable();
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});

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
			if (this._mainmode == "modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mainmode == "insert") {
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
							<th>시설물　관리　그룹　번호</th>
							<td>
								<input id="sFcltsMngGroupNo" type="text" size="15" maxlength="14"/>
							</td>
							<th>시설물　관리　그룹　명</th>
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
								<th style="width=10%; height=20; text-align:center;">조회 자료수</th>
								<td>
									<input type="text" size="10" id="totalCount" class="ygpaNumber" disabled="disabled" />
								</td>
								<th style="width:10%; height:20; text-align:center;">시공금액 합계</th>
								<td>
									<input type="text" size="20" id="sumCnstrctAmt" class="ygpaNumber" disabled="disabled"/>
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
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:20%; height:18px;">시설물 관리 그룹 번호</th>
								<td>
									<input id="prtAtCodeNm" type="hidden"/>
									<input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
									<input type="text" id="fcltsMngGroupNo" size="92" maxlength="14" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">시설물　관리 그룹　명</th>
								<td>
									<input type="text" id="fcltsMngGroupNm" size="105" maxlength="80"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">위　　　　　　　　치</th>
								<td>
									<input type="text" id="loc" size="105" maxlength="150"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">시　설　물　　종　별</th>
								<td>
									<select id="fcltsGbn">
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">시　설　물　　종　류</th>
								<td>
									<input id="fcltsKnd" type="hidden"/>
									<select id="fcltsSe">
										<option value="" selected>선택</option>
										<option value="1">교량</option>
										<option value="2">터널</option>
										<option value="3">항만</option>
										<option value="4">댐</option>
										<option value="5">건축물</option>
										<option value="6">하천</option>
										<option value="7">상하수도</option>
										<option value="8">옹벽</option>
										<option value="9">절토사면</option>
									</select>
									<select id="fcltsKndSelect">
										<option value="" selected>선택</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">준　　공　　일　　자</th>
								<td>
									<input type="text" id="bldDt" size="15" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">운　　　　영　　　사</th>
								<td>
									<input type="text" id="owner" size="105" maxlength="60"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">시　　　　공　　　자</th>
								<td>
									<input type="text" id="cnstrtr" size="105" maxlength="60"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">시　　공　　금　　액</th>
								<td>
									<input type="text" class="ygpaNumber" id="cnstrctAmt" size="102" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">공　　사　　기　　간</th>
								<td>
									<input type="text" size="18" id="cnstBeginDt" class="emdcal"/>
									&nbsp; ~ &nbsp;
									<input type="text" size="18" id="cnstEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">건축시설　사용　유무</th>
								<td>
									<select id="archFcltyUseYn">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">토목시설　사용　유무</th>
								<td>
									<select id="cvlEngFcltyUseYn">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">기계시설　사용　유무</th>
								<td>
									<select id="mechFcltyUseYn">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">전기시설　사용　유무</th>
								<td>
									<select id="elctyFcltyUseYn">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18px;">통신시설　사용　유무</th>
								<td>
									<select id="infoCommFcltyUseYn">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
								<button id="btnFirstData">처음　자료</button>
								<button id="btnPrevData">이전　자료</button>
								<button id="btnNextData">다음　자료</button>
								<button id="btnLastData">마지막　자료</button>
								<button id="btnInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnSave" class="buttonSave">　　저　장　　</button>
								<button id="btnRemove" class="buttonDelete">　　삭　제　　</button>
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
