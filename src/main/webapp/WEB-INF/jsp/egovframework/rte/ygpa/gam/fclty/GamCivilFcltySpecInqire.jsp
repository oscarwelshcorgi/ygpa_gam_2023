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

	// 테이블 설정
	this.$("#civilFcltySpecInqireList").flexigrid({
		module: this,
		url: '/fclty/selectCivilFcltySpecInqireList.do',
		dataType: "json",
		colModel : [
					{display:"항구분",		name:"gisAssetsPrtAtName",	width:80,		sortable:false,		align:"center"},
					{display:"시설명",		name:"prtFcltyNm",			width:280,		sortable:false,		align:"left"},
					{display:"구조형식",		name:"strctFmt",			width:200,		sortable:false,		align:"left"},
					{display:"시설분류",	 	name:"prtFcltySeNm",		width:100,		sortable:false,		align:"left"},
					{display:"시설규격",	    name:"prtFcltyStndrd",		width:230,		sortable:false,		align:"left"},
					{display:"시설단위",  	    name:"prtFcltyUnit",		width:80,		sortable:false,		align:"left"},
					{display:"설치일자",		name:"prtFcltyInstlDt",		width:80,		sortable:false,		align:"center"},
					{display:"변경일자",		name:"prtFcltyChangeDt",	width:80,		sortable:false,		align:"center"}
			],
		height: "auto",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
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
					{display:"파일명",	name:"atchFileNmLogic",		width:160,		sortable:true,		align:"left"},

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

	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
			case "prtFcltyInqire":
				this._cmd = 'modify';
				this.$("#civilFcltySpecInqireTab").tabs("option", {
					active : 1
				});
			}
		}
	}
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
	var row = this.$('#civilFcltySpecInqireList').selectedRows();
	if(row.length==0 && this._params!=undefined && this._params.action=="prtFcltyInqire") {
		row={'fcltsMngNo': this._params.fcltsMngNo};
		this._params.action=="prtFcltyInqire1";	// 처음 한번만 체크 하도록 한다.
	}
	else {
		row = row[0];
	}

	if(row['fcltsMngNo']==null || row['fcltsMngNo'].length==0) {
		alert('시설물 관리번호에 오류가 있습니다.');
		this._cmd = '';
		this.initDisplay();
		return;
	}

	this.doAction('/fclty/selectCivilFcltySpecInqireDetail.do', row, function(module, result) {
		if(result.resultCode == "0"){
			module._fcltyManageVO=result.result;
			module.makeFormValues('#fcltyManageVO', module._fcltyManageVO);
			module.$("#dispfcltsMngNo").text(module.$("#fcltsMngNo").val());
			module.loadFileData();
		}
		else {
			this._cmd="";
			module.initDisplay();
			alert(result.resultMsg);
		}
	});
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

