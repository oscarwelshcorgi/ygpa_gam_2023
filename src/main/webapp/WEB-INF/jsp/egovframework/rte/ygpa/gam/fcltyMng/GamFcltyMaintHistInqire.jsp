<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyMaintHistInqire.jsp
  * @Description : 시설물 유지보수이력 조회
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
<%
/******************************** SCRIPT START ********************************/
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyMaintHistInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyMaintHistInqireModule() {}

GamFcltyMaintHistInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMaintHistInqireModule.prototype.loadComplete = function() {
	
	this._mode = "";
	//console.log("GamFcltyMaintHistInqireModule");
	// 테이블 설정
	this.$("#fcltyMaintHistInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintHistInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시행년도", 			name:"enforceYear",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"시공업체", 			name:"cnstrtr",					width:200, 		sortable:false,		align:"center"},
					{display:"시설명", 			name:"prtFcltyNm",				width:200, 		sortable:false,		align:"center"},
					{display:"유지보수순번", 		name:"mntnRprSeq",				width:120, 		sortable:false,		align:"center"},
					{display:"유지보수구분",		name:"mntnRprSeNm",				width:80, 		sortable:false,		align:"center"},
					{display:"유지보수공법",		name:"mntnRprCnstMth",			width:80,		sortable:false,		align:"center"},
					{display:"단위",				name:"unit",					width:80,		sortable:false,		align:"center"},
					{display:"수량",				name:"qy",						width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"단가",				name:"price",					width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"공사금액",			name:"objMntnRprCnstAmt",		width:140,		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수예산", 		name:"mntnRprBdgt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수부위", 		name:"mntnRprPart",				width:250, 		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

 	
 	
 	this.$("#fcltyMaintHistInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.$("#fcltyMaintHistInqireListTab").tabs("option", {active: 1});
	});
 	
 	
	// 파일 정보 속성이 변경 된 경우 이벤트 실행
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});
	
	
	// 유지보수시설명 검색조건 클릭시 초기화 처리
	this.$("#sFcltsMngNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngNo").val('');
		event.data.module.$("#sPrtFcltyNm").val('');
	});
	
};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintHistInqireModule.prototype.onSubmit = function(){
	
	// 날짜 무결성 체크
	if(!this.searchDateChk()){
		return;
	}
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintHistInqireModule.prototype.loadData = function(){
	
	// tabs2 항목 초기화
	this.makeDivValues('#fcltyMaintHistInqireListVO', {});
	
	this.$("#fcltyMaintHistInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintHistInqireForm');
	this.$('#fcltyMaintHistInqireList').flexOptions({params:searchOpt}).flexReload();
	
};


<%
/**
 * @FUNCTION NAME : searchDateChk
 * @DESCRIPTION   : 조회시 날짜의 유지보수공사시작일과 종료일 무결성 체크함수
 * @PARAMETER     : target
**/
%>
GamFcltyMaintHistInqireModule.prototype.searchDateChk = function() {
	var mntnRprCnstStartDt = this.$("#sMntnRprCnstStartDtFr").val();
	var mntnRprCnstEndDt = this.$("#sMntnRprCnstStartDtTo").val();
	
	var chk = true;

	if(!EMD.util.isDate(mntnRprCnstStartDt) && mntnRprCnstStartDt){
		alert("유지보수 공사시작일이 날짜형식이 아닙니다.");
		chk = false;
	}
	if(!EMD.util.isDate(mntnRprCnstEndDt) && mntnRprCnstEndDt){
		alert("유지보수 공사종료일이 날짜형식이 아닙니다.");
		chk = false;
	}
	
	if(!mntnRprCnstStartDt && mntnRprCnstEndDt){
		alert("공사시작일을 입력해주세요.");
		chk = false;
	}
	

	if(mntnRprCnstStartDt && mntnRprCnstEndDt){
		if(EMD.util.strToDate(mntnRprCnstStartDt).getTime() > EMD.util.strToDate(mntnRprCnstEndDt).getTime()){
			alert("공사종료일이 공사시작일보다 크거나 같아야합니다.");
			chk = false;
		}
	}

	return chk;

};


<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintHistInqireModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyMaintHistInqireList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintHistInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	
	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyMaintHistInqireDetail.do', row, function(module, result) {
		if(result.resultCode == "0"){
			module.makeDivValues('#fcltyMaintHistInqireListVO', result.result);
		}else{
			module.$("#fcltyMaintHistInqireListTab").tabs("option", {active: 0});
		}
    });
	
};


