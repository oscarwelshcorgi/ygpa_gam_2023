<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamInfoTechFcltySpecMng.jsp
  * @Description : 정보통신시설 제원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.17  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.17
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
 * @FUNCTION NAME : GamInfoTechFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamInfoTechFcltySpecMngModule() {
}

GamInfoTechFcltySpecMngModule.prototype = new EmdModule(1000,700);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};
	this._params = params;

	this._mainmode = '';
	this._prtFcltySe = 'I';
	this._deleteAtchFileList = null;

	this.$('#mainGrid').flexigrid({
		module: this,
		url: '/fclty/selectInfoTechFcltySpecMngList.do',
		dataType: 'json',
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"품목명",		name:"prdlstNm",			width:120,		sortable:false,		align:"left"},
					{display:"소재지",		name:"loc",					width:180,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:120,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
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
		url: '/fclty/selectInfoTechFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",		width:180,		sortable:true,		align:"left"},
			],
		height: '400'
	});

	this.$('#atchFileGrid').on('onItemSelected', function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});

	this.$('.atchFileEditItem').bind('change keyup', {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});


	this.$('#selectGisPrtFcltyCd').on('change', {module: this}, function(event) {
		event.data.module.$('#gisPrtFcltyCd').val($(this).val());
	});

	this.$('#sFcltsMngGroupNo').bind('click', {module: this}, function(event) {
		event.data.module.$('#sFcltsMngGroupNo').val('');
		event.data.module.$('#sFcltsMngGroupNoNm').val('');
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
GamInfoTechFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.loadData = function() {
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
GamInfoTechFcltySpecMngModule.prototype.loadDataComplete = function() {
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
GamInfoTechFcltySpecMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#mainGrid').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fclty/excelDownloadInfoTechFcltySpecMngList.do');
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.loadDetail = function() {
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

	this.doAction('/fclty/selectInfoTechFcltySpecMngDetail.do', row, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#detailForm', result.result);
			module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
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
GamInfoTechFcltySpecMngModule.prototype.loadAtchFileList = function() {
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
GamInfoTechFcltySpecMngModule.prototype.initBeforeInsert = function() {
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
GamInfoTechFcltySpecMngModule.prototype.setControlStatus = function() {
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
		this.$('#popupDetailArchFcltsMng').enable();
		this.$('#popupDetailArchFcltsMng').removeClass('ui-state-disabled');
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
		this.$('#popupDetailArchFcltsMng').enable();
		this.$('#popupDetailArchFcltsMng').removeClass('ui-state-disabled');
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
		this.$('#popupDetailArchFcltsMng').disable({disableClass:'ui-state-disabled'});
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
		this.$('#popupDetailArchFcltsMng').disable({disableClass:'ui-state-disabled'});
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
GamInfoTechFcltySpecMngModule.prototype.validateDetailForm = function() {
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
GamInfoTechFcltySpecMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
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
GamInfoTechFcltySpecMngModule.prototype.getSaveData = function() {
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
 * @DESCRIPTION   : 정보통신재원 데이터 저장
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.saveData = function() {
	if(!this.validateDetailForm()){
		return;
	}

	if(!this.validateDuration(this.$('#prtFcltyInstlDt').val(), this.$('#prtFcltyChangeDt').val(),
									'설치일자', '변경일자', true, true, false)) {
		return;
	}

	var inputData = this.getSaveData();

	if(this._mainmode == 'insert') {
	 	this.doAction('/fclty/insertInfoTechFcltySpecMngDetail.do', inputData, function(module, result) {
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
		this.doAction('/fclty/updateInfoTechFcltySpecMngDetail.do', inputData, function(module, result) {
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
 * @DESCRIPTION   : 정보통신재원 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.deleteData = function() {
	var rows = this.$('#mainGrid').selectedRows();
	if(rows.length==0) {
		alert('선택된 항목이 없습니다.');
		return;
	}
	if(confirm('삭제하시겠습니까?')) {
		var deleteVO = rows[0];
	 	this.doAction('/fclty/deleteInfoTechFcltySpecMngDetail.do', deleteVO, function(module, result) {
	 		if(result.resultCode == '0') {
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

<%
/**
 * @FUNCTION NAME : selectAtchFileItem
 * @DESCRIPTION   : 첨부파일목록에서 행 선택처리
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.selectAtchFileItem = function() {
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
GamInfoTechFcltySpecMngModule.prototype.atchFileInfoChanged = function(target) {
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
GamInfoTechFcltySpecMngModule.prototype.uploadAtchFileItem = function() {
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
	}, '정보통신재원 첨부파일 업로드');
};

<%
/**
 * @FUNCTION NAME : downloadAtchFileItem
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamInfoTechFcltySpecMngModule.prototype.downloadAtchFileItem = function() {
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
GamInfoTechFcltySpecMngModule.prototype.removeAtchFileItem = function() {
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
GamInfoTechFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {
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

		// 건축시설물 관리번호(디테일 화면)
		case 'popupDetailArchFcltsMng':
			this.doExecuteDialog('selectArchFcltsMng', '건축시설조회', '/popup/showConsFcltyInfo.do', {});
			break;

		case 'setFeature': // GIS 피처 지정
			this.$('#setFeature').hide();
			var row = this.$('#mainGrid').selectedRows();

			if(row.length!=1) {
				alert('지정 할 시설을 하나만 선택 해 주시기 바랍니다.');
				return;
			}
			this.setFeatureCode('gisTeleFclty',
					row[0],
					this._params.feature);
			alert('지정 되었습니다.');
//			this.closeWindow();
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
GamInfoTechFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){
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
			this.$('#infoCommFcltsClCd').val(value['fcltsClCd']);
			this.$('#infoCommFcltsClCdNm').val(value['fcltsClCdNm']);
			break;

		case 'selectArchFcltsMng':
			this.$('#archFcltsMngNo').val(value['fcltsMngNo']);
			this.$('#archFcltsMngNoNm').val(value['prtFcltyNm']);
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
GamInfoTechFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
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

var module_instance = new GamInfoTechFcltySpecMngModule();

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
								<input id="sFcltsMngGroupNo" type="text" size="14" maxlength="14" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM069" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" maxlength="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" maxlength="30" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#listTab" class="emdTab">정보통신시설 목록</a></li>
				<li><a href="#detailTab" class="emdTab">정보통신시설 제원</a></li>
				<li><a href="#atchFileTab" class="emdTab">정보통신시설 첨부파일</a></li>
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
	                                <button data-role="showMap" data-gis-layer="gisTeleFclty" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
									<button data-role="editMap" data-gis-layer="gisTeleFclty">맵편집</button>
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
								<th width="70%">정보통신시설 일반</th>
								<th>시설물관리번호 : <span id="dispfcltsMngNo"></span><input type="hidden" id="fcltsMngNo" /></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　구　　분</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>
							    <input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/></td>
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
							<td><input type="text" size="30" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3">
								<input id="loc" type="text" size="79" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="5" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM069" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">시　　설　　명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="21" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="popupDetailFcltsMngGroup" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　규　격</th>
							<td colspan="3"><input id="prtFcltyStndrd" type="text" size="50" title="시설규격" maxlength="40" /></td>
							<th width="12%" height="17" class="required_text">시　설　단　위</th>
							<td><input id="prtFcltyUnit" type="text" size="32" title="시설단위" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="11" title="설치일자" maxlength="11" /></td>
							<th width="12%" height="17" class="required_text">변　경　일　자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="11" title="변경일자" maxlength="11" /></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>정보통신시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">품　　목　　명</th>
							<td colspan="3"><input id="prdlstNm" type="text" size="50" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">모　　　　　델</th>
							<td><input id="model" type="text" size="50" maxlength="250" /></td>
							<th width="12%" height="17" class="required_text">제　　조　　사</th>
							<td><input id="maker" type="text" size="50" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">수　　　　　량</th>
							<td><input id="qy" type="text" size="50" class="ygpaNumber"/> 개</td>
							<th width="12%" height="17" class="required_text">규　　　　　격</th>
							<td><input id="stndrd" type="text" size="50" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">세　부　내　역</th>
							<td colspan="3"><input id="ptlrDtls" type="text" size="50" maxlength="250" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　구　분</th>
							<td colspan="3">
								<select id="instlSe">
                                    <option value="">선택</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　번　호</th>
							<td><input id="instlNo" type="text" size="50" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">기　　　　　능</th>
							<td><input id="func" type="text" size="50" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　높　이</th>
							<td><input id="instlHt" type="text" size="50" class="ygpaNumber" /></td>
							<th width="12%" height="17" class="required_text">설　치　규　격</th>
							<td><input id="instlStndrd" type="text" size="50" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제　어　방　식</th>
							<td><input id="ctrlMthd" type="text" size="50" maxlength="20" /></td>
							<th width="12%" height="17" class="required_text">LAMP　형　식</th>
							<td><input id="lampFmt" type="text" size="50" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="3">
								<input id="archFcltsMngNo" type="text" size="30" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="30" disabled="disabled" />
								<button id="popupDetailArchFcltsMng" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비　　　　　고</th>
							<td colspan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="1000"></textarea>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">　　저　장　　</button>
				</div>
			</div>

			<!-- 정보통신시설 첨부파일 -->
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
