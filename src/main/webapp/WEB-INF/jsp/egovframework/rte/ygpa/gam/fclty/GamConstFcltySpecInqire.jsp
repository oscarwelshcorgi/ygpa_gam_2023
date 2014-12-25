<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamConstFcltySpecInqire.jsp
  * @Description : 건축시설 제원 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014. 12. 5.		김영길		최초 생성
  *
  * author LFIT
  * since 2014.12.5
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyManageVO" method="validateFcltyManageVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamConstFcltySpecInqireModule() {
}

GamConstFcltySpecInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamConstFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this._fcltyItem = null;
	
	// 테이블 설정
	this.$("#constFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항분류",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this.$("#constFcltySpecInqireList").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd="modify";
	});

	this.$("#constFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd="modify";
		module.$("#constFcltySpecInqireListTab").tabs("option", {active: 1});	// 탭을 전환 한다.
	});


	this.$("#selectedGAM005").on("change", {module: this}, function(event) {
		event.data.module.$("#gisPrtFcltyCd").val($(this).val());
	});

	this.$("#fcltyFileList").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",				width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",			width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",				width:240,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:200,		sortable:true,		align:"left"}
			],
		height: "auto"
	});

	this.$("#fcltyFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.imagePreview();
	});
};

GamConstFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

GamConstFcltySpecInqireModule.prototype.loadData = function() {
	this._cmd="";
	this.makeFormValues('#fcltyManageVO', {});
	var searchOpt=this.makeFormArgs("#fcltyForm");
 	this.$("#constFcltySpecInqireListTab").tabs("option", {active: 0});
 	this.$("#constFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
};

GamConstFcltySpecInqireModule.prototype.imagePreview = function() {
	
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
			//this.$("#previewImage").fadeIn(400, function() {
		    	this.$("#previewImage").attr("src", $imgURL);
		    //});
		}else{
			this.$("#previewImage").removeAttr("src");
		}
	}
};

GamConstFcltySpecInqireModule.prototype.loadDetail = function() {
	var row = this.$('#constFcltySpecInqireList').selectedRows();
	row = row[0];
	
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#constFcltySpecInqireListTab").tabs("option", {active: 0});
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		this.initDisplay();
		return;
	}
	// 건축시설 제원 처리
	var prtFclty = [{ name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
	this.doAction('/fclty/gamConstFcltySpecInqireDetail.do', prtFclty, function(module, result) {
 		if(result.resultCode == "0"){
 			module.clearCodePage();
 			module._fcltyItem=result.result;
 			module.makeDivValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
 			module.$("#titlefcltsInqireNo").text(result.result["fcltsMngNo"]);	// 결과값을 채운다.

 			module.$("#beforeGisPrtFcltyCd").val(specModule.$("#gisPrtFcltyCd").val());
 			module.$("#beforeGisPrtFcltySeq").val(specModule.$("#gisPrtFcltySeq").val());
 		}
 		module.$("#gisCodePopupBtn").hide();
 		module.$("#selectedGAM005").disable();
 	});

	// 첨부파일 처리
	this.$('#fcltyFileList').flexOptions({params:prtFclty}).flexReload();
	this.clearFilePage();

};

GamConstFcltySpecInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#constFcltySpecInqireList').selectedRowCount()!=1) {
			alert('건축시설 항목을 선택 하세요.');
			return false;
		}
	}

	return true;
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamConstFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
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

GamConstFcltySpecInqireModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamConstFcltySpecInqireModule.prototype.clearFilePage = function() {
	this.$('#fcltyFileList').flexEmptyData();
	this.$('#fcltyGisFileForm :input[type=text]').val('');
	this.$('#previewImage').attr('src', '');
};

