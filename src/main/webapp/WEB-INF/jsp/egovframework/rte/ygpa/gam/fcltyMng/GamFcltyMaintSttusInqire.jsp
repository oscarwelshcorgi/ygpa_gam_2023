<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyMaintSttusInqire.jsp
  * @Description : 시설물 유지보수현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.8  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.8
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<%-- <validator:javascript formName="fcltyMaintSttusInqireVO" method="validateFcltyMaintSttusInqireVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" ></span> --%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMaintSttusInqireModule() {
}

GamFcltyMaintSttusInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMaintSttusInqireModule.prototype.loadComplete = function() {
	
	this._mode = "";
	//console.log("GamFcltyMaintSttusInqireModule");
	// 테이블 설정
	this.$("#fcltyMaintSttusInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintSttusInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시행년도", 			name:"enforceYear",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"유지보수순번", 		name:"mntnRprSeq",				width:120, 		sortable:false,		align:"center"},
					{display:"유지보수구분",		name:"mntnRprSe",				width:80, 		sortable:false,		align:"left"},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수부위", 		name:"mntnRprPart",				width:250, 		sortable:false,		align:"center"}
			],
		height: "auto"
	});


	this.$("#mntnSttusRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectMntnSttusRprObjFcltsFList.do',
		dataType: "json",
		colModel : [
					//{display:"상태",			name:"_updtId",				width:60,		sortable:false,		align:"center"},
					{display:"관리번호",			name:"fcltsMngNo",			width:60,		sortable:false,		align:"center"},
					{display:"유지보수공법",		name:"mntnRprCnstMth",		width:80,		sortable:false,		align:"center"},
					{display:"단위",				name:"unit",				width:80,		sortable:false,		align:"center"},
					{display:"수량",				name:"qy",					width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"단가",				name:"price",				width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"공사금액",			name:"mntnRprCnstAmt",		width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"비고",				name:"rm",					width:200,		sortable:false,		align:"center"}
			],
		height: "auto"
	});



 	this.$("#fcltyMaintSttusFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintFileList.do',
		dataType: 'json',
		colModel : [
					//{display:"상태",			name:"_updtId",				width:60,		sortable:false,		align:"center"},
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",		width:200,		sortable:true,		align:"left"},
					{display:"생성일시",	name:"atchFileWritngDt",		width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});
 	
 	
 	this.$("#fcltyMaintSttusInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});
 	
 	this.$("#fcltyMaintSttusInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 1});
	});
 	
 	this.$("#fcltyMaintSttusFileList").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyFileDataChanged();
	});
 	
 	
	// 파일 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});
	
	
	// 연도 셀렉트 옵션에 뿌리기
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var option = "";
	for(var i = 2000;i<=toYear;i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#enforceYear").append(option);


};

// 하단부 데이타 수정시 그리드 반영
GamFcltyMaintSttusInqireModule.prototype.applyFileChanged = function(target){
	var changed=false;
	var row={};

	var selectRow = this.$('#fcltyMaintSttusFileList').selectedRows();

	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSeNm'] = $(target).find('option:selected').text();
			row['atchFileSe'] = $(target).val();
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
		var rowid=this.$("#fcltyMaintSttusFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyMaintSttusFileList').flexUpdateRow(rowid, row);
	}
};

// 파일 그리드 선택시 하단부 데이타 수정창에 갱신
GamFcltyMaintSttusInqireModule.prototype.applyFileDataChanged = function(){

	var row = this.$('#fcltyMaintSttusFileList').selectedRows();
	row = row[0];

	this.$("#fcltyMaintSttusInqireFileForm input").val('');
	this.makeFormValues("#fcltyMaintSttusInqireFileForm", row);
	this._editDataFile = this.getFormValues("#fcltyMaintSttusInqireFileForm", row);
	this._editRowFile = this.$("#fcltyMaintSttusFileList").selectedRowIds()[0];
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
			this.$("#previewImage").attr("src", "");
		}
	}
};


GamFcltyMaintSttusInqireModule.prototype.onSubmit = function(){
	this.loadData();
};

GamFcltyMaintSttusInqireModule.prototype.loadData = function(){
	
	// tabs2 항목 초기화
	this.makeFormValues('#fcltyMaintSttusInqireListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#mntnSttusRprObjFcltsF').flexEmptyData();
	
	// tabs4 항목/그리드 초기화
	this.makeFormValues('#fcltyMaintSttusInqireFileForm', {});
	this.$('#fcltyMaintSttusFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "");
	
	this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintSttusInqireForm');
	this.$('#fcltyMaintSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
};


GamFcltyMaintSttusInqireModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyMaintSttusInqireList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'mntnRprSeq', value: row['mntnRprSeq'] }
	               ];

	// tabs2 항목 데이타로딩
	this.makeFormValues('#fcltyMaintSttusInqireListVO', row);
	// tabs3 그리드 리로드
	this.$('#mntnSttusRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
	// tabs4 항목 데이타 로딩/ 그리드 리로드
	this.makeFormValues('#fcltyMaintSttusInqireFileForm', {});
	this.$("#previewImage").attr("src", "");
	this.$('#fcltyMaintSttusFileList').flexOptions({params:searchVO}).flexReload();
	
};


