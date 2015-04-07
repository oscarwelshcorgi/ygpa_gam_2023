<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtMng.jsp
 * @Description : 시설물 계약관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.29    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.29
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
 * @FUNCTION NAME : GamFcltyCtrtMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyCtrtMngModule() {}

GamFcltyCtrtMngModule.prototype = new EmdModule(1000, 645);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngList.do',
		dataType : 'json',
		colModel : [
					{display:'계약 번호',			name:'ctrtNo',				width:120,		sortable:false,		align:'center'},
					{display:'구분',				name:'ctrtSeNm',			width:60,		sortable:false,		align:'center'},
					{display:'발주 방식',			name:'orderMthd',			width:100,		sortable:false,		align:'left'},
					{display:'계약 명',				name:'ctrtNm',				width:200,		sortable:false,		align:'left'},
					{display:'계약 방법',			name:'ctrtMth',				width:100,		sortable:false,		align:'left'},
					{display:'계약 일자',			name:'ctrtDt',				width:80,		sortable:false,		align:'center'},
					{display:'계약 금액',			name:'ctrtAmt',				width:100,		sortable:false,		align:'right'},
					{display:'조달 공고 번호',		name:'prcuPblancNo',		width:100,		sortable:false,		align:'left'},
					{display:'입찰 공고 번호',		name:'bidPblancNo',			width:100,		sortable:false,		align:'left'},
					{display:'입찰 공고 일자',		name:'bidPblancDt',			width:90,		sortable:false,		align:'center'},
					{display:'입찰 일자',			name:'bidDt',				width:80,		sortable:false,		align:'center'},
					{display:'입찰 방법',			name:'bidMth',				width:100,		sortable:false,		align:'left'},
					{display:'낙찰 자',				name:'scsbider',			width:100,		sortable:false,		align:'left'},
					{display:'낙찰 금액',			name:'scsbidAmt',			width:100,		sortable:false,		align:'right'},
					{display:'낙찰 율',				name:'scsbidRate',			width:80,		sortable:false,		align:'right'},
					{display:'계약 시작 일자',		name:'ctrtDtFrom',			width:100,		sortable:false,		align:'center'},
					{display:'계약 종료 일자',		name:'ctrtDtTo',			width:100,		sortable:false,		align:'center'},
					{display:'계약 보증 금액',		name:'ctrtGrntyAmt',		width:100,		sortable:false,		align:'right'},
					{display:'계약 보증 방법',		name:'ctrtGrntyMthNm',		width:100,		sortable:false,		align:'left'},
					{display:'계약 검사 일자',		name:'ctrtExamDt',			width:100,		sortable:false,		align:'center'},
					{display:'원인 행위',			name:'causeAct',			width:200,		sortable:false,		align:'left'},
					{display:'등록 업체 명',		name:'registEntrpsNm',		width:150,		sortable:false,		align:'left'},
					{display:'기초 금액',			name:'baseAmt',				width:100,		sortable:false,		align:'right'},
					{display:'설계 금액',			name:'planAmt',				width:100,		sortable:false,		align:'right'},
					{display:'예정 금액',			name:'prmtAmt',				width:100,		sortable:false,		align:'right'},
					{display:'이월 예산 금액',		name:'caryFwdBdgtAmt',		width:100,		sortable:false,		align:'right'},
					{display:'감독자 1',			name:'intendant1',			width:80,		sortable:false,		align:'left'},
					{display:'감독자 2',			name:'intendant2',			width:80,		sortable:false,		align:'left'},
					{display:'감독자 3',			name:'intendant3',			width:80,		sortable:false,		align:'left'},
					{display:'담당 부서',			name:'jobChrgDeptNm',		width:100,		sortable:false,		align:'left'},
					{display:'하자 시작 일자',		name:'flawDtFrom',			width:100,		sortable:false,		align:'center'},
					{display:'하자 종료 일자',		name:'flawDtTo',			width:100,		sortable:false,		align:'center'},
					{display:'연대 보증',			name:'sldrtGrnty',			width:100,		sortable:false,		align:'left'},
					{display:'현장 설명',			name:'siteDesc',			width:200,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumCtrtAmt').val($.number(data.sumCtrtAmt));
			module.$('#sumPlanAmt').val($.number(data.sumPlanAmt));
			module.$('#sumPrmtAmt').val($.number(data.sumPrmtAmt));
			module.$('#sumScsbidAmt').val($.number(data.sumScsbidAmt));
			module.$('#sumBaseAmt').val($.number(data.sumBaseAmt));
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.ctrtNo;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.ctrtNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#joinGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngJoinContrList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',			name:'joinSeq',				width:50,		sortable:false,		align:'center'},
					{display:'업체 명',			name:'joinEntrpsNm',		width:140,		sortable:false,		align:'left'},
					{display:'대표자',			name:'joinRprsntv',			width:80,		sortable:false,		align:'left'},
					{display:'지분 율',			name:'qotaRate',			width:80,		sortable:false,		align:'right'},
					{display:'업종',			name:'induty',				width:100,		sortable:false,		align:'left'},
					{display:'주요 품목',		name:'stplPrdlst',			width:100,		sortable:false,		align:'left'},
					{display:'사업자 번호',		name:'joinBsnmNo',			width:100,		sortable:false,		align:'center'},
					{display:'거래 관계',		name:'dealRelate',			width:80,		sortable:false,		align:'left'},
					{display:'전화 번호',		name:'joinTlphonNo',		width:100,		sortable:false,		align:'left'},
					{display:'FAX 번호',		name:'joinFaxNo',			width:100,		sortable:false,		align:'left'},
					{display:'우편 번호',		name:'postNo',				width:80,		sortable:false,		align:'center'},
					{display:'도로명 주소',		name:'roadnmAdres',			width:250,		sortable:false,		align:'left'},
					{display:'지번 주소',		name:'lnmAdres',			width:250,		sortable:false,		align:'left'},
					{display:'담당자',			name:'charger',				width:80,		sortable:false,		align:'left'},
					{display:'담당자 직위',		name:'chargerOfcPos',		width:100,		sortable:false,		align:'left'},
					{display:'담당자 HP',		name:'chargerMoblphonNo',	width:100,		sortable:false,		align:'left'},
					{display:'담당자 E-MAIL',	name:'chargerEmail',		width:150,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '150'
	});

	this.$("#joinGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectJoinData();
	});

	this.$("#joinGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._joinmode = 'modify';
		module._joinKeyValue = row.joinCtrtNo;
		module._joinSeq = row.joinSeq;
		module.$('#joinSeq').val(row.joinSeq);
		module.loadJoinDetail('joinTab');
		module.enableJoinDetailInputItem();
	});

	this.$("#subGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngSubctrtList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',				name:'subSeq',				width:50,		sortable:false,		align:'center'},
					{display:'업체 명',				name:'subEntrpsNm',			width:120,		sortable:false,		align:'left'},
					{display:'대금 지급 합의',		name:'moneyPymntAgreeNm',	width:110,		sortable:false,		align:'left'},
					{display:'공종',				name:'workClass',			width:140,		sortable:false,		align:'left'},
					{display:'하도급 율',			name:'subctrtRate',			width:80,		sortable:false,		align:'right'},
					{display:'원도급 금액',			name:'orginlContrAmt',		width:120,		sortable:false,		align:'right'},
					{display:'하도급 계약 금액',	name:'subctrtCtrtAmt',		width:120,		sortable:false,		align:'right'},
					{display:'계약 시작 일자',		name:'subctrtCtrtDtFrom',	width:100,		sortable:false,		align:'center'},
					{display:'계약 종료 일자',		name:'subctrtCtrtDtTo',		width:100,		sortable:false,		align:'center'}
					],
		showTableToggleBtn : false,
		height : '230'
	});

	this.$("#subGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectSubData();
	});

	this.$("#subGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._submode = 'modify';
		module._subKeyValue = row.subCtrtNo;
		module._subSeq = row.subSeq;
		module.$('#subSeq').val(row.subSeq);
		module.loadSubDetail('subTab');
		module.enableSubDetailInputItem();
	});

	this.$("#changeGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngChangeList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',					name:'changeSeq',			width:50,		sortable:false,		align:'center'},
					{display:'변경 구분',				name:'changeSeNm',			width:100,		sortable:false,		align:'left'},
					{display:'변경 일자',				name:'changeDt',			width:100,		sortable:false,		align:'center'},
					{display:'변경 사유',				name:'changeRsn',			width:210,		sortable:false,		align:'left'},
					{display:'변경 계약 시작 일자',		name:'changeCtrtDtFrom',	width:120,		sortable:false,		align:'center'},
					{display:'변경 계약 종료 일자',		name:'changeCtrtDtTo',		width:120,		sortable:false,		align:'center'},
					{display:'변경 계약 금액',			name:'changeCtrtAmt',		width:120,		sortable:false,		align:'right'},
					{display:'최종 계약 금액',			name:'lastCtrtAmt',			width:120,		sortable:false,		align:'right'},
					{display:'비고',					name:'changeRm',			width:250,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height: '210'
	});

	this.$("#changeGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectChangeData();
	});

	this.$("#changeGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._changemode = 'modify';
		module._changeKeyValue = row.changeCtrtNo;
		module._changeSeq = row.changeSeq;
		module.$('#changeSeq').val(row.changeSeq);
		module.loadChangeDetail('changeTab');
		module.enableChangeDetailInputItem();
	});

	this.$("#pymntGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngMoneyPymntList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',				name:'pymntSeq',			width:50,		sortable:false,		align:'center'},
					{display:'지급 분류',			name:'pymntClNm',			width:100,		sortable:false,		align:'center'},
					{display:'지급 일자',			name:'pymntDt',				width:100,		sortable:false,		align:'center'},
					{display:'금회 기성 금액',		name:'thisTimeEstbAmt',		width:120,		sortable:false,		align:'right'},
					{display:'선금 정산 금액',		name:'depositExcclcAmt',	width:120,		sortable:false,		align:'right'},
					{display:'지급 금액',			name:'pymntAmt',			width:120,		sortable:false,		align:'right'},
					{display:'지급 누계 금액',		name:'pymntAggrAmt',		width:120,		sortable:false,		align:'right'},
					{display:'비고',				name:'pymntRm',				width:215,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '230'
	});

	this.$("#pymntGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectPymntData();
	});

	this.$("#pymntGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._pymntmode = 'modify';
		module._pymntKeyValue = row.pymntCtrtNo;
		module._pymntSeq = row.pymntSeq;
		module.$('#pymntSeq').val(row.pymntSeq);
		module.loadPymntDetail('pymntTab');
		module.enablePymntDetailInputItem();
	});

	this.$("#caryFwdGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',				name:'caryFwdSeq',			width:50,		sortable:false,		align:'center'},
					{display:'이행 이월 년도',		name:'fulfillCaryFwdYear',	width:150,		sortable:false,		align:'center'},
					{display:'이행 금액',			name:'fulfillAmt',			width:250,		sortable:false,		align:'right'},
					{display:'이월 금액',			name:'caryFwdAmt',			width:250,		sortable:false,		align:'right'}
					],
		showTableToggleBtn : false,
		height : '280'
	});

	this.$("#caryFwdGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectCaryFwdData();
	});

	this.$("#caryFwdGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._caryfwdmode = 'modify';
		module._caryFwdKeyValue = row.caryFwdCtrtNo;
		module._caryFwdSeq = row.caryFwdSeq;
		module.$('#caryFwdSeq').val(row.caryFwdSeq);
		module.loadCaryFwdDetail('caryFwdTab');
		module.enableCaryFwdDetailInputItem();
	});

	this.$("#scsbidGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngScsbidInfoList.do',
		dataType : 'json',
		colModel : [
					{display:'낙찰 순위',		name:'scsbidRank',			width:90,		sortable:false,		align:'center'},
					{display:'업체 명',			name:'scsbidEntrpsNm',		width:160,		sortable:false,		align:'left'},
					{display:'대표자',			name:'scsbidRprsntv',		width:120,		sortable:false,		align:'left'},
					{display:'사업자 번호',		name:'scsbidBsnmNo',		width:110,		sortable:false,		align:'center'},
					{display:'전화 번호',		name:'scsbidTlphonNo',		width:120,		sortable:false,		align:'center'},
					{display:'FAX 번호',		name:'scsbidFaxNo',			width:120,		sortable:false,		align:'center'},
					{display:'비고',			name:'scsbidRm',			width:220,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '230'
	});

	this.$("#scsbidGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectScsbidData();
	});

	this.$("#scsbidGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._scsbidmode = 'modify';
		module._scsbidKeyValue = row.scsbidCtrtNo;
		module._scsbidSeq = row.scsbidSeq;
		module.$('#scsbidSeq').val(row.scsbidSeq);
		module.loadScsbidDetail('scsbidTab');
		module.enableScsbidDetailInputItem();
	});

	this.$("#flawGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtMngFlawGrntyList.do',
		dataType : 'json',
		colModel : [
					{display:'순번',					name:'flawGrntySeq',			width:50,		sortable:false,		align:'center'},
					{display:'하자 만료 일',			name:'flawEndDt',				width:80,		sortable:false,		align:'center'},
					{display:'보증 시작 일',			name:'flawGrntyStartDt',		width:80,		sortable:false,		align:'center'},
					{display:'보증 종료 일',			name:'flawGrntyEndDt',			width:80,		sortable:false,		align:'center'},
					{display:'보증 가입 금액',			name:'flawGrntySrbctAmt',		width:100,		sortable:false,		align:'right'},
					{display:'보증 율',					name:'flawGrntyRate',			width:60,		sortable:false,		align:'right'},
					{display:'보증 계약 금액',			name:'flawGrntyCtrtAmt',		width:100,		sortable:false,		align:'right'},
					{display:'보험 증권 번호',			name:'flawGrntyInsuNo',			width:100,		sortable:false,		align:'left'},
					{display:'보증 보험 회사',			name:'flawGrntyInsuCmpy',		width:150,		sortable:false,		align:'left'},
					{display:'보증 보험 회사 주소',		name:'flawGrntyInsuAddr',		width:200,		sortable:false,		align:'left'},
					{display:'대표자',					name:'flawGrntyInsuRprsntv',	width:80,		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : '210'
	});

	this.$("#flawGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectFlawGrntyData();
	});

	this.$("#flawGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._flawgrntymode = 'modify';
		module._flawGrntyKeyValue = row.flawGrntyCtrtNo;
		module._flawGrntySeq = row.flawGrntySeq;
		module.$('#flawGrntySeq').val(row.flawGrntySeq);
		module.loadFlawGrntyDetail('joinTab');
		module.enableFlawGrntyDetailInputItem();
	});

	this.$("#sRegistEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getSearchEntrpsNm();
	});

	this.$("#registEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getRegistEntrpsNm();
	});

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._joinmode = 'query';
	this._joinKeyValue = '';
	this._joinSeq = '';
	this._submode = 'query';
	this._subKeyValue = '';
	this._subSeq = '';
	this._changemode = 'query';
	this._changeKeyValue = '';
	this._changeSeq = '';
	this._pymntmode = 'query';
	this._pymntKeyValue = '';
	this._pymntSeq = '';
	this._caryfwdmode = 'query';
	this._caryFwdKeyValue = '';
	this._caryFwdSeq = '';
	this._scsbidmode = 'query';
	this._scsbidKeyValue = '';
	this._scsbidSeq = '';
	this._flawgrntymode = 'query';
	this._flawGrntyKeyValue = '';
	this._flawGrntySeq = '';
	this._searchButtonClick = false;
	var year = new Date().getFullYear();
	this.$('#sStartCtrtDt').val(year + '-01-01');
	this.$('#sEndCtrtDt').val(year + '-12-31');
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : getCurrentYearString
 * @DESCRIPTION   : CURRENT YEAR STRING을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getCurrentYearString = function() {

	var toDay = new Date();
	var year = toDay.getFullYear();
	return year + "";

};

<%
/**
 * @FUNCTION NAME : getToDayString
 * @DESCRIPTION   : TODAY STRING을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getToDayString = function() {

	var toDay = new Date();
	var year = toDay.getFullYear();
	var month = toDay.getMonth() + 1;
	var day = toDay.getDate();
	var toDayString = "";
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			toDayString = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			toDayString = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			toDayString = year + "-" + month + "-" + "0" + day;
		} else {
			toDayString = year + "-" + month + "-" + day;
		}
	}
	return toDayString;

};

<%
/**
 * @FUNCTION NAME : addDateString
 * @DESCRIPTION   : ADD DATE STRING을 구한다.
 * @PARAMETER     :
 *   1. dateString - YEAR STRING
 *   2. addDays - ADD DAYS
**/
%>
GamFcltyCtrtMngModule.prototype.addDateString = function(dateString, addDays) {

	var sourceDateString = dateString;
	var newDateString = "";
	var year = "";
	var month = "";
	var day = "";
	if (dateString == "") {
		var toDay = new Date();
		year = toDay.getFullYear();
		month = toDay.getMonth() + 1;
		day = toDay.getDate();
		if (month >= 1 && month <= 9) {
			if (day >= 1 && day <= 9) {
				sourceDateString = year + "-" + "0" + month + "-" + "0" + day;
			} else {
				sourceDateString = year + "-" + "0" + month + "-" + day;
			}
		} else {
			if (day >= 1 && day <= 9) {
				sourceDateString = year + "-" + month + "-" + "0" + day;
			} else {
				sourceDateString = year + "-" + month + "-" + day;
			}
		}
	}
	var sourceDate = EMD.util.strToDate(sourceDateString);
	var dayOfMonth = sourceDate.getDate();
	sourceDate.setDate(dayOfMonth + addDays);
	year = sourceDate.getFullYear();
	month = sourceDate.getMonth() + 1;
	day = sourceDate.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			newDateString = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			newDateString = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			newDateString = year + "-" + month + "-" + "0" + day;
		} else {
			newDateString = year + "-" + month + "-" + day;
		}
	}
	return newDateString;

};

