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
	this.$("#qcHistDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcHistDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:120,		sortable:false,		align:"center"},
					{display:"점검시설명",		name:"prtfcltyNm",			width:100,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"시행년도",		name:"enforceYear",			width:70,		sortable:false,		align:"center"},
					{display:"점검관리명",		name:"qcMngNm", 			width:120,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSe",			width:90,		sortable:false,		align:"center"},
					{display:"점검시작일자",	name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",			    width:90,		sortable:false,		align:"center"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:120,		sortable:false,		align:"center"},
					{display:"점검자",		name:"inspector",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvl",			width:120,		sortable:false,		align:"center"},
					{display:"점검진단예산",	name:"qcInspBdgt",			width:120,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"center"},
					{display:"비고",			name:"rm",					width:200,		sortable:false,		align:"center"},
			],
		height: "auto"
	});
		
	this.$("#qcHistDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#fcltyQcHistInqireTab").tabs("option", {active: 1});
	});
};

GamFcltyQcHistInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//화면 및 데이터 초기화
GamFcltyQcHistInqireModule.prototype.initDisplay = function() {	
	this.makeDivValues("#fcltyQcHistInqireVO", {});
};

//점검이력 목록 조회
GamFcltyQcHistInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcHistInqireForm");
	this.$("#qcHistDtlsList").flexOptions({params:searchOpt}).flexReload();
	this.$("#fcltyQcHistInqireTab").tabs("option", {active: 0});
};

//점검이력 데이터 조회
GamFcltyQcHistInqireModule.prototype.loadDetailData = function() {
	var rows = this.$('#qcHistDtlsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] },
	           		{name: 'sFcltsMngNo', value: row['fcltsMngNo'] }
		           ];
		this.doAction('/fcltyMng/selectQcHistDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeDivValues('#fcltyQcHistInqireVO', result.result);
				module.$("#qcInspResult").text(result.result.qcInspResult);
			}
			else {
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcHistInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnSearch":
			this.initDisplay();
			this.loadData();
			break;
		case "popupSearchFcltsMngNo":
			this.doExecuteDialog('selectFcltsMngNo', '시설물 선택', '/popup/showFcltsMngNo.do', {});
			break;
	}
};

/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyQcHistInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1') {
		this.initDisplay();
		this.loadDetailData();
	}
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
		case 'selectFcltsMngNo': //시설물 선택(조회)
        	this.$('#sFcltsMngNo').val(value['fcltsMngNo']);
        	this.$('#sPrtFcltyNm').val(value['prtFcltyNm']);
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
			<form id="searchFcltyQcHistInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>점검진단 기관명</th>
							<td colspan="3"><input type="text" id="sQcInspInsttNm" size="60"/></td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>점검 시설명</th>
							<td>
								<input type="text" size="15" id="sFcltsMngNo" />
								<input type="text" size="30" id="sPrtFcltyNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngNo" class="popupButton">선택</button>
							</td>
							<th>점검진단일자</th>
							<td>
								<input id="sQcInspDtFr" type="text" class="emdcal" size="10" /> ~ <input id="sQcInspDtTo" type="text" class="emdcal" size="10" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyQcHistInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">점검이력목록</a></li>
				<li><a href="#tabs2" class="emdTab">점검이력내역</a></li>
			</ul>
			
			<!-- 시설물점검이력목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcHistDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>

			<!-- 시설물점검이력내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcHistInqireVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="30px" height="17">점검진단기관명</th>
							<td colspan="3">
								<span id="qcInspInsttNm"></span>
							</td>
							<th width="30px" height="17">점검시설명</th>
							<td><span id="prtFcltyNm"></span></td>
						</tr>
						<tr>
							<th height="17">점검진단일자</th>
							<td width="200px"><span id="qcInspDt"></span></td>
							<th width="30px" height="17">시행년도</th>
							<td width="200px"><span id="enforceYear"></span></td>
							<th height="17">점검관리명</th>
							<td><span id="qcMngNm"></span></td>
						</tr>
						<tr>
							<th height="17">점검진단구분</th>
							<td>
								<span id="qcInspSe"></span>
							</td>
							<th height="17">점검시작일자</th>
							<td><span id="qcBeginDt"></span></td>
							<th height="17">점검종료일자</th>
							<td><span id="qcEndDt"></span></td>
						</tr>
						<tr>
							<th height="17">책임기술자명</th>
							<td><span id="responEngineerNm"></span></td>
							<th height="17">점검진단예산</th>
							<td><span id="qcInspBdgt"></span></td>
							<th height="17">점검진단금액</th>
							<td><span id="qcInspAmt"></span></td>
						</tr>
						<tr>
							<th height="17">점검자</th>
							<td><span id="inspector"></span></td>
							<th height="17">상태평가등급</th>
							<td colspan="3"><span id="sttusEvlLvl"></span></td>
						</tr>
						<tr>
							<th height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="120" rows="7" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th height="17">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
					</table>
				</div>
				<div class="emdControlPanel">
				</div>
				</form>
			</div>
						
		</div>
	</div>
</div>