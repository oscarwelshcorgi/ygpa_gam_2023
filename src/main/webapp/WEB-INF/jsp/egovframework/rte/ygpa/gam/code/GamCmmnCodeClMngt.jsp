<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCmmnCodeClMngt.jsp
  * @Description : 공통분류코드 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  kok          최초 생성
  *
  * author kok
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamCodeClMngt" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmmnCodeClMngtModule() {}

GamCmmnCodeClMngtModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmmnCodeClMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#cmmnCodeClMngList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCmmnClCodeList.do" />',
		dataType: "json",
		colModel : [
					{display:"순번", 		name:"rnum",		width:60, 	sortable:false,		align:"center"},
					{display:"분류코드", 	name:"clCode",		width:150, 	sortable:false,		align:"center"},
					{display:"분류코드명", 	name:"clCodeNm",	width:330, 	sortable:false,		align:"center"},
					{display:"사용여부", 	name:"useAt",		width:123, 	sortable:false,		align:"center"}
					],
		height: "auto"
	});

	this.$("#cmmnCodeClMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module.doAction('<c:url value="/code/gamCmmnClCodeDetail.do" />', {clCode: row["clCode"]}, function(module, result) {

			module.$("#cmd").val("modify");
			module.$("#clCode").val(result.codeDetail.clCode);				// 분류코드
			module.$("#clCodeNm").val(result.codeDetail.clCodeNm);			// 분류명
			module.$("#clCodeDc").val(result.codeDetail.clCodeDc);			// 분류코드 설명
			module.$("#useAt").val(result.codeDetail.useAt);				// 사용여부

			module.$("#clCode").attr("disabled","disabled");				// 분류코드
	 	});
	});
	
	this.$("#cmmnCodeClMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmmnCodeClMngListTab").tabs("option", {active: 1});			// 탭을 전환 한다.
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamCmmnCodeClMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#cmmnCodeClMngtForm");
			this.$("#cmmnCodeClMngListTab").tabs("option", {active: 0});
		 	this.$("#cmmnCodeClMngList").flexOptions({params:searchOpt}).flexReload();
		 	throw 0;
		break;

			// 목록
		case "listBtn":
			this.$("#cmmnCodeClMngListTab").tabs("option", {active: 0});
		break;

		// 추가
		case "addBtn":
			this.$("#cmmnCodeClMngListTab").tabs("option", {active: 1});
			this.$("#cmmnCodeClManageVO :input").val("");
			this.$("#cmd").val("insert");
			this.$("#clCode").removeAttr("disabled");
		break;

		// 저장
		case "saveBtn":
			
			if(!validateGamCodeClMngt(this.$("#cmmnCodeClManageVO")[0])) return;
			
		 	var inputVO = this.makeFormArgs("#cmmnCodeClManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/code/gamCmmnClCodeRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeClMngtForm");
						module.$("#cmmnCodeClMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeClMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeClManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCmmnClCodeModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeClMngtForm");
						module.$("#cmmnCodeClMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeClMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeClManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#cmmnCodeClManageVO");
			 	this.doAction('<c:url value="/code/gamCmmnClCodeRemove.do" />', {clCode : this.$("#clCode").val()}, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeClMngtForm");
						module.$("#cmmnCodeClMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeClMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeClManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


GamCmmnCodeClMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmmnCodeClMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="cmmnCodeClMngtForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>공통분류코드 목록</th>
							<td>&nbsp;
								<select id="searchCondition" class="select">
									<option selected="selected">--선택하세요--</option>
									<option value="1">분류코드</option>
									<option value="2">분류코드명</option>
								</select>
							</td>
							<td>&nbsp;<input name="searchKeyword" id="searchKeyword" type="text" size="70" maxlength="60" title="검색조건" /></td>
							<td><button id="searchBtn" class="submit">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="cmmnCodeClMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">분류코드목록</a></li>
				<li><a href="#tabs2" class="emdTab">분류코드상세</a></li>
			</ul>
			
			<!-- 목록 탭 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="cmmnCodeClMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>
			
			<!-- 저장 및 상세 탭 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="cmmnCodeClManageVO" style="height:370px;">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">분류코드</th>
							<td><input type="text" size="80" id="clCode" maxlength="3" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">분류코드명</th>
							<td><input type="text" size="80" id="clCodeNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">분류코드설명</th>
							<td><input type="text" size="80" id="clCodeDc" maxlength="200" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">사용여부</th>
							<td>
								<select id="useAt">
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel emdControlPanel1">
					<button id="listBtn">목록</button>
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>