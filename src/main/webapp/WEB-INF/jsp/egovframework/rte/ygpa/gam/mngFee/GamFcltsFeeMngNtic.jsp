<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsFeeMngNtic.jsp
 * @Description : 시설물 관리비 고지
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.11.22  lee          최초 생성
 *
 * author domh
 * since 2014.11.22
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
 * @FUNCTION NAME : GamFcltsFeeMngNticModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsFeeMngNticModule() {}

GamFcltsFeeMngNticModule.prototype = new EmdModule(1000, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngNtic.do',
		dataType : 'json',
		colModel : [
					{display:'관리 월',				name:'mngYrMt',				width:60,		sortable:false,		align:'center'},
					{display:'업무 구분',			name:'mngFeeJobSeNm',		width:80,		sortable:false,		align:'center'},
					{display:'업체 명',				name:'entrpsNm',			width:150,		sortable:false,		align:'left'},
					{display:'관리비 제목',			name:'mngFeeSj',			width:150,		sortable:false,		align:'left'},
					{display:'사용 면적',			name:'usageAr',				width:70,		sortable:false,		align:'right'},
					{display:'고지 일자',			name:'nticDt',				width:80,		sortable:false,		align:'center'},
					{display:'요금 종류',			name:'chrgeKndNm',			width:100,		sortable:false,		align:'left'},
					{display:'회계 년도',			name:'accnutYear',			width:80,		sortable:false,		align:'center'},
					{display:'고지 번호',			name:'nticNo',				width:80,		sortable:false,		align:'center'},
					{display:'사용료',				name:'fee',					width:90,		sortable:false,		align:'right'},
					{display:'부가세',				name:'vat',					width:90,		sortable:false,		align:'right'},
					{display:'고지 금액',			name:'nticAmt',				width:90,		sortable:false,		align:'right'},
					{display:'관리 비',				name:'mngFee',				width:100,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'elctyFee',			width:100,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'waterFee',			width:100,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'gasFee',				width:100,		sortable:false,		align:'right'},
					{display:'환경개선 부담금',		name:'envFee',				width:100,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mngTotalFee',			width:100,		sortable:false,		align:'right'},
					{display:'납부 기한',			name:'payTmlmt',			width:80,		sortable:false,		align:'center'},
					{display:'수납 구분',			name:'rcivSeNm',			width:80,		sortable:false,		align:'center'},
					{display:'수납 일자',			name:'rcivDt',				width:80,		sortable:false,		align:'center'},
					{display:'부가세 구분',			name:'vatYnNm',				width:80,		sortable:false,		align:'center'},
					{display:'고지 여부',			name:'nhtIsueYn',			width:80,		sortable:false,		align:'center'},
					{display:'출력 여부',			name:'nhtPrintYn',			width:80,		sortable:false,		align:'center'},
					{display:'추가 고지 여부',		name:'aditNticYn',			width:90,		sortable:false,		align:'center'},
					{display:'연체 번호',			name:'arrrgNo',				width:80,		sortable:false,		align:'center'},
					{display:'연체 금액',			name:'arrrgAmt',			width:90,		sortable:false,		align:'right'},
					{display:'연체 요율',			name:'arrrgTariff',			width:80,		sortable:false,		align:'right'},
					{display:'연체 일수',			name:'arrrgPayDates',		width:80,		sortable:false,		align:'right'},
					{display:'요금 종류 코드',		name:'chrgeKnd',			width:100,		sortable:false,		align:'left'},
					{display:'항 코드',				name:'prtAtCode',			width:80,		sortable:false,		align:'center'},
					{display:'관리 월',				name:'mngMt',				width:80,		sortable:false,		align:'center'},
					{display:'업무 구분',			name:'mngFeeJobSe',			width:80,		sortable:false,		align:'center'},
					{display:'관리 순번',			name:'mngSeq',				width:80,		sortable:false,		align:'center'},
					{display:'의뢰 순번',			name:'reqestSeq',			width:80,		sortable:false,		align:'center'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumFee').val(data.sumFee);
			module.$('#sumVat').val(data.sumVat);
			module.$('#sumNticAmt').val(data.sumNticAmt);
			module.$('#sumArrrgAmt').val(data.sumArrrgAmt);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#detailGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngNticDetail.do',
		dataType : 'json',
		colModel : [
					{display:'순번', 			name:'reqestSeq',		width:40,		sortable:false,		align:'center'},
					{display:'회계 년도',		name:'accnutYear',		width:70,		sortable:false,		align:'center'},
					{display:'고지 번호',		name:'nticNo',			width:70,		sortable:false,		align:'center'},
					{display:'요금 종류',		name:'chrgeKndNm',		width:100,		sortable:false,		align:'center'},
					{display:'사용료',			name:'fee',				width:90,		sortable:false,		align:'right'},
					{display:'부가세',			name:'vat',				width:90,		sortable:false,		align:'right'},
					{display:'부가세 구분',		name:'vatYnNm',			width:80,		sortable:false,		align:'center'},
					{display:'고지 금액',		name:'nticAmt',			width:90,		sortable:false,		align:'right'},
					{display:'고지 일자',		name:'nticDt',			width:80,		sortable:false,		align:'center'},
					{display:'납부 기한',		name:'payTmlmt',		width:80,		sortable:false,		align:'center'},
					{display:'수납 구분',		name:'rcivSeNm',		width:70,		sortable:false,		align:'center'},
					{display:'수납 일자',		name:'rcivDt',			width:80,		sortable:false,		align:'center'},
					{display:'최초 고지 일자',	name:'firstNticDt',		width:100,		sortable:false,		align:'center'},
					{display:'최초 납부 기한',	name:'firstPayTmlmt',	width:100,		sortable:false,		align:'center'}
					],
		showTableToggleBtn: true,
		height: '100'
	});

	this.$("#unpaidGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngInqireUnpaid.do',
		dataType : 'json',
		colModel : [
					{display:'연체 순번', 		name:'dlySerNo',		width:70,		sortable:false,		align:'center'},
					{display:'회계 년도',		name:'fiscalYr',		width:70,		sortable:false,		align:'center'},
					{display:'고지 번호',		name:'billNo',			width:70,		sortable:false,		align:'center'},
					{display:'고지 일자',		name:'dlyBillDt',		width:80,		sortable:false,		align:'center'},
					{display:'납부 기한',		name:'dlyDueDt',		width:80,		sortable:false,		align:'center'},
					{display:'연체 금액',		name:'dlyBillAmnt',		width:90,		sortable:false,		align:'right'},
					{display:'연체 요율',		name:'arrrgTriff',		width:70,		sortable:false,		align:'right'},
					{display:'연체 일수',		name:'arrrgPayDates',	width:70,		sortable:false,		align:'right'},
					{display:'출력 여부',		name:'dlyBillPrtYn',	width:70,		sortable:false,		align:'center'},
					{display:'수납 구분',		name:'dlyRcvdTpNm',		width:70,		sortable:false,		align:'center'},
					{display:'수납 일자',		name:'dlyRcvdDt',		width:80,		sortable:false,		align:'center'},
					{display:'산출 내역',		name:'dlyBillRsn',		width:200,		sortable:false,		align:'left'},
					{display:'지로 금액',		name:'djiroAmnt',		width:90,		sortable:false,		align:'right'},
					{display:'이전 고지 일자',	name:'prvBillDt',		width:100,		sortable:false,		align:'center'},
					{display:'이전 납부 기한',	name:'prvDueDt',		width:100,		sortable:false,		align:'center'}
					],
		showTableToggleBtn : true,
		height : '100'
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngMt + row.mngFeeJobSe + row.mngSeq + row.reqestSeq;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngMt + row.mngFeeJobSe + row.mngSeq + row.reqestSeq;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getQueryEntrpsNm();
	});

	this.$('#chrgeKnd').on('change',{module:this}, function(event){
		var chrgeKndNm = event.data.module.$('#chrgeKnd_select').find('option:selected').text();
		event.data.module.$('#chrgeKndNm').val(chrgeKndNm);
	});

	this.$('#vatYn').on('change',{module:this}, function(event){
		var vatYnNm = event.data.module.$('#vatYn_select').find('option:selected').text();
		event.data.module.$('#vatYnNm').val(vatYnNm);
		event.data.module.calcNticAmt();
	});

	this.$('#fee').on('keyup change',{module:this}, function(event){
		event.data.module.calcNticAmt();
	});

	this.$('#vat').on('keyup change',{module:this}, function(event){
		event.data.module.calcNticAmt();
	});

	this.$('#nticDt').on('keyup change',{module:this}, function(event){
		event.data.module.setPayTmlmt();
	});

	this._mode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._detailDisplay = 'detail';
	this.$('#unpaidGrid').hide();
	if (params != null) {
		if (params.action == "selectFcltsFeeMngNtic") {
        	this.$('#sStartMngYear').val(params.paramVo.mngMtYear);
        	this.$('#sStartMngMt').val(params.paramVo.mngMtMon);
        	this.$('#sEndMngYear').val(params.paramVo.mngMtYear);
        	this.$('#sEndMngMt').val(params.paramVo.mngMtMon);
        	this.$('#sMngFeeJobSe').val(params.paramVo.mngFeeJobSe);
        	this._mode="query";
        	this._mainKeyValue = "";
        	var searchOpt=this.makeFormArgs('#searchForm');
        	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
		}
	} else {
		var mon = new Date().getMonth()+1;
		if (mon.length==1) {
			mon="0"+mon;
		}
		this.$('#sStartMngMt').val(mon);
		this.$('#sEndMngMt').val(mon);
	}
	this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});

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
GamFcltsFeeMngNticModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchEntrpscd':
			if (msg == 'ok') {
				this.$('#sEntrpscd').val(value.entrpscd);
				this.$('#sEntrpsNm').val(value.entrpsNm);
			}
			break;
		case 'popupDataEntrpscd':
			if (msg == 'ok') {
				this.$('#entrpscd').val(value.entrpscd);
				this.$('#entrpsNm').val(value.entrpsNm);
				this.$('#bizrno').val(value.bizrno);
			}
			break;
		case 'popupProcessNticIssueUnpaid':
			this.processNticIssueUnpaid(value);
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
GamFcltsFeeMngNticModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnSaveNticIssue':
			this.saveNticIssue();
			break;
		case 'btnProcessNticIssue':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.processNticIssue();
			}
			break;
		case 'btnProcessNticIssue2':
			this.processNticIssue();
			break;
		case 'btnCancelNticIssue':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.cancelNticIssue();
			}
			break;
		case 'btnCancelNticIssue2':
			this.cancelNticIssue();
			break;
		case 'btnPrintNticIssue':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.printNticIssue();
			}
			break;
		case 'btnPrintNticIssue2':
			this.printNticIssue();
			break;
		case 'btnAddNticIssue':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this._mode="insert";
				this.$("#mainTab").tabs("option", {active: 1});
				this.addNticIssue();
			}
			break;
		case 'btnAddNticIssue2':
			this._mode="insert";
			this.addNticIssue();
			break;
		case 'btnDelNticIssue':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteNticIssue();
			}
			break;
		case 'btnDelNticIssue2':
			this.deleteNticIssue();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'btnProcessNticIssueUnpaid':
			this.popupArrrgNticIssue();
			break;
		case 'btnCancelNticIssueUnpaid':
			this.cancelNticIssueUnpaid();
			break;
		case 'btnOpenFcltsFeeMngInqire':
		case 'btnOpenFcltsFeeMngInqire2':
			this.openFcltsFeeMngInqireModule();
			break;
		case 'popupSearchEntrpscd':
			this.doExecuteDialog('popupSearchEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
		case 'popupDataEntrpscd':
			this.doExecuteDialog('popupDataEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
		case 'btnArrrgList':
			if (this._detailDisplay != 'unpaid') {
				this._detailDisplay = 'unpaid';
				this.$('#detailGrid').hide();
				this.$('#unpaidGrid').show();
				this.loadUnpaidData();
			}
			break;
		case 'btnNticList':
			if (this._detailDisplay != 'detail') {
				this._detailDisplay = 'detail';
				this.$('#unpaidGrid').hide();
				this.$('#detailGrid').show();
			}
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
GamFcltsFeeMngNticModule.prototype.onSubmit = function() {

	this._mode="query";
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
GamFcltsFeeMngNticModule.prototype.loadData = function() {

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
 GamFcltsFeeMngNticModule.prototype.refreshData = function() {

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
GamFcltsFeeMngNticModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	    var detailOpt=this.makeFormArgs('#detailForm');
	    this.$('#detailGrid').flexOptions({params:detailOpt}).flexReload();
	    if (this.$('#arrrgSttus').val() == "연체상태") {
			this.getNewDlySerNo();
	    }
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectFcltsFeeMngNticPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
			    var detailOpt=module.makeFormArgs('#detailForm');
			    module.$('#detailGrid').flexOptions({params:detailOpt}).flexReload();
			    if (this.$('#arrrgSttus').val() == "연체상태") {
					this.getNewDlySerNo();
			    }
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
GamFcltsFeeMngNticModule.prototype.selectData = function() {

	this.rowColorGridData();
	if (this._mode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._mode != 'insert' && this._mode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	if (this._mainKeyValue == "") {
		return;
	}
	var mngMt = this._mainKeyValue.substring(0,6);
	var mngFeeJobSe = this._mainKeyValue.substring(6,7);
	var mngSeq = this._mainKeyValue.substring(7,10);
	var reqestSeq = this._mainKeyValue.substring(10,13);
	this.$("#mainGrid").selectFilterRow([{col:"mngMt", filter:mngMt},
	                                     {col:"mngFeeJobSe", filter:mngFeeJobSe},
										 {col:"mngSeq", filter:mngSeq},
										 {col:"reqestSeq", filter:reqestSeq}]);
	this._mode = 'modify';
	this.disableDetailInputItem();
	this.loadDetail('detailTab');
	this.enableDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadUnpaidData
 * @DESCRIPTION   : UNPAID DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadUnpaidData = function() {

	var searchOpt=this.makeFormArgs('#detailForm');
	this.$('#unpaidGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : rowColorGridData
 * @DESCRIPTION   : GRID DATA ROW COLOR 설정
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.rowColorGridData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (gridRowCount == 0) {
		return;
	}
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (row.arrrgSttus != null && row.arrrgSttus != "") {
			this.$('#mainGrid')[0].dgrid.setRowColor(i + 1,"yellow");
		} else {
			this.$('#mainGrid')[0].dgrid.setRowColor(i + 1,"white");
		}
	}

};

<%
/**
 * @FUNCTION NAME : calcNticAmt
 * @DESCRIPTION   : 고지 금액을 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.calcNticAmt = function() {

	var vatYn = this.$('#vatYn').val();
	var fee = Number(this.$('#fee').val().replace(/,/gi, ""));
	var vat = Number(this.$('#vat').val().replace(/,/gi, ""));
	var calcVat = 0;
	var nticAmt = 0;
	if (vatYn == "0" || vatYn == "1" || vatYn == "3") {
		calcVat = 0;
	} else {
		calcVat = Math.floor(fee / 10);
	}
	nticAmt = fee + calcVat;
	if (vat != calcVat) {
		this.$('#vat').val('' + $.number(calcVat));
	}
	this.$('#nticAmt').val('' + $.number(nticAmt));

};

<%
/**
 * @FUNCTION NAME : setPayTmlmt
 * @DESCRIPTION   : 납부 기한을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.setPayTmlmt = function() {

	var billDt = this.$('#nticDt').val();
	var toDay = new Date();
	var year = "";
	var month = "";
	var day = "";
	var payTmlmt = "";
	if (billDt == "") {
		year = toDay.getFullYear();
		month = toDay.getMonth() + 1;
		day = toDay.getDate();
		if (month >= 1 && month <= 9) {
			if (day >= 1 && day <= 9) {
				billDt = year + "-" + "0" + month + "-" + "0" + day;
			} else {
				billDt = year + "-" + "0" + month + "-" + day;
			}
		} else {
			if (day >= 1 && day <= 9) {
				billDt = year + "-" + month + "-" + "0" + day;
			} else {
				billDt = year + "-" + month + "-" + day;
			}
		}
		this.$('#nticDt').val(billDt);
	}
	var dueDate = EMD.util.strToDate(this.$('#nticDt').val());
	var dayOfMonth = dueDate.getDate();
	dueDate.setDate(dayOfMonth + 15);
	year = dueDate.getFullYear();
	month = dueDate.getMonth() + 1;
	day = dueDate.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			payTmlmt = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			payTmlmt = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			payTmlmt = year + "-" + month + "-" + "0" + day;
		} else {
			payTmlmt = year + "-" + month + "-" + day;
		}
	}
	this.$('#payTmlmt').val(payTmlmt);

};

<%
/**
 * @FUNCTION NAME : getNewReqestSeq
 * @DESCRIPTION   : 새로운 징수 의뢰 순번를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.getNewReqestSeq = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#mngMt').val() == "" || this.$('#mngFeeJobSe').val() == "" || this.$('#mngSeq').val() == "") {
		return;
	}
	this.doAction('/mngFee/gamSelectFcltsFeeMngNticMaxReqestSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#reqestSeq').val(result.sMaxReqestSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getQueryEntrpsNm
 * @DESCRIPTION   : 조회조건 고지업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.getQueryEntrpsNm = function() {

	var sEntrpscd = this.$('#sEntrpscd').val();
	if (sEntrpscd.length == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/mngFee/gamSelectFcltsFeeMngNticEntrpsNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#sEntrpsNm').val(result.sEntrpsNm);
			}
		});
	} else {
		this.$('#sEntrpsNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : getNewDlySerNo
 * @DESCRIPTION   : 새로운 연체 횟수를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.getNewDlySerNo = function() {

	var prtAtCode = this.$('#prtAtCode').val();
	var feeTp = this.$('#feeTp').val();
	var fiscalYr = this.$('#fiscalYr').val();
	var billNo = this.$('#billNo').val();
	if (prtAtCode == "" || feeTp == "" || fiscalYr == "" || billNo == "") {
		return("");
	}
	var searchVO = {
		'prtAtCode':prtAtCode,
		'feeTp':feeTp,
		'fiscalYr':fiscalYr,
		'billNo':billNo
	};
	this.doAction('/mngFee/gamSelectFcltsFeeMngNticUnpaidFMaxDlySerNo.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#newDlySerNo').val(result.sMaxDlySerNo);
		}
	});

};

<%
/**
 * @FUNCTION NAME : saveNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.saveNticIssue = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var chrgeKnd = this.$('#chrgeKnd').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var nticDt = this.$('#nticDt').val();
	var payTmlmt = this.$('#payTmlmt').val();
	var vatYn = this.$('#vatYn').val();
	var fee = Number(this.$('#fee').val().replace(/,/gi, ""));
	var vat = Number(this.$('#vat').val().replace(/,/gi, ""));
	var nticAmt = Number(this.$('#nticAmt').val().replace(/,/gi, ""));
	var toDay = new Date();
	var year = toDay.getFullYear();
	var month = toDay.getMonth() + 1;
	var day = toDay.getDate();
	var todayString = "";
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			todayString = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			todayString = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			todayString = year + "-" + month + "-" + "0" + day;
		} else {
			todayString = year + "-" + month + "-" + day;
		}
	}
	if (nhtIsueYn == "Y") {
		alert('고지 처리가 완료된 자료입니다.');
		return;
	}
	if (chrgeKnd == null || chrgeKnd == "") {
		alert('요금 종류가 부정확합니다.');
		this.$("#chrgeKnd").focus();
		return;
	}
	if (nticDt != null && nticDt != "") {
		if (nticDt > todayString || nticDt < "2000-01-01" || nticDt == "") {
			alert('고지 일자가 부정확합니다.');
			this.$("#nticDt").focus();
			return;
		}
		if (nticDt > payTmlmt || payTmlmt < "2000-01-01" || payTmlmt == "") {
			alert('납부 기한이 부정확합니다.');
			this.$("#payTmlmt").focus();
			return;
		}
	}
	if (fee > 999999999999 || fee <= 0) {
		alert('사용료가 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (vat > 999999999999 || vat < 0) {
		alert('부가세가 부정확합니다.');
		this.$("#vat").focus();
		return;
	}
	if (vatYn == "0" || vatYn == "1" || vatYn == "3") {
		if (vat != 0) {
			alert('부가세가 부정확합니다.');
			this.$("#vat").focus();
			return;
		}
	} else {
		if (vat != Math.floor(fee / 10)) {
			alert('부가세가 부정확합니다.');
			this.$("#vat").focus();
			return;
		}
	}
	if (nticAmt > 999999999999 || nticAmt <= 0) {
		alert('고지 금액이 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (nticAmt != (fee + vat)) {
		alert('고지 금액이 (사용료 + 부가세)와 일치하지 않습니다.');
		this.$("#fee").focus();
		return;
	}
	if (this._mode == "insert") {
		this.doAction('/mngFee/gamInsertFcltsFeeMngNtic.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamSaveFcltsFeeMngNticIssue.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : processNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 고지처리한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.processNticIssue = function() {

	var processVO = [];
	var chrgeKnd = this.$('#chrgeKnd').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var nticDt = this.$('#nticDt').val();
	var payTmlmt = this.$('#payTmlmt').val();
	var vatYn = this.$('#vatYn').val();
	var fee = Number(this.$('#fee').val().replace(/,/gi, ""));
	var vat = Number(this.$('#vat').val().replace(/,/gi, ""));
	var nticAmt = Number(this.$('#nticAmt').val().replace(/,/gi, ""));
	var toDay = new Date();
	var year = toDay.getFullYear();
	var month = toDay.getMonth() + 1;
	var day = toDay.getDate();
	var todayString = "";
	var confirmMessage = "";
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			todayString = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			todayString = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			todayString = year + "-" + month + "-" + "0" + day;
		} else {
			todayString = year + "-" + month + "-" + day;
		}
	}
	if (nhtIsueYn == "Y") {
		alert('고지 처리가 완료된 자료입니다.');
		return;
	}
	if (chrgeKnd == "") {
		alert('요금 종류가 부정확합니다.');
		this.$("#chrgeKnd").focus();
		return;
	}
	if (nticDt > todayString || nticDt < "2000-01-01" || nticDt == "") {
		alert('고지 일자가 부정확합니다.');
		this.$("#nticDt").focus();
		return;
	}
	if (nticDt > payTmlmt || payTmlmt < "2000-01-01" || payTmlmt == "") {
		alert('납부 기한이 부정확합니다.');
		this.$("#payTmlmt").focus();
		return;
	}
	if (fee > 999999999999 || fee <= 0) {
		alert('사용료가 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (vat > 999999999999 || vat < 0) {
		alert('부가세가 부정확합니다.');
		this.$("#vat").focus();
		return;
	}
	if (vatYn == "0" || vatYn == "1" || vatYn == "3") {
		if (vat != 0) {
			alert('부가세가 부정확합니다.');
			this.$("#vat").focus();
			return;
		}
	} else {
		if (vat != Math.floor(fee / 10)) {
			alert('부가세가 부정확합니다.');
			this.$("#vat").focus();
			return;
		}
	}
	if (nticAmt > 999999999999 || nticAmt <= 0) {
		alert('고지 금액이 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (nticAmt != (fee + vat)) {
		alert('고지 금액이 (사용료 + 부가세)와 일치하지 않습니다.');
		this.$("#fee").focus();
		return;
	}
	confirmMessage = "[" + this.$('#chrgeKnd_select').find('option:selected').text() + "] " + this.$('#fee').val() + "원을 고지하시겠습니까?";
	if (confirm(confirmMessage)) {
		processVO={
			'mngMt':this.$('#mngMt').val(),
			'mngFeeJobSe':this.$('#mngFeeJobSe').val(),
			'mngSeq':this.$('#mngSeq').val(),
			'reqestSeq':this.$('#reqestSeq').val(),
			'prtAtCode':this.$('#prtAtCode').val(),
			'chrgeKnd':this.$('#chrgeKnd').val(),
			'accnutYear':this.$('#accnutYear').val(),
			'nticNo':this.$('#nticNo').val(),
			'nticDt':this.$('#nticDt').val(),
			'payTmlmt':this.$('#payTmlmt').val(),
			'fee':this.$('#fee').val().replace(/,/gi, ""),
			'vatYn':this.$('#vatYn').val(),
			'vat':this.$('#vat').val().replace(/,/gi, ""),
			'nticAmt':this.$('#nticAmt').val().replace(/,/gi, ""),
			'nhtIsueYn':"Y",
			'rm':this.$('#rm').val()
		};
		this.doAction('/mngFee/gamProcessFcltsFeeMngNticIssue.do', processVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : cancelNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 고지취소한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.cancelNticIssue = function() {

	var cancelVO = [];
	var chrgeKnd = this.$('#chrgeKnd').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var rcivSe = this.$('#rcivSe').val();
	var accnutYear = this.$('#accnutYear').val();
	var nticNo = this.$('#nticNo').val();
	var arrrgNo = this.$('#arrrgNo').val();
	var confirmMessage = "";
	if (nhtIsueYn != "Y") {
		alert('고지 처리가 완료된 자료가 아닙니다.');
		return;
	}
	if (rcivSe != "0") {
		alert('미수납된 자료가 아닙니다.');
		return;
	}
	if (chrgeKnd == "") {
		alert('요금 종류가 부정확합니다.');
		return;
	}
	if (accnutYear > "2999" || accnutYear < "2000" || accnutYear == "") {
		alert('회계 년도가 부정확합니다.');
		return;
	}
	if (nticNo > "999999" || nticNo < "000001" || nticNo == "") {
		alert('고지 번호가 부정확합니다.');
		return;
	}
	if (arrrgNo != null && arrrgNo != "") {
		alert('연체 고지 자료가 존재합니다.\r\n연체 고지 취소를 먼저 수행하시길 바랍니다.');
		return;
	}
	confirmMessage = "[" + this.$('#chrgeKnd_select').find('option:selected').text() + "] " + this.$('#fee').val() + "원을 고지 취소하시겠습니까?";
	if (confirm(confirmMessage)) {
		cancelVO={
				'mngMt':this.$('#mngMt').val(),
				'mngFeeJobSe':this.$('#mngFeeJobSe').val(),
				'mngSeq':this.$('#mngSeq').val(),
				'reqestSeq':this.$('#reqestSeq').val(),
				'prtAtCode':this.$('#prtAtCode').val(),
				'chrgeKnd':this.$('#chrgeKnd').val(),
				'accnutYear':this.$('#accnutYear').val(),
				'nticNo':this.$('#nticNo').val(),
				'nticDt':this.$('#nticDt').val(),
				'payTmlmt':this.$('#payTmlmt').val(),
				'fee':this.$('#fee').val().replace(/,/gi, ""),
				'vatYn':this.$('#vatYn').val(),
				'vat':this.$('#vat').val().replace(/,/gi, ""),
				'nticAmt':this.$('#nticAmt').val().replace(/,/gi, ""),
				'nhtIsueYn':"N",
				'nhtPrintYn':this.$('#nhtPrintYn').val(),
				'arrrgNo':this.$('#arrrgNo').val(),
				'rm':this.$('#rm').val()
		};
		this.doAction('/mngFee/gamCancelFcltsFeeMngNticIssue.do', cancelVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : printNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역 지로 고지서를 출력한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.printNticIssue = function() {

	var row = this.$('#mainGrid').selectedRows()[0];
	if (row == null) {
		alert('자료가 선택되지 않았습니다.');
		return;
	}
	if (row['nhtIsueYn'] != "Y") {
		alert('고지 처리가 완료된 자료가 아닙니다.');
		return;
	}
	this.printPage('/mngFee/gamPrintPreviewFcltsFeeMngNticNoticeIssue.do', row);
	//alert("고지서 출력이 완료됐습니다.");
	this.refreshData();

};

<%
/**
 * @FUNCTION NAME : addNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.addNticIssue = function() {

	this.$('#prtAtCode').val('622');
	this.$('#feeTp').val('');
	this.$('#fiscalYr').val('');
	this.$('#billNo').val('');
	this.$('#rcvdTp').val('');
	this.$('#chrgeKnd').val('');
	this.$('#reqestSeq').val('');
	this.$('#accnutYear').val('');
	this.$('#nticNo').val('');
	this.$('#vatYn').val('2');
	this.$('#fee').val('0');
	this.$('#vat').val('0');
	this.$('#nticAmt').val('0');
	this.$('#nticDt').val('');
	this.$('#payTmlmt').val('');
	this.$('#rcivSe').val('0');
	this.$('#rcivSeNm').val('미고지');
	this.$('#rcivDt').val('');
	this.$('#arrrgNo').val('');
	this.$('#arrrgPayDates').val('');
	this.$('#arrrgAmt').val('');
	this.$('#setoffYn').val('N');
	this.$('#nticMth').val('');
	this.$('#nhtIsueYn').val('N');
	this.$('#nhtPrintYn').val('N');
	this.$('#aditNticYn').val('Y');
	this.$('#rm').val('');
	this.enableDetailInputItem();
	this.enableListButtonItem();
	this.getNewReqestSeq();
	this.$('#chrgeKnd').focus();

};

<%
/**
 * @FUNCTION NAME : deleteNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.deleteNticIssue = function() {

	if (this.$('#aditNticYn').val() != "Y") {
		alert('추가고지 자료가 아닙니다.');
		return;
	}
	if (this.$('#nhtIsueYn').val() == "Y") {
		alert('고지 처리가 완료된 자료입니다.');
		return;
	}
	if (confirm('추가고지 내역을 삭제하시겠습니까?')) {
		var inputVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteFcltsFeeMngNtic.do', inputVO, function(module, result) {
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
 * @FUNCTION NAME : openFcltsFeeMngInqireModule
 * @DESCRIPTION   : [시설물 관리비 관리비 납부현황 조회] 화면 MODULE OPEN
 * @PARAMETER     : NONE
**/
%>
 GamFcltsFeeMngNticModule.prototype.openFcltsFeeMngInqireModule = function() {

	var rows = this.$('#mainGrid').selectedRows();
    var formParams = {};
	if (rows.length==0) {
		return;
	}
	formParams = {
		action: 'selectFcltsFeeMngInqire',
		paramVo:{ mngMtYear: rows[0].mngMtYear, mngMtMon: rows[0].mngMtMon, mngFeeJobSe: rows[0].mngFeeJobSe }
	};
	EMD.util.create_window('시설물 관리비 납부현황 조회', '/mngFee/gamFcltsFeeMngInqire.do', null, formParams);

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadFcltsFeeMngNtic.do');

};

<%
/**
 * @FUNCTION NAME : popupArrrgNticIssue
 * @DESCRIPTION   : 시설물 관리비 연체 고지 POPUP을 호출한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.popupArrrgNticIssue = function() {

	var processVO = [];
	var toDay = new Date();
	var year = "";
	var month = "";
	var day = "";
	var dlyBillDt = "";
	var dlyDueDt = "";
	var prtAtCode = this.$('#prtAtCode').val();
	var feeTp = this.$('#feeTp').val();
	var fiscalYr = this.$('#fiscalYr').val();
	var billNo = this.$('#billNo').val();
	var billDt = this.$('#nticDt').val();
	var dueDt = this.$('#payTmlmt').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var rcivSe = this.$('#rcivSe').val();
	var dlySerNo = this.$('#newDlySerNo').val();
	var fee = this.$('#fee').val();
	var vat = this.$('#vat').val();
	var nticAmt = this.$('#nticAmt').val();
	if (nhtIsueYn != "Y") {
		alert('고지 처리가 완료된 자료가 아닙니다.');
		return;
	}
	if (rcivSe != "0" && rcivSe != "1") {
		alert('미수납된 자료가 아닙니다.');
		return;
	}
	if (prtAtCode == "" || feeTp == "" || fiscalYr == "" || billNo == "") {
		alert("고지 정보가 부정확합니다.");
		return;
	}
	year = toDay.getFullYear();
	month = toDay.getMonth() + 1;
	day = toDay.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			dlyBillDt = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			dlyBillDt = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			dlyBillDt = year + "-" + month + "-" + "0" + day;
		} else {
			dlyBillDt = year + "-" + month + "-" + day;
		}
	}
	if (dlyBillDt <= dueDt) {
		alert("연체상태가 아닙니다. (납부 기한 내)");
		return;
	}
	var dueDate = EMD.util.strToDate(dlyBillDt);
	var dayOfMonth = dueDate.getDate();
	dueDate.setDate(dayOfMonth + 15);
	year = dueDate.getFullYear();
	month = dueDate.getMonth() + 1;
	day = dueDate.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			dlyDueDt = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			dlyDueDt = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			dlyDueDt = year + "-" + month + "-" + "0" + day;
		} else {
			dlyDueDt = year + "-" + month + "-" + day;
		}
	}
	processVO={
		'mngMt':this.$('#mngMt').val(),
		'mngFeeJobSe':this.$('#mngFeeJobSe').val(),
		'mngSeq':this.$('#mngSeq').val(),
		'reqestSeq':this.$('#reqestSeq').val(),
		'prtAtCode':prtAtCode,
		'chrgeKnd':this.$('#chrgeKnd').val(),
		'chrgeKndNm':this.$('#chrgeKndNm').val(),
		'feeTp':feeTp,
		'fiscalYr':fiscalYr,
		'billNo':billNo,
		'dlySerNo':dlySerNo,
		'entrpscd':this.$('#entrpscd').val(),
		'entrpsNm':this.$('#entrpsNm').val(),
		'bizrno':this.$('#bizrno').val(),
		'firstBillDt':this.$('#firstNticDt').val(),
		'billDt':billDt,
		'firstDueDt':this.$('#firstPayTmlmt').val(),
		'dueDt':dueDt,
		'prvBillDt':billDt,
		'prvDueDt':dueDt,
		'billAmnt':$.number(fee),
		'vatYn':this.$('#vatYn').val(),
		'vatYnNm':this.$('#vatYnNm').val(),
		'vat':$.number(vat),
		'sumBillAmnt':$.number(nticAmt),
		'rcvdTp':this.$('#rcvdTp').val(),
		'dlyBillDt':dlyBillDt,
		'dlyDueDt':dlyDueDt,
		'arrrgTariff':"0.00",
		'arrrgPayDates':"0",
		'dlyBillAmnt':"0",
		'dlyBillRsn':"",
		'processMode':"I"
	};
	this.doExecuteDialog('popupProcessNticIssueUnpaid', '연체 고지 처리', '/mngFee/showFcltsFeeMngArrrgNticPopup.do', null, processVO);

};

<%
/**
 * @FUNCTION NAME : processNticIssueUnpaid
 * @DESCRIPTION   : 시설물 관리비 연체 내역을 고지 처리한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.processNticIssueUnpaid = function(processVo) {

	var prtAtCode = processVo.prtAtCode;
	var chrgeKndNm = processVo.chrgeKndNm;
	var feeTp = processVo.feeTp;
	var fiscalYr = processVo.fiscalYr;
	var billNo = processVo.billNo;
	var dlySerNo = processVo.dlySerNo;
	var dlyBillDt = processVo.dlyBillDt;
	var dlyDueDt = processVo.dlyDueDt;
	var arrrgTariff = processVo.arrrgTariff;
	var arrrgPayDates = processVo.arrrgPayDates;
	var dlyBillAmnt = processVo.dlyBillAmnt;
	var dataValue = 0;
	var confirmMessage = "";
	if (prtAtCode == "" || feeTp == "" || fiscalYr == "" || billNo == "") {
		alert("고지 정보가 부정확합니다.");
		return;
	}
	if (dlySerNo == "" || dlySerNo >= "99" || dlySerNo <= "00") {
		alert("연체 고지 횟수가 부정확합니다.");
		return;
	}
	if (dlyBillDt > dlyDueDt) {
		alert("연체 고지 일자가 연체 납부 기한보다 큽니다.");
		return;
	}
	dataValue = Number(dlyBillAmnt);
	if (dataValue > 999999999999 || dataValue <= 0) {
		alert("연체 금액이 부정확합니다.");
		return;
	}
	dataValue = Number(arrrgPayDates);
	if (dataValue > 1800 || dataValue <= 0) {
		alert("연체 일수가 부정확합니다.");
		return;
	}
	dataValue = Number(arrrgTariff);
	if (dataValue > 0.15 || dataValue <= 0) {
		alert("연체 요율이 부정확합니다.");
		return;
	}
	confirmMessage = "[" + chrgeKndNm + "] 연체 금액 " + dlyBillAmnt + "원을 고지 처리하시겠습니까?";
	if (confirm(confirmMessage)) {
		this.doAction('/mngFee/gamProcessFcltsFeeMngNticIssueUnpaid.do', processVo, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : cancelNticIssueUnpaid
 * @DESCRIPTION   : 시설물 관리비 연체 내역을 고지 취소한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.cancelNticIssueUnpaid = function() {

	var cancelVo = [];
	var prtAtCode = this.$('#prtAtCode').val();
	var chrgeKndNm = this.$('#chrgeKndNm').val();
	var feeTp = this.$('#feeTp').val();
	var fiscalYr = this.$('#fiscalYr').val();
	var billNo = this.$('#billNo').val();
	var dlySerNo = this.$('#arrrgNo').val();
	var dlyBillDt = this.$('#nticDt').val();
	var dlyDueDt = this.$('#payTmlmt').val();
	var dlyBillAmnt = this.$('#arrrgAmt').val().replace(/,/gi, "");
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var rcivSe = this.$('#rcivSe').val();
	var dataValue = 0;
	var confirmMessage = "";
	if (nhtIsueYn != "Y") {
		alert('고지 처리가 완료된 자료가 아닙니다.');
		return;
	}
	if (rcivSe != "0" && rcivSe != "1") {
		alert('미수납된 자료가 아닙니다.');
		return;
	}
	if (prtAtCode == "" || feeTp == "" || fiscalYr == "" || billNo == "") {
		alert("고지 정보가 부정확합니다.");
		return;
	}
	if (dlySerNo == "" || dlySerNo >= "99" || dlySerNo <= "00") {
		alert("연체 고지 횟수가 부정확합니다.");
		return;
	}
	if (dlyBillDt > dlyDueDt) {
		alert("연체 고지 일자가 연체 납부 기한보다 큽니다.");
		return;
	}
	dataValue = Number(dlyBillAmnt);
	if (dataValue > 999999999999 || dataValue <= 0) {
		alert("연체 금액이 부정확합니다.");
		return;
	}
	confirmMessage = "[" + chrgeKndNm + "] 연체 금액 " + dlyBillAmnt + "원을 고지 취소하시겠습니까?";
	if (confirm(confirmMessage)) {
		cancelVo={
			'mngMt':this.$('#mngMt').val(),
			'mngFeeJobSe':this.$('#mngFeeJobSe').val(),
			'mngSeq':this.$('#mngSeq').val(),
			'reqestSeq':this.$('#reqestSeq').val(),
			'prtAtCode':prtAtCode,
			'chrgeKnd':this.$('#chrgeKnd').val(),
			'chrgeKndNm':chrgeKndNm,
			'feeTp':feeTp,
			'fiscalYr':fiscalYr,
			'billNo':billNo,
			'dlySerNo':dlySerNo,
			'entrpscd':this.$('#entrpscd').val(),
			'entrpsNm':this.$('#entrpsNm').val(),
			'bizrno':this.$('#bizrno').val(),
			'firstBillDt':this.$('#firstNticDt').val(),
			'billDt':dlyBillDt,
			'firstDueDt':this.$('#firstPayTmlmt').val(),
			'dueDt':dlyDueDt,
			'billAmnt':this.$('#fee').val(),
			'vatYn':this.$('#vatYn').val(),
			'vatYnNm':this.$('#vatYnNm').val(),
			'vat':this.$('#vat').val(),
			'sumBillAmnt':this.$('#nticAmt').val(),
			'rcvdTp':this.$('#rcvdTp').val(),
			'dlyBillDt':dlyBillDt,
			'dlyDueDt':dlyDueDt,
			'arrrgTariff':this.$('#arrrgTariff').val(),
			'arrrgPayDates':this.$('#arrrgPayDates').val(),
			'dlyBillAmnt':dlyBillAmnt,
			'processMode':"D"
		};
		this.doAction('/mngFee/gamCancelFcltsFeeMngNticIssueUnpaid.do', cancelVo, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
		this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
	} else {
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
			return;
		}
		var nhtIsueYn = row['nhtIsueYn'];
		var rcivSe = row['rcivSe'];
		var aditNticYn = row['aditNticYn'];
		var arrrgNo = row['#arrrgNo'];
		if (rcivSe == "0" || rcivSe == "1") {
			if (nhtIsueYn == "Y") {
				this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
				if (arrrgNo != null && arrrgNo != "") {
					this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
				} else {
					this.$('#btnCancelNticIssue').enable();
					this.$('#btnCancelNticIssue').removeClass('ui-state-disabled');
				}
				this.$('#btnPrintNticIssue').enable();
				this.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
				this.$('#btnOpenFcltsFeeMngInqire').enable();
				this.$('#btnOpenFcltsFeeMngInqire').removeClass('ui-state-disabled');
			} else {
				this.$('#btnProcessNticIssue').enable();
				this.$('#btnProcessNticIssue').removeClass('ui-state-disabled');
				this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
				this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
				this.$('#btnOpenFcltsFeeMngInqire').enable();
				this.$('#btnOpenFcltsFeeMngInqire').removeClass('ui-state-disabled');
			}
			this.$('#btnAddNticIssue').enable();
			this.$('#btnAddNticIssue').removeClass('ui-state-disabled');
			if (aditNticYn == "Y") {
				this.$('#btnDelNticIssue').enable();
				this.$('#btnDelNticIssue').removeClass('ui-state-disabled');
			} else {
				this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
			}
		} else if (rcivSe == "2" || rcivSe == "3" || rcivSe == "4") {
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').enable();
			this.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
			this.$('#btnAddNticIssue').enable();
			this.$('#btnAddNticIssue').removeClass('ui-state-disabled');
			this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire').enable();
			this.$('#btnOpenFcltsFeeMngInqire').removeClass('ui-state-disabled');
		} else {
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
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
GamFcltsFeeMngNticModule.prototype.enableDetailInputItem = function() {

	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var rcivSe = this.$('#rcivSe').val();
	var aditNticYn = this.$('#aditNticYn').val();
	var arrrgNo = this.$('#arrrgNo').val();
	var arrrgSttus = this.$('#arrrgSttus').val();
	if (this._mode == "insert") {
		this.$('#chrgeKnd').enable();
		this.$('#vatYn').enable();
		this.$('#fee').enable();
		this.$('#vat').enable();
		this.$('#nticAmt').enable();
		this.$('#nticDt').enable();
		this.$('#payTmlmt').enable();
		this.$('#rm').enable();
		this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrintNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveNticIssue').enable();
		this.$('#btnSaveNticIssue').removeClass('ui-state-disabled');
		this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});
	} else {
		if (rcivSe == "0" || rcivSe == "1") {
			if (nhtIsueYn == "Y") {
				this.$('#chrgeKnd').disable();
				this.$('#vatYn').disable();
				this.$('#fee').disable();
				this.$('#vat').disable();
				this.$('#nticAmt').disable();
				this.$('#nticDt').disable();
				this.$('#payTmlmt').disable();
				this.$('#rm').disable();
				this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
				if (arrrgNo != null && arrrgNo != "") {
					this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
				} else {
					this.$('#btnCancelNticIssue2').enable();
					this.$('#btnCancelNticIssue2').removeClass('ui-state-disabled');
				}
				this.$('#btnPrintNticIssue2').enable();
				this.$('#btnPrintNticIssue2').removeClass('ui-state-disabled');
				this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
			} else {
				this.$('#chrgeKnd').enable();
				this.$('#vatYn').enable();
				this.$('#fee').enable();
				this.$('#vat').enable();
				this.$('#nticAmt').enable();
				this.$('#nticDt').enable();
				this.$('#payTmlmt').enable();
				this.$('#rm').enable();
				this.$('#btnProcessNticIssue2').enable();
				this.$('#btnProcessNticIssue2').removeClass('ui-state-disabled');
				this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
				this.$('#btnPrintNticIssue2').disable({disableClass:"ui-state-disabled"});
				this.$('#btnSaveNticIssue').enable();
				this.$('#btnSaveNticIssue').removeClass('ui-state-disabled');
			}
			this.$('#btnAddNticIssue2').enable();
			this.$('#btnAddNticIssue2').removeClass('ui-state-disabled');
			if (aditNticYn == "Y") {
				this.$('#btnDelNticIssue2').enable();
				this.$('#btnDelNticIssue2').removeClass('ui-state-disabled');
			} else {
				this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
			}
			if (arrrgSttus != null && arrrgSttus != "") {
				this.$('#btnProcessNticIssueUnpaid').enable();
				this.$('#btnProcessNticIssueUnpaid').removeClass('ui-state-disabled');
				if (arrrgNo != null && arrrgNo != "") {
					this.$('#btnProcessNticIssueUnpaid').enable();
					this.$('#btnProcessNticIssueUnpaid').removeClass('ui-state-disabled');
				} else {
					this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
				}
			} else {
				if (arrrgNo != null && arrrgNo != "") {
					this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
					this.$('#btnCancelNticIssueUnpaid').enable();
					this.$('#btnCancelNticIssueUnpaid').removeClass('ui-state-disabled');
				} else {
					this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
					this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
				}
			}
			this.$('#btnOpenFcltsFeeMngInqire2').enable();
			this.$('#btnOpenFcltsFeeMngInqire2').removeClass('ui-state-disabled');
		} else if (rcivSe == "2" || rcivSe == "3" || rcivSe == "4") {
			this.$('#chrgeKnd').disable();
			this.$('#vatYn').disable();
			this.$('#fee').disable();
			this.$('#vat').disable();
			this.$('#nticAmt').disable();
			this.$('#nticDt').disable();
			this.$('#payTmlmt').disable();
			this.$('#rm').disable();
			this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue2').enable();
			this.$('#btnPrintNticIssue2').removeClass('ui-state-disabled');
			this.$('#btnAddNticIssue2').enable();
			this.$('#btnAddNticIssue2').removeClass('ui-state-disabled');
			this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire2').enable();
			this.$('#btnOpenFcltsFeeMngInqire2').removeClass('ui-state-disabled');
		} else {
			this.$('#chrgeKnd').disable();
			this.$('#vatYn').disable();
			this.$('#fee').disable();
			this.$('#vat').disable();
			this.$('#nticAmt').disable();
			this.$('#nticDt').disable();
			this.$('#payTmlmt').disable();
			this.$('#rm').disable();
			this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});
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
GamFcltsFeeMngNticModule.prototype.disableDetailInputItem = function() {

	this.$('#chrgeKnd').disable();
	this.$('#vatYn').disable();
	this.$('#fee').disable();
	this.$('#vat').disable();
	this.$('#nticAmt').disable();
	this.$('#nticDt').disable();
	this.$('#payTmlmt').disable();
	this.$('#rm').disable();
	this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnProcessNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssueUnpaid').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});

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
GamFcltsFeeMngNticModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mode=="insert") {
				this.$('#chrgeKnd').focus();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			if (this._detailDisplay == 'unpaid') {
				this.loadUnpaidData();
			}
			break;
	}

};

var module_instance = new GamFcltsFeeMngNticModule();

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
                            <th style="width:6%; height:18;">관리 기간</th>
							<td>
								<select id="sStartMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
								<select id="sStartMngMt">
									<option value="01" selected>01월</option>
									<option value="02">02월</option>
									<option value="03">03월</option>
									<option value="04">04월</option>
									<option value="05">05월</option>
									<option value="06">06월</option>
									<option value="07">07월</option>
									<option value="08">08월</option>
									<option value="09">09월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								</select>
								&nbsp; ~ &nbsp;
								<select id="sEndMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
								<select id="sEndMngMt">
									<option value="01" selected>01월</option>
									<option value="02">02월</option>
									<option value="03">03월</option>
									<option value="04">04월</option>
									<option value="05">05월</option>
									<option value="06">06월</option>
									<option value="07">07월</option>
									<option value="08">08월</option>
									<option value="09">09월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								</select>
							</td>
							<th style="width:6%; height:18;">업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th style="width:6%; height:18;">고지 업체</th>
							<td>
								<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
								<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
								<button id="popupSearchEntrpscd" class="popupButton">선택</button>
							</td>
							<td rowSpan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th style="width:6%; height:18;">고지 기간</th>
							<td>
								<input id="sStartNticDt" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sEndNticDt" size="12">
								&nbsp; ~ &nbsp;
								<input id="sEndNticDt" type="text" class="emdcal" data-role="dtTo" data-dt-from="sStartNticDt" size="12">
							</td>
							<th style="width:6%; height:18;">수납 구분</th>
							<td>
								<select id="sRcivSe">
									<option value="" selected>전체</option>
									<option value="0">미수납</option>
									<option value="1">연체</option>
									<option value="3">수납</option>
									<option value="2">연체수납</option>
									<option value="4">불납</option>
								</select>
							</td>
							<th style="width:6%; height:18;">요금 종류</th>
							<td>
								<input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" />
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
				<li><a href="#listTab" class="emdTab">시설물 관리비 고지현황</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리비 고지내역</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width=10%; height=20; text-align:center;">자료수</th>
								<td><input type="text" size="10" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width=10%; height=20; text-align:center;">총사용료</th>
								<td><input type="text" size="18" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width=10%; height=20; text-align:center;">총부가세</th>
								<td><input type="text" size="18" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width=10%; height=20; text-align:center;">총고지금액</th>
								<td><input type="text" size="18" id="sumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width=10%; height=20; text-align:center;">총연체금액</th>
								<td><input type="text" size="18" id="sumArrrgAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<button id="btnProcessNticIssue">　고지　처리　</button>
					<button id="btnCancelNticIssue">　고지　취소　</button>
					<button id="btnPrintNticIssue">고지서　　출력</button>
					<button id="btnAddNticIssue" class="buttonAdd">추가고지　입력</button>
					<button id="btnDelNticIssue" class="buttonDelete">추가고지　삭제</button>
					<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
					<button id="btnOpenFcltsFeeMngInqire">납부현황　조회</button>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 부과 내역</th>
								<td style="text-align:right;">
									<button id="btnOpenFcltsFeeMngInqire2">납부현황　조회</button>
									<button id="btnNticList">고지 내역 보기</button>
									<button id="btnArrrgList">연체 내역 보기</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18;">관　리　년　월</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<input id="mngMtYear" type="hidden"/>
									<input id="mngMtMon" type="hidden"/>
									<input type="text" size="20" id="mngYrMt" disabled>
									<input type="text" size="11" id="mngSeq" disabled>
								</td>
								<th style="width:10%; height:18;">업　무　구　분</th>
								<td>
									<input id="mngFeeJobSe" type="hidden"/>
									<input type="text" size="33" id="mngFeeJobSeNm" disabled>
								</td>
								<th style="width:10%; height:18;">부　과　업　체</th>
								<td>
									<input id="entrpscd" type="hidden"/>
									<input id="bizrno" type="hidden"/>
									<input type="text" size="33" id="entrpsNm" disabled>
									<!--
									<button id="popupDataEntrpscd" class="popupButton">선택</button>
									 -->
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:18;">관리비　　제목</th>
								<td colspan="3"><input type="text" size="93" id="mngFeeSj" disabled/></td>
								<th style="width:10%; height:18;">사　용　면　적</th>
								<td><input type="text" size="30" class="ygpaNumber" id="usageAr" disabled/> m<sup>2</sup></td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">시설관리용역비</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mngFee" disabled/> 원</td>
								<th style="width:10%; height:18;">전　기　요　금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="elctyFee" disabled/> 원</td>
								<th style="width:10%; height:18;">상하수도　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="waterFee" disabled/> 원</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">도시가스　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="gasFee" disabled/> 원</td>
								<th style="width:10%; height:18;">환경개선부담금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="envFee" disabled/> 원</td>
								<th style="width:10%; height:18;">관리비　　합계</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mngTotalFee" disabled/> 원</td>
                            </tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 고지 내역</th>
								<td style="text-align:right;">
									<button id="btnProcessNticIssue2">　고지　처리　</button>
									<button id="btnCancelNticIssue2">　고지　취소　</button>
									<button id="btnPrintNticIssue2">고지서　　출력</button>
									<button id="btnAddNticIssue2" class="buttonAdd">추가고지　입력</button>
									<button id="btnDelNticIssue2" class="buttonDelete">추가고지　삭제</button>
									<button id="btnSaveNticIssue" class="buttonSave">　　저　장　　</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18;">요　금　종　류</th>
								<td>
									<input id="prtAtCode" type="hidden"/>
									<input id="feeTp" type="hidden"/>
									<input id="fiscalYr" type="hidden"/>
									<input id="billNo" type="hidden"/>
									<input id="rcvdTp" type="hidden"/>
									<input id="chrgeKndNm" type="hidden"/>
									<input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM024" disabled/>
									<input type="text" id="reqestSeq" size="5" disabled/>
								</td>
								<th style="width:10%; height:18;">고　지　번　호</th>
								<td>
									<input type="text" size="15" id="accnutYear" disabled>
									<input type="text" size="16" id="nticNo" disabled>
								</td>
								<th style="width:10%; height:18;">부가세　　구분</th>
								<td>
									<input id="vatYnNm" type="hidden"/>
									<input id="vatYn" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" disabled/>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:18;">사　　용　　료</th>
								<td>
									<input type="text" size="30" class="ygpaNumber" id="fee" disabled/> 원
								</td>
								<th style="width:10%; height:18;">부　　가　　세</th>
								<td>
									<input type="text" size="30" class="ygpaNumber" id="vat" disabled/> 원
								</td>
								<th style="width:10%; height:18;">고　지　금　액</th>
								<td>
									<input type="text" size="30" class="ygpaNumber" id="nticAmt" disabled/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18;">고　지　일　자</th>
								<td>
									최초:
									<input type="text" size="12" id="firstNticDt" disabled/> /
									<input type="text" size="12" id="nticDt" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18;">납　부　기　한</th>
								<td>
									최초:
									<input type="text" size="12" id="firstPayTmlmt" disabled/> /
									<input type="text" size="12" id="payTmlmt" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18;">수　납　일　자</th>
								<td>
									<input id="rcivSe" type="hidden"/>
									<input type="text" size="16" id="rcivSeNm" disabled>
									<input type="text" size="16" id="rcivDt" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18;">연체요율／일수</th>
								<td>
									<input id="newDlySerNo" type="hidden"/>
									<input type="text" size="9" id="arrrgNo" disabled>
									<input type="text" size="10" class="ygpaNumber" id="arrrgTariff" disabled/>
									<input type="text" size="7" class="ygpaNumber" id="arrrgPayDates" disabled/> 일
								</td>
								<th style="width:10%; height:18;">연　체　금　액</th>
								<td>
									<input type="text" size="18" class="ygpaNumber" id="arrrgAmt" disabled/> 원
									<input type="text" size="10" id="arrrgSttus" style="font-weight:bold; background:yellow; text-align:center;" disabled>
								</td>
								<th style="width:10%; height:18;">연　체　처　리</th>
								<td>
									<button id="btnProcessNticIssueUnpaid">연체고지 처리</button>
									<button id="btnCancelNticIssueUnpaid">연체고지 취소</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18;">비　　　　　고</th>
                                <td colspan="3"><input type="text" size="92" id="rm"/></td>
								<th style="width:10%; height:18;">고지/출력/추가</th>
								<td>
									<input id="setoffYn" type="hidden"/>
									<input id="nticMth" type="hidden"/>
									<input type="text" size="10" id="nhtIsueYn" disabled>
									<input type="text" size="10" id="nhtPrintYn" disabled>
									<input type="text" size="10" id="aditNticYn" disabled>
								</td>
							</tr>
						</table>
						<table id="detailGrid" style="display:none;"></table>
						<table id="unpaidGrid" style="display:none;"></table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
