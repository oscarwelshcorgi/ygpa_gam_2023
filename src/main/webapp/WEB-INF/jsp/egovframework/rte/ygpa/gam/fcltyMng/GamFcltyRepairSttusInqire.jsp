<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairSttusInqire.jsp
  * @Description : 시설물 하자보수현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.9  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.12.9
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyRepairSttusInqireModule() {
}

GamFcltyRepairSttusInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyRepairSttusInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#fcltyRepairSttusInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairSttusInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시행년도", 			name:"enforceYear",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"하자보수순번", 		name:"flawRprSeq",				width:120, 		sortable:false,		align:"center"},
					{display:"하자검사구분",		name:"flawExamSe",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수유형",		name:"flawRprTy",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수명",			name:"flawRprNm",				width:250, 		sortable:false,		align:"left"},
					{display:"하자보수금액", 		name:"flawRprAmt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"하자보수완료여부", 	name:"flawRprComptYn",			width:150, 		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	
	this.$("#flawRprSttusObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawRprSttusObjFcltsF.do',
		dataType: 'json',
		colModel : [
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"left"},
					{display:"하자유무",		name:"flawEnnc",	width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",	name:"flawExamDt",	width:100,		sortable:true,		align:"center"},
					{display:"비고",			name:"rm",			width:350,		sortable:true,		align:"left"}
			],
		height: "auto"
	});


	this.$("#flawExamUsrSttusF").flexigrid({
		module: this,
		url: '/fcltyMng/selectFlawExamUsrSttusFList.do',
		dataType: "json",
		colModel : [
					{display:"순번",				name:"seq",					width:100,		sortable:false,		align:"center"},
					{display:"하자검사자",			name:"flawExamUsr",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",			width:250,		sortable:false,		align:"center"},
					{display:"하자검사완료여부",		name:"flawExamComptYn",		width:250,		sortable:false,		align:"center"}
			],
		height: "auto"
	});



 	this.$("#fcltyRepairSttusFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairSttusFileList.do',
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

 	
 	this.$("#fcltyRepairSttusInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 1});
	});
 	
 	this.$("#fcltyRepairSttusFileList").on('onItemSelected', function(event, module, row, grid, param) {
 		module.applyFileDataChanged();
	});
 	
 	
	// 파일 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});


};



// 파일 그리드 선택시 하단부 데이타 수정창에 갱신
GamFcltyRepairSttusInqireModule.prototype.applyFileDataChanged = function(){

	var row = this.$('#fcltyRepairSttusFileList').selectedRows();
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
			this.$("#previewImage").attr("src", "");
		}
	}
};


GamFcltyRepairSttusInqireModule.prototype.onSubmit = function(){
	this.loadData();
};

GamFcltyRepairSttusInqireModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeDivValues('#fcltyRepairSttusInqireListVO', {});
	
	// tabs3 그리드 초기화
	this.$('#flawRprSttusObjFcltsF').flexEmptyData();
	
	// tabs4 그리드 초기화
	this.$('#flawExamUsrSttusF').flexEmptyData();
	
	// tabs5 항목/그리드 초기화
	this.makeDivValues('#fcltyRepairSttusInqireFileForm', {});
	this.$('#fcltyRepairSttusFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "");
	
	this.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairSttusInqireForm');
	this.$('#fcltyRepairSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
};


GamFcltyRepairSttusInqireModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyRepairSttusInqireList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairSttusInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'flawRprSeq', value: row['flawRprSeq'] }
	               ];

	// tabs2 항목 데이타로딩
	this.makeDivValues('#fcltyRepairSttusInqireListVO', row);
	// tabs3 그리드 리로드
	this.$('#flawRprSttusObjFcltsF').flexOptions({params:searchVO}).flexReload();
	// tabs4 그리드 리로드
	this.$('#flawExamUsrSttusF').flexOptions({params:searchVO}).flexReload();
	// tabs5 항목 데이타 로딩/ 그리드 리로드
	this.makeDivValues('#fcltyRepairSttusInqireFileForm', {});
	this.$("#previewImage").attr("src", "");
	this.$('#fcltyRepairSttusFileList').flexOptions({params:searchVO}).flexReload();
	
};


GamFcltyRepairSttusInqireModule.prototype.downloadFileData = function() {
	var selectRow = this.$('#fcltyRepairSttusFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


GamFcltyRepairSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
		
		case "tabs5":

		break;
	}
	
};




// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyRepairSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyRepairSttusInqireForm">
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
							<th>하자보수명</th>
							<td><input type="text" id="sFlawRprNm" size="50" title="하자보수명" /></td>
							<td rowspan="2"><button class="buttonSearch" title="조회">조회</button></td>
						</tr>
						<tr>
							<th>하자검사구분</th>
							<td>
								<select id="sFlawExamSe" title="하자검사구분">
									<option value="">선택</option>
									<option value="r1">하자보수1</option>
									<option value="r2">하자보수2</option>
									<option value="r3">하자보수3</option>
								</select>
							</td>
							<th>하자검사일</th>
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
		<div id="fcltyRepairSttusInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
				<li><a href="#tabs3" class="emdTab">하자보수대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">하자검사자</a></li>
				<li><a href="#tabs5" class="emdTab">하자보수 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairSttusInqireList" style="display:none" class="fillHeight"></table>
			</div>


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyRepairSttusInqireListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="30px" height="23" class="required_text">시행년도</th>
							<td width="100px"><span id="enforceYear" title="시행년도"></span></td>
							<th width="30px" height="23" class="required_text">하자유무</th>
							<td width="100px"><span id="flawEnnc" title="하자유무"></span></td>
							<th width="30px" height="23" class="required_text">시설물업무구분</th>
							<td width="100px"><span id="fcltsJobSe" title="시설물업무구분"></span></td>
							<th width="30px" height="23" class="required_text">하자보수유형</th>
							<td><span id="flawRprTy" title="하자보수유형"></span></td>
						</tr>
						<tr>
							<th height="17" class="required_text">시설물관리그룹</th>
							<td colspan="7">
								<span id="fcltsMngGroupNo" title="시설물관리그룹넘버" ></span>
								<span id="fcltsMngGroupNoNm" title="시설물관리그룹명"></span>
							</td>
						</tr>
						<tr>
							<th height="23" class="required_text">계약번호</th>
							<td colspan="5">
								<span id="ctrtNo" title="계약번호"></span> 
								<span id="ctrtNm" title="계약명"></span>
							</td>
							<th height="23" class="required_text">하자보수순번</th>
							<td><span id="flawRprSeq" title="하자보수순번" ></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수명</th>
							<td colspan="7"><span id="flawRprNm" title="하자보수명"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수업체명</th>
							<td colspan="7"><span id="flawRprEntrpsNm" title="하자보수업체명"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수완료여부</th>
							<td><span id="flawRprComptYn" title="하자보수완료여부"></span></td>
							<th height="23" class="required_text">하자보수금액</th>
							<td colspan="3"><span id="flawRprAmt" title="하자보수금액" class="ygpaNumber"></span></td>
							<th>하자검사구분</th>
							<td><span id="flawExamSe" title="하자검사구분"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자발생일자</th>
							<td><span id="flawOccrrncDt" title="하자발생일자" class="emdcal"></span></td>
							<th height="23" class="required_text">하자검사일자</th>
							<td><span id="flawExamDt" title="하자검사일자" class="emdcal"></span></td>
							<th height="23" class="required_text">하자보수시작일자</th>
							<td><span id="flawRprStartDt" title="하자보수시작일자" class="emdcal"></span></td>
							<th height="23" class="required_text">하자보수종료일자</th>
							<td><span id="flawRprEndDt" title="하자보수종료일자" class="emdcal"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수내용</th>
							<td colspan="7"><textarea id="flawRprContents" cols="130" rows="10" disabled="disabled" title="하자보수내용"></textarea></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수결과</th>
							<td colspan="7"><textarea id="flawExamResult" cols="130" rows="10" disabled="disabled" title="하자보수결과"></textarea></td>
						</tr>
						<tr>
							<th height="23" class="required_text">비고</th>
							<td colspan="7"><span id="rm" title="비고"></span></td>
						</tr>
					</table>
				</form>
			</div>
			
			<!-- 하자보수 대상 시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="flawRprSttusObjFcltsF" style="display:none" class="fillHeight"></table>
			</div>
			
			<!-- 하자보수 검사자 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="flawExamUsrSttusF" style="display:none" class="fillHeight"></table>
			</div>

			<!-- 하자보수내역 첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyRepairSttusFileList" style="display:none" class="fillHeight"></table>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>