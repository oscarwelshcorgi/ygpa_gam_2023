<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcSttusInqire.jsp
  * @Description : 시설 점검 현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.09  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.1.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyQcSttusInqireModule() {
}

GamFcltyQcSttusInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyQcSttusInqireModule.prototype.loadComplete = function(params) {
	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.

	// 테이블 설정
	this.$("#qcMngDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:150,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
			],
		height: "auto"
	});
	
	this._cmd = '';
	
	this.$("#qcMngDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#fcltyQcwWrtMngTab").tabs("option", {active: 1});
	});

	this.$("#qcMngObjFcltsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"시설물",		name:"prtFcltyNm",		width:150,		sortable:true,		align:"left"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",	width:90,		sortable:true,		align:"center"},
					{display:"점검자",		name:"inspector",		width:100,		sortable:true,		align:"left"},
					{display:"점검진단일자",	name:"qcInspDt",		width:100,		sortable:true,		align:"center"},
					{display:"비고",			name:"rm",				width:350,		sortable:true,		align:"left"}
			],
		height: "220"
	});
	
	this.$("#qcMngObjFcltsList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectQcMngFcltsItem();
	});
	
	this.$("#qcMngResultItemList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusResultItemList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"seq",				width:90,		sortable:true,		align:"center"},
					{display:"시설명",		name:"qcItemUpperNm",	width:300,		sortable:true,		align:"left"},
					{display:"점검항목",		name:"qcItemNm",		width:300,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChkNm",	width:150,		sortable:true,		align:"center"}
			],
		height: "250"
	});

	this.$("#qcMngResultItemList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectQcMngResultItem();
	});
	
	this.$("#qcMngAtchFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusAtchFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"제목",		name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"파일명",	name:"atchFileNmLogic",		width:200,		sortable:true,		align:"left"},
			],
		height: "400"
	});

	this.$("#qcMngAtchFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});
	
	this.fillSelectBoxYear('#sEnforceYear'); 		
};

//2000년부터 현재년도까지 select 박스에 채워넣는 함수.
GamFcltyQcSttusInqireModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '</option>');
	}
};

//화면 및 데이터 초기화
GamFcltyQcSttusInqireModule.prototype.initDisplay = function() {
	this.$("#fcltyQcwWrtMngVO :input").val("");
	this.$("#qcMngAtchFileForm :input").val("");
	
	this.makeDivValues('#fcltyQcwWrtMngVO', []);
	this.summaryDisplay();
	
	this.makeDivValues('#gamQcMngObjFcltsForm', []);
	this.makeDivValues('#gamQcMngResultItemForm', []);
	this.makeDivValues('#qcMngAtchFileForm', []);
	
	this.makeFormValues('#gamQcMngObjFcltsForm', []);
	this.makeFormValues('#gamQcMngResultItemForm', []);

	this.$('#qcMngObjFcltsList').flexEmptyData();
	this.$('#qcMngResultItemList').flexEmptyData();
	this.$('#qcMngAtchFileList').flexEmptyData();
	
	this.$("#previewImage").removeAttr("src");
	
	this.$("#qcInspResult").disable();
	this.$("#objMngQcInspResult").disable();
	
	this.$("#actionCn").disable();
	this.$("#inspResultCn").disable();
};

GamFcltyQcSttusInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//점검관리내역 조회
GamFcltyQcSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcwWrtMngForm");
	this.$("#qcMngDtlsList").flexOptions({params:searchOpt}).flexReload();
	this.$("#fcltyQcwWrtMngTab").tabs("option", {active: 0});
};

