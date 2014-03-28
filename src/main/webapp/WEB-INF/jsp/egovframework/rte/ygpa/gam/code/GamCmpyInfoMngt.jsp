<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @Class Name : GamCmpyInfoMngt.jsp
  * @Description : 업체정보관리 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.03.05  kok          최초 생성
  *
  * author kok
  * since 2014.03.05
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamCmpyCode" staticJavascript="false" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmpyInfoMngtModule() {}

GamCmpyInfoMngtModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmpyInfoMngtModule.prototype.loadComplete = function() {

	// 업체정보 목록 조회
	this.$("#cmpyInfoMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCmpyInfoMngtList.do" />',
		dataType: "json",
		colModel : [
					{display:"업체코드", 		name:"entrpscd",		width:100, 	sortable:false,		align:"center"},
					{display:"업체명", 			name:"entrpsNm",		width:150, 	sortable:false,		align:"center"},
					{display:"대표자명", 		name:"rprsntvNm",		width:80, 	sortable:false,		align:"center"},
					{display:"사업자등록번호", 	name:"bizrno",			width:120, 	sortable:false,		align:"center"},
					{display:"업종", 			name:"induty",			width:80, 	sortable:false,		align:"center"},
					{display:"전화번호", 		name:"tlphonNo",		width:100, 	sortable:false,		align:"center"},
					{display:"팩스", 			name:"fax",				width:100, 	sortable:false,		align:"center"},
					{display:"우편번호", 		name:"zip",				width:80, 	sortable:false,		align:"center"},
					{display:"주소", 			name:"adres",			width:150, 	sortable:false,		align:"center"}
					],
		height: "auto"
	});
	
	this.$("#cmpyInfoMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.makeFormValues("#cmpyInfoMngtManageVO", row);
		module._editInfoData = module.getFormValues("#cmpyInfoMngtManageVO", row);
		module._editInfoRow = module.$("#cmpyInfoMngtList").selectedRowIds()[0];
		module.$("#cmd").val("modify");
		module.$("#entrpscd").attr("disabled","disabled");
	});
	
	// 업체정보 목록 선택
	this.$("#cmpyInfoMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// 이벤트내에선 모듈에 대해 선택한다.
		module.doAction('<c:url value="/code/cmpyInfoMngtDetail.do" />', {entrpscd: row["entrpscd"]}, function(module, result) {

			var searchOpt = module.makeFormArgs("#cmpyInfoMngtManageVO");
			module.$("#cmpyMngtList").flexOptions({params:searchOpt}).flexReload();
			module.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});
	 	});
	});
	
	// 업체목록 조회
	this.$("#cmpyMngtList").flexigrid({
		module: this,
		url: '<c:url value="/code/gamCmpyMngtList.do" />',
		dataType: "json",
		colModel : [
					{display:"번호",		 		name:"rnum",				width:40, 	sortable:false,		align:"center"},
					{display:"담당자명", 		name:"chargerNm",			width:100, 	sortable:false,		align:"center"},
					{display:"부서", 			name:"chargerDept",			width:100, 	sortable:false,		align:"center"},
					{display:"직위", 			name:"chargerOfcPos",		width:80, 	sortable:false,		align:"center"},
					{display:"업무", 			name:"chrgJobDisplay",		width:100, 	sortable:false,		align:"center"},
					{display:"휴대폰번호", 		name:"chargerMoblphonNo",	width:120, 	sortable:false,		align:"center"},
					{display:"팩스 번호", 		name:"chargerFax",			width:100, 	sortable:false,		align:"center"},
					{display:"이메일", 			name:"chargerEmail",		width:80, 	sortable:false,		align:"center"}
					],
		height: "auto"
	});
	
	this.$("#cmpyMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.makeFormValues("#cmpyChargerMngtManageVO", row);
		module._editData = module.getFormValues("#cmpyChargerMngtManageVO", row);
		module._editRow = module.$("#cmpyMngtList").selectedRowIds()[0];
	});
	
	// 업체 담당자 목록 선택
	this.$("#cmpyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmpyInfoMngtListTab").tabs("option", {active: 2});			// 탭을 전환 한다.
	});
};
		

/**
 * 정의 된 버튼 클릭 시
 */
GamCmpyInfoMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
			
			if(this.$("#searchEntrpsCd").val() == "" && this.$("#searchBizrno").val() == ""){
				if(this.$("#searchEntrpsNm").val() == "" || this.$("#searchEntrpsNm").val().length < 2){
					this.$("#searchEntrpsNm").focus();
					alert("업체 명은 2자 이상 입력하십시오.");
					return;
				}
			}
			
			var searchOpt = this.makeFormArgs("#cmpyInfoMngtForm");
		 	this.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
		 	this.$("#cmpyInfoMngtListTab").tabs("option", {active: 0});
		break;
	
		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
	
		// 추가
		case "addBtn":
			this.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});
			this.$("#cmpyInfoMngtManageVO :input").val("");
			this.$("#cmpyMngtList").flexAddData({resultList:[]});
			this.$("#entrpscd").removeAttr("disabled");
			this.$("#cmd").val("insert");
		break;

		// 업체정보 담당자 정보 추가
		case "chargerAddBtn":
			
			this.$("#cmpyInfoMngtListTab").tabs("option", {active: 2});
			this.$("#cmpyChargerMngtManageVO :input").val("");
			
			this._editData = this.getFormValues("#cmpyChargerMngtManageVO", {_updtId:"I"});
			this._editRow = this.$("#cmpyMngtList").flexGetData().length;
		break;
			
		// 저장
		case "saveBtn":
			
			var inputVO=[{}];
			inputVO[inputVO.length]={name: "updateList", value :JSON.stringify(this.$("#cmpyMngtList").selectFilterData([{col: '_updtId', filter: 'U'}])) };
			inputVO[inputVO.length]={name: "insertList", value: JSON.stringify(this.$("#cmpyMngtList").selectFilterData([{col: '_updtId', filter: 'I'}])) };
			inputVO[inputVO.length]={name: "deleteList", value: JSON.stringify(this._deleteDataList) };
			inputVO[inputVO.length]={name: "form", value: JSON.stringify(this.getFormValues("#cmpyInfoMngtManageVO", {})) };	

			if(this.$("#cmd").val() == "insert") {

				this.doAction('<c:url value="/code/gamCmpyInfoMngtRegist.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0});
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
				
			 	this.doAction('<c:url value="/code/gamCmpyInfoMngtModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;
		
		// 업체담당자 정보 화면상 임시 저장
		case "chargerSaveBtn":

			if(this._editData == null){
				alert("적용할 업체 담당자가 없습니다.");
				return;
			}
			
			if(!validateGamCmpyCode(this.$("#cmpyChargerMngtManageVO")[0])) return;

			this._editInfoData = this.getFormValues("#cmpyInfoMngtManageVO", this._editInfoData);
			this._editData = this.getFormValues("#cmpyChargerMngtManageVO", this._editData);

			if(this._editData._updtId == undefined || this._editData._updtId != "I"){
				this._editData._updtId = "U";
				this.$("#cmpyMngtList").flexUpdateRow(this._editRow, this._editData);
			}else{
				this.$("#cmpyMngtList").flexAddRow(this._editData);
			}
			
			this.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});
			this.$("#cmpyChargerMngtManageVO :input").val("");
			this._editData = null;
		break;
		
		// 삭제
		case "chargerDeleteBtn":
			if(this.$("#cmpyMngtList").selectedRowIds().length > 0) {
				for(var i=this.$("#cmpyMngtList").selectedRowIds().length-1; i>=0; i--) {
					
					var row = this.$("#cmpyMngtList").flexGetRow(this.$("#cmpyMngtList").selectedRowIds()[i]);
					if(row._updtId == undefined || row._updtId != "I") this._deleteDataList[this._deleteDataList.length] = row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
					this.$("#cmpyMngtList").flexRemoveRow(this.$("#cmpyMngtList").selectedRowIds()[i]);
				}
			}
			else{
				alert("삭제할 업체담당자를 선택하여 주십시오.");
				return;
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("선택한 업체정보를 삭제하시겠습니까?")){
				if(this.$("#cmpyInfoMngtList").selectedRowIds().length > 0) {
					var row = this.$("#cmpyInfoMngtList").selectedRows();

				 	this.doAction('<c:url value="/code/gamCmpyInfoMngtRemove.do" />', {entrpscd:row[0]["entrpscd"]}, function(module, result) {
				 		if(result.resultCode == "0"){
				 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
							module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
							module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
							module.$("#cmmnCodeDetailManageVO :input").val("");
				 		}
				 		alert(result.resultMsg);
				 	});
				}else{
					alert("삭제할 업체정보를 선택하여 주십시오.");
					return;	
				}
			}
		break;
	}
};


GamCmpyInfoMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
			this.$('#searchViewStack')[0].changePanelId(0);
		break;
	
		case "tabs2":
			this.$("#searchViewStack")[0].changePanelId(1);
			this._deleteDataList = [];
			/*
			var row = this.$("#cmpyMngtList").selectedRows();
			if(row.length == 0) this.$("#cmd").val("insert");
			else this.$("#cmd").val("modify");
*/
		break;

		case "tabs3":
			this.$('#searchViewStack')[0].changePanelId(2);
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
 GamCmpyInfoMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
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
			this.$("#searchEntrpsCd").val(value["entrpscd"]);
			this.$("#searchEntrpsNm").val(value["entrpsNm"]);
		break;
	
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			throw 0;
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmpyInfoMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div id="searchViewStack" class="viewStack">
			<form id="cmpyInfoMngtForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>업체 명</th>
							<td><input type="text" size="20" id="searchEntrpsNm" maxlength="50" /></td>
							<th>업체 코드</th>
							<td><input type="text" size="10" id="searchEntrpsCd" maxlength="10" /></td>
							<th>사업자번호</th>
							<td><input id="searchBizrno" type="text" size="10" maxlength="12" title="사업자 번호"/></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="cmpyInfoMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">업체정보 목록</a></li>
				<li><a href="#tabs2" class="emdTab">업체정보 상세</a></li>
				<li><a href="#tabs3" class="emdTab">업체담당자 정보</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="cmpyInfoMngtList" style="display:none;" class="fillHeight"></table>
			</div>
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="cmpyInfoMngtManageVO">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">업체 코드</th>
							<td><input type="text" size="30" id="entrpscd" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">대표자 명</th>
							<td><input type="text" size="30" id="rprsntvNm" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 명</th>
							<td><input type="text" size="30" id="entrpsNm" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">사업자등록번호</th>
							<td><input type="text" size="12" id="bizrno" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업종</th>
							<td colspan="3"><input type="text" id="induty" title="업종" size="30" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">전화번호</th>
							<td><input type="text" size="30" id="tlphonNo" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">팩스</th>
							<td><input type="text" size="30" id="fax" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">우편번호</th>
							<td><input type="text" size="30" id="zip" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">주소</th>
							<td><input type="text" size="50" id="adres" disabled="disabled" /></td>
						</tr>
					</table>
					<br />
					<table id="cmpyMngtList" style="display:none;" class="fillHeight"></table>
				</form>
				<div class="emdControlPanel">
					<button id="chargerAddBtn">추가</button>
					<button id="chargerDeleteBtn">삭제</button>
					<button id="saveBtn">업체정보 저장</button>
				</div>
			</div>
			
			<!-- 업체담당자 정보 -->
			<div id="tabs3" class="emdTabPage" style="overflow: hidden;">
				<form id="cmpyChargerMngtManageVO">
					<input type="hidden" id="chargerEntrpscd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">담당자 명</th>
							<td><input type="text" size="30" id="chargerNm" maxlength="40" /></td>
							<th width="20%" height="23" class="required_text">담당자 직위</th>
							<td><input type="text" size="10" id="chargerOfcPos" maxlength="10" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">부서</th>
							<td colspan="3"><input type="text" size="10" id="chargerDept" maxlength="80" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">담당 업무</th>
							<td><input id="chrgJob" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM045" /></td>
							<th width="20%" height="23" class="required_text">관리부서</th>
							<td>
								<select class="select" id="mngDeptCd">
									<c:forEach var="result" items="${ogrnztId_result}" varStatus="status">
										<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
									</c:forEach>			  		   
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">휴대폰</th>
							<td><input type="text" size="30" id="chargerMoblphonNo" maxlength="20" /></td>
							<th width="20%" height="23" class="required_text">전화번호</th>
							<td><input type="text" size="30" id="chargerTlphonNo" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">팩스</th>
							<td><input type="text" size="30" id="chargerFax" maxlength="20" /></td>
							<th width="20%" height="23" class="required_text">이메일</th>
							<td><input type="text" size="30" id="chargerEmail" maxlength="80" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="chargerSaveBtn">적용</button>
				</div>
			</div>
		</div>
	</div>
</div>