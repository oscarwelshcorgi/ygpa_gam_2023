<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsChargerMng.jsp
 * @Description : 시설물 담당자 관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.03.05  ACEWOLF          최초 생성
 *
 * author ACEWOLF
 * since 2015.03.05
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
 * @FUNCTION NAME : GamMngFeeGubunMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsChargerMngModule() {}

GamFcltsChargerMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectFcltsChargerMngList.do',
		dataType : 'json',
		colModel : [
					{display:'담당자 사번', 	name:'chargerNo',				width:100, 		sortable:false,		align:'left'},
					{display:'담당자 명', 		name:'chargerNm',				width:100, 		sortable:false,		align:'left'},
					{display:'업무 구분', 		name:'fcltsJobSeNm',			width:100, 		sortable:false,		align:'left'},
					{display:'담당자 표시', 	name:'chargerDisplayNm',		width:100, 		sortable:false,		align:'left'},
					{display:'담당자 직위', 	name:'chargerOfcPos',			width:135, 		sortable:false,		align:'left'},
					{display:'담당자 부서', 	name:'chargerDept',				width:140, 		sortable:false,		align:'left'},
					{display:'직인 파일명', 	name:'signFileNmLogic',			width:100, 		sortable:false,		align:'left'},
					{display:"프리뷰",			name:"photoUrl",				width:100,		sortable:false,		align:"center",		displayFormat:"image"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			$.each(data.resultList, function() {
				this.photoUrl = "";
				var signFileNmPhysicl = this.signFileNmPhysicl;
				if (signFileNmPhysicl != null && signFileNmPhysicl != "") {
					var ext = signFileNmPhysicl.substring(signFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
					if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
						this.photoUrl = module.getPfPhotoUrl(signFileNmPhysicl) + "^" + this.signFileNmLogic + "^" + "100";
					} else if (ext == "hwp") {
						this.photoUrl = "js/codebase/imgs/hwp.png";
					} else if (ext == "dwg") {
						this.photoUrl = "js/codebase/imgs/dwg.png";
					} else {
						this.photoUrl = "js/codebase/imgs/unknown.png";
					}
				}
			});
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
		module._mainKeyValue = row.fcltsJobSe + row.chargerNo;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsJobSe + row.chargerNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#fcltsJobSe').bind('change', {module: this}, function(event) {
		var fcltsJobSe = $(this).val();
		var fcltsJobSeNm = event.data.module.getFcltsJobSeNm(fcltsJobSe);
		event.data.module.$('#fcltsJobSeNm').val(fcltsJobSeNm);
	});

	this.$('#chargerNm').bind('change', {module: this}, function(event) {
		var chargerNm = $(this).val();
		event.data.module.$('#chargerDisplayNm').val(chargerNm);
	});

	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._signFilePreview = false;
	if (EMD.userinfo.mngFcltyCd == null || EMD.userinfo.mngFcltyCd != "*") {
		this.$('#sChargerNo').val(EMD.userinfo.emplNo);
	}
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#mainGrid')[0].dgrid.setColumnHidden(7, true);

};

