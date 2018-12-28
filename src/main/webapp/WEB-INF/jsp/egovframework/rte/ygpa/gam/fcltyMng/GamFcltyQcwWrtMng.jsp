<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMng.jsp
  * @Description : 시설 점검 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.24  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<%
/******************************** SCRIPT START ********************************/
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyQcwWrtMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyQcwWrtMngModule() {
}

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,710);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadComplete = function(params) {
	this._mainmode = '';

	this._qcResultList = null;
	this._qcresultmode = '';

	this.$('#mainGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngDtlsList.do',
		dataType: 'json',
		colModel : [
					{display:'선택<div id="'+this.getId('title_chkRole')+'" style="padding-right:3px"></div>',name:'chkRole', width:40, sortable:false, align:'center', displayFormat: 'checkbox', skipxls: true},
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"left"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:150,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
					{display:"작성일자",		name:"wrtDt",				width:120,		sortable:true,		align:"center"}
			],
		height: 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

	this.$('#mainGrid').on('onLoadDataComplete', function(event, module, data) {
		module.loadDataComplete();
	});

	this.$('#mainGrid').on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module._mainFcltsJobSe = row.fcltsJobSe;
		module._mainQcMngSeq = row.qcMngSeq;
		module.setControlStatus();
	});

	//메인그리드에서 선택 해더를 누를 때 발생하는 이벤트 처리 2016.01.22 김종민 추가
	this.$('#mainGrid')[0].dgrid.attachEvent('onHeaderClick', function(index, object) {
		var module = this.p.module;
		if(index == 0) {
			if(module._allMainListSelected == void(0)) {
				module._allMainListSelected = false;
			}
			module._allMainListSelected = (module._allMainListSelected) ? false : true;
			module.setSelectStatusAllMainList(module._allMainListSelected);
		}
	});

	this.$('#mainGrid').on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainFcltsMngGroupNo = row.fcltsMngGroupNo;
		module._mainFcltsJobSe = row.fcltsJobSe;
		module._mainQcMngSeq = row.qcMngSeq;
		module.setControlStatus();
		module.$('#mainTab').tabs('option', {active: 1});
	});

	this.$('#qcObjFcltsGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"선택",		name:"chkRole",		width:40,	sortable:false,	align:"center", displayFormat:"checkbox"},
					{display:"점검시설명",	name:"prtFcltyNm",	width:303,	sortable:false,	align:"left"}
			],
		height: '455',
		groupBy: 'gisPrtFcltyNm',
		preProcess : function(module,data) {
			$.each(data.resultList, function() {
				this.chkRole = this.chkRole === 'TRUE';
			});
			return data;
		}
	});

	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fcltyMng/gamSelectFcltyQcwWrtMngQcMngAtchFileList.do',
		dataType : 'json',
		colModel : [
					{display:"선택",		name:"atchFileSelChk",		width:40,		sortable:false,		align:"center",		displayFormat:"checkbox"},
					{display:"번호",		name:"atchFileSeq",			width:40,		sortable:false,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:60,		sortable:false,		align:"center"},
					{display:"파일명",		name:"atchFileNmLogic",		width:200,		sortable:false,		align:"left"},
					{display:"프리뷰",		name:"photoUrl",			width:200,		sortable:false,		align:"center",		displayFormat:"image"}
					],
		height : "430",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.atchFileSelChk = (this.atchFileSelChk === 'TRUE');
				this.photoUrl = "";
				var atchFileNmPhysicl = this.atchFileNmPhysicl;
				var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
				if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
					this.photoUrl = module.getUrl("/fcltyMng/getQcWrtAttachFile.do?physicalFileNm=")+atchFileNmPhysicl + "^" + this.atchFileNmLogic + "^" + "200";
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
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.enableFileButtonItem();
		module.showQcMngAtchFileViewPopup();
	});

	this.$('#sFcltsMngGroupNo').bind('click', {module: this}, function(event) {
		event.data.module.$('#sFcltsMngGroupNo').val('');
		event.data.module.$('#sFcltsMngGroupNm').val('');
	});

	this.$('#fcltsJobSe').bind('change', {module: this}, function(event) {
		event.data.module.loadQcSubDataList();
	});

	this.$('#mechFcltsSe').bind('change', {module: this}, function(event) {
		event.data.module.loadQcSubDataList();
	});

	this.$('#sQcInspSe').bind('change', {module: this}, function(event) {
		event.data.module.checkSearchQcInspSe();
	});

	this.$('#sQcSe').disable();

	this.$('#qcInspSe').bind('change', {module: this}, function(event) {
		event.data.module.checkQcInspSe();
	});
	this.$('#qcSe').disable();

	this.$('#mechFcltsSe').hide();

	this.setControlStatus();

	this.fillSelectBoxYear('#enforceYear');
	this.fillSelectBoxYear('#sEnforceYear');
	this.$('#sEnforceYear').val((new Date()).getFullYear());

	this._mainmode = "query";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainQcMngSeq = "";
	/*
	this._detailDisplay = 'fclts';
	this._atchFilePreview = false;
	this.$('#fileGrid')[0].dgrid.setColumnHidden(4, true);
	this.$('#fileGrid').hide();
	this.$('#btnFileUpload').hide();
	this.$('#btnFileDownload').hide();
	this.$('#btnFileRemove').hide();
	this.$('#btnFilePreview').hide();
	*/

	/* 2015-10-27 김종민 수정 Start*/
	this._detailDisplay = 'file';
	this._atchFilePreview = false;
	this.$('#fileGrid')[0].dgrid.setColumnHidden(4, true);
	this.$('#btnFileUpload').enable();
	this.$('#btnFileUpload').removeClass('ui-state-disabled');
	this.$('#btnFileDownload').enable();
	this.$('#btnFileDownload').removeClass('ui-state-disabled');
	this.$('#btnFileRemove').enable();
	this.$('#btnFileRemove').removeClass('ui-state-disabled');
	this.$('#btnFilePreview').enable();
	this.$('#btnFilePreview').removeClass('ui-state-disabled');
	this.$('#qcObjFcltsGrid').hide();
	/* 2015-10-27 김종민 수정 End */

	if (EMD.userinfo.mngFcltyCd != null && EMD.userinfo.mngFcltyCd != "*") {
		this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);
	}

	this.getMapInfoList(params);
};

<%
/**
 * @FUNCTION NAME : getMapInfoList
 * @DESCRIPTION   : 맵에서 유지보수 정보를 클릭할때 넘어오는 Param으로 리스트 가져오는 함수
 * @PARAMETER
 *		1. fcltsMngGroupNo   : 시설물 관리 그룹 코드
 *		2. fcltsMngGroupNoNm : 시설물 관리 그룹 코드명
**/
%>
GamFcltyQcwWrtMngModule.prototype.getMapInfoList = function(params){

	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
				case "manage":
					this.$('#sFcltsMngGroupNo').val(this._params.fcltsMngGroupNo);
					this.$('#sFcltsMngGroupNm').val(this._params.fcltsMngGroupNoNm);

					this.loadData();
				break;
			}
		}
	}

};

<%
/**
 * @FUNCTION NAME : setSelectStatusAllMainList
 * @DESCRIPTION   : ALL MAIN LIST SELECT STATUS 설정
 * @PARAMETER     :
 *   1. argStatusFlag - SELECT STATUS FLAG
**/
%>
GamFcltyQcwWrtMngModule.prototype.setSelectStatusAllMainList = function(argStatusFlag) {

	var rows = this.$('#mainGrid').flexGetData();
	var atchFileDataCount = rows.length;
	if (atchFileDataCount > 0) {
		for (var i=0; i<atchFileDataCount; i++) {
			var row = rows[i];
			row["chkRole"] = argStatusFlag;
			var rowid = this.$('#mainGrid')[0].dgrid.getRowId(i);
			this.$('#mainGrid').flexUpdateRow(rowid, row);
		}
	}

};

