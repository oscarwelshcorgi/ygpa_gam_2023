<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamOlnlpMngt.jsp
  * @Description : 공시지가 관리
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.03.07  kok          최초 생성
  *
  * author kok
  * since 2014.03.07
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamOlnlpCode" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOlnlpMngtModule() {}

GamOlnlpMngtModule.prototype = new EmdModule(800,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamOlnlpMngtModule.prototype.loadComplete = function() {

	// 공시지가 등록현황 목록
	this.$("#olnlpInsertList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpInsertList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsCd",			width:60,		sortable:false,		align:"center"},
				{display:"소재지",		 			name:"gisAssetsLocplc",		width:220,		sortable:false,		align:"center"},
				{display:"지번", 					name:"gisAssetsLnmDisplay",	width:100,		sortable:false,		align:"center"},
				{display:"자산명",		 			name:"gisAssetsNm",			width:140,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	// 공시지가 목록
	this.$("#olnlpMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"순번", 					name:"olnlpSeq",		width:80,		sortable:false,		align:"center"},
				{display:"시작일자",	 				name:"beginDt",			width:150,		sortable:false,		align:"center"},
				{display:"종료일자",		 			name:"endDt",			width:150,		sortable:false,		align:"center"},
				{display:"공시지가",		 			name:"olnlp",			width:270,		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	this.$("#olnlpInsertList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#olnlpMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var listInput = {gisAssetsCd:row["gisAssetsCd"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"]};
		module.doAction('<c:url value="/code/gamOlnlpMngtList.do" />', listInput, function(module, result) {

			module.$("#gisAssetsCd").val(result.searchOption.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.searchOption.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.searchOption.gisAssetsSubCd);

			var searchOpt = module.makeFormArgs("#olnlpManageVO");
			module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
			if(result.totalCount == 0) alert("등록된 공시지가 목록이 없습니다.");
	 	});
	});
	
	this.$("#olnlpMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		module.$("#beginDt").val(row["beginDt"]);			// 시작일자
		module.$("#endDt").val(row["endDt"]);				// 종료일자
		module.$("#olnlp").val(row["olnlp"]);				// 공지시가
		module.$("#olnlpSeq").val(row["olnlpSeq"]);			// 순번
		module.$("#cmd").val("update");						// 저장 Flag
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
		
		// 추가
		case "addBtn":
			this.$("#olnlpMngtListTab").tabs("option", {active: 1});
			this.$("#olnlpSeq").val("");
			this.$("#beginDt").val("");
			this.$("#endDt").val("");
			this.$("#olnlp").val("");
			this.$("#cmd").val("insert");
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;
			
		// 저장
		case "saveBtn":
			
			if(!validateGamOlnlpCode(this.$("#olnlpManageVO")[0])) return;
			
			var inputVO = this.makeFormArgs("#olnlpManageVO");
			
			if(this.$("#cmd").val() == "insert"){
				this.doAction('<c:url value="/code/insertOlnlpMngt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#olnlpForm");
						module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#olnlpMngtListTab").tabs("option", {active: 0}); 
						module.$("#olnlpManageVO :input").val("");
						
						// 저장 후 input text가 비어있으므로 입력형태 insert로 설정
						module.$("#cmd").val("insert");
			 		}
			 		alert(result.resultMsg);
			 	});	
			}else{
				this.doAction('<c:url value="/code/updateOlnlpMngt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#olnlpForm");
						module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#olnlpMngtListTab").tabs("option", {active: 0}); 
						module.$("#olnlpManageVO :input").val("");
						
						// 저장 후 input text가 비어있으므로 입력형태 insert로 설정
						module.$("#cmd").val("insert");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		 	
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("선택 한 공시지가를 삭제하시겠습니까?")){

				var row = this.$("#olnlpMngtList").selectedRows();
				
				var inputVO = {gisAssetsCd:row[0]["gisAssetsCd"], gisAssetsPrtAtCode:row[0]["gisAssetsPrtAtCode"], gisAssetsSubCd:row[0]["gisAssetsSubCd"], olnlpSeq:row[0]["olnlpSeq"]};
			 	this.doAction('<c:url value="/code/gamOlnlpDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#olnlpForm");
						module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#olnlpMngtListTab").tabs("option", {active: 0}); 
						module.$("#olnlpManageVO :input").val("");
						
						// 삭제 후 input text가 비어있으므로 입력형태 insert로 설정
						module.$("#cmd").val("insert");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamOlnlpMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var row = this.$("#olnlpMngtList").selectedRows();
		if(row.length == 0) this.$("#cmd").val("insert");
		else this.$("#cmd").val("modify");
		break;
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
							<td rowSpan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
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
				<!-- <div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div> -->
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="olnlpMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">공시지가등록현황목록</a></li>
				<li><a href="#tabs2" class="emdTab">공시지가목록</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpInsertList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="insertExcel">엑셀등록</button>
				</div>
			</div>
			
			<!-- 공시지가 목록 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<table id="olnlpMngtList" style="display:none" class="fillHeight"></table>
				<form id="olnlpManageVO">
				<input type="hidden" id="cmd" />
				<input type="hidden" id="gisAssetsCd" />
				<input type="hidden" id="gisAssetsPrtAtCode" />
				<input type="hidden" id="gisAssetsSubCd" />	
				<input type="hidden" id="olnlpSeq" />	
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>적용일자</th>
							<td><input id="beginDt" type="text" size="20" maxlength="10" class="emdcal" title="시작일자" /></td>
							<td><input id="endDt" type="text" size="20" maxlength="10" class="emdcal" title="종료일자" /></td>
						</tr>
						<tr>
							<th>공시지가</th>
							<td colspan="2">
								<input id="olnlp" type="text" size="40" title="공시지가 금액" />
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>