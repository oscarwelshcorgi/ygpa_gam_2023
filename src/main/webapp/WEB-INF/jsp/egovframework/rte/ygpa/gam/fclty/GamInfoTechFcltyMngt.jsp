<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamInfoTechFcltyMngt.jsp
  * @Description : 정보통신시설사용목록관리
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
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"항만시설코드", 				name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
				{display:"항만시설명", 				name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				//{display:"항만시설 구분", 			name:"prtFcltySe",			width:80,		sortable:false,		align:"center"},
				{display:"단위",		 	 			name:"prtFcltyUnit",		width:80,		sortable:false,		align:"center"},
				{display:"설치일자",					name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
				{display:"변경일자",					name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"},
				{display:"등록자",					name:"regUsr",			width:80,		sortable:false,		align:"center"},
				{display:"등록일자",					name:"registDt",			width:80,		sortable:false,		align:"center"},
				{display:"hidden", 					name:"gisPrtFcltySeq",		width:1,		sortable:false,		align:"center"},
				{display:"hidden",		 			name:"gisAssetsCd",			width:1,		sortable:false,		align:"center"},
				{display:"hidden", 					name:"gisAssetsSubCd",		width:1,		sortable:false,		align:"center"}
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
		module.$("#gisPrtFcltyCd").val("AC");
		
		var detailInput = {gisAssetsCd:row["gisAssetsCd"], gisPrtFcltySeq:row["gisPrtFcltySeq"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"], gisPrtFcltyCd:module.$("#gisPrtFcltyCd").val()};
		module.doAction('<c:url value="/fclty/gamFcltyMngSelectView.do" />', detailInput, function(module, result) {

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
			module.$("#prtFcltySe").val(result.detail.prtFcltySe);
			module.$("#prtFcltyMngEntrpsCd").val(result.detail.prtFcltyMngEntrpsCd);
			module.$("#gisAssetsLocplc").val(result.detail.gisAssetsLocplc);
			module.$("#gisAssetsLnm").val(result.detail.gisAssetsLnm);
			module.$("#gisAssetsLnmSub").val(result.detail.gisAssetsLnmSub);
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
		
		// 추가
		case "addBtn":
			this.$("#displayDate").hide();
			this.$("#fcltyManageVO :input").val("");
			this.$("#cmd").val("insert");
			
			// 자산구분코드 설정
			this.$("#gisPrtFcltyCd").val("AC");
			this.$("#fcltyMngtListTab").tabs("option", {active: 1});
			
			this.doAction('<c:url value="/fclty/gamFcltyGetInsertSeq.do" />', inputVO, function(module, result) {
				module.$("#gisPrtFcltySeq").val(result.seq);
		 	});
		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드 조회", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드 조회", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
			
		// 저장
		case "saveBtn":
			// 날짜 설정
			this.$("#prtFcltyInstlDt").val(this.$("#prtFcltyInstlDt").val().replace(/\-/g,""));
			this.$("#prtFcltyChangeDt").val(this.$("#prtFcltyChangeDt").val().replace(/\-/g,""));
		 	var inputVO = this.makeFormArgs("#fcltyManageVO");
			if(this.$("#cmd").val() == "insert") {

			 	this.doAction('<c:url value="/fclty/gamFcltyInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/fclty/gamFcltyUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				
				var inputVO = this.makeFormArgs("#fcltyManageVO");
			 	this.doAction('<c:url value="/fclty/gamFcltyDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
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
 GamFcltyMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var row = this.$("#fcltyMngtList").selectedRows();
		if(row.length == 0) this.$("#cmd").val("insert");
		else this.$("#cmd").val("modify");
		break;
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
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);
			
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
			this.$("#prtFcltyMngEntrpsCd").val(value["entrpscd"]);
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
				<input type="hidden" id="uniqFcltyCd" value="AC" />
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="searchPrtAtCode" type="text" size="3" maxlength="3" title="검색조건" /></td>
							<th>자산코드</th>
							<td>
								<input id="searchAssetsCd" type="text" size="3" maxlength="3" title="검색조건" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" disabled="disabled"/>
								<button id="searchPopupBtn">선택</button>
							</td>
							<th>항만시설코드</th>
							<td>
								<input id="searchFcltyCd" type="text" size="2" maxlength="2" title="검색조건" value="AC" disabled="disabled"/>&nbsp;-&nbsp;
								<input id="searchFcltySeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
						</tr>
						<tr>
							<th>항만시설 명</th>
							<td colspan="5"><input id="searchKeyword" type="text" size="40" maxlength="40" title="검색조건" /></td>
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
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="3" id="gisAssetsCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="2" id="gisAssetsSubCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="3" id="gisAssetsPrtAtCode" disabled="disabled"/>
								<button id="gisCodePopupBtn">자산코드 검색</button>
							</td>
						</tr>
						<!-- 
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 SUB 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="40" id="gisAssetsSubCd" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 항코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="40" id="gisAssetsPrtAtCode" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 GIS 코드</th>
							<td><input type="text" size="40" id="prtFcltyGisCd"/></td>
						</tr>
						-->
						<tr>
							<th width="20%" height="23" class="required_text">항만시설코드</th>
							<td>
								<input type="text" size="10" id="gisPrtFcltyCd" disabled="disabled"/>&nbsp;-&nbsp;<input type="text" size="13" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 명</th>
							<td><input type="text" size="40" id="prtFcltyNm"/></td>
						</tr>
						<!-- 
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 구분</th>
							<td><input type="text" size="40" id="prtFcltySe"/></td>
						</tr>
						 -->
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 규격</th>
							<td><input type="text" size="40" id="prtFcltyStndrd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 단위</th>
							<td><input type="text" size="40" id="prtFcltyUnit"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 관리 업체 코드</th>
							<td>
								<input type="text" size="27" id="prtFcltyMngEntrpsCd" disabled="disabled"/>&nbsp;&nbsp;
								<button id="searchEntrpsCdBtn">업체조회</button>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 설치일자</th>
							<td><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">항만시설 변경일자</th>
							<td><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">소재지</th>
							<td><input id="gisAssetsLocplc" type="text" size="40" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="20" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="13" title="지번 뒷자리" disabled="disabled" />
							</td>
						</tr>
						<tr id="displayDate">
							<th width="20%" height="23" class="required_text">등록일자</th>
							<td><input type="text" size="40" id="registDt" disabled="disabled" /></td>
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