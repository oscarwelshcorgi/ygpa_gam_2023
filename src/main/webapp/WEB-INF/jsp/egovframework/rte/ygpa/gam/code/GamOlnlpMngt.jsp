<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamOlnlpMngtModule() {}

GamOlnlpMngtModule.prototype = new EmdModule(840,510);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamOlnlpMngtModule.prototype.loadComplete = function() {

	// 공시지가 등록현황 목록
	this.$("#olnlpInsertList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpInsertList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:40,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsCd",			width:80,		sortable:false,		align:"center"},
				{display:"소재지",		 			name:"gisAssetsLocplc",		width:60,		sortable:false,		align:"center"},
				{display:"지번", 					name:"gisAssetsLnm",		width:80,		sortable:false,		align:"center"},
				{display:"자산명",		 			name:"gisAssetsNm",			width:60,		sortable:false,		align:"center"}
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

			
			module.$("#cmd").val("modify");
			module.$("#gisAssetsCd").val(result.detail.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.detail.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.detail.gisAssetsSubCd);
			module.$("#gisPrtFcltySeq").val(result.detail.gisPrtFcltySeq);
			module.$("#gisPrtFcltyCd").val(result.detail.gisPrtFcltyCd);
			module.$("#prtFcltyNm").val(result.detail.prtFcltyNm);
			module.$("#prtFcltyStndrd").val(result.detail.prtFcltyStndrd);
			module.$("#prtFcltyUnit").val(result.detail.prtFcltyUnit);
			module.$("#prtFcltyInstlDt").val(result.detail.prtFcltyInstlDt);
			module.$("#prtFcltyChangeDt").val(result.detail.prtFcltyChangeDt);
			module.$("#prtFcltyMngEntrpsCd").val(result.detail.prtFcltyMngEntrpsCd);
			module.$("#prtFcltyMngEntrpsNm").val(result.detail.prtFcltyMngEntrpsNm);
			module.$("#gisAssetsLocplc").val(result.detail.gisAssetsLocplc);
			module.$("#gisAssetsLnm").val(result.detail.gisAssetsLnm);
			module.$("#gisAssetsLnmSub").val(result.detail.gisAssetsLnmSub);
			module.$("#gisAssetsNm").val(result.detail.gisAssetsNm);
	 	});
	});
	
	// 공시지가 목록
	this.$("#olnlpMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamOlnlpMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"항이름",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"소재지",		 			name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"지번", 					name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
				{display:"공시지가 시작일자",	 		name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				{display:"공시지가 종료일자",		 	name:"gisAssetsLocCd",		width:40,		sortable:false,		align:"center"},
				{display:"등록 수",		 			name:"prtFcltyStndrd",		width:100,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "230"
	});
	
	this.$("#olnlpList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#olnlpMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var detailInput = {gisAssetsCd:row["gisAssetsCd"], gisPrtFcltySeq:row["gisPrtFcltySeq"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"], gisPrtFcltyCd:row["gisPrtFcltyCd"]};
		module.doAction('<c:url value="/code/gamConstFcltyView.do" />', detailInput, function(module, result) {

			module.$("#cmd").val("modify");
			module.$("#gisAssetsCd").val(result.detail.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.detail.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.detail.gisAssetsSubCd);
			module.$("#gisPrtFcltySeq").val(result.detail.gisPrtFcltySeq);
			module.$("#gisPrtFcltyCd").val(result.detail.gisPrtFcltyCd);
			module.$("#prtFcltyNm").val(result.detail.prtFcltyNm);
			module.$("#prtFcltyStndrd").val(result.detail.prtFcltyStndrd);
			module.$("#prtFcltyUnit").val(result.detail.prtFcltyUnit);
			module.$("#prtFcltyInstlDt").val(result.detail.prtFcltyInstlDt);
			module.$("#prtFcltyChangeDt").val(result.detail.prtFcltyChangeDt);
			module.$("#prtFcltyMngEntrpsCd").val(result.detail.prtFcltyMngEntrpsCd);
			module.$("#prtFcltyMngEntrpsNm").val(result.detail.prtFcltyMngEntrpsNm);
			module.$("#gisAssetsLocplc").val(result.detail.gisAssetsLocplc);
			module.$("#gisAssetsLnm").val(result.detail.gisAssetsLnm);
			module.$("#gisAssetsLnmSub").val(result.detail.gisAssetsLnmSub);
			module.$("#gisAssetsNm").val(result.detail.gisAssetsNm);
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
			var searchOpt=this.makeFormArgs("#olnlpForm");
		 	this.$("#olnlpMngtListTab").tabs("option", {active: 0});
		 	this.$("#olnlpInsertList").flexOptions({params:searchOpt}).flexReload();
		break;
		
		// 추가
		case "addBtn":
			this.$("#olnlpMngtListTab").tabs("option", {active: 1});
			this.$("#OlnlpManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
			
		// 저장
		case "saveBtn":
			
			// 날짜 설정
			this.$("#prtOlnlpInstlDt").val(this.$("#prtOlnlpInstlDt").val().replace(/\-/g,""));
			this.$("#prtOlnlpChangeDt").val(this.$("#prtOlnlpChangeDt").val().replace(/\-/g,""));
		 	var inputVO = this.makeFormArgs("#OlnlpManageVO");
			
		       
		 	this.doAction('<c:url value="/code/updateOlnlpMngt.do" />', inputVO, function(module, result) {
		 		if(result.resultCode == "0"){
		 			var searchOpt = module.makeFormArgs("#olnlpForm");
					module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
					module.$("#olnlpMngtListTab").tabs("option", {active: 0}); 
					module.$("#olnlpManageVO :input").val("");
		 		}
		 		alert(result.resultMsg);
		 	});
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("선택 한 공시지가를 삭제하시겠습니까?")){
				
				var row = this.$("#olnlpMngtList").selectedRows();
				
				var inputVO = {gisAssetsCd:row[0]["gisAssetsCd"], gisPrtOlnlpSeq:row[0]["gisPrtOlnlpSeq"], gisAssetsPrtAtCode:row[0]["gisAssetsPrtAtCode"], gisAssetsSubCd:row[0]["gisAssetsSubCd"], gisPrtOlnlpCd:row[0]["gisPrtOlnlpCd"]};
			 	this.doAction('<c:url value="/code/gamOlnlpDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#olnlpForm");
						module.$("#olnlpMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#olnlpMngtListTab").tabs("option", {active: 0}); 
						module.$("#olnlpManageVO :input").val("");
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
		
		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
		break;

		// 조회화면
		case "searchGisCodePopup2":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#prtOlnlpMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtOlnlpMngEntrpsNm").val(value["entrpsNm"]);
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
							<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" disabled="disabled"/>
								<button id="searchPopupBtn">선택</button>
							</td>
						<tr>
							<th>소재지</th>
							<td>
								<input id="searchOlnlpCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" />&nbsp;-&nbsp;
								<input id="searchOlnlpSeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
							<th>지번</th>
							<td><input id="searchKeyword" type="text" size="40" maxlength="40" title="검색조건"  /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="olnlpMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">공시지가등록현황목록</a></li>
				<li><a href="#tabs2" class="emdTab">공시지가목록</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="olnlpInsertList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="insertExcel">엑셀등록</button>
				</div>
			</div>
			
			<!-- 공시자가 목록 -->
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<table id="olnlpList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
				</div>
			</div>
		</div>
	</div>
</div>