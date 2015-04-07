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

GamFcltsMngRegistMngModule.prototype = new EmdModule(1000, 710);

GamFcltsMngRegistMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngList.do',
		dataType : "json",
		colModel : [
					{display:"시설물 번호",		name:"fcltsNo",					width:110,		sortable:true,	align:"center"},
					{display:"시설물 명",		name:"fcltsNm",					width:150,		sortable:true,	align:"left"},
					{display:"종별",			name:"fcltsGbnNm",				width:50,		sortable:true,	align:"center"},
					{display:"구분",			name:"fcltsSeNm",				width:50,		sortable:true,	align:"center"},
					{display:"종류",			name:"fcltsKndNm",				width:60,		sortable:true,	align:"center"},
					{display:"위치",			name:"loc",						width:150,		sortable:true,	align:"left"},
					{display:"준공 일자",		name:"bldDt",					width:80,		sortable:true,	align:"center"},
					{display:"관리 주체",		name:"mngMainbd",				width:110,		sortable:true,	align:"left"},
					{display:"소유자",			name:"owner",					width:110,		sortable:true,	align:"left"},
					{display:"하자 만료일",		name:"flawEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계 시작일",		name:"planBeginDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계 종료일",		name:"planEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"설계자",			name:"planner",					width:110,		sortable:true,	align:"left"},
					{display:"공사 시작일",		name:"cnstBeginDt",				width:80,		sortable:true,	align:"center"},
					{display:"공사 종료일",		name:"cnstEndDt",				width:80,		sortable:true,	align:"center"},
					{display:"시공자",			name:"cnstrtr",					width:110,		sortable:true,	align:"left"},
					{display:"시공 금액",		name:"cnstrctAmt",				width:110,		sortable:true,	align:"right"},
					{display:"감리 시작일",		name:"inspectBeginDt",			width:80,		sortable:true,	align:"center"},
					{display:"감리 종료일",		name:"inspectEndDt",			width:80,		sortable:true,	align:"center"},
					{display:"감리자",			name:"inspector",				width:110,		sortable:true,	align:"left"},
					{display:"공사 발주자",		name:"cnstOrderBody",			width:110,		sortable:true,	align:"left"},
					{display:"공사 명",			name:"cnstNm",					width:150,		sortable:true,	align:"left"},
					{display:"공사 감독관",		name:"cnstSupervisor",			width:110,		sortable:true,	align:"left"},
					{display:"상세 제원",		name:"dtlsSpecYn",				width:80,		sortable:true,	align:"center"},
					{display:"안전 점검",		name:"qcHistYn",				width:80,		sortable:true,	align:"center"},
					{display:"보수보강",		name:"mntnHistYn",				width:80,		sortable:true,	align:"center"},
					{display:"영10조 대상",		name:"erqProofPlanApplcEnnc",	width:80,		sortable:true,	align:"center"},
					{display:"작성 일자",		name:"wrtDt",					width:80,		sortable:true,	align:"center"},
					{display:"작성자",			name:"wrtUsr",					width:110,		sortable:true,	align:"left"},
					{display:"수정 일자",		name:"lastUpdtDt",				width:80,		sortable:true,	align:"center"},
					{display:"수정자",			name:"lastUpdtUsr",				width:110,		sortable:true,	align:"left"},
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
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsNo;
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module.loadDetail('listTab');
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsNo;
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#fcltsNo").bind("keyup change", {module: this}, function(event) {
		var fcltsNo = $(this).val();
		event.data.module.changeDetailAreaForm(fcltsNo);
	});

	this.$("#fcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#fcltsMngGroupNo", "#fcltsMngGroupNm");
		var fcltsNm = event.data.module.$('#fcltsNm').val();
		var fcltsMngGroupNm = event.data.module.$('#fcltsMngGroupNm').val();
		if (fcltsMngGroupNm != "" && fcltsNm == "") {
			event.data.module.$('#fcltsNm').val(fcltsMngGroupNm);
		}
	});

	this.$('#fcltsSe').on('change',{module:this}, function(event){
		event.data.module.makeFcltsKndData("");
	});

	this.$('#fcltsKndSelect').on('change',{module:this}, function(event){
		var sFcltsKnd = $(this).val();
		event.data.module.$('#fcltsKnd').val(sFcltsKnd);
	});

	this.$('#paveWd').on('keyup change',{module:this}, function(event){
		event.data.module.calcSumPaveRoadWd();
	});

	this.$('#roadWd').on('keyup change',{module:this}, function(event){
		event.data.module.calcSumPaveRoadWd();
	});

	this.$('#upRoadCn').on('keyup change',{module:this}, function(event){
		event.data.module.calcSumUpDownRoadCn();
	});

	this.$('#downRoadCn').on('keyup change',{module:this}, function(event){
		event.data.module.calcSumUpDownRoadCn();
	});

	this.$("#qcPlanGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngQcMngDtlsPlanList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',			name:'rnum',				width:50,		sortable:false,		align:'center'},
					{display:'구분',			name:'qcInspSeNm',			width:100,		sortable:false,		align:'left'},
					{display:'시행일',			name:'qcInspDt',			width:100,		sortable:false,		align:'center'},
					{display:'예산',			name:'qcInspBdgt',			width:150,		sortable:false,		align:'right'},
					{display:'점검진단자',		name:'qcInspTpNm',			width:180,		sortable:false,		align:'left'},
					{display:'비고',			name:'rm',					width:360,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '215'
	});

	this.$("#qcHistGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngQcMngDtlsHistList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',				name:'rnum',				width:50,		sortable:false,		align:'center'},
					{display:'점검진단기간',		name:'qcInspDtFromTo',		width:160,		sortable:false,		align:'center'},
					{display:'점검진단기관명',		name:'qcInspInsttNm',		width:150,		sortable:false,		align:'left'},
					{display:'비용',				name:'qcInspAmt',			width:100,		sortable:false,		align:'right'},
					{display:'점검진단구분',		name:'qcInspSeNm',			width:90,		sortable:false,		align:'center'},
					{display:'점검진단책임기술자',	name:'responEngineerNm',	width:125,		sortable:false,		align:'left'},
					{display:'상태등급',			name:'sttusEvlLvlNm',		width:70,		sortable:false,		align:'center'},
					{display:'주요 점검진단결과',	name:'qcInspResult',		width:250,		sortable:false,		align:'left'},
					{display:'주요 보수보강(안)',	name:'actionCn',			width:250,		sortable:false,		align:'left'},
					{display:'작성일',				name:'wrtDt',				width:80,		sortable:false,		align:'center'},
					{display:'작성자',				name:'wrtUsr',				width:120,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '215'
	});

	this.$("#mntnPlanGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngMntnRprDtlsPlanList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',			name:'rnum',					width:50,		sortable:false,		align:'center'},
					{display:'공사기간',		name:'mntnRprCnstDtFromTo',		width:180,		sortable:false,		align:'center'},
					{display:'공사구분',		name:'mntnRprSeNm',				width:100,		sortable:false,		align:'center'},
					{display:'예산',			name:'mntnRprBdgt',				width:150,		sortable:false,		align:'right'},
					{display:'부위',			name:'mntnRprPart',				width:160,		sortable:false,		align:'left'},
					{display:'공사내역',		name:'mntnRprCn',				width:300,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '215'
	});

	this.$("#mntnHistGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsMngRegistMngMntnRprDtlsHistList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',			name:'rnum',					width:50,		sortable:false,		align:'center'},
					{display:'공사기간',		name:'mntnRprCnstDtFromTo',		width:160,		sortable:false,		align:'center'},
					{display:'공사구분',		name:'mntnRprSeNm',				width:80,		sortable:false,		align:'center'},
					{display:'부위',			name:'mntnRprPart',				width:150,		sortable:false,		align:'left'},
					{display:'공사내역',		name:'mntnRprCn',				width:250,		sortable:false,		align:'left'},
					{display:'공사비',			name:'mntnRprCnstAmt',			width:120,		sortable:false,		align:'right'},
					{display:'설계자',			name:'plannerNm',				width:150,		sortable:false,		align:'left'},
					{display:'시공자',			name:'cnstrtr',					width:150,		sortable:false,		align:'left'},
					{display:'책임기술자',		name:'responEngineer',			width:150,		sortable:false,		align:'left'},
,					{display:'공사감독',		name:'cnstChargNm',				width:150,		sortable:false,		align:'left'},
					{display:'작성일',			name:'wrtDt',					width:80,		sortable:false,		align:'center'},
					{display:'작성자',			name:'wrtUsr',					width:120,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '215'
	});

	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._mainFcltsMngGroupNo = '';
	this._qcMngFcltsMngGroupNo = '';
	this._mntnRprFcltsMngGroupNo = '';
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrint').disable({disableClass:"ui-state-disabled"});

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
 * @FUNCTION NAME : calcSumPaveRoadWd
 * @DESCRIPTION   : 폭 합계를 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.calcSumPaveRoadWd = function() {

	var paveWd = this.$('#paveWd').val().replace(/,/gi, "") * 1;
	var roadWd = this.$('#roadWd').val().replace(/,/gi, "") * 1;
	var sumPaveRoadWd = paveWd + roadWd;
	this.$('#sumPaveRoadWd').val('' + $.number(sumPaveRoadWd));

};

