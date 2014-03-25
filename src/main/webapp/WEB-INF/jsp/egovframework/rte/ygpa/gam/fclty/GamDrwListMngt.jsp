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

GamFcltyDrwListMngtModule.prototype = new EmdModule(800,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyDrwListMngtModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#drwListMngtList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyDrwMngtInfoList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면목록번호",	name:"drawListNumber",		width:120,		sortable:false,		align:"center"},
				{display:"도면 목록 명", 				name:"drwLstNm",				width:240,		sortable:false,		align:"center"},
				{display:"공사 명",			name:"authnm",			width:160,		sortable:false,		align:"center"},
				{display:"시공자", 			name:"cnstrtr",				width:120,		sortable:false,		align:"center"},
				{display:"도면 관리 부서 명",			name:"drwLstMngDeptCd",			width:120,		sortable:false,		align:"center"}

			],
		showTableToggleBtn: false,
		height: "auto",
		preProcess: function(module, data) {
			$.each(data.resultList, function(value){
				this.drawListNumber = value.drwLstRegistYear+'-'+value.drwLstSeq;
			});
			return data;
		}
	});

	this.$("#drwListMngtListSub").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamFcltyDrwMngtList.do" />',
		dataType: "json",
		colModel : [
				{display:"도면 자료 코드",		name:"drwDtaCd",			width:150,		sortable:false,		align:"center"},
				{display:"도면 명", 				name:"drwNm",				width:160,		sortable:false,		align:"center"},
				{display:"도면 파일명",		name:"drwFilenmLogic",		width:120,		sortable:false,		align:"center"},
				{display:"도면 구분", 		name:"drwSeCd",				width:100,		sortable:false,		align:"center"},
				{display:"도면 번호",				name:"drwNo",				width:100,		sortable:false,		align:"center"},
				{display:"도면 작성 일자",		name:"drwWritngDt",			width:100,		sortable:false,		align:"center"},
				{display:"도면 변경일",			name:"drwChangedt",			width:100,		sortable:false,		align:"center"}
			],
		usepager: true,
		useRp: true,
		showTableToggleBtn: false,
		height: "200"
	});


