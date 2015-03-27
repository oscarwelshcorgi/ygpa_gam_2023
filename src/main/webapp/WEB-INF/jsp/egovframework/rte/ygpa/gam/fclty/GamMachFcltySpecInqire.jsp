<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamMachFcltySpecInqire.jsp
 * @Description : 기계시설 제원 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.03.10  김영길          최초 생성
 *
 * author 김영길
 * since 2015.03.10
 *
 * Copyright (C) 2015 by LFIT  All right reserved.
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamMachFcltySpecInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamMachFcltySpecInqireModule() {}

GamMachFcltySpecInqireModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecInqireList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",	width:60,		sortable:false,		align:"center"},
					{display:"항만시설 명",			name:"prtFcltyNm",				width:200,		sortable:false,		align:"left"},
					{display:"시설물 관리 그룹",	name:"fcltsMngGroupNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설물 분류",			name:"gisPrtFcltyCdNm",			width:100,		sortable:false,		align:"left"},
					{display:"소재지",	 			name:"loc",						width:150,		sortable:false,		align:"left"},
					{display:"시설 구분",		 	name:"mechFcltsSeNm",			width:80,		sortable:false,		align:"left"},
					{display:"장비명",	 			name:"eqpmnNm",					width:150,		sortable:false,		align:"left"},
					{display:"장비 번호",	 		name:"eqpmnNo",					width:80,		sortable:false,		align:"left"},
					{display:"운영 회사",		 	name:"operCmpny",				width:100,		sortable:false,		align:"left"},
					{display:"제작 회사",	 		name:"mfcCmpny",				width:150,		sortable:false,		align:"left"},
					{display:"제작 금액",	 		name:"mfcAmt",					width:100,		sortable:false,		align:"right"},
					{display:"설치 년월",	 		name:"instlYrmt",				width:80,		sortable:false,		align:"center"},
					{display:"도급자",	 			name:"contrUsr",				width:80,		sortable:false,		align:"left"},
					{display:"도급 금액",			name:"contrAmt",				width:100,		sortable:false,		align:"right"},
					{display:"규격",				name:"eqpmnStndrd",				width:80,		sortable:false,		align:"right"},
					{display:"연결 도교",			name:"linkBridge",				width:80,		sortable:false,		align:"right"},
					{display:"해당 건축시설",	 	name:"archFcltsNm",				width:100,		sortable:false,		align:"left"},
					{display:"시설물 관리 번호",	name:"fcltsMngNo",				width:130,		sortable:false,		align:"left"},
					{display:"자산 명",	 			name:"gisAssetsNm",				width:200,		sortable:false,		align:"left"},
					{display:"자산 위치",	 		name:"gisAssetsLocNm",			width:200,		sortable:false,		align:"left"}
					],
		showTableToggleBtn : false,
		height : '520',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumMfcAmt').val($.number(data.sumMfcAmt));
			module.$('#sumContrAmt').val($.number(data.sumContrAmt));
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#sFcltsMngGroupNo", "#sFcltsMngGroupNm");
	});

	this.$("#statusGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecInqireCvlEngStatusList.do',
		dataType : "json",
		colModel : [
					{display:"부두명",				name:"sttusFcltsMngGroupNm",	width:130,		sortable:false,		align:"center"},
					{display:"운영사",			 	name:"sttusOperCmpny",			width:100,		sortable:false,		align:"center"},
					{display:"C/C",					name:"sttusCcCount",			width:100,		sortable:false,		align:"center"},
					{display:"T/C",					name:"sttusTcCountDisplay",		width:110,		sortable:false,		align:"center"},
					{display:"Y/T",					name:"sttusYtCount",			width:100,		sortable:false,		align:"center"},
					{display:"샷시",				name:"sttusCsCount",			width:100,		sortable:false,		align:"center"},
					{display:"Reach",				name:"sttusRsCount",			width:100,		sortable:false,		align:"center"},
					{display:"Top Handler",			name:"sttusThCount",			width:100,		sortable:false,		align:"center"},
					{display:"비고(eRTGC)",			name:"sttusTcRtgcCount",		width:100,		sortable:false,		align:"center"}
					],
		showTableToggleBtn : false,
		height : '477',
		mergeRows : 'sttusFcltsMngGroupNm'
	});

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecInqireFcltsAtchFileList.do',
		dataType : 'json',
		colModel : [
					{display:"선택",		name:"atchFileSelChk",		width:40,		sortable:false,		align:"center",		displayFormat:"checkbox"},
					{display:"번호",		name:"atchFileNo",			width:60,		sortable:false,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:60,		sortable:false,		align:"center"},
					{display:"파일명",		name:"atchFileNmLogic",		width:200,		sortable:false,		align:"left"},
					{display:"프리뷰",		name:"photoUrl",			width:100,		sortable:false,		align:"center",		displayFormat:"image"}
					],
		height: "477",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.atchFileSelChk = (this.atchFileSelChk === 'TRUE');
				this.photoUrl = "";
				var atchFileNmPhysicl = this.atchFileNmPhysicl;
				var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
				if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
					this.photoUrl = module.getPfPhotoUrl(atchFileNmPhysicl) + "^" + this.atchFileNmLogic + "^" + "100";
				} else if (ext == "hwp") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/hwp.png";
				} else if (ext == "dwg") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/dwg.png";
				} else if (ext == "xls") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/xls.png";
				} else if (ext == "xlsx") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/xlsx.png";
				} else if (ext == "pdf") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/pdf.jpg";
				} else if (ext == "dow") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/dow.png";
				} else if (ext == "ppt") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/ppt.png";
				} else if (ext == "txt") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/txt.png";
				} else if (ext == "zip") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/zip.jpg";
				} else {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/unknown.png";
				}
			});
			return data;
		}
	});

	this.$("#fileGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectFileData();
	});
	
	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
	});

	this.$("#fileGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.showFcltsAtchFileViewPopup();
	});

	this.$('#dirQueryOption').on('change',{module:this}, function(event){
		event.data.module.displayAtchFileDirectory("");
		event.data.module.displayAtchFileList("");
	});
	
	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._atchFileDirLoad = false;
	this._atchFilePreview = false;
	this._mainGridDisplay = 'mainGrid';
	this.$('#statusGrid').hide();
	
};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNm
 * @DESCRIPTION   : 시설물 관리 그룹 명을 구한다.
 * @PARAMETER     :
 *   1. argFcltsMngGroupNoVariableName - 시설물 관리 그룹 번호 변수 명
 *   2. argFcltsMngGroupNmVariableName - 시설물 관리 그룹 명 변수 명