<%
/**
 * @FUNCTION NAME : calcSumUpDownRoadCn
 * @DESCRIPTION   : 차로수 합계를 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.calcSumUpDownRoadCn = function() {

	var upRoadCn = this.$('#upRoadCn').val().replace(/,/gi, "") * 1;
	var downRoadCn = this.$('#downRoadCn').val().replace(/,/gi, "") * 1;
	var sumUpDownRoadCn = upRoadCn + downRoadCn;
	this.$('#sumUpDownRoadCn').val('' + $.number(sumUpDownRoadCn));

};

<%
/**
 * @FUNCTION NAME : changeDetailAreaForm
 * @DESCRIPTION   : 시설 번호에 따른 폼 입력 영역 변경
 * @PARAMETER     :
 *   1. argFcltsNo - FCLTS NO.(시설 번호)
**/
%>
GamFcltsMngRegistMngModule.prototype.changeDetailAreaForm = function(argFcltsNo) {

	var fcltsNoSe = "Z";
	if (argFcltsNo != "") {
		fcltsNoSe = argFcltsNo.substring(0,1);
	}
	if (fcltsNoSe == "H") {
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
		this.$('#cvlEngDetailArea').show();
	} else if (fcltsNoSe == "A") {
		this.$('#cvlEngDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
		this.$('#archDetailArea').show();
	} else if (fcltsNoSe == "B") {
		this.$('#cvlEngDetailArea').hide();
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').show();
	} else {
		this.$('#cvlEngDetailArea').hide();
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
	}

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
					var fcltsNm = module.$('#fcltsNm').val();
					if (fcltsNm == "") {
						module.$('#fcltsNm').val(result.sFcltsMngGroupNm);
					}
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
			this._mainFcltsMngGroupNo = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnInsert':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this._mainFcltsMngGroupNo = '';
			this.makeFormValues('#detailForm', {});
			this.makeDivValues('#detailForm', {});
			this.makeFormValues('#cvlEngDetailAreaForm', {});
			this.makeDivValues('#cvlEngDetailAreaForm', {});
			this.makeFormValues('#archDetailAreaForm', {});
			this.makeDivValues('#archDetailAreaForm', {});
			this.makeFormValues('#bridgeDetailAreaForm', {});
			this.makeDivValues('#bridgeDetailAreaForm', {});
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
GamFcltsMngRegistMngModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._mainFcltsMngGroupNo = '';
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
		this.makeFormValues('#cvlEngDetailAreaForm', row[0]);
		this.makeDivValues('#cvlEngDetailAreaForm', row[0]);
		this.makeFormValues('#archDetailAreaForm', row[0]);
		this.makeDivValues('#archDetailAreaForm', row[0]);
		this.makeFormValues('#bridgeDetailAreaForm', row[0]);
		this.makeDivValues('#bridgeDetailAreaForm', row[0]);
		this.$('#fcltsJobSe').val(this.$('#sFcltsJobSe').val());
		this.makeFcltsKndData(this.$('#fcltsKnd').val());
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/fclty/gamSelectFcltsMngRegistMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				module.makeFormValues('#cvlEngDetailAreaForm', result.result);
				module.makeDivValues('#cvlEngDetailAreaForm', result.result);
				module.makeFormValues('#archDetailAreaForm', result.result);
				module.makeDivValues('#archDetailAreaForm', result.result);
				module.makeFormValues('#bridgeDetailAreaForm', result.result);
				module.makeDivValues('#bridgeDetailAreaForm', result.result);
				module.$('#fcltsJobSe').val(this.$('#sFcltsJobSe').val());
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
	var fcltsNo = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"fcltsNo", filter:fcltsNo}]);
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
GamFcltsMngRegistMngModule.prototype.firstData = function() {

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
	var fcltsNo = row["fcltsNo"];
	if (fcltsNo != "") {
		this.$("#mainTab").tabs("option", {active: 0});
		this.$("#mainGrid").selectFilterRow([{col:"fcltsNo", filter:fcltsNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsNo;
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this.$("#mainTab").tabs("option", {active: 1});
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.prevData = function() {

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
		keyValue = row["fcltsNo"];
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
	var fcltsNo = row["fcltsNo"];
	if (fcltsNo != "") {
		this.$("#mainTab").tabs("option", {active: 0});
		this.$("#mainGrid").selectFilterRow([{col:"fcltsNo", filter:fcltsNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsNo;
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this.$("#mainTab").tabs("option", {active: 1});
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.nextData = function() {

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
		keyValue = row["fcltsNo"];
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
	var fcltsNo = row["fcltsNo"];
	if (fcltsNo != "") {
		this.$("#mainTab").tabs("option", {active: 0});
		this.$("#mainGrid").selectFilterRow([{col:"fcltsNo", filter:fcltsNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsNo;
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this.$("#mainTab").tabs("option", {active: 1});
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngRegistMngModule.prototype.lastData = function() {

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
	var fcltsNo = row["fcltsNo"];
	if (fcltsNo != "") {
		this.$("#mainTab").tabs("option", {active: 0});
		this.$("#mainGrid").selectFilterRow([{col:"fcltsNo", filter:fcltsNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsNo;
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this.$("#mainTab").tabs("option", {active: 1});
	}

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
	this.$('#fcltsGbn').val("1");
	this.$('#fcltsSe').val("3");
	this.makeFcltsKndData("");
	this.$('#fcltsKnd').val("32");
	this.$('#fcltsKndSelect').val("32");
	this.$('#locDo').val("전라남도");
	this.$('#locSi').val("광양시");
	this.$('#locDong').val("");
	this.$('#locJibun').val("");
	this.$('#mngMainbdSe').val("공공");
	this.$('#mngMainbd').val("여수광양항만공사 운영본부");
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
	this.$('#planBookMnten').val("대상-보존");
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
	this.$('#baseFmt').val("");
	this.$('#strctFmt').val("");
	this.$('#ar').val("");
	this.$('#archAr').val("");
	this.$('#plotAr').val("");
	this.$('#mainUsagePrpos').val("");
	this.$('#prkAr').val("");
	this.$('#isdPrkAr').val("");
	this.$('#osdPrkAr').val("");
	this.$('#prkCnt').val("");
	this.$('#isdPrkCnt').val("");
	this.$('#osdPrkCnt').val("");
	this.$('#exhaustDuctEnnc').val("");
	this.$('#vntltnArcndtMthd').val("");
	this.$('#wrtTankLoc').val("");
	this.$('#sbtLoc').val("");
	this.$('#oilSavefcltyLoc').val("");
	this.$('#swgClupfcltyLoc').val("");
	this.$('#liftCntPsngr').val("");
	this.$('#liftCntCargo').val("");
	this.$('#liftCntEmgcy').val("");
	this.$('#liftOperMthd').val("");
	this.$('#clngEnnc').val("");
	this.$('#clngSrc').val("");
	this.$('#htngEnnc').val("");
	this.$('#htngSrc').val("");
	this.$('#spictankFmt').val("");
	this.$('#elctyLeadInCapa').val("");
	this.$('#groundFloorCn').val("");
	this.$('#topFloorCn').val("");
	this.$('#underFloorCn').val("");
	this.$('#highHt').val("");
	this.$('#highFllorHt').val("");
	this.$('#highFllorHtPos').val("");
	this.$('#baseSideDp').val("");
	this.$('#floorArRate').val("");
	this.$('#capaRate').val("");
	this.$('#dtMaxUsageCn').val("");
	this.$('#dtUsageCn').val("");
	this.$('#bldMntnMngSysYn').val("");
	this.$('#mntnMngAddFcltyYn').val("");
	this.$('#apptTp').val("");
	this.$('#bridgeStartLoc').val("");
	this.$('#bridgeEndLoc').val("");
	this.$('#planLiveWght').val("");
	this.$('#allowPassWght').val("");
	this.$('#extLt').val("");
	this.$('#extSpanCn').val("");
	this.$('#extMaxSpanLt').val("");
	this.$('#paveWd').val("");
	this.$('#roadWd').val("");
	this.$('#sumPaveRoadWd').val("");
	this.$('#upRoadCn').val("");
	this.$('#downRoadCn').val("");
	this.$('#sumUpDownRoadCn').val("");
	this.$('#spanComp').val("");
	this.$('#mainSpanFmt').val("");
	this.$('#sunSpanFmt').val("");
	this.$('#propSe').val("");
	this.$('#buldConnSe').val("");
	this.$('#subPassLmtHt').val("");
	this.$('#bridgePierFmt1').val("");
	this.$('#bridgePierCn1').val("");
	this.$('#bridgePierBaseFmt1').val("");
	this.$('#bridgePierFmt2').val("");
	this.$('#bridgePierCn2').val("");
	this.$('#bridgePierBaseFmt2').val("");
	this.$('#bridgePropFmt1').val("");
	this.$('#bridgePropBaseFmt1').val("");
	this.$('#bridgePropFmt2').val("");
	this.$('#bridgePropBaseFmt2').val("");
	this.$('#crossRoute').val("");
	this.$('#crossRiverHighDpwt').val("");
	this.enableDetailInputItem();
	this.$('#fcltsNo').focus();

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
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	if (fcltsNo == "") {
		alert('시설물 번호가 부정확합니다.');
		this.$("#fcltsNo").focus();
		return;
	}
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹이 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
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
		var inputVO = this.makeFormArgs("#detailTab");
		this._mainKeyValue = fcltsNo;
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this.doAction('/fclty/gamInsertFcltsMngRegistMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		var inputVO = this.makeFormArgs("#detailTab");
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
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
	if (fcltsNo == "") {
		alert('시설물 번호가 부정확합니다.');
		this.$("#fcltsNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailTab");
		this.doAction('/fclty/gamDeleteFcltsMngRegistMng.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainmode = 'query';
				module._mainKeyValue = '';
				module._mainFcltsMngGroupNo = '';
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
		this.$('#btnPrint').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrint').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnPrint').enable();
			this.$('#btnPrint').removeClass('ui-state-disabled');
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrint').disable({disableClass:"ui-state-disabled"});
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
		this.$('#baseFmt').enable();
		this.$('#strctFmt').enable();
		this.$('#ar').enable();
		this.$('#archAr').enable();
		this.$('#plotAr').enable();
		this.$('#mainUsagePrpos').enable();
		this.$('#prkAr').enable();
		this.$('#isdPrkAr').enable();
		this.$('#osdPrkAr').enable();
		this.$('#prkCnt').enable();
		this.$('#isdPrkCnt').enable();
		this.$('#osdPrkCnt').enable();
		this.$('#exhaustDuctEnnc').enable();
		this.$('#vntltnArcndtMthd').enable();
		this.$('#wrtTankLoc').enable();
		this.$('#sbtLoc').enable();
		this.$('#oilSavefcltyLoc').enable();
		this.$('#swgClupfcltyLoc').enable();
		this.$('#liftCntPsngr').enable();
		this.$('#liftCntCargo').enable();
		this.$('#liftCntEmgcy').enable();
		this.$('#liftOperMthd').enable();
		this.$('#clngEnnc').enable();
		this.$('#clngSrc').enable();
		this.$('#htngEnnc').enable();
		this.$('#htngSrc').enable();
		this.$('#spictankFmt').enable();
		this.$('#elctyLeadInCapa').enable();
		this.$('#groundFloorCn').enable();
		this.$('#topFloorCn').enable();
		this.$('#underFloorCn').enable();
		this.$('#highHt').enable();
		this.$('#highFllorHt').enable();
		this.$('#highFllorHtPos').enable();
		this.$('#baseSideDp').enable();
		this.$('#floorArRate').enable();
		this.$('#capaRate').enable();
		this.$('#dtMaxUsageCn').enable();
		this.$('#dtUsageCn').enable();
		this.$('#bldMntnMngSysYn').enable();
		this.$('#mntnMngAddFcltyYn').enable();
		this.$('#apptTp').enable();
		this.$('#bridgeStartLoc').enable();
		this.$('#bridgeEndLoc').enable();
		this.$('#planLiveWght').enable();
		this.$('#allowPassWght').enable();
		this.$('#extLt').enable();
		this.$('#extSpanCn').enable();
		this.$('#extMaxSpanLt').enable();
		this.$('#paveWd').enable();
		this.$('#roadWd').enable();
		this.$('#sumPaveRoadWd').enable();
		this.$('#upRoadCn').enable();
		this.$('#downRoadCn').enable();
		this.$('#sumUpDownRoadCn').enable();
		this.$('#spanComp').enable();
		this.$('#mainSpanFmt').enable();
		this.$('#sunSpanFmt').enable();
		this.$('#propSe').enable();
		this.$('#buldConnSe').enable();
		this.$('#subPassLmtHt').enable();
		this.$('#bridgePierFmt1').enable();
		this.$('#bridgePierCn1').enable();
		this.$('#bridgePierBaseFmt1').enable();
		this.$('#bridgePierFmt2').enable();
		this.$('#bridgePierCn2').enable();
		this.$('#bridgePierBaseFmt2').enable();
		this.$('#bridgePropFmt1').enable();
		this.$('#bridgePropBaseFmt1').enable();
		this.$('#bridgePropFmt2').enable();
		this.$('#bridgePropBaseFmt2').enable();
		this.$('#crossRoute').enable();
		this.$('#crossRiverHighDpwt').enable();
		this.$('#popupFcltsMngGroupNo').enable();
		this.$('#popupFcltsMngGroupNo').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDetailPrint').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
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
			this.$('#baseFmt').enable();
			this.$('#strctFmt').enable();
			this.$('#ar').enable();
			this.$('#archAr').enable();
			this.$('#plotAr').enable();
			this.$('#mainUsagePrpos').enable();
			this.$('#prkAr').enable();
			this.$('#isdPrkAr').enable();
			this.$('#osdPrkAr').enable();
			this.$('#prkCnt').enable();
			this.$('#isdPrkCnt').enable();
			this.$('#osdPrkCnt').enable();
			this.$('#exhaustDuctEnnc').enable();
			this.$('#vntltnArcndtMthd').enable();
			this.$('#wrtTankLoc').enable();
			this.$('#sbtLoc').enable();
			this.$('#oilSavefcltyLoc').enable();
			this.$('#swgClupfcltyLoc').enable();
			this.$('#liftCntPsngr').enable();
			this.$('#liftCntCargo').enable();
			this.$('#liftCntEmgcy').enable();
			this.$('#liftOperMthd').enable();
			this.$('#clngEnnc').enable();
			this.$('#clngSrc').enable();
			this.$('#htngEnnc').enable();
			this.$('#htngSrc').enable();
			this.$('#spictankFmt').enable();
			this.$('#elctyLeadInCapa').enable();
			this.$('#groundFloorCn').enable();
			this.$('#topFloorCn').enable();
			this.$('#underFloorCn').enable();
			this.$('#highHt').enable();
			this.$('#highFllorHt').enable();
			this.$('#highFllorHtPos').enable();
			this.$('#baseSideDp').enable();
			this.$('#floorArRate').enable();
			this.$('#capaRate').enable();
			this.$('#dtMaxUsageCn').enable();
			this.$('#dtUsageCn').enable();
			this.$('#bldMntnMngSysYn').enable();
			this.$('#mntnMngAddFcltyYn').enable();
			this.$('#apptTp').enable();
			this.$('#bridgeStartLoc').enable();
			this.$('#bridgeEndLoc').enable();
			this.$('#planLiveWght').enable();
			this.$('#allowPassWght').enable();
			this.$('#extLt').enable();
			this.$('#extSpanCn').enable();
			this.$('#extMaxSpanLt').enable();
			this.$('#paveWd').enable();
			this.$('#roadWd').enable();
			this.$('#sumPaveRoadWd').enable();
			this.$('#upRoadCn').enable();
			this.$('#downRoadCn').enable();
			this.$('#sumUpDownRoadCn').enable();
			this.$('#spanComp').enable();
			this.$('#mainSpanFmt').enable();
			this.$('#sunSpanFmt').enable();
			this.$('#propSe').enable();
			this.$('#buldConnSe').enable();
			this.$('#subPassLmtHt').enable();
			this.$('#bridgePierFmt1').enable();
			this.$('#bridgePierCn1').enable();
			this.$('#bridgePierBaseFmt1').enable();
			this.$('#bridgePierFmt2').enable();
			this.$('#bridgePierCn2').enable();
			this.$('#bridgePierBaseFmt2').enable();
			this.$('#bridgePropFmt1').enable();
			this.$('#bridgePropBaseFmt1').enable();
			this.$('#bridgePropFmt2').enable();
			this.$('#bridgePropBaseFmt2').enable();
			this.$('#crossRoute').enable();
			this.$('#crossRiverHighDpwt').enable();
			this.$('#popupFcltsMngGroupNo').enable();
			this.$('#popupFcltsMngGroupNo').removeClass('ui-state-disabled');
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
			this.$('#btnDetailPrint').enable();
			this.$('#btnDetailPrint').removeClass('ui-state-disabled');
			this.$('#btnFirstData').enable();
			this.$('#btnFirstData').removeClass('ui-state-disabled');
			this.$('#btnPrevData').enable();
			this.$('#btnPrevData').removeClass('ui-state-disabled');
			this.$('#btnNextData').enable();
			this.$('#btnNextData').removeClass('ui-state-disabled');
			this.$('#btnLastData').enable();
			this.$('#btnLastData').removeClass('ui-state-disabled');
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
			this.$('#baseFmt').disable();
			this.$('#strctFmt').disable();
			this.$('#ar').disable();
			this.$('#archAr').disable();
			this.$('#plotAr').disable();
			this.$('#mainUsagePrpos').disable();
			this.$('#prkAr').disable();
			this.$('#isdPrkAr').disable();
			this.$('#osdPrkAr').disable();
			this.$('#prkCnt').disable();
			this.$('#isdPrkCnt').disable();
			this.$('#osdPrkCnt').disable();
			this.$('#exhaustDuctEnnc').disable();
			this.$('#vntltnArcndtMthd').disable();
			this.$('#wrtTankLoc').disable();
			this.$('#sbtLoc').disable();
			this.$('#oilSavefcltyLoc').disable();
			this.$('#swgClupfcltyLoc').disable();
			this.$('#liftCntPsngr').disable();
			this.$('#liftCntCargo').disable();
			this.$('#liftCntEmgcy').disable();
			this.$('#liftOperMthd').disable();
			this.$('#clngEnnc').disable();
			this.$('#clngSrc').disable();
			this.$('#htngEnnc').disable();
			this.$('#htngSrc').disable();
			this.$('#spictankFmt').disable();
			this.$('#elctyLeadInCapa').disable();
			this.$('#groundFloorCn').disable();
			this.$('#topFloorCn').disable();
			this.$('#underFloorCn').disable();
			this.$('#highHt').disable();
			this.$('#highFllorHt').disable();
			this.$('#highFllorHtPos').disable();
			this.$('#baseSideDp').disable();
			this.$('#floorArRate').disable();
			this.$('#capaRate').disable();
			this.$('#dtMaxUsageCn').disable();
			this.$('#dtUsageCn').disable();
			this.$('#bldMntnMngSysYn').disable();
			this.$('#mntnMngAddFcltyYn').disable();
			this.$('#apptTp').disable();
			this.$('#bridgeStartLoc').disable();
			this.$('#bridgeEndLoc').disable();
			this.$('#planLiveWght').disable();
			this.$('#allowPassWght').disable();
			this.$('#extLt').disable();
			this.$('#extSpanCn').disable();
			this.$('#extMaxSpanLt').disable();
			this.$('#paveWd').disable();
			this.$('#roadWd').disable();
			this.$('#sumPaveRoadWd').disable();
			this.$('#upRoadCn').disable();
			this.$('#downRoadCn').disable();
			this.$('#sumUpDownRoadCn').disable();
			this.$('#spanComp').disable();
			this.$('#mainSpanFmt').disable();
			this.$('#sunSpanFmt').disable();
			this.$('#propSe').disable();
			this.$('#buldConnSe').disable();
			this.$('#subPassLmtHt').disable();
			this.$('#bridgePierFmt1').disable();
			this.$('#bridgePierCn1').disable();
			this.$('#bridgePierBaseFmt1').disable();
			this.$('#bridgePierFmt2').disable();
			this.$('#bridgePierCn2').disable();
			this.$('#bridgePierBaseFmt2').disable();
			this.$('#bridgePropFmt1').disable();
			this.$('#bridgePropBaseFmt1').disable();
			this.$('#bridgePropFmt2').disable();
			this.$('#bridgePropBaseFmt2').disable();
			this.$('#crossRoute').disable();
			this.$('#crossRiverHighDpwt').disable();
			this.$('#popupFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDetailPrint').disable({disableClass:"ui-state-disabled"});
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
	this.$('#baseFmt').disable();
	this.$('#strctFmt').disable();
	this.$('#ar').disable();
	this.$('#archAr').disable();
	this.$('#plotAr').disable();
	this.$('#mainUsagePrpos').disable();
	this.$('#prkAr').disable();
	this.$('#isdPrkAr').disable();
	this.$('#osdPrkAr').disable();
	this.$('#prkCnt').disable();
	this.$('#isdPrkCnt').disable();
	this.$('#osdPrkCnt').disable();
	this.$('#exhaustDuctEnnc').disable();
	this.$('#vntltnArcndtMthd').disable();
	this.$('#wrtTankLoc').disable();
	this.$('#sbtLoc').disable();
	this.$('#oilSavefcltyLoc').disable();
	this.$('#swgClupfcltyLoc').disable();
	this.$('#liftCntPsngr').disable();
	this.$('#liftCntCargo').disable();
	this.$('#liftCntEmgcy').disable();
	this.$('#liftOperMthd').disable();
	this.$('#clngEnnc').disable();
	this.$('#clngSrc').disable();
	this.$('#htngEnnc').disable();
	this.$('#htngSrc').disable();
	this.$('#spictankFmt').disable();
	this.$('#elctyLeadInCapa').disable();
	this.$('#groundFloorCn').disable();
	this.$('#topFloorCn').disable();
	this.$('#underFloorCn').disable();
	this.$('#highHt').disable();
	this.$('#highFllorHt').disable();
	this.$('#highFllorHtPos').disable();
	this.$('#baseSideDp').disable();
	this.$('#floorArRate').disable();
	this.$('#capaRate').disable();
	this.$('#dtMaxUsageCn').disable();
	this.$('#dtUsageCn').disable();
	this.$('#bldMntnMngSysYn').disable();
	this.$('#mntnMngAddFcltyYn').disable();
	this.$('#apptTp').disable();
	this.$('#bridgeStartLoc').disable();
	this.$('#bridgeEndLoc').disable();
	this.$('#planLiveWght').disable();
	this.$('#allowPassWght').disable();
	this.$('#extLt').disable();
	this.$('#extSpanCn').disable();
	this.$('#extMaxSpanLt').disable();
	this.$('#paveWd').disable();
	this.$('#roadWd').disable();
	this.$('#sumPaveRoadWd').disable();
	this.$('#upRoadCn').disable();
	this.$('#downRoadCn').disable();
	this.$('#sumUpDownRoadCn').disable();
	this.$('#spanComp').disable();
	this.$('#mainSpanFmt').disable();
	this.$('#sunSpanFmt').disable();
	this.$('#propSe').disable();
	this.$('#buldConnSe').disable();
	this.$('#subPassLmtHt').disable();
	this.$('#bridgePierFmt1').disable();
	this.$('#bridgePierCn1').disable();
	this.$('#bridgePierBaseFmt1').disable();
	this.$('#bridgePierFmt2').disable();
	this.$('#bridgePierCn2').disable();
	this.$('#bridgePierBaseFmt2').disable();
	this.$('#bridgePropFmt1').disable();
	this.$('#bridgePropBaseFmt1').disable();
	this.$('#bridgePropFmt2').disable();
	this.$('#bridgePropBaseFmt2').disable();
	this.$('#crossRoute').disable();
	this.$('#crossRiverHighDpwt').disable();
	this.$('#popupFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDetailPrint').disable({disableClass:"ui-state-disabled"});
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
				this.makeFormValues('#cvlEngDetailAreaForm', {});
				this.makeDivValues('#cvlEngDetailAreaForm', {});
				this.makeFormValues('#archDetailAreaForm', {});
				this.makeDivValues('#archDetailAreaForm', {});
				this.makeFormValues('#bridgeDetailAreaForm', {});
				this.makeDivValues('#bridgeDetailAreaForm', {});
				this.disableDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.makeFormValues('#cvlEngDetailAreaForm', {});
				this.makeDivValues('#cvlEngDetailAreaForm', {});
				this.makeFormValues('#archDetailAreaForm', {});
				this.makeDivValues('#archDetailAreaForm', {});
				this.makeFormValues('#bridgeDetailAreaForm', {});
				this.makeDivValues('#bridgeDetailAreaForm', {});
				this.disableDetailInputItem();
			}
			this.changeDetailAreaForm(this.$('#fcltsNo').val());
			break;
		case 'qcMngTab':
			if (this._mainFcltsMngGroupNo != "") {
				if (this._mainFcltsMngGroupNo != this._qcMngFcltsMngGroupNo) {
					this._qcMngFcltsMngGroupNo = this._mainFcltsMngGroupNo;
					var sFcltsJobSe = this.$('#sFcltsJobSe').val();
					var detailOpt = {
										'fcltsMngGroupNo':this._qcMngFcltsMngGroupNo,
										'fcltsJobSe':sFcltsJobSe
									};
					this.$('#qcPlanGrid').flexOptions({params:detailOpt}).flexReload();
					this.$('#qcHistGrid').flexOptions({params:detailOpt}).flexReload();
				}
			} else {
				this._qcMngFcltsMngGroupNo = '';
				this.$('#qcPlanGrid').flexEmptyData();
				this.$('#qcHistGrid').flexEmptyData();
			}
			break;
		case 'mntnRprTab':
			if (this._mainFcltsMngGroupNo != "") {
				if (this._mainFcltsMngGroupNo != this._mntnRprFcltsMngGroupNo) {
					this._mntnRprFcltsMngGroupNo = this._mainFcltsMngGroupNo;
					var sFcltsJobSe = this.$('#sFcltsJobSe').val();
					var detailOpt = {
							'fcltsMngGroupNo':this._qcMngFcltsMngGroupNo,
							'fcltsJobSe':sFcltsJobSe
						};
					this.$('#mntnPlanGrid').flexOptions({params:detailOpt}).flexReload();
					this.$('#mntnHistGrid').flexOptions({params:detailOpt}).flexReload();
				}
			} else {
				this._mntnRprFcltsMngGroupNo = '';
				this.$('#mntnPlanGrid').flexEmptyData();
				this.$('#mntnHistGrid').flexEmptyData();
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
							<th>시설물 번호</th>
							<td>
								<input id="sFcltsNo" type="text" size="16" maxlength="14"/>
							</td>
							<th>시설물 명</th>
							<td>
								<input id="sFcltsNm" type="text" size="30" maxlength="80"/>
							</td>
							<th>시설물 종별</th>
							<td>
								<select id="sFcltsGbn">
									<option value="" selected>선택</option>
									<option value="1">1종</option>
									<option value="2">2종</option>
									<option value="3">1종/2종</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th>조회 구분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="" selected>전체</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
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
				<li><a href="#qcMngTab" class="emdTab">점검 관리 내역</a></li>
				<li><a href="#mntnRprTab" class="emdTab">유지 보수 내역</a></li>
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
									<input type="text" size="25" id="sumCnstrctAmt" class="ygpaNumber" disabled="disabled"/>
								</td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">추가</button>
									<button id="btnDelete" class="buttonDelete">삭제</button>
									<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
									<button id="btnPrint" data-role="printPage" data-search-option="detailForm" data-url='/fclty/selectFcltReportMngPrint.do'>시설물관리대장인쇄</button>
									<button id="btnHwp" data-role="printDown" data-filename="검사조서.hwp" data-url='/fclty/selectFcltReportMngPrintHwp.do' data-search-option="detailForm">H　W　P 　</button>
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
								<th style="width:10%; height:18px;">시설물　　번호</th>
								<td>
									<input type="text" id="fcltsNo" size="33" maxlength="14"/>
								</td>
								<th style="width:10%; height:18px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="hidden" id="fcltsJobSe">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8"/>
									<input type="text" size="60" id="fcltsMngGroupNm" disabled/>
									<button id="popupFcltsMngGroupNo" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">노　　　　　선</th>
								<td>
									<input type="text" id="route" size="33" maxlength="20"/>
								</td>
								<th style="width:10%; height:18px;">종　별／영10조</th>
								<td>
									<select id="fcltsGbn">
										<option value="" selected>선택</option>
										<option value="1">1종</option>
										<option value="2">2종</option>
										<option value="3">1종/2종</option>
										<option value="9">기타</option>
									</select>
									<select id="erqProofPlanApplcEnnc">
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">시설물　　종류</th>
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
								<th style="width:10%; height:18px;">시　설　물　명</th>
								<td>
									<input type="text" id="fcltsNm" size="33" maxlength="80"/>
								</td>
								<th style="width:10%; height:18px;">위　　　　　치</th>
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
								<th style="width:10%; height:18px;">관　리　주　체</th>
								<td>
									<input type="text" id="mngMainbd" size="33" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">관리주체　구분</th>
								<td>
									<input type="text" id="mngMainbdSe" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">소　　유　　자</th>
								<td>
									<input type="text" id="owner" size="33" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">준　　공　　일</th>
								<td>
									<input type="text" size="30" id="bldDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">하자책임만료일</th>
								<td>
									<input type="text" size="30" id="flawEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">제원/안전/보수</th>
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
								<th style="width:10%; height:18px;">설　계　기　간</th>
								<td>
									<input type="text" size="11" id="planBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="planEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">설　　계　　자</th>
								<td>
									<input type="text" size="33" id="planner" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">설계도서　보존</th>
								<td>
									<input type="text" size="33" id="planBookMnten" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">공　사　기　간</th>
								<td>
									<input type="text" size="11" id="cnstBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="cnstEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">시　　공　　자</th>
								<td>
									<input type="text" size="33" id="cnstrtr" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">총　공　사　비</th>
								<td>
									<input type="text" size="30" id="cnstrctAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">감　리　기　간</th>
								<td>
									<input type="text" size="11" id="inspectBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="inspectEndDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">감　　리　　자</th>
								<td>
									<input type="text" size="33" id="inspector" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">공사　　발주자</th>
								<td>
									<input type="text" size="33" id="cnstOrderBody" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">공　　사　　명</th>
								<td>
									<input type="text" size="33" id="cnstNm" maxlength="255"/>
								</td>
								<th style="width:10%; height:18px;">공사　　감독관</th>
								<td>
									<input type="text" size="33" id="cnstSupervisor" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">작성일／작성자</th>
								<td>
									<input type="text" size="12" id="wrtDt" class="emdcal"/>／
									<input type="text" size="13" id="wrtUsr" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">비　　　　　고</th>
								<td colspan="3">
									<input type="text" size="91" id="basicRm" maxlength="1000"/>
								</td>
								<th style="width:10%; height:18px;">수정일／수정자</th>
								<td>
									<input type="text" size="12" id="lastUpdtDt" class="emdcal"/>／
									<input type="text" size="13" id="lastUpdtUsr" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">기타　상세제원</th>
								<td colspan="5">
									<input type="text" size="149" id="etcDtlsSpec" maxlength="500"/>
								</td>
							</tr>
						</table>
					</form>
<!-- -->
					<form id="cvlEngDetailAreaForm">
						<table id="cvlEngDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">규　　　　　격</th>
								<td>
									최대계류 선박규모 : <input type="text" size="24" id="maxShipScl" maxlength="20"/>
									&nbsp;/&nbsp;
									연장 : <input type="text" size="23" id="extd" maxlength="20"/>
									&nbsp;/&nbsp;
									천단고 : <input type="text" size="24" id="upsideAlt" maxlength="20"/>
									&nbsp;/&nbsp;
									수심 : <input type="text" size="24" id="dpwt" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">구　조(중력식)</th>
								<td>
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
								<th style="width:10%; height:18px;">구　조(잔교식)</th>
								<td>
									말뚝식 (구경,연장,본수) : <input type="text" id="fmt2Desc11" size="16" maxlength="50"/>
									<input type="text" id="fmt2Desc12" size="16" maxlength="50"/>
									<input type="text" id="fmt2Desc13" size="16" maxlength="50"/>
									&nbsp;/&nbsp;
									원통식 : <input type="text" id="fmt2Desc2" size="23" maxlength="50"/>
									&nbsp;/&nbsp;
									교각식 : <input type="text" id="fmt2Desc3" size="23" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">구조(널말뚝식)</th>
								<td>
									규격 : <input type="text" id="fmt3Desc1" size="30" maxlength="50"/>
									&nbsp;/&nbsp;
									기타 : <input type="text" id="fmt3Desc2" size="102" maxlength="100"/>
								</td>
							</tr>
						</table>
					</form>
<!-- -->
<!-- -->
					<form id="archDetailAreaForm">
						<table id="archDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">주　사용　용도</th>
								<td>
									<input type="text" size="33" id="mainUsagePrpos" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">구　조　형　식</th>
								<td>
									<input type="text" size="33" id="strctFmt" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">기　초　형　식</th>
								<td>
									<input type="text" size="33" id="baseFmt" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">층　　　　　수</th>
								<td colspan="3">
									지상 (옥탑제외) : <input type="text" id="groundFloorCn" size="10" maxlength="10"/> /
									옥탑 : <input type="text" id="topFloorCn" size="10" maxlength="10"/> /
									지하 : <input type="text" id="underFloorCn" size="10" maxlength="10"/> /
									최고높이 : <input type="text" id="highHt" size="12" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">최　고　층　고</th>
								<td>
									높이 : <input type="text" id="highFllorHt" size="8" maxlength="10"/> /
									해당층 : <input type="text" id="highFllorHtPos" size="7" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">면적/건폐/용적</th>
								<td colspan="3">
									대지 :<input type="text" id="plotAr" size="10" maxlength="20"/> /
									건축 :<input type="text" id="archAr" size="10" maxlength="20"/> /
									연면적 :<input type="text" id="ar" size="10" maxlength="20"/> /
									건폐율 :<input type="text" id="floorArRate" size="7" maxlength="10"/> /
									용적율:<input type="text" id="capaRate" size="7" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">지정형태／깊이</th>
								<td>
									<input type="text" size="14" id="apptTp" maxlength="20"/>／
									<input type="text" size="14" id="baseSideDp" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">주　차　시　설</th>
								<td colspan="5">
									주차면적:<input type="text" id="prkAr" size="8" maxlength="20"/> /
									옥내면적:<input type="text" id="isdPrkAr" size="8" maxlength="20"/> /
									옥외면적:<input type="text" id="osdPrkAr" size="8" maxlength="20"/> /
									주차대수:<input type="text" id="prkCnt" size="6" maxlength="10"/> /
									옥내주차:<input type="text" id="isdPrkCnt" size="6" maxlength="10"/> /
									옥외주차:<input type="text" id="osdPrkCnt" size="6" maxlength="10"/> /
									일시최대:<input type="text" id="dtMaxUsageCn" size="5" maxlength="10"/> /
									1일사용:<input type="text" id="dtUsageCn" size="6" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">승강기운영방식</th>
								<td>
									<input type="text" size="33" id="liftOperMthd" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">승객/비상/화물</th>
								<td>
									<input type="text" size="8" id="liftCntPsngr" class="ygpaNumber" maxlength="6"/>／
									<input type="text" size="8" id="liftCntEmgcy" class="ygpaNumber" maxlength="6"/>／
									<input type="text" size="8" id="liftCntCargo" class="ygpaNumber" maxlength="6"/>
								</td>
								<th style="width:10%; height:18px;">환기공조　방식</th>
								<td>
									<input type="text" size="33" id="vntltnArcndtMthd" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">오수정화/물탱크</th>
								<td>
									<input type="text" size="14" id="swgClupfcltyLoc" maxlength="50"/>／
									<input type="text" size="14" id="wrtTankLoc" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">유류저장/변전실</th>
								<td>
									<input type="text" size="14" id="oilSavefcltyLoc" maxlength="50"/>／
									<input type="text" size="14" id="sbtLoc" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">정화조　　형식</th>
								<td>
									<input type="text" size="33" id="spictankFmt" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">냉방유무／열원</th>
								<td>
									<select id="clngEnnc">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
									/
									<input type="text" size="24" id="clngSrc" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">난방유무／열원</th>
								<td>
									<select id="htngEnnc">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
									/
									<input type="text" size="24" id="htngSrc" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">전기　인입용량</th>
								<td>
									<input type="text" size="33" id="elctyLeadInCapa" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">배기닥트　유무</th>
								<td>
									<select id="exhaustDuctEnnc">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
								</td>
								<th width="10%" height="18">유지관리시스템</th>
								<td>
									<select id="bldMntnMngSysYn">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
								</td>
								<th width="10%" height="18">부　대　시　설</th>
								<td>
									<select id="mntnMngAddFcltyYn">
										<option value="Y">유</option>
										<option value="N">무</option>
									</select>
								</td>
							</tr>
						</table>
					</form>
<!-- -->
<!-- -->
					<form id="bridgeDetailAreaForm">
						<table id="bridgeDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">교량시점　위치</th>
								<td>
									<input type="text" size="33" id="bridgeStartLoc" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">교량종점　위치</th>
								<td>
									<input type="text" size="33" id="bridgeEndLoc" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">설계　　활하중</th>
								<td>
									<input type="text" size="33" id="planLiveWght" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">허용 통행 하중</th>
								<td>
									<input type="text" size="33" id="allowPassWght" maxlength="20"/>
								</td>
								<th style="width:10%; height:18px;">길　이／경간수</th>
								<td>
									<input type="text" size="14" id="extLt" maxlength="10"/> ／
									<input type="text" size="14" id="extSpanCn" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">최대　　경간장</th>
								<td>
									<input type="text" size="33" id="extMaxSpanLt" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">　폭　(보　도)</th>
								<td>
									<input type="text" id="paveWd" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">　폭　(차　도)</th>
								<td>
									<input type="text" id="roadWd" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">　폭　(　계　)</th>
								<td>
									<input type="text" id="sumPaveRoadWd" size="33" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">차　로(상　행)</th>
								<td>
									<input type="text" id="upRoadCn" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">차　로(하　행)</th>
								<td>
									<input type="text" id="downRoadCn" size="33" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">차　로(　계　)</th>
								<td>
									<input type="text" id="sumUpDownRoadCn" size="33" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">경　간　구　성</th>
								<td>
									<input type="text" size="33" id="spanComp" maxlength="100"/>
								</td>
								<th width="10%" height="18">주　경간　형식</th>
								<td>
									<input type="text" size="33" id="mainSpanFmt" maxlength="50"/>
								</td>
								<th width="10%" height="18">부　경간　형식</th>
								<td>
									<input type="text" size="33" id="subSpanFmt" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">받　침　종　류</th>
								<td>
									<input type="text" size="33" id="propSe" maxlength="20"/>
								</td>
								<th width="10%" height="18">신축이음　종류</th>
								<td>
									<input type="text" size="33" id="buldConnSe" maxlength="20"/>
								</td>
								<th width="10%" height="18">통과제한　높이</th>
								<td>
									<input type="text" size="33" id="subPassLmtHt" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">교　각　형　식</th>
								<td>
									<input type="text" size="33" id="bridgePierFmt1" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">교　각　갯　수</th>
								<td>
									<input type="text" size="33" id="bridgePierCn1" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">교각　기초형식</th>
								<td>
									<input type="text" size="33" id="bridgePierBaseFmt1" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">교　대　형　식</th>
								<td>
									<input type="text" size="33" id="bridgePropFmt" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">교대　기초형식</th>
								<td>
									<input type="text" size="33" id="bridgePropBaseFmt" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">교차노선／수심</th>
								<td>
									<input type="text" size="14" id="crossRoute" maxlength="50"/> ／
									<input type="text" size="14" id="crossRiverHighDpwt" maxlength="10"/>
								</td>
							</tr>
						</table>
					</form>
<!-- -->
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
								<button id="btnDetailPrint" data-role="printPage" data-search-option="detailForm" data-url='/fclty/selectFcltReportMngPrint.do'>시설물관리대장인쇄</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 214. TAB 3 AREA (QC MNG) -->
			<div id="qcMngTab" class="emdTabPage" style="overflow:scroll;">
				<form id="qcMngForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">안전점검 및 정밀안전진단 계획</th>
						</tr>
					</table>
					<table id="qcPlanGrid" style="display:none"></table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">안전점검 및 정밀안전진단 이력</th>
						</tr>
					</table>
					<table id="qcHistGrid" style="display:none"></table>
				</form>
			</div>
			<!-- 215. TAB 4 AREA (MNTN RPR) -->
			<div id="mntnRprTab" class="emdTabPage" style="overflow:scroll;">
				<form id="mntnRprForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">보수 및 보강 계획</th>
						</tr>
					</table>
					<table id="mntnPlanGrid" style="display:none"></table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">보수 및 보강 이력</th>
						</tr>
					</table>
					<table id="mntnHistGrid" style="display:none"></table>
				</form>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
