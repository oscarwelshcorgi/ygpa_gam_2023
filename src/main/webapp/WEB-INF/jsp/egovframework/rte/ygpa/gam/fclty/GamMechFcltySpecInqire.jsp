<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamMechFcltySpecInqire.jsp
  * @Description : 기계시설 제원 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014. 12. 9.		김영길		최초 생성
  *
  * author LFIT
  * since 2014. 12. 9.
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMechFcltySpecInqireModule() {
}
// 초기 시작 창크기 지정
GamMechFcltySpecInqireModule.prototype = new EmdModule(1000,700);

// 페이지가 호출 되었을때 호출 되는 함수
GamMechFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this._fcltyItem = null;
	
	// 테이블 설정
	this.$("#mechFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항구분명",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	this.$("#mechFcltySpecInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});
	
	this.$("#mechFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#mechFcltySpecInqireTab").tabs("option", {active: 1});
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"}
			],
		height: "400"
	});

	this.$("#fcltyFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.imagePreview();
	});
};

GamMechFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

GamMechFcltySpecInqireModule.prototype.loadData = function() {
	this._cmd="";
	this.makeFormValues('#fcltyManageVO', {});
	var searchOpt=this.makeFormArgs("#fcltyForm");
 	this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
 	this.$("#mechFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
};

GamMechFcltySpecInqireModule.prototype.imagePreview = function() {
	var row = this.$('#fcltyFileList').selectedRows();
	row = row[0];

	this.$("#fcltyGisFileForm input").val('');
	this.makeFormValues("#fcltyGisFileForm", row);
	this._editDataFile = this.getFormValues("#fcltyGisFileForm", row);
	this._editRowFile = this.$("#fcltyFileList").selectedRowIds()[0];
	if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {

		// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
		var filenm = row["atchFileNmPhysicl"];
		var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();

		if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){

			$imgURL = this.getPfPhotoUrl(filenm);
		    	this.$("#previewImage").attr("src", $imgURL);
		}else{
			this.$("#previewImage").removeAttr("src");
		}
	}
};

//시설제원데이터 로드
GamMechFcltySpecInqireModule.prototype.loadDetail = function() {
	var row = this.$('#mechFcltySpecInqireList').selectedRows();
	row = row[0];
	
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		this.initDisplay();
		return;
	}
	// 기계시설 제원 처리
	var prtFclty = [{ name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
	this.doAction('/fclty/selectMechFcltySpecInqireDetail.do', prtFclty, function(module, result) {
		if(result.resultCode == "0"){
			module.clearCodePage();
			module._fcltyItem=result.result;
			module.makeDivValues('#fcltyManageVO', result.result);
			module.$("#dispfcltsMngNo").text(result.result["fcltsMngNo"]);
 			module.$("#beforeGisPrtFcltyCd").val(module.$("#gisPrtFcltyCd").val());
 			module.$("#beforeGisPrtFcltySeq").val(module.$("#gisPrtFcltySeq").val());
		}
		module.$("#selectGisPrtFcltyCd").disable();
	});
	
	// 첨부파일 처리
	this.$("#fcltyFileList").flexOptions({params:prtFclty}).flexReload();
	this.clearFilePage();
};

GamMechFcltySpecInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#mechFcltySpecInqireList').selectedRowCount()!=1) {
			alert('기계시설 항목을 선택 하세요.');
			return false;
		}
	}

	return true;
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMechFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	
		// 파일 다운로드
		case 'btnDownloadFile':
			this.downloadFile();
		break;
	
		// 위치 조회
		case "gotoLocation":	
			this.gotoLocation();
		break;
		
		// 검색조건 시설물 관리 그룹 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;
	}
};

