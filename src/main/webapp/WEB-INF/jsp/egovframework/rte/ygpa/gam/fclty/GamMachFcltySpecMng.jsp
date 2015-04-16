<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamMachFcltySpecMng.jsp
 * @Description : 기계시설 제원 관리
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
 * @FUNCTION NAME : GamMachFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamMachFcltySpecMngModule() {}

GamMachFcltySpecMngModule.prototype = new EmdModule(1000, 730);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecMngList.do',
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
					{display:"규격",				name:"eqpmnStndrd",				width:150,		sortable:false,		align:"left"},
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
	});

	this.$("#prtFcltyMngEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#prtFcltyMngEntrpsCd", "#prtFcltyMngEntrpsNm");
	});

	this.$("#fcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNm("#fcltsMngGroupNo", "#fcltsMngGroupNm");
	});

	this.$("#archFcltsMngNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getGisPrtFcltyNm("#archFcltsMngNo", "#archFcltsNm");
	});

	this.$("#prtFcltyLoc").bind("change", {module: this}, function(event) {
		event.data.module.setLoc($(this).val());
	});

	this.$("#mechFcltsSe").bind("change", {module: this}, function(event) {
		var mechFcltsSe = $(this).val();
		event.data.module.changeDetailAreaForm(mechFcltsSe);
	});

	this.$("#statusGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecMngMachFcltySttusList.do',
		dataType : "json",
		colModel : [
					{display:"부두명",				name:"wharfNm",			width:130,		sortable:false,		align:"center"},
					{display:"운영사",			 	name:"operCmpny",		width:100,		sortable:false,		align:"center"},
					{display:"C/C",					name:"ccCount",			width:100,		sortable:false,		align:"center"},
					{display:"T/C",					name:"tcCount",			width:110,		sortable:false,		align:"center"},
					{display:"Y/T",					name:"ytCount",			width:100,		sortable:false,		align:"center"},
					{display:"샷시",				name:"csCount",			width:100,		sortable:false,		align:"center"},
					{display:"Reach",				name:"rsCount",			width:100,		sortable:false,		align:"center"},
					{display:"Top Handler",			name:"thCount",			width:100,		sortable:false,		align:"center"},
					{display:"비고(eRTGC)",			name:"tcRtgcCount",		width:100,		sortable:false,		align:"center"}
					],
		showTableToggleBtn : false,
		height : '477',
		mergeRows : 'wharfNm'
	});

	this.$("#statusGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectStatusData();
	});

	this.$("#statusGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._statusmode = 'modify';
		module._statusKeyValue1 = row.wharfNm;
		module._statusKeyValue2 = row.operCmpny;
	});

	this.$("#statusGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._statusmode = 'modify';
		module._statusKeyValue1 = row.wharfNm;
		module._statusKeyValue2 = row.operCmpny;
		module.showMachFcltySttusPopup("U");
	});

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMachFcltySpecMngFcltsAtchFileList.do',
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
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.enableFileButtonItem();
		module.showFcltsAtchFileViewPopup();
	});

	this.$('#dirQueryOption').on('change',{module:this}, function(event){
		event.data.module.displayAtchFileDirectory("");
		event.data.module.displayAtchFileList("");
	});

	this._params = params;
	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._statusmode = 'query';
	this._statusKeyValue1 = '';
	this._statusKeyValue2 = '';
	this._searchButtonClick = false;
	this._fileKeyValue = "";
	this._atchFileDirLoad = false;
	this._atchFilePreview = false;
	this._mainGridDisplay = 'mainGrid';
	this.$('#statusGrid').hide();
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
	//this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
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
 * @FUNCTION NAME : isValidYearMonth
 * @DESCRIPTION   : YEAR & MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. yearMonthString - YEAR & MONTH STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamMachFcltySpecMngModule.prototype.isValidYearMonth = function(yearMonthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (yearMonthString == "") {
			return false;
		}
	} else {
		if (yearMonthString == "") {
			return true;
		}
	}
	var year = Number(yearMonthString.substring(0,4));
	var month = Number(yearMonthString.substring(5,7));
	if (year > 9999 || year < 1900) {
		return false;
	}
	if (month > 12 || month < 1) {
		return false;
	}
	return true;

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
GamMachFcltySpecMngModule.prototype.isValidDate = function(dateString, nullCheckFlag) {

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
GamMachFcltySpecMngModule.prototype.isValidDateFromTo = function(startDateString, endDateString, nullCheckFlag) {

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
GamMachFcltySpecMngModule.prototype.isValidFirstDate = function(firstDateString, secondDateString, nullCheckFlag) {

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
GamMachFcltySpecMngModule.prototype.isValidAmount = function(amountValue) {

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
GamMachFcltySpecMngModule.prototype.isValidAr = function(arValue) {

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
GamMachFcltySpecMngModule.prototype.isValidCnt = function(cntValue) {

	if (cntValue > 99999 || cntValue < 0) {
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
GamMachFcltySpecMngModule.prototype.isValidNumber10P2 = function(numValue) {

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
GamMachFcltySpecMngModule.prototype.isValidNumber8P2 = function(numValue) {

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
 GamMachFcltySpecMngModule.prototype.isValidNumber6P2 = function(numValue) {

	if (numValue > 9999.99 || numValue < 0) {
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
GamMachFcltySpecMngModule.prototype.getFcltsMngGroupNm = function(argFcltsMngGroupNoVariableName, argFcltsMngGroupNmVariableName) {

	var sFcltsMngGroupNm = "";
	if (argFcltsMngGroupNoVariableName == null || argFcltsMngGroupNoVariableName == "") {
		return sFcltsMngGroupNm;
	}
	var sFcltsMngGroupNo = this.$(argFcltsMngGroupNoVariableName).val();
	if (sFcltsMngGroupNo.length == 14) {
		var searchVO = { 'sFcltsMngGroupNo':sFcltsMngGroupNo };
		this.doAction('/fclty/gamSelectMachFcltySpecMngFcltsMngGroupNm.do', searchVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.getEntrpsNm = function(argEntrpsCdVariableName, argEntrpsNmVariableName) {

	var sEntrpsNm = "";
	if (argEntrpsCdVariableName == null || argEntrpsCdVariableName == "") {
		return sEntrpsNm;
	}
	var sEntrpscd = this.$(argEntrpsCdVariableName).val();
	if (sEntrpscd.length() == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/fclty/gamSelectMachFcltySpecMngEntrpsNm.do', searchVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.getFcltsClCdNm = function(argFcltsClCdVariableName, argFcltsClCdNmVariableName) {

	var sFcltsClCdNm = "";
	if (argFcltsClCdVariableName == null || argFcltsClCdVariableName == "") {
		return sFcltsClCdNm;
	}
	var sFcltsClCd = this.$(argFcltsClCdVariableName).val();
	if (sFcltsClCd.length == 14) {
		var searchVO = { 'sFcltsClCd':sFcltsClCd };
		this.doAction('/fclty/gamSelectMachFcltySpecMngFcltsClCdNm.do', searchVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.setLoc = function(argPrtFcltyLoc) {

	var loc = this.$('#loc').val();
	if (loc != argPrtFcltyLoc) {
		this.$('#loc').val(argPrtFcltyLoc);
	}

};

<%
/**
 * @FUNCTION NAME : setEqpmnNm
 * @DESCRIPTION   : 장비명을 설정한다.
 * @PARAMETER     :
 *   1. argEqpmnNm - 장비명
**/
%>
GamMachFcltySpecMngModule.prototype.setEqpmnNm = function(argEqpmnNm) {

	var equipNm = this.$('#eqpmnNm').val();
	if (equipNm != argEqpmnNm) {
		this.$('#eqpmnNm').val(argEqpmnNm);
	}

};

<%
/**
 * @FUNCTION NAME : setInstlYrmt
 * @DESCRIPTION   : 설치년월을 설정한다.
 * @PARAMETER     :
 *   1. argInstlYrmt - 설치년월
**/
%>
GamMachFcltySpecMngModule.prototype.setInstlYrmt = function(argInstlYrmt) {

	var instlYrmt = this.$('#instlYrmt').val();
	if (instlYrmt != argInstlYrmt) {
		this.$('#instlYrmt').val(argInstlYrmt);
	}

};

<%
/**
 * @FUNCTION NAME : setMfcCmpny
 * @DESCRIPTION   : 제조회사를 설정한다.
 * @PARAMETER     :
 *   1. argMfcCmpny - 제조회사
**/
%>
GamMachFcltySpecMngModule.prototype.setMfcCmpny = function(argMfcCmpny) {

	var mfcCmpny = this.$('#mfcCmpny').val();
	if (mfcCmpny != argMfcCmpny) {
		this.$('#mfcCmpny').val(argMfcCmpny);
	}

};

<%
/**
 * @FUNCTION NAME : setMfcAmt
 * @DESCRIPTION   : 제조금액을 설정한다.
 * @PARAMETER     :
 *   1. argMfcAmt - 제조금액
**/
%>
GamMachFcltySpecMngModule.prototype.setMfcAmt = function(argMfcAmt) {

	var mfcAmt = this.$('#mfcAmt').val();
	if (mfcAmt != argMfcAmt) {
		this.$('#mfcAmt').val(argMfcAmt);
	}

};

<%
/**
 * @FUNCTION NAME : setOperCmpny
 * @DESCRIPTION   : 운영회사를 설정한다.
 * @PARAMETER     :
 *   1. argOperCmpny - 운영회사
**/
%>
GamMachFcltySpecMngModule.prototype.setOperCmpny = function(argOperCmpny) {

	var operCmpny = this.$('#operCmpny').val();
	if (operCmpny != argOperCmpny) {
		this.$('#operCmpny').val(argOperCmpny);
	}

};

<%
/**
 * @FUNCTION NAME : getGisPrtFcltyNm
 * @DESCRIPTION   : 시설물 명을 구한다.
 * @PARAMETER     :
 *   1. argFcltsMngGroupNoVariableName - 시설물 관리 번호 변수 명
 *   2. argGisPrtFcltyNmVariableName - 시설물 명 변수 명
**/
%>
GamMachFcltySpecMngModule.prototype.getGisPrtFcltyNm = function(argFcltsMngNoVariableName, argGisPrtFcltyNmVariableName) {

	var sGisPrtFcltyNm = "";
	if (argFcltsMngNoVariableName == null || argFcltsMngNoVariableName == "") {
		return sGisPrtFcltyNm;
	}
	var sFcltsMngNo = this.$(argFcltsMngNoVariableName).val();
	if (sFcltsMngNo.length == 15) {
		var searchVO = { 'sFcltsMngNo':sFcltsMngNo };
		this.doAction('/fclty/gamSelectMachFcltySpecMngGisPrtFcltyNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				sGisPrtFcltyNm = result.sGisPrtFcltyNm;
				if (argGisPrtFcltyNmVariableName != null && argGisPrtFcltyNmVariableName != "") {
					module.$(argGisPrtFcltyNmVariableName).val(result.sGisPrtFcltyNm);
				}
			}
			return sGisPrtFcltyNm;
		});
	}
	if (argGisPrtFcltyNmVariableName != null && argGisPrtFcltyNmVariableName != "") {
		this.$(argGisPrtFcltyNmVariableName).val(sGisPrtFcltyNm);
	}
	return sGisPrtFcltyNm;

};

<%
/**
 * @FUNCTION NAME : changeDetailAreaForm
 * @DESCRIPTION   : 시설물 구분에 따른 폼 입력 영역 변경
 * @PARAMETER     :
 *   1. argMechFcltsSe - 시설물 구분
**/
%>
GamMachFcltySpecMngModule.prototype.changeDetailAreaForm = function(argMechFcltsSe) {

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
GamMachFcltySpecMngModule.prototype.onAtchFileDirTreeItemClick = function(itemId) {

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
GamMachFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value) {

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
				this.$('#mechFcltsSe').focus();
			}
			break;
		case 'popupSpecArchFcltsMngNo':
			if (msg == 'ok') {
				this.$('#archFcltsMngNo').val(value.fcltsMngNo);
				this.$('#archFcltsNm').val(value.prtFcltyNm);
				this.$('#rm').focus();
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
		case 'popupMachFcltySttus':
			if (msg == 'ok') {
				if (value.processType == "I") {
					this._statusmode = "modify";
					this._statusKeyValue1 = value.wharfNm;
					this._statusKeyValue2 = value.operCmpny;
				} else if (value.processType == "U") {
					this._statusmode = "modify";
					this._statusKeyValue1 = value.wharfNm;
					this._statusKeyValue2 = value.operCmpny;
				} else {
					this._statusmode = "query";
					this._statusKeyValue1 = "";
					this._statusKeyValue2 = "";
				}
				var searchOpt=this.makeFormArgs('#searchForm');
				this.$('#statusGrid').flexOptions({params:searchOpt}).flexReload();
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
GamMachFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {

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
	    case 'btnSpecCopyData':
	    	this.copyData();
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
		case 'popupSpecArchFcltsMngNo':
            var searchOpts = {
				'fcltsJobSe':"A"
            };
			this.doExecuteDialog(buttonId, "시설물 선택", '/popup/showFcltsMngNo.do', null, searchOpts);
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
		case 'btnFileAllSelect':
			this.selectAllFile();
			break;
		case 'btnFileUpload':
			this.uploadFile();
			break;
		case 'btnFileDownload':
			this.downloadMultiFile();
			break;
		case 'btnFileRemove':
			this.deleteMultiFileData();
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
GamMachFcltySpecMngModule.prototype.onSubmit = function() {

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
GamMachFcltySpecMngModule.prototype.loadData = function() {

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
GamMachFcltySpecMngModule.prototype.refreshData = function() {

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
GamMachFcltySpecMngModule.prototype.loadDetail = function(tabId) {

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
GamMachFcltySpecMngModule.prototype.selectData = function() {

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
 * @FUNCTION NAME : selectStatusData
 * @DESCRIPTION   : STATUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.selectStatusData = function() {

	if (this._statusmode == 'query') {
		var gridRowCount = this.$("#statusGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._statusmode != 'insert' && this._statusmode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	if (this._statusKeyValue1 == "" || this._statusKeyValue2 == "") {
		return;
	}
	var wharfNm = this._statusKeyValue1;
	var operCmpny = this._statusKeyValue2;
	this.$("#statusGrid").selectFilterRow([{col:"wharfNm", filter:wharfNm},
										   {col:"operCmpny", filter:operCmpny}]);
	this._statusmode = 'modify';

};

<%
/**
 * @FUNCTION NAME : selectFileData
 * @DESCRIPTION   : FILE DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.selectFileData = function() {

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
GamMachFcltySpecMngModule.prototype.getFcltsMngGroupNoNm = function() {

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
GamMachFcltySpecMngModule.prototype.getNewGisPrtFcltySeq = function() {

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
	this.doAction('/fclty/gamSelectMachFcltySpecMngMaxGisPrtFcltySeq.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#gisPrtFcltySeq').val(result.sMaxGisPrtFcltySeq);
			module.setFcltsMngNo();
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
GamMachFcltySpecMngModule.prototype.setFcltsMngNo = function() {

	var gisAssetsPrtAtCode = this.$('#gisAssetsPrtAtCode').val();
	var gisAssetsCd = this.$('#gisAssetsCd').val();
	var gisAssetsSubCd = this.$('#gisAssetsSubCd').val();
	var gisPrtFcltyCd = this.$('#gisPrtFcltyCd').val();
	var prtFcltySe = this.$('#prtFcltySe').val();
	var gisPrtFcltySeq = this.$('#gisPrtFcltySeq').val();
	var fcltsMngNo = this.$('#fcltsMngNo').val();
	if (gisAssetsPrtAtCode == "" || gisAssetsCd == "" || gisAssetsSubCd == "" || gisPrtFcltyCd == "" || prtFcltySe == "" || gisPrtFcltySeq == "") {
		this.$('#fcltsMngNo').val("");
	} else {
		var newFcltsMngNo = gisAssetsPrtAtCode + gisAssetsCd + gisAssetsSubCd + gisPrtFcltyCd + gisPrtFcltySeq + prtFcltySe;
		if (fcltsMngNo == "" || fcltsMngNo != newFcltsMngNo) {
			this.$('#fcltsMngNo').val(newFcltsMngNo);
		}
	}

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.firstData = function() {

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
GamMachFcltySpecMngModule.prototype.prevData = function() {

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
GamMachFcltySpecMngModule.prototype.nextData = function() {

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
GamMachFcltySpecMngModule.prototype.lastData = function() {

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
		this.enableDetailInputItem();
	}

};

<%
/**
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : DATA COPY (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.copyData = function() {

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
	var currentRowIndex = -1;
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		if (fcltsMngNo == row["fcltsMngNo"]) {
			currentRowIndex = i;
			break;
		}
	}
	if (currentRowIndex >= gridRowCount || currentRowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	this._mainmode = 'insert';
	this._mainKeyValue = "";
	this.makeFormValues('#detailForm', rows[currentRowIndex]);
	this.makeDivValues('#detailForm', rows[currentRowIndex]);
	this.enableDetailInputItem();
	this.$('#fcltsMngNo').val("");
	this.$('#prtFcltySeNm').val("기계시설");
	this.$('#prtFcltySe').val("M");
	this.$('#prtFcltyGisCd').val("");
	this.$('#laCrdnt').val("");
	this.$('#loCrdnt').val("");
	this.$('#lat').val("");
	this.$('#lng').val("");
	this.getNewGisPrtFcltySeq();
	this.$('#mechFcltsSe').focus();

};

<%
/**
 * @FUNCTION NAME : showMachFcltySttusPopup
 * @DESCRIPTION   : MACH FCLTY STTUS POPUP
 * @PARAMETER     :
 *   1. argProcessType - PROCESS TYPE
**/
%>
GamMachFcltySpecMngModule.prototype.showMachFcltySttusPopup = function(argProcessType) {

	var wharfNm = "";
	var operCmpny = "";
	var ccCount = "";
	var tcCount = "";
	var ytCount = "";
	var csCount = "";
	var rsCount = "";
	var thCount = "";
	var tcRtgcCount = "";
	var fcltsMngGroupNo = "";
	if (argProcessType == "I") {
		wharfNm = "부두명";
		operCmpny = "운영사";
	} else if (argProcessType == "U") {
		var selectRow = this.$('#statusGrid').selectedRows();
		if(selectRow.length <= 0) {
			alert('자료가 선택되지 않았습니다.');
			return;
		}
		var row = selectRow[0];
		wharfNm = row['wharfNm'];
		operCmpny = row['operCmpny'];
		ccCount = row['ccCount'];
		tcCount = row['tcCount'];
		ytCount = row['ytCount'];
		csCount = row['csCount'];
		rsCount = row['rsCount'];
		thCount = row['thCount'];
		tcRtgcCount = row['tcRtgcCount'];
		fcltsMngGroupNo = row['fcltsMngGroupNo'];
		if (wharfNm == "계") {
			operCmpny = "계";
		}
	} else {
		alert('처리구분이 부정확합니다.');
		return;
	}
    var searchOpts = {
		'processType':argProcessType,
		'wharfNm':wharfNm,
		'operCmpny':operCmpny,
		'ccCount':ccCount,
		'tcCount':tcCount,
		'ytCount':ytCount,
		'csCount':csCount,
		'rsCount':rsCount,
		'thCount':thCount,
		'tcRtgcCount':tcRtgcCount,
		'fcltsMngGroupNo':fcltsMngGroupNo
    };
	this.doExecuteDialog('popupMachFcltySttus', '기계 시설 현황', '/popup/showMachFcltySttusPopup.do', null, searchOpts);

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : DATA ADD (MAIN)
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.addData = function() {

	this.$('#fcltsMngNo').val("");
	this.$('#gisAssetsLocCd').val("");
	this.$('#gisAssetsLocNm').val("");
	this.$('#gisAssetsNm').val("");
	this.$('#prtFcltySeNm').val("기계시설");
	this.$('#gisAssetsPrtAtCode').val("");
	this.$('#gisAssetsCd').val("");
	this.$('#gisAssetsSubCd').val("");
	this.$('#gisAssetsPrtAtCodeNm').val("");
	this.$('#prtFcltySe').val("M");
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
	this.$('#mechFcltsClCd').val("");
	this.$('#mechFcltsSe').val("");
	this.$('#mechFcltsSeNm').val("");
	this.$('#cvlEngEqpmnNm').val("");
	this.$('#shipEqpmnNm').val("");
	this.$('#archEqpmnNm').val("");
	this.$('#eqpmnNo').val("");
	this.$('#cvlEngOperCmpny').val("");
	this.$('#archOperCmpny').val("");
	this.$('#cvlEngMfcCmpny').val("");
	this.$('#shipMfcCmpny').val("0");
	this.$('#cvlEngMfcAmt').val("0");
	this.$('#shipMfcAmt').val("0");
	this.$('#cvlEngInstlYrmt').val("");
	this.$('#shipInstlYrmt').val("");
	this.$('#archInstlYrmt').val("");
	this.$('#mfcChkUsr').val("");
	this.$('#outReach').val("0.00");
	this.$('#backReach').val("0.00");
	this.$('#refloatHt').val("0.00");
	this.$('#processAblty').val("");
	this.$('#driveWd').val("0.00");
	this.$('#railWd').val("0.00");
	this.$('#selfLoad').val("0.00");
	this.$('#wheelWght').val("");
	this.$('#eqpmnStndrd').val("");
	this.$('#rubberFender').val("0.00");
	this.$('#elctyMthd').val("");
	this.$('#elctyMthdInstlQy').val("");
	this.$('#capaTon').val("0.00");
	this.$('#contrUsr').val("");
	this.$('#contrAmt').val("0");
	this.$('#vntltnArcndtMthd').val("");
	this.$('#clngSrc').val("");
	this.$('#htngSrc').val("");
	this.$('#waterTank').val("0.00");
	this.$('#oilSaveTank').val("0.00");
	this.$('#spictankFmt').val("");
	this.$('#fenderStndrd').val("");
	this.$('#fenderInstlQy').val("");
	this.$('#linkBridgeStndrd').val("");
	this.$('#rateWght').val("");
	this.$('#fmt').val("");
	this.$('#stndrd').val("");
	this.$('#archFcltsMngNo').val("");
	this.$('#archFcltsNm').val("");
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
GamMachFcltySpecMngModule.prototype.saveData = function() {

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
	var mechFcltsSe = this.$('#mechFcltsSe').val();
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
	if (mechFcltsSe == "1") {
		var eqpmnNm = this.$('#cvlEngEqpmnNm').val();
		var instlYrmt = this.$('#cvlEngInstlYrmt').val();
		var mfcAmt = Number(this.$('#cvlEngMfcAmt').val().replace(/,/gi, ""));
		var outReach = Number(this.$('#outReach').val().replace(/,/gi, ""));
		var backReach = Number(this.$('#backReach').val().replace(/,/gi, ""));
		var refloatHt = Number(this.$('#refloatHt').val().replace(/,/gi, ""));
		var driveWd = Number(this.$('#driveWd').val().replace(/,/gi, ""));
		var railWd = Number(this.$('#railWd').val().replace(/,/gi, ""));
		var selfLoad = Number(this.$('#selfLoad').val().replace(/,/gi, ""));
		var mfcCmpny = this.$('#cvlEngMfcCmpny').val();
		var operCmpny = this.$('#cvlEngOperCmpny').val();
		if (eqpmnNm == "") {
			alert('장비 명이 부정확합니다.');
			this.$("#cvlEngEqpmnNm").focus();
			return;
		}
		if (this.isValidYearMonth(instlYrmt, false) == false) {
			alert('설치 년월이 부정확합니다.');
			this.$("#cvlEngInstlYrmt").focus();
			return;
		}
		if (this.isValidAmount(mfcAmt) == false) {
			alert('제작 금액이 부정확합니다.');
			this.$("#cvlEngMfcAmt").focus();
			return;
		}
		if (this.isValidNumber8P2(outReach) == false) {
			alert('아웃 리치가 부정확합니다.');
			this.$("#outReach").focus();
			return;
		}
		if (this.isValidNumber8P2(backReach) == false) {
			alert('백 리치가 부정확합니다.');
			this.$("#backReach").focus();
			return;
		}
		if (this.isValidNumber8P2(refloatHt) == false) {
			alert('인양 높이가 부정확합니다.');
			this.$("#refloatHt").focus();
			return;
		}
		if (this.isValidNumber8P2(driveWd) == false) {
			alert('주행 폭이 부정확합니다.');
			this.$("#driveWd").focus();
			return;
		}
		if (this.isValidNumber8P2(railWd) == false) {
			alert('레일 폭이 부정확합니다.');
			this.$("#railWd").focus();
			return;
		}
		if (this.isValidNumber8P2(selfLoad) == false) {
			alert('자중이 부정확합니다.');
			this.$("#selfLoad").focus();
			return;
		}
		this.setEqpmnNm(eqpmnNm);
		this.setInstlYrmt(instlYrmt);
		this.setMfcCmpny(mfcCmpny);
		this.setMfcAmt(mfcAmt);
		this.setOperCmpny(operCmpny);
	} else if (mechFcltsSe == "2") {
		var eqpmnNm = this.$('#shipEqpmnNm').val();
		var instlYrmt = this.$('#shipInstlYrmt').val();
		var mfcAmt = Number(this.$('#shipMfcAmt').val().replace(/,/gi, ""));
		var capaTon = Number(this.$('#capaTon').val().replace(/,/gi, ""));
		var fenderInstlQy = Number(this.$('#fenderInstlQy').val().replace(/,/gi, ""));
		var elctyMthdInstlQy = Number(this.$('#elctyMthdInstlQy').val().replace(/,/gi, ""));
		var mfcCmpny = this.$('#shipMfcCmpny').val();
		if (eqpmnNm == "") {
			alert('장비 명이 부정확합니다.');
			this.$("#shipEqpmnNm").focus();
			return;
		}
		if (this.isValidYearMonth(instlYrmt, false) == false) {
			alert('설치 년월이 부정확합니다.');
			this.$("#shipInstlYrmt").focus();
			return;
		}
		if (this.isValidAmount(mfcAmt) == false) {
			alert('제작 금액이 부정확합니다.');
			this.$("#shipMfcAmt").focus();
			return;
		}
		if (this.isValidNumber8P2(capaTon) == false) {
			alert('적재 톤수가 부정확합니다.');
			this.$("#capaTon").focus();
			return;
		}
		if (this.isValidNumber8P2(fenderInstlQy) == false) {
			alert('고무방충재 수량이 부정확합니다.');
			this.$("#fenderInstlQy").focus();
			return;
		}
		if (this.isValidNumber8P2(elctyMthdInstlQy) == false) {
			alert('전기방식 수량이 부정확합니다.');
			this.$("#elctyMthdInstlQy").focus();
			return;
		}
		this.setEqpmnNm(eqpmnNm);
		this.setInstlYrmt(instlYrmt);
		this.setMfcCmpny(mfcCmpny);
		this.setMfcAmt(mfcAmt);
	} else if (mechFcltsSe == "3") {
		var eqpmnNm = this.$('#archEqpmnNm').val();
		var instlYrmt = this.$('#archInstlYrmt').val();
		var contrAmt = Number(this.$('#contrAmt').val().replace(/,/gi, ""));
		var waterTank = Number(this.$('#waterTank').val().replace(/,/gi, ""));
		var oilSaveTank = Number(this.$('#oilSaveTank').val().replace(/,/gi, ""));
		var operCmpny = this.$('#archOperCmpny').val();
		if (eqpmnNm == "") {
			alert('장비 명이 부정확합니다.');
			this.$("#archEqpmnNm").focus();
			return;
		}
		if (this.isValidYearMonth(instlYrmt, false) == false) {
			alert('설치 년월이 부정확합니다.');
			this.$("#archInstlYrmt").focus();
			return;
		}
		if (this.isValidAmount(contrAmt) == false) {
			alert('도급 금액이 부정확합니다.');
			this.$("#contrAmt").focus();
			return;
		}
		if (this.isValidNumber8P2(waterTank) == false) {
			alert('물 탱크가 부정확합니다.');
			this.$("#waterTank").focus();
			return;
		}
		if (this.isValidNumber8P2(oilSaveTank) == false) {
			alert('유류 저장 탱크가 부정확합니다.');
			this.$("#oilSaveTank").focus();
			return;
		}
		this.setEqpmnNm(eqpmnNm);
		this.setInstlYrmt(instlYrmt);
		this.setOperCmpny(operCmpny);
	} else {
		alert('시설물 구분이 부정확합니다.');
		this.$("#mechFcltsSe").focus();
		return;
	}
	this.setFcltsMngNo();
	var inputVO = this.makeFormArgs("#detailForm");
	if (this._mainmode == "insert") {
		this._mainKeyValue = fcltsMngNo;
		this.doAction('/fclty/gamInsertMachFcltySpecMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/fclty/gamUpdateMachFcltySpecMng.do', inputVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.deleteData = function() {

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
		this.doAction('/fclty/gamDeleteMachFcltySpecMng.do', deleteVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.saveMap = function() {

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
GamMachFcltySpecMngModule.prototype.downloadExcel = function(buttonId) {

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
				this.$('#mainGrid').flexExcelDown('/fclty/gamExcelDownloadMachFcltySpecMng.do');
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
GamMachFcltySpecMngModule.prototype.displayAtchFileDirectory = function(argDirNo) {

	this.$("#atchFileDirTreeList").empty();
	var inputVO = this.makeFormArgs("#dirForm");
	this.doAction('/fclty/gamSelectMachFcltySpecMngAtchFileDirList.do', inputVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.refreshDirData = function(argDirNo) {

	if (argDirNo > 1) {
		this.$('#dirNo').val('' + argDirNo);
		var dirQueryOption = this.$('#dirQueryOption').val();
		var searchVO = this.getFormValues('#dirForm');
		this.doAction('/fclty/gamSelectMachFcltySpecMngAtchFileDirPk.do', searchVO, function(module, result){
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
 * @FUNCTION NAME : addAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY를 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.addAtchFileDirectory = function() {

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
	if (dirFcltsJobSe != "M") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (생성불가)');
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
		this.$('#dirFcltsJobSe').val("M");
		this.$('#dirNo').val("");
		var insertVO = this.makeFormArgs("#dirForm");
		this.$('#dirNm').val(dirNm);
		this.$('#dirPath').val(dirPath);
		this.$('#dirUpperNo').val(dirUpperNo);
		this.$('#depthSort').val("" + depthSort);
		this.$('#leafYn').val(leafYn);
		this.$('#dirFcltsJobSe').val(dirFcltsJobSe);
		this.$('#dirNo').val("" + dirNo);
		this.doAction('/fclty/gamInsertMachFcltySpecMngAtchFileDir.do', insertVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.renameAtchFileDirectory = function() {

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
	if (dirFcltsJobSe != "M") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (변경불가)');
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
		this.doAction('/fclty/gamUpdateMachFcltySpecMngAtchFileDir.do', updateVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.removeAtchFileDirectory = function() {

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
	if (dirFcltsJobSe != "M") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (삭제불가)');
		return;
	}
	if (confirm("[" + dirNm + "] 디렉토리를 삭제하시겠습니까?\r\n(하위 디렉토리 및 첨부 파일도 모두 삭제됩니다)")) {
		var deleteVO = this.makeFormArgs("#dirForm");
		this.doAction('/fclty/gamDeleteMachFcltySpecMngAtchFileDir.do', deleteVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.displayAtchFileList = function(argAtchFileDirNo) {

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
GamMachFcltySpecMngModule.prototype.refreshFileData = function(argAtchFileNo) {

	if (argAtchFileNo != null && argAtchFileNo != "") {
		this.$('#atchFileNo').val(argAtchFileNo);
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectMachFcltySpecMngFcltsAtchFilePk.do', searchVO, function(module, result){
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
		this.makeFormValues('#fileForm', {});
		this.makeDivValues('#fileForm', {});
		this.disableFileButtonItem();
	}

};

<%
/**
 * @FUNCTION NAME : selectAllFile
 * @DESCRIPTION   : ALL FILE SELECT
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.selectAllFile = function() {

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
GamMachFcltySpecMngModule.prototype.saveUploadFileData = function(argAtchFileFcltsMngNo, argAtchFileFcltsDirNo, argAtchFileFcltsJobSe, argAtchFileFcltsDataSe, argAtchFileFcltsMngSeq, argAtchFileNmLogic, argAtchFileNmPhysicl) {

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
	this.doAction('/fclty/gamInsertMachFcltySpecMngFcltsAtchFile.do', inputVO, function(module, result) {
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
GamMachFcltySpecMngModule.prototype.uploadFile = function() {

	var atchFileFcltsDirNo = Number(this.$('#dirNo').val());
	var atchFileFcltsMngNo = this.$('#fcltsMngNo').val();
	var atchFileFcltsDataSe = "D";
	var atchFileFcltsJobSe = "M";
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
GamMachFcltySpecMngModule.prototype.downloadFile = function() {

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
GamMachFcltySpecMngModule.prototype.downloadMultiFile = function() {

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
 * @FUNCTION NAME : deleteFileData
 * @DESCRIPTION   : FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.deleteFileData = function() {

	var atchFileDirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	var atchFileFcltsJobSe = this.$('#atchFileFcltsJobSe').val();
	if (atchFileNo == "") {
		alert('첨부 파일 번호가 부정확합니다.');
		return;
	}
	if (atchFileFcltsJobSe != "M") {
		alert('다른 시설담당자가 첨부한 파일입니다. (삭제불가)');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#fileForm");
		this.doAction('/fclty/gamDeleteMachFcltySpecMngFcltsAtchFile.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList(atchFileDirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteMultiFileData
 * @DESCRIPTION   : MULTI FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.deleteMultiFileData = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('다운로드할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	var atchFileDirNo = this.$('#dirNo').val();
	var atchFileNo = "";
	var atchFileFcltsJobSe = "";
	var atchFileNmLogic = "";
	var deleteDataCount = rows.length;
	var deleteAtchFileNoList = "";
	for (var i=0; i<deleteDataCount; i++) {
		var row = rows[i];
		atchFileNo = row["atchFileNo"];
		atchFileFcltsJobSe = row["atchFileFcltsJobSe"];
		atchFileNmLogic = row["atchFileNmLogic"];
		if (atchFileNo == "") {
			alert('[' + atchFileNmLogic + '] 첨부 파일 번호가 부정확합니다.');
			return;
		}
		if (atchFileFcltsJobSe != "M") {
			alert('[' + atchFileNmLogic + '] 다른 시설담당자가 첨부한 파일입니다. (삭제불가)');
			return;
		}
		if (deleteAtchFileNoList != "") {
			deleteAtchFileNoList = deleteAtchFileNoList + "," + atchFileNo;
		} else {
			deleteAtchFileNoList = atchFileNo;
		}
	}
	if (confirm("[" + deleteDataCount + "] 건의 첨부 파일 자료를 삭제하시겠습니까?")) {
		var deleteVO = {
				'deleteAtchFileNoList':deleteAtchFileNoList
		};
		this.doAction('/fclty/gamDeleteMachFcltySpecMngFcltsAtchFileMulti.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList(atchFileDirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : displayPreviewFile
 * @DESCRIPTION   : FILE PREVIEW DISPLAY
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.displayPreviewFile = function() {

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
GamMachFcltySpecMngModule.prototype.showFcltsAtchFileViewPopup = function() {

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
		'imageURL':imageURL
    };
	this.doExecuteDialog('popupFcltsAtchFileView', '시설물 첨부 파일 보기', '/popup/showFcltsAtchFileViewPopup.do', null, searchOpts);

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.enableListButtonItem = function() {

	if (this._mainmode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
		//this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
			//this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
			this.$('#btnShowMap').enable();
			this.$('#btnShowMap').removeClass('ui-state-disabled');
			//this.$('#btnEditMap').enable();
			//this.$('#btnEditMap').removeClass('ui-state-disabled');
			this.$('#btnSaveMap').disable({disableClass:"ui-state-disabled"});
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			this.$('#btnShowMap').disable({disableClass:"ui-state-disabled"});
			//this.$('#btnEditMap').disable({disableClass:"ui-state-disabled"});
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
GamMachFcltySpecMngModule.prototype.enableDetailInputItem = function() {

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
		this.$('#mechFcltsSe').enable();
		this.$('#cvlEngEqpmnNm').enable();
		this.$('#shipEqpmnNm').enable();
		this.$('#archEqpmnNm').enable();
		this.$('#eqpmnNo').enable();
		this.$('#cvlEngOperCmpny').enable();
		this.$('#archOperCmpny').enable();
		this.$('#cvlEngMfcCmpny').enable();
		this.$('#shipMfcCmpny').enable();
		this.$('#cvlEngMfcAmt').enable();
		this.$('#shipMfcAmt').enable();
		this.$('#cvlEngInstlYrmt').enable();
		this.$('#shipInstlYrmt').enable();
		this.$('#archInstlYrmt').enable();
		this.$('#mfcChkUsr').enable();
		this.$('#outReach').enable();
		this.$('#backReach').enable();
		this.$('#refloatHt').enable();
		this.$('#processAblty').enable();
		this.$('#driveWd').enable();
		this.$('#railWd').enable();
		this.$('#selfLoad').enable();
		this.$('#wheelWght').enable();
		this.$('#eqpmnStndrd').enable();
		this.$('#rubberFender').enable();
		this.$('#elctyMthd').enable();
		this.$('#elctyMthdInstlQy').enable();
		this.$('#capaTon').enable();
		this.$('#contrUsr').enable();
		this.$('#contrAmt').enable();
		this.$('#vntltnArcndtMthd').enable();
		this.$('#clngSrc').enable();
		this.$('#htngSrc').enable();
		this.$('#waterTank').enable();
		this.$('#oilSaveTank').enable();
		this.$('#spictankFmt').enable();
		this.$('#fenderStndrd').enable();
		this.$('#fenderInstlQy').enable();
		this.$('#linkBridgeStndrd').enable();
		this.$('#rateWght').enable();
		this.$('#fmt').enable();
		this.$('#stndrd').enable();
		this.$('#rm').enable();
		this.$('#loc').enable();
		this.$('#archFcltsMngNo').enable();
		this.$('#popupSpecGisAssetsCd').enable();
		this.$('#popupSpecGisAssetsCd').removeClass('ui-state-disabled');
		this.$('#popupSpecPrtFcltyMngEntrpsCd').enable();
		this.$('#popupSpecPrtFcltyMngEntrpsCd').removeClass('ui-state-disabled');
		this.$('#popupSpecFcltsMngGroupNo').enable();
		this.$('#popupSpecFcltsMngGroupNo').removeClass('ui-state-disabled');
		this.$('#popupSpecArchFcltsMngNo').enable();
		this.$('#popupSpecArchFcltsMngNo').removeClass('ui-state-disabled');
		this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecSave').enable();
		this.$('#btnSpecSave').removeClass('ui-state-disabled');
		this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecLastData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSpecCopyData').disable({disableClass:"ui-state-disabled"});
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
			this.$('#mechFcltsSe').disable();
			this.$('#cvlEngEqpmnNm').enable();
			this.$('#shipEqpmnNm').enable();
			this.$('#archEqpmnNm').enable();
			this.$('#eqpmnNo').enable();
			this.$('#cvlEngOperCmpny').enable();
			this.$('#archOperCmpny').enable();
			this.$('#cvlEngMfcCmpny').enable();
			this.$('#shipMfcCmpny').enable();
			this.$('#cvlEngMfcAmt').enable();
			this.$('#shipMfcAmt').enable();
			this.$('#cvlEngInstlYrmt').enable();
			this.$('#shipInstlYrmt').enable();
			this.$('#archInstlYrmt').enable();
			this.$('#mfcChkUsr').enable();
			this.$('#outReach').enable();
			this.$('#backReach').enable();
			this.$('#refloatHt').enable();
			this.$('#processAblty').enable();
			this.$('#driveWd').enable();
			this.$('#railWd').enable();
			this.$('#selfLoad').enable();
			this.$('#wheelWght').enable();
			this.$('#eqpmnStndrd').enable();
			this.$('#rubberFender').enable();
			this.$('#elctyMthd').enable();
			this.$('#elctyMthdInstlQy').enable();
			this.$('#capaTon').enable();
			this.$('#contrUsr').enable();
			this.$('#contrAmt').enable();
			this.$('#vntltnArcndtMthd').enable();
			this.$('#clngSrc').enable();
			this.$('#htngSrc').enable();
			this.$('#waterTank').enable();
			this.$('#oilSaveTank').enable();
			this.$('#spictankFmt').enable();
			this.$('#fenderStndrd').enable();
			this.$('#fenderInstlQy').enable();
			this.$('#linkBridgeStndrd').enable();
			this.$('#rateWght').enable();
			this.$('#fmt').enable();
			this.$('#stndrd').enable();
			this.$('#rm').enable();
			this.$('#loc').enable();
			this.$('#archFcltsMngNo').enable();
			this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecPrtFcltyMngEntrpsCd').enable();
			this.$('#popupSpecPrtFcltyMngEntrpsCd').removeClass('ui-state-disabled');
			this.$('#popupSpecFcltsMngGroupNo').enable();
			this.$('#popupSpecFcltsMngGroupNo').removeClass('ui-state-disabled');
			this.$('#popupSpecArchFcltsMngNo').enable();
			this.$('#popupSpecArchFcltsMngNo').removeClass('ui-state-disabled');
			this.$('#btnSpecInsert').enable();
			this.$('#btnSpecInsert').removeClass('ui-state-disabled');
			this.$('#btnSpecSave').enable();
			this.$('#btnSpecSave').removeClass('ui-state-disabled');
			this.$('#btnSpecRemove').enable();
			this.$('#btnSpecRemove').removeClass('ui-state-disabled');
			this.$('#btnSpecFirstData').enable();
			this.$('#btnSpecFirstData').removeClass('ui-state-disabled');
			this.$('#btnSpecPrevData').enable();
			this.$('#btnSpecPrevData').removeClass('ui-state-disabled');
			this.$('#btnSpecNextData').enable();
			this.$('#btnSpecNextData').removeClass('ui-state-disabled');
			this.$('#btnSpecLastData').enable();
			this.$('#btnSpecLastData').removeClass('ui-state-disabled');
			this.$('#btnSpecCopyData').enable();
			this.$('#btnSpecCopyData').removeClass('ui-state-disabled');
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
			this.$('#mechFcltsSe').disable();
			this.$('#cvlEngEqpmnNm').disable();
			this.$('#shipEqpmnNm').disable();
			this.$('#archEqpmnNm').disable();
			this.$('#eqpmnNo').disable();
			this.$('#cvlEngOperCmpny').disable();
			this.$('#archOperCmpny').disable();
			this.$('#cvlEngMfcCmpny').disable();
			this.$('#shipMfcCmpny').disable();
			this.$('#cvlEngMfcAmt').disable();
			this.$('#shipMfcAmt').disable();
			this.$('#cvlEngInstlYrmt').disable();
			this.$('#shipInstlYrmt').disable();
			this.$('#archInstlYrmt').disable();
			this.$('#mfcChkUsr').disable();
			this.$('#outReach').disable();
			this.$('#backReach').disable();
			this.$('#refloatHt').disable();
			this.$('#processAblty').disable();
			this.$('#driveWd').disable();
			this.$('#railWd').disable();
			this.$('#selfLoad').disable();
			this.$('#wheelWght').disable();
			this.$('#eqpmnStndrd').disable();
			this.$('#rubberFender').disable();
			this.$('#elctyMthd').disable();
			this.$('#elctyMthdInstlQy').disable();
			this.$('#capaTon').disable();
			this.$('#contrUsr').disable();
			this.$('#contrAmt').disable();
			this.$('#vntltnArcndtMthd').disable();
			this.$('#clngSrc').disable();
			this.$('#htngSrc').disable();
			this.$('#waterTank').disable();
			this.$('#oilSaveTank').disable();
			this.$('#spictankFmt').disable();
			this.$('#fenderStndrd').disable();
			this.$('#fenderInstlQy').disable();
			this.$('#linkBridgeStndrd').disable();
			this.$('#rateWght').disable();
			this.$('#fmt').disable();
			this.$('#stndrd').disable();
			this.$('#rm').disable();
			this.$('#loc').disable();
			this.$('#archFcltsMngNo').disable();
			this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecPrtFcltyMngEntrpsCd').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
			this.$('#popupSpecArchFcltsMngNo').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecFirstData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecPrevData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecNextData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecLastData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSpecCopyData').disable({disableClass:"ui-state-disabled"});
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
GamMachFcltySpecMngModule.prototype.disableDetailInputItem = function() {

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
	this.$('#mechFcltsSe').disable();
	this.$('#cvlEngEqpmnNm').disable();
	this.$('#shipEqpmnNm').disable();
	this.$('#archEqpmnNm').disable();
	this.$('#eqpmnNo').disable();
	this.$('#cvlEngOperCmpny').disable();
	this.$('#archOperCmpny').disable();
	this.$('#cvlEngMfcCmpny').disable();
	this.$('#shipMfcCmpny').disable();
	this.$('#cvlEngMfcAmt').disable();
	this.$('#shipMfcAmt').disable();
	this.$('#cvlEngInstlYrmt').disable();
	this.$('#shipInstlYrmt').disable();
	this.$('#archInstlYrmt').disable();
	this.$('#mfcChkUsr').disable();
	this.$('#outReach').disable();
	this.$('#backReach').disable();
	this.$('#refloatHt').disable();
	this.$('#processAblty').disable();
	this.$('#driveWd').disable();
	this.$('#railWd').disable();
	this.$('#selfLoad').disable();
	this.$('#wheelWght').disable();
	this.$('#eqpmnStndrd').disable();
	this.$('#rubberFender').disable();
	this.$('#elctyMthd').disable();
	this.$('#elctyMthdInstlQy').disable();
	this.$('#capaTon').disable();
	this.$('#contrUsr').disable();
	this.$('#contrAmt').disable();
	this.$('#vntltnArcndtMthd').disable();
	this.$('#clngSrc').disable();
	this.$('#htngSrc').disable();
	this.$('#waterTank').disable();
	this.$('#oilSaveTank').disable();
	this.$('#spictankFmt').disable();
	this.$('#fenderStndrd').disable();
	this.$('#linkBridgeStndrd').disable();
	this.$('#rateWght').disable();
	this.$('#fmt').disable();
	this.$('#stndrd').disable();
	this.$('#rm').disable();
	this.$('#loc').disable();
	this.$('#archFcltsMngNo').disable();
	this.$('#popupSpecGisAssetsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecPrtFcltyMngEntrpsCd').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecFcltsMngGroupNo').disable({disableClass:"ui-state-disabled"});
	this.$('#popupSpecArchFcltsMngNo').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecFirstData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecPrevData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecNextData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecLastData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSpecCopyData').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : enableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamMachFcltySpecMngModule.prototype.enableFileButtonItem = function() {

	var dirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	if (dirNo != "") {
		if (atchFileNo != "") {
			this.$('#btnFileAllSelect').enable();
			this.$('#btnFileAllSelect').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
			this.$('#btnFilePreview').enable();
			this.$('#btnFilePreview').removeClass('ui-state-disabled');
		} else {
			this.$('#btnFileAllSelect').enable();
			this.$('#btnFileAllSelect').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
			this.$('#btnFilePreview').enable();
			this.$('#btnFilePreview').removeClass('ui-state-disabled');
		}
	} else {
		this.$('#btnFileAllSelect').disable({disableClass:"ui-state-disabled"});
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
GamMachFcltySpecMngModule.prototype.disableFileButtonItem = function() {

	this.$('#btnFileAllSelect').disable({disableClass:"ui-state-disabled"});
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
GamMachFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamMachFcltySpecMngModule();

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
									<button id="btnAdd" class="buttonAdd">추가</button>
									<button id="btnDelete" class="buttonDelete">삭제</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
									<button id="btnShowMap" data-role="showMap" data-gis-layer="gisMechFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
									<button id="btnEditMap" data-role="editMap" data-gis-layer="gisMechFclty">맵편집</button>
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
									<input id="gisPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="" data-code-id="GAM067"/>
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
								<th style="width:10%; height:18px;">규격／스프레더</th>
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
								<th style="font-weight:bold; height:20px;">기계시설 제원</th>
								<td style="text-align:right;">
									<button id="btnSpecFirstData">처음 자료</button>
									<button id="btnSpecPrevData">이전 자료</button>
									<button id="btnSpecNextData">다음 자료</button>
									<button id="btnSpecLastData">마지막 자료</button>
									<button id="btnSpecInsert" class="buttonAdd">추가</button>
									<button id="btnSpecSave" class="buttonSave">저장</button>
									<button id="btnSpecRemove" class="buttonDelete">삭제</button>
									<button id="btnSpecCopyData" class="buttonAdd">복사 추가</button>
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
									<select id="mechFcltsSe">
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
									<input type="text" size="62" id="cvlEngEqpmnNm" maxlength="150"/>
								</td>
								<th style="width:10%; height:18px;">장　비　번　호</th>
								<td>
									<input type="text" size="62" id="eqpmnNo" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="cvlEngInstlYrmt" maxlength="7"/>
								</td>
								<th style="width:10%; height:18px;">운　　영　　사</th>
								<td>
									<input type="text" size="62" id="cvlEngOperCmpny" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제　　작　　사</th>
								<td>
									<input type="text" size="62" id="cvlEngMfcCmpny" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">제　　작　　비</th>
								<td>
									<input type="text" size="59" id="cvlEngMfcAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제조　　검사자</th>
								<td>
									<input type="text" size="62" id="mfcChkUsr" maxlength="20"/>
								</td>
								<th style="width:10%; height:18px;">아　웃　리　치</th>
								<td>
									<input type="text" size="59" id="outReach" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">백　　리　　치</th>
								<td>
									<input type="text" size="59" id="backReach" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
								<th style="width:10%; height:18px;">인　양　높　이</th>
								<td>
									<input type="text" size="59" id="refloatHt" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">처　리　능　력</th>
								<td>
									<input type="text" size="62" id="processAblty" maxlength="200"/>
								</td>
								<th style="width:10%; height:18px;">주　　행　　폭</th>
								<td>
									<input type="text" size="59" id="driveWd" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">레　　일　　폭</th>
								<td>
									<input type="text" size="59" id="railWd" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> m
								</td>
								<th style="width:10%; height:18px;">자　　　　　중</th>
								<td>
									<input type="text" size="59" id="selfLoad" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> 톤
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">윤　　하　　중</th>
								<td>
									<input type="text" size="62" id="wheelWght" maxlength="150"/>
								</td>
								<th style="width:10%; height:18px;">정　격　하　중</th>
								<td>
									<input type="text" size="62" id="rateWght" maxlength="200"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">T/C　　형 태</th>
								<td>
									<select id="fmt">
										<option value="">선택</option>
										<option value="RMQC">RMQC(Rail Mounted Quayside Crane)</option>
										<option value="RTGC">RTGC(Rubber-Tired Grantry Crane)</option>
										<option value="RMGC">RMGC(Rail-Mounted Grantry Crane)</option>
										<option value="OHBC">OHBC(Over Head Bridge Crane)</option>
									</select>
								</td>
								<th style="width:10%; height:18px;">T/C 장치작업</th>
								<td>
									<select id="stndrd">
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
									<input type="text" size="62" id="shipEqpmnNm" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="shipInstlYrmt" maxlength="7"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">제　　작　　사</th>
								<td>
									<input type="text" size="62" id="shipMfcCmpny" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">제　　작　　비</th>
								<td>
									<input type="text" size="59" id="shipMfcAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">규　　　　　격</th>
								<td colspan="3">
									<input type="text" size="149" id="eqpmnStndrd" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">연결도교　규격</th>
								<td>
									<input type="text" size="62" id="linkBridgeStndrd" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">적　재　톤　수</th>
								<td>
									<input type="text" size="59" id="capaTon" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> 톤
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">고무방충재규격</th>
								<td>
									<input type="text" size="62" id="fenderStndrd" maxlength="100"/>
								</td>
								<th style="width:10%; height:18px;">고무방충재수량</th>
								<td>
									<input type="text" size="59" id="fenderInstlQy" class="ygpaNumber" maxlength="10"/>EA
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">전　기　방　식</th>
								<td>
									<input type="text" size="62" id="elctyMthd" maxlength="50"/>
								</td>
								<th style="width:10%; height:18px;">전기방식　수량</th>
								<td>
									<input type="text" size="59" id="elctyMthdInstlQy" class="ygpaNumber" maxlength="10"/>EA
								</td>
							</tr>
						</table>
						<table id="archDetailArea" class="detailPanel" style="width:100%; display:none;">
							<tr>
								<th style="width:10%; height:18px;">건　　물　　명</th>
								<td>
									<input type="text" size="62" id="archEqpmnNm" maxlength="150"/>
								</td>
								<th style="width:10%; height:18px;">운　　영　　사</th>
								<td>
									<input type="text" size="62" id="archOperCmpny" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">설　치　년　월</th>
								<td>
									<input type="text" size="62" id="archInstlYrmt" maxlength="7"/>
								</td>
								<th style="width:10%; height:18px;">도　　급　　자</th>
								<td>
									<input type="text" size="62" id="contrUsr" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">도　　급　　액</th>
								<td>
									<input type="text" size="59" id="contrAmt" class="ygpaNumber" maxlength="20"/> 원
								</td>
								<th style="width:10%; height:18px;">환기　공조방식</th>
								<td>
									<input type="text" size="62" id="vntltnArcndtMthd" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">냉　방　열　원</th>
								<td>
									<input type="text" size="62" id="clngSrc" maxlength="25"/>
								</td>
								<th style="width:10%; height:18px;">난　방　열　원</th>
								<td>
									<input type="text" size="62" id="htngSrc" maxlength="25"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">물　　탱　　크</th>
								<td>
									<input type="text" size="59" id="waterTank" class="ygpaNumber" data-decimal-point="2" maxlength="10"/> 톤
								</td>
								<th style="width:10%; height:18px;">유류　저장탱크</th>
								<td>
									<input type="text" size="59" id="oilSaveTank" class="ygpaNumber" data-decimal-point="2"/> ℓ
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">정화조　　형식</th>
								<td colspan="3">
									<input type="text" size="62" id="spictankFmt" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:18px;">해당　건축시설</th>
								<td colspan="3">
									<input type="text" size="25" id="archFcltsMngNo" maxlength="20"/> -
									<input type="text" size="108" id="archFcltsNm" disabled/>
									<button id="popupSpecArchFcltsMngNo" class="popupButton">선택</button>
								</td>
							</tr>
						</table>
						<table id="rmDetailArea" class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18px;">비　　　　　고</th>
								<td colspan="3">
									<input type="text" size="149" id="rm" maxlength="1000"/>
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
								<input id="inputDirNm" type="text" size="50" maxlength="100"/>
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
							<button id="btnDirAdd" class="buttonAdd">디렉토리 생성</button>
							<button id="btnDirRename" class="buttonSave">디렉토리명 변경</button>
							<button id="btnDirRemove" class="buttonDelete">디렉토리 삭제</button>
						</td>
						<td style="width:50%; text-align:right;">
							<button id="btnFileAllSelect">전체 선택</button>
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