/* 	this.$("#drwListMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
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

			module.$("#drwListManageVO :input").removeAttr("disabled");
			module.$("#drwDtaListManageVO").hide();

			module.$("#drwLstRegistYear").attr("disabled","disabled");
			module.$("#drwLstSeq").attr("disabled","disabled");
			module.$("#regUsr").attr("disabled","disabled");
			module.$("#registDt").attr("disabled","disabled");
	 	});
	});
 */
	this.$("#drwListMngtListSub").on("onItemDoubleClick", function(event, module, row, grid, param) {

		module.$("#dtaCmd").val("modify");
		module.$("#subListDiv").show();
		module.$("#drwDtaListManageVO").show();
		module.$(".changeView").show();
		module.doAction('<c:url value="/fclty/gamFcltyDrwListMngSelectView.do" />', {drwLstRegistYear:row["drwLstRegistYear"], drwLstSeq:row["drwLstSeq"], drwDtaCd:row["drwDtaCd"]}, function(module, result) {

			module.$("#drwDtaCd").val(result.detail.drwDtaCd);
			module.$("#drwNm").val(result.detail.drwNm);
			module.$("#drwFilenmPhysicl").val(result.detail.drwFilenmPhysicl);
			module.$("#drwFilenmLogic").val(result.detail.drwFilenmLogic);
			module.$("#drwSeCd").val(result.detail.drwSeCd);
			module.$("#drwNo").val(result.detail.drwNo);
			module.$("#drwWritngDt").val(result.detail.drwWritngDt);
			module.$("#drwChangedt").val(result.detail.drwChangedt);
			module.$("#drwChangeDtls").val(result.detail.drwChangeDtls);
			module.$("#drwGisCd").val(result.detail.drwGisCd);
			module.$("#drwLstRegistYearSub").val(result.detail.drwLstRegistYear);
			module.$("#drwLstSeqSub").val(result.detail.drwLstSeq);
			module.$("#regUsrSub").val(result.detail.regUsr);
			module.$("#registdtSub").val(result.detail.registdt);

			module.$("#drwDtaCd").attr("disabled","disabled");
			module.$("#drwChangedt").attr("disabled","disabled");
		});
	});

};


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
			 	this.doAction('<c:url value="/fclty/gamFcltyDrwInfoListMngUpdate.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#drwListForm");
			 			module.$("#drwListMngtListTab").tabs("option", {active: 0});
						module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;


		// 저장
		case "saveSubBtn":
			this.$("#drwWritngDt").val(this.$("#drwWritngDt").val().replace(/\-/g,""));
			this.$("#drwChangedt").val(this.$("#drwChangedt").val().replace(/\-/g,""));
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
						module.$("#drwDtaListManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){

				var filter = [{ 'col': 'chkRole', 'filter': true}];
				var reglist = this.$("#drwListMngtList").selectFilterData(filter);

				if(reglist.length > 0){

					var drwLstRegistYears = "";
					var drwLstSeqs = "";

					for(var i=0; i<reglist.length; i++){
						if(reglist[i].chkRole == true){
							if(i < (reglist.length-1)){
								drwLstRegistYears += reglist[i].drwLstRegistYear + ";";
								drwLstSeqs += reglist[i].drwLstSeq + ";";
							}else{
								drwLstRegistYears += reglist[i].drwLstRegistYear;
								drwLstSeqs += reglist[i].drwLstSeq;
							}
						}
					}

					var inputVO = {drwLstRegistYears: drwLstRegistYears, drwLstSeqs:drwLstSeqs};
					this.doAction('<c:url value="/fclty/gamFcltyDrwInfoListMngDelete.do" />', inputVO, function(module, result) {
				 		if(result.resultCode == 0){
				 			module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
							module.$("#drwListMngtListTab").tabs("option", {active: 0});
							module.$("#drwDtaListManageVO :input").val("");
				 		}
				 		alert(result.resultMsg);
				 	});
				}else{

				}
			}
		break;

		// 삭제
		case "deleteSubBtn":
			if(confirm("삭제하시겠습니까?")){

				var inputVO = {drwLstRegistYear: this.$("#drwLstRegistYearSub").val(), drwLstSeq:this.$("#drwLstSeqSub").val(), drwDtaCd:this.$("#drwDtaCd").val()};
				this.doAction('<c:url value="/fclty/gamFcltyDrwListMngDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#drwListMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#drwListMngtListTab").tabs("option", {active: 0});
						module.$("#drwDtaListManageVO :input").val("");
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
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>도면 목록 등록 년도</th>
							<td><input id="searchDrwLstRegistYear" type="text" size="4" maxlength="4" title="등록년도" />-<input id="searchDrwLstSeq" type="text" size="4" maxlength="4" title="등록순번" /></td>
							<th>도면 명</th>
							<td><input id="searchDrwLstNm" type="text" size="40" title="도면명" /></td>
							<td rowspan="2"><button id="searchBtn">조회</button></td>
						</tr>
						<tr>
							<th>공사 명</th>
							<td><input id="searchAuthnm" type="text" size="20" maxlength="4" title="공사 명" /></td>
							<th>관리부서</th>
							<td><input id="searchDeptCd" type="text" class="ygpaDeptSelect" data-default-prompt="전체"/></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="drwListMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">도면목록</a></li>
				<li><a href="#tabs2" class="emdTab">도면목록상세</a></li>
				<li><a href="#tabs3" class="emdTab">도면파일</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage fillHeight">
				<table id="drwListMngtList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="showMap">맵조회</button>
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="drwListManageVO">
					<input type="hidden" id="cmd" />
					<table class="searchPanel">
						<tr>
							<th width="150" height="23" class="required_text">도면 목록 등록 년도<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="4" id="drwLstRegistYear" maxlength="4" />-<input type="text" size="4" id="drwLstSeq" disabled="disabled"/><p>등록년도를 입력하면 순번이 자동으로 부여됩니다.</p></td>
						</tr>
						<tr>
							<th height="23" class="required_text">도면 목록 명</th>
							<td colspan="3"><input type="text" size="40" id="drwLstNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 구분</th>
							<td colspan="3"><input type="text" id="drwLstSeCd" class="ygpaCmmnCd" data-code-id="GAM047" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사명</th>
							<td colspan="3"><input type="text" size="40" id="authnm" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시공자</th>
							<td colspan="3"><input type="text" size="40" id="cnstrtr" maxlength="30" /></td>
						</tr>
						<tr>
							<th height="23" class="required_text">제출자</th>
							<td><input type="text" size="30" id="sbmNm" maxlength="40"/></td>
							<th width="20%" height="23">검토자</th>
							<td><input type="text" size="30" id="exmNm" maxlength="40" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">도면 목록 관리 부서 코드</th>
							<td colspan="3"><input type="text" id="drwLstMngDeptCd" maxlength="20" class="ygpaDeptSelect"/></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnEditMap">도면위치등록</button>
					<button id="btnRemoveMap">도면위치삭제</button>
					<button id="btnGotoMap">도면위치조회</button>
					<button id="deleteBtn">도면목록삭제</button>
					<button id="saveBtn">도면목록저장</button>
				</div>
			</div>
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<!-- 도면자료 목록조회 -->
					<table id="drwListMngtListSub" style="display: none;"></table>
					<div class="emdControlPanel"><button id="btnUploadFile">업로드</button><button id="btnDownloadFile">다운로드</button><button id="removeAssetGisPhoto">삭제</button></div>
					<form id="drwDtaListManageVO">
						<input type="hidden" id="dtaCmd" />
						<table class="searchPanel">
							<tr>
								<th width="20%" height="23">도면 자료 코드</th>
								<td colspan="3"><input type="text" size="40" id="drwDtaCd" disabled="disabled" maxlength="20" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면번호</th>
								<td><input type="text" size="8" id="drwNo" maxlength="8" /></td>
								<th width="20%" height="23" class="required_text">도면 구분</th>
								<td><input type="text" id="drwSeCd" class="ygpaCmmnCd" data-code-id="GAM048" /></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면명</th>
								<td colspan="3"><input type="text" size="40" id="drwNm" maxlength="40"/></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면작성일자</th>
								<td><input type="text" id="drwWritngDt" class="emdcal"/></td>
								<th width="20%" height="23" class="required_text">도면 변경일</th>
								<td><input type="text" id="drwChangedt" class="emdcal"/></td>
							</tr>
							<tr>
								<th width="20%" height="23" class="required_text">도면 변경 내역</th>
								<td colspan="3"><input type="text" size="60" id="drwChangeDtls" maxlength="200" /></td>
							</tr>
						</table>
					</form>
					<div class="emdControlPanel">
						<button id="addSubBtn">적용</button>
					</div>
				</div>
		</div>
	</div>
</div>