<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyMaintMaintMng.jsp
  * @Description : 시설물유지보수관리
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
<validator:javascript formName="fcltyMaintMngVO" method="validateFcltyMaintMngVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMaintMngModule() {
}

GamFcltyMaintMngModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMaintMngModule.prototype.loadComplete = function() {
	
	this._mode = "";
	this._deleteDataMaintList=[];
	this._deleteDataFileList=[];

	//console.log("GamFcltyMaintMngModule");
	// 테이블 설정
	this.$("#fcltyMngMngtList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintMngList.do',
		dataType: "json",
		colModel : [
					{display:"시설물관리그룹", 		name:"fcltsMngGroupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"유지보수구분",		name:"mntnRprSeNm",				width:80, 		sortable:false,		align:"center"},
					{display:"시작일자",			name:"mntnRprCnstStartDt",		width:80, 		sortable:false,		align:"center"},
					{display:"종료일자",			name:"mntnRprCnstEndDt",		width:80, 		sortable:false,		align:"center"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수부위", 		name:"mntnRprPart",				width:250, 		sortable:false,		align:"center"},
					{display:"시공자", 			name:"cnstrtr",					width:150, 		sortable:false,		align:"center"},
					{display:"책임기술자", 		name:"responEngineer",			width:150, 		sortable:false,		align:"center"},
					{display:"공사감독자", 		name:"cnstChargNm",				width:150, 		sortable:false,		align:"center"},
					{display:"계약명", 			name:"ctrtNm",					width:250, 		sortable:false,		align:"center"}
					
					
			],
		height: "auto"
	});


	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectMntnRprObjFcltsFList.do',
		dataType: "json",
		colModel : [
					{display:"관리번호",			name:"fcltsMngNo",			width:150,		sortable:false,		align:"center"},
					{display:"시설명",			name:"prtFcltyNm",			width:250,		sortable:false,		align:"left"},
					{display:"유지보수공법",		name:"mntnRprCnstMth",		width:80,		sortable:false,		align:"center"},
					{display:"단위",				name:"unit",				width:80,		sortable:false,		align:"center"},
					{display:"수량",				name:"qy",					width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"단가",				name:"price",				width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"공사금액",			name:"mntnRprCnstAmt",		width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"비고",				name:"rm",					width:200,		sortable:false,		align:"center"}
			],
		height: "200"
	});
	
	
	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyDataChanged(event.target);
	});

	this.$("#mntnRprObjFcltsF").on("onItemSelected", function(event, module, row, grid, param) {

		module.$("#gamPopupMaintForm input").val('');
		module.makeFormValues("#gamPopupMaintForm", row);
	});



 	this.$("#fcltyMaintFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"}
			],
		height: "300"
	});
 	
 	
 	this.$("#fcltyMngMngtList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});
 	
 	this.$("#fcltyMngMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyMaintMngListTab").tabs("option", {active: 1});
	});
 	
 	this.$("#fcltyMaintFileList").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyFileDataChanged();
	});
 	
 	
	// 파일 정보 속성이 변경 된 경우 이벤트 실행sFcltsMngGroupNo
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});
	
	
	// 시설물관리그룹 검색조건 클릭시 초기화 처리
	this.$("#sFcltsMngGroupNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngGroupNo").val('');
		event.data.module.$("#sFcltsMngGroupNoNm").val('');
	});
	
	// 공사계약 검색조건 클릭시 초기화 처리
	this.$("#sCtrtNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sCtrtNo").val('');
		event.data.module.$("#sCtrtNm").val('');
	});
	
	
	// 연도 셀렉트 옵션에 뿌리기
	this.applySelectYear();


};

GamFcltyMaintMngModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var option = "";
	for(var i = 2000;i<=toYear;i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#enforceYear").append(option);
};



