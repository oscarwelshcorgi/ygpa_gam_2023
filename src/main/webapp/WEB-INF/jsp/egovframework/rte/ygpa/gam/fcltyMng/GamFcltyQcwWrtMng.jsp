<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMngMng.jsp
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

<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyQcwWrtMngModule() {
}

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyQcwWrtMngModule.prototype.loadComplete = function(params) {
	
	console.log('GamFcltyQcwWrtMngModule');
	
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#qcMngDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"관리그룹번호",	name:"fcltsMngGroupNo",		width:100,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSe",			width:60,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:120,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSe",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:120,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:120,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:120,		sortable:false,		align:"left"},
					{display:"점검진단예산",	name:"qcInspBdgt",			width:90,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검진단금액",	name:"qcInspAmt",			width:90,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"상태평가등급",	name:"sttusEvlLvl",			width:90,		sortable:false,		align:"center"},
					{display:"조치구분",		name:"actionSe",			width:60,		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	this._cmd = '';
	this._deleteAtchFileList = null;
	this._deleteObjFcltsList = null;
	this._deleteResultItemList = null;
	
	this.$("#qcMngDtlsList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});
	
	this.$("#qcMngDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#fcltyQcwWrtMngTab").tabs("option", {active: 1});
	});

	this.$("#qcMngObjFcltsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"left"},
					{display:"점검진단구분",	name:"qcInspSe",	width:90,		sortable:true,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",	width:100,		sortable:true,		align:"center"},
					{display:"점검자",		name:"inspector",	width:100,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",			width:350,		sortable:true,		align:"left"}
			],
		height: "auto"
	});
	
	this.$("#qcMngResultItemList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngResultItemList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"seq",				width:90,		sortable:true,		align:"center"},
					{display:"점검항목코드",	name:"qcItemCd",		width:100,		sortable:true,		align:"center"},
					{display:"점검항목명",		name:"qcItemNm",		width:150,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChk",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#qcMngAtchFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngAtchFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"제목",		name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:200,		sortable:true,		align:"left"},
					{display:"작성일시",	name:"atchFileWritingDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#qcMngAtchFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});
	
	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});

	this.fillSelectBoxYear('#enforceYear'); //시행년도에 년수 채워넣기	
};

//2000년부터 현재년도까지 select 박스에 채워넣는 함수.
GamFcltyQcwWrtMngModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '</option>');
	}
};

//화면 및 데이터 초기화
GamFcltyQcwWrtMngModule.prototype.initDisplay = function() {
	this._deleteAtchFileList = [];
	this._deleteObjFcltsList = [];
	this._deleteResultItemList = [];
	
	this.$("#fcltyQcwWrtMngVO :input").val("");
	this.$("#qcMngAtchFileForm :input").val("");
	
	this.$('#qcMngObjFcltsList').flexEmptyData();
	this.$('#qcMngResultItemList').flexEmptyData();
	this.$('#qcMngAtchFileList').flexEmptyData();
	
	this.$("#previewImage").attr("src", "#");
	
	if(this._cmd == "insert") {
		this.$("#fcltsMngGroupNo").enable();
		this.$("#fcltsJobSe").enable();
		this.$("#btnSearchFcltsMngGroup").show();
		this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 1});		
	} else if (this._cmd == "modify") {
		this.$("#fcltsMngGroupNo").disable();
		this.$("#fcltsJobSe").disable();
		this.$("#btnSearchFcltsMngGroup").hide();
	} else {
		this.$("#fcltsJobSe").enable();
		this.$("#btnSearchFcltsMngGroup").show();
		this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
	}
	
};

GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {
	this.loadData();
};

//점검관리내역 조회
GamFcltyQcwWrtMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcwWrtMngForm");
	this.$("#qcMngDtlsList").flexOptions({params:searchOpt}).flexReload();
};

//점검관리내역 삽입
GamFcltyQcwWrtMngModule.prototype.insertData = function() {
	var data = this.makeFormArgs("#fcltyQcwWrtMngVO");
 	this.doAction('/fcltyMng/insertQcMngDtls.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
 			module.$("#qcMngSeq").val(result.qcMngSeq);
			module.saveQcMngObjFclts();
			module.saveQcMngResultItem();
			module.saveAtchFile();
			module.loadData();
 		}
 		alert(result.resultMsg);
 	});	
};

//점검관리내역 수정
GamFcltyQcwWrtMngModule.prototype.updateData = function() {
	var data = this.makeFormArgs("#fcltyQcwWrtMngVO");
 	this.doAction('/fcltyMng/updateQcMngDtls.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
			module.saveQcMngObjFclts();
			module.saveQcMngResultItem();
			module.saveAtchFile();
			module.loadData();
 		}
 		alert(result.resultMsg);
 	});	
};

