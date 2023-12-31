<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamElctyUsageSttusMng.jsp
 * @Description : 전기 사용 현황
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  Lee          최초 생성
 *
 * author Lee
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
 * @FUNCTION NAME : GamElctyUsageSttusMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamElctyUsageSttusMngModule() {}

GamElctyUsageSttusMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectElctyUsageSttusMng.do',
		dataType : 'json',
		colModel : [
					{display:'업무 구분',		name:'mngFeeJobSeNm',	width:90, 		sortable:false,		align:'center'},
					{display:'시설 명',			name:'mngFeeFcltyNm',	width:175, 		sortable:false,		align:'left'},
					{display:'사용 월',			name:'usageYrMt',		width:80, 		sortable:false,		align:'center'},
					{display:'전월 사용 량',	name:'prevMtUsageQy',	width:100, 		sortable:false,		align:'right'},
					{display:'당월 사용 량',	name:'saidMtUsageQy',	width:100, 		sortable:false,		align:'right'},
					{display:'적용 계수',		name:'applcCoef',		width:100, 		sortable:false,		align:'right'},
					{display:'순 사용 량',		name:'netUsageQy',		width:100, 		sortable:false,		align:'right'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.usageMt + row.mngFeeJobSe;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.mngFeeFcltyCd + row.usageMt + row.mngFeeJobSe;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#usageMtYear').on('change',{module:this}, function(event){
		event.data.module.setUsageMt();
		event.data.module.getPrevMtUsageQy();
	});

	this.$('#usageMtMon').on('change',{module:this}, function(event){
		event.data.module.setUsageMt();
		event.data.module.getPrevMtUsageQy();
	});

	this.$('#saidMtUsageQy').on('keyup change',{module:this}, function(event){
		event.data.module.calcNetUsageQy();
	});

	this.$('#applcCoef').on('keyup change',{module:this}, function(event){
		event.data.module.calcNetUsageQy();
	});
	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	var mon = new Date().getMonth()+1;
	if (mon > 0 && mon < 10) {
		mon = "0" + mon;
	} else {
		mon = "" + mon;
	}
	this.$('#sUsageMt').val(mon);
	this.$('#sApplcCoef').val('0.66');
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
GamElctyUsageSttusMngModule.prototype.isValidYear = function(yearString, nullCheckFlag) {

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
GamElctyUsageSttusMngModule.prototype.isValidMonth = function(monthString, nullCheckFlag) {

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
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.drawChart = function() {

	var netUsageQyArr=[];
	var maxNetUsageQy=0;
	var netUsageQy=0;
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamSelectElctyUsageSttusMngChart.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			for (var i=0; i<12; i++) {
				netUsageQy=result.resultList[i]['netUsageQy']*1;
				netUsageQyArr[i]={month: (i+1), gauge: netUsageQy};
				if (maxNetUsageQy<netUsageQy) {
					maxNetUsageQy=netUsageQy;
				}
			};
		} else {
			for (var i=0; i<12; i++) {
				netUsageQy=0;
				netUsageQyArr[i]={month: (i+1), gauge: netUsageQy};
			};
		}
		if (maxNetUsageQy<10) {
			maxNetUsageQy=10;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#elctyUsageSttusChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#gauge#",
				tooltip			: "#gauge# kw/h",
				xAxis			: {
					title 		: "전기 사용 현황",
					template	: "#month# 월"
				},
				yAxis			: {
					start		: 0,
					end			: maxNetUsageQy + 10,
					step		: Math.ceil(maxNetUsageQy / 10),
					title		: "전기 사용량,kw/h"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : maxNetUsageQy + 10,
				step : Math.ceil(maxNetUsageQy / 10),
				title : "전기 사용량,kw/h"
			});
		}
		module.barChart.parse(netUsageQyArr, "json");
		module.barChart.refresh();
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
GamElctyUsageSttusMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupMngFeeFcltyCd':
			if (msg == 'ok') {
				this.$('#mngFeeFcltyCd').val(value.mngFeeFcltyCd);
				this.$('#mngFeeFcltyNm').val(value.mngFeeFcltyNm);
				this.$('#mngFeeJobSe').val(value.mngFeeJobSe);
				this.$('#mngFeeJobSeNm').val('[' + value.mngFeeJobSe + ']:' + value.mngFeeJobSeNm);
				this.getPrevMtUsageQy();
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
GamElctyUsageSttusMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'btnCopy':
			this.copyData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'btnExcelUpload':
			this.uploadExcel();
			break;
		case 'popupMngFeeFcltyCd':
			this.doExecuteDialog('popupMngFeeFcltyCd', '시설 선택', '/popup/showMngCode.do', null);
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
GamElctyUsageSttusMngModule.prototype.onSubmit = function() {

	var sUsageYear = this.$('#sUsageYear').val();
	var sUsageMt = this.$('#sUsageMt').val();
	if (this.isValidYear(sUsageYear, true) == false) {
		alert('사용 년도가 부정확합니다.');
		this.$("#sUsageYear").focus();
		return;
	}
	if (this.isValidMonth(sUsageMt, false) == false) {
		alert('사용 월이 부정확합니다.');
		this.$("#sUsageMt").focus();
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
GamElctyUsageSttusMngModule.prototype.loadData = function() {

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
 GamElctyUsageSttusMngModule.prototype.refreshData = function() {

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
GamElctyUsageSttusMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/mngFee/gamSelectElctyUsageSttusMngPk.do', searchVO, function(module, result){
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
GamElctyUsageSttusMngModule.prototype.selectData = function() {

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
	var mngFeeFcltyCd = this._mainKeyValue.substring(0,2);
	var usageMt = this._mainKeyValue.substring(2,8);
	var mngFeeJobSe = this._mainKeyValue.substring(8,9);
	this.$("#mainGrid").selectFilterRow([{col:"mngFeeFcltyCd", filter:mngFeeFcltyCd},
										 {col:"usageMt", filter:usageMt},
										 {col:"mngFeeJobSe", filter:mngFeeJobSe}]);
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.drawChart();

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.firstData = function() {

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
	var rowIndex = 0;
	var row = rows[rowIndex];
	var mngFeeFcltyCd = row["mngFeeFcltyCd"];
	var usageMt = row["usageMt"];
	var mngFeeJobSe = row["mngFeeJobSe"];
	if (mngFeeFcltyCd != "" && usageMt != "" && mngFeeJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"usageMt", filter:usageMt},
											 {col:"mngFeeJobSe", filter:mngFeeJobSe},
											 {col:"mngFeeFcltyCd", filter:mngFeeFcltyCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = mngFeeFcltyCd + usageMt + mngFeeJobSe;
		this.makeFormValues('#detailForm', rows[rowIndex]);
		this.makeDivValues('#detailForm', rows[rowIndex]);
		this.enableDetailInputItem();
		this.drawChart();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.prevData = function() {

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
	var rowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["mngFeeFcltyCd"] + row["usageMt"] + row["mngFeeJobSe"];
		if (this._mainKeyValue == keyValue) {
			rowIndex = i - 1;
			break;
		}
	}
	if (rowIndex < 0) {
		alert("첫번째 자료 입니다!");
		return;
	}
	if (rowIndex >= gridRowCount) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	var row = rows[rowIndex];
	var mngFeeFcltyCd = row["mngFeeFcltyCd"];
	var usageMt = row["usageMt"];
	var mngFeeJobSe = row["mngFeeJobSe"];
	if (mngFeeFcltyCd != "" && usageMt != "" && mngFeeJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"usageMt", filter:usageMt},
											 {col:"mngFeeJobSe", filter:mngFeeJobSe},
											 {col:"mngFeeFcltyCd", filter:mngFeeFcltyCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = mngFeeFcltyCd + usageMt + mngFeeJobSe;
		this.makeFormValues('#detailForm', rows[rowIndex]);
		this.makeDivValues('#detailForm', rows[rowIndex]);
		this.enableDetailInputItem();
		this.drawChart();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.nextData = function() {

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
	var rowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["mngFeeFcltyCd"] + row["usageMt"] + row["mngFeeJobSe"];
		if (this._mainKeyValue == keyValue) {
			rowIndex = i + 1;
			break;
		}
	}
	if (rowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	if (rowIndex >= gridRowCount) {
		alert("마지막 자료 입니다!");
		return;
	}
	var row = rows[rowIndex];
	var mngFeeFcltyCd = row["mngFeeFcltyCd"];
	var usageMt = row["usageMt"];
	var mngFeeJobSe = row["mngFeeJobSe"];
	if (mngFeeFcltyCd != "" && usageMt != "" && mngFeeJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"usageMt", filter:usageMt},
											 {col:"mngFeeJobSe", filter:mngFeeJobSe},
											 {col:"mngFeeFcltyCd", filter:mngFeeFcltyCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = mngFeeFcltyCd + usageMt + mngFeeJobSe;
		this.makeFormValues('#detailForm', rows[rowIndex]);
		this.makeDivValues('#detailForm', rows[rowIndex]);
		this.enableDetailInputItem();
		this.drawChart();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.lastData = function() {

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
	var rowIndex = gridRowCount - 1;
	var row = rows[rowIndex];
	var mngFeeFcltyCd = row["mngFeeFcltyCd"];
	var usageMt = row["usageMt"];
	var mngFeeJobSe = row["mngFeeJobSe"];
	if (mngFeeFcltyCd != "" && usageMt != "" && mngFeeJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"usageMt", filter:usageMt},
											 {col:"mngFeeJobSe", filter:mngFeeJobSe},
											 {col:"mngFeeFcltyCd", filter:mngFeeFcltyCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = mngFeeFcltyCd + usageMt + mngFeeJobSe;
		this.makeFormValues('#detailForm', rows[rowIndex]);
		this.makeDivValues('#detailForm', rows[rowIndex]);
		this.enableDetailInputItem();
		this.drawChart();
	}

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.addData = function() {

	var usageMtYear = this.$('#sUsageYear').val();
	var usageMtMon = this.$('#sUsageMt').val();
	var mngFeeJobSe = this.$('#sMngFeeJobSe').val();
	var mngFeeJobSeNm = "";
	var sApplcCoef = Number(this.$('#sApplcCoef').val().replace(/,/gi, ""));
	if (usageMtYear == "") {
		usageMtYear = new Date().getFullYear();
	}
	this.$('#usageMtYear').val(usageMtYear);
	if (usageMtMon == "") {
		usageMtMon = new Date().getMonth()+1;
	}
	if (usageMtMon.length==1) {
		usageMtMon="0"+usageMtMon;
	}
	this.$('#usageMtMon').val(usageMtMon);
	this.$('#usageMt').val(usageMtYear + usageMtMon);
	if (mngFeeJobSe == "M") {
		mngFeeJobSeNm = "[M]:마린센터";
	} else if (mngFeeJobSe == "E") {
		mngFeeJobSeNm = "[E]:전기시설";
	}
	this.$('#mngFeeJobSe').val(mngFeeJobSe);
	this.$('#mngFeeJobSeNm').val(mngFeeJobSeNm);
	this.$('#mngFeeFcltyCd').val("");
	this.$('#mngFeeFcltyNm').val("");
	this.$('#prevMtUsageQy').val("0");
	this.$('#saidMtUsageQy').val("0");
	this.$('#applcCoef').val(sApplcCoef);
	this.$('#netUsageQy').val("0");
	this.enableDetailInputItem();
	this.$('#usageMtYear').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var usageMtYear = this.$('#usageMtYear').val();
	var usageMtMon = this.$('#usageMtMon').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	var prevMtUsageQy = Number(this.$('#prevMtUsageQy').val().replace(/,/gi, ""));
	var saidMtUsageQy = Number(this.$('#saidMtUsageQy').val().replace(/,/gi, ""));
	var netUsageQy = Number(this.$('#netUsageQy').val().replace(/,/gi, ""));
	var applcCoef = Number(this.$('#applcCoef').val().replace(/,/gi, ""));
	if (this.isValidYear(usageMtYear, true) == false) {
		alert('사용 년도가 부정확합니다.');
		this.$("#usageMtYear").focus();
		return;
	}
	if (this.isValidMonth(usageMtMon, true) == false) {
		alert('사용 월이 부정확합니다.');
		this.$("#usageMtMon").focus();
		return;
	}
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	if (mngFeeFcltyCd == "" || mngFeeFcltyCd.length != 4) {
		alert('시설 코드가 부정확합니다.');
		return;
	}
	if (prevMtUsageQy > 99999999.99 || prevMtUsageQy < 0) {
		alert('전월 사용 량이 부정확합니다.');
		return;
	}
	if (saidMtUsageQy > 99999999.99 || saidMtUsageQy < 0) {
		alert('당월 사용 량이 부정확합니다.');
		this.$("#saidMtUsageQy").focus();
		return;
	}
	if (prevMtUsageQy > saidMtUsageQy) {
		alert('당월 사용 량이 전월 사용 량보다 적습니다.');
		this.$("#saidMtUsageQy").focus();
		return;
	}
	if (applcCoef >= 1  || applcCoef < 0) {
		alert('적용 계수가 부정확합니다.');
		this.$("#applcCoef").focus();
		return;
	}
	if (netUsageQy > 99999999.99 || netUsageQy < 0) {
		alert('순 사용 량이 부정확합니다.');
		this.$("#netUsageQy").focus();
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = mngFeeFcltyCd + usageMtYear + usageMtMon + mngFeeJobSe;
		this.doAction('/mngFee/gamInsertElctyUsageSttusMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateElctyUsageSttusMng.do', inputVO, function(module, result) {
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
GamElctyUsageSttusMngModule.prototype.deleteData = function() {

	var usageMtYear = this.$('#usageMtYear').val();
	var usageMtMon = this.$('#usageMtMon').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	if (usageMtYear == "") {
		alert('사용 년도가 부정확합니다.');
		this.$("#usageMtYear").focus();
		return;
	}
	if (usageMtMon == "") {
		alert('사용 월이 부정확합니다.');
		this.$("#usageMtMon").focus();
		return;
	}
	if (mngFeeJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mngFeeJobSe").focus();
		return;
	}
	if (mngFeeFcltyCd == "") {
		alert('시설 코드가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteElctyUsageSttusMng.do', deleteVO, function(module, result) {
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
GamElctyUsageSttusMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryUsageYear = this.$('#sUsageYear').val();
	var sQueryUsageMt = this.$('#sUsageMt').val();
	var mtCnt=0;
	var sUsageYear = this.$('#sUsageYear').val();
	var sUsageMt = this.$('#sUsageMt').val();
	if (this.isValidYear(sUsageYear, true) == false) {
		alert('사용 년도가 부정확합니다.');
		this.$("#sUsageYear").focus();
		return;
	}
	if (this.isValidMonth(sUsageMt, true) == false) {
		alert('사용 월이 부정확합니다.');
		this.$("#sUsageMt").focus();
		return;
	}
	if (confirm("이전월의 자료를 [" + sQueryUsageYear + "-" + sQueryUsageMt + "월] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectElctyUsageSttusMngMonthCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		mtCnt=result.resultList[0]['mtCnt']*1;
		if (mtCnt > 0) {
			alert('[' + sQueryUsageYear + '-' + sQueryUsageMt + '월] 자료가 존재합니다.');
			return;
		}
		module.doAction('/mngFee/gamCopyElctyUsageSttusMng.do', searchVO, function(module, result) {
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
GamElctyUsageSttusMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadElctyUsageSttusMng.do');

};

<%
/**
 * @FUNCTION NAME : uploadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 업로드한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.uploadExcel = function() {

	this.uploadSingleFile('/mngFee/gamExcelUploadElctyUsageSttusMng.do', function(module, resp) {
		if(resp.resultCode!=0) {
			alert(resp.resultMsg);
			return;
		} else {
			alert(resp.resultMsg);
			module._mainmode = 'query';
			module._mainKeyValue = '';
			module.loadData();
		}
	});

};

<%
/**
 * @FUNCTION NAME : setUsageMt
 * @DESCRIPTION   : 사용 월을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.setUsageMt = function() {

	var usageMtYear = this.$('#usageMtYear').val();
	var usageMtMon = this.$('#usageMtMon').val();
	var usageMt = "";
	if (usageMtYear != "" && usageMtMon != "") {
		usageMt = usageMtYear + usageMtMon;
	}
	this.$('#usageMt').val(usageMt);

};

<%
/**
 * @FUNCTION NAME : calcNetUsageQy
 * @DESCRIPTION   : 순 사용 량을 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.calcNetUsageQy = function() {

	var prevMtUsageQy = Number(this.$('#prevMtUsageQy').val().replace(/,/gi, ""));
	var saidMtUsageQy = Number(this.$('#saidMtUsageQy').val().replace(/,/gi, ""));
	var applcCoef = Number(this.$('#applcCoef').val().replace(/,/gi, ""));
	var realMtUsageQy = 0;
	var netUsageQy = 0;
	realMtUsageQy = saidMtUsageQy - prevMtUsageQy;
	if (realMtUsageQy <= 0 || applcCoef <= 0) {
		netUsageQy = 0;
	} else {
		netUsageQy = Math.floor(realMtUsageQy * applcCoef);
	}
	this.$('#netUsageQy').val('' + $.number(netUsageQy));

};

<%
/**
 * @FUNCTION NAME : getPrevMtUsageQy
 * @DESCRIPTION   : 전월 사용 량을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.getPrevMtUsageQy = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#usageMtYear').val() == "" || this.$('#usageMtMon').val() == "" || this.$('#mngFeeFcltyCd').val() == "" || this.$('#mngFeeJobSe').val() == "") {
		this.$('#prevMtUsageQy').val('0');
		return;
	}
	this.doAction('/mngFee/gamSelectElctyUsageSttusMngPrevMtUsageQy.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#prevMtUsageQy').val('' + $.number(result.sPrevMtUsageQy));
			if (module.$('#saidMtUsageQy').val() == "0") {
				module.$('#saidMtUsageQy').val('' + $.number(result.sPrevMtUsageQy));
			}
		} else {
			module.$('#prevMtUsageQy').val('0');
		}
	});

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyUsageSttusMngModule.prototype.enableListButtonItem = function() {

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
GamElctyUsageSttusMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#usageMtYear').enable();
		this.$('#usageMtMon').enable();
		this.$('#mngFeeJobSe').enable();
		this.$('#prevMtUsageQy').enable();
		this.$('#saidMtUsageQy').enable();
		this.$('#applcCoef').enable();
		this.$('#netUsageQy').enable();
		this.$('#popupMngFeeFcltyCd').enable();
		this.$('#popupMngFeeFcltyCd').removeClass('ui-state-disabled');
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
			this.$('#usageMtYear').disable();
			this.$('#usageMtMon').disable();
			this.$('#mngFeeJobSe').disable();
			this.$('#prevMtUsageQy').enable();
			this.$('#saidMtUsageQy').enable();
			this.$('#applcCoef').enable();
			this.$('#netUsageQy').enable();
			this.$('#popupMngFeeFcltyCd').disable({disableClass:"ui-state-disabled"});
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
			this.$('#usageMtYear').disable();
			this.$('#usageMtMon').disable();
			this.$('#mngFeeJobSe').disable();
			this.$('#prevMtUsageQy').disable();
			this.$('#saidMtUsageQy').disable();
			this.$('#applcCoef').disable();
			this.$('#netUsageQy').disable();
			this.$('#popupMngFeeFcltyCd').disable({disableClass:"ui-state-disabled"});
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
GamElctyUsageSttusMngModule.prototype.disableDetailInputItem = function() {

	this.$('#usageMtYear').disable();
	this.$('#usageMtMon').disable();
	this.$('#mngFeeJobSe').disable();
	this.$('#prevMtUsageQy').disable();
	this.$('#saidMtUsageQy').disable();
	this.$('#applcCoef').disable();
	this.$('#netUsageQy').disable();
	this.$('#popupMngFeeFcltyCd').disable({disableClass:"ui-state-disabled"});
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
GamElctyUsageSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamElctyUsageSttusMngModule();

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
							<th>사용 년도</th>
							<td>
								<select id="sUsageYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
							</td>
							<th>사용 월</th>
							<td>
								<select id="sUsageMt">
									<option value="" selected>선택</option>
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
							<th>업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th style="text-align:right;">적용 계수(조회조건 아님)</th>
							<td>
								<input type="text" size="4" id="sApplcCoef" class="ygpaNumber" data-decimal-point="2" />
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
				<li><a href="#listTab" class="emdTab">전기 사용현황</a></li>
				<li><a href="#detailTab" class="emdTab">전기 사용현황 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="10" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
									<button id="btnCopy">이전월　자료복사</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
	                                <button id="btnExcelUpload" class="buttonExcel">엑셀　　업로드</button>
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
								<th style="width:10%; height:26;">사　용　년　월</th>
								<td>
									<input id="usageMt" type="hidden"/>
									<select id="usageMtYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									<select id="usageMtMon" class='selt'>
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
								<td rowspan="12" style="padding-left:4px;">
									<div id="elctyUsageSttusChart" style="width:515px;height:415px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">업　무　구　분</th>
								<td>
									<input id="mngFeeJobSeNm" type="hidden"/>
									<select id="mngFeeJobSe" disabled>
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">시　설　코　드</th>
								<td>
									<input type="text" size="13" id="mngFeeFcltyCd" disabled/>
									<button id="popupMngFeeFcltyCd" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">시　　설　　명</th>
								<td>
									<input type="text" size="25" id="mngFeeFcltyNm" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">전월　　사용량</th>
								<td>
									<input type="text" size="20" id="prevMtUsageQy" class="ygpaNumber" disabled/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">당월　　사용량</th>
								<td>
									<input type="text" size="20" id="saidMtUsageQy" class="ygpaNumber" maxlength="16"/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">적　용　계　수</th>
								<td>
									<input type="text" size="20" id="applcCoef" class="ygpaNumber" data-decimal-point="2" maxlength="9"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">순　사　용　량</th>
								<td>
									<input type="text" size="20" id="netUsageQy" class="ygpaNumber" maxlength="16"/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">등　　록　　자</th>
								<td>
									<input type="text" size="25" id="regUsr" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">등　록　일　시</th>
								<td>
									<input type="text" size="25" id="registDt" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">수　　정　　자</th>
								<td>
									<input type="text" size="25" id="updUsr" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:26;">수　정　일　시</th>
								<td>
									<input type="text" size="25" id="updtDt" disabled>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnFirstData">처음 자료</button>
								<button id="btnPrevData">이전 자료</button>
								<button id="btnNextData">다음 자료</button>
								<button id="btnLastData">마지막 자료</button>
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