//속성 변경 된 경우 이벤트 실행
GamFcltyMaintMngModule.prototype.applyDataChanged = function(target) {
	var changed=false;
	var row={};

	var selectRow = this.$('#mntnRprObjFcltsF').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#fcltsMngNo').is(target)) {
			row['fcltsMngNo'] = $(target).val();
			changed=true;
		}
		if(this.$('#prtFcltyNm').is(target)) {
			row['prtFcltyNm'] = $(target).val();
			changed=true;
		}
		if(this.$('#mntnRprCnstMth').is(target)) {
			row['mntnRprCnstMth'] = $(target).val();
			changed=true;
		}
		if(this.$('#unit').is(target)) {
			row['unit'] = $(target).val();
			changed=true;
		}
		if(this.$('#qy').is(target)) {
			row['qy'] = $(target).val();
			changed=true;
		}
		if(this.$('#price').is(target)) {
			row['price'] = $(target).val();
			changed=true;
		}
		if(this.$('#mntnRprCnstAmt').is(target)) {
			row['mntnRprCnstAmt'] = $(target).val();
			changed=true;
		}
		if(this.$('#rm').is(target)) {
			row['rm'] = $(target).val();
			changed=true;
		}

	}
	if(changed) {
		var rowid=this.$("#mntnRprObjFcltsF").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#mntnRprObjFcltsF').flexUpdateRow(rowid, row);
	}
};


// 하단부 데이타 수정시 그리드 반영
GamFcltyMaintMngModule.prototype.applyFileChanged = function(target){
	var changed=false;
	var row={};

	var selectRow = this.$('#fcltyMaintFileList').selectedRows();

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
		var rowid=this.$("#fcltyMaintFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyMaintFileList').flexUpdateRow(rowid, row);
	}
};

// 파일 그리드 선택시 하단부 데이타 수정창에 갱신
GamFcltyMaintMngModule.prototype.applyFileDataChanged = function(){

	var row = this.$('#fcltyMaintFileList').selectedRows();
	row = row[0];

	this.$("#fcltyMaintMngFileForm input").val('');
	this.makeFormValues("#fcltyMaintMngFileForm", row);
	this._editDataFile = this.getFormValues("#fcltyMaintMngFileForm", row);
	this._editRowFile = this.$("#fcltyMaintFileList").selectedRowIds()[0];
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
			this.$("#previewImage").removeAttr("src");
		}
	}
};


GamFcltyMaintMngModule.prototype.onSubmit = function(){
	this.loadData();
};

GamFcltyMaintMngModule.prototype.loadData = function(){
	
	// tabs2 항목 초기화
	this.makeFormValues('#fcltyMaintMngListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#mntnRprObjFcltsF').flexEmptyData();
	
	// tabs4 항목/그리드 초기화
	this.makeFormValues('#fcltyMaintMngFileForm', {});
	this.$('#fcltyMaintFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "");
	
	this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintMngForm');
	this.$('#fcltyMngMngtList').flexOptions({params:searchOpt}).flexReload();
	
};


GamFcltyMaintMngModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyMngMngtList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'mntnRprSeq', value: row['mntnRprSeq'] }
	               ];

	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyMaintMngDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#fcltyMaintMngListVO', result.result);
			
			// tabs3 그리드 리로드
			module.$('#mntnRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			// tabs4 항목 데이타 로딩/ 그리드 리로드
			module.makeFormValues('#fcltyMaintMngFileForm', {});
			module.$("#previewImage").attr("src", "");
			module.$('#fcltyMaintFileList').flexOptions({params:searchVO}).flexReload();
		}else{
			module.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		}
    });

};


GamFcltyMaintMngModule.prototype.addData = function() {

	this._mode="insert";
	
	// tabs2 초기화
	this.makeFormValues('#fcltyMaintMngListVO', {});
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();
	// tabs3 초기화
	this.$("#mntnRprObjFcltsF").flexEmptyData();
	// tabs4 초기화
	this.makeFormValues('#fcltyMaintMngFileForm', {});
	this.$("#previewImage").attr("src", "");
	this.$("#fcltyMaintFileList").flexEmptyData();
	
	this.$("#fcltyMaintMngListTab").tabs("option", {active: 1});

};