**/
%>
GamMachFcltySpecInqireModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectMachFcltySpecInqireFcltsMngGroupNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				sFcltsMngGroupNm = result.sFcltsMngGroupNm;
				if (argFcltsMngGroupNmVariableName != null && argFcltsMngGroupNmVariableName != "") {
					module.$(argFcltsMngGroupNmVariableName).val(result.sFcltsMngGroupNm);
				}
			}
			return sFcltsMngGroupNm;
		});
	}
	if (argFcltsMngGroupNmVariableName != null && argFcltsMngGroupNmVariableName != "") {
		this.$(argFcltsMngGroupNmVariableName).val(sFcltsMngGroupNm);
	}
	return sFcltsMngGroupNm;

};

<%
/**
 * @FUNCTION NAME : changeDetailAreaForm
 * @DESCRIPTION   : 시설물 구분에 따른 폼 입력 영역 변경
 * @PARAMETER     :
 *   1. argMechFcltsSe - 시설물 구분
**/
%>
GamMachFcltySpecInqireModule.prototype.changeDetailAreaForm = function(argMechFcltsSe) {

	var mechFcltsSe = "Z";
	if (argMechFcltsSe != null) {
		mechFcltsSe = argMechFcltsSe;
	}
	if (mechFcltsSe == "1") {
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
		this.$('#cvlEngDetailArea').show();
	} else if (mechFcltsSe == "2") {
		this.$('#cvlEngDetailArea').hide();
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').show();
	} else if (mechFcltsSe == "3") {
		this.$('#cvlEngDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
		this.$('#archDetailArea').show();
	} else {
		this.$('#cvlEngDetailArea').hide();
		this.$('#archDetailArea').hide();
		this.$('#bridgeDetailArea').hide();
	}

};

<%
/**
 * @FUNCTION NAME : onAtchFileDirTreeItemClick
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY TREE ITEM CLICK EVENT
 * @PARAMETER     :
 *   1. itemId - ITEM ID
**/
%>
GamMachFcltySpecInqireModule.prototype.onAtchFileDirTreeItemClick = function(itemId) {

	$(this)[0].module.refreshDirData(itemId);

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
GamMachFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchFcltsMngGroupNo':
			if (msg == 'ok') {
				this.$('#sFcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#sFcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#sFcltsClCd').focus();
			}
			break;
		case 'popupFcltsAtchFileView':
			if (msg == 'ok') {
				var atchFileNo = this.$('#atchFileNo').val();
				if (atchFileNo == value.atchFileNo) {
					this.$('#atchFileSe').val(value.atchFileSe);
					this.$('#atchFileSeNm').val(value.atchFileSeNm);
					this.$('#atchFileSj').val(value.atchFileSj);
					var selectRow = this.$('#fileGrid').selectedRows();
					if(selectRow.length > 0) {
						var row = selectRow[0];
						row['atchFileSeNm'] = value.atchFileSeNm;
						row['atchFileSe'] = value.atchFileSe;
						row['atchFileSj'] = value.atchFileSj;
						var rowid = this.$("#fileGrid").selectedRowIds()[0];
						this.$('#fileGrid').flexUpdateRow(rowid, row);
					}
				}
			}
			break;
		case 'btnAtchDirFileSearch':
			if (msg == 'ok') {
				if (value.listSe == "F") {
					this._fileKeyValue = value.fileNo;
					this.displayAtchFileDirectory("" + value.dirNo);
				} else {
					this.displayAtchFileDirectory("" + value.dirNo);
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
GamMachFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
	    case 'btnSpecFirstData':
	    	this.firstData();
			break;
	    case 'btnSpecPrevData':
	    	this.prevData();
			break;
	    case 'btnSpecNextData':
	    	this.nextData();
			break;
	    case 'btnSpecLastData':
	    	this.lastData();
			break;
		case 'popupSearchFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFcltsMngGroup.do', null);
			break;
		case 'btnDirRefresh':
			this.displayAtchFileDirectory("");
			this.displayAtchFileList("");
			break;
		case 'btnFileAllSelect':
			this.selectAllFile();
			break;
		case 'btnFileDownload':
			this.downloadMultiFile();
			break;
	    case 'btnFilePreview':
	    	this.displayPreviewFile();
			break;
	    case 'btnAtchDirFileSearch':
			var sFcltsJobSe = this.$('#dirQueryOption').val();
			var sSearchSe = "D";
            var searchOpts = {
    				'sSearchSe':sSearchSe,
    				'sFcltsJobSe':sFcltsJobSe
                };
			this.doExecuteDialog('btnAtchDirFileSearch', '디렉토리/파일 검색', '/popup/showAtchDirFile.do', null, searchOpts);
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
GamMachFcltySpecInqireModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = true;
	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var sGisPrtFcltyCd = this.$('#sGisPrtFcltyCd').val();
	var searchOpt=this.makeFormArgs('#searchForm');
	if (sGisPrtFcltyCd != "00") {
		this._mainGridDisplay = 'mainGrid';
		this.$('#statusGrid').hide();
		this.$('#mainGrid').show();
		this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
	} else {
		this._mainGridDisplay = 'statusGrid';
		this.$('#mainGrid').hide();
		this.$('#statusGrid').show();
		this.$('#statusGrid').flexOptions({params:searchOpt}).flexReload();
	}

};

<%
/**
 * @FUNCTION NAME : refreshData
 * @DESCRIPTION   : DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.refreshData = function() {

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
GamMachFcltySpecInqireModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else if (tabId == 'detailTab') {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/fclty/gamSelectMachFcltySpecMngPk.do', searchVO, function(module, result){
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
GamMachFcltySpecInqireModule.prototype.selectData = function() {

	if (this._mainmode == 'query') {
		this.$('#sFcltsMngNo').val("");
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		if (this._params != null && this._params.action != null && this._params.action == "prtFcltyInqire") {
			if (this._params.fcltsMngNo != null && this._params.fcltsMngNo != "") {
				var fcltsMngNo = this._params.fcltsMngNo;
				this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:fcltsMngNo}]);
				this._mainmode = 'modify';
				this._mainKeyValue = fcltsMngNo;
				this.$("#mainTab").tabs("option", {active: 1});
				this._params.action = "";
				this._params.fcltsMngNo = "";
			}
		}
		return;
	}
};

<%
/**
 * @FUNCTION NAME : selectFileData
 * @DESCRIPTION   : FILE DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.selectFileData = function() {

	if (this._fileKeyValue == "") {
		return;
	}
	var atchFileNo = this._fileKeyValue;
	this._fileKeyValue = "";
	this.$("#fileGrid").selectFilterRow([{col:"atchFileNo", filter:atchFileNo}]);
	this.refreshFileData(atchFileNo);
	this.enableFileButtonItem();

};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.getFcltsMngGroupNoNm = function() {

	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.firstData = function() {

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
	var firstFcltsMngNo = row["fcltsMngNo"];
	if (firstFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:firstFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = firstFcltsMngNo;
		this.makeFormValues('#detailForm', rows[firstRowIndex]);
		this.makeDivValues('#detailForm', rows[firstRowIndex]);
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (fcltsMngNo == row["fcltsMngNo"]) {
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
	var prevFcltsMngNo = row["fcltsMngNo"];
	if (prevFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:prevFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = prevFcltsMngNo;
		this.makeFormValues('#detailForm', rows[prevRowIndex]);
		this.makeDivValues('#detailForm', rows[prevRowIndex]);
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngNo = this._mainKeyValue;
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (fcltsMngNo == row["fcltsMngNo"]) {
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
	var nextFcltsMngNo = row["fcltsMngNo"];
	if (nextFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:nextFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = nextFcltsMngNo;
		this.makeFormValues('#detailForm', rows[nextRowIndex]);
		this.makeDivValues('#detailForm', rows[nextRowIndex]);
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.lastData = function() {

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
	var lastFcltsMngNo = row["fcltsMngNo"];
	if (lastFcltsMngNo != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:lastFcltsMngNo}]);
		this._mainmode = 'modify';
		this._mainKeyValue = lastFcltsMngNo;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
	}

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamMachFcltySpecInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			if (this._mainGridDisplay == 'statusGrid') {
				gridRowCount = this.$("#statusGrid").flexRowCount();
			} else {
				gridRowCount = this.$("#mainGrid").flexRowCount();
			}
			break;
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			if (this._mainGridDisplay == 'statusGrid') {
				this.$('#statusGrid').flexiXlsDown("하역장비현황.xls","하역장비현황");
			} else {
				this.$('#mainGrid').flexExcelDown('/fclty/gamExcelDownloadMachFcltySpecInqire.do');
			}
			break;
	}

};

<%
/**
 * @FUNCTION NAME : displayAtchFileDirectory
 * @DESCRIPTION   : 첨부 파일 디렉토리를 TREE형태로 보여준다.
 * @PARAMETER     :
 *   1. argDirNo - DIRECTORY NO.
**/
%>
GamMachFcltySpecInqireModule.prototype.displayAtchFileDirectory = function(argDirNo) {

	this.$("#atchFileDirTreeList").empty();
	var inputVO = this.makeFormArgs("#dirForm");
	this.doAction('/fclty/gamSelectMachFcltySpecInqireAtchFileDirList.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var atchFileDirTreeNode = module.$('#atchFileDirTreeList');
				var atchFileDirTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var atchFileDir = result.resultList[i];
					atchFileDirTreeItems[atchFileDirTreeItems.length] = [atchFileDir.dirNo, atchFileDir.dirUpperNo, atchFileDir.dirNm];
				}
				module.tree = new dhtmlXTreeObject(atchFileDirTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("<c:url value='/js/codebase/imgs/dhxtree_skyblue/'/>");
				module.tree.loadJSArray(atchFileDirTreeItems);
				module.tree.setUserData('module', module);
 				module.tree.openAllItems(0);
				module.tree.module = module;
				module.tree.setOnClickHandler(module.onAtchFileDirTreeItemClick);
				if (argDirNo != "") {
					module.tree.selectItem(argDirNo);
					module.tree.focusItem(argDirNo);
					module.refreshDirData(argDirNo);
				}
 			}
		}
	});

};