//점검관리목록 엑셀다운로드
GamFcltyQcSttusInqireModule.prototype.downloadExcel = function() {
	var rowCount = this.$("#qcMngDtlsList").flexRowCount();
	if (rowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#qcMngDtlsList').flexExcelDown('/fcltyMng/excelDownloadQcSttusDtlsList.do');
};

//점검관리내역 데이터 조회
GamFcltyQcSttusInqireModule.prototype.loadDetailData = function() {
	var rows = this.$('#qcMngDtlsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcSttusDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeDivValues('#fcltyQcwWrtMngVO', result.result);
				module.makeFormValues('#fcltyQcwWrtMngVO', result.result);
				module.summaryDisplay();
				module.$("#qcMngObjFcltsList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngAtchFileList").flexOptions({params:opts}).flexReload();
				module.$("#qcMngResultItemList").flexOptions({params:opts}).flexReload();
			}
			else {
				module._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
};

//점검관리 대상물과 결과항목에 상세부분 출력
GamFcltyQcSttusInqireModule.prototype.summaryDisplay = function() {
	this.$("#summaryFcltsMngGroupNm1").text(this.$("#fcltsMngGroupNm").text());	
	this.$("#summaryQcMngNm1").text(this.$("#qcMngNm").text());	
	this.$("#summaryQcInspDtNm1").text(this.$("#qcInspDt").text());
	this.$("#summaryQcInspTpNm1").text(this.$("#qcInspTpNm").text());	
	this.$("#summaryQcSeNm1").text(this.$("#qcSeNm").text());
	this.$("#summaryQcInspSeNm1").text(this.$("#qcInspSeNm").text());	

	this.$("#summaryFcltsMngGroupNm2").text(this.$("#summaryFcltsMngGroupNm1").text());	
	this.$("#summaryQcMngNm2").text(this.$("#summaryQcMngNm1").text());	
	this.$("#summaryQcInspDtNm2").text(this.$("#summaryQcInspDtNm1").text());
	this.$("#summaryQcInspTpNm2").text(this.$("#summaryQcInspTpNm1").text());	
	this.$("#summaryQcSeNm2").text(this.$("#summaryQcSeNm1").text());
	this.$("#summaryQcInspSeNm2").text(this.$("#summaryQcInspSeNm1").text());	
};

//점검관리 대상시설물 항목선택
GamFcltyQcSttusInqireModule.prototype.selectQcMngFcltsItem = function() {
	var rows = this.$('#qcMngObjFcltsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		//row의 col명과 form의 id가 달라서 직접대입.
		this.$("#objMngPrtFcltyNm").text((row["prtFcltyNm"] != null) ? row["prtFcltyNm"] : '');
		this.$("#objMngSttusEvlLvlNm").text((row["sttusEvlLvlNm"] != null) ? row["sttusEvlLvlNm"] : '');
		this.$("#objMngQcInspDt").text((row["qcInspDt"] != null) ? row["qcInspDt"] : '');
		this.$("#objMngInspector").text((row["inspector"] != null) ? row["inspector"] : '');
		this.$("#objMngRm").text((row["rm"] != null) ? row["rm"] : '' );
		this.$("#objMngQcInspResult").val(row["qcInspResult"]);
	}
};

//점검관리 결과항목 항목선택
GamFcltyQcSttusInqireModule.prototype.selectQcMngResultItem = function() {
	var rows = this.$('#qcMngResultItemList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.makeDivValues("#gamQcMngResultItemForm", row);
		if(row["inspResultChkNm"] != null) {
			this.$("#inspResultChkNm").text(row["inspResultChkNm"]);
		} else {
			this.$("#inspResultChkNm").text('');
		}
		this.makeFormValues("#gamQcMngResultItemForm", row);
	}
};


//첨부파일 항목선택
GamFcltyQcSttusInqireModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#qcMngAtchFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		this.makeDivValues("#qcMngAtchFileForm", row);
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
GamFcltyQcSttusInqireModule.prototype.downloadAtchFileItem = function() {
	var selectRow = this.$('#qcMngAtchFileList').selectedRows();
	if(selectRow.length > 0) {
		var row=selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyQcSttusInqireModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		//점검관리목록 조회
		case "btnSearch":
			this._cmd = '';
			this.initDisplay();
			this.loadData();
			break;
			
		//점검관리목록 엑셀 다운로드 
		case 'btnExcelDownload':
			this.downloadExcel();
			break;	
						
		//첨부파일다운로드			
		case "btnDownloadFile":
			this.downloadAtchFileItem();
			break;
						
		//시설물관리그룹선택(조회화면)
		case "popupSearchFcltsMngGroup":
			this.doExecuteDialog("selectFcltsMngGroup", "관리그룹 선택", '/popup/showFcltsMngGroup.do', {});
			break;
			
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamFcltyQcSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1') {
		this.initDisplay();
		this.loadDetailData();
	}
	if(oldTabId == 'tabs2') {
		this.summaryDisplay();
	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
		case "tabs3":
		case "tabs4":
		case "tabs5":
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		//시설물 관리 그룹(조회화면)
		case "selectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNm").val(value["fcltsMngGroupNm"]);
			break;
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyQcSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyQcwWrtMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>관리그룹</th>
							<td>
								<input type="hidden" id="sFcltsMngGroupNo" />
								<input type="text" id="sFcltsMngGroupNm" size="30" disabled="disabled" />
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th></th>
							<td></td>
							<td rowspan="3"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>업무구분</th>
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="">선택</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
								</select>
								<input id="sFcltsJobSeNm" type="hidden" />
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
						</tr>
						<tr>
							<th>점검구분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
                                <input id="sQcSeNm" type="hidden" />
							</td>
							<th>시행년도</th>
							<th>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</th>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyQcwWrtMngTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물점검목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물점검내역</a></li>
				<li><a href="#tabs3" class="emdTab">점검관리대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">점검관리결과</a></li>
				<li><a href="#tabs5" class="emdTab">점검관리첨부파일</a></li>
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcMngDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button data-role="printPage" data-search-option="searchFcltyQcwWrtMngForm" data-url='/fcltyMng/selectQcSttusDtlsReportPrint.do'>점검목록인쇄</button>
					<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
				</div>
			</div>

			<!-- 시설물점검내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcwWrtMngVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="12%" height="17">관리그룹</th>
							<td colspan="3">
								<span id="fcltsMngGroupNm"></span>
							</td>
							<th width="12%" height="17">점검관리순번</th>
							<td>
								<span id="qcMngSeq"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">업무구분</th>
							<td>
								<span id="fcltsJobSeNm"></span>
							</td>
							<th width="12%" height="17">점검관리명</th>
							<td colspan="3">
								<span id="qcMngNm"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">시행년도</th>
							<td>
								<span id="enforceYear"></span>
							</td>
							<th width="12%" height="17">점검구분</th>
							<td>
								<span id="qcSeNm"></span>
							</td>
							<th width="12%" height="17">시행일자</th>
							<td>
								<span id="qcInspDt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단자</th>
							<td>
								<span id="qcInspTpNm"></span>
							</td>
							<th width="12%" height="17">점검진단구분</th>
							<td>
								<span id="qcInspSeNm"></span>
							</td>
							<th width="12%" height="17">점검진단금액</th>
							<td>
								<span id="qcInspAmt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">상태평가등급</th>
							<td colspan="5">
								<span id="sttusEvlLvlNm"></span>
							</td>
						</tr>
						<tr>							
							<th width="12%" height="17">점검진단기관명</th>
							<td>
								<span id="qcInspInsttNm"></span>
							</td>
							<th width="12%" height="17">점검기간</th>
							<td colspan="3">
								<span id="qcBeginDt"></span> ~ 
								<span id="qcEndDt"></span>
							</td>
						</tr>
						<tr>
							<th width="12%" height="17">책임기술자명</th>
							<td colspan="5"><span id="responEngineerNm"></span></td>
						</tr>
						<tr>
							<th width="12%" height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="135" rows="8"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="135" rows="8"></textarea></td>
						</tr>
						<tr>
							<th width="12%" height="17">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
					</table>
				</div>
				</form>
			</div>
			
			<!-- 점검관리대상시설물 -->			
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 상세내역</th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th>관리그룹</th>
								<td><span id="summaryFcltsMngGroupNm1"></span></td>
								<th>점검관리명</th>
								<td><span id="summaryQcMngNm1"></span></td>
								<th>시행일자</th>
								<td><span id="summaryQcInspDtNm1"></span></td>
							</tr>
							<tr>
								<th>점검진단자</th>
								<td><span id="summaryQcInspTpNm1"></span></td>
								<th>점검구분</th>
								<td><span id="summaryQcSeNm1"></span></td>
								<th>점검진단구분</th>
								<td><span id="summaryQcInspSeNm1"></span></td>
							</tr>
						</tbody>
					</table>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 대상시설물</th>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="qcMngObjFcltsList" style="display:none"></table>
				<form id="gamQcMngObjFcltsForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th>시설물</th>
		                        <td>
		                        	<span id="objMngPrtFcltyNm"></span>
		                    	</td>
		                        <th>상태평가등급</th>
		                        <td>
		                        	<span id="objMngSttusEvlLvlNm"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>점검자</th>
		                        <td>
		                        	<span id="objMngInspector"></span>
		                        </td>
								<th>점검진단일자</th>
		                        <td>
		                        	<span id="objMngQcInspDt"></span>
		                        </td>
							</tr>
							<tr>
								<th>점검진단결과</th>
								<td colspan="3"><textarea id="objMngQcInspResult" cols="133" rows="4" class="EditItem"></textarea></td>
							</tr>
							<tr>
								<th>비고</th>
								<td colspan="3">
									<span id="objMngRm"></span>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			
			<!-- 점검관리결과항목 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<div class="emdControlPanel">
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 상세내역</th>
							</tr>
						</tbody>
					</table>
					<table class="detailPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th>관리그룹</th>
								<td><span id="summaryFcltsMngGroupNm2"></span></td>
								<th>점검관리명</th>
								<td><span id="summaryQcMngNm2"></span></td>
								<th>시행일자</th>
								<td><span id="summaryQcInspDtNm2"></span></td>
							</tr>
							<tr>
								<th>점검진단자</th>
								<td><span id="summaryQcInspTpNm2"></span></td>
								<th>점검구분</th>
								<td><span id="summaryQcSeNm2"></span></td>
								<th>점검진단구분</th>
								<td><span id="summaryQcInspSeNm2"></span></td>
							</tr>
						</tbody>
					</table>
					<table class="summaryPanel"  style="width:100%;">
						<tbody>
							<tr>
								<th style="font-weight:bold;">점검관리 결과</th>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="qcMngResultItemList" style="display:none"></table>
				<form id="gamQcMngResultItemForm">
					<table class="searchPanel">
						<tbody>
							<tr>
		                        <th width="10%">점검항목</th>
		                        <td width="40%">
		                        	<span id="qcItemNm"></span>
		                        </td>							
		                        <th width="10%">점검결과구분</th>
		                        <td width="40%">
		                        	<span id="inspResultChkNm"></span>
		                        </td>
							</tr>
							<tr>
								<th>점검결과내용</th>
								<td colspan="3"><textarea id="inspResultCn" cols="133" rows="5" class="EditItem"></textarea></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>			

			<!-- 점검관리첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="qcMngAtchFileList" style="display:none" class="fillHeight"></table>
							<div class="emdControlPanel">
								<button id="btnDownloadFile">다운로드</button>
							</div>
			
							<form id="qcMngAtchFileForm">
								<table class="searchPanel editForm">
									<tr>
										<th width="15%" height="23" class="required_text">파일구분</th>
										<td>
											<span id="atchFileSeNm"></span>
										</td>
										<th width="15%" height="23" class="required_text">파일제목</th>
										<td>
											<span id="atchFileSj"></span>
										</td>
									</tr>
								</table>
							</form>
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