<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCvlEngFcltySpecInqire.jsp
 * @Description : 토목시설 제원 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.03.09  김영길          최초 생성
 *
 * author 김영길
 * since 2015.03.09
 *
 * Copyright (C) 2015 by LFIT  All right reserved.
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>
var fcltyMaintMngListPopup;
var fcltyMaintMngListGrid;
var fcltyMaintMngDataList = [];
<%
/**
 * @FUNCTION NAME : GamCvlEngFcltySpecInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCvlEngFcltySpecInqireModule() {}

GamCvlEngFcltySpecInqireModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectCvlEngFcltySpecInqireList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",	width:60,		sortable:false,		align:"center"},
					{display:"항만시설 명",			name:"prtFcltyNm",				width:200,		sortable:false,		align:"left"},
					{display:"시설물 관리 그룹",	name:"fcltsMngGroupNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설물 분류",			name:"gisPrtFcltyCdNm",			width:100,		sortable:false,		align:"left"},
					{display:"소재지",	 			name:"loc",						width:150,		sortable:false,		align:"left"},
					{display:"구조 형식",	 		name:"strctFmt",				width:150,		sortable:false,		align:"left"},
					{display:"선석",				name:"berth",					width:80,		sortable:false,		align:"right"},
					{display:"폭",				 	name:"wd",						width:80,		sortable:false,		align:"right"},
					{display:"길이",	 			name:"lt",						width:80,		sortable:false,		align:"right"},
					//{display:"주요 취급 화물", 		name:"stplHndlFrght",			width:150,		sortable:false,		align:"left"},
					//{display:"포장 종류",			name:"packKnd",					width:100,		sortable:false,		align:"left"},
					{display:"시설 면적",	 		name:"prtFcltyAr",				width:80,		sortable:false,		align:"right"},
					{display:"구분",	 			name:"prtFcltySeNm",			width:80,		sortable:false,		align:"left"},
					//{display:"설치 일자",	 		name:"prtFcltyInstlDt",			width:80,		sortable:false,		align:"center"},
					//{display:"변경 일자",	 		name:"prtFcltyChangeDt",		width:80,		sortable:false,		align:"center"},
					//{display:"만료 일자",	 		name:"prtFcltyExprDt",			width:80,		sortable:false,		align:"center"},
					{display:"규격",				name:"prtFcltyStndrd",			width:80,		sortable:false,		align:"left"},
					{display:"단위",	 			name:"prtFcltyUnit",			width:80,		sortable:false,		align:"left"},
					{display:"수량",	 			name:"prtPrtFcltyCnt",			width:80,		sortable:false,		align:"left"},
					//{display:"담당자",	 			name:"prtPrtFcltyMnger",		width:80,		sortable:false,		align:"left"},
					//{display:"관리 업체",	 		name:"prtFcltyMngEntrpsNm",		width:150,		sortable:false,		align:"left"},
					//{display:"접안 선박 규모",	 	name:"csdhpShipScl",			width:100,		sortable:false,		align:"right"},
					{display:"시설물 연장",	 		name:"fcltsExt",				width:100,		sortable:false,		align:"right"},
					{display:"방충재 종류",		 	name:"fenderKndCd",				width:100,		sortable:false,		align:"left"},
					{display:"방충재 배치 간격",	name:"fenderPmntItv",			width:120,		sortable:false,		align:"left"},
					{display:"방충재 형식",		 	name:"fenderFmt",				width:150,		sortable:false,		align:"left"},
					//{display:"에이프런 폭",	 		name:"apronWd",					width:100,		sortable:false,		align:"right"},
					//{display:"에이프런 포장 종류",	name:"apronPackKnd",			width:120,		sortable:false,		align:"left"},
					//{display:"에이프런 포장 구배",	name:"apronPackGrdnt",			width:150,		sortable:false,		align:"left"},
					//{display:"야적장 면적",	 		name:"yardAr",					width:100,		sortable:false,		align:"right"},
					//{display:"야적장 포장 종류",	name:"yardPackKnd",				width:120,		sortable:false,		align:"left"},
					{display:"시설물 관리 번호",	name:"fcltsMngNo",				width:130,		sortable:false,		align:"left"},
					//{display:"자산 위치",	 		name:"gisAssetsLocNm",			width:200,		sortable:false,		align:"left"},
					{display:"자산 명",	 			name:"gisAssetsNm",				width:200,		sortable:false,		align:"left"},
					{display:"유지보수기간",	 		name:"mntnRprCnstDt",			width:500,		sortable:false,		align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumYardAr').val($.number(data.sumYardAr));
			module.$('#sumBerth').val($.number(data.sumBerth));
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

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectCvlEngFcltySpecInqireFcltsAtchFileList.do',
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

	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._atchFileDirLoad = false;
	this._atchFilePreview = false;
	
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
GamCvlEngFcltySpecInqireModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectCvlEngFcltySpecInqireFcltsMngGroupNm.do', searchVO, function(module, result) {
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
 * @FUNCTION NAME : buildFcltyMaintMngList
 * @DESCRIPTION   : FCLTY MAINT MNG LIST를 구성한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.buildFcltyMaintMngList = function() {

	fcltyMaintMngDataList = [];
	this.$('#fcltyMaintMngList option').remove();
	var searchVO = [{ name: 'fcltsMngGroupNo', value: this.$('#fcltsMngGroupNo').val() },
					{ name: 'fcltsJobSe', value: "C" },
	                { name: 'fcltsMngNo', value: this.$('#fcltsMngNo').val() }
	               ];
	this.doAction('/fclty/gamSelectCvlEngFcltySpecInqireMntnRprDtlsList.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length <= 0) {
				module.$('#fcltyMaintMngList').append('<option value="">없음　　　　　　　　</option>');
			}
			$.each(result.resultList, function(){
				module.$('#fcltyMaintMngList').append('<option value="' + this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt + '">' + this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt + '</option>');
				fcltyMaintMngDataList[fcltyMaintMngDataList.length] = { 'mntnRprSeq' : this.mntnRprSeq,
																		'mntnRprCnstDt' : this.mntnRprCnstStartDt + '~' + this.mntnRprCnstEndDt,
																		'mntnRprCnstNm' : this.mntnRprCnstNm };
			});
		} else {
			module.$('#fcltyMaintMngList').append('<option value="">없음　　　　　　　　</option>');
		}
    });

};

<%
/**
 * @FUNCTION NAME : showFcltyMaintMngList
 * @DESCRIPTION   : FCLTY MAINT MNG LIST POPUP SHOW
 * @PARAMETER     :
 *   1. argParent - PARENT
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.showFcltyMaintMngList = function(argParent) {

	if (!fcltyMaintMngListPopup) {
		fcltyMaintMngListPopup = new dhtmlXPopup();
		fcltyMaintMngListGrid = fcltyMaintMngListPopup.attachGrid(430,200);
		fcltyMaintMngListGrid.setHeader("순번, 유지보수기간, 유지보수명");
		fcltyMaintMngListGrid.setInitWidths("50,150,220");
		fcltyMaintMngListGrid.setColAlign("center,center,left");
		fcltyMaintMngListGrid.setColTypes("ed,ed,ed");
		fcltyMaintMngListGrid.setColSorting("str,str,str");
		fcltyMaintMngListGrid.init();
	}
	if (fcltyMaintMngListPopup.isVisible()) {
		fcltyMaintMngListPopup.hide();
	} else {
		var x = window.dhx4.absLeft(argParent);
		var y = window.dhx4.absTop(argParent);
		var w = argParent.offsetWidth;
		var h = argParent.offsetHeight;
		fcltyMaintMngListPopup.show(x,y,w,h);
		fcltyMaintMngListGrid.clearAll();
		for (var i=0; i < fcltyMaintMngDataList.length ; i++) {
			var dataValue = fcltyMaintMngDataList[i].mntnRprSeq + ', ' + fcltyMaintMngDataList[i].mntnRprCnstDt + ', ' + fcltyMaintMngDataList[i].mntnRprCnstNm;
			fcltyMaintMngListGrid.addRow(fcltyMaintMngDataList[i].mntnRprSeq, dataValue);
		}
	}

};

<%
/**
 * @FUNCTION NAME : hideFcltyMaintMngList
 * @DESCRIPTION   : FCLTY MAINT MNG LIST POPUP HIDE
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.hideFcltyMaintMngList = function() {

	if (fcltyMaintMngListPopup) {
		fcltyMaintMngListPopup.hide();
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
GamCvlEngFcltySpecInqireModule.prototype.onAtchFileDirTreeItemClick = function(itemId) {

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
GamCvlEngFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

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
GamCvlEngFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {

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
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showCvlFcltsMngGroup.do', null);
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
	    case 'btnFcltyMaintMngList':
			this.showFcltyMaintMngList(this.$('#btnFcltyMaintMngList')[0]);
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
GamCvlEngFcltySpecInqireModule.prototype.onSubmit = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.loadData = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.refreshData = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
		this.buildFcltyMaintMngList();
	}
};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.selectData = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.selectFileData = function() {

	if (this._fileKeyValue == "") {
		return;
	}
	var atchFileNo = this._fileKeyValue;
	this._fileKeyValue = "";
	this.$("#fileGrid").selectFilterRow([{col:"atchFileNo", filter:atchFileNo}]);
	this.refreshFileData(atchFileNo);

};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.getFcltsMngGroupNoNm = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.firstData = function() {

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
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.prevData = function() {

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
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.nextData = function() {

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
		this.buildFcltyMaintMngList();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.lastData = function() {

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
		this.buildFcltyMaintMngList();
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
GamCvlEngFcltySpecInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#mainGrid").flexRowCount();
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
			var clone =	this.$('#mainGrid').clone();
			$(clone).find('th,td').each(function() {
				if($(this).css('display')=='none') {
					$(this).remove();
				}
				else {
					$(this).css('border-left', '0.1pt solid black');
					$(this).css('border-top', '0.1pt solid black');
					$(this).css('border-right', '0.1pt solid black');
					$(this).css('border-bottom', '0.1pt solid black');
				}
			});
			clone.find("tr:eq(0)").remove();
			clone.find("tr:eq(1)").remove();
			clone.find("tr:eq(0) td").css({"font-size":"15px","font-weight":"bold","background-color":"#BDBDBD","height":"35px"});
			clone.table2excel({
				filename: "토목시설목록",
			});
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
GamCvlEngFcltySpecInqireModule.prototype.displayAtchFileDirectory = function(argDirNo) {

	this.$("#atchFileDirTreeList").empty();
	var inputVO = this.makeFormArgs("#dirForm");
	this.doAction('/fclty/gamSelectCvlEngFcltySpecInqireAtchFileDirList.do', inputVO, function(module, result) {
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
				module.tree.openItem(1);
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
GamCvlEngFcltySpecInqireModule.prototype.refreshDirData = function(argDirNo) {

	if (argDirNo > 1) {
		this.$('#dirNo').val('' + argDirNo);
		var dirQueryOption = this.$('#dirQueryOption').val();
		var searchVO = this.getFormValues('#dirForm');
		this.doAction('/fclty/gamSelectCvlEngFcltySpecInqireAtchFileDirPk.do', searchVO, function(module, result){
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
		this.$('#dirFcltsJobSe').val("C");
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
GamCvlEngFcltySpecInqireModule.prototype.displayAtchFileList = function(argAtchFileDirNo) {

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
GamCvlEngFcltySpecInqireModule.prototype.refreshFileData = function(argAtchFileNo) {

	if (argAtchFileNo != null && argAtchFileNo != "") {
		this.$('#atchFileNo').val(argAtchFileNo);
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectCvlEngFcltySpecInqireFcltsAtchFilePk.do', searchVO, function(module, result){
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
GamCvlEngFcltySpecInqireModule.prototype.selectAllFile = function() {

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
 * @FUNCTION NAME : downloadMultiFile
 * @DESCRIPTION   : MULTI FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecInqireModule.prototype.downloadMultiFile = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.displayPreviewFile = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.showFcltsAtchFileViewPopup = function() {

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
GamCvlEngFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
			}
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

var module_instance = new GamCvlEngFcltySpecInqireModule();

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
								<input id="sGisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM070"/>
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
				<li><a href="#listTab" class="emdTab">토목시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">토목시설 제원</a></li>
				<li><a href="#fileTab" class="emdTab">토목시설 설계도서</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:8%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">야적장 면적</th>
								<td><input type="text" size="18" id="sumYardAr" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">선석</th>
								<td><input type="text" size="18" id="sumBerth" class="ygpaNumber" disabled="disabled"/></td>
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
								<th style="font-weight:bold; height:20px;">토목시설 일반</th>
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
									<input type="hidden" id="gisAssetsLocCd"/ disabled>
									<input type="hidden" id="gisAssetsLocNm"/ disabled>
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
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM070" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시　　설　　명</th>
								<td>
									<input type="text" size="33" id="prtFcltyNm" maxlength="80" disabled/>
								</td>
								<th style="width:10%; height:18px;">소　　재　　지</th>
								<td>
									<input type="text" size="33" id="loc" disabled/>
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
								<th style="width:10%; height:18px;">설치일／담당자</th>
								<td>
									<input type="text" size="11" id="prtFcltyInstlDt" class="emdcal" disabled/>／
									<input type="text" size="14" id="prtPrtFcltyMnger" maxlength="80" disabled/>
								</td>
								<th style="width:10%; height:18px;">유지보수　이력</th>
								<td>
									<select id="fcltyMaintMngList">
										<option value="">없음　　　　　　　　</option>
									</select>
									<button id="btnFcltyMaintMngList">조회</button>
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
								<th style="font-weight:bold; height:20px;">토목시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecFirstData">처음자료</button>
									<button id="btnSpecPrevData">이전자료</button>
									<button id="btnSpecNextData">다음자료</button>
									<button id="btnSpecLastData">마지막자료</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">주요 취급 화물</th>
								<td>
									<input type="hidden" id="cvlEngFcltsClCd" disabled/>
									<input type="hidden" id="stplMoorShip" disabled/>
									<input type="text" size="33" id="stplHndlFrght" maxlength="300" disabled/>
								</td>
								<th style="width:10%; height:18px;">구　조　형　식</th>
								<td colspan="3">
									<input type="text" size="93" id="strctFmt" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">기초 저면 토질</th>
								<td>
									<input type="text" size="33" id="baseBttmSoil" maxlength="150" disabled/>
								</td>
								<th style="width:10%; height:18px;">시설물　　연장</th>
								<td>
									<input type="text" size="33" id="fcltsExt" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/>
								</td>
								<th style="width:10%; height:18px;">접안 선박 규모</th>
								<td>
									<input type="text" size="33" id="csdhpShipScl" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">선석수 ／ 길이</th>
								<td>
									<input type="text" size="11" id="berth" class="ygpaNumber" maxlength="6" disabled/> 개 ／
									<input type="text" size="11" id="lt" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m
								</td>
								<th style="width:10%; height:18px;">　　　폭　　　</th>
								<td>
									<input type="text" size="30" id="wd" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">포　장　종　류</th>
								<td>
									<input type="text" size="33" id="packKnd" maxlength="30" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">에이프런　　폭</th>
								<td>
									<input type="text" size="30" id="apronWd" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m
								</td>
								<th style="width:10%; height:18px;">에이프런　포장</th>
								<td>
									<input type="text" size="33" id="apronPackKnd" maxlength="30" disabled/>
								</td>
								<th style="width:10%; height:18px;">포　장　구　배</th>
								<td>
									<input type="text" size="33" id="apronPackGrdnt" maxlength="60" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">상치폭／말뚝수</th>
								<td>
									<input type="text" size="11" id="permWd" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m ／
									<input type="text" size="11" id="pileQty" class="ygpaNumber" maxlength="6" disabled/> 개
								</td>
								<th style="width:10%; height:18px;">말뚝구경／연장</th>
								<td>
									<input type="text" size="14" id="pileClbr" class="ygpaNumber" data-decimal-point="2" maxlength="8" disabled/>／
									<input type="text" size="14" id="pileExt" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
								<th style="width:10%; height:18px;">널말뚝　　규격</th>
								<td>
									<input type="text" size="33" id="sheetFileStndrd" maxlength="100" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">상　재　하　중</th>
								<td>
									<input type="text" size="33" id="frostDmgWght" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
								<th style="width:10%; height:18px;">야적장　　면적</th>
								<td>
									<input type="text" size="30" id="yardAr" class="ygpaNumber" data-decimal-point="2" maxlength="13" disabled/> m<sup>2</sup>
								</td>
								<th style="width:10%; height:18px;">야적장포장종류</th>
								<td>
									<input type="text" size="33" id="yardPackKnd" maxlength="30" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">방충재　　종류</th>
								<td>
									<input type="text" size="33" id="fenderKndCd" maxlength="30" disabled/>
								</td>
								<th style="width:10%; height:18px;">방충재　　형식</th>
								<td>
									<input type="text" size="33" id="fenderFmt" maxlength="30" disabled/>
								</td>
								<th style="width:10%; height:18px;">방충재배치간격</th>
								<td>
									<input type="text" size="33" id="fenderPmntItv" maxlength="30" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">선　좌　수　심</th>
								<td>
									<input type="text" size="30" id="berthDpwt" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">급수전　　수량</th>
								<td>
									<input type="text" size="30" id="hydrntQy" class="ygpaNumber" maxlength="6" disabled/> 개
								</td>
								<th style="width:10%; height:18px;">소화전　　수량</th>
								<td>
									<input type="text" size="30" id="firepgQy" class="ygpaNumber" maxlength="6" disabled/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시작점　　위치</th>
								<td>
									<input type="text" size="33" id="beginPtLoc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">외측소파공피복</th>
								<td>
									<input type="text" size="33" id="outerSwaveCover" maxlength="60" disabled/>
								</td>
								<th style="width:10%; height:18px;">외측소파공경사</th>
								<td>
									<input type="text" size="33" id="outerSwaveSlpRate" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">종착점　　위치</th>
								<td>
									<input type="text" size="33" id="endPtLoc" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">내측소파공피복</th>
								<td>
									<input type="text" size="33" id="inSwaveCover" maxlength="60" disabled/>
								</td>
								<th style="width:10%; height:18px;">내측소파공경사</th>
								<td>
									<input type="text" size="33" id="inSwaveSlpRate" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">계선주 규격　1</th>
								<td>
									<input type="text" size="33" id="mrpostStndrd1" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">계선주 간격　1</th>
								<td>
									<input type="text" size="33" id="mrpostPmntItv1" maxlength="30" disabled/>
								</td>
								<th style="width:10%; height:18px;">견인력／수량 1</th>
								<td>
									<input type="text" size="20" id="mrpostPwr1" maxlength="30" disabled/>／
									<input type="text" size="5" id="mrpostQy1" class="ygpaNumber" maxlength="6" disabled/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">계선주 규격　2</th>
								<td>
									<input type="text" size="33" id="mrpostStndrd2" maxlength="100" disabled/>
								</td>
								<th style="width:10%; height:18px;">계선주 간격　2</th>
								<td>
									<input type="text" size="33" id="mrpostPmntItv2" maxlength="30" disabled/>
								</td>
								<th style="width:10%; height:18px;">견인력／수량 2</th>
								<td>
									<input type="text" size="20" id="mrpostPwr2" maxlength="30" disabled/>／
									<input type="text" size="5" id="mrpostQy2" class="ygpaNumber" maxlength="6" disabled/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">천　단　표　고</th>
								<td>
									<input type="hidden" id="upsideWd"/>
									<input type="text" size="33" id="upsideAltud" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/> m
								</td>
								<th style="width:10%; height:18px;">설　계　파　고</th>
								<td>
									<input type="text" size="33" id="planHegh" class="ygpaNumber" data-decimal-point="2" maxlength="10" disabled/>
								</td>
								<th style="width:10%; height:18px;">파랑주　　방향</th>
								<td>
									<input type="text" size="33" id="wavemainDir" maxlength="30" disabled/>
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
									<option value="C" selected>토목시설</option>
									<option value="M">기계시설</option>
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
