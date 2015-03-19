<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamElctyUsageQyMng.jsp
 * @Description : 전기 사용 량 관리
 * @Modification Information
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * author ACEWOLF
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
 * @FUNCTION NAME : GamElctyUsageQyMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamElctyUsageQyMngModule() {}

GamElctyUsageQyMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectElctyUsageQyMngList.do',
		dataType : 'json',
		colModel : [
					{display:'사용 년도',		name:'usageYr',			width:60, 		sortable:false,		align:'center'},
					{display:'시설 명',			name:'mngFeeFcltyNm',	width:180, 		sortable:false,		align:'left'},
					{display:'전력 사용 량',	name:'usageQy',			width:100, 		sortable:false,		align:'right'},
					{display:'전기 요금',		name:'usageAmt',		width:100, 		sortable:false,		align:'right'},
					{display:'PEEK 사용 량',	name:'peekQy',			width:100, 		sortable:false,		align:'right'},
					{display:'01월 사용 량',	name:'mt01Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'02월 사용 량',	name:'mt02Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'03월 사용 량',	name:'mt03Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'04월 사용 량',	name:'mt04Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'05월 사용 량',	name:'mt05Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'06월 사용 량',	name:'mt06Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'07월 사용 량',	name:'mt07Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'08월 사용 량',	name:'mt08Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'09월 사용 량',	name:'mt09Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'10월 사용 량',	name:'mt10Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'11월 사용 량',	name:'mt11Qy',			width:100, 		sortable:false,		align:'right'},
					{display:'12월 사용 량',	name:'mt12Qy',			width:100, 		sortable:false,		align:'right'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumUsageQy').val($.number(data.sumUsageQy));
			module.$('#sumUsageAmt').val($.number(data.sumUsageAmt));
			module.$('#sumPeekQy').val($.number(data.sumPeekQy));
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.usageYr + row.mngFeeJobSe;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.usageYr + row.mngFeeJobSe;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#chartValueSe').on('change',{module:this}, function(event){
		event.data.module.saveChartValueSe();
	});

	this._mainmode = "";
	this._mainKeyValue = "";
	this._chartValueSe = "M";
	this._searchButtonClick = false;
	var year = new Date().getFullYear() - 1;
	this.$('#sStartUsageYear').val(year);
	this.$('#sEndUsageYear').val(year);
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

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
GamElctyUsageQyMngModule.prototype.isValidYear = function(yearString, nullCheckFlag) {

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
 * @FUNCTION NAME : isValidFirstYear
 * @DESCRIPTION   : FIRST YEAR STRING > SECOND YEAR STRING을 검사한다.
 * @PARAMETER     :
 *   1. firstYearString - FIRST YEAR STRING
 *   2. secondYearString - SECOND YEAR STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamElctyUsageQyMngModule.prototype.isValidFirstYear = function(firstYearString, secondYearString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (firstYearString == "" || secondYearString == "") {
			return false;
		}
	} else {
		if (firstYearString == "" || secondYearString == "") {
			return true;
		}
	}
	var firstYear = Number(firstYearString);
	var secondYear = Number(secondYearString);
	if (firstYear > secondYear) {
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
GamElctyUsageQyMngModule.prototype.isValidAmount = function(amountValue) {

	if (amountValue > 9999999999999999 || amountValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidNumber12P2
 * @DESCRIPTION   : NUMBER에 대한 VALIDATION을 검사한다. (소수점이상 10자리, 소수점이하 2자리)
 * @PARAMETER     :
 *   1. numValue - NUMBER VALUE
**/
%>
GamElctyUsageQyMngModule.prototype.isValidNumber12P2 = function(numValue) {

	if (numValue > 9999999999.99 || numValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : saveChartValueSe
 * @DESCRIPTION   : 그래프 형태를 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.saveChartValueSe = function() {

	var chartValueSe = this.$('#chartValueSe').val();
	if (chartValueSe != "") {
		this._chartValueSe = chartValueSe;
	}

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.drawChart = function() {
console.log("drawChart");
	var dataValueArr = [];
	var maxDataValue = 0;
	var dataValue = 0;
	var chartValueSe = this._chartValueSe;
	var chartValueNm = "";
	var usageYr = Number(this.$('#sEndUsageYear').val());
	var searchVO = this.makeFormArgs("#detailForm");
	this.$('#chartValueSe').val(this._chartValueSe);
	if (chartValueSe == "A") {
		chartValueNm = "전기 요금";
	} else if (chartValueSe == "P") {
		chartValueNm = "PEEK 사용량";
	} else {
		chartValueNm = "전력 사용량";
	}
	if (chartValueSe == "M") {
		this.doAction('/mngFee/gamSelectElctyUsageQyMngMonthChart.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				for (var i=0; i<12; i++) {
					usageYr = i + 1;
					switch (i) {
						case  0 : dataValue = result.resultList[0]['mt01Qy']*1;	break;
						case  1 : dataValue = result.resultList[0]['mt02Qy']*1;	break;
						case  2 : dataValue = result.resultList[0]['mt03Qy']*1;	break;
						case  3 : dataValue = result.resultList[0]['mt04Qy']*1;	break;
						case  4 : dataValue = result.resultList[0]['mt05Qy']*1;	break;
						case  5 : dataValue = result.resultList[0]['mt06Qy']*1;	break;
						case  6 : dataValue = result.resultList[0]['mt07Qy']*1;	break;
						case  7 : dataValue = result.resultList[0]['mt08Qy']*1;	break;
						case  8 : dataValue = result.resultList[0]['mt09Qy']*1;	break;
						case  9 : dataValue = result.resultList[0]['mt10Qy']*1;	break;
						case 10 : dataValue = result.resultList[0]['mt11Qy']*1;	break;
						case 11 : dataValue = result.resultList[0]['mt12Qy']*1;	break;
						default : dataValue = 0;								break;
					}
					dataValueArr[i] = { year : usageYr, gauge: dataValue };
					if (maxDataValue < dataValue) {
						maxDataValue = dataValue;
					}
				};
			} else {
				for (var i=0; i<12; i++) {
					dataValue = 0;
					dataValueArr[i] = { year : i + 1, gauge: dataValue };
				};
			}
			if (maxDataValue < 10) {
				maxDataValue = 10;
			}
			if (module.barChart == null) {
				module.barChart = new dhtmlXChart({
					view			: "bar",
					container		: module.$('#elctyUsageQyChart')[0],
					value			: "#gauge#",
					color			: "#000BE0",
		            gradient		: "rising",
					width			: 30,
					label			: "#gauge#",
					tooltip			: "#gauge# kw/h",
					xAxis			: {
						title 		: chartValueNm,
						template	: "#year#"
					},
					yAxis			: {
						start		: 0,
						end			: maxDataValue + 10,
						step		: Math.ceil(maxDataValue / 10),
						title		: chartValueNm
					}
				});
			} else {
				module.barChart.clearAll();
				module.barChart.define("yAxis", {
					start           : 0,
					end             : maxDataValue + 10,
					step            : Math.ceil(maxDataValue / 10),
					title           : chartValueNm
				});
			}
			module.barChart.parse(dataValueArr, "json");
			module.barChart.refresh();
		});
	} else {
		this.doAction('/mngFee/gamSelectElctyUsageQyMngChart.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				for (var i=0; i<12; i++) {
					usageYr = result.resultList[i]['usageYr']*1;
					if (chartValueSe == "A") {
						dataValue = result.resultList[i]['usageAmt']*1;
					} else if (chartValueSe == "P") {
						dataValue = result.resultList[i]['peekQy']*1;
					} else {
						dataValue = result.resultList[i]['usageQy']*1;
					}
					dataValueArr[i] = { year : usageYr, gauge: dataValue };
					if (maxDataValue < dataValue) {
						maxDataValue = dataValue;
					}
				};
			} else {
				for (var i=0; i<12; i++) {
					dataValue = 0;
					dataValueArr[i] = { year : usageYr - 12 - i, gauge: dataValue };
				};
			}
			if (maxDataValue < 10) {
				maxDataValue = 10;
			}
			if (module.barChart == null) {
				module.barChart = new dhtmlXChart({
					view			: "bar",
					container		: module.$('#elctyUsageQyChart')[0],
					value			: "#gauge#",
					color			: "#000BE0",
		            gradient		: "rising",
					width			: 30,
					label			: "#gauge#",
					tooltip			: "#gauge# kw/h",
					xAxis			: {
						title 		: chartValueNm,
						template	: "#year#"
					},
					yAxis			: {
						start		: 0,
						end			: maxDataValue + 10,
						step		: Math.ceil(maxDataValue / 10),
						title		: chartValueNm
					}
				});
			} else {
				module.barChart.clearAll();
				module.barChart.define("yAxis", {
					start           : 0,
					end             : maxDataValue + 10,
					step            : Math.ceil(maxDataValue / 10),
					title           : chartValueNm
				});
			}
			module.barChart.parse(dataValueArr, "json");
			module.barChart.refresh();
		});
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
GamElctyUsageQyMngModule.prototype.onButtonClick = function(buttonId) {

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
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnRemove':
			this.deleteData();
			break;
		case 'btnCopy':
			this.copyData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'btnChartSearch':
			this.drawChart();
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
GamElctyUsageQyMngModule.prototype.onSubmit = function() {

	var sStartUsageYear = this.$('#sStartUsageYear').val();
	var sEndUsageYear = this.$('#sEndUsageYear').val();
	if (this.isValidYear(sStartUsageYear, true) == false) {
		alert('시작 사용 년도가 부정확합니다.');
		this.$("#sStartUsageYear").focus();
		return;
	}
	if (this.isValidYear(sEndUsageYear, true) == false) {
		alert('종료 사용 년도가 부정확합니다.');
		this.$("#sEndUsageYear").focus();
		return;
	}
	if (this.isValidFirstYear(sStartUsageYear, sEndUsageYear, true) == false) {
		alert('시작 사용 년도가 종료 사용 년도보다 큽니다.');
		this.$("#sEndUsageYear").focus();
		return;
	}
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
GamElctyUsageQyMngModule.prototype.loadData = function() {

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
 GamElctyUsageQyMngModule.prototype.refreshData = function() {

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
GamElctyUsageQyMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectElctyUsageQyMngPk.do', searchVO, function(module, result){
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
GamElctyUsageQyMngModule.prototype.selectData = function() {

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
	var mngFeeFcltyCd = this._mainKeyValue.substring(0,4);
	var usageYr = this._mainKeyValue.substring(4,8);
	var mngFeeJobSe = this._mainKeyValue.substring(8,9);
	this.$("#mainGrid").selectFilterRow([{col:"usageYr", filter:usageYr},
										 {col:"mngFeeJobSe", filter:mngFeeJobSe},
										 {col:"mngFeeFcltyCd", filter:mngFeeFcltyCd}]);
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.drawChart();

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.addData = function() {

	var sEndUsageYear = this.$('#sEndUsageYear').val();
	var mngFeeJobSe = "E";
	var mngFeeJobSeNm = "[E]:전기시설";
	var mngFeeFcltyCd = this.$('#sMngFeeFcltyCd').val();
	var mngFeeFcltyNm = this.$('#sMngFeeFcltyCd_select').find('option:selected').text();
	this.$('#usageYr').val(sEndUsageYear);
	this.$('#mngFeeJobSe').val(mngFeeJobSe);
	this.$('#mngFeeJobSeNm').val(mngFeeJobSeNm);
	this.$('#mngFeeFcltyCd').val(mngFeeFcltyCd);
	this.$('#mngFeeFcltyNm').val(mngFeeFcltyNm);
	this.$('#usageQy').val("0.00");
	this.$('#usageAmt').val("0");
	this.$('#peekQy').val("0.00");
	this.$('#mt01Qy').val("0.00");
	this.$('#mt02Qy').val("0.00");
	this.$('#mt03Qy').val("0.00");
	this.$('#mt04Qy').val("0.00");
	this.$('#mt05Qy').val("0.00");
	this.$('#mt06Qy').val("0.00");
	this.$('#mt07Qy').val("0.00");
	this.$('#mt08Qy').val("0.00");
	this.$('#mt09Qy').val("0.00");
	this.$('#mt10Qy').val("0.00");
	this.$('#mt11Qy').val("0.00");
	this.$('#mt12Qy').val("0.00");
	this.enableDetailInputItem();
	this.$('#mngFeeFcltyCd').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var usageYr = this.$('#usageYr').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	var usageQy = Number(this.$('#usageQy').val().replace(/,/gi, ""));
	var usageAmt = Number(this.$('#usageAmt').val().replace(/,/gi, ""));
	var peekQy = Number(this.$('#peekQy').val().replace(/,/gi, ""));
	var mt01Qy = this.$('#mt01Qy').val();
	var mt02Qy = this.$('#mt02Qy').val();
	var mt03Qy = this.$('#mt03Qy').val();
	var mt04Qy = this.$('#mt04Qy').val();
	var mt05Qy = this.$('#mt05Qy').val();
	var mt06Qy = this.$('#mt06Qy').val();
	var mt07Qy = this.$('#mt07Qy').val();
	var mt08Qy = this.$('#mt08Qy').val();
	var mt09Qy = this.$('#mt09Qy').val();
	var mt10Qy = this.$('#mt10Qy').val();
	var mt11Qy = this.$('#mt11Qy').val();
	var mt12Qy = this.$('#mt12Qy').val();
	if (this.isValidYear(usageYr, true) == false) {
		alert('사용 년도가 부정확합니다.');
		this.$("#usageYr").focus();
		return;
	}
	if (mngFeeFcltyCd == "" || mngFeeFcltyCd.length != 4) {
		alert('시설 코드가 부정확합니다.');
		this.$("#mngFeeFcltyCd").focus();
		return;
	}
	if (this.isValidNumber12P2(usageQy) == false) {
		alert('전력 사용 량이 부정확합니다.');
		this.$("#usageQy").focus();
		return;
	}
	if (this.isValidAmount(usageAmt) == false) {
		alert('전기 요금이 부정확합니다.');
		this.$("#usageAmt").focus();
		return;
	}
	if (this.isValidNumber12P2(peekQy) == false) {
		alert('전력 사용 량이 부정확합니다.');
		this.$("#peekQy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt01Qy) == false) {
		alert('01월 사용 량이 부정확합니다.');
		this.$("#mt01Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt02Qy) == false) {
		alert('02월 사용 량이 부정확합니다.');
		this.$("#mt02Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt03Qy) == false) {
		alert('03월 사용 량이 부정확합니다.');
		this.$("#mt03Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt04Qy) == false) {
		alert('04월 사용 량이 부정확합니다.');
		this.$("#mt04Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt05Qy) == false) {
		alert('05월 사용 량이 부정확합니다.');
		this.$("#mt05Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt06Qy) == false) {
		alert('06월 사용 량이 부정확합니다.');
		this.$("#mt06Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt07Qy) == false) {
		alert('07월 사용 량이 부정확합니다.');
		this.$("#mt07Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt08Qy) == false) {
		alert('08월 사용 량이 부정확합니다.');
		this.$("#mt08Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt09Qy) == false) {
		alert('09월 사용 량이 부정확합니다.');
		this.$("#mt09Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt10Qy) == false) {
		alert('10월 사용 량이 부정확합니다.');
		this.$("#mt10Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt11Qy) == false) {
		alert('11월 사용 량이 부정확합니다.');
		this.$("#mt11Qy").focus();
		return;
	}
	if (this.isValidNumber12P2(mt12Qy) == false) {
		alert('12월 사용 량이 부정확합니다.');
		this.$("#mt12Qy").focus();
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = mngFeeFcltyCd + usageYr + mngFeeJobSe;
		this.doAction('/mngFee/gamInsertElctyUsageQyMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateElctyUsageQyMng.do', inputVO, function(module, result) {
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
GamElctyUsageQyMngModule.prototype.deleteData = function() {

	var usageYr = this.$('#usageYr').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	if (usageYr == "") {
		alert('사용 년도가 부정확합니다.');
		this.$("#usageYr").focus();
		return;
	}
	if (mngFeeFcltyCd == "") {
		alert('시설 코드가 부정확합니다.');
		this.$("#mngFeeFcltyCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteElctyUsageQyMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : 월별 항목을 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryUsageYear = this.$('#sEndUsageYear').val();
	var yrCnt=0;
	if (this.isValidYear(sQueryUsageYear, true) == false) {
		alert('종료 사용 년도가 부정확합니다.');
		this.$("#sEndUsageYear").focus();
		return;
	}
	if (confirm("이전 년도의 자료를 [" + sQueryUsageYear + "년] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectElctyUsageQyMngYearCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		yrCnt=result.resultList[0]['yrCnt']*1;
		if (yrCnt > 0) {
			alert("[" + sQueryUsageYear + "년] 자료가 존재합니다.");
			return;
		}
		module.doAction('/mngFee/gamCopyElctyUsageQyMng.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainmode = 'query';
				module._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	});

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadElctyUsageQyMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageQyMngModule.prototype.enableListButtonItem = function() {

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
GamElctyUsageQyMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#usageYr').enable();
		this.$('#mngFeeFcltyCd').enable();
		this.$('#usageQy').enable();
		this.$('#usageAmt').enable();
		this.$('#peekQy').enable();
		this.$('#mt01Qy').enable();
		this.$('#mt02Qy').enable();
		this.$('#mt03Qy').enable();
		this.$('#mt04Qy').enable();
		this.$('#mt05Qy').enable();
		this.$('#mt06Qy').enable();
		this.$('#mt07Qy').enable();
		this.$('#mt08Qy').enable();
		this.$('#mt09Qy').enable();
		this.$('#mt10Qy').enable();
		this.$('#mt11Qy').enable();
		this.$('#mt12Qy').enable();
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#chartValueSe').disable();
		this.$('#btnChartSearch').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#usageYr').disable();
			this.$('#mngFeeFcltyCd').disable();
			this.$('#usageQy').enable();
			this.$('#usageAmt').enable();
			this.$('#peekQy').enable();
			this.$('#mt01Qy').enable();
			this.$('#mt02Qy').enable();
			this.$('#mt03Qy').enable();
			this.$('#mt04Qy').enable();
			this.$('#mt05Qy').enable();
			this.$('#mt06Qy').enable();
			this.$('#mt07Qy').enable();
			this.$('#mt08Qy').enable();
			this.$('#mt09Qy').enable();
			this.$('#mt10Qy').enable();
			this.$('#mt11Qy').enable();
			this.$('#mt12Qy').enable();
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
			this.$('#chartValueSe').enable();
			this.$('#btnChartSearch').enable();
			this.$('#btnChartSearch').removeClass('ui-state-disabled');
		} else {
			this.$('#usageYr').disable();
			this.$('#mngFeeFcltyCd').disable();
			this.$('#usageQy').disable();
			this.$('#usageAmt').disable();
			this.$('#peekQy').disable();
			this.$('#mt01Qy').disable();
			this.$('#mt02Qy').disable();
			this.$('#mt03Qy').disable();
			this.$('#mt04Qy').disable();
			this.$('#mt05Qy').disable();
			this.$('#mt06Qy').disable();
			this.$('#mt07Qy').disable();
			this.$('#mt08Qy').disable();
			this.$('#mt09Qy').disable();
			this.$('#mt10Qy').disable();
			this.$('#mt11Qy').disable();
			this.$('#mt12Qy').disable();
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#chartValueSe').disable();
			this.$('#btnChartSearch').disable({disableClass:"ui-state-disabled"});
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
GamElctyUsageQyMngModule.prototype.disableDetailInputItem = function() {

	this.$('#usageYr').disable();
	this.$('#mngFeeFcltyCd').disable();
	this.$('#usageQy').disable();
	this.$('#usageAmt').disable();
	this.$('#peekQy').disable();
	this.$('#mt01Qy').disable();
	this.$('#mt02Qy').disable();
	this.$('#mt03Qy').disable();
	this.$('#mt04Qy').disable();
	this.$('#mt05Qy').disable();
	this.$('#mt06Qy').disable();
	this.$('#mt07Qy').disable();
	this.$('#mt08Qy').disable();
	this.$('#mt09Qy').disable();
	this.$('#mt10Qy').disable();
	this.$('#mt11Qy').disable();
	this.$('#mt12Qy').disable();
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#chartValueSe').disable();
	this.$('#btnChartSearch').disable({disableClass:"ui-state-disabled"});

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
GamElctyUsageQyMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mainmode=="insert") {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			this.drawChart();
			break;
	}

};

var module_instance = new GamElctyUsageQyMngModule();

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
							<th>사용 기간</th>
							<td>
								<select id="sStartUsageYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
								&nbsp; ∼  &nbsp;
								<select id="sEndUsageYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
							</td>
							<th>사용 시설</th>
							<td>
								<select id="sMngFeeFcltyCd">
									<option value="" selected="selected">전체</option>
									<c:forEach  items="${mngFeeFcltyCdList}" var="mngFeeFcltyCdItem">
										<option value="${mngFeeFcltyCdItem.mngFeeFcltyCd}">${mngFeeFcltyCdItem.mngFeeFcltyNm}</option>
									</c:forEach>
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
				<li><a href="#listTab" class="emdTab">전기 사용 량</a></li>
				<li><a href="#detailTab" class="emdTab">전기 사용 량 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:5%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">사용량 합계</th>
								<td><input type="text" size="18" id="sumUsageQy" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">요금 합계</th>
								<td><input type="text" size="18" id="sumUsageAmt" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">추가</button>
									<button id="btnDelete" class="buttonDelete">삭제</button>
									<button id="btnCopy">자료복사</button>
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
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">사　용　년　도</th>
								<td>
									<select id="usageYr" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
								</td>
								<th style="width:10%; height:18;">그래프　　구분</th>
								<td>
									<select id="chartValueSe">
										<option value="">선택</option>
										<option value="M">월별 전력 사용량</option>
										<option value="U">년별 전력 사용량</option>
										<option value="A">년별 전기 요금</option>
										<option value="P">년별 PEEK 사용량</option>
									</select>
									&nbsp;     &nbsp;
									<button id="btnChartSearch">그래프 조회</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시　설　코　드</th>
								<td>
									<input id="mngFeeJobSe" type="hidden"/>
									<input id="mngFeeJobSeNm" type="hidden"/>
									<select id="mngFeeFcltyCd">
										<option value="" selected="selected">선택</option>
										<c:forEach  items="${mngFeeFcltyCdList}" var="mngFeeFcltyCdItem">
											<option value="${mngFeeFcltyCdItem.mngFeeFcltyCd}">${mngFeeFcltyCdItem.mngFeeFcltyNm}</option>
										</c:forEach>
									</select>
								</td>
								<td colspan="2" rowspan="10" style="padding-left:4px;">
									<div id="elctyUsageQyChart" style="width:485px;height:380px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">전력　　사용량</th>
								<td>
									<input type="text" size="20" id="usageQy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> kW/h
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">전　기　요　금</th>
								<td>
									<input type="text" size="20" id="usageAmt" class="ygpaNumber" maxlength="17"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">PEEK　　사용량</th>
								<td>
									<input type="text" size="20" id="peekQy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">01/02월 사용량</th>
								<td>
									<input type="text" size="11" id="mt01Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt02Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">03/04월 사용량</th>
								<td>
									<input type="text" size="11" id="mt03Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt04Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">05/06월 사용량</th>
								<td>
									<input type="text" size="11" id="mt05Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt06Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">07/08월 사용량</th>
								<td>
									<input type="text" size="11" id="mt07Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt08Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">09/10월 사용량</th>
								<td>
									<input type="text" size="11" id="mt09Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt10Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">11/12월 사용량</th>
								<td>
									<input type="text" size="11" id="mt11Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/> /
									<input type="text" size="11" id="mt12Qy" class="ygpaNumber" data-decimal-point="2" maxlength="18"/>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
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
