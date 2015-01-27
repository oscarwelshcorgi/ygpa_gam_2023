<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairMng.jsp
  * @Description : 시설물하자보수관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.20  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.20
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyRepairMngVO" method="validateFcltyRepairMngVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<%
/******************************** SCRIPT START ********************************/
%>

<script>
<%
/**
 * @FUNCTION NAME : GamFcltyRepairMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyRepairMngModule() {}

GamFcltyRepairMngModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadComplete = function(params) {
	this._mode = "";
	this._deleteObjFcltsList=[];
	this._deleteDataRepairList=[];
	this._deleteDataFileList=[];
	
	//console.log('GamFcltyRepairMngModule');

	this.$("#fcltyRepairMngList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairMngList.do',
		dataType: "json",
		colModel : [
					{display:"시설물관리그룹", 		name:"fcltsMngGoupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"순번", 				name:"flawRprSeq",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"계약명",			name:"flawRprNm",				width:250, 		sortable:false,		align:"left"},
					{display:"도급업체명",			name:"flawRprEntrpsNm",			width:250, 		sortable:false,		align:"left"},
					{display:"업무구분",			name:"fcltsJobSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자검사구분",		name:"flawExamSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",				width:80, 		sortable:false,		align:"center"},
					{display:"하자발생일자",		name:"flawOccrrncDt",			width:80, 		sortable:false,		align:"center"},
					{display:"하자보수유형",		name:"flawRprTyNm",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수기간",		name:"flawRprTerm",				width:160, 		sortable:false,		align:"center"},
					{display:"하자보수금액", 		name:"flawRprAmt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"하자보수완료여부", 	name:"flawRprComptYn",			width:120, 		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});
	
	
	this.$("#flawRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawRprObjFcltsF.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"rnum",			width:100,		sortable:false,		align:"center"},
					{display:"대상시설물",		name:"prtFcltyNm",		width:250,		sortable:false,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",		width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",		width:100,		sortable:true,		align:"center"},
					{display:"하자검사결과",	name:"flawExamResult",	width:350,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",				width:350,		sortable:true,		align:"left"}
			],
		height: "110"
	});
	
	
 	this.$("#fcltyRepairMngList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});
 	
 	this.$("#fcltyRepairMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
	});
 	
 	this.$("#flawRprObjFcltsF").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyObjDataChanged();
	});
 	
 	
 	this.$(".objFcltsEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.objFcltsDataChanged(event.target);
	});
	
	this.applySelectYear();
	
};


<%
/**
 * @FUNCTION NAME : applySelectYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	for(var i = toYear;i>=2000;i--){
		this.$("#enforceYear").append("<option value='" + i + "'>" + i + "년</option>");
	}
};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.onSubmit = function(){
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#flawRprObjFcltsF').flexEmptyData();
	this.$('#gamObjFcltsForm input').val('');
	this.$('#gamObjFcltsForm textarea').val('');
	this.makeDivValues('#gamObjFcltsDetailForm',{});

	this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairMngForm');
	this.$('#fcltyRepairMngList').flexOptions({params:searchOpt}).flexReload();
	
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : DATA LOAD (DETAIL)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.loadDetail = function(){
	var row = this.$('#fcltyRepairMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'flawRprSeq', value: row['flawRprSeq'] }
	               ];
	
	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyRepairMngDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#fcltyRepairMngListVO', result.result);
			
			// tabs3 그리드 리로드
			module.makeDivValues('#gamObjFcltsDetailForm',result.result);
			module.$('#flawRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			module.fillAtchFileList(searchVO);
		}else{
			module.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		}
    });
};


<%
/**
 * @FUNCTION NAME : fillAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트를 select element에 채워넣기.
 * @PARAMETER     : 
 *   1. searchVO
 *     1-1. fcltsJobSe : 시설물업무구분
 *     1-2. fcltsMngGroupNo : 시설물 관리그룹번호
 *     1-3. mntnRprSeq : 유지보수 순번
**/
%>
GamFcltyRepairMngModule.prototype.fillAtchFileList = function(searchVO) {
	this.doAction('/fcltyMng/selectFcltyRepairFileList.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.$('#fcltyRepairFileList option').remove();
			module.$('#fcltyRepairFileList').append('<option value="">선택</option>');
			$.each(result.resultList, function(){
				module.$('#fcltyRepairFileList').append('<option value="' + this.atchFileNmPhysicl + '">' + this.atchFileNmLogic + '</option>');
			});
		}else{
			module.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		}
    });
};