GamFcltyMaintMngModule.prototype.saveData = function() {
	
	if(!validateFcltyMaintMngVO(this.$("#fcltyMaintMngListVO")[0])){
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 1});
		return;
	}

 	var inputVO = this.makeFormArgs("#fcltyMaintMngListVO");
	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'mntnRprSeq':result.mntnRprSeq};

				// 유지보수 대상시설물 데이타 적용
				if(module.mergeMntnRprObjFcltsF(subVo)){
					// 유지보수 첨부파일 데이타 적용
					if(module.mergeFcltyMaintFile(subVo)){
					}
				}
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'mntnRprSeq':result.mntnRprSeq};

				// 유지보수 대상시설물 데이타 적용
				if(module.mergeMntnRprObjFcltsF(subVo)){
					// 유지보수 첨부파일 데이타 적용
					if(module.mergeFcltyMaintFile(subVo)){
					}
				}
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintMngModule.prototype.mergeMntnRprObjFcltsF = function(subVo) {

	var all_rows = this.$('#mntnRprObjFcltsF').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["mntnRprSeq"] = subVo.mntnRprSeq;
	}
	
	var inputMntnRprObjVO = [];
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'updateList', value :JSON.stringify(this.$('#mntnRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'insertList', value: JSON.stringify(this.$('#mntnRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputMntnRprObjVO[inputMntnRprObjVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataMaintList) };
	
	var chk = true;
	this.doAction('/fcltyMng/mergeMntnRprObjFcltsF.do', inputMntnRprObjVO, function(mntnRprObjModule, result) {
        if(result.resultCode != 0){
			chk = false;
        }
    });
	
	return chk;

};


GamFcltyMaintMngModule.prototype.mergeFcltyMaintFile = function(subVo) {

	var all_rows = this.$('#fcltyMaintFileList').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["mntnRprSeq"] = subVo.mntnRprSeq;
	}

	var inputFileVO=[];
	inputFileVO[inputFileVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyMaintFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFileVO[inputFileVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyMaintFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFileVO[inputFileVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };
	
	var chk = true;
    this.doAction('/fcltyMng/mergeFcltyMaintFile.do', inputFileVO, function(fileModule, fileResult) {
        if(fileResult.resultCode != 0){
        	chk = false;
        }
    });
    
    return chk;

};


GamFcltyMaintMngModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltyMngMngtList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("삭제하시겠습니까?")){
		row = row[0];
		var inputVO = { 'fcltsJobSe': row['fcltsJobSe'],'fcltsMngGroupNo': row['fcltsMngGroupNo'],'mntnRprSeq': row['mntnRprSeq'] };
	 	this.doAction('/fcltyMng/deleteFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintMngModule.prototype.uploadFileData = function() {
	// 파일을 업로드하고 업로드한 파일 목록을 result에 어레이로 리턴한다.
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			module.$("#fcltyMaintFileList").flexAddRow({_updtId:'I', atchFileSeq:"", atchFileSe:"D", atchFileSeNm:"문서",  atchFileSj: "", atchFileNmLogic: this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt: ""});
		});
	}, "첨부파일 업로드");
};


GamFcltyMaintMngModule.prototype.downloadFileData = function() {
	var selectRow = this.$('#fcltyMaintFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamFcltyMaintMngModule.prototype.removeFileData = function() {
	var rows = this.$("#fcltyMaintFileList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#fcltyMaintFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyMaintFileList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyMaintFileList").flexGetRow(this.$("#fcltyMaintFileList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyMaintFileList").flexRemoveRow(this.$("#fcltyMaintFileList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyMaintMngFileForm").find(":input").val("");
    this._editDataFile = null;
};


GamFcltyMaintMngModule.prototype.delMaintItem = function() {
	var rows = this.$("#mntnRprObjFcltsF").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#mntnRprObjFcltsF").selectedRowIds().length>0) {
    	for(var i=this.$("#mntnRprObjFcltsF").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#mntnRprObjFcltsF").flexGetRow(this.$("#mntnRprObjFcltsF").selectedRowIds()[i]);

    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataMaintList[this._deleteDataMaintList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#mntnRprObjFcltsF").flexRemoveRow(this.$("#mntnRprObjFcltsF").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}

    this._editDataFile = null;
};


GamFcltyMaintMngModule.prototype.addMaintItem = function() {
	this.$('#gamPopupMaintForm :input').val('');

	this.$("#mntnRprObjFcltsF").flexAddRow({'_updtId': 'I','fcltsMngNo':'','mntnRprCnstMth':'','unit':'','qy':'','price':'','mntnRprCnstAmt':'','rm':''});
	var all_rows = this.$('#mntnRprObjFcltsF').flexGetData();
	var sel_row_id = all_rows.length - 1;
	this.$("#mntnRprObjFcltsF").selectRowId(sel_row_id);
};



/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyMaintMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
		// 추가
		case "addMaintItemBtn":
			this.addMaintItem();
		break;
		
		case "delMaintItemBtn":
			this.delMaintItem();
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
		
		// 검색조건시설물관리그룹
		case "sSearchFcltsMngGroupNo":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호 검색", '/popup/showFcltsMngGroup.do', {});
		break;
		
		// 검색조건계약번호
		case "sCtrtNoPopupBtn":
			this.doExecuteDialog("sSelectCtrtNo", "계약번호 검색", '/popup/popupCtrtNo.do', {});
		break;
	}
};


GamFcltyMaintMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._mode == 'modify') {
		this.loadDetail();
	}
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
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
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
			} 
		break;
		
		case "tabs4":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
			} 
		break;
	}
	
};


