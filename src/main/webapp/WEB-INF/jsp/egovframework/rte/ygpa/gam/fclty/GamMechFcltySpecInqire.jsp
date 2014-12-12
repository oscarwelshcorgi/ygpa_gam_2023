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

GamMechFcltySpecInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamMechFcltySpecInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#mechFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:80,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplayCd",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설코드", 	    name:"gisPrtFcltyDisplayCd",width:80,		sortable:false,		align:"center"},
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
	
	this.$(".text").bind("change keyup", {module: this}, function(event) {
		var limit_char = /[|]/;
		if(limit_char.test(event.target.value)){
			alert('|'+"(파이프) 특수문자는 사용 하실수 없습니다.");
			var rep= event.target.value.replace(limit_char,"");
			event.target.value = rep;
			return;
		}
	});

	this.$("#fcltsFileList").flexigrid({
		module: this,
		url: '/fclty/selectMechFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},
					{display:"물리파일명",	name:"atchFileNmPhysicl",	width:160,		sortable:true,		align:"left"},
					{display:"작성일자",	name:"atchFileWritngDt",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.$("#fcltsFileList").selectedRowIds()[0];
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				
				imgURL = module.getPfPhotoUrl(filenm);
				module.$("#previewImage").fadeIn(400, function() {
				module.$("#previewImage").attr("src", imgURL);
				});
			}else{
				module.$("#previewImage").attr("src", "#");
			}
		}
	});
};

//시설제원데이터 로드
GamMechFcltySpecInqireModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#mechFcltySpecInqireList').selectedRows();
	row = selectRows[0];
	var opts = [{name: 'fcltsMngNo', value: row['fcltsMngNo'] }];
	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		return;
	}
	// 기계시설 제원 처리
	this.doAction('/fclty/selectMechFcltySpecInqireDetail.do', opts, function(module, result) {
		if(result.resultCode == "0"){
			module.clearCodePage();
			module.makeDivValues('#fcltyManageVO', result.result);
			module.$("#dispfcltsMngNo").text(result.result["fcltsMngNo"]);
		}
	});
	var searchOpt = [{name: 'sFcltsMngNo', value: row['fcltsMngNo'] }];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
	this.clearFilePage();
};

//첨부파일 다운로드
GamMechFcltySpecInqireModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMechFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		// 조회
		case "searchBtn":
			this._cmd = "";
			var searchOpt = this.makeFormArgs("#searchMechFcltySpecInqireForm");
			this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
			this.$("#mechFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
			break;
		
		// 자산코드 팝업(조회화면)
		case "searchGisCodeBtn":
			this.doExecuteDialog("selectGisCode", "자산코드", '/popup/showAssetsCd.do', {});
			break;
			
		//파일다운로드			
		case "btnDownloadFile":
			this.downloadAtchFileItem();
			break;
						
		case "gotoLocation":	// 위치 조회
			if(this._fcltyItem.laCrdnt!=null && this._fcltyItem.laCrdnt!=null) {
				EMD.gis.goLocation(this._fcltyItem.laCrdnt, this._fcltyItem.loCrdnt);
				EMD.gis.selectPrtFclty(this._fcltyItem);
			} else if(this._fcltyItem.lat!=null && this._fcltyItem.lng!=null){
				EMD.gis.goLocation4326(this._fcltyItem.lat, this._fcltyItem.lng);
				EMD.gis.selectPrtFclty(this._fcltyItem);
			} else {
				alert("시설위치가 등록되지 않았습니다.");
			}
			break;
	}
};

/**
 * 탭 변경시 실행 이벤트
 */
 GamMechFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.loadDetailData();
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
			alert('기계시설항목을 선택하시거나 시설추가버튼을 누르세요.');
		} 
		break;
	case "tabs3":
		if(this._cmd != 'modify') {
			this.$("#mechFcltySpecInqireTab").tabs("option", {active: 0});
			alert('기계시설항목을 선택하시거나 시설추가버튼을 누르세요.');
		} 
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
 GamMechFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		// 조회화면
		case "selectGisCode":
			this.$("#sAssetsCd").val(value["gisAssetsCd"]);
			this.$("#sAssetsSubCd").val(value["gisAssetsSubCd"]);
			break;

		case "selectGisCode2":
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
		
		case "selectFcltsClCd":
			this.$("#mechFcltsClCd").val(value["fcltsClCd"]);
			this.$("#mechFcltsClCdNm").val(value["fcltsClCdNm"]);			
			break;

		case "selectArchFcltsMngNo":
			this.$("#archFcltsMngNo").val(value["fcltsMngNo"]);
			this.$("#archFcltsMngNoNm").val(value["prtFcltyNm"]);			
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

GamMechFcltySpecInqireModule.prototype.clearCodePage = function() {
	this.$('#fcltyManageVO :input').val('');
};

GamMechFcltySpecInqireModule.prototype.clearFilePage = function() {
	this.$('#fcltsFileList').flexEmptyData();
	this.$('#previewImage').attr('src', '');
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
			<form id="searchMechFcltySpecInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" />
								<button id="searchGisCodeBtn" class="popupButton">선택</button>
							</td>
							<th>기계시설분류</th>
							<td>
								<input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM058" />
							</td>
							<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>기계시설명</th>
							<td colspan="5"><input id="sPrtFcltyNm" type="text" size="60" maxlength="40"  /></td>
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
				<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnDownloadFile">다운로드</button>
				</div>
				<div class="emdPanel"><img id="previewImage" style="border: 1px solid #000; max-width:800px; max-height: 600px" src=""></div>
			</div>
		</div>
	</div>
</div>