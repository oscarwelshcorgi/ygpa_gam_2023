<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyRepairHistInqire.jsp
  * @Description : 시설물 하자보수이력 조회
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
 * @FUNCTION NAME : GamFcltyRepairHistInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyRepairHistInqireModule() {
}

GamFcltyRepairHistInqireModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyRepairHistInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#fcltyRepairHistInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyRepairHistInqireList.do',
		dataType: "json",
		colModel : [
					{display:"시행년도", 			name:"enforceYear",				width:60, 		sortable:false,		align:"center"},
					{display:"계약번호", 			name:"ctrtNo",					width:200, 		sortable:false,		align:"center"},
					{display:"업체명",			name:"flawRprEntrpsNm",			width:200,		sortable:true,		align:"left"},
					{display:"시설물명",			name:"prtFcltyNm",				width:200,		sortable:true,		align:"left"},
					{display:"하자보수순번", 		name:"flawRprSeq",				width:120, 		sortable:false,		align:"center"},
					{display:"하자검사구분",		name:"flawExamSeNm",			width:80, 		sortable:false,		align:"center"},
					{display:"하자유무",			name:"flawEnnc",				width:90,		sortable:true,		align:"center"},
					{display:"하자검사일자",		name:"flawExamDt",				width:100,		sortable:true,		align:"center"},
					{display:"하자보수유형",		name:"flawRprTyNm",				width:80, 		sortable:false,		align:"center"},
					{display:"하자보수명",			name:"flawRprNm",				width:250, 		sortable:false,		align:"left"},
					{display:"하자보수금액", 		name:"flawRprAmt",				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"하자보수완료여부", 	name:"flawRprComptYn",			width:150, 		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

 	
 	this.$("#fcltyRepairHistInqireList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.$("#fcltyRepairHistInqireListTab").tabs("option", {active: 1});
	});
 	
 	
 	// 하자보수시설명 검색조건 클릭시 초기화 처리
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
GamFcltyRepairHistInqireModule.prototype.onSubmit = function(){
	this.loadData();
};


<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairHistInqireModule.prototype.loadData = function(){

	// tabs2 항목 초기화
	this.makeDivValues('#fcltyRepairHistInqireListVO', {});

	this.$("#fcltyRepairHistInqireListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyRepairHistInqireForm');
	this.$('#fcltyRepairHistInqireList').flexOptions({params:searchOpt}).flexReload();
	
};


<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyRepairHistInqireModule.prototype.loadDetail = function(){
	
	var row = this.$('#fcltyRepairHistInqireList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyRepairHistInqireListTab").tabs("option", {active: 0});
		return;
	}
	
	row = row[0];
	
	// tabs2 항목 데이타로딩
	this.doAction('/fcltyMng/selectFcltyRepairHistInqireDetail.do', row, function(module, result) {
		if(result.resultCode == "0"){
			module.makeDivValues('#fcltyRepairHistInqireListVO', result.result);
		}else{
			module.$("#fcltyRepairHistInqireListTab").tabs("option", {active: 0});
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
GamFcltyRepairHistInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#fcltyRepairHistInqireList").flexRowCount();
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
			this.$('#fcltyRepairHistInqireList').flexExcelDown('/fcltyMng/selectFcltyRepairHistListExcel.do');
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
GamFcltyRepairHistInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
 GamFcltyRepairHistInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		case "btnSearchFcltsMngNo":
			this.doExecuteDialog("selectFcltsMngNo", "하자보수 시설명", '/popup/showFcltsMngNo.do', {}, {});
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
 GamFcltyRepairHistInqireModule.prototype.onClosePopup = function(popupId, msg, value){

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
var module_instance = new GamFcltyRepairHistInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyRepairHistInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시공업체명</th>
							<td colspan="3"><input type="text" id="sFlawRprEntrpsNm" size="100" title="시공업체" /></td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>하자보수 시설명</th>
							<td>
								<input type="text" size="10" id="sFcltsMngNo" />
								<input type="text" size="20" id="sPrtFcltyNm" disabled="disabled"  title="유지보수 시설명" />
								<button id="btnSearchFcltsMngNo" class="popupButton">선택</button>
							</td>
							<th>하자보수공사시작일</th>
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
		<div id="fcltyRepairHistInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">하자보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">하자보수내역 상세</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyRepairHistInqireList" style="display:none" class="fillHeight"></table>
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


			<!-- 하자보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyRepairHistInqireListVO">
					<table class="editForm"  style="width:100%;">
						<tr>
							<th width="30px" height="23" class="required_text">시행년도</th>
							<td width="100px"><span id="enforceYear" title="시행년도"></span></td>
							<th width="30px" height="23" class="required_text">하자유무</th>
							<td width="100px"><span id="flawEnnc" title="하자유무"></span></td>
							<th width="30px" height="23" class="required_text">시설물업무구분</th>
							<td width="100px"><span id="fcltsJobSeNm" title="시설물업무구분"></span></td>
							<th width="30px" height="23" class="required_text">하자보수유형</th>
							<td><span id="flawRprTyNm" title="하자보수유형"></span></td>
						</tr>
						<tr>
							<th height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo" title="시설물관리그룹넘버" ></span>
								[ <span id="fcltsMngGoupNm" title="시설물관리그룹명"></span> ]
							</td>
							<th height="17" class="required_text">시설명</th>
							<td>
								<span id="prtFcltyNm" title="시설명"></span>
							</td>
						</tr>
						<tr>
							<th height="23" class="required_text">계약번호</th>
							<td colspan="5">
								<span id="ctrtNo" title="계약번호"></span> 
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
							<td><span id="flawExamSeNm" title="하자검사구분"></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자발생일자</th>
							<td><span id="flawOccrrncDt" title="하자발생일자" ></span></td>
							<th height="23" class="required_text">하자검사일자</th>
							<td><span id="flawExamDt" title="하자검사일자" ></span></td>
							<th height="23" class="required_text">하자보수시작일자</th>
							<td><span id="flawRprStartDt" title="하자보수시작일자" ></span></td>
							<th height="23" class="required_text">하자보수종료일자</th>
							<td><span id="flawRprEndDt" title="하자보수종료일자" ></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수내용</th>
							<td colspan="7"><span id="flawRprContents" title="하자보수내용" ></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">하자보수결과</th>
							<td colspan="7"><span id="flawExamResult" title="하자보수결과" ></span></td>
						</tr>
						<tr>
							<th height="23" class="required_text">비고</th>
							<td colspan="7"><span id="rm" title="비고"></span></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>