/**
 * 팝업 close 이벤트
 */
 GamFcltyMaintMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		
		case "selectFcltsMngNo":
			this.$("#fcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#prtFcltyNm").val(value["prtFcltyNm"]);
			
			// 대상시설물 팝업에서 상태값 변경시 그리드 적용
			this.$(".EditItem").trigger("change");
		break;
	
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
		case "selectCtrtNo":
			this.$("#ctrtNo").val(value["ctrtNo"]);
			this.$("#ctrtNm").val(value["ctrtNm"]);
		break;
		
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
		case "sSelectCtrtNo":
			this.$("#sCtrtNo").val(value["ctrtNo"]);
			this.$("#sCtrtNm").val(value["ctrtNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};



// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMaintMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyMaintMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo" data-required="true" title="시설물관리그룹넘버" />-
								<input type="text" size="17" id="sFcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="sSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th>공사계약</th>
							<td>
								<input type="text" size="15" id="sCtrtNo" title="계약번호"/>-
								<input type="text" size="17" id="sCtrtNm" disabled="disabled" title="계약명"/>
								<button id="sCtrtNoPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="3"><button class="buttonSearch">조회</button></td>
						</tr>
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
							<td><input type="text" id="sMntnRprCnstNm" size="49" title="유지보수공사명검색조건" /></td>
						</tr>
						<tr>
							<th>유지보수구분</th>
							<td>
								<select id="sMntnRprSe" title="유지보수구분검색조건">
									<option value="">선택</option>
									<option value="1">개량</option>
									<option value="2">보수</option>
									<option value="3">보강</option>
									<option value="4">변경-증설</option>
									<option value="5">변경-구조변경</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<input id="sMntnRprCnstStartDtFr" type="text" class="emdcal" size="17" title="유지보수공사검색시작일" /> ~ <input id="sMntnRprCnstStartDtTo" type="text" class="emdcal" size="17" title="유지보수공사검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMaintMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">유지보수 대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">유지보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMngMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>


			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintMngListVO">
					<table class="editForm"  style="width:100%;" border="1">
						<tr>
							<th width="13%" height="23" class="required_text">시행년도</th>
							<td width="10%">
								<select id="enforceYear" title="시행년도">
									<option value="">선택</option>
								</select>
							</td>
							<th width="10%" height="23" class="required_text">시설물업무구분</th>
							<td width="10%">
								<select id="fcltsJobSe" data-required="true" title="시설물업무구분">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th width="10%" height="23" class="required_text">유지보수구분</th>
							<td width="300px">
								<select id="mntnRprSe" title="유지보수구분">
									<option value="">선택</option>
									<option value="1">개량</option>
									<option value="2">보수</option>
									<option value="3">보강</option>
									<option value="4">변경-증설</option>
									<option value="5">변경-구조변경</option>
									<option value="9">기타</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="13%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="3">
								<input type="text" size="20" id="fcltsMngGroupNo" disabled="disabled" data-required="true" title="시설물관리그룹넘버" />-
								<input type="text" size="35" id="fcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th width="10%" height="23" class="required_text">유지보수순번</th>
							<td><input type="text" size="20" id="mntnRprSeq" disabled="disabled" title="유지보수순번" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<input type="text" size="20" id="ctrtNo" disabled="disabled" title="계약번호"/>-
								<input type="text" size="35" id="ctrtNm" disabled="disabled" title="계약명"/>
								<button id="ctrtNoPopupBtn" class="popupButton">선택</button>
							</td>
							<th width="10%" height="23" class="required_text">공사기간</th>
							<td><input id="mntnRprCnstStartDt" type="text" size="11" title="공사시작일자" class="emdcal" /> ~ <input id="mntnRprCnstEndDt" type="text" size="11" title="공사종료일자" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">공사명</th>
							<td colspan="5"><input id="mntnRprCnstNm" type="text"  title="공사명" maxlength="25" style="width:830px;" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">유지보수부위</th>
							<td colspan="5"><input id="mntnRprPart" type="text" size="130" title="유지보수부위" maxlength="25" style="width:830px;" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">예산</th>
							<td><input id="mntnRprBdgt" type="text" size="30" title="예산" class="ygpaNumber" maxlength="16" /></td>
							<th width="10%" height="23" class="required_text">설계자</th>
							<td><input id="plannerNm" type="text" size="20" title="설계자" maxlength="6" /></td>
							<th width="10%" height="23" class="required_text">시공자</th>
							<td><input id="cnstrtr" type="text" size="20" title="시공자" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">공사금액</th>
							<td><input id="mntnRprCnstAmt" type="text" size="30" title="공사금액" class="ygpaNumber" maxlength="16" /></td>
							<th width="10%" height="23" class="required_text">책임기술자</th>
							<td><input id="responEngineer" type="text" size="20" title="책임기술자" maxlength="20" /></td>
							<th width="13%" height="23" class="required_text">공사감독자</th>
							<td><input id="cnstChargNm" type="text" size="20" title="공사감독자" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">유지보수내용</th>
							<td colspan="5"><textarea id="mntnRprCn" style="width:830px;" rows="7" title="유지보수내용" maxlength="1333"></textarea></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">비고</th>
							<td colspan="5"><input id="rm" type="text" title="비고" maxlength="333" style="width:830px;" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 유지보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="mntnRprObjFcltsF" style="display:none"></table>
				<div class="emdControlPanel">
		            <button id="addMaintItemBtn">추가</button>
		            <button id="delMaintItemBtn">삭제</button>
		        </div>
				<div class="emdControlPanel">
					<form id="gamPopupMaintForm">
						<table class="searchPanel">
							<tbody>
								<tr>
			                        <th>대상시설물</th>
			                        <td colspan="3">
			                        	<input id="fcltsMngNo" type="text" style="width: 125px;" title="관리번호" maxlength="20" class="EditItem" disabled="disabled"/>
			                        	<input id="prtFcltyNm" type="text" style="width: 170px;" title="시설명" maxlength="20" class="EditItem" disabled="disabled"/>
			                        	<button id="searchFcltsMngNo" class="popupButton">선택</button>
			                        </td>
			                        <th>유지보수공법</th>
			                        <td colspan="3"><input id="mntnRprCnstMth" type="text" style="width: 398px;" title="유지보수공법" maxlength="33" class="EditItem"/></td>
								</tr>
								<tr>
									<th>단위</th>
			                        <td><input id="unit" type="text" style="width: 150px;" title="단위" maxlength="3" class="EditItem"/></td>
			                        <th>수량</th>
			                        <td ><input id="qy" type="text" style="width: 150px;" title="수량" maxlength="10" class="ygpaNumber EditItem"/></td>
			                        <th>단가</th>
									<td><input id="price" type="text" style="width: 150px;" title="단가" maxlength="16" class="ygpaNumber EditItem"/></td>
									<th>공사금액</th>
									<td><input id="mntnRprCnstAmt" type="text" style="width: 152px;" title="공사금액" maxlength="16" class="ygpaNumber EditItem"/></td>
								</tr>
								<tr>
									<th>비고</th>
									<td colspan="7"><input id="rm" type="text" style="width: 869px;" title="비고" maxlength="333" class="EditItem"/></td>
								</tr>
							</tbody>
						</table>
					</form>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 유지보수내역 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltyMaintFileList" style="display:none"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="saveBtn">저장</button>
							</div>
							<form id="fcltyMaintMngFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<select id="atchFileSe" class="fileEditItem" title="파일구분">
			                                    <option value="D">문서</option>
			                                    <option value="P">사진</option>
			                                    <option value="Z">기타</option>
			                                </select>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td><input id="atchFileSj" type="text" size="45" class="fileEditItem" maxlength="16" title="파일제목"/></td>
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