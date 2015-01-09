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

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyRepairMngModule() {
}

GamFcltyRepairMngModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyRepairMngModule.prototype.loadComplete = function(params) {
	this._mode = "";
	this._deleteObjFcltsList=[];
	this._deleteDataRepairList=[];
	this._deleteDataFileList=[];
	
	//console.log('GamFcltyRepairMngModule');

	// 테이블 설정
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
		height: "auto"
	});
	
	
	this.$("#flawRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawRprObjFcltsF.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"flawRprSeq",		width:100,		sortable:false,		align:"center"},
					{display:"대상시설물",		name:"prtFcltyNm",		width:250,		sortable:false,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",		width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",		width:100,		sortable:true,		align:"center"},
					{display:"하자검사결과",	name:"flawExamResult",	width:350,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",				width:350,		sortable:true,		align:"left"}
			],
		height: "130"
	});


	this.$("#flawExamUsrF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawExamUsrFList.do',
		dataType: "json",
		colModel : [
					{display:"순번",				name:"seq",					width:100,		sortable:false,		align:"center"},
					{display:"하자검사자",			name:"flawExamUsr",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사완료여부",		name:"flawExamComptYn",		width:250,		sortable:false,		align:"center"}
			],
		height: "220"
	});

	

 	this.$("#fcltyRepairFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"설명",		name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"}
			],
		height: "350"
	});

	
 	this.$("#fcltyRepairMngList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});
 	
 	this.$("#fcltyRepairMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
	});
 	
 	this.$("#fcltyRepairFileList").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyFileDataChanged();
	});
 	
 	
 	this.$("#flawRprObjFcltsF").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyObjDataChanged();
	});
 	
 	
 	this.$(".objFcltsEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.objFcltsDataChanged(event.target);
	});
 	
 	
 	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyExamDataChanged(event.target);
	});

	this.$("#flawExamUsrF").on("onItemSelected", function(event, module, row, grid, param) {
		module.setExamDataChanged();
	});
 	
 	
	// 파일 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});
	
	// 연도 셀렉트 옵션에 뿌리기
	this.applySelectYear();
	
};


// 셀렉트옵션 생성
GamFcltyRepairMngModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();
	
	var option = "";
	for(var i = 2000;i<=toYear;i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#enforceYear").append(option);
};


GamFcltyRepairMngModule.prototype.onSubmit = function(){
	this.loadData();
};

GamFcltyRepairMngModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#flawRprObjFcltsF').flexEmptyData();
	this.$('#gamObjFcltsForm input').val('');
	this.$('#gamObjFcltsForm textarea').val('');
	this.makeDivValues('#gamObjFcltsDetailForm',{});
	
	// tabs4 그리드 초기화
	this.$('#flawExamUsrF').flexEmptyData();
	this.$('#gamPopupRepairForm input').val('');
	this.makeDivValues('#gamExamUsrDetailForm',{});
	
	// tabs5 항목/그리드 초기화
	this.makeFormValues('#fcltyRepairMngFileForm', {});
	this.$('#fcltyRepairFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "");
	
	this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairMngForm');
	this.$('#fcltyRepairMngList').flexOptions({params:searchOpt}).flexReload();
	
};

// tabs2 상세 정보처리
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
			// tabs4 그리드 리로드
			module.makeDivValues('#gamExamUsrDetailForm',result.result);
			module.$('#flawExamUsrF').flexOptions({params:searchVO}).flexReload();
			// tabs5 항목 데이타 로딩/ 그리드 리로드
			module.makeFormValues('#fcltyRepairMngFileForm', {});
			module.$("#previewImage").attr("src", "");
			module.$('#fcltyRepairFileList').flexOptions({params:searchVO}).flexReload();
		}else{
			module.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
		}
    });
};


//tabs1에서 추가 버튼 클릭시
GamFcltyRepairMngModule.prototype.addData = function() {

	this._mode="insert";
	
	// tabs2 초기화
	this.makeFormValues('#fcltyRepairMngListVO', {});
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();
	
	// tabs3 초기화
	this.$("#flawRprObjFcltsF").flexEmptyData();
	
	// tabs4 초기화
	this.$("#flawExamUsrF").flexEmptyData();
	
	// tabs5 초기화
	this.makeFormValues('#fcltyRepairMngFileForm', {});
	this.$("#previewImage").attr("src", "");
	this.$("#fcltyRepairFileList").flexEmptyData();
	
	this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});

};