<%
/**
 * @FUNCTION NAME : imgPreview
 * @DESCRIPTION   : 선택한 첨부파일이 이미지이면 미리보기 보여주는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.imgPreview = function(){

	var selImg = this.$('#fcltyRepairFileList').val();
	if(selImg) {
		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var ext = selImg.substring(selImg.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
			this.$('#previewHidden').append('<div id="'+this.getId("previewDialog")+'"><img id="'+this.getId("previewImage")+'" src=""/></div>');
			var imgURL = this.getPfPhotoUrl(selImg);
			this.$("#previewImage").attr("src", imgURL);
			
			this.$("#previewImage").bind('load', {module: this},function(event){
				event.data.module.$('#previewDialog').dialog({
					modal: true,
					maxWidth: 800,
					maxHeight: 600,
					resizable: false,
					draggable: true,
					width: 'auto',
					title: '이미지미리보기',
					buttons:[{text:"close", click: function() { $(this).dialog('close'); }}]
				});
			});   
			
		}else{
			this.$("#previewImage").removeAttr("src");
			alert('미리보기는 이미지파일만 가능합니다.');
		}
	}else{
		alert('미리보기할 파일을 선택하세요.');
	}
	
};


<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : tabs1에서 추가 버튼 클릭시
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.addData = function() {

	this._mode="insert";
	
	// tabs2 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();
	
	// tabs3 초기화
	this.$("#flawRprObjFcltsF").flexEmptyData();
	
	this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});

};


<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 선택 DATA 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltyRepairMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("삭제하시겠습니까?")){
		row = row[0];
		var inputVO = { 'fcltsJobSe': row['fcltsJobSe'],'fcltsMngGroupNo': row['fcltsMngGroupNo'],'flawRprSeq': row['flawRprSeq'] };
	 	this.doAction('/fcltyMng/deleteFcltyRepairMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


<%
/**
 * @FUNCTION NAME : applyObjDataChanged
 * @DESCRIPTION   : 하자보수대상시설물 그리드 선택시 하위폼 데이타 처리
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.applyObjDataChanged = function(){
	
	var row = this.$('#flawRprObjFcltsF').selectedRows();
	row = row[0];
	
	if(row['_updtId']!='I'){
		this.$('#searchFcltsMngNo').hide();
	}else{
		this.$('#searchFcltsMngNo').show();
	}
	
	this.$('#oFcltsMngNo').val(row['fcltsMngNo']);
	this.$('#prtFcltyNm').val(row['prtFcltyNm']);
	this.$('#oFlawExamDt').val(row['flawExamDt']);
	this.$('#oFlawEnnc').val(row['flawEnnc']);
	this.$('#oFlawExamResult').val(row['flawExamResult']);
	this.$('#oRm').val(row['rm']);

};


<%
/**
 * @FUNCTION NAME : objFcltsDataChanged
 * @DESCRIPTION   : 하자보수대상시설물 하위폼 정보변경시 그리드 적용
 * @PARAMETER     : target
**/
%>
GamFcltyRepairMngModule.prototype.objFcltsDataChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#flawRprObjFcltsF').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#oFcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}
		
		if(this.$('#prtFcltyNm').is(target)) {
			row['prtFcltyNm'] = $(target).val();
			changed=true;
		}

		if(this.$('#oFlawExamDt').is(target)) {
			row['flawExamDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#oFlawEnnc').is(target)) {
			row['flawEnnc'] = $(target).val();
			changed=true;
		}
		if(this.$('#oRm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}
		if(this.$('#oFlawExamResult').is(target)) {
			row['flawExamResult'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#flawRprObjFcltsF").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#flawRprObjFcltsF').flexUpdateRow(rowid, row);
	}
};


<%
/**
 * @FUNCTION NAME : addObjFcltsItem
 * @DESCRIPTION   : 하자보수 대상 시설물 추가
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.addObjFcltsItem = function() {
	this.$('#gamObjFcltsForm :input').val('');
	this.$('#searchFcltsMngNo').show();
	this.$("#flawRprObjFcltsF").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'flawRprSeq':'', 'fcltsMngNo':'', 'flawEnnc':'', 'flawExamDt':'', 'flawExamResult':'', 'rm':''});
	var allRows = this.$('#flawRprObjFcltsF').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#flawRprObjFcltsF").selectRowId(selRowId);	
};


<%
/**
 * @FUNCTION NAME : delObjFcltsItem
 * @DESCRIPTION   : 하자보수 대상 시설물 데이터 삭제
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.delObjFcltsItem = function() {
	var rows = this.$("#flawRprObjFcltsF").selectedRows();
    if(rows.length == 0){
        alert("하자보수대상 시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#flawRprObjFcltsF").selectedRowIds().length>0) {
    	for(var i=this.$("#flawRprObjFcltsF").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#flawRprObjFcltsF").flexGetRow(this.$("#flawRprObjFcltsF").selectedRowIds()[i]);
    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteObjFcltsList[this._deleteObjFcltsList.length] = row;
			}
        	this.$("#flawRprObjFcltsF").flexRemoveRow(this.$("#flawRprObjFcltsF").selectedRowIds()[i]);
        	this._edited=true;
		}
    	alert("삭제되었습니다.");
	}
    this.$("#gamObjFcltsForm").find(":input").val("");
};


<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : DATA 저장
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.saveData = function() {
	
	if(!validateFcltyRepairMngVO(this.$("#fcltyRepairMngListVO")[0])){
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
		return;
	}
	
	var inputVO = this.makeSaveParam();

	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyRepairMng.do', inputVO, function(module, result) {
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyRepairMng.do', inputVO, function(module, result) {
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
GamFcltyRepairMngModule.prototype.makeSaveParam = function() {
	
	var all_rows = this.$('#flawRprObjFcltsF').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		all_rows[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		all_rows[i]["flawRprSeq"] = this.$("#flawRprSeq").val();
	}
	
	var inputVO = [];
 	inputVO[inputVO.length] = {name: 'fcltyRepairMngListVO', value :JSON.stringify(this.makeFormArgs("#fcltyRepairMngListVO",'object')) };
 	inputVO[inputVO.length] = {name: 'insertObjList', 		 value: JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'I'}])) };
 	inputVO[inputVO.length] = {name: 'insertRepairFileList', value :JSON.stringify(this.makeSelectArgs("#fcltyRepairFileList")) };
 	
 	// 아래는 수정시에만 필요한 데이타 형식
 	if(this._mode == "modify") {
		inputVO[inputVO.length]={name: 'updateObjList', value :JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	 	inputVO[inputVO.length]={name: 'deleteObjList', value: JSON.stringify(this._deleteObjFcltsList) };
 	}
 	
	return inputVO;
};


<%
/**
 * @FUNCTION NAME : makeSelectArgs
 * @DESCRIPTION   : 셀렉트 option value, text json 생성함수 - 여기서는 첨부파일
 * @PARAMETER     : SELECT_ID
**/
%>
GamFcltyRepairMngModule.prototype.makeSelectArgs = function(selId) {
	var optionValues = [];
	this.$(selId + ' option').each(function() {
		if($(this).val()){
			optionValues.push({"atchFileNmPhysicl":$(this).val(), "atchFileNmLogic":$(this).eq(0).text()});
		}
	});

	return optionValues;
};


<%
/**
 * @FUNCTION NAME : atchFileUpload
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.atchFileUpload = function() {
	this.uploadPfPhoto('uploadPhoto', function(module, result) {
		$.each(result, function(){
			module.$('#fcltyRepairFileList').append('<option value="' + this.physcalFileNm + '">' + this.logicalFileNm + '</option>');
		});
	}, '유지보수관리 첨부파일 업로드');	
};


<%
/**
 * @FUNCTION NAME : atchFileRemove
 * @DESCRIPTION   : 첨부파일제거
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.atchFileRemove = function() {
	if(this.$('#fcltyRepairFileList').val() != '') {
		this.$('#fcltyRepairFileList option[value="' + this.$('#fcltyRepairFileList').val() + '"]').remove();
	}
	else {
		alert('첨부파일을 선택해주십시오.');
	} 
};


<%
/**
 * @FUNCTION NAME : downloadFileData
 * @DESCRIPTION   : 파일다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.downloadFileData = function() {
	var selectFilePhysicl = this.$('#fcltyRepairFileList').val();
	var selectFileLogic = this.$('#fcltyRepairFileList').find('option:selected').text();

	if(selectFilePhysicl) {
		this.downPfPhoto(selectFilePhysicl, selectFileLogic);
	}else{
		alert('다운로드할 파일을 선택하세요.');
	}
};


<%
/**
 * @FUNCTION NAME : fillTitleData
 * @DESCRIPTION   : 대상시설물 탭에 상단 내용 채우는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.fillTitleData = function() {
	var changData = this.makeFormArgs('#fcltyRepairMngListVO');
	this.makeDivValues('#gamObjFcltsDetailForm',changData);
	
	// 셀렉트박스는 한글처리
	var fcltsJobSeNm = this.$('#fcltsJobSe').find('option:selected').text();
	var flawExamSeNm = this.$('#flawExamSe').find('option:selected').text();
	
	this.$('#objFcltsJobSeNm').text(fcltsJobSeNm);
	this.$('#objFlawExamSeNm').text(flawExamSeNm);

};


<%
/**
 * @FUNCTION NAME : existRepairFcltsItem
 * @DESCRIPTION   : 유지보수 대상시설물 존재유무 체크
 * @PARAMETER     : 
 * 		1. fcltsMngNo : 시설물관리번호
**/
%>
GamFcltyRepairMngModule.prototype.existRepairFcltsItem = function(fcltsMngNo) {
	var rows = this.$('#flawRprObjFcltsF').flexGetData();
	var result = false;
	if(rows.length > 0) {
		var row = null;
		for(var i=0; i<rows.length; i++) {
			row = rows[i];
			if(row['fcltsMngNo'] == fcltsMngNo) {
				result = true;
				break;
			}
		}
	}
	return result;
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairMngModule.prototype.downloadExcel = function() {
	
	var rowCount = this.$('#fcltyRepairMngList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#fcltyRepairMngList').flexExcelDown('/fcltyMng/selectFcltyRepairMngListExcel.do');

};



<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyRepairMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
		// 대상시설물 추가
		case "addObjItemBtn":
			this.addObjFcltsItem();
		break;
		
		// 대상시설물 삭제
		case "delObjItemBtn":
			this.delObjFcltsItem();
		break;
		
		// 추가
		case "addBtn":
			this.addData();
		break;

		// 저장
		case "saveBtn":
			this.saveData();
		break;

		// 삭제
		case "deleteBtn":
			this.deleteData();
		break;
		
		// 이미지미리보기
		case "btnPreviewFile":
			this.imgPreview();
		break;
		
		// 파일업로드
		case "btnUploadFile":
			this.atchFileUpload();
		break;
		
		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
		break;
		
		// 파일삭제
		case "btnRemoveFile":
			this.atchFileRemove();
		break;
		
		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel();
		break;
		
		// 대상시설물
		case "searchFcltsMngNo":
			this.doExecuteDialog("selectFcltsMngNo", "대상시설물 관리번호", '/popup/showFcltsMngNo.do', {}, {'fcltsJobSe' : this.$('#fcltsJobSe').val()});
		break;
		
		// 시설물관리그룹
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;
		
		// 계약번호
		case "ctrtNoPopupBtn":
			this.doExecuteDialog("selectCtrtNo", "계약번호", '/popup/popupCtrtNo.do', {});
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
GamFcltyRepairMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._mode == 'modify') {
		this.loadDetail();
	}

	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			} 

			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);
			}
			if(this._mode=="modify"){
				this.$("#searchFcltsMngGroupNo").hide();
				this.$("#fcltsJobSe").disable();
			}
		break;
		
		case "tabs3":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			} 
			if(this._mode=="modify"){
				// tabs2에서 수정사항발생시 반영 
				this.fillTitleData();
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
GamFcltyRepairMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		
		case "selectFcltsMngNo":
			if(this.existRepairFcltsItem(value['fcltsMngNo'])) {
				alert('대상시설물이 이미 존재합니다.');
			} else {
				this.$("#oFcltsMngNo").val(value["fcltsMngNo"]);
				this.$("#prtFcltyNm").val(value["prtFcltyNm"]);
				
				// 대상시설물 팝업에서 상태값 변경시 그리드 적용
				this.$(".objFcltsEditItem").trigger("change");
			}
		break;
	
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGoupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		case "selectCtrtNo":
			this.$("#ctrtNo").val(value["ctrtNo"]);
			this.$("#flawRprNm").val(value["ctrtNm"]);
			this.$("#flawRprEntrpsNm").val(value["entrpsNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyRepairMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyRepairMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe" title="시설물업무구분">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>계약명</th>
							<td><input type="text" id="sFlawRprNm" size="50" title="하자보수명" /></td>
							<td rowspan="2"><button class="buttonSearch" title="조회">조회</button></td>
						</tr>
						<tr>
							<th>하자검사구분</th>
							<td>
								<select id="sFlawExamSe" title="하자검사구분">
									<option value="">선택</option>
									<option value="1">상반기</option>
									<option value="2">하반기</option>
								</select>
							</td>
							<th>하자검사기간</th>
							<td>
								<input id="sFlawRprStartDtFr" type="text" class="emdcal" size="15" title="하자검사일 검색시작일" /> ~ <input id="sFlawRprStartDtTo" type="text" class="emdcal" size="15" title="하자검사일 검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>




	<div class="emdPanel fillHeight">
		<div id="fcltyRepairMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">하자보수대상시설물</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:250px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button data-role="printPage" data-search-option="searchFcltyRepairMngForm" data-url='/fcltyMng/selectFcltyRepairCheckResultPrint.do'>하자검사결과인쇄</button>
								<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
								<button id="addBtn" class="buttonAdd">　　추　가　　</button>
								<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
							</td>
						</tr>
					</table>
					
				</div>
			</div>


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyRepairMngListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="18" id="fcltsMngGroupNo" disabled="disabled" data-required="true" title="시설물관리그룹넘버" />
								<input type="text" size="36" id="fcltsMngGoupNoNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th width="15%" height="23" class="required_text">시행년도</th>
							<td>
								<select id="enforceYear" title="시행년도">
									<option value="">선택</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">시설물업무구분</th>
							<td>
								<select id="fcltsJobSe" data-required="true" title="시설물업무구분">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<input type="text" size="20" id="ctrtNo" disabled="disabled" title="계약번호"/>-
								<input type="text" size="33" id="flawRprNm" disabled="disabled" title="계약명"/>
								<button id="ctrtNoPopupBtn" class="popupButton">선택</button>
							</td>
							<th width="15%" height="23" class="required_text">도급업체명</th>
							<td colspan="3"><input id="flawRprEntrpsNm" type="text" size="53" title="도급업체명" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">순번</th>
							<td><input type="text" size="20" id="flawRprSeq" disabled="disabled" title="하자보수순번" /></td>
							<th>하자검사구분</th>
							<td>
								<select id="flawExamSe" title="하자검사구분">
									<option value="">선택</option>
									<option value="1">상반기</option>
									<option value="2">하반기</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">하자검사일자</th>
							<td colspan="3"><input id="flawExamDt" type="text" size="20" title="하자검사일자" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자유무</th>
							<td>
								<select id="flawEnnc" title="하자유무">
									<option value="">선택</option>
									<option value="Y">유</option>
									<option value="N">무</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">하자발생일자</th>
							<td><input id="flawOccrrncDt" type="text" size="15" title="하자발생일자" class="emdcal" /></td>
							<th width="15%" height="23" class="required_text">하자보수기간</th>
							<td colspan="3">
								<input id="flawRprStartDt" type="text" size="20" title="하자보수시작일자" class="emdcal" /> ~ 
								<input id="flawRprEndDt" type="text" size="20" title="하자보수종료일자" class="emdcal" />
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수유형</th>
							<td>
								<select id="flawRprTy" title="하자보수유형">
									<option value="">선택</option>
									<option value="O">자체</option>
									<option value="S">용역</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">하자보수금액</th>
							<td><input id="flawRprAmt" type="text" size="20" title="하자보수금액" class="ygpaNumber" maxlength="16" /> 원</td>
							<th width="15%" height="23" class="required_text">하자보수완료여부</th>
							<td colspan="3">
								<select id="flawRprComptYn" title="하자보수완료여부">
									<option value="">선택</option>
									<option value="Y">완료</option>
									<option value="N">미완료</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수내용</th>
							<td colspan="7"><textarea id="flawRprContents" cols="143" rows="5" title="하자보수내용" maxlength="1333"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">하자보수결과</th>
							<td colspan="7"><textarea id="flawExamResult" cols="143" rows="5" title="하자보수결과" maxlength="1333"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">비고</th>
							<td colspan="7"><input id="rm" type="text" size="145" title="비고" maxlength="333" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button data-role="printPage" data-search-option="fcltyRepairMngListVO" data-url='/fcltyMng/selectFcltyRepairCheckReportPrint.do'>하자검사조서인쇄</button>
					<button data-role="printPage" data-search-option="fcltyRepairMngListVO" data-url='/fcltyMng/selectFcltyRepairExpireCheckReportPrint.do'>하자만료검사조서인쇄</button>
					<button id="addBtn" class="buttonAdd">　　추　가　　</button>
					<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
					<button id="saveBtn" class="buttonSave">  저 장  </button>
				</div>
			</div>
			
			<!-- 하자보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">하자보수 상세내역</th>
							</tr>
						</tbody>
					</table>
					<form id="gamObjFcltsDetailForm">
						<table class="detailPanel"  style="width:100%;">
							<tbody>
								<tr>
									<th>시설물관리그룹</th>
									<td><span id="fcltsMngGoupNoNm"></span></td>
									<th>업무구분</th>
									<td><span id="objFcltsJobSeNm"></span></td>
									<th>하자검사구분</th>
									<td><span id="objFlawExamSeNm"></span></td>
								</tr>
								<tr>
									<th>계약번호</th>
									<td><span id="ctrtNo"></span></td>
									<th>계약명</th>
									<td><span id="flawRprNm"></span></td>
									<th>도급업체명</th>
									<td><span id="flawRprEntrpsNm"></span></td>
								</tr>
							</tbody>
						</table>
					</form>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">하자보수 대상시설물</th>
							</tr>
						</tbody>
					</table>
				</div>

				<table id="flawRprObjFcltsF" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addObjItemBtn" class="buttonAdd">  추 가 </button>
		            <button id="delObjItemBtn" class="buttonDelete">  삭 제  </button>
				</div>
				<div class="emdControlPanel">
					<form id="gamObjFcltsForm">
						<table class="searchPanel">
							<tbody>
								<tr>
			                        <th>대상시설물</th>
			                        <td>
			                        	<input id="oFcltsMngNo" type="text" style="width: 130px;" title="시설물관리번호" maxlength="20" class="objFcltsEditItem" disabled="disabled"/>
			                        	<input id="prtFcltyNm" type="text" style="width: 175px;" title="시설명" maxlength="20" class="objFcltsEditItem" disabled="disabled"/>
			                        	<button id="searchFcltsMngNo" class="popupButton">선택</button>
			                        </td>
									<th>하자검사일자</th>
			                        <td><input id="oFlawExamDt" type="text" style="width: 100px;" maxlength="10" class="emdcal objFcltsEditItem"/></td>
			                        <th>하자유무</th>
			                        <td>
			                        	<select id="oFlawEnnc" class="objFcltsEditItem">
			                        		<option value="">선택</option>
			                        		<option value="Y">유</option>
			                        		<option value="N">무</option>
			                        	</select>
			                        </td>
								</tr>
								<tr>
									<th>하자검사결과</th>
									<td colspan="7"><textarea id="oFlawExamResult" cols="149" rows="2" class="objFcltsEditItem" maxlength="1333"></textarea></td>
								</tr>
								<tr>
									<th>비고</th>
									<td colspan="7"><input id="oRm" type="text" size="151" maxlength="333" class="objFcltsEditItem"/></td>
								</tr>
								<tr>
									<th height="18" class="required_text">첨부파일</th>
									<td>
										<select id="fcltyRepairFileList">
											<option value="">선택</option>
										</select> 
									</td>
									<td colspan="6" style="text-align:right;">
										<button id="btnPreviewFile">첨부파일 미리보기</button> 
										<div id="previewHidden" style="display: none;"></div>
										<button id="btnUploadFile">업로드</button> 
										<button id="btnDownloadFile">다운로드</button> 
										<button id="btnRemoveFile" class="buttonDelete"> 삭 제 </button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<button id="addBtn" class="buttonAdd">　　추　가　　</button>
					<button id="deleteBtn" class="buttonDelete">　　삭 제　　</button>
					<button id="saveBtn" class="buttonSave">  저 장  </button>
				</div>
			</div>
		</div>
	</div>
</div>