//점검관리내역 삽입 및 수정저장
GamFcltyQcwWrtMngModule.prototype.saveData = function() {
	//if(!validateFcltyManageVO(this.$('#fcltyManageVO')[0])){ 		
	//	return;
	//}
 	if(this._cmd == "insert") {
 		this.insertData();
	} else if (this._cmd == "modify") { 
		this.updateData();
	}			
};

//점검관리내역 삭제
GamFcltyQcwWrtMngModule.prototype.deleteData = function() {
	var rows = this.$("#qcMngDtlsList").selectedRows();
	if(rows.length <= 0){
		alert("삭제할 점검관리내역을 선택하십시오.");
		return;
	}
	if(confirm("점검관리내역을 삭제하시겠습니까?")) {
		var row = rows[0];
		var opts = [
	           		{name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'qcMngSeq', value: row['qcMngSeq'] }
		           ];
	 	this.doAction('/fcltyMng/deleteQcMngDtls.do', opts, function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

//점검관리내역 데이터 조회
GamFcltyQcwWrtMngModule.prototype.loadDetailData = function() {
	this.initDisplay();
	var rows = this.$('#qcMngDtlsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcMngDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltyQcwWrtMngVO', result.result);
				module.$("#qcMngObjFcltsList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngAtchFileList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngResultItemList").flexOptions({params:opts}).flexReload();
			}
			else {
				this._cmd="";
				alert(result.resultMsg);
			}
		});	
	}
};

//점검관리 대상시설물 수정 팝업
GamFcltyQcwWrtMngModule.prototype.showModifyQcMngObjFcltsList = function() {
	var allRows = this.$('#qcMngObjFcltsList').flexGetData();
	this.doExecuteDialog("modifiedQcMngObjFclts", "점검관리대상시설물목록", '/popup/showQcMngObjFcltsPopup.do', {}, allRows);
};

