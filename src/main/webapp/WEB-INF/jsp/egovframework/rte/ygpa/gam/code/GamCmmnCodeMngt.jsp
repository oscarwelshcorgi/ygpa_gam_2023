<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCmmnCodeMngt.jsp
  * @Description : 공통코드관리 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.23  kok          최초 생성
  *
  * author kok
  * since 2014.01.23
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamCodeMngt" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */


function GamCmmnCodeMngtModule() {}

GamCmmnCodeMngtModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmmnCodeMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#cmmnCodeMngList").flexigrid({
		module: this,
		url: '/code/gamCcmCmmnCodeList.do',
		dataType: "json",
		colModel : [
					{display:"순번", 		name:"rnum",		width:50, 	sortable:false,		align:"center"},
					{display:"분류코드", 		name:"clCode",		width:60, 	sortable:false,		align:"center"},
					{display:"분류명",		name:"clCodeNm",	width:220, 	sortable:false,		align:"left"},
					{display:"코드ID", 		name:"codeId",		width:80, 	sortable:false,		align:"center"},
					{display:"코드ID명", 	name:"codeIdNm",	width:165, 	sortable:false,		align:"left"},
					{display:"사용여부", 	name:"useAt",		width:70, 	sortable:false,		align:"center"}
					],
		height: "auto"
	});

	this.$("#cmmnCodeMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module.doAction('/code/gamCcmCmmnCodeDetail.do', {codeId: row["codeId"]}, function(module, result) {

			module.$("#cmd").val("modify");
			module.$("#clCode").val(result.codeDetail.clCode);			// 분류코드
			module.$("#codeId").val(result.codeDetail.codeId);			// 코드ID
			module.$("#codeIdNm").val(result.codeDetail.codeIdNm);		// 코드명
			module.$("#codeIdDc").val(result.codeDetail.codeIdDc);		// 코드설명
			module.$("#useAt").val(result.codeDetail.useAt);			// 사용여부

			module.$("#clCode").disable();			// 분류코드
			module.$("#codeId").disable();			// 코드ID
	 	});
	});

	this.$("#cmmnCodeMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmmnCodeMngListTab").tabs("option", {active: 1});			// 탭을 전환 한다.
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamCmmnCodeMngtModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#cmmnCodeMngtForm");
			this.$("#cmmnCodeMngListTab").tabs("option", {active: 0});
		 	this.$("#cmmnCodeMngList").flexOptions({params:searchOpt}).flexReload();
		break;

			// 목록
		case "listBtn":
			this.$("#cmmnCodeMngListTab").tabs("option", {active: 0});
		break;

		// 추가
		case "addBtn":
			this.$("#cmmnCodeMngListTab").tabs("option", {active: 1});
			this.$("#cmmnCodeManageVO :input").val("");
			this.$("#useAt").val("Y");
			this.$("#cmd").val("insert");
			this.$("#clCode").enable();
			this.$("#codeId").enable();
		break;

		// 저장
		case "saveBtn":

			if(!validateGamCodeMngt(this.$("#cmmnCodeManageVO")[0])) return;

		 	var inputVO = this.makeFormArgs("#cmmnCodeManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('/code/gamCcmCmmnCodeRegist.do', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeMngtForm");
						module.$("#cmmnCodeMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('/code/gamCcmCmmnCodeModify.do', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeMngtForm");
						module.$("#cmmnCodeMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#cmmnCodeManageVO");
			 	this.doAction('/code/gamCcmCmmnCodeRemove.do', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeMngtForm");
						module.$("#cmmnCodeMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


GamCmmnCodeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmmnCodeMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="cmmnCodeMngtForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>공통코드 목록</th>
							<td>&nbsp;
								<select id="searchCondition" class="select">
									<option selected="selected">--선택하세요--</option>
									<option value="1">코드ID</option>
									<option value="2">코드ID명</option>
									<option value="3">분류코드</option>
								</select>
							</td>
							<td>&nbsp;<input name="searchKeyword" id="searchKeyword" type="text" size="70" maxlength="60" title="검색조건" />
<!-- 								<input id="searchClCode" class="ygpaCmmnCl" data-column-id="searchKeyword" data-cl-code='GAM' data-display-value="N" size="10" style="display:none;"/>
 -->							</td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="cmmnCodeMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">공통코드목록</a></li>
				<li><a href="#tabs2" class="emdTab">공통코드상세</a></li>
			</ul>

			<!-- 목록 탭 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="cmmnCodeMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>

			<!-- 저장 및 상세 탭 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="cmmnCodeManageVO" style="height:370px;">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel editForm">
						<tr>
							<th width="20%" height="23" class="required_text">분류코드</th>
							<td>
								<input id="clCode" class="ygpaCmmnCl" data-display-value="N" size="10"/>
								<input id="clCode2" class="skipValue" type="text"/>
 							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드ID</th>
							<td><input type="text" size="80" id="codeId" maxlength="6" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드명</th>
							<td><input type="text" size="80" id="codeIdNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드설명</th>
							<td><input type="text" size="80" id="codeIdDc" maxlength="200" /></td>
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
				<div class="emdControlPanel">
<!-- 					<button id="listBtn">목록</button> -->
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>