GamFcltyMaintSttusInqireModule.prototype.addData = function() {

	this._mode="insert";
	
	// tabs2 초기화
	this.makeFormValues('#fcltyMaintSttusInqireListVO', {});
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();
	// tabs3 초기화
	this.$("#mntnSttusRprObjFcltsF").flexEmptyData();
	// tabs4 초기화
	this.makeFormValues('#fcltyMaintSttusInqireFileForm', {});
	this.$("#previewImage").attr("src", "");
	this.$("#fcltyMaintSttusFileList").flexEmptyData();
	
	this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 1});

};


GamFcltyMaintSttusInqireModule.prototype.saveData = function() {
	
	if(!validateFcltyMaintSttusInqireVO(this.$("#fcltyMaintSttusInqireListVO")[0])){
		this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 1});
		return;
	}

 	var inputVO = this.makeFormArgs("#fcltyMaintSttusInqireListVO");
	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyMaintSttusInqire.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'mntnRprSeq':result.mntnRprSeq};

				// 유지보수 대상시설물 데이타 적용
				if(module.mergeMntnRprObjFcltsF(subVo)){
					// 유지보수 첨부파일 데이타 적용
					if(module.mergeFcltyMaintFile(subVo)){
						module.loadData();
					}
				}
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyMaintSttusInqire.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'mntnRprSeq':result.mntnRprSeq};

				// 유지보수 대상시설물 데이타 적용
				if(module.mergeMntnRprObjFcltsF(subVo)){
					// 유지보수 첨부파일 데이타 적용
					if(module.mergeFcltyMaintFile(subVo)){
						module.loadData();
					}
				}
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintSttusInqireModule.prototype.mergeMntnRprObjFcltsF = function(subVo) {

	var all_rows = this.$('#mntnSttusRprObjFcltsF').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["mntnRprSeq"] = subVo.mntnRprSeq;
	}
	
	var inputMntnRprObjVO = [];
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'updateList', value :JSON.stringify(this.$('#mntnSttusRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'insertList', value: JSON.stringify(this.$('#mntnSttusRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataMaintList) };
	
	var chk = true;
	this.doAction('/fcltyMng/mergeMntnRprObjFcltsF.do', inputMntnRprObjVO, function(mntnRprObjModule, result) {
        if(result.resultCode != 0){
			chk = false;
        }
    });
	
	return chk;

};


GamFcltyMaintSttusInqireModule.prototype.mergeFcltyMaintFile = function(subVo) {

	var all_rows = this.$('#fcltyMaintSttusFileList').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["mntnRprSeq"] = subVo.mntnRprSeq;
	}

	var inputFileVO=[];
	inputFileVO[inputFileVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyMaintSttusFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFileVO[inputFileVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyMaintSttusFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFileVO[inputFileVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };
	
	var chk = true;
    this.doAction('/fcltyMng/mergeFcltyMaintFile.do', inputFileVO, function(fileModule, fileResult) {
        if(fileResult.resultCode != 0){
        	chk = false;
        }
    });
    
    return chk;

};


GamFcltyMaintSttusInqireModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltyMaintSttusInqireList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("삭제하시겠습니까?")){
		row = row[0];
		var inputVO = { 'fcltsJobSe': row['fcltsJobSe'],'fcltsMngGroupNo': row['fcltsMngGroupNo'],'mntnRprSeq': row['mntnRprSeq'] };
	 	this.doAction('/fcltyMng/deleteFcltyMaintSttusInqire.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintSttusInqireModule.prototype.addEditData = function() {
	var all_rows = this.$('#mntnSttusRprObjFcltsF').flexGetData();
	
	this.doExecuteDialog("mntnSttusRprObjFcltsFPopup", "유지보수대상시설물현황", '/popup/selectMntnRprObjFcltsFPopup.do', {},all_rows);
};


GamFcltyMaintSttusInqireModule.prototype.uploadFileData = function() {
	// 파일을 업로드하고 업로드한 파일 목록을 result에 어레이로 리턴한다.
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			module.$("#fcltyMaintSttusFileList").flexAddRow({_updtId:'I', atchFileSeq:"", atchFileSe:"D", atchFileSeNm:"문서",  atchFileSj: "", atchFileNmLogic: this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt: ""});
		});
	}, "첨부파일 업로드");
};


