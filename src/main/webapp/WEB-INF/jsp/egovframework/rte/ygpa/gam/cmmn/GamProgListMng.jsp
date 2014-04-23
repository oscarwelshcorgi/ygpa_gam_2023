<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamProgListMng.jsp
  * @Description : 프로그램 목록 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.09  kok          최초 생성
  *
  * author kok
  * since 2014.01.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamProgListMng" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamProgListMngModule() {}

GamProgListMngModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamProgListMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#progListMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamProgramListManageSelect.do" />',
		dataType: 'json',
		colModel : [
					{display:'프로그램파일명', 	name:'progrmFileNm',	width:160, 		sortable:false,		align:'center'},
					{display:'프로그램명', 		name:'progrmKoreanNm',	width:160, 		sortable:false,		align:'center'},
					{display:'프로그램경로', 		name:'progrmStrePath',	width:150, 		sortable:false,		align:'center'},
					{display:'URL', 			name:'url',				width:200, 		sortable:false,		align:'center'},
					{display:'프로그램설명',		name:'progrmDc',		width:180,		sortable:false,		align:'center'}
					],
		height: "auto"
	});

	this.$("#progListMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		module.$("#programListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row != null) {
			module.$("#progrmFileNm").attr("disabled","disabled");
			module.$("#cmd").val("modify");	 							// 더블클릭한 아이템을 수정한다
			module.$("#progrmFileNm").val(row["progrmFileNm"]);			// 프로그램 파일명
			module.$("#progrmStrePath").val(row["progrmStrePath"]);		// 프로그램명
			module.$("#progrmKoreanNm").val(row["progrmKoreanNm"]);		// 프로그램 경로
			module.$("#URL").val(row["url"]);							// URL
			module.$("#progrmDc").val(row["progrmDc"]);					// 프로그램 설명
			throw 0;
		}
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamProgListMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#progListMngForm");
			this.$("#programListTab").tabs("option", {active: 0});
		 	this.$("#progListMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 목록
		case "listBtn":
			this.$("#programListTab").tabs("option", {active: 0});
			this.$("#progListMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 추가
		case "addBtn":
			this.$("#progrmFileNm").removeAttr("disabled");
			this.$("#programListTab").tabs("option", {active: 1});
			this.$("#progrmManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 저장
		case "saveBtn":
			
			if(!validateGamProgListMng(this.$("#progrmManageVO")[0])) return;
			
			var inputVO = this.makeFormArgs("#progrmManageVO");
			
			// insert
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamInsertProgramListRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#progListMngForm");
						module.$("#progListMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#programListTab").tabs("option", {active: 0});
						module.$("#progrmManageVO :input").val("");
			 		}
					alert(result.resultMsg);
			 	});
			// update
			}else{
			 	this.doAction('<c:url value="/cmmn/gamProgramListDetailSelectUpdt.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#progListMngForm");
					 	module.$("#progListMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#programListTab").tabs("option", {active: 0});
						module.$("#progrmManageVO :input").val("");
			 		}
					alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#progrmManageVO");
				this.doAction('<c:url value="/cmmn/gamProgramListManageDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0") {
						var searchOpt = module.makeFormArgs("#progListMngForm");
						module.$("#progListMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#programListTab").tabs("option", {active: 0});
						module.$("#progrmManageVO :input").val("");
			 		}
					alert(result.resultMsg);
				});
			}
		break;
	}
};

GamProgListMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
	break;
	case "tabs2":
		var row = this.$("#progListMngList").selectedRows();
		if(row.length == 0) this.$("#cmd").val("insert");
		else this.$("#cmd").val("modify");
	break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamProgListMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="progListMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>프로그램 명</th>
							<td>&nbsp;<input id="searchKeyword" name="searchKeyword" type="text" size="60" /></td>
							<td><button id="searchBtn">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="programListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">프로그램목록</a></li>
				<li><a href="#tabs2" class="emdTab">프로그램상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="progListMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="progrmManageVO">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">프로그램파일명</th>
							<td><input type="text" size="80" id="progrmFileNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">한글명</th>
							<td><input type="text" size="80" id="progrmKoreanNm" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">프로그램경로</th>
							<td><input type="text" size="80" id="progrmStrePath" maxlength="100" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">URL</th>
							<td><input type="text" size="80" id="URL" maxlength="100" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">프로그램설명</th>
							<td><textarea cols="80" rows="10" id="progrmDc" maxlength="200"></textarea></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
					<button id="listBtn">목록</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>