<%
/**
 * @FUNCTION NAME : isValidYear
 * @DESCRIPTION   : YEAR STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. yearString - YEAR STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltyCtrtMngModule.prototype.isValidYear = function(yearString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (yearString == "") {
			return false;
		}
	} else {
		if (yearString == "") {
			return true;
		}
	}
	var year = Number(yearString);
	if (year > 9999 || year < 1900) {
		return false;
	}
	return true;

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
GamFcltyCtrtMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

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
 * @FUNCTION NAME : isValidAmount
 * @DESCRIPTION   : AMOUNT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. amountValue - AMOUNT VALUE
 *   2. zeroCheckFlag - ZERO CHECK FLAG
**/
%>
GamFcltyCtrtMngModule.prototype.isValidAmount = function(amountValue, zeroCheckFlag) {

	if (zeroCheckFlag == true) {
		if (amountValue > 9999999999999999 || amountValue <= 0) {
			return false;
		}
	} else {
		if (amountValue > 9999999999999999 || amountValue < 0) {
			return false;
		}
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidRate
 * @DESCRIPTION   : RATE에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. rateValue - RATE VALUE
**/
%>
GamFcltyCtrtMngModule.prototype.isValidRate = function(rateValue) {

	if (rateValue > 1 || rateValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidPercent
 * @DESCRIPTION   : PERCENT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. percentValue - PERCENT VALUE
**/
%>
GamFcltyCtrtMngModule.prototype.isValidPercent = function(percentValue) {

	if (percentValue > 100 || percentValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidAmountFromTo
 * @DESCRIPTION   : 범위 AMOUNT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. startAmount - START AMOUNT
 *   2. endAmount - END AMOUNT
**/
%>
GamFcltyCtrtMngModule.prototype.isValidAmountFromTo = function(startAmount, endAmount) {

	if (startAmount > endAmount) {
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
GamFcltyCtrtMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

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
GamFcltyCtrtMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

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
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamFcltyCtrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchRegistEntrpsCd':
			if (msg == 'ok') {
				this.$('#sRegistEntrpsCd').val(value.entrpscd);
				this.$('#sRegistEntrpsNm').val(value.entrpsNm);
				this.$('#sStartCtrtAmt').focus();
			}
			break;
		case 'popupCtrtRegistEntrpsCd':
			if (msg == 'ok') {
				this.$('#registEntrpsCd').val(value.entrpscd);
				this.$('#registEntrpsNm').val(value.entrpsNm);
				this.$('#scsbider').focus();
			}
			break;
		case 'popupJoinEntrpsCd':
			if (msg == 'ok') {
				this.$('#joinEntrpsNm').val(value.entrpsNm);
				this.$('#joinRprsntv').val(value.rprsntvNm);
				this.$('#joinBsnmNo').val(value.bizrno);
				this.$('#induty').val(value.induty);
				this.$('#joinTlphonNo').val(value.tlphonNo);
				this.$('#joinFaxNo').val(value.fax);
				this.$('#postNo').val(value.zip);
				this.$('#lnmAdres').val(value.adres);
				this.$('#roadnmAdres').val(value.adres);
				this.$('#charger').focus();
			}
			break;
		case 'popupSubEntrpsCd':
			if (msg == 'ok') {
				this.$('#subEntrpsNm').val(value.entrpsNm);
				this.$('#workClass').focus();
			}
			break;
		case 'popupScsbidEntrpsCd':
			if (msg == 'ok') {
				this.$('#scsbidEntrpsNm').val(value.entrpsNm);
				this.$('#scsbidRprsntv').val(value.rprsntvNm);
				this.$('#scsbidBsnmNo').val(value.bizrno);
				this.$('#scsbidTlphonNo').val(value.tlphonNo);
				this.$('#scsbidFaxNo').val(value.fax);
				this.$('#scsbidRm').focus();
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
GamFcltyCtrtMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnDelete':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnCtrtInsert':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.makeFormValues('#detailForm', {});
			this.makeDivValues('#detailForm', {});
			this.disableDetailInputItem();
			this.addData();
			break;
	    case 'btnCtrtSave':
	    	this.saveData();
			break;
		case 'btnCtrtRemove':
			this.deleteData();
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
		case 'btnJoinInsert':
			this._joinmode = 'insert';
			this._joinKeyValue = '';
			this.makeFormValues('#joinForm', {});
			this.makeDivValues('#joinForm', {});
			this.disableJoinDetailInputItem();
			this.copyCtrtInfoData('joinTab');
			this.addJoinData();
			break;
	    case 'btnJoinSave':
	    	this.saveJoinData();
			break;
		case 'btnJoinRemove':
			this.deleteJoinData();
			break;
		case 'btnJoinExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnSubInsert':
			this._submode = 'insert';
			this._subKeyValue = '';
			this.makeFormValues('#subForm', {});
			this.makeDivValues('#subForm', {});
			this.disableSubDetailInputItem();
			this.copyCtrtInfoData('subTab');
			this.addSubData();
			break;
	    case 'btnSubSave':
	    	this.saveSubData();
			break;
		case 'btnSubRemove':
			this.deleteSubData();
			break;
		case 'btnSubExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnChangeInsert':
			this._changemode = 'insert';
			this._changeKeyValue = '';
			this.makeFormValues('#changeForm', {});
			this.makeDivValues('#changeForm', {});
			this.disableChangeDetailInputItem();
			this.copyCtrtInfoData('changeTab');
			this.addChangeData();
			break;
	    case 'btnChangeSave':
	    	this.saveChangeData();
			break;
		case 'btnChangeRemove':
			this.deleteChangeData();
			break;
		case 'btnChangeExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnPymntInsert':
			this._pymntmode = 'insert';
			this._pymntKeyValue = '';
			this.makeFormValues('#pymntForm', {});
			this.makeDivValues('#pymntForm', {});
			this.disablePymntDetailInputItem();
			this.copyCtrtInfoData('pymntTab');
			this.addPymntData();
			break;
	    case 'btnPymntSave':
	    	this.savePymntData();
			break;
		case 'btnPymntRemove':
			this.deletePymntData();
			break;
		case 'btnPymntExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnCaryFwdInsert':
			this._caryfwdmode = 'insert';
			this._caryFwdKeyValue = '';
			this.makeFormValues('#caryFwdForm', {});
			this.makeDivValues('#caryFwdForm', {});
			this.disableCaryFwdDetailInputItem();
			this.copyCtrtInfoData('caryFwdTab');
			this.addCaryFwdData();
			break;
	    case 'btnCaryFwdSave':
	    	this.saveCaryFwdData();
			break;
		case 'btnCaryFwdRemove':
			this.deleteCaryFwdData();
			break;
		case 'btnCaryFwdExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnScsbidInsert':
			this._scsbidmode = 'insert';
			this._scsbidKeyValue = '';
			this.makeFormValues('#scsbidForm', {});
			this.makeDivValues('#scsbidForm', {});
			this.disableScsbidDetailInputItem();
			this.copyCtrtInfoData('scsbidTab');
			this.addScsbidData();
			break;
	    case 'btnScsbidSave':
	    	this.saveScsbidData();
			break;
		case 'btnScsbidRemove':
			this.deleteScsbidData();
			break;
		case 'btnScsbidExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnFlawGrntyInsert':
			this._flawgrntymode = 'insert';
			this._flawGrntyKeyValue = '';
			this.makeFormValues('#flawGrntyForm', {});
			this.makeDivValues('#flawGrntyForm', {});
			this.disableFlawGrntyDetailInputItem();
			this.copyCtrtInfoData('flawTab');
			this.addFlawGrntyData();
			break;
	    case 'btnFlawGrntySave':
	    	this.saveFlawGrntyData();
			break;
		case 'btnFlawGrntyRemove':
			this.deleteFlawGrntyData();
			break;
		case 'btnFlawGrntyExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'popupSearchRegistEntrpsCd':
		case 'popupCtrtRegistEntrpsCd':
		case 'popupJoinEntrpsCd':
		case 'popupSubEntrpsCd':
		case 'popupScsbidEntrpsCd':
			this.doExecuteDialog(buttonId, '업체 선택', '/popup/showEntrpsInfo.do', null);
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
GamFcltyCtrtMngModule.prototype.onSubmit = function() {

	var sStartCtrtDt = this.$('#sStartCtrtDt').val();
	var sEndCtrtDt = this.$('#sEndCtrtDt').val();
	var sStartCtrtAmt = Number(this.$('#sStartCtrtAmt').val().replace(/,/gi, ""));
	var sEndCtrtAmt = Number(this.$('#sEndCtrtAmt').val().replace(/,/gi, ""));
	if (this.isValidDate(sStartCtrtDt, true) == false) {
		alert('계약 시작 일자가 부정확합니다.');
		this.$("#sStartCtrtDt").focus();
		return;
	}
	if (this.isValidDate(sEndCtrtDt, true) == false) {
		alert('계약 종료 일자가 부정확합니다.');
		this.$("#sEndCtrtDt").focus();
		return;
	}
	if (this.isValidDateFromTo(sStartCtrtDt, sEndCtrtDt, true) == false) {
		alert('계약 기간이 부정확합니다.');
		this.$("#sEndCtrtDt").focus();
		return;
	}
	if (this.isValidAmount(sStartCtrtAmt, false) == false) {
		alert('계약 시작 금액이 부정확합니다.');
		this.$("#sStartCtrtAmt").focus();
		return;
	}
	if (this.isValidAmount(sEndCtrtAmt, false) == false) {
		alert('계약 종료 금액이 부정확합니다.');
		this.$("#sEndCtrtAmt").focus();
		return;
	}
	if (this.isValidAmountFromTo(sStartCtrtAmt, sEndCtrtAmt) == false) {
		alert('계약 시작 금액이 계약 종료 금액보다 큽니다.');
		this.$("#sEndCtrtAmt").focus();
		return;
	}
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._joinmode = 'query';
	this._joinKeyValue = '';
	this._joinSeq = '';
	this._submode = 'query';
	this._subKeyValue = '';
	this._subSeq = '';
	this._changemode = 'query';
	this._changeKeyValue = '';
	this._changeSeq = '';
	this._pymntmode = 'query';
	this._pymntKeyValue = '';
	this._pymntSeq = '';
	this._caryfwdmode = 'query';
	this._caryFwdKeyValue = '';
	this._caryFwdSeq = '';
	this._scsbidmode = 'query';
	this._scsbidKeyValue = '';
	this._scsbidSeq = '';
	this._flawgrntymode = 'query';
	this._flawGrntyKeyValue = '';
	this._flawGrntySeq = '';
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
GamFcltyCtrtMngModule.prototype.loadData = function() {

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
GamFcltyCtrtMngModule.prototype.refreshData = function() {

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
GamFcltyCtrtMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else if (tabId == 'detailTab') {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngPk.do', searchVO, function(module, result){
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
GamFcltyCtrtMngModule.prototype.selectData = function() {

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
	var ctrtNo = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"ctrtNo", filter:ctrtNo}]);
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
GamFcltyCtrtMngModule.prototype.firstData = function() {

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
	var firstCtrtNo = row["ctrtNo"];
	if (firstCtrtNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"ctrtNo", filter:firstCtrtNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = firstCtrtNo;
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
GamFcltyCtrtMngModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var ctrtNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (ctrtNo == row["ctrtNo"]) {
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
	var prevCtrtNo = row["ctrtNo"];
	if (prevCtrtNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"ctrtNo", filter:prevCtrtNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = prevCtrtNo;
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
GamFcltyCtrtMngModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var ctrtNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (ctrtNo == row["ctrtNo"]) {
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
	var nextCtrtNo = row["ctrtNo"];
	if (nextCtrtNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"ctrtNo", filter:nextCtrtNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = nextCtrtNo;
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
GamFcltyCtrtMngModule.prototype.lastData = function() {

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
	var lastCtrtNo = row["ctrtNo"];
	if (lastCtrtNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"ctrtNo", filter:lastCtrtNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = lastCtrtNo;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : copyCtrtInfoData
 * @DESCRIPTION   : CTRT INFO 항목을 복사한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.copyCtrtInfoData = function(tabId) {

	var ctrtNo = this.$('#ctrtNo').val();
	var ctrtSe = this.$('#ctrtSe').val();
	var ctrtNm = this.$('#ctrtNm').val();
	var ctrtDt = this.$('#ctrtDt').val();
	var ctrtDtFrom = this.$('#ctrtDtFrom').val();
	var ctrtDtTo = this.$('#ctrtDtTo').val();
	var ctrtAmt = this.$('#ctrtAmt').val();
	var ctrtMth = this.$('#ctrtMth').val();
	var orderMthd = this.$('#orderMthd').val();
	var causeAct = this.$('#causeAct').val();
	if (tabId == "joinTab") {
		this.$('#joinCtrtNo').val(ctrtNo);
		this.$('#joinCtrtSe').val(ctrtSe);
		this.$('#joinCtrtNm').val(ctrtNm);
		this.$('#joinCauseAct').val(causeAct);
		this.$('#joinOrderMthd').val(orderMthd);
		this.$('#joinCtrtMth').val(ctrtMth);
		this.$('#joinCtrtDt').val(ctrtDt);
		this.$('#joinCtrtAmt').val(ctrtAmt);
		this.$('#joinCtrtDtFrom').val(ctrtDtFrom);
		this.$('#joinCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "subTab") {
		this.$('#subCtrtNo').val(ctrtNo);
		this.$('#subCtrtSe').val(ctrtSe);
		this.$('#subCtrtNm').val(ctrtNm);
		this.$('#subCauseAct').val(causeAct);
		this.$('#subOrderMthd').val(orderMthd);
		this.$('#subCtrtMth').val(ctrtMth);
		this.$('#subCtrtDt').val(ctrtDt);
		this.$('#subCtrtAmt').val(ctrtAmt);
		this.$('#subCtrtDtFrom').val(ctrtDtFrom);
		this.$('#subCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "changeTab") {
		this.$('#changeInfoCtrtNo').val(ctrtNo);
		this.$('#changeInfoCtrtSe').val(ctrtSe);
		this.$('#changeInfoCtrtNm').val(ctrtNm);
		this.$('#changeInfoCauseAct').val(causeAct);
		this.$('#changeInfoOrderMthd').val(orderMthd);
		this.$('#changeInfoCtrtMth').val(ctrtMth);
		this.$('#changeInfoCtrtDt').val(ctrtDt);
		this.$('#changeInfoCtrtAmt').val(ctrtAmt);
		this.$('#changeInfoCtrtDtFrom').val(ctrtDtFrom);
		this.$('#changeInfoCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "pymntTab") {
		this.$('#pymntCtrtNo').val(ctrtNo);
		this.$('#pymntCtrtSe').val(ctrtSe);
		this.$('#pymntCtrtNm').val(ctrtNm);
		this.$('#pymntCauseAct').val(causeAct);
		this.$('#pymntOrderMthd').val(orderMthd);
		this.$('#pymntCtrtMth').val(ctrtMth);
		this.$('#pymntCtrtDt').val(ctrtDt);
		this.$('#pymntCtrtAmt').val(ctrtAmt);
		this.$('#pymntCtrtDtFrom').val(ctrtDtFrom);
		this.$('#pymntCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "caryFwdTab") {
		this.$('#caryFwdCtrtNo').val(ctrtNo);
		this.$('#caryFwdCtrtSe').val(ctrtSe);
		this.$('#caryFwdCtrtNm').val(ctrtNm);
		this.$('#caryFwdCauseAct').val(causeAct);
		this.$('#caryFwdOrderMthd').val(orderMthd);
		this.$('#caryFwdCtrtMth').val(ctrtMth);
		this.$('#caryFwdCtrtDt').val(ctrtDt);
		this.$('#caryFwdCtrtAmt').val(ctrtAmt);
		this.$('#caryFwdCtrtDtFrom').val(ctrtDtFrom);
		this.$('#caryFwdCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "scsbidTab") {
		this.$('#scsbidCtrtNo').val(ctrtNo);
		this.$('#scsbidCtrtSe').val(ctrtSe);
		this.$('#scsbidCtrtNm').val(ctrtNm);
		this.$('#scsbidCauseAct').val(causeAct);
		this.$('#scsbidOrderMthd').val(orderMthd);
		this.$('#scsbidCtrtMth').val(ctrtMth);
		this.$('#scsbidCtrtDt').val(ctrtDt);
		this.$('#scsbidCtrtAmt').val(ctrtAmt);
		this.$('#scsbidCtrtDtFrom').val(ctrtDtFrom);
		this.$('#scsbidCtrtDtTo').val(ctrtDtTo);
	} else if (tabId == "flawTab") {
		this.$('#flawCtrtNo').val(ctrtNo);
		this.$('#flawCtrtSe').val(ctrtSe);
		this.$('#flawCtrtNm').val(ctrtNm);
		this.$('#flawCauseAct').val(causeAct);
		this.$('#flawOrderMthd').val(orderMthd);
		this.$('#flawCtrtMth').val(ctrtMth);
		this.$('#flawCtrtDt').val(ctrtDt);
		this.$('#flawCtrtAmt').val(ctrtAmt);
		this.$('#flawCtrtDtFrom').val(ctrtDtFrom);
		this.$('#flawCtrtDtTo').val(ctrtDtTo);
	}

};

<%
/**
 * @FUNCTION NAME : loadJoinDetail
 * @DESCRIPTION   : JOIN 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadJoinDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#joinForm', {});
		this.makeDivValues('#joinForm', {});
		this.$('#joinGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('joinTab');
		var detailOpt = this.getFormValues('#joinForm');
		this.$('#joinGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'joinTab') {
		var searchVO = this.getFormValues('#joinForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngJoinContrPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#joinForm', result.result);
				module.makeDivValues('#joinForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshJoinData
 * @DESCRIPTION   : JOIN DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshJoinData = function() {

	var searchOpt=this.makeFormArgs('#joinForm');
	this.$('#joinGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectJoinData
 * @DESCRIPTION   : JOIN DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectJoinData = function() {

	if (this._joinmode == 'query') {
		this.enableJoinDetailInputItem();
		return;
	} else if (this._joinmode != 'insert' && this._joinmode != 'modify') {
		this.enableJoinDetailInputItem();
		return;
	}
	if (this._joinKeyValue == "" || this._joinSeq == "") {
		this.disableJoinDetailInputItem();
		return;
	}
	var joinCtrtNo = this._joinKeyValue;
	var joinSeq = this._joinSeq;
	this.$("#joinGrid").selectFilterRow([{col:"joinCtrtNo", filter:joinCtrtNo},
										 {col:"joinSeq", filter:joinSeq}]);
	this._joinmode = 'modify';
	this.loadJoinDetail('joinTab');
	this.enableJoinDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadSubDetail
 * @DESCRIPTION   : SUB 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadSubDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#subForm', {});
		this.makeDivValues('#subForm', {});
		this.$('#subGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('subTab');
		var detailOpt = this.getFormValues('#subForm');
		this.$('#subGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'subTab') {
		var searchVO = this.getFormValues('#subForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngSubctrtPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#subForm', result.result);
				module.makeDivValues('#subForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshSubData
 * @DESCRIPTION   : SUB DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshSubData = function() {

	var searchOpt=this.makeFormArgs('#subForm');
	this.$('#subGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectSubData
 * @DESCRIPTION   : SUB DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectSubData = function() {

	if (this._submode == 'query') {
		this.enableSubDetailInputItem();
		return;
	} else if (this._submode != 'insert' && this._submode != 'modify') {
		this.enableSubDetailInputItem();
		return;
	}
	if (this._subKeyValue == "" || this._subSeq == "") {
		this.disableSubDetailInputItem();
		return;
	}
	var subCtrtNo = this._subKeyValue;
	var subSeq = this._subSeq;
	this.$("#subGrid").selectFilterRow([{col:"subCtrtNo", filter:subCtrtNo},
										{col:"subSeq", filter:subSeq}]);
	this._submode = 'modify';
	this.loadSubDetail('subTab');
	this.enableSubDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadChangeDetail
 * @DESCRIPTION   : CHANGE 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadChangeDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#changeForm', {});
		this.makeDivValues('#changeForm', {});
		this.$('#changeGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('changeTab');
		var detailOpt = this.getFormValues('#changeForm');
		this.$('#changeGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'changeTab') {
		var searchVO = this.getFormValues('#changeForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngChangePk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#changeForm', result.result);
				module.makeDivValues('#changeForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshChangeData
 * @DESCRIPTION   : CHANGE DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshChangeData = function() {

	var searchOpt=this.makeFormArgs('#changeForm');
	this.$('#changeGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectChangeData
 * @DESCRIPTION   : CHANGE DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectChangeData = function() {

	if (this._changemode == 'query') {
		this.enableChangeDetailInputItem();
		return;
	} else if (this._changemode != 'insert' && this._changemode != 'modify') {
		this.enableChangeDetailInputItem();
		return;
	}
	if (this._changeKeyValue == "" || this._changeSeq == "") {
		this.disableChangeDetailInputItem();
		return;
	}
	var changeCtrtNo = this._changeKeyValue;
	var changeSeq = this._changeSeq;
	this.$("#changeGrid").selectFilterRow([{col:"changeCtrtNo", filter:changeCtrtNo},
										   {col:"changeSeq", filter:changeSeq}]);
	this._changemode = 'modify';
	this.loadChangeDetail('changeTab');
	this.enableChangeDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadPymntDetail
 * @DESCRIPTION   : PYMNT 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadPymntDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#pymntForm', {});
		this.makeDivValues('#pymntForm', {});
		this.$('#pymntGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('pymntTab');
		var detailOpt = this.getFormValues('#pymntForm');
		this.$('#pymntGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'pymntTab') {
		var searchVO = this.getFormValues('#pymntForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngMoneyPymntPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#pymntForm', result.result);
				module.makeDivValues('#pymntForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshPymntData
 * @DESCRIPTION   : PYMNT DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshPymntData = function() {

	var searchOpt=this.makeFormArgs('#pymntForm');
	this.$('#pymntGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectPymntData
 * @DESCRIPTION   : PYMNT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectPymntData = function() {

	if (this._pymntmode == 'query') {
		this.enablePymntDetailInputItem();
		return;
	} else if (this._pymntmode != 'insert' && this._pymntmode != 'modify') {
		this.enablePymntDetailInputItem();
		return;
	}
	if (this._pymntKeyValue == "" || this._pymntSeq == "") {
		this.disablePymntDetailInputItem();
		return;
	}
	var pymntCtrtNo = this._pymntKeyValue;
	var pymntSeq = this._pymntSeq;
	this.$("#pymntGrid").selectFilterRow([{col:"pymntCtrtNo", filter:pymntCtrtNo},
										  {col:"pymntSeq", filter:pymntSeq}]);
	this._pymntmode = 'modify';
	this.loadPymntDetail('pymntTab');
	this.enablePymntDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadCaryFwdDetail
 * @DESCRIPTION   : CARY FWD 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadCaryFwdDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#caryFwdForm', {});
		this.makeDivValues('#caryFwdForm', {});
		this.$('#caryFwdGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('caryFwdTab');
		var detailOpt = this.getFormValues('#caryFwdForm');
		this.$('#caryFwdGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'caryFwdTab') {
		var searchVO = this.getFormValues('#caryFwdForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#caryFwdForm', result.result);
				module.makeDivValues('#caryFwdForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshCaryFwdData
 * @DESCRIPTION   : CARY FWD DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshCaryFwdData = function() {

	var searchOpt=this.makeFormArgs('#caryFwdForm');
	this.$('#caryFwdGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectCaryFwdData
 * @DESCRIPTION   : CARY FWD DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectCaryFwdData = function() {

	if (this._caryfwdmode == 'query') {
		this.enableCaryFwdDetailInputItem();
		return;
	} else if (this._caryfwdmode != 'insert' && this._caryfwdmode != 'modify') {
		this.enableCaryFwdDetailInputItem();
		return;
	}
	if (this._caryFwdKeyValue == "" || this._caryFwdSeq == "") {
		this.disableCaryFwdDetailInputItem();
		return;
	}
	var caryFwdCtrtNo = this._caryFwdKeyValue;
	var caryFwdSeq = this._caryFwdSeq;
	this.$("#caryFwdGrid").selectFilterRow([{col:"caryFwdCtrtNo", filter:caryFwdCtrtNo},
											{col:"caryFwdSeq", filter:caryFwdSeq}]);
	this._caryfwdmode = 'modify';
	this.loadCaryFwdDetail('caryFwdTab');
	this.enableCaryFwdDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadScsbidDetail
 * @DESCRIPTION   : SCSBID 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadScsbidDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#scsbidForm', {});
		this.makeDivValues('#scsbidForm', {});
		this.$('#scsbidGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('scsbidTab');
		var detailOpt = this.getFormValues('#scsbidForm');
		this.$('#scsbidGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'scsbidTab') {
		var searchVO = this.getFormValues('#scsbidForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngScsbidInfoPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#scsbidForm', result.result);
				module.makeDivValues('#scsbidForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshScsbidData
 * @DESCRIPTION   : SCSBID FWD DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshScsbidData = function() {

	var searchOpt=this.makeFormArgs('#scsbidForm');
	this.$('#scsbidGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectScsbidData
 * @DESCRIPTION   : SCSBID DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectScsbidData = function() {

	if (this._scsbidmode == 'query') {
		this.enableScsbidDetailInputItem();
		return;
	} else if (this._scsbidmode != 'insert' && this._scsbidmode != 'modify') {
		this.enableScsbidDetailInputItem();
		return;
	}
	if (this._scsbidKeyValue == "" || this._scsbidSeq == "") {
		this.disableScsbidDetailInputItem();
		return;
	}
	var scsbidCtrtNo = this._scsbidKeyValue;
	var scsbidSeq = this._scsbidSeq;
	this.$("#scsbidGrid").selectFilterRow([{col:"scsbidCtrtNo", filter:scsbidCtrtNo},
										   {col:"scsbidSeq", filter:scsbidSeq}]);
	this._scsbidmode = 'modify';
	this.loadScsbidDetail('scsbidTab');
	this.enableScsbidDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadFlawGrntyDetail
 * @DESCRIPTION   : FLAW GRNTY 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtMngModule.prototype.loadFlawGrntyDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#flawGrntyForm', {});
		this.makeDivValues('#flawGrntyForm', {});
		this.$('#flawGrid').flexEmptyData();
		if (this._mainKeyValue == "") {
			return;
		}
		this.copyCtrtInfoData('flawTab');
		var detailOpt = this.getFormValues('#flawGrntyForm');
		this.$('#flawGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'flawTab') {
		var searchVO = this.getFormValues('#flawGrntyForm');
		this.doAction('/ctrt/gamSelectFcltyCtrtMngFlawGrntyPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#flawGrntyForm', result.result);
				module.makeDivValues('#flawGrntyForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : refreshFlawGrntyData
 * @DESCRIPTION   : FLAW GRNTY DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.refreshFlawGrntyData = function() {

	var searchOpt=this.makeFormArgs('#flawGrntyForm');
	this.$('#flawGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectFlawGrntyData
 * @DESCRIPTION   : FLAW GRNTY DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.selectFlawGrntyData = function() {

	if (this._flawgrntymode == 'query') {
		this.enableFlawGrntyDetailInputItem();
		return;
	} else if (this._flawgrntymode != 'insert' && this._flawgrntymode != 'modify') {
		this.enableFlawGrntyDetailInputItem();
		return;
	}
	if (this._flawGrntyKeyValue == "" || this._flawGrntySeq == "") {
		this.disableFlawGrntyDetailInputItem();
		return;
	}
	var flawGrntyCtrtNo = this._flawGrntyKeyValue;
	var flawGrntySeq = this._flawGrntySeq;
	this.$("#flawGrid").selectFilterRow([{col:"flawGrntyCtrtNo", filter:flawGrntyCtrtNo},
										 {col:"flawGrntySeq", filter:flawGrntySeq}]);
	this._flawgrntymode = 'modify';
	this.loadFlawGrntyDetail('flawTab');
	this.enableFlawGrntyDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : getNewCtrtNo
 * @DESCRIPTION   : 새로운 CTRT NO.를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewCtrtNo = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	var ctrtDt = this.$('#ctrtDt').val();
	if (ctrtDt == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngNewCtrtNo.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#ctrtNo').val(result.sNewCtrtNo);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewJoinSeq
 * @DESCRIPTION   : 새로운 JOIN 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewJoinSeq = function() {

	var searchVO = this.makeFormArgs("#joinForm");
	var joinCtrtNo = this.$('#joinCtrtNo').val();
	if (joinCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngJoinContrMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#joinSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewSubSeq
 * @DESCRIPTION   : 새로운 SUB 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewSubSeq = function() {

	var searchVO = this.makeFormArgs("#subForm");
	var subCtrtNo = this.$('#subCtrtNo').val();
	if (subCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngSubctrtMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#subSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewChangeSeq
 * @DESCRIPTION   : 새로운 CHANGE 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewChangeSeq = function() {

	var searchVO = this.makeFormArgs("#changeForm");
	var changeCtrtNo = this.$('#changeCtrtNo').val();
	if (changeCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngChangeMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#changeSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewPymntSeq
 * @DESCRIPTION   : 새로운 PYMNT 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewPymntSeq = function() {

	var searchVO = this.makeFormArgs("#pymntForm");
	var pymntCtrtNo = this.$('#pymntCtrtNo').val();
	if (pymntCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngMoneyPymntMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#pymntSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewCaryFwdSeq
 * @DESCRIPTION   : 새로운 CARY FWD 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewCaryFwdSeq = function() {

	var searchVO = this.makeFormArgs("#caryFwdForm");
	var caryFwdCtrtNo = this.$('#caryFwdCtrtNo').val();
	if (caryFwdCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#caryFwdSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewScsbidSeq
 * @DESCRIPTION   : 새로운 SCSBID 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewScsbidSeq = function() {

	var searchVO = this.makeFormArgs("#scsbidForm");
	var scsbidCtrtNo = this.$('#scsbidCtrtNo').val();
	if (scsbidCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngScsbidInfoMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#scsbidSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getNewFlawGrntySeq
 * @DESCRIPTION   : 새로운 FLAW GRNTY 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getNewFlawGrntySeq = function() {

	var searchVO = this.makeFormArgs("#flawGrntyForm");
	var flawGrntyCtrtNo = this.$('#flawGrntyCtrtNo').val();
	if (flawGrntyCtrtNo == "") {
		return;
	}
	this.doAction('/ctrt/gamSelectFcltyCtrtMngFlawGrntyMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#flawGrntySeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addData = function() {

	this.$('#ctrtNo').val("");
	this.$('#ctrtSe').val("1");
	this.$('#ctrtNm').val("");
	this.$('#causeAct').val("");
	this.$('#baseAmt').val("0");
	this.$('#planAmt').val("0");
	this.$('#orderMthd').val("");
	this.$('#ctrtMth').val("");
	this.$('#prcuPblancNo').val("");
	this.$('#bidPblancNo').val("");
	this.$('#bidPblancDt').val("");
	this.$('#bidDt').val("");
	this.$('#bidMth').val("");
	this.$('#registEntrpsCd').val("");
	this.$('#registEntrpsNm').val("");
	this.$('#scsbider').val("");
	this.$('#scsbidAmt').val("0");
	this.$('#scsbidRate').val("0.00000");
	this.$('#ctrtDt').val(this.getToDayString());
	this.$('#ctrtGrntyAmt').val("0");
	this.$('#ctrtGrntyMth').val("");
	this.$('#ctrtAmt').val("0");
	this.$('#ctrtDtFrom').val(this.getToDayString());
	this.$('#ctrtDtTo').val(this.addDateString("", 365));
	this.$('#ctrtExamDt').val("");
	this.$('#prmtAmt').val("0");
	this.$('#flawDtFrom').val("");
	this.$('#flawDtTo').val("");
	this.$('#sldrtGrnty').val("");
	this.$('#intendant1').val("");
	this.$('#intendant2').val("");
	this.$('#intendant3').val("");
	this.$('#jobChrgDeptCd').val("");
	this.$('#jobChrgDeptNm').val("");
	this.$('#caryFwdBdgtAmt').val("0");
	this.$('#siteDesc').val("");
	this.$('#cnstCstmrNm').val("");
	this.$('#confmDt').val("");
	this.$('#confmerCd').val("");
	this.enableDetailInputItem();
	this.$('#ctrtSe').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var ctrtNo = this.$('#ctrtNo').val();
	var ctrtSe = this.$('#ctrtSe').val();
	var ctrtNm = this.$('#ctrtNm').val();
	var ctrtDt = this.$('#ctrtDt').val();
	var ctrtDtFrom = this.$('#ctrtDtFrom').val();
	var ctrtDtTo = this.$('#ctrtDtTo').val();
	var bidPblancDt = this.$('#bidPblancDt').val();
	var bidDt = this.$('#bidDt').val();
	var ctrtExamDt = this.$('#ctrtExamDt').val();
	var flawDtFrom = this.$('#flawDtFrom').val();
	var flawDtTo = this.$('#flawDtTo').val();
	var baseAmt = Number(this.$('#baseAmt').val().replace(/,/gi, ""));
	var planAmt = Number(this.$('#planAmt').val().replace(/,/gi, ""));
	var scsbidAmt = Number(this.$('#scsbidAmt').val().replace(/,/gi, ""));
	var scsbidRate = Number(this.$('#scsbidRate').val().replace(/,/gi, ""));
	var ctrtGrntyAmt = Number(this.$('#ctrtGrntyAmt').val().replace(/,/gi, ""));
	var ctrtAmt = Number(this.$('#ctrtAmt').val().replace(/,/gi, ""));
	var prmtAmt = Number(this.$('#prmtAmt').val().replace(/,/gi, ""));
	var caryFwdBdgtAmt = Number(this.$('#caryFwdBdgtAmt').val().replace(/,/gi, ""));
	if (ctrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		this.$("#ctrtNo").focus();
		return;
	}
	if (ctrtSe != "1" && ctrtSe != "2" && ctrtSe != "3") {
		alert('계약 구분이 부정확합니다.');
		this.$("#ctrtSe").focus();
		return;
	}
	if (ctrtNm == "") {
		alert('계약 명이 부정확합니다.');
		this.$("#ctrtNo").focus();
		return;
	}
	if (this.isValidDate(ctrtDt, true) == false) {
		alert('계약 일자가 부정확합니다.');
		this.$("#ctrtDt").focus();
		return;
	}
	if (this.isValidDate(ctrtDtFrom, true) == false) {
		alert('계약 시작 일자가 부정확합니다.');
		this.$("#ctrtDtFrom").focus();
		return;
	}
	if (this.isValidDate(ctrtDtTo, true) == false) {
		alert('계약 종료 일자가 부정확합니다.');
		this.$("#ctrtDtTo").focus();
		return;
	}
	if (this.isValidDateFromTo(ctrtDtFrom, ctrtDtTo, true) == false) {
		alert('계약 기간이 부정확합니다.');
		this.$("#ctrtDtTo").focus();
		return;
	}
	if (this.isValidFirstDate(ctrtDt, ctrtDtFrom, false) == false) {
		alert('계약 일자가 계약 시작 일자보다 큽니다.');
		this.$("#ctrtDtFrom").focus();
		return;
	}
	if (this.isValidDate(bidPblancDt, false) == false) {
		alert('입찰 공고 일자가 부정확합니다.');
		this.$("#bidPblancDt").focus();
		return;
	}
	if (this.isValidDate(bidDt, false) == false) {
		alert('입찰 일자가 부정확합니다.');
		this.$("#bidDt").focus();
		return;
	}
	if (this.isValidFirstDate(bidPblancDt, bidDt, false) == false) {
		alert('입찰 공고 일자가 입찰 일자보다 큽니다.');
		this.$("#bidPblancDt").focus();
		return;
	}
	if (this.isValidFirstDate(bidDt, ctrtDt, false) == false) {
		alert('입찰 일자가 계약 일자보다 큽니다.');
		this.$("#bidDt").focus();
		return;
	}
	if (this.isValidDate(ctrtExamDt, false) == false) {
		alert('계약 검사 일자가 부정확합니다.');
		this.$("#ctrtExamDt").focus();
		return;
	}
	if (this.isValidDate(flawDtFrom, false) == false) {
		alert('하자 시작 일자가 부정확합니다.');
		this.$("#flawDtFrom").focus();
		return;
	}
	if (this.isValidDate(flawDtTo, false) == false) {
		alert('하자 종료 일자가 부정확합니다.');
		this.$("#flawDtTo").focus();
		return;
	}
	if (this.isValidDateFromTo(flawDtFrom, flawDtTo, false) == false) {
		alert('하자 기간이 부정확합니다.');
		this.$("#flawDtTo").focus();
		return;
	}
	if (this.isValidFirstDate(ctrtDt, flawDtFrom, false) == false) {
		alert('계약 일자가 하자 시작 일자보다 큽니다.');
		this.$("#flawDtFrom").focus();
		return;
	}
	if (this.isValidAmount(baseAmt) == false) {
		alert('기초 금액이 부정확합니다.');
		this.$("#baseAmt").focus();
		return;
	}
	if (this.isValidAmount(planAmt) == false) {
		alert('설계 금액이 부정확합니다.');
		this.$("#planAmt").focus();
		return;
	}
	if (this.isValidAmount(scsbidAmt) == false) {
		alert('낙찰 금액이 부정확합니다.');
		this.$("#scsbidAmt").focus();
		return;
	}
	if (this.isValidAmount(scsbidRate) == false) {
		alert('낙찰 율이 부정확합니다.');
		this.$("#scsbidRate").focus();
		return;
	}
	if (this.isValidAmount(ctrtGrntyAmt) == false) {
		alert('계약 보증 금액이 부정확합니다.');
		this.$("#ctrtGrntyAmt").focus();
		return;
	}
	if (this.isValidAmount(ctrtAmt) == false) {
		alert('계약 금액이 부정확합니다.');
		this.$("#ctrtAmt").focus();
		return;
	}
	if (this.isValidAmount(prmtAmt) == false) {
		alert('예정 금액이 부정확합니다.');
		this.$("#prmtAmt").focus();
		return;
	}
	if (this.isValidAmount(caryFwdBdgtAmt) == false) {
		alert('이월 예산 금액이 부정확합니다.');
		this.$("#caryFwdBdgtAmt").focus();
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = ctrtNo;
		this.doAction('/ctrt/gamInsertFcltyCtrtMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMng.do', inputVO, function(module, result) {
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
GamFcltyCtrtMngModule.prototype.deleteData = function() {

	var ctrtNo = this.$('#ctrtNo').val();
	if (ctrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		this.$("#ctrtNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?\r\n(공동 도급, 하도급, 변경, 대금 지급, 이행 이월, 낙찰 정보도 모두 삭제됩니다)")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : addJoinData
 * @DESCRIPTION   : JOIN 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addJoinData = function() {

	this.$('#joinCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#joinSeq').val("001");
	this.$('#qotaRate').val("1");
	this.$('#joinEntrpsNm').val("");
	this.$('#joinRprsntv').val("");
	this.$('#dealRelate').val("");
	this.$('#joinBsnmNo').val("");
	this.$('#induty').val("");
	this.$('#stplPrdlst').val("");
	this.$('#joinTlphonNo').val("");
	this.$('#joinFaxNo').val("");
	this.$('#charger').val("");
	this.$('#chargerOfcPos').val("");
	this.$('#chargerMoblphonNo').val("");
	this.$('#chargerEmail').val("");
	this.$('#postNo').val("");
	this.$('#lnmAdres').val("");
	this.$('#roadnmAdres').val("");
	this.enableJoinDetailInputItem();
	this.$('#joinEntrpsNm').focus();
	this.getNewJoinSeq();

};

<%
/**
 * @FUNCTION NAME : saveJoinData
 * @DESCRIPTION   : JOIN 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveJoinData = function() {

	var inputVO = this.makeFormArgs("#joinForm");
	var joinCtrtNo = this.$('#joinCtrtNo').val();
	var joinSeq = this.$('#joinSeq').val();
	var joinEntrpsNm = this.$('#joinEntrpsNm').val();
	var joinRprsntv = this.$('#joinRprsntv').val();
	var joinTlphonNo = this.$('#joinTlphonNo').val();
	var qotaRate = Number(this.$('#qotaRate').val().replace(/,/gi, ""));
	if (joinCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (joinSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (joinEntrpsNm == "") {
		alert('업체 명이 부정확합니다.');
		this.$("#joinEntrpsNm").focus();
		return;
	}
	if (joinRprsntv == "") {
		alert('대표자가 부정확합니다.');
		this.$("#joinRprsntv").focus();
		return;
	}
	if (joinTlphonNo == "") {
		alert('전화 번호가 부정확합니다.');
		this.$("#joinTlphonNo").focus();
		return;
	}
	if (this.isValidRate(qotaRate) == false) {
		alert('지분 율이 부정확합니다.');
		this.$("#qotaRate").focus();
		return;
	}
	if (this._joinmode == "insert") {
		this._joinKeyValue = joinCtrtNo;
		this._joinSeq = joinSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngJoinContr.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshJoinData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngJoinContr.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshJoinData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteJoinData
 * @DESCRIPTION   : JOIN 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteJoinData = function() {

	var joinCtrtNo = this.$('#joinCtrtNo').val();
	var joinSeq = this.$('#joinSeq').val();
	if (joinCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (joinSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#joinForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngJoinContr.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._joinmode = 'query';
				module._joinKeyValue = '';
				module._joinSeq = '';
				module.loadJoinDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addSubData
 * @DESCRIPTION   : SUB 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addSubData = function() {

	this.$('#subCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#subSeq').val("001");
	this.$('#subEntrpsNm').val("");
	this.$('#workClass').val("");
	this.$('#moneyPymntAgree').val("10");
	this.$('#subctrtRate').val("0");
	this.$('#orginlContrAmt').val("0");
	this.$('#subctrtCtrtAmt').val("0");
	this.$('#subCtrtDtFrom').val(this.$('#ctrtDtFrom').val());
	this.$('#subCtrtDtTo').val(this.$('#ctrtDtTo').val());
	this.enableSubDetailInputItem();
	this.$('#subEntrpsNm').focus();
	this.getNewSubSeq();

};

<%
/**
 * @FUNCTION NAME : saveSubData
 * @DESCRIPTION   : SUB 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveSubData = function() {

	var inputVO = this.makeFormArgs("#subForm");
	var subCtrtNo = this.$('#subCtrtNo').val();
	var subCtrtDt = this.$('#subCtrtDt').val();
	var subSeq = this.$('#subSeq').val();
	var subEntrpsNm = this.$('#subEntrpsNm').val();
	var moneyPymntAgree = this.$('#moneyPymntAgree').val();
	var subctrtRate = Number(this.$('#subctrtRate').val().replace(/,/gi, ""));
	var orginlContrAmt = Number(this.$('#orginlContrAmt').val().replace(/,/gi, ""));
	var subctrtCtrtAmt = Number(this.$('#subctrtCtrtAmt').val().replace(/,/gi, ""));
	var subctrtCtrtDtFrom = this.$('#subctrtCtrtDtFrom').val();
	var subctrtCtrtDtTo = this.$('#subctrtCtrtDtTo').val();
	if (subCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (subSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (subEntrpsNm == "") {
		alert('업체 명이 부정확합니다.');
		this.$("#subEntrpsNm").focus();
		return;
	}
	if (moneyPymntAgree != "10" && moneyPymntAgree != "20") {
		alert('대금 지급 합의가 부정확합니다.');
		this.$("#moneyPymntAgree").focus();
		return;
	}
	if (this.isValidRate(subctrtRate) == false) {
		alert('하도급 율이 부정확합니다.');
		this.$("#subctrtRate").focus();
		return;
	}
	if (this.isValidAmount(orginlContrAmt) == false) {
		alert('원 도급 금액이 부정확합니다.');
		this.$("#orginlContrAmt").focus();
		return;
	}
	if (this.isValidAmount(subctrtCtrtAmt) == false) {
		alert('하도급 금액이 부정확합니다.');
		this.$("#subctrtCtrtAmt").focus();
		return;
	}
	if (subctrtCtrtAmt > orginlContrAmt) {
		alert('하도급 금액이 원 도급 금액보다 큽니다.');
		this.$("#subctrtCtrtAmt").focus();
		return;
	}
	if (this.isValidDate(subctrtCtrtDtFrom, true) == false) {
		alert('하도급 계약 시작 일자가 부정확합니다.');
		this.$("#subctrtCtrtDtFrom").focus();
		return;
	}
	if (this.isValidDate(subctrtCtrtDtTo, true) == false) {
		alert('하도급 계약 종료 일자가 부정확합니다.');
		this.$("#subctrtCtrtDtTo").focus();
		return;
	}
	if (this.isValidDateFromTo(subctrtCtrtDtFrom, subctrtCtrtDtTo, true) == false) {
		alert('하도급 계약 기간이 부정확합니다.');
		this.$("#subctrtCtrtDtTo").focus();
		return;
	}
	if (this.isValidFirstDate(subCtrtDt, subctrtCtrtDtFrom, true) == false) {
		alert('계약 일자가 하도급 계약 시작 일자보다 큽니다.');
		this.$("#subctrtCtrtDtFrom").focus();
		return;
	}
	if (this._submode == "insert") {
		this._subKeyValue = subCtrtNo;
		this._subSeq = subSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngSubctrt.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshSubData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngSubctrt.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshSubData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteSubData
 * @DESCRIPTION   : SUB 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteSubData = function() {

	var subCtrtNo = this.$('#subCtrtNo').val();
	var subSeq = this.$('#subSeq').val();
	if (subCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (subSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#subForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngSubctrt.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._submode = 'query';
				module._subKeyValue = '';
				module._subSeq = '';
				module.loadSubDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addChangeData
 * @DESCRIPTION   : CHANGE 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addChangeData = function() {

	this.$('#changeCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#changeSeq').val("001");
	this.$('#changeSe').val("1");
	this.$('#changeDt').val(this.getToDayString());
	this.$('#changeRsn').val("계약기간변경");
	this.$('#changeCtrtDtFrom').val(this.$('#ctrtDtFrom').val());
	this.$('#changeCtrtDtTo').val(this.$('#ctrtDtTo').val());
	this.$('#changeCtrtAmt').val("0");
	this.$('#lastCtrtAmt').val("0");
	this.$('#changeRm').val("");
	this.enableChangeDetailInputItem();
	this.$('#changeDt').focus();
	this.getNewChangeSeq();

};

<%
/**
 * @FUNCTION NAME : saveChangeData
 * @DESCRIPTION   : CHANGE 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveChangeData = function() {

	var inputVO = this.makeFormArgs("#changeForm");
	var changeCtrtNo = this.$('#changeCtrtNo').val();
	var changeCtrtDt = this.$('#changeCtrtDt').val();
	var changeSeq = this.$('#changeSeq').val();
	var changeSe = this.$('#changeSe').val();
	var changeDt = this.$('#changeDt').val();
	var changeRsn = this.$('#changeRsn').val();
	var changeCtrtDtFrom = this.$('#changeCtrtDtFrom').val();
	var changeCtrtDtTo = this.$('#changeCtrtDtTo').val();
	var changeCtrtAmt = Number(this.$('#changeCtrtAmt').val().replace(/,/gi, ""));
	var lastCtrtAmt = Number(this.$('#lastCtrtAmt').val().replace(/,/gi, ""));
	if (changeCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (changeSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (changeSe != "1" && changeSe != "2" && changeSe != "9") {
		alert('변경 구분이 부정확합니다.');
		this.$("#changeSe").focus();
		return;
	}
	if (this.isValidDate(changeDt, true) == false) {
		alert('변경 일자가 부정확합니다.');
		this.$("#changeDt").focus();
		return;
	}
	if (changeRsn == "") {
		alert('변경 사유가 부정확합니다.');
		this.$("#changeRsn").focus();
		return;
	}
	if (changeSe == "1") {
		if (this.isValidDate(changeCtrtDtFrom, true) == false) {
			alert('변경 계약 시작 일자가 부정확합니다.');
			this.$("#changeCtrtDtFrom").focus();
			return;
		}
		if (this.isValidDate(changeCtrtDtTo, true) == false) {
			alert('변경 계약 종료 일자가 부정확합니다.');
			this.$("#changeCtrtDtTo").focus();
			return;
		}
		if (this.isValidDateFromTo(changeCtrtDtFrom, changeCtrtDtTo, true) == false) {
			alert('변경 계약 기간이 부정확합니다.');
			this.$("#changeCtrtDtTo").focus();
			return;
		}
		if (this.isValidFirstDate(changeCtrtDt, changeCtrtDtFrom, true) == false) {
			alert('계약 일자가 변경 계약 시작 일자보다 큽니다.');
			this.$("#changeCtrtDtFrom").focus();
			return;
		}
	} else if (changeSe == "2") {
		if (this.isValidAmount(changeCtrtAmt) == false) {
			alert('변경 계약 금액이 부정확합니다.');
			this.$("#changeCtrtAmt").focus();
			return;
		}
		if (this.isValidAmount(lastCtrtAmt) == false) {
			alert('최종 계약 금액이 부정확합니다.');
			this.$("#lastCtrtAmt").focus();
			return;
		}
	}
	if (this._changemode == "insert") {
		this._changeKeyValue = changeCtrtNo;
		this._changeSeq = changeSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngChange.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshChangeData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngChange.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshChangeData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteChangeData
 * @DESCRIPTION   : CHANGE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteChangeData = function() {

	var changeCtrtNo = this.$('#changeCtrtNo').val();
	var changeSeq = this.$('#changeSeq').val();
	if (changeCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (changeSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#changeForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngChange.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._changemode = 'query';
				module._changeKeyValue = '';
				module._changeSeq = '';
				module.loadChangeDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addPymntData
 * @DESCRIPTION   : PYMNT 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addPymntData = function() {

	this.$('#pymntCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#pymntSeq').val("001");
	this.$('#pymntCl').val("10");
	this.$('#pymntDt').val(this.getToDayString());
	this.$('#thisTimeEstbAmt').val("0");
	this.$('#depositExcclcAmt').val("0");
	this.$('#pymntAmt').val("0");
	this.$('#pymntAggrAmt').val("0");
	this.$('#pymntRm').val("");
	this.enablePymntDetailInputItem();
	this.$('#pymntCl').focus();
	this.getNewPymntSeq();

};

<%
/**
 * @FUNCTION NAME : savePymntData
 * @DESCRIPTION   : PYMNT 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.savePymntData = function() {

	var inputVO = this.makeFormArgs("#pymntForm");
	var pymntCtrtNo = this.$('#pymntCtrtNo').val();
	var pymntSeq = this.$('#pymntSeq').val();
	var pymntCl = this.$('#pymntCl').val();
	var pymntDt = this.$('#pymntDt').val();
	var thisTimeEstbAmt = Number(this.$('#thisTimeEstbAmt').val().replace(/,/gi, ""));
	var depositExcclcAmt = Number(this.$('#depositExcclcAmt').val().replace(/,/gi, ""));
	var pymntAmt = Number(this.$('#pymntAmt').val().replace(/,/gi, ""));
	var pymntAggrAmt = Number(this.$('#pymntAggrAmt').val().replace(/,/gi, ""));
	if (pymntCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (pymntSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (pymntCl != "10" && pymntCl != "20" && pymntCl != "30") {
		alert('지급 분류가 부정확합니다.');
		this.$("#pymntCl").focus();
		return;
	}
	if (this.isValidDate(pymntDt, true) == false) {
		alert('지급 일자가 부정확합니다.');
		this.$("#pymntDt").focus();
		return;
	}
	if (this.isValidAmount(thisTimeEstbAmt) == false) {
		alert('금회 기성 금액이 부정확합니다.');
		this.$("#thisTimeEstbAmt").focus();
		return;
	}
	if (this.isValidAmount(depositExcclcAmt) == false) {
		alert('선금 정산 금액이 부정확합니다.');
		this.$("#depositExcclcAmt").focus();
		return;
	}
	if (this.isValidAmount(pymntAmt) == false) {
		alert('지급 금액이 부정확합니다.');
		this.$("#pymntAmt").focus();
		return;
	}
	if (this.isValidAmount(pymntAggrAmt) == false) {
		alert('지급 누계 금액이 부정확합니다.');
		this.$("#pymntAggrAmt").focus();
		return;
	}
	if (pymntAmt > pymntAggrAmt) {
		alert('지급 금액이 지급 누계 금액보다 큽니다.');
		this.$("#pymntAggrAmt").focus();
		return;
	}
	if (this._pymntmode == "insert") {
		this._pymntKeyValue = pymntCtrtNo;
		this._pymntSeq = pymntSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngMoneyPymnt.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshPymntData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngMoneyPymnt.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshPymntData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deletePymntData
 * @DESCRIPTION   : PYMNT 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deletePymntData = function() {

	var pymntCtrtNo = this.$('#pymntCtrtNo').val();
	var pymntSeq = this.$('#pymntSeq').val();
	if (pymntCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (pymntSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#pymntForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngMoneyPymnt.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._pymntmode = 'query';
				module._pymntKeyValue = '';
				module._pymntSeq = '';
				module.loadPymntDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addCaryFwdData
 * @DESCRIPTION   : CARY FWD 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addCaryFwdData = function() {

	this.$('#caryFwdCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#caryFwdSeq').val("001");
	this.$('#fulfillCaryFwdYear').val(this.getCurrentYearString());
	this.$('#fulfillAmt').val("0");
	this.$('#caryFwdAmt').val("0");
	this.enableCaryFwdDetailInputItem();
	this.$('#fulfillCaryFwdYear').focus();
	this.getNewCaryFwdSeq();

};

<%
/**
 * @FUNCTION NAME : saveCaryFwdData
 * @DESCRIPTION   : CARY FWD 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveCaryFwdData = function() {

	var inputVO = this.makeFormArgs("#caryFwdForm");
	var caryFwdCtrtNo = this.$('#caryFwdCtrtNo').val();
	var caryFwdSeq = this.$('#caryFwdSeq').val();
	var fulfillCaryFwdYear = this.$('#fulfillCaryFwdYear').val();
	var fulfillAmt = Number(this.$('#fulfillAmt').val().replace(/,/gi, ""));
	var caryFwdAmt = Number(this.$('#caryFwdAmt').val().replace(/,/gi, ""));
	if (caryFwdCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (caryFwdSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (this.isValidYear(fulfillCaryFwdYear, true) == false) {
		alert('이월 년도가 부정확합니다.');
		this.$("#fulfillCaryFwdYear").focus();
		return;
	}
	if (this.isValidAmount(fulfillAmt) == false) {
		alert('이행 금액이 부정확합니다.');
		this.$("#fulfillAmt").focus();
		return;
	}
	if (this.isValidAmount(caryFwdAmt) == false) {
		alert('이월 금액이 부정확합니다.');
		this.$("#caryFwdAmt").focus();
		return;
	}
	if (this._caryfwdmode == "insert") {
		this._caryFwdKeyValue = caryFwdCtrtNo;
		this._caryFwdSeq = caryFwdSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngFulfillCaryFwd.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshCaryFwdData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngFulfillCaryFwd.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshCaryFwdData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteCaryFwdData
 * @DESCRIPTION   : CARY FWD 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteCaryFwdData = function() {

	var caryFwdCtrtNo = this.$('#caryFwdCtrtNo').val();
	var caryFwdSeq = this.$('#caryFwdSeq').val();
	if (caryFwdCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (caryFwdSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#caryFwdForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngFulfillCaryFwd.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._caryfwdmode = 'query';
				module._caryFwdKeyValue = '';
				module._caryFwdSeq = '';
				module.loadCaryFwdDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addScsbidData
 * @DESCRIPTION   : SCSBID 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addScsbidData = function() {

	this.$('#scsbidCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#scsbidSeq').val("001");
	this.$('#scsbidRank').val("001");
	this.$('#scsbidEntrpsNm').val("");
	this.$('#scsbidRprsntv').val("");
	this.$('#scsbidBsnmNo').val("");
	this.$('#scsbidTlphonNo').val("");
	this.$('#scsbidRm').val("");
	this.enableScsbidDetailInputItem();
	this.$('#scsbidRank').focus();
	this.getNewScsbidSeq();

};

<%
/**
 * @FUNCTION NAME : saveScsbidData
 * @DESCRIPTION   : SCSBID 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveScsbidData = function() {

	var inputVO = this.makeFormArgs("#scsbidForm");
	var scsbidCtrtNo = this.$('#scsbidCtrtNo').val();
	var scsbidSeq = this.$('#scsbidSeq').val();
	var scsbidRank = Number(this.$('#scsbidRank').val().replace(/,/gi, ""));
	var scsbidEntrpsNm = this.$('#scsbidEntrpsNm').val();
	var scsbidRprsntv = this.$('#scsbidRprsntv').val();
	var scsbidBsnmNo = this.$('#scsbidBsnmNo').val();
	if (scsbidCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (scsbidSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (scsbidRank > 999 && scsbidRank < 1) {
		alert('낙찰 순위가 부정확합니다.');
		this.$("#pymntCl").focus();
		return;
	}
	if (scsbidEntrpsNm == "") {
		alert('업체 명이 부정확합니다.');
		this.$("#scsbidEntrpsNm").focus();
		return;
	}
	if (scsbidRprsntv == "") {
		alert('대표자가 부정확합니다.');
		this.$("#scsbidRprsntv").focus();
		return;
	}
	if (scsbidBsnmNo == "") {
		alert('사업자 번호가 부정확합니다.');
		this.$("#scsbidBsnmNo").focus();
		return;
	}
	if (this._scsbidmode == "insert") {
		this._scsbidKeyValue = scsbidCtrtNo;
		this._scsbidSeq = scsbidSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngScsbidInfo.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshScsbidData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngScsbidInfo.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshScsbidData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteScsbidData
 * @DESCRIPTION   : SCSBID 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteScsbidData = function() {

	var scsbidCtrtNo = this.$('#scsbidCtrtNo').val();
	var scsbidSeq = this.$('#scsbidSeq').val();
	if (scsbidCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (scsbidSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#scsbidForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngScsbidInfo.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._scsbidmode = 'query';
				module._scsbidKeyValue = '';
				module._scsbidSeq = '';
				module.loadScsbidDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : addFlawGrntyData
 * @DESCRIPTION   : FLAW GRNTY 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.addFlawGrntyData = function() {

	this.$('#flawGrntyCtrtNo').val(this.$('#ctrtNo').val());
	this.$('#flawGrntySeq').val("001");
	this.$('#flawEndDt').val("");
	this.$('#flawGrntyStartDt').val("");
	this.$('#flawGrntyEndDt').val("");
	this.$('#flawGrntySrbctAmt').val("0");
	this.$('#flawGrntyRate').val("0");
	this.$('#flawGrntyCtrtAmt').val("0");
	this.$('#flawGrntyInsuNo').val("");
	this.$('#flawGrntyInsuCmpy').val("");
	this.$('#flawGrntyInsuAddr').val("");
	this.$('#flawGrntyInsuRprsntv').val("");
	this.enableFlawGrntyDetailInputItem();
	this.$('#flawEndDt').focus();
	this.getNewFlawGrntySeq();

};

<%
/**
 * @FUNCTION NAME : saveFlawGrntyData
 * @DESCRIPTION   : FLAW GRNTY 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.saveFlawGrntyData = function() {

	var inputVO = this.makeFormArgs("#flawGrntyForm");
	var flawGrntyCtrtNo = this.$('#flawGrntyCtrtNo').val();
	var flawGrntySeq = this.$('#flawGrntySeq').val();
	var flawEndDt = this.$('#flawEndDt').val();
	var flawGrntyStartDt = this.$('#flawGrntyStartDt').val();
	var flawGrntyEndDt = this.$('#flawGrntyEndDt').val();
	var flawGrntySrbctAmt = Number(this.$('#flawGrntySrbctAmt').val().replace(/,/gi, ""));
	var flawGrntyRate = Number(this.$('#flawGrntyRate').val().replace(/,/gi, ""));
	var flawGrntyCtrtAmt = Number(this.$('#flawGrntyCtrtAmt').val().replace(/,/gi, ""));
	if (flawGrntyCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (flawGrntySeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (this.isValidDate(flawEndDt, true) == false) {
		alert('하자 만료 일자가 부정확합니다.');
		this.$("#flawEndDt").focus();
		return;
	}
	if (this.isValidDate(flawGrntyStartDt, false) == false) {
		alert('하자 보증 시작 일자가 부정확합니다.');
		this.$("#flawGrntyStartDt").focus();
		return;
	}
	if (this.isValidDate(flawGrntyEndDt, false) == false) {
		alert('하자 보증 종료 일자가 부정확합니다.');
		this.$("#flawGrntyEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(flawGrntyStartDt, flawGrntyEndDt, false) == false) {
		alert('하자 보증 기간이 부정확합니다.');
		this.$("#flawGrntyEndDt").focus();
		return;
	}
	if (this.isValidPercent(flawGrntyRate) == false) {
		alert('하자 보증 율이 부정확합니다.');
		this.$("#flawGrntyRate").focus();
		return;
	}
	if (this.isValidAmount(flawGrntySrbctAmt, false) == false) {
		alert('하자 보증 가입 금액이 부정확합니다.');
		this.$("#flawGrntySrbctAmt").focus();
		return;
	}
	if (this.isValidAmount(flawGrntyCtrtAmt, false) == false) {
		alert('하자 보증 계약 금액이 부정확합니다.');
		this.$("#flawGrntyCtrtAmt").focus();
		return;
	}
	if (this._flawgrntymode == "insert") {
		this._flawGrntyKeyValue = joinCtrtNo;
		this._flawGrntySeq = joinSeq;
		this.doAction('/ctrt/gamInsertFcltyCtrtMngFlawGrnty.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshFlawGrntyData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/ctrt/gamUpdateFcltyCtrtMngFlawGrnty.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshFlawGrntyData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteFlawGrntyData
 * @DESCRIPTION   : FLAW GRNTY 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.deleteFlawGrntyData = function() {

	var flawGrntyCtrtNo = this.$('#flawGrntyCtrtNo').val();
	var flawGrntySeq = this.$('#flawGrntySeq').val();
	if (flawGrntyCtrtNo == "") {
		alert('계약 번호가 부정확합니다.');
		return;
	}
	if (flawGrntySeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#flawGrntyForm");
		this.doAction('/ctrt/gamDeleteFcltyCtrtMngFlawGrnty.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._flawgrntymode = 'query';
				module._flawGrntyKeyValue = '';
				module._flawGrntySeq = '';
				module.loadFlawGrntyDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : getSearchEntrpsNm
 * @DESCRIPTION   : 조회조건 업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getSearchEntrpsNm = function() {

	var sRegistEntrpsCd = this.$('#sRegistEntrpsCd').val();
	if (sRegistEntrpsCd.length == 8) {
		var searchVO = { 'sEntrpscd':sRegistEntrpsCd };
		this.doAction('/ctrt/gamSelectFcltyCtrtMngEntrpsInfo.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#sRegistEntrpsNm').val(result.result.entrpsNm);
			}
		});
	} else {
		this.$('#sRegistEntrpsNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : getRegistEntrpsNm
 * @DESCRIPTION   : 등록 업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.getRegistEntrpsNm = function() {

	var registEntrpsCd = this.$('#registEntrpsCd').val();
	if (registEntrpsCd.length == 8) {
		var searchVO = { 'sEntrpscd':registEntrpsCd };
		this.doAction('/ctrt/gamSelectFcltyCtrtSttusInqireEntrpsNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#registEntrpsNm').val(result.result.entrpsNm);
			}
		});
	} else {
		this.$('#registEntrpsNm').val('');
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
GamFcltyCtrtMngModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#mainGrid").flexRowCount();
			break;
		case 'btnJoinExcelDownload':
			gridRowCount = this.$("#joinGrid").flexRowCount();
			break;
		case 'btnSubExcelDownload':
			gridRowCount = this.$("#subGrid").flexRowCount();
			break;
		case 'btnChangeExcelDownload':
			gridRowCount = this.$("#changeGrid").flexRowCount();
			break;
		case 'btnPymntExcelDownload':
			gridRowCount = this.$("#pymntGrid").flexRowCount();
			break;
		case 'btnCaryFwdExcelDownload':
			gridRowCount = this.$("#caryFwdGrid").flexRowCount();
			break;
		case 'btnScsbidExcelDownload':
			gridRowCount = this.$("#scsbidGrid").flexRowCount();
			break;
		case 'btnFlawGrntyExcelDownload':
			gridRowCount = this.$("#flawGrid").flexRowCount();
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
			this.$('#mainGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMng.do');
			break;
		case 'btnJoinExcelDownload':
			this.$('#joinGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngJoinContr.do');
			break;
		case 'btnSubExcelDownload':
			this.$('#subGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngSubctrt.do');
			break;
		case 'btnChangeExcelDownload':
			this.$('#changeGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngChange.do');
			break;
		case 'btnPymntExcelDownload':
			this.$('#pymntGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngMoneyPymnt.do');
			break;
		case 'btnCaryFwdExcelDownload':
			this.$('#caryFwdGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngFulfillCaryFwd.do');
			break;
		case 'btnScsbidExcelDownload':
			this.$('#scsbidGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngScsbidInfo.do');
			break;
		case 'btnFlawGrntyExcelDownload':
			this.$('#flawGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtMngFlawGrnty.do');
			break;
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableListButtonItem = function() {

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
GamFcltyCtrtMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#ctrtNo').enable();
		this.$('#ctrtSe').enable();
		this.$('#ctrtNm').enable();
		this.$('#causeAct').enable();
		this.$('#baseAmt').enable();
		this.$('#planAmt').enable();
		this.$('#orderMthd').enable();
		this.$('#ctrtMth').enable();
		this.$('#prcuPblancNo').enable();
		this.$('#bidPblancNo').enable();
		this.$('#bidPblancDt').enable();
		this.$('#bidDt').enable();
		this.$('#bidMth').enable();
		this.$('#registEntrpsCd').enable();
		this.$('#scsbider').enable();
		this.$('#scsbidAmt').enable();
		this.$('#scsbidRate').enable();
		this.$('#ctrtDt').enable();
		this.$('#ctrtGrntyAmt').enable();
		this.$('#ctrtGrntyMth').enable();
		this.$('#ctrtAmt').enable();
		this.$('#ctrtDtFrom').enable();
		this.$('#ctrtDtTo').enable();
		this.$('#ctrtExamDt').enable();
		this.$('#prmtAmt').enable();
		this.$('#flawDtFrom').enable();
		this.$('#flawDtTo').enable();
		this.$('#sldrtGrnty').enable();
		this.$('#intendant1').enable();
		this.$('#intendant2').enable();
		this.$('#intendant3').enable();
		this.$('#jobChrgDeptCd').enable();
		this.$('#caryFwdBdgtAmt').enable();
		this.$('#siteDesc').enable();
		this.$('#popupCtrtRegistEntrpsCd').enable();
		this.$('#popupCtrtRegistEntrpsCd').removeClass('ui-state-disabled');
		this.$('#popupCtrtJobChrgDeptCd').enable();
		this.$('#popupCtrtJobChrgDeptCd').removeClass('ui-state-disabled');
		this.$('#btnCtrtInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCtrtSave').enable();
		this.$('#btnCtrtSave').removeClass('ui-state-disabled');
		this.$('#btnCtrtRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#ctrtNo').disable();
			this.$('#ctrtSe').enable();
			this.$('#ctrtNm').enable();
			this.$('#causeAct').enable();
			this.$('#baseAmt').enable();
			this.$('#planAmt').enable();
			this.$('#orderMthd').enable();
			this.$('#ctrtMth').enable();
			this.$('#prcuPblancNo').enable();
			this.$('#bidPblancNo').enable();
			this.$('#bidPblancDt').enable();
			this.$('#bidDt').enable();
			this.$('#bidMth').enable();
			this.$('#registEntrpsCd').enable();
			this.$('#scsbider').enable();
			this.$('#scsbidAmt').enable();
			this.$('#scsbidRate').enable();
			this.$('#ctrtDt').enable();
			this.$('#ctrtGrntyAmt').enable();
			this.$('#ctrtGrntyMth').enable();
			this.$('#ctrtAmt').enable();
			this.$('#ctrtDtFrom').enable();
			this.$('#ctrtDtTo').enable();
			this.$('#ctrtExamDt').enable();
			this.$('#prmtAmt').enable();
			this.$('#flawDtFrom').enable();
			this.$('#flawDtTo').enable();
			this.$('#sldrtGrnty').enable();
			this.$('#intendant1').enable();
			this.$('#intendant2').enable();
			this.$('#intendant3').enable();
			this.$('#jobChrgDeptCd').enable();
			this.$('#caryFwdBdgtAmt').enable();
			this.$('#siteDesc').enable();
			this.$('#popupCtrtRegistEntrpsCd').enable();
			this.$('#popupCtrtRegistEntrpsCd').removeClass('ui-state-disabled');
			this.$('#popupCtrtJobChrgDeptCd').enable();
			this.$('#popupCtrtJobChrgDeptCd').removeClass('ui-state-disabled');
			this.$('#btnCtrtInsert').enable();
			this.$('#btnCtrtInsert').removeClass('ui-state-disabled');
			this.$('#btnCtrtSave').enable();
			this.$('#btnCtrtSave').removeClass('ui-state-disabled');
			this.$('#btnCtrtRemove').enable();
			this.$('#btnCtrtRemove').removeClass('ui-state-disabled');
			this.$('#btnFirstData').enable();
			this.$('#btnFirstData').removeClass('ui-state-disabled');
			this.$('#btnPrevData').enable();
			this.$('#btnPrevData').removeClass('ui-state-disabled');
			this.$('#btnNextData').enable();
			this.$('#btnNextData').removeClass('ui-state-disabled');
			this.$('#btnLastData').enable();
			this.$('#btnLastData').removeClass('ui-state-disabled');
		} else {
			this.$('#ctrtNo').disable();
			this.$('#ctrtSe').disable();
			this.$('#ctrtNm').disable();
			this.$('#causeAct').disable();
			this.$('#baseAmt').disable();
			this.$('#planAmt').disable();
			this.$('#orderMthd').disable();
			this.$('#ctrtMth').disable();
			this.$('#prcuPblancNo').disable();
			this.$('#bidPblancNo').disable();
			this.$('#bidPblancDt').disable();
			this.$('#bidDt').disable();
			this.$('#bidMth').disable();
			this.$('#registEntrpsCd').disable();
			this.$('#scsbider').disable();
			this.$('#scsbidAmt').disable();
			this.$('#scsbidRate').disable();
			this.$('#ctrtDt').disable();
			this.$('#ctrtGrntyAmt').disable();
			this.$('#ctrtGrntyMth').disable();
			this.$('#ctrtAmt').disable();
			this.$('#ctrtDtFrom').disable();
			this.$('#ctrtDtTo').disable();
			this.$('#ctrtExamDt').disable();
			this.$('#prmtAmt').disable();
			this.$('#flawDtFrom').disable();
			this.$('#flawDtTo').disable();
			this.$('#sldrtGrnty').disable();
			this.$('#intendant1').disable();
			this.$('#intendant2').disable();
			this.$('#intendant3').disable();
			this.$('#jobChrgDeptCd').disable();
			this.$('#caryFwdBdgtAmt').disable();
			this.$('#siteDesc').disable();
			this.$('#popupCtrtRegistEntrpsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupCtrtJobChrgDeptCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCtrtInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCtrtSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCtrtRemove').disable({disableClass:"ui-state-disabled"});
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
GamFcltyCtrtMngModule.prototype.disableDetailInputItem = function() {

	this.$('#ctrtNo').disable();
	this.$('#ctrtSe').disable();
	this.$('#ctrtNm').disable();
	this.$('#causeAct').disable();
	this.$('#baseAmt').disable();
	this.$('#planAmt').disable();
	this.$('#orderMthd').disable();
	this.$('#ctrtMth').disable();
	this.$('#prcuPblancNo').disable();
	this.$('#bidPblancNo').disable();
	this.$('#bidPblancDt').disable();
	this.$('#bidDt').disable();
	this.$('#bidMth').disable();
	this.$('#registEntrpsCd').disable();
	this.$('#scsbider').disable();
	this.$('#scsbidAmt').disable();
	this.$('#scsbidRate').disable();
	this.$('#ctrtDt').disable();
	this.$('#ctrtGrntyAmt').disable();
	this.$('#ctrtGrntyMth').disable();
	this.$('#ctrtAmt').disable();
	this.$('#ctrtDtFrom').disable();
	this.$('#ctrtDtTo').disable();
	this.$('#ctrtExamDt').disable();
	this.$('#prmtAmt').disable();
	this.$('#flawDtFrom').disable();
	this.$('#flawDtTo').disable();
	this.$('#sldrtGrnty').disable();
	this.$('#intendant1').disable();
	this.$('#intendant2').disable();
	this.$('#intendant3').disable();
	this.$('#jobChrgDeptCd').disable();
	this.$('#caryFwdBdgtAmt').disable();
	this.$('#siteDesc').disable();
	this.$('#popupCtrtRegistEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupCtrtJobChrgDeptCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCtrtInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCtrtSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCtrtRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableJoinDetailInputItem
 * @DESCRIPTION   : JOIN DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableJoinDetailInputItem = function() {

	if (this._joinmode == "insert") {
		this.$('#qotaRate').enable();
		this.$('#joinEntrpsNm').enable();
		this.$('#joinRprsntv').enable();
		this.$('#dealRelate').enable();
		this.$('#joinBsnmNo').enable();
		this.$('#induty').enable();
		this.$('#stplPrdlst').enable();
		this.$('#joinTlphonNo').enable();
		this.$('#joinFaxNo').enable();
		this.$('#charger').enable();
		this.$('#chargerOfcPos').enable();
		this.$('#chargerMoblphonNo').enable();
		this.$('#chargerEmail').enable();
		this.$('#postNo').enable();
		this.$('#lnmAdres').enable();
		this.$('#roadnmAdres').enable();
		this.$('#popupJoinEntrpsCd').enable();
		this.$('#popupJoinEntrpsCd').removeClass('ui-state-disabled');
		this.$('#btnJoinInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnJoinSave').enable();
		this.$('#btnJoinSave').removeClass('ui-state-disabled');
		this.$('#btnJoinRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._joinKeyValue != "") {
			this.$('#qotaRate').enable();
			this.$('#joinEntrpsNm').enable();
			this.$('#joinRprsntv').enable();
			this.$('#dealRelate').enable();
			this.$('#joinBsnmNo').enable();
			this.$('#induty').enable();
			this.$('#stplPrdlst').enable();
			this.$('#joinTlphonNo').enable();
			this.$('#joinFaxNo').enable();
			this.$('#charger').enable();
			this.$('#chargerOfcPos').enable();
			this.$('#chargerMoblphonNo').enable();
			this.$('#chargerEmail').enable();
			this.$('#postNo').enable();
			this.$('#lnmAdres').enable();
			this.$('#roadnmAdres').enable();
			this.$('#popupJoinEntrpsCd').enable();
			this.$('#popupJoinEntrpsCd').removeClass('ui-state-disabled');
			this.$('#btnJoinInsert').enable();
			this.$('#btnJoinInsert').removeClass('ui-state-disabled');
			this.$('#btnJoinSave').enable();
			this.$('#btnJoinSave').removeClass('ui-state-disabled');
			this.$('#btnJoinRemove').enable();
			this.$('#btnJoinRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#qotaRate').disable();
			this.$('#joinEntrpsNm').disable();
			this.$('#joinRprsntv').disable();
			this.$('#dealRelate').disable();
			this.$('#joinBsnmNo').disable();
			this.$('#induty').disable();
			this.$('#stplPrdlst').disable();
			this.$('#joinTlphonNo').disable();
			this.$('#joinFaxNo').disable();
			this.$('#charger').disable();
			this.$('#chargerOfcPos').disable();
			this.$('#chargerMoblphonNo').disable();
			this.$('#chargerEmail').disable();
			this.$('#postNo').disable();
			this.$('#lnmAdres').disable();
			this.$('#roadnmAdres').disable();
			this.$('#popupJoinEntrpsCd').disable({disableClass:"ui-state-disabled"});
			if (this.$('#joinCtrtNo').val() != "") {
				this.$('#btnJoinInsert').enable();
				this.$('#btnJoinInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnJoinInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnJoinSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnJoinRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableJoinDetailInputItem
 * @DESCRIPTION   : JOIN DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableJoinDetailInputItem = function() {

	this.$('#qotaRate').disable();
	this.$('#joinEntrpsNm').disable();
	this.$('#joinRprsntv').disable();
	this.$('#dealRelate').disable();
	this.$('#joinBsnmNo').disable();
	this.$('#induty').disable();
	this.$('#stplPrdlst').disable();
	this.$('#joinTlphonNo').disable();
	this.$('#joinFaxNo').disable();
	this.$('#charger').disable();
	this.$('#chargerOfcPos').disable();
	this.$('#chargerMoblphonNo').disable();
	this.$('#chargerEmail').disable();
	this.$('#postNo').disable();
	this.$('#lnmAdres').disable();
	this.$('#roadnmAdres').disable();
	this.$('#popupJoinEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnJoinInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnJoinSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnJoinRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableSubDetailInputItem
 * @DESCRIPTION   : SUB DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableSubDetailInputItem = function() {

	if (this._submode == "insert") {
		this.$('#subEntrpsNm').enable();
		this.$('#workClass').enable();
		this.$('#moneyPymntAgree').enable();
		this.$('#subctrtRate').enable();
		this.$('#orginlContrAmt').enable();
		this.$('#subctrtCtrtAmt').enable();
		this.$('#subctrtCtrtDtFrom').enable();
		this.$('#subctrtCtrtDtTo').enable();
		this.$('#popupSubEntrpsCd').enable();
		this.$('#popupSubEntrpsCd').removeClass('ui-state-disabled');
		this.$('#btnSubInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSubSave').enable();
		this.$('#btnSubSave').removeClass('ui-state-disabled');
		this.$('#btnSubRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._subKeyValue != "") {
			this.$('#subEntrpsNm').enable();
			this.$('#workClass').enable();
			this.$('#moneyPymntAgree').enable();
			this.$('#subctrtRate').enable();
			this.$('#orginlContrAmt').enable();
			this.$('#subctrtCtrtAmt').enable();
			this.$('#subctrtCtrtDtFrom').enable();
			this.$('#subctrtCtrtDtTo').enable();
			this.$('#popupSubEntrpsCd').enable();
			this.$('#popupSubEntrpsCd').removeClass('ui-state-disabled');
			this.$('#btnSubInsert').enable();
			this.$('#btnSubInsert').removeClass('ui-state-disabled');
			this.$('#btnSubSave').enable();
			this.$('#btnSubSave').removeClass('ui-state-disabled');
			this.$('#btnSubRemove').enable();
			this.$('#btnSubRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#subEntrpsNm').disable();
			this.$('#workClass').disable();
			this.$('#moneyPymntAgree').disable();
			this.$('#subctrtRate').disable();
			this.$('#orginlContrAmt').disable();
			this.$('#subctrtCtrtAmt').disable();
			this.$('#subctrtCtrtDtFrom').disable();
			this.$('#subctrtCtrtDtTo').disable();
			this.$('#popupSubEntrpsCd').disable({disableClass:"ui-state-disabled"});
			if (this.$('#subCtrtNo').val() != "") {
				this.$('#btnSubInsert').enable();
				this.$('#btnSubInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnSubInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnSubSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSubRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableSubDetailInputItem
 * @DESCRIPTION   : SUB DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableSubDetailInputItem = function() {

	this.$('#subEntrpsNm').disable();
	this.$('#workClass').disable();
	this.$('#moneyPymntAgree').disable();
	this.$('#subctrtRate').disable();
	this.$('#orginlContrAmt').disable();
	this.$('#subctrtCtrtAmt').disable();
	this.$('#subctrtCtrtDtFrom').disable();
	this.$('#subctrtCtrtDtTo').disable();
	this.$('#popupSubEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSubInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSubSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSubRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableChangeDetailInputItem
 * @DESCRIPTION   : CHANGE DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableChangeDetailInputItem = function() {

	if (this._changemode == "insert") {
		this.$('#changeSe').enable();
		this.$('#changeDt').enable();
		this.$('#changeRsn').enable();
		this.$('#changeCtrtDtFrom').enable();
		this.$('#changeCtrtDtTo').enable();
		this.$('#changeCtrtAmt').enable();
		this.$('#lastCtrtAmt').enable();
		this.$('#changeRm').enable();
		this.$('#btnChangeInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnChangeSave').enable();
		this.$('#btnChangeSave').removeClass('ui-state-disabled');
		this.$('#btnChangeRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._changeKeyValue != "") {
			this.$('#changeSe').enable();
			this.$('#changeDt').enable();
			this.$('#changeRsn').enable();
			this.$('#changeCtrtDtFrom').enable();
			this.$('#changeCtrtDtTo').enable();
			this.$('#changeCtrtAmt').enable();
			this.$('#lastCtrtAmt').enable();
			this.$('#changeRm').enable();
			this.$('#btnChangeInsert').enable();
			this.$('#btnChangeInsert').removeClass('ui-state-disabled');
			this.$('#btnChangeSave').enable();
			this.$('#btnChangeSave').removeClass('ui-state-disabled');
			this.$('#btnChangeRemove').enable();
			this.$('#btnChangeRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#changeSe').disable();
			this.$('#changeDt').disable();
			this.$('#changeRsn').disable();
			this.$('#changeCtrtDtFrom').disable();
			this.$('#changeCtrtDtTo').disable();
			this.$('#changeCtrtAmt').disable();
			this.$('#lastCtrtAmt').disable();
			this.$('#changeRm').disable();
			if (this.$('#changeInfoCtrtNo').val() != "") {
				this.$('#btnChangeInsert').enable();
				this.$('#btnChangeInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnChangeInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnChangeSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnChangeRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableChangeDetailInputItem
 * @DESCRIPTION   : CHANGE DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableChangeDetailInputItem = function() {

	this.$('#changeSe').disable();
	this.$('#changeDt').disable();
	this.$('#changeRsn').disable();
	this.$('#changeCtrtDtFrom').disable();
	this.$('#changeCtrtDtTo').disable();
	this.$('#changeCtrtAmt').disable();
	this.$('#lastCtrtAmt').disable();
	this.$('#changeRm').disable();
	this.$('#btnChangeInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnChangeSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnChangeRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enablePymntDetailInputItem
 * @DESCRIPTION   : PYMNT DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enablePymntDetailInputItem = function() {

	if (this._pymntmode == "insert") {
		this.$('#pymntCl').enable();
		this.$('#pymntDt').enable();
		this.$('#thisTimeEstbAmt').enable();
		this.$('#depositExcclcAmt').enable();
		this.$('#pymntAmt').enable();
		this.$('#pymntAggrAmt').enable();
		this.$('#pymntRm').enable();
		this.$('#btnPymntInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPymntSave').enable();
		this.$('#btnPymntSave').removeClass('ui-state-disabled');
		this.$('#btnPymntRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._pymntKeyValue != "") {
			this.$('#pymntCl').enable();
			this.$('#pymntDt').enable();
			this.$('#thisTimeEstbAmt').enable();
			this.$('#depositExcclcAmt').enable();
			this.$('#pymntAmt').enable();
			this.$('#pymntAggrAmt').enable();
			this.$('#pymntRm').enable();
			this.$('#btnPymntInsert').enable();
			this.$('#btnPymntInsert').removeClass('ui-state-disabled');
			this.$('#btnPymntSave').enable();
			this.$('#btnPymntSave').removeClass('ui-state-disabled');
			this.$('#btnPymntRemove').enable();
			this.$('#btnPymntRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#pymntCl').disable();
			this.$('#pymntDt').disable();
			this.$('#thisTimeEstbAmt').disable();
			this.$('#depositExcclcAmt').disable();
			this.$('#pymntAmt').disable();
			this.$('#pymntAggrAmt').disable();
			this.$('#pymntRm').disable();
			if (this.$('#pymntCtrtNo').val() != "") {
				this.$('#btnPymntInsert').enable();
				this.$('#btnPymntInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnPymntInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnPymntSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPymntRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disablePymntDetailInputItem
 * @DESCRIPTION   : PYMNT DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disablePymntDetailInputItem = function() {

	this.$('#pymntCl').disable();
	this.$('#pymntDt').disable();
	this.$('#thisTimeEstbAmt').disable();
	this.$('#depositExcclcAmt').disable();
	this.$('#pymntAmt').disable();
	this.$('#pymntAggrAmt').disable();
	this.$('#pymntRm').disable();
	this.$('#btnPymntInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPymntSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPymntRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableCaryFwdDetailInputItem
 * @DESCRIPTION   : CARY FWD DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableCaryFwdDetailInputItem = function() {

	if (this._caryfwdmode == "insert") {
		this.$('#fulfillCaryFwdYear').enable();
		this.$('#fulfillAmt').enable();
		this.$('#caryFwdAmt').enable();
		this.$('#btnCaryFwdInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCaryFwdSave').enable();
		this.$('#btnCaryFwdSave').removeClass('ui-state-disabled');
		this.$('#btnCaryFwdRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._caryFwdKeyValue != "") {
			this.$('#fulfillCaryFwdYear').enable();
			this.$('#fulfillAmt').enable();
			this.$('#caryFwdAmt').enable();
			this.$('#btnCaryFwdInsert').enable();
			this.$('#btnCaryFwdInsert').removeClass('ui-state-disabled');
			this.$('#btnCaryFwdSave').enable();
			this.$('#btnCaryFwdSave').removeClass('ui-state-disabled');
			this.$('#btnCaryFwdRemove').enable();
			this.$('#btnCaryFwdRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#fulfillCaryFwdYear').disable();
			this.$('#fulfillAmt').disable();
			this.$('#caryFwdAmt').disable();
			if (this.$('#caryFwdCtrtNo').val() != "") {
				this.$('#btnCaryFwdInsert').enable();
				this.$('#btnCaryFwdInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnCaryFwdInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnCaryFwdSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCaryFwdRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableCaryFwdDetailInputItem
 * @DESCRIPTION   : CARY FWD DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableCaryFwdDetailInputItem = function() {

	this.$('#fulfillCaryFwdYear').disable();
	this.$('#fulfillAmt').disable();
	this.$('#caryFwdAmt').disable();
	this.$('#btnCaryFwdInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCaryFwdSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCaryFwdRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableScsbidDetailInputItem
 * @DESCRIPTION   : SCSBID DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableScsbidDetailInputItem = function() {

	if (this._scsbidmode == "insert") {
		this.$('#scsbidRank').enable();
		this.$('#scsbidEntrpsNm').enable();
		this.$('#scsbidRprsntv').enable();
		this.$('#scsbidBsnmNo').enable();
		this.$('#scsbidTlphonNo').enable();
		this.$('#scsbidFaxNo').enable();
		this.$('#scsbidRm').enable();
		this.$('#popupScsbidEntrpsCd').enable();
		this.$('#popupScsbidEntrpsCd').removeClass('ui-state-disabled');
		this.$('#btnScsbidInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnScsbidSave').enable();
		this.$('#btnScsbidSave').removeClass('ui-state-disabled');
		this.$('#btnScsbidRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._scsbidKeyValue != "") {
			this.$('#scsbidRank').enable();
			this.$('#scsbidEntrpsNm').enable();
			this.$('#scsbidRprsntv').enable();
			this.$('#scsbidBsnmNo').enable();
			this.$('#scsbidTlphonNo').enable();
			this.$('#scsbidFaxNo').enable();
			this.$('#scsbidRm').enable();
			this.$('#popupScsbidEntrpsCd').enable();
			this.$('#popupScsbidEntrpsCd').removeClass('ui-state-disabled');
			this.$('#btnScsbidInsert').enable();
			this.$('#btnScsbidInsert').removeClass('ui-state-disabled');
			this.$('#btnScsbidSave').enable();
			this.$('#btnScsbidSave').removeClass('ui-state-disabled');
			this.$('#btnScsbidRemove').enable();
			this.$('#btnScsbidRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#scsbidRank').disable();
			this.$('#scsbidEntrpsNm').disable();
			this.$('#scsbidRprsntv').disable();
			this.$('#scsbidBsnmNo').disable();
			this.$('#scsbidTlphonNo').disable();
			this.$('#scsbidFaxNo').disable();
			this.$('#scsbidRm').disable();
			this.$('#popupScsbidEntrpsCd').disable({disableClass:"ui-state-disabled"});
			if (this.$('#scsbidCtrtNo').val() != "") {
				this.$('#btnScsbidInsert').enable();
				this.$('#btnScsbidInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnScsbidInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnScsbidSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnScsbidRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableScsbidDetailInputItem
 * @DESCRIPTION   : SCSBID DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableScsbidDetailInputItem = function() {

	this.$('#scsbidRank').disable();
	this.$('#scsbidEntrpsNm').disable();
	this.$('#scsbidRprsntv').disable();
	this.$('#scsbidBsnmNo').disable();
	this.$('#scsbidTlphonNo').disable();
	this.$('#scsbidFaxNo').disable();
	this.$('#scsbidRm').disable();
	this.$('#popupScsbidEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnScsbidInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnScsbidSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnScsbidRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableFlawGrntyDetailInputItem
 * @DESCRIPTION   : FLAW GRNTY DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.enableFlawGrntyDetailInputItem = function() {

	if (this._flawgrntymode == "insert") {
		this.$('#flawEndDt').enable();
		this.$('#flawGrntyStartDt').enable();
		this.$('#flawGrntyEndDt').enable();
		this.$('#flawGrntySrbctAmt').enable();
		this.$('#flawGrntyRate').enable();
		this.$('#flawGrntyCtrtAmt').enable();
		this.$('#flawGrntyInsuNo').enable();
		this.$('#flawGrntyInsuCmpy').enable();
		this.$('#flawGrntyInsuAddr').enable();
		this.$('#flawGrntyInsuRprsntv').enable();
		this.$('#flawGrntyRm').enable();
		this.$('#btnFlawGrntyInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFlawGrntySave').enable();
		this.$('#btnFlawGrntySave').removeClass('ui-state-disabled');
		this.$('#btnFlawGrntyRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._flawGrntyKeyValue != "") {
			this.$('#flawEndDt').enable();
			this.$('#flawGrntyStartDt').enable();
			this.$('#flawGrntyEndDt').enable();
			this.$('#flawGrntySrbctAmt').enable();
			this.$('#flawGrntyRate').enable();
			this.$('#flawGrntyCtrtAmt').enable();
			this.$('#flawGrntyInsuNo').enable();
			this.$('#flawGrntyInsuCmpy').enable();
			this.$('#flawGrntyInsuAddr').enable();
			this.$('#flawGrntyInsuRprsntv').enable();
			this.$('#flawGrntyRm').enable();
			this.$('#btnFlawGrntyInsert').enable();
			this.$('#btnFlawGrntyInsert').removeClass('ui-state-disabled');
			this.$('#btnFlawGrntySave').enable();
			this.$('#btnFlawGrntySave').removeClass('ui-state-disabled');
			this.$('#btnFlawGrntyRemove').enable();
			this.$('#btnFlawGrntyRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#flawEndDt').disable();
			this.$('#flawGrntyStartDt').disable();
			this.$('#flawGrntyEndDt').disable();
			this.$('#flawGrntySrbctAmt').disable();
			this.$('#flawGrntyRate').disable();
			this.$('#flawGrntyCtrtAmt').disable();
			this.$('#flawGrntyInsuNo').disable();
			this.$('#flawGrntyInsuCmpy').disable();
			this.$('#flawGrntyInsuAddr').disable();
			this.$('#flawGrntyInsuRprsntv').disable();
			this.$('#flawGrntyRm').disable();
			if (this.$('#flawGrntyCtrtNo').val() != "") {
				this.$('#btnFlawGrntyInsert').enable();
				this.$('#btnFlawGrntyInsert').removeClass('ui-state-disabled');
			} else {
				this.$('#btnFlawGrntyInsert').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnFlawGrntySave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFlawGrntyRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableFlawGrntyDetailInputItem
 * @DESCRIPTION   : FLAW GRNTY DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtMngModule.prototype.disableFlawGrntyDetailInputItem = function() {

	this.$('#flawEndDt').disable();
	this.$('#flawGrntyStartDt').disable();
	this.$('#flawGrntyEndDt').disable();
	this.$('#flawGrntySrbctAmt').disable();
	this.$('#flawGrntyRate').disable();
	this.$('#flawGrntyCtrtAmt').disable();
	this.$('#flawGrntyInsuNo').disable();
	this.$('#flawGrntyInsuCmpy').disable();
	this.$('#flawGrntyInsuAddr').disable();
	this.$('#flawGrntyInsuRprsntv').disable();
	this.$('#flawGrntyRm').disable();
	this.$('#btnFlawGrntyInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFlawGrntySave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFlawGrntyRemove').disable({disableClass:"ui-state-disabled"});

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
GamFcltyCtrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
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
		case 'joinTab':
			if (this._mainKeyValue != "") {
				var joinCtrtNo = this.$('#joinCtrtNo').val();
				if (joinCtrtNo == "" || joinCtrtNo != this._mainKeyValue) {
					this.loadJoinDetail('listTab');
					this.enableJoinDetailInputItem();
				}
			} else {
				this.makeFormValues('#joinForm', {});
				this.makeDivValues('#joinForm', {});
				this.$('#joinGrid').flexEmptyData();
				this.disableJoinDetailInputItem();
			}
			break;
		case 'subTab':
			if (this._mainKeyValue != "") {
				var subCtrtNo = this.$('#subCtrtNo').val();
				if (subCtrtNo == "" || subCtrtNo != this._mainKeyValue) {
					this.loadSubDetail('listTab');
					this.enableSubDetailInputItem();
				}
			} else {
				this.makeFormValues('#subForm', {});
				this.makeDivValues('#subForm', {});
				this.$('#subGrid').flexEmptyData();
				this.disableJoinDetailInputItem();
			}
			break;
		case 'changeTab':
			if (this._mainKeyValue != "") {
				var changeCtrtNo = this.$('#changeCtrtNo').val();
				if (changeCtrtNo == "" || changeCtrtNo != this._mainKeyValue) {
					this.loadChangeDetail('listTab');
					this.enableChangeDetailInputItem();
				}
			} else {
				this.makeFormValues('#changeForm', {});
				this.makeDivValues('#changeForm', {});
				this.$('#changeGrid').flexEmptyData();
				this.disableChangeDetailInputItem();
			}
			break;
		case 'pymntTab':
			if (this._mainKeyValue != "") {
				var pymntCtrtNo = this.$('#pymntCtrtNo').val();
				if (pymntCtrtNo == "" || pymntCtrtNo != this._mainKeyValue) {
					this.loadPymntDetail('listTab');
					this.enablePymntDetailInputItem();
				}
			} else {
				this.makeFormValues('#pymntForm', {});
				this.makeDivValues('#pymntForm', {});
				this.$('#pymntGrid').flexEmptyData();
				this.disablePymntDetailInputItem();
			}
			break;
		case 'caryFwdTab':
			if (this._mainKeyValue != "") {
				var caryFwdCtrtNo = this.$('#caryFwdCtrtNo').val();
				if (caryFwdCtrtNo == "" || caryFwdCtrtNo != this._mainKeyValue) {
					this.loadCaryFwdDetail('listTab');
					this.enableCaryFwdDetailInputItem();
				}
			} else {
				this.makeFormValues('#caryFwdForm', {});
				this.makeDivValues('#caryFwdForm', {});
				this.$('#caryFwdGrid').flexEmptyData();
				this.disableCaryFwdDetailInputItem();
			}
			break;
		case 'scsbidTab':
			if (this._mainKeyValue != "") {
				var scsbidCtrtNo = this.$('#scsbidCtrtNo').val();
				if (scsbidCtrtNo == "" || scsbidCtrtNo != this._mainKeyValue) {
					this.loadScsbidDetail('listTab');
					this.enableScsbidDetailInputItem();
				}
			} else {
				this.makeFormValues('#scsbidForm', {});
				this.makeDivValues('#scsbidForm', {});
				this.$('#scsbidGrid').flexEmptyData();
				this.disableScsbidDetailInputItem();
			}
			break;
		case 'flawTab':
			if (this._mainKeyValue != "") {
				var flawGrntyCtrtNo = this.$('#flawGrntyCtrtNo').val();
				if (flawGrntyCtrtNo == "" || flawGrntyCtrtNo != this._mainKeyValue) {
					this.loadFlawGrntyDetail('listTab');
					this.enableFlawGrntyDetailInputItem();
				}
			} else {
				this.makeFormValues('#flawGrntyForm', {});
				this.makeDivValues('#flawGrntyForm', {});
				this.$('#flawGrid').flexEmptyData();
				this.disableFlawGrntyDetailInputItem();
			}
			break;
	}

};

var module_instance = new GamFcltyCtrtMngModule();

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
							<th>계약 구분</th>
							<td>
								<select id="sCtrtSe">
									<option value="" selected>전체</option>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
							</td>
							<th>계약 명</th>
							<td>
								<input id="sCtrtNm" type="text" size="25" maxlength="100">
							</td>
							<th>계약 일자 기간</th>
							<td>
								<input id="sStartCtrtDt" type="text" class="emdcal" size="12"> ∼
								<input id="sEndCtrtDt" type="text" class="emdcal" size="12">
							</td>
							<td rowspan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>등록 업체</th>
							<td colspan="3">
								<input id="sRegistEntrpsCd" type="text" size="8" maxlength="8">&nbsp; &nbsp;
								<input id="sRegistEntrpsNm" type="text" size="27" disabled="disabled">&nbsp; &nbsp;
								<button id="popupSearchRegistEntrpsCd" class="popupButton">선택</button>
							</td>
							<th>계약 금액</th>
							<td>
								<input id="sStartCtrtAmt" type="text" class="ygpaNumber" size="15" maxlength="20"> ∼
								<input id="sEndCtrtAmt" type="text" class="ygpaNumber" size="15" maxlength="20">
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
				<li><a href="#listTab" class="emdTab">계약 정보 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">계약 정보 상세</a></li>
				<li><a href="#joinTab" class="emdTab">계약 공동 도급</a></li>
				<li><a href="#subTab" class="emdTab">계약 하도급</a></li>
				<li><a href="#changeTab" class="emdTab">계약 변경</a></li>
				<li><a href="#pymntTab" class="emdTab">계약 대금 지급</a></li>
				<li><a href="#caryFwdTab" class="emdTab">계약 이행 이월</a></li>
				<li><a href="#scsbidTab" class="emdTab">계약 낙찰 정보</a></li>
				<li><a href="#flawTab" class="emdTab">하자 보증</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">설계금액</th>
								<td><input type="text" size="18" id="sumPlanAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">계약금액</th>
								<td><input type="text" size="18" id="sumCtrtAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">낙찰금액</th>
								<td><input type="text" size="18" id="sumScsbidAmt" class="ygpaNumber" disabled="disabled"/></td>
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
								<th style="width:10%; height:27px;">계　약　번　호</th>
								<td>
									<select id="ctrtSe">
										<option value="" selected>선택</option>
										<option value="1">공사</option>
										<option value="2">용역</option>
										<option value="3">지급자재</option>
									</select>
									<input type="text" size="21" id="ctrtNo" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">계　　약　　명</th>
								<td colspan="3">
									<input type="text" size="93" id="ctrtNm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">원　인　행　위</th>
								<td>
									<input type="text" size="33" id="causeAct" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">기　초　금　액</th>
								<td>
									<input type="text" size="30" id="baseAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:27px;">설　계　금　액</th>
								<td>
									<input type="text" size="30" id="planAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">발　주　방　식</th>
								<td>
									<input type="text" size="33" id="orderMthd" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">계　약　방　법</th>
								<td>
									<input type="text" size="33" id="ctrtMth" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">조달 공고 번호</th>
								<td>
									<input type="text" size="33" id="prcuPblancNo" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">입찰 공고 번호</th>
								<td>
									<input type="text" size="33" id="bidPblancNo" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">입찰 공고 일자</th>
								<td>
									<input type="text" size="30" id="bidPblancDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:27px;">입　찰　일　자</th>
								<td>
									<input type="text" size="30" id="bidDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">입　찰　방　법</th>
								<td>
									<input type="text" size="33" id="bidMth" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">등　록　업　체</th>
								<td colspan="3">
									<input type="text" size="15" id="registEntrpsCd" maxlength="8"/>
									<input type="text" size="63" id="registEntrpsNm" disabled/>
									<button id="popupCtrtRegistEntrpsCd" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">낙　　찰　　자</th>
								<td>
									<input type="text" size="33" id="scsbider" maxlength="100"/>
								</td>
								<th style="width:10%; height:27px;">낙　찰　금　액</th>
								<td>
									<input type="text" size="30" id="scsbidAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:27px;">낙　　찰　　율</th>
								<td>
									<input type="text" size="33" id="scsbidRate" class="ygpaNumber" maxlength="6" data-decimal-point="5"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">계　약　일　자</th>
								<td>
									<input type="text" size="30" id="ctrtDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:27px;">계약 보증 금액</th>
								<td>
									<input type="text" size="30" id="ctrtGrntyAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:27px;">계약 보증 방법</th>
								<td>
									<select id="ctrtGrntyMth">
										<option value="" selected>선택</option>
										<option value="10">계약보증서</option>
										<option value="20">지급각서대체</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">계　약　금　액</th>
								<td>
									<input type="text" size="30" id="ctrtAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:27px;">계　약　기　간</th>
								<td>
									<input type="text" size="11" id="ctrtDtFrom" class="emdcal"/> ∼
									<input type="text" size="11" id="ctrtDtTo" class="emdcal"/>
								</td>
								<th style="width:10%; height:27px;">계약 검사 일자</th>
								<td>
									<input type="text" size="30" id="ctrtExamDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">예　정　금　액</th>
								<td>
									<input type="text" size="30" id="prmtAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:27px;">하　자　기　간</th>
								<td>
									<input type="text" size="11" id="flawDtFrom" class="emdcal"/> ∼
									<input type="text" size="11" id="flawDtTo" class="emdcal"/>
								</td>
								<th style="width:10%; height:27px;">연　대　보　증</th>
								<td>
									<input type="text" size="33" id="sldrtGrnty" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">감　　독　　자</th>
								<td>
									<input type="text" size="9" id="intendant1" maxlength="10"/>
									<input type="text" size="9" id="intendant2" maxlength="10"/>
									<input type="text" size="9" id="intendant3" maxlength="10"/>
								</td>
								<th style="width:10%; height:27px;">담당 부서 코드</th>
								<td>
									<input type="text" size="33" id="jobChrgDeptCd" class="ygpaCmmnCd" data-code-id="GAM064" data-default-prompt="없음">
								</td>
								<th style="width:10%; height:27px;">이월 예산 금액</th>
								<td>
									<input type="text" size="30" id="caryFwdBdgtAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:20px;">현　장　설　명</th>
								<td colspan="5">
									<input type="text" size="148" id="siteDesc" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:27px;">품의 거래처 명</th>
								<td>
									<input type="text" size="33" id="cnstCstmrNm" disabled/>
								</td>
								<th style="width:10%; height:27px;">승　인　일　자</th>
								<td>
									<input type="text" size="33" id="confmDt" disabled/>
								</td>
								<th style="width:10%; height:27px;">승인자　　코드</th>
								<td>
									<input type="text" size="33" id="confmerCd" disabled/>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnFirstData">처음자료</button>
								<button id="btnPrevData">이전자료</button>
								<button id="btnNextData">다음자료</button>
								<button id="btnLastData">마지막자료</button>
								<button id="btnCtrtInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnCtrtSave" class="buttonSave">　　저　장　　</button>
								<button id="btnCtrtRemove" class="buttonDelete">　　삭　제　　</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 214. TAB 3 AREA (JOIN) -->
			<div id="joinTab" class="emdTabPage" style="overflow:scroll;">
				<form id="joinForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="joinCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="joinCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="joinCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="joinCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="joinOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="joinCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="joinCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="joinCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="joinCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="joinCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 공동 도급 내역</th>
							<td style="text-align:right;">
								<button id="btnJoinInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnJoinSave" class="buttonSave">　　저　장　　</button>
								<button id="btnJoinRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnJoinExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="joinGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순　번／지분율</th>
							<td>
								<input type="text" size="10" id="joinSeq" disabled/>／
								<input type="text" size="19" id="qotaRate" class="ygpaNumber" data-decimal-point="5" maxlength="20"/>
							</td>
							<th style="width:10%; height:18px;">업　　체　　명</th>
							<td>
								<input type="text" size="21" id="joinEntrpsNm" maxlength="100"/>
								<button id="popupJoinEntrpsCd" class="popupButton">선택</button>
							</td>
							<th style="width:10%; height:18px;">대　　표　　자</th>
							<td>
								<input type="text" size="33" id="joinRprsntv" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">사업자　　번호</th>
							<td>
								<input type="text" size="33" id="joinBsnmNo" maxlength="14"/>
							</td>
							<th style="width:10%; height:18px;">업　　　　　종</th>
							<td>
								<input type="text" size="33" id="induty" maxlength="40"/>
							</td>
							<th style="width:10%; height:18px;">주　요　품　목</th>
							<td>
								<input type="text" size="33" id="stplPrdlst" maxlength="40"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">전　화　번　호</th>
							<td>
								<input type="text" size="33" id="joinTlphonNo" maxlength="100"/>
							</td>
							<th style="width:10%; height:18px;">팩　스　번　호</th>
							<td>
								<input type="text" size="33" id="joinFaxNo" maxlength="100"/>
							</td>
							<th style="width:10%; height:18px;">담　　당　　자</th>
							<td>
								<input type="text" size="33" id="charger" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">담당자　　직위</th>
							<td>
								<input type="text" size="33" id="chargerOfcPos" maxlength="20"/>
							</td>
							<th style="width:10%; height:18px;">담당자　　H　P</th>
							<td>
								<input type="text" size="33" id="chargerMoblphonNo" maxlength="20"/>
							</td>
							<th style="width:10%; height:18px;">담당자　E-MAIL</th>
							<td>
								<input type="text" size="33" id="chargerEmail" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">우　편　번　호</th>
							<td>
								<input type="text" size="33" id="postNo" maxlength="7"/>
							</td>
							<th style="width:10%; height:18px;">지　번　주　소</th>
							<td colspan="3">
								<input type="text" size="93" id="lnmAdres" maxlength="200"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">거　래　관　계</th>
							<td>
								<input type="text" size="33" id="dealRelate" maxlength="20"/>
							</td>
							<th style="width:10%; height:18px;">도로명　　주소</th>
							<td colspan="3">
								<input type="text" size="93" id="roadnmAdres" maxlength="200"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 214. TAB 4 AREA (SUB) -->
			<div id="subTab" class="emdTabPage" style="overflow:scroll;">
				<form id="subForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="subCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="subCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="subCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="subCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="subOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="subCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="subCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="subCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="subCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="subCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 하도급 내역</th>
							<td style="text-align:right;">
								<button id="btnSubInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnSubSave" class="buttonSave">　　저　장　　</button>
								<button id="btnSubRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnSubExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="subGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순　　　　　번</th>
							<td>
								<input type="text" size="33" id="subSeq" disabled/>
							</td>
							<th style="width:10%; height:18px;">업　　체　　명</th>
							<td>
								<input type="text" size="21" id="subEntrpsNm" maxlength="100"/>
								<button id="popupSubEntrpsCd" class="popupButton">선택</button>
							</td>
							<th style="width:10%; height:18px;">하도급계약기간</th>
							<td>
								<input type="text" size="11" id="subctrtCtrtDtFrom" class="emdcal"/> ∼
								<input type="text" size="11" id="subctrtCtrtDtTo" class="emdcal"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">대금 지급 합의</th>
							<td>
								<select id="moneyPymntAgree">
									<option value="" selected>선택</option>
									<option value="10">하도급지급합의</option>
									<option value="20">하도급지급미합의</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">하　도　급　율</th>
							<td>
								<input type="text" size="33" id="subctrtRate" class="ygpaNumber" data-decimal-point="5" maxlength="20"/>
							</td>
							<th style="width:10%; height:18px;">원도급　　금액</th>
							<td>
								<input type="text" size="30" id="orginlContrAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">하도급계약금액</th>
							<td>
								<input type="text" size="30" id="subctrtCtrtAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">공　　　　　종</th>
							<td colspan="3">
								<input type="text" size="92" id="workClass" maxlength="100"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 215. TAB 5 AREA (CHANGE) -->
			<div id="changeTab" class="emdTabPage" style="overflow:scroll;">
				<form id="changeForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="changeInfoCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="changeInfoCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="changeInfoCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="changeInfoCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="changeInfoOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="changeInfoCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="changeInfoCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="changeInfoCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="changeInfoCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="changeInfoCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 변경 내역</th>
							<td style="text-align:right;">
								<button id="btnChangeInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnChangeSave" class="buttonSave">　　저　장　　</button>
								<button id="btnChangeRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnChangeExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="changeGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순　　　　　번</th>
							<td>
								<input type="text" size="33" id="changeSeq" disabled/>
							</td>
							<th style="width:10%; height:18px;">변　경　구　분</th>
							<td>
								<select id="changeSe">
									<option value="" selected>선택</option>
									<option value="1">기간변경</option>
									<option value="2">금액변경</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">변　경　일　자</th>
							<td>
								<input type="text" size="30" id="changeDt" class="emdcal"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">변　경　사　유</th>
							<td colspan="5">
								<input type="text" size="150" id="changeRsn" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">변경 계약 기간</th>
							<td>
								<input type="text" size="11" id="changeCtrtDtFrom" class="emdcal"/> ~
								<input type="text" size="11" id="changeCtrtDtTo" class="emdcal"/>
							</td>
							<th style="width:10%; height:18px;">변경 계약 금액</th>
							<td>
								<input type="text" size="30" id="changeCtrtAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">최종 계약 금액</th>
							<td>
								<input type="text" size="30" id="lastCtrtAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="changeRm" maxlength="1000"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 216. TAB 6 AREA (PYMNT) -->
			<div id="pymntTab" class="emdTabPage" style="overflow:scroll;">
				<form id="pymntForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="pymntCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="pymntCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="pymntCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="pymntCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="pymntOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="pymntCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="pymntCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="pymntCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="pymntCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="pymntCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 대금 지급 내역</th>
							<td style="text-align:right;">
								<button id="btnPymntInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnPymntSave" class="buttonSave">　　저　장　　</button>
								<button id="btnPymntRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnPymntExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="pymntGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순번／지급분류</th>
							<td>
								<input type="text" size="10" id="pymntSeq" disabled/>／
								<select id="pymntCl">
									<option value="" selected>선택</option>
									<option value="10">선급금</option>
									<option value="20">기성금</option>
									<option value="30">잔금</option>
								</select>
							</td>
							<th style="width:10%; height:18px;">지　급　일　자</th>
							<td>
								<input type="text" size="30" id="pymntDt" class="emdcal"/>
							</td>
							<th style="width:10%; height:18px;">금회 기성 금액</th>
							<td>
								<input type="text" size="30" id="thisTimeEstbAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">선금 정산 금액</th>
							<td>
								<input type="text" size="30" id="depositExcclcAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">지　급　금　액</th>
							<td>
								<input type="text" size="30" id="pymntAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">지급 누계 금액</th>
							<td>
								<input type="text" size="30" id="pymntAggrAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="pymntRm" maxlength="1000"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 217. TAB 7 AREA (CARYFWD) -->
			<div id="caryFwdTab" class="emdTabPage" style="overflow:scroll;">
				<form id="caryFwdForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="caryFwdCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="caryFwdCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="caryFwdCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="caryFwdCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="caryFwdOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="caryFwdCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="caryFwdCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="caryFwdCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="caryFwdCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="caryFwdCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 이행 이월 내역</th>
							<td style="text-align:right;">
								<button id="btnCaryFwdInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnCaryFwdSave" class="buttonSave">　　저　장　　</button>
								<button id="btnCaryFwdRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnCaryFwdExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="caryFwdGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순번／이월년도</th>
							<td>
								<input type="text" size="10" id="caryFwdSeq" disabled/>／
								<input type="text" size="19" id="fulfillCaryFwdYear" maxlength="4"/>
							</td>
							<th style="width:10%; height:18px;">이　행　금　액</th>
							<td>
								<input type="text" size="30" id="fulfillAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">이　월　금　액</th>
							<td>
								<input type="text" size="30" id="caryFwdAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 218. TAB 8 AREA (SCSBID) -->
			<div id="scsbidTab" class="emdTabPage" style="overflow:scroll;">
				<form id="scsbidForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="scsbidCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="scsbidCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="scsbidCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="scsbidCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="scsbidOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="scsbidCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="scsbidCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="scsbidCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="11" id="scsbidCtrtDtFrom" disabled/> ∼
								<input type="text" size="11" id="scsbidCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 낙찰 정보 내역</th>
							<td style="text-align:right;">
								<button id="btnScsbidInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnScsbidSave" class="buttonSave">　　저　장　　</button>
								<button id="btnScsbidRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnScsbidExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="scsbidGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순번／낙찰순위</th>
							<td>
								<input type="text" size="10" id="scsbidSeq" disabled/>／
								<input type="text" size="19" id="scsbidRank" maxlength="3"/>
							</td>
							<th style="width:10%; height:18px;">업　　체　　명</th>
							<td>
								<input type="text" size="21" id="scsbidEntrpsNm" maxlength="100"/>
								<button id="popupScsbidEntrpsCd" class="popupButton">선택</button>
							</td>
							<th style="width:10%; height:18px;">대　　표　　자</th>
							<td>
								<input type="text" size="33" id="scsbidRprsntv" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">사업자　　번호</th>
							<td>
								<input type="text" size="33" id="scsbidBsnmNo" maxlength="14"/>
							</td>
							<th style="width:10%; height:18px;">전　화　번　호</th>
							<td>
								<input type="text" size="33" id="scsbidTlphonNo" maxlength="100"/>
							</td>
							<th style="width:10%; height:18px;">팩　스　번　호</th>
							<td>
								<input type="text" size="33" id="scsbidFaxNo" maxlength="100"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="scsbidRm" maxlength="1000"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 219. TAB 9 AREA (FLAW GRNTY) -->
			<div id="flawTab" class="emdTabPage" style="overflow:scroll;">
				<form id="flawGrntyForm">
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 정보 내역</th>
						</tr>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">계　약　번　호</th>
							<td>
								<select id="flawCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
								<input type="text" size="19" id="flawGrntyCtrtNo" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="92" id="flawCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="flawCauseAct" disabled/>
							</td>
							<th style="width:10%; height:18px;">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="flawOrderMthd" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="flawCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="flawCtrtDt" disabled/>
							</td>
							<th style="width:10%; height:18px;">계　약　금　액</th>
							<td>
								<input type="text" size="30" id="flawCtrtAmt" class="ygpaNumber" disabled/> 원
							</td>
							<th style="width:10%; height:18px;">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="flawCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="flawCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 하자 보증 내역</th>
							<td style="text-align:right;">
								<button id="btnFlawGrntyInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnFlawGrntySave" class="buttonSave">　　저　장　　</button>
								<button id="btnFlawGrntyRemove" class="buttonDelete">　　삭　제　　</button>
                                <button id="btnFlawGrntyExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="flawGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th style="width:10%; height:18px;">순　　　　　번</th>
							<td>
								<input type="text" size="33" id="flawGrntySeq" disabled/>
							</td>
							<th style="width:10%; height:18px;">하자　　만료일</th>
							<td>
								<input type="text" size="30" id="flawEndDt" class="emdcal"/>
							</td>
							<th style="width:10%; height:18px;">하자 보증 기간</th>
							<td>
								<input type="text" size="11" id="flawGrntyStartDt" class="emdcal"/> ~
								<input type="text" size="11" id="flawGrntyEndDt" class="emdcal"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">보증 증권 번호</th>
							<td>
								<input type="text" size="33" id="flawGrntyInsuNo" maxlength="30"/>
							</td>
							<th style="width:10%; height:18px;">보증 가입 금액</th>
							<td>
								<input type="text" size="30" id="flawGrntySrbctAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
							<th style="width:10%; height:18px;">보증 계약 금액</th>
							<td>
								<input type="text" size="30" id="flawGrntyCtrtAmt" class="ygpaNumber" maxlength="20"/> 원
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">보증 보험 회사</th>
							<td>
								<input type="text" size="33" id="flawGrntyInsuCmpy" maxlength="50"/>
							</td>
							<th style="width:10%; height:18px;">보증 보험 주소</th>
							<td>
								<input type="text" size="33" id="flawGrntyInsuAddr" maxlength="100"/>
							</td>
							<th style="width:10%; height:18px;">보증보험대표자</th>
							<td>
								<input type="text" size="33" id="flawGrntyInsuRprsntv" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th style="width:10%; height:18px;">보증　　보험율</th>
							<td>
								<input type="text" size="30" id="flawGrntyRate" class="ygpaNumber" maxlength="3"/>％
							</td>
							<th style="width:10%; height:18px;">비　　　　　고</th>
							<td colspan="3">
								<input type="text" size="92" id="flawGrntyRm" maxlength="1000"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
