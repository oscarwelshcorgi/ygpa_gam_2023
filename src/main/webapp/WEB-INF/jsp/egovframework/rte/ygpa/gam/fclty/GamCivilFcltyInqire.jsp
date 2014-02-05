<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCivilFcltyInqire.jsp
  * @Description : 토목시설사용목록조회
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.02.05  kok          최초 생성
  *
  * author kok
  * since 2014.02.05
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyMngtModule() {}

GamFcltyMngtModule.prototype = new EmdModule(840,510);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#fcltyMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"등록번호", 				name:"gisPrtFcltySeq",		width:50,		sortable:false,		align:"center"},
				{display:"GIS 자산 코드",		 		name:"gisAssetsCd",			width:100,		sortable:false,		align:"center"},
				{display:"GIS 자산 SUB 코드", 		name:"gisAssetsSubCd",		width:120,		sortable:false,		align:"center"},
				{display:"GIS 자산 항코드", 			name:"gisAssetsPrtAtCode",	width:100,		sortable:false,		align:"center"},
				{display:"항만시설명", 				name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				{display:"항만시설 구분", 			name:"prtFcltySe",			width:80,		sortable:false,		align:"center"},
				{display:"등록일자",					name:"registDt",			width:80,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "270"
	});
	
	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		
		// 자산구분코드 설정
		module.$("#gisPrtFcltyCd").val("04");
		
		var detailInput = {gisAssetsCd:row["gisAssetsCd"], gisPrtFcltySeq:row["gisPrtFcltySeq"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"], gisPrtFcltyCd:module.$("#gisPrtFcltyCd").val()};
		module.doAction('<c:url value="/fclty/gamFcltyMngSelectView.do" />', detailInput, function(module, result) {

			module.$("#cmd").val("modify");
			module.$("#gisAssetsCd").val(result.detail.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").val(result.detail.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").val(result.detail.gisAssetsSubCd);
			module.$("#gisPrtFcltySeq").val(result.detail.gisPrtFcltySeq);
			module.$("#prtFcltyNm").val(result.detail.prtFcltyNm);
			module.$("#prtFcltyStndrd").val(result.detail.prtFcltyStndrd);
			module.$("#prtFcltyUnit").val(result.detail.prtFcltyUnit);
			module.$("#prtFcltyInstlDt").val(result.detail.prtFcltyInstlDt);
			module.$("#prtFcltyChangeDt").val(result.detail.prtFcltyChangeDt);
			module.$("#prtFcltySe").val(result.detail.prtFcltySe);
			module.$("#prtFcltyMngEntrpsCd").val(result.detail.prtFcltyMngEntrpsCd);
			module.$("#prtFcltyGisCd").val(result.detail.prtFcltyGisCd);
			module.$("#registDt").val(result.detail.registDt);
	 	});
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#fcltyForm");
		 	this.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
			// 목록
		case "listBtn":
			this.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
 GamFcltyMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		
		case "tabs1": break;
		case "tabs2": break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltyForm">
				<input type="hidden" id="uniqFcltyCd" value="04" />
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
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div id="fcltyMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">항만시설목록</a></li>
				<li><a href="#tabs2" class="emdTab">항만시설상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="fcltyMngtList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="fcltyManageVO">
					<input type="hidden" id="cmd" />
					<input type="hidden" id="gisPrtFcltySeq" />
					<input type="hidden" id="gisPrtFcltyCd"  />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드</th>
							<td><input type="text" size="40" id="gisAssetsCd" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 SUB 코드</th>
							<td><input type="text" size="40" id="gisAssetsSubCd" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 항코드</th>
							<td><input type="text" size="40" id="gisAssetsPrtAtCode" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 GIS 코드</th>
							<td><input type="text" size="40" id="prtFcltyGisCd"disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 명</th>
							<td><input type="text" size="40" id="prtFcltyNm" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 구분</th>
							<td><input type="text" size="40" id="prtFcltySe" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 규격</th>
							<td><input type="text" size="40" id="prtFcltyStndrd" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 단위</th>
							<td><input type="text" size="40" id="prtFcltyUnit" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 관리 업체 코드</th>
							<td><input type="text" size="40" id="prtFcltyMngEntrpsCd" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 설치일자</th>
							<td><input id="prtFcltyInstlDt" type="text" size="20" title="설치일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 변경일자</th>
							<td><input id="prtFcltyChangeDt" type="text" size="20" title="변경일자" disabled="disabled" /></td>
						</tr>
						<tr id="displayDate">
							<th width="20%" height="23" class="required_text">등록일자</th>
							<td><input type="text" size="40" id="registDt" disabled="disabled" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="listBtn">목록</button>
				</div>
			</div>
		</div>
	</div>
</div>