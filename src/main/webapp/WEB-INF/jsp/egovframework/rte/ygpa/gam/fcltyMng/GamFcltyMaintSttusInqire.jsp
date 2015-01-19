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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMaintSttusInqireModule() {}

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
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});


	this.$("#mntnSttusRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectMntnSttusRprObjFcltsFList.do',
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
		height: "auto"
	});



 	this.$("#fcltyMaintSttusFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintSttusFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"}
			],
		height: "360"
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
	
};


// 파일 그리드 선택시 하단부 데이타 수정창에 갱신
GamFcltyMaintSttusInqireModule.prototype.applyFileDataChanged = function(){

	var row = this.$('#fcltyMaintSttusFileList').selectedRows();
	row = row[0];

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


GamFcltyMaintSttusInqireModule.prototype.onSubmit = function(){
	this.loadData();
};

GamFcltyMaintSttusInqireModule.prototype.loadData = function(){
	
	// tabs2 항목 초기화
	this.makeDivValues('#fcltyMaintSttusInqireListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#mntnSttusRprObjFcltsF').flexEmptyData();
	
	// tabs4 항목/그리드 초기화
	this.makeDivValues('#fcltyMaintSttusInqireFileForm', {});
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
	this.doAction('/fcltyMng/selectFcltyMaintSttusInqireDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeDivValues('#fcltyMaintSttusInqireListVO', result.result);
			
			// tabs3 그리드 리로드
			module.$('#mntnSttusRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			// tabs4 항목 데이타 로딩/ 그리드 리로드
			module.makeDivValues('#fcltyMaintSttusInqireFileForm', {});
			module.$("#previewImage").attr("src", "");
			module.$('#fcltyMaintSttusFileList').flexOptions({params:searchVO}).flexReload();
		}else{
			module.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		}
    });
	
};


GamFcltyMaintSttusInqireModule.prototype.downloadFileData = function() {
	var selectRow = this.$('#fcltyMaintSttusFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamFcltyMaintSttusInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#fcltyMaintSttusInqireList").flexRowCount();
			break;
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			this.$('#fcltyMaintSttusInqireList').flexExcelDown('/fcltyMng/selectFcltyMaintInqireListExcel.do');
			break;
	}

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyMaintSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel(buttonId);
		break;
		
		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
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



GamFcltyMaintSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1') {
		this.loadDetail();
	}
	
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);                                                                                                                                                                                                                                                                         
			}
		break;
		
		case "tabs3":
		break;
		
		case "tabs4":
		break;
	}
	
};


/**
 * 팝업 close 이벤트
 */
 GamFcltyMaintSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		
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
							<th>시설물관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo" title="시설물관리그룹넘버" />-
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
		<div id="fcltyMaintSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">유지보수 대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">유지보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintSttusInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:250px;text-align:right;"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload">엑셀 다운로드</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintSttusInqireListVO">
					<table class="editForm"  style="width:100%;" border="1">
						<tr>
							<th width="13%" height="23" class="required_text">시행년도</th>
							<td width="15%"><span id="enforceYear" title="시행년도"></span></td>
							<th width="10%" height="23" class="required_text">시설물업무구분</th>
							<td width="15%"><span id="fcltsJobSeNm" title="시설물업무구분"></span></td>
							<th width="10%" height="23" class="required_text">유지보수구분</th>
							<td width="300px"><span id="mntnRprSeNm" title="유지보수구분"></span></td>
						</tr>
						<tr>
							<th width="13%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="3">
								<span id="fcltsMngGroupNo" title="시설물관리그룹넘버"></span> 
								[ <span id="fcltsMngGroupNoNm" title="시설물관리그룹명"></span> ]
							</td>
							<th width="10%" height="23" class="required_text">유지보수순번</th>
							<td><span id="mntnRprSeq" title="유지보수순번"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<span id="ctrtNo" title="계약번호"></span> 
								[ <span id="ctrtNm" title="계약명"></span> ]
							</td>
							<th width="10%" height="23" class="required_text">공사기간</th>
							<td><span id="mntnRprCnstStartDt" title="공사시작일자"  ></span> ~ <span id="mntnRprCnstEndDt" title="공사종료일자"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">공사명</th>
							<td colspan="5"><span id="mntnRprCnstNm" title="공사명"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">유지보수부위</th>
							<td colspan="5"><span id="mntnRprPart" title="유지보수부위"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">예산</th>
							<td style="text-align:right;"><span id="mntnRprBdgt" title="예산" class="ygpaNumber"></span></td>
							<th width="10%" height="23" class="required_text">설계자</th>
							<td><span id="plannerNm" title="설계자"  ></span></td>
							<th width="10%" height="23" class="required_text">시공자</th>
							<td><span id="cnstrtr" title="시공자"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">공사금액</th>
							<td style="text-align:right;"><span id="mntnRprCnstAmt" title="공사금액" class="ygpaNumber"></span></td>
							<th width="10%" height="23" class="required_text">책임기술자</th>
							<td><span id="responEngineer" title="책임기술자"></span></td>
							<th width="13%" height="23" class="required_text">공사감독자</th>
							<td><span id="cnstChargNm" title="공사감독자"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">유지보수내용</th>
							<td colspan="5"><span id="mntnRprCn" title="유지보수내용"></span></td>
						</tr>
						<tr>
							<th width="13%" height="23" class="required_text">비고</th>
							<td colspan="5"><span id="rm" title="비고"></span></td>
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
				<table>
					<tr>
						<td width="50%">
							<table id="fcltyMaintSttusFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnDownloadFile">다운로드</button>
							</div>
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