<%
/**
 * @FUNCTION NAME : fillSelectBoxYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : Select Element ID
**/
%>
GamFcltyQcwWrtMngModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=1980; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '년</option>');
	}
};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.firstData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainQcMngSeq == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		return;
	}
	var firstRowIndex = 0;
	var row = rows[firstRowIndex];
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	var qcMngSeq = row["qcMngSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && qcMngSeq != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe},
											 {col:"qcMngSeq", filter:qcMngSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainQcMngSeq = qcMngSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainQcMngSeq == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	var fcltsMngGroupNo = "";
	var fcltsJobSe = "";
	var qcMngSeq = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		fcltsMngGroupNo = row["fcltsMngGroupNo"];
		fcltsJobSe = row["fcltsJobSe"];
		qcMngSeq = row["qcMngSeq"];
		if (this._mainFcltsMngGroupNo == fcltsMngGroupNo && this._mainFcltsJobSe == fcltsJobSe && this._mainQcMngSeq == qcMngSeq) {
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
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	qcMngSeq = row["qcMngSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && qcMngSeq != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe},
											 {col:"qcMngSeq", filter:qcMngSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainQcMngSeq = qcMngSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainQcMngSeq == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	var fcltsMngGroupNo = "";
	var fcltsJobSe = "";
	var qcMngSeq = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		fcltsMngGroupNo = row["fcltsMngGroupNo"];
		fcltsJobSe = row["fcltsJobSe"];
		qcMngSeq = row["qcMngSeq"];
		if (this._mainFcltsMngGroupNo == fcltsMngGroupNo && this._mainFcltsJobSe == fcltsJobSe && this._mainQcMngSeq == qcMngSeq) {
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
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	qcMngSeq = row["qcMngSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && qcMngSeq != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe},
											 {col:"qcMngSeq", filter:qcMngSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainQcMngSeq = qcMngSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.lastData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainFcltsMngGroupNo == "" || this._mainFcltsJobSe == "" || this._mainQcMngSeq == "") {
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
	var fcltsMngGroupNo = row["fcltsMngGroupNo"];
	var fcltsJobSe = row["fcltsJobSe"];
	var qcMngSeq = row["qcMngSeq"];
	if (fcltsMngGroupNo != "" && fcltsJobSe != "" && qcMngSeq != "") {
		this.$("#mainGrid").selectFilterRow([{col:"fcltsMngGroupNo", filter:fcltsMngGroupNo},
											 {col:"fcltsJobSe", filter:fcltsJobSe},
											 {col:"qcMngSeq", filter:qcMngSeq}]);
		this._mainmode = 'modify';
		this._mainFcltsMngGroupNo = fcltsMngGroupNo;
		this._mainFcltsJobSe = fcltsJobSe;
		this._mainQcMngSeq = qcMngSeq;
		this.loadDetail();
	}

};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {

	this._mainmode = "query";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainQcMngSeq = "";
	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadData = function() {

	this.$('#mainTab').tabs('option', {active: 0});
	var searchOpt = this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDataComplete
 * @DESCRIPTION   : DATA LOAD COMPLETE(LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadDataComplete = function() {

	this._mainmode = 'listed';
	this.makeFormValues('#detailForm', {});
	this.setControlStatus();

};

<%
/**
 * @FUNCTION NAME : tableToExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.tableToExcel = function() {
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
	clone.find("img").remove();
	clone.find("tr:eq(0)").remove();
	clone.find("tr:eq(1)").remove();
	clone.find("td:eq(0)").remove();
	clone.find(".ev_dhx_skyblue").find("td:eq(0)").remove();
	clone.find(".odd_dhx_skyblue").find("td:eq(0)").remove();
	clone.find("td:eq(0)").css("width","200");
	clone.find("tr:eq(0) td").css({"font-size":"15px","font-weight":"bold","background-color":"#BDBDBD","height":"35px"});
	clone.find("td:eq(1)").css("width","100");
	clone.find("td:eq(2)").css("width","300");
	clone.find("td:eq(3)").css("width","100");
	clone.find("td:eq(4)").css("width","100");
	clone.find("td:eq(5)").css("width","100");
	clone.find("td:eq(6)").css("width","100");
	clone.find("td:eq(7)").css("width","100");
	clone.find("td:eq(8)").css("width","100");
	clone.find("td:eq(9)").css("width","150");
	clone.find("td:eq(10)").css("width","100");
	clone.find("td:eq(11)").css("width","100");
	clone.find("td:eq(12)").css("width","150");
	clone.find("td:eq(13)").css("width","100");
	clone.find("td:eq(14)").css("width","180");
	clone.table2excel({
		filename: "점검내역 목록",
	});
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadDetail = function() {

	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcMngDtlsDetail.do', opts, function(module, result) {
			if(result.resultCode == '0'){
				module.makeFormValues('#detailForm', result.detailData);
				module.checkQcInspSe();
				module.loadQcSubDataList();
				module.displayAtchFileList();
				module.setPrintUrl();
			//	module.setHwpUrl();
			}
			else {
				module._mainmode = 'listed';
				module.$('#fileGrid').flexEmptyData();
				module.setControlStatus();
				alert(result.resultMsg);
			}
		});
	} else {
		alert('조회할 데이터를 선택하세요.');
	}

};

<%
/**
 * @FUNCTION NAME : checkSearchQcInspSe
 * @DESCRIPTION   : 점검진단구분값에 따른 점검진단 enable/disable 설정(조회 조건)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.checkSearchQcInspSe = function() {
	var value = this.$('#sQcInspSe').val();
	if(value == '1' || value == '4' || value == '5') {
		this.$('#sQcSe').enable();
	}
	else {
		this.$('#sQcSe').val('');
		this.$('#sQcSe').disable();
	}
};

<%
/**
 * @FUNCTION NAME : checkQcInspSe
 * @DESCRIPTION   : 점검진단구분값에 따른 점검진단 enable/disable 설정
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.checkQcInspSe = function() {
	var value = this.$('#qcInspSe').val();
	if(value == '1' || value == '4' || value == '5') {
		this.$('#qcSe').enable();
	}
	else {
		this.$('#qcSe').val('');
		this.$('#qcSe').disable();
	}
};

<%
/**
 * @FUNCTION NAME : initBeforeInsert
 * @DESCRIPTION   : 추가작업 전 초기화
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.initBeforeInsert = function() {
	this._mainmode="insert";
	this._mainFcltsMngGroupNo = "";
	this._mainFcltsJobSe = "";
	this._mainQcMngSeq = "";
	this._qcResultList = null;
	this._qcresultmode = '';
	this.makeFormValues('#detailForm', {});
	this.$('#qcInspSe').val('');
	this.checkQcInspSe();
	this.$('#mechFcltsSe').val('1');
	this.$('#mechFcltsSe').hide();
	this.setControlStatus();
	this.$('#planHistSe').val('H');
	this.$('#enforceYear').val((new Date()).getFullYear());
	if (EMD.userinfo.mngFcltyCd != null && EMD.userinfo.mngFcltyCd != "*") {
		this.$('#fcltsJobSe').val(EMD.userinfo.mngFcltyCd);
	}
	this.$('#wrtDt').val(EMD.util.getDate());
	this.$('#wrtUsr').val(EMD.userinfo.name);
	this.loadQcSubDataList();
	this.$('#mainTab').tabs('option', {active: 1});
};

<%
/**
 * @FUNCTION NAME : setControlStatus
 * @DESCRIPTION   : 컨트롤 상태 변경
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.setControlStatus = function() {
	if(this._mainmode == 'insert') {
		this.$('#fcltsJobSe').enable();
		this.$('#popupDetailFcltsMngGroup').enable();
		this.$('#popupDetailFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailAdd').disable({disableClass:'ui-state-disabled'});
		//this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').enable();
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		if(this._qcresultmode == 'loaded') {
			this.$('#popupEditQcResultItem').enable();
			this.$('#popupEditQcResultItem').removeClass('ui-state-disabled');
		}
		else {
			this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		}
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
		this.$('#qcObjFcltsGrid').flexEmptyData();
		this.$('#fileGrid').flexEmptyData();
		this.disableFileButtonItem();
	}
	else if(this._mainmode == 'modify') {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDetailAdd').enable();
		this.$('#btnDetailAdd').removeClass('ui-state-disabled');
		this.$('#btnDelete').enable();
		//this.$('#btnDelete').removeClass('ui-state-disabled');
		this.$('#btnDetailDelete').enable();
		this.$('#btnDetailDelete').removeClass('ui-state-disabled');
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnPrint').enable();
		this.$('#btnPrint').removeClass('ui-state-disabled');
		if(this._qcresultmode == 'loaded') {
			this.$('#popupEditQcResultItem').enable();
			this.$('#popupEditQcResultItem').removeClass('ui-state-disabled');
		}
		else {
			this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		}
		this.$('#btnFirstData').enable();
		this.$('#btnFirstData').removeClass('ui-state-disabled');
		this.$('#btnPrevData').enable();
		this.$('#btnPrevData').removeClass('ui-state-disabled');
		this.$('#btnNextData').enable();
		this.$('#btnNextData').removeClass('ui-state-disabled');
		this.$('#btnLastData').enable();
		this.$('#btnLastData').removeClass('ui-state-disabled');
		this.enableFileButtonItem();
	}
	else if(this._mainmode == 'listed') {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDetailAdd').enable();
		this.$('#btnDetailAdd').removeClass('ui-state-disabled');
		//this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').enable();
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
		this.$('#qcObjFcltsGrid').flexEmptyData();
		this.$('#fileGrid').flexEmptyData();
		this.disableFileButtonItem();
	}
	else {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailAdd').disable({disableClass:'ui-state-disabled'});
		//this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').enable();
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
		this.$('#qcObjFcltsGrid').flexEmptyData();
		this.$('#fileGrid').flexEmptyData();
		this.disableFileButtonItem();
	}
};


<%
/**
 * @FUNCTION NAME : setPrintUrl
 * @DESCRIPTION   : 업무구분에 따른 인쇄 url 셋팅
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.setPrintUrl = function() {
	if(this.$('#fcltsJobSe').val() == 'A') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintA.do');
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintA.do');
	}
	if(this.$('#fcltsJobSe').val() == 'C') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintC.do');
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintC.do');
	}
	if(this.$('#fcltsJobSe').val() == 'E') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintE.do');
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintE.do');
	}
	if(this.$('#fcltsJobSe').val() == 'I') {
		this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintI.do');
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintI.do');
	}
	if(this.$('#fcltsJobSe').val() == 'M') {
		if(this.$('#mechFcltsSe').val() == "1"){
			this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintM1.do');
			this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintM1.do');
		}else{
			// 기계설비 점검표 인쇄
			this.$('#btnPrint').data('url','/fcltyMng/selectFcltyQcPrintM2.do');
			this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintM2.do');
		}
	}
};

<%
/**
 * @FUNCTION NAME : setHwpUrl
 * @DESCRIPTION   : 업무구분에 따른 HWP url 셋팅
 * @PARAMETER     : NONE
**/
%>

GamFcltyQcwWrtMngModule.prototype.setHwpUrl = function() {

	if(this.$('#fcltsJobSe').val() == 'A') {
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpA.do');
	}
	if(this.$('#fcltsJobSe').val() == 'C') {
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpC.do');
	}
	if(this.$('#fcltsJobSe').val() == 'E') {
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpE.do');
	}
	if(this.$('#fcltsJobSe').val() == 'I') {
		this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpI.do');
	}
	if(this.$('#fcltsJobSe').val() == 'M') {
		if(this.$('#mechFcltsSe').val() == "1"){
			this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpM1.do');
		}else{
			// 기계설비 점검표 인쇄
			this.$('#btnHwp').data('url','/fcltyMng/selectFcltyQcPrintHwpM2.do');
		}
	}
};

<%
/**
 * @FUNCTION NAME : validateDetailForm
 * @DESCRIPTION   : Detail Form Validate 체크
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.validateDetailForm = function() {
	if(this.$('#fcltsMngGroupNo').val() == '') {
		alert('관리그룹을 입력하세요.');
		return false;
	}
	if(this.$('#fcltsJobSe').val() == '') {
		alert('업무구분을 선택하세요.');
		this.$('#fcltsJobSe').focus();
		return false;
	}
	if(this.$('#qcMngNm').val() == '') {
		alert('점검관리명을 입력하세요.');
		this.$('#qcMngNm').focus();
		return false;
	}
	if((this._mainmode == 'modify') && (this.$('#qcMngSeq').val() == '')) {
		alert('잘못된 순번입니다.');
		return false;
	}
	return true;
};

<%
/**
 * @FUNCTION NAME : validateDuration
 * @DESCRIPTION   : 유효성 있는 기간 체크
 * @PARAMETER     :
	 1. startDate   : 시작일 문자열,
	 2. endDate     : 종료일 문자열,
	 3. startTitle  : 시작일 제목,
	 4. endTitle    : 종료일 제목,
	 5. startIgnore :
		 5-1. true  : 시작일 필수입력사항 미체크,
		 5-2. false : 시작일 필수입력사항 체크
	 6. endIgnore :
		 6-1. true  : 종료일 필수입력사항 미체크,
		 6-2. false : 종료일 필수입력사항 체크
	 7. equals      :
		 7-1. true  : 종료일이 시작일 보다 크거나 같으면 허용
		 7-2. false : 종료일이 시작일 보다 커야 허용
**/
%>
GamFcltyQcwWrtMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
	var result = false;
	if(((startDate == null) || (startDate == '')) && ((endDate == null) || (endDate == ''))) {
		return true;
	}
	if((endDate == null) || (endDate == '')) {
		if(!endIgnore) {
			alert(endTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
		result = true;
		if((startDate != null) && (startDate != '')) {
			result = EMD.util.isDate(startDate);
			if(!result) {
				alert(startTitle + '은(는) 날짜형식이 아닙니다.');
			}
		}
		return result;
	}
	if((startDate == null) || (startDate == '')) {
		if(startIgnore) {
			result = EMD.util.isDate(endDate);
			if(!result) {
				alert(endTitle + '은(는) 날짜형식이 아닙니다.');
			}
			return result;
		} else {
			alert(startTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
	}
	if(!EMD.util.isDate(startDate)) {
		alert(startTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	if(!EMD.util.isDate(endDate)) {
		alert(endTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	startDate = EMD.util.strToDate(startDate);
	endDate = EMD.util.strToDate(endDate);
	var compareResult = (startDate.getTime() > endDate.getTime()) ? -1 :
							(startDate.getTime() == endDate.getTime()) ? 0 : 1;
	result = (equals) ? (compareResult >= 0) : (compareResult > 0);
	if(!result) {
		alert(endTitle +'은(는) ' + startTitle + ((equals) ? '보다 같거나 커야합니다.' : '보다 커야합니다.'));
	}
	return result;
};

<%
/**
 * @FUNCTION NAME : getSaveData
 * @DESCRIPTION   : 저장할 데이터 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.getSaveData = function() {
	var result = [];
	var detailForm = this.makeFormArgs('#detailForm', 'object');
	var qcObjList = this.getQcObjList();
	var qcResultList = this.getQcResultItemList();

	result[result.length] = {name: 'detailForm', value :JSON.stringify(detailForm) };
	result[result.length] = {name: 'qcObjList', value :JSON.stringify(qcObjList)};
	result[result.length] = {name: 'qcResultList', value :JSON.stringify(qcResultList)};

	return result;
};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 점검관리 데이터 저장
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.saveData = function() {
	if(!this.validateDetailForm()){
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#qcInspDt').val(),
								'시행년도', '시행일자', true, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#qcInspDt').val(), this.$('#qcBeginDt').val(),
								'시행일자', '점검기간 시작일', true, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#qcBeginDt').val(), this.$('#qcEndDt').val(),
								'점검기간 시작일', '점검기간 종료일', false, false, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#qcBeginDt').val(),
								'시행년도', '점검진단 시작일', true, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#qcEndDt').val(),
								'시행년도', '점검진단 종료일', true, true, true)) {
		return;
	}

	var inputData = this.getSaveData();

	if(this._mainmode == 'insert') {
	 	this.doAction('/fcltyMng/insertQcMngDtls.do', inputData, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module.$('#qcMngSeq').val(result.qcMngSeq);
	 			module._mainmode = "modify";
	 			module._mainFcltsMngGroupNo = module.$("#fcltsMngGroupNo").val();
	 			module._mainFcltsJobSe = module.$("#fcltsJobSe").val();
	 			module._mainQcMngSeq = module.$("#qcMngSeq").val();
	 			module.setControlStatus();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
	else if(this._mainmode == 'modify')
	{
	 	this.doAction('/fcltyMng/updateQcMngDtls.do', inputData, function(module, result) {
	 		alert(result.resultMsg);
	 	});
	}
};


<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 선택된 점검 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.deleteData = function() {
	var filter = [{ 'col': 'chkRole', 'filter': true}];
	var deleteList = this.$("#mainGrid").selectFilterData(filter);

	if (deleteList.length <= 0) {
		alert('삭제할 항목을 선택 하십시요');
		return;
	}

	if(confirm('삭제하시겠습니까?')) {
		var param = {};
		param['deleteList'] = JSON.stringify(deleteList);
	 	this.doAction('/fcltyMng/deleteSelectedQcMngDtls.do', param, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module._mainmode = "query";
	 			module._mainFcltsMngGroupNo = "";
	 			module._mainFcltsJobSe = "";
	 			module._mainQcMngSeq = "";
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

<%
/**
 * @FUNCTION NAME : deleteDetailData
 * @DESCRIPTION   : 점검 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.deleteDetailData = function() {
	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length==0) {
		alert('선택된 항목이 없습니다.');
		return;
	}
	if(confirm('삭제하시겠습니까?')) {
		var deleteVO = rows[0];
	 	this.doAction('/fcltyMng/deleteQcMngDtls.do', deleteVO, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module._mainmode = "query";
	 			module._mainFcltsMngGroupNo = "";
	 			module._mainFcltsJobSe = "";
	 			module._mainQcMngSeq = "";
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

<%
/**
 * @FUNCTION NAME : loadQcSubDataList
 * @DESCRIPTION   : 점검관리결과항목과 대상물 리스트 로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadQcSubDataList = function() {
	if(this.$('#fcltsJobSe').val() == '' || this.$('#fcltsMngGroupNo').val() == '')
		return;

	var searchVO = [];

	if(this.$('#fcltsJobSe').val() == 'M') {
		if(this.$('#mechFcltsSe').val() == '1') {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M02' };
			this.$('#sMechCdStartChar').val('M02');
		} else if(this.$('#mechFcltsSe').val() == '2') {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M03' };
			this.$('#sMechCdStartChar').val('M03');
		} else {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M01' };
			this.$('#sMechCdStartChar').val('M01');
		}
		this.$('#mechFcltsSe').show();
		if(this._mainmode == 'insert') {
			this.$('#mechFcltsSe').enable();
		} else {
			this.$('#mechFcltsSe').disable({disableClass:'ui-state-disabled'});
		}
	} else {
		this.$('#mechFcltsSe').hide();
	}
	if(this._mainmode == 'insert') {
		searchVO[searchVO.length] = { name: 'sFcltsJobSe', value: this.$('#fcltsJobSe').val() };
		//searchVO[searchVO.length] = { name: 'sQcMngSeq', value: '' };
	}
	else {
		searchVO[searchVO.length] = { name: 'sFcltsJobSe', value: this.$('#fcltsJobSe').val() };
		searchVO[searchVO.length] = { name: 'sFcltsMngGroupNo', value: this.$('#fcltsMngGroupNo').val() };
		searchVO[searchVO.length] = { name: 'sQcMngSeq', value: this.$('#qcMngSeq').val() };
	}
	searchVO[searchVO.length] = { name: 'sGamCode', value: this.getGamCode() };

	this._qcResultList = null;
	this._qcresultmode = '';
	this.setControlStatus();
	if(this.$('#fcltsJobSe').val() != '') {
		this.doAction('/fcltyMng/selectQcMngResultItemList.do', searchVO, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module._qcResultList = result.resultList;
	 			module._qcresultmode = 'loaded';
	 			module.setControlStatus();
	 			if(module._mainmode == 'insert') {
	 				searchVO[searchVO.length] = { name: 'sFcltsMngGroupNo', value: module.$('#fcltsMngGroupNo').val() };
	 				//추가시 기계의 항만하역장비일 경우 없음을 디폴트값으로 넣는다.
	 				if((module.$('#fcltsJobSe').val() == 'M') && (module.$('#mechFcltsSe').val() == '1')) {
	 					for(var i=0; i<module._qcResultList.length; i++) {
	 						module._qcResultList[i].inspResultChk = 'E';
	 					}
	 				}
	 			}
	 			module.$('#qcObjFcltsGrid').flexOptions({params:searchVO}).flexReload();
	 		}
	 		else {
	 			alert(result.resultMsg);
	 		}
		});
	}
	else {
			if(this._mainmode == 'insert') {
				searchVO[searchVO.length] = { name: 'sFcltsMngGroupNo', value: this.$('#fcltsMngGroupNo').val() };
			}
		this.$('#qcObjFcltsGrid').flexOptions({params:searchVO}).flexReload();
	}

};

<%
/**
 * @FUNCTION NAME : getGamCode
 * @DESCRIPTION   : 업무구분에 따른 시설분류 대분류코드 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.getGamCode = function() {
	var result = '';
	switch(this.$('#fcltsJobSe').val()) {
	case 'A' : result = 'GAM066'; break;
	case 'C' : result = 'GAM070'; break;
	case 'E' : result = 'GAM068'; break;
	case 'I' : result = 'GAM069'; break;
	case 'M' : result = 'GAM067'; break;
	}
	return result;
};

<%
/**
 * @FUNCTION NAME : getQcObjList
 * @DESCRIPTION   : 점검관리 대상물 데이터 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.getQcObjList = function() {
	var resultList = [];
	var rows = this.$('#qcObjFcltsGrid').selectFilterData([{col: 'chkRole', filter: true}]);
	console.log('debug');
	if(rows.length > 0) {
		for(var i=0; i<rows.length; i++) {
			var row = rows[i];
			resultList[resultList.length] = {
				fcltsMngGroupNo : this.$('#fcltsMngGroupNo').val()
				, fcltsJobSe : this.$('#fcltsJobSe').val()
				, qcMngSeq : this.$('#qcMngSeq').val()
				, fcltsMngNo : row['fcltsMngNo']
			};
		}
	}
	return resultList;

};

<%
/**
 * @FUNCTION NAME : getQcResultItemList
 * @DESCRIPTION   : 점검관리 항목 데이터 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.getQcResultItemList = function() {
	var resultList = [];
	var rows = this._qcResultList;
	if(rows.length > 0) {
		for(var i=0; i<rows.length; i++) {
			var row = rows[i];
			resultList[resultList.length] = {
				fcltsMngGroupNo : this.$('#fcltsMngGroupNo').val()
				, fcltsJobSe : this.$('#fcltsJobSe').val()
				, qcMngSeq : this.$('#qcMngSeq').val()
				, qcItemCd : row['qcItemCd']
				, inspResultChk : row['inspResultChk']
				, inspResultCn : row['inspResultCn']
			};
		}
	}
	return resultList;
};

<%
/**
 * @FUNCTION NAME : enableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.enableFileButtonItem = function() {

	var atchFileDataCount = this.$('#fileGrid').flexRowCount();
	if (atchFileDataCount > 0) {
		this.$('#btnFileUpload').enable();
		this.$('#btnFileUpload').removeClass('ui-state-disabled');
		this.$('#btnFileDownload').enable();
		this.$('#btnFileDownload').removeClass('ui-state-disabled');
		this.$('#btnFileRemove').enable();
		this.$('#btnFileRemove').removeClass('ui-state-disabled');
		this.$('#btnFilePreview').enable();
		this.$('#btnFilePreview').removeClass('ui-state-disabled');
	} else {
		this.$('#btnFileUpload').enable();
		this.$('#btnFileUpload').removeClass('ui-state-disabled');
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
GamFcltyQcwWrtMngModule.prototype.disableFileButtonItem = function() {

	this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : displayAtchFileList
 * @DESCRIPTION   : 첨부 파일 목록을 보여준다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.displayAtchFileList = function() {

	var sFcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var sFcltsJobSe = this.$('#fcltsJobSe').val();
	var sQcMngSeq = this.$('#qcMngSeq').val();
	this.$('#fileGrid').flexEmptyData();
	if (sFcltsMngGroupNo != "" && sFcltsJobSe != "" && sQcMngSeq != "") {
		var searchVO = [
		           		{name: 'sFcltsMngGroupNo', value: sFcltsMngGroupNo },
		           		{name: 'sFcltsJobSe', value: sFcltsJobSe },
		           		{name: 'sQcMngSeq', value: sQcMngSeq }
			           ];
		this.$('#fileGrid').flexOptions({params:searchVO}).flexReload();
		this.enableFileButtonItem();
	} else {
		this.disableFileButtonItem();
	}

};

<%
/**
 * @FUNCTION NAME : displayPreviewFile
 * @DESCRIPTION   : FILE PREVIEW DISPLAY
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.displayPreviewFile = function() {

	var atchFilePreviewFlag = this.$('#fileGrid')[0].dgrid.isColumnHidden(4);
	if (atchFilePreviewFlag == true) {
		atchFilePreviewFlag = false;
	} else {
		atchFilePreviewFlag = true;
	}
	this.$('#fileGrid')[0].dgrid.setColumnHidden(3, !atchFilePreviewFlag);
	this.$('#fileGrid')[0].dgrid.setColumnHidden(4, atchFilePreviewFlag);
	this._atchFilePreview = atchFilePreviewFlag;
	this.displayAtchFileList();

};

<%
/**
 * @FUNCTION NAME : setSelectStatusAllAtchFile
 * @DESCRIPTION   : ALL ATTACH FILE SELECT STATUS 설정
 * @PARAMETER     :
 *   1. argStatusFlag - SELECT STATUS FLAG
**/
%>
GamFcltyQcwWrtMngModule.prototype.setSelectStatusAllAtchFile = function(argStatusFlag) {

	var rows = this.$('#fileGrid').flexGetData();
	var atchFileDataCount = rows.length;
	if (atchFileDataCount > 0) {
		for (var i=0; i<atchFileDataCount; i++) {
			var row = rows[i];
			row["atchFileSelChk"] = argStatusFlag;
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
 *   1. argFcltsMngGroupNo - FCLTS MNG GROUP NO.
 *   2. argFcltsJobSe - FCLTS JOB SE
 *   3. argQcMngSeq - QC MNG SEQ.
 *   4. argAtchFileNmLogic - ATTACHE FILE NAME LOGICAL.
 *   5. argAtchFileNmPhysicl - ATTACHE FILE NAME PHYSICAL
**/
%>
GamFcltyQcwWrtMngModule.prototype.saveUploadFileData = function(argFcltsMngGroupNo, argFcltsJobSe, argQcMngSeq, argAtchFileNmLogic, argAtchFileNmPhysicl) {

	var inputVO = [];
	var atchFileSe = "D";
	var atchFileSeNm = "문서";
	var atchFileWritngDt = "";
	var toDay = new Date();
	var year = "";
	var month = "";
	var day = "";
	year = toDay.getFullYear();
	month = toDay.getMonth() + 1;
	day = toDay.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			atchFileWritngDt = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			atchFileWritngDt = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			atchFileWritngDt = year + "-" + month + "-" + "0" + day;
		} else {
			atchFileWritngDt = year + "-" + month + "-" + day;
		}
	}
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
			'fcltsMngGroupNo':argFcltsMngGroupNo,
			'fcltsJobSe':argFcltsJobSe,
			'qcMngSeq':argQcMngSeq,
			'atchFileSeq':"",
			'atchFileSe':atchFileSe,
			'atchFileSj':"",
			'atchFileNmLogic':argAtchFileNmLogic,
			'atchFileNmPhysicl':argAtchFileNmPhysicl,
			'atchFileWritngDt':atchFileWritngDt,
			'atchFileRm':"",
			'regUsr':"",
			'registDt':"",
			'updUsr':"",
			'updtDt':""
	};
	this.doAction('/fcltyMng/gamInsertFcltyQcwWrtMngQcMngAtchFile.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$("#fileGrid").flexAddRow({ fcltsMngGroupNo: argFcltsMngGroupNo,
											   fcltsJobSe: argFcltsJobSe,
											   qcMngSeq: argQcMngSeq,
											   atchFileSeq:result.atchFileSeq,
											   atchFileSe:atchFileSe,
											   atchFileSeNm:atchFileSeNm,
											   atchFileSj:"",
											   atchFileNmLogic: argAtchFileNmLogic,
											   atchFileNmPhysicl: argAtchFileNmPhysicl,
											   atchFileWritngDt: atchFileWritngDt,
											   atchFileRm: ""
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
GamFcltyQcwWrtMngModule.prototype.uploadFile = function() {

	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var qcMngSeq = this.$('#qcMngSeq').val();
	if (fcltsMngGroupNo == "" || fcltsJobSe == "" || qcMngSeq == "") {
		alert('점검기록 정보가 부정확합니다.');
		return;
	}
	this.uploadMultiFile('/fcltyMng/uploadQcWrtAttachFile.do', function(module, resp) {
		if(resp.resultCode!=0) {
			alert(resp.resultMsg);
			return;
		}
		$.each(resp.result, function() {
			module.saveUploadFileData(fcltsMngGroupNo, fcltsJobSe, qcMngSeq, this.logicalFileNm, this.physcalFileNm);
		});
	});

};

<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadFile = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length > 0) {
		var row = selectRow[0];
		this.downloadSingleFile("/fcltyMng/downloadQcWrtAttachFile.do", row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : downloadMultiFile
 * @DESCRIPTION   : MULTI FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadMultiFile = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('다운로드할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	for (var i=0; i<rows.length; i++) {
		var row = rows[i];
		this.downloadSingleFile("/fcltyMng/downloadQcWrtAttachFile.do", row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : deleteFileData
 * @DESCRIPTION   : FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.deleteFileData = function() {

	var deleteVO = [];
	var fcltsMngGroupNo = "";
	var fcltsJobSe = "";
	var qcMngSeq = "";
	var atchFileSeq = "";
	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length <= 0) {
		alert('삭제할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	var row = selectRow[0];
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	qcMngSeq = row["qcMngSeq"];
	atchFileSeq = row["atchFileSeq"];
	if (fcltsMngGroupNo == "" || fcltsJobSe == "" || qcMngSeq == "" || atchFileSeq == "") {
		alert('삭제할 첨부 파일 정보가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		deleteVO = {
					'fcltsMngGroupNo' : fcltsMngGroupNo,
					'fcltsJobSe' : fcltsJobSe,
					'qcMngSeq' : qcMngSeq,
					'atchFileSeq' : atchFileSeq
		};
		this.doAction('/fcltyMng/gamDeleteFcltyQcwWrtMngQcMngAtchFile.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList();
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
GamFcltyQcwWrtMngModule.prototype.deleteMultiFileData = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('삭제할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var qcMngSeq = this.$('#qcMngSeq').val();
	if (fcltsMngGroupNo == "" || fcltsJobSe == "" || qcMngSeq == "") {
		alert('삭제할 첨부 파일 정보가 부정확합니다.');
		return;
	}
	var atchFileSeq = "";
	var atchFileNmLogic = "";
	var deleteDataCount = rows.length;
	var deleteAtchFileSeqList = "";
	for (var i=0; i<deleteDataCount; i++) {
		var row = rows[i];
		atchFileSeq = row["atchFileSeq"];
		atchFileNmLogic = row["atchFileNmLogic"];
		if (atchFileSeq == "") {
			alert('[' + atchFileNmLogic + '] 첨부 파일 번호가 부정확합니다.');
			return;
		}
		if (deleteAtchFileSeqList != "") {
			deleteAtchFileSeqList = deleteAtchFileSeqList + "," + atchFileSeq;
		} else {
			deleteAtchFileSeqList = atchFileSeq;
		}
	}
	if (confirm("[" + deleteDataCount + "] 건의 첨부 파일 자료를 삭제하시겠습니까?")) {
		var deleteVO = {
						'fcltsMngGroupNo' : fcltsMngGroupNo,
						'fcltsJobSe' : fcltsJobSe,
						'qcMngSeq' : qcMngSeq,
						'deleteAtchFileSeqList' : deleteAtchFileSeqList
		};
		this.doAction('/fcltyMng/gamDeleteFcltyQcwWrtMngQcMngAtchFileMulti.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : showFcltsAtchFileViewPopup
 * @DESCRIPTION   : QC MNG ATTACHE FILE VIEW POPUP
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.showQcMngAtchFileViewPopup = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length <= 0) {
		return;
	}
	var row = selectRow[0];
	fcltsMngGroupNo = row["fcltsMngGroupNo"];
	fcltsJobSe = row["fcltsJobSe"];
	qcMngSeq = row["qcMngSeq"];
	atchFileSeq = row["atchFileSeq"];
	if (fcltsMngGroupNo == "" || fcltsJobSe == "" || qcMngSeq == "" || atchFileSeq == "") {
		alert('조회할 첨부 파일 정보가 부정확합니다.');
		return;
	}
	var atchFileNmPhysicl = row['atchFileNmPhysicl'];
	var imageURL = "";
	if (atchFileNmPhysicl != "") {
		imageURL = this.getUrl("/fcltyMng/getQcWrtAttachFile.do?physicalFileNm=") + atchFileNmPhysicl;
	}
	var searchOpts = {
						'fcltsMngGroupNo' : fcltsMngGroupNo,
						'fcltsJobSe' : fcltsJobSe,
						'qcMngSeq' : qcMngSeq,
						'atchFileSeq' : atchFileSeq,
						'imageURL' : imageURL
	};
	this.doExecuteDialog('popupQcMngAtchFileView', '점검관리 첨부파일 보기', '/popup/showQcMngAtchFileViewPopup.do', null, searchOpts);

};

<%
/**
 * @FUNCTION NAME : showQcInspResult
 * @DESCRIPTION   : 점검항목에 따른 점검결과 자동 디스플레이 모듈
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.showQcInspResult = function() {
	var rows = this._qcResultList;
	var qcInspResultVal = '';
	if(rows.length > 0) {
		if(this.$("#fcltsJobSe").val() == 'C') {
			for(var i=0; i<rows.length; i++) {
				var row = rows[i];
				if((row['inspResultCn'] != null)) {
					if(row['inspResultCn'] != '') {
						qcInspResultVal += row['qcItemNm'] + ' : '
									+ row['inspResultCn'] + '\n';
					}
				}
			}
		}
		else {
			for(var i=0; i<rows.length; i++) {
				var row = rows[i];
				if((row['inspResultChk'] != 'N') && (row['inspResultChk'] != 'E')) {
					qcInspResultVal += row['qcItemNm'] + ' : '
									+ (row['inspResultChk'] == 'W' ? '요주의' :
										(row['inspResultChk'] == 'X' ? '불량' : '')) + '\n';
				}
			}
		}
		if(qcInspResultVal == '') qcInspResultVal = '이상없음';
		this.$('#qcInspResult').val(qcInspResultVal);
	}
	else {
		alert('점검결과항목 데이터가 존재하지 않습니다.');
		this.$('#qcInspResult').val('');
	}
};


<%
/**
 * @FUNCTION NAME : downloadSelectedSafetyQcResult
 * @DESCRIPTION   : 선택한 점검결과데이터 안전점검 한글문서 작성 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadSelectedSafetyQcResult = function() {
	var filter = [{ 'col': 'chkRole', 'filter': true}];
	var downList = this.$("#mainGrid").selectFilterData(filter);

	if (downList.length <= 0) {
		alert('다운로드할 항목을 선택 하십시요');
		return;
	}

	var url = '/fcltyMng/downloadSelectedSafetyQcResult.do';
	var param = {};
	param['downList'] = JSON.stringify(downList);
	param['filename'] = '안전점검결과리스트.hwp';

	this.doAction('/fcltyMng/selectFcltyQcMngReportListPictureCount.do', param, function(module, result) {
		if(result.result != '0') {
			$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
		} else {
			alert('사진파일이 없으므로 안전점검결과는 출력할수 없습니다.');
		}
	});
};

<%
/**
 * @FUNCTION NAME : downloadSelectedResultList
 * @DESCRIPTION   : 선택한 점검결과데이터 시설물점검표 한글문서 작성 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadSelectedResultList = function() {
	var filter = [{ 'col': 'chkRole', 'filter': true}];
	var downList = this.$("#mainGrid").selectFilterData(filter);

	if (downList.length <= 0) {
		alert('다운로드할 항목을 선택 하십시요');
		return;
	}

	var url = '/fcltyMng/downloadSelectedResultList.do';
	var param = {};
	param['downList'] = JSON.stringify(downList);
	param['filename'] = '시설물점검표리스트.hwp';

	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
};

<%
/**
 * @FUNCTION NAME : downloadSafetyQcResult
 * @DESCRIPTION   : 점검결과데이터 안전점검 한글문서 작성 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadSafetyQcResult = function() {
	var rowCount = this.$('#fileGrid').flexRowCount();
	if(rowCount > 0) {
		var url = '/fcltyMng/downloadSafetyQcResult.do';
		var param = this.makeFormArgs('#detailForm', 'object');
		param['filename'] = '안전점검결과.hwp';
		$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
	} else {
		alert('사진파일이 없으므로 안전점검결과는 출력할수 없습니다.');
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
GamFcltyQcwWrtMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch':
			this.loadData();
			break;

		case 'btnExcelDownload':
			this.tableToExcel();
			break;

		case 'btnAdd' :
 		case 'btnDetailAdd' :
			this.initBeforeInsert();
			break;

		case 'btnDelete' :
			this.deleteData();
			break;
		case 'btnDetailDelete' :
			this.deleteDetailData();
			break;

		case 'btnSave' :
			this.saveData();
			break;

		case 'popupEditQcResultItem' :
			this.doExecuteDialog(
									'editQcResultItem'
									, '점검표'
									, '/popup/showQcMngResultItemPopup.do'
									, {}
									, {
										'fcltsJobSe' : this.$('#fcltsJobSe').val()
										, 'fcltsJobSeNm' : this.$('#fcltsJobSe').find('option:selected').text()
										, 'fcltsMngGroupNm' : this.$('#fcltsMngGroupNm').val()
										, 'qcResultList' : this._qcResultList
										, 'popupMode' : 'edit'
										, 'mechFcltsSe' : this.$('#mechFcltsSe').val()
									}
								);
			break;

		case 'QcItemMng' :
			EMD.util.create_window('gamQcItemCdMng', '점검 항목 관리', '/code/gamQcItemCdMng.do', null, null);
			break;

		case 'popupDetailFcltsMngGroup':
			this.doExecuteDialog(
									'selectDetailFcltsMngGroup'
									, '관리그룹 선택'
									, '/popup/showFcltsMngGroup.do'
									, {}
								);
			break;

		case 'popupSearchFcltsMngGroup':
			this.popupSearchFcltsMngGroup();
			break;

		case 'btnAllSelect' :
			if (this._detailDisplay == 'fclts') {
				this.allSelectQcObj();
			} else if (this._detailDisplay == 'file') {
				this.setSelectStatusAllAtchFile(true);
			}
			break;

		// 선택데이터 안전점검 한글문서 다운로드
		case 'btnSelectedSafetyQcResultHwp':
			this.downloadSelectedSafetyQcResult();
			break;

		// 선택데이터 시설물점검표 한글문서 다운로드
		case 'btnSelectedResultListHwp':
			this.downloadSelectedResultList();
			break;

			// 안전점검 한글문서 다운로드
		case 'btnSafetyQcResultHwp':
			this.downloadSafetyQcResult();
			break;

		case 'btnAllUnSelect' :
			if (this._detailDisplay == 'fclts') {
				this.allUnSelectQcObj();
			} else if (this._detailDisplay == 'file') {
				this.setSelectStatusAllAtchFile(false);
			}
			break;

		case 'btnFcltsList':
			if (this._detailDisplay != 'fclts') {
				this._detailDisplay = 'fclts';
				this.$('#fileGrid').hide();
				this.$('#qcObjFcltsGrid').show();
				this.$('#btnFileUpload').hide();
				this.$('#btnFileDownload').hide();
				this.$('#btnFileRemove').hide();
				this.$('#btnFilePreview').hide();
			}
			break;
		case 'btnFileList':
			if (this._detailDisplay != 'file') {
				this._detailDisplay = 'file';
				this.$('#qcObjFcltsGrid').hide();
				this.$('#fileGrid').show();
				this.$('#btnFileUpload').show();
				this.$('#btnFileDownload').show();
				this.$('#btnFileRemove').show();
				this.$('#btnFilePreview').show();
			}
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
	}
};



GamFcltyQcwWrtMngModule.prototype.allSelectQcObj= function() {

	var rows = this.$('#qcObjFcltsGrid').flexGetData();
	var qcObjDataCount = rows.length;
	if (qcObjDataCount > 0) {
		for (var i=0; i<qcObjDataCount; i++) {
			var row = rows[i];
			row["chkRole"] = true;
			var rowid = this.$('#qcObjFcltsGrid')[0].dgrid.getRowId(i);
			this.$('#qcObjFcltsGrid').flexUpdateRow(rowid, row);
		}
	}

};

GamFcltyQcwWrtMngModule.prototype.allUnSelectQcObj= function() {

	var rows = this.$('#qcObjFcltsGrid').flexGetData();
	var qcObjDataCount = rows.length;
	if (qcObjDataCount > 0) {
		for (var i=0; i<qcObjDataCount; i++) {
			var row = rows[i];
			row["chkRole"] = false;
			var rowid = this.$('#qcObjFcltsGrid')[0].dgrid.getRowId(i);
			this.$('#qcObjFcltsGrid').flexUpdateRow(rowid, row);
		}
	}

};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. popupId  - POPUP ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/

%>
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'editQcResultItem':
			if(msg == 'ok' ) {
				this._qcResultList = value['resultList'];
				this.showQcInspResult();
			}
			break;
		case 'selectDetailFcltsMngGroup':
			this.$('#fcltsMngGroupNo').val(value['fcltsMngGroupNo']);
			this.$('#fcltsMngGroupNm').val(value['fcltsMngGroupNm']);
			this.loadQcSubDataList();
			break;
		case 'selectSearchFcltsMngGroup':
			this.$('#sFcltsMngGroupNo').val(value['fcltsMngGroupNo']);
			this.$('#sFcltsMngGroupNm').val(value['fcltsMngGroupNm']);
			break;
		case 'popupQcMngAtchFileView':
			if (msg == 'ok') {
				this.displayAtchFileList();
			}
			break;
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			break;
	}

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
GamFcltyQcwWrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if(this._mainmode == 'modify' && oldTabId == 'listTab') {
				this.loadDetail();
			}
			break;
	}

};


GamFcltyQcwWrtMngModule.prototype.popupSearchFcltsMngGroup= function() {
	var sFcltsJobSe = this.$('#sFcltsJobSe').val();
	console.log(sFcltsJobSe);
	switch(sFcltsJobSe){
	case 'A':
		this.doExecuteDialog('selectSearchFcltsMngGroup', "관리그룹 선택", '/popup/showArchFcltsMngGroup.do', null);
		break;
	case 'C':
		this.doExecuteDialog('selectSearchFcltsMngGroup', "관리그룹 선택", '/popup/showCvlFcltsMngGroup.do', null);
		break;
	case 'E':
		this.doExecuteDialog('selectSearchFcltsMngGroup', "관리그룹 선택", '/popup/showElectyFcltsMngGroup.do', null);
		break;
	case 'I':
		this.doExecuteDialog('selectSearchFcltsMngGroup', "관리그룹 선택", '/popup/showInfoCommFcltsMngGroup.do', null);
		break;
	case 'M':
		this.doExecuteDialog('selectSearchFcltsMngGroup', "관리그룹 선택", '/popup/showMachFcltsMngGroup.do', null);
		break;
	default :
		this.doExecuteDialog('selectSearchFcltsMngGroup', '관리그룹 선택', '/popup/showFcltsMngGroup.do', {});
		break;
	}
};

var module_instance = new GamFcltyQcwWrtMngModule();

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
							<th>시설물관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo"/>-
								<input type="text" size="17" id="sFcltsMngGroupNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th>업무구분</th>
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="">선택</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
								</select>
								<input id="sFcltsJobSeNm" type="hidden" />
							</td>
							<th>시행년도</th>
							<td>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</td>
							<td rowspan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>점검관리명</th>
							<td>
								<input type="text" id="sQcMngNm" size="50" />
							</td>
							<th>점검진단구분</th>
							<td>
								<select id="sQcInspSe">
		                        	<option value="">선택</option>
		                            <option value="1">정기점검</option>
		                            <option value="2">정밀점검</option>
		                            <option value="3">초기점검</option>
		                            <option value="4">긴급점검(손상)</option>
		                            <option value="5">긴급점검(특별)</option>
		                            <option value="6">정밀안전점검(정기)</option>
		                            <option value="7">정밀안전점검(긴급)</option>
		                            <option value="8">정밀안전점검(하자)</option>
		                            <option value="9">기타</option>
		                        </select>
							</td>
							<th>점검구분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">우기(풍수해)대비</option>
                                    <option value="3">동절기대비</option>
                                    <!-- <option value="4">우기대비</option> -->
                                </select>
                                <input id="sQcSeNm" type="hidden" />
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
				<li><a href="#listTab" class="emdTab">시설물 점검목록</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 점검내역</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">자료수</th>
								<td>
									<input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/>
								</td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
	                                <button data-role="printPage" data-search-option="searchForm" data-url="/fcltyMng/gamFcltyQcwWrtMngPrint.do">인쇄</button>
									<button id="btnSelectedResultListHwp" >선택 점검표 다운로드</button>
									<button id="btnSelectedSafetyQcResultHwp" >선택 안전점검결과 다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<!-- 2.1.3. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table class="summaryPanel" style="width:100%;">
					<tr>
						<th style="font-weight:bold; height:20px;">시설물 점검내역</th>
						<td style="text-align:right;">
							<button id="btnAllSelect">선택</button>
							<button id="btnAllUnSelect">해제</button>
							&nbsp;　　　&nbsp;
							&nbsp;　　　&nbsp;
							&nbsp;　　　&nbsp;
							&nbsp;　　　&nbsp;
							&nbsp;　　　&nbsp;
							&nbsp;　　　&nbsp;
						</td>
					</tr>
				</table>
				<!-- 2.1.3.1 Layout Table -->
				<table style="width:100%;" class="editForm">
					<tr>
						<td width="60%">
							<!-- 2.1.3.1.1 Element Table -->
							<form id="detailForm">
							<input type="hidden" id="sMechCdStartChar">
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<th style="width:14%; height:18px;">관 리　그 룹</th>
									<td colspan="3">
										<input type="text" size="20" id="fcltsMngGroupNo" data-required="true" disabled="disabled"/>-
										<input type="text" size="53" id="fcltsMngGroupNm" disabled="disabled"/>
										<button id="popupDetailFcltsMngGroup" class="popupButton">선택</button>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">업 무 구 분</th>
									<td width="40%">
		                                <input type="hidden" id="printSe"/>
		                                <input type="hidden" id="qcMngSeq"/>
										<select id="fcltsJobSe" data-required="true">
											<option value="">선택</option>
											<option value="A">건축시설</option>
											<option value="C">토목시설</option>
											<option value="E">전기시설</option>
											<option value="I">정보통신시설</option>
											<option value="M">기계시설</option>
		                                </select>
										<select id="planHistSe" disabled="disabled">
		                                    <option value="P">계획</option>
		                                    <option value="H" selected="selected">이력</option>
		                                </select>
									</td>
									<th style="width:14%; height:18px;">시　행　년　도</th>
									<td>
										<!-- 년도 자동 주입 -->
										<select id="enforceYear">
											<option value="">선택</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점검진단　예산</th>
									<td width="32%">
										<input type="text" size="21" id="qcInspBdgt" class="ygpaNumber"/> 원
									</td>
									<th style="width:14%; height:18px;">점검　관리　명</th>
									<td>
										<input type="text" size="35" id="qcMngNm" maxlength="70" data-required="true"/>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점　검　구　분</th>
									<td>
										<select id="qcSe">
		                                    <option value="">선택</option>
		                                    <option value="1">해빙기 대비　　　　　</option>
		                                    <option value="2">풍수해 대비　　　　　</option>
		                                    <option value="3">동절기 대비　　　　　</option>
		                                    <option value="4">우기 대비　　　　　　</option>
		                                </select>
									</td>
									<th style="width:14%; height:18px;">점검진단　구분</th>
									<td>
										<select id="qcInspSe">
		                                    <option value="">선택</option>
		                                    <option value="1">정기점검　　　　　　　　　　　　</option>
		                                    <option value="2">정밀점검　　　　　　　　　　　　</option>
		                                    <option value="3">초기점검　　　　　　　　　　　　</option>
		                                    <option value="4">긴급점검(손상)　　　　　　　　　</option>
		                                    <option value="5">긴급점검(특별)　　　　　　　　　</option>
		                                    <option value="6">정밀안전점검(정기)　　　　　　　</option>
		                                    <option value="7">정밀안전점검(긴급)　　　　　　　</option>
		                                    <option value="8">정밀안전점검(하자)　　　　　　　</option>
		                                    <option value="9">기타</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점검　진단　자</th>
									<td>
										<select id="qcInspTp">
		                                    <option value="">선택</option>
		                                    <option value="1">자체 점검　　　　　　</option>
		                                    <option value="2">용역 점검　　　　　　</option>
		                                </select>
									</td>
									<th style="width:14%; height:18px;">시　행　일　자</th>
									<td>
										<input type="text" class="emdcal" id="qcInspDt" size="32"/>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점검진단　금액</th>
									<td>
										<input type="text" size="21" id="qcInspAmt" class="ygpaNumber"/> 원
									</td>
									<th style="width:14%; height:18px;">점검진단기관명</th>
									<td>
										<input type="text" size="35" id="qcInspInsttNm" maxlength="70"/>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">책임　기술자명</th>
									<td>
										<input type="text" size="23" id="responEngineerNm" maxlength="20"/>
									</td>
									<th style="width:14%; height:18px;">점　검　기　간</th>
									<td>
										<input type="text" size="12" id="qcBeginDt" class="emdcal"/> ~
										<input type="text" size="12" id="qcEndDt" class="emdcal"/>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">작　성　일　자</th>
									<td>
										<input type="text" size="20" id="wrtDt" class="emdcal"/>
									</td>
									<th style="width:14%; height:18px;">작　　성　　자</th>
									<td>
										<input type="text" size="35" id="wrtUsr" maxlength="20"/>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">상태평가　등급</th>
									<td colspan="3">
										<select id="sttusEvlLvl">
		                                    <option value="">선택</option>
		                                    <option value="0">양호　　　　　　　　</option>
		                                    <option value="1">보통　　　　　　　　</option>
		                                    <option value="2">불량　　　　　　　　</option>
		                                    <option value="A">A 　　　　　　　　　</option>
		                                    <option value="B">B 　　　　　　　　　</option>
		                                    <option value="C">C 　　　　　　　　　</option>
		                                    <option value="D">D 　　　　　　　　　</option>
		                                    <option value="E">E 　　　　　　　　　</option>
		                                    <option value="Z">불명　　　　　　　　</option>
		                                </select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<select id="mechFcltsSe">
											<option value="1">하역장비</option>
											<option value="2">항만부잔교</option>
											<option value="3">건축기계설비</option>
										</select>
										<button id="popupEditQcResultItem">점검표</button>
										&nbsp;&nbsp;
										<button id="QcItemMng">점검표 관리</button>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점검진단　결과</th>
									<td colspan="3">
										<textarea id="qcInspResult" cols="87" rows="5"></textarea>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">점　검　내　용</th>
									<td colspan="3">
										<textarea id="actionCn" cols="87" rows="5"></textarea>
									</td>
								</tr>
								<tr>
									<th style="width:14%; height:18px;">비　　　　　고</th>
									<td colspan="3">
										<textarea id="rm" cols="87" rows="4"></textarea>
									</td>
								</tr>
							</table>
							</form>
						</td>
						<td width="40%">
							<!-- 2.1.3.1.2 Grid Table -->
							<table id="fcltsListArea" class="detailPanel" style="width:100%;">
								<tr>
									<td>
										<table id="qcObjFcltsGrid" style="display:none" class="fillHeight"></table>
									</td>
								</tr>
							</table>
							<table id="fileListArea" class="detailPanel" style="width:100%;">
								<tr>
									<td>
										<table id="fileGrid" style="display:none" class="fillHeight"></table>
									</td>
								</tr>
								<tr>
									<td style="text-align:right">
										<button id="btnFileUpload" class="buttonAdd">파일추가</button>
										<button id="btnFileDownload">파일다운로드</button>
										<button id="btnFileRemove" class="buttonDelete">파일삭제</button>
										<button id="btnFilePreview">미리보기</button>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table style="width:100%;">
					<tr>
						<td style="text-align:left;">
							<button id="btnFirstData">처음 자료</button>
							<button id="btnPrevData">이전 자료</button>
							<button id="btnNextData">다음 자료</button>
							<button id="btnLastData">마지막 자료</button>
						</td>
						<td style="text-align:right">
							<button id="btnDetailAdd" class="buttonAdd">　　추　가　　</button>
							<button id="btnDetailDelete" class="buttonDelete">　　삭　제　　</button>
							<button id="btnSave" class="buttonSave">　　저　장　　</button>
							<button id="btnResultListHwp" data-role="printDown" data-filename="시설물점검표.hwp" data-search-option="detailForm" data-url="/fcltyMng/downloadQcMngResultLIst.do">점검표 다운로드</button>
							<button id="btnSafetyQcResultHwp">안전점검결과 다운로드</button>
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
