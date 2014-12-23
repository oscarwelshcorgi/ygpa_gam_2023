<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamCivilFcltySpecInqire.jsp
  * @Description : 토목시설 제원 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.4  	정성현          최초 생성
  *
  * author 정성현
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
function GamCivilFcltySpecInqireModule() {
}

GamCivilFcltySpecInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamCivilFcltySpecInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#civilFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectCivilFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항구분명",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"자산명",		name:"gisAssetsNm",			width:200,		sortable:false,		align:"left"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
					{display:"시설규격",	    name:"prtFcltyStndrd",		width:230,		sortable:false,		align:"left"},
					{display:"시설단위",  	    name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto"
	});

	this._cmd = '';
	this._prtFcltySe = 'C';

	this.$("#civilFcltySpecInqireList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#civilFcltySpecInqireList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#civilFcltySpecInqireTab").tabs("option", {active: 1});
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
		url: '/fclty/selectCivilFcltySpecInqireFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"파일제목",	name:"atchFileSj",			width:160,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},

		            ],
		height: "400"
	});

	this.$("#fcltsFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});
	
	//첨부파일 정보 변화 이벤트 처리기
	this.$(".photoEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.atchFileInfoChanged(event.target);
	});
};



GamCivilFcltySpecInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//시설목록 로드
GamCivilFcltySpecInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchCivilFcltySpecInqireForm");
	this.$("#civilFcltySpecInqireList").flexOptions({params:searchOpt}).flexReload();
};

//시설재원데이터 로드
GamCivilFcltySpecInqireModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#civilFcltySpecInqireList').selectedRows();
	if(selectRows.length > 0) {
		var row = selectRows[0];
		if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
			alert('시설물 관리번호에 오류가 있습니다.');
			this._cmd = '';
			this.initDisplay();
			return;
		}
		
		this.doAction('/fclty/selectCivilFcltySpecInqireDetail.do', row, function(module, result) {
		
			if(result.resultCode == "0"){
				module._fcltyManageVO=result.result;
				module.makeDivValues('#fcltyManageVO', module._fcltyManageVO);

				module.loadFileData();
			}
			else {
				this._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});
	}
};

//시설 첨부파일 로드
GamCivilFcltySpecInqireModule.prototype.loadFileData = function() {
	var searchOpt = [{name: 'sFcltsMngNo', value: this._fcltyManageVO['fcltsMngNo']}];
	this.$("#fcltsFileList").flexOptions({params:searchOpt}).flexReload();
};

// 화면 및 데이터 초기화 처리
GamCivilFcltySpecInqireModule.prototype.initDisplay = function() {
	this._deleteDataFileList = [];
	this.$("#fcltyManageVO :input").val("");
	this.$('#fcltsFileList').flexEmptyData();
	this.$("#previewImage").attr("src", "#");
	
	 if (this._cmd == "modify") {
		this.$("#selectGisPrtFcltyCd").disable();
		this.$("#searchGisCodeBtn2").hide();
	} else {
		this.$("#fcltyManageVO :input").val("");
		this.$("#selectGisPrtFcltyCd").enable();
		this.$("#searchGisCodeBtn2").show();
		this.$("#civilFcltySpecInqireTab").tabs("option", {active: 0});
	}
};

//첨부파일 정보 변화 처리
GamCivilFcltySpecInqireModule.prototype.atchFileInfoChanged = function(target) {
	var changed=false;
	var row={};
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		row=selectRow[0];
		if(this.$('#atchFileSe').is(target)) {
			row['atchFileSe'] = $(target).val();
			row['atchFileSeNm'] = $(target).find('option:selected').text();
			changed=true;
		}
		if(this.$('#atchFileSj').is(target)) {
			row['atchFileSj'] = $(target).val();
			changed=true;
		}
		if(this.$('#atchFileWritngDt').is(target)) {
			row['atchFileWritngDt'] = $(target).val();
			changed=true;
		}
	}
	if(changed) {
		var rowid=this.$("#fcltsFileList").selectedRowIds()[0];
		if(row['_updtId']!='I') row['_updtId']='U';
		this.edited=true;
		this.$('#fcltsFileList').flexUpdateRow(rowid, row);
	}
};


//첨부파일 항목선택
GamCivilFcltySpecInqireModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#fcltsFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.$("#fcltsFileForm input").val('');
		this.makeFormValues("#fcltsFileForm", row);
		if(row.atchFileNmPhysicl != null || row.atchFileNmPhysicl != "") {
			// 파일의 확장자를 체크하여 이미지 파일이면 미리보기를 수행한다.
			var filenm = row["atchFileNmPhysicl"];
			var ext = filenm.substring(filenm.lastIndexOf(".")+1).toLowerCase();
			if(ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif"){
				var imgURL = this.getPfPhotoUrl(filenm);
			    this.$("#previewImage").attr("src", imgURL);
			}else{
				this.$("#previewImage").removeAttr("src");
			}
		}
	}
};


