<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtOperRentMngt.jsp
  * @Description : 항만시설사용목록관리
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.02.03  kok          최초 생성
  *
  * author kok
  * since 2014.02.03
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCivilFcltyMngtModule() {}

GamCivilFcltyMngtModule.prototype = new EmdModule(840,482);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamCivilFcltyMngtModule.prototype.loadComplete = function() {
	
	// 테이블 설정
	this.$("#civilFcltyMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/mngt/gamCivilFcltyMngtList.do" />',
		dataType: 'json',
		colModel : [
				{display:'항만시설명', 				name:'PRT_AT_CODE',			width:60,		sortable:false,		align:'center'},
				{display:'항만시설 구분', 			name:'ASSETS_CODE',			width:100,		sortable:false,		align:'center'},
				{display:'항만시설 규격',				name:'ASSETS_NM',			width:160,		sortable:false,		align:'center'},
				{display:'항만시설 단위',				name:'LOCPLC',				width:100,		sortable:false,		align:'center'},
				{display:'항만시설 관리 업체 코드',	name:'LNM',					width:100,		sortable:false,		align:'center'},
				{display:'항만시설 설치일자',			name:'LNM_SUB',				width:100,		sortable:false,		align:'center'},
				{display:'항만시설 변경일자',			name:'AR',					width:100,		sortable:false,		align:'center'},
				{display:'항만시설 GIS코드',			name:'VAL_AMOUNT',			width:100,		sortable:false,		align:'center'},
				{display:'등록일자',					name:'REC_AMOUNT',			width:100,		sortable:false,		align:'center'}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "352"
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamCivilFcltyMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#civilFcltyForm");
		 	this.$("#civilFcltyMngtList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
			// 목록
		case "listBtn":
			this.$("#civilFcltyMngtListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#displayDate").hide();
			this.$("#civilFcltyManageVO :input").val("");
			this.$("#cmd").val("insert");
			this.$("#civilFcltyMngtListTab").tabs("option", {active: 1});
		break;
			
		// 저장
		case "saveBtn":
		 	var inputVO = this.makeFormArgs("#civilFcltyManageVO");
		 	alert(this.$("#cmd").val());
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/fclty/mngt/gamCivilFcltyInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#civilFcltyForm");
						module.$("#civilFcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#civilFcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#civilFcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/cmmn/gamCivilFcltyUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#civilFcltyForm");
						module.$("#civilFcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#civilFcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#civilFcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#civilFcltyManageVO");
			 	this.doAction('<c:url value="/cmmn/gamCivilFcltyDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#civilFcltyForm");
						module.$("#civilFcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#civilFcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#civilFcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCivilFcltyMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="civilFcltyForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항만시설 명</th>
							<td><input id="searchKeyword" type="text" size="60" maxlength="60" title="검색조건" /></td>
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
		<div id="civilFcltyMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">항만시설목록</a></li>
				<li><a href="#tabs2" class="emdTab">항만시설상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="civilFcltyMngtList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="civilFcltyManageVO">
					<input type="hidden" id="cmd" />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 항만 시설 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="60" id="gisPrtFcltyCd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 항코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="60" id="gisAssetsPrtAtCode"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="60" id="gisAssetsCd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 SUB 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="60" id="gisAssetsSubCd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 GIS 코드</th>
							<td><input type="text" size="60" id="prtFcltyGisCd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 명</th>
							<td><input type="text" size="60" id="prtFcltyNm"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 구분</th>
							<td><input type="text" size="60" id="prtFcltySe"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 규격</th>
							<td><input type="text" size="60" id="prtFcltyStndrd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 단위</th>
							<td><input type="text" size="60" id="prtFcltyUnit"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 관리 업체 코드</th>
							<td><input type="text" size="60" id="prtFcltyMngEntrpsCd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 설치일자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 변경일자</th>
							<td><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" disabled="disabled" /></td>
						</tr>
						<tr id="displayDate">
							<th width="20%" height="23" class="required_text">등록일자</th>
							<td><input type="text" size="60" id="registDt" disabled="disabled" /></td>
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