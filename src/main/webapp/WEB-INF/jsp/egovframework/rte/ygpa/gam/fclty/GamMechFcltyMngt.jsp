<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMechFcltyMngt.jsp
  * @Description : 기계시설사용목록관리
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
		url: '<c:url value="/fclty/gamMechFcltyMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"항코드",		 			name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
				{display:"항이름",		 			name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
				{display:"자산코드",		 			name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
				{display:"기계시설코드", 				name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
				{display:"시설명",	 				name:"prtFcltyNm",			width:100,		sortable:false,		align:"center"},
				{display:"위치",		 				name:"gisAssetsLocCd",		width:40,		sortable:false,		align:"center"},
				{display:"구조",		 				name:"prtFcltyStndrd",		width:100,		sortable:false,		align:"center"},
				{display:"시설단위", 	 			name:"prtFcltyUnit",		width:80,		sortable:false,		align:"center"},
				{display:"관리업체 명", 	 			name:"prtFcltyMngEntrpsNm",	width:80,		sortable:false,		align:"center"},
				{display:"관리업체 코드",	 			name:"prtFcltyMngEntrpsCd",	width:80,		sortable:false,		align:"center"},
				{display:"설치일자",					name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
				{display:"변경일자",					name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
//		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "230"
	});
	
	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		var detailInput = {gisAssetsCd:row["gisAssetsCd"], gisPrtFcltySeq:row["gisPrtFcltySeq"], gisAssetsPrtAtCode:row["gisAssetsPrtAtCode"], gisAssetsSubCd:row["gisAssetsSubCd"], gisPrtFcltyCd:row["gisPrtFcltyCd"]};
		module.doAction('<c:url value="/fclty/gamMechFcltyView.do" />', detailInput, function(module, result) {

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
			module.$("#prtFcltyMngEntrpsCd").val(result.detail.prtFcltyMngEntrpsCd);
			module.$("#prtFcltyMngEntrpsNm").val(result.detail.prtFcltyMngEntrpsNm);
			module.$("#gisAssetsLocplc").val(result.detail.gisAssetsLocplc);
			module.$("#gisAssetsLnm").val(result.detail.gisAssetsLnm);
			module.$("#gisAssetsLnmSub").val(result.detail.gisAssetsLnmSub);
			module.$("#gisAssetsNm").val(result.detail.gisAssetsNm);
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
		
		// 추가
		case "addBtn":
			this.$("#fcltyMngtListTab").tabs("option", {active: 1});
			this.$("#fcltyManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 자산코드 팝업
		case "gisCodePopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		// 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
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
			 	this.doAction('<c:url value="/fclty/gamMechFcltyInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#fcltyForm");
						module.$("#fcltyMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyMngtListTab").tabs("option", {active: 0}); 
						module.$("#fcltyManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			       
			 	this.doAction('<c:url value="/fclty/gamMechFcltyUpdate.do" />', inputVO, function(module, result) {
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
			if(confirm("선택 한 기계 시설을 삭제하시겠습니까?")){
				
				var row = this.$("#fcltyMngtList").selectedRows();
				
				var inputVO = {gisAssetsCd:row[0]["gisAssetsCd"], gisPrtFcltySeq:row[0]["gisPrtFcltySeq"], gisAssetsPrtAtCode:row[0]["gisAssetsPrtAtCode"], gisAssetsSubCd:row[0]["gisAssetsSubCd"], gisPrtFcltyCd:row[0]["gisPrtFcltyCd"]};
			 	this.doAction('<c:url value="/fclty/gamMechFcltyDelete.do" />', inputVO, function(module, result) {
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

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#prtFcltyMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtFcltyMngEntrpsNm").val(value["entrpsNm"]);
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
							<th>기계시설코드</th>
							<td>
								<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" />&nbsp;-&nbsp;
								<input id="searchFcltySeq" type="text" size="4" maxlength="4" title="검색조건" />
							</td>
						</tr>
						<tr>
							<th>기계시설 명</th>
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
				<li><a href="#tabs1" class="emdTab">기계시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">기계시설 상세</a></li>
				<li><a href="#tabs3" class="emdTab">기계시설 사진</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="fcltyMngtList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addBtn">시설 추가</button>
					<button id="deleteBtn">시설 삭제</button>
					<button id="mapSearch">맵 조회</button>
				</div>
			</div>
			
			<!-- 기계시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="fcltyManageVO">
					<input type="hidden" id="cmd" />
					<!-- 신경 쓸 필요 없다 함.2014-03-07 					
					<input type="hidden" id="prtFcltyGisCd" /> -->
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="8" id="gisAssetsCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="6" id="gisAssetsSubCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="8" id="gisAssetsPrtAtCode" disabled="disabled"/>
							</td>
							<td colspan="2"><button id="gisCodePopupBtn">자산코드</button></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="80" id="gisAssetsNm" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">소재지</th>
							<td><input id="gisAssetsLocplc" type="text" size="40" title="소재지" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">지번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="5" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="4" title="지번 뒷자리" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시설분류</th>
							<td colspan="3">
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" onchange="alert(11);" id="selectedGAM005"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">기계시설 코드</th>
							<td colspan="3">
								<input type="text" size="10" id="gisPrtFcltyCd" />&nbsp;-&nbsp;<input type="text" size="20" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">기계시설 명</th>
							<td colspan="3"><input type="text" size="80" id="prtFcltyNm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">기계시설 규격</th>
							<td><input type="text" size="30" id="prtFcltyStndrd" maxlength="80" /></td>
							<th width="20%" height="23" class="required_text">단위</th>
							<td><input type="text" size="30" id="prtFcltyUnit"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관리 업체</th>
							<td>
								<input type="text" size="10" id="prtFcltyMngEntrpsCd" disabled="disabled"/>
								<input type="text" size="20" id="prtFcltyMngEntrpsNm" disabled="disabled"/>&nbsp;&nbsp;
							</td>
							<td colspan="2">
								<button id="searchEntrpsCdBtn">업체조회</button>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설치일자</th>
							<td colspan="3"><input id="prtFcltyInstlDt" type="text" class="emdcal" size="20" title="설치일자" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">변경일자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" class="emdcal" size="20" title="변경일자" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="insertGIS">GIS 등록</button>
					<button id="searchPosition">위치조회</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>
			
			<!-- 기계시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<div class="emdControlPanel">
					<button id="uploadPic">업로드</button>
					<button id="uploadPicOpen">사진열기</button>
					<button id="uploadPicDel">삭제</button>
				</div>
				<form id="fcltyPicManageVO">
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">제목<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">촬영일시<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">사진 설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<input type="text" size="8" id="" />
							</td>
						</tr>
					</table>
					<div class="emdControlPanel">
						<button id="">적용</button>
					</div>
					<table>
						<tr>
							<th width="20%" height="23" class="required_text">미리보기<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
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