<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamArchFcltySpecMng.jsp
 * @Description : 건축시설 제원 관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.01.15  ACEWOLF          최초 생성
 *
 * author ACEWOLF
 * since 2015.01.15
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
 * @FUNCTION NAME : GamArchFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamArchFcltySpecMngModule() {}

GamArchFcltySpecMngModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectArchFcltySpecMngList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",	width:60,		sortable:false,		align:"center"},
					{display:"항만시설 명",			name:"gisAssetsNm",				width:150,		sortable:false,		align:"left"},
					{display:"시설물 관리 그룹",	name:"fcltsMngGroupNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설물 분류",			name:"archFcltsClCdNm",			width:100,		sortable:false,		align:"left"},
					{display:"소재지",	 			name:"loc",						width:150,		sortable:false,		align:"left"},
					{display:"준공 일자",	 		name:"bldDt",					width:80,		sortable:false,		align:"center"},
					{display:"기초 형식",	 		name:"baseFmt",					width:150,		sortable:false,		align:"left"},
					{display:"구조 형식",	 		name:"strctFmt",				width:150,		sortable:false,		align:"left"},
					{display:"연면적",	 			name:"ar",						width:80,		sortable:false,		align:"right"},
					{display:"건축 면적",		 	name:"archAr",					width:80,		sortable:false,		align:"right"},
					{display:"대지 면적",	 		name:"plotAr",					width:80,		sortable:false,		align:"right"},
					{display:"시설 면적",	 		name:"prtFcltyAr",				width:80,		sortable:false,		align:"right"},
					{display:"구분",	 			name:"prtFcltySeNm",			width:80,		sortable:false,		align:"left"},
					{display:"설치 일자",	 		name:"prtFcltyInstlDt",			width:80,		sortable:false,		align:"center"},
					{display:"변경 일자",	 		name:"prtFcltyChangeDt",		width:80,		sortable:false,		align:"center"},
					{display:"만료 일자",	 		name:"prtFcltyExprDt",			width:80,		sortable:false,		align:"center"},
					{display:"규격",				name:"prtFcltyStndrd",			width:80,		sortable:false,		align:"left"},
					{display:"단위",	 			name:"prtFcltyUnit",			width:80,		sortable:false,		align:"left"},
					{display:"수량",	 			name:"prtPrtFcltyCnt",			width:80,		sortable:false,		align:"left"},
					{display:"담당자",	 			name:"prtPrtFcltyMnger",		width:80,		sortable:false,		align:"left"},
					{display:"관리 업체",	 		name:"prtFcltyMngEntrpsNm",		width:150,		sortable:false,		align:"left"},
					{display:"설계 공사 명",	 	name:"planCnstNm",				width:150,		sortable:false,		align:"left"},
					{display:"설계 수행 회사",	 	name:"planExcCmpny",			width:150,		sortable:false,		align:"left"},
					{display:"설계 시작 일자",	 	name:"planBeginDt",				width:100,		sortable:false,		align:"center"},
					{display:"설계 종료 일자",		name:"planEndDt",				width:100,		sortable:false,		align:"center"},
					{display:"시공 공사 명",	 	name:"cnstrctCnstNm",			width:150,		sortable:false,		align:"left"},
					{display:"시공 수행 회사",	 	name:"cnstrctExcCmpny",			width:150,		sortable:false,		align:"left"},
					{display:"시공 시작 일자",	 	name:"cnstrctBeginDt",			width:100,		sortable:false,		align:"center"},
					{display:"시공 종료 일자",		name:"cnstrctEndDt",			width:100,		sortable:false,		align:"center"},
					{display:"하자 만료 일자",	 	name:"flawEndDt",				width:100,		sortable:false,		align:"center"},
					{display:"시설물 관리 번호",	name:"fcltsMngNo",				width:120,		sortable:false,		align:"left"},
					{display:"자산 명",	 			name:"gisAssetsNm",				width:200,		sortable:false,		align:"left"},
					{display:"자산 위치",	 		name:"gisAssetsLocNm",			width:200,		sortable:false,		align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumAr').val($.number(data.sumAr));
			module.$('#sumArchAr').val($.number(data.sumArchAr));
			module.$('#sumPlotAr').val($.number(data.sumPlotAr));
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
		module.enableListButtonItem();
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsMngNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#sFcltsMngGroupNo", "#sFcltsMngGroupNm");
	});

	this.$('#gisPrtFcltyCd').on('change',{module:this}, function(event){
		event.data.module.getNewGisPrtFcltySeq();
		event.data.module.setFcltsMngNo();
	});

	this.$("#prtFcltyMngEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#prtFcltyMngEntrpsCd", "#prtFcltyMngEntrpsNm");
	});

	this.$("#fcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#fcltsMngGroupNo", "#fcltsMngGroupNm");
	});

	this.$("#archFcltsClCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsClCdNm("#archFcltsClCd", "#archFcltsClCdNm");
	});

	this.$("#prtFcltyLoc").bind("change", {module: this}, function(event) {
		event.data.module.setLoc($(this).val());
	});

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectArchFcltySpecMngAtchFileList.do',
		dataType : 'json',
		colModel : [
					{display:"순번",				name:"atchFileSeq",				width:50,		sortable:false,		align:"center"},
					{display:"구분",				name:"atchFileSeNm",			width:50,		sortable:false,		align:"center"},
					{display:"설명",				name:"atchFileSj",				width:200,		sortable:false,		align:"left"},
					{display:"파일명",				name:"atchFileNmLogic",			width:160,		sortable:false,		align:"left"}
					],
		height: "420"
	});

	this.$("#fileGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectFileData();
	});

	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._filemode = 'modify';
		module._fileKeyValue = row.atchFileFcltsMngNo;
		module._fileSeq = row.atchFileSeq;
		module.loadFileDetail('fileTab');
		module.enableFileDetailInputItem();
		module.previewFile(row.atchFileNmPhysicl);
	});

	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._filemode = 'query';
	this._fileKeyValue = '';
	this._fileSeq = '';
	this._searchButtonClick = false;
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
	this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
	if (params != null) {
		if (params.action != null) {
			switch (params.action) {
				case "setFeature":
					this.$('#btnSaveMap').enable();
					this.$('#btnSaveMap').removeClass('ui-state-disabled');
					break;
				case "prtFcltyInqire":
					if (params.fcltsMngNo != null && params.fcltsMngNo != "") {
						this.$('#sPrtAtCode').val("");
						this.$('#sFcltsMngGroupNo').val("");
						this.$('#sFcltsMngGroupNm').val("");
						this.$('#sFcltsClCd').val("");
						this.$('#sPrtFcltyNm').val("");
						this.$('#sLoc').val("");
						this.$('#sFcltsMngNo').val(params.fcltsMngNo);
						this._mainmode = 'query';
						this._mainKeyValue = '';
						this.$("#mainTab").tabs("option", {active: 0});
						var searchOpt=this.makeFormArgs('#searchForm');
						this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
					}
					break;
			}
		}
	}

};

