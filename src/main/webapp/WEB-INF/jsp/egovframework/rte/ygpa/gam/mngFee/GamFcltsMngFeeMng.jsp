<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsMngFeeMng.jsp
 * @Description : 시설물 관리비 관리
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.11.19  Lee     최초 생성
 *
 * author lee
 * since 2014.11.19  Lee
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
 * @FUNCTION NAME : GamFcltsMngFeeMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsMngFeeMngModule() {}

GamFcltsMngFeeMngModule.prototype = new EmdModule(1000, 645);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsMngFeeMng.do',
		dataType : 'json',
		colModel : [
					{display:'관리 월',				name:'mainMngYrMt',				width:70,		sortable:false,		align:'center'},
					{display:'업무 구분',			name:'mainMngFeeJobSeNm',		width:90,		sortable:false,		align:'center'},
					{display:'관리비 제목',			name:'mainMngFeeSj',			width:175,		sortable:false,		align:'left'},
					{display:'시설 관리 용역비',	name:'mainFcltyMngFee',			width:100,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'mainElctyFee',			width:100,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'mainWaterFee',			width:100,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'mainGasFee',				width:100,		sortable:false,		align:'right'},
					{display:'환경개선 부담금',		name:'mainEnvFee',				width:100,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mainMngTotalFee',			width:100,		sortable:false,		align:'right'},
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumFcltyMngFee').val(data.sumFcltyMngFee);
			module.$('#sumElctyFee').val(data.sumElctyFee);
			module.$('#sumWaterFee').val(data.sumWaterFee);
			module.$('#sumGasFee').val(data.sumGasFee);
			module.$('#sumEnvFee').val(data.sumEnvFee);
			module.$('#sumMngTotalFee').val(data.sumMngTotalFee);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#detailGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsMngFeeMngDetail.do',
		dataType : 'json',
		colModel : [
					{display:'관리 월',				name:'mngYrMt',				width:60,		sortable:false,		align:'center'},
					{display:'순번',				name:'mngSeq',				width:50,		sortable:false,		align:'center'},
					{display:'업체 명',				name:'entrpsNm',			width:150,		sortable:false,		align:'left'},
					{display:'사용 면적',			name:'usageAr',				width:95,		sortable:false,		align:'right'},
					{display:'관리 비',				name:'mngFee',				width:95,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'elctyFee',			width:95,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'waterFee',			width:95,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'gasFee',				width:95,		sortable:false,		align:'right'},
					{display:'환경개선 부담금',		name:'envFee',				width:100,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mngTotalFee',			width:95,		sortable:false,		align:'right'},
					],
		showTableToggleBtn: true,
		height: '175'
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mainMngMt + row.mainMngFeeJobSe;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mainMngMt + row.mainMngFeeJobSe;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#detailGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectDetailData();
	});

	this.$("#detailGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._detailmode = 'modify';
		module._detailKeyValue = row.mngMt + row.mngFeeJobSe + row.mngSeq;
		module.loadSubDetail();
		module.enableSubDetailInputItem();
	});

	this.$('#mainMngMtYear').on('change',{module:this}, function(event){
		event.data.module.setMainMngMt();
	});

	this.$('#mainMngMtMon').on('change',{module:this}, function(event){
		event.data.module.setMainMngMt();
	});

	this.$('#mainMngFeeJobSe').on('change',{module:this}, function(event){
		event.data.module.setMainMngFeeJobSeNm();
	});

	this.$('#mainFcltyMngFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMainMngTotalFee();
	});

	this.$('#mainElctyFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMainMngTotalFee();
	});

	this.$('#mainWaterFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMainMngTotalFee();
	});

	this.$('#mainGasFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMainMngTotalFee();
	});

	this.$('#mainEnvFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMainMngTotalFee();
	});

	this.$('#mngFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMngTotalFee();
	});

	this.$('#elctyFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMngTotalFee();
	});

	this.$('#waterFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMngTotalFee();
	});

	this.$('#gasFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMngTotalFee();
	});

	this.$('#envFee').on('keyup change',{module:this}, function(event){
		event.data.module.calcMngTotalFee();
	});

	this.$('#nticDt').on('keyup change',{module:this}, function(event){
		event.data.module.setPayTmlmt();
	});

	this.$('#chrgeKnd').on('change',{module:this}, function(event){
		var chrgeKndNm = event.data.module.$('#chrgeKnd_select').find('option:selected').text();
		//var chrgeKndNm = event.data.module.$('#chrgeKnd option:selected').text();
		event.data.module.$('#chrgeKndNm').val(chrgeKndNm);
	});

	this.$("#statEntrpscd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getQueryEntrpsNm();
	});

	this._mode = '';
	this._detailmode = '';
	this._mainKeyValue = '';
	this._detailKeyValue = '';
	this._searchButtonClick = false;
	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sStartMngMt').val(mon);
	this.$('#sEndMngMt').val(mon);
	this.$('#statMngMtMon').val(mon);
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : isValidSeq
 * @DESCRIPTION   : SEQ STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. seqString - SEQ STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidSeq = function(seqString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (seqString == "") {
			return false;
		}
	} else {
		if (seqString == "") {
			return true;
		}
	}
	var seq = Number(seqString);
	if (seq > 999 || seq <= 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidNticNo
 * @DESCRIPTION   : NTIC NO. STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. nticNoString - NTIC NO. STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidNticNo = function(nticNoString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (nticNoString == "") {
			return false;
		}
	} else {
		if (nticNoString == "") {
			return true;
		}
	}
	var nticNo = Number(nticNoString);
	if (nticNo > 999999 || nticNo <= 0) {
		return false;
	}
	return true;

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
GamFcltsMngFeeMngModule.prototype.isValidYear = function(yearString, nullCheckFlag) {

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
 * @FUNCTION NAME : isValidMonth
 * @DESCRIPTION   : MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. monthString - MONTH STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidMonth = function(monthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (monthString == "") {
			return false;
		}
	} else {
		if (monthString == "") {
			return true;
		}
	}
	var month = Number(monthString);
	if (month > 12 || month < 1) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidYearMonth
 * @DESCRIPTION   : YEAR & MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. yearMonthString - YEAR & MONTH STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidYearMonth = function(yearMonthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (yearMonthString == "") {
			return false;
		}
	} else {
		if (yearMonthString == "") {
			return true;
		}
	}
	var year = Number(yearMonthString.substring(0,4));
	var month = Number(yearMonthString.substring(4,6));
	if (year > 9999 || year < 1900) {
		return false;
	}
	if (month > 12 || month < 1) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidMonthFromTo
 * @DESCRIPTION   : 기간 MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. startMonthString - START MONTH STRING
 *   2. endMonthString - END MONTH STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidMonthFromTo = function(startMonthString, endMonthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (startMonthString == "" || endMonthString == "") {
			return false;
		}
	} else {
		if (startMonthString == "" && endMonthString == "") {
			return true;
		}
	}
	var startMonth = Number(startMonthString.replace(/-/gi, ""));
	var endMonth = Number(endMonthString.replace(/-/gi, ""));
	if (startMonth > endMonth) {
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
GamFcltsMngFeeMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

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
GamFcltsMngFeeMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

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
GamFcltsMngFeeMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

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
 *   2. zeroCheckFlag - ZERO CHECK FLAG
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidAmount = function(amountValue, zeroCheckFlag) {

	if (zeroCheckFlag == true) {
		if (amountValue > 9999999999999 || amountValue <= 0) {
			return false;
		}
	} else {
		if (amountValue > 9999999999999 || amountValue < 0) {
			return false;
		}
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidAr
 * @DESCRIPTION   : AR에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. arValue - AR VALUE
**/
%>
GamFcltsMngFeeMngModule.prototype.isValidAr = function(arValue) {

	if (arValue > 99999.99 || arValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.drawChart = function() {

	var statVO = [];
	var statValueArr=[];
	var maxStatValue=0;
	var statValue=0;
	var statMt="";
	var statMngFeeSeNm = this.$('#statMngFeeSe option:selected').text();
	var statType = this.$('#statTypeSe').val();
	var statTypeSeNm = this.$('#statTypeSe option:selected').text();
	statVO={
			'statMngMtYear':this.$('#statMngMtYear').val(),
			'statMngMtMon':this.$('#statMngMtMon').val(),
			'statTypeSe':this.$('#statTypeSe').val(),
			'statMngFeeJobSe':this.$('#statMngFeeJobSe').val(),
			'statEntrpscd':this.$('#statEntrpscd').val(),
			'statMngFeeSe':this.$('#statMngFeeSe').val()
	};
	this.doAction('/mngFee/gamSelectFcltsMngFeeMngChart.do', statVO, function(module, result) {
		if (result.resultCode == "0") {
			var dataCount = result.resultList[0]['dataCount']*1;
			if (dataCount > 0) {
				for (var i=0; i<dataCount; i++) {
					if (statType == "M") {
						statMt=result.resultList[i]['mt'] + "월";
					} else {
						statMt=result.resultList[i]['mt'];
					}
					statValue=result.resultList[i]['statValue']*1;
					statValueArr[i]={month: statMt, gauge: statValue};
					if (maxStatValue<statValue) {
						maxStatValue=statValue;
					}
				};
			} else {
				for (var i=0; i<12; i++) {
					statMt="" + i;
					statValue=0;
					statValueArr[i]={month: statMt, gauge: statValue};
				};
			}
		} else {
			for (var i=0; i<12; i++) {
				statMt="" + i;
				statValue=0;
				statValueArr[i]={month: statMt, gauge: statValue};
			};
		}
		if (maxStatValue<10) {
			maxStatValue=10;
		}
		if (module.statChart==null) {
			module.statChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#fcltyMngFeeMngSttusChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				tooltip			: "#gauge# 원",
				label			: "#gauge#",
				xAxis			: {
					title 		: "관리비 부과 현황 " + "(" + statTypeSeNm + ")",
					template	: "#month#"
				},
				yAxis			: {
					start		: 0,
					end			: maxStatValue + 10,
					step		: Math.ceil(maxStatValue / 10),
					title		: statMngFeeSeNm + " (단위 : 원)"
				}
			});
		} else {
			module.statChart.clearAll();
			module.statChart.define("xAxis", {
				title 			: "관리비 부과 현황 " + "(" + statTypeSeNm + ")",
				template		: "#month#"
			});
			module.statChart.define("yAxis", {
				start			: 0,
				end				: maxStatValue + 10,
				step 			: Math.ceil(maxStatValue / 10),
				title			: statMngFeeSeNm + " (단위 : 원)"
			});
		}
		module.statChart.parse(statValueArr, "json");
		module.statChart.refresh();
	});

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
GamFcltsMngFeeMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupDataEntrpscd':
			if (msg == 'ok') {
				this.$('#entrpscd').val(value.entrpscd);
				this.$('#entrpsNm').val(value.entrpsNm);
				this.$("#mngFee").focus();
			}
			break;
		case 'popupStatEntrpscd':
			if (msg == 'ok') {
				this.$('#statEntrpscd').val(value.entrpscd);
				this.$('#statEntrpsNm').val(value.entrpsNm);
				this.$("#statMngFeeSe").focus();
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
GamFcltsMngFeeMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mode = 'insert';
			this._detailmode = 'query';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnDelete':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.enableSubDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnCopy':
			this.copyData();
			break;
		case 'btnSaveMain':
			this.saveData();
			break;
		case 'btnDeleteMain':
			this.deleteData();
			break;
		case 'btnAddDetail':
			this.addDetailData();
			break;
		case 'btnSaveDetail':
			this.saveDetailData();
			break;
		case 'btnDeleteDetail':
			this.deleteDetailData();
			break;
		case 'btnProcessNticIssue':
			this.processNticIssue();
			break;
		case 'btnCancelNticIssue':
			this.cancelNticIssue();
			break;
		case 'btnPrintNticIssue':
			this.printNticIssue();
			break;
		case 'btnPrintReport':
			this.printReport();
			break;
		case 'btnOpenFcltsFeeMngNtic':
		case 'btnOpenFcltsFeeMngNtic2':
			this.openFcltsFeeMngNticModule();
			break;
		case 'btnOpenFcltsFeeMngInqire':
		case 'btnOpenFcltsFeeMngInqire2':
			this.openFcltsFeeMngInqireModule();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'btnExcelDownloadDetail':
			this.downloadExcelDetail();
			break;
		case 'btnStatSearch':
			this.drawChart();
			break;
		case 'popupDataEntrpscd':
			this.doExecuteDialog('popupDataEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
		case 'popupStatEntrpscd':
			this.doExecuteDialog('popupStatEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
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
GamFcltsMngFeeMngModule.prototype.onSubmit = function() {

	var sStartMngYear = this.$('#sStartMngYear').val();
	var sStartMngMt = this.$('#sStartMngMt').val();
	var sEndMngYear = this.$('#sEndMngYear').val();
	var sEndMngMt = this.$('#sEndMngMt').val();
	if (this.isValidYear(sStartMngYear, true) == false) {
		alert('시작 관리 년도가 부정확합니다.');
		this.$("#sStartMngYear").focus();
		return;
	}
	if (this.isValidMonth(sStartMngMt, true) == false) {
		alert('시작 관리 월이 부정확합니다.');
		this.$("#sStartMngMt").focus();
		return;
	}
	if (this.isValidYear(sEndMngYear, true) == false) {
		alert('종료 관리 년도가 부정확합니다.');
		this.$("#sEndMngYear").focus();
		return;
	}
	if (this.isValidMonth(sEndMngMt, true) == false) {
		alert('종료 관리 월이 부정확합니다.');
		this.$("#sEndMngMt").focus();
		return;
	}
	if (this.isValidMonthFromTo(sStartMngYear + sStartMngMt, sEndMngYear + sEndMngMt, true) == false) {
		alert('시작 관리 년월이 종료 관리 년월보다 큽니다.');
		this.$("#sStartMngYear").focus();
		return;
	}
	this._mode = 'query';
	this._detailmode = 'query';
	this._mainKeyValue = '';
	this._detailKeyValue = '';
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
GamFcltsMngFeeMngModule.prototype.loadData = function() {

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
GamFcltsMngFeeMngModule.prototype.refreshData = function() {

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
GamFcltsMngFeeMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
		var mainMngMt = this.$('#mainMngMt').val();
		var mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
		this.makeFormValues('#subDetailForm', {});
		this.makeDivValues('#subDetailForm', {});
		this.$('#mngMt').val(mainMngMt);
		this.$('#mngFeeJobSe').val(mainMngFeeJobSe);
		var detailOpt=this.makeFormArgs('#subDetailForm');
		this.$('#detailGrid').flexOptions({params:detailOpt}).flexReload();
		this.makeFormValues('#subDetailForm', {});
		this.makeDivValues('#subDetailForm', {});
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectFcltsMngFeeMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				var mainMngMt = module.$('#mainMngMt').val();
				var mainMngFeeJobSe = module.$('#mainMngFeeJobSe').val();
			    module.makeFormValues('#subDetailForm', {});
			    module.makeDivValues('#subDetailForm', {});
			    module.$('#mngMt').val(mainMngMt);
			    module.$('#mngFeeJobSe').val(mainMngFeeJobSe);
			    var detailOpt=module.makeFormArgs('#subDetailForm');
			    module.$('#detailGrid').flexOptions({params:detailOpt}).flexReload();
			    module.makeFormValues('#subDetailForm', {});
			    module.makeDivValues('#subDetailForm', {});
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
GamFcltsMngFeeMngModule.prototype.selectData = function() {

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
	this.$("#mainGrid").selectFilterRow([{col:"mngMt", filter:mngMt},
										 {col:"mngFeeJobSe", filter:mngFeeJobSe}]);
	this._mode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.enableSubDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : loadSubDetail
 * @DESCRIPTION   : SUB 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.loadSubDetail = function() {

	var row = this.$('#detailGrid').selectedRows();

	if (row.length==0) {
		this.makeFormValues('#subDetailForm', []);
		this.makeDivValues('#subDetailForm', []);
		return;
	}
	this.makeFormValues('#subDetailForm', row[0]);
	this.makeDivValues('#subDetailForm', row[0]);

};

<%
/**
 * @FUNCTION NAME : selectDetailData
 * @DESCRIPTION   : DETAIL DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.selectDetailData = function() {

	if (this._detailmode == 'query') {
		return;
	} else if (this._detailmode != 'insert' && this._detailmode != 'modify') {
		return;
	}
	if (this._detailKeyValue == "") {
		return;
	}
	var mngMt = this._detailKeyValue.substring(0,6);
	var mngFeeJobSe = this._detailKeyValue.substring(6,7);
	var mngSeq = this._detailKeyValue.substring(7,10);
	this.$("#detailGrid").selectFilterRow([{col:"mngMt", filter:mngMt},
	                                       {col:"mngFeeJobSe", filter:mngFeeJobSe},
										   {col:"mngSeq", filter:mngSeq}]);
	this._detailmode = 'modify';
	this.loadSubDetail();
	this.enableSubDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : setMainMngMt
 * @DESCRIPTION   : MAIN 관리 월을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.setMainMngMt = function() {

	var mainMngMtYear = this.$('#mainMngMtYear').val();
	var mainMngMtMon = this.$('#mainMngMtMon').val();
	var mainMngMt = "";
	if (mainMngMtYear != "" && mainMngMtMon != "") {
		mainMngMt = mainMngMtYear + mainMngMtMon;
	}
	this.$('#mainMngMt').val(mainMngMt);
	this.$('#mainMngFeeSj').val(mainMngMtYear + '년 ' + mainMngMtMon + '월 관리비');

};

<%
/**
 * @FUNCTION NAME : setMainMngFeeJobSeNm
 * @DESCRIPTION   : MAIN 관리비 업무 구분을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.setMainMngFeeJobSeNm = function() {

	var mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
	var mainMngFeeJobSeNm = "";
	if (mainMngFeeJobSe == "M") {
		mainMngFeeJobSeNm = "[M]:마린센터";
	} else if (mainMngFeeJobSe == "E") {
		mainMngFeeJobSeNm = "[E]:전기시설";
	}
	this.$('#mainMngFeeJobSeNm').val(mainMngFeeJobSeNm);

};

<%
/**
 * @FUNCTION NAME : calcMainMngTotalFee
 * @DESCRIPTION   : MAIN 관리비 합계를 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.calcMainMngTotalFee = function() {

	var mainFcltyMngFee = Number(this.$('#mainFcltyMngFee').val().replace(/,/gi, ""));
	var mainElctyFee = Number(this.$('#mainElctyFee').val().replace(/,/gi, ""));
	var mainWaterFee = Number(this.$('#mainWaterFee').val().replace(/,/gi, ""));
	var mainGasFee = Number(this.$('#mainGasFee').val().replace(/,/gi, ""));
	var mainEnvFee = Number(this.$('#mainEnvFee').val().replace(/,/gi, ""));
	var mainMngTotalFee = mainFcltyMngFee + mainElctyFee + mainWaterFee + mainGasFee + mainEnvFee;
	this.$('#mainMngTotalFee').val('' + $.number(mainMngTotalFee));

};

<%
/**
 * @FUNCTION NAME : calcMngTotalFee
 * @DESCRIPTION   : 관리비 합계를 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.calcMngTotalFee = function() {

	var fcltyMngFee = Number(this.$('#mngFee').val().replace(/,/gi, ""));
	var elctyFee = Number(this.$('#elctyFee').val().replace(/,/gi, ""));
	var waterFee = Number(this.$('#waterFee').val().replace(/,/gi, ""));
	var gasFee = Number(this.$('#gasFee').val().replace(/,/gi, ""));
	var envFee = Number(this.$('#envFee').val().replace(/,/gi, ""));
	var mngTotalFee = fcltyMngFee + elctyFee + waterFee + gasFee + envFee;
	var vat = Math.floor(mngTotalFee / 10);
	var nticAmt = mngTotalFee + vat;
	this.$('#mngTotalFee').val('' + $.number(mngTotalFee));
	this.$('#fee').val('' + $.number(mngTotalFee));
	this.$('#vat').val('' + $.number(vat));
	this.$('#nticAmt').val('' + $.number(nticAmt));

};

<%
/**
 * @FUNCTION NAME : setPayTmlmt
 * @DESCRIPTION   : 납부 기한을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.setPayTmlmt = function() {

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
 * @FUNCTION NAME : getNewMngSeq
 * @DESCRIPTION   : 새로운 관리 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.getNewMngSeq = function() {

	var searchVO = this.makeFormArgs("#subDetailForm");
	var mngMt = this.$('#mngMt').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	if (mngMt > "999912"  || mngMt < "200001" || mngMt == "") {
		return;
	}
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		return;
	}
	this.doAction('/mngFee/gamSelectFcltsMngFeeMngDetailMaxMngSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#mngSeq').val(result.sMaxMngSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : DATA ADD (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.addData = function() {

	var mainMngFeeJobSe = this.$('#sMngFeeJobSe').val();
	var mainMngFeeJobSeNm = "";
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	if (month.length==1) {
		month="0"+month;
	}
	this.$('#mainMngMtYear').val(year);
	this.$('#mainMngMtMon').val(month);
	this.$('#mainMngMt').val(year + month);
	if (mainMngFeeJobSe == "M") {
		mainMngFeeJobSeNm = "[M]:마린센터";
	} else if (mainMngFeeJobSe == "E") {
		mainMngFeeJobSeNm = "[E]:전기시설";
	}
	this.$('#mainMngFeeJobSe').val(mainMngFeeJobSe);
	this.$('#mainMngFeeJobSeNm').val(mainMngFeeJobSeNm);
	this.$('#mainMngFeeSj').val(year + '년 ' + month + '월 관리비');
	this.$('#mainFcltyMngFee').val('0');
	this.$('#mainElctyFee').val('0');
	this.$('#mainWaterFee').val('0');
	this.$('#mainGasFee').val('0');
	this.$('#mainEnvFee').val('0');
	this.$('#mainMngTotalFee').val('0');
	this.enableDetailInputItem();
	this.$('#mainMngFeeSj').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : DATA SAVE (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mainMngMtYear = this.$('#mainMngMtYear').val();
	var mainMngMtMon = this.$('#mainMngMtMon').val();
	var mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
	var mainFcltyMngFee = Number(this.$('#mainFcltyMngFee').val().replace(/,/gi, ""));
	var mainElctyFee = Number(this.$('#mainElctyFee').val().replace(/,/gi, ""));
	var mainWaterFee = Number(this.$('#mainWaterFee').val().replace(/,/gi, ""));
	var mainGasFee = Number(this.$('#mainGasFee').val().replace(/,/gi, ""));
	var mainEnvFee = Number(this.$('#mainEnvFee').val().replace(/,/gi, ""));
	var mainMngTotalFee = Number(this.$('#mainMngTotalFee').val().replace(/,/gi, ""));
	if (this.isValidYear(mainMngMtYear, true) == false) {
		alert('관리 년도가 부정확합니다.');
		this.$("#mainMngMtYear").focus();
		return;
	}
	if (this.isValidMonth(mainMngMtMon, true) == false) {
		alert('관리 월이 부정확합니다.');
		this.$("#mainMngMtMon").focus();
		return;
	}
	if (mainMngFeeJobSe != "M" && mainMngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mainMngFeeJobSe").focus();
		return;
	}
	if (this.isValidAmount(mainFcltyMngFee, false) == false) {
		alert('시설 관리 용역비가 부정확합니다.');
		this.$("#mainFcltyMngFee").focus();
		return;
	}
	if (this.isValidAmount(mainElctyFee, false) == false) {
		alert('전기 요금이 부정확합니다.');
		this.$("#mainElctyFee").focus();
		return;
	}
	if (this.isValidAmount(mainWaterFee, false) == false) {
		alert('상하수도 요금이 부정확합니다.');
		this.$("#mainWaterFee").focus();
		return;
	}
	if (this.isValidAmount(mainGasFee, false) == false) {
		alert('도시가스 요금이 부정확합니다.');
		this.$("#mainGasFee").focus();
		return;
	}
	if (this.isValidAmount(mainEnvFee, false) == false) {
		alert('환경개선 부담금이 부정확합니다.');
		this.$("#mainEnvFee").focus();
		return;
	}
	if (this.isValidAmount(mainMngTotalFee, true) == false) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (mainMngTotalFee != (mainFcltyMngFee + mainElctyFee + mainWaterFee + mainGasFee + mainEnvFee)) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = mainMngMtYear + mainMngMtMon + mainMngFeeJobSe;
		this.doAction('/mngFee/gamInsertFcltsMngFeeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this._mainKeyValue = mainMngMtYear + mainMngMtMon + mainMngFeeJobSe;
		this.doAction('/mngFee/gamUpdateFcltsMngFeeMng.do', inputVO, function(module, result) {
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
 * @DESCRIPTION   : DATA DELETE (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.deleteData = function() {

	var mainMngMtYear = this.$('#mainMngMtYear').val();
	var mainMngMtMon = this.$('#mainMngMtMon').val();
	var mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
	var nhtIsueYn = this.$('#mainNhtIsueYn').val();
	var confirmMessage = "";
	if (mainMngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mainMngMtYear").focus();
		return;
	}
	if (mainMngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mainMngMtMon").focus();
		return;
	}
	if (mainMngFeeJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mainMngFeeJobSe").focus();
		return;
	}
	if (nhtIsueYn == "Y") {
		alert("관리비 상세내역에 고지처리된 자료가 존재합니다!");
		return;
	} else if (nhtIsueYn == "N") {
		confirmMessage = "[" + mainMngMtYear + "-" + mainMngMtMon + "]월 관리비 내역을 삭제하시겠습니까?" +
						"\r\n(관리비 상세내역이 모두 삭제됩니다)";
	} else if (nhtIsueYn == "X") {
		confirmMessage = "[" + mainMngMtYear + "-" + mainMngMtMon + "]월 관리비 내역을 삭제하시겠습니까?";
	} else {
		confirmMessage = "[" + mainMngMtYear + "-" + mainMngMtMon + "]월 관리비 내역을 삭제하시겠습니까?";
	}
	if (confirm(confirmMessage)) {
		var inputVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteFcltsMngFeeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mode = 'query';
				module._detailmode = 'query';
				module._mainKeyValue = '';
				module._detailKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : 이전 월 자료를 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.copyData = function() {

	var copyVO = [];
	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryMngYear = this.$('#sEndMngYear').val();
	var sQueryMngMt = this.$('#sEndMngMt').val();
	var sQueryMngFeeJobSe = this.$('#sMngFeeJobSe').val();
	var mtCnt=0;
	var prevMngMt = "";
	var mainCnt = "";
	var detailCnt = "";
	var reqestCnt = "";
	if (this.isValidYear(sQueryMngYear, true) == false) {
		alert('종료 관리 년도가 부정확합니다.');
		this.$("#sEndMngYear").focus();
		return;
	}
	if (this.isValidMonth(sQueryMngMt, true) == false) {
		alert('종료 관리 월이 부정확합니다.');
		this.$("#sEndMngMt").focus();
		return;
	}
	if (sQueryMngFeeJobSe != "M" && sQueryMngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#sMngFeeJobSe").focus();
		return;
	}
	if (confirm("이전월의 자료를 [" + sQueryMngYear + "-" + sQueryMngMt + "월] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectFcltsMngFeeMngMonthCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		mtCnt=result.resultList[0]['mtCnt']*1;
		if (mtCnt > 0) {
			alert('[' + sQueryMngYear + '-' + sQueryMngMt + '월] 자료가 존재합니다.');
			return;
		}
		prevMngMt = result.resultList[0]['prevMt'];
		mainCnt = result.resultList[0]['mainCnt'];
		detailCnt = result.resultList[0]['detailCnt'];
		reqestCnt = result.resultList[0]['reqestCnt'];
		copyVO={
			'mngMtYear':sQueryMngYear,
			'mngMtMon':sQueryMngMt,
			'mngFeeJobSe':sQueryMngFeeJobSe,
			'prevMngMt':prevMngMt,
			'mainCnt':mainCnt,
			'detailCnt':detailCnt,
			'reqestCnt':reqestCnt,
			'regUsr':"",
			'deptCd':""
		};
		module.doAction('/mngFee/gamCopyFcltsMngFeeMng.do', copyVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	});

};

<%
/**
 * @FUNCTION NAME : addDetailData
 * @DESCRIPTION   : DATA ADD (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.addDetailData = function() {

	var mainMngMtYear = this.$('#mainMngMtYear').val();
	var mainMngMtMon = this.$('#mainMngMtMon').val();
	var mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
	var mainMngFeeSj = this.$('#mainMngFeeSj').val();
	var mngSeq = "";
	if (mainMngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		return;
	}
	if (mainMngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		return;
	}
	if (mainMngFeeJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		return;
	}
	this._detailmode = 'insert';
	this._detailKeyValue = '';
	this.$('#mngMt').val(mainMngMtYear + mainMngMtMon);
	this.$('#mngFeeJobSe').val(mainMngFeeJobSe);
	mngSeq = this.getNewMngSeq();
	this.$('#mngSeq').val(mngSeq);
	this.$('#usageAr').val('0.00');
	this.$('#entrpscd').val('');
	this.$('#entrpsNm').val('');
	this.$('#mngFee').val('0');
	this.$('#elctyFee').val('0');
	this.$('#waterFee').val('0');
	this.$('#gasFee').val('0');
	this.$('#envFee').val('0');
	this.$('#mngTotalFee').val('0');
	this.$('#reqestSeq').val('001');
	this.$('#setoffYn').val('N');
	this.$('#nticMth').val('1');
	this.$('#aditNticYn').val('N');
	this.$('#vatYn').val('2');
	this.$('#nhtIsueYn').val('N');
	this.$('#nhtPrintYn').val('N');
	this.$('#vatYnNm').val('');
	this.$('#fee').val('0');
	this.$('#vat').val('0');
	this.$('#nticAmt').val('0');
	this.$('#nticDt').val('');
	this.$('#payTmlmt').val('');
	this.$('#prtAtCode').val('622');
	if (mainMngFeeJobSe == "M") {
		this.$('#chrgeKnd').val('QM');
		this.$('#chrgeKndNm').val('마린센터 관리비');
	} else if (mainMngFeeJobSe == "E") {
		this.$('#chrgeKnd').val('QE');
		this.$('#chrgeKndNm').val('전기시설 관리비');
	} else {
		this.$('#chrgeKnd').val('');
		this.$('#chrgeKndNm').val('');
	}
	this.$('#accnutYear').val('');
	this.$('#nticNo').val('');
	this.$('#rcivSe').val('0');
	this.$('#rcivSeNm').val('');
	this.$('#rcivDt').val('');
	this.$('#rm').val(mainMngFeeSj);
	this.enableSubDetailInputItem();
	this.$('#usageAr').focus();

};

<%
/**
 * @FUNCTION NAME : saveDetailData
 * @DESCRIPTION   : DATA SAVE (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.saveDetailData = function() {

	var inputVO = this.makeFormArgs("#subDetailForm");
	var mngMt = this.$('#mngMt').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngSeq = this.$('#mngSeq').val();
	var usageAr = Number(this.$('#usageAr').val().replace(/,/gi, ""));
	var entrpscd = this.$('#entrpscd').val();
	var mngFee = Number(this.$('#mngFee').val().replace(/,/gi, ""));
	var elctyFee = Number(this.$('#elctyFee').val().replace(/,/gi, ""));
	var waterFee = Number(this.$('#waterFee').val().replace(/,/gi, ""));
	var gasFee = Number(this.$('#gasFee').val().replace(/,/gi, ""));
	var envFee = Number(this.$('#envFee').val().replace(/,/gi, ""));
	var mngTotalFee = Number(this.$('#mngTotalFee').val().replace(/,/gi, ""));
	var chrgeKnd = this.$('#chrgeKnd').val();
	if (this.isValidYearMonth(mngMt, true) == false) {
		alert('관리 년월이 부정확합니다.');
		return;
	}
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		return;
	}
	if (this.isValidSeq(mngSeq, true) == false) {
		alert('관리 월이 부정확합니다.');
		return;
	}
	if (this.isValidAr(usageAr) == false) {
		alert('면적이 부정확합니다.');
		this.$("#usageAr").focus();
		return;
	}
	if (entrpscd == "") {
		alert('부과 업체가 부정확합니다.');
		this.$("#usageAr").focus();
		return;
	}
	if (this.isValidAmount(mngFee, false) == false) {
		alert('시설 관리 용역비가 부정확합니다.');
		this.$("#mainFcltyMngFee").focus();
		return;
	}
	if (this.isValidAmount(elctyFee, false) == false) {
		alert('전기 요금이 부정확합니다.');
		this.$("#mainElctyFee").focus();
		return;
	}
	if (this.isValidAmount(waterFee, false) == false) {
		alert('상하수도 요금이 부정확합니다.');
		this.$("#mainWaterFee").focus();
		return;
	}
	if (this.isValidAmount(gasFee, false) == false) {
		alert('도시가스 요금이 부정확합니다.');
		this.$("#mainGasFee").focus();
		return;
	}
	if (this.isValidAmount(envFee, false) == false) {
		alert('환경개선 부담금이 부정확합니다.');
		this.$("#mainEnvFee").focus();
		return;
	}
	if (this.isValidAmount(mngTotalFee, true) == false) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (mngTotalFee != (mngFee + elctyFee + waterFee + gasFee + envFee)) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (chrgeKnd == "") {
		alert('요금 종류가 부정확합니다.');
		return;
	}
	if (this._detailmode == "insert") {
		this.doAction('/mngFee/gamInsertFcltsMngFeeMngDetail.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._detailKeyValue = mngMt + mngFeeJobSe + mngSeq;
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateFcltsMngFeeMngDetail.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._detailKeyValue = mngMt + mngFeeJobSe + mngSeq;
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteDetailData
 * @DESCRIPTION   : DATA DELETE (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.deleteDetailData = function() {

	var mngMt = this.$('#mngMt').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var entrpsNm = this.$('#entrpsNm').val();
	var nhtIsueYn = this.$('#nhtIsueYn').val();
	var confirmMessage = "";
	if (mngMt == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMt").focus();
		return;
	}
	if (mngFeeJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	if (nhtIsueYn == "Y") {
		alert("고지처리된 자료입니다!");
		return;
	} else if (nhtIsueYn == "N") {
		confirmMessage = "[" + mngMt + "]월 " + entrpsNm + " 관리비 부과 내역을 삭제하시겠습니까?";
	} else if (nhtIsueYn == "X") {
		confirmMessage = "[" + mngMt + "]월 " + entrpsNm + " 관리비 부과 내역을 삭제하시겠습니까?";
	} else {
		confirmMessage = "[" + mngMt + "]월 " + entrpsNm + " 관리비 부과 내역을 삭제하시겠습니까?";
	}
	if (confirm(confirmMessage)) {
		var inputVO = this.makeFormArgs("#subDetailForm");
		this.doAction('/mngFee/gamDeleteFcltsMngFeeMngDetail.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._detailKeyValue = '';
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : getQueryEntrpsNm
 * @DESCRIPTION   : 조회조건 고지업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.getQueryEntrpsNm = function() {

	var sEntrpscd = this.$('#statEntrpscd').val();
	if (sEntrpscd.length == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/mngFee/gamSelectFcltsMngFeeMngEntrpsNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#statEntrpsNm').val(result.sEntrpsNm);
			}
		});
	} else {
		this.$('#statEntrpsNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : processNticIssue
 * @DESCRIPTION   : 시설물 관리비 부과 내역을 고지처리한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.processNticIssue = function() {

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
	if (this.isValidDate(nticDt, true) == false) {
		alert('고지 일자가 부정확합니다.');
		this.$("#nticDt").focus();
		return;
	}
	if (this.isValidFirstDate(nticDt, todayString, true) == false) {
		alert('고지 일자가 현재 일자보다 큽니다.');
		this.$("#nticDt").focus();
		return;
	}
	if (this.isValidDate(payTmlmt, true) == false) {
		alert('납부 기한이 부정확합니다.');
		this.$("#nticDt").focus();
		return;
	}
	if (this.isValidFirstDate(nticDt, payTmlmt, true) == false) {
		alert('고지 일자가 납부 기한보다 큽니다.');
		this.$("#nticDt").focus();
		return;
	}
	if (this.isValidAmount(fee, true) == false) {
		alert('사용료가 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (this.isValidAmount(vat, false) == false) {
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
	if (this.isValidAmount(nticAmt, true) == false) {
		alert('고지 금액이 부정확합니다.');
		this.$("#fee").focus();
		return;
	}
	if (nticAmt != (fee + vat)) {
		alert('고지 금액이 (사용료 + 부가세)와 일치하지 않습니다.');
		this.$("#fee").focus();
		return;
	}
	confirmMessage = "[" + this.$('#chrgeKndNm').val() + "] " + this.$('#fee').val() + "원을 고지하시겠습니까?";
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
		this.doAction('/mngFee/gamProcessFcltsMngFeeMngNticIssue.do', processVO, function(module, result) {
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
 * @DESCRIPTION   : 시설물 관리비 부과 내역을 고지취소한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.cancelNticIssue = function() {

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
	if (this.isValidYear(accnutYear, true) == false) {
		alert('회계 년도가 부정확합니다.');
		return;
	}
	if (this.isValidNticNo(nticNo, true) == false) {
		alert('고지 번호가 부정확합니다.');
		return;
	}
	if (arrrgNo != "") {
		alert('연체 고지 자료가 존재합니다.\r\n연체 고지 취소를 먼저 수행하시길 바랍니다.');
		return;
	}
	confirmMessage = "[" + this.$('#chrgeKndNm').val() + "] " + this.$('#fee').val() + "원을 고지 취소하시겠습니까?";
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
		this.doAction('/mngFee/gamCancelFcltsMngFeeMngNticIssue.do', cancelVO, function(module, result) {
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
GamFcltsMngFeeMngModule.prototype.printNticIssue = function() {

	var row = this.$('#detailGrid').selectedRows()[0];
	if (row == null) {
		alert('자료가 선택되지 않았습니다.');
		return;
	}
	if (row['nhtIsueYn'] != "Y") {
		alert('고지 처리가 완료된 자료가 아닙니다.');
		return;
	}
	this.printPage('/mngFee/gamPrintPreviewFcltsMngFeeMngNoticeIssue.do', row);
	alert("고지서 출력이 완료됐습니다.");
	this.refreshData();

};

<%
/**
 * @FUNCTION NAME : printReport
 * @DESCRIPTION   : 시설물 관리비 내역서를 출력한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.printReport = function() {

	var row = this.$('#detailGrid').selectedRows()[0];
	if (row == null) {
		return;
	}
	this.printPage('/mngFee/gamPrintPreviewFcltsMngFeeMngReport.do', row);

};

<%
/**
 * @FUNCTION NAME : openFcltsFeeMngNticModule
 * @DESCRIPTION   : [시설물 관리비 고지] 화면 MODULE OPEN
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.openFcltsFeeMngNticModule = function() {

	var rows = this.$('#mainGrid').selectedRows();
    var formParams = {};
	if (rows.length==0) {
		return;
	}
	formParams = {
		action: 'selectFcltsFeeMngNtic',
		paramVo:{ mngMtYear: rows[0].mainMngMtYear, mngMtMon: rows[0].mainMngMtMon, mngFeeJobSe: rows[0].mainMngFeeJobSe }
	};
	EMD.util.create_window('시설물 관리비 고지', '/mngFee/gamFcltsFeeMngNtic.do', null, formParams);

};

<%
/**
 * @FUNCTION NAME : openFcltsFeeMngInqireModule
 * @DESCRIPTION   : [시설물 관리비 관리비 납부현황 조회] 화면 MODULE OPEN
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.openFcltsFeeMngInqireModule = function() {

	var rows = this.$('#mainGrid').selectedRows();
    var formParams = {};
	if (rows.length==0) {
		return;
	}
	formParams = {
		action: 'selectFcltsFeeMngInqire',
		paramVo:{ mngMtYear: rows[0].mainMngMtYear, mngMtMon: rows[0].mainMngMtMon, mngFeeJobSe: rows[0].mainMngFeeJobSe }
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
GamFcltsMngFeeMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadFcltsMngFeeMng.do');

};

<%
/**
 * @FUNCTION NAME : downloadExcelDetail
 * @DESCRIPTION   : DETAIL 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.downloadExcelDetail = function() {

	var detailGridRowCount = this.$("#detailGrid").flexRowCount();
	if (detailGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#detailGrid').flexExcelDown('/mngFee/gamExcelDownloadFcltsMngFeeMngDetail.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
			return;
		}
		var nhtIsueYn = row['mainNhtIsueYn'];
		if (nhtIsueYn == "Y") {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngNtic').enable();
			this.$('#btnOpenFcltsFeeMngNtic').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngInqire').enable();
			this.$('#btnOpenFcltsFeeMngInqire').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "N") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngNtic').enable();
			this.$('#btnOpenFcltsFeeMngNtic').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngInqire').enable();
			this.$('#btnOpenFcltsFeeMngInqire').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "X") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
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
GamFcltsMngFeeMngModule.prototype.enableDetailInputItem = function() {

	var nhtIsueYn = this.$('#mainNhtIsueYn').val();
	if (this._mode == "insert") {
		this.$('#mainMngMtYear').enable();
		this.$('#mainMngMtMon').enable();
		this.$('#mainMngFeeJobSe').enable();
		this.$('#mainMngFeeSj').enable();
		this.$('#mainFcltyMngFee').enable();
		this.$('#mainElctyFee').enable();
		this.$('#mainWaterFee').enable();
		this.$('#mainGasFee').enable();
		this.$('#mainEnvFee').enable();
		this.$('#btnSaveMain').enable();
		this.$('#btnSaveMain').removeClass('ui-state-disabled');
		this.$('#btnDeleteMain').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngNtic2').disable({disableClass:"ui-state-disabled"});
		this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});
	} else {
		if (nhtIsueYn == "Y") {
			this.$('#mainMngMtYear').disable();
			this.$('#mainMngMtMon').disable();
			this.$('#mainMngFeeJobSe').disable();
			this.$('#mainMngFeeSj').disable();
			this.$('#mainFcltyMngFee').disable();
			this.$('#mainElctyFee').disable();
			this.$('#mainWaterFee').disable();
			this.$('#mainGasFee').disable();
			this.$('#mainEnvFee').disable();
			this.$('#btnSaveMain').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteMain').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngNtic2').enable();
			this.$('#btnOpenFcltsFeeMngNtic2').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngInqire2').enable();
			this.$('#btnOpenFcltsFeeMngInqire2').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "N") {
			this.$('#mainMngMtYear').disable();
			this.$('#mainMngMtMon').disable();
			this.$('#mainMngFeeJobSe').disable();
			this.$('#mainMngFeeSj').disable();
			this.$('#mainFcltyMngFee').disable();
			this.$('#mainElctyFee').disable();
			this.$('#mainWaterFee').disable();
			this.$('#mainGasFee').disable();
			this.$('#mainEnvFee').disable();
			this.$('#btnSaveMain').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteMain').enable();
			this.$('#btnDeleteMain').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngNtic2').enable();
			this.$('#btnOpenFcltsFeeMngNtic2').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngInqire2').enable();
			this.$('#btnOpenFcltsFeeMngInqire2').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "X") {
			this.$('#mainMngMtYear').enable();
			this.$('#mainMngMtMon').enable();
			this.$('#mainMngFeeJobSe').enable();
			this.$('#mainMngFeeSj').enable();
			this.$('#mainFcltyMngFee').enable();
			this.$('#mainElctyFee').enable();
			this.$('#mainWaterFee').enable();
			this.$('#mainGasFee').enable();
			this.$('#mainEnvFee').enable();
			this.$('#btnSaveMain').enable();
			this.$('#btnSaveMain').removeClass('ui-state-disabled');
			this.$('#btnDeleteMain').enable();
			this.$('#btnDeleteMain').removeClass('ui-state-disabled');
			this.$('#btnOpenFcltsFeeMngNtic2').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#mainMngMtYear').disable();
			this.$('#mainMngMtMon').disable();
			this.$('#mainMngFeeJobSe').disable();
			this.$('#mainMngFeeSj').disable();
			this.$('#mainFcltyMngFee').disable();
			this.$('#mainElctyFee').disable();
			this.$('#mainWaterFee').disable();
			this.$('#mainGasFee').disable();
			this.$('#mainEnvFee').disable();
			this.$('#btnSaveMain').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteMain').disable({disableClass:"ui-state-disabled"});
			this.$('#btnOpenFcltsFeeMngNtic2').disable({disableClass:"ui-state-disabled"});
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
GamFcltsMngFeeMngModule.prototype.disableDetailInputItem = function() {

	this.$('#mainMngMtYear').disable();
	this.$('#mainMngMtMon').disable();
	this.$('#mainMngFeeJobSe').disable();
	this.$('#mainMngFeeSj').disable();
	this.$('#mainFcltyMngFee').disable();
	this.$('#mainElctyFee').disable();
	this.$('#mainWaterFee').disable();
	this.$('#mainGasFee').disable();
	this.$('#mainEnvFee').disable();
	this.$('#btnSaveMain').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDeleteMain').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngNtic2').disable({disableClass:"ui-state-disabled"});
	this.$('#btnOpenFcltsFeeMngInqire2').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableSubDetailInputItem
 * @DESCRIPTION   : SUB DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.enableSubDetailInputItem = function() {

	var nhtIsueYn = this.$('#nhtIsueYn').val();
	if (this._detailmode == "insert") {
		this.$('#usageAr').enable();
		this.$('#popupDataEntrpscd').enable();
		this.$('#popupDataEntrpscd').removeClass('ui-state-disabled');
		this.$('#mngFee').enable();
		this.$('#elctyFee').enable();
		this.$('#waterFee').enable();
		this.$('#gasFee').enable();
		this.$('#envFee').enable();
		this.$('#nticDt').disable();
		this.$('#payTmlmt').disable();
		this.$('#rm').disable();
		this.$('#chrgeKnd').enable();
		this.$('#btnAddDetail').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveDetail').enable();
		this.$('#btnSaveDetail').removeClass('ui-state-disabled');
		this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
		this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrintReport').disable({disableClass:"ui-state-disabled"});
	} else {
		if (nhtIsueYn == "Y") {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
			this.$('#nticDt').disable();
			this.$('#payTmlmt').disable();
			this.$('#rm').disable();
			this.$('#chrgeKnd').disable();
			this.$('#btnAddDetail').enable();
			this.$('#btnAddDetail').removeClass('ui-state-disabled');
			this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').enable();
			this.$('#btnCancelNticIssue').removeClass('ui-state-disabled');
			this.$('#btnPrintNticIssue').enable();
			this.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
			this.$('#btnPrintReport').enable();
			this.$('#btnPrintReport').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "N") {
			this.$('#usageAr').enable();
			this.$('#popupDataEntrpscd').enable();
			this.$('#popupDataEntrpscd').removeClass('ui-state-disabled');
			this.$('#mngFee').enable();
			this.$('#elctyFee').enable();
			this.$('#waterFee').enable();
			this.$('#gasFee').enable();
			this.$('#envFee').enable();
			this.$('#nticDt').enable();
			this.$('#payTmlmt').enable();
			this.$('#rm').enable();
			this.$('#chrgeKnd').enable();
			this.$('#btnAddDetail').enable();
			this.$('#btnAddDetail').removeClass('ui-state-disabled');
			this.$('#btnSaveDetail').enable();
			this.$('#btnSaveDetail').removeClass('ui-state-disabled');
			this.$('#btnDeleteDetail').enable();
			this.$('#btnDeleteDetail').removeClass('ui-state-disabled');
			this.$('#btnProcessNticIssue').enable();
			this.$('#btnProcessNticIssue').removeClass('ui-state-disabled');
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintReport').disable({disableClass:"ui-state-disabled"});
		} else if (nhtIsueYn == "X") {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
			this.$('#nticDt').disable();
			this.$('#payTmlmt').disable();
			this.$('#rm').disable();
			this.$('#chrgeKnd').disable();
			this.$('#btnAddDetail').enable();
			this.$('#btnAddDetail').removeClass('ui-state-disabled');
			this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintReport').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
			this.$('#nticDt').disable();
			this.$('#payTmlmt').disable();
			this.$('#rm').disable();
			this.$('#chrgeKnd').disable();
			if (this.$('#mainMngMt').val() != "" && this.$('#mainMngFeeJobSe').val() != "") {
				this.$('#btnAddDetail').enable();
				this.$('#btnAddDetail').removeClass('ui-state-disabled');
			} else {
				this.$('#btnAddDetail').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintReport').disable({disableClass:"ui-state-disabled"});
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
GamFcltsMngFeeMngModule.prototype.disableSubDetailInputItem = function() {

	this.$('#usageAr').disable();
	this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
	this.$('#mngFee').disable();
	this.$('#elctyFee').disable();
	this.$('#waterFee').disable();
	this.$('#gasFee').disable();
	this.$('#envFee').disable();
	this.$('#nticDt').disable();
	this.$('#payTmlmt').disable();
	this.$('#rm').disable();
	this.$('#chrgeKnd').disable();
	this.$('#btnAddDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintReport').disable({disableClass:"ui-state-disabled"});

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
GamFcltsMngFeeMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
				this.enableSubDetailInputItem();
			} else if (this._mode=="insert") {
				this._mainKeyValue = '';
				this._detailKeyValue = '';
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.$('#detailGrid').flexEmptyData();
				this.makeFormValues('#subDetailForm', {});
				this.makeDivValues('#subDetailForm', {});
				this.disableSubDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.$('#detailGrid').flexEmptyData();
				this.makeFormValues('#subDetailForm', {});
				this.makeDivValues('#subDetailForm', {});
				this.disableSubDetailInputItem();
			}
			break;
		case 'statTab':
			this.$('#statMngFeeJobSe').val(this.$('#sMngFeeJobSe').val());
			break;
	}

};

var module_instance = new GamFcltsMngFeeMngModule();

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
                            <th style="width:10%; height:18;">관　리　기　간</th>
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
							<th style="width:10%; height:18;">업　무　구　분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
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
				<li><a href="#listTab" class="emdTab">시설물 관리비 현황</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리비 내역</a></li>
				<li><a href="#statTab" class="emdTab">시설물 관리비 통계</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">총관리용역</th>
								<td><input type="text" size="13" id="sumFcltyMngFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총전기요금</th>
								<td><input type="text" size="13" id="sumElctyFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총상하수도</th>
								<td><input type="text" size="13" id="sumWaterFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총도시가스</th>
								<td><input type="text" size="13" id="sumGasFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총환경개선</th>
								<td><input type="text" size="13" id="sumEnvFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총관리비합</th>
								<td><input type="text" size="13" id="sumMngTotalFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<th style="width=10%; height=20; text-align:center;">자료수</th>
							<td>
								<input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled" />
							</td>
							<td style="text-align:right;">
								<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
								<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
								<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								<button id="btnExcelUpload" class="buttonExcel">엑셀　　업로드</button>
								<button id="btnCopy">이전월　자료복사</button>
								<button id="btnOpenFcltsFeeMngNtic">고지내역　조회</button>
								<button id="btnOpenFcltsFeeMngInqire">납부현황　조회</button>
							</td>
					</table>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 관리 내역</th>
								<td style="text-align:right;">
									<button id="btnSaveMain" class="buttonSave">관리내역 저장</button>
									<button id="btnDeleteMain" class="buttonDelete">관리내역 삭제</button>
									<button id="btnOpenFcltsFeeMngNtic2">고지내역　조회</button>
									<button id="btnOpenFcltsFeeMngInqire2">납부현황　조회</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18;">관　리　년　월</th>
								<td>
									<input id="mainMngMt" type="hidden"/>
									<select id="mainMngMtYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									<select id="mainMngMtMon" class='selt'>
										<option value="">선택</option>
										<option value="01">01월</option>
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
								<th style="width:10%; height:18;">업　무　구　분</th>
								<td>
									<input id="mainMngFeeJobSeNm" type="hidden"/>
									<select id="mainMngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
								<th style="width:10%; height:18;">수　　정　　자</th>
								<td>
									<input id="mainNhtIsueYn" type="hidden"/>
									<input type="text" size="9" id="mainUpdUsr" disabled>
									<input type="text" size="22" id="mainUpdtDt" disabled>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:18;">관리비　　제목</th>
								<td colspan="5"><input type="text" size="150" id="mainMngFeeSj" disabled/></td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">시설관리용역비</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainFcltyMngFee" disabled/> 원</td>
								<th style="width:10%; height:18;">전　기　요　금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainElctyFee" disabled/> 원</td>
								<th style="width:10%; height:18;">상하수도　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainWaterFee" disabled/> 원</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">도시가스　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainGasFee" disabled/> 원</td>
								<th style="width:10%; height:18;">환경개선부담금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainEnvFee" disabled/> 원</td>
								<th style="width:10%; height:18;">관리비　　합계</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mainMngTotalFee" disabled/> 원</td>
                            </tr>
						</table>
					</form>
					<form id="subDetailForm">
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 상세 내역</th>
								<td style="text-align:right;">
									<button id="btnAddDetail" class="buttonAdd">상세　추가</button>
									<button id="btnSaveDetail" class="buttonSave">상세　저장</button>
									<button id="btnDeleteDetail" class="buttonDelete">상세　삭제</button>
									<button id="btnProcessNticIssue">고　　　지</button>
									<button id="btnCancelNticIssue">고지　취소</button>
									<button id="btnPrintNticIssue">고지서 출력</button>
									<button id="btnPrintReport">내역서 출력</button>
									<button id="btnExcelDownloadDetail" class="buttonExcel">엑셀 다운로드</button>
								</td>
							</tr>
						</table>
						<table id="detailGrid" style="display:none;"></table>
						<table class="detailPanel" style="width:100%;">
                            <tr>
								<th style="width:10%; height:18;">순번／사용면적</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<input id="mngFeeJobSe" type="hidden"/>
									<input id="reqestSeq" type="hidden"/>
									<input type="text" size="5" id="mngSeq" disabled/> ／
									<input type="text" size="20" class="ygpaNumber" id="usageAr" disabled/> m<sup>2</sup>
								</td>
								<th style="width:10%; height:18;">부　과　업　체</th>
								<td>
									<input id="entrpscd" type="hidden"/>
									<input type="text" size="22" id="entrpsNm" disabled>
									<button id="popupDataEntrpscd" class="popupButton">선택</button>
								</td>
								<th style="width:10%; height:18;">고지/출력/요금</th>
								<td>
									<input id="setoffYn" type="hidden"/>
									<input id="nticMth" type="hidden"/>
									<input id="chrgeKndNm" type="hidden"/>
									<input id="aditNticYn" type="hidden"/>
									<input type="text" size="1" id="nhtIsueYn" disabled>/
									<input type="text" size="1" id="nhtPrintYn" disabled>/
									<input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM024" disabled/>
								</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">관　　리　　비</th>
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
                            <tr>
								<th style="width:10%; height:18;">고지／납부기한</th>
								<td>
                                	<input type="text" size="14" id="nticDt"  class="emdcal" disabled/> ／
                                	<input type="text" size="14" id="payTmlmt" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18;">사용료／부가세</th>
								<td>
									<input type="text" size="12" class="ygpaNumber" id="fee" disabled> 원 /
									<input type="text" size="12" class="ygpaNumber" id="vat" disabled> 원
								</td>
								<th style="width:10%; height:18;">고　지　금　액</th>
								<td>
									<input type="text" size="19" class="ygpaNumber" id="nticAmt" disabled> 원
									<input type="text" size="9" id="arrrgSttus" style="font-weight:bold; background:yellow; text-align:center;" disabled>
								</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">비　　　　　고</th>
								<td>
									<input type="text" size="33" id="rm" disabled/>
								</td>
								<th style="width:10%; height:18;">회　계　정　보</th>
								<td>
									<input id="prtAtCode" type="hidden"/>
									<input id="vatYn" type="hidden"/>
									<input type="text" size="18" id="vatYnNm" disabled/>
									<input type="text" size="4" id="accnutYear" disabled>
									<input type="text" size="7" id="nticNo" disabled>
								</td>
								<th style="width:10%; height:18;">수　납　정　보</th>
								<td>
									<input id="arrrgNo" type="hidden"/>
									<input id="arrrgPayDates" type="hidden"/>
									<input id="arrrgAmt" type="hidden"/>
									<input id="rcivSe" type="hidden"/>
									<input type="text" size="14" id="rcivSeNm" disabled> ／
                                	<input type="text" size="14" id="rcivDt" disabled/>
								</td>
                            </tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 3 AREA (STAT) -->
			<div id="statTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="statForm">
						<table style="width:100%;" class="searchPanel">
							<tr>
								<th style="width:10%; height:18;">관　리　년　월</th>
								<td >
									<select id="statMngMtYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									<select id="statMngMtMon" class='selt'>
										<option value="">선택</option>
										<option value="01">01월</option>
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
									<select id="statTypeSe">
										<option value="M" selected>월별</option>
										<option value="E">업체별</option>
									</select>
								</td>
								<th style="width:10%; height:18;">업　무　구　분</th>
								<td >
									<select id="statMngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
								<td rowspan="2">
									<button id="btnStatSearch">통　계　조　회</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18;">부　과　업　체</th>
								<td>
									<input id="statEntrpscd" type="text" size="10">&nbsp; &nbsp;
									<input id="statEntrpsNm" type="text" size="30" disabled="disabled">
									<button id="popupStatEntrpscd" class="popupButton">선택</button>
								</td>
								<th style="width:10%; height:18;">조　회　구　분</th>
								<td >
									<select id="statMngFeeSe">
										<option value="M">관리비</option>
										<option value="E">전기 요금</option>
										<option value="W">상하수도 요금</option>
										<option value="G">도시가스 요금</option>
										<option value="I">환경개선 부담금</option>
										<option value="T" selected>관리비 합계</option>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="9" style="padding-left:4px;">
									<div id="fcltyMngFeeMngSttusChart" style="width:955px;height:420px;border:1px solid #A4BED4;"></div>
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
