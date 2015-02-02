<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamElctyFcltySpecMng.jsp
  * @Description : 전기시설 제원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.19  	HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.19
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
 * @FUNCTION NAME : GamConstFcltySpecMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamElctyFcltySpecMngModule() {
}

GamElctyFcltySpecMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#elctyFcltySpecMngList").flexigrid({
		module: this,
		url: '/fclty/selectElctyFcltySpecMngList.do',
		dataType: "json",
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",	    name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"gisAssetsLocplc",		width:180,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:120,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"center"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

	this._cmd = '';
	this._deleteDataFileList = null;
	this._prtFcltySe = 'E';

	this.$("#elctyFcltySpecMngList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#elctyFcltySpecMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#elctyFcltySpecMngTab").tabs("option", {active: 1});
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#fcltsFileList").flexigrid({
		module: this,
		url: '/fclty/selectElctyFcltySpecFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:180,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.imagePreview();
	});
	
	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});
	
	
	// 맵관련 추가사항
	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
			case "setFeature":
				this.$('#setFeature').show();
				break;
			case "prtFcltyInqire":
				this._cmd = 'modify';
				this.$("#elctyFcltySpecMngTab").tabs("option", {
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
GamElctyFcltySpecMngModule.prototype.onSubmit = function() {
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchElctyFcltySpecMngForm");
	this.$("#elctyFcltySpecMngTab").tabs("option", {active: 0});
	this.$("#elctyFcltySpecMngList").flexOptions({params:searchOpt}).flexReload();
};


<%
/**
 * @FUNCTION NAME : loadDetailData
 * @DESCRIPTION   : DATA LOAD (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.loadDetailData = function() {
	var row = this.$('#elctyFcltySpecMngList').selectedRows();
	if(row.length > 0) {
		// 맵관련 변경사항
		if(row.length==0 && this._params!=undefined && this._params.action=="prtFcltyInqire") {
			row={'fcltsMngNo': this._params.fcltsMngNo};
		}
		else {
			row = row[0];
		}
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			this._cmd = '';
			this.initDisplay();
			return;
		}
		var opts = [{name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
		this.doAction('/fclty/selectElctyFcltySpecMngDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltyManageVO', result.result);
				module.$("#dispfcltsMngNo").text(result.result.fcltsMngNo);
				module.loadFileData();
			}
			else {
				this._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});
	}
};


<%
/**
 * @FUNCTION NAME : loadFileData
 * @DESCRIPTION   : 첨부파일 LOAD 
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this.$("#dispfcltsMngNo").text()}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
};


<%
/**
 * @FUNCTION NAME : imagePreview
 * @DESCRIPTION   : 선택한 첨부파일이 이미지이면 미리보기 보여주는 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.imagePreview = function() {
	
	var row = this.$('#fcltsFileList').selectedRows();
	row = row[0];
	this.$("#fcltsFileForm input").val('');
	this.makeFormValues("#fcltsFileForm", row);

	if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var filenm = row["atchFileNmPhysicl"];
		var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
			$imgURL = this.getPfPhotoUrl(filenm);
			//this.$("#previewImage").fadeIn(400, function() {
		    	this.$("#previewImage").attr("src", $imgURL);
		    //});
		}else{
			this.$("#previewImage").removeAttr("src");
		}
	}
};


<%
/**
 * @FUNCTION NAME : initDisplay
 * @DESCRIPTION   : 화면 초기화 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.initDisplay = function() {
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$("#dispfcltsMngNo").text("");
	this.$("#previewImage").attr("src", "#");
	this.$('#fcltsFileList').flexEmptyData();
	if(this._cmd == "insert") {
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#elctyFcltySpecMngTab").tabs("option", {active: 1});		
	} else if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#searchGisCodeBtn2").hide();
	} else {
		this.$("#fcltyManageVO :input").val("");
		this.$("#dispfcltsMngNo").text("");
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#elctyFcltySpecMngTab").tabs("option", {active: 0});
	}
};


<%
/**
 * @FUNCTION NAME : atchFileInfoChanged
 * @DESCRIPTION   : 첨부파일 하위폼 정보변경시 그리드 적용
 * @PARAMETER     : target
**/
%>
GamElctyFcltySpecMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltsFileList').selectedRows();
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
		var rowid=this.$("#fcltsFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltsFileList').flexUpdateRow(rowid, row);
	}
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
GamElctyFcltySpecMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
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
 * @FUNCTION NAME : validateDetailForm
 * @DESCRIPTION   : Detail Form Validate 체크
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.validateDetailForm = function() {
	if(this._cmd == 'insert') {
		if(this.$('#gisAssetsPrtAtCode').val() == '') {
			alert('항코드를 선택하세요.');
			return false;
		}
		if(this.$('#gisAssetsCd').val() == '') {
			alert('GIS자산코드를 선택하세요.');
			return false;
		}
		if(this.$('#gisAssetsSubCd').val() == '') {
			alert('GIS자산부코드를 선택하세요.');
			return false;
		}
		if(this.$('#gisPrtFcltyCd').val() == '') {
			alert('시설코드를 입력하세요.');
			return false;
		}
	}
	else if(this._cmd == 'modify') {
		if(this.$('#fcltsMngNo').val() == '') {
			alert('잘못된 시설물 번호입니다.');
			return false;
		}
	}
	return true;
};


<%
/**
 * @FUNCTION NAME : saveFcltyData
 * @DESCRIPTION   : DATA 저장
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.saveFcltyData = function() {
	if(!this.validateDetailForm()){
		return;
	}

	if(!this.validateDuration(this.$('#mfcDt').val(), this.$('#instlDt').val(),  
			'제작일자', '설치일자', true, true, false)) {
		return;
	}
	if(!this.validateDuration(this.$('#mfcDt').val(), this.$('#prtFcltyChangeDt').val(),  
			'제작일자', '변경일자', true, true, false)) {
		return;
	}
	if(!this.validateDuration(this.$('#instlDt').val(), this.$('#prtFcltyChangeDt').val(),  
			'설치일자', '변경일자', true, true, false)) {
		return;
	}
	
	//GIS 설치일자에 반영
	this.$("#prtFcltyInstlDt").val(this.$("#instlDt").val());
	var inputVO = this.makeSaveParam();	
	// 전기시설제원 입력/수정처리
 	if(this._cmd == "insert") {
	 	this.doAction('/fclty/insertElctyFcltySpecMngDetail.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
				module.$("#gisPrtFcltySeq").val(result.gisPrtFcltySeq);
				module.$("#dispfcltsMngNo").text(result.fcltsMngNo);
				
				module.$("#selectGisPrtFcltyCd").disable();
	 			module.$("#searchGisCodeBtn2").hide();
				
				module._cmd = "modify";
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fclty/updateElctyFcltySpecMngDetail.do', inputVO, function(module, result) {
	 		alert(result.resultMsg);
	 	});
	}
};


<%
/**
 * @FUNCTION NAME : makeSaveParam
 * @DESCRIPTION   : 입력 수정에 필요한 DATA 가공
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.makeSaveParam = function() {

	var inputVO = [];
 	inputVO[inputVO.length] = {name: 'fcltyManageVO', value :JSON.stringify(this.makeFormArgs("#fcltyManageVO",'object')) };
 	// 조건은 수정시에만 필요한 데이타 형식
 	if(this._cmd == "modify") {
 		var all_rows = this.$('#fcltsFileList').flexGetData();
 		var fcltsMngNo = this.$("#dispfcltsMngNo").text();

 		for(var i=0;i<all_rows.length;i++){
 			all_rows[i]["fcltsMngNo"] = fcltsMngNo;
 		}
 		
		inputVO[inputVO.length]={name: 'updateFileList', value :JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	 	inputVO[inputVO.length]={name: 'deleteFileList', value: JSON.stringify(this._deleteDataFileList) };
 	}
 	inputVO[inputVO.length] = {name: 'insertFileList', value :JSON.stringify(this.$('#fcltsFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
 	
	return inputVO;
};



<%
/**
 * @FUNCTION NAME : deleteFcltyData
 * @DESCRIPTION   : 선택 DATA 삭제
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.deleteFcltsData = function() { 
	
	var rows = this.$("#elctyFcltySpecMngList").selectedRows();
	if(rows.length <= 0){
		alert("삭제할 시설을 선택하십시오.");
		return;
	}
	if(confirm("시설정보을 삭제하시겠습니까?")) {
		var row = rows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			return;
		}
		
		var data = { 'fcltsMngNo': row["fcltsMngNo"] };
	 	this.doAction('/fclty/deleteElctyFcltySpecMngDetail.do', data, function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
				module.initDisplay();
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
		
	}

};


<%
/**
 * @FUNCTION NAME : uploadFile
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.uploadFile = function() {
	this.$('#atchFileSe').val('D');
	this.uploadPfPhoto("uploadPhoto", function(module, result) {
		$.each(result, function(){
			module.$("#fcltsFileList").flexAddRow({_updtId:'I', fcltsMngNo:module.$("#dispfcltsMngNo").text(), atchFileSe:'D', atchFileSeNm :'문서', atchFileNmLogic:this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt:''});
		});
	}, "전기시설파일 업로드");
};


<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : 파일다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.downloadFile = function() {
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.downloadExcel = function(buttonId) {
	
	var rowCount = this.$('#elctyFcltySpecMngList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#elctyFcltySpecMngList').flexExcelDown('/fclty/selectElctyFcltySpecMngListExcel.do');

};


<%
/**
 * @FUNCTION NAME : registLocation
 * @DESCRIPTION   : 위치등록 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.registLocation = function() {
	var module=this;
	EMD.gis.addPrtFcltyMarker(this._fcltyItem, function(value) {
		module.$('#laCrdnt').val(value.laCrdnt);
		module.$('#loCrdnt').val(value.loCrdnt);
	});
};


<%
/**
 * @FUNCTION NAME : gotoLocation
 * @DESCRIPTION   : 위치조회 함수
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.gotoLocation = function() {
	if(this._fcltyItem.laCrdnt!=null && this._fcltyItem.laCrdnt!=null) {
		EMD.gis.goLocation(this._fcltyItem.laCrdnt, this._fcltyItem.loCrdnt);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else if(this._fcltyItem.lat!=null && this._fcltyItem.lng!=null){
		EMD.gis.goLocation4326(this._fcltyItem.lat, this._fcltyItem.lng);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else {
		alert("시설위치가 등록되지 않았습니다.");
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
GamElctyFcltySpecMngModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	
	switch(buttonId) {
				
		// 검색조건 시설물 관리 그룹 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
			break;

		// 자산코드 팝업(디테일 화면)
		case "searchGisCodeBtn2":
			this.doExecuteDialog("selectGisCode2", "자산코드", '/popup/showAssetsCd.do', {});
			break;

		// 시설물관리그룹(디테일 화면)
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
			break;
			
		// 시설물 분류코드(디테일 화면)
		case "searchFcltsClCd" :
			this.doExecuteDialog("selectFcltsClCd", "시설물 분류코드", '/popup/showFcltsClCd.do', { 'sFcltsClCdChar' : this._prtFcltySe });			
			break;
			
		// 건축시설물 관리번호(디테일 화면) 			
		case "searchArchFcltsMngNo":
			this.doExecuteDialog("selectArchFcltsMngNo", "건축시설관리", '/popup/showConsFcltyInfo.do', {});
			break;
			
		// 시설추가
		case "btnAdd":
			this._cmd = "insert";
			this.initDisplay();
			break;
		
		//시설삭제
		case "btnDelete" :
			this.deleteFcltsData(); 
			break;
			
		// 저장
		case "btnSave":
			this.saveFcltyData();
			break;
					
		//파일업로드
		case "btnUploadFile":
			this.uploadFile();
			break;
			
		//파일다운로드			
		case "btnDownloadFile":
			this.downloadFile();
			break;
			
		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel(buttonId);
		break;
						
		//파일삭제
		case "btnRemoveFile":
			this.removeAtchFileItem();
			break;
		
		case "registLocation":	// 위치 등록
			this.registLocation();
			break;
			
		case "gotoLocation":	// 위치 조회
			this.gotoLocation();
			break;
			
		// 맵관련 추가
		case "setFeature": // GIS 피처 지정
			this.$('#setFeature').hide();
			var row = this.$("#elctyFcltySpecMngList").selectedRows();

			if(row.length!=1) {
				alert('지정 할 시설을 하나만 선택 해 주시기 바랍니다.');
				return;
			}
			this.setFeatureCode('gisElecFclty',
					row[0],
					this._param.feature);
			alert('지정 되었습니다.');
			//this.closeWindow();
			break;
	}
};


<%
/**
 * @FUNCTION NAME : removeAtchFileItem
 * @DESCRIPTION   : 첨부파일제거
 * @PARAMETER     : NONE
**/
%>
GamElctyFcltySpecMngModule.prototype.removeAtchFileItem = function() {
	var rows = this.$("#fcltsFileList").selectedRows();
    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#fcltsFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltsFileList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#fcltsFileList").flexGetRow(this.$("#fcltsFileList").selectedRowIds()[i]);
            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltsFileList").flexRemoveRow(this.$("#fcltsFileList").selectedRowIds()[i]);
		}
    	this.$("#previewImage").attr("src","#");
    	alert("삭제되었습니다.");
	}
    this.$("#fcltsFileForm").find(":input").val("");
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
GamElctyFcltySpecMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.loadDetailData();
	}
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		if(oldTabId == 'tabs1') {
			this.$("#tabs2").scrollTop(0);
		}
		if((this._cmd != 'insert') && (this._cmd != 'modify')) {
			this.$("#elctyFcltySpecMngTab").tabs("option", {active: 0});
			alert('전기시설 항목을 선택 하세요.');
		} 
		break;
	case "tabs3":
		if((this._cmd != 'insert') && (this._cmd != 'modify')) {
			this.$("#elctyFcltySpecMngTab").tabs("option", {active: 0});
			alert('전기시설 항목을 선택 하세요.');
		} 
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
GamElctyFcltySpecMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 조회화면
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
			
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;

		case "selectGisCode2":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode2").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
			break;
		
		case "selectFcltsClCd":
			this.$("#elctyFcltsClCd").val(value["fcltsClCd"]);
			this.$("#elctyFcltsClCdNm").val(value["fcltsClCdNm"]);			
			break;

		case "selectArchFcltsMngNo":
			this.$("#archFcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#archFcltsMngNoNm").val(value["prtFcltyNm"]);			
			break;
			
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamElctyFcltySpecMngModule();
</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchElctyFcltySpecMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" title="시설물관리그룹넘버" maxlength="14" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" title="시설물관리그룹명" disabled="disabled" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" maxlength="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" title="소재지" maxlength="30" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="elctyFcltySpecMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">전기시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">전기시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">전기시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="elctyFcltySpecMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:250px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload">엑셀 다운로드</button>
								<button id="btnAdd">시설추가</button>
								<button id="btnDelete">시설삭제</button>
								<button data-role="showMap" data-gis-layer="gisElecFclty" data-flexi-grid="elctyFcltySpecMngList" data-style="default">맵조회</button>
								<!-- 맵관련 추가 -->
								<button data-role="editMap" data-gis-layer="gisElecFclty">맵편집</button>
								<button id="setFeature" style="display: none;">맵지정</button>
							</td>
						</tr>
					</table>
					
				</div>
			</div>


			<!-- 전기시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<input type="hidden" id="fcltsMngNo">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">전기시설 일반</th>
								<th>시설물관리번호 : <span id="dispfcltsMngNo"></span></th>
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
								<button id="searchGisCodeBtn2" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
							<td><input type="text" size="30" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지　　　　　번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="4" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="7" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td><input id="gisAssetsLocplc" type="text" size="32" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;
								<input type="text" size="5" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM061" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">전 기　시 설 명</th>
							<td><input type="text" size="32" id="prtFcltyNm" maxlength="25" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="21" id="fcltsMngGroupNoNm" disabled="disabled"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　규　격</th>
							<td colspan="3"><input id="prtFcltyStndrd" type="text" size="50" title="시설규격" maxlength="13" /></td>
							<th width="12%" height="17" class="required_text">시　설　단　위</th>
							<td><input id="prtFcltyUnit" type="text" size="32" title="시설단위" maxlength="3" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">변　경　일　자</th>
							<td colspan="5">
								<input id="prtFcltyChangeDt" type="text" class="emdcal" size="11" title="변경일자" />
								<input id="prtFcltyInstlDt" type="hidden" />
							</td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>전기시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">용　　　　　도</th>
							<td><input id="prpos" type="text" size="50" maxlength="33" title="용도" /></td>
							<th width="12%" height="17" class="required_text">수　　　　　량</th>
							<td><input id="qy" type="text" size="50" class="ygpaNumber" title="수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규　　　　　격</th>
							<td><input id="stndrd" type="text" size="50" maxlength="16" title="규격" /></td>
							<th width="12%" height="17" class="required_text">형　　　　　식</th>
							<td><input id="fmt" type="text" size="50" maxlength="33" title="형식" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">용　　　　　량</th>
							<td><input id="capa" type="text" size="50" maxlength="6" title="용량" /></td>
							<th width="12%" height="17" class="required_text">전　　　　　압</th>
							<td><input id="volt" type="text" size="50" maxlength="6" title="전압" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">출　　　　　력</th>
							<td><input id="output" type="text" size="50" maxlength="6" title="출력" /></td>
							<th width="12%" height="17" class="required_text">연 료　소 비 량</th>
							<td><input id="fuelConsum" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="연료소비량" maxlength="10" /> kg/h</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제　작　회　사</th>
							<td><input id="mfcCmpny" type="text" size="50" maxlength="33" title="제작회사" /></td>
							<th width="12%" height="17" class="required_text">제　작　일　자</th>
							<td><input id="mfcDt" type="text" size="11" class="emdcal" title="제작일자" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관　　리　　자</th>
							<td><input id="manager" type="text" size="50" maxlength="6" title="관리자" /></td>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td><input id="instlDt" type="text" size="11" class="emdcal" title="설치일자" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사　용　업　체</th>
							<td colspan="3"><input id="usageEntrps" type="text" size="50" maxlength="33" title="사용업체" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관　로　길　이</th>
							<td><input id="ductLineLt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="관로길이" maxlength="8" /></td>
							<th width="12%" height="17" class="required_text">조 명 탑　높 이</th>
							<td><input id="lightwrHt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="조명탑높이" maxlength="6" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">공　급　전　원</th>
							<td><input id="spplyPwr" type="text" size="50" maxlength="6" title="공급전원" /></td>
							<th width="12%" height="17" class="required_text">공　급　 T　R</th>
							<td><input id="spplyTr" type="text" size="50" maxlength="6" title="공급TR" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">결　선　방　식</th>
							<td><input id="fnlMthd" type="text" size="50" maxlength="6" title="결선방식" /></td>
							<th width="12%" height="17" class="required_text">케 이 블　연 장</th>
							<td><input id="cableExt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="케이블연장" maxlength="8"/></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연　료　탱　크</th>
							<td><input id="fuelTank" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="연료탱크" maxlength="10" /></td>
							<th width="12%" height="17" class="required_text">유　　　　　량</th>
							<td><input id="oilQty" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="유량" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">한 전　변 전 소</th>
							<td colspan="3"><input id="korElecSubstn" type="text" size="50" maxlength="6" title="한전변전소" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">구　간　FROM</th>
							<td><input id="sectionFrom" type="text" size="50" maxlength="6" title="구간 FROM" /></td>
							<th width="12%" height="17" class="required_text">구　간　T　O</th>
							<td><input id="sectionTo" type="text" size="50" maxlength="6" title="구간 TO" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전　주　규　격</th>
							<td colspan="3"><input id="premainStndrd" type="text" size="50" maxlength="20" title="전주규격" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전　주　높　이</th>
							<td><input id="premainHt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" title="전주높이" maxlength="6" /></td>
							<th width="12%" height="17" class="required_text">전　주　수　량</th>
							<td><input id="premainQy" type="text" size="50" class="ygpaNumber" title="전주수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">등 기 구　규 격</th>
							<td><input id="lightappStndrd" type="text" size="50" maxlength="20" title="등기구규격" /></td>
							<th width="12%" height="17" class="required_text">등 기 구　수 량</th>
							<td><input id="lightappQy" type="text" size="50" class="ygpaNumber" title="등기구수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">등 기 구　형 식</th>
							<td colspan="3"><input id="lightappFmt" type="text" size="50" maxlength="6" title="등기구형식" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP　형　식</th>
							<td colspan="3"><input id="lampFmt" type="text" size="50" maxlength="6" title="LAMP형식" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">LAMP　용　량</th>
							<td><input id="lampCapa" type="text" size="50" maxlength="6" title="LAMP용량" /></td>
							<th width="12%" height="17" class="required_text">LAMP　수　량</th>
							<td><input id="lampQy" type="text" size="50" class="ygpaNumber" title="LAMP수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑등기구분류코드</th>
							<td><input id="lightwrLightappClcd" type="text" size="50" maxlength="10" title="조명탑등기구 분류코드" /></td>
							<th width="12%" height="17" class="required_text">조명탑등기구수량</th>
							<td><input id="lightwrLightappQy" type="text" size="50" class="ygpaNumber" title="조명탑등기구수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">조명탑LAMP분류코드</th>
							<td><input id="lightwrLampClcd" type="text" size="50" maxlength="10" title="조명탑 LAMP분류코드" /></td>
							<th width="12%" height="17" class="required_text">조명탑LAMP수량</th>
							<td><input id="lightwrLampQy" type="text" size="50" class="ygpaNumber" title="조명탑 LAMP수량" maxlength="5" /> 개</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기시설물분류코드</th>
							<td colspan="3">
								<input id="elctyFcltsClCd" type="text" size="29" disabled="disabled" />
								<input id="elctyFcltsClCdNm" type="text" size="30" disabled="disabled" />
								<button id="searchFcltsClCd" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="3">
								<input id="archFcltsMngNo" type="text" size="29" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="30" disabled="disabled" />
								<button id="searchArchFcltsMngNo" class="popupButton">선택</button>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3"><input id="loc" type="text" size="135" maxlength="50" title="위치" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비　　　　　고</th>
							<td colspan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="333"></textarea>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<!-- <button id="registLocation">위치등록</button>
					<button id="gotoLocation">위치조회</button> -->
					<button id="btnSave">저장</button>
				</div>
			</div>
			
			<!-- 전기시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="btnSave">저장</button>
							</div>
							<form id="fcltsFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="photoEditItem">
			                                    <option value="D">문서</option>
			                                    <option value="P">사진</option>
			                                    <option value="Z">기타</option>
			                                </select>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td><input id="atchFileSj" type="text" size="45" class="photoEditItem" maxlength="25" /></td>
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