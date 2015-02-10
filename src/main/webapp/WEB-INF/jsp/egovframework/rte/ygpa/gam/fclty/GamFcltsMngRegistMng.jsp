<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsMngRegistMng.jsp
 * @Description : 시설물 관리 대장 화면
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015. 2. 9.		ACEWOLF		최초 생성
 *
 * author ACEWOLF
 * since 2015.02.09
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
 * @FUNCTION NAME : GamFcltsMngRegistMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsMngRegistMngModule() {}

GamFcltsMngRegistMngModule.prototype = new EmdModule(1000, 645);

GamFcltsMngRegistMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngList.do',
		dataType : "json",
		colModel : [
					{display:"시설물 번호",		name:"fcltsNo",					width:100,		sortable:true,	align:"center"},
					{display:"시설물 명",		name:"fcltsNm",					width:150,		sortable:true,	align:"left"},
					{display:"종별",			name:"fcltsGbnNm",				width:50,		sortable:true,	align:"center"},
					{display:"구분",			name:"fcltsSeNm",				width:50,		sortable:true,	align:"center"},
					{display:"종류",			name:"fcltsKndNm",				width:50,		sortable:true,	align:"center"},
					{display:"위치",			name:"loc",						width:150,		sortable:true,	align:"left"},
					{display:"준공 일자",		name:"bldDt",					width:80,		sortable:true,	align:"center"},
					{display:"관리 주체",		name:"mngMainbd",				width:100,		sortable:true,	align:"left"},
					{display:"소유자",			name:"owner",					width:100,		sortable:true,	align:"left"},
					{display:"하자 만료일",		name:"flawEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계 시작일",		name:"planBeginDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계 종료일",		name:"planEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계자",			name:"planner",					width:100,		sortable:true,	align:"left"},
					{display:"공사 시작일",		name:"cnstBeginDt",				width:80,		sortable:true,	align:"center"},
					{display:"공사 종료일",		name:"cnstEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"시공자",			name:"cnstrtr",					width:100,		sortable:true,	align:"left"},
					{display:"시공 금액",		name:"cnstrctAmt",				width:100,		sortable:true,	align:"right"},
					{display:"감리 시작일",		name:"inspectBeginDt",			width:80,		sortable:true,	align:"center"},
					{display:"감리 종료일",		name:"inspectEndDt",			width:80,		sortable:true,	align:"center"},
					{display:"감리자",			name:"inspector",				width:100,		sortable:true,	align:"left"},
					{display:"공사 발주자",		name:"cnstOrderBody",			width:100,		sortable:true,	align:"left"},
					{display:"공사 명",			name:"cnstNm",					width:150,		sortable:true,	align:"left"},
					{display:"공사 감독관",		name:"cnstSupervisor",			width:100,		sortable:true,	align:"left"},
					{display:"상세 제원",		name:"dtlsSpecYn",				width:80,		sortable:true,	align:"center"},
					{display:"안전 점검",		name:"qcHistYn",				width:80,		sortable:true,	align:"center"},
					{display:"보수보강",		name:"mntnHistYn",				width:80,		sortable:true,	align:"center"},
					{display:"영10조 대상",		name:"erqProofPlanApplcEnnc",	width:80,		sortable:true,	align:"center"},
					{display:"작성 일자",		name:"wrtDt",					width:80,		sortable:true,	align:"center"},
					{display:"작성자",			name:"wrtUsr",					width:100,		sortable:true,	align:"left"},
					{display:"수정 일자",		name:"lastUpdtDt",				width:80,		sortable:true,	align:"center"},
					{display:"수정자",			name:"lastUpdtUsr",				width:100,		sortable:true,	align:"left"},
					{display:"관리 그룹 번호",	name:"fcltsMngGroupNo",			width:110,		sortable:true,	align:"center"},
					{display:"관리 그룹 명",	name:"fcltsMngGroupNm",			width:150,		sortable:true,	align:"left"}
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
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsNo;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#fcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#fcltsMngGroupNo", "#fcltsMngGroupNm");
	});

	this.$('#fcltsSe').on('change',{module:this}, function(event){
		event.data.module.makeFcltsKndData("");
	});

	this.$('#fcltsKndSelect').on('change',{module:this}, function(event){
		var sFcltsKnd = $(this).val();
		event.data.module.$('#fcltsKnd').val(sFcltsKnd);
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
GamFcltsMngRegistMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

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
GamFcltsMngRegistMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

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
GamFcltsMngRegistMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

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
GamFcltsMngRegistMngModule.prototype.isValidAmount = function(amountValue) {

	if (amountValue > 9999999999999999 || amountValue < 0) {
		return false;
	}
	return true;

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
GamFcltsMngRegistMngModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectFcltsMngRegistMngFcltsMngGroupNm.do', searchVO, function(module, result) {
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
 * @FUNCTION NAME : makeFcltsKndData
 * @DESCRIPTION   : 시설물 종류를 구성한다.
 * @PARAMETER     :
 *   1. fcltsKnd - 시설물 종류
**/
%>
GamFcltsMngRegistMngModule.prototype.makeFcltsKndData = function(fcltsKnd) {

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
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamFcltsMngRegistMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupFcltsMngGroupNo':
			if (msg == 'ok') {
				this.$('#fcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#fcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#route').focus();
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
GamFcltsMngRegistMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'popupFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFcltsMngGroup.do', null);
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
GamFcltsMngRegistMngModule.prototype.onSubmit = function() {

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
GamFcltsMngRegistMngModule.prototype.loadData = function() {

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
GamFcltsMngRegistMngModule.prototype.refreshData = function() {

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
GamFcltsMngRegistMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/fclty/gamSelectFcltsMngRegistMngPk.do', searchVO, function(module, result){
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
GamFcltsMngRegistMngModule.prototype.selectData = function() {

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
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.addData = function() {

	this.$('#fcltsNo').val("");
	this.$('#fcltsNm').val("");
	this.$('#route').val("");
	this.$('#fcltsGbn').val("");
	this.$('#fcltsSe').val("");
	this.$('#fcltsKnd').val("");
	this.$('#fcltsKndSelect').val("");
	this.$('#locDo').val("");
	this.$('#locSi').val("");
	this.$('#locDong').val("");
	this.$('#locJibun').val("");
	this.$('#mngMainbdSe').val("");
	this.$('#mngMainbd').val("");
	this.$('#owner').val("");
	this.$('#bldDt').val("");
	this.$('#flawEndDt').val("");
	this.$('#dtlsSpecYn').val("Y");
	this.$('#qcHistYn').val("N");
	this.$('#mntnHistYn').val("N");
	this.$('#planBeginDt').val("");
	this.$('#planEndDt').val("");
	this.$('#planner').val("");
	this.$('#cnstBeginDt').val("");
	this.$('#cnstEndDt').val("");
	this.$('#cnstrtr').val("");
	this.$('#cnstrctAmt').val("0");
	this.$('#erqProofPlanApplcEnnc').val("N");
	this.$('#inspectBeginDt').val("");
	this.$('#inspectEndDt').val("");
	this.$('#inspector').val("");
	this.$('#cnstOrderBody').val("");
	this.$('#cnstNm').val("");
	this.$('#cnstSupervisor').val("");
	this.$('#planBookMnten').val("");
	this.$('#wrtDt').val("");
	this.$('#wrtUsr').val("");
	this.$('#lastUpdtDt').val("");
	this.$('#lastUpdtUsr').val("");
	this.$('#basicRm').val("");
	this.$('#maxShipScl').val("");
	this.$('#extd').val("");
	this.$('#upsideAlt').val("");
	this.$('#dpwt').val("");
	this.$('#fmt1Desc1').val("");
	this.$('#fmt1Desc2').val("");
	this.$('#fmt1Desc3').val("");
	this.$('#fmt1Desc4').val("");
	this.$('#fmt2Desc11').val("");
	this.$('#fmt2Desc12').val("");
	this.$('#fmt2Desc13').val("");
	this.$('#fmt2Desc2').val("");
	this.$('#fmt2Desc3').val("");
	this.$('#fmt3Desc1').val("");
	this.$('#fmt3Desc2').val("");
	this.$('#etcDtlsSpec').val("");
	this.$('#fcltsMngGroupNo').val("");
	this.$('#fcltsMngGroupNm').val("");
	this.$('#loc').val("");

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.saveData = function() {

	var fcltsNo = this.$('#fcltsNo').val();
	var fcltsNm = this.$('#fcltsNm').val();
	var bldDt = this.$('#bldDt').val();
	var flawEndDt = this.$('#flawEndDt').val();
	var planBeginDt = this.$('#planBeginDt').val();
	var planEndDt = this.$('#planEndDt').val();
	var cnstBeginDt = this.$('#cnstBeginDt').val();
	var cnstEndDt = this.$('#cnstEndDt').val();
	var inspectBeginDt = this.$('#inspectBeginDt').val();
	var inspectEndDt = this.$('#inspectEndDt').val();
	var cnstrctAmt = Number(this.$('#cnstrctAmt').val().replace(/,/gi, ""));
	var wrtDt = this.$('#wrtDt').val();
	var lastUpdtDt = this.$('#lastUpdtDt').val();
	var dtlsSpecYn = this.$('#dtlsSpecYn').val();
	var qcHistYn = this.$('#qcHistYn').val();
	var mntnHistYn = this.$('#mntnHistYn').val();
	var erqProofPlanApplcEnnc = this.$('#erqProofPlanApplcEnnc').val();
	if (fcltsNo == "") {
		alert('시설물 번호가 부정확합니다.');
		this.$("#fcltsNo").focus();
		return;
	}
	if (fcltsNm == "") {
		alert('시설물 명이 부정확합니다.');
		this.$("#fcltsNm").focus();
		return;
	}
	if (this.isValidDate(bldDt, false) == false) {
		alert('준공 일자가 부정확합니다.');
		this.$("#bldDt").focus();
		return;
	}
	if (this.isValidDate(flawEndDt, false) == false) {
		alert('하자 만료일자가 부정확합니다.');
		this.$("#flawEndDt").focus();
		return;
	}
	if (this.isValidDate(planBeginDt, false) == false) {
		alert('설계 시작 일자가 부정확합니다.');
		this.$("#planBeginDt").focus();
		return;
	}
	if (this.isValidDate(planEndDt, false) == false) {
		alert('설계 종료 일자가 부정확합니다.');
		this.$("#planEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(planBeginDt, planEndDt, false) == false) {
		alert('설계 기간이 부정확합니다.');
		this.$("#planEndDt").focus();
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
	if (this.isValidDate(inspectBeginDt, false) == false) {
		alert('감리 시작 일자가 부정확합니다.');
		this.$("#inspectBeginDt").focus();
		return;
	}
	if (this.isValidDate(inspectEndDt, false) == false) {
		alert('감리 종료 일자가 부정확합니다.');
		this.$("#inspectEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(inspectBeginDt, inspectEndDt, false) == false) {
		alert('감리 기간이 부정확합니다.');
		this.$("#inspectEndDt").focus();
		return;
	}
	if (this.isValidFirstDate(planEndDt, cnstBeginDt, false) == false) {
		alert('공사 시작 일자가 설계 종료 일자보다 큽니다.');
		this.$("#cnstBeginDt").focus();
		return;
	}
	if (this.isValidFirstDate(bldDt, flawEndDt, false) == false) {
		alert('준공 일자가 하자 만료 일자보다 큽니다.');
		this.$("#cnstrctBeginDt").focus();
		return;
	}
	if (this.isValidAmount(cnstrctAmt) == false) {
		alert('총공사비가 부정확합니다.');
		this.$("#cnstrctAmt").focus();
		return;
	}
	if (dtlsSpecYn != "Y" && dtlsSpecYn != "N") {
		alert('상세제원 유무가 부정확합니다.');
		this.$("#dtlsSpecYn").focus();
		return;
	}
	if (qcHistYn != "Y" && qcHistYn != "N") {
		alert('안전점검이력 유무가 부정확합니다.');
		this.$("#qcHistYn").focus();
		return;
	}
	if (mntnHistYn != "Y" && mntnHistYn != "N") {
		alert('보수보강이력 유무가 부정확합니다.');
		this.$("#mntnHistYn").focus();
		return;
	}
	if (erqProofPlanApplcEnnc != "Y" && erqProofPlanApplcEnnc != "N") {
		alert('영10조 대상 유무가 부정확합니다.');
		this.$("#erqProofPlanApplcEnnc").focus();
		return;
	}
	if (this.isValidDate(wrtDt, false) == false) {
		alert('작성 일자가 부정확합니다.');
		this.$("#wrtDt").focus();
		return;
	}
	if (this.isValidDate(lastUpdtDt, false) == false) {
		alert('수정 일자가 부정확합니다.');
		this.$("#lastUpdtDt").focus();
		return;
	}
	if (this._mainmode == "insert") {
		var inputVO = this.makeFormArgs("#detailForm");
		this._mainKeyValue = fcltsMngGroupNo;
		this.doAction('/fclty/gamInsertFcltsMngRegistMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		var inputVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamUpdateFcltsMngRegistMng.do', inputVO, function(module, result) {
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
GamFcltsMngRegistMngModule.prototype.deleteData = function() {

	var fcltsNo = this.$('#fcltsNo').val();
	if (fcltsMngGroupNo == "") {
		alert('시설물 번호가 부정확합니다.');
		this.$("#fcltsNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteFcltsMngRegistMng.do', deleteVO, function(module, result) {
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
GamFcltsMngRegistMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fclty/gamExcelDownloadFcltsMngRegistMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.enableListButtonItem = function() {

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
GamFcltsMngRegistMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#fcltsNo').enable();
		this.$('#fcltsNm').enable();
		this.$('#route').enable();
		this.$('#fcltsGbn').enable();
		this.$('#fcltsSe').enable();
		this.$('#fcltsKndSelect').enable();
		this.$('#locDo').enable();
		this.$('#locSi').enable();
		this.$('#locDong').enable();
		this.$('#locJibun').enable();
		this.$('#mngMainbdSe').enable();
		this.$('#mngMainbd').enable();
		this.$('#owner').enable();
		this.$('#bldDt').enable();
		this.$('#flawEndDt').enable();
		this.$('#dtlsSpecYn').enable();
		this.$('#qcHistYn').enable();
		this.$('#mntnHistYn').enable();
		this.$('#planBeginDt').enable();
		this.$('#planEndDt').enable();
		this.$('#planner').enable();
		this.$('#cnstBeginDt').enable();
		this.$('#cnstEndDt').enable();
		this.$('#cnstrtr').enable();
		this.$('#cnstrctAmt').enable();
		this.$('#erqProofPlanApplcEnnc').enable();
		this.$('#inspectBeginDt').enable();
		this.$('#inspectEndDt').enable();
		this.$('#inspector').enable();
		this.$('#cnstOrderBody').enable();
		this.$('#cnstNm').enable();
		this.$('#cnstSupervisor').enable();
		this.$('#planBookMnten').enable();
		this.$('#wrtDt').enable();
		this.$('#wrtUsr').enable();
		this.$('#lastUpdtDt').enable();
		this.$('#lastUpdtUsr').enable();
		this.$('#basicRm').enable();
		this.$('#maxShipScl').enable();
		this.$('#extd').enable();
		this.$('#upsideAlt').enable();
		this.$('#dpwt').enable();
		this.$('#fmt1Desc1').enable();
		this.$('#fmt1Desc2').enable();
		this.$('#fmt1Desc3').enable();
		this.$('#fmt1Desc4').enable();
		this.$('#fmt2Desc11').enable();
		this.$('#fmt2Desc12').enable();
		this.$('#fmt2Desc13').enable();
		this.$('#fmt2Desc2').enable();
		this.$('#fmt2Desc3').enable();
		this.$('#fmt3Desc1').enable();
		this.$('#fmt3Desc2').enable();
		this.$('#etcDtlsSpec').enable();
		this.$('#fcltsMngGroupNo').enable();
		this.$('#popupFcltsMngGroupNo').enable();
		this.$('#popupFcltsMngGroupNo').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#fcltsNo').disable();
			this.$('#fcltsNm').enable();
			this.$('#route').enable();
			this.$('#fcltsGbn').enable();
			this.$('#fcltsSe').enable();
			this.$('#fcltsKndSelect').enable();
			this.$('#locDo').enable();
			this.$('#locSi').enable();
			this.$('#locDong').enable();
			this.$('#locJibun').enable();
			this.$('#mngMainbdSe').enable();
			this.$('#mngMainbd').enable();
			this.$('#owner').enable();
			this.$('#bldDt').enable();
			this.$('#flawEndDt').enable();
			this.$('#dtlsSpecYn').enable();
			this.$('#qcHistYn').enable();
			this.$('#mntnHistYn').enable();
			this.$('#planBeginDt').enable();
			this.$('#planEndDt').enable();
			this.$('#planner').enable();
			this.$('#cnstBeginDt').enable();
			this.$('#cnstEndDt').enable();
			this.$('#cnstrtr').enable();
			this.$('#cnstrctAmt').enable();
			this.$('#erqProofPlanApplcEnnc').enable();
			this.$('#inspectBeginDt').enable();
			this.$('#inspectEndDt').enable();
			this.$('#inspector').enable();
			this.$('#cnstOrderBody').enable();
			this.$('#cnstNm').enable();
			this.$('#cnstSupervisor').enable();
			this.$('#planBookMnten').enable();
			this.$('#wrtDt').enable();
			this.$('#wrtUsr').enable();
			this.$('#lastUpdtDt').enable();
			this.$('#lastUpdtUsr').enable();
			this.$('#basicRm').enable();
			this.$('#maxShipScl').enable();
			this.$('#extd').enable();
			this.$('#upsideAlt').enable();
			this.$('#dpwt').enable();
			this.$('#fmt1Desc1').enable();
			this.$('#fmt1Desc2').enable();
			this.$('#fmt1Desc3').enable();
			this.$('#fmt1Desc4').enable();
			this.$('#fmt2Desc11').enable();
			this.$('#fmt2Desc12').enable();
			this.$('#fmt2Desc13').enable();
			this.$('#fmt2Desc2').enable();
			this.$('#fmt2Desc3').enable();
			this.$('#fmt3Desc1').enable();
			this.$('#fmt3Desc2').enable();
			this.$('#etcDtlsSpec').enable();
			this.$('#fcltsMngGroupNo').enable();
			this.$('#popupFcltsMngGroupNo').enable();
			this.$('#popupFcltsMngGroupNo').removeClass('ui-state-disabled');
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#fcltsNo').disable();
			this.$('#fcltsNm').disable();
			this.$('#route').disable();
			this.$('#fcltsGbn').disable();
			this.$('#fcltsSe').disable();
			this.$('#fcltsKndSelect').disable();
			this.$('#locDo').disable();
			this.$('#locSi').disable();
			this.$('#locDong').disable();
			this.$('#locJibun').disable();
			this.$('#mngMainbdSe').disable();
			this.$('#mngMainbd').disable();
			this.$('#owner').disable();
			this.$('#bldDt').disable();
			this.$('#flawEndDt').disable();
			this.$('#dtlsSpecYn').disable();
			this.$('#qcHistYn').disable();
			this.$('#mntnHistYn').disable();
			this.$('#planBeginDt').disable();
			this.$('#planEndDt').disable();
			this.$('#planner').disable();
			this.$('#cnstBeginDt').disable();
			this.$('#cnstEndDt').disable();
			this.$('#cnstrtr').disable();
			this.$('#cnstrctAmt').disable();
			this.$('#erqProofPlanApplcEnnc').disable();
			this.$('#inspectBeginDt').disable();
			this.$('#inspectEndDt').disable();
			this.$('#inspector').disable();
			this.$('#cnstOrderBody').disable();
			this.$('#cnstNm').disable();
			this.$('#cnstSupervisor').disable();
			this.$('#planBookMnten').disable();
			this.$('#wrtDt').disable();
			this.$('#wrtUsr').disable();
			this.$('#lastUpdtDt').disable();
			this.$('#lastUpdtUsr').disable();
			this.$('#basicRm').disable();
			this.$('#maxShipScl').disable();
			this.$('#extd').disable();
			this.$('#upsideAlt').disable();
			this.$('#dpwt').disable();
			this.$('#fmt1Desc1').disable();
			this.$('#fmt1Desc2').disable();
			this.$('#fmt1Desc3').disable();
			this.$('#fmt1Desc4').disable();
			this.$('#fmt2Desc11').disable();
			this.$('#fmt2Desc12').disable();
			this.$('#fmt2Desc13').disable();
			this.$('#fmt2Desc2').disable();
			this.$('#fmt2Desc3').disable();
			this.$('#fmt3Desc1').disable();
			this.$('#fmt3Desc2').disable();
			this.$('#etcDtlsSpec').disable();
			this.$('#fcltsMngGroupNo').disable();
			this.$('#popupFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
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
GamFcltsMngRegistMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsNo').disable();
	this.$('#fcltsNm').disable();
	this.$('#route').disable();
	this.$('#fcltsGbn').disable();
	this.$('#fcltsSe').disable();
	this.$('#fcltsKndSelect').disable();
	this.$('#locDo').disable();
	this.$('#locSi').disable();
	this.$('#locDong').disable();
	this.$('#locJibun').disable();
	this.$('#mngMainbdSe').disable();
	this.$('#mngMainbd').disable();
	this.$('#owner').disable();
	this.$('#bldDt').disable();
	this.$('#flawEndDt').disable();
	this.$('#dtlsSpecYn').disable();
	this.$('#qcHistYn').disable();
	this.$('#mntnHistYn').disable();
	this.$('#planBeginDt').disable();
	this.$('#planEndDt').disable();
	this.$('#planner').disable();
	this.$('#cnstBeginDt').disable();
	this.$('#cnstEndDt').disable();
	this.$('#cnstrtr').disable();
	this.$('#cnstrctAmt').disable();
	this.$('#erqProofPlanApplcEnnc').disable();
	this.$('#inspectBeginDt').disable();
	this.$('#inspectEndDt').disable();
	this.$('#inspector').disable();
	this.$('#cnstOrderBody').disable();
	this.$('#cnstNm').disable();
	this.$('#cnstSupervisor').disable();
	this.$('#planBookMnten').disable();
	this.$('#wrtDt').disable();
	this.$('#wrtUsr').disable();
	this.$('#lastUpdtDt').disable();
	this.$('#lastUpdtUsr').disable();
	this.$('#basicRm').disable();
	this.$('#maxShipScl').disable();
	this.$('#extd').disable();
	this.$('#upsideAlt').disable();
	this.$('#dpwt').disable();
	this.$('#fmt1Desc1').disable();
	this.$('#fmt1Desc2').disable();
	this.$('#fmt1Desc3').disable();
	this.$('#fmt1Desc4').disable();
	this.$('#fmt2Desc11').disable();
	this.$('#fmt2Desc12').disable();
	this.$('#fmt2Desc13').disable();
	this.$('#fmt2Desc2').disable();
	this.$('#fmt2Desc3').disable();
	this.$('#fmt3Desc1').disable();
	this.$('#fmt3Desc2').disable();
	this.$('#etcDtlsSpec').disable();
	this.$('#fcltsMngGroupNo').disable();
	this.$('#popupFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
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
GamFcltsMngRegistMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamFcltsMngRegistMngModule();

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
							<th>시설물　　번호</th>
							<td>
								<input id="sFcltsMngGroupNo" type="text" size="18" maxlength="14"/>
							</td>
							<th>시　설　물　명</th>
							<td>
								<input id="sFcltsMngGroupNm" type="text" size="50" maxlength="80"/>
							</td>
							<th>시설물　　종별</th>
							<td>
								<select id="sFcltsGbn">
									<option value="" selected>선택</option>
									<option value="1">1종</option>
									<option value="2">2종</option>
									<option value="3">1종/2종</option>
									<option value="9">기타</option>
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
				<li><a href="#listTab" class="emdTab">시설물 관리 대장 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리 대장 상세</a></li>
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
								<th style="width:10%; height:20; text-align:center;">총공사비 합계</th>
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
								<th style="width:10%; height:24px;">시설물　　번호</th>
								<td>
									<input type="text" id="fcltsNo" size="33" maxlength="14"/>
								</td>
								<th style="width:10%; height:24px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8"/>
									<input type="text" size="60" id="fcltsMngGroupNm" disabled/>
									<button id="popupFcltsMngGroupNo" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">노　　　　　선</th>
								<td>
									<input type="text" id="route" size="33" maxlength="20"/>
								</td>
								<th style="width:10%; height:24px;">시설물　　종별</th>
								<td>
									<select id="fcltsGbn">
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
								</td>
								<th style="width:10%; height:24px;">시설물　　종류</th>
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
								<th style="width:10%; height:24px;">시　설　물　명</th>
								<td>
									<input type="text" id="fcltsNm" size="33" maxlength="80"/>
								</td>
								<th style="width:10%; height:24px;">위　　　　　치</th>
								<td colspan="3">
									시,도:<input type="text" id="locDo" size="10" maxlength="20"/>
									&nbsp;&nbsp;
									시,군,구:<input type="text" id="locSi" size="10" maxlength="20"/>
									&nbsp;&nbsp;
									읍,면,동:<input type="text" id="locDong" size="10" maxlength="20"/>
									&nbsp;&nbsp;
									번지:<input type="text" id="locJibun" size="22" maxlength="40"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">관　리　주　체</th>
								<td>
									<input type="text" id="mngMainbd" size="33" maxlength="50"/>
								</td>
								<th style="width:10%; height:24px;">관리주체　구분</th>
								<td>
									<input type="text" id="mngMainbdSe" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:24px;">소　　유　　자</th>
								<td>
									<input type="text" id="owner" size="33" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">준　　공　　일</th>
								<td>
									<input type="text" size="30" id="bldDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:24px;">하자책임만료일</th>
								<td>
									<input type="text" size="30" id="flawEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:24px;">제원／안전／보수</th>
								<td>
									<select id="dtlsSpecYn">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
									&nbsp;／&nbsp;
									<select id="qcHistYn">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
									&nbsp;／&nbsp;
									<select id="mntnHistYn">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">설　계　기　간</th>
								<td>
									<input type="text" size="11" id="planBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="planEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:24px;">설　　계　　자</th>
								<td>
									<input type="text" size="33" id="planner" maxlength="100"/>
								</td>
								<th style="width:10%; height:24px;">설계도서　보존</th>
								<td>
									<input type="text" size="33" id="planBookMnten" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">공　사　기　간</th>
								<td>
									<input type="text" size="11" id="cnstBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="cnstEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:24px;">시　　공　　자</th>
								<td>
									<input type="text" size="33" id="cnstrtr" maxlength="100"/>
								</td>
								<th style="width:10%; height:24px;">총　공　사　비</th>
								<td>
									<input type="text" size="33" id="cnstrctAmt" class="ygpaNumber" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">감　리　기　간</th>
								<td>
									<input type="text" size="11" id="inspectBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="inspectEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:24px;">감　　리　　자</th>
								<td>
									<input type="text" size="33" id="inspector" maxlength="100"/>
								</td>
								<th style="width:10%; height:24px;">영10조　　대상</th>
								<td>
									<select id="erqProofPlanApplcEnnc">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">공사　　발주자</th>
								<td>
									<input type="text" size="33" id="cnstOrderBody" maxlength="100"/>
								</td>
								<th style="width:10%; height:24px;">공　　사　　명</th>
								<td>
									<input type="text" size="33" id="cnstNm" maxlength="255"/>
								</td>
								<th style="width:10%; height:24px;">공사　　감독관</th>
								<td>
									<input type="text" size="33" id="cnstSupervisor" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">계류선박／연장</th>
								<td>
									<input type="text" size="14" id="maxShipScl" maxlength="20"/>／
									<input type="text" size="14" id="extd" maxlength="20"/>
								</td>
								<th style="width:10%; height:24px;">천단고／수　심</th>
								<td>
									<input type="text" size="14" id="upsideAlt" maxlength="20"/>／
									<input type="text" size="14" id="dpwt" maxlength="20"/>
								</td>
								<th style="width:10%; height:24px;">작성일／작성자</th>
								<td>
									<input type="text" size="12" id="wrtDt" class="emdcal"/>／
									<input type="text" size="13" id="wrtUsr" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">비　　　　　고</th>
								<td colspan="3">
									<input type="text" size="91" id="basicRm" maxlength="1000"/>
								</td>
								<th style="width:10%; height:24px;">수정일／수정자</th>
								<td>
									<input type="text" size="12" id="lastUpdtDt" class="emdcal"/>／
									<input type="text" size="13" id="lastUpdtUsr" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">구조（중력식）</th>
								<td colspan="5">
									케이슨식 : <input type="text" id="fmt1Desc1" size="22" maxlength="50"/>
									&nbsp;/&nbsp;
									L형 블럭 : <input type="text" id="fmt1Desc2" size="22" maxlength="50"/>
									&nbsp;/&nbsp;
									셀룰러블럭식: <input type="text" id="fmt1Desc3" size="22" maxlength="50"/>
									&nbsp;/&nbsp;
									현장타설식: <input type="text" id="fmt1Desc4" size="23" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">구조（잔교식）</th>
								<td colspan="5">
									말뚝식(구경,연장,본수) : <input type="text" id="fmt2Desc11" size="16" maxlength="50"/>
									<input type="text" id="fmt2Desc12" size="16" maxlength="50"/>
									<input type="text" id="fmt2Desc13" size="16" maxlength="50"/>
									&nbsp;/&nbsp;
									원통식 : <input type="text" id="fmt2Desc2" size="23" maxlength="50"/>
									&nbsp;/&nbsp;
									교각식 : <input type="text" id="fmt2Desc3" size="23" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:24px;">구조(널말뚝식)</th>
								<td colspan="5">
									규격 : <input type="text" id="fmt3Desc1" size="33" maxlength="50"/>
									&nbsp;/&nbsp;
									기타 : <input type="text" id="fmt3Desc2" size="100" maxlength="100"/>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
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
