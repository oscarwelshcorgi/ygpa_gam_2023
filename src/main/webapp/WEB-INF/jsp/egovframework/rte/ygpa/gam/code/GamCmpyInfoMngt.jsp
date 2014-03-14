<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
					{display:"업체코드", 		name:"entrpscd",		width:60, 	sortable:false,		align:"center"},
					{display:"업체명", 			name:"entrpsNm",		width:150, 	sortable:false,		align:"center"},
					{display:"업체유형", 		name:"entrpsTyDisplay",	width:50, 	sortable:false,		align:"center"},
					{display:"사업자구분", 		name:"bsnmSeDisplay",	width:80, 	sortable:false,		align:"center"},
					{display:"대표자명", 		name:"rprsntvNm",		width:80, 	sortable:false,		align:"center"},
					{display:"사업자등록번호", 	name:"bizrno",			width:120, 	sortable:false,		align:"center"},
					{display:"법인등록번호", 		name:"cprregistno",		width:100, 	sortable:false,		align:"center"},
					{display:"업종", 			name:"induty",			width:80, 	sortable:false,		align:"center"},
					{display:"업태", 			name:"bizcnd",			width:80, 	sortable:false,		align:"center"},
					{display:"전화번호", 		name:"tlphonNo",		width:100, 	sortable:false,		align:"center"},
					{display:"팩스", 			name:"fax",				width:100, 	sortable:false,		align:"center"},
					{display:"우편번호", 		name:"zip",				width:80, 	sortable:false,		align:"center"},
					{display:"주소", 			name:"adres",			width:150, 	sortable:false,		align:"center"}
					],
		//usepager: true,
		useRp: true,
		rp: 13,
		showTableToggleBtn: false,
		height: 'auto'
	});
	
	this.$("#cmpyInfoMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.makeFormValues("#cmpyInfoMngtManageVO", row);
		module._editInfoData = module.getFormValues("#cmpyInfoMngtManageVO", row);
		module._editInfoRow = module.$("#cmpyInfoMngtList").selectedRowIds()[0];
	});
	
	// 업체정보 목록 선택
	this.$("#cmpyInfoMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});			// 탭을 전환 한다.

		module.doAction('<c:url value="/code/cmpyInfoMngtDetail.do" />', {entrpscd: row["entrpscd"]}, function(module, result) {

			// value 설정
			/*
			module.$("#cmd").val("modify");
			module.$("#entrpscd").val(result.detail.entrpscd);										// 업체ID(hidden)
			module.$("#entrpsNm").val(result.detail.entrpsNm);										// 업체명
			module.$("#bizrno").val(result.detail.bizrno);											// 사업자등록번호
			module.$("#entrpsTy").val(result.detail.entrpsTy).attr("selected","selected");			// 업체유형
			module.$("#bsnmSe").val(result.detail.bsnmSe).attr("selected","selected");				// 사업자구분
			module.$("#cprregistno").val(result.detail.cprregistno);								// 법인등록번호
			module.$("#induty").val(result.detail.induty);											// 업종
			module.$("#bizcnd").val(result.detail.bizcnd);											// 업태
			module.$("#tlphonNo").val(result.detail.tlphonNo);										// 전화번호
			module.$("#fax").val(result.detail.fax);												// 팩스
			module.$("#zip").val(result.detail.zip);												// 우편번호
			module.$("#adres").val(result.detail.adres);											// 주소
			*/
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
					{display:"업무", 			name:"chrgJob",				width:100, 	sortable:false,		align:"center"},
					{display:"휴대폰번호", 		name:"chargerMoblphonNo",	width:120, 	sortable:false,		align:"center"},
					{display:"팩스 번호", 		name:"chargerFax",			width:100, 	sortable:false,		align:"center"},
					{display:"이메일", 			name:"chargerEmail",		width:80, 	sortable:false,		align:"center"}
					],
		//usepager: true,
		useRp: true,
		rp: 13,
		showTableToggleBtn: false,
		height: 'auto'
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
		/*
		module.doAction('<c:url value="/code/cmpyMngtDetail.do" />', {entrpscd: row["entrpscd"], chargerNo: row["chargerNo"]}, function(module, result) {

			// value 설정
		module.$("#cmd").val("modify");
			module.$("#entrpscd").val(result.detail.entrpscd);										// 업체코드
			module.$("#chargerNm").val(result.detail.chargerNm);									// 담당자명
			module.$("#chargerOfcPos").val(result.detail.chargerOfcPos);							// 담당자 직위
			module.$("#chargerDept").val(result.detail.chargerDept);								// 담당자 부서
			module.$("#chrgJob").val(result.detail.chrgJob);										// 담당 업무
			module.$("#cprregistno").val(result.detail.cprregistno);								// 법인등록번호
			module.$("#mngDeptCd").val(result.detail.mngDeptCd).attr("selected","selected");		// 관리 부서
			module.$("#chargerMoblphonNo").val(result.detail.chargerMoblphonNo);					// 담당자 휴대폰 번호
			module.$("#chargerTlphonNo").val(result.detail.chargerTlphonNo);						// 담당자 전화번호
			module.$("#chargerFax").val(result.detail.chargerFax);									// 담당자 팩스
			module.$("#chargerEmail").val(result.detail.chargerEmail);								// 담당자 이메일
			
	 	});
		*/
	});
};
		

/**
 * 정의 된 버튼 클릭 시
 */
GamCmpyInfoMngtModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case "searchBtn":
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
				alert("업데이트");
				
				/*
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
				*/
			}
		break;
		
		// 업체담당자 정보 화면상 임시 저장
		case "chargerSaveBtn":

			if(this._editData == null) return;
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
			
		 /*	var inputVO = this.makeFormArgs("#cmpyChargerMngtManageVO");
			if(this.$("#chargerCmd").val() == "insert") {
				this.$("#chargerEntrpscd").val(this.$("#entrpscd").val());
				
			 	this.doAction('<c:url value="/code/gamCmpyChargerMngtRegist.do" />', inputVO, function(module, result) {
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/code/gamCmpyChargerMngtModify.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#cmpyInfoMngtForm");
						module.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
						module.$("#cmpyInfoMngtListTab").tabs("option", {active: 0}); 
						module.$("#cmmnCodeDetailManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}*/
		break;
		
		// 삭제
		case "chargerDeleteBtn":
			if(confirm("업체 담당자 정보를 삭제하시겠습니까?")){
				
				if(this.$("#cmpyMngtList").selectedRowIds().length > 0) {
					for(var i=this.$("#cmpyMngtList").selectedRowIds().length-1; i>=0; i--) {
				
						var row = this.$("#cmpyMngtList").flexGetRow(this.$("#cmpyMngtList").selectedRowIds()[i]);
						if(row._updtId == undefined || row._updtId != "I") this._deleteDataList[this._deleteDataList.length] = row;	// 삽입 된 자료가 아니면 DB에 삭제를 반영한다.
						this.$("#cmpyMngtList").flexRemoveRow(this.$("#cmpyMngtList").selectedRowIds()[i]);
					}
				}
				break;
				
				var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRemove.do" />', {codeId : this.$("#codeId").val(), code : this.$("#code").val()}, function(module, result) {
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

		// 삭제
		case "deleteBtn":
			if(confirm("해당 업체정보를 삭제하시겠습니까?")){
				var inputVO = this.makeFormArgs("#cmmnCodeDetailManageVO");
			 	this.doAction('<c:url value="/code/gamCcmCmmnDetailCodeRemove.do" />', {codeId : this.$("#codeId").val(), code : this.$("#code").val()}, function(module, result) {
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
			
			var row = this.$("#cmpyMngtList").selectedRows();
			if(row.length == 0) this.$("#cmd").val("insert");
			else this.$("#cmd").val("modify");

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
							<td>
								<input type="text" size="7" id="searchEntrpsCd" />&nbsp;-&nbsp;
								<input type="text" size="20" id="searchEntrpsNm" />&nbsp;<button id="searchEntrpsCdBtn">업체조회</button>
							</td>
							<th>업체유형</th>
							<td width="10%">
								<input id="searchEntrpsTy" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM020" />
							</td>
						</tr>
						<tr>
							<th>사업자번호</th>
							<td>
								<input id="searchBizrno" type="text" size="10" maxlength="12" title="사업자 번호"/>
							</td>
							<th>사업자구분</th>
							<td width="10%">  		
								<input id="searchBsnmSe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM018" />
							</td>
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
		<div id="cmpyInfoMngtListTab" class="emdTabPanel" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">업체정보 목록</a></li>
				<li><a href="#tabs2" class="emdTab">업체정보 상세</a></li>
				<li><a href="#tabs3" class="emdTab">업체담당자 정보</a></li>
			</ul>
			<div id="tabs1" class="emdTabPage">
				<table id="cmpyInfoMngtList" style="display:none"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmpyInfoMngtManageVO">
					<input type="hidden" id="cmd"/>
					<input type="hidden" id="entrpscd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">업체 코드<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="30" id="entrpscd" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="entrpsNm" /></td>
							<th width="20%" height="23" class="required_text">사업자등록번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="10" id="bizrno"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input id="entrpsTy" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM020" /></td>
							<th width="20%" height="23" class="required_text">사업자구분<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input id="bsnmSe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM018" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">법인등록번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" id="cprregistno" size="13" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업종<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" id="induty" title="업종" size="30" maxlength="50" /></td>
							<th width="20%" height="23" class="required_text">업태<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="bizcnd"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="tlphonNo" /></td>
							<th width="20%" height="23" class="required_text">팩스<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="fax"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">우편번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="zip" /></td>
							<th width="20%" height="23" class="required_text">주소<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="adres" /></td>
						</tr>
					</table>
					<table id="cmpyMngtList" style="display:none"></table>
				</form>
				<div class="emdControlPanel">
					<button id="chargerAddBtn">추가</button>
					<button id="edieBtn">편집</button>
					<button id="chargerDeleteBtn">삭제</button>
					<button id="saveBtn">업체정보 저장</button>
				</div>
			</div>
			
			
			<!-- 업체담당자 정보 -->
			<div id="tabs3" class="emdTabPage" style="height:300px; overflow: scroll;">
				<form id="cmpyChargerMngtManageVO">
					<input type="hidden" id="chargerEntrpscd"/>
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">담당자 명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerNm" /></td>
							<th width="20%" height="23" class="required_text">담당자 직위<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="10" id="chargerOfcPos"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">부서<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td colspan="3"><input type="text" size="10" id="chargerDept"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">담당 업무<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input id="chrgJob" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM045" /></td>
							<th width="20%" height="23" class="required_text">관리부서<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td>
								<select class="select" id="mngDeptCd">
									<c:forEach var="result" items="${ogrnztId_result}" varStatus="status">
										<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
									</c:forEach>			  		   
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">휴대폰<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerMoblphonNo" /></td>
							<th width="20%" height="23" class="required_text">전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerTlphonNo"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">팩스<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerFax" /></td>
							<th width="20%" height="23" class="required_text">이메일<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="30" id="chargerEmail"/></td>
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