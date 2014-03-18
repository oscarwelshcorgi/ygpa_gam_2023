<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmmnCodeMngt.jsp
  * @Description : 공통분류코드 화면
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
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmmnCodeMngtModule() {}

GamCmmnCodeMngtModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmmnCodeMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#cmmnCodeMngList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCcmCmmnCodeList.do" />',
		dataType: "json",
		colModel : [
					{display:"순번", 		name:"rnum",		width:60, 	sortable:false,		align:"center"},
					{display:"분류명",		name:"clCodeNm",	width:250, 	sortable:false,		align:"center"},
					{display:"코드ID", 		name:"codeId",		width:100, 	sortable:false,		align:"center"},
					{display:"코드ID명", 	name:"codeIdNm",	width:150, 	sortable:false,		align:"center"},
					{display:"사용여부", 	name:"useAt",		width:80, 	sortable:false,		align:"center"}
					],
		//usepager: true,
		//useRp: true,
		//rp: 24,
		//showTableToggleBtn: false,
		height: "300",
	});
	
	this.$("#cmmnCodeMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module.doAction('<c:url value="/code/gamCcmCmmnCodeDetail.do" />', {codeId: row["codeId"]}, function(module, result) {

			module.$("#cmd").val("modify");
			module.$("#clCode").val(result.codeDetail.clCode);			// 분류코드
			module.$("#codeId").val(result.codeDetail.codeId);			// 코드ID
			module.$("#codeIdNm").val(result.codeDetail.codeIdNm);		// 코드명
			module.$("#codeIdDc").val(result.codeDetail.codeIdDc);		// 코드설명
			module.$("#useAt").val(result.codeDetail.useAt);			// 사용여부
			
			module.$("#clCode").attr("disabled","disabled");			// 분류코드
			module.$("#codeId").attr("disabled","disabled");			// 코드ID
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
			this.$("#cmd").val("insert");
			this.$("#clCode").removeAttr("disabled","disabled");			// 분류코드
			this.$("#codeId").removeAttr("disabled","disabled");			// 코드ID
		break;
			
		// 저장
		case "saveBtn":
		 	var inputVO = this.makeFormArgs("#cmmnCodeManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/code/gamCcmCmmnCodeRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeMngtForm");
						module.$("#cmmnCodeMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCcmCmmnCodeModify.do" />', inputVO, function(module, result) {
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
			 	this.doAction('<c:url value="/code/gamCcmCmmnCodeRemove.do" />', {codeId : this.$("#codeId").val()}, function(module, result) {
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
							<td width="10%">
								<select id="searchCondition" class="select">
									<option selected="selected">--선택하세요--</option>
									<option value="1">코드ID</option>
									<option value="2">코드ID명</option>
								</select>	   
							</td>
							<td><input name="searchKeyword" id="searchKeyword" type="text" size="80" maxlength="60" title="검색조건" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
					<button id="addBtn">추가</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="cmmnCodeMngListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">관리코드목록</a></li>
				<li><a href="#tabs2" class="emdTab">관리코드상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="cmmnCodeMngList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmmnCodeManageVO">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">분류코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select name="clCode" class="select" id="clCode">
									<c:forEach var="result" items="${cmmnClCode}" varStatus="status">
										<option value='<c:out value="${result.clCode}"/>'><c:out value="${result.clCodeNm}"/></option>
									</c:forEach>			  		   
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드ID<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="codeId"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="codeIdNm"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="codeIdDc"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">사용여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select id="useAt">
									<option value="N">N</option>
									<option value="Y">Y</option>
								</select>
							</td>
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