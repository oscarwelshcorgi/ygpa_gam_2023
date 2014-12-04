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
					{display:'관리 비',				name:'mngFee',				width:90,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'elctyFee',			width:90,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'waterFee',			width:90,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'gasFee',				width:90,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mngTotalFee',			width:90,		sortable:false,		align:'right'},
					{display:'납부 기한',			name:'payTmlmt',			width:80,		sortable:false,		align:'center'},
					{display:'수납 구분',			name:'rcivSeNm',			width:80,		sortable:false,		align:'center'},
					{display:'수납 일자',			name:'rcivDt',				width:80,		sortable:false,		align:'center'},
					{display:'부가세 구분',			name:'vatYnNm',				width:80,		sortable:false,		align:'center'},
					{display:'고지 여부',			name:'nhtIsueYn',			width:80,		sortable:false,		align:'center'},
					{display:'출력 여부',			name:'nhtOutputYn',			width:80,		sortable:false,		align:'center'},
					{display:'추가 고지 여부',		name:'aditNticYn',			width:90,		sortable:false,		align:'center'},
					{display:'연체 번호',			name:'arrrgNo',				width:80,		sortable:false,		align:'center'},
					{display:'연체 금액',			name:'arrrgAmt',			width:90,		sortable:false,		align:'right'},
					{display:'연체 요율',			name:'arrrgTariff',			width:80,		sortable:false,		align:'right'},
					{display:'연체 일수',			name:'arrrgPayDates',		width:80,		sortable:false,		align:'right'},
					{display:'요금 종류 코드',		name:'chrgeKnd',			width:100,		sortable:false,		align:'left'},
					{display:'항 코드',				name:'prtAtCode',			width:100,		sortable:false,		align:'left'}
                    ],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumFee').val(data.sumFee);
			module.$('#sumVat').val(data.sumVat);
			module.$('#sumNticAmt').val(data.sumNticAmt);
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
					{display:'수납 일자',		name:'rcivDt',			width:80,		sortable:false,		align:'center'}
					],
		showTableToggleBtn: true,
		height: '110'
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		var nhtIsueYn = row['#nhtIsueYn'];
		var rcivSe = row['rcivSe'];
		var aditNticYn = row['aditNticYn'];
		if (rcivSe == "0" || rcivSe == "1") {
			if (nhtIsueYn == "Y") {
				module.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
				module.$('#btnCancelNticIssue').enable();
				module.$('#btnCancelNticIssue').removeClass('ui-state-disabled');
				module.$('#btnPrintNticIssue').enable();
				module.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
				module.$('#btnPrintTaxInvoice').enable();
				module.$('#btnPrintTaxInvoice').removeClass('ui-state-disabled');
			} else {
				module.$('#btnProcessNticIssue').enable();
				module.$('#btnProcessNticIssue').removeClass('ui-state-disabled');
				module.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
				module.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
				module.$('#btnPrintTaxInvoice').disable({disableClass:"ui-state-disabled"});
			}
			module.$('#btnAddNticIssue').enable();
			module.$('#btnAddNticIssue').removeClass('ui-state-disabled');
			if (aditNticYn == "Y") {
				module.$('#btnDelNticIssue').enable();
				module.$('#btnDelNticIssue').removeClass('ui-state-disabled');
			} else {
				module.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
			}
		} else if (rcivSe == "2" || rcivSe == "3" || rcivSe == "4") {
			module.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnPrintNticIssue').enable();
			module.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
			module.$('#btnPrintTaxInvoice').enable();
			module.$('#btnPrintTaxInvoice').removeClass('ui-state-disabled');
			module.$('#btnAddNticIssue').enable();
			module.$('#btnAddNticIssue').removeClass('ui-state-disabled');
			module.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
		} else {
			module.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnPrintTaxInvoice').disable({disableClass:"ui-state-disabled"});
			module.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
			module.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
		}
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#vatYn').on('keyup',{module:this}, function(event){
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

	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sStartMngMt').val(mon);
	this.$('#sEndMngMt').val(mon);
	this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintTaxInvoice').disable({disableClass:"ui-state-disabled"});
	this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});

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
GamFcltsFeeMngNticModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnSaveNticIssue':
			this.saveNticIssue();
			break;
		case 'btnProcessNticIssue2':
			this.processNticIssue();
			break;
		case 'btnCancelNticIssue2':
			this.cancelNticIssue();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'popupSearchEntrpscd':
			this.doExecuteDialog('popupSearchEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
		case 'popupDataEntrpscd':
			this.doExecuteDialog('popupDataEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
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

	this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintTaxInvoice').disable({disableClass:"ui-state-disabled"});
	this.$('#btnAddNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.loadData();

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
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadDetail = function() {

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
		alert('이미 고지된 자료입니다.');
		return;
	}
	if (chrgeKnd == "") {
		alert('요금 종류가 부정확합니다.');
		this.$("#chrgeKnd").focus();
		return;
	}
	if (nticDt != "") {
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
	this.doAction('/mngFee/gamSaveFcltsFeeMngNticIssue.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.loadData();
		}
		alert(result.resultMsg);
	});

};

<%
/**
 * @FUNCTION NAME : processNticIssue
 * @DESCRIPTION   : 시설물 관리비 고지 내역을 고지처리한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.processNticIssue = function() {

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
		alert('이미 고지된 자료입니다.');
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
	//confirmMessage = "[" + this.$('#chrgeKnd').find('option:selected').text() + "] " + this.$('#fee').val() + "원을 고지하시겠습니까?";
	confirmMessage = "[" + this.$('#chrgeKnd_select').find('option:selected').text() + "] " + this.$('#fee').val() + "원을 고지하시겠습니까?";
	if (confirm(confirmMessage)) {
		this.doAction('/mngFee/gamProcessFcltsFeeMngNticIssue.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
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

	var inputVO = this.makeFormArgs("#detailForm");
	var chrgeKnd = this.$('#chrgeKnd').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var rcivSe = this.$('#rcivSe').val();
	var accnutYear = this.$('#accnutYear').val();
	var nticNo = this.$('#nticNo').val();
	var confirmMessage = "";
	if (nhtIsueYn != "Y") {
		alert('고지된 자료가 아닙니다.');
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
	confirmMessage = "[" + this.$('#chrgeKnd_select').find('option:selected').text() + "] " + this.$('#fee').val() + "원을 고지 취소하시겠습니까?";
	if (confirm(confirmMessage)) {
		this.doAction('/mngFee/gamCancelFcltsFeeMngNticIssue.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
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
GamFcltsFeeMngNticModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelFcltsFeeMngNtic.do');

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
		this.$('#btnPrintTaxInvoice2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveNticIssue').enable();
		this.$('#btnSaveNticIssue').removeClass('ui-state-disabled');
	} else {
		if (rcivSe == "0" || rcivSe == "1") {
			this.$('#rm').enable();
			if (nhtIsueYn == "Y") {
				this.$('#chrgeKnd').disable();
				this.$('#vatYn').disable();
				this.$('#fee').disable();
				this.$('#vat').disable();
				this.$('#nticAmt').disable();
				this.$('#nticDt').disable();
				this.$('#payTmlmt').disable();
				this.$('#btnProcessNticIssue2').disable({disableClass:"ui-state-disabled"});
				this.$('#btnCancelNticIssue2').enable();
				this.$('#btnCancelNticIssue2').removeClass('ui-state-disabled');
				this.$('#btnPrintNticIssue2').enable();
				this.$('#btnPrintNticIssue2').removeClass('ui-state-disabled');
				this.$('#btnPrintTaxInvoice2').enable();
				this.$('#btnPrintTaxInvoice2').removeClass('ui-state-disabled');
				this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
			} else {
				this.$('#chrgeKnd').enable();
				this.$('#vatYn').enable();
				this.$('#fee').enable();
				this.$('#vat').enable();
				this.$('#nticAmt').enable();
				this.$('#nticDt').enable();
				this.$('#payTmlmt').enable();
				this.$('#btnProcessNticIssue2').enable();
				this.$('#btnProcessNticIssue2').removeClass('ui-state-disabled');
				this.$('#btnCancelNticIssue2').disable({disableClass:"ui-state-disabled"});
				this.$('#btnPrintNticIssue2').disable({disableClass:"ui-state-disabled"});
				this.$('#btnPrintTaxInvoice2').disable({disableClass:"ui-state-disabled"});
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
			this.$('#btnPrintTaxInvoice2').enable();
			this.$('#btnPrintTaxInvoice2').removeClass('ui-state-disabled');
			this.$('#btnAddNticIssue2').enable();
			this.$('#btnAddNticIssue2').removeClass('ui-state-disabled');
			this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
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
			this.$('#btnPrintTaxInvoice2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});
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
	this.$('#btnPrintTaxInvoice2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnAddNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelNticIssue2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSaveNticIssue').disable({disableClass:"ui-state-disabled"});

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
				this.loadDetail();
				this.enableDetailInputItem();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
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
                            <th>관리 기간</th>
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
							<th>업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th>고지 업체</th>
							<td>
								<input id="sEntrpscd" type="text" size="6" readonly>&nbsp; &nbsp;
								<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
								<button id="popupSearchEntrpscd" class="popupButton">선택</button>
							</td>
							<td rowSpan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>고지 기간</th>
							<td>
								<input id="sStartNticDt" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sEndNticDt" size="8"> ~
								<input id="sEndNticDt" type="text" class="emdcal" data-role="dtTo" data-dt-from="sStartNticDt" size="8">
							</td>
							<th>수납 구분</th>
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
							<th>요금종류</th>
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
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th width="10%" height="20">자료수</th>
								<td><input type="text" size="15" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총사용료</th>
								<td><input type="text" size="25" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총부가세</th>
								<td><input type="text" size="25" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총고지금액</th>
								<td><input type="text" size="25" id="sumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<button id="btnProcessNticIssue">　　고　지　　</button>
					<button id="btnCancelNticIssue">　고지　취소　</button>
					<button id="btnPrintNticIssue">고지서　　출력</button>
					<button id="btnPrintTaxInvoice">계산서　　 출력</button>
					<button id="btnAddNticIssue">추가고지　입력</button>
					<button id="btnDelNticIssue">추가고지　삭제</button>
					<button id="btnExcelDownload">엑셀　다운로드</button>
					<button id="btnFcltsFeeMngInqire">납부현황　조회</button>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 부과 내역</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="10%" height="18">관리 년월</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<input id="mngMtYear" type="hidden"/>
									<input id="mngMtMon" type="hidden"/>
									<input type="text" size="20" id="mngYrMt" disabled>
									<input type="text" size="12" id="mngSeq" disabled>
								</td>
								<th width="10%" height="18">업무 구분</th>
								<td>
									<input id="mngFeeJobSe" type="hidden"/>
									<input type="text" size="33" id="mngFeeJobSeNm" disabled>
								</td>
								<th width="10%" height="18">부과 업체</th>
								<td>
									<input id="entrpscd" type="hidden"/>
									<input type="text" size="33" id="entrpsNm" disabled>
									<!--
									<button id="popupDataEntrpscd" class="popupButton">선택</button>
									 -->
								</td>
							</tr>
                            <tr>
								<th width="10%" height="18">관리비 제목</th>
								<td colspan="3"><input type="text" size="87" id="mngFeeSj" disabled/></td>
								<th width="10%" height="18">사용 면적</th>
								<td><input type="text" size="33" class="ygpaNumber" id="usageAr" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">시설 관리 용역비</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mngFee" disabled/></td>
								<th width="10%" height="18">전기 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="elctyFee" disabled/></td>
								<th width="10%" height="18">상하수도 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="waterFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">도시가스 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="gasFee" disabled/></td>
								<th width="10%" height="18">환경개선 부담금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="envFee" disabled/></td>
								<th width="10%" height="18">관리비 합계</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mngTotalFee" disabled/></td>
                            </tr>
						</table>
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 고지 내역</td>
								<td style="text-align: right">
									<button id="btnProcessNticIssue2">　　고　지　　</button>
									<button id="btnCancelNticIssue2">　고지　취소　</button>
									<button id="btnPrintNticIssue2">고지서　　출력</button>
									<button id="btnPrintTaxInvoice2">계산서　　 출력</button>
									<button id="btnAddNticIssue2">추가고지　입력</button>
									<button id="btnDelNticIssue2">추가고지　삭제</button>
									<button id="btnSaveNticIssue">　　저　장　　</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="10%" height="18">요금 종류</th>
								<td>
									<input id="prtAtCode" type="hidden"/>
									<input id="feeTp" type="hidden"/>
									<input id="fiscalYr" type="hidden"/>
									<input id="billNo" type="hidden"/>
									<input id="rcvdTp" type="hidden"/>
									<input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM024" disabled/>
									<!--
									<input type="text" size="7" id="chrgeKnd" disabled>
									<input type="text" size="25" id="chrgeKndNm" disabled>
									 -->
									<input type="text" id="reqestSeq" size="7" disabled/>
								</td>
								<th width="10%" height="18">고지 번호</th>
								<td>
									<input type="text" size="16" id="accnutYear" disabled>
									<input type="text" size="16" id="nticNo" disabled>
								</td>
								<th width="10%" height="18">부가세 구분</th>
								<td>
									<input id="vatYn" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" disabled/>
								</td>
							</tr>
                            <tr>
								<th width="10%" height="18">사용료</th>
								<td>
									<input type="text" size="33" class="ygpaNumber" id="fee" disabled/>
								</td>
								<th width="10%" height="18">부가세</th>
								<td>
									<!--
									<input type="text" size="11" id="vatYn" disabled>
									 -->
									<input type="text" size="33" class="ygpaNumber" id="vat" disabled/>
								</td>
								<th width="10%" height="18">고지 금액</th>
								<td>
									<input type="text" size="33" class="ygpaNumber" id="nticAmt" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">고지 일자</th>
								<td>
                                	<input type="text" size="30" id="nticDt" class="emdcal" disabled/>
								</td>
								<th width="10%" height="18">납부 기한</th>
								<td>
                                	<input type="text" size="30" id="payTmlmt" class="emdcal" disabled/>
								</td>
								<th width="10%" height="18">수납 일자</th>
								<td>
									<input id="rcivSe" type="hidden"/>
									<input type="text" size="16" id="rcivSeNm" disabled>
                                	<input type="text" size="16" id="rcivDt" disabled/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">연체 번호</th>
								<td>
									<input type="text" size="16" id="arrrgNo" disabled>
									<input type="text" size="16" class="ygpaNumber" id="arrrgPayDates" disabled/>
								</td>
								<th width="10%" height="18">연체 금액</th>
								<td>
									<input type="text" size="33" class="ygpaNumber" id="arrrgAmt" disabled/>
								</td>
								<th width="10%" height="18">고지/출력/추가</th>
								<td>
									<input type="text" size="10" id="nhtIsueYn" disabled>
									<input type="text" size="10" id="nhtOutputYn" disabled>
									<input type="text" size="10" id="aditNticYn" disabled>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="140" id="rm"/></td>
							</tr>
						</table>
						<table id="detailGrid" style="display:none"></table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
