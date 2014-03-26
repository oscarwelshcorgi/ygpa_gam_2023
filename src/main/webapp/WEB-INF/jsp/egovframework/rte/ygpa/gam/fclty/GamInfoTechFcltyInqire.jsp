<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamInfoTechInqire.jsp
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
		//usepager: true,
		//useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "230"
	});
	
	this.$("#fcltyMngtList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltyManageVO :input").val("");
		module.makeFormValues("#fcltyManageVO", row);
		module.getFormValues("#fcltyManageVO", row);
		module.$("#fcltyMngtList").selectedRowIds()[0];
		module.$("#cmd").val("modify");
		
        var searchOpt=module.makeFormArgs("#fcltyManageVO");
        module.$("#fcltyPhotoList").flexOptions({params:searchOpt}).flexReload();
	});
	
	this.$("#fcltyMngtList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.$("#fcltyMngtListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
	});
	
	this.$("#fcltyPhotoList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamInfoTechFcltyPhotoList.do"/>',
		dataType: 'json',
		colModel : [
				{display:"사진 순번",			name:"prtFcltyPhotoSeq",	width:80,		sortable:true,		align:"center"},
				{display:"사진 제목",			name:"photoSj",				width:300,		sortable:true,		align:"center"},
				{display:"파일명",			name:"filenmLogic",			width:200,		sortable:true,		align:"center"},
				{display:"파일명",			name:"filenmPhysicl",			width:200,		sortable:true,		align:"center"},
				{display:"파일 설명",			name:"photoDesc",			width:200,		sortable:true,		align:"center"},
				{display:"촬영 일시",			name:"shotDt",				width:120,		sortable:true,		align:"center"}
			],
		showTableToggleBtn: false,
		height: "160"
	});

	
	this.$("#fcltyPhotoList").on("onItemSelected", function(event, module, row, grid, param) {
		
		module.makeFormValues("#fcltyGisPhotoForm", row);
		module._editDataFile = module.getFormValues("#fcltyGisPhotoForm", row);
		module._editRowFile = module.$("#fcltyPhotoList").selectedRowIds()[0];

		if(row.filenmPhysicl != null || row.filenmPhysicl != "") {
			
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["filenmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
			    
				$imgURL = module.getImageUrl(filenm);
				module.$("#previewImage").fadeIn(400, function() {
			    	module.$("#previewImage").attr("src", $imgURL);
			    });
			}else{
				module.$("#previewImage").attr(src, "#");
			}
		}
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
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup2", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
		break;

		case "btnAddGisMap":
			if(this.$("#assetCodeList").selectedRowIds().length>0) {
				var row = this.$("#erpAssetCodeList").selectedRows();
				this.addGisAssetsCdMap("GAC", {"gisPrtAtCode": "620", "gisAssetsCd": "LNF", "gisAssetsSubCd": "01"});
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
		break;
	case "tabs3":
		break;
	}
};


/**
 * 팝업 close 이벤트
 */
GamFcltyMngtModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		
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
					<input type="hidden" id="cmd" />
					<table class="searchPanel">
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산 코드</th>
							<td colspan="3">
								<input type="text" size="8" id="gisAssetsCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="6" id="gisAssetsSubCd" disabled="disabled"/>&nbsp;-&nbsp;
								<input type="text" size="8" id="gisAssetsPrtAtCode" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">GIS 자산명</th>
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
							<th width="20%" height="23" class="required_text">정보통신시설 코드</th>
							<td colspan="3">
								<input type="text" size="10" id="gisPrtFcltyCd" disabled="disabled" />&nbsp;-&nbsp;<input type="text" size="20" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">정보통신시설 명</th>
							<td colspan="3"><input type="text" size="80" id="prtFcltyNm" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">정보통신시설 규격</th>
							<td><input type="text" size="30" id="prtFcltyStndrd" disabled="disabled" /></td>
							<th width="20%" height="23" class="required_text">단위</th>
							<td><input type="text" size="30" id="prtFcltyUnit" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관리 업체</th>
							<td colspan="3">
								<input type="text" size="10" id="prtFcltyMngEntrpsCd" disabled="disabled"/>
								<input type="text" size="20" id="prtFcltyMngEntrpsNm" disabled="disabled"/>&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설치일자</th>
							<td colspan="3"><input id="prtFcltyInstlDt" type="text" size="20" title="설치일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">변경일자</th>
							<td colspan="3"><input id="prtFcltyChangeDt" type="text" size="20" title="변경일자" disabled="disabled" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="insertGIS">GIS 등록</button>
					<button id="searchPosition">위치조회</button>
				</div>
			</div>
			
			<!-- 정보통신시설 사진 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;" data-onactivate="onShowTab3Activate">
				<table id="fcltyPhotoList" style="display:none"></table>
				<form id="fcltyGisPhotoForm">
					<table>
						<tr>
							<th><span class="label">제 목</span></th>
							<td><input id="photoSj" type="text" size="60" class="photoEditItem" disabled="disabled" /></td>
						</tr>
						<tr>
							<th><span class="label">촬영일자</span></th>
							<td><input id="shotDt" type="text" size="10" class="photoEditItem" disabled="disabled" /></td>
						</tr>
						<tr>
							<th><span class="label">사진 설명</span></th>
							<td><input id="photoDesc" type="text" size="60"  class="photoEditItem" disabled="disabled" /></td>
						</tr>
					</table>
				</form>
				<div class="emdPanel fillHeight" style="overflow:scroll"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>