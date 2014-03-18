<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmmnCodeDetailMngt.jsp
  * @Description : 공통코드 상세관리 화면
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
function GamCmmnCodeDetailMngtModule() {}

GamCmmnCodeDetailMngtModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmmnCodeDetailMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#cmmnCodeMngDetailMngList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCcmCmmnDetailCodeList.do" />',
		dataType: "json",
		colModel : [
					{display:"순번", 		name:"rnum",		width:60, 	sortable:false,		align:"center"},
					{display:"코드ID", 		name:"codeId",		width:200, 	sortable:false,		align:"center"},
					{display:"코드", 		name:"code",		width:100, 	sortable:false,		align:"center"},
					{display:"코드명", 		name:"codeNm",		width:250, 	sortable:false,		align:"center"},
					{display:"사용여부", 	name:"useAt",		width:80, 	sortable:false,		align:"center"}
					],
		//usepager: true,
		//useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "270"
	});
	
	this.$("#cmmnCodeMngDetailMngList").on("onItemSelected", function(event, module, row, grid, param) {
		module.doAction('<c:url value="/code/gamCcmCmmnDetailCodeDetail.do" />', {codeId: row["codeId"], code: row["code"]}, function(module, result) {

			// value 설정
			module.$("#cmd").val("modify");
			module.$("#codeId").val(result.codeDetail.codeId);			// 코드ID(hidden)
			module.$("#codeIdText").val(result.codeDetail.codeIdNm);	// 코드ID(text) [display]
			module.$("#code").val(result.codeDetail.code);				// 코드
			module.$("#codeNm").val(result.codeDetail.codeNm);			// 코드명
			module.$("#codeDc").val(result.codeDetail.codeDc);			// 코드설명
			module.$("#useAt").val(result.codeDetail.useAt);			// 사용여부
			
			// style 설정
			module.$("#codeIdText").show();								// 코드ID(text)[display]
			module.$("#codeIdText").attr("disabled","disabled");		// 코드ID(text)[display]
			module.$("#code").attr("disabled","disabled");				// 코드
			module.$("#codeIdText").removeAttr("style");				// 코드ID(text)[display]
			module.$("#codeIdSelect").hide();							// 코드ID(select)[display]
			module.$("#clCode").hide();									// 코드ID(select)[display]
	 	});
	});

	
	this.$("#cmmnCodeMngDetailMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 1});			// 탭을 전환 한다.
	});
	
	this.$("#clCode").on("change", {module: this}, function(event) {
		
		event.data.module.doAction('<c:url value="/code/gamGetSubCode.do" />', {clCode : $(this).val()}, function(module, result) {
			
			if(result.resultCode == "0"){
				if(result.cmmnCodeList.length > 0){
					for(var i=0; i<result.cmmnCodeList.length; i++){
						if(i == 0){
							module.$("#codeIdSelect").html("<option value='"+result.cmmnCodeList[i].codeId+"'>"+result.cmmnCodeList[i].codeIdNm+"</option>");	
						}else{
							module.$("#codeIdSelect").append("<option value='"+result.cmmnCodeList[i].codeId+"'>"+result.cmmnCodeList[i].codeIdNm+"</option>");
						}
					}	
				}else{
					module.$("#codeIdSelect").html("");
				}
	 		}
	 	});
	});
	
	

};
		

/**
 * 정의 된 버튼 클릭 시
 */
GamCmmnCodeDetailMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#cmmnCodeDetailMngtForm");
		 	this.$("#cmmnCodeMngDetailMngList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
			// 목록
		case "listBtn":
			this.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 1});
			this.$("#cmmnCodeDetailManageVO :input").val("");
			this.$("#cmd").val("insert");
			
			this.$("#codeIdText").hide();						// 코드ID(text)[display]
			this.$("#code").removeAttr("disabled");				// 코드
			this.$("#codeIdSelect").show();						// 코드ID(select)[display]
			this.$("#clCode").show();							// 코드ID(select)[display]
		break;
			
		// 저장
		case "saveBtn":

		 	var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");

			if(this.$("#cmd").val() == "insert") {
				this.$("#codeId").val(this.$("#codeIdSelect").val());
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeDetailMngtForm");
						module.$("#cmmnCodeMngDetailMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeDetailMngtForm");
						module.$("#cmmnCodeMngDetailMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRemove.do" />', {codeId : this.$("#codeId").val(), code : this.$("#code").val()}, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmmnCodeDetailMngtForm");
						module.$("#cmmnCodeMngDetailMngList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmmnCodeMngDetailMngListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};


GamCmmnCodeDetailMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;
	
		case "tabs2":
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmmnCodeDetailMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="cmmnCodeDetailMngtForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>공통상세코드 목록</th>
							<td width="10%">  		
								<select id="searchCondition" class="select" title="searchCondition">
									<option selected="selected">--선택하세요--</option>
									<option value="1">코드ID</option>
									<option value="2">코드</option>
									<option value="3">코드명</option>
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
		<div id="cmmnCodeMngDetailMngListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">관리코드상세목록</a></li>
				<li><a href="#tabs2" class="emdTab">관리코드상세정보</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="cmmnCodeMngDetailMngList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmmnCodeDetailManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="codeId" />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">코드ID<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="80" id="codeIdText" style="display:none" />
								<select class="select" id="clCode" title="clCode">
									<c:forEach var="result" items="${cmmnClCodeList}" varStatus="status">
										<option value='<c:out value="${result.clCode}"/>' <c:if test="${result.clCode == cmmnCode.clCode}">selected="selected"</c:if>><c:out value="${result.clCodeNm}"/></option>
									</c:forEach>
								</select>
								<select class="select" id="codeIdSelect"></select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="code"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="codeNm"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">코드설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="80" id="codeDc"/></td>
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