//tab1에서 삭제 버튼 클릭시
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



//하자보수대상시설물 그리드 선택시 하위폼 데이타 처리
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


//하자보수대상시설물 하위폼 정보변경시 그리드 적용
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

//하자보수 대상 시설물 추가
GamFcltyRepairMngModule.prototype.addObjFcltsItem = function() {
	this.$('#gamObjFcltsForm :input').val('');
	this.$('#searchFcltsMngNo').show();
	this.$("#flawRprObjFcltsF").flexAddRow({'_updtId': 'I', 'fcltsMngGroupNo':'', 'fcltsJobSe':'', 'flawRprSeq':'', 'fcltsMngNo':'', 'flawEnnc':'', 'flawExamDt':'', 'flawExamResult':'', 'rm':''});
	var allRows = this.$('#flawRprObjFcltsF').flexGetData();
	var selRowId = allRows.length - 1;
	this.$("#flawRprObjFcltsF").selectRowId(selRowId);	
};

//하자보수 대상 시설물 데이터 삭제
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


//하자검사자 그리드 선택시 하위폼 데이타 처리
GamFcltyRepairMngModule.prototype.setExamDataChanged = function(){
	
	var row = this.$('#flawExamUsrF').selectedRows();
	row = row[0];
	
	this.$('#eSeq').val(row['seq']);
	this.$('#eFlawExamUsr').val(row['flawExamUsr']);
	this.$('#eFlawExamDt').val(row['flawExamDt']);
	this.$('#eFlawExamComptYn').val(row['flawExamComptYn']);

};

// 하자검사자 하위폼 정보수정시 그리드 반영
GamFcltyRepairMngModule.prototype.applyExamDataChanged = function(target) {
	var changed=false;
	var row={};
	// // console.log("change event occur");

	var selectRow = this.$('#flawExamUsrF').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#eSeq').is(target)) {
			row['seq'] = $(target).val();
			changed=true;
		}
		if(this.$('#eFlawExamUsr').is(target)) {
			row['flawExamUsr'] = $(target).val();
			changed=true;
		}
		if(this.$('#eFlawExamDt').is(target)) {
			row['flawExamDt'] = $(target).val();
			changed=true;
		}
		if(this.$('#eFlawExamComptYn').is(target)) {
			row['flawExamComptYn'] = $(target).find('option:selected').text();
			changed=true;
		}

	}
	if(changed) {
		var rowid=this.$("#flawExamUsrF").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#flawExamUsrF').flexUpdateRow(rowid, row);
	}
};


//하자보수 검사자 추가
GamFcltyRepairMngModule.prototype.addExamUsrItem = function() {
	// TODO: 향후 수정 --- 아래의 코드를 사용하니 인터넷익스플로러에서 에러 발생(달력클릭이 안됨) - 그래서 아래에 각각 val('') 처리해줌
	// this.$('#gamPopupRepairForm :input').val('');
	this.$('#eFlawExamUsr').val('');
	this.$('#eFlawExamDt').val('');
	this.$('#eFlawExamComptYn').val('');
	
	this.$("#flawExamUsrF").flexAddRow({'_updtId': 'I','seq':'','flawExamUsr':'','flawExamDt':'','flawExamComptYn':''});
	var all_rows = this.$('#flawExamUsrF').flexGetData();
	var sel_row_id = all_rows.length - 1;
	this.$("#flawExamUsrF").selectRowId(sel_row_id);
};


//하자보수 검사자 삭제
GamFcltyRepairMngModule.prototype.delExamUsrItem = function() {
	var rows = this.$("#flawExamUsrF").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#flawExamUsrF").selectedRowIds().length>0) {
    	for(var i=this.$("#flawExamUsrF").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#flawExamUsrF").flexGetRow(this.$("#flawExamUsrF").selectedRowIds()[i]);

    		if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataRepairList[this._deleteDataRepairList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#flawExamUsrF").flexRemoveRow(this.$("#flawExamUsrF").selectedRowIds()[i]);

        	this._edited=true;
		}

    	alert("삭제되었습니다.");
	}
    
    this.$("#gamPopupRepairForm").find(":input").val("");

    //this._editDataFile = null;
};



