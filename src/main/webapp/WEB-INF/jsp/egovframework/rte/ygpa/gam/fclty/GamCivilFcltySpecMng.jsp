<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCivilFcltySpecMng.jsp
  * @Description : 토목시설 제원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.6  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.6
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
 * @FUNCTION NAME : GamCivilFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCivilFcltySpecMngModule() {
}

GamCivilFcltySpecMngModule.prototype = new EmdModule(1000,700);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.loadComplete = function(params) {

	this._mainmode = '';
	this._prtFcltySe = 'C';
	this._deleteAtchFileList = null;

	this.$('#mainGrid').flexigrid({
		module: this,
		url: '/fclty/selectCivilFcltySpecMngList.do',
		dataType: 'json',
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"구조형식",		name:"strctFmt",			width:200,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
					{display:"시설규격",	    name:"prtFcltyStndrd",		width:230,		sortable:false,		align:"left"},
					{display:"시설단위",  	    name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
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


	this.$('#atchFileGrid').flexigrid({
		module: this,
		url: '/fclty/selectCivilFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
			],
		height: '400'
	});

	this.$('#atchFileGrid').on('onItemSelected', function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});

	this.$('.atchFileEditItem').bind('change keyup', {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});


	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#sFcltsMngGroupNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngGroupNo").val('');
		event.data.module.$("#sFcltsMngGroupNoNm").val('');
	});

	this.setControlStatus();

	this._params = params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
			case "setFeature":
				this.$('#setFeature').show();
				break;
			case "prtFcltyInqire":
				this._mainmode = 'modify';
				this.$("#mainTab").tabs("option", {
					active : 1
				});
			}
		}
	}

};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.loadData = function() {
	this.$('#mainTab').tabs('option', {active: 0});
	var searchOpt = this.makeFormArgs("#searchForm");
	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : loadDataComplete
 * @DESCRIPTION   : DATA LOAD COMPLETE(LIST)
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.loadDataComplete = function() {
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
GamCivilFcltySpecMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#mainGrid').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fclty/excelDownloadCivilFcltySpecMngList.do');
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.loadDetail = function() {
	var rows = this.$('#mainGrid').selectedRows();
	var row = null;

	if(rows.length==0 && this._params!=undefined && this._params.action=="prtFcltyInqire") {
		row = {'fcltsMngNo': this._params.fcltsMngNo};
	}
	else {
		row = rows[0];
	}

	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$('#mainTab').tabs('option', {active: 0});
		this._mainmode = '';
		this.setControlStatus();
		alert('시설물 관리번호에 오류가 있습니다.');
		return;
	}

	this.doAction('/fclty/selectCivilFcltySpecMngDetail.do', row, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#detailForm', result.result);
			module.$('#dispfcltsMngNo').text(module.$('#fcltsMngNo').val());
			module.loadAtchFileList();
		}
		else {
			module._mainmode = 'listed';
			module.setControlStatus();
			alert(result.resultMsg);
		}
	});
};

