<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamDrwListMngt.jsp
  * @Description : 도면시설사용목록관리
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
function GamFcltyDrwListMngtModule() {}

GamFcltyDrwListMngtModule.prototype = new EmdModule(840,500);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyDrwListMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#drwListMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyDrwMngtInfoList.do" />',
		dataType: "json",
		colModel : [
//{display:'삭제', 				name:'regYn',					width:40, 		sortable:false,		align:'center', displayFormat:'button', displayOption:{label:'삭제', className:'deleteButton'}},
				{display:"도면 목록 순번", 		name:"drwLstSeq",				width:100,		sortable:false,		align:"center"},
				{display:"도면 목록 등록 년도",	name:"drwLstRegistYear",		width:120,		sortable:false,		align:"center"},
				{display:"도면 명", 				name:"drwLstNm",				width:300,		sortable:false,		align:"center"},
				{display:"도면 부서 명",			name:"drwLstMngDeptCd",			width:160,		sortable:false,		align:"center"},
				{display:"도면 번호", 			name:"drwLstSeCd",				width:100,		sortable:false,		align:"center"},
				{display:"등록일자",				name:"registDt",				width:100,		sortable:false,		align:"center"},
				{display:"등록자",				name:"regUsr",					width:100,		sortable:false,		align:"center"}
				
			],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: "240"
	});
	
	this.$("#drwListMngtListSub").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyDrwMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면 자료 코드",		name:"drwDtaCd",			width:150,		sortable:false,		align:"center"},
				{display:"도면 명", 				name:"drwNm",				width:100,		sortable:false,		align:"center"},
				{display:"도면 파일명 물리", 		name:"drwFilenmPhysicl",	width:200,		sortable:false,		align:"center"},
				{display:"도면 파일명 논리",		name:"drwFilenmLogic",		width:200,		sortable:false,		align:"center"},
				{display:"도면 구분 코드", 		name:"drwSeCd",				width:100,		sortable:false,		align:"center"},
				{display:"도면 번호",				name:"drwNo",				width:100,		sortable:false,		align:"center"},
				{display:"도면 작성 일자",		name:"drwWritngDt",			width:100,		sortable:false,		align:"center"},
				{display:"등록일자",				name:"registdt",			width:100,		sortable:false,		align:"center"},
				{display:"등록자",				name:"regUsr",				width:100,		sortable:false,		align:"center"},
				{display:"도면 변경일",			name:"drwChangedt",			width:100,		sortable:false,		align:"center"},
				{display:"도면 변경 내역",		name:"drwChangeDtls",		width:100,		sortable:false,		align:"center"},
				{display:"hidden",				name:"drwLstRegistYear",	width:1,		sortable:false,		align:"center"},
				{display:"hidden",				name:"drwLstSeq",			width:1,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		showTableToggleBtn: false,
		height: "200"
	});
	
	
	this.$("#drwListMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#drwListMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		module.doAction('<c:url value="/fclty/gamFcltyDrwMngtList.do" />', {drwLstRegistYear:row["drwLstRegistYear"], drwLstSeq:row["drwLstSeq"]}, function(module, result) {
			module.$("#cmd").val("modify");
			module.$("#drwLstRegistYear").val(result.detail.drwLstRegistYear);
			module.$("#drwLstSeq").val(result.detail.drwLstSeq);
			module.$("#drwLstNm").val(result.detail.drwLstNm);
			module.$("#drwLstMngDeptCd").val(result.detail.drwLstMngDeptCd);
			module.$("#regUsr").val(result.detail.regUsr);
			module.$("#registDt").val(result.detail.registDt);
			module.$("#drwLstSeCd").val(result.detail.drwLstSeCd);
			module.$("#drwLstGisCd").val(result.detail.drwLstGisCd);
			module.$("#authnm").val(result.detail.authnm);
			module.$("#sbmNm").val(result.detail.sbmNm);
			module.$("#exmNm").val(result.detail.exmNm);
			module.$("#cnstrtr").val(result.detail.cnstrtr);
			module.$("#scl").val(result.detail.scl);
			
			var totalCount = result.totalCount;
			if(totalCount > 0){
				var searchOpt = module.makeFormArgs("#drwListManageVO");
				module.$("#drwListMngtListSub").flexOptions({params:searchOpt}).flexReload();
				module.$("#subListDiv").show();				
			}else{
				module.$("#subListDiv").hide();
			}
			module.$("#subBtnDiv").show();
	 	});
	});
	
	this.$("#drwListMngtListSub").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.doAction('<c:url value="/fclty/gamFcltyDrwListMngSelectView.do" />', {drwLstRegistYear:row["drwLstRegistYear"], drwLstSeq:row["drwLstSeq"], drwDtaCd:row["drwDtaCd"]}, function(module, result) {
			
			/*alert(result.detail.drwDtaCd);
			alert(result.detail.drwLstRegistYear);
			alert(result.detail.drwDtaCd);*/
		});
	});

};