<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintHistInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#fcltyMaintHistInqireList").flexRowCount();
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
			this.$('#fcltyMaintHistInqireList').flexExcelDown('/fcltyMng/selectFcltyMaintHistInqireListExcel.do');
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
GamFcltyMaintHistInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
 GamFcltyMaintHistInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		case "btnSearchFcltsMngNo":
			this.doExecuteDialog("selectFcltsMngNo", "유지보수 시설명", '/popup/showFcltsMngNo.do', {}, {});
		break;
		
		// 엑셀다운로드
		case "btnExcelDownload":
			this.downloadExcel(buttonId);
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
 GamFcltyMaintHistInqireModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		case "selectFcltsMngNo":
			this.$("#sFcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#sPrtFcltyNm").val(value["prtFcltyNm"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");

		break;
	}
};




// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMaintHistInqireModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyMaintHistInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시공업체명</th>
							<td colspan="3"><input type="text" id="sCnstrtr" size="130" title="시공업체" /></td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>유지보수 시설명</th>
							<td>
								<input type="text" size="10" id="sFcltsMngNo" />
								<input type="text" size="20" id="sPrtFcltyNm" disabled="disabled"  title="유지보수 시설명" />
								<button id="btnSearchFcltsMngNo" class="popupButton">선택</button>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<input id="sMntnRprCnstStartDtFr" type="text" class="emdcal" size="15" title="유지보수공사검색시작일" /> ~ <input id="sMntnRprCnstStartDtTo" type="text" class="emdcal" size="15" title="유지보수공사검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMaintHistInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수이력 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수이력 상세</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintHistInqireList" style="display:none" class="fillHeight"></table>
				<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintSttusInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:250px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload">엑셀 다운로드</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			</div>
			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintHistInqireListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="30px" height="23" class="required_text">시행년도</th>
							<td width="150px"><span id="enforceYear" title="시행년도"></span></td>
							<th width="30px" height="23" class="required_text">시설물업무구분</th>
							<td width="150px"><span id="fcltsJobSeNm" title="시설물업무구분"></span></td>
							<th width="30px" height="23" class="required_text">유지보수구분</th>
							<td><span id="mntnRprSeNm" title="유지보수구분"></span></td>
						</tr>
						<tr>
							<th height="17" class="required_text">시설물관리그룹</th>
							<td colspan="3">
								<span id="fcltsMngGroupNo" title="시설물관리그룹넘버"></span>
								[ <span id="fcltsMngGoupNm" title="시설물관리그룹명"></span> ]
							</td>
							<th height="17" class="required_text">시설명</th>
							<td>
								<span id="prtFcltyNm" title="시설명"></span>
							</td>
						</tr>
						<tr>
							<th height="23" class="required_text">계약번호</th>
							<td colspan="3">
								<span id="ctrtNo" title="계약번호"></span>
								[ <span id="ctrtNm" title="계약명"></span> ]
							</td>
							<th height="23" class="required_text">유지보수순번</th>
							<td><span id="mntnRprSeq" title="유지보수순번"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">공사명</th>
							<td colspan="5"><span id="mntnRprCnstNm" title="공사명"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">유지보수부위</th>
							<td colspan="5"><span id="mntnRprPart" title="유지보수부위"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">예산</th>
							<td colspan="3"><span id="mntnRprBdgt" title="예산" class="ygpaNumber"></span> 원</td>
							<th height="23" class="required_text">공사시작일자</th>
							<td><span id="mntnRprCnstStartDt" title="공사시작일자"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">공사금액</th>
							<td colspan="3"><span id="mntnRprCnstAmt" title="공사금액" class="ygpaNumber"></span> 원</td>
							<th height="23" class="required_text">공사종료일자</th>
							<td><span id="mntnRprCnstEndDt" title="공사종료일자"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">설계자</th>
							<td><span id="plannerNm" title="설계자"></span></td>
							<th height="23" class="required_text">시공자</th>
							<td colspan="3"><span id="cnstrtr" title="시공자"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">책임기술자</th>
							<td><span id="responEngineer" title="책임기술자"></span></td>
							<th height="23" class="required_text">공사감독자</th>
							<td colspan="3"><span id="cnstChargNm" title="공사감독자"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">유지보수내용</th>
							<td colspan="5"><span id="mntnRprCn" title="유지보수내용"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">비고</th>
							<td colspan="5"><span id="rm" title="비고"></span></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>