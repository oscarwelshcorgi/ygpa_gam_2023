<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtLgerHist.jsp
 * @Description : 시설물 계약대장 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.28  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.28
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
 * @FUNCTION NAME : GamFcltyCtrtLgerHistModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyCtrtLgerHistModule() {}

GamFcltyCtrtLgerHistModule.prototype = new EmdModule(1000, 645);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistList.do',
		dataType : 'json',
		colModel : [
					{display:'계약 번호',			name:'ctrtNo',				width:120,		sortable:false,		align:'center'},
					{display:'구분',				name:'ctrtSeNm',			width:60,		sortable:false,		align:'center'},
					{display:'발주 방식',			name:'orderMthd',			width:100,		sortable:false,		align:'left'},
					{display:'계약 명',				name:'ctrtNm',				width:200,		sortable:false,		align:'left'},
					{display:'계약 방법',			name:'ctrtMth',				width:100,		sortable:false,		align:'left'},
					{display:'계약 일자',			name:'ctrtDt',				width:80,		sortable:false,		align:'center'},
					{display:'계약 금액',			name:'ctrtAmt',				width:100,		sortable:false,		align:'right'},
					{display:'계약 시작 일자',		name:'ctrtDtFrom',			width:100,		sortable:false,		align:'center'},
					{display:'계약 종료 일자',		name:'ctrtDtTo',			width:100,		sortable:false,		align:'center'},
					{display:'계약 보증 금액',		name:'ctrtGrntyAmt',		width:100,		sortable:false,		align:'right'},
					{display:'계약 보증 방법',		name:'ctrtGrntyMthNm',		width:100,		sortable:false,		align:'left'},
					{display:'계약 검사 일자',		name:'ctrtExamDt',			width:100,		sortable:false,		align:'center'},
					{display:'원인 행위',			name:'causeAct',			width:200,		sortable:false,		align:'left'},
					{display:'등록 업체 명',		name:'registEntrpsNm',		width:150,		sortable:false,		align:'left'},
					{display:'조달 공고 번호',		name:'prcuPblancNo',		width:100,		sortable:false,		align:'left'},
					{display:'입찰 공고 번호',		name:'bidPblancNo',			width:100,		sortable:false,		align:'left'},
					{display:'입찰 공고 일자',		name:'bidPblancDt',			width:90,		sortable:false,		align:'center'},
					{display:'입찰 일자',			name:'bidDt',				width:80,		sortable:false,		align:'center'},
					{display:'입찰 방법',			name:'bidMth',				width:100,		sortable:false,		align:'left'},
					{display:'낙찰 자',				name:'scsbider',			width:100,		sortable:false,		align:'left'},
					{display:'낙찰 금액',			name:'scsbidAmt',			width:100,		sortable:false,		align:'right'},
					{display:'낙찰 율',				name:'scsbidRate',			width:80,		sortable:false,		align:'right'},
					{display:'기초 금액',			name:'baseAmt',				width:100,		sortable:false,		align:'right'},
					{display:'설계 금액',			name:'planAmt',				width:100,		sortable:false,		align:'right'},
					{display:'예정 금액',			name:'prmtAmt',				width:100,		sortable:false,		align:'right'},
					{display:'이월 예산 금액',		name:'caryFwdBdgtAmt',		width:100,		sortable:false,		align:'right'},
					{display:'감독자 1',			name:'intendant1',			width:80,		sortable:false,		align:'left'},
					{display:'감독자 2',			name:'intendant2',			width:80,		sortable:false,		align:'left'},
					{display:'감독자 2',			name:'intendant3',			width:80,		sortable:false,		align:'left'},
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
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.ctrtNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#joinGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistJoinContrList.do',
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

	this.$("#joinGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._joinmode = 'modify';
		module._joinKeyValue = row.joinCtrtNo;
		module._joinSeq = row.joinSeq;
		module.$('#joinSeq').val(row.joinSeq);
		module.loadJoinDetail('joinTab');
	});

	this.$("#subGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistSubctrtList.do',
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

	this.$("#subGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._submode = 'modify';
		module._subKeyValue = row.subCtrtNo;
		module._subSeq = row.subSeq;
		module.$('#subSeq').val(row.subSeq);
		module.loadSubDetail('subTab');
	});

	this.$("#changeGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistChangeList.do',
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

	this.$("#changeGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._changemode = 'modify';
		module._changeKeyValue = row.changeCtrtNo;
		module._changeSeq = row.changeSeq;
		module.$('#changeSeq').val(row.changeSeq);
		module.loadChangeDetail('changeTab');
	});

	this.$("#pymntGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistMoneyPymntList.do',
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

	this.$("#pymntGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._pymntmode = 'modify';
		module._pymntKeyValue = row.pymntCtrtNo;
		module._pymntSeq = row.pymntSeq;
		module.$('#pymntSeq').val(row.pymntSeq);
		module.loadPymntDetail('pymntTab');
	});

	this.$("#caryFwdGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistFulfillCaryFwdList.do',
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

	this.$("#caryFwdGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._caryfwdmode = 'modify';
		module._caryFwdKeyValue = row.caryFwdCtrtNo;
		module._caryFwdSeq = row.caryFwdSeq;
		module.$('#caryFwdSeq').val(row.caryFwdSeq);
		module.loadCaryFwdDetail('caryFwdTab');
	});

	this.$("#scsbidGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtLgerHistScsbidInfoList.do',
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

	this.$("#scsbidGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._scsbidmode = 'modify';
		module._scsbidKeyValue = row.scsbidCtrtNo;
		module._scsbidSeq = row.scsbidSeq;
		module.$('#scsbidSeq').val(row.scsbidSeq);
		module.loadScsbidDetail('scsbidTab');
	});

	this.$("#sRegistEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getSearchEntrpsNm();
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
	this._searchButtonClick = false;
	var year = new Date().getFullYear();
	this.$('#sStartCtrtDt').val(year + '-01-01');
	this.$('#sEndCtrtDt').val(year + '-12-31');

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
GamFcltyCtrtLgerHistModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchRegistEntrpsCd':
			if (msg == 'ok') {
				this.$('#sRegistEntrpsCd').val(value.entrpscd);
				this.$('#sRegistEntrpsNm').val(value.entrpsNm);
				this.$('#sStartCtrtAmt').focus();
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
GamFcltyCtrtLgerHistModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnJoinExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnSubExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnChangeExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnPymntExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnCaryFwdExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnScsbidExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'popupSearchRegistEntrpsCd':
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
GamFcltyCtrtLgerHistModule.prototype.onSubmit = function() {

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
GamFcltyCtrtLgerHistModule.prototype.loadData = function() {

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
GamFcltyCtrtLgerHistModule.prototype.loadDetail = function(tabId){

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtLgerHistModule.prototype.selectData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (this._mainmode == 'query') {
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

};

<%
/**
 * @FUNCTION NAME : copyCtrtInfoData
 * @DESCRIPTION   : CTRT INFO 항목을 복사한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.copyCtrtInfoData = function(tabId) {

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
GamFcltyCtrtLgerHistModule.prototype.loadJoinDetail = function(tabId) {

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
		var row = this.$('#joinGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#joinForm', row[0]);
		this.makeDivValues('#joinForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : loadSubDetail
 * @DESCRIPTION   : SUB 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadSubDetail = function(tabId) {

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
		var row = this.$('#subGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#subForm', row[0]);
		this.makeDivValues('#subForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : loadChangeDetail
 * @DESCRIPTION   : CHANGE 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadChangeDetail = function(tabId) {

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
		var row = this.$('#changeGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#changeForm', row[0]);
		this.makeDivValues('#changeForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : loadPymntDetail
 * @DESCRIPTION   : PYMNT 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadPymntDetail = function(tabId) {

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
		var row = this.$('#pymntGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#pymntForm', row[0]);
		this.makeDivValues('#pymntForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : loadCaryFwdDetail
 * @DESCRIPTION   : CARYFWD 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadCaryFwdDetail = function(tabId) {

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
		var row = this.$('#caryFwdGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#caryFwdForm', row[0]);
		this.makeDivValues('#caryFwdForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : loadScsbidDetail
 * @DESCRIPTION   : SCSBID 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.loadScsbidDetail = function(tabId) {

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
		var row = this.$('#scsbidGrid').selectedRows();
		if (row.length==0) {
			return;
		}
		this.makeFormValues('#scsbidForm', row[0]);
		this.makeDivValues('#scsbidForm', row[0]);
	}

};

<%
/**
 * @FUNCTION NAME : getSearchEntrpsNm
 * @DESCRIPTION   : 조회조건 업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtLgerHistModule.prototype.getSearchEntrpsNm = function() {

	var sRegistEntrpsCd = this.$('#sRegistEntrpsCd').val();
	if (sRegistEntrpsCd.length == 8) {
		var searchVO = { 'sEntrpscd':sRegistEntrpsCd };
		this.doAction('/ctrt/gamSelectFcltyCtrtLgerHistEntrpsInfo.do', searchVO, function(module, result) {
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
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyCtrtLgerHistModule.prototype.downloadExcel = function(buttonId) {

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
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			this.$('#mainGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHist.do');
			break;
		case 'btnJoinExcelDownload':
			this.$('#joinGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistJoinContr.do');
			break;
		case 'btnSubExcelDownload':
			this.$('#subGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistSubctrt.do');
			break;
		case 'btnChangeExcelDownload':
			this.$('#changeGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistChange.do');
			break;
		case 'btnPymntExcelDownload':
			this.$('#pymntGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistMoneyPymnt.do');
			break;
		case 'btnCaryFwdExcelDownload':
			this.$('#caryFwdGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistFulfillCaryFwd.do');
			break;
		case 'btnScsbidExcelDownload':
			this.$('#scsbidGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtLgerHistScsbidInfo.do');
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
GamFcltyCtrtLgerHistModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
			}
			break;
		case 'joinTab':
			if (this._mainKeyValue != "") {
				var joinCtrtNo = this.$('#joinCtrtNo').val();
				if (joinCtrtNo == "" || joinCtrtNo != this._mainKeyValue) {
					this.loadJoinDetail('listTab');
				}
			} else {
				this.makeFormValues('#joinForm', {});
				this.makeDivValues('#joinForm', {});
				this.$('#joinGrid').flexEmptyData();
			}
			break;
		case 'subTab':
			if (this._mainKeyValue != "") {
				var subCtrtNo = this.$('#subCtrtNo').val();
				if (subCtrtNo == "" || subCtrtNo != this._mainKeyValue) {
					this.loadSubDetail('listTab');
				}
			} else {
				this.makeFormValues('#subForm', {});
				this.makeDivValues('#subForm', {});
				this.$('#subGrid').flexEmptyData();
			}
			break;
		case 'changeTab':
			if (this._mainKeyValue != "") {
				var changeCtrtNo = this.$('#changeCtrtNo').val();
				if (changeCtrtNo == "" || changeCtrtNo != this._mainKeyValue) {
					this.loadChangeDetail('listTab');
				}
			} else {
				this.makeFormValues('#changeForm', {});
				this.makeDivValues('#changeForm', {});
				this.$('#changeGrid').flexEmptyData();
			}
			break;
		case 'pymntTab':
			if (this._mainKeyValue != "") {
				var pymntCtrtNo = this.$('#pymntCtrtNo').val();
				if (pymntCtrtNo == "" || pymntCtrtNo != this._mainKeyValue) {
					this.loadPymntDetail('listTab');
				}
			} else {
				this.makeFormValues('#pymntForm', {});
				this.makeDivValues('#pymntForm', {});
				this.$('#pymntGrid').flexEmptyData();
			}
			break;
		case 'caryFwdTab':
			if (this._mainKeyValue != "") {
				var caryFwdCtrtNo = this.$('#caryFwdCtrtNo').val();
				if (caryFwdCtrtNo == "" || caryFwdCtrtNo != this._mainKeyValue) {
					this.loadCaryFwdDetail('listTab');
				}
			} else {
				this.makeFormValues('#caryFwdForm', {});
				this.makeDivValues('#caryFwdForm', {});
				this.$('#caryFwdGrid').flexEmptyData();
			}
			break;
		case 'scsbidTab':
			if (this._mainKeyValue != "") {
				var scsbidCtrtNo = this.$('#scsbidCtrtNo').val();
				if (scsbidCtrtNo == "" || scsbidCtrtNo != this._mainKeyValue) {
					this.loadScsbidDetail('listTab');
				}
			} else {
				this.makeFormValues('#scsbidForm', {});
				this.makeDivValues('#scsbidForm', {});
				this.$('#scsbidGrid').flexEmptyData();
			}
			break;
	}

};

var module_instance = new GamFcltyCtrtLgerHistModule();

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
							<th>계약 기간</th>
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
								<th style="width:6%; height:20; text-align:center;">기초금액</th>
								<td><input type="text" size="18" id="sumBaseAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">설계금액</th>
								<td><input type="text" size="18" id="sumPlanAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">낙찰금액</th>
								<td><input type="text" size="18" id="sumScsbidAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:6%; height:20; text-align:center;">계약금액</th>
								<td><input type="text" size="18" id="sumCtrtAmt" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
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
								<th width="10%" height="27px">계　약　번　호</th>
								<td>
									<select id="ctrtSe" disabled>
										<option value="" selected>선택</option>
										<option value="1">공사</option>
										<option value="2">용역</option>
										<option value="3">지급자재</option>
									</select>-
									<input type="text" size="19" id="ctrtNo" disabled/>
								</td>
								<th width="10%" height="27px">계　　약　　명</th>
								<td colspan="3">
									<input type="text" size="93" id="ctrtNm" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">원　인　행　위</th>
								<td>
									<input type="text" size="33" id="causeAct" disabled/>
								</td>
								<th width="10%" height="27px">기　초　금　액</th>
								<td>
									<input type="text" size="33" id="baseAmt" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">설　계　금　액</th>
								<td>
									<input type="text" size="33" id="planAmt" class="ygpaNumber" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">발　주　방　식</th>
								<td>
									<input type="text" size="33" id="orderMthd" disabled/>
								</td>
								<th width="10%" height="27px">계　약　방　법</th>
								<td>
									<input type="text" size="33" id="ctrtMth" disabled/>
								</td>
								<th width="10%" height="27px">조달 공고 번호</th>
								<td>
									<input type="text" size="33" id="prcuPblancNo" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">입찰 공고 번호</th>
								<td>
									<input type="text" size="33" id="bidPblancNo" disabled/>
								</td>
								<th width="10%" height="27px">입찰 공고 일자</th>
								<td>
									<input type="text" size="33" id="bidPblancDt" disabled/>
								</td>
								<th width="10%" height="27px">입　찰　일　자</th>
								<td>
									<input type="text" size="33" id="bidDt" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">입　찰　방　법</th>
								<td>
									<input type="text" size="33" id="bidMth" disabled/>
								</td>
								<th width="10%" height="27px">등　록　업　체</th>
								<td colspan="3">
									<input type="text" size="15" id="registEntrpsCd" disabled/>
									<input type="text" size="76" id="registEntrpsNm" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">낙　　찰　　자</th>
								<td>
									<input type="text" size="33" id="scsbider" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">낙　찰　금　액</th>
								<td>
									<input type="text" size="33" id="scsbidAmt" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">낙　　찰　　율</th>
								<td>
									<input type="text" size="33" id="scsbidRate" class="ygpaNumber" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">계　약　일　자</th>
								<td>
									<input type="text" size="33" id="ctrtDt" disabled/>
								</td>
								<th width="10%" height="27px">계약 보증 금액</th>
								<td>
									<input type="text" size="33" id="ctrtGrntyAmt" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">계약 보증 방법</th>
								<td>
									<select id="ctrtGrntyMth" disabled>
										<option value="" selected>선택</option>
										<option value="10">계약보증서</option>
										<option value="20">지급각서대체</option>
									</select>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">계　약　금　액</th>
								<td>
									<input type="text" size="33" id="ctrtAmt" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">계　약　기　간</th>
								<td>
									<input type="text" size="14" id="ctrtDtFrom" disabled/> ∼
									<input type="text" size="14" id="ctrtDtTo" disabled/>
								</td>
								<th width="10%" height="27px">계약 검사 일자</th>
								<td>
									<input type="text" size="33" id="ctrtExamDt" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">예　정　금　액</th>
								<td>
									<input type="text" size="33" id="prmtAmt" class="ygpaNumber" disabled/>
								</td>
								<th width="10%" height="27px">하　자　기　간</th>
								<td>
									<input type="text" size="14" id="flawDtFrom" disabled/> ∼
									<input type="text" size="14" id="flawDtTo" disabled/>
								</td>
								<th width="10%" height="27px">연　대　보　증</th>
								<td>
									<input type="text" size="33" id="sldrtGrnty" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">감　　독　　자</th>
								<td>
									<input type="text" size="9" id="intendant1" disabled/>-
									<input type="text" size="9" id="intendant2" disabled/>-
									<input type="text" size="9" id="intendant3" disabled/>
								</td>
								<th width="10%" height="27px">담당 부서 코드</th>
								<td>
									<input type="text" size="33" id="jobChrgDeptCd" class="ygpaCmmnCd" data-code-id="GAM064" data-default-prompt="없음" disabled>
								</td>
								<th width="10%" height="27px">이월 예산 금액</th>
								<td>
									<input type="text" size="33" id="caryFwdBdgtAmt" class="ygpaNumber" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:20px;">현　장　설　명</th>
								<td colspan="5">
									<input type="text" size="148" id="siteDesc" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="27px">품의 거래처 명</th>
								<td>
									<input type="text" size="33" id="cnstCstmrNm" disabled/>
								</td>
								<th width="10%" height="27px">승　인　일　자</th>
								<td>
									<input type="text" size="33" id="confmDt" disabled/>
								</td>
								<th width="10%" height="27px">승인자　　코드</th>
								<td>
									<input type="text" size="33" id="confmerCd" disabled/>
								</td>
							</tr>
						</table>
					</form>
					<!--
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnCtrtPrint" class="buttonPrint">계　약　대　장　출　력</button>
							</td>
						</tr>
					</table>
					 -->
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="joinCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="joinCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="joinCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="joinCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="joinOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="joinCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="joinCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="joinCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
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
                                <button id="btnJoinExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="joinGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순　번／지분율</th>
							<td>
								<input type="text" size="10" id="joinSeq" disabled/>／
								<input type="text" size="19" id="qotaRate" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">업　　체　　명</th>
							<td>
								<input type="text" size="33" id="joinEntrpsNm" disabled/>
							</td>
							<th width="10%" height="18">대　　표　　자</th>
							<td>
								<input type="text" size="33" id="joinRprsntv" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">사업자　　번호</th>
							<td>
								<input type="text" size="33" id="joinBsnmNo" disabled/>
							</td>
							<th width="10%" height="18">업　　　　　종</th>
							<td>
								<input type="text" size="33" id="induty" disabled/>
							</td>
							<th width="10%" height="18">주　요　품　목</th>
							<td>
								<input type="text" size="33" id="stplPrdlst" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">전　화　번　호</th>
							<td>
								<input type="text" size="33" id="joinTlphonNo" disabled/>
							</td>
							<th width="10%" height="18">팩　스　번　호</th>
							<td>
								<input type="text" size="33" id="joinFaxNo" disabled/>
							</td>
							<th width="10%" height="18">담　　당　　자</th>
							<td>
								<input type="text" size="33" id="charger" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">담당자　　직위</th>
							<td>
								<input type="text" size="33" id="chargerOfcPos" disabled/>
							</td>
							<th width="10%" height="18">담당자　　H　P</th>
							<td>
								<input type="text" size="33" id="chargerMoblphonNo" disabled/>
							</td>
							<th width="10%" height="18">담당자　E-MAIL</th>
							<td>
								<input type="text" size="33" id="chargerEmail" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">우　편　번　호</th>
							<td>
								<input type="text" size="33" id="postNo" disabled/>
							</td>
							<th width="10%" height="18">지　번　주　소</th>
							<td colspan="3">
								<input type="text" size="93" id="lnmAdres" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">거　래　관　계</th>
							<td>
								<input type="text" size="33" id="dealRelate" disabled/>
							</td>
							<th width="10%" height="18">도로명　　주소</th>
							<td colspan="3">
								<input type="text" size="93" id="roadnmAdres" disabled/>
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="subCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="subCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="subCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="subCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="subOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="subCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="subCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="subCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
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
                                <button id="btnSubExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="subGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순　　　　　번</th>
							<td>
								<input type="text" size="33" id="subSeq" disabled/>
							</td>
							<th width="10%" height="18">업　　체　　명</th>
							<td>
								<input type="text" size="33" id="subEntrpsNm" disabled/>
							</td>
							<th width="10%" height="18">하도급계약기간</th>
							<td>
								<input type="text" size="14" id="subctrtCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="subctrtCtrtDtTo" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">대금 지급 합의</th>
							<td>
								<select id="moneyPymntAgree" disabled>
									<option value="" selected>선택</option>
									<option value="10">하도급지급합의</option>
									<option value="20">하도급지급미합의</option>
								</select>
							</td>
							<th width="10%" height="18">하　도　급　율</th>
							<td>
								<input type="text" size="33" id="subctrtRate" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">원도급　　금액</th>
							<td>
								<input type="text" size="33" id="orginlContrAmt" class="ygpaNumber" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">하도급계약금액</th>
							<td>
								<input type="text" size="33" id="subctrtCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">공　　　　　종</th>
							<td colspan="3">
								<input type="text" size="93" id="workClass" disabled/>
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="changeInfoCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="changeInfoCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="changeInfoCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="changeInfoCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="changeInfoOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="changeInfoCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="changeInfoCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="changeInfoCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
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
                                <button id="btnChangeExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="changeGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순　　　　　번</th>
							<td>
								<input type="text" size="33" id="changeSeq" disabled/>
							</td>
							<th width="10%" height="18">변　경　구　분</th>
							<td>
								<select id="changeSe" disabled>
									<option value="" selected>선택</option>
									<option value="1">기간변경</option>
									<option value="2">금액변경</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th width="10%" height="18">변　경　일　자</th>
							<td>
								<input type="text" size="33" id="changeDt" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">변　경　사　유</th>
							<td colspan="5">
								<input type="text" size="150" id="changeRsn" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">변경 계약 기간</th>
							<td>
								<input type="text" size="14" id="changeCtrtDtFrom" disabled/> ~
								<input type="text" size="14" id="changeCtrtDtTo" disabled/>
							</td>
							<th width="10%" height="18">변경 계약 금액</th>
							<td>
								<input type="text" size="33" id="changeCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">최종 계약 금액</th>
							<td>
								<input type="text" size="33" id="lastCtrtAmt" class="ygpaNumber" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="changeRm" disabled/>
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="pymntCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="pymntCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="pymntCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="pymntCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="pymntOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="pymntCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="pymntCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="pymntCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
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
                                <button id="btnPymntExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="pymntGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순번／지급분류</th>
							<td>
								<input type="text" size="10" id="pymntSeq" disabled/>／
								<select id="pymntCl" disabled>
									<option value="" selected>선택</option>
									<option value="10">선급금</option>
									<option value="20">기성금</option>
									<option value="30">잔금</option>
								</select>
							</td>
							<th width="10%" height="18">지　급　일　자</th>
							<td>
								<input type="text" size="33" id="pymntDt" disabled/>
							</td>
							<th width="10%" height="18">금회 기성 금액</th>
							<td>
								<input type="text" size="33" id="thisTimeEstbAmt" class="ygpaNumber" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">선금 정산 금액</th>
							<td>
								<input type="text" size="33" id="depositExcclcAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">지　급　금　액</th>
							<td>
								<input type="text" size="33" id="pymntAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">지급 누계 금액</th>
							<td>
								<input type="text" size="33" id="pymntAggrAmt" class="ygpaNumber" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="pymntRm" disabled/>
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="caryFwdCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="caryFwdCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="caryFwdCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="caryFwdCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="caryFwdOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="caryFwdCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="caryFwdCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="caryFwdCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
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
                                <button id="btnCaryFwdExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="caryFwdGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순번／이월년도</th>
							<td>
								<input type="text" size="10" id="caryFwdSeq" disabled/>／
								<input type="text" size="19" id="fulfillCaryFwdYear" disabled/>
							</td>
							<th width="10%" height="18">이　행　금　액</th>
							<td>
								<input type="text" size="33" id="fulfillAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">이　월　금　액</th>
							<td>
								<input type="text" size="33" id="caryFwdAmt" class="ygpaNumber" disabled/>
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
							<th width="10%" height="18">계　약　번　호</th>
							<td>
								<select id="scsbidCtrtSe" disabled>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>-
								<input type="text" size="19" id="scsbidCtrtNo" disabled/>
							</td>
							<th width="10%" height="18">계　　약　　명</th>
							<td colspan="3">
								<input type="text" size="93" id="scsbidCtrtNm" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">원　인　행　위</th>
							<td>
								<input type="text" size="33" id="scsbidCauseAct" disabled/>
							</td>
							<th width="10%" height="18">발　주　방　식</th>
							<td>
								<input type="text" size="33" id="scsbidOrderMthd" disabled/>
							</td>
							<th width="10%" height="18">계　약　방　법</th>
							<td>
								<input type="text" size="33" id="scsbidCtrtMth" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">계　약　일　자</th>
							<td>
								<input type="text" size="33" id="scsbidCtrtDt" disabled/>
							</td>
							<th width="10%" height="18">계　약　금　액</th>
							<td>
								<input type="text" size="33" id="scsbidCtrtAmt" class="ygpaNumber" disabled/>
							</td>
							<th width="10%" height="18">계　약　기　간</th>
							<td>
								<input type="text" size="14" id="scsbidCtrtDtFrom" disabled/> ∼
								<input type="text" size="14" id="scsbidCtrtDtTo" disabled/>
							</td>
						</tr>
					</table>
					<table class="summaryPanel" style="width:100%;">
						<tr>
							<th style="font-weight:bold; height:20px;">계약 낙찰 정보 내역</th>
							<td style="text-align:right;">
                                <button id="btnScsbidExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
					<table id="scsbidGrid" style="display:none"></table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="10%" height="18">순번／낙찰순위</th>
							<td>
								<input type="text" size="10" id="scsbidSeq" disabled/>／
								<input type="text" size="19" id="scsbidRank" disabled/>
							</td>
							<th width="10%" height="18">업　　체　　명</th>
							<td>
								<input type="text" size="33" id="scsbidEntrpsNm" disabled/>
							</td>
							<th width="10%" height="18">대　　표　　자</th>
							<td>
								<input type="text" size="33" id="scsbidRprsntv" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">사업자　　번호</th>
							<td>
								<input type="text" size="33" id="scsbidBsnmNo" disabled/>
							</td>
							<th width="10%" height="18">전　화　번　호</th>
							<td>
								<input type="text" size="33" id="scsbidTlphonNo" disabled/>
							</td>
							<th width="10%" height="18">팩　스　번　호</th>
							<td>
								<input type="text" size="33" id="scsbidFaxNo" disabled/>
							</td>
						</tr>
						<tr>
							<th width="10%" height="18">비　　　　　고</th>
							<td colspan="5">
								<input type="text" size="150" id="scsbidRm" disabled/>
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
