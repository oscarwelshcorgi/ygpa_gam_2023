<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamGasUsageSttusMng.jsp
 * @Description : 가스 사용 현황
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
 * @FUNCTION NAME : GamGasUsageSttusMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamGasUsageSttusMngModule() {}

GamGasUsageSttusMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamGasUsageSttusMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectGasUsageSttusMng.do',
		dataType : 'json',
		colModel : [
					{display:'업무 구분',	 	name:'mngFeeJobSeNm',	width:120, 		sortable:false,		align:'center'},
					{display:'사용 월', 		name:'usageYrMt',		width:120, 		sortable:false,		align:'center'},
					{display:'전월 사용 량', 	name:'prevMtUsageQy',	width:120, 		sortable:false,		align:'right'},
					{display:'당월 사용 량',	name:'saidMtUsageQy',	width:120, 		sortable:false,		align:'right'},
					{display:'적용 계수', 		name:'applcCoef',		width:120, 		sortable:false,		align:'right'},
					{display:'순 사용 량', 		name:'netUsageQy',		width:120, 		sortable:false,		align:'right'}
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
		module._mainKeyValue = row.mngFeeFcltyCd + row.usageMt + row.mngFeeJobSe;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
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

	this.$('#mngFeeJobSe').on('change',{module:this}, function(event){
		var module=event.data.module;
		var sMngFeeJobSe = $(this).val();
		if (sMngFeeJobSe == 'M') {
			module.$('#mngFeeFcltyCd').val(sMngFeeJobSe + '000');
			module.$('#mngFeeFcltyNm').val('마린센터');
		} else if (sMngFeeFcltyCd == 'E') {
			module.$('#mngFeeFcltyCd').val(sMngFeeJobSe + '000');
			module.$('#mngFeeFcltyNm').val('전기시설');
		} else {
			module.$('#mngFeeFcltyCd').val(sMngFeeJobSe + '000');
			module.$('#mngFeeFcltyNm').val('UNKNOWN');
		}
		module.getPrevMtUsageQy();
	});

	this.$('#saidMtUsageQy').on('keyup change',{module:this}, function(event){
		event.data.module.calcNetUsageQy();
	});

	this.$('#applcCoef').on('keyup change',{module:this}, function(event){
		event.data.module.calcNetUsageQy();
	});

	this.$('#sApplcCoef').val('0.63');
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
GamGasUsageSttusMngModule.prototype.drawChart = function() {

	var netUsageQyArr=[];
	var maxNetUsageQy=0;
	var netUsageQy=0;
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamGasUsageSttusMngChart.do', searchVO, function(module, result) {
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
				container		: module.$('#gasUsageSttusChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#gauge#",
				tooltip			: "#gauge# kcal/h",
				xAxis			: {
					title 		: "가스 사용 현황",
					template	: "#month# 월"
				},
				yAxis			: {
					start		: 0,
					end			: maxNetUsageQy + 10,
					step		: Math.ceil(maxNetUsageQy / 10),
					title		: "가스 사용량,kcal/h"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : maxNetUsageQy + 10,
				step : Math.ceil(maxNetUsageQy / 10),
				title : "가스 사용량,kcal/h"
			});
		}
		module.barChart.parse(netUsageQyArr, "json");
		module.barChart.refresh();
	});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamGasUsageSttusMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
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
		case 'btnExcelDownload':
			this.downloadExcel();
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
GamGasUsageSttusMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
	this._mainKeyValue = '';
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
GamGasUsageSttusMngModule.prototype.loadData = function() {

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
 GamGasUsageSttusMngModule.prototype.refreshData = function() {

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
GamGasUsageSttusMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/mngFee/gamSelectGasUsageSttusMngPk.do', searchVO, function(module, result){
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
GamGasUsageSttusMngModule.prototype.selectData = function() {

	if (this._mode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		return;
	} else if (this._mode != 'insert' && this._mode != 'modify') {
		return;
	}
	var mainKeyValue = this._mainKeyValue;
	if (mainKeyValue == "") {
		return;
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
GamGasUsageSttusMngModule.prototype.addData = function() {

	var usageMtYear = this.$('#sUsageYear').val();
	var usageMtMon = this.$('#sUsageMt').val();
	var mngFeeJobSe = this.$('#sMngFeeJobSe').val();
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
		this.$('#mngFeeJobSe').val(mngFeeJobSe);
		this.$('#mngFeeJobSeNm').val('[M]:마린센터');
		this.$('#mngFeeFcltyCd').val(mngFeeJobSe + '000');
		this.$('#mngFeeFcltyNm').val('마린센터');
	} else if (mngFeeJobSe == "E") {
		this.$('#mngFeeJobSe').val(mngFeeJobSe);
		this.$('#mngFeeJobSeNm').val('[E]:전기시설');
		this.$('#mngFeeFcltyCd').val(mngFeeJobSe + '000');
		this.$('#mngFeeFcltyNm').val('전기시설');
	} else {
		this.$('#mngFeeJobSe').val('M');
		this.$('#mngFeeJobSeNm').val('[M]:마린센터');
		this.$('#mngFeeFcltyCd').val('M000');
		this.$('#mngFeeFcltyNm').val('마린센터');
	}
	this.$('#prevMtUsageQy').val('0');
	this.$('#saidMtUsageQy').val('0');
	this.$('#applcCoef').val(sApplcCoef);
	this.$('#netUsageQy').val('0');
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
GamGasUsageSttusMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var usageMtYear = this.$('#usageMtYear').val();
	var usageMtMon = this.$('#usageMtMon').val();
	var mngFeeJobSe = this.$('#mngFeeJobSe').val();
	var mngFeeFcltyCd = this.$('#mngFeeFcltyCd').val();
	var prevMtUsageQy = Number(this.$('#prevMtUsageQy').val().replace(/,/gi, ""));
	var saidMtUsageQy = Number(this.$('#saidMtUsageQy').val().replace(/,/gi, ""));
	var netUsageQy = Number(this.$('#netUsageQy').val().replace(/,/gi, ""));
	var applcCoef = Number(this.$('#applcCoef').val().replace(/,/gi, ""));
	if (usageMtYear > "9999"  || usageMtYear < "2000" || usageMtYear == "") {
		alert('사용 년도가 부정확합니다.');
		this.$("#usageMtYear").focus();
		return;
	}
	if (usageMtMon > "12"  || usageMtMon < "01" || usageMtMon == "") {
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
		this.$("#mngFeeFcltyCd").focus();
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
	if (this._mode == "insert") {
		this._mainKeyValue = mngFeeFcltyCd + usageMtYear + usageMtMon + mngFeeJobSe;
		this.doAction('/mngFee/gamInsertGasUsageSttusMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateGasUsageSttusMng.do', inputVO, function(module, result) {
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
GamGasUsageSttusMngModule.prototype.deleteData = function() {

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
		this.$("#mngFeeFcltyCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteGasUsageSttusMng.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				this._mode = 'query';
				this._mainKeyValue = '';
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
GamGasUsageSttusMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelGasUsageSttusMng.do');

};

<%
/**
 * @FUNCTION NAME : setUsageMt
 * @DESCRIPTION   : 사용 월을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamGasUsageSttusMngModule.prototype.setUsageMt = function() {

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
GamGasUsageSttusMngModule.prototype.calcNetUsageQy = function() {

	var saidMtUsageQy = Number(this.$('#saidMtUsageQy').val().replace(/,/gi, ""));
	var applcCoef = Number(this.$('#applcCoef').val().replace(/,/gi, ""));
	var netUsageQy = 0;
	if (saidMtUsageQy <= 0 || applcCoef <= 0) {
		netUsageQy = 0;
	} else {
		netUsageQy = Math.floor(saidMtUsageQy * applcCoef);
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
GamGasUsageSttusMngModule.prototype.getPrevMtUsageQy = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#usageMtYear').val() == "" || this.$('#usageMtMon').val() == "" || this.$('#mngFeeFcltyCd').val() == "" || this.$('#mngFeeJobSe').val() == "") {
		this.$('#prevMtUsageQy').val('0');
		return;
	}
	this.doAction('/mngFee/gamGasUsageSttusMngPrevMtUsageQy.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#prevMtUsageQy').val('' + $.number(result.sPrevMtUsageQy));
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
GamGasUsageSttusMngModule.prototype.enableListButtonItem = function() {

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
GamGasUsageSttusMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#usageMtYear').enable();
		this.$('#usageMtMon').enable();
		this.$('#mngFeeJobSe').enable();
		this.$('#prevMtUsageQy').enable();
		this.$('#saidMtUsageQy').enable();
		this.$('#applcCoef').enable();
		this.$('#netUsageQy').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#usageMtYear').disable();
			this.$('#usageMtMon').disable();
			this.$('#mngFeeJobSe').disable();
			this.$('#prevMtUsageQy').enable();
			this.$('#saidMtUsageQy').enable();
			this.$('#applcCoef').enable();
			this.$('#netUsageQy').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#usageMtYear').disable();
			this.$('#usageMtMon').disable();
			this.$('#mngFeeJobSe').disable();
			this.$('#prevMtUsageQy').disable();
			this.$('#saidMtUsageQy').disable();
			this.$('#applcCoef').disable();
			this.$('#netUsageQy').disable();
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
GamGasUsageSttusMngModule.prototype.disableDetailInputItem = function() {

	this.$('#usageMtYear').disable();
	this.$('#usageMtMon').disable();
	this.$('#mngFeeJobSe').disable();
	this.$('#prevMtUsageQy').disable();
	this.$('#saidMtUsageQy').disable();
	this.$('#applcCoef').disable();
	this.$('#netUsageQy').disable();
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
GamGasUsageSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamGasUsageSttusMngModule();

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
							<th>시설 업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th>적용 계수</th>
							<td>
								<input type="text" size="10" id="sApplcCoef" class="ygpaNumber" data-decimal-point="2" />
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
				<li><a href="#listTab" class="emdTab">가스 사용현황 </a></li>
				<li><a href="#detailTab" class="emdTab">가스 사용현황 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:20%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd">추가</button>
									<button id="btnDelete">삭제</button>
	                                <button id="btnExcelDownload">엑셀다운로드</button>
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
								<th style="width:15%; height:26;">사용 년월</th>
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
									<div id="gasUsageSttusChart" style="width:515px;height:415px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">업무 구분</th>
								<td>
									<input id="mngFeeJobSeNm" type="hidden"/>
									<select id="mngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">시설 코드</th>
								<td><input type="text" size="20" id="mngFeeFcltyCd" disabled/></td>
							</tr>
							<tr>
								<th width="15%" height="26">시설 명</th>
								<td><input type="text" size="20" id="mngFeeFcltyNm" disabled/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">전월 사용 량</th>
								<td><input type="text" size="20" id="prevMtUsageQy" disabled/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">당월 사용 량</th>
								<td><input type="text" size="20" id="saidMtUsageQy"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">적용 계수</th>
								<td><input type="text" size="20" id="applcCoef"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">순 사용 량</th>
								<td><input type="text" size="20" id="netUsageQy"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">등록일시</th>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">수정일시</th>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnSave" class="buttonSave">저장</button>
								<button id="btnRemove" class="buttonDelete">삭제</button>
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
