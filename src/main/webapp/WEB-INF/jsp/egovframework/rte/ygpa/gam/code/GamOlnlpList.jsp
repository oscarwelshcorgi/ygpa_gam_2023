<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamOlnlpList.jsp
  * @Description : 공시지가 조회
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.03.10  kok          최초 생성
  *
  * author kok
  * since 2014.03.10
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOlnlpMngtModule() {}

GamOlnlpMngtModule.prototype = new EmdModule(800, 600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamOlnlpMngtModule.prototype.loadComplete = function() {

	// 공시지가 등록현황 목록
	this.$("#olnlpInsertList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpInsertList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:50,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:70,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsCd",			width:70,		sortable:false,		align:"center"},
				{display:"소재지",		 			name:"gisAssetsLocplc",		width:210,		sortable:false,		align:"center"},
				{display:"지번", 					name:"gisAssetsLnm",		width:80,		sortable:false,		align:"center"},
				{display:"자산명",		 			name:"gisAssetsNm",			width:130,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	// 공시지가 목록
	this.$("#olnlpMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"순번", 					name:"olnlpSeq",		width:100,		sortable:false,		align:"center"},
				{display:"시작일자",	 				name:"beginDt",			width:140,		sortable:false,		align:"center"},
				{display:"종료일자",		 			name:"endDt",			width:140,		sortable:false,		align:"center"},
				{display:"공시지가",		 			name:"olnlp",			width:230,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "230"
	});
	
	this.$("#olnlpInsertList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#olnlpMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var listInput = {gisAssetsCd:row["gisAssetsCd"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"]};
		module.doAction('<c:url value="/code/gamOlnlpMngtList.do" />', listInput, function(module, result) {

			var totalCount = result.totalCount;
			if(totalCount > 0){
				module.$("#gisAssetsCd").val(result.searchOption.gisAssetsCd);
				module.$("#gisAssetsPrtAtCode").val(result.searchOption.gisAssetsPrtAtCode);
				module.$("#gisAssetsSubCd").val(result.searchOption.gisAssetsSubCd);
				
				var searchOpt = module.makeFormArgs("#olnlpManageVO");
				module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
			}else{
				alert("등록된 공시지가 목록이 없습니다.");
			}
	 	});
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamOlnlpMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			
			if(this.$("#gisAssetsLnmSub").val() != ""){
				if(this.$("#gisAssetsLnm").val() == ""){
					this.$("#gisAssetsLnm").focus();
					alert("지번 정보를 입력하여 주십시오.");
					return;
				}
			}
			
			var searchOpt = this.makeFormArgs("#olnlpForm");
		 	this.$("#olnlpMngtListTab").tabs("option", {active: 0});
		 	this.$("#olnlpInsertList").flexOptions({params:searchOpt}).flexReload();
		break;
		
		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamOlnlpMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1": break;
		case "tabs2": break;
	}
};


/**
 * 팝업 close 이벤트
 */
GamOlnlpMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		
		// 자산코드 조회
		case "searchGisCodePopup":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamOlnlpMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="olnlpForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="searchAssetsPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" disabled="disabled"/>
								<button id="searchPopupBtn">자산코드</button>
							</td>
						<tr>
							<th>소재지</th>
							<td>
								<input id="gisAssetsLocplc" type="text" size="50" title="검색조건" />
							</td>
							<th>지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="5" title="검색조건"  />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="5" title="검색조건"  />
							</td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="olnlpMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">공시지가등록현황목록</a></li>
				<li><a href="#tabs2" class="emdTab">공시지가목록</a></li>
			</ul>
			
			<!-- 공시지가 등록현황 목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpInsertList" style="display:none" class="fillHeight"></table>
			</div>
			
			<!-- 공시지가 목록 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpMngtList" style="display:none" class="fillHeight"></table>
				<form id="olnlpManageVO">
					<input type="hidden" id="gisAssetsCd" />
					<input type="hidden" id="gisAssetsPrtAtCode" />
					<input type="hidden" id="gisAssetsSubCd" />	
				</form>
			</div>
		</div>
	</div>
</div>