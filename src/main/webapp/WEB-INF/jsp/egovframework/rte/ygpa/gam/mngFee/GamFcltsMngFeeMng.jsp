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
		url : '/mngFee/gamSelectFcltsMngFeeMngDetailList.do',
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
		height: '180'
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

	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sStartMngMt').val(mon);
	this.$('#sEndMngMt').val(mon);
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});

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
				this.$("#usageAr").focus();
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
				this.loadDetail();
				this.enableDetailInputItem();
				this.enableSubDetailInputItem();
				this.deleteData();
			}
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
			break;
		case 'btnFcltsFeeMngNtic':
			this.openFcltsFeeMngNticModule();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
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
GamFcltsMngFeeMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
	this._detailmode = 'query';
	this._mainKeyValue = '';
	this._detailKeyValue = '';
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
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();
	var mainMngMt = "";
	var mainMngFeeJobSe = "";

	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.makeFormValues('#detailForm', row[0]);
	this.makeDivValues('#detailForm', row[0]);
	mainMngMt = this.$('#mainMngMt').val();
	mainMngFeeJobSe = this.$('#mainMngFeeJobSe').val();
	var detailOpt=this.makeFormArgs('#subDetailForm');
	$.each(detailOpt, function(){
		if (this.name == 'mngMt') {
			this.value = mainMngMt;
		} else if (this.name == 'mngFeeJobSe') {
			this.value = mainMngFeeJobSe;
		}
	});
	this.$('#detailGrid').flexOptions({params:detailOpt}).flexReload();
	this.makeFormValues('#subDetailForm', {});
	this.makeDivValues('#subDetailForm', {});

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.selectData = function() {

	if (this._mode != 'insert' && this._mode != 'modify') {
		return;
	}
	var mainKeyValue = this._mainKeyValue;
	if (mainKeyValue == "") {
		return;
	}
	var mainMngMt = mainKeyValue.substring(0,6);
	var mainMngFeeJobSe = mainKeyValue.substring(6,7);
	var gridRowCount = this.$("#mainGrid").flexGetData().length;
	var rowNo = -1;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (row.mainMngMt == mainMngMt && row.mainMngFeeJobSe == mainMngFeeJobSe) {
			rowNo = i;
			break;
		}
	}
	if (rowNo >= 0) {
		this.$("#mainGrid").selectRowId(rowNo);
		this._mode = 'modify';
		//this.$("#mainTab").tabs("option", {active: 1});
		this.loadDetail();
		this.enableDetailInputItem();
		this.enableSubDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : selectDetailData
 * @DESCRIPTION   : DETAIL DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.selectDetailData = function() {
console.log('asdf');
	if (this._detailmode != 'insert' && this._detailmode != 'modify') {
		return;
	}
	var detailKeyValue = this._detailKeyValue;
	if (detailKeyValue == "") {
		return;
	}
	var mngMt = detailKeyValue.substring(0,6);
	var mngFeeJobSe = detailKeyValue.substring(6,7);
	var mngSeq = detailKeyValue.substring(7,10);
	var detailGridRowCount = this.$("#detailGrid").flexGetData().length;
	var detailRowNo = -1;
	for(var i=0; i<detailGridRowCount; i++) {
		var row = this.$("#detailGrid").flexGetRow(i+1);
		if (row.mngMt == mngMt && row.mngFeeJobSe == mngFeeJobSe && row.mngSeq == mngSeq) {
			detailRowNo = i;
			break;
		}
	}
	if (detailRowNo >= 0) {
		this.$("#detailGrid").selectRowId(detailRowNo);
		this._detailmode = 'modify';
		this.loadSubDetail();
		this.enableSubDetailInputItem();
	}

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
	this.doAction('<c:url value="/mngFee/gamSelectFcltsMngFeeMngDetailMaxMngSeq.do" />', searchVO, function(module, result) {
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
	if (mainMngMtYear > "9999"  || mainMngMtYear < "2000" || mainMngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mainMngMtYear").focus();
		return;
	}
	if (mainMngMtMon > "12"  || mainMngMtMon < "01" || mainMngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mainMngMtMon").focus();
		return;
	}
	if (mainMngFeeJobSe != "M" && mainMngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		this.$("#mainMngFeeJobSe").focus();
		return;
	}
	if (mainFcltyMngFee > 999999999999 || mainFcltyMngFee < 0) {
		alert('시설 관리 용역비가 부정확합니다.');
		this.$("#mainFcltyMngFee").focus();
		return;
	}
	if (mainElctyFee > 999999999999 || mainElctyFee < 0) {
		alert('전기 요금이 부정확합니다.');
		this.$("#mainElctyFee").focus();
		return;
	}
	if (mainWaterFee > 999999999999 || mainWaterFee < 0) {
		alert('상하수도 요금이 부정확합니다.');
		this.$("#mainWaterFee").focus();
		return;
	}
	if (mainGasFee > 999999999999 || mainGasFee < 0) {
		alert('도시가스 요금이 부정확합니다.');
		this.$("#mainGasFee").focus();
		return;
	}
	if (mainEnvFee > 999999999999 || mainEnvFee < 0) {
		alert('환경개선 부담금이 부정확합니다.');
		this.$("#mainEnvFee").focus();
		return;
	}
	if (mainMngTotalFee > 999999999999 || mainMngTotalFee < 0) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (mainMngTotalFee != (mainFcltyMngFee + mainElctyFee + mainWaterFee + mainGasFee + mainEnvFee)) {
		alert('관리비 합계가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('/mngFee/gamInsertFcltsMngFeeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainKeyValue = mainMngMtYear + mainMngMtMon + mainMngFeeJobSe;
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateFcltsMngFeeMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainKeyValue = mainMngMtYear + mainMngMtMon + mainMngFeeJobSe;
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
	if (mainMngMtYear > "9999"  || mainMngMtYear < "2000" || mainMngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mainMngMtYear").focus();
		return;
	}
	if (mainMngMtMon > "12"  || mainMngMtMon < "01" || mainMngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mainMngMtMon").focus();
		return;
	}
	if (mainMngFeeJobSe != "M" && mainMngFeeJobSe != "E") {
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
				this._mode = 'query';
				this._detailmode = 'query';
				this._mainKeyValue = '';
				this._detailKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

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
	var mngSeq = "";
	if (mainMngMtYear > "9999"  || mainMngMtYear < "2000" || mainMngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		return;
	}
	if (mainMngMtMon > "12"  || mainMngMtMon < "01" || mainMngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		return;
	}
	if (mainMngFeeJobSe != "M" && mainMngFeeJobSe != "E") {
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
	this.$('#paynticNoTmlmt').val('');
	this.$('#rcivSe').val('0');
	this.$('#rcivSeNm').val('');
	this.$('#rcivDt').val('');
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
	if (mngMt > "999912"  || mngMt < "200001" || mngMt == "") {
		alert('관리 월이 부정확합니다.');
		return;
	}
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
		alert('업무 구분이 부정확합니다.');
		return;
	}
	if (mngSeq > "999"  || mngSeq < "001" || mngSeq == "") {
		alert('관리 월이 부정확합니다.');
		return;
	}
	if (usageAr > 99999.99 || usageAr < 0) {
		alert('면적이 부정확합니다.');
		this.$("#usageAr").focus();
		return;
	}
	if (entrpscd == "") {
		alert('부과 업체가 부정확합니다.');
		this.$("#usageAr").focus();
		return;
	}
	if (mngFee > 999999999999 || mngFee < 0) {
		alert('시설 관리 용역비가 부정확합니다.');
		this.$("#mainFcltyMngFee").focus();
		return;
	}
	if (elctyFee > 999999999999 || elctyFee < 0) {
		alert('전기 요금이 부정확합니다.');
		this.$("#mainElctyFee").focus();
		return;
	}
	if (waterFee > 999999999999 || waterFee < 0) {
		alert('상하수도 요금이 부정확합니다.');
		this.$("#mainWaterFee").focus();
		return;
	}
	if (gasFee > 999999999999 || gasFee < 0) {
		alert('도시가스 요금이 부정확합니다.');
		this.$("#mainGasFee").focus();
		return;
	}
	if (envFee > 999999999999 || envFee < 0) {
		alert('환경개선 부담금이 부정확합니다.');
		this.$("#mainEnvFee").focus();
		return;
	}
	if (mngTotalFee > 999999999999 || mngTotalFee < 0) {
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
	if (mngMt > "999912"  || mngMt < "200001" || mngMt == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMt").focus();
		return;
	}
	if (mngFeeJobSe != "M" && mngFeeJobSe != "E") {
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
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelFcltsMngFeeMng.do');

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
 * @FUNCTION NAME : openFcltsFeeMngNticModule
 * @DESCRIPTION   : 시설물 관리비 고지화면 MODULE OPEN
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngFeeMngModule.prototype.openFcltsFeeMngNticModule = function() {

	var gridRowCount = this.$("#mainGrid").flexGetData().length;
	alert(gridRowCount);

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
		this.$('#btnFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
			return;
		}
		var nhtIsueYn = row['mainNhtIsueYn'];
		if (nhtIsueYn == "Y") {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngNtic').enable();
			this.$('#btnFcltsFeeMngNtic').removeClass('ui-state-disabled');
			this.$('#btnFcltsFeeMngInqire').enable();
			this.$('#btnFcltsFeeMngInqire').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "N") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnFcltsFeeMngNtic').enable();
			this.$('#btnFcltsFeeMngNtic').removeClass('ui-state-disabled');
			this.$('#btnFcltsFeeMngInqire').enable();
			this.$('#btnFcltsFeeMngInqire').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "X") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngNtic').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFcltsFeeMngInqire').disable({disableClass:"ui-state-disabled"});
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
		this.$('#btnAddDetail').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveDetail').enable();
		this.$('#btnSaveDetail').removeClass('ui-state-disabled');
		this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
		this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
	} else {
		if (nhtIsueYn == "Y") {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
			this.$('#btnAddDetail').enable();
			this.$('#btnAddDetail').removeClass('ui-state-disabled');
			this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').enable();
			this.$('#btnCancelNticIssue').removeClass('ui-state-disabled');
			this.$('#btnPrintNticIssue').enable();
			this.$('#btnPrintNticIssue').removeClass('ui-state-disabled');
		} else if (nhtIsueYn == "N") {
			this.$('#usageAr').enable();
			this.$('#popupDataEntrpscd').enable();
			this.$('#popupDataEntrpscd').removeClass('ui-state-disabled');
			this.$('#mngFee').enable();
			this.$('#elctyFee').enable();
			this.$('#waterFee').enable();
			this.$('#gasFee').enable();
			this.$('#envFee').enable();
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
		} else if (nhtIsueYn == "X") {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
			this.$('#btnAddDetail').enable();
			this.$('#btnAddDetail').removeClass('ui-state-disabled');
			this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#usageAr').disable();
			this.$('#popupDataEntrpscd').disable({disableClass:"ui-state-disabled"});
			this.$('#mngFee').disable();
			this.$('#elctyFee').disable();
			this.$('#waterFee').disable();
			this.$('#gasFee').disable();
			this.$('#envFee').disable();
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
	this.$('#btnAddDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSaveDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDeleteDetail').disable({disableClass:"ui-state-disabled"});
	this.$('#btnProcessNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnCancelNticIssue').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrintNticIssue').disable({disableClass:"ui-state-disabled"});

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
				this.loadDetail();
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
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th width="10%" height="20" style="text-align: center">총관리용역</th>
								<td><input type="text" size="13" id="sumFcltyMngFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20" style="text-align: center">총전기요금</th>
								<td><input type="text" size="13" id="sumElctyFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20" style="text-align: center">총상하수도</th>
								<td><input type="text" size="13" id="sumWaterFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20" style="text-align: center">총도시가스</th>
								<td><input type="text" size="13" id="sumGasFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20" style="text-align: center">총환경개선</th>
								<td><input type="text" size="13" id="sumEnvFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20" style="text-align: center">총관리비합</th>
								<td><input type="text" size="13" id="sumMngTotalFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<th width="10%" height="20" style="text-align: center">자료수</th>
							<td>
								<input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled" />
							</td>
							<td style="text-align: right">
								<button id="btnAdd">　　추　가　　</button>
								<button id="btnDelete">　　삭　제　　</button>
								<button id="btnExcelDownload">엑셀　다운로드</button>
								<button id="btnExcelUpload">엑셀　　업로드</button>
								<button id="btnFcltsFeeMngNtic">고지내역　조회</button>
								<button id="btnFcltsFeeMngInqire">납부현황　조회</button>
							</td>
					</table>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 관리 내역</td>
								<td style="text-align: right">
									<button id="btnSaveMain">관리내역　저장</button>
									<button id="btnDeleteMain">관리내역　삭제</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="10%" height="18">관리 년월</th>
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
								<th width="10%" height="18">업무 구분</th>
								<td>
									<input id="mainMngFeeJobSeNm" type="hidden"/>
									<select id="mainMngFeeJobSe">
										<option value="M">마린센터</option>
										<option value="E">전기시설</option>
									</select>
								</td>
								<th width="10%" height="18">수정자</th>
								<td>
									<input id="mainNhtIsueYn" type="hidden"/>
									<input type="text" size="16" id="mainUpdUsr" disabled>
									<input type="text" size="16" id="mainUpdtDt" disabled>
								</td>
							</tr>
                            <tr>
								<th width="10%" height="18">관리비 제목</th>
								<td colspan="5"><input type="text" size="139" id="mainMngFeeSj" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">시설 관리 용역비</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainFcltyMngFee" disabled/></td>
								<th width="10%" height="18">전기 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainElctyFee" disabled/></td>
								<th width="10%" height="18">상하수도 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainWaterFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">도시가스 요금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainGasFee" disabled/></td>
								<th width="10%" height="18">환경개선 부담금</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainEnvFee" disabled/></td>
								<th width="10%" height="18">관리비 합계</th>
								<td><input type="text" size="33" class="ygpaNumber" id="mainMngTotalFee" disabled/></td>
                            </tr>
						</table>
					</form>
					<form id="subDetailForm">
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 상세 내역</td>
								<td style="text-align: right">
									<button id="btnAddDetail">상세내역　추가</button>
									<button id="btnSaveDetail">상세내역　저장</button>
									<button id="btnDeleteDetail">상세내역　삭제</button>
									<button id="btnProcessNticIssue">　　고　지　　</button>
									<button id="btnCancelNticIssue">　고지　취소　</button>
									<button id="btnPrintNticIssue">고지서　　출력</button>
								</td>
							</tr>
						</table>
						<table id="detailGrid" style="display:none"></table>
						<table class="detailPanel" style="width:100%">
                            <tr>
								<th width="10%" height="18">순번</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<input id="mngFeeJobSe" type="hidden"/>
									<input id="reqestSeq" type="hidden"/>
									<input type="text" size="33" id="mngSeq" disabled/>
								</td>
								<th width="10%" height="18">사용 면적</th>
								<td><input type="text" size="33" class="ygpaNumber" id="usageAr" disabled/></td>
								<th width="10%" height="18">부과 업체</th>
								<td>
									<input id="entrpscd" type="hidden"/>
									<input type="text" size="22" id="entrpsNm" disabled>
									<button id="popupDataEntrpscd" class="popupButton">선택</button>
								</td>
                            </tr>
                            <tr>
								<th width="10%" height="18">관리비</th>
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
                            <tr>
								<th width="10%" height="18">고지/출력/과세</th>
								<td>
									<input id="setoffYn" type="hidden"/>
									<input id="nticMth" type="hidden"/>
									<input id="aditNticYn" type="hidden"/>
									<input id="vatYn" type="hidden"/>
									<input id="rm" type="hidden"/>
									<input type="text" size="5" id="nhtIsueYn" disabled>
									<input type="text" size="5" id="nhtPrintYn" disabled>
									<input type="text" size="20" id="vatYnNm" disabled/>
								</td>
								<th width="10%" height="18">사용료/부가세</th>
								<td>
									<input type="text" size="16" class="ygpaNumber" id="fee" disabled>
									<input type="text" size="15" class="ygpaNumber" id="vat" disabled>
								</td>
								<th width="10%" height="18">고지 금액</th>
								<td>
									<input type="text" size="33" class="ygpaNumber" id="nticAmt" disabled>
								</td>
                            </tr>
                            <tr>
								<th width="10%" height="18">고지일/납부기한</th>
								<td>
                                	<input type="text" size="16" id="nticDt" disabled/>
                                	<input type="text" size="15" id="payTmlmt" disabled/>
								</td>
								<th width="10%" height="18">회계 정보</th>
								<td>
									<input id="prtAtCode" type="hidden"/>
									<input id="chrgeKnd" type="hidden"/>
									<input type="text" size="18" id="chrgeKndNm" disabled>
									<input type="text" size="6" id="accnutYear" disabled>
									<input type="text" size="6" id="nticNo" disabled>
								</td>
								<th width="10%" height="18">수납 정보</th>
								<td>
									<input id="rcivSe" type="hidden"/>
									<input type="text" size="16" id="rcivSeNm" disabled>
                                	<input type="text" size="15" id="rcivDt" disabled/>
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
