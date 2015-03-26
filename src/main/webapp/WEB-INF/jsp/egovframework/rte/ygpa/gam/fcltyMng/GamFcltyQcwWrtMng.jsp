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

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,760);

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
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
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
		module.setControlStatus();
	});

	this.$('#mainGrid').on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module.setControlStatus();
		module.$('#mainTab').tabs('option', {active: 1});
	});

	this.$('#qcObjFcltsGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"선택",		name:"chkRole",		width:40,	sortable:false,	align:"center", displayFormat:"checkbox"},
					{display:"점검시설명",	name:"prtFcltyNm",	width:245,	sortable:false,	align:"left"},
					{display:"시설분류",	name:"gisPrtFcltyNm",	width:150,	sortable:false,	align:"left"},
			],
		height: '450',
		groupBy: 'gisPrtFcltyNm',
		preProcess : function(module,data) {
			$.each(data.resultList, function() {
				this.chkRole = this.chkRole === 'TRUE';
			});
			return data;
		}
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
	this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);

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
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {
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
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#mainGrid').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/excelDownloadQcMngDtlsList.do');
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
				module.fillAtchFileList(result.atchFileList);
				module.loadQcSubDataList();
				module.setPrintUrl();
			//	module.setHwpUrl();
			}
			else {
				module._mainmode = 'listed';
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
	this._mainmode = 'insert';
	this._qcResultList = null;
	this._qcresultmode = '';
	this.makeFormValues('#detailForm', {});
	this.$('#qcInspSe').val('');
	this.checkQcInspSe();
	this.$('#mechFcltsSe').val('1');
	this.setControlStatus();
	this.$('#planHistSe').val('H');
	this.$('#enforceYear').val((new Date()).getFullYear());
	this.$('#fcltsJobSe').val(EMD.userinfo.mngFcltyCd);
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
		this.$('#atchFile option').remove();
		this.$('#atchFile').append('<option value="">선택</option>');
		this.$('#fcltsJobSe').enable();
		this.$('#popupDetailFcltsMngGroup').enable();
		this.$('#popupDetailFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#btnUploadFile').enable();
		this.$('#btnUploadFile').removeClass('ui-state-disabled');
		this.$('#btnDownloadFile').enable();
		this.$('#btnDownloadFile').removeClass('ui-state-disabled');
		this.$('#btnRemoveFile').enable();
		this.$('#btnRemoveFile').removeClass('ui-state-disabled');
		this.$('#btnPreviewFile').enable();
		this.$('#btnPreviewFile').removeClass('ui-state-disabled');
		if(this._qcresultmode == 'loaded') {
			this.$('#popupEditQcResultItem').enable();
			this.$('#popupEditQcResultItem').removeClass('ui-state-disabled');
		}
		else {
			this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		}
		this.$('#qcObjFcltsGrid').flexEmptyData();
	}
	else if(this._mainmode == 'modify') {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDetailAdd').enable();
		this.$('#btnDetailAdd').removeClass('ui-state-disabled');
		this.$('#btnDelete').enable();
		this.$('#btnDelete').removeClass('ui-state-disabled');
		this.$('#btnDetailDelete').enable();
		this.$('#btnDetailDelete').removeClass('ui-state-disabled');
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnPrint').enable();
		this.$('#btnPrint').removeClass('ui-state-disabled');
		this.$('#btnUploadFile').enable();
		this.$('#btnUploadFile').removeClass('ui-state-disabled');
		this.$('#btnDownloadFile').enable();
		this.$('#btnDownloadFile').removeClass('ui-state-disabled');
		this.$('#btnRemoveFile').enable();
		this.$('#btnRemoveFile').removeClass('ui-state-disabled');
		this.$('#btnPreviewFile').enable();
		this.$('#btnPreviewFile').removeClass('ui-state-disabled');
		if(this._qcresultmode == 'loaded') {
			this.$('#popupEditQcResultItem').enable();
			this.$('#popupEditQcResultItem').removeClass('ui-state-disabled');
		}
		else {
			this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		}
	}
	else if(this._mainmode == 'listed') {
		this.$('#atchFile option').remove();
		this.$('#atchFile').append('<option value="">선택</option>');
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDetailAdd').enable();
		this.$('#btnDetailAdd').removeClass('ui-state-disabled');
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#btnUploadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDownloadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnRemoveFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPreviewFile').disable({disableClass:'ui-state-disabled'});
		this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		this.$('#qcObjFcltsGrid').flexEmptyData();
	}
	else {
		this.$('#atchFile option').remove();
		this.$('#atchFile').append('<option value="">선택</option>');
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPrint').disable({disableClass:'ui-state-disabled'});
		this.$('#btnUploadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDownloadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnRemoveFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnPreviewFile').disable({disableClass:'ui-state-disabled'});
		this.$('#popupEditQcResultItem').disable({disableClass:'ui-state-disabled'});
		this.$('#qcObjFcltsGrid').flexEmptyData();
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
		alert('업무구분을 입력하세요.');
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
	var atchFileList = this.getAtchFileList();

	result[result.length] = {name: 'detailForm', value :JSON.stringify(detailForm) };
	result[result.length] = {name: 'qcObjList', value :JSON.stringify(qcObjList)};
	result[result.length] = {name: 'qcResultList', value :JSON.stringify(qcResultList)};
	result[result.length] = {name: 'atchFileList', value :JSON.stringify(atchFileList)};

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
	 			module._mainmode = 'modify';
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
 * @DESCRIPTION   : 점검 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.deleteData = function() {
	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length==0) {
		alert('선택된 항목이 없습니다.');
		return;
	}
	if(confirm('삭제하시겠습니까?')) {
		var deleteVO = rows[0];
	 	this.doAction('/fcltyMng/deleteQcMngDtls.do', deleteVO, function(module, result) {
	 		if(result.resultCode == '0') {
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
		} else {
			searchVO[searchVO.length] = { name: 'sMechCdStartChar', value: 'M01' };
			this.$('#sMechCdStartChar').val('M01');
		}
		this.$('#mechFcltsSe').show();
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
 * @DESCRIPTION   : 점검관리 대상물 데이터 얻기
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
			};
		}
	}
	return resultList;
};