<%
/**
 * @FUNCTION NAME : refreshDirData
 * @DESCRIPTION   : DIRECTORY DATA REFRESH (LIST)
 * @PARAMETER     :
 *   1. argDirNo - DIRECTORY NO.
**/
%>
GamMachFcltySpecInqireModule.prototype.refreshDirData = function(argDirNo) {

	if (argDirNo > 1) {
		this.$('#dirNo').val('' + argDirNo);
		var dirQueryOption = this.$('#dirQueryOption').val();
		var searchVO = this.getFormValues('#dirForm');
		this.doAction('/fclty/gamSelectMachFcltySpecInqireAtchFileDirPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#dirForm', result.result);
				module.makeDivValues('#dirForm', result.result);
				module.$('#dirQueryOption').val(dirQueryOption);
				module.$('#inputDirNm').val(result.result.dirNm);
				module.displayAtchFileList(argDirNo);
			} else {
				module.makeFormValues('#dirForm', {});
				module.makeDivValues('#dirForm', {});
				module.$('#dirQueryOption').val(dirQueryOption);
				module.displayAtchFileList("");
			}
		});
	} else {
		this.$('#dirNo').val("1");
		this.$('#dirNm').val("ROOT");
		this.$('#dirPath').val("/");
		this.$('#dirUpperNo').val("0");
		this.$('#depthSort').val("0");
		this.$('#leafYn').val("N");
		this.$('#dirFcltsJobSe').val("M");
		this.$('#inputDirNm').val("ROOT");
		this.displayAtchFileList("");
	}

};