GamFcltyMaintSttusInqireModule.prototype.downloadFileData = function() {
	var selectRow = this.$('#fcltyMaintSttusFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamFcltyMaintSttusInqireModule.prototype.removeFileData = function() {
	var rows = this.$("#fcltyMaintSttusFileList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#fcltyMaintSttusFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyMaintSttusFileList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyMaintSttusFileList").flexGetRow(this.$("#fcltyMaintSttusFileList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyMaintSttusFileList").flexRemoveRow(this.$("#fcltyMaintSttusFileList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyMaintSttusInqireFileForm").find(":input").val("");
    this._editDataFile = null;
};



/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyMaintSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

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
		
		// 추가/편집
		case "mntnSttusRprObjFcltsFPopupBtn":
			this.addEditData();
		break;
		
		// 파일업로드
		case "btnUploadFile":
			this.uploadFileData();
		break;
		
		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
		break;
		
		// 파일삭제
		case "btnRemoveFile":
			this.removeFileData();
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


GamFcltyMaintSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._mode == 'modify') {
		this.loadDetail();
	}
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
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
			this._deleteDataMaintList=[];
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
			} 
		break;
		
		case "tabs4":
			this._deleteDataFileList=[];
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintSttusInqireListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
			} 
		break;
	}
	
};


/**
 * 팝업 close 이벤트
 */
 GamFcltyMaintSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 상세화면
		case "mntnSttusRprObjFcltsFPopup":
			if(msg == 'ok'){
				this.$("#mntnSttusRprObjFcltsF").flexEmptyData();
			}
			this.$("#mntnSttusRprObjFcltsF").flexAddData({resultList: value["inputVo"] });

			this._deleteDataMaintList = value["deleteDataMaintList"];
		break;
		
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
		case "selectCtrtNo":
			this.$("#ctrtNo").val(value["ctrtNo"]);
			this.$("#ctrtNm").val(value["ctrtNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};



// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMaintSttusInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyMaintSttusInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe" title="시설물업무구분검색조건">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>유지보수공사명</th>
							<td><span id="sMntnRprCnstNm" size="50" title="유지보수공사명검색조건" ></span></td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>유지보수구분</th>
							<td>
								<select id="sMntnRprSe" title="유지보수구분검색조건">
									<option value="">선택</option>
									<option value="m1">유지보수1</option>
									<option value="m2">유지보수2</option>
									<option value="m3">유지보수3</option>
								</select>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<span id="sMntnRprCnstStartDtFr"  size="15" title="유지보수공사검색시작일" ></span> ~ <span id="sMntnRprCnstStartDtTo"  size="15" title="유지보수공사검색종료일" ></span>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMaintSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">유지보수 대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">유지보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintSttusInqireList" style="display:none" class="fillHeight"></table>
			</div>
			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintSttusInqireListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="15%" height="23" class="required_text">시행년도</th>
							<td><span id="enforceYear" title="시행년도"></span></td>
							<th width="15%" height="23" class="required_text">시설물업무구분</th>
							<td><span id="fcltsJobSe" title="시설물업무구분"></span></td>
							<th width="15%" height="23" class="required_text">유지보수구분</th>
							<td><span id="mntnRprSe" title="유지보수구분"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo" title="시설물관리그룹넘버"></span>
								<span id="fcltsMngGroupNoNm" title="시설물관리그룹명"></span>
							</td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<span id="ctrtNo" title="계약번호"></span>-
								<span id="ctrtNm" title="계약명"></span>
							</td>
							<th width="15%" height="23" class="required_text">유지보수순번</th>
							<td><span id="mntnRprSeq" title="유지보수순번" ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사명</th>
							<td colspan="5"><span id="mntnRprCnstNm" title="공사명"  ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">유지보수부위</th>
							<td colspan="5"><span id="mntnRprPart" title="유지보수부위"  ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">예산</th>
							<td colspan="3"><span id="mntnRprBdgt" title="예산" class="ygpaNumber" ></span></td>
							<th width="15%" height="23" class="required_text">공사시작일자</th>
							<td><span id="mntnRprCnstStartDt" title="공사시작일자"  ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사금액</th>
							<td colspan="3"><span id="mntnRprCnstAmt" title="공사금액" class="ygpaNumber" ></span></td>
							<th width="15%" height="23" class="required_text">공사종료일자</th>
							<td><span id="mntnRprCnstEndDt" title="공사종료일자" ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">설계자</th>
							<td><span id="plannerNm" title="설계자"  ></span></td>
							<th width="15%" height="23" class="required_text">시공자</th>
							<td colspan="3"><span id="cnstrtr" title="시공자"  ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">책임기술자</th>
							<td><span id="responEngineer" title="책임기술자"  ></span></td>
							<th width="15%" height="23" class="required_text">공사감독자</th>
							<td colspan="3"><span id="cnstChargNm" title="공사감독자"  ></span></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">유지보수내용</th>
							<td colspan="5"><textarea id="mntnRprCn" cols="130" rows="10" title="유지보수내용" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">비고</th>
							<td colspan="5"><span id="rm" title="비고"  ></span></td>
						</tr>
					</table>
				</form>
			</div>
			
			<!-- 유지보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="mntnSttusRprObjFcltsF" style="display:none" class="fillHeight"></table>
			</div>
			<!-- 유지보수내역 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyMaintSttusFileList" style="display:none" class="fillHeight"></table>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>