/**
 * 탭 변경시 실행 이벤트
 */
 GamConstFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
				this.$("#constFcltySpecInqireListTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
			} 
			break;
	
		case "tabs3":
			if(this._cmd != 'modify') {
				this.$("#constFcltySpecInqireListTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
			} 
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
 GamConstFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		// 상세화면
		case "searchGisCodePopup":
			this.$("#gisAssetsPrtAtCodeStr").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtCode").val(value["gisAssetsPrtAtCode"]);
			this.$("#gisAssetsPrtAtName").val(value["gisAssetsPrtAtCodeNm"]);
			this.$("#gisAssetsSubCd").val(value["gisAssetsSubCd"]);				// GIS SUB자산코드
			this.$("#gisAssetsCd").val(value["gisAssetsCd"]);					// GIS 자산코드
			this.$("#gisAssetsNm").val(value["gisAssetsNm"]);					// GIS 자산명

			this.$("#gisAssetsLocplc").val(value["gisAssetsLocplc"]); 			// 소재지
			this.$("#gisAssetsLnm").val(value["gisAssetsLnm"]);					// 지번
			this.$("#gisAssetsLnmSub").val(value["gisAssetsLnmSub"]);			// 서브지번

			if(this._cmd!="insert") alert('변경된 내용은 페이지를 새로고침을 해야 반영 됩니다.');
		break;

		// 검색조건 시설물 관리 그룹 
		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		// 업체조회화면
		case "searchEntrpsCdPopup":
			this.$("#prtFcltyMngEntrpsCd").val(value["entrpscd"]);
			this.$("#prtFcltyMngEntrpsNm").val(value["entrpsNm"]);
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
var module_instance = new GamConstFcltySpecInqireModule();
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
							<td><input id="prtFcltySe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14" title="시설물관리그룹넘버" />&nbsp;-&nbsp;
								<input id="sFcltsMngGroupNoNm" type="text" size="56" title="시설물관리그룹명" disabled="disabled" />
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" title="시설명"  /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" title="소재지"  /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="constFcltySpecInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">건축시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">건축시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">건축시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="constFcltySpecInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="constFcltySpecInqireList" data-style="default">맵조회</button>
				</div>
			</div>

			<!-- 건축시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: auto;">
				<form id="fcltyManageVO">
				<input type="hidden" id="beforeGisPrtFcltyCd">
          		<input type="hidden" id="beforeGisPrtFcltySeq">
          		<input type="hidden" id="fcltsMngNo">

				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">건축시설 일반</th>
								<th>시설물관리번호 : <span id="titlefcltsInqireNo"></span></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항코드</th>
							<td>
								<span id="gisAssetsPrtAtCodeStr"></span>&nbsp;&nbsp;&nbsp;
								<span id="gisAssetsPrtAtName"></span>
							</td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"></span>-
								<span id="gisAssetsSubCd"></span>-
								<span id="gisAssetsPrtAtCode"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td>
								<span id="gisAssetsNm"></span>
							</td>
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
								<span class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" id="selectedGAM005" data-required="true" data-column-id="gisPrtFcltyCd" />
								<input type="hidden" id="prtFcltySeNm"/>
								
							</td>
							<th width="12%" height="17" class="required_text">건축시설명</th>
							<td>
								<span id="prtFcltyNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
								<span id="fcltsMngGroupNo"></span>&nbsp;&nbsp;&nbsp;
								<span id="fcltsMngGroupNoNm"></span>
							</td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>건축시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">준공일자</th>
							<td>
								<span id="bldDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">구조형식</th>
							<td colspan="3">
								<span id="strctFmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">하자만료일자</th>
							<td>
								<span id="flawEndDt"></span>
							</td>
							<th width="12%" height="17" class="required_text">기초형식</th>
							<td colspan="3">
								<span id="baseFmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">연면적</th>
							<td>
								<span id="ar"></span>
							</td>
							<th width="12%" height="17" class="required_text">건축면적</th>
							<td>
								<span id="archAr"></span>
							</td>
							<th width="12%" height="17" class="required_text">대지면적</th>
							<td>
								<span id="plotAr"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주사용용도</th>
							<td colspan="5">
								<span id="mainUsagePrpos"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차면적</th>
							<td>
								<span id="prkAr"></span>
							</td>
							<th width="12%" height="17" class="required_text">옥내주차면적</th>
							<td>
								<span id="isdPrkAr"></span>
							</td>
							<th width="12%" height="17" class="required_text">옥외주차면적</th>
							<td>
								<span id=""></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주차대수</th>
							<td>
								<span id="prkCnt"></span>
							</td>
							<th width="12%" height="17" class="required_text">옥내주차대수</th>
							<td>
								<span id="isdPrkCnt"></span>
							<th width="12%" height="17" class="required_text">옥외주차대수</th>
							<td>
								<span id="osdPrkCnt"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">배기닥트유무</th>
							<td>
								<span id="exhaustDuctEnnc"></span>
							<th width="12%" height="17" class="required_text">환기공조방식</th>
							<td colspan="3">
								<span id="vntltnArcndtMthd"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수승객용</th>
							<td>
								<span id="liftCntPsngr"></span>
							<th width="12%" height="17" class="required_text">물탱크위치</th>
							<td colspan="3">
								<span id="wrtTankLoc"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수화물용</th>
							<td>
								<span id="liftCntCargo"></span>
							<th width="12%" height="17" class="required_text">변전실위치</th>
							<td colspan="3">
								<span id="sbtLoc"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기대수비상용</th>
							<td>
								<span id="liftCntEmgcy"></span>
							<th width="12%" height="17" class="required_text">유류저장시설위치</th>
							<td colspan="3">
								<span id="oilSavefcltyLoc"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">냉방유무</th>
							<td>
								<span id="clngEnnc"></span>
							<th width="12%" height="17" class="required_text">냉방열원</th>
							<td>
								<span id="clngSrc"></span>
							<th width="12%" height="17" class="required_text">오수정화시설위치</th>
							<td colspan="3">
								<span id="swgClupfcltyLoc"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">승강기운영방식</th>
							<td colspan="5">
								<span id="liftOperMthd"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">난방유무</th>
							<td>
								<span id="htngEnnc"></span>
							<th width="12%" height="17" class="required_text">난방열원</th>
							<td colspan="3">
								<span id="htngSrc"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">전기인입용량</th>
							<td>
								<span id="elctyLeadInCapa"></span>
							<th width="12%" height="17" class="required_text">정화조형식</th>
							<td colspan="3">
								<span id="spictankFmt"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">외장</th>
							<td colspan="5">
								<span id="fcg"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">내장</th>
							<td colspan="5">
								<span id="itr"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">천장</th>
							<td colspan="5">
								<span id="ceil"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕</th>
							<td colspan="5">
								<span id="roof"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">지붕방수</th>
							<td colspan="5">
								<span id="roofWtprf"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계공사명</th>
							<td colspan="5">
								<span id="planCnstNm"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계수행회사</th>
							<td colspan="5">
								<span id="planExcCmpny"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계시작일자</th>
							<td>
								<span id="planBeginDt"></span>
							<th width="12%" height="17" class="required_text">설계종료일자</th>
							<td>
								<span id="planEndDt"></span>
							<th width="12%" height="17" class="required_text">시공시작일자</th>
							<td>
								<span id="cnstrctBeginDt"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공공사명</th>
							<td colspan="5">
								<span id="cnstrctCnstNm"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공수행회사</th>
							<td colspan="5">
								<span id="cnstrctExcCmpny"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시공종료일자</th>
							<td>
								<span id="cnstrctEndDt"></span>
							<th width="12%" height="17" class="required_text">건축시설물분류코드</th>
							<td colspan="3">
								<span id="archFcltsClCd"></span>&nbsp;&nbsp;&nbsp;
								<span id="archFcltsClCdNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">비고</th>
							<td colSpan="5">
								<span id="rm"></span>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colSpan="5">
								<span id="loc"></span>
						</tr>

					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>
			
			<!-- 건축시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="fcltyFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnDownloadFile">다운로드</button>
				</div>

				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>