GamMechFcltySpecInqireModule.prototype.downloadFile = function() {
	var selectRow = this.$('#fcltyFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

GamMechFcltySpecInqireModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamMechFcltySpecInqireModule.prototype.clearFilePage = function() {
	this.$('#fcltyFileList').flexEmptyData();
	this.$('#fcltyGisFileForm :input[type=text]').val('');
	this.$('#previewImage').attr('src', '');
};

/**
 * 탭 변경시 실행 이벤트
 */
 GamMechFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.loadDetail();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);
			}
			if(this._cmd != 'modify') {
				this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
				alert('기계시설 항목을 선택 하세요.');
			} 
			break;
		case "tabs3":
			if(this._cmd != 'modify') {
				this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
				alert('기계시설 항목을 선택 하세요.');
			} 
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
 GamMechFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	 switch(popupId){
		// 상세화면
		case "selectGisCode":
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode2").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명
			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번
		break;
		
		// 검색조건 시설물 관리 그룹 
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		case "selectFcltsClCd":
			this.$("#archFcltsClCd").val(value["fcltsClCd"]);
			this.$("#archFcltsClCdNm").val(value["fcltsClCdNm"]);			
			break;
			
		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
		
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMechFcltySpecInqireModule();
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
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14"/>
								<input id="sFcltsMngGroupNoNm" type="text" size="59" disabled="disabled"/>
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM058" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30"/></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30"/></td>							
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="mechFcltySpecInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">기계시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">기계시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">기계시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="mechFcltySpecInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="mechFcltySpecInqireList" data-style="default">맵조회</button>
				</div>
			</div>

			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">

				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">기계시설 일반</th>
								<th>
									시설물관리번호 : <span id="dispfcltsMngNo"></span>
									<input type="hidden" id="fcltsMngNo" />
								</th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항코드</th>
							<td>
								<span id="gisAssetsPrtAtCode"></span>&nbsp;&nbsp;&nbsp;
								<span id="gisAssetsPrtAtName"></span>
							</td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"></span>-
								<span id="gisAssetsSubCd"></span>-
								<span id="gisAssetsPrtAtCode2"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td>
								<span id="gisAssetsNm"></span>
							<th width="12%" height="17" class="required_text">지번</th>
							<td>
								<span id="gisAssetsLnm"></span>&nbsp;-&nbsp;
								<span id="gisAssetsLnmSub"></span>
							</td>
							<th width="12%" height="17" class="required_text">소재지</th>
							<td>
								<span id="gisAssetsLocplc"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설코드</th>
							<td>
								<span id="gisPrtFcltyCd"></span>&nbsp;-&nbsp;
								<span id="gisPrtFcltySeq"></span>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
								<span class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM058" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"></span>
							</td>
							<th width="12%" height="17" class="required_text">기계시설명</th>
							<td>
								<span id="prtFcltyNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo"></span>
								<span id="fcltsMngGroupNoNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td colspan="5">
								<span id="prtFcltyInstlDt"></span>
							</td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>기계시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">품명</th>
							<td colspan="5">
								<span id="gdsnm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">용도</th>
							<td colspan="5">
								<span id="prpos"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">형식</th>
							<td colspan="5">
								<span id="fmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">규격</th>
							<td colspan="5">
								<input id="instlDt" type="hidden"/>
								<span id="stndrd"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사용시작일자</th>
							<td>
								<span id="usageBeginDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">사용종료일자</th>
							<td>
								<span id="usageEndDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">상태등급</th>
							<td>
								<span id="sttusLvl"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제작회사</th>
							<td colspan="5">
								<span id="mfcCmpny"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제작일자</th>
							<td>
								<span id="mfcDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">장비번호</th>
							<td colspan="3">
								<span id="eqpmnNo"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">관리자</th>
							<td colspan="5">
								<span id="manager"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사용업체</th>
							<td colspan="5">
								<span id="usageEntrps"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">운영회사</th>
							<td colspan="5">
								<span id="operCmpny"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">길이</th>
							<td>
								<span id="lt"></span>
							</td>
							<th width="12%" height="17" class="required_text">수량</th>
							<td>
								<span id="qy"></span>
							</td>
							<th width="12%" height="17" class="required_text">단위</th>
							<td>
								<span id="unit"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소유주체</th>
							<td>
								<span id="posesnMainbd"></span>
							</td>
							<th width="12%" height="17" class="required_text">취득금액</th>
							<td colspan="3">
								<span id="acqAmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">검사시작일자</th>
							<td>
								<span id="examBeginDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">검사종료일자</th>
							<td>
								<span id="examEndDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">검사합격번호</th>
							<td>
								<span id="examOkNo"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">검사기관</th>
							<td colspan="5">
								<span id="examInstt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">하역능력</th>
							<td colspan="3">
								<span id="lnlAblty"></span>
							</td>
							<th width="12%" height="17" class="required_text">내용년수</th>
							<td>
								<span id="cnyear"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">자중</th>
							<td colspan="5">
								<span id="selfLoad"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정격하중</th>
							<td colspan="5">
								<span id="rateWght"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">최대바퀴하중</th>
							<td colspan="5">
								<span id=""></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">레일간격</th>
							<td colspan="5">
								<span id="railItv"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정격마력</th>
							<td colspan="5">
								<span id=""></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">처리능력</th>
							<td colspan="5">
								<span id="processAblty"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">적재톤수</th>
							<td colspan="5">
								<span id="capaTon"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">강제중량</th>
							<td colspan="5">
								<span id="structWqnt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colspan="5">
								<span id="loc"></span>
							</td>
						</tr>
						<tr>							
							<th width="12%" height="17" class="required_text">기계시설물분류코드</th>
							<td colspan="5">
								<span id=""></span>
								<span id=""></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="5">
								<span id="archFcltsMngNo"></span>
								<span id="archFcltsMngNoNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">흘수</th>
							<td colspan="5">
								<span id="draft"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사용목적</th>
							<td colspan="5">
								<span id="usagePurps"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colspan="5">
								<span id="rm"></span>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>
			
			<!-- 기계시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table>
					<tr>
						<td width="50%">
							<table id="fcltyFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnDownloadFile">다운로드</button>
							</div>
						</td>
						<td style="text-align:center;vertical-align:middle;">
							<img id="previewImage" style="border: 1px solid #000; max-width:300px; max-height: 300px" src="">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>