this.$("#drwListMngtList").on("onButtonClicked", function(event, module, row, grid, param) {
	throw 0;
});


/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyDrwListMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			var searchOpt=this.makeFormArgs("#drwListForm");
		 	this.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload(); 
		break;
		
			// 목록
		case "listBtn":
			this.$("#drwListMngtListTab").tabs("option", {active: 0}); 
		break;
		
		// 추가
		case "addBtn":
			this.$("#displayDate").hide();
			this.$("#subListDiv").hide();
			this.$("#drwListManageVO :input").val("");
			this.$("#drwListManageVO :input").removeAttr("disabled");
			this.$("#cmd").val("insert");
			
			this.doAction('<c:url value="/fclty/gamFcltyDrwInfoGetInsertSeq.do" />', inputVO, function(module, result) {
				module.$("#drwLstSeq").val(result.seq);
				module.$("#drwLstSeq").attr("disabled","disabled");
				module.$("#regUsr").val("TEST01");
				module.$("#regUsr").attr("disabled","disabled");
		 	});
			
			this.$("#drwListMngtListTab").tabs("option", {active: 1});
		break;
		
		
		// 도면 자료 추가
		case "addSubBtn":
			this.$("#subListDiv").show();
			this.$(".changeView").hide();
			this.$("#drwDtaListManageVO").show();
			this.$("#drwDtaListManageVO :input").val("");
			this.$("#drwDtaListManageVO :input").removeAttr("disabled");
			this.$("#dtaCmd").val("insert");
			this.$("#drwLstRegistYearSub").val(this.$("#drwLstRegistYear").val());
			this.$("#drwLstRegistYearSub").attr("disabled","disabled");
			this.$("#drwLstSeqSub").val(this.$("#drwLstSeq").val());
			this.$("#drwLstSeqSub").attr("disabled","disabled");
			this.$("#drwWritngDt").attr("disabled","disabled");
			this.$("#regUsrSub").val("TEST01");
			this.$("#regUsrSub").attr("disabled","disabled");
		break;

		// 저장
		case "saveBtn":

		 	var inputVO = this.makeFormArgs("#drwListManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwInfoListMngInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0}); 
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwListMngUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0}); 
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		
		// 저장
		case "saveSubBtn":
			this.$("#drwWritngDt").val(this.$("#drwWritngDt").val().replace(/\-/g,""));
		 	var inputVO = this.makeFormArgs("#drwDtaListManageVO");
			if(this.$("#dtaCmd").val() == "insert") {
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwListMngInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0}); 
						module.$("#drwDtaListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwListMngUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0}); 
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				
				var inputVO = this.makeFormArgs("#drwListManageVO");
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwListMngDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0}); 
						module.$("#drwListManageVO :input").val("");
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
 GamFcltyDrwListMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var row = this.$("#drwListMngtList").selectedRows();
		if(row.length == 0) this.$("#cmd").val("insert");
		else this.$("#cmd").val("modify");
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
GamFcltyDrwListMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
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
var module_instance = new GamFcltyDrwListMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="drwListForm">
				<input type="hidden" id="uniqFcltyCd" value="AE" />
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>도면 목록 등록 년도</th>
							<td><input id="searchDrwLstRegistYear" type="text" size="20" maxlength="4" title="검색조건" /></td>
							<th>도면 목록 순번</th>
							<td><input id="searchDrwLstSeq" type="text" size="20" maxlength="4" title="검색조건" /></td>
						</tr>
						<tr>
							<th>도면 명</th>
							<td><input id="searchDrwLstNm" type="text" size="40" title="검색조건" /></td>
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
		<div id="drwListMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">도면시설목록</a></li>
				<li><a href="#tabs2" class="emdTab">도면시설상세</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="drwListMngtList" style="display:none"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="drwListManageVO">
					<input type="hidden" id="cmd" />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 등록 년도<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="40" id="drwLstRegistYear" disabled="disabled" maxlength="4" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 순번</th>
							<td><input type="text" size="40" id="drwLstSeq" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 명</th>
							<td><input type="text" size="40" id="drwLstNm" disabled="disabled" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사명</th>
							<td><input type="text" size="40" id="authnm" disabled="disabled" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">제출자</th>
							<td><input type="text" size="40" id="sbmNm" disabled="disabled" maxlength="40"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">검토자</th>
							<td><input type="text" size="40" id="exmNm" disabled="disabled" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시공자</th>
							<td><input type="text" size="40" id="cnstrtr" disabled="disabled" maxlength="30" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 구분 코드</th>
							<td><input type="text" size="40" id="drwLstSeCd" disabled="disabled" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 관리 부서 코드</th>
							<td><input type="text" size="40" id="drwLstMngDeptCd" disabled="disabled" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 GIS 코드</th>
							<td><input type="text" size="40" id="drwLstGisCd" disabled="disabled" maxlength="11" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">등록자</th>
							<td><input type="text" size="40" id="regUsr"/></td>
						</tr>
						<tr id="displayDate">
							<th width="20%" height="23" class="required_text">등록일자</th>
							<td><input type="text" size="40" id="registDt" disabled="disabled" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel"><button id="saveBtn">도면저장</button></div>
				
				<!-- 도면자료 목록조회 --> 
				<div id="subListDiv">
					<table id="drwListMngtListSub" style="display: none;"></table>
					<br />
					<form id="drwDtaListManageVO" style="display: none;">
						<input type="hidden" id="dtaCmd" />
						<table class="searchPanel">
							<tr>
								<th width="20%" height="23" class="required_text">도면 자료 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
								<td><input type="text" size="40" id="drwDtaCd" disabled="disabled" maxlength="20" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면명</th>
								<td><input type="text" size="40" id="drwNm" disabled="disabled" maxlength="40"/></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 파일명 물리</th>
								<td><input type="text" size="40" id="drwFilenmPhysicl" disabled="disabled" maxlength="240" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 파일명 논리</th>
								<td><input type="text" size="40" id="drwFilenmLogic" disabled="disabled" maxlength="240" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 구분 코드</th>
								<td><input type="text" size="40" id="drwSeCd" disabled="disabled" maxlength="10"/></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면번호</th>
								<td><input type="text" size="40" id="drwNo" disabled="disabled" maxlength="8" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면작성일자</th>
								<td><input type="text" size="40" id="drwWritngDt" class="emdcal" disabled="disabled" maxlength="8" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 목록 구분 코드</th>
								<td><input type="text" size="40" id="drwLstSeCd" disabled="disabled" maxlength="10" /></td>
							</tr>
							<tr class="changeView">
								<th width="20%" height="23" class="required_text">도면 변경일</th>
								<td><input type="text" size="40" id="drwChangedt" disabled="disabled" maxlength="8" /></td>
							</tr>
							<tr class="changeView">
								<th width="20%" height="23" class="required_text">도면 변경 내역</th>
								<td><input type="text" size="40" id="drwChangeDtls" disabled="disabled" maxlength="200" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 GIS 코드</th>
								<td><input type="text" size="40" id="drwGisCd" disabled="disabled" maxlength="11" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 목록 등록 년도</th>
								<td><input type="text" size="40" id="drwLstRegistYearSub" disabled="disabled" maxlength="4" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 목록 순번</th>
								<td><input type="text" size="40" id="drwLstSeqSub" disabled="disabled" maxlength="4" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">등록자</th>
								<td><input type="text" size="40" id="regUsrSub"/></td>
							</tr>
							<tr class="changeView">
								<th width="20%" height="23" class="required_text">등록일자</th>
								<td><input type="text" size="40" id="registdtSub" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div id="subBtnDiv">
					<div class="emdControlPanel">
						<button id="listSubBtn">목록</button>
						<button id="addSubBtn">도면자료추가</button>
						<button id="saveSubBtn">도면자료저장</button>
						<button id="deleteSubBtn">도면자료삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>