<%
/**
 * @FUNCTION NAME : isValidDate
 * @DESCRIPTION   : DATE STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. dateString - DATE STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamArchFcltySpecMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (dateString == "") {
			return false;
		}
	} else {
		if (dateString == "") {
			return true;
		}
	}
	var year = Number(dateString.substring(0,4));
	var month = Number(dateString.substring(5,7));
	var day = Number(dateString.substring(8,10));
	if (year > 9999 || year < 1900) {
		return false;
	}
	if (month > 12 || month < 1) {
		return false;
	}
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
		if (day > 31 || day < 1) {
			return false;
		}
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		if (day > 30 || day < 1) {
			return false;
		}
	} else if (month == 2) {
		if (day > 29 || day < 1) {
			return false;
		}
	} else {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidDateFromTo
 * @DESCRIPTION   : 기간 DATE STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. startDateString - START DATE STRING
 *   2. endDateString - END DATE STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamArchFcltySpecMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (startDateString == "" || endDateString == "") {
			return false;
		}
	} else {
		if (startDateString == "" && endDateString == "") {
			return true;
		}
	}
	var startDate = Number(startDateString.replace(/-/gi, ""));
	var endDate = Number(endDateString.replace(/-/gi, ""));
	if (startDate > endDate) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidFirstDate
 * @DESCRIPTION   : FIRST DATE STRING > SECOND DATE STRING을 검사한다.
 * @PARAMETER     :
 *   1. firstDateString - FIRST DATE STRING
 *   2. secondDateString - SECOND DATE STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamArchFcltySpecMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (firstDateString == "" || secondDateString == "") {
			return false;
		}
	} else {
		if (firstDateString == "" || secondDateString == "") {
			return true;
		}
	}
	var firstDate = Number(firstDateString.replace(/-/gi, ""));
	var secondDate = Number(secondDateString.replace(/-/gi, ""));
	if (firstDate > secondDate) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidAmount
 * @DESCRIPTION   : AMOUNT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. amountValue - AMOUNT VALUE
**/
%>
GamArchFcltySpecMngModule.prototype.isValidAmount = function(amountValue) {

	if (amountValue > 9999999999999999 || amountValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidAr
 * @DESCRIPTION   : AR에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. arValue - AR VALUE
**/
%>
GamArchFcltySpecMngModule.prototype.isValidAr = function(arValue) {

	if (arValue > 99999999.99 || arValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidCnt
 * @DESCRIPTION   : COUNT에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. cntValue - COUNT VALUE
**/
%>
GamArchFcltySpecMngModule.prototype.isValidCnt = function(cntValue) {

	if (cntValue > 99999 || cntValue < 0) {
		return false;
	}
	return true;

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
GamArchFcltySpecMngModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectArchFcltySpecMngFcltsMngGroupNm.do', searchVO, function(module, result) {
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
 * @FUNCTION NAME : getEntrpsNm
 * @DESCRIPTION   : 업체 명을 구한다.
 * @PARAMETER     :
 *   1. argEntrpsCdVariableName - 업체 코드 변수 명
 *   2. argEntrpsNmVariableName - 업체 명 변수 명
**/
%>
GamArchFcltySpecMngModule.prototype.getEntrpsNm = function(argEntrpsCdVariableName, argEntrpsNmVariableName) {

	var sEntrpsNm = "";
	if (argEntrpsCdVariableName == null || argEntrpsCdVariableName == "") {
		return sEntrpsNm;
	}
	var sEntrpscd = this.$(argEntrpsCdVariableName).val();
	if (sEntrpscd.length() == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/fclty/gamSelectArchFcltySpecMngEntrpsNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				sEntrpsNm = result.sEntrpsNm;
				if (argEntrpsNmVariableName != null && argEntrpsNmVariableName != "") {
					module.$(argEntrpsNmVariableName).val(result.sEntrpsNm);
				}
			}
			return sEntrpsNm;
		});
	}
	if (argEntrpsNmVariableName != null && argEntrpsNmVariableName != "") {
		this.$(argEntrpsNmVariableName).val(sEntrpsNm);
	}
	return sEntrpsNm;

};

<%
/**
 * @FUNCTION NAME : getFcltsClCdNm
 * @DESCRIPTION   : 시설물 분류 코드 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.getFcltsClCdNm = function(argFcltsClCdVariableName, argFcltsClCdNmVariableName) {

	var sFcltsClCdNm = "";
	if (argFcltsClCdVariableName == null || argFcltsClCdVariableName == "") {
		return sFcltsClCdNm;
	}
	var sFcltsClCd = this.$(argFcltsClCdVariableName).val();
	if (sFcltsClCd.length == 14) {
		var searchVO = { 'sFcltsClCd':sFcltsClCd };
		this.doAction('/fclty/gamSelectArchFcltySpecMngFcltsClCdNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				sFcltsClCdNm = result.sFcltsClCdNm;
				if (argFcltsClCdNmVariableName != null && argFcltsClCdNmVariableName != "") {
					module.$(argFcltsClCdNmVariableName).val(result.sFcltsClCdNm);
				}
			}
			return sFcltsClCdNm;
		});
	}
	if (argFcltsClCdNmVariableName != null && argFcltsClCdNmVariableName != "") {
		this.$(argFcltsClCdNmVariableName).val(sFcltsClCdNm);
	}
	return sFcltsClCdNm;

};

<%
/**
 * @FUNCTION NAME : setLoc
 * @DESCRIPTION   : 소재지를 설정한다.
 * @PARAMETER     :
 *   1. argPrtFcltyLoc - 소재지
**/
%>
GamArchFcltySpecMngModule.prototype.setLoc = function(argPrtFcltyLoc) {

	var loc = this.$('#loc').val();
	if (loc != argPrtFcltyLoc) {
		this.$('#loc').val(argPrtFcltyLoc);
	}

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
GamArchFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchFcltsMngGroupNo':
			if (msg == 'ok') {
				this.$('#sFcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#sFcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#sFcltsClCd').focus();
			}
			break;
		case 'popupSpecGisAssetsCd':
			if (msg == 'ok') {
				this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
				this.$('#gisAssetsPrtAtCodeNm').val(value.gisAssetsPrtAtCodeNm);
				this.$('#gisAssetsCd').val(value.gisAssetsCd);
				this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
				this.$('#gisAssetsNm').val(value.gisAssetsNm);
				this.$('#gisAssetsLocCd').val(value.gisAssetsLocCd);
				this.$('#gisAssetsLocNm').val(value.gisAssetsLocNm);
				var gisAssetsLocplc = value.gisAssetsLocplc;
				var gisAssetsLnm = value.gisAssetsLnm;
				var gisAssetsLnmSub = value.gisAssetsLnmSub;
				var prtFcltyLoc = "";
				if (gisAssetsLocplc != "") {
					if (gisAssetsLnm != "") {
						if (gisAssetsLnmSub != "") {
							prtFcltyLoc = gisAssetsLocplc + " " + gisAssetsLnm + "-" + gisAssetsLnmSub;
						} else {
							prtFcltyLoc = gisAssetsLocplc + " " + gisAssetsLnm;
						}
					} else {
						if (gisAssetsLnmSub != "") {
							prtFcltyLoc = gisAssetsLocplc + " " + gisAssetsLnmSub;
						} else {
							prtFcltyLoc = gisAssetsLocplc;
						}
					}
				}
				this.$('#gisAssetsLocplcLnm').val(prtFcltyLoc);
				this.$('#prtFcltyLoc').val(prtFcltyLoc);
				this.$('#loc').val(prtFcltyLoc);
				this.getNewGisPrtFcltySeq();
				this.setFcltsMngNo();
				this.$('#gisPrtFcltyCd').focus();
			}
			break;
		case 'popupSpecPrtFcltyMngEntrpsCd':
			if (msg == 'ok') {
				this.$('#prtFcltyMngEntrpsCd').val(value.entrpscd);
				this.$('#prtFcltyMngEntrpsNm').val(value.entrpsNm);
				this.$('#prtFcltyMngEntrpsCd').focus();
			}
			break;
		case 'popupSpecFcltsMngGroupNo':
			if (msg == 'ok') {
				this.$('#fcltsMngGroupNo').val(value.fcltsMngGroupNo);
				this.$('#fcltsMngGroupNm').val(value.fcltsMngGroupNm);
				this.$('#archFcltsClCd').focus();
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
GamArchFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnDelete':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'btnSaveMap':
			this.saveMap();
			break;
		case 'btnSpecInsert':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.makeFormValues('#detailForm', {});
			this.makeDivValues('#detailForm', {});
			this.disableDetailInputItem();
			this.addData();
			break;
	    case 'btnSpecSave':
	    	this.saveData();
			break;
		case 'btnSpecRemove':
			this.deleteData();
			break;
		case 'btnFileUpload':
			this.uploadFile();
			break;
		case 'btnFileDownload':
			this.downloadFile();
			break;
	    case 'btnFileSave':
	    	this.saveFileData();
			break;
		case 'btnFileRemove':
			this.deleteFileData();
			break;
		case 'popupSearchFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFcltsMngGroup.do', null);
			break;
		case 'popupSpecGisAssetsCd':
			this.doExecuteDialog(buttonId, "자산 선택", '/popup/showAssetsCd.do', null);
			break;
		case 'popupSpecPrtFcltyMngEntrpsCd':
			this.doExecuteDialog(buttonId, "관리 업체 선택", '/popup/showEntrpsInfo.do', null);
			break;
		case 'popupSpecFcltsMngGroupNo':
			this.doExecuteDialog(buttonId, "시설물 관리 그룹 선택", '/popup/showFcltsMngGroup.do', null);
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
GamArchFcltySpecMngModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._filemode = 'query';
	this._fileKeyValue = '';
	this._fileSeq = '';
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
GamArchFcltySpecMngModule.prototype.loadData = function() {

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
GamArchFcltySpecMngModule.prototype.refreshData = function() {

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
GamArchFcltySpecMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/fclty/gamSelectArchFcltySpecMngPk.do', searchVO, function(module, result){
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
GamArchFcltySpecMngModule.prototype.selectData = function() {

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
	} else if (this._mainmode != 'insert' && this._mainmode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	if (this._mainKeyValue == "") {
		return;
	}
	var fcltsMngNo = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"fcltsMngNo", filter:fcltsMngNo}]);
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : getNewFileSeq
 * @DESCRIPTION   : 새로운 FILE 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.getNewFileSeq = function() {

	var searchVO = this.makeFormArgs("#fileForm");
	var atchFileFcltsMngNo = this.$('#atchFileFcltsMngNo').val();
	if (atchFileFcltsMngNo == "") {
		return;
	}
	this.doAction('/fclty/gamSelectArchFcltySpecMngAtchFileMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#atchFileSeq').val(result.sMaxSeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : previewFile
 * @DESCRIPTION   : FILE PREVIEW
 * @PARAMETER     :
 *   1. atchFileNmPhysicl - ATTCH FILE NAME PHYSICAL
**/
%>
GamArchFcltySpecMngModule.prototype.previewFile = function(atchFileNmPhysicl) {

	if (atchFileNmPhysicl != null || atchFileNmPhysicl != "") {
		var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
		if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
			$imgURL = this.getPfPhotoUrl(atchFileNmPhysicl);
			this.$("#previewImage").attr("src", $imgURL);
		} else {
			this.$("#previewImage").removeAttr("src");
		}
	}

};

<%
/**
 * @FUNCTION NAME : loadFileDetail
 * @DESCRIPTION   : FILE 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamArchFcltySpecMngModule.prototype.loadFileDetail = function(tabId) {

	if (tabId == 'listTab') {
		this.makeFormValues('#fileForm', {});
		this.makeDivValues('#fileForm', {});
		this.$('#fileGrid').flexEmptyData();
		this.$('#previewImage').attr('src', '');
		if (this._mainKeyValue == "") {
			return;
		}
		this.$('#atchFileFcltsMngNo').val(this._mainKeyValue);
		var detailOpt = this.getFormValues('#fileForm');
		this.$('#fileGrid').flexOptions({params:detailOpt}).flexReload();
	} else if (tabId == 'fileTab') {
		var atchFileFcltsMngNo = this.$('#atchFileFcltsMngNo').val();
		var atchFileSeq = this.$('#atchFileSeq').val();
		if (atchFileFcltsMngNo == "" || atchFileFcltsMngNo != this._fileKeyValue ||
			atchFileSeq == "" || atchFileSeq != this._fileSeq) {
			this.$('#atchFileFcltsMngNo').val(this._fileKeyValue);
			this.$('#atchFileSeq').val(this._fileSeq);
		}
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectArchFcltySpecMngAtchFilePk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#fileForm', result.result);
				module.makeDivValues('#fileForm', result.result);
				var atchFileNmPhysicl = module.$('#atchFileNmPhysicl').val();
				module.previewFile(atchFileNmPhysicl);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : selectFileData
 * @DESCRIPTION   : FILE DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.selectFileData = function() {

	if (this._filemode == 'query') {
		this.enableFileDetailInputItem();
		return;
	} else if (this._filemode != 'insert' && this._filemode != 'modify') {
		this.enableFileDetailInputItem();
		return;
	}
	if (this._fileKeyValue == "" || this._fileSeq == "") {
		this.disableFileDetailInputItem();
		return;
	}
	var atchFileFcltsMngNo = this._fileKeyValue;
	var atchFileSeq = this._fileSeq;
	this.$("#fileGrid").selectFilterRow([{col:"atchFileFcltsMngNo", filter:atchFileFcltsMngNo},
										 {col:"atchFileSeq", filter:atchFileSeq}]);
	this._filemode = 'modify';
	this.loadFileDetail('fileTab');
	this.enableFileDetailInputItem();

};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.getFcltsMngGroupNoNm = function() {

	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : getNewGisPrtFcltySeq
 * @DESCRIPTION   : 새로운 GIS 항만 시설 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.getNewGisPrtFcltySeq = function() {

	var searchVO = this.makeFormArgs("#detailForm");
	var gisAssetsPrtAtCode = this.$('#gisAssetsPrtAtCode').val();
	var gisAssetsCd = this.$('#gisAssetsCd').val();
	var gisAssetsSubCd = this.$('#gisAssetsSubCd').val();
	var gisPrtFcltyCd = this.$('#gisPrtFcltyCd').val();
	var prtFcltySe = this.$('#prtFcltySe').val();
	this.$('#gisPrtFcltyCdDisplay').val(gisPrtFcltyCd);
	if (gisAssetsPrtAtCode == "" || gisAssetsCd == "" || gisAssetsSubCd == "" || gisPrtFcltyCd == "" || prtFcltySe == "") {
		return;
	}
	this.doAction('/fclty/gamSelectArchFcltySpecMngMaxGisPrtFcltySeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#gisPrtFcltySeq').val(result.sMaxGisPrtFcltySeq);
		}
	});

};

<%
/**
 * @FUNCTION NAME : setFcltsMngNo
 * @DESCRIPTION   : 시설물 관리 번호을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.setFcltsMngNo = function() {

	var gisAssetsPrtAtCode = this.$('#gisAssetsPrtAtCode').val();
	var gisAssetsCd = this.$('#gisAssetsCd').val();
	var gisAssetsSubCd = this.$('#gisAssetsSubCd').val();
	var gisPrtFcltyCd = this.$('#gisPrtFcltyCd').val();
	var prtFcltySe = this.$('#prtFcltySe').val();
	var gisPrtFcltySeq = this.$('#gisPrtFcltySeq').val();
	if (gisAssetsPrtAtCode == "" || gisAssetsCd == "" || gisAssetsSubCd == "" || gisPrtFcltyCd == "" || prtFcltySe == "" || gisPrtFcltySeq == "") {
		this.$('#fcltsMngNo').val("");
	} else {
		this.$('#fcltsMngNo').val(gisPrtFcltyCd + gisPrtFcltySeq + gisAssetsPrtAtCode + gisAssetsCd + gisAssetsSubCd + prtFcltySe);
	}

};

<%
/**
 * @FUNCTION NAME : getNewAtchFileSeq
 * @DESCRIPTION   : 새로운 첨부파일 순번을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.getNewAtchFileSeq = function() {

	var searchVO = this.makeFormArgs("#fileForm");
	var atchFileFcltsMngNo = this.$('#atchFileFcltsMngNo').val();
	if (atchFileFcltsMngNo == "") {
		return;
	}
	this.doAction('/fclty/gamSelectArchFcltySpecMngAtchFileMaxSeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#atchFileSeq').val(result.sMaxSeq);
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
GamArchFcltySpecMngModule.prototype.addData = function() {

	this.$('#fcltsMngNo').val("");
	this.$('#gisAssetsLocCd').val("");
	this.$('#gisAssetsLocNm').val("");
	this.$('#gisAssetsNm').val("");
	this.$('#prtFcltySeNm').val("건축시설");
	this.$('#gisAssetsPrtAtCode').val("");
	this.$('#gisAssetsCd').val("");
	this.$('#gisAssetsSubCd').val("");
	this.$('#gisAssetsPrtAtCodeNm').val("");
	this.$('#prtFcltySe').val("A");
	this.$('#prtFcltyGisCd').val("");
	this.$('#gisPrtFcltyCdSub').val("");
	this.$('#gisPrtFcltyCdNm').val("");
	this.$('#gisPrtFcltyCd').val("");
	this.$('#gisPrtFcltySeq').val("");
	this.$('#prtFcltyNm').val("");
	this.$('#laCrdnt').val("");
	this.$('#loCrdnt').val("");
	this.$('#lat').val("");
	this.$('#lng').val("");
	this.$('#gisAssetsLocplcLnm').val("");
	this.$('#loc').val("");
	this.$('#prtFcltyLoc').val("");
	this.$('#prtFcltyStndrd').val("");
	this.$('#prtFcltyUnit').val("");
	this.$('#prtFcltyAr').val("0");
	this.$('#prtPrtFcltyCnt').val("");
	this.$('#prtFcltyInstlDt').val("");
	this.$('#prtFcltyChangeDt').val("");
	this.$('#prtFcltyExprDt').val("");
	this.$('#prtPrtFcltyMnger').val("");
	this.$('#prtFcltyMngEntrpsCd').val("");
	this.$('#prtFcltyMngEntrpsNm').val("");
	this.$('#fcltsMngGroupNo').val("");
	this.$('#fcltsMngGroupNm').val("");
	this.$('#archFcltsClCdNm').val("");
	this.$('#archFcltsClCd').val("");
	this.$('#bldDt').val("");
	this.$('#strctFmt').val("");
	this.$('#flawEndDt').val("");
	this.$('#baseFmt').val("");
	this.$('#ar').val("0");
	this.$('#archAr').val("0");
	this.$('#plotAr').val("0");
	this.$('#mainUsagePrpos').val("");
	this.$('#prkAr').val("0");
	this.$('#prkCnt').val("0");
	this.$('#osdPrkAr').val("0");
	this.$('#osdPrkCnt').val("0");
	this.$('#isdPrkAr').val("0");
	this.$('#isdPrkCnt').val("0");
	this.$('#exhaustDuctEnnc').val("N");
	this.$('#vntltnArcndtMthd').val("");
	this.$('#liftOperMthd').val("");
	this.$('#liftCntPsngr').val("0");
	this.$('#liftCntEmgcy').val("0");
	this.$('#liftCntCargo').val("0");
	this.$('#elctyLeadInCapa').val("0");
	this.$('#sbtLoc').val("");
	this.$('#spictankFmt').val("");
	this.$('#swgClupfcltyLoc').val("");
	this.$('#wrtTankLoc').val("");
	this.$('#oilSavefcltyLoc').val("");
	this.$('#htngEnnc').val("N");
	this.$('#clngSrc').val("");
	this.$('#clngEnnc').val("N");
	this.$('#htngSrc').val("");
	this.$('#fcg').val("");
	this.$('#itr').val("");
	this.$('#ceil').val("");
	this.$('#roof').val("");
	this.$('#roofWtprf').val("");
	this.$('#planCnstNm').val("");
	this.$('#planExcCmpny').val("");
	this.$('#planBeginDt').val("");
	this.$('#planEndDt').val("");
	this.$('#cnstrctCnstNm').val("");
	this.$('#cnstrctExcCmpny').val("");
	this.$('#cnstrctBeginDt').val("");
	this.$('#cnstrctEndDt').val("");
	this.$('#rm').val("");
	this.enableDetailInputItem();
	this.$('#gisPrtFcltyCd').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsMngNo = this.$('#fcltsMngNo').val();
	var gisAssetsPrtAtCode = this.$('#gisAssetsPrtAtCode').val();
	var gisAssetsCd = this.$('#gisAssetsCd').val();
	var gisAssetsSubCd = this.$('#gisAssetsSubCd').val();
	var prtFcltySe = this.$('#prtFcltySe').val();
	var gisPrtFcltySeq = this.$('#gisPrtFcltySeq').val();
	var gisPrtFcltyCd = this.$('#gisPrtFcltyCd').val();
	var prtFcltyNm = this.$('#prtFcltyNm').val();
	var prtFcltyLoc = this.$('#prtFcltyLoc').val();
	var prtFcltyAr = Number(this.$('#prtFcltyAr').val().replace(/,/gi, ""));
	var prtPrtFcltyCnt = Number(this.$('#prtPrtFcltyCnt').val().replace(/,/gi, ""));
	var prtFcltyInstlDt = this.$('#prtFcltyInstlDt').val();
	var prtFcltyChangeDt = this.$('#prtFcltyChangeDt').val();
	var prtFcltyExprDt = this.$('#prtFcltyExprDt').val();
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var archFcltsClCd = this.$('#archFcltsClCd').val();
	var bldDt = this.$('#bldDt').val();
	var ar = Number(this.$('#ar').val().replace(/,/gi, ""));
	var plotAr = Number(this.$('#plotAr').val().replace(/,/gi, ""));
	var archAr = Number(this.$('#archAr').val().replace(/,/gi, ""));
	var prkAr = Number(this.$('#prkAr').val().replace(/,/gi, ""));
	var prkCnt = Number(this.$('#prkCnt').val().replace(/,/gi, ""));
	var osdPrkAr = Number(this.$('#osdPrkAr').val().replace(/,/gi, ""));
	var osdPrkCnt = Number(this.$('#osdPrkCnt').val().replace(/,/gi, ""));
	var isdPrkAr = Number(this.$('#isdPrkAr').val().replace(/,/gi, ""));
	var isdPrkCnt = Number(this.$('#isdPrkCnt').val().replace(/,/gi, ""));
	var liftCntPsngr = Number(this.$('#liftCntPsngr').val().replace(/,/gi, ""));
	var liftCntEmgcy = Number(this.$('#liftCntEmgcy').val().replace(/,/gi, ""));
	var liftCntCargo = Number(this.$('#liftCntCargo').val().replace(/,/gi, ""));
	var elctyLeadInCapa = Number(this.$('#elctyLeadInCapa').val().replace(/,/gi, ""));
	var exhaustDuctEnnc = this.$('#exhaustDuctEnnc').val();
	var htngEnnc = this.$('#htngEnnc').val();
	var clngEnnc = this.$('#clngEnnc').val();
	var flawEndDt = this.$('#flawEndDt').val();
	var planBeginDt = this.$('#planBeginDt').val();
	var planEndDt = this.$('#planEndDt').val();
	var cnstrctBeginDt = this.$('#cnstrctBeginDt').val();
	var cnstrctEndDt = this.$('#cnstrctEndDt').val();
	if (gisAssetsPrtAtCode == "" || gisAssetsCd == "" || gisAssetsSubCd == "") {
		alert('GIS 자산 코드가 부정확합니다.');
		return;
	}
	if (gisPrtFcltyCd == "") {
		alert('GIS 항만 시설 코드가 부정확합니다.');
		this.$("#gisPrtFcltyCd").focus();
		return;
	}
	if (gisPrtFcltySeq == "") {
		alert('GIS 항만 시설 순번이 부정확합니다.');
		this.$("#prtFcltySe").focus();
		return;
	}
	if (prtFcltySe != "A" && prtFcltySe != "C" && prtFcltySe != "M" && prtFcltySe != "E" && prtFcltySe != "I") {
		alert('항만 시설 분류가 부정확합니다.');
		this.$("#prtFcltySe").focus();
		return;
	}
	if (fcltsMngNo == "") {
		alert('시설물 관리 번호가 부정확합니다.');
		return;
	}
	if (prtFcltyNm == "") {
		alert('시설 명이 부정확합니다.');
		this.$("#prtFcltyNm").focus();
		return;
	}
	if (prtFcltyLoc == "") {
		alert('소재지가 부정확합니다.');
		this.$("#prtFcltyLoc").focus();
		return;
	}
	if (this.isValidAr(prtFcltyAr) == false) {
		alert('항만 시설 면적이 부정확합니다.');
		this.$("#prtFcltyAr").focus();
		return;
	}
	if (this.isValidCnt(prtPrtFcltyCnt) == false) {
		alert('항만 시설 수량이 부정확합니다.');
		this.$("#prtPrtFcltyCnt").focus();
		return;
	}
	if (this.isValidDate(prtFcltyInstlDt, false) == false) {
		alert('항만 시설 설치 일자가 부정확합니다.');
		this.$("#prtFcltyInstlDt").focus();
		return;
	}
	if (this.isValidDate(prtFcltyChangeDt, false) == false) {
		alert('항만 시설 변경 일자가 부정확합니다.');
		this.$("#prtFcltyChangeDt").focus();
		return;
	}
	if (this.isValidFirstDate(prtFcltyInstlDt, prtFcltyChangeDt, false) == false) {
		alert('항만 시설 변경 일자가 항만 시설 설치 일자보다 큽니다.');
		this.$("#prtFcltyChangeDt").focus();
		return;
	}
	if (this.isValidDate(prtFcltyExprDt, false) == false) {
		alert('항만 시설 만료 일자가 부정확합니다.');
		this.$("#prtFcltyExprDt").focus();
		return;
	}
	if (this.isValidFirstDate(prtFcltyChangeDt, prtFcltyExprDt, false) == false) {
		alert('항만 시설 만료 일자가 항만 시설 변경 일자보다 큽니다.');
		this.$("#prtFcltyExprDt").focus();
		return;
	}
	if (fcltsMngGroupNo == "") {
		alert('시설물 관리 그룹이 부정확합니다.');
		this.$("#fcltsMngGroupNo").focus();
		return;
	}
	if (archFcltsClCd == "") {
		alert('시설물 분류가 부정확합니다.');
		this.$("#archFcltsClCd").focus();
		return;
	}
	if (this.isValidDate(bldDt, false) == false) {
		alert('준공 일자가 부정확합니다.');
		this.$("#bldDt").focus();
		return;
	}
	if (this.isValidAr(ar) == false) {
		alert('연면적이 부정확합니다.');
		this.$("#ar").focus();
		return;
	}
	if (this.isValidAr(plotAr) == false) {
		alert('대지 면적이 부정확합니다.');
		this.$("#plotAr").focus();
		return;
	}
	if (this.isValidAr(archAr) == false) {
		alert('건축 면적이 부정확합니다.');
		this.$("#archAr").focus();
		return;
	}
	if (this.isValidAr(prkAr) == false) {
		alert('주차 면적이 부정확합니다.');
		this.$("#prkAr").focus();
		return;
	}
	if (this.isValidCnt(prkCnt) == false) {
		alert('주차 대수가 부정확합니다.');
		this.$("#prkCnt").focus();
		return;
	}
	if (this.isValidAr(osdPrkAr) == false) {
		alert('옥외 주차 면적이 부정확합니다.');
		this.$("#osdPrkAr").focus();
		return;
	}
	if (this.isValidCnt(osdPrkCnt) == false) {
		alert('옥외 주차 대수가 부정확합니다.');
		this.$("#osdPrkCnt").focus();
		return;
	}
	if (this.isValidAr(isdPrkAr) == false) {
		alert('옥내 주차 면적이 부정확합니다.');
		this.$("#isdPrkAr").focus();
		return;
	}
	if (this.isValidCnt(isdPrkCnt) == false) {
		alert('옥내 주차 대수가 부정확합니다.');
		this.$("#isdPrkCnt").focus();
		return;
	}
	if (this.isValidCnt(liftCntPsngr) == false) {
		alert('승객용 승강기 대수가 부정확합니다.');
		this.$("#liftCntPsngr").focus();
		return;
	}
	if (this.isValidCnt(liftCntEmgcy) == false) {
		alert('비상용 승강기 대수가 부정확합니다.');
		this.$("#liftCntEmgcy").focus();
		return;
	}
	if (this.isValidCnt(liftCntCargo) == false) {
		alert('화물용 승강기 대수가 부정확합니다.');
		this.$("#liftCntCargo").focus();
		return;
	}
	if (this.isValidAr(elctyLeadInCapa) == false) {
		alert('전기 인입 용량이 부정확합니다.');
		this.$("#elctyLeadInCapa").focus();
		return;
	}
	if (exhaustDuctEnnc != "Y" && exhaustDuctEnnc != "N") {
		alert('배기 닥트 유무가 부정확합니다.');
		this.$("#exhaustDuctEnnc").focus();
		return;
	}
	if (htngEnnc != "Y" && htngEnnc != "N") {
		alert('난방 유무가 부정확합니다.');
		this.$("#htngEnnc").focus();
		return;
	}
	if (clngEnnc != "Y" && clngEnnc != "N") {
		alert('냉방 유무가 부정확합니다.');
		this.$("#clngEnnc").focus();
		return;
	}
	if (this.isValidDate(flawEndDt, false) == false) {
		alert('하자 만료 일자가 부정확합니다.');
		this.$("#flawEndDt").focus();
		return;
	}
	if (this.isValidDate(planBeginDt, false) == false) {
		alert('설계 시작 일자가 부정확합니다.');
		this.$("#planBeginDt").focus();
		return;
	}
	if (this.isValidDate(planEndDt, false) == false) {
		alert('설계 종료 일자가 부정확합니다.');
		this.$("#planEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(planBeginDt, planEndDt, false) == false) {
		alert('설계 기간이 부정확합니다.');
		this.$("#planEndDt").focus();
		return;
	}
	if (this.isValidDate(cnstrctBeginDt, false) == false) {
		alert('시공 시작 일자가 부정확합니다.');
		this.$("#cnstrctBeginDt").focus();
		return;
	}
	if (this.isValidDate(cnstrctEndDt, false) == false) {
		alert('시공 종료 일자가 부정확합니다.');
		this.$("#cnstrctEndDt").focus();
		return;
	}
	if (this.isValidDateFromTo(cnstrctBeginDt, cnstrctEndDt, false) == false) {
		alert('시공 기간이 부정확합니다.');
		this.$("#cnstrctEndDt").focus();
		return;
	}
	if (this.isValidFirstDate(planEndDt, cnstrctBeginDt, false) == false) {
		alert('시공 시작 일자가 설계 종료 일자보다 큽니다.');
		this.$("#cnstrctBeginDt").focus();
		return;
	}
	if (this.isValidFirstDate(cnstrctBeginDt, bldDt, false) == false) {
		alert('시공 종료 일자가 준공 일자보다 큽니다.');
		this.$("#cnstrctBeginDt").focus();
		return;
	}
	if (this.isValidFirstDate(bldDt, flawEndDt, false) == false) {
		alert('준공 일자가 하자 만료 일자보다 큽니다.');
		this.$("#cnstrctBeginDt").focus();
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = fcltsMngNo;
		this.doAction('/fclty/gamInsertArchFcltySpecMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/fclty/gamUpdateArchFcltySpecMng.do', inputVO, function(module, result) {
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
GamArchFcltySpecMngModule.prototype.deleteData = function() {

	var gisAssetsPrtAtCode = this.$('#gisAssetsPrtAtCode').val();
	var gisAssetsCd = this.$('#gisAssetsCd').val();
	var gisAssetsSubCd = this.$('#gisAssetsSubCd').val();
	var gisPrtFcltyCd = this.$('#gisPrtFcltyCd').val();
	var gisPrtFcltySeq = this.$('#gisPrtFcltySeq').val();
	var prtFcltySe = this.$('#prtFcltySe').val();
	var fcltsMngNo = this.$('#fcltsMngNo').val();
	if (gisAssetsPrtAtCode == "" || gisAssetsCd == "" || gisAssetsSubCd == "") {
		alert('GIS 자산 코드가 부정확합니다.');
		return;
	}
	if (gisPrtFcltyCd == "") {
		alert('GIS 항만 시설 코드가 부정확합니다.');
		this.$("#gisPrtFcltyCd").focus();
		return;
	}
	if (gisPrtFcltySeq == "") {
		alert('GIS 항만 시설 순번이 부정확합니다.');
		this.$("#prtFcltySe").focus();
		return;
	}
	if (prtFcltySe == "") {
		alert('항만 시설 분류가 부정확합니다.');
		this.$("#prtFcltySe").focus();
		return;
	}
	if (fcltsMngNo == "") {
		alert('시설물 관리 번호가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?\r\n(첨부 파일도 모두 삭제됩니다)")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteArchFcltySpecMng.do', deleteVO, function(module, result) {
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
 * @FUNCTION NAME : saveMap
 * @DESCRIPTION   : MAP SAVE
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.saveMap = function() {

	this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
	var rows = this.$("#mainGrid").selectedRows();
	if (rows.length != 1) {
		alert('맵지정이 실패했습니다!\r\n(시설을 하나만 선택 해 주시기 바랍니다.)');
		return;
	}
	if (this._params != null && this._params.action != null && this._params.action == "setFeature") {
		this.setFeatureCode('gisArchFclty', rows[0], this._params.feature);
		alert('맵지정이 완료되었습니다.');
	}

};

<%
/**
 * @FUNCTION NAME : addFileData
 * @DESCRIPTION   : FILE 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.addFileData = function() {

	this.$('#atchFileFcltsMngNo').val(this.$('#fcltsMngNo').val());
	this.$('#atchFileSeq').val("001");
	this.$('#atchFileSe').val("P");
	this.$('#atchFileSj').val("");
	this.$('#atchFileNmLogic').val("");
	this.$('#atchFileNmPhysicl').val("");
	this.$('#atchFileWritngDt').val("");
	this.enableFileDetailInputItem();
	this.$('#atchFileSe').focus();
	this.getNewAtchFileSeq();

};

<%
/**
 * @FUNCTION NAME : saveFileData
 * @DESCRIPTION   : FILE 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.saveFileData = function() {

	var inputVO = this.makeFormArgs("#fileForm");
	var atchFileFcltsMngNo = this.$('#atchFileFcltsMngNo').val();
	var atchFileSeq = this.$('#atchFileSeq').val();
	var atchFileSe = this.$('#atchFileSe').val();
	var atchFileNmLogic = this.$('#atchFileNmLogic').val();
	var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
	if (atchFileFcltsMngNo == "") {
		alert('시설물 관리 번호가 부정확합니다.');
		return;
	}
	if (atchFileSeq > "999" || atchFileSeq <= "000") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (atchFileSe != "D" && atchFileSe != "P" && atchFileSe != "Z") {
		alert('구분이 부정확합니다.');
		this.$("#atchFileSe").focus();
		return;
	}
	if (atchFileNmLogic == "") {
		alert('논리 파일명이 부정확합니다.');
		return;
	}
	if (atchFileNmPhysicl == "") {
		alert('물리 파일명이 부정확합니다.');
		return;
	}
	if (this._filemode == "insert") {
		this._fileKeyValue = atchFileFcltsMngNo;
		this._fileSeq = atchFileSeq;
		this.doAction('/fclty/gamInsertArchFcltySpecMngAtchFile.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshFileData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/fclty/gamUpdateArchFcltySpecMngAtchFile.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshFileData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteFileData
 * @DESCRIPTION   : FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.deleteFileData = function() {

	var atchFileFcltsMngNo = this.$('#atchFileFcltsMngNo').val();
	var atchFileSeq = this.$('#atchFileSeq').val();
	if (atchFileFcltsMngNo == "") {
		alert('시설물 관리 번호가 부정확합니다.');
		return;
	}
	if (atchFileSeq == "") {
		alert('순번이 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#fileForm");
		this.doAction('/fclty/gamDeleteArchFcltySpecMngAtchFile.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._filemode = 'query';
				module._fileKeyValue = '';
				module._fileSeq = '';
				module.loadFileDetail('listTab');
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : saveUploadFileData
 * @DESCRIPTION   : UPLOAD FILE 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.saveUploadFileData = function(argAtchFileFcltsMngNo, argAtchFileSeq, argAtchFileSj, argAtchFileNmLogic, argAtchFileNmPhysicl) {

	var inputVO = [];
	var atchFileSe = "D";
	var atchFileSeNm = "문서";
	if (argAtchFileNmPhysicl != null || argAtchFileNmPhysicl != "") {
		var ext = argAtchFileNmPhysicl.substring(argAtchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
		if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
			atchFileSe = "P";
			atchFileSeNm = "사진";
		}
	}
	var atchFileSeqString = "000" + argAtchFileSeq;
	var atchFileSeq = atchFileSeqString.substring(atchFileSeqString.length - 3);
	inputVO={
			'atchFileFcltsMngNo':argAtchFileFcltsMngNo,
			'atchFileSeq':atchFileSeq,
			'atchFileSe':atchFileSe,
			'atchFileSeNm':atchFileSeNm,
			'atchFileSj':argAtchFileSj,
			'atchFileNmLogic':argAtchFileNmLogic,
			'atchFileNmPhysicl':argAtchFileNmPhysicl,
			'atchFileWritngDt':"",
			'regUsr':"",
			'registDt':"",
			'updUsr':"",
			'updtDt':""
	};
	this.doAction('/fclty/gamUploadArchFcltySpecMngAtchFile.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$("#fileGrid").flexAddRow({atchFileFcltsMngNo:argAtchFileFcltsMngNo, atchFileSeq:atchFileSeq, atchFileSe:atchFileSe, atchFileSeNm:atchFileSeNm,  atchFileSj: argAtchFileSj, atchFileNmLogic: argAtchFileNmLogic, atchFileNmPhysicl: argAtchFileNmPhysicl, atchFileWritngDt: ""});
		} else {
			alert(result.resultMsg);
		}
	});

};

<%
/**
 * @FUNCTION NAME : uploadFile
 * @DESCRIPTION   : FILE UPLOAD
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.uploadFile = function() {

	var atchFileFcltsMngNo = this.$('#fcltsMngNo').val();
	var rows = this.$("#fileGrid").flexGetData();
	var gridSeq = 0;
	if (rows.length > 0) {
		gridSeq = Number(rows[rows.length - 1].atchFileSeq);
	}
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			gridSeq += 1;
			module.saveUploadFileData(atchFileFcltsMngNo, gridSeq, this.logicalFileNm, this.logicalFileNm, this.physcalFileNm);
		});
		//module._filemode = 'query';
		//module._fileKeyValue = '';
		//module._fileSeq = '';
		//module.loadFileDetail('listTab');
		//module.makeFormValues('#fileForm', {});
		//module.makeDivValues('#fileForm', {});
		//module.$('#fileGrid').flexEmptyData();
		//module.$('#previewImage').attr('src', '');
		//module.$('#atchFileFcltsMngNo').val(atchFileFcltsMngNo);
		//var detailOpt = module.getFormValues('#fileForm');
		//module.$('#fileGrid').flexOptions({params:detailOpt}).flexReload();
		//module.$('#fileGrid').dgrid.sortRows(0);
	}, "첨부파일 업로드");

};

<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.downloadFile = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length > 0) {
		var row = selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
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
GamArchFcltySpecMngModule.prototype.downloadExcel = function(buttonId) {

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
			this.$('#mainGrid').flexExcelDown('/fclty/gamExcelDownloadArchFcltySpecMng.do');
			break;
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.enableListButtonItem = function() {

	if (this._mainmode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
		this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
			this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnShowMap').enable();
			this.$('#btnShowMap').removeClass('ui-state-disabled');
			this.$('#btnEditMap').enable();
			this.$('#btnEditMap').removeClass('ui-state-disabled');
			this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
			this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
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
GamArchFcltySpecMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#gisPrtFcltyCd').enable();
		this.$('#prtFcltyNm').enable();
		this.$('#prtFcltyLoc').enable();
		this.$('#prtFcltyStndrd').enable();
		this.$('#prtFcltyUnit').enable();
		this.$('#prtFcltyAr').enable();
		this.$('#prtPrtFcltyCnt').enable();
		this.$('#prtFcltyInstlDt').enable();
		this.$('#prtFcltyChangeDt').enable();
		this.$('#prtFcltyExprDt').enable();
		this.$('#prtPrtFcltyMnger').enable();
		this.$('#prtFcltyMngEntrpsCd').enable();
		this.$('#fcltsMngGroupNo').enable();
		this.$('#archFcltsClCd').enable();
		this.$('#bldDt').enable();
		this.$('#strctFmt').enable();
		this.$('#flawEndDt').enable();
		this.$('#baseFmt').enable();
		this.$('#ar').enable();
		this.$('#archAr').enable();
		this.$('#plotAr').enable();
		this.$('#mainUsagePrpos').enable();
		this.$('#prkAr').enable();
		this.$('#prkCnt').enable();
		this.$('#osdPrkAr').enable();
		this.$('#osdPrkCnt').enable();
		this.$('#isdPrkAr').enable();
		this.$('#isdPrkCnt').enable();
		this.$('#exhaustDuctEnnc').enable();
		this.$('#vntltnArcndtMthd').enable();
		this.$('#liftOperMthd').enable();
		this.$('#liftCntPsngr').enable();
		this.$('#liftCntEmgcy').enable();
		this.$('#liftCntCargo').enable();
		this.$('#elctyLeadInCapa').enable();
		this.$('#sbtLoc').enable();
		this.$('#spictankFmt').enable();
		this.$('#swgClupfcltyLoc').enable();
		this.$('#wrtTankLoc').enable();
		this.$('#oilSavefcltyLoc').enable();
		this.$('#htngEnnc').enable();
		this.$('#clngSrc').enable();
		this.$('#clngEnnc').enable();
		this.$('#htngSrc').enable();
		this.$('#fcg').enable();
		this.$('#itr').enable();
		this.$('#ceil').enable();
		this.$('#roof').enable();
		this.$('#roofWtprf').enable();
		this.$('#planCnstNm').enable();
		this.$('#planExcCmpny').enable();
		this.$('#planBeginDt').enable();
		this.$('#planEndDt').enable();
		this.$('#cnstrctCnstNm').enable();
		this.$('#cnstrctExcCmpny').enable();
		this.$('#cnstrctBeginDt').enable();
		this.$('#cnstrctEndDt').enable();
		this.$('#loc').enable();
		this.$('#rm').enable();
		this.$('#popupSpecGisAssetsCd').enable();
		this.$('#popupSpecGisAssetsCd').removeClass('ui-state-disabled');
		this.$('#popupSpecPrtFcltyMngEntrpsCd').enable();
		this.$('#popupSpecPrtFcltyMngEntrpsCd').removeClass('ui-state-disabled');
		this.$('#popupSpecFcltsMngGroupNo').enable();
		this.$('#popupSpecFcltsMngGroupNo').removeClass('ui-state-disabled');
		this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecSave').enable();
		this.$('#btnSpecSave').removeClass('ui-state-disabled');
		this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#gisPrtFcltyCd').disable();
			this.$('#prtFcltyNm').enable();
			this.$('#prtFcltyLoc').enable();
			this.$('#prtFcltyStndrd').enable();
			this.$('#prtFcltyUnit').enable();
			this.$('#prtFcltyAr').enable();
			this.$('#prtPrtFcltyCnt').enable();
			this.$('#prtFcltyInstlDt').enable();
			this.$('#prtFcltyChangeDt').enable();
			this.$('#prtFcltyExprDt').enable();
			this.$('#prtPrtFcltyMnger').enable();
			this.$('#prtFcltyMngEntrpsCd').enable();
			this.$('#fcltsMngGroupNo').enable();
			this.$('#archFcltsClCd').enable();
			this.$('#bldDt').enable();
			this.$('#strctFmt').enable();
			this.$('#flawEndDt').enable();
			this.$('#baseFmt').enable();
			this.$('#ar').enable();
			this.$('#archAr').enable();
			this.$('#plotAr').enable();
			this.$('#mainUsagePrpos').enable();
			this.$('#prkAr').enable();
			this.$('#prkCnt').enable();
			this.$('#osdPrkAr').enable();
			this.$('#osdPrkCnt').enable();
			this.$('#isdPrkAr').enable();
			this.$('#isdPrkCnt').enable();
			this.$('#exhaustDuctEnnc').enable();
			this.$('#vntltnArcndtMthd').enable();
			this.$('#liftOperMthd').enable();
			this.$('#liftCntPsngr').enable();
			this.$('#liftCntEmgcy').enable();
			this.$('#liftCntCargo').enable();
			this.$('#elctyLeadInCapa').enable();
			this.$('#sbtLoc').enable();
			this.$('#spictankFmt').enable();
			this.$('#swgClupfcltyLoc').enable();
			this.$('#wrtTankLoc').enable();
			this.$('#oilSavefcltyLoc').enable();
			this.$('#htngEnnc').enable();
			this.$('#clngSrc').enable();
			this.$('#clngEnnc').enable();
			this.$('#htngSrc').enable();
			this.$('#fcg').enable();
			this.$('#itr').enable();
			this.$('#ceil').enable();
			this.$('#roof').enable();
			this.$('#roofWtprf').enable();
			this.$('#planCnstNm').enable();
			this.$('#planExcCmpny').enable();
			this.$('#planBeginDt').enable();
			this.$('#planEndDt').enable();
			this.$('#cnstrctCnstNm').enable();
			this.$('#cnstrctExcCmpny').enable();
			this.$('#cnstrctBeginDt').enable();
			this.$('#cnstrctEndDt').enable();
			this.$('#loc').enable();
			this.$('#rm').enable();
			this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecPrtFcltyMngEntrpsCd').enable();
			this.$('#popupSpecPrtFcltyMngEntrpsCd').removeClass('ui-state-disabled');
			this.$('#popupSpecFcltsMngGroupNo').enable();
			this.$('#popupSpecFcltsMngGroupNo').removeClass('ui-state-disabled');
			this.$('#btnSpecInsert').enable();
			this.$('#btnSpecInsert').removeClass('ui-state-disabled');
			this.$('#btnSpecSave').enable();
			this.$('#btnSpecSave').removeClass('ui-state-disabled');
			this.$('#btnSpecRemove').enable();
			this.$('#btnSpecRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#gisPrtFcltyCd').disable();
			this.$('#prtFcltyNm').disable();
			this.$('#prtFcltyLoc').disable();
			this.$('#prtFcltyStndrd').disable();
			this.$('#prtFcltyUnit').disable();
			this.$('#prtFcltyAr').disable();
			this.$('#prtPrtFcltyCnt').disable();
			this.$('#prtFcltyInstlDt').disable();
			this.$('#prtFcltyChangeDt').disable();
			this.$('#prtFcltyExprDt').disable();
			this.$('#prtPrtFcltyMnger').disable();
			this.$('#prtFcltyMngEntrpsCd').disable();
			this.$('#fcltsMngGroupNo').disable();
			this.$('#archFcltsClCd').disable();
			this.$('#bldDt').disable();
			this.$('#strctFmt').disable();
			this.$('#flawEndDt').disable();
			this.$('#baseFmt').disable();
			this.$('#ar').disable();
			this.$('#archAr').disable();
			this.$('#plotAr').disable();
			this.$('#mainUsagePrpos').disable();
			this.$('#prkAr').disable();
			this.$('#prkCnt').disable();
			this.$('#osdPrkAr').disable();
			this.$('#osdPrkCnt').disable();
			this.$('#isdPrkAr').disable();
			this.$('#isdPrkCnt').disable();
			this.$('#exhaustDuctEnnc').disable();
			this.$('#vntltnArcndtMthd').disable();
			this.$('#liftOperMthd').disable();
			this.$('#liftCntPsngr').disable();
			this.$('#liftCntEmgcy').disable();
			this.$('#liftCntCargo').disable();
			this.$('#elctyLeadInCapa').disable();
			this.$('#sbtLoc').disable();
			this.$('#spictankFmt').disable();
			this.$('#swgClupfcltyLoc').disable();
			this.$('#wrtTankLoc').disable();
			this.$('#oilSavefcltyLoc').disable();
			this.$('#htngEnnc').disable();
			this.$('#clngSrc').disable();
			this.$('#clngEnnc').disable();
			this.$('#htngSrc').disable();
			this.$('#fcg').disable();
			this.$('#itr').disable();
			this.$('#ceil').disable();
			this.$('#roof').disable();
			this.$('#roofWtprf').disable();
			this.$('#planCnstNm').disable();
			this.$('#planExcCmpny').disable();
			this.$('#planBeginDt').disable();
			this.$('#planEndDt').disable();
			this.$('#cnstrctCnstNm').disable();
			this.$('#cnstrctExcCmpny').disable();
			this.$('#cnstrctBeginDt').disable();
			this.$('#cnstrctEndDt').disable();
			this.$('#loc').disable();
			this.$('#rm').disable();
			this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecPrtFcltyMngEntrpsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});
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
GamArchFcltySpecMngModule.prototype.disableDetailInputItem = function() {

	this.$('#gisPrtFcltyCd').disable();
	this.$('#prtFcltyNm').disable();
	this.$('#prtFcltyLoc').disable();
	this.$('#prtFcltyStndrd').disable();
	this.$('#prtFcltyUnit').disable();
	this.$('#prtFcltyAr').disable();
	this.$('#prtPrtFcltyCnt').disable();
	this.$('#prtFcltyInstlDt').disable();
	this.$('#prtFcltyChangeDt').disable();
	this.$('#prtFcltyExprDt').disable();
	this.$('#prtPrtFcltyMnger').disable();
	this.$('#prtFcltyMngEntrpsCd').disable();
	this.$('#fcltsMngGroupNo').disable();
	this.$('#archFcltsClCd').disable();
	this.$('#bldDt').disable();
	this.$('#strctFmt').disable();
	this.$('#flawEndDt').disable();
	this.$('#baseFmt').disable();
	this.$('#ar').disable();
	this.$('#archAr').disable();
	this.$('#plotAr').disable();
	this.$('#mainUsagePrpos').disable();
	this.$('#prkAr').disable();
	this.$('#prkCnt').disable();
	this.$('#osdPrkAr').disable();
	this.$('#osdPrkCnt').disable();
	this.$('#isdPrkAr').disable();
	this.$('#isdPrkCnt').disable();
	this.$('#exhaustDuctEnnc').disable();
	this.$('#vntltnArcndtMthd').disable();
	this.$('#liftOperMthd').disable();
	this.$('#liftCntPsngr').disable();
	this.$('#liftCntEmgcy').disable();
	this.$('#liftCntCargo').disable();
	this.$('#elctyLeadInCapa').disable();
	this.$('#sbtLoc').disable();
	this.$('#spictankFmt').disable();
	this.$('#swgClupfcltyLoc').disable();
	this.$('#wrtTankLoc').disable();
	this.$('#oilSavefcltyLoc').disable();
	this.$('#htngEnnc').disable();
	this.$('#clngSrc').disable();
	this.$('#clngEnnc').disable();
	this.$('#htngSrc').disable();
	this.$('#fcg').disable();
	this.$('#itr').disable();
	this.$('#ceil').disable();
	this.$('#roof').disable();
	this.$('#roofWtprf').disable();
	this.$('#planCnstNm').disable();
	this.$('#planExcCmpny').disable();
	this.$('#planBeginDt').disable();
	this.$('#planEndDt').disable();
	this.$('#cnstrctCnstNm').disable();
	this.$('#cnstrctExcCmpny').disable();
	this.$('#cnstrctBeginDt').disable();
	this.$('#cnstrctEndDt').disable();
	this.$('#loc').disable();
	this.$('#rm').disable();
	this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecPrtFcltyMngEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableFileDetailInputItem
 * @DESCRIPTION   : FILE DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.enableFileDetailInputItem = function() {

	if (this._filemode == "insert") {
		this.$('#atchFileSe').enable();
		this.$('#atchFileSj').enable();
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFileSave').enable();
			this.$('#btnFileSave').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._fileKeyValue != "") {
			this.$('#atchFileSe').enable();
			this.$('#atchFileSj').enable();
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileSave').enable();
			this.$('#btnFileSave').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#atchFileSe').disable();
			this.$('#atchFileSj').disable();
			if (this.$('#fileFcltsMngNo').val() != "") {
				this.$('#btnFileInsert').enable();
				this.$('#btnFileInsert').removeClass('ui-state-disabled');
				this.$('#btnFileUpload').enable();
				this.$('#btnFileUpload').removeClass('ui-state-disabled');
			} else {
				this.$('#btnFileInsert').disable({disableClass:"ui-state-disabled"});
				this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
			}
			this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFileSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableFileDetailInputItem
 * @DESCRIPTION   : FILE DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamArchFcltySpecMngModule.prototype.disableFileDetailInputItem = function() {

	this.$('#atchFileSe').disable();
	this.$('#atchFileSj').disable();
	this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});

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
GamArchFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode == "modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
			} else if (this._mainmode == "insert") {
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
		case 'fileTab':
			if (this._mainKeyValue != "") {
				var atchFcltsMngNo = this.$('#atchFcltsMngNo').val();
				if (atchFcltsMngNo == "" || atchFcltsMngNo != this._fileKeyValue || this._mainKeyValue != this._fileKeyValue) {
					this.loadFileDetail('listTab');
					this.enableFileDetailInputItem();
				}
			} else {
				this.makeFormValues('#fileForm', {});
				this.makeDivValues('#fileForm', {});
				this.$('#fileGrid').flexEmptyData();
				this.$('#previewImage').attr('src', '');
				this.disableFileDetailInputItem();
			}
			break;
	}

};

var module_instance = new GamArchFcltySpecMngModule();

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
								<select id="sFcltsClCd">
									<option value="" selected="selected">선택</option>
									<c:forEach  items="${fcltsClCdList}" var="fcltsClCdItem">
										<option value="${fcltsClCdItem.fcltsClCd}">${fcltsClCdItem.fcltsClCdNm}</option>
									</c:forEach>
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
				<li><a href="#listTab" class="emdTab">건축시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">건축시설 제원</a></li>
				<li><a href="#fileTab" class="emdTab">건축시설 첨부파일</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:8%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">연면적</th>
								<td><input type="text" size="18" id="sumAr" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:8%; height:20; text-align:center;">건물 면적</th>
								<td><input type="text" size="18" id="sumArchAr" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">추가</button>
									<button id="btnDelete" class="buttonDelete">삭제</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
									<button id="btnShowMap" data-role="showMap" data-gis-layer="gisArchFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
									<button id="btnEditMap" data-role="editMap" data-gis-layer="gisArchFclty">맵편집</button>
									<button id="btnSaveMap">맵지정</button>
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
								<th style="font-weight:bold; height:20px;">건축시설 일반</th>
								<td style="text-align:right;">
									<input type="hidden" id="fcltySpecExistYn"/>
									&nbsp;시설물관리번호 : &nbsp;
									<input type="text" size="20" id="fcltsMngNo" disabled/>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th width="10%" height="18">GIS 　자산코드</th>
								<td>
									<input type="hidden" id="gisAssetsLocCd"/>
									<input type="hidden" id="gisAssetsLocNm"/>
									<input type="text" size="22" id="gisAssetsNm" disabled/>
									<button id="popupSpecGisAssetsCd" class="popupButton">선택</button>
								</td>
								<th width="10%" height="18">항구분／분　류</th>
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
								<th width="10%" height="18">시　설　코　드</th>
								<td>
									<input type="hidden" id="prtFcltyGisCd"/>
									<input type="hidden" id="gisPrtFcltyCdSub"/>
									<input type="hidden" id="gisPrtFcltyCdNm"/>
									<input type="text" size="3" id="gisPrtFcltyCdDisplay" disabled/>-
									<input type="text" size="6" id="gisPrtFcltySeq" disabled/>
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM005"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">시　　설　　명</th>
								<td>
									<input type="text" size="33" id="prtFcltyNm" maxlength="80"/>
								</td>
								<th width="10%" height="18">소　　재　　지</th>
								<td>
									<input type="hidden" id="laCrdnt"/>
									<input type="hidden" id="loCrdnt"/>
									<input type="hidden" id="lat"/>
									<input type="hidden" id="lng"/>
									<input type="hidden" id="gisAssetsLocplcLnm"/>
									<input type="hidden" id="loc"/>
									<input type="text" size="33" id="prtFcltyLoc"/>
								</td>
								<th width="10%" height="18">규　격／단　위</th>
								<td>
									<input type="text" size="20" id="prtFcltyStndrd" maxlength="80"/>／
									<input type="text" size="10" id="prtFcltyUnit" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">면　적／수　량</th>
								<td>
									<input type="text" size="11" id="prtFcltyAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>／
									<input type="text" size="11" id="prtPrtFcltyCnt" class="ygpaNumber" maxlength="10"/> 개
								</td>
								<th width="10%" height="18">설치일／변경일</th>
								<td>
									<input type="text" size="11" id="prtFcltyInstlDt" class="emdcal"/>／
									<input type="text" size="11" id="prtFcltyChangeDt" class="emdcal"/>
								</td>
								<th width="10%" height="18">만료일／담당자</th>
								<td>
									<input type="text" size="11" id="prtFcltyExprDt" class="emdcal"/>／
									<input type="text" size="16" id="prtPrtFcltyMnger" maxlength="80"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">관　리　업　체</th>
								<td>
									<input type="text" size="2" id="prtFcltyMngEntrpsCd" maxlength="8"/>
									<input type="text" size="17" id="prtFcltyMngEntrpsNm" disabled/>
									<button id="popupSpecPrtFcltyMngEntrpsCd" class="popupButton">선택</button>
								</td>
								<th width="10%" height="18">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8"/>
									<input type="text" size="61" id="fcltsMngGroupNm" disabled/>
									<button id="popupSpecFcltsMngGroupNo" class="popupButton">선택</button>
								</td>
							</tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">건축시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecInsert" class="buttonAdd">　추　가　</button>
									<button id="btnSpecSave" class="buttonSave">　저　장　</button>
									<button id="btnSpecRemove" class="buttonDelete">　삭　제　</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th width="10%" height="18">시설물　　분류</th>
								<td>
									<input type="hidden" id="archFcltsClCdNm"/>
									<select id="archFcltsClCd">
										<option value="" selected="selected">선택</option>
										<c:forEach  items="${fcltsClCdList}" var="fcltsClCdItem">
											<option value="${fcltsClCdItem.fcltsClCd}">${fcltsClCdItem.fcltsClCdNm}</option>
										</c:forEach>
									</select>
								</td>
								<th width="10%" height="18">주　사용　용도</th>
								<td colspan="3">
									<input type="text" size="93" id="mainUsagePrpos" maxlength="200"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">준　공　일　자</th>
								<td>
									<input type="text" size="30" id="bldDt" class="emdcal"/>
								</td>
								<th width="10%" height="18">구　조　형　식</th>
								<td>
									<input type="text" size="33" id="strctFmt" maxlength="100"/>
								</td>
								<th width="10%" height="18">기　초　형　식</th>
								<td>
									<input type="text" size="33" id="baseFmt" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">연　　면　　적</th>
								<td>
									<input type="text" size="30" id="ar" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>
								</td>
								<th width="10%" height="18">대　지　면　적</th>
								<td>
									<input type="text" size="30" id="plotAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>
								</td>
								<th width="10%" height="18">건　축　면　적</th>
								<td>
									<input type="text" size="30" id="archAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">주차면적／대수</th>
								<td>
									<input type="text" size="11" id="prkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>／
									<input type="text" size="11" id="prkCnt" class="ygpaNumber" maxlength="6"/> 대
								</td>
								<th width="10%" height="18">옥외면적／대수</th>
								<td>
									<input type="text" size="11" id="osdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>／
									<input type="text" size="11" id="osdPrkCnt" class="ygpaNumber" maxlength="6"/> 대
								</td>
								<th width="10%" height="18">옥내면적／대수</th>
								<td>
									<input type="text" size="11" id="isdPrkAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>／
									<input type="text" size="11" id="isdPrkCnt" class="ygpaNumber" maxlength="6"/> 대
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">승강기운영방식</th>
								<td>
									<input type="text" size="33" id="liftOperMthd" maxlength="100"/>
								</td>
								<th width="10%" height="18">승객/비상/화물</th>
								<td>
									<input type="text" size="5" id="liftCntPsngr" class="ygpaNumber" maxlength="6"/> 대／
									<input type="text" size="5" id="liftCntEmgcy" class="ygpaNumber" maxlength="6"/> 대／
									<input type="text" size="5" id="liftCntCargo" class="ygpaNumber" maxlength="6"/> 대
								</td>
								<th width="10%" height="18">환기공조　방식</th>
								<td>
									<input type="text" size="33" id="vntltnArcndtMthd" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">전기　인입용량</th>
								<td>
									<input type="text" size="29" id="elctyLeadInCapa" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> kW
								</td>
								<th width="10%" height="18">변전실　　위치</th>
								<td>
									<input type="text" size="33" id="sbtLoc" maxlength="100"/>
								</td>
								<th width="10%" height="18">정화조　　형식</th>
								<td>
									<input type="text" size="33" id="spictankFmt" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">오수정화　위치</th>
								<td>
									<input type="text" size="33" id="swgClupfcltyLoc" maxlength="100"/>
								</td>
								<th width="10%" height="18">물탱크　　위치</th>
								<td>
									<input type="text" size="33" id="wrtTankLoc" maxlength="100"/>
								</td>
								<th width="10%" height="18">유류저장　위치</th>
								<td>
									<input type="text" size="33" id="oilSavefcltyLoc" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">배기닥트　유무</th>
								<td>
									<select id="exhaustDuctEnnc">
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
								</td>
								<th width="10%" height="18">냉방유무／열원</th>
								<td>
									<select id="clngEnnc">
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
									/
									<input type="text" size="25" id="clngSrc" maxlength="100"/>
								</td>
								<th width="10%" height="18">난방유무／열원</th>
								<td>
									<select id="htngEnnc">
										<option value="Y">Y</option>
										<option value="N">N</option>
									</select>
									/
									<input type="text" size="25" id="htngSrc" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">외　　　　　장</th>
								<td>
									<input type="text" size="33" id="fcg" maxlength="200"/>
								</td>
								<th width="10%" height="18">내　　　　　장</th>
								<td>
									<input type="text" size="33" id="itr" maxlength="200"/>
								</td>
								<th width="10%" height="18">천　　　　　장</th>
								<td>
									<input type="text" size="33" id="ceil" maxlength="200"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">지　　　　　붕</th>
								<td>
									<input type="text" size="33" id="roof" maxlength="200"/>
								</td>
								<th width="10%" height="18">지　붕　방　수</th>
								<td>
									<input type="text" size="33" id="roofWtprf" maxlength="200"/>
								</td>
								<th width="10%" height="18">하자　만료일자</th>
								<td>
									<input type="text" size="30" id="flawEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">설계　공사　명</th>
								<td>
									<input type="text" size="33" id="planCnstNm" maxlength="200"/>
								</td>
								<th width="10%" height="18">설계　수행회사</th>
								<td>
									<input type="text" size="33" id="planExcCmpny" maxlength="200"/>
								</td>
								<th width="10%" height="18">설　계　기　간</th>
								<td>
									<input type="text" size="11" id="planBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="planEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">시공　공사　명</th>
								<td>
									<input type="text" size="33" id="cnstrctCnstNm" maxlength="200"/>
								</td>
								<th width="10%" height="18">시공　수행회사</th>
								<td>
									<input type="text" size="33" id="cnstrctExcCmpny" maxlength="200"/>
								</td>
								<th width="10%" height="18">시　공　기　간</th>
								<td>
									<input type="text" size="11" id="cnstrctBeginDt" class="emdcal"/>∼
									<input type="text" size="11" id="cnstrctEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th width="10%" height="18">비　　　　　고</th>
								<td colSpan="5">
									<input type="text" size="149" id="rm" maxlength="1000"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 214. TAB 3 AREA (FILE) -->
			<div id="fileTab" class="emdTabPage" style="overflow:scroll;">
				<table>
					<tr>
						<td width="50%">
							<form id="fileForm">
								<table class="detailPanel">
									<tr>
										<th width="15%" height="20px">시설물관리번호</th>
										<td>
											<input id="atchFileFcltsMngNo" type="text" size="65" disabled/>
										</td>
									</tr>
									<tr>
										<th width="15%" height="20px">구　　　　분</th>
										<td>
											<input id="atchFileSeq" type="text" size="5" disabled/>
											<select id="atchFileSe">
												<option value="D">문서</option>
												<option value="P">사진</option>
												<option value="Z">기타</option>
											</select>
										</td>
									</tr>
									<tr>
										<th width="15%" height="20px">설　　　　명</th>
										<td>
											<input id="atchFileSj" type="text" size="65" maxlength="80"/>
											<input type="hidden" id="atchFileNmLogic"/>
											<input type="hidden" id="atchFileNmPhysicl"/>
											<input type="hidden" id="atchFileWritngDt"/>
										</td>
									</tr>
								</table>
							</form>
							<div class="emdControlPanel">
								<button id="btnFileUpload" class="buttonAdd">추가　(업로드)</button>
								<button id="btnFileDownload">다　운　로　드</button>
								<button id="btnFileRemove" class="buttonDelete">　삭　　　제　</button>
								<button id="btnFileSave" class="buttonSave">　저　　　장　</button>
								&nbsp;  &nbsp;
							</div>
							<table id="fileGrid" style="display:none" class="fillHeight"></table>
						</td>
						<td style="text-align:center; vertical-align:middle; border:black 3px double;">
							<img id="previewImage" style="border: 1px solid #000; max-width:300px; max-height: 300px" src="">
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