//시설재원목록 엑셀다운로드
GamCivilFcltySpecInqireModule.prototype.downloadExcel = function() {
	var rowCount = this.$("#civilFcltySpecInqireList").flexRowCount();
	if (rowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#civilFcltySpecInqireList').flexExcelDown('/fclty/excelDownloadCivilFcltySpecInqireList.do');
};

/**
 * 정의 된 버튼 클릭 시
 */
GamCivilFcltySpecInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnSearch": //조회
			this._cmd = "";
			this.initDisplay();
			this.loadData();
			break;

		// 엑셀 다운로드
		case "btnExcelDownload":
			this.downloadExcel();
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
							<th>항 구 분</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd"
								data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>시설물관리그룹</th>
							<td colspan="3"><input id="sFcltsMngGroupNo" type="text"
								size="14" /> <input id="sFcltsMngGroupNoNm" type="text"
								size="53" disabled="disabled" />
								<button id="popupSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설분류</th>
							<td><input id="sPrtFcltyCd" class="ygpaCmmnCd"
								data-default-prompt="전체" data-code-id="GAM070" /></td>
							<th>시설명</th>
							<td><input id="sPrtFcltyNm" type="text" size="30" /></td>
							<th>소재지</th>
							<td><input id="sLoc" type="text" size="30" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="civilFcltySpecInqireTab" class="emdTabPanel fillHeight"
			data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">토목시설 목록</a></li>
				<li><a href="#tabs2" class="emdTab">토목시설 제원</a></li>
				<li><a href="#tabs3" class="emdTab">토목시설 첨부파일</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="civilFcltySpecInqireList" style="display: none"
					class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
	                                <button data-role="showMap" data-gis-layer="gisCivilFclty" data-flexi-grid="civilFcltySpecMngList" data-style="default">맵조회</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>


			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyManageVO">
					<div style="margin-bottom: 10px;">
						<table class="searchPanel">
							<tbody>
								<tr>
									<th width="70%">토목시설 일반</th>
									<th>시설물관리번호 : <span id="dispfcltsMngNo"></span><input
										type="hidden" id="fcltsMngNo" /></th>
								</tr>
							</tbody>
						</table>
						<table class="detailPanel" style="width: 100%;">
							<tr>
								<th width="12%" height="17">항　　코　　드</th>
								<td><input type="text" size="5" id="gisAssetsPrtAtCode"
									disabled="disabled" /> <input type="text" size="15"
									id="gisAssetsPrtAtName" disabled="disabled" /></td>
								<th width="12%" height="17">GIS 자 산 코 드</th>
								<td colspan="3"><input type="text" size="4"
									id="gisAssetsCd" disabled="disabled" data-required="true" />-
									<input type="text" size="3" id="gisAssetsSubCd"
									disabled="disabled" />- <input type="text" size="4"
									id="gisAssetsPrtAtCode2" disabled="disabled" /></td>
							</tr>
							<tr>
								<th width="12%" height="17" class="required_text">GIS 자　산　명</th>
								<td><input type="text" size="20" id="gisAssetsNm"
									disabled="disabled" /></td>
								<th width="12%" height="17" class="required_text">소　　재　　지</th>
								<td colspan="3">
									<input id="loc" type="text" size="70" maxlength="50" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th width="12%" height="17" class="required_text">시　설　코　드</th>
								<td><input type="text" size="2" id="gisPrtFcltyCd"
									disabled="disabled" />&nbsp;-&nbsp; <input type="text"
									size="3" id="gisPrtFcltySeq" disabled="disabled" /></td>
								<th width="12%" height="17" class="required_text">시　설　분　류</th>
								<td><input class="ygpaCmmnCd" data-default-prompt="전체"
									data-code-id="GAM070" id="selectGisPrtFcltyCd"
									data-required="true" data-column-id="gisPrtFcltyCd" /> <input
									type="hidden" id="prtFcltySeNm" disabled="disabled" /></td>
								<th width="12%" height="17" class="required_text">시　　설　　명</th>
								<td><input type="text" size="27" id="prtFcltyNm"
									maxlength="80" disabled /></td>
							</tr>
							<tr>
								<th width="12%" height="17" class="required_text">시설물관리그룹</th>
								<td colspan="5"><input type="text" size="20"
									id="fcltsMngGroupNo" disabled="disabled" /> <input type="text"
									size="50" id="fcltsMngGroupNoNm" disabled="disabled" /></td>
							</tr>
							<tr>
								<th width="12%" height="17" class="required_text">설　치　일　자</th>
								<td><input id="prtFcltyInstlDt" type="text" size="20"
									title="설치일자" disabled /></td>
								<th width="12%" height="17">변　경　일　자</th>
								<td colspan="3"><input id="prtFcltyChangeDt" type="text"
									size="20" title="변경일자" disabled /></td>
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
							<th width="12%" height="17">시　설　물　연　장</th>
							<td><input id="fcltsExt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17">천　단　　　표　고</th>
							<td><input id="upsideAltud" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17">선　좌　　　수　심</th>
							<td><input id="berthDpwt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">구　조　　　형　식</th>
							<td colspan="5"><input id="strctFmt" type="text" size="139"  disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">에　이　프　런　폭</th>
							<td><input id="apronWd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17">에이프런　포장종류</th>
							<td><input id="apronPackKnd" type="text" size="20" maxlength="3" disabled/></td>
							<th width="12%" height="17">에이프런　포장구배</th>
							<td><input id="apronPackGrdnt" type="text" size="20" disabled /></td>
						</tr>
						<tr>
							<th width="12%" height="17">상　　　치　　　폭</th>
							<td><input id="permWd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17" >접안　 선박 　규모</th>
							<td><input id="csdhpShipScl" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17" >상　재　　　하　중</th>
							<td><input id="frostDmgWght" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">기초　 저면 　토질</th>
							<td><input id="baseBttmSoil" type="text" size="20" maxlength="150" disabled/></td>
							<th width="12%" height="17">취　급　　　화　물</th>
							<td colspan="3"><input id="hndlFrght" type="text" size="20" maxlength="100" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >말　뚝　　　구　경</th>
							<td><input id="pileClbr" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17" >말　뚝　　　연　장</th>
							<td><input id="pileExt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17" >말　뚝　　　본　수</th>
							<td><input id="pileQty" type="text" size="20" class="ygpaNumber" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >널　말　뚝　규　격</th>
							<td><input id="sheetFileStndrd" type="text" size="20" maxlength="100" disabled/></td>
							<th width="12%" height="17" >　　　　폭</th>
							<td><input id="wd" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
							<th width="12%" height="17" >길　　　　　　　이</th>
							<td><input id="lt" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >시　작　점　위　치</th>
							<td><input id="beginPtLoc" type="text" size="20" maxlength="100" disabled/></td>
							<th width="12%" height="17" >종　착　점　위　치</th>
							<td><input id="endPtLoc" type="text" size="20" maxlength="100" disabled/></td>
							<th width="12%" height="17" >포　장　　　종　류</th>
							<td><input id="packKnd" type="text" size="20" maxlength="3" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >급　수　전　수　량</th>
							<td><input id="hydrntQy" type="text" size="20" class="ygpaNumber" disabled/> 개</td>
							<th width="12%" height="17" >소　화　전　수　량</th>
							<td ><input id="firepgQy" type="text" size="20" class="ygpaNumber" disabled/> 개</td>
							<th width="12%" height="17" >선 　　　　　　　석</th>
							<td><input id="berth" type="text" size="20" class="ygpaNumber" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >야적장　포장　종류</th>
							<td><input id="yardPackKnd" type="text" size="20" maxlength="3" disabled/></td>
							<th width="12%" height="17" >야　적　장　면　적</th>
							<td><input id="yardAr" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/> m<sup>2</sup></td>
							<th width="12%" height="17" >설　계　　　파　고</th>
							<td><input id="planHegh" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17" >방　충　재　수　량</th>
							<td><input id="fenderQy" type="text" size="20" class="ygpaNumber" disabled/> 개</td>
							<th width="12%" height="17" >방　충　재　형　식</th>
							<td><input id="fenderFmt" type="text" size="20" maxlength="3" disabled/></td>
							<th width="12%" height="17" >방충재　배치　간격</th>
							<td><input id="fenderPmntItv" type="text" size="20" maxlength="30" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">계　선　주　수량1</th>
							<td><input id="mrpostQy1" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/> 개</td>
							<th width="12%" height="17">계　선　주　형식1</th>
							<td><input id="mrpostStndrd1" type="text" size="20" maxlength="100" disabled/></td>
				    		<th width="12%" height="17">계선주　배치　간격1</th>
							<td><input id="mrpostPmntItv1" type="text" size="20" maxlength="30" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">계　선　주　수량2</th>
							<td><input id="mrpostQy2" type="text" size="20" class="ygpaNumber" data-decimal-point="2" disabled/> 개</td>
							<th width="12%" height="17">계　선　주　형식2</th>
							<td><input id="mrpostStndrd2" type="text" size="20" maxlength="100" disabled/></td>
							<th width="12%" height="17">계선주　배치　간격2</th>
							<td><input id="mrpostPmntItv2" type="text" size="20" maxlength="30" disabled/></td>
						</tr>
						<tr>
							<th width="12%" height="17">계선주　견인력　1</th>
							<td><input id="mrpostPwr1" type="text" size="20" maxlength="30" disabled/></td>
							<th width="12%" height="17">계선주　견인력　2</th>
							<td><input id="mrpostPwr2" type="text" size="20" maxlength="30" disabled/></td>
							<th width="12%" height="17">파　랑　주　방　향</th>
							<td><input id="wavemainDir" type="text" size="20" maxlength="30" disabled/></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">

					<!-- <button id="gotoLocation">위치조회</button> -->

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