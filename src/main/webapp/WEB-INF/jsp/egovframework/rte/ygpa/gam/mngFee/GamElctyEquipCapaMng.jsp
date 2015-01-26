<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamElctyEquipCapaMng.jsp
 * @Description : 전기 설비 용량 관리
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
 * @FUNCTION NAME : GamElctyEquipCapaMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamElctyEquipCapaMngModule() {}

GamElctyEquipCapaMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamElctyEquipCapaMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectElctyEquipCapaMng.do',
		dataType : 'json',
		colModel : [
					{display:'관리 년도',		name:'mngYear',			width:90, 		sortable:false,		align:'center'},
					{display:'전기 시설 명',	name:'elctyEquipNm',	width:235, 		sortable:false,		align:'left'},
					{display:'전기 구분',		name:'elctySeNm',		width:90, 		sortable:false,		align:'center'},
					{display:'설비 용량',		name:'equipCapa',		width:110, 		sortable:false,		align:'right'},
					{display:'계약 용량',		name:'ctrtCapa',		width:110, 		sortable:false,		align:'right'},
					{display:'사용 전압',		name:'usageVolt',		width:110, 		sortable:false,		align:'right'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumEquipCapa').val(data.sumEquipCapa);
			module.$('#sumCtrtCapa').val(data.sumCtrtCapa);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngYear + row.fcltsMngGroupNo + row.mngSeq;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngYear + row.fcltsMngGroupNo + row.mngSeq;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#mngYear').on('change',{module:this}, function(event){
		event.data.module.getNewMngSeq();
		if (module._mode == 'insert') {
			event.data.module.getPrevYearCapa();
		}
	});

	this.$('#elctyEquipNm').on('change',{module:this}, function(event){
		if (module._mode == 'insert') {
			event.data.module.getPrevYearCapa();
		}
	});

	this._mode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : makeWordWrap
 * @DESCRIPTION   : WORD WRAP 문자열을 만든다.
 * @PARAMETER     : NONE
