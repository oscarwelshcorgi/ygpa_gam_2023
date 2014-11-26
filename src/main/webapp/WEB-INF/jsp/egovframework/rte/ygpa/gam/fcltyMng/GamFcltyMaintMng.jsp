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
GamFcltyMaintMngModule.prototype.loadComplete = function(params) {
	
	this._mode = "";

	// 테이블 설정
	this.$("#fcltyMngMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fcltyMng/selectFcltyMaintMngList.do" />',
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


	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '<c:url value="/fcltyMng/selectMntnRprObjFcltsF.do" />',
		dataType: "json",
		colModel : [
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



 	this.$("#fcltyMaintFileList").flexigrid({
		module: this,
		url: '<c:url value="/fcltyMng/selectFcltyMaintFileList.do"/>',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",		width:200,		sortable:true,		align:"left"},
					{display:"생성일시",	name:"atchFileWritngDt",		width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});
 	
 	
 	this.$("#fcltyMngMngtList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});
 	
 	this.$("#fcltyMngMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyMaintMngListTab").tabs("option", {active: 1});
	});


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
	
	this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintMngForm');
	this.$('#fcltyMngMngtList').flexOptions({params:searchOpt}).flexReload();
	
};


GamFcltyMaintMngModule.prototype.loadDetail = function(){
	
	// tabs2 항목 데이타로딩
	this.makeFormValues('#fcltyMaintMngListVO', {});
	
	// tabs3 그리드 리로드
	this.$('#mntnRprObjFcltsF').flexOptions({params:searchOpt}).flexReload();
	
	// tabs4 항목 데이타 로딩/ 그리드 리로드
	this.makeFormValues('#fcltyMaintMngFileForm', {});
	this.$('#fcltyMaintFileList').flexOptions({params:searchOpt}).flexReload();
	
};


GamFcltyMaintMngModule.prototype.addData = function() {

	this._mode="insert";
	this.$("#fcltyMaintMngListTab").tabs("option", {active: 1});

};


GamFcltyMaintMngModule.prototype.saveData = function() {
	
	if(!validateFcltyMaintMngVO(this.$("#fcltyMaintMngListVO")[0])) return;

 	var inputVO = this.makeFormArgs("#fcltyMaintMngListVO");
 	//alert(this._mode);
	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintMngModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltyMngMngtList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("삭제하시겠습니까?")){
		var inputVO = this.makeFormArgs("#fcltyMaintMngListVO");
	 	this.doAction('/fcltyMng/deleteFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltyMaintMngModule.prototype.addEditData = function() {
	
	var all_rows = this.$('#mntnRprObjFcltsF').flexGetData();
	for(i=0;i<all_rows.length;i++){
		all_rows[i]["_updtId"] = "";
	}

	this.doExecuteDialog("mntnRprObjFcltsFPopup", "유지보수대상시설물현황", '/popup/selectMntnRprObjFcltsFPopup.do', {},all_rows);

};


GamFcltyMaintMngModule.prototype.uploadFileData = function() {
	// 파일을 업로드하고 업로드한 파일 목록을 result에 어레이로 리턴한다.
	this.uploadPfPhoto("uploadFile", function(module, result) {
		var userid = "admin";
		console.log('debug');
		$.each(result, function(){
			alert(this.physcalFileNm);
			module.$("#fcltyMaintFileList").flexAddRow({_updtId:'I', atchFileSeq:"", atchFileSe:"D", atchFileSeNm:"문서",  atchFileSj: "", atchFileNmLogic: this.logicalFileNm, atchFileNmPhysicl: this.physcalFileNm, atchFileWritngDt: ""});
		});
	}, "첨부파일 업로드");

};


GamFcltyMaintMngModule.prototype.downloadFileData = function() {
	
	var selectRow = this.$('#fcltyMaintFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfFile(row["filenmPhysicl"], row["filenmLogic"]);
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



/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyMaintMngModule.prototype.onButtonClick = function(buttonId) {

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
		case "mntnRprObjFcltsFPopupBtn":
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
	}
};


GamFcltyMaintMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if(oldTabId == "tabs1"){
				if(this._mode=="modify") {
					this.loadDetail();
				} else {
					this._mode="insert";
					// tabs2 초기화
					this.makeFormValues('#fcltyMaintMngListVO', {});
					// tabs3 초기화
					this.$("#mntnRprObjFcltsF").flexEmptyData();
					// tabs4 초기화
					this.makeFormValues('#fcltyMaintMngFileForm', {});
					this.$("#fcltyMaintFileList").flexEmptyData();
				}
			}
		break;
		
		case "tabs3":
		break;
		
		case "tabs4":
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
							<th>유지보수공사명</th>
							<td><input type="text" id="sMntnRprCnstNm" size="50" /></td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>유지보수구분</th>
							<td>
								<select id="sMntnRprSe">
									<option value="">선택</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<input id="sMntnRprCnstStartDtFr" type="text" class="emdcal" size="15" /> ~ <input id="sMntnRprCnstStartDtTo" type="text" class="emdcal" size="15" />
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
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="15%" height="23" class="required_text">시행년도</th>
							<td>
								<select id="enforceYear">
									<option value="2014">2014</option>
									<option value="2013">2013</option>
								</select>
							</td>
							<th width="15%" height="23" class="required_text">시설물업무구분</th>
							<td><input type="text" size="20" id="fcltsJobSe" /></td>
							<th width="15%" height="23" class="required_text">유지보수구분</th>
							<td><input type="text" size="20" id="mntnRprSe" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">계약번호</th>
							<td colspan="3"><input type="text" size="80" id="ctrtNo" /></td>
							<th width="15%" height="23" class="required_text">유지보수순번</th>
							<td><input type="text" size="20" id="mntnRprSeq" /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사명</th>
							<td colspan="5"><input id="mntnRprCnstNm" type="text" size="125" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">유지보수부위</th>
							<td colspan="5"><input id="mntnRprPart" type="text" size="125" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">예산</th>
							<td colspan="3"><input id="mntnRprBdgt" type="text" size="80" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사시작일자</th>
							<td><input id="mntnRprCnstStartDt" type="text" size="20" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">공사금액</th>
							<td colspan="3"><input id="mntnRprCnstAmt" type="text" size="80" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사종료일자</th>
							<td><input id="mntnRprCnstEndDt" type="text" size="20" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">설계자</th>
							<td><input id="plannerNm" type="text" size="20" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">시공자</th>
							<td colspan="3"><input id="cnstrtr" type="text" size="80" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">책임기술자</th>
							<td><input id="responEngineer" type="text" size="20" title="소재지"  /></td>
							<th width="15%" height="23" class="required_text">공사감독자</th>
							<td colspan="3"><input id="cnstChargNm" type="text" size="80" title="소재지"  /></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">유지보수내용</th>
							<td colspan="5"><textarea id="mntnRprCn" cols="130" rows="10"></textarea></td>
						</tr>
						<tr>
							<th width="15%" height="23" class="required_text">비고</th>
							<td colspan="5"><input id="rm" type="text" size="125" title="소재지"  /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 유지보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="mntnRprObjFcltsF" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button class="text" id="mntnRprObjFcltsFPopupBtn">추가/편집</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>

			<!-- 유지보수내역 첨부파일 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyMaintFileList" style="display:none" class="fillHeight"></table>
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