<%
/**
 * @FUNCTION NAME : displayAtchFileList
 * @DESCRIPTION   : 첨부 파일 목록을 보여준다.
 * @PARAMETER     :
 *   1. argAtchFileDirNo - ATTACHE FILE DIRECTORY NO.
**/
%>
GamMachFcltySpecInqireModule.prototype.displayAtchFileList = function(argAtchFileDirNo) {

	this.makeFormValues('#fileForm', {});
	this.makeDivValues('#fileForm', {});
	this.$('#fileGrid').flexEmptyData();
	if (argAtchFileDirNo != null && argAtchFileDirNo != "") {
		this.$('#atchFileDirNo').val(argAtchFileDirNo);
		var detailOpt = this.getFormValues('#fileForm');
		this.$('#atchFileDirNo').val("");
		this.$('#fileGrid').flexOptions({params:detailOpt}).flexReload();
	}

};

<%
/**
 * @FUNCTION NAME : refreshFileData
 * @DESCRIPTION   : FILE DATA REFRESH (LIST)
 * @PARAMETER     :
 *   1. argAtchFileNo - ATTACHE FILE NO.
**/
%>
GamMachFcltySpecInqireModule.prototype.refreshFileData = function(argAtchFileNo) {

	if (argAtchFileNo != null && argAtchFileNo != "") {
		this.$('#atchFileNo').val(argAtchFileNo);
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectMachFcltySpecInqireFcltsAtchFilePk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#fileForm', result.result);
				module.makeDivValues('#fileForm', result.result);
			} else {
				module.makeFormValues('#fileForm', {});
				module.makeDivValues('#fileForm', {});
			}
		});
	} else {
		this.makeFormValues('#fileForm', {});
		this.makeDivValues('#fileForm', {});
	}

};

