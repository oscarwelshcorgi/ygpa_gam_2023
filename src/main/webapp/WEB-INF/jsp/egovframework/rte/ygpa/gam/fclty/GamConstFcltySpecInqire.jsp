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
 *   수정일          수정자             수정내용
 *  -------    --------    ---------------------------
 *  2014.12.5	김영길		최초 생성
 *
 * author 김영길
 * since 2014.12.5
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
 * @FUNCTION NAME : GamConstFcltySpecInqireModule
 * @DESCRIPTION   : MODULE 고유 함수 (아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.)
 * @PARAMETER     : NONE
**/
%>
function GamConstFcltySpecInqireModule() {}

// 초기 시작 창크기 지정
GamConstFcltySpecInqireModule.prototype = new EmdModule(1000,700);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamConstFcltySpecInqireModule.prototype.loadComplete = function(params) {

	this._fcltyItem = null;
	
	// 테이블 설정
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/fclty/gamConstFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항분류",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:180,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:200,		sortable:false,		align:"left"},
					{display:"소재지",		name:"loc",					width:170,		sortable:false,		align:"left"},
					{display:"시설물관리그룹",	name:"fcltsMngGroupNoNm",	width:150,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:80,		sortable:false,		align:"center"},
					{display:"준공일자",		name:"bldDt",				width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this.$("#mainGrid").on("onItemSelected", function(event, module, row, grid, param) {
		module._cmd="modify";
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd="modify";
		module.$("#mainTab").tabs("option", {active: 1});	// 탭을 전환 한다.
	});

	this.$("#sFcltsMngGroupNo").bind("keyup change", {module: this}, function(event) {
		event.data.module.getFcltsMngGroupNoNm();
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
					{display:"파일제목",	name:"atchFileSj",				width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",			width:180,		sortable:true,		align:"left"}
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
GamConstFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamConstFcltySpecInqireModule.prototype.loadData = function() {
	this._cmd="";
	this.makeFormValues('#fcltyManageVO', {});
	var searchOpt=this.makeFormArgs("#fcltyForm");
 	this.$("#mainTab").tabs("option", {active: 0});
 	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
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
GamConstFcltySpecInqireModule.prototype.loadDetail = function() {
	var row = this.$('#mainGrid').selectedRows();
	row = row[0];
	
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#mainTab").tabs("option", {active: 0});
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
 			console.log(result.result);
 			module.makeFormValues('#fcltyManageVO', result.result);	// 결과값을 채운다.
 			module.$("#titleFcltsMngNo").text(result.result["fcltsMngNo"]);	// 결과값을 채운다.
 			module.$("#beforeGisPrtFcltyCd").val(module.$("#gisPrtFcltyCd").val());
 			module.$("#beforeGisPrtFcltySeq").val(module.$("#gisPrtFcltySeq").val());
 		}
 		module.$("#selectedGAM005").disable();
 	});

	// 첨부파일 처리
	this.$('#fcltyFileList').flexOptions({params:prtFclty}).flexReload();
	this.clearFilePage();
};

GamConstFcltySpecInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {

	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#mainGrid').selectedRowCount()!=1) {
			alert('건축시설 항목을 선택 하세요.');
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
			this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', null);
		break;
	}
};

GamConstFcltySpecInqireModule.prototype.downloadFile = function() {
	var selectRow = this.$('#fcltyFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
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

<%
/**
 * @FUNCTION NAME : getFcltsMngGroupNoNm
 * @DESCRIPTION   : 조회조건 시설물관리그룹 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamConstFcltySpecInqireModule.prototype.getFcltsMngGroupNoNm = function() {
	var sFcltsMngGroupNo = this.$('#sFcltsMngGroupNo').val();
	if (sFcltsMngGroupNo.length != 14) {
		this.$('#sFcltsMngGroupNoNm').val('');
	}

};

GamConstFcltySpecInqireModule.prototype.gotoLocation = function() {
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
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
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
				this.$("#mainTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
			} 
			break;
	
		case "tabs3":
			if(this._cmd != 'modify') {
				this.$("#mainTab").tabs("option", {active: 0});
				alert('건축시설 항목을 선택 하세요.');
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
 GamConstFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
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
var module_instance = new GamConstFcltySpecInqireModule();
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
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">건축시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">건축시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">건축시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="mainGrid" data-style="default">맵조회</button>
				</div>
			</div>

			<!-- 건축시설 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: auto;">
				<form id="fcltyManageVO">
				<input type="hidden" id="beforeGisPrtFcltyCd">
          		<input type="hidden" id="beforeGisPrtFcltySeq">
          		<input type="hidden" id="fcltsMngNo">

				<div style="margin-bottom:10px;">
					<table  class="searchPanel"  style="width:100%;">
						<tr>
							<th width="70%">건축시설 일반</th>
							<th>시설물관리번호 : <span id="titleFcltsMngNo"></span></th>
						</tr>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">항　　구　　분</th>
							<td>
								<input type="text" size="5" id="gisAssetsPrtAtCodeStr" disabled="disabled"/>
								<input type="text" size="23" id="gisAssetsPrtAtName" disabled="disabled"/>
							</td>
							<th width="12%" height="17">GIS 자 산 코 드</th>
							<td colspan="3">
								<input type="text" size="4" id="gisAssetsCd" disabled="disabled" data-required="true"/>-
								<input type="text" size="2" id="gisAssetsSubCd" disabled="disabled"/>-
								<input type="text" size="3" id="gisAssetsPrtAtCode" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">GIS 자　산　명</th>
							<td>
								<input type="text" size="30" id="gisAssetsNm" disabled="disabled"/>
							</td>
							<th width="12%" height="17">지　　　　　번</th>
							<td>
								<input id="gisAssetsLnm" type="text" size="4" title="지번 앞자리" disabled="disabled" />&nbsp;-&nbsp;
								<input id="gisAssetsLnmSub" type="text" size="7" title="지번 뒷자리" disabled="disabled" />
							</td>
							<th width="12%" height="17">소　　재　　지</th>
							<td>
								<input id="gisAssetsLocplc" type="text" size="30" title="소재지" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시　설　코　드</th>
							<td>
								<input type="text" size="2" id="gisPrtFcltyCd" disabled="disabled" />-
								<input type="text" size="25" id="gisPrtFcltySeq" disabled="disabled"/>
							</td>
							<th width="12%" height="17">시　설　분　류</th>
							<td>
								<input class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" id="selectedGAM005" data-required="true" data-column-id="gisPrtFcltyCd" />
								<input type="hidden" id="prtFcltySeNm"/>
							</td>
							<th width="12%" height="17">건 축　시 설 명</th>
							<td>
								<input type="text" size="30" id="prtFcltyNm" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시설물관리그룹</th>
							<td colspan="5">
								<input type="text" size="16" id="fcltsMngGroupNo" disabled="disabled"/>
								<input type="text" size="61" id="fcltsMngGroupNoNm" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</div>
					<table  class="searchPanel"  style="width:100%;">
						<tr>
							<th>건축시설 제원</th>
						</tr>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">준　공　일　자</th>
							<td><input type="text" size="11" id="bldDt" class="emdcal" disabled="disabled" /></td>
							<th width="12%" height="17">구　조　형　식</th>
							<td><input type="text" size="50" id="strctFmt" disabled="disabled" /></td>
						</tr>
						
						<tr>
							<th width="12%" height="17">하자 만 료 일 자</th>
							<td><input type="text" size="11" id="flawEndDt" class="emdcal" disabled="disabled" /></td>
							<th width="12%" height="17">기　초　형　식</th>
							<td><input type="text" size="50" id="baseFmt"  data-required="true" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">대　지　면　적</th>
							<td><input type="text" size="50" id="plotAr" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">건　축　면　적</th>
							<td><input id="archAr" type="text" size="50" title="건축면적" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">연　　면　　적</th>
							<td><input id="ar" type="text" size="50" title="연면적" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">주　사 용 용 도</th>
							<td><input type="text" size="50" id="mainUsagePrpos" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">주　차　면　적</th>
							<td><input type="text" size="50" id="prkAr" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">주　차　대　수</th>
							<td><input type="text" size="50" id="prkCnt" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">옥외 주 차 면 적</th>
							<td><input type="text" size="50" id="osdPrkAr" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">옥외 주 차 대 수</th>
							<td><input type="text" size="50" id="osdPrkCnt" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">옥내 주 차 면 적</th>
							<td><input type="text" size="50" id="isdPrkAr" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">옥내 주 차 대 수</th>
							<td><input type="text" size="50" id="isdPrkCnt" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">배기 닥 트 유 무</th>
							<td><input type="text" size="50" id="exhaustDuctEnnc" disabled="disabled" />
							</td>
							<th width="12%" height="17">환기 공 조 방 식</th>
							<td><input type="text" size="50" id="vntltnArcndtMthd" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">승강기운영방식</th>
							<td><input type="text" size="50" id="liftOperMthd" disabled="disabled" /></td>
							<th width="12%" height="17">승강기대수승객용</th>
							<td><input type="text" size="50" id="liftCntPsngr" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>	
							<th width="12%" height="17">승강기대수비상용</th>
							<td><input type="text" size="50" id="liftCntEmgcy" class="ygpaNumber" disabled="disabled" /></td>
							<th width="12%" height="17">승강기대수화물용</th>
							<td><input type="text" size="50" id="liftCntCargo" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">전기 인 입 용 량</th>
							<td><input id="elctyLeadInCapa" type="text" size="50" title="전기인입용량" class="ygpaNumber" data-decimal-point="2" disabled="disabled" /></td>
							<th width="12%" height="17">변　전　실　위 치</th>
							<td><input type="text" size="50" id="sbtLoc" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">정 화 조　형 식</th>
							<td><input type="text"  size="50" id="spictankFmt" disabled="disabled" /></td>
							<th width="12%" height="17">오수정화시설위치</th>
							<td><input type="text" size="50" id="swgClupfcltyLoc" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">물 탱 크　위 치</th>
							<td><input type="text" size="50" id="wrtTankLoc" disabled="disabled" /></td>
							<th width="12%" height="17">유류저장시설위치</th>
							<td><input type="text" size="50" id="oilSavefcltyLoc" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">냉　방　유　무</th>
							<td>
								<input type="text" size="50" id="clngEnnc" disabled="disabled" />
							</td>
							<th width="12%" height="17">냉　방　열　원</th>
							<td><input type="text" size="50" id="clngSrc" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">난　방　유　무</th>
							<td>
								<input type="text" size="50" id="htngEnnc" disabled="disabled" />
							</td>
							<th width="12%" height="17">난　방　열　원</th>
							<td><input  id="htngSrc" type="text"  size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">외　　　　　장</th>
							<td><input id="fcg" type="text"  size="50" disabled="disabled" /></td>
							<th width="12%" height="17">내　　　　　장</th>
							<td><input id="itr" type="text"  size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">천　　　　　장</th>
							<td><input id="ceil" type="text"  size="50" disabled="disabled" /></td>
							<th width="12%" height="17">지　　　　　붕</th>
							<td><input id="roof" type="text"  size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">지　붕　방　수</th>
							<td colspan="3"><input id="roofWtprf" type="text"  size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">설 계　공 사 명</th>
							<td><input id="planCnstNm" type="text"  size="50" disabled="disabled" /></td>
							<th width="12%" height="17">설계 수 행 회 사</th>
							<td><input id="planExcCmpny" type="text" size="50" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">설계 시 작 일 자</th>
							<td><input id="planBeginDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
							<th width="12%" height="17">설계 종 료 일 자</th>
							<td><input id="planEndDt" type="text" class="emdcal" size="11" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">시 공　공 사 명</th>
							<td><input id="cnstrctCnstNm" type="text"  size="50" title="시공공사명" disabled="disabled" /></td>
							<th width="12%" height="17">시공 수 행 회 사</th>
							<td><input id="cnstrctExcCmpny" type="text" size="50" title="시공수행회사" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">시공 시 작 일 자</th>
							<td><input id="cnstrctBeginDt" type="text" class="emdcal" size="11" title="시공시작일자" disabled="disabled" /></td>
							<th width="12%" height="17">시공 종 료 일 자</th>
							<td><input id="cnstrctEndDt" type="text" class="emdcal" size="11" title="시공종료일자" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">건축시설물분류코드</th>
							<td colspan="3">
								<input id="archFcltsClCd" type="text" size="14" title="건축시설물분류코드" disabled="disabled" />
								<input id="archFcltsClCdNm" type="text" size="34" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">소　　재　　지</th>
							<td colspan="3"><input id="loc" type="text" size="135" title="소재지" disabled="disabled" /></td>
						</tr>
						<tr>
							<th width="12%" height="17">비　　　　　고</th>
							<td colSpan="3">
								<textarea rows="4" cols="133" id="rm" maxlength="1000" disabled="disabled"></textarea>
							</td>
						</tr>

					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>

			<!-- 건축시설 첨부파일 -->
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

<%
/******************************** UI       END ********************************/
%>