<%
/**
 * @FUNCTION NAME : getFcltsJobSeNm
 * @DESCRIPTION   : 시설물 업무 구분명을 구한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltsChargerMngModule.prototype.getFcltsJobSeNm = function(argFcltsJobSe) {

	var fcltsJobSeNm = "";
	switch (argFcltsJobSe) {
		case "A" : fcltsJobSeNm = '건축시설';		break;
		case "C" : fcltsJobSeNm = '토목시설';		break;
		case "E" : fcltsJobSeNm = '전기시설';		break;
		case "I" : fcltsJobSeNm = '정보통신시설';	break;
		case "M" : fcltsJobSeNm = '기계시설';		break;
	}
	return fcltsJobSeNm;
};

<%
/**
 * @FUNCTION NAME : displayPreviewSign
 * @DESCRIPTION   : SIGN PREVIEW DISPLAY
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.displayPreviewSign = function() {

	var signFilePreviewFlag = this.$('#mainGrid')[0].dgrid.isColumnHidden(7);
	var signFileNameFlag = false;
	if (signFilePreviewFlag == true) {
		signFilePreviewFlag = false;
		signFileNameFlag = true;
	} else {
		signFilePreviewFlag = true;
		signFileNameFlag = false;
	}
	this.$('#mainGrid')[0].dgrid.setColumnHidden(7, signFilePreviewFlag);
	this.$('#mainGrid')[0].dgrid.setColumnHidden(6, signFileNameFlag);

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltsChargerMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'btnSignPreview':
			this.displayPreviewSign();
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
		case 'btnSignRegister':
			this.registerSign();
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
GamFcltsChargerMngModule.prototype.onSubmit = function() {

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
GamFcltsChargerMngModule.prototype.loadData = function() {

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
 GamFcltsChargerMngModule.prototype.refreshData = function() {

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
GamFcltsChargerMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/fclty/gamSelectFcltsChargerMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
				module.previewFile();
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
GamFcltsChargerMngModule.prototype.selectData = function() {

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
	var fcltsJobSe = this._mainKeyValue.substring(0,1);
	var chargerNo = this._mainKeyValue.substring(1,21);
	this.$("#mainGrid").selectFilterRow([{col:"chargerNo", filter:chargerNo},
										 {col:"fcltsJobSe", filter:fcltsJobSe}]);
	this._mainmode = 'modify';
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
GamFcltsChargerMngModule.prototype.addData = function() {

	var fcltsJobSe = this.$('#sFcltsJobSe').val();
	var fcltsJobSeNm = this.getFcltsJobSeNm(fcltsJobSe);
	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		this.$('#chargerNo').val(EMD.userinfo.emplNo);
		this.$('#chargerNm').val(EMD.userinfo.name);
	} else {
		this.$('#chargerNo').val("");
		this.$('#chargerNm').val("");
	}
	this.$('#fcltsJobSe').val(fcltsJobSe);
	this.$('#fcltsJobSeNm').val(fcltsJobSeNm);
	this.$('#chargerDisplayNm').val("");
	this.$('#chargerOfcPos').val("");
	this.$('#chargerDept').val("");
	this.$('#signFileNmLogic').val("");
	this.$('#signFileNmPhysicl').val("");
	this.enableDetailInputItem();
	this.$('#chargerNo').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.saveData = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var chargerNo = this.$('#chargerNo').val();
	var chargerNm = this.$('#chargerNm').val();
	var chargerDisplayNm = this.$('#chargerDisplayNm').val();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		alert('업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (chargerNo == "") {
		alert('담당자 사번이 부정확합니다.');
		this.$("#chargerNo").focus();
		return;
	}
	if (chargerNm == "") {
		alert('담당자 명이 부정확합니다.');
		this.$("#chargerNm").focus();
		return;
	}
	if (chargerDisplayNm == "") {
		alert('담당자 표시 명이 부정확합니다.');
		this.$("#chargerDisplayNm").focus();
		return;
	}
	var inputVO = this.makeFormArgs("#detailForm");
	if (this._mainmode == "insert") {
		this._mainKeyValue = fcltsJobSe + chargerNo;
		this.doAction('/fclty/gamInsertFcltsChargerMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/fclty/gamUpdateFcltsChargerMng.do', inputVO, function(module, result) {
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
GamFcltsChargerMngModule.prototype.deleteData = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var chargerNo = this.$('#chargerNo').val();
	if (fcltsJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (chargerNo == "") {
		alert('담당자 사번이 부정확합니다.');
		this.$("#chargerNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteFcltsChargerMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : registerSign
 * @DESCRIPTION   : SIGN REGISTER
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.registerSign = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var chargerNo = this.$('#chargerNo').val();
	if (fcltsJobSe == "") {
		alert('업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (chargerNo == "") {
		alert('담당자 사번이 부정확합니다.');
		this.$("#chargerNo").focus();
		return;
	}
	this.uploadPfPhoto("registerSign", function(module, result) {
		$.each(result, function(){
			var signFileNmLogic = this.logicalFileNm;
			var signFileNmPhysicl = this.physcalFileNm;
			if (signFileNmLogic != "" && signFileNmPhysicl != "") {
				module.$('#signFileNmLogic').val(signFileNmLogic);
				module.$('#signFileNmPhysicl').val(signFileNmPhysicl);
				var ext = signFileNmPhysicl.substring(signFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
				if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
					var imgURL = module.getPfPhotoUrl(signFileNmPhysicl);
					module.$("#previewImage").attr("src", imgURL);
				} else {
					module.$("#previewImage").removeAttr("src");
				}
			}
		});
	}, "직인 등록");

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexiXlsDown("시설물담당자.xls","시설물 담당자");

};

<%
/**
 * @FUNCTION NAME : previewFile
 * @DESCRIPTION   : FILE PREVIEW
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.previewFile = function() {

	var signFileNmPhysicl = this.$('#signFileNmPhysicl').val();
	if (signFileNmPhysicl != null || signFileNmPhysicl != "") {
		var ext = signFileNmPhysicl.substring(signFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
		if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
			var imgURL = this.getPfPhotoUrl(signFileNmPhysicl);
			this.$("#previewImage").attr("src", imgURL);
		} else {
			this.$("#previewImage").removeAttr("src");
		}
	} else {
		this.$('#previewImage').attr("src", "");
	}

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.firstData = function() {

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
	var firstRowIndex = 0;
	var row = rows[firstRowIndex];
	var chargerNo = row["chargerNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	if (chargerNo != "" && fcltsJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"chargerNo", filter:chargerNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsJobSe + chargerNo;
		this.makeFormValues('#detailForm', rows[firstRowIndex]);
		this.makeDivValues('#detailForm', rows[firstRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.prevData = function() {

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
	var prevRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["fcltsJobSe"] + row["chargerNo"];
		if (this._mainKeyValue == keyValue) {
			prevRowIndex = i - 1;
			break;
		}
	}
	if (prevRowIndex < 0) {
		alert("첫번째 자료 입니다!");
		return;
	}
	if (prevRowIndex >= gridRowCount) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	var row = rows[prevRowIndex];
	var chargerNo = row["chargerNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	if (chargerNo != "" && fcltsJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"chargerNo", filter:chargerNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsJobSe + chargerNo;
		this.makeFormValues('#detailForm', rows[prevRowIndex]);
		this.makeDivValues('#detailForm', rows[prevRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.nextData = function() {

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
	var nextRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["fcltsJobSe"] + row["chargerNo"];
		if (this._mainKeyValue == keyValue) {
			nextRowIndex = i + 1;
			break;
		}
	}
	if (nextRowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	if (nextRowIndex >= gridRowCount) {
		alert("마지막 자료 입니다!");
		return;
	}
	var row = rows[nextRowIndex];
	var chargerNo = row["chargerNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	if (chargerNo != "" && fcltsJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"chargerNo", filter:chargerNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsJobSe + chargerNo;
		this.makeFormValues('#detailForm', rows[nextRowIndex]);
		this.makeDivValues('#detailForm', rows[nextRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.lastData = function() {

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
	var lastRowIndex = gridRowCount - 1;
	var row = rows[lastRowIndex];
	var chargerNo = row["chargerNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	if (chargerNo != "" && fcltsJobSe != "") {
		this.$("#mainGrid").selectFilterRow([{col:"chargerNo", filter:chargerNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe}]);
		this._mainmode = 'modify';
		this._mainKeyValue = fcltsJobSe + chargerNo;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsChargerMngModule.prototype.enableListButtonItem = function() {

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
GamFcltsChargerMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#fcltsJobSe').enable();
		this.$('#chargerNo').enable();
		this.$('#chargerNm').enable();
		this.$('#chargerDisplayNm').enable();
		this.$('#chargerOfcPos').enable();
		this.$('#chargerDept').enable();
		this.$('#btnSignRegister').enable();
		this.$('#btnSignRegister').removeClass('ui-state-disabled');
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
			this.$('#fcltsJobSe').enable();
			this.$('#chargerNo').enable();
			this.$('#chargerNm').enable();
			this.$('#chargerDisplayNm').enable();
			this.$('#chargerOfcPos').enable();
			this.$('#chargerDept').enable();
			this.$('#btnSignRegister').enable();
			this.$('#btnSignRegister').removeClass('ui-state-disabled');
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
			this.$('#fcltsJobSe').disable();
			this.$('#chargerNo').disable();
			this.$('#chargerNm').disable();
			this.$('#chargerDisplayNm').disable();
			this.$('#chargerOfcPos').disable();
			this.$('#chargerDept').disable();
			this.$('#btnSignRegister').disable({disableClass:"ui-state-disabled"});
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
GamFcltsChargerMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsJobSe').disable();
	this.$('#chargerNo').disable();
	this.$('#chargerNm').disable();
	this.$('#chargerDisplayNm').disable();
	this.$('#chargerOfcPos').disable();
	this.$('#chargerDept').disable();
	this.$('#btnSignRegister').disable({disableClass:"ui-state-disabled"});
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
GamFcltsChargerMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
				this.previewFile();
			} else if (this._mainmode=="insert") {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.addData();
				this.previewFile();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.previewFile();
			}
			break;
	}

};

var module_instance = new GamFcltsChargerMngModule();

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
							<th style="width:10%; height:18;">담　당　자　명</th>
							<td>
								<input id="sChargerNo" type="hidden"/>
								<input type="text" size="35" id="sChargerNm" maxlength="20">
							</td>
							<th style="width:10%; height:18;">업　무　구　분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="" selected>전체</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
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
				<li><a href="#listTab" class="emdTab">시설물 담당자 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 담당자 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　추　가　</button>
									<button id="btnDelete" class="buttonDelete">　삭　제　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
									<button id="btnSignPreview">직인 미리보기</button>
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
								<th style="width:15%; height:30px;">업　무　구　분</th>
								<td>
									<input id="fcltsJobSeNm" type="hidden"/>
									<select id="fcltsJobSe">
										<option value="">선택</option>
										<option value="E">전기시설</option>
										<option value="M">기계시설</option>
										<option value="C">토목시설</option>
										<option value="A">건축시설</option>
										<option value="I">정보통신시설</option>
									</select>
									<button id="btnSignRegister">직인 등록</button>
								</td>
								<td rowspan="8" style="text-align:center; vertical-align:middle;">
									<input id="signFileNmLogic" type="hidden"/>
									<input id="signFileNmPhysicl" type="hidden"/>
									<img id="previewImage" style="border:1px solid #000; width:415px; height:415px; max-width:415px; max-height:415px;" src="">
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">담당자　　사번</th>
								<td>
									<input type="text" size="35" id="chargerNo" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">담　당　자　명</th>
								<td>
									<input type="text" size="35" id="chargerNm" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">담당자　표시명</th>
								<td>
									<input type="text" size="35" id="chargerDisplayNm" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">담당자　　직위</th>
								<td>
									<input type="text" size="35" id="chargerOfcPos" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">담당자　　부서</th>
								<td>
									<input type="text" size="35" id="chargerDept" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">등　　록　　자</th>
								<td>
									<input type="text" size="13" id="regUsr" disabled>
									<input type="text" size="20" id="registDt" disabled>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:30px;">수　　정　　자</th>
								<td>
									<input type="text" size="13" id="updUsr" disabled>
									<input type="text" size="20" id="updtDt" disabled>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnFirstData">처음　자료</button>
								<button id="btnPrevData">이전　자료</button>
								<button id="btnNextData">다음　자료</button>
								<button id="btnLastData">마지막　자료</button>
								<button id="btnInsert" class="buttonAdd">　추　가　</button>
								<button id="btnSave" class="buttonSave">　저　장　</button>
								<button id="btnRemove" class="buttonDelete">　삭　제　</button>
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
