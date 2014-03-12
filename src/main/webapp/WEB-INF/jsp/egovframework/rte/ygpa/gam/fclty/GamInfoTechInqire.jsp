<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamInfoTechFcltyInqire.jsp
  * @Description : 정보통신시설사용목록조회
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
		url: '<c:url value="/fclty/gamInfoTechFcltyMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"정보통신시설코드", 				name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
				{display:"시설명",	 				name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				{display:"위치",		 				name:"gisAssetsLocCd",		width:40,		sortable:false,		align:"center"},
				{display:"구조",		 				name:"prtFcltyStndrd",		width:100,		sortable:false,		align:"center"},
				{display:"시설단위", 	 			name:"prtFcltyUnit",		width:80,		sortable:false,		align:"center"},
				{display:"관리업체 명", 	 			name:"prtFcltyMngEntrpsNm",	width:80,		sortable:false,		align:"center"},
				{display:"관리업체 코드",	 			name:"prtFcltyMngEntrpsCd",	width:80,		sortable:false,		align:"center"},
				{display:"설치일자",					name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
				{display:"변경일자",					name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		//usepager: false,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "230"
	});
	
	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var detailInput = {gisAssetsCd:row["gisAssetsCd"], gisPrtFcltySeq:row["gisPrtFcltySeq"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"], gisPrtFcltyCd:row["gisPrtFcltyCd"]};
		module.doAction('<c:url value="/fclty/gamInfoTechFcltyView.do" />', detailInput, function(module, result) {

			module.$("#gisAssetsCd").text(result.detail.gisAssetsCd);
			module.$("#gisAssetsPrtAtCode").text(result.detail.gisAssetsPrtAtCode);
			module.$("#gisAssetsSubCd").text(result.detail.gisAssetsSubCd);
			module.$("#gisPrtFcltySeq").text(result.detail.gisPrtFcltySeq);
			module.$("#gisPrtFcltyCd").text(result.detail.gisPrtFcltyCd);
			module.$("#prtFcltyNm").text(result.detail.prtFcltyNm);
			module.$("#prtFcltyStndrd").text(result.detail.prtFcltyStndrd);
			module.$("#prtFcltyUnit").text(result.detail.prtFcltyUnit);
			module.$("#prtFcltyInstlDt").text(result.detail.prtFcltyInstlDt);
			module.$("#prtFcltyChangeDt").text(result.detail.prtFcltyChangeDt);
			//module.$("#prtFcltyMngEntrpsCd").text(result.detail.prtFcltyMngEntrpsCd);
			module.$("#prtFcltyMngEntrpsNm").text(result.detail.prtFcltyMngEntrpsNm);
			module.$("#gisAssetsLocplc").text(result.detail.gisAssetsLocplc);
			module.$("#gisAssetsLnm").text(result.detail.gisAssetsLnm);
			module.$("#gisAssetsLnmSub").text(result.detail.gisAssetsLnmSub);
			module.$("#gisAssetsNm").text(result.detail.gisAssetsNm);
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
		 	this.$("#fcltyMngtListTab").tabs("option", {active: 0});
		 	this.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
		break;
		
		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
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


/**
 * 팝업 close 이벤트
 */
GamFcltyMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
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

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
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
							<th>정보통신시설코드</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" />&nbsp;-&nbsp;
								<input id="searchFcltySeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
						</tr>
						<tr>
							<th>정보통신시설 명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="40" maxlength="40" title="검색조건"  /></td>
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
				<li><a href="#tabs1" class="emdTab">정보통신시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">정보통신시설 상세</a></li>
				<li><a href="#tabs3" class="emdTab">정보통신시설 사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="fcltyMngtList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="mapSearch">맵 조회</button>
				</div>
			</div>
			
			<!-- 정보통신시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="fcltyManageVO">
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"></span>&nbsp;-&nbsp;
								<span id="gisAssetsSubCd"></span>&nbsp;-&nbsp;
								<span id="gisAssetsPrtAtCode"></span>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산명</th>
							<td colspan="3"><span id="gisAssetsNm"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">소재지</th>
							<td><span id="gisAssetsLocplc"></span></td>
							<th width="20%" height="23" class="required_text">지번</th>
							<td><span id="gisAssetsLnm"></span>&nbsp;-&nbsp;<span id="gisAssetsLnmSub"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시설분류</th>
							<td colspan="3">
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" onchange="alert(11);" id="selectedGAM005"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">정보통신시설 코드</th>
							<td colspan="3">
								<span id="gisPrtFcltyCd"></span>&nbsp;-&nbsp;<span id="gisPrtFcltySeq"></span>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">정보통신시설 명</th>
							<td colspan="3"><span id="prtFcltyNm"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">정보통신시설 규격</th>
							<td><span id="prtFcltyStndrd"></span></td>
							<th width="20%" height="23" class="required_text">단위</th>
							<td><span id="prtFcltyUnit"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관리 업체</th>
							<td colspan="3"><span id="prtFcltyMngEntrpsNm"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설치일자</th>
							<td colspan="3"><span id="prtFcltyInstlDt"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">변경일자</th>
							<td colspan="3"><span id="prtFcltyChangeDt"></span></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="insertGIS">GIS 등록</button>
					<button id="searchPosition">위치조회</button>
				</div>
			</div>
			
			<!-- 정보통신시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<div class="emdControlPanel">
					<button id="uploadPic">업로드</button>
					<button id="uploadPicOpen">사진열기</button>
					<button id="uploadPicDel">삭제</button>
				</div>
				<form id="fcltyPicManageVO">
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">제목</th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">촬영일시</th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">사진 설명</th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<th width="20%" height="23" class="required_text">미리보기</th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>