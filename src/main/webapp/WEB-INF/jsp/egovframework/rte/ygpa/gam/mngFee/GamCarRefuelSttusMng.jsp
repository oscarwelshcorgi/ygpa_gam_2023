<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCarRefuelSttusMng.jsp
 * @Description : 차량 주유현황
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
 * @FUNCTION NAME : GamCarRefuelSttusMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCarRefuelSttusMngModule() {}

GamCarRefuelSttusMngModule.prototype = new EmdModule(1000, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectCarRefuelSttusMng.do',
		dataType : 'json',
		colModel : [
					{display:'연료 구분', 		name:'fuelKnd',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 명', 		name:'carNm',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 등록 번호', 	name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
					{display:'합계', 			name:'total',		width:60, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'소계', 			name:'mtotal',		width:60, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'1월', 			name:'m1',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'2월', 			name:'m2',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'3월', 			name:'m3',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'4월', 			name:'m4',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'5월', 			name:'m5',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'6월', 			name:'m6',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'7월', 			name:'m7',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'8월', 			name:'m8',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'9월', 			name:'m9',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'10월', 			name:'m10',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'11월', 			name:'m11',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'},
					{display:'12월', 			name:'m12',			width:47, 		sortable:false,		align:'right',		displayFormat: 'number'}
					],
		showTableToggleBtn: false,
		height : 'auto',
		mergeRows : 'fuelKnd,total',
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
		module._mainKeyValue = row.carRegistNo;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.carRegistNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#sRefuelMt').on('change', {module: this}, function(event) {
		event.data.module.$('#refuelMt').val(event.data.module.$('#sRefuelMt').val());
	});

	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.drawChart = function() {

	var values = this.getFormValues('#detailForm');
	var fuelArr=[];
	var maxFuel=0;
	var fuel=0;
	for (var i=0; i<12; i++) {
		fuel=values['m'+(i+1)].replace(",","")*1;
		fuelArr[i]={month: (i+1), gauge: fuel};
		if (maxFuel<fuel) {
			maxFuel=fuel;
		}
	};
	if (maxFuel<10) {
		maxFuel=10;
	}
	if (this.barChart==null) {
		this.barChart = new dhtmlXChart({
			view			: "bar",
			container		: this.$('#fuelChart')[0],
			value			: "#gauge#",
			color			: "#000BE0",
            gradient		: "rising",
			width			: 30,
			label			: "#gauge#",
			tooltip			: "#gauge# 리터",
			xAxis			: {
				title 		: "차량 연간 주유 현황",
				template	: "#month# 월"
			},
			yAxis			: {
				start		: 0,
				end			: maxFuel + 10,
				step		: Math.ceil(maxFuel / 10),
				title		: "주유량,리터"
			}
		});
	} else {
		this.barChart.clearAll();
		this.barChart.define("yAxis", {
			start : 0,
			end : maxFuel + 10,
			step : Math.ceil(maxFuel / 10),
			title : "주유량,리터"
		});
	}
	this.barChart.parse(fuelArr, "json");
	this.barChart.refresh();

};

<%
/**
 * @FUNCTION NAME : allSelectQueryFuelKnd
 * @DESCRIPTION   : 조회조건 주유구분의 모든 항목을 선택한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.allSelectQueryFuelKnd = function() {

	$('input[name="FuelKndCheck"]').each(function(){
		$(this).attr('checked', true);
	});

};

<%
/**
 * @FUNCTION NAME : allUnselectQueryFuelKnd
 * @DESCRIPTION   : 조회조건 주유구분의 모든 항목을 선택해제한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.allUnselectQueryFuelKnd = function() {

	$('input[name="FuelKndCheck"]').each(function(){
		$(this).attr('checked', false);
		//this.checked = false;
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
GamCarRefuelSttusMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAllSelect':
	    	this.allSelectQueryFuelKnd();
			break;
		case 'btnAllUnselect':
	    	this.allUnselectQueryFuelKnd();
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
		case 'btnExcelUpload':
			this.uploadExcel();
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
GamCarRefuelSttusMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
	this._mainKeyValue = '';
	this.loadData();
	this.enableListButtonItem();

};

<%
/**
 * @FUNCTION NAME : rowSpanGrid
 * @DESCRIPTION   : GRID DATA ROW SPAN
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.rowSpanGridData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (gridRowCount == 0) {
		return;
	}
	var fuelKnd = "";
	var startRowNo = 0;
	var endRowNo = 0;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (fuelKnd == "") {
			fuelKnd = row.fuelKnd;
		}
		if (row.fuelKnd != fuelKnd) {
			endRowNo = i - 1;
			if (endRowNo > startRowNo) {
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,0,(endRowNo - startRowNo) + 1);
				this.$('#mainGrid')[0].dgrid.setColVAlign("middle");
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,3,(endRowNo - startRowNo) + 1);
			}
			startRowNo = i;
			fuelKnd = row.fuelKnd;
		}
	}

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	if (this.$('#sFuelKndCheck1').is(":checked")) {
		this.$("#sFuelKnd1").val(this.$('#sFuelKndCheck1').val());
	} else {
		this.$("#sFuelKnd1").val('');
	}
	if (this.$('#sFuelKndCheck2').is(":checked")) {
		this.$("#sFuelKnd2").val(this.$('#sFuelKndCheck2').val());
	} else {
		this.$("#sFuelKnd2").val('');
	}
	if (this.$('#sFuelKndCheck3').is(":checked")) {
		this.$("#sFuelKnd3").val(this.$('#sFuelKndCheck3').val());
	} else {
		this.$("#sFuelKnd3").val('');
	}
	if (this.$('#sFuelKndCheck4').is(":checked")) {
		this.$("#sFuelKnd4").val(this.$('#sFuelKndCheck4').val());
	} else {
		this.$("#sFuelKnd4").val('');
	}
	if (this.$('#sFuelKndCheck5').is(":checked")) {
		this.$("#sFuelKnd5").val(this.$('#sFuelKndCheck5').val());
	} else {
		this.$("#sFuelKnd5").val('');
	}
	if (this.$('#sFuelKndCheck6').is(":checked")) {
		this.$("#sFuelKnd6").val(this.$('#sFuelKndCheck6').val());
	} else {
		this.$("#sFuelKnd6").val('');
	}
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
 GamCarRefuelSttusMngModule.prototype.refreshData = function() {

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
GamCarRefuelSttusMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
		this.$('#refuelMt').val(this.$('#sRefuelMt').val());
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectCarRefuelSttusMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				module.$('#refuelMt').val(module.$('#sRefuelMt').val());
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
GamCarRefuelSttusMngModule.prototype.selectData = function() {

	//this.rowSpanGridData();
	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (this._mode == 'query') {
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
	var carRegistNo = mainKeyValue;
	var mainRowNo = -1;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (row.carRegistNo == carRegistNo) {
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
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var carRegistNo = this.$('#carRegistNo').val();
	var m1 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m2 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m3 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m4 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m5 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m6 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m7 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m8 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m9 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m10 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m11 = Number(this.$('#m1').val().replace(/,/gi, ""));
	var m12 = Number(this.$('#m1').val().replace(/,/gi, ""));
	if (carRegistNo == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	if (m1 > 99999999.99 || m1 < 0) {
		alert('1월 주유량이 부정확합니다.');
		this.$("#m1").focus();
		return;
	}
	if (m2 > 99999999.99 || m2 < 0) {
		alert('2월 주유량이 부정확합니다.');
		this.$("#m2").focus();
		return;
	}
	if (m3 > 99999999.99 || m3 < 0) {
		alert('3월 주유량이 부정확합니다.');
		this.$("#m3").focus();
		return;
	}
	if (m4 > 99999999.99 || m4 < 0) {
		alert('4월 주유량이 부정확합니다.');
		this.$("#m4").focus();
		return;
	}
	if (m5 > 99999999.99 || m5 < 0) {
		alert('5월 주유량이 부정확합니다.');
		this.$("#m5").focus();
		return;
	}
	if (m6 > 99999999.99 || m6 < 0) {
		alert('6월 주유량이 부정확합니다.');
		this.$("#m6").focus();
		return;
	}
	if (m7 > 99999999.99 || m7 < 0) {
		alert('7월 주유량이 부정확합니다.');
		this.$("#m7").focus();
		return;
	}
	if (m8 > 99999999.99 || m8 < 0) {
		alert('8월 주유량이 부정확합니다.');
		this.$("#m8").focus();
		return;
	}
	if (m9 > 99999999.99 || m9 < 0) {
		alert('9월 주유량이 부정확합니다.');
		this.$("#m9").focus();
		return;
	}
	if (m10 > 99999999.99 || m10 < 0) {
		alert('10월 주유량이 부정확합니다.');
		this.$("#m10").focus();
		return;
	}
	if (m11 > 99999999.99 || m11 < 0) {
		alert('11월 주유량이 부정확합니다.');
		this.$("#m11").focus();
		return;
	}
	if (m12 > 99999999.99 || m12 < 0) {
		alert('12월 주유량이 부정확합니다.');
		this.$("#m12").focus();
		return;
	}
	this.doAction('/mngFee/gamInsertCarRefuelSttusMng.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.refreshData();
		}
		alert(result.resultMsg);
	});

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.deleteData = function() {

	var carRegistNo = this.$('#carRegistNo').val();
	if (carRegistNo == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteCarRefuelSttusMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadCarRefuelSttusMng.do');

};

<%
/**
 * @FUNCTION NAME : uploadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 업로드한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.uploadExcel = function() {

	this.uploadXlsFile('xlsCarRefuelSttusMngUpload', function(module, result) {
		module._mode = 'query';
		module._mainKeyValue = '';
		module.loadData();
		alert(result.resultMsg);
	}, '차량 주유현황 엑셀파일 업로드', '/mngFee/gamExcelUploadCarRefuelSttusMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	} else {
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
GamCarRefuelSttusMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#carRegistNo').disable();
		this.$('#fuelKnd').disable();
		this.$('#carNm').disable();
		this.$('#m1').enable();
		this.$('#m2').enable();
		this.$('#m3').enable();
		this.$('#m4').enable();
		this.$('#m5').enable();
		this.$('#m6').enable();
		this.$('#m7').enable();
		this.$('#m8').enable();
		this.$('#m9').enable();
		this.$('#m10').enable();
		this.$('#m11').enable();
		this.$('#m12').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#carRegistNo').disable();
			this.$('#fuelKnd').disable();
			this.$('#carNm').disable();
			this.$('#m1').enable();
			this.$('#m2').enable();
			this.$('#m3').enable();
			this.$('#m4').enable();
			this.$('#m5').enable();
			this.$('#m6').enable();
			this.$('#m7').enable();
			this.$('#m8').enable();
			this.$('#m9').enable();
			this.$('#m10').enable();
			this.$('#m11').enable();
			this.$('#m12').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#carRegistNo').disable();
			this.$('#fuelKnd').disable();
			this.$('#carNm').disable();
			this.$('#m1').disable();
			this.$('#m2').disable();
			this.$('#m3').disable();
			this.$('#m4').disable();
			this.$('#m5').disable();
			this.$('#m6').disable();
			this.$('#m7').disable();
			this.$('#m8').disable();
			this.$('#m9').disable();
			this.$('#m10').disable();
			this.$('#m11').disable();
			this.$('#m12').disable();
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
GamCarRefuelSttusMngModule.prototype.disableDetailInputItem = function() {

	this.$('#carRegistNo').disable();
	this.$('#fuelKnd').disable();
	this.$('#carNm').disable();
	this.$('#m1').disable();
	this.$('#m2').disable();
	this.$('#m3').disable();
	this.$('#m4').disable();
	this.$('#m5').disable();
	this.$('#m6').disable();
	this.$('#m7').disable();
	this.$('#m8').disable();
	this.$('#m9').disable();
	this.$('#m10').disable();
	this.$('#m11').disable();
	this.$('#m12').disable();
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
GamCarRefuelSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			this.drawChart();
			break;
	}

};

var module_instance = new GamCarRefuelSttusMngModule();

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
							<th style="width:8%; height:18;">주　유　년　도</th>
							<td>
								<select id="sRefuelMt">
									<option value="">선택</option>
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
                                </select>
							</td>
							<th style="width:8%; height:18;">차량 등록 번호</th>
							<td>
								<input id="sCarRegistNo" type="text" size="15" maxlength="14">
							</td>
							<th style="width:8%; height:18;">주　유　구　분</th>
							<td>
								<input id="sFuelKnd1" type="hidden" />
								<input id="sFuelKnd2" type="hidden" />
								<input id="sFuelKnd3" type="hidden" />
								<input id="sFuelKnd4" type="hidden" />
								<input id="sFuelKnd5" type="hidden" />
								<input id="sFuelKnd6" type="hidden" />
								휘발류<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck1" style="vertical-align: middle;" value="휘발류" checked="checked" class="chk">
								경유<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck2" style="vertical-align: middle;" value="경유"	checked="checked"	class="chk">
								LPG<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck3" style="vertical-align: middle;" value="LPG"	checked="checked"	class="chk">
								전기<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck4" style="vertical-align: middle;" value="전기"	checked="checked"	class="chk">
								하이브리드<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck5" style="vertical-align: middle;" value="하이브리드"	checked="checked"	class="chk">
								기타<input type="checkbox" size="10" name="FuelKndCheck" id="sFuelKndCheck6" style="vertical-align: middle;" value="기타"	checked="checked"	class="chk">
								<button id="btnAllSelect">선택</button>
								<button id="btnAllUnselect">해제</button>
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
				<li><a href="#listTab" class="emdTab">차량주유 현황</a></li>
				<li><a href="#detailTab" class="emdTab">차량주유 현황 상세</a></li>
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
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
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
						<input type="hidden" id="refuelMt"/>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th style="width:15%; height:23;">차량 등록 번호</th>
								<td><input type="text" size="20" id="carRegistNo" readonly="readonly"/></td>
								<th style="width:15%; height:23;">연　료　구　분</th>
								<td><input type="text" size="20" id="fuelKnd" readonly="readonly"/></td>
								<th style="width:15%; height:23;">차　　량　　명</th>
								<td><input type="text" size="20" id="carNm" readonly="readonly"/></td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th style="width:15%; height:23;">1월 　　주유량</th>
								<td style="width:15%;"><input type="text" size="20" id="m1" class="ygpaNumber"></td>
								<td rowspan="15" style="padding-left:4px;">
									<div id="fuelChart" style="width:670px;height:370px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">2월 　　주유량</th>
								<td><input type="text" size="20" id="m2" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">3월 　　주유량</th>
								<td><input type="text" size="20" id="m3" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">4월 　　주유량</th>
								<td><input type="text" size="20" id="m4" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">5월 　　주유량</th>
								<td><input type="text" size="20" id="m5" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">6월 　　주유량</th>
								<td><input type="text" size="20" id="m6" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">7월 　　주유량</th>
								<td><input type="text" size="20" id="m7" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">8월 　　주유량</th>
								<td><input type="text" size="20" id="m8" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">9월 　　주유량</th>
								<td><input type="text" size="20" id="m9" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">10월　　주유량</th>
								<td><input type="text" size="20" id="m10" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">11월　　주유량</th>
								<td><input type="text" size="20" id="m11" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th style="width:15%; height:23;">12월　　주유량</th>
								<td><input type="text" size="20" id="m12" class="ygpaNumber"></td>
							</tr>
                        </table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
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
