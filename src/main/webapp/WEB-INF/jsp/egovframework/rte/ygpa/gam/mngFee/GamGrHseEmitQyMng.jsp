<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamGrHseEmitQyMng.jsp
 * @Description : 온실가스 배출현황
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
 * @FUNCTION NAME : GamGrHseEmitQyMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamGrHseEmitQyMngModule() {}

GamGrHseEmitQyMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectGrHseEmitQyMng.do',
		dataType : 'json',
		colModel : [
					{display:'연료 코드',			name:'fuelCd',			width:70, 		sortable:false,		align:'center'},
					{display:'연료 명',				name:'fuelNm',			width:123, 		sortable:false,		align:'left'},
					{display:'온실가스 계수',		name:'grHseCoef',		width:110, 		sortable:false,		align:'right'},
					{display:'관리 월',				name:'mngYrMt',			width:110, 		sortable:false,		align:'center'},
					{display:'사용 량',				name:'usageQy',			width:110, 		sortable:false,		align:'right'},
					{display:'에너지 사용 량',		name:'energyUsageQy',	width:110, 		sortable:false,		align:'right'},
					{display:'온실가스 배출 량',	name:'grHseEmitQy',		width:110, 		sortable:false,		align:'right'}
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
		module._mode = 'modify';
		module._mainKeyValue = row.fuelCd + row.mngYear + row.mngMt;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fuelCd + row.mngYear + row.mngMt;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#mngMtYear').on('change',{module:this}, function(event){
		event.data.module.setMngMt();
	});

	this.$('#mngMtMon').on('change',{module:this}, function(event){
		event.data.module.setMngMt();
	});

	this.$('#usageQy').on('keyup change',{module:this}, function(event){
		event.data.module.calcGrHseEmitQy();
	});

	this._mode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sMngMt').val(mon);
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.drawChart = function() {

	var grHseEmitQyArr=[];
	var maxGrHseEmitQy=0;
	var grHseEmitQy=0;
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamSelectGrHseEmitQyMngChart.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			for (var i=0; i<12; i++) {
				grHseEmitQy=result.resultList[i]['grHseEmitQy']*1;
				grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
				if (maxGrHseEmitQy<grHseEmitQy) {
					maxGrHseEmitQy=grHseEmitQy;
				}
			};
		} else {
			for (var i=0; i<12; i++) {
				grHseEmitQy=0;
				grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
			};
		}
		if (maxGrHseEmitQy<10) {
			maxGrHseEmitQy=10;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#grHseEmitChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#gauge#",
				tooltip			: "#gauge# (KgCO2)",
				xAxis			: {
					title 		: "온실가스 배출 현황",
					template	: "#month# 월"
				},
				yAxis			: {
					start		: 0,
					end			: maxGrHseEmitQy + 10,
					step		: Math.ceil(maxGrHseEmitQy / 10),
					title		: "온실가스 배출량,KgCO2"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : maxGrHseEmitQy + 10,
				step : Math.ceil(maxGrHseEmitQy / 10),
				title : "온실가스 배출량,KgCO2"
			});
		}
		module.barChart.parse(grHseEmitQyArr, "json");
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
GamGrHseEmitQyMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupFuelCd':
			if (msg == 'ok') {
				this.$('#mngYear').val(value.mngYear);
				this.$('#fuelCd').val(value.fuelCd);
				this.$('#fuelNm').val(value.fuelNm);
				this.$('#energyUnit').val(value.energyUnit);
				this.$('#energyTotalCalVal').val(value.energyTotalCalVal);
				this.$('#energyNetCalVal').val(value.energyNetCalVal);
				this.$('#grHseUnit').val(value.grHseUnit);
				this.$('#grHseCoef').val(value.grHseCoef);
				this.$("#usageQy").focus();
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
GamGrHseEmitQyMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnInsert':
			this._mode = 'insert';
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
			if (this._mode=="modify") {
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
		case 'popupFuelCd':
			this.doExecuteDialog('popupFuelCd', '연료 선택', '/popup/showFuelCode.do', null);
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
GamGrHseEmitQyMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
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
GamGrHseEmitQyMngModule.prototype.loadData = function() {

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
 GamGrHseEmitQyMngModule.prototype.refreshData = function() {

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
GamGrHseEmitQyMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/mngFee/gamSelectGrHseEmitQyMngPk.do', searchVO, function(module, result){
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
GamGrHseEmitQyMngModule.prototype.selectData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (this._mode == 'query') {
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
	var mainKeyValue = this._mainKeyValue;
	if (mainKeyValue == "") {
		return;
	}
	var fuelCd = mainKeyValue.substring(0,4);
	var mngYear = mainKeyValue.substring(4,8);
	var mngMt = mainKeyValue.substring(8,14);
	var mainRowNo = -1;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (row.fuelCd == fuelCd && row.mngYear == mngYear && row.mngMt == mngMt) {
			mainRowNo = i;
			break;
		}
	}
	if (mainRowNo >= 0) {
		this.$("#mainGrid").selectRowId(mainRowNo);
	}
	this._mode = 'modify';
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
GamGrHseEmitQyMngModule.prototype.addData = function() {

	var mngMtYear = this.$('#sMngYear').val();
	var mngMtMon = this.$('#sMngMt').val();
	if (mngMtYear == "") {
		mngMtYear = new Date().getFullYear();
	}
	this.$('#mngMtYear').val(mngMtYear);
	if (mngMtMon == "") {
		mngMtMon = new Date().getMonth()+1;
	}
	if(mngMtMon.length==1) {
		mngMtMon="0"+mngMtMon;
	}
	this.$('#mngMtMon').val(mngMtMon);
	this.$('#mngMt').val(mngMtYear + mngMtMon);
	this.$('#fuelCd').val("");
	this.$('#fuelNm').val("");
	this.$('#mngYear').val("");
	this.$('#energyUnit').val("");
	this.$('#energyTotalCalVal').val("");
	this.$('#energyNetCalVal').val("");
	this.$('#grHseUnit').val("");
	this.$('#grHseCoef').val("");
	this.$('#usageQy').val("0.00");
	this.$('#energyUsageQy').val("0.00");
	this.$('#grHseEmitQy').val("0.00");
	this.enableDetailInputItem();
	this.$('#mngMtYear').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mngMtYear = this.$('#mngMtYear').val();
	var mngMtMon = this.$('#mngMtMon').val();
	var mngYear = this.$('#mngYear').val();
	var fuelCd = this.$('#fuelCd').val();
	var usageQy = Number(this.$('#usageQy').val().replace(/,/gi, ""));
	var energyUsageQy = Number(this.$('#energyUsageQy').val().replace(/,/gi, ""));
	var grHseEmitQy = Number(this.$('#grHseEmitQy').val().replace(/,/gi, ""));
	if (mngMtYear > "9999"  || mngMtYear < "2000" || mngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngMtYear").focus();
		return;
	}
	if (mngMtMon > "12"  || mngMtMon < "01" || mngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMtMon").focus();
		return;
	}
	if (mngYear > "9999"  || mngYear < "2000" || mngYear == "") {
		alert('연료 코드 관리 년도가 부정확합니다.');
		return;
	}
	if (fuelCd == "" || fuelCd.length != 4) {
		alert('연료 코드가 부정확합니다.');
		return;
	}
	if (usageQy > 99999999.99 || usageQy < 0) {
		alert('사용 량이 부정확합니다.');
		this.$("#usageQy").focus();
		return;
	}
	if (energyUsageQy > 99999999.99 || energyUsageQy < 0) {
		alert('에너지 사용 량이 부정확합니다.');
		this.$("#energyUsageQy").focus();
		return;
	}
	if (grHseEmitQy > 99999999.99 || grHseEmitQy < 0) {
		alert('온실가스 배출 량이 부정확합니다.');
		this.$("#grHseEmitQy").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = fuelCd + mngYear + mngMtYear + mngMtMon;
		this.doAction('/mngFee/gamInsertGrHseEmitQyMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateGrHseEmitQyMng.do', inputVO, function(module, result) {
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
GamGrHseEmitQyMngModule.prototype.deleteData = function() {

	var mngMtYear = this.$('#mngMtYear').val();
	var mngMtMon = this.$('#mngMtMon').val();
	var mngYear = this.$('#mngYear').val();
	var fuelCd = this.$('#fuelCd').val();
	if (mngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngMtYear").focus();
		return;
	}
	if (mngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMtMon").focus();
		return;
	}
	if (mngYear == "") {
		alert('연료 코드 관리 년도가 부정확합니다.');
		return;
	}
	if (fuelCd == "") {
		alert('연료 코드가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteGrHseEmitQyMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : 월별 항목을 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryMngYear = this.$('#sMngYear').val();
	var sQueryMngMt = this.$('#sMngMt').val();
	var mtCnt=0;
	if (confirm("이전월의 자료를 [" + sQueryMngYear + "-" + sQueryMngMt + "월] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectGrHseEmitQyMngMonthCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		mtCnt=result.resultList[0]['mtCnt']*1;
		if (mtCnt > 0) {
			alert('[' + sQueryMngYear + '-' + sQueryMngMt + '월] 자료가 존재합니다.');
			return;
		}
		module.doAction('/mngFee/gamCopyGrHseEmitQyMng.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mode = 'query';
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
GamGrHseEmitQyMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadGrHseEmitQyMng.do');

};

<%
/**
 * @FUNCTION NAME : setMngMt
 * @DESCRIPTION   : 관리 월을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.setMngMt = function() {

	var mngMtYear = this.$('#mngMtYear').val();
	var mngMtMon = this.$('#mngMtMon').val();
	var mngMt = "";
	if (mngMtYear != "" && mngMtMon != "") {
		mngMt = mngMtYear + mngMtMon;
	}
	this.$('#mngMt').val(mngMt);

};

<%
/**
 * @FUNCTION NAME : calcGrHseEmitQy
 * @DESCRIPTION   : 온실가스 배출 량을 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.calcGrHseEmitQy = function() {

	var usageQy = Number(this.$('#usageQy').val().replace(/,/gi, ""));
	var energyTotalCalVal = Number(this.$('#energyTotalCalVal').val().replace(/,/gi, ""));
	var energyNetCalVal = Number(this.$('#energyNetCalVal').val().replace(/,/gi, ""));
	var grHseCoef = Number(this.$('#grHseCoef').val().replace(/,/gi, ""));
	var energyUsageQy = 0;
	var grHseEmitQy = 0;
	if (usageQy > 0 && energyTotalCalVal > 0) {
		energyUsageQy = Math.round(usageQy * energyTotalCalVal * 100) / 100;
	}
	if (usageQy > 0 && energyNetCalVal > 0 && grHseCoef > 0) {
		grHseEmitQy = Math.round(usageQy * energyNetCalVal * grHseCoef * 100) / 100;
	}
	this.$('#energyUsageQy').val('' + $.number(energyUsageQy, 2));
	this.$('#grHseEmitQy').val('' + $.number(grHseEmitQy, 2));

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
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
GamGrHseEmitQyMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#mngMtYear').enable();
		this.$('#mngMtMon').enable();
		this.$('#usageQy').enable();
		this.$('#energyUsageQy').enable();
		this.$('#grHseEmitQy').enable();
		this.$('#popupFuelCd').enable();
		this.$('#popupFuelCd').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#mngMtYear').disable();
			this.$('#mngMtMon').disable();
			this.$('#usageQy').enable();
			this.$('#energyUsageQy').enable();
			this.$('#grHseEmitQy').enable();
			this.$('#popupFuelCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#mngMtYear').disable();
			this.$('#mngMtMon').disable();
			this.$('#usageQy').disable();
			this.$('#energyUsageQy').disable();
			this.$('#grHseEmitQy').disable();
			this.$('#popupFuelCd').disable({disableClass:"ui-state-disabled"});
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
GamGrHseEmitQyMngModule.prototype.disableDetailInputItem = function() {

	this.$('#mngMtYear').disable();
	this.$('#mngMtMon').disable();
	this.$('#usageQy').disable();
	this.$('#energyUsageQy').disable();
	this.$('#grHseEmitQy').disable();
	this.$('#popupFuelCd').disable({disableClass:"ui-state-disabled"});
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
GamGrHseEmitQyMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mode=="insert") {
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

var module_instance = new GamGrHseEmitQyMngModule();

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
							<th>관리 년도</th>
							<td>
								<select id="sMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
							</td>
							<th>관리 월</th>
							<td>
								<select id="sMngMt">
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
							<th>연료　코드</th>
							<td>
								<input type="text" size="5" id="sFuelCd" maxlength="4">
								<input id="sFuelNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
								<button id="popupSearchFuelCd" class="popupButton">선택</button>
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
				<li><a href="#listTab" class="emdTab">온실가스 배출현황</a></li>
				<li><a href="#detailTab" class="emdTab">온실가스 배출현황 상세</a></li>
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
								<td style="text-align: right">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
									<button id="btnCopy">이전월　자료복사</button>
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
								<th style="width:10%; height:28;">관　리　년　월</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<select id="mngMtYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									<select id="mngMtMon" class='selt'>
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
								<td rowspan="15" style="padding-left:4px;">
									<div id="grHseEmitChart" style="width:512px;height:410px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">연　료　코　드</th>
								<td>
									<input id="mngYear" type="hidden" />
									<input type="text" size="13" id="fuelCd" disabled/>
									<button id="popupFuelCd" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">연　　료　　명</th>
								<td><input type="text" size="25" id="fuelNm" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">에너지　　단위</th>
								<td><input type="text" size="25" id="energyUnit" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">에너지총발열량</th>
								<td><input type="text" size="25" id="energyTotalCalVal" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">에너지순발열량</th>
								<td><input type="text" size="25" id="energyNetCalVal" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">온실가스　단위</th>
								<td><input type="text" size="25" id="grHseUnit" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">온실가스　계수</th>
								<td><input type="text" size="25" id="grHseCoef" disabled></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">사　　용　　량</th>
								<td><input type="text" size="25"  class="ygpaNumber" id="usageQy" maxlength="16"/></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">에너지　사용량</th>
								<td><input type="text" size="25" class="ygpaNumber" id="energyUsageQy" maxlength="16"/></td>
							</tr>
							<tr>
								<th style="width:10%; height:28;">온실가스배출량</th>
								<td><input type="text" size="25" class="ygpaNumber" id="grHseEmitQy" maxlength="16"/></td>
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