//파일 그리드 선택시 하단부 데이타 수정창에 갱신
GamFcltyRepairMngModule.prototype.applyFileDataChanged = function(){

	var row = this.$('#fcltyRepairFileList').selectedRows();
	row = row[0];

	this.$("#fcltyRepairMngFileForm input").val('');
	this.makeFormValues("#fcltyRepairMngFileForm", row);
	this._editDataFile = this.getFormValues("#fcltyRepairMngFileForm", row);
	this._editRowFile = this.$("#fcltyRepairFileList").selectedRowIds()[0];
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

//하자보수첨부파일 하위폼 수정시 그리드 반영
GamFcltyRepairMngModule.prototype.applyFileChanged = function(target){
	var changed=false;
	var row={};

	var selectRow = this.$('#fcltyRepairFileList').selectedRows();

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
		var rowid=this.$("#fcltyRepairFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltyRepairFileList').flexUpdateRow(rowid, row);
	}
};






// 전 tab에서 저장 버튼 클릭시
GamFcltyRepairMngModule.prototype.saveData = function() {
	
	if(!validateFcltyRepairMngVO(this.$("#fcltyRepairMngListVO")[0])){
		this.$("#fcltyRepairMngListTab").tabs("option", {active: 1});
		return;
	}

 	var inputVO = this.makeFormArgs("#fcltyRepairMngListVO");
	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyRepairMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'flawRprSeq':result.flawRprSeq};
				
	 			// 하자보수 대상시설물 데이타 적용
	 			if(module.mergeFlawRprObjFcltsF(subVo)){
					// 하자보수 검사자 데이타 적용
					if(module.mergeFlawExamUsrF(subVo)){
						// 하자보수 첨부파일 데이타 적용
						if(module.mergeFcltyRepairFile(subVo)){
						}
					}
	 			}
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{

	 	this.doAction('/fcltyMng/updateFcltyRepairMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			var subVo = {'fcltsJobSe':result.fcltsJobSe,'fcltsMngGroupNo':result.fcltsMngGroupNo,'flawRprSeq':result.flawRprSeq};

	 			// 하자보수 대상시설물 데이타 적용
	 			if(module.mergeFlawRprObjFcltsF(subVo)){
					// 하자보수 검사자 데이타 적용
					if(module.mergeFlawExamUsrF(subVo)){
						// 하자보수 첨부파일 데이타 적용
						if(module.mergeFcltyRepairFile(subVo)){
						}
					}
	 			}
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};

//하자보수 대상시설물 데이타 적용
GamFcltyRepairMngModule.prototype.mergeFlawRprObjFcltsF = function(subVo) {

	var all_rows = this.$('#flawRprObjFcltsF').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["flawRprSeq"] = subVo.flawRprSeq;
	}
	
	var inputFlawRprObjFcltsVO = [];
	inputFlawRprObjFcltsVO[inputFlawRprObjFcltsVO.length]={name: 'updateList', value :JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFlawRprObjFcltsVO[inputFlawRprObjFcltsVO.length]={name: 'insertList', value: JSON.stringify(this.$('#flawRprObjFcltsF').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFlawRprObjFcltsVO[inputFlawRprObjFcltsVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteObjFcltsList) };
	
	var chk = true;
	this.doAction('/fcltyMng/mergeFlawRprObjFcltsF.do', inputFlawRprObjFcltsVO, function(mntnRprObjModule, result) {
        if(result.resultCode != 0){
			chk = false;
        }
    });
	
	return chk;

};

//하자보수 검사자 데이타 적용
GamFcltyRepairMngModule.prototype.mergeFlawExamUsrF = function(subVo) {

	var all_rows = this.$('#flawExamUsrF').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["flawRprSeq"] = subVo.flawRprSeq;
	}

	var inputFlawExamUsrVO = [];
	inputFlawExamUsrVO[inputFlawExamUsrVO.length]={name: 'updateList', value :JSON.stringify(this.$('#flawExamUsrF').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFlawExamUsrVO[inputFlawExamUsrVO.length]={name: 'insertList', value: JSON.stringify(this.$('#flawExamUsrF').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFlawExamUsrVO[inputFlawExamUsrVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataRepairList) };
	
	var chk = true;
	this.doAction('/fcltyMng/mergeFlawExamUsrF.do', inputFlawExamUsrVO, function(mntnRprObjModule, result) {
        if(result.resultCode != 0){
			chk = false;
        }
    });
	
	return chk;

};

//하자보수 첨부파일 데이타 적용
GamFcltyRepairMngModule.prototype.mergeFcltyRepairFile = function(subVo) {

	var all_rows = this.$('#fcltyRepairFileList').flexGetData();
	for(var i=0;i<all_rows.length;i++){
		all_rows[i]["fcltsJobSe"] = subVo.fcltsJobSe;
		all_rows[i]["fcltsMngGroupNo"] = subVo.fcltsMngGroupNo;
		all_rows[i]["flawRprSeq"] = subVo.flawRprSeq;
	}

	var inputFileVO=[];
	inputFileVO[inputFileVO.length]={name: 'updateList', value :JSON.stringify(this.$('#fcltyRepairFileList').selectFilterData([{col: '_updtId', filter: 'U'}])) };
	inputFileVO[inputFileVO.length]={name: 'insertList', value: JSON.stringify(this.$('#fcltyRepairFileList').selectFilterData([{col: '_updtId', filter: 'I'}])) };
	inputFileVO[inputFileVO.length]={name: 'deleteList', value: JSON.stringify(this._deleteDataFileList) };
	
	var chk = true;
    this.doAction('/fcltyMng/mergeFcltyRepairFile.do', inputFileVO, function(fileModule, fileResult) {
        if(fileResult.resultCode != 0){
        	chk = false;
        }
    });
    
    return chk;

};




GamFcltyRepairMngModule.prototype.uploadFileData = function() {
	// 파일을 업로드하고 업로드한 파일 목록을 result에 어레이로 리턴한다.
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			module.$("#fcltyRepairFileList").flexAddRow({'_updtId': 'I', 'atchFileSeq':'', 'atchFileSe':'D', 'atchFileSeNm':'문서',  'atchFileSj': '', 'atchFileNmLogic': this.logicalFileNm, 'atchFileNmPhysicl': this.physcalFileNm, 'atchFileWritngDt': ''});
		});
	}, "첨부파일 업로드");
};


GamFcltyRepairMngModule.prototype.downloadFileData = function() {
	var selectRow = this.$('#fcltyRepairFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamFcltyRepairMngModule.prototype.removeFileData = function() {
	var rows = this.$("#fcltyRepairFileList").selectedRows();

    if(rows.length == 0){
        alert("파일목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#fcltyRepairFileList").selectedRowIds().length>0) {
    	for(var i=this.$("#fcltyRepairFileList").selectedRowIds().length-1; i>=0; i--) {

    		var row = this.$("#fcltyRepairFileList").flexGetRow(this.$("#fcltyRepairFileList").selectedRowIds()[i]);

            if(row._updtId == undefined || row._updtId != "I") {
            	this._deleteDataFileList[this._deleteDataFileList.length] = row;  // 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
			}
        	this.$("#fcltyRepairFileList").flexRemoveRow(this.$("#fcltyRepairFileList").selectedRowIds()[i]);

        	this._edited=true;
		}

    	this.$("#previewImage").attr("src","");
    	alert("삭제되었습니다.");
	}

    this.$("#fcltyRepairMngFileForm").find(":input").val("");
    this._editDataFile = null;
};


GamFcltyRepairMngModule.prototype.fillTitleData = function() {
	var changData = this.makeFormArgs('#fcltyRepairMngListVO');
	this.makeDivValues('#gamObjFcltsDetailForm',changData);
	this.makeDivValues('#gamExamUsrDetailForm',changData);
	
	// 셀렉트박스는 한글처리
	var fcltsJobSeNm = this.$('#fcltsJobSe').find('option:selected').text();
	var flawExamSeNm = this.$('#flawExamSe').find('option:selected').text();
	
	this.$('#objFcltsJobSeNm').text(fcltsJobSeNm);
	this.$('#objFlawExamSeNm').text(flawExamSeNm);
	
	this.$('#examFcltsJobSeNm').text(fcltsJobSeNm);
	this.$('#examFlawExamSeNm').text(flawExamSeNm);
	
};


/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyRepairMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
		// 추가
		case "addObjItemBtn":
			this.addObjFcltsItem();
		break;
		
		case "delObjItemBtn":
			this.delObjFcltsItem();
		break;
		
		// 추가
		case "addExamItemBtn":
			this.addExamUsrItem();
		break;
		
		case "delExamItemBtn":
			this.delExamUsrItem();
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
		
		// 추가/편집
		case "flawExamUsrFPopupBtn":
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
		
		// 엑셀다운로드
		case "btnFcltyRepairMngListExcelDownload":
			this.$('#fcltyRepairMngList').flexExcelDown('/fcltyMng/selectFcltyRepairMngListExcel.do');
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
		
		case "tabs4":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			} 
			if(this._mode=="modify"){
				// tabs2에서 수정사항발생시 반영
				this.fillTitleData();
			}
		break;
		
		case "tabs5":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyRepairMngListTab").tabs("option", {active: 0});
				alert('하자보수 항목을 선택 하세요.');
			} 
		break;
	}
	
};


