<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsMngGroupMng.jsp
 * @Description : 시설물 관리 그룹 화면
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.12.10  	 김종민          화면단 최초 생성
 *
 * author 김종민
 * since 2014.12.10
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
 * @FUNCTION NAME : GamFcltsMngGroupMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsMngGroupMngModule() {}

GamFcltsMngGroupMngModule.prototype = new EmdModule(900, 640);

GamFcltsMngGroupMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectFcltsMngGroupMng.do',
		dataType : "json",
		colModel : [
					{display:"관리 그룹 번호",	name:"fcltsMngGroupNo",		width:105,		sortable:true,	align:"center"},
					{display:"관리 그룹 명",	name:"fcltsMngGroupNm",		width:150,		sortable:true,	align:"left"},
					{display:"시설물 내용",		name:"fcltsCn",				width:200,		sortable:true,	align:"left"},
					{display:"시설물 구조",		name:"fcltsStrct",			width:200,		sortable:true,	align:"left"},
					{display:"준공 일자",		name:"bldDt",				width:80,		sortable:true,	align:"center"},
					{display:"소유자",			name:"owner",				width:105,		sortable:true,	align:"left"},
					{display:"건축 갯수",		name:"archFcltsCnt",		width:80,		sortable:true,	align:"right"},
					{display:"토목 갯수",		name:"cvlEngFcltsCnt",		width:80,		sortable:true,	align:"right"},
					{display:"기계 갯수",		name:"mechFcltsCnt",		width:80,		sortable:true,	align:"right"},
					{display:"전기 갯수",		name:"elctyFcltsCnt",		width:80,		sortable:true,	align:"right"},
					{display:"정보통신 갯수",	name:"infoCommFcltsCnt",	width:100,		sortable:true,	align:"right"},
					{display:"기타 갯수",		name:"etcFcltsCnt",			width:80,		sortable:true,	align:"right"},
					{display:"항구분",			name:"prtAtCodeNm",			width:60,		sortable:true,	align:"left"},
					{display:"위치",			name:"loc",					width:200,		sortable:true,	align:"left"},
					{display:"공사 시작 일자",	name:"cnstBeginDt",			width:100,		sortable:true,	align:"center"},
					{display:"공사 종료 일자",	name:"cnstEndDt",			width:100,		sortable:true,	align:"center"},
					{display:"시공자",			name:"cnstrtr",				width:150,		sortable:true,	align:"left"},
					{display:"시공 금액",		name:"cnstrctAmt",			width:100,		sortable:true,	align:"right"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumArchFcltsCnt').val(data.sumArchFcltsCnt);
			module.$('#sumCvlEngFcltsCnt').val(data.sumCvlEngFcltsCnt);
			module.$('#sumMechFcltsCnt').val(data.sumMechFcltsCnt);
			module.$('#sumElctyFcltsCnt').val(data.sumElctyFcltsCnt);
			module.$('#sumInfoCommFcltsCnt').val(data.sumInfoCommFcltsCnt);
			module.$('#sumEtcFcltsCnt').val(data.sumEtcFcltsCnt);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.fcltsMngGroupNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#prtAtCode').on('change',{module:this}, function(event){
		var prtAtCodeNm = event.data.module.$('#prtAtCode').find('option:selected').text();
		event.data.module.$('#prtAtCodeNm').val(prtAtCodeNm);
		if (event.data.module._mode == 'insert') {
			event.data.module.getNewFcltsMngGroupNo();
		}
	});

	this.$('#bldDt').on('keyup change',{module:this}, function(event){
		if (event.data.module._mode == 'insert') {
			event.data.module.getNewFcltsMngGroupNo();
		}
	});

	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltsMngGroupMngModule.prototype.onButtonClick = function(buttonId) {

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
GamFcltsMngGroupMngModule.prototype.onSubmit = function() {

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
GamFcltsMngGroupMngModule.prototype.loadData = function() {

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
GamFcltsMngGroupMngModule.prototype.refreshData = function() {

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
GamFcltsMngGroupMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/code/gamSelectFcltsMngGroupMngPk.do', searchVO, function(module, result){
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
GamFcltsMngGroupMngModule.prototype.selectData = function() {

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
	var fcltsMngGroupNo = mainKeyValue;
	var mainRowNo = -1;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (row.fcltsMngGroupNo == fcltsMngGroupNo) {
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

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.addData = function() {

	var fcltsCnTemplete =   "(*) 건축 시설물" + "\r\n" + "     - " + "\r\n" +
							"(*) 토목 시설물" + "\r\n" + "     - " + "\r\n" +
							"(*) 기계 시설물" + "\r\n" + "     - " + "\r\n" +
							"(*) 전기 시설물" + "\r\n" + "     - " + "\r\n" +
							"(*) 정보통신 시설물" + "\r\n" + "     - " + "\r\n" +
							"(*) 기타 시설물" + "\r\n" + "     - ";
	this.$('#prtAtCode').val("622");
	this.$('#prtAtCodeNm').val("광양항");
	this.$('#fcltsMngGroupNo').val("");
	this.$('#fcltsMngGroupNm').val("");
	this.$('#loc').val("");
	this.$('#owner').val("여수광양항만공사");
	this.$('#bldDt').val("");
	this.$('#cnstBeginDt').val("");
	this.$('#cnstEndDt').val("");
	this.$('#cnstrtr').val("");
	this.$('#cnstrctAmt').val("0");
	this.$('#fcltsCn').val(fcltsCnTemplete);
	this.$('#fcltsStrct').val(fcltsCnTemplete);
	this.$('#archFcltsCn').val("");
	this.$('#archFcltsStrct').val("");
	this.$('#cvlEngFcltsCn').val("");
	this.$('#cvlEngFcltsStrct').val("");
	this.$('#mechFcltsCn').val("");
	this.$('#mechFcltsStrct').val("");
	this.$('#elctyFcltsCn').val("");
	this.$('#elctyFcltsStrct').val("");
	this.$('#infoCommFcltsCn').val("");
	this.$('#infoCommFcltsStrct').val("");
	this.$('#etcFcltsCn').val("");
	this.$('#etcFcltsStrct').val("");
	this.$('#archFcltsCnt').val("0");
	this.$('#cvlEngFcltsCnt').val("0");
	this.$('#mechFcltsCnt').val("0");
	this.$('#elctyFcltsCnt').val("0");
	this.$('#infoCommFcltsCnt').val("0");
	this.$('#etcFcltsCnt').val("0");
	this.$('#rm').val("");
	this.enableDetailInputItem();
	this.$('#fcltsMngGroupNo').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.saveData = function() {

	var prtAtCode = this.$('#prtAtCode').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var fcltsMngGroupNm = this.$('#fcltsMngGroupNm').val();
	var owner = this.$('#owner').val();
	var bldDt = this.$('#bldDt').val();
	var cnstBeginDt = this.$('#cnstBeginDt').val();
	var cnstEndDt = this.$('#cnstEndDt').val();
	var cnstrctAmt = Number(this.$('#cnstrctAmt').val().replace(/,/gi, ""));
	var archFcltsCnt = Number(this.$('#archFcltsCnt').val().replace(/,/gi, ""));
	var cvlEngFcltsCnt = Number(this.$('#cvlEngFcltsCnt').val().replace(/,/gi, ""));
	var mechFcltsCnt = Number(this.$('#mechFcltsCnt').val().replace(/,/gi, ""));
	var elctyFcltsCnt = Number(this.$('#elctyFcltsCnt').val().replace(/,/gi, ""));
	var infoCommFcltsCnt = Number(this.$('#infoCommFcltsCnt').val().replace(/,/gi, ""));
	var etcFcltsCnt = Number(this.$('#etcFcltsCnt').val().replace(/,/gi, ""));
	var archFcltsCn = this.$('#archFcltsCn').val();
	var cvlEngFcltsCn = this.$('#cvlEngFcltsCn').val();
	var mechFcltsCn = this.$('#mechFcltsCn').val();
	var elctyFcltsCn = this.$('#elctyFcltsCn').val();
	var infoCommFcltsCn = this.$('#infoCommFcltsCn').val();
	var etcFcltsCn = this.$('#etcFcltsCn').val();
	var archFcltsStrct = this.$('#archFcltsStrct').val();
	var cvlEngFcltsStrct = this.$('#cvlEngFcltsStrct').val();
	var mechFcltsStrct = this.$('#mechFcltsStrct').val();
	var elctyFcltsStrct = this.$('#elctyFcltsStrct').val();
	var infoCommFcltsStrct = this.$('#infoCommFcltsStrct').val();
	var etcFcltsStrct = this.$('#etcFcltsStrct').val();
	var fcltsCnTemplete = "";
	if (prtAtCode == "") {
		alert('항구분이 부정확합니다.');
		this.$("#prtAtCode").focus();
		return;
	}
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹 번호가 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
		return;
	}
	if (fcltsMngGroupNm == "") {
		alert('시설물 관리 그룹 명이 부정확합니다.');
		this.$("#fcltsMngGroupNm").focus();
		return;
	}
	if (owner == "") {
		alert('소유자가 부정확합니다.');
		this.$("#owner").focus();
		return;
	}
	if (bldDt > "2999-12-31" || bldDt <= "1900-01-01") {
		alert('준공 일자가 부정확합니다.');
		this.$("#bldDt").focus();
		return;
	}
	if (cnstBeginDt != "" || cnstEndDt != "") {
		if (cnstBeginDt > "2999-12-31" || cnstBeginDt <= "1900-01-01") {
			alert('공사 시작 일자가 부정확합니다.');
			this.$("#cnstBeginDt").focus();
			return;
		}
		if (cnstEndDt > "2999-12-31" || cnstEndDt <= "1900-01-01") {
			alert('공사 종료 일자가 부정확합니다.');
			this.$("#cnstEndDt").focus();
			return;
		}
		if (cnstBeginDt > cnstEndDt) {
			alert('공사 시작 일자가 공사 종료 일자보다 큽니다.');
			this.$("#cnstBeginDt").focus();
			return;
		}
	}
	if (cnstrctAmt > 9999999999999999 || cnstrctAmt < 0) {
		alert('시공 금액이 부정확합니다.');
		this.$("#cnstrctAmt").focus();
		return;
	}
	if (archFcltsCnt > 99999 || archFcltsCnt < 0) {
		alert('건축 시설물 갯수가 부정확합니다.');
		this.$("#archFcltsCnt").focus();
		return;
	}
	if (cvlEngFcltsCnt > 99999 || cvlEngFcltsCnt < 0) {
		alert('토목 시설물 갯수가 부정확합니다.');
		this.$("#cvlEngFcltsCnt").focus();
		return;
	}
	if (mechFcltsCnt > 99999 || mechFcltsCnt < 0) {
		alert('기계 시설물 갯수가 부정확합니다.');
		this.$("#mechFcltsCnt").focus();
		return;
	}
	if (elctyFcltsCnt > 99999 || elctyFcltsCnt < 0) {
		alert('전기 시설물 갯수가 부정확합니다.');
		this.$("#elctyFcltsCnt").focus();
		return;
	}
	if (infoCommFcltsCnt > 99999 || infoCommFcltsCnt < 0) {
		alert('정보통신 시설물 갯수가 부정확합니다.');
		this.$("#infoCommFcltsCnt").focus();
		return;
	}
	if (etcFcltsCnt > 99999 || etcFcltsCnt < 0) {
		alert('기타 시설물 갯수가 부정확합니다.');
		this.$("#etcFcltsCnt").focus();
		return;
	}
	if (archFcltsCn != "") {
		archFcltsCn = "     " + archFcltsCn;
	} else {
		archFcltsCn = "     - ";
	}
	if (cvlEngFcltsCn != "") {
		cvlEngFcltsCn = "     " + cvlEngFcltsCn;
	} else {
		cvlEngFcltsCn = "     - ";
	}
	if (mechFcltsCn != "") {
		mechFcltsCn = "     " + mechFcltsCn;
	} else {
		mechFcltsCn = "     - ";
	}
	if (elctyFcltsCn != "") {
		elctyFcltsCn = "     " + elctyFcltsCn;
	} else {
		elctyFcltsCn = "     - ";
	}
	if (infoCommFcltsCn != "") {
		infoCommFcltsCn = "     " + infoCommFcltsCn;
	} else {
		infoCommFcltsCn = "     - ";
	}
	if (etcFcltsCn != "") {
		etcFcltsCn = "     " + etcFcltsCn;
	} else {
		etcFcltsCn = "     - ";
	}
	fcltsCnTemplete =   "(*) 건축 시설물" + "\r\n" + archFcltsCn + "\r\n" +
						"(*) 토목 시설물" + "\r\n" + cvlEngFcltsCn + "\r\n" +
						"(*) 기계 시설물" + "\r\n" + mechFcltsCn + "\r\n" +
						"(*) 전기 시설물" + "\r\n" + elctyFcltsCn + "\r\n" +
						"(*) 정보통신 시설물" + "\r\n" + infoCommFcltsCn + "\r\n" +
						"(*) 기타 시설물" + "\r\n" + etcFcltsCn;
	this.$('#fcltsCn').val(fcltsCnTemplete);
	if (archFcltsStrct != "") {
		archFcltsStrct = "     " + archFcltsCn;
	} else {
		archFcltsStrct = "     - ";
	}
	if (cvlEngFcltsStrct != "") {
		cvlEngFcltsStrct = "     " + cvlEngFcltsCn;
	} else {
		cvlEngFcltsStrct = "     - ";
	}
	if (mechFcltsStrct != "") {
		mechFcltsStrct = "     " + mechFcltsCn;
	} else {
		mechFcltsStrct = "     - ";
	}
	if (elctyFcltsStrct != "") {
		elctyFcltsStrct = "     " + elctyFcltsCn;
	} else {
		elctyFcltsStrct = "     - ";
	}
	if (infoCommFcltsStrct != "") {
		infoCommFcltsStrct = "     " + infoCommFcltsCn;
	} else {
		infoCommFcltsStrct = "     - ";
	}
	if (etcFcltsStrct != "") {
		etcFcltsStrct = "     " + etcFcltsCn;
	} else {
		etcFcltsStrct = "     - ";
	}
	fcltsCnTemplete =   "(*) 건축 시설물" + "\r\n" + archFcltsStrct + "\r\n" +
						"(*) 토목 시설물" + "\r\n" + cvlEngFcltsStrct + "\r\n" +
						"(*) 기계 시설물" + "\r\n" + mechFcltsStrct + "\r\n" +
						"(*) 전기 시설물" + "\r\n" + elctyFcltsStrct + "\r\n" +
						"(*) 정보통신 시설물" + "\r\n" + infoCommFcltsStrct + "\r\n" +
						"(*) 기타 시설물" + "\r\n" + etcFcltsStrct;
	this.$('#fcltsStrct').val(fcltsCnTemplete);
	if (this._mode == "insert") {
		var inputVO = this.makeFormArgs("#detailForm");
		this._mainKeyValue = fcltsMngGroupNo;
		this.doAction('/code/gamInsertFcltsMngGroupMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		var inputVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamUpdateFcltsMngGroupMng.do', inputVO, function(module, result) {
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
GamFcltsMngGroupMngModule.prototype.deleteData = function() {

	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹 번호가 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamDeleteFcltsMngGroupMng.do', deleteVO, function(module, result) {
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
GamFcltsMngGroupMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/code/gamExcelDownloadFcltsMngGroupMng.do');

};

<%
/**
 * @FUNCTION NAME : getNewReqestSeq
 * @DESCRIPTION   : 새로운 징수 의뢰 순번를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsMngGroupMngModule.prototype.getNewFcltsMngGroupNo = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	if (this.$('#prtAtCode').val() == "" || this.$('#bldDt').val() == "") {
		return;
	}
	this.doAction('/code/gamSelectFcltsMngGroupMngMaxGroupNo.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#fcltsMngGroupNo').val(result.sMaxGroupNo);
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
GamFcltsMngGroupMngModule.prototype.enableListButtonItem = function() {

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
GamFcltsMngGroupMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#prtAtCode').enable();
		this.$('#fcltsMngGroupNm').enable();
		this.$('#loc').enable();
		this.$('#owner').enable();
		this.$('#bldDt').enable();
		this.$('#cnstBeginDt').enable();
		this.$('#cnstEndDt').enable();
		this.$('#cnstrtr').enable();
		this.$('#cnstrctAmt').enable();
		this.$('#archFcltsCn').enable();
		this.$('#archFcltsStrct').enable();
		this.$('#cvlEngFcltsCn').enable();
		this.$('#cvlEngFcltsStrct').enable();
		this.$('#mechFcltsCn').enable();
		this.$('#mechFcltsStrct').enable();
		this.$('#elctyFcltsCn').enable();
		this.$('#elctyFcltsStrct').enable();
		this.$('#infoCommFcltsCn').enable();
		this.$('#infoCommFcltsStrct').enable();
		this.$('#etcFcltsCn').enable();
		this.$('#etcFcltsStrct').enable();
		this.$('#archFcltsCnt').enable();
		this.$('#cvlEngFcltsCnt').enable();
		this.$('#mechFcltsCnt').enable();
		this.$('#elctyFcltsCnt').enable();
		this.$('#infoCommFcltsCnt').enable();
		this.$('#etcFcltsCnt').enable();
		this.$('#rm').enable();
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#prtAtCode').disable();
			this.$('#fcltsMngGroupNm').enable();
			this.$('#loc').enable();
			this.$('#owner').enable();
			this.$('#bldDt').disable();
			this.$('#cnstBeginDt').enable();
			this.$('#cnstEndDt').enable();
			this.$('#cnstrtr').enable();
			this.$('#cnstrctAmt').enable();
			this.$('#archFcltsCn').enable();
			this.$('#archFcltsStrct').enable();
			this.$('#cvlEngFcltsCn').enable();
			this.$('#cvlEngFcltsStrct').enable();
			this.$('#mechFcltsCn').enable();
			this.$('#mechFcltsStrct').enable();
			this.$('#elctyFcltsCn').enable();
			this.$('#elctyFcltsStrct').enable();
			this.$('#infoCommFcltsCn').enable();
			this.$('#infoCommFcltsStrct').enable();
			this.$('#etcFcltsCn').enable();
			this.$('#etcFcltsStrct').enable();
			this.$('#archFcltsCnt').enable();
			this.$('#archFcltsCnt').enable();
			this.$('#cvlEngFcltsCnt').enable();
			this.$('#mechFcltsCnt').enable();
			this.$('#elctyFcltsCnt').enable();
			this.$('#infoCommFcltsCnt').enable();
			this.$('#etcFcltsCnt').enable();
			this.$('#rm').enable();
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#prtAtCode').disable();
			this.$('#fcltsMngGroupNm').disable();
			this.$('#loc').disable();
			this.$('#owner').disable();
			this.$('#bldDt').disable();
			this.$('#cnstBeginDt').disable();
			this.$('#cnstEndDt').disable();
			this.$('#cnstrtr').disable();
			this.$('#cnstrctAmt').disable();
			this.$('#archFcltsCn').disable();
			this.$('#archFcltsStrct').disable();
			this.$('#cvlEngFcltsCn').disable();
			this.$('#cvlEngFcltsStrct').disable();
			this.$('#mechFcltsCn').disable();
			this.$('#mechFcltsStrct').disable();
			this.$('#elctyFcltsCn').disable();
			this.$('#elctyFcltsStrct').disable();
			this.$('#infoCommFcltsCn').disable();
			this.$('#infoCommFcltsStrct').disable();
			this.$('#etcFcltsCn').disable();
			this.$('#etcFcltsStrct').disable();
			this.$('#archFcltsCnt').enable();
			this.$('#archFcltsCnt').disable();
			this.$('#cvlEngFcltsCnt').disable();
			this.$('#mechFcltsCnt').disable();
			this.$('#elctyFcltsCnt').disable();
			this.$('#infoCommFcltsCnt').disable();
			this.$('#etcFcltsCnt').disable();
			this.$('#rm').disable();
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
GamFcltsMngGroupMngModule.prototype.disableDetailInputItem = function() {

	this.$('#prtAtCode').disable();
	this.$('#fcltsMngGroupNm').disable();
	this.$('#loc').disable();
	this.$('#owner').disable();
	this.$('#bldDt').disable();
	this.$('#cnstBeginDt').disable();
	this.$('#cnstEndDt').disable();
	this.$('#cnstrtr').disable();
	this.$('#cnstrctAmt').disable();
	this.$('#archFcltsCn').disable();
	this.$('#archFcltsStrct').disable();
	this.$('#cvlEngFcltsCn').disable();
	this.$('#cvlEngFcltsStrct').disable();
	this.$('#mechFcltsCn').disable();
	this.$('#mechFcltsStrct').disable();
	this.$('#elctyFcltsCn').disable();
	this.$('#elctyFcltsStrct').disable();
	this.$('#infoCommFcltsCn').disable();
	this.$('#infoCommFcltsStrct').disable();
	this.$('#etcFcltsCn').disable();
	this.$('#etcFcltsStrct').disable();
	this.$('#archFcltsCnt').disable();
	this.$('#cvlEngFcltsCnt').disable();
	this.$('#mechFcltsCnt').disable();
	this.$('#elctyFcltsCnt').disable();
	this.$('#infoCommFcltsCnt').disable();
	this.$('#etcFcltsCnt').disable();
	this.$('#rm').disable();
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
GamFcltsMngGroupMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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
			break;
	}

};

var module_instance = new GamFcltsMngGroupMngModule();

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
							<th>시설물　관리　그룹　번호</th>
							<td>
								<input id="sFcltsMngGroupNo" type="text" size="15" maxlength="14"/>
							</td>
							<th>시설물　관리　그룹　명</th>
							<td>
								<input id="sFcltsMngGroupNm" type="text" size="30" maxlength="80"/>
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
				<li><a href="#listTab" class="emdTab">시설물 관리 그룹 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리 그룹 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">총건축갯수</th>
								<td><input type="text" size="10" id="sumArchFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총토목갯수</th>
								<td><input type="text" size="10" id="sumCvlEngFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총기계갯수</th>
								<td><input type="text" size="10" id="sumMechFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총전기갯수</th>
								<td><input type="text" size="10" id="sumElctyFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총통신갯수</th>
								<td><input type="text" size="10" id="suminfoCommFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총기타갯수</th>
								<td><input type="text" size="10" id="sumEtcFcltsCnt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
							<tr>
								<th style="width=10%; height=20; text-align:center;">자료수</th>
								<td>
									<input type="text" size="10" id="totalCount" class="ygpaNumber" disabled="disabled" />
								</td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
									<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
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
								<th style="width:15%; height:20;">시설물 관리 그룹 번호</th>
								<td>
									<input id="prtAtCodeNm" type="hidden"/>
									<input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
									<input type="text" id="fcltsMngGroupNo" size="37" maxlength="14" disabled/>
								</td>
								<th style="width:15%; height:20;">준공일자 / 소유자</th>
								<td>
									<input type="text" id="bldDt" size="14" class="emdcal"/>/
									<input type="text" id="owner" size="30" maxlength="60"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">시설물 관리 그룹 명</th>
								<td>
									<input id="fcltsCn" type="hidden"/>
									<input id="fcltsStrct" type="hidden"/>
									<input type="text" id="fcltsMngGroupNm" size="50" maxlength="80"/>
								</td>
								<th style="width:15%; height:20;">위　　　　　치</th>
								<td>
									<input type="text" id="loc" size="50" maxlength="150"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">건축 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="archFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">건축 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="archFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">토목 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="cvlEngFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">토목 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="cvlEngFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">기계 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="mechFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">기계 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="mechFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">전기 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="elctyFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">전기 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="elctyFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">정보통신 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="infoCommFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">정보통신 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="infoCommFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">기타 시설물 내용</th>
								<td>
									<textarea rows="3" cols="48" id="etcFcltsCn" maxlength="600"></textarea>
								</td>
								<th style="width:15%; height:20;">기타 시설물 구조</th>
								<td>
									<textarea rows="3" cols="48" id="etcFcltsStrct" maxlength="600"></textarea>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">시설물 갯수</th>
								<td colspan="3">
									건축 : <input type="text" class="ygpaNumber" id="archFcltsCnt" size="9" maxlength="6"/>
									&nbsp; / &nbsp;
									토목 : <input type="text" class="ygpaNumber" id="cvlEngFcltsCnt" size="9" maxlength="6"/>
									&nbsp; / &nbsp;
									기계 : <input type="text" class="ygpaNumber" id="mechFcltsCnt" size="9" maxlength="6"/>
									&nbsp; / &nbsp;
									전기 : <input type="text" class="ygpaNumber" id="elctyFcltsCnt" size="9" maxlength="6"/>
									&nbsp; / &nbsp;
									정보통신 : <input type="text" class="ygpaNumber" id="infoCommFcltsCnt" size="9" maxlength="6"/>
									&nbsp; / &nbsp;
									기타 : <input type="text" class="ygpaNumber" id="etcFcltsCnt" size="9" maxlength="6"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">시공자 / 시공금액</th>
								<td>
									<input type="text" id="cnstrtr" size="32" maxlength="60"/>
									<input type="text" class="ygpaNumber" id="cnstrctAmt" size="15" maxlength="20"/>
								</td>
								<th style="width:15%; height:20;">공사기간 / 시공금액</th>
								<td>
									<input type="text" size="18" id="cnstBeginDt" class="emdcal"/>
									&nbsp; ~ &nbsp;
									<input type="text" size="18" id="cnstEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:20;">비　　　　　고</th>
								<td colspan="3">
									<textarea rows="3" cols="126" id="rm" maxlength="1000"></textarea>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
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
