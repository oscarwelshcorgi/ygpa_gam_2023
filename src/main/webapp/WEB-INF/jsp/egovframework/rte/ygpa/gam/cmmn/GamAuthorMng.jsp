<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamAuthorMng.jsp
  * @Description : 권한 관리 리스트 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2013.10.29  kok          최초 생성
  *
  * author kok
  * since 2014.01.09
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamAuthorMng" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAuthorMngModule() {}

GamAuthorMngModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#authorMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamAuthorList.do" />',
		dataType: "json",
		colModel : [
					{display:"권한 ID", 	name:"authorCode",		width:250, 	sortable:false,		align:"left"},
					{display:"권한명", 	name:"authorNm",		width:140, 	sortable:false,		align:"center"},
					{display:"설명", 	name:"authorDc",		width:280, 	sortable:false,		align:"center"}
					],
		height: "auto",
	});
	
	this.$("#authorMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#authorMngListTab").tabs("option", {active: 1});			// 탭을 전환 한다.

		if(row != null) {
			
			module.$("#cmd").val("modify");	 								// 더블클릭한 아이템을 수정한다
			module.$("#authorCode").val(row["authorCode"]);					// 권한 ID
			module.$("#authorNm").val(row["authorNm"]);						// 권한명
			module.$("#authorDc").val(row["authorDc"]);						// 설명
			module.$("#authorCreatDe").val(row["authorCreatDe"]);			// 등록일자
			
			module.$("#authorCode").attr("disabled","disabled");			// 수정불가능
			throw 0;
		}
	});
};
		

/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorMngModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#authorForm");
			this.$("#authorMngListTab").tabs("option", {active: 0}); 
		 	this.$("#authorMngList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
			// 목록
		case "listBtn":
			this.$("#authorMngListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#authorCode").removeAttr("disabled");
			this.$("#authorMngListTab").tabs("option", {active: 1});
			this.$("#authorManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;
			
		// 저장
		case "saveBtn":
			if(!validateGamAuthorMng(this.$("#authorManageVO")[0])) return;
			
		 	var inputVO = this.makeFormArgs("#authorManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamAuthorInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#authorForm");
						module.$("#authorMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#authorMngListTab").tabs("option", {active: 0}); 
						module.$("#authorManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/cmmn/gamAuthorUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#authorForm");
						module.$("#authorMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#authorMngListTab").tabs("option", {active: 0}); 
						module.$("#authorManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#authorManageVO");
			 	this.doAction('<c:url value="/cmmn/gamAuthorDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#authorForm");
						module.$("#authorMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#authorMngListTab").tabs("option", {active: 0}); 
						module.$("#authorManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


GamAuthorMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;
	
		case "tabs2":
			var row = this.$("#authorMngList").selectedRows();
			if(row.length == 0) this.$("#cmd").val("insert");
			else this.$("#cmd").val("modify");
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="authorForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>권한 명</th>
							<td>&nbsp;<input id="searchKeyword" type="text" size="80" maxlength="60" title="검색조건" /></td>
							<td><button id="searchBtn">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="authorMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">권한목록</a></li>
				<li><a href="#tabs2" class="emdTab">권한상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="authorMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="authorManageVO" style="height:370px;">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">권한코드</th>
							<td><input type="text" size="80" id="authorCode" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">권한명</th>
							<td><input type="text" size="80" id="authorNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설명</th>
							<td><input type="text" size="80" id="authorDc" maxlength="200" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="listBtn">목록</button>
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>