<%
/**
 * @FUNCTION NAME : loadAtchFileList
 * @DESCRIPTION   : 첨부파일목록을 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.loadAtchFileList = function() {
	var searchOpt = [
	                 	{name: 'sFcltsMngNo', value: this.$('#fcltsMngNo').val()}
	                ];
	this._deleteAtchFileList = [];
	this.$('#atchFileGrid').flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : initBeforeInsert
 * @DESCRIPTION   : 추가작업 전 초기화
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.initBeforeInsert = function() {
	this._mainmode = 'insert';
	this.makeFormValues('#detailForm', {});
	this._deleteAtchFileList = [];
	this.setControlStatus();
	this.$('#mainTab').tabs('option', {active: 1});
};


<%
/**
 * @FUNCTION NAME : setControlStatus
 * @DESCRIPTION   : 컨트롤 상태 변경
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.setControlStatus = function() {
	if(this._mainmode == 'insert') {
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnDetailSave').enable();
		this.$('#btnDetailSave').removeClass('ui-state-disabled');
		this.$('#btnUploadFile').enable();
		this.$('#btnUploadFile').removeClass('ui-state-disabled');
		this.$('#btnDownloadFile').enable();
		this.$('#btnDownloadFile').removeClass('ui-state-disabled');
		this.$('#btnRemoveFile').enable();
		this.$('#btnRemoveFile').removeClass('ui-state-disabled');
		this.$('#popupDetailGisCode').enable();
		this.$('#popupDetailGisCode').removeClass('ui-state-disabled');
		this.$('#popupDetailFcltsMngGroup').enable();
		this.$('#popupDetailFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#popupDetailFcltsClCd').enable();
		this.$('#popupDetailFcltsClCd').removeClass('ui-state-disabled');
		this.$('#selectGisPrtFcltyCd').enable();
		this.$("#dispfcltsMngNo").text('');
		this.$('#atchFileGrid').flexEmptyData();
	}
	else if(this._mainmode == 'modify') {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDelete').enable();
		this.$('#btnDelete').removeClass('ui-state-disabled');
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnDetailSave').enable();
		this.$('#btnDetailSave').removeClass('ui-state-disabled');
		this.$('#btnUploadFile').enable();
		this.$('#btnUploadFile').removeClass('ui-state-disabled');
		this.$('#btnDownloadFile').enable();
		this.$('#btnDownloadFile').removeClass('ui-state-disabled');
		this.$('#btnRemoveFile').enable();
		this.$('#btnRemoveFile').removeClass('ui-state-disabled');
		this.$('#popupDetailGisCode').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailFcltsMngGroup').enable();
		this.$('#popupDetailFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#popupDetailFcltsClCd').enable();
		this.$('#popupDetailFcltsClCd').removeClass('ui-state-disabled');
		this.$('#selectGisPrtFcltyCd').disable();
	}
	else if(this._mainmode == 'listed') {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnUploadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDownloadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnRemoveFile').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailGisCode').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailFcltsClCd').disable({disableClass:'ui-state-disabled'});
		this.$('#selectGisPrtFcltyCd').disable();
		this.$("#dispfcltsMngNo").text('');
		this.$('#atchFileGrid').flexEmptyData();
	}
	else {
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDetailSave').disable({disableClass:'ui-state-disabled'});
		this.$('#btnUploadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDownloadFile').disable({disableClass:'ui-state-disabled'});
		this.$('#btnRemoveFile').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailGisCode').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#popupDetailFcltsClCd').disable({disableClass:'ui-state-disabled'});
		this.$('#selectGisPrtFcltyCd').disable();
		this.$("#dispfcltsMngNo").text('');
		this.$('#atchFileGrid').flexEmptyData();
	}
};

<%
/**
 * @FUNCTION NAME : validateDetailForm
 * @DESCRIPTION   : Detail Form Validate 체크
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.validateDetailForm = function() {
	if(this._mainmode == 'insert') {
		if(this.$('#gisAssetsPrtAtCode').val() == '' 
				|| this.$('#gisAssetsCd').val() == '' 
				|| this.$('#gisAssetsSubCd').val() == '') {
			EMD.util.showMessage(this.$('#popupDetailGisCode')[0], 'GIS자산코드를 선택하세요.');
			return false;
		}
		if(this.$('#gisPrtFcltyCd').val() == '') {
			EMD.util.showMessage(this.$('#selectGisPrtFcltyCd')[0], '시설분류를 선택하세요.');
			return false;
		}
	}
	else if(this._mainmode == 'modify') {
		if(this.$('#fcltsMngNo').val() == '') {
			alert('잘못된 시설물 번호입니다.');
			return false;
		}
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
GamCivilFcltySpecMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
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
GamCivilFcltySpecMngModule.prototype.getSaveData = function() {
	var result = [];
	var fcltsMngNo = this.$('#fcltsMngNo').val();
	var fileList = this.$('#atchFileGrid').flexGetData();
	
	if(this._mainmode == 'modify') {
		for(var i=0; i<fileList.length; i++) {
			fileList[i]['fcltsMngNo'] = fcltsMngNo;
		}
	}
	
	result[result.length] = {name: 'detailForm', value: JSON.stringify(this.makeFormArgs('#detailForm', 'object'))};
	result[result.length] = {name: 'insertAtchFileList', value: JSON.stringify(this.$('#atchFileGrid').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	if(this._mainmode == 'modify') {
	    result[result.length]={name: 'updateAtchFileList', value: JSON.stringify(this.$('#atchFileGrid').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	    result[result.length]={name: 'deleteAtchFileList', value: JSON.stringify(this._deleteAtchFileList) };
	}

	return result;
};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 토목시설재원 데이터 저장
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.saveData = function() {
	if(!this.validateDetailForm()){
		return;
	}

	if(!this.validateDuration(this.$('#prtFcltyInstlDt').val(), this.$('#prtFcltyChangeDt').val(), 
								'설치일자', '변경일자', true, true, false)) {
		return;
	}
	
	var inputData = this.getSaveData();

	if(this._mainmode == 'insert') {
	 	this.doAction('/fclty/insertCivilFcltySpecMngDetail.do', inputData, function(module, result) {
	 		if(result.resultCode == '0'){
	 			module.$('#gisPrtFcltySeq').val(result.gisPrtFcltySeq);
				module.$('#fcltsMngNo').val(result.fcltsMngNo);
				module.$('#dispfcltsMngNo').text(result.fcltsMngNo);
	 			module._mainmode = 'modify';
	 			module.setControlStatus();
	 			module.loadAtchFileList();
	 		}
	 		alert(result.resultMsg);
	 	});
	} else if (this._mainmode == 'modify') {
		this.doAction('/fclty/updateCivilFcltySpecMngDetail.do', inputData, function(module, result) {
			if(result.resultCode == '0'){
	 			module.loadAtchFileList();
			}
			alert(result.resultMsg);
		});
	}
};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 토목재원 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.deleteData = function() {
	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length==0) {
		alert('선택된 항목이 없습니다.');
		return;
	}
	if(confirm('삭제하시겠습니까?')) {
		var deleteVO = rows[0];
	 	this.doAction('/fclty/deleteCivilFcltySpecMngDetail.do', deleteVO, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

<%
/**
 * @FUNCTION NAME : saveAtchFile
 * @DESCRIPTION   : 첨부파일 병합저장
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.saveAtchFile = function() {
	var fcltsMngNo = this.$("#fcltsMngNo").val();
	var fileList = this.$("#atchFileGrid").flexGetData();
	for(var i=0; i<fileList.length; i++) {
		fileList[i]["fcltsMngNo"] = fcltsMngNo;
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#atchFileGrid').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#atchFileGrid').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteAtchFileList) };
    this.doAction('/fclty/mergeCivilFcltySpecAtchFile.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteAtchFileList = [];
			module.loadAtchFileList();
        }
        else {
        	alert(result.resultMsg);
        }
    });
};

<%
/**
 * @FUNCTION NAME : selectAtchFileItem
 * @DESCRIPTION   : 첨부파일목록에서 행 선택처리
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#atchFileGrid').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.makeFormValues('#archFileForm', {});
		this.makeFormValues('#archFileForm', row);
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != '') {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row['atchFileNmPhysicl'];
			var ext = filenm.substring(filenm.lastIndexOf('.')+1).toLowerCase();
			if(ext == 'jpg' || ext == 'jpeg' || ext == 'bmp' || ext == 'png' || ext == 'gif'){
				var imgURL = this.getPfPhotoUrl(filenm);
			    this.$('#previewImage').attr('src', imgURL);
			}else{
				this.$('#previewImage').removeAttr('src');
			}
		}
	}
};

<%
/**
 * @FUNCTION NAME : atchFileInfoChanged
 * @DESCRIPTION   : 첨부파일정보 수정처리
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#atchFileGrid').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSe'] = $(target).val();
			row['atchFileSeNm'] = $(target).find('option:selected').text();
			changed=true;
		}
		if(this.$('#atchFileSj').is(target)) {
			row['atchFileSj'] = $(target).val();
			changed=true;
		}
		if(this.$('#atchFileWritngDt').is(target)) {
			row['atchFileWritngDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$('#atchFileGrid').selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#atchFileGrid').flexUpdateRow(rowid, row);
	}
};

<%
/**
 * @FUNCTION NAME : uploadAtchFileItem
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.uploadAtchFileItem = function() {
	this.$('#atchFileSe').val('D');
	this.uploadPfPhoto('uploadPhoto', function(module, result) {
		$.each(result, function(){
			module.$('#atchFileGrid').flexAddRow(
					{ 	
						_updtId:'I', 
						fcltsMngNo:module.$('#fcltsMngNo').val(), 
						atchFileSe:'D', 
						atchFileSeNm :'문서', 
						atchFileNmLogic:this.logicalFileNm, 
						atchFileNmPhysicl: this.physcalFileNm, 
						atchFileWritingDt:''
					});
		});
	}, '토목재원 첨부파일 업로드');
};

<%
/**
 * @FUNCTION NAME : downloadAtchFileItem
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#atchFileGrid').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row['atchFileNmPhysicl'], row['atchFileNmLogic']);
	}
};

<%
/**
 * @FUNCTION NAME : removeAtchFileItem
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamCivilFcltySpecMngModule.prototype.removeAtchFileItem = function() {
	var rows = this.$('#atchFileGrid').selectedRows();
    if(rows.length == 0){
        alert('첨부파일정보를 선택하십시오.');
        return;
    }
    if(this.$('#atchFileGrid').selectedRowIds().length>0) {
    	for(var i=this.$('#atchFileGrid').selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$('#atchFileGrid').flexGetRow(this.$('#atchFileGrid').selectedRowIds()[i]);
            if(row._updtId == undefined || row._updtId != 'I') {
            	this._deleteAtchFileList[this._deleteAtchFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$('#atchFileGrid').flexRemoveRow(this.$('#atchFileGrid').selectedRowIds()[i]);
		}
    	this.$('#previewImage').removeAttr('src');
    	alert('삭제되었습니다.');
	}
    this.makeFormValues("#archFileForm", {});
};


<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamCivilFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch':
			this.loadData();
			break;

		case 'btnExcelDownload':
			this.downloadExcel();
			break;

		case 'btnAdd':
			this.initBeforeInsert();
			break;

		case 'btnDelete':
			this.deleteData();
			break;

		case 'btnSave':
		case 'btnDetailSave':
			this.saveData();
			break;

		case 'btnUploadFile':
			this.uploadAtchFileItem();
			break;

		case 'btnDownloadFile':
			this.downloadAtchFileItem();
			break;

		case 'btnRemoveFile':
			this.removeAtchFileItem();
			break;

		// 자산코드 팝업(디테일 화면)
		case 'popupDetailGisCode':
			this.doExecuteDialog('selectGisCode', '자산코드', '/popup/showAssetsCd.do', {});
			break;

		// 시설물관리그룹(조회 화면)
		case 'popupSearchFcltsMngGroup':
			this.doExecuteDialog('selectFcltsMngGroup', '시설물그룹조회', '/popup/showFcltsMngGroup.do', {});
			break;

		// 시설물관리그룹(디테일 화면)
		case 'popupDetailFcltsMngGroup':
			this.doExecuteDialog('detailFcltsMngGroup', '시설물그룹조회', '/popup/showFcltsMngGroup.do', {});
			break;

		// 시설물 분류코드(디테일 화면)
		case 'popupDetailFcltsClCd' :
			this.doExecuteDialog('selectFcltsClCd', '시설물분류코드', '/popup/showFcltsClCd.do',
										{ sFcltsClCdChar : this._prtFcltySe });
			break;

		case 'setFeature': // GIS 피처 지정
			this.$('#setFeature').hide();
			var row = this.$('#mainGrid').selectedRows();

			if(row.length!=1) {
				alert('지정 할 시설을 하나만 선택 해 주시기 바랍니다.');
				return;
			}
			this.setFeatureCode('gisCivilFclty',
					row[0],
					this._param.feature);
			this.closeWindow();
			break;
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
GamCivilFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		case 'selectGisCode':
			this.$('#gisAssetsPrtAtCode').val(value['gisAssetsPrtAtCode']);
			this.$('#gisAssetsPrtAtCode2').val(value['gisAssetsPrtAtCode']);
			this.$('#gisAssetsPrtAtName').val(value['gisAssetsPrtAtCodeNm']);
			this.$('#gisAssetsSubCd').val(value['gisAssetsSubCd']);				// GIS SUB자산코드
			this.$('#gisAssetsCd').val(value['gisAssetsCd']);					// GIS 자산코드
			this.$('#gisAssetsNm').val(value['gisAssetsNm']);					// GIS 자산명
			if((value['gisAssetsLnmSub'] == null) || (value['gisAssetsLnmSub'] == '')) {
				if((value['gisAssetsLnm'] == null) || (value['gisAssetsLnm'] == '')) {
					this.$('#loc').val(value['gisAssetsLocplc']);
				}
				else {
					this.$('#loc').val(value['gisAssetsLocplc'] + ' ' + value['gisAssetsLnm']);
				}
			} else {
				this.$('#loc').val(value['gisAssetsLocplc'] + ' ' + value['gisAssetsLnm'] + '-' + value['gisAssetsLnmSub']);
			}
			break;

		case 'selectFcltsClCd':
			this.$('#cvlEngFcltsClCd').val(value['fcltsClCd']);
			this.$('#cvlEngFcltsClCdNm').val(value['fcltsClCdNm']);
			break;

		case 'selectFcltsMngGroup':
			this.$('#sFcltsMngGroupNo').val(value['fcltsMngGroupNo']);
			this.$('#sFcltsMngGroupNoNm').val(value['fcltsMngGroupNm']);
			break;

		case 'detailFcltsMngGroup':
			this.$('#fcltsMngGroupNo').val(value['fcltsMngGroupNo']);
			this.$('#fcltsMngGroupNoNm').val(value['fcltsMngGroupNm']);
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
GamCivilFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if(this._mainmode == 'modify' && oldTabId == 'listTab') {
				this.loadDetail();
			}
			break;
		case 'atchFileTab':
			if(this._mainmode == 'modify' && oldTabId == 'listTab') {
				this.loadDetail();
			}
			break;
	}
};

var module_instance = new GamCivilFcltySpecMngModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>


<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="18"/>
								<input id="sFcltsMngGroupNoNm" type="text" size="49" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM070" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30"/></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30"/></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#listTab" class="emdTab">토목시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">토목시설 제원</a></li>
				<li><a href="#atchFileTab" class="emdTab">토목시설 첨부파일</a></li>
			</ul>

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
	                                <button data-role="showMap" data-gis-layer="gisCivilFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
									<button data-role="editMap" data-gis-layer="gisCivilFclty">맵편집</button>
									<button id="setFeature" style="display: none;">맵지정</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div id="detailTab" class="emdTabPage" style="overflow: hidden;">
				<form id="detailForm">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">토목시설 일반</th>
								<th>시설물관리번호 : <span id="dispfcltsMngNo"></span><input type="hidden" id="fcltsMngNo" /></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　구　　분</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>  <input type="text" size="15" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="3" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="4" id="gisAssetsPrtAtCode2" disabled="disabled"/>
								<button id="popupDetailGisCode" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
							<td><input type="text" size="23" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3">
								<input id="loc" type="text" size="70" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="3" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM070" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">토 목　시 설 명</th>
							<td><input type="text" size="27" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="20" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="50" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="popupDetailFcltsMngGroup" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" /></td>
							<th width="12%" height="17" class="required_text">변　경　일　자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" /></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>

								<th>토목시설 제원</th>
							</tr>
						</tbody>
					</table>

						<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">시　설　물　연　장</th>
							<td><input id="fcltsExt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17">천　단　　　표　고</th>
							<td><input id="upsideAltud" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17">선　좌　　　수　심</th>
							<td><input id="berthDpwt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">구　조　　　형　식</th>
							<td colspan="5"><input id="strctFmt" type="text" size="139"  /></td>
						</tr>
						<tr>
							<th width="12%" height="17">에　이　프　런　폭</th>
							<td><input id="apronWd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17">에이프런　포장종류</th>
							<td><input id="apronPackKnd" type="text" size="20" maxlength="3" /></td>
							<th width="12%" height="17">에이프런　포장구배</th>
							<td><input id="apronPackGrdnt" type="text" size="20"  /></td>
						</tr>
						<tr>
							<th width="12%" height="17">상　　　치　　　폭</th>
							<td><input id="permWd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17" >접안　 선박 　규모</th>
							<td><input id="csdhpShipScl" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17" >상　재　　　하　중</th>
							<td><input id="frostDmgWght" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
						</tr>
						<tr>
							<th width="12%" height="17"  >기초　 저면 　토질</th>
							<td><input id="baseBttmSoil" type="text" size="20" maxlength="150" /></td>
							<th width="12%" height="17" >취　급　　　화　물</th>
							<td colspan="3"><input id="hndlFrght" type="text" size="20" maxlength="100" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >말　뚝　　　구　경</th>
							<td><input id="pileClbr" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17" >말　뚝　　　연　장</th>
							<td><input id="pileExt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17" >말　뚝　　　본　수</th>
							<td><input id="pileQty" type="text" size="20" class="ygpaNumber" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >널　말　뚝　규　격</th>
							<td><input id="sheetFileStndrd" type="text" size="20" maxlength="100" /></td>
							<th width="12%" height="17" >　　　　폭</th>
							<td><input id="wd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
							<th width="12%" height="17" >길　　　　　　　이</th>
							<td><input id="lt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >시　작　점　위　치</th>
							<td><input id="beginPtLoc" type="text" size="20" maxlength="100" /></td>
							<th width="12%" height="17" >종　착　점　위　치</th>
							<td><input id="endPtLoc" type="text" size="20" maxlength="100" /></td>
							<th width="12%" height="17" >포　장　　　종　류</th>
							<td><input id="packKnd" type="text" size="20" maxlength="3" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >급　수　전　수　량</th>
							<td><input id="hydrntQy" type="text" size="20" class="ygpaNumber" /> 개</td>
							<th width="12%" height="17" >소　화　전　수　량</th>
							<td ><input id="firepgQy" type="text" size="20" class="ygpaNumber" /> 개</td>
							<th width="12%" height="17" >선 　　　　　　　석</th>
							<td><input id="berth" type="text" size="20" class="ygpaNumber" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >야적장　포장　종류</th>
							<td><input id="yardPackKnd" type="text" size="20" maxlength="3" /></td>
							<th width="12%" height="17" >야　적　장　면　적</th>
							<td><input id="yardAr" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /> m<sup>2</sup></td>
							<th width="12%" height="17" >설　계　　　파　고</th>
							<td><input id="planHegh" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" >방　충　재　수　량</th>
							<td><input id="fenderQy" type="text" size="20" class="ygpaNumber" /> 개</td>
							<th width="12%" height="17" >방　충　재　형　식</th>
							<td><input id="fenderFmt" type="text" size="20" maxlength="3" /></td>
							<th width="12%" height="17" >방충재　배치　간격</th>
							<td><input id="fenderPmntItv" type="text" size="20" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">계　선　주　수량1</th>
							<td><input id="mrpostQy1" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /> 개</td>
							<th width="12%" height="17">계　선　주　형식1</th>
							<td><input id="mrpostStndrd1" type="text" size="20" maxlength="100" /></td>
				    		<th width="12%" height="17">계선주　배치　간격1</th>
							<td><input id="mrpostPmntItv1" type="text" size="20" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">계　선　주　수량2</th>
							<td><input id="mrpostQy2" type="text" size="20" class="ygpaNumber" data-decimal-point="2" /> 개</td>
							<th width="12%" height="17">계　선　주　형식2</th>
							<td><input id="mrpostStndrd2" type="text" size="20" maxlength="100" /></td>
							<th width="12%" height="17">계선주　배치　간격2</th>
							<td><input id="mrpostPmntItv2" type="text" size="20" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">계선주　견인력　1</th>
							<td><input id="mrpostPwr1" type="text" size="20" maxlength="30" /></td>
							<th width="12%" height="17">계선주　견인력　2</th>
							<td><input id="mrpostPwr2" type="text" size="20" maxlength="30" /></td>
							<th width="12%" height="17">파　랑　주　방　향</th>
							<td><input id="wavemainDir" type="text" size="20" maxlength="30" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">저장</button>
				</div>
			</div>

			<!-- 토목시설 첨부파일 -->
			<div id="atchFileTab" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="atchFileGrid" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="btnDetailSave">저장</button>
							</div>

							<form id="archFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="atchFileEditItem">
												<option value="D">문서</option>
			                                    <option value="P">사진</option>
			                                    <option value="Z">기타</option>
			                                </select>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td><input id="atchFileSj" type="text" size="45" class="atchFileEditItem" maxlength="25" /></td>
									</tr>
								</table>
							</form>
						</td>
						<td style="text-align:center;vertical-align:middle;">
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