<%
/**
 * @FUNCTION NAME : selectAllFile
 * @DESCRIPTION   : ALL FILE SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.selectAllFile = function() {

	var rows = this.$('#fileGrid').flexGetData();
	var atchFileDataCount = rows.length;
	if (atchFileDataCount > 0) {
		for (var i=0; i<atchFileDataCount; i++) {
			var row = rows[i];
			row["atchFileSelChk"] = true;
			var rowid = this.$('#fileGrid')[0].dgrid.getRowId(i);
			this.$('#fileGrid').flexUpdateRow(rowid, row);
		}
	}

};

<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.downloadFile = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length > 0) {
		var row = selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : downloadMultiFile
 * @DESCRIPTION   : MULTI FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.downloadMultiFile = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('다운로드할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	for (var i=0; i<rows.length; i++) {
		var row = rows[i];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : displayPreviewFile
 * @DESCRIPTION   : FILE PREVIEW DISPLAY
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.displayPreviewFile = function() {

	var atchFilePreviewFlag = this.$('#fileGrid')[0].dgrid.isColumnHidden(4);
	var columnWidth = "200";
	if (atchFilePreviewFlag == true) {
		atchFilePreviewFlag = false;
		columnWidth = "200";
	} else {
		atchFilePreviewFlag = true;
		columnWidth = "300";
	}
	this.$('#fileGrid')[0].dgrid.setColumnHidden(4,atchFilePreviewFlag);
	this.$('#fileGrid')[0].dgrid.setColWidth(3, columnWidth);
	this._atchFilePreview = atchFilePreviewFlag;
	this.displayAtchFileList(this.$('#dirNo').val());

};

<%
/**
 * @FUNCTION NAME : showFcltsAtchFileViewPopup
 * @DESCRIPTION   : FCLTS ATTACHE FILE VIEW POPUP
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecInqireModule.prototype.showFcltsAtchFileViewPopup = function() {

	var inqire = true;
	var atchFileNo = this.$('#atchFileNo').val();
	var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
	var imageURL = "";
	if (atchFileNo == "") {
		return;
	}
	if (atchFileNmPhysicl != "") {
		imageURL = this.getPfPhotoUrl(atchFileNmPhysicl);
	}
    var searchOpts = {
		'atchFileNo':atchFileNo,
		'imageURL':imageURL,
		'inqire':inqire
    };
	this.doExecuteDialog('popupFcltsAtchFileView', '시설물 첨부 파일 보기', '/popup/showFcltsAtchFileViewPopup.do', null, searchOpts);

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
GamMachFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
			}
			this.changeDetailAreaForm(this.$('#mechFcltsSe').val());
			break;
		case 'fileTab':
			if (this._atchFileDirLoad == false) {
				this.displayAtchFileDirectory("");
				this._atchFileDirLoad = true;
				this.$('#fileGrid')[0].dgrid.setColumnHidden(4, true);
				this.$('#fileGrid')[0].dgrid.setColWidth(3, "300");
			}
			break;
	}

};

var module_instance = new GamMachFcltySpecInqireModule();

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
							<th>항구분</th>
							<td>
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>
							</td>
							<th>시설물 관리 그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="16" maxlength="14"/>
								<input id="sFcltsMngGroupNm" type="text" size="55" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2">
								<input type="hidden" id="sFcltsMngNo"/>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>시설물 분류</th>
							<td>
								<!--
								<input id="sGisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM067"/>
								 -->
								<select id="sGisPrtFcltyCd">
									<option value="" selected>전체</option>
									<option value="M1">컨테이너크레인</option>
									<option value="MA">부잔교</option>
									<option value="MB">건축 기계설비</option>
									<option value="00">하역장비현황</option>
								</select>
							</td>
							<th>시설 명</th>
							<td>
								<input id="sPrtFcltyNm" type="text" size="34" maxlength="80"/>
							</td>
							<th>소재지</th>
							<td>
								<input id="sLoc" type="text" size="34" maxlength="150"/>
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
				<li><a href="#listTab" class="emdTab">기계시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">기계시설 제원</a></li>
				<li><a href="#fileTab" class="emdTab">기계시설 첨부파일</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<table id="statusGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:8%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">제조금액 합계</th>
								<td><input type="text" size="18" id="sumMfcAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">도급금액 합계</th>
								<td><input type="text" size="18" id="sumContrAmt" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
									<button id="btnShowMap" data-role="showMap" data-gis-layer="gisArchFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
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
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">기계시설 일반</th>
								<td style="text-align:right;">
									<input type="hidden" id="fcltySpecExistYn"/>
									&nbsp;시설물관리번호 : &nbsp;
									<input type="text" size="20" id="fcltsMngNo" disabled/>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">GIS 　자산코드</th>
								<td>
									<input type="hidden" id="gisAssetsLocCd"/>
									<input type="hidden" id="gisAssetsLocNm"/>
									<input type="text" size="22" id="gisAssetsNm" disabled/>
								</td>
								<th style="width:10%; height:18px;">항구분／분　류</th>
								<td>
									<input type="hidden" id="prtFcltySeNm"/>
									<input type="text" size="3" id="gisAssetsPrtAtCode" disabled/>
									<input type="text" size="4" id="gisAssetsCd" disabled/>
									<input type="text" size="2" id="gisAssetsSubCd" disabled/>
									<input type="text" size="5" id="gisAssetsPrtAtCodeNm" disabled/>
									<select id="prtFcltySe" disabled>
										<option value="A">건축시설</option>
										<option value="C">토목시설</option>
										<option value="M">기계시설</option>
										<option value="E">전기시설</option>
										<option value="I">통신시설</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">시　설　코　드</th>
								<td>
									<input type="hidden" id="prtFcltyGisCd"/>
									<input type="hidden" id="gisPrtFcltyCdSub"/>
									<input type="hidden" id="gisPrtFcltyCdNm"/>
									<input type="text" size="3" id="gisPrtFcltyCdDisplay" disabled/>-
									<input type="text" size="6" id="gisPrtFcltySeq" disabled/>
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM067" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시　　설　　명</th>
								<td>
									<input type="text" size="33" id="prtFcltyNm" maxlength="80" disabled/>
								</td>
								<th style="width:10%; height:18px;">소　　재　　지</th>
								<td>
									<input type="hidden" id="laCrdnt"/>
									<input type="hidden" id="loCrdnt"/>
									<input type="hidden" id="lat"/>
									<input type="hidden" id="lng"/>
									<input type="hidden" id="gisAssetsLocplcLnm"/>
									<input type="hidden" id="loc"/>
									<input type="text" size="33" id="prtFcltyLoc" disabled/>
								</td>
								<th style="width:10%; height:18px;">규　격／단　위</th>
								<td>
									<input type="text" size="20" id="prtFcltyStndrd" maxlength="80" disabled/>／
									<input type="text" size="10" id="prtFcltyUnit" maxlength="10" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">면　적／수　량</th>
								<td>
									<input type="text" size="11" id="prtFcltyAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>／
									<input type="text" size="11" id="prtPrtFcltyCnt" class="ygpaNumber" maxlength="10" disabled/> 개
								</td>
								<th style="width:10%; height:18px;">설치일／변경일</th>
								<td>
									<input type="text" size="11" id="prtFcltyInstlDt" class="emdcal" disabled/>／
									<input type="text" size="11" id="prtFcltyChangeDt" class="emdcal" disabled/>
								</td>
								<th style="width:10%; height:18px;">만료일／담당자</th>
								<td>
									<input type="text" size="11" id="prtFcltyExprDt" class="emdcal" disabled/>／
									<input type="text" size="16" id="prtPrtFcltyMnger" maxlength="80" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">관　리　업　체</th>
								<td>
									<input type="text" size="2" id="prtFcltyMngEntrpsCd" maxlength="8" disabled/>
									<input type="text" size="17" id="prtFcltyMngEntrpsNm" disabled/>
								</td>
								<th style="width:10%; height:18px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8" disabled/>
									<input type="text" size="61" id="fcltsMngGroupNm" disabled/>
								</td>
							</tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">기계시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecFirstData">처음 자료</button>
									<button id="btnSpecPrevData">이전 자료</button>
									<button id="btnSpecNextData">다음 자료</button>
									<button id="btnSpecLastData">마지막 자료</button>
								</td>
							</tr>
						</table>
						<table id="fcltsSeDetailArea" class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">시설물　　구분</th>
								<td colspan="3">
									<input type="hidden" id="mechFcltsSeNm"/>
									<input type="hidden" id="eqpmnNm"/>
									<input type="hidden" id="instlYrmt"/>
									<input type="hidden" id="mfcCmpny"/>
									<input type="hidden" id="mfcAmt"/>
									<input type="hidden" id="operCmpny"/>
									<input type="hidden" id="mechFcltsClCd"/>
									<select id="mechFcltsSe" disabled>
										<option value="1">하역장비</option>
										<option value="2">항만부잔교</option>
										<option value="3">건축 기계설비</option>
									</select>
								</td>
							</tr>
						</table>
						<table id="cvlEngDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">장　　비　　명</th>
								<td>
									<input type="text" size="62" id="cvlEngEqpmnNm" maxlength="150" disabled/>
								</td>
								<th style="width:10%; height:18px;">장　비　번　호</th>
								<td>
									<input type="text" size="62" id="eqpmnNo" maxlength="50" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="cvlEngInstlYrmt" maxlength="7" disabled/>
								</td>
								<th style="width:10%; height:18px;">운　　영　　사</th>
								<td>
									<input type="text" size="62" id="cvlEngOperCmpny" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제　　작　　사</th>
								<td>
									<input type="text" size="62" id="cvlEngMfcCmpny" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">제　　작　　비</th>
								<td>
									<input type="text" size="59" id="cvlEngMfcAmt" class="ygpaNumber" maxlength="20" disabled/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제조　　검사자</th>
								<td>
									<input type="text" size="62" id="mfcChkUsr" maxlength="20" disabled/>
								</td>
								<th style="width:10%; height:18px;">아　웃　리　치</th>
								<td>
									<input type="text" size="59" id="outReach" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">백　　리　　치</th>
								<td>
									<input type="text" size="59" id="backReach" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">인　양　높　이</th>
								<td>
									<input type="text" size="59" id="refloatHt" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">처　리　능　력</th>
								<td>
									<input type="text" size="62" id="processAblty" maxlength="200" disabled/>
								</td>
								<th style="width:10%; height:18px;">주　　행　　폭</th>
								<td>
									<input type="text" size="59" id="driveWd" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">레　　일　　폭</th>
								<td>
									<input type="text" size="59" id="railWd" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">자　　　　　중</th>
								<td>
									<input type="text" size="59" id="selfLoad" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> 톤
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">윤　　하　　중</th>
								<td>
									<input type="text" size="62" id="wheelWght" maxlength="150" disabled/>
								</td>
								<th style="width:10%; height:18px;">정　격　하　중</th>
								<td>
									<input type="text" size="62" id="rageWght" maxlength="200" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">T/C　　형 태</th>
								<td>
									<select id="fmt" disabled>
										<option value="">선택</option>
										<option value="RMQC">RMQC(Rail Mounted Quayside Crane)</option>
										<option value="RTGC">RTGC(Rubber-Tired Grantry Crane)</option>
										<option value="RMGC">RMGC(Rail-Mounted Grantry Crane)</option>
										<option value="OHBC">OHBC(Over Head Bridge Crane)</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">T/C 장치작업</th>
								<td>
									<select id="stndrd" disabled>
										<option value="">선택</option>
										<option value="4단">4단 장치작업</option>
										<option value="5단">5단 장치작업</option>
										<option value="철송">철송</option>
									</select>
								</td>
							</tr>
						</table>
						<table id="bridgeDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">함　　선　　명</th>
								<td>
									<input type="text" size="62" id="shipEqpmnNm" maxlength="50" disabled/>
								</td>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="shipInstlYrmt" maxlength="7" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제　　작　　사</th>
								<td>
									<input type="text" size="62" id="shipMfcCmpny" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">제　　작　　비</th>
								<td>
									<input type="text" size="59" id="shipMfcAmt" class="ygpaNumber" maxlength="20" disabled/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">규　　　　　격</th>
								<td colspan="3">
									<input type="text" size="149" id="eqpmnStndrd" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">연　결　도　교</th>
								<td>
									<input type="text" size="59" id="linkBridge" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">연결도교　규격</th>
								<td>
									<input type="text" size="62" id="linkBridgeStndrd" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">고무　　방충재</th>
								<td>
									<input type="text" size="59" id="rubberFender" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">고무방충재규격</th>
								<td>
									<input type="text" size="62" id="fenderStndrd" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">적　재　톤　수</th>
								<td>
									<input type="text" size="59" id="capaTon" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> 톤
								</td>
								<th style="width:10%; height:18px;">전　기　방　식</th>
								<td>
									<input type="text" size="62" id="elctyMthd" maxlength="50" disabled/>
								</td>
							</tr>
						</table>
						<table id="archDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">건　　물　　명</th>
								<td>
									<input type="text" size="62" id="archEqpmnNm" maxlength="150" disabled/>
								</td>
								<th style="width:10%; height:18px;">운　　영　　사</th>
								<td>
									<input type="text" size="62" id="archOperCmpny" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="archInstlYrmt" maxlength="7" disabled/>
								</td>
								<th style="width:10%; height:18px;">도　　급　　자</th>
								<td>
									<input type="text" size="62" id="contrUsr" maxlength="20" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">도　　급　　액</th>
								<td>
									<input type="text" size="59" id="contrAmt" class="ygpaNumber" maxlength="20" disabled/> 원
								</td>
								<th style="width:10%; height:18px;">환기　공조방식</th>
								<td>
									<input type="text" size="62" id="vntltnArcndtMthd" maxlength="50" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">냉　방　열　원</th>
								<td>
									<input type="text" size="62" id="clngSrc" maxlength="25" disabled/>
								</td>
								<th style="width:10%; height:18px;">난　방　열　원</th>
								<td>
									<input type="text" size="62" id="htngSrc" maxlength="25" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">물　　탱　　크</th>
								<td>
									<input type="text" size="59" id="waterTank" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> 톤
								</td>
								<th style="width:10%; height:18px;">유류　저장탱크</th>
								<td>
									<input type="text" size="59" id="oilSaveTank" class="ygpaNumber" data-decimal-point="2" disabled/> ℓ
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">정화조　　형식</th>
								<td colspan="3">
									<input type="text" size="62" id="spictankFmt" maxlength="50" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">해당　건축시설</th>
								<td colspan="3">
									<input type="text" size="25" id="archFcltsMngNo" maxlength="20" disabled/> -
									<input type="text" size="108" id="archFcltsNm" disabled/>
								</td>
							</tr>
						</table>
						<table id="rmDetailArea" class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">비　　　　　고</th>
								<td colspan="3">
									<input type="text" size="149" id="rm" maxlength="1000" disabled/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 214. TAB 3 AREA (FILE) -->
			<div id="fileTab" class="emdTabPage" style="overflow:scroll;">
				<table class="detailPanel" style="width:100%;">
					<tr>
						<th style="width:10%; height:20px;">선택디렉토리</th>
						<td>
							<form id="dirForm">
								<input id="dirNo" type="hidden"/>
								<input id="dirNm" type="hidden"/>
								<input id="dirFcltsJobSe" type="hidden"/>
								<input id="dirUpperNo" type="hidden"/>
								<input id="dirPath" type="hidden"/>
								<input id="depthSort" type="hidden"/>
								<input id="leafYn" type="hidden"/>
								<input id="inputDirNm" type="text" size="50" maxlength="100" disabled/>
								<select id="dirQueryOption">
									<option value="">전체</option>
									<option value="A">건축시설</option>
									<option value="C">토목시설</option>
									<option value="M" selected>기계시설</option>
									<option value="E">전기시설</option>
									<option value="I">통신시설</option>
								</select>
							</form>
						</td>
						<td>
							<button id="btnAtchDirFileSearch">디렉토리/파일 검색</button>
						</td>
						<th style="width:10%; height:20px;">선택첨부파일</th>
						<td>
							<form id="fileForm">
								<input id="atchFileNo" type="hidden"/>
								<input id="atchFileNmPhysicl" type="hidden"/>
								<input id="atchFileSe" type="hidden"/>
								<input id="atchFileSeNm" type="hidden"/>
								<input id="atchFileSj" type="hidden"/>
								<input id="atchFileDirNo" type="hidden"/>
								<input id="atchFileDataSe" type="hidden"/>
								<input id="atchFileFcltsJobSe" type="hidden"/>
								<input id="atchFileFcltsMngNo" type="hidden"/>
								<input id="atchFileFcltsMngSeq" type="hidden"/>
								<input id="atchFileNmLogic" type="text" size="41" disabled/>
							</form>
						</td>
					</tr>
				</table>
				<table style="width:100%;">
					<tr>
						<td style="width:50%;">
							<div id="atchFileDirTreeList" class="tree" style="position:relative; margin:4px; width:455px; height:460px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill"></div>
						</td>
						<td style="width:50%;">
							<table id="fileGrid" style="margin:1px; display:none;"></table>
						</td>
					</tr>
				</table>
				<table class="detailPanel" style="width:100%;">
					<tr>
						<td style="width:50%;">
							<button id="btnDirRefresh">디렉토리 재조회</button>
						</td>
						<td style="width:50%; text-align:right;">
							<button id="btnFileAllSelect">전체 선택</button>
							<button id="btnFileDownload">파일 다운로드</button>
							<button id="btnFilePreview">파일 미리보기</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