/**
 * 팝업 close 이벤트
 */
 GamFcltyRepairMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		
		case "selectFcltsMngNo":
			this.$("#oFcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#prtFcltyNm").val(value["prtFcltyNm"]);
			
			// 대상시설물 팝업에서 상태값 변경시 그리드 적용
			this.$(".objFcltsEditItem").trigger("change");
		break;
	
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
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
				<li><a href="#tabs4" class="emdTab">하자검사자</a></li>
				<li><a href="#tabs5" class="emdTab">하자보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="printPage" data-search-option="searchFcltyRepairMngForm" data-url='/fcltyMng/selectFcltyRepairCheckResultPrint.do'>하자검사결과인쇄</button>
					<button id="btnFcltyRepairMngListExcelDownload">엑셀</button>
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
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
								<input type="text" size="36" id="fcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
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
							<td><input id="flawRprAmt" type="text" size="20" title="하자보수금액" class="ygpaNumber" maxlength="16" /></td>
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
					<button id="saveBtn">저장</button>
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
					<button id="addObjItemBtn">추가</button>
		            <button id="delObjItemBtn">삭제</button>
				</div>
				<div class="emdControlPanel">
					<form id="gamObjFcltsForm">
						<table class="searchPanel">
							<tbody>
								<tr>
			                        <th>대상시설물</th>
			                        <td>
			                        	<input id="oFcltsMngNo" type="text" style="width: 150px;" title="시설물관리번호" maxlength="20" class="objFcltsEditItem" disabled="disabled"/>
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
									<td colspan="7"><textarea id="oFlawExamResult" cols="149" rows="3" class="objFcltsEditItem" maxlength="1333"></textarea></td>
								</tr>
								<tr>
									<th>비고</th>
									<td colspan="7"><input id="oRm" type="text" size="151" maxlength="333" class="objFcltsEditItem"/></td>
								</tr>
							</tbody>
						</table>
					</form>
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 하자보수 검사자 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table class="summaryPanel"  style="width:100%;">
					<tbody>
						<tr>
							<th style="font-weight:bold;">하자보수 상세내역</th>
						</tr>
					</tbody>
				</table>
				<form id="gamExamUsrDetailForm">
					<table class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th>시설물관리그룹</th>
								<td><span id="fcltsMngGoupNoNm"></span></td>
								<th>업무구분</th>
								<td><span id="examFcltsJobSeNm"></span></td>
								<th>하자검사구분</th>
								<td><span id="examFlawExamSeNm"></span></td>
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
							<th style="font-weight:bold;">하자 검사자</th>
						</tr>
					</tbody>
				</table>
				<table id="flawExamUsrF" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addExamItemBtn">추가</button>
		            <button id="delExamItemBtn">삭제</button>
				</div>
				<div class="emdControlPanel">
					<form id="gamPopupRepairForm">
						<table class="searchPanel">
							<tbody>
								<tr>
			                        <!-- <th>순번</th>
			                        <td><input id="eSeq" type="text" style="width: 150px;" title="순번" maxlength="3" class="ygpaNumber EditItem" disabled="disabled" /></td> -->
			                        <th>하자검사자</th>
			                        <td><input id="eFlawExamUsr" type="text" style="width: 150px;" title="하자검사자" maxlength="20" class="EditItem"/></td>
									<th>하자검사일자</th>
			                        <td><input id="eFlawExamDt" type="text" style="width: 150px;" title="하자검사일자" class="emdcal EditItem"/></td>
			                        <th>하자검사완료여부</th>
			                        <td>
			                        	<select id="eFlawExamComptYn" class="EditItem">
			                        		<option value="">선택</option>
			                        		<option value="Y">Y</option>
			                        		<option value="N">N</option>
			                        	</select>
			                        </td>
			
								</tr>
							</tbody>
						</table>
					</form>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 하자보수내역 첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltyRepairFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnUploadFile">업로드</button>
								<button id="btnDownloadFile">다운로드</button>
								<button id="btnRemoveFile">삭제</button>
								<button id="saveBtn">저장</button>
							</div>
							<form id="fcltyRepairMngFileForm">
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
										<td><input id="atchFileSj" type="text" size="45" class="fileEditItem" maxlength="26" title="파일제목"/></td>
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