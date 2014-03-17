<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmpyInfoList.jsp
  * @Description : 업체정보관리조회 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.03.17  kok          최초 생성
  *
  * author kok
  * since 2014.03.17
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
					{display:"업체코드", 		name:"entrpscd",		width:100, 	sortable:false,		align:"center"},
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
		//useRp: true,
		//rp: 13,
		showTableToggleBtn: false,
		height: "250"
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

			var searchOpt = module.makeFormArgs("#cmpyInfoMngtManageVO");
			module.$("#cmpyMngtList").flexOptions({params:searchOpt}).flexReload();
			module.$("#cmpyInfoMngtListTab").tabs("option", {active: 1});
			
			module.$("#entrpscd").val(result.detail.entrpscd);										// 업체ID(hidden)
			module.$("#entrpscdSpan").text(result.detail.entrpscd);										// 업체ID(hidden)
			module.$("#entrpsNm").text(result.detail.entrpsNm);										// 업체명
			module.$("#bizrno").text(result.detail.bizrno);											// 사업자등록번호
			module.$("#entrpsTy").val(result.detail.entrpsTy).attr("selected","selected");			// 업체유형
			//alert(module.$("#entrpsTy option:selected").text());
			module.$("#bsnmSe").val(result.detail.bsnmSe).attr("selected","selected");				// 사업자구분
			module.$("#cprregistno").text(result.detail.cprregistno);								// 법인등록번호
			module.$("#induty").text(result.detail.induty);											// 업종
			module.$("#bizcnd").text(result.detail.bizcnd);											// 업태
			module.$("#tlphonNo").text(result.detail.tlphonNo);										// 전화번호
			module.$("#fax").text(result.detail.fax);												// 팩스
			module.$("#zip").text(result.detail.zip);												// 우편번호
			module.$("#adres").text(result.detail.adres);											// 주소
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
		//useRp: true,
		//rp: 13,
		showTableToggleBtn: false,
		height: "100"
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
			var searchOpt = this.makeFormArgs("#cmpyInfoMngtForm");
		 	this.$("#cmpyInfoMngtList").flexOptions({params:searchOpt}).flexReload();
		 	this.$("#cmpyInfoMngtListTab").tabs("option", {active: 0});
		break;
	
		// 업체조회 팝업
		case "searchEntrpsCdBtn":
			this.doExecuteDialog("searchEntrpsCdPopup", "업체조회", '<c:url value="/popup/showEntrpsInfo.do"/>', {});
		break;
	}
};


GamCmpyInfoMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;
	
		case "tabs2":
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
			</ul>
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
				<table id="cmpyInfoMngtList" style="display:none" class="fillHeight"></table>
			</div>

			<div id="tabs2" class="emdTabPage" style="height:300px; overflow: scroll;" data-onactivate="onShowTab2Activate">
				<form id="cmpyInfoMngtManageVO">
				<input type="hidden" id="entrpscd" />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">업체 코드</th>
							<td><span id="entrpscdSpan"></span></td>
							<th width="20%" height="23" class="required_text">대표자 명</th>
							<td><input type="text" size="30" id="rprsntvNm" maxlength="20" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 명</th>
							<td><span id="entrpsNm"></span></td>
							<th width="20%" height="23" class="required_text">사업자등록번호</th>
							<td><span id="bizrno"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업체 유형</th>
							<td><input id="entrpsTy" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM020" disabled="disabled"/></td>
							<th width="20%" height="23" class="required_text">사업자구분</th>
							<td><input id="bsnmSe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM018" disabled="disabled"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">법인등록번호</th>
							<td colspan="3"><span id="cprregistno"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">업종</th>
							<td><span id="induty"></span></td>
							<th width="20%" height="23" class="required_text">업태</th>
							<td><span id="bizcnd"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">전화번호</th>
							<td><span id="tlphonNo"></span></td>
							<th width="20%" height="23" class="required_text">팩스</th>
							<td><span id="fax"></span></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">우편번호</th>
							<td><span id="zip"></span></td>
							<th width="20%" height="23" class="required_text">주소</th>
							<td><span id="adres"></span></td>
						</tr>
					</table>
					<br />
					<table id="cmpyMngtList" style="display:none" class="fillHeight"></table>
				</form>
			</div>
		</div>
	</div>
</div>