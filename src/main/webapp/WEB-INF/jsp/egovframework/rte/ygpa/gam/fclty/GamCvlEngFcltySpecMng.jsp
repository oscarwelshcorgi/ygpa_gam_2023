<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCvlEngFcltySpecMng.jsp
 * @Description : 토목시설 제원 관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2015.02.17  ACEWOLF          최초 생성
 *
 * author ACEWOLF
 * since 2015.02.17
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
 * @FUNCTION NAME : GamCvlEngFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCvlEngFcltySpecMngModule() {}

GamCvlEngFcltySpecMngModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectCvlEngFcltySpecMngList.do',
		dataType : "json",
		colModel : [
					{display:"항구분",				name:"gisAssetsPrtAtCodeNm",	width:60,		sortable:false,		align:"center"},
					{display:"항만시설 명",			name:"prtFcltyNm",				width:150,		sortable:false,		align:"left"},
					{display:"시설물 관리 그룹",	name:"fcltsMngGroupNm",			width:120,		sortable:false,		align:"left"},
					{display:"시설물 분류",			name:"cvlEngFcltsClCdNm",		width:100,		sortable:false,		align:"left"},
					{display:"소재지",	 			name:"loc",						width:150,		sortable:false,		align:"left"},
					{display:"구조 형식",	 		name:"strctFmt",				width:150,		sortable:false,		align:"left"},
					{display:"선석",				name:"berth",					width:80,		sortable:false,		align:"right"},
					{display:"폭",				 	name:"wd",						width:80,		sortable:false,		align:"right"},
					{display:"길이",	 			name:"lt",						width:80,		sortable:false,		align:"right"},
					{display:"주요 취급 화물", 		name:"stplHndlFrght",			width:150,		sortable:false,		align:"left"},
					{display:"주요 계류 선박", 		name:"stplMoorShip",			width:150,		sortable:false,		align:"left"},
					{display:"포장 종류",			name:"packKnd",					width:100,		sortable:false,		align:"left"},
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
					{display:"접안 선박 규모",	 	name:"csdhpShipScl",			width:100,		sortable:false,		align:"right"},
					{display:"시설물 연장",	 		name:"fcltsExt",				width:100,		sortable:false,		align:"right"},
					{display:"방충재 종류",		 	name:"fenderKndCd",				width:100,		sortable:false,		align:"left"},
					{display:"방충재 배치 간격",	name:"fenderPmntItv",			width:120,		sortable:false,		align:"left"},
					{display:"방충재 형식",		 	name:"fenderFmt",				width:150,		sortable:false,		align:"left"},
					{display:"에이프런 폭",	 		name:"apronWd",					width:100,		sortable:false,		align:"right"},
					{display:"에이프런 포장 종류",	name:"apronPackKnd",			width:120,		sortable:false,		align:"left"},
					{display:"에이프런 포장 구배",	name:"apronPackGrdnt",			width:150,		sortable:false,		align:"left"},
					{display:"야적장 면적",	 		name:"yardAr",					width:100,		sortable:false,		align:"right"},
					{display:"야적장 포장 종류",	name:"yardPackKnd",				width:120,		sortable:false,		align:"left"},
					{display:"시설물 관리 번호",	name:"fcltsMngNo",				width:120,		sortable:false,		align:"left"},
					{display:"자산 명",	 			name:"gisAssetsNm",				width:200,		sortable:false,		align:"left"},
					{display:"자산 위치",	 		name:"gisAssetsLocNm",			width:200,		sortable:false,		align:"left"}
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

	this.$("#cvlEngFcltsClCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsClCdNm("#cvlEngFcltsClCd", "#cvlEngFcltsClCdNm");
	});

	this.$("#prtFcltyLoc").bind("change", {module: this}, function(event) {
		event.data.module.setLoc($(this).val());
	});

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectCvlEngFcltySpecMngFcltsAtchFileList.do',
		dataType : 'json',
		colModel : [
					{display:"번호",		name:"atchFileNo",			width:100,		sortable:false,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:60,		sortable:false,		align:"center"},
					{display:"파일명",		name:"atchFileNmLogic",		width:300,		sortable:false,		align:"left"}
					],
		height: "240"
	});

	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.enableFileButtonItem();
	});

	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this._atchFileDirLoad = false;
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
GamCvlEngFcltySpecMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

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
GamCvlEngFcltySpecMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

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
GamCvlEngFcltySpecMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

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
GamCvlEngFcltySpecMngModule.prototype.isValidAmount = function(amountValue) {

	if (amountValue > 9999999999999999 || amountValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidNumber10P2
 * @DESCRIPTION   : NUMBER에 대한 VALIDATION을 검사한다. (소수점이상 8자리, 소수점이하 2자리)
 * @PARAMETER     :
 *   1. numValue - NUMBER VALUE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.isValidNumber10P2 = function(numValue) {

	if (numValue > 99999999.99 || numValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidNumber8P2
 * @DESCRIPTION   : NUMBER에 대한 VALIDATION을 검사한다. (소수점이상 6자리, 소수점이하 2자리)
 * @PARAMETER     :
 *   1. numValue - NUMBER VALUE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.isValidNumber8P2 = function(numValue) {

	if (numValue > 999999.99 || numValue < 0) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidNumber6P2
 * @DESCRIPTION   : NUMBER에 대한 VALIDATION을 검사한다. (소수점이상 4자리, 소수점이하 2자리)
 * @PARAMETER     :
 *   1. numValue - NUMBER VALUE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.isValidNumber6P2 = function(numValue) {

	if (numValue > 9999.99 || numValue < 0) {
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
GamCvlEngFcltySpecMngModule.prototype.isValidAr = function(arValue) {

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
GamCvlEngFcltySpecMngModule.prototype.isValidCnt = function(cntValue) {

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
GamCvlEngFcltySpecMngModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngFcltsMngGroupNm.do', searchVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.getEntrpsNm = function(argEntrpsCdVariableName, argEntrpsNmVariableName) {

	var sEntrpsNm = "";
	if (argEntrpsCdVariableName == null || argEntrpsCdVariableName == "") {
		return sEntrpsNm;
	}
	var sEntrpscd = this.$(argEntrpsCdVariableName).val();
	if (sEntrpscd.length() == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngEntrpsNm.do', searchVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.getFcltsClCdNm = function(argFcltsClCdVariableName, argFcltsClCdNmVariableName) {

	var sFcltsClCdNm = "";
	if (argFcltsClCdVariableName == null || argFcltsClCdVariableName == "") {
		return sFcltsClCdNm;
	}
	var sFcltsClCd = this.$(argFcltsClCdVariableName).val();
	if (sFcltsClCd.length == 14) {
		var searchVO = { 'sFcltsClCd':sFcltsClCd };
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngFcltsClCdNm.do', searchVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.setLoc = function(argPrtFcltyLoc) {

	var loc = this.$('#loc').val();
	if (loc != argPrtFcltyLoc) {
		this.$('#loc').val(argPrtFcltyLoc);
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
GamCvlEngFcltySpecMngModule.prototype.onAtchFileDirTreeItemClick = function(itemId) {

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
GamCvlEngFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value) {

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
				this.$('#cvlEngFcltsClCd').focus();
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
GamCvlEngFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'btnDirRefresh':
			this.displayAtchFileDirectory("");
			this.displayAtchFileList("");
			break;
		case 'btnDirAdd':
			this.addAtchFileDirectory();
			break;
		case 'btnDirRename':
			this.renameAtchFileDirectory();
			break;
		case 'btnDirRemove':
			this.removeAtchFileDirectory();
			break;
		case 'btnFileRefresh':
			this.displayAtchFileList(this.$('#dirNo').val());
			break;
		case 'btnFileUpload':
			this.uploadFile();
			break;
		case 'btnFileDownload':
			this.downloadFile();
			break;
		case 'btnFileRemove':
			this.deleteFileData();
			break;
	    case 'btnFilePreview':
	    	this.previewFile(true);
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
GamCvlEngFcltySpecMngModule.prototype.onSubmit = function() {

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
GamCvlEngFcltySpecMngModule.prototype.loadData = function() {

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
GamCvlEngFcltySpecMngModule.prototype.refreshData = function() {

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
GamCvlEngFcltySpecMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngPk.do', searchVO, function(module, result){
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
GamCvlEngFcltySpecMngModule.prototype.selectData = function() {

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
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.getFcltsMngGroupNoNm = function() {

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
GamCvlEngFcltySpecMngModule.prototype.getNewGisPrtFcltySeq = function() {

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
	this.doAction('/fclty/gamSelectCvlEngFcltySpecMngMaxGisPrtFcltySeq.do', searchVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.setFcltsMngNo = function() {

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
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : DATA ADD (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.addData = function() {

	this.$('#fcltsMngNo').val("");
	this.$('#gisAssetsLocCd').val("");
	this.$('#gisAssetsLocNm').val("");
	this.$('#gisAssetsNm').val("");
	this.$('#prtFcltySeNm').val("토목시설");
	this.$('#gisAssetsPrtAtCode').val("");
	this.$('#gisAssetsCd').val("");
	this.$('#gisAssetsSubCd').val("");
	this.$('#gisAssetsPrtAtCodeNm').val("");
	this.$('#prtFcltySe').val("C");
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
	this.$('#cvlEngFcltsClCdNm').val("");
	this.$('#cvlEngFcltsClCd').val("");
	this.$('fcltsExt').val("0.00");
	this.$('strctFmt').val("");
	this.$('upsideAltud').val("0.00");
	this.$('berthDpwt').val("0.00");
	this.$('permWd').val("0.00");
	this.$('apronWd').val("0.00");
	this.$('apronPackKnd').val("");
	this.$('apronPackGrdnt').val("");
	this.$('csdhpShipScl').val("0.00");
	this.$('frostDmgWght').val("0.00");
	this.$('baseBttmSoil').val("");
	this.$('hndlFrght').val("");
	this.$('pileClbr').val("0.00");
	this.$('pileExt').val("0.00");
	this.$('pileQty').val("0");
	this.$('sheetFileStndrd').val("");
	this.$('hydrntQy').val("0");
	this.$('firepgQy').val("0");
	this.$('yardPackKnd').val("");
	this.$('yardAr').val("0.00");
	this.$('fenderKndCd').val("");
	this.$('fenderPmntItv').val("");
	this.$('fenderFmt').val("");
	this.$('mrpostStndrd1').val("");
	this.$('mrpostPmntItv1').val("");
	this.$('mrpostQy1').val("0");
	this.$('mrpostPwr1').val("");
	this.$('mrpostStndrd2').val("");
	this.$('mrpostPmntItv2').val("");
	this.$('mrpostQy2').val("0");
	this.$('mrpostPwr2').val("");
	this.$('berth').val("0");
	this.$('stplHndlFrght').val("");
	this.$('stplMoorShip').val("");
	this.$('beginPtLoc').val("");
	this.$('endPtLoc').val("");
	this.$('wd').val("0.00");
	this.$('lt').val("0.00");
	this.$('packKnd').val("");
	this.$('upsideWd').val("0.00");
	this.$('planHegh').val("0.00");
	this.$('wavemainDir').val("");
	this.$('outerSwaveSlpRate').val("0.00");
	this.$('inSwaveSlpRate').val("0.00");
	this.$('outerSwaveCover').val("");
	this.$('inSwaveCover').val("");
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
GamCvlEngFcltySpecMngModule.prototype.saveData = function() {

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
	var cvlEngFcltsClCd = this.$('#cvlEngFcltsClCd').val();
	var berth = Number(this.$('#berth').val().replace(/,/gi, ""));
	var lt = Number(this.$('#lt').val().replace(/,/gi, ""));
	var wd = Number(this.$('#wd').val().replace(/,/gi, ""));
	var fcltsExt = Number(this.$('#fcltsExt').val().replace(/,/gi, ""));
	var csdhpShipScl = Number(this.$('#csdhpShipScl').val().replace(/,/gi, ""));
	var apronWd = Number(this.$('#apronWd').val().replace(/,/gi, ""));
	var permWd = Number(this.$('#permWd').val().replace(/,/gi, ""));
	var pileQty = Number(this.$('#pileQty').val().replace(/,/gi, ""));
	var pileClbr = Number(this.$('#pileClbr').val().replace(/,/gi, ""));
	var pileExt = Number(this.$('#pileExt').val().replace(/,/gi, ""));
	var frostDmgWght = Number(this.$('#frostDmgWght').val().replace(/,/gi, ""));
	var yardAr = Number(this.$('#yardAr').val().replace(/,/gi, ""));
	var berthDpwt = Number(this.$('#berthDpwt').val().replace(/,/gi, ""));
	var hydrntQy = Number(this.$('#hydrntQy').val().replace(/,/gi, ""));
	var firepgQy = Number(this.$('#firepgQy').val().replace(/,/gi, ""));
	var outerSwaveSlpRate = Number(this.$('#outerSwaveSlpRate').val().replace(/,/gi, ""));
	var inSwaveSlpRate = Number(this.$('#inSwaveSlpRate').val().replace(/,/gi, ""));
	var mrpostQy1 = Number(this.$('#mrpostQy1').val().replace(/,/gi, ""));
	var mrpostQy2 = Number(this.$('#mrpostQy2').val().replace(/,/gi, ""));
	var upsideAltud = Number(this.$('#upsideAltud').val().replace(/,/gi, ""));
	var upsideWd = Number(this.$('#upsideWd').val().replace(/,/gi, ""));
	var planHegh = Number(this.$('#planHegh').val().replace(/,/gi, ""));
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
	if (cvlEngFcltsClCd == "") {
		alert('시설물 분류가 부정확합니다.');
		this.$("#cvlEngFcltsClCd").focus();
		return;
	}
	if (this.isValidCnt(berth) == false) {
		alert('선석수가 부정확합니다.');
		this.$("#berth").focus();
		return;
	}
	if (this.isValidNumber10P2(lt) == false) {
		alert('길이가 부정확합니다.');
		this.$("#lt").focus();
		return;
	}
	if (this.isValidNumber8P2(wd) == false) {
		alert('폭이 부정확합니다.');
		this.$("#lt").focus();
		return;
	}
	if (this.isValidNumber10P2(fcltsExt) == false) {
		alert('시설물 연장이 부정확합니다.');
		this.$("#fcltsExt").focus();
		return;
	}
	if (this.isValidNumber8P2(csdhpShipScl) == false) {
		alert('접안 선박 규모가 부정확합니다.');
		this.$("#csdhpShipScl").focus();
		return;
	}
	if (this.isValidNumber10P2(apronWd) == false) {
		alert('에이프런 폭이 부정확합니다.');
		this.$("#apronWd").focus();
		return;
	}
	if (this.isValidNumber8P2(permWd) == false) {
		alert('상치 폭이 부정확합니다.');
		this.$("#permWd").focus();
		return;
	}
	if (this.isValidCnt(pileQty) == false) {
		alert('말뚝수가 부정확합니다.');
		this.$("#pileQty").focus();
		return;
	}
	if (this.isValidNumber8P2(pileClbr) == false) {
		alert('말뚝 구경이 부정확합니다.');
		this.$("#pileClbr").focus();
		return;
	}
	if (this.isValidNumber10P2(pileExt) == false) {
		alert('말뚝 연장이 부정확합니다.');
		this.$("#pileExt").focus();
		return;
	}
	if (this.isValidNumber8P2(frostDmgWght) == false) {
		alert('상재 하중이 부정확합니다.');
		this.$("#frostDmgWght").focus();
		return;
	}
	if (this.isValidAr(yardAr) == false) {
		alert('야적장 면적이 부정확합니다.');
		this.$("#yardAr").focus();
		return;
	}
	if (this.isValidNumber8P2(berthDpwt) == false) {
		alert('선좌 수심이 부정확합니다.');
		this.$("#berthDpwt").focus();
		return;
	}
	if (this.isValidCnt(hydrntQy) == false) {
		alert('급수전 수량이 부정확합니다.');
		this.$("#hydrntQy").focus();
		return;
	}
	if (this.isValidCnt(firepgQy) == false) {
		alert('소화전 수량이 부정확합니다.');
		this.$("#firepgQy").focus();
		return;
	}
	if (this.isValidNumber8P2(outerSwaveSlpRate) == false) {
		alert('외측소 파공 경사 비율이 부정확합니다.');
		this.$("#outerSwaveSlpRate").focus();
		return;
	}
	if (this.isValidNumber8P2(inSwaveSlpRate) == false) {
		alert('내측소 파공 경사 비율이 부정확합니다.');
		this.$("#inSwaveSlpRate").focus();
		return;
	}
	if (this.isValidCnt(mrpostQy1) == false) {
		alert('계선주 수량 1이 부정확합니다.');
		this.$("#mrpostQy1").focus();
		return;
	}
	if (this.isValidCnt(mrpostQy2) == false) {
		alert('계선주 수량 2이 부정확합니다.');
		this.$("#mrpostQy2").focus();
		return;
	}
	if (this.isValidNumber8P2(upsideAltud) == false) {
		alert('천단 표고가 부정확합니다.');
		this.$("#upsideAltud").focus();
		return;
	}
	if (this.isValidNumber8P2(upsideWd) == false) {
		alert('천단 폭이 부정확합니다.');
		this.$("#upsideWd").focus();
		return;
	}
	if (this.isValidNumber8P2(planHegh) == false) {
		alert('설계 파고가 부정확합니다.');
		this.$("#planHegh").focus();
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = fcltsMngNo;
		this.doAction('/fclty/gamInsertCvlEngFcltySpecMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/fclty/gamUpdateCvlEngFcltySpecMng.do', inputVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.deleteData = function() {

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
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/fclty/gamDeleteCvlEngFcltySpecMng.do', deleteVO, function(module, result) {
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
GamCvlEngFcltySpecMngModule.prototype.saveMap = function() {

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
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamCvlEngFcltySpecMngModule.prototype.downloadExcel = function(buttonId) {

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
			this.$('#mainGrid').flexExcelDown('/fclty/gamExcelDownloadCvlEngFcltySpecMng.do');
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
GamCvlEngFcltySpecMngModule.prototype.displayAtchFileDirectory = function(argDirNo) {

	this.$("#atchFileDirTreeList").empty();
	var inputVO = this.makeFormArgs("#dirForm");
	this.doAction('/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirList.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var atchFileDirTreeNode = module.$('#atchFileDirTreeList');
				var atchFileDirTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var atchFileDir = result.resultList[i];
					atchFileDirTreeItems[atchFileDirTreeItems.length] = [atchFileDir.dirNo, atchFileDir.dirUpperNo, atchFileDir.dirNm];
				}
				module.tree = new dhtmlXTreeObject(atchFileDirTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("./js/codebase/imgs/dhxtree_skyblue/");
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
GamCvlEngFcltySpecMngModule.prototype.refreshDirData = function(argDirNo) {

	if (argDirNo > 1) {
		this.$('#dirNo').val('' + argDirNo);
		var searchVO = this.getFormValues('#dirForm');
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#dirForm', result.result);
				module.makeDivValues('#dirForm', result.result);
				module.$('#inputDirNm').val(result.result.dirNm);
				module.displayAtchFileList(argDirNo);
			} else {
				module.makeFormValues('#dirForm', {});
				module.makeDivValues('#dirForm', {});
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
 * @FUNCTION NAME : addAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY를 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.addAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	var inputDirNm = this.$('#inputDirNm').val();
	if (inputDirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('상위 디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (inputDirNm == dirNm) {
		alert('생성 디렉토리명이 현재 디렉토리명과 동일합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (confirm("[" + inputDirNm + "] 디렉토리를 생성하시겠습니까?")) {
		this.$('#dirNm').val(inputDirNm);
		this.$('#dirPath').val(dirPath + inputDirNm + "/");
		this.$('#dirUpperNo').val(dirNo);
		this.$('#depthSort').val("" + (depthSort + 1));
		this.$('#leafYn').val("Y");
		this.$('#dirFcltsJobSe').val("C");
		this.$('#dirNo').val("");
		var insertVO = this.makeFormArgs("#dirForm");
		this.$('#dirNm').val(dirNm);
		this.$('#dirPath').val(dirPath);
		this.$('#dirUpperNo').val(dirUpperNo);
		this.$('#depthSort').val("" + depthSort);
		this.$('#leafYn').val(leafYn);
		this.$('#dirFcltsJobSe').val(dirFcltsJobSe);
		this.$('#dirNo').val("" + dirNo);
		this.doAction('/fclty/gamInsertCvlEngFcltySpecMngAtchFileDir.do', insertVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("" + dirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : renameAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY NAME을 변경한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.renameAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	var inputDirNm = this.$('#inputDirNm').val();
	if (inputDirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (inputDirNm == dirNm) {
		alert('변경 디렉토리명이 현재 디렉토리명과 동일합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	var tempDirPath = dirPath.replace(/\/+$/, "");
	var oldDirNm = tempDirPath.substring(tempDirPath.lastIndexOf("/")+1);
	var upperDirPath = tempDirPath.substring(0,tempDirPath.lastIndexOf("/"));
	var newDirPath = upperDirPath + "/" + inputDirNm + "/";
	if (oldDirNm != dirNm) {
		alert('디렉토리 PATH에 현재 디렉토리명이 존재하지 않습니다.');
		return;
	}
	if (confirm("[" + dirNm + "]을 " + "[" + inputDirNm + "]로 변경하시겠습니까?")) {
		this.$('#dirNm').val(inputDirNm);
		this.$('#dirPath').val(newDirPath);
		var updateVO = this.makeFormArgs("#dirForm");
		this.$('#dirNm').val(dirNm);
		this.$('#dirPath').val(dirPath);
		this.doAction('/fclty/gamUpdateCvlEngFcltySpecMngAtchFileDir.do', updateVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("" + dirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : removeAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY를 제거한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.removeAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	if (dirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (confirm("[" + dirNm + "] 디렉토리를 삭제하시겠습니까?\r\n(하위 디렉토리 및 첨부 파일도 모두 삭제됩니다)")) {
		var deleteVO = this.makeFormArgs("#dirForm");
		this.doAction('/fclty/gamDeleteCvlEngFcltySpecMngAtchFileDir.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("");
			}
			alert(result.resultMsg);
		});
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
GamCvlEngFcltySpecMngModule.prototype.displayAtchFileList = function(argAtchFileDirNo) {

	this.$('#previewImage').attr('src', '');
	this.makeFormValues('#fileForm', {});
	this.makeDivValues('#fileForm', {});
	this.$('#fileGrid').flexEmptyData();
	if (argAtchFileDirNo != null && argAtchFileDirNo != "") {
		this.$('#atchFileDirNo').val(argAtchFileDirNo);
		var detailOpt = this.getFormValues('#fileForm');
		this.$('#atchFileDirNo').val("");
		this.$('#fileGrid').flexOptions({params:detailOpt}).flexReload();
		this.enableFileButtonItem();
	} else {
		this.disableFileButtonItem();
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
GamCvlEngFcltySpecMngModule.prototype.refreshFileData = function(argAtchFileNo) {

	if (argAtchFileNo != null && argAtchFileNo != "") {
		this.$('#atchFileNo').val(argAtchFileNo);
		this.$('#previewImage').attr('src', '');
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectCvlEngFcltySpecMngFcltsAtchFilePk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#fileForm', result.result);
				module.makeDivValues('#fileForm', result.result);
				module.enableFileButtonItem();
			} else {
				module.makeFormValues('#fileForm', {});
				module.makeDivValues('#fileForm', {});
				module.disableFileButtonItem();
			}
		});
	} else {
		this.$('#previewImage').attr('src', '');
		this.makeFormValues('#fileForm', {});
		this.makeDivValues('#fileForm', {});
		this.disableFileButtonItem();
	}

};

<%
/**
 * @FUNCTION NAME : saveUploadFileData
 * @DESCRIPTION   : UPLOAD FILE 항목을 저장한다.
 * @PARAMETER     :
 *   1. argAtchFileFcltsMngNo - ATTACHE FILE FCLTS MNG NO.
 *   2. argAtchFileFcltsDirNo - ATTACHE FILE FCLTS DIRECTORY NO.
 *   3. argAtchFileFcltsJobSe - ATTACHE FILE FCLTS JOB SE
 *   4. argAtchFileFcltsDataSe - ATTACHE FILE FCLTS DATA SE
 *   5. argAtchFileFcltsMngSeq - ATTACHE FILE FCLTS MNG SEQ.
 *   6. argAtchFileNmLogic - ATTACHE FILE NAME LOGICAL.
 *   7. argAtchFileNmPhysicl - ATTACHE FILE NAME PHYSICAL
**/
%>
GamCvlEngFcltySpecMngModule.prototype.saveUploadFileData = function(argAtchFileFcltsMngNo, argAtchFileFcltsDirNo, argAtchFileFcltsJobSe, argAtchFileFcltsDataSe, argAtchFileFcltsMngSeq, argAtchFileNmLogic, argAtchFileNmPhysicl) {

	var inputVO = [];
	var atchFileSe = "D";
	var atchFileSeNm = "문서";
	if (argAtchFileNmPhysicl != null || argAtchFileNmPhysicl != "") {
		var ext = argAtchFileNmPhysicl.substring(argAtchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
		if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
			atchFileSe = "P";
			atchFileSeNm = "사진";
		} else if (ext == "dwg" || ext == "dxf") {
			atchFileSe = "C";
			atchFileSeNm = "도면";
		}
	}
	inputVO={
			'atchFileNo':"",
			'atchFileNmLogic':argAtchFileNmLogic,
			'atchFileNmPhysicl':argAtchFileNmPhysicl,
			'atchFileSe':atchFileSe,
			'atchFileSeNm':atchFileSeNm,
			'atchFileDirNo':argAtchFileFcltsDirNo,
			'atchFileFcltsDataSe':argAtchFileFcltsDataSe,
			'atchFileFcltsMngNo':argAtchFileFcltsMngNo,
			'atchFileFcltsJobSe':argAtchFileFcltsJobSe,
			'atchFileFcltsMngSeq':argAtchFileFcltsMngSeq,
			'regUsr':"",
			'registDt':"",
			'updUsr':"",
			'updtDt':""
	};
	this.doAction('/fclty/gamInsertCvlEngFcltySpecMngFcltsAtchFile.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$("#fileGrid").flexAddRow({ atchFileNo:result.atchFileNo,
											   atchFileSe:atchFileSe,
											   atchFileSeNm:atchFileSeNm,
											   atchFileNmLogic: argAtchFileNmLogic,
											   atchFileNmPhysicl: argAtchFileNmPhysicl,
											   atchFileFcltsDirNo: argAtchFileFcltsDirNo,
											   atchFileFcltsDataSe: argAtchFileFcltsDataSe,
											   atchFileFcltsMngNo: argAtchFileFcltsMngNo,
											   atchFileFcltsJobSe: argAtchFileFcltsJobSe,
											   atchFileFcltsMngSeq: argAtchFileFcltsMngSeq
											  });
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
GamCvlEngFcltySpecMngModule.prototype.uploadFile = function() {

	var atchFileFcltsDirNo = Number(this.$('#dirNo').val());
	var atchFileFcltsMngNo = this.$('#fcltsMngNo').val();
	var atchFileFcltsDataSe = "D";
	var atchFileFcltsJobSe = "C";
	var atchFileFcltsMngSeq = "";
	if (atchFileFcltsDirNo <= 0) {
		alert('업로드 디렉토리가 선택되지 않았습니다.');
		return;
	}
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			module.saveUploadFileData(atchFileFcltsMngNo, atchFileFcltsDirNo, atchFileFcltsJobSe, atchFileFcltsDataSe, atchFileFcltsMngSeq, this.logicalFileNm, this.physcalFileNm);
		});
	}, "첨부파일 업로드");

};

<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.downloadFile = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length > 0) {
		var row = selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : deleteFileData
 * @DESCRIPTION   : FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.deleteFileData = function() {

	var atchFileDirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	if (atchFileNo == "") {
		alert('첨부 파일 번호가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#fileForm");
		this.doAction('/fclty/gamDeleteCvlEngFcltySpecMngFcltsAtchFile.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList(atchFileDirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : previewFile
 * @DESCRIPTION   : FILE PREVIEW
 * @PARAMETER     :
 *   1. argPreviewFlag - PREVIEW FLAG
**/
%>
GamCvlEngFcltySpecMngModule.prototype.previewFile = function(argPreviewFlag) {

	if (argPreviewFlag == true) {
		var selectRow = this.$('#fileGrid').selectedRows();
		if (selectRow.length > 0) {
			var row = selectRow[0];
			var atchFileNmPhysicl = row["atchFileNmPhysicl"];
			if (atchFileNmPhysicl != null || atchFileNmPhysicl != "") {
				var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
				if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
					$imgURL = this.getPfPhotoUrl(atchFileNmPhysicl);
					this.$("#previewImage").attr("src", $imgURL);
				} else {
					this.$("#previewImage").removeAttr("src");
				}
			}
		}
	} else {
		this.$('#previewImage').attr('src', '');
	}

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.enableListButtonItem = function() {

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
GamCvlEngFcltySpecMngModule.prototype.enableDetailInputItem = function() {

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
		this.$('#cvlEngFcltsClCd').enable();
		this.$('fcltsExt').enable();
		this.$('strctFmt').enable();
		this.$('upsideAltud').enable();
		this.$('berthDpwt').enable();
		this.$('permWd').enable();
		this.$('apronWd').enable();
		this.$('apronPackKnd').enable();
		this.$('apronPackGrdnt').enable();
		this.$('csdhpShipScl').enable();
		this.$('frostDmgWght').enable();
		this.$('baseBttmSoil').enable();
		this.$('hndlFrght').enable();
		this.$('pileClbr').enable();
		this.$('pileExt').enable();
		this.$('pileQty').enable();
		this.$('sheetFileStndrd').enable();
		this.$('hydrntQy').enable();
		this.$('firepgQy').enable();
		this.$('yardPackKnd').enable();
		this.$('yardAr').enable();
		this.$('fenderKndCd').enable();
		this.$('fenderPmntItv').enable();
		this.$('fenderFmt').enable();
		this.$('mrpostStndrd1').enable();
		this.$('mrpostPmntItv1').enable();
		this.$('mrpostQy1').enable();
		this.$('mrpostPwr1').enable();
		this.$('mrpostStndrd2').enable();
		this.$('mrpostPmntItv2').enable();
		this.$('mrpostQy2').enable();
		this.$('mrpostPwr2').enable();
		this.$('berth').enable();
		this.$('stplHndlFrght').enable();
		this.$('stplMoorShip').enable();
		this.$('loc').enable();
		this.$('beginPtLoc').enable();
		this.$('endPtLoc').enable();
		this.$('wd').enable();
		this.$('lt').enable();
		this.$('packKnd').enable();
		this.$('upsideWd').enable();
		this.$('planHegh').enable();
		this.$('wavemainDir').enable();
		this.$('outerSwaveSlpRate').enable();
		this.$('inSwaveSlpRate').enable();
		this.$('outerSwaveCover').enable();
		this.$('inSwaveCover').enable();
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
			this.$('#cvlEngFcltsClCd').enable();
			this.$('fcltsExt').enable();
			this.$('strctFmt').enable();
			this.$('upsideAltud').enable();
			this.$('berthDpwt').enable();
			this.$('permWd').enable();
			this.$('apronWd').enable();
			this.$('apronPackKnd').enable();
			this.$('apronPackGrdnt').enable();
			this.$('csdhpShipScl').enable();
			this.$('frostDmgWght').enable();
			this.$('baseBttmSoil').enable();
			this.$('hndlFrght').enable();
			this.$('pileClbr').enable();
			this.$('pileExt').enable();
			this.$('pileQty').enable();
			this.$('sheetFileStndrd').enable();
			this.$('hydrntQy').enable();
			this.$('firepgQy').enable();
			this.$('yardPackKnd').enable();
			this.$('yardAr').enable();
			this.$('fenderKndCd').enable();
			this.$('fenderPmntItv').enable();
			this.$('fenderFmt').enable();
			this.$('mrpostStndrd1').enable();
			this.$('mrpostPmntItv1').enable();
			this.$('mrpostQy1').enable();
			this.$('mrpostPwr1').enable();
			this.$('mrpostStndrd2').enable();
			this.$('mrpostPmntItv2').enable();
			this.$('mrpostQy2').enable();
			this.$('mrpostPwr2').enable();
			this.$('berth').enable();
			this.$('stplHndlFrght').enable();
			this.$('stplMoorShip').enable();
			this.$('loc').enable();
			this.$('beginPtLoc').enable();
			this.$('endPtLoc').enable();
			this.$('wd').enable();
			this.$('lt').enable();
			this.$('packKnd').enable();
			this.$('upsideWd').enable();
			this.$('planHegh').enable();
			this.$('wavemainDir').enable();
			this.$('outerSwaveSlpRate').enable();
			this.$('inSwaveSlpRate').enable();
			this.$('outerSwaveCover').enable();
			this.$('inSwaveCover').enable();
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
			this.$('#cvlEngFcltsClCd').disable();
			this.$('fcltsExt').disable();
			this.$('strctFmt').disable();
			this.$('upsideAltud').disable();
			this.$('berthDpwt').disable();
			this.$('permWd').disable();
			this.$('apronWd').disable();
			this.$('apronPackKnd').disable();
			this.$('apronPackGrdnt').disable();
			this.$('csdhpShipScl').disable();
			this.$('frostDmgWght').disable();
			this.$('baseBttmSoil').disable();
			this.$('hndlFrght').disable();
			this.$('pileClbr').disable();
			this.$('pileExt').disable();
			this.$('pileQty').disable();
			this.$('sheetFileStndrd').disable();
			this.$('hydrntQy').disable();
			this.$('firepgQy').disable();
			this.$('yardPackKnd').disable();
			this.$('yardAr').disable();
			this.$('fenderKndCd').disable();
			this.$('fenderPmntItv').disable();
			this.$('fenderFmt').disable();
			this.$('mrpostStndrd1').disable();
			this.$('mrpostPmntItv1').disable();
			this.$('mrpostQy1').disable();
			this.$('mrpostPwr1').disable();
			this.$('mrpostStndrd2').disable();
			this.$('mrpostPmntItv2').disable();
			this.$('mrpostQy2').disable();
			this.$('mrpostPwr2').disable();
			this.$('berth').disable();
			this.$('stplHndlFrght').disable();
			this.$('stplMoorShip').disable();
			this.$('loc').disable();
			this.$('beginPtLoc').disable();
			this.$('endPtLoc').disable();
			this.$('wd').disable();
			this.$('lt').disable();
			this.$('packKnd').disable();
			this.$('upsideWd').disable();
			this.$('planHegh').disable();
			this.$('wavemainDir').disable();
			this.$('outerSwaveSlpRate').disable();
			this.$('inSwaveSlpRate').disable();
			this.$('outerSwaveCover').disable();
			this.$('inSwaveCover').disable();
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
GamCvlEngFcltySpecMngModule.prototype.disableDetailInputItem = function() {

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
	this.$('#cvlEngFcltsClCd').disable();
	this.$('fcltsExt').disable();
	this.$('strctFmt').disable();
	this.$('upsideAltud').disable();
	this.$('berthDpwt').disable();
	this.$('permWd').disable();
	this.$('apronWd').disable();
	this.$('apronPackKnd').disable();
	this.$('apronPackGrdnt').disable();
	this.$('csdhpShipScl').disable();
	this.$('frostDmgWght').disable();
	this.$('baseBttmSoil').disable();
	this.$('hndlFrght').disable();
	this.$('pileClbr').disable();
	this.$('pileExt').disable();
	this.$('pileQty').disable();
	this.$('sheetFileStndrd').disable();
	this.$('hydrntQy').disable();
	this.$('firepgQy').disable();
	this.$('yardPackKnd').disable();
	this.$('yardAr').disable();
	this.$('fenderKndCd').disable();
	this.$('fenderPmntItv').disable();
	this.$('fenderFmt').disable();
	this.$('mrpostStndrd1').disable();
	this.$('mrpostPmntItv1').disable();
	this.$('mrpostQy1').disable();
	this.$('mrpostPwr1').disable();
	this.$('mrpostStndrd2').disable();
	this.$('mrpostPmntItv2').disable();
	this.$('mrpostQy2').disable();
	this.$('mrpostPwr2').disable();
	this.$('berth').disable();
	this.$('stplHndlFrght').disable();
	this.$('stplMoorShip').disable();
	this.$('loc').disable();
	this.$('beginPtLoc').disable();
	this.$('endPtLoc').disable();
	this.$('wd').disable();
	this.$('lt').disable();
	this.$('packKnd').disable();
	this.$('upsideWd').disable();
	this.$('planHegh').disable();
	this.$('wavemainDir').disable();
	this.$('outerSwaveSlpRate').disable();
	this.$('inSwaveSlpRate').disable();
	this.$('outerSwaveCover').disable();
	this.$('inSwaveCover').disable();
	this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecPrtFcltyMngEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.enableFileButtonItem = function() {

	var dirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	if (dirNo != "") {
		if (atchFileNo != "") {
			this.$('#btnFileRefresh').enable();
			this.$('#btnFileRefresh').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
			this.$('#btnFilePreview').enable();
			this.$('#btnFilePreview').removeClass('ui-state-disabled');
		} else {
			this.$('#btnFileRefresh').enable();
			this.$('#btnFileRefresh').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});
		}
	} else {
		this.$('#btnFileRefresh').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});
	}

};

<%
/**
 * @FUNCTION NAME : disableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamCvlEngFcltySpecMngModule.prototype.disableFileButtonItem = function() {

	this.$('#btnFileRefresh').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});

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
GamCvlEngFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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
			if (this._atchFileDirLoad == false) {
				this.displayAtchFileDirectory("");
				this._atchFileDirLoad = true;
			}
			break;
	}

};

var module_instance = new GamCvlEngFcltySpecMngModule();

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
				<li><a href="#listTab" class="emdTab">토목시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">토목시설 제원</a></li>
				<li><a href="#fileTab" class="emdTab">토목시설 첨부파일</a></li>
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
									<input type="hidden" id="gisAssetsLocCd"/>
									<input type="hidden" id="gisAssetsLocNm"/>
									<input type="text" size="22" id="gisAssetsNm" disabled/>
									<button id="popupSpecGisAssetsCd" class="popupButton">선택</button>
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
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM005"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시　　설　　명</th>
								<td>
									<input type="text" size="33" id="prtFcltyNm" maxlength="80"/>
								</td>
								<th style="width:10%; height:18px;">소　　재　　지</th>
								<td>
									<input type="hidden" id="laCrdnt"/>
									<input type="hidden" id="loCrdnt"/>
									<input type="hidden" id="lat"/>
									<input type="hidden" id="lng"/>
									<input type="hidden" id="gisAssetsLocplcLnm"/>
									<input type="hidden" id="loc"/>
									<input type="text" size="33" id="prtFcltyLoc"/>
								</td>
								<th style="width:10%; height:18px;">규　격／단　위</th>
								<td>
									<input type="text" size="20" id="prtFcltyStndrd" maxlength="80"/>／
									<input type="text" size="10" id="prtFcltyUnit" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">면　적／수　량</th>
								<td>
									<input type="text" size="11" id="prtFcltyAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>／
									<input type="text" size="11" id="prtPrtFcltyCnt" class="ygpaNumber" maxlength="10"/> 개
								</td>
								<th style="width:10%; height:18px;">설치일／변경일</th>
								<td>
									<input type="text" size="11" id="prtFcltyInstlDt" class="emdcal"/>／
									<input type="text" size="11" id="prtFcltyChangeDt" class="emdcal"/>
								</td>
								<th style="width:10%; height:18px;">만료일／담당자</th>
								<td>
									<input type="text" size="11" id="prtFcltyExprDt" class="emdcal"/>／
									<input type="text" size="16" id="prtPrtFcltyMnger" maxlength="80"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">관　리　업　체</th>
								<td>
									<input type="text" size="2" id="prtFcltyMngEntrpsCd" maxlength="8"/>
									<input type="text" size="17" id="prtFcltyMngEntrpsNm" disabled/>
									<button id="popupSpecPrtFcltyMngEntrpsCd" class="popupButton">선택</button>
								</td>
								<th style="width:10%; height:18px;">시설물관리그룹</th>
								<td colspan="3">
									<input type="text" size="18" id="fcltsMngGroupNo" maxlength="8"/>
									<input type="text" size="61" id="fcltsMngGroupNm" disabled/>
									<button id="popupSpecFcltsMngGroupNo" class="popupButton">선택</button>
								</td>
							</tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">토목시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecInsert" class="buttonAdd">　추　가　</button>
									<button id="btnSpecSave" class="buttonSave">　저　장　</button>
									<button id="btnSpecRemove" class="buttonDelete">　삭　제　</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">시설물　　분류</th>
								<td>
									<input type="hidden" id="cvlEngFcltsClCdNm"/>
									<select id="cvlEngFcltsClCd">
										<option value="" selected="selected">선택</option>
										<c:forEach  items="${fcltsClCdList}" var="fcltsClCdItem">
											<option value="${fcltsClCdItem.fcltsClCd}">${fcltsClCdItem.fcltsClCdNm}</option>
										</c:forEach>
									</select>
								</td>
								<th style="width:10%; height:18px;">주요 취급 화물</th>
								<td>
									<input type="text" size="33" id="stplHndlFrght" maxlength="300"/>
								</td>
								<th style="width:10%; height:18px;">주요 계류 선박</th>
								<td>
									<input type="text" size="33" id="stplMoorShip" maxlength="300"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">구　조　형　식</th>
								<td>
									<input type="text" size="33" id="strctFmt" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">선석수 ／ 길이</th>
								<td>
									<input type="text" size="11" id="berth" class="ygpaNumber" maxlength="6"/> 개 ／
									<input type="text" size="11" id="lt" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m
								</td>
								<th style="width:10%; height:18px;">폭 ／ 포장종류</th>
								<td>
									<input type="text" size="12" id="wd" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m ／
									<input type="text" size="13" id="packKnd" maxlength="30"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">기초 저면 토질</th>
								<td>
									<input type="text" size="33" id="baseBttmSoil" maxlength="150"/>
								</td>
								<th style="width:10%; height:18px;">시 설 물 연 장</th>
								<td>
									<input type="text" size="33" id="fcltsExt" class="ygpaNumber" data-decimal-point="2" maxlength="13"/>
								</td>
								<th style="width:10%; height:18px;">접안 선박 규모</th>
								<td>
									<input type="text" size="33" id="csdhpShipScl" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">에 이 프 런 폭</th>
								<td>
									<input type="text" size="30" id="apronWd" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m
								</td>
								<th style="width:10%; height:18px;">포　장　종　류</th>
								<td>
									<input type="text" size="33" id="apronPackKnd" maxlength="30"/>
								</td>
								<th style="width:10%; height:18px;">포　장　구　배</th>
								<td>
									<input type="text" size="33" id="apronPackGrdnt" maxlength="60"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">상치폭／말뚝수</th>
								<td>
									<input type="text" size="11" id="permWd" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m ／
									<input type="text" size="11" id="pileQty" class="ygpaNumber" maxlength="6"/> 개
								</td>
								<th style="width:10%; height:18px;">말뚝구경／연장</th>
								<td>
									<input type="text" size="14" id="pileClbr" class="ygpaNumber" data-decimal-point="2" maxlength="8"/>／
									<input type="text" size="14" id="pileExt" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">널 말 뚝 규 격</th>
								<td>
									<input type="text" size="33" id="sheetFileStndrd" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">상　재　하　중</th>
								<td>
									<input type="text" size="33" id="frostDmgWght" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">야 적 장 면 적</th>
								<td>
									<input type="text" size="30" id="yardAr" class="ygpaNumber" data-decimal-point="2" maxlength="13"/> m<sup>2</sup>
								</td>
								<th style="width:10%; height:18px;">야적장포장종류</th>
								<td>
									<input type="text" size="33" id="yardPackKnd" maxlength="30"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">방 충 재 종 류</th>
								<td>
									<input type="text" size="33" id="fenderKndCd" maxlength="30"/>
								</td>
								<th style="width:10%; height:18px;">방 충 재 형 식</th>
								<td>
									<input type="text" size="33" id="fenderFmt" maxlength="30"/>
								</td>
								<th style="width:10%; height:18px;">방충재배치간격</th>
								<td>
									<input type="text" size="33" id="fenderPmntItv" maxlength="30"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">선　좌　수　심</th>
								<td>
									<input type="text" size="30" id="berthDpwt" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
								<th style="width:10%; height:18px;">급 수 전 수 량</th>
								<td>
									<input type="text" size="30" id="hydrntQy" class="ygpaNumber" maxlength="6"/> 개
								</td>
								<th style="width:10%; height:18px;">소 화 전 수 량</th>
								<td>
									<input type="text" size="30" id="firepgQy" class="ygpaNumber" maxlength="6"/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">시 작 점 위 치</th>
								<td>
									<input type="text" size="33" id="beginPtLoc" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">외측소파공피복</th>
								<td>
									<input type="text" size="33" id="outerSwaveCover" maxlength="60"/>
								</td>
								<th style="width:10%; height:18px;">외측소파공경사</th>
								<td>
									<input type="text" size="33" id="outerSwaveSlpRate" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">종 착 점 위 치</th>
								<td>
									<input type="text" size="33" id="endPtLoc" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">내측소파공피복</th>
								<td>
									<input type="text" size="33" id="inSwaveCover" maxlength="60"/>
								</td>
								<th style="width:10%; height:18px;">내측소파공경사</th>
								<td>
									<input type="text" size="33" id="inSwaveSlpRate" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">계선주 규격　1</th>
								<td>
									<input type="text" size="33" id="mrpostStndrd1" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">계선주 간격　1</th>
								<td>
									<input type="text" size="33" id="mrpostPmntItv1" maxlength="30"/>
								</td>
								<th style="width:10%; height:18px;">견인력／수량 1</th>
								<td>
									<input type="text" size="20" id="mrpostPwr1" maxlength="30"/>／
									<input type="text" size="5" id="mrpostQy1" class="ygpaNumber" maxlength="6"/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">계선주 규격　2</th>
								<td>
									<input type="text" size="33" id="mrpostStndrd2" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">계선주 간격　2</th>
								<td>
									<input type="text" size="33" id="mrpostPmntItv2" maxlength="30"/>
								</td>
								<th style="width:10%; height:18px;">견인력／수량 2</th>
								<td>
									<input type="text" size="20" id="mrpostPwr2" maxlength="30"/>／
									<input type="text" size="5" id="mrpostQy2" class="ygpaNumber" maxlength="6"/> 개
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">천단표고 ／ 폭</th>
								<td>
									<input type="text" size="11" id="upsideAltud" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m ／
									<input type="text" size="11" id="upsideWd" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
								<th style="width:10%; height:18px;">설　계　파　고</th>
								<td>
									<input type="text" size="33" id="planHegh" class="ygpaNumber" data-decimal-point="2" maxlength="10"/>
								</td>
								<th style="width:10%; height:18px;">파 랑 주 방 향</th>
								<td>
									<input type="text" size="33" id="wavemainDir" maxlength="30"/>
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
						<th style="font-weight:bold; height:20px;">디렉토리 영역 : </th>
						<th style="width:10%; height:20px;">선택디렉토리</th>
						<td style="width:50%;">
							<form id="dirForm">
								<input id="dirNo" type="hidden"/>
								<input id="dirNm" type="hidden"/>
								<input id="dirFcltsJobSe" type="hidden"/>
								<input id="dirUpperNo" type="hidden"/>
								<input id="dirPath" type="hidden"/>
								<input id="depthSort" type="hidden"/>
								<input id="leafYn" type="hidden"/>
								<input id="inputDirNm" type="text" size="50" maxlength="100"/>
							</form>
						</td>
						<th style="font-weight:bold; height:20px;">첨부파일 영역 : </th>
						<th style="width:15%; height:20px;">선택첨부파일</th>
						<td>
							<form id="fileForm">
								<input id="atchFileNo" type="hidden"/>
								<input id="atchFileNmPhysicl" type="hidden"/>
								<input id="atchFileSe" type="hidden"/>
								<input id="atchFileSeNm" type="hidden"/>
								<input id="atchFileDirNo" type="hidden"/>
								<input id="atchFileDataSe" type="hidden"/>
								<input id="atchFileJobSe" type="hidden"/>
								<input id="atchFileFcltsMngNo" type="hidden"/>
								<input id="atchFileFcltsMngSeq" type="hidden"/>
								<input id="atchFileNmLogic" type="text" size="50" disabled/>
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
							<img id="previewImage" style="margin:1px; width:477px; height:235px; border:1px solid #000; max-width:477px; max-height:235px" src="">
						</td>
					</tr>
				</table>
				<table class="detailPanel" style="width:100%;">
					<tr>
						<td style="width:50%;">
							<button id="btnDirRefresh">디렉토리 재조회</button>
							<button id="btnDirAdd" class="buttonAdd">디렉토리 생성</button>
							<button id="btnDirRename" class="buttonSave">디렉토리명 변경</button>
							<button id="btnDirRemove" class="buttonDelete">디렉토리 삭제</button>
						</td>
						<td style="width:50%; text-align:right;">
							<button id="btnFileRefresh">파일 재조회</button>
							<button id="btnFileUpload" class="buttonAdd">파일 추가</button>
							<button id="btnFileDownload">파일 다운로드</button>
							<button id="btnFileRemove" class="buttonDelete">파일 삭제</button>
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