//점검관리 대상시설물 병합저장
GamFcltyQcwWrtMngModule.prototype.saveQcMngObjFclts = function() {
	var dataList = this.$("#qcMngObjFcltsList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		dataList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		dataList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngObjFcltsList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngObjFcltsList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteAtchFileList) };
    this.doAction('/fcltyMng/mergeQcMngObjFclts.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteObjFcltsList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngObjFcltsList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//점검관리 결과항목 수정 팝업
GamFcltyQcwWrtMngModule.prototype.showModifyQcMngResultItemList = function() {
	var allRows = this.$('#qcMngResultItemList').flexGetData();
	this.doExecuteDialog("modifiedQcMngResultItem", "점검관리결과항목목록", '/popup/showQcMngResultItemPopup.do', {}, allRows);
};

//점검관리 결과항목 병합저장
GamFcltyQcwWrtMngModule.prototype.saveQcMngResultItem = function() {
	var dataList = this.$("#qcMngResultItemList").flexGetData();
	for(var i=0; i<dataList.length; i++) {
		dataList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		dataList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		dataList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngResultItemList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngResultItemList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteResultItemList) };
    this.doAction('/fcltyMng/mergeQcMngResultItem.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteObjFcltsList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngResultItemList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//첨부파일 정보 변화 처리
GamFcltyQcwWrtMngModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#qcMngAtchFileList').selectedRows();
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
		if(this.$('#atchFileWritingDt').is(target)) {
			row['atchFileWritingDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#qcMngAtchFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#qcMngAtchFileList').flexUpdateRow(rowid, row);
	}
};

//첨부파일목록 병합저장
GamFcltyQcwWrtMngModule.prototype.saveAtchFile = function() {
	var fileList = this.$("#qcMngAtchFileList").flexGetData();
	for(var i=0; i<fileList.length; i++) {
		fileList[i]["fcltsMngGroupNo"] = this.$("#fcltsMngGroupNo").val();
		fileList[i]["fcltsJobSe"] = this.$("#fcltsJobSe").val();
		fileList[i]["qcMngSeq"] = this.$("#qcMngSeq").val();		
	}
    var inputVO=[];
    inputVO[inputVO.length]={name: 'updateList', value: JSON.stringify(this.$('#qcMngAtchFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
    inputVO[inputVO.length]={name: 'insertList', value: JSON.stringify(this.$('#qcMngAtchFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
    inputVO[inputVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteAtchFileList) };
    this.doAction('/fcltyMng/mergeQcMngAtchFile.do', inputVO, function(module, result) {
        if(result.resultCode == 0){
			module._deleteAtchFileList = [];				    	
			var opts = [
		           		{name: 'sFcltsMngGroupNo', value: module.$("#fcltsMngGroupNo").val() },
		           		{name: 'sFcltsJobSe', value: module.$("#fcltsJobSe").val() },
		           		{name: 'sQcMngSeq', value: module.$("#qcMngSeq").val() }
			           ];
			module.$("#qcMngAtchFileList").flexOptions({params:opts}).flexReload();
        }
        else {
        	alert(result.resultMsg);
        }
    });	
};

//첨부파일내역 삭제
GamFcltyQcwWrtMngModule.prototype.removeAtchFileItem = function() {
	var rows = this.$("#qcMngAtchFileList").selectedRows();
    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }
    if(this.$("#qcMngAtchFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#qcMngAtchFileList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#qcMngAtchFileList").flexGetRow(this.$("#qcMngAtchFileList").selectedRowIds()[i]);
            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteAtchFileList[this._deleteAtchFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#qcMngAtchFileList").flexRemoveRow(this.$("#qcMngAtchFileList").selectedRowIds()[i]);
		}
    	this.$("#previewImage").attr("src","#");
    	alert("삭제되었습니다.");
	}
    this.$("#qcMngAtchFileForm").find(":input").val("");
};

//첨부파일 항목선택
GamFcltyQcwWrtMngModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#qcMngAtchFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#qcMngAtchFileForm input").val('');
		this.makeFormValues("#qcMngAtchFileForm", row);
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				var imgURL = this.getPfPhotoUrl(filenm);
				//this.$("#previewImage").fadeIn(400, function() {
			    	this.$("#previewImage").attr("src", imgURL);
			    //});
			}else{
				this.$("#previewImage").attr(src, "#");
			}
		}
	}
}

//첨부파일 업로드
GamFcltyQcwWrtMngModule.prototype.uploadAtchFileItem = function() {
	this.$('#atchFileSe').val('D');
	this.uploadPfPhoto("uploadPhoto", function(module, result) {
		$.each(result, function(){
			module.$("#qcMngAtchFileList").flexAddRow({_updtId:'I', fcltsMngGroupNo:'', fcltsJobSe:'', qcMngSeq:'', atchFileSe:'D', atchFileSeNm :'문서', atchFileNmLogic:this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritingDt:''});
		});
	}, "점검관리 첨부파일 업로드");	
};

//첨부파일 다운로드
GamFcltyQcwWrtMngModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#qcMngAtchFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcwWrtMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		//점검관리내역 목록 조회
		case "searchBtn":
			this._cmd = '';
			this.initDisplay();
			this.loadData();
			break;
			
		//점검관리내역 추가			
		case "btnAdd" :
			this._cmd = 'insert';
			this.initDisplay();
			break;
			
		//점검관리내역 삭제	
		case "btnDelete" :
			this.deleteData();
			break;
			
		//점검관리내역 저장			
		case "btnSave" :
			this.saveData();
			break;
		
		//첨부파일업로드
		case "btnUploadFile":
			this.uploadAtchFileItem();
			break;
			
		//첨부파일다운로드			
		case "btnDownloadFile":
			this.downloadAtchFileItem();
			break;
						
		//첨부파일삭제
		case "btnRemoveFile":
			this.removeAtchFileItem();
			break;
			
		//시설물관리그룹선택
		case "btnSearchFcltsMngGroup":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물관리그룹", '/popup/showFcltsMngGroup.do', {});
			break;
			
		//점검관리대상시설물 수정
		case "btnModifyQcMngObjFclts":
			this.showModifyQcMngObjFcltsList();
			break;
			
		//점검관리결과항목 수정			
		case "btnModifyQcMngResultItem":
			this.showModifyQcMngResultItemList();
			break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyQcwWrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.loadDetailData();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
			} 
			break;
		case "tabs3":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
			} 
			break;
		case "tabs4":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
			} 
			break;
		case "tabs5":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
			} 
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		//시설물 관리 그룹
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
		//점검관리 대상시설물 수정			
		case "modifiedQcMngObjFclts":
			this.$("#qcMngObjFcltsList").flexEmptyData();
			this.$("#qcMngObjFcltsList").flexAddData({resultList: value["resultList"] });
			this._deleteObjFcltsList = value["deleteObjFcltsList"];
			break;
		//점검관리 결과항목 수정			
		case "modifiedQcMngResultItem":
			this.$("#qcMngResultItemList").flexEmptyData();
			this.$("#qcMngResultItemList").flexAddData({resultList: value["resultList"] });
			this._deleteResultItemList = value["deleteResultItemList"];
			break;
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyQcwWrtMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyQcwWrtMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>점검진단구분</th>
							<td>
								<select id="sQcInspSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검기간</th>
							<td>
								<input id="sQcBeginDt" type="text" class="emdcal" size="15" /> ~ <input id="sQcEndDt" type="text" class="emdcal" size="15" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyQcwWrtMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물점검목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물점검내역</a></li>
				<li><a href="#tabs3" class="emdTab">점검관리대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">점검관리결과</a></li>
				<li><a href="#tabs5" class="emdTab">점검관리첨부파일</a></li>
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcMngDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd">점검추가</button>
					<button id="btnDelete">점검삭제</button>
				</div>
			</div>

			<!-- 시설물점검내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcwWrtMngVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="14" id="fcltsMngGroupNo" maxlength="14" />
								<input type="text" size="40" id="fcltsMngGroupNm" disabled="disabled"/>
								<button id="btnSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<input type="text" size="10" id="qcMngSeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시설물업무구분</th>
							<td>
								<select id="fcltsJobSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
                                </select>
							</td>
							<th width="12%" height="17">점검관리명</th>
							<td colspan="3">
								<input type="text" size="60" id="qcMngNm" maxlength="200" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시행년도</th>
							<td>
								<!-- 년도 자동 주입 -->
								<select id="enforceYear">
                                </select>
							</td>
							<th width="12%" height="17">점검진단일자</th>
							<td><input id="qcInspDt" type="text" class="emdcal" size="20"/></td>
							<th width="12%" height="17">점검진단기관명</th>
							<td><input type="text" size="30" id="qcInspInsttNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<select id="qcInspSe">
                                    <option value="">선택</option>
                                    <option value="A">1선택</option>
                                    <option value="B">2선택</option>
                                    <option value="C">3선택</option>
                                    <option value="D">4선택</option>
                                    <option value="E">5선택</option>
                                </select>
							</td>
							<th width="12%" height="17">점검시작일자</th>
							<td><input id="qcBeginDt" type="text" class="emdcal" size="20"/></td>
							<th width="12%" height="17">점검종료일자</th>
							<td colspan="3"><input id="qcEndDt" type="text" class="emdcal" size="20"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">책임기술자명</th>
							<td><input type="text" size="30" id="responEngineerNm" maxlength="60" /></td>
							<th width="12%" height="17">점검진단예산</th>
							<td><input id="qcInspBdgt" type="text" size="20" class="ygpaNumber"/></td>
							<th width="12%" height="17">점검진단금액</th>
							<td><input id="qcInspAmt" type="text" size="20" class="ygpaNumber"/></td>
						</tr>
						<tr>
							<th width="12%" height="17">상태평가등급</th>
							<td colspan="5">
								<select id="sttusEvlLvl">
                                    <option value="">선택</option>
                                    <option value="A">A등급</option>
                                    <option value="B">B등급</option>
                                    <option value="C">C등급</option>
                                </select>			
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="120" rows="7"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치구분</th>
							<td colspan="5">
								<select id="actionSe">
                                    <option value="">선택</option>
                                    <option value="1">조치1</option>
                                    <option value="2">조치2</option>
                                    <option value="3">조치3</option>
                                </select>							
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="120" rows="7"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><input id="rm" type="text" size="110"/></td>
						</tr>
					</table>
				</div>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">저장</button>
				</div>
			</div>
			
			<!-- 점검관리대상시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngObjFcltsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnModifyQcMngObjFclts">추가/삭제</button>
					<button id="btnSave">저장</button>
				</div>
			</div>
			
			<!-- 점검관리결과 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngResultItemList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnModifyQcMngResultItem">추가/삭제</button>
					<button id="btnSave">저장</button>
				</div>
			</div>			

			<!-- 점검관리첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngAtchFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnUploadFile">업로드</button>
					<button id="btnDownloadFile">다운로드</button>
					<button id="btnRemoveFile">삭제</button>
					<button id="btnSave">저장</button>
				</div>
				<form id="qcMngAtchFileForm">
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
							<td><input id="atchFileSj" type="text" size="20" class="photoEditItem" maxlength="40" /></td>
							<th width="15%" height="23" class="required_text">작성일자</th>
							<td><input id="atchFileWritingDt" type="text" size="18" class="emdcal photoEditItem" maxlength="10" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>

		</div>
	</div>
</div>