//첨부파일 다운로드
GamCivilFcltySpecInqireModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#fcltsFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};


/**
 * 정의 된 버튼 클릭 시
 */
GamCivilFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	switch(buttonId) {
		case "btnSearch": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;

	

//파일다운로드
		case "btnDownloadFile":
		this.downloadAtchFileItem();
			break;
			break;

		// 시설물관리그룹(조회 화면)
		case "popupSearchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;
			
		// 시설물관리그룹(디테일 화면)
		case "popupSearchFcltsMngGroupNo2":
			this.doExecuteDialog("selectFcltsMngGroup2", "시설물그룹번호", '/popup/showFcltsMngGroup.do', {});
			break;
			
			
		case "registLocation":	// 위치 등록
			var module=this;
			EMD.gis.addPrtFcltyMarker(this._fcltyItem, function(value) {
				module.$('#laCrdnt').val(value.laCrdnt);
				module.$('#loCrdnt').val(value.loCrdnt);
			});
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
GamCivilFcltySpecInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.$('#tabs2').scrollTop(0);
		this.loadDetailData();
	}
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		if(this._cmd != 'modify') {
			this.$("#civilFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 토목시설항목을 선택 하세요.');
		}
		break;
	case "tabs3":
		if(this._cmd != 'modify') {
			this.$("#civilFcltySpecInqireTab").tabs("option", {active: 0});
			alert('먼저 토목시설항목을 선택하세요.');
		}
		break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamCivilFcltySpecInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		
		//조회화면
		case "selectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
		
		//디테일화면
		case "selectFcltsMngGroup2":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
			break;
			
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCivilFcltySpecInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchCivilFcltySpecInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3">
								<input id="sFcltsMngGroupNo" type="text" size="14"/>
								<input id="sFcltsMngGroupNoNm" type="text" size="53" disabled="disabled"/>
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" /></td>
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
		<div id="civilFcltySpecInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">토목시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">토목시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">토목시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="civilFcltySpecInqireList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">

					<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="civilFcltySpecInqireList" data-style="default">맵조회</button>
				</div>
			</div>


			<!-- 토목시설 제원 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
				<div style="margin-bottom:10px;">
					<table class="searchPanel">
						<tbody>
							<tr>
								<th width="70%">토목시설 일반</th>
								<th>시설물관리번호 :  <span id="fcltsMngNo"></span></th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">항코드</th>
							<td><span id="gisAssetsPrtAtCode"></span>&nbsp;&nbsp;&nbsp;<span id="gisAssetsPrtAtName"></span></td>
							<th width="12%" height="17" class="required_text">GIS 자산코드</th>
							<td colspan="3">
								<span id="gisAssetsCd"></span>-<span id="gisAssetsSubCd"></span>-<span id="gisAssetsPrtAtCode2"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">GIS 자산명</th>
							<td><span id="gisAssetsNm"></span></td>
							<th width="12%" height="17" class="required_text">지번</th>
							<td><span id="gisAssetsLnm"></span>&nbsp;-&nbsp;<span id="gisAssetsLnmSub"></span></td>
							<th width="12%" height="17" class="required_text">소재지</th>
							<td><span id="gisAssetsLocplc"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설코드</th>
							<td>
							<span id="gisPrtFcltyCd"></span>&nbsp;-&nbsp;<span id="gisPrtFcltySeq"></span>
							</td>
							<th width="12%" height="17" class="required_text">시설분류</th>
							<td>
								 <span class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM005" id="selectGisPrtFcltyCd" data-required="true" data-column-id="gisPrtFcltyCd"/></span>

								<input type="hidden" id="prtFcltySeNm" disabled="disabled" />
							</td>
							<th width="12%" height="17" class="required_text">토목시설명</th>
							<td><span id="prtFcltyNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시설물관리그룹</th>
							<td colspan="5">
						<span id="fcltsMngGroupNo"></span>&nbsp;-&nbsp;<span id="fcltsMngGroupNoNm" ></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설치일자</th>
							<td><span id="prtFcltyInstlDt"></span></td>
							<th width="12%" height="17" class="required_text">변경일자</th>
							<td colspan="3"><span id="prtFcltyChangeDt"></span></td>
						</tr>
					</table>
				</div>
					<table class="searchPanel">
						<tbody>
							<tr>
								<th>토목시설 제원</th>
							</tr>
						</tbody>
					</table>
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17" class="required_text">시설물연장</th>
							<td><span id="fcltsExt"></span></td>
							<th width="12%" height="17" class="required_text">천단표고</th>
							<td><span id="upsideAltud"></span></td>
							<th width="12%" height="17" class="required_text">천단폭</th>
							<td><span id="upsideWd"></span>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">구조형식</th>
							<td colspan="5"><span id="strctFmt"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">선좌수심</th>
							<td><span id="berthDpwt"></span></td>
							<th width="12%" height="17" class="required_text">상치폭</th>
							<td><span id="permWd"></span></td>
							<th width="12%" height="17" class="required_text">에이프런폭</th>
							<td><span id="apronWd"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">에이프런포장종류</th>
							<td><span id="apronPackKnd"></span></td>
							<th width="12%" height="17" class="required_text">에이프런포장구배</th>
							<td colspan="3"><span id="apronPackGrdnt"></span></td>

						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">접안선박규모</th>
							<td><span id="csdhpShipScl"></span></td>
							<th width="12%" height="17" class="required_text">상재하중</th>
							<td colspan="3"><span id="frostDmgWght"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">기초저면토질</th>
							<td colspan="5"><span id="baseBttmSoil"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">취급화물</th>
							<td colspan="5"><span id="hndlFrght"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">말뚝구경</th>
							<td><span id="pileClbr"></span></td>
							<th width="12%" height="17" class="required_text">말뚝연장</th>
								<td><span id="pileExt"></span></td>
							<th width="12%" height="17" class="required_text">말뚝본수</th>
							<td><span id="pileQty"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">널말뚝규격</th>
							<td colspan="3"><span id="sheetFileStndrd"></span></td>
							<th width="12%" height="17" class="required_text">굽수전수량</th>
							<td><span id="hydrntQy"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">소화전수량</th>
								<td><span id="firepgQy"></span></td>
							<th width="12%" height="17" class="required_text">야적장포장종류</th>
							<td><span id="yardPackKnd"></span></td>
							<th width="12%" height="17" class="required_text">야적장면적</th>
							<td><span id="yardAr"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">방충재종류코드</th>
							<td><span id="fenderKndCd"></span></td>
							<th width="12%" height="17" class="required_text">방충재배치간격</th>
							<td><span id="fenderPmntItv"></span></td>
							<th width="12%" height="17" class="required_text">방충재형식</th>
							<td><span id="fenderFmt"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">계선주규격1</th>
							<td><span id="mrpostStndrd1"></span></td>
							<th width="12%" height="17" class="required_text">계선주배치간격1</th>
							<td><span id="mrpostPmntItv1"></span></td>
							<th width="12%" height="17" class="required_text">계선주수량1</th>
							<td><span id="mrpostQy1"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">계선주견인력1</th>
							<td><span id="mrpostPwr1"></span></td>
							<th width="12%" height="17" class="required_text">계선주규격2</th>
							<td><span id="mrpostStndrd2"></span></td>
							<th width="12%" height="17" class="required_text">계선주배치간격2</th>
							<td><span id="mrpostPmntItv2"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">계선주수량2</th>
							<td><span id="mrpostQy2"></span></td>
							<th width="12%" height="17" class="required_text">계선주견인력2</th>
							<td><span id="mrpostPwr2"></span></td>
							<th width="12%" height="17" class="required_text">선석</th>
							<td><span id="berth"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주요취급화물</th>
							<td colspan="5"><span id="stplHndlFrght"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">주요계류선박</th>
							<td colspan="5"><span id="stplMoorShip"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">위치</th>
							<td colspan="5"><span id="loc"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">시작점위치</th>
							<td colspan="5"><span id="beginPtLoc"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">종착점위치</th>
							<td colspan="5"><span id="endPtLoc"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">폭</th>
							<td><span id="wd"></span></td>
							<th width="12%" height="17" class="required_text">길이</th>
							<td><span id="lt"></span></td>
							<th width="12%" height="17" class="required_text">포장종류</th>
							<td><span id="packKnd"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">설계파고</th>
							<td><span id="planHegh"></span></td>
							<th width="12%" height="17" class="required_text">파랑주방향</th>
							<td><span id="wavemainDir"></span></td>
							<th width="12%" height="17" class="required_text">토목시설물분류코드</th>
							<td><span id="cvlEngFcltsClCd"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">외축소파공경사비율</th>
							<td><span id="outerSwaveSlpRate"></span></td>
							<th width="12%" height="17" class="required_text">외축소파공피복</th>
							<td colspan="3"><span id="outerSwaveCover"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17" class="required_text">내축소파공경사비율</th>
							<td><span id="inSwaveSlpRate"></span></td>
							<th width="12%" height="17" class="required_text">내축소파공피복</th>

							<td colspan="3"><span id="inSwaveCover"></span></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="gotoLocation">위치조회</button>
				</div>
			</div>

			<!-- 토목시설 첨부파일 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="fcltsFileList" style="display:none" class="fillHeight"></table>
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