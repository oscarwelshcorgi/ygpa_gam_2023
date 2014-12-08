<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcHistInqire.jsp
  * @Description : 시설 점검이력 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.08  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.08
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyQcHistInqireModule() {
}

GamFcltyQcHistInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyQcHistInqireModule.prototype.loadComplete = function(params) {
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
		
	this.$("#qcMngDtlsList").on('onItemSelected', function(event, module, row, grid, param) {
	});
	
	this.$("#qcMngDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
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
};


GamFcltyQcHistInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//점검관리내역 조회
GamFcltyQcHistInqireModule.prototype.loadData = function() {
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcHistInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		//점검관리내역 목록 조회
		case "searchBtn":
			break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyQcHistInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcHistInqireModule.prototype.onClosePopup = function(popupId, msg, value){
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
var module_instance = new GamFcltyQcHistInqireModule();
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
							<th>시설물관리번호</th>
							<td>
							</td>
							<th>점검기관명</th>
							<td><input type="text" id="sQcInspInsttNm" size="50" /></td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
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
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcMngDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
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
								<span id="fcltsMngGroupNo"></span>
								<span id="fcltsMngGroupNm"></span>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<span id="qcMngSeq"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시설물업무구분</th>
							<td>
								<span id="fcltsJobSe"></span>
								<span id="fcltsJobSeNm"></span>
							</td>
							<th width="12%" height="17">점검관리명</th>
							<td colspan="3">
								<span id="qcMngNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시행년도</th>
							<td>
								<span id="enforceYear"></span>
							</td>
							<th width="12%" height="17">점검진단일자</th>
							<td><span id="qcInspDt"></span></td>
							<th width="12%" height="17">점검진단기관명</th>
							<td><span id="qcInspInsttNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<span id="qcInspSe"></span>
								<span id="qcInspSeNm"></span>
							</td>
							<th width="12%" height="17">점검시작일자</th>
							<td><span id="qcBeginDt"></span></td>
							<th width="12%" height="17">점검종료일자</th>
							<td colspan="3"><span id="qcEndDt"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17">책임기술자명</th>
							<td><span id="responEngineerNm"></span></td>
							<th width="12%" height="17">점검진단예산</th>
							<td><span id="qcInspBdgt"></span></td>
							<th width="12%" height="17">점검진단금액</th>
							<td><span id="qcInspAmt"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17">상태평가등급</th>
							<td colspan="5">
								<span id="sttusEvlLvl"></span>
								<span id="sttusEvlLvlNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="120" rows="7" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치구분</th>
							<td colspan="5">
								<span id="actionSe"></span>
								<span id="actionSeNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="120" rows="7" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
					</table>
				</div>
				</form>
				<div class="emdControlPanel">
				</div>
			</div>
			
			<!-- 점검관리대상시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngObjFcltsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>
			
			<!-- 점검관리결과 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="qcMngResultItemList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>			

		</div>
	</div>
</div>