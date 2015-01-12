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
					{display:"점검시설명",		name:"prtFcltyNm",			width:150,		sortable:false,		align:"center"},
					{display:"시설점검일자",	name:"objQcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"시설상태등급",	name:"objSttusEvlLvlNm",	width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
			],
		height: "auto"
	});
		
	this.$("#qcHistDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#fcltyQcHistInqireTab").tabs("option", {active: 1});
	});
	
	this.$("#qcInspResult").disable();
	this.$("#actionCn").disable();
	this.$("#objQcInspResult").disable();
};

GamFcltyQcHistInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//화면 및 데이터 초기화
GamFcltyQcHistInqireModule.prototype.initDisplay = function() {	
	this.makeDivValues("#fcltyQcHistInqireVO", {});
	this.makeFormValues("#fcltyQcHistInqireVO", {});
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
				module.$("#actionCn").text(result.result.actionCn);
				module.$("#objQcInspResult").text(result.result.objQcInspResult);
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
							<td colspan="3"><input type="text" id="sQcInspInsttNm" size="46"/></td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>점검 시설명</th>
							<td>
								<input type="hidden" id="sFcltsMngNo" />
								<input type="text" size="35" id="sPrtFcltyNm" disabled="disabled"/>
								<button id="popupSearchFcltsMngNo" class="popupButton">선택</button>
							</td>
							<th>시설점검기간</th>
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
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검 대상시설물 내역</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th width="12%" height="17">시설물</th>
								<td colspan="5">
									<span id="prtFcltyNm"></span>
								</td>
							</tr>
							<tr>
								<th width="12%" height="17">점검자</th>
								<td>
									<span id="objInspector"></span>
								</td>
								<th width="12%" height="17">점검일자</th>
								<td>
									<span id="objQcInspDt"></span>
								</td>
								<th width="12%" height="17">상태평가등급</th>
								<td>
									<span id="objSttusEvlLvlNm"></span>
								</td>
							</tr>
							<tr>
								<th width="12%" height="17">시설진단결과</th>
								<td colspan="5"><textarea id="objQcInspResult" cols="135" rows="4"></textarea></td>
							</tr>
							<tr>
								<th width="12%" height="17">비고</th>
								<td colspan="5">
									<span id="objRm"></span>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 내역</th>
							</tr>
						</tbody>
					</table>				
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">관리그룹</th>
							<td colspan="3">
								<span id="fcltsMngGroupNm"></span>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<span id="qcMngSeq"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">업무구분</th>
							<td>
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
							<th width="12%" height="17">점검구분</th>
							<td>
								<span id="qcSeNm"></span>
							</td>
							<th width="12%" height="17">시행일자</th>
							<td>
								<span id="qcInspDt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단자</th>
							<td>
								<span id="qcInspTpNm"></span>
							</td>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<span id="qcInspSeNm"></span>
							</td>
							<th width="12%" height="17">점검진단금액</th>
							<td>
								<span id="qcInspAmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">상태평가등급</th>
							<td>
								<span id="sttusEvlLvlNm"></span>
							</td>
							<th width="12%" height="17">점검기간</th>
							<td colspan="3">
								<span id="qcBeginDt"></span> ~ 
								<span id="qcEndDt"></span>
							</td>
						</tr>
						<tr>							
							<th width="12%" height="17">점검진단기관명</th>
							<td>
								<span id="qcInspInsttNm"></span>
							</td>
							<th width="12%" height="17">책임기술자명</th>
							<td colspan="3"><span id="responEngineerNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="135" rows="3"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="135" rows="3"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
					</table>
				</div>
				</form>
			</div>
						
		</div>
	</div>
</div>