<%
/**
 * @FUNCTION NAME : getAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트 얻기
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.getAtchFileList = function() {
	var resultList = [];
	var len = this.$('#atchFile option').size();
	var logicalFileNm, physcalFileNm;
	for(var i=0; i<len; i++) {
		physcalFileNm = this.$('#atchFile option:eq(' + i + ')').val();
		logicalFileNm = this.$('#atchFile option:eq(' + i + ')').text();
		if(physcalFileNm != '') {
			resultList[resultList.length] = {
												fcltsMngGroupNo:this.$('#fcltsMngGroupNo').val()
												, fcltsJobSe:this.$('#fcltsJobSe').val()
												, qcMngSeq:this.$('#qcMngSeq').val()
												, atchFileSe:'P'
												, atchFileNmLogic:logicalFileNm
												, atchFileNmPhysicl: physcalFileNm
												, atchFileWritingDt:''
											};
		}
	}
	return resultList;
};

<%
/**
 * @FUNCTION NAME : fillAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트를 select element에 채워넣기.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.fillAtchFileList = function(atchFileList) {
	var module = this;
	this.$('#atchFile option').remove();
	this.$('#atchFile').append('<option value="">선택</option>');
	$.each(atchFileList, function(index){
		module.$('#atchFile').append(
				'<option value="' + atchFileList[index].atchFileNmPhysicl + '">'
				+ atchFileList[index].atchFileNmLogic + '</option>');
	});
};

<%
/**
 * @FUNCTION NAME : atchFileUpload
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.atchFileUpload = function() {
	console.log('upload');
	this.uploadMultiFile('/fcltyMng/uploadQcWrtAttachFile.do', function(module, resp) {
		if(resp.resultCode!=0) {
			alert(resp.resultMsg);
			return;
		}
		$.each(resp.result, function() {
            module.$('#atchFile').append(
					'<option value="' + this.physcalFileNm + '">'
					+ this.logicalFileNm + '</option>'); // 업로드 파일명이 physcalFileNm (물리명), logicalFileNm (논리명)으로 리턴 된다.
		});
		if(resp.result!=null && resp.result.length>0) this._edited=true;
	});
};

<%
/**
 * @FUNCTION NAME : atchFileDownload
 * @DESCRIPTION   : 첨부파일 다운로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.atchFileDownload = function() {
	if(this.$('#atchFile').val() != '') {
		this.downloadSingleFile("/fcltyMng/downloadQcWrtAttachFile.do", this.$('#atchFile').find('option:selected').val(), this.$('#atchFile').find('option:selected').text() );
	}
	else {
		alert('첨부파일을 선택해주십시오.');
	}
};

<%
/**
 * @FUNCTION NAME : atchFileRemove
 * @DESCRIPTION   : 첨부파일제거
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.atchFileRemove = function() {
	if(this.$('#atchFile').val() != '') {
		this.$('#atchFile option[value="' + this.$('#atchFile').val() + '"]').remove();
	}
	else {
		alert('첨부파일을 선택해주십시오.');
	}
};

<%
/**
* @FUNCTION NAME : showPreviewImage
* @DESCRIPTION : 첨부파일 미리보기 대화상자
* @PARAMETER : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.showPreviewImage = function(fileName){
	if(fileName != '') {
		var ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
		var imgURL='';
		if(ext == 'jpg' || ext == 'jpeg' || ext == 'bmp' || ext == 'png' || ext == 'gif')
		{
			this.$('#previewDialogArea').append(
				'<div id="' + this.getId('previewDialog') + '">' +
				'<img id="' + this.getId('previewImage') + '" src=""/>' +
				'</div>');
			imgURL = this.getUrl("/fcltyMng/getQcWrtAttachFile.do?physicalFileNm=")+fileName;

			this.$("#previewImage").attr('src', imgURL);
			this.$("#previewImage").bind('load', {module: this}, function(event) {
				event.data.module.$('#previewDialog').dialog({
					modal: true,
					maxWidth: 800,
					maxHeight: 600,
					resizable: false,
			 		draggable: true,
			 		width: 'auto',
			 		title: '이미지미리보기',
			 		buttons:[{text:"close", click: function() {
			 						$(this).dialog('close');
			 					}
			 				}]
			 	});
			});
		}
		else {
			alert('이미지 파일이 아닙니다.');
		}
	}
	else {
		alert('첨부파일을 선택하십시오.');
	}
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
		for(var i=0; i<rows.length; i++) {
			var row = rows[i];
			if(row['inspResultChk'] != 'N') {
				qcInspResultVal += row['qcItemNm'] + ' : '
								+ (row['inspResultChk'] == 'W' ? '요주의' :
									(row['inspResultChk'] == 'X' ? '불량' : '')) + '\n';
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
			this.downloadExcel();
			break;

		case 'btnAdd' :
 		case 'btnDetailAdd' :
			this.initBeforeInsert();
			break;

		case 'btnDelete' :
		case 'btnDetailDelete' :
			this.deleteData();
			break;

		case 'btnSave' :
			this.saveData();
			break;

		case 'btnUploadFile' :
			this.atchFileUpload();
			break;

		case 'btnDownloadFile' :
			this.atchFileDownload();
			break;

		case 'btnRemoveFile' :
			this.atchFileRemove();
			break;

		case 'btnPreviewFile' :
			this.showPreviewImage(this.$('#atchFile').val());
			break;

		case 'popupEditQcResultItem' :
			this.doExecuteDialog(
									'editQcResultItem'
									, '점검결과항목 편집'
									, '/popup/showQcMngResultItemPopup.do'
									, {}
									, {
										'fcltsJobSeNm' : this.$('#fcltsJobSe').find('option:selected').text()
										, 'fcltsMngGroupNm' : this.$('#fcltsMngGroupNm').val()
										, 'qcResultList' : this._qcResultList
										, 'popupMode' : 'edit'
									}
								);
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
			this.doExecuteDialog(
									'selectSearchFcltsMngGroup'
									, '관리그룹 선택'
									, '/popup/showFcltsMngGroup.do'
									, {}
								);
			break;

		case 'btnAllSelect' :

			this.allSelectQcObj();
			break;

		case 'btnAllUnSelect' :

			this.allUnSelectQcObj();
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
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		case 'editQcResultItem':
			this._qcResultList = null;
			this._qcResultList = value['resultList'];
			this.showQcInspResult();
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

var module_instance = new GamFcltyQcwWrtMngModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 1. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>관　리　그　룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo"/>-
								<input type="text" size="17" id="sFcltsMngGroupNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th>업　무　구　분</th>
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
							<td rowspan="3"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th height="17">점검　진단　구분</th>
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
							<th>점　검　구　분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
                                <input id="sQcSeNm" type="hidden" />
							</td>
						</tr>
						<tr>
							<th>시　행　년　도</th>
							<td>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</td>
							<th>점검　관리　명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 2.1. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 2.1.1. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">시설물점검목록</a></li>
				<li><a href="#detailTab" class="emdTab">시설물점검내역</a></li>
			</ul>

			<!-- 2.1.2. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<!-- 2.1.3. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow: hidden;">
				<!-- 2.1.3.1 Layout Table -->
				<table style="width:100%;" class="editForm">
					<tr>
						<td width="70%">
							<!-- 2.1.3.1.1 Element Table -->
							<form id="detailForm">
							<input type="hidden" id="sMechCdStartChar">
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<th width="14%">관　리　그　룹</th>
									<td colspan="3">
										<input type="hidden" id="fcltsMngGroupNo"/>
										<input type="text" size="40" id="fcltsMngGroupNm" disabled="disabled"/>
										<button id="popupDetailFcltsMngGroup" class="popupButton">선택</button>
									</td>
								</tr>
								<tr>
									<th width="14%" height="17">업　무　구　분</th>
									<td width="40%">
										<select id="fcltsJobSe">
											<option value="">선택</option>
											<option value="A">건축시설</option>
											<option value="C">토목시설</option>
											<option value="E">전기시설</option>
											<option value="I">정보통신시설</option>
											<option value="M">기계시설</option>
		                                </select>
		                                <input type="hidden" id="printSe"/>
		                                <input type="hidden" id="qcMngSeq"/>
									</td>
									<th width="14%" height="17">점검　진단　예산</th>
									<td width="32%"><input id="qcInspBdgt" type="text" size="20" class="ygpaNumber"/> 원</td>
								</tr>
								<tr>
									<th height="17">점검　관리　명</th>
									<td>
										<input type="text" size="35" id="qcMngNm" maxlength="66" />
									</td>
									<th height="17">계획　이력　구분</th>
									<td>
										<select id="planHistSe" disabled="disabled">
		                                    <option value="P">계획</option>
		                                    <option value="H" selected="selected">이력</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">점검　진단　구분</th>
									<td>
										<select id="qcInspSe">
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
									<th height="17">점　검　구　분</th>
									<td>
										<select id="qcSe">
		                                    <option value="">선택</option>
		                                    <option value="1">해빙기대비</option>
		                                    <option value="2">풍수해대비</option>
		                                    <option value="3">동절기대비</option>
		                                    <option value="4">우기대비</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">시　행　년　도</th>
									<td>
										<!-- 년도 자동 주입 -->
										<select id="enforceYear">
											<option value="">선택</option>
		                                </select>
									</td>
									<th height="17">점검　진단　자</th>
									<td>
										<select id="qcInspTp">
		                                    <option value="">선택</option>
		                                    <option value="1">자체점검</option>
		                                    <option value="2">용역점검</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">시　행　일　자</th>
									<td><input id="qcInspDt" type="text" class="emdcal" size="12"/></td>
									<th height="17">점검　진단　금액</th>
									<td><input id="qcInspAmt" type="text" size="20" class="ygpaNumber"/> 원</td>
								</tr>
								<tr>
									<th height="17">상태　평가　등급</th>
									<td>
										<select id="sttusEvlLvl">
		                                    <option value="">선택</option>
		                                    <option value="0">양호</option>
		                                    <option value="1">보통</option>
		                                    <option value="2">불량</option>
		                                    <option value="A">A</option>
		                                    <option value="B">B</option>
		                                    <option value="C">C</option>
		                                    <option value="D">D</option>
		                                    <option value="E">E</option>
		                                    <option value="Z">불명</option>
		                                </select>
									</td>
									<th height="17">점검　진단　기관명</th>
									<td><input type="text" size="20" id="qcInspInsttNm" maxlength="60" /></td>
								</tr>
								<tr>
									<th height="17">점　검　기　간</th>
									<td>
										<input id="qcBeginDt" type="text" class="emdcal" size="12"/> ~
										<input id="qcEndDt" type="text" class="emdcal" size="12"/>
									</td>
									<th height="17">책임　기술자　명</th>
									<td><input type="text" size="20" id="responEngineerNm" maxlength="20" /></td>
								</tr>
								<tr>
									<th height="17">작　성　일　자</th>
									<td>
										<input id="wrtDt" type="text" class="emdcal" size="12"/>
									</td>
									<th height="17">작　　성　　자</th>
									<td><input id="wrtUsr" type="text" size="20" maxlength="7" /></td>
								</tr>
								<tr>
									<th height="17">점검　진단　결과</th>
									<td colspan="3">
										<textarea id="qcInspResult" cols="88" rows="5"></textarea>
										<button id="popupEditQcResultItem" class="popupButton">점검결과항목선택</button>
										<select id="mechFcltsSe">
											<option value="1">하역장비</option>
											<option value="2">항만부잔교</option>
											<option value="3">건축 기계설비</option>
										</select>
									</td>
								</tr>
								<tr>
									<th height="17">조　치　내　용</th>
									<td colspan="3"><textarea id="actionCn" cols="88" rows="5"></textarea></td>
								</tr>
								<tr>
									<th height="17">비　　　　　고</th>
									<td colspan="3"><input id="rm" type="text" size="90"/></td>
								</tr>
							</table>
							</form>
						</td>
						<td width="30%">
							<!-- 2.1.3.1.2 Grid Table -->
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<td>
										<table id="qcObjFcltsGrid" style="display:none" class="fillHeight"></table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table class="editForm" style="width:100%">
					<tr>
						<th width="11%" height="20">첨　부　파　일</th>
						<td colspan="2">
							<select id="atchFile">
                            </select>
						</td>
						<td style="text-align:right">
							<button id="btnAllSelect">전체선택</button>
							<button id="btnAllUnSelect">선택해제</button>
							<div id="previewDialogArea" style="display: none;"></div>
						</td>
					</tr>
				</table>

				<div class="emdSummaryPanel">
				<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnPreviewFile">첨부파일미리보기</button>
					<button id="btnRemoveFile">첨부파일삭제</button>
				<div style="text-align:right">
					<button id="btnDetailAdd" class="buttonAdd">　　추　가　　</button>
					<button id="btnDetailDelete" class="buttonDelete">　　삭　제　　</button>
					<button id="btnSave" class="buttonSave">　　저　장　　</button>
					<!-- <button id="btnPrint" data-role="printPage" data-search-option="detailForm" data-url="/fcltyMng/printQcMngDtls.do">　　인　쇄　　</button> -->
					<button id="btnPrint" data-role="printPage" data-search-option="detailForm">　　인　쇄　　</button>
					
					<!-- <button id="btnHwp" data-role="printDown" data-url="/fcltyMng/selectFcltyQcHwp.do" data-filename="검사조서.hwp" data-search-option="detailForm">H　W　P 　</button> -->
					<button id="btnHwp" data-role="printDown" data-filename="검사조서.hwp" data-search-option="detailForm">H　W　P 　</button>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%
/******************************** UI       END ********************************/
%>