**/
%>
GamElctyEquipCapaMngModule.prototype.makeWordWrap = function(argString, argWordWrapSize) {

	if (argString == null || argString == "") {
		return ("");
	}
	if (argWordWrapSize == null || argWordWrapSize <= 0) {
		return (argString);
	}
	var stringLength = argString.length;
	var wordWrapString = "";
	var splitString = "";
	var i = 0;
	for (i=0; i<stringLength; i+=argWordWrapSize) {
		if (stringLength > (i+argWordWrapSize)) {
			splitString = argString.substring(i, i + argWordWrapSize);
		} else {
			splitString = argString.substring(i, stringLength);
		}
		if (i > 0) {
			wordWrapString = wordWrapString + "<br />" + splitString;
		} else {
			wordWrapString = splitString;
		}
	}
	return (wordWrapString);

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamElctyEquipCapaMngModule.prototype.drawChart = function() {

	var dataValueArr = [];
	var maxDataValue = 0;
	var dataValue1 = 0;
	var dataValue2 = 0;
	var dataValue3 = 0;
	var dataCount = 0;
	var elctyEquipNm = "";
	var dataValueName = "설비용량, 계약용량 (kw/h)";
	var dispElctyEquipNm = "";
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamSelectElctyEquipCapaMngChart.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			dataCount = result.resultList[0]['dataCount']*1;
			for (var i=0; i<dataCount; i++) {
				elctyEquipNm = result.resultList[i]['elctyEquipNm'];
				dispElctyEquipNm = module.makeWordWrap(elctyEquipNm.replace(/ /gi, ""), 6);
				dataValue1 = result.resultList[i]['equipCapa']*1;
				dataValue2 = result.resultList[i]['ctrtCapa']*1;
				dataValue3 = result.resultList[i]['usageVolt']*1;
				dataValueArr[i] = {equip: dispElctyEquipNm, value1: dataValue1, value2: dataValue2, value3: dataValue3};
				if (maxDataValue < dataValue1) {
					maxDataValue = dataValue1;
				}
				if (maxDataValue < dataValue2) {
					maxDataValue = dataValue2;
				}
				if (maxDataValue < dataValue3) {
					maxDataValue = dataValue3;
				}
			};
		} else {
			for (var i=0; i<1; i++) {
				dataValue1 = 0;
				dataValue2 = 0;
				dataValue3 = 0;
				dataValueArr[i] = {equip: dispElctyEquipNm, value1: dataValue1, value2: dataValue2, value3: dataValue3};
			};
		}
		if (maxDataValue < 10) {
			maxDataValue = 10;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#elctyEquipCapaChart')[0],
				value			: "#value1#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#value1#",
				tooltip			: "#value1# kw/h",
				xAxis			: {
					title 		: "전기 설비 용량",
					template	: "#equip#"
				},
				yAxis			: {
					start		: 0,
					end			: maxDataValue + 10,
					step		: Math.ceil(maxDataValue / 10),
					title		: dataValueName
				}
			});
			module.barChart.addSeries({
				value				: "#value2#",
				color				: "#66cc00",
				label				: "#value2#",
				tooltip				: {
					template		: "#value2# kw/h"
	            }
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start			: 0,
				end				: maxDataValue + 10,
				step			: Math.ceil(maxDataValue / 10),
				title			: dataValueName
			});
		}
		module.barChart.parse(dataValueArr, "json");
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
GamElctyEquipCapaMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupFcltsMngGroup':
			if (msg == 'ok') {
				this.$('#fcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#elctyEquipNm').val(value.fcltsMngGroupNm);
				this.getNewMngSeq();
				if (this._mode == 'insert') {
					this.getPrevYearCapa();
				}
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
GamElctyEquipCapaMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'popupFcltsMngGroup':
			this.doExecuteDialog('popupFcltsMngGroup', '시설물 관리 그룹 선택', '/popup/showFcltsMngGroup.do', null);
			break;
		case 'btnGraphSearch':
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
GamElctyEquipCapaMngModule.prototype.onSubmit = function() {

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
GamElctyEquipCapaMngModule.prototype.loadData = function() {

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
 GamElctyEquipCapaMngModule.prototype.refreshData = function() {

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
GamElctyEquipCapaMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
		this.$('#graphSe').val(this._graphSe);
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectElctyEquipCapaMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				module.$('#graphSe').val(module._graphSe);
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
GamElctyEquipCapaMngModule.prototype.selectData = function() {

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
	var mngYear = this._mainKeyValue.substring(0,4);
	var fcltsMngGroupNo = this._mainKeyValue.substring(4,18);
	var mngSeq = this._mainKeyValue.substring(18,21);
	this.$("#mainGrid").selectFilterRow([{col:"mngYear", filter:mngYear},
	                                     {col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
	                                     {col:"mngSeq", filter:mngSeq}]);
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
GamElctyEquipCapaMngModule.prototype.addData = function() {

	var mngYear = this.$('#sMngYear').val();
	this.$('#mngYear').val(mngYear);
	this.$('#elctySe').val('1');
	this.$('#elctySeNm').val('산업용');
	this.$('#equipCapa').val("0");
	this.$('#ctrtCapa').val("0");
	this.$('#usageVolt').val("0");
	this.enableDetailInputItem();
	this.$('#mngYear').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/

%>
GamElctyEquipCapaMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mngYear = this.$('#mngYear').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var mngSeq = this.$('#mngSeq').val();
	var elctyEquipNm = this.$('#elctyEquipNm').val();
	var elctySe = this.$('#elctySe').val();
	var equipCapa = Number(this.$('#equipCapa').val().replace(/,/gi, ""));
	var ctrtCapa = Number(this.$('#ctrtCapa').val().replace(/,/gi, ""));
	var usageVolt = Number(this.$('#usageVolt').val().replace(/,/gi, ""));
	if (mngYear > "9999"  || mngYear < "2000" || mngYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngYear").focus();
		return;
	}
	if (fcltsMngGroupNo == ""  || fcltsMngGroupNo.length != 14) {
		alert('시설물 관리 그룹이 부정확합니다.');
		return;
	}
	if (mngSeq > "999"  || mngSeq < "000" || mngSeq == "") {
		alert('관리 순번이 부정확합니다.');
		return;
	}
	if (elctySe != "1" && elctySe != "2") {
		alert('전기 구분이 부정확합니다.');
		this.$("#elctySe").focus();
		return;
	}
	if (elctyEquipNm == "") {
		alert('전기 설비 명이 부정확합니다.');
		return;
	}
	if (equipCapa > 9999999999 || equipCapa < 0) {
		alert('설비 용량이 부정확합니다.');
		this.$("#equipCapa").focus();
		return;
	}
	if (ctrtCapa > 9999999999 || ctrtCapa < 0) {
		alert('계약 용량이 부정확합니다.');
		this.$("#ctrtCapa").focus();
		return;
	}
	if (ctrtCapa > equipCapa) {
		alert('계약 용량이 설비 용량보다 큽니다.');
		this.$("#ctrtCapa").focus();
		return;
	}
	if (usageVolt > 9999999 || usageVolt < 0) {
		alert('사용 전압이 부정확합니다.');
		this.$("#usageVolt").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = mngYear + fcltsMngGroupNo + mngSeq;
		this.doAction('/mngFee/gamInsertElctyEquipCapaMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateElctyEquipCapaMng.do', inputVO, function(module, result) {
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
GamElctyEquipCapaMngModule.prototype.deleteData = function() {

	var mngYear = this.$('#mngYear').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var mngSeq = this.$('#mngSeq').val();
	if (mngYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngYear").focus();
		return;
	}
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹이 부정확합니다.');
		return;
	}
	if (mngSeq == "") {
		alert('관리 순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteElctyEquipCapaMng.do', deleteVO, function(module, result) {
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
GamElctyEquipCapaMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryMngYear = this.$('#sMngYear').val();
	var mtCnt=0;
	if (confirm("이전년도의 자료를 [" + sQueryMngYear + "년] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectElctyEquipCapaMngYearCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		mtCnt=result.resultList[0]['mtCnt']*1;
		if (mtCnt > 0) {
			alert('[' + sQueryMngYear + '년] 자료가 존재합니다.');
			return;
		}
		module.doAction('/mngFee/gamCopyElctyEquipCapaMng.do', searchVO, function(module, result) {
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
GamElctyEquipCapaMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadElctyEquipCapaMng.do');

};

<%
/**
 * @FUNCTION NAME : getNewMngSeq
 * @DESCRIPTION   : 새로운 관리 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyEquipCapaMngModule.prototype.getNewMngSeq = function() {

	var mngYear = this.$('#mngYear').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	if (mngYear == "" || fcltsMngGroupNo == "") {
		this.$('#mngSeq').val('');
		return;
	}
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/code/gamSelectElctyEquipCapaMngNewMngSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#mngSeq').val(result.sNewMngSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : getPrevYearCapa
 * @DESCRIPTION   : 전년도 용량을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamElctyEquipCapaMngModule.prototype.getPrevYearCapa = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#mngYear').val() == "" || this.$('#fcltsMngGroupNo').val() == "" || this.$('#mngFeeFcltyCd').val() == "" || this.$('#elctyEquipNm').val() == "") {
		return;
	}
	this.doAction('/mngFee/gamSelectElctyEquipCapaMngPrevYearCapa.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			var elctyCapa = result.result[0]['elctyCapa'];
			var ctrtCapa = result.result[0]['ctrtCapa'];
			var usageVolt = result.result[0]['usageVolt'];
			if (elctyCapa == "") {
				elctyCapa = "0";
			}
			if (ctrtCapa == "") {
				ctrtCapa = "0";
			}
			if (usageVolt == "") {
				usageVolt = "0";
			}
			module.$('#elctyCapa').val(elctyCapa);
			module.$('#ctrtCapa').val(ctrtCapa);
			module.$('#usageVolt').val(usageVolt);
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
GamElctyEquipCapaMngModule.prototype.enableListButtonItem = function() {

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
GamElctyEquipCapaMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#mngYear').enable();
		this.$('#elctyEquipNm').enable();
		this.$('#elctySe').enable();
		this.$('#equipCapa').enable();
		this.$('#ctrtCapa').enable();
		this.$('#usageVolt').enable();
		this.$('#popupFcltsMngGroup').enable();
		this.$('#popupFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#mngYear').disable();
			this.$('#elctyEquipNm').enable();
			this.$('#elctySe').enable();
			this.$('#equipCapa').enable();
			this.$('#ctrtCapa').enable();
			this.$('#usageVolt').enable();
			this.$('#popupFcltsMngGroup').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#mngYear').disable();
			this.$('#elctyEquipNm').disable();
			this.$('#elctySe').disable();
			this.$('#equipCapa').disable();
			this.$('#ctrtCapa').disable();
			this.$('#usageVolt').disable();
			this.$('#popupFcltsMngGroup').disable({disableClass:"ui-state-disabled"});
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
GamElctyEquipCapaMngModule.prototype.disableDetailInputItem = function() {

	this.$('#mngYear').disable();
	this.$('#elctyEquipNm').disable();
	this.$('#elctySe').disable();
	this.$('#equipCapa').disable();
	this.$('#ctrtCapa').disable();
	this.$('#usageVolt').disable();
	this.$('#popupFcltsMngGroup').disable({disableClass:"ui-state-disabled"});
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
GamElctyEquipCapaMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamElctyEquipCapaMngModule();

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
							<th>전기 구분</th>
							<td>
								<select id="sElctySe">
									<option value="" selected>전체</option>
									<option value="1">산업용</option>
									<option value="2">일반용</option>
								</select>
							</td>
							<th>전기 설비 명</th>
							<td>
								<input type="text" size="20" id="sElctyEquipNm" maxlength="80">
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
				<li><a href="#listTab" class="emdTab">전기 설비 용량 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">전기 설비 용량 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="text-align:center;">조회 자료수</th>
								<td><input type="text" size="15" id=totalCount class="ygpaNumber" disabled="disabled" /></td>
								<th style="text-align:center;">총 설비 용량</th>
								<td><input type="text" size="15" id="sumEquipCapa" class="ygpaNumber" disabled="disabled" /></td>
								<th style="text-align:center;">총 계약 용량</th>
								<td><input type="text" size="15" id="sumCtrtCapa" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
								<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
								<button id="btnCopy">이전년도 자료복사</button>
                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:25;">관　리　년　도</th>
								<td>
									<select id="mngYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									&nbsp; &nbsp;
									<input type="text" size="10" id="mngSeq" disabled/>
								</td>
								<td rowspan="11" style="padding-left:4px;">
									<div id="elctyEquipCapaChart" style="width:510px;height:380px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">시설물그룹번호</th>
								<td>
									<input type="text" size="13" id="fcltsMngGroupNo" disabled/>
									<button id="popupFcltsMngGroup" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">전기　시설　명</th>
								<td>
									<input type="text" size="25" id="elctyEquipNm" maxlength="80"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">전　기　구　분</th>
								<td>
									<input id="elctySeNm" type="hidden"/>
									<select id="elctySe">
										<option value="1">산업용</option>
										<option value="2">일반용</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">설　비　용　량</th>
								<td>
									<input type="text" size="21" id="equipCapa" class="ygpaNumber" maxlength="13"/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">계　약　용　량</th>
								<td>
									<input type="text" size="21" id="ctrtCapa" class="ygpaNumber" maxlength="13"/> kW
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">사　용　전　압</th>
								<td>
									<input type="text" size="21" id="usageVolt" class="ygpaNumber" maxlength="9"/>  V
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">등　　록　　자</th>
								<td>
									<input type="text" size="25" id="regUsr" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">등　록　일　시</th>
								<td>
									<input type="text" size="25" id="registDt" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">수　　정　　자</th>
								<td>
									<input type="text" size="25" id="updUsr" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25;">수　정　일　시</th>
								<td>
									<input type="text" size="25" id="updtDt" disabled>
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
