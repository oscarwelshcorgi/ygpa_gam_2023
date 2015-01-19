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
 *   수정일         수정자             수정내용
 *  -------    --------    ---------------------------
 *  2014.12.9	김영길		최초 생성
 *
 * author 김영길
 * since 2014.12.9
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>
<%
/**
 * @FUNCTION NAME : GamMechFcltySpecInqireModule
 * @DESCRIPTION   : MODULE 고유 함수 (아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.)
 * @PARAMETER     : NONE
**/
%>
function GamMechFcltySpecInqireModule() {}

// 초기 시작 창크기 지정
GamMechFcltySpecInqireModule.prototype = new EmdModule(1000,700);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamMechFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this._fcltyItem = null;
	
	// 테이블 설정
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"loc",					width:180,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:120,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}		
	});
	
	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});
	
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#selectGisPrtFcltyCd").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNoNm();
	});
	
	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:200,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",			width:180,		sortable:true,		align:"left"}
			],
		height: "400"
	});

	this.$("#fcltyFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.imagePreview();
	});
};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamMechFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamMechFcltySpecInqireModule.prototype.loadData = function() {
	this._cmd="";
	this.makeFormValues('#fcltyManageVO', {});
	var searchOpt=this.makeFormArgs("#fcltyForm");
 	this.$("#mainTab").tabs("option", {active: 0});
 	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
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

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamMechFcltySpecInqireModule.prototype.loadDetail = function() {
	var row = this.$('#mainGrid').selectedRows();
	row = row[0];
	
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#mainTab").tabs("option", {active: 0});
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
			module.makeFormValues('#fcltyManageVO', result.result);
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

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : Excel Download
 * @PARAMETER     :
**/
%>
GamMechFcltySpecInqireModule.prototype.downloadExcel = function() {
	var rowCount = this.$("#mainGrid").flexRowCount();
	if (rowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fclty/excelDownloadMechFcltySpecInqireList.do');
};

GamMechFcltySpecInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#mainGrid').selectedRowCount()!=1) {
			alert('기계시설 항목을 선택 하세요.');
			return false;
		}
	}

	return true;
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
 GamMechFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {

		// 엑셀 다운로드
		case "btnExcelDownload": 
			this.downloadExcel();
			break;
	
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

GamMechFcltySpecInqireModule.prototype.gotoLocation = function() {
	if(this._fcltyItem.laCrdnt!=null && this._fcltyItem.laCrdnt!=null) {
		EMD.gis.goLocation(this._fcltyItem.laCrdnt, this._fcltyItem.loCrdnt);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else if(this._fcltyItem.lat!=null && this._fcltyItem.lng!=null){
		EMD.gis.goLocation4326(this._fcltyItem.lat, this._fcltyItem.lng);
		EMD.gis.selectPrtFclty(this._fcltyItem);
	} else {
		alert("시설위치가 등록되지 않았습니다.");
	}
};

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamMechFcltySpecInqireModule.prototype.getFcltsMngGroupNoNm = function() {
	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNoNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
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
				this.$("#mainTab").tabs("option", {active: 0});
				alert('기계시설 항목을 선택 하세요.');
			} 
			break;
		case "tabs3":
			if(this._cmd != 'modify') {
				this.$("#mainTab").tabs("option", {active: 0});
				alert('기계시설 항목을 선택 하세요.');
			} 
			break;
	}
};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
 GamMechFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	 switch(popupId){
		// 검색조건 시설물 관리 그룹 
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;
		
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMechFcltySpecInqireModule();
</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

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
								<input id="sFcltsMngGroupNo" type="text" size="14"/>&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" disabled="disabled"/>
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
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">기계시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">기계시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">기계시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
	                                <button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="civilFcltySpecMngList" data-style="default">맵조회</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">기계시설 일반</th>
								<th>시설물관리번호 : <span id="dispfcltsMngNo"></span><input type="hidden" id="fcltsMngNo" /></th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel" style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항　　구　　분</th>
							<td><input type="text" size="5" id="gisAssetsPrtAtCode" disabled="disabled"/>  <input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="2" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="3" id="gisAssetsPrtAtCode2" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
							<td><input type="text" size="30" id="gisAssetsNm" disabled="disabled"/></td>
							<th width="12%" height="17" class="required_text">지　　　　　번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="4" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="7" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td><input id="gisAssetsLocplc" type="text" size="30" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />-
								<input type="text" size="25" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17" class="required_text">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM058" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd" disabled="disabled" />
								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">시　　설　　명</th>
							<td><input type="text" size="30" id="prtFcltyNm" maxlength="80" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="61" id="fcltsMngGroupNoNm" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설　치　일　자</th>
							<td colspan="5"><input id="prtFcltyInstlDt" type="text" class="emdcal" size="11" title="설치일자" disabled="disabled" /></td>
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
							<th width="12%" height="17" class="required_text">품　　　　　명</th>
							<td><input id="gdsnm" type="text" size="50" maxlength="150" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">용　　　　　도</th>
							<td><input id="prpos" type="text" size="50" maxlength="100" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">형　　　　　식</th>
							<td><input id="fmt" type="text" size="50" maxlength="100" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">규　　　　　격</th>
							<td>
								<input id="stndrd" type="text" size="50" maxlength="50" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">길　　　　　이</th>
							<td><input id="lt" type="text" size="50" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">수　　　　　량</th>
							<td><input id="qy" type="text" size="50" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">단　　　　　위</th>
							<td><input id="unit" type="text" size="50" maxlength="20" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">흘　　　　　수</th>
							<td><input id="draft" type="text" size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">장　비　번　호</th>
							<td><input id="eqpmnNo" type="text" size="50" maxlength="50" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">상　태　등　급</th>
							<td colspan="3"><input id="sttusLvl" type="text" size="50" maxlength="1" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">제　작　회　사</th>
							<td><input id="mfcCmpny" type="text" size="50" maxlength="100" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">제　작　일　자</th>
							<td><input id="mfcDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">하　역　능　력</th>
							<td><input id="lnlAblty" type="text" size="50" maxlength="30" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">내　용　년　수</th>
							<td><input id="cnyear" type="text" size="50" maxlength="20" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">자　　　　　중</th>
							<td><input id="selfLoad" type="text" size="50" maxlength="200" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">레　일　간　격</th>
							<td><input id="railItv" type="text" size="50" maxlength="200" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정　격　하　중</th>
							<td><input id="rateWght" type="text" size="50" maxlength="200" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">최　대바퀴하중</th>
							<td><input id="maxWheelWght" type="text" size="50" maxlength="200" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">적　재　톤　수</th>
							<td><input id="capaTon" type="text" size="50" maxlength="100" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">강　제　중　량</th>
							<td><input id="structWqnt" type="text" size="50" maxlength="100" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">정　격　마　력</th>
							<td><input id="rateHp" type="text" size="50" maxlength="200" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">처　리　능　력</th>
							<td><input id="processAblty" type="text" size="50" maxlength="100" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사　용　업　체</th>
							<td><input id="usageEntrps" type="text" size="50" maxlength="100" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">운　영　회　사</th>
							<td><input id="operCmpny" type="text" size="50" maxlength="100" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사　용　목　적</th>
							<td><input id="usagePurps" type="text" size="50" maxlength="200" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">관　　리　　자</th>
							<td><input id="manager" type="text" size="50" maxlength="60" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">사용　시작일자</th>
							<td><input id="usageBeginDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">사용　종료일자</th>
							<td><input id="usageEndDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소　유　주　체</th>
							<td><input id="posesnMainbd" type="text" size="50" maxlength="1" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">취　득　금　액</th>
							<td><input id="acqAmt" type="text" size="50" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">검　사　기　관</th>
							<td><input id="examInstt" type="text" size="50" maxlength="100" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">검사　합격번호</th>
							<td><input id="examOkNo" type="text" size="50" maxlength="10" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">검사　시작일자</th>
							<td><input id="examBeginDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
							<th width="12%" height="17" class="required_text">검사　종료일자</th>
							<td><input id="examEndDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
						</tr>
						<tr>							
							<th width="12%" height="17" class="required_text">시설물분류코드</th>
							<td colspan="3">
								<input id="mechFcltsClCd" type="text" size="20" disabled="disabled" />
								<input id="mechFcltsClCdNm" type="text" size="28" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">건축시설물관리번호</th>
							<td colspan="3">
								<input id="archFcltsMngNo" type="text" size="20" disabled="disabled" />
								<input id="archFcltsMngNoNm" type="text" size="28" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소　　재　　지</th>
							<td colspan="3"><input id="loc" type="text" size="135" maxlength="150" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비　　　　　고</th>
							<td colspan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="1000" disabled="disabled"></textarea>
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