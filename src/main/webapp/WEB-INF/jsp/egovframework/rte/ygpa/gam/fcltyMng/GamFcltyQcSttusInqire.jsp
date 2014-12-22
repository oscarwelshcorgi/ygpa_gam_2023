<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcSttusInqire.jsp
  * @Description : 시설 점검현황 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.08  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.08
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
	this.$("#qcSttusDtlsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusDtlsList.do',
		dataType: "json",
		colModel : [
					{display:"관리그룹번호",	name:"fcltsMngGroupNo",		width:100,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSe",			width:60,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:120,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSe",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:120,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:120,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:120,		sortable:false,		align:"left"},
					{display:"점검진단예산",	name:"qcInspBdgt",			width:90,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검진단금액",	name:"qcInspAmt",			width:90,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"상태평가등급",	name:"sttusEvlLvl",			width:90,		sortable:false,		align:"center"},
					{display:"조치구분",		name:"actionSe",			width:60,		sortable:false,		align:"center"}
			],
		height: "auto"
	});
	
	this.$("#qcSttusDtlsList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#fcltyQcSttusInqireTab").tabs("option", {active: 1});
	});
	
	this.$("#qcSttusObjFcltsList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusObjFcltsList.do',
		dataType: 'json',
		colModel : [
					{display:"시설물관리번호",	name:"fcltsMngNo",	width:100,		sortable:true,		align:"center"},
					{display:"시설물명",		name:"prtFcltyNm",	width:150,		sortable:true,		align:"left"},
					{display:"점검진단구분",	name:"qcInspSe",	width:90,		sortable:true,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",	width:100,		sortable:true,		align:"center"},
					{display:"점검자",		name:"inspector",	width:100,		sortable:true,		align:"left"},
					{display:"비고",			name:"rm",			width:350,		sortable:true,		align:"left"}
			],
		height: "auto"
	});
	
	this.$("#qcSttusResultItemList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusResultItemList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",			name:"seq",				width:90,		sortable:true,		align:"center"},
					{display:"점검항목코드",	name:"qcItemCd",		width:100,		sortable:true,		align:"center"},
					{display:"점검항목명",		name:"qcItemNm",		width:150,		sortable:true,		align:"left"},
					{display:"점검항목결과구분",	name:"inspResultChk",	width:120,		sortable:true,		align:"center"}
			],
		height: "auto"
	});

	this.$("#qcSttusAtchFileList").flexigrid({
		module: this,
		url: '/fcltyMng/selectQcSttusAtchFileList.do',
		dataType: 'json',
		colModel : [
					{display:"순번",		name:"atchFileSeq",			width:40,		sortable:true,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:40,		sortable:true,		align:"center"},
					{display:"제목",		name:"atchFileSj",			width:200,		sortable:true,		align:"left"},
					{display:"논리파일명",	name:"atchFileNmLogic",		width:200,		sortable:true,		align:"left"},
			],
		height: "500"
	});

	this.$("#qcSttusAtchFileList").on("onItemSelected", function(event, module, row, grid, param) {
		module.selectAtchFileItem();
	});
	
};


GamFcltyQcSttusInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

//화면 및 데이터 초기화
GamFcltyQcSttusInqireModule.prototype.initDisplay = function() {	
	this.makeDivValues("#fcltyQcSttusInqireVO", {});
	this.$('#qcSttusObjFcltsList').flexEmptyData();
	this.$('#qcSttusResultItemList').flexEmptyData();
	this.$('#qcSttusAtchFileList').flexEmptyData();
	
	this.$("#previewImage").removeAttr("src");
};

//점검관리내역 조회
GamFcltyQcSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchFcltyQcSttusInqireForm");
	this.$("#qcSttusDtlsList").flexOptions({params:searchOpt}).flexReload();
	this.$("#fcltyQcSttusInqireTab").tabs("option", {active: 0});
};

//점검관리내역 데이터 조회
GamFcltyQcSttusInqireModule.prototype.loadDetailData = function() {
	var rows = this.$('#qcSttusDtlsList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
		var opts = [
	           		{name: 'sFcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	           		{name: 'sFcltsJobSe', value: row['fcltsJobSe'] },
	           		{name: 'sQcMngSeq', value: row['qcMngSeq'] }
		           ];
		this.doAction('/fcltyMng/selectQcSttusDtlsDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeDivValues('#fcltyQcSttusInqireVO', result.result);
				module.$("#qcInspResult").text(result.result.qcInspResult);
				module.$("#actionCn").text(result.result.actionCn);
				module.$("#qcSttusObjFcltsList").flexOptions({params:opts}).flexReload();
				module.$("#qcSttusAtchFileList").flexOptions({params:opts}).flexReload();
				module.$("#qcSttusResultItemList").flexOptions({params:opts}).flexReload();
			}
			else {
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
};

//첨부파일 항목선택
GamFcltyQcSttusInqireModule.prototype.selectAtchFileItem = function() {
	var rows = this.$('#qcSttusAtchFileList').selectedRows();
	if(rows.length > 0) {
		var row = rows[0];
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
	var selectRow = this.$('#qcSttusAtchFileList').selectedRows();
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
		//점검관리내역 목록 조회
		case "btnSearch":
			this.initDisplay();
			this.loadData();
			break;
			
		//첨부파일다운로드			
		case "btnDownloadFile":
			this.downloadAtchFileItem();
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
	switch(newTabId) {
		case "tabs1":
			break;
	}
};

/**
 * 팝업 close 이벤트
 */
GamFcltyQcSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		//시설물 관리 그룹
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
var module_instance = new GamFcltyQcSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyQcSttusInqireForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
							<td rowspan="2"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>점검진단구분</th>
							<td>
								<select id="sQcInspSe">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>점검기간</th>
							<td>
								<input id="sQcBeginDt" type="text" class="emdcal" size="15" /> ~ <input id="sQcEndDt" type="text" class="emdcal" size="15" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyQcSttusInqireTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물점검목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물점검내역</a></li>
				<li><a href="#tabs3" class="emdTab">점검관리대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">점검관리결과</a></li>
				<li><a href="#tabs5" class="emdTab">점검관리첨부파일</a></li>
			</ul>
			
			<!-- 시설물점검목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="qcSttusDtlsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>

			<!-- 시설물점검내역 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyQcSttusInqireVO">
				<div style="margin-bottom:10px;">
					<table  class="detailPanel"  style="width:100%;">
						<tr>
							<th width="30px" height="17">시설물관리그룹</th>
							<td colspan="3"><span id="fcltsMngGroupNo"></span></td>
							<th width="30px" height="17">점검관리순번</th>
							<td><span id="qcMngSeq"></span></td>
						</tr>
						<tr>
							<th height="17">시설물업무구분</th>
							<td><span id="fcltsJobSe"></span></td>
							<th height="17">점검관리명</th>
							<td colspan="3"><span id="qcMngNm"></span></td>
						</tr>
						<tr>
							<th height="17">시행년도</th>
							<td width="200px"><span id="enforceYear"></span></td>
							<th width="30px" height="17">점검진단일자</th>
							<td width="200px"><span id="qcInspDt"></span></td>
							<th height="17">점검진단기관명</th>
							<td><span id="qcInspInsttNm"></span></td>
						</tr>
						<tr>
							<th height="17">점검진단구분</th>
							<td><span id="qcInspSe"></span></td>
							<th height="17">점검시작일자</th>
							<td><span id="qcBeginDt"></span></td>
							<th height="17">점검종료일자</th>
							<td><span id="qcEndDt"></span></td>
						</tr>
						<tr>
							<th height="17">책임기술자명</th>
							<td><span id="responEngineerNm"></span></td>
							<th height="17">점검진단예산</th>
							<td><span id="qcInspBdgt"></span></td>
							<th height="17">점검진단금액</th>
							<td><span id="qcInspAmt"></span></td>
						</tr>
						<tr>
							<th height="17">상태평가등급</th>
							<td colspan="5"><span id="sttusEvlLvl"></span></td>
						</tr>
						<tr>
							<th height="17">점검진단결과</th>
							<td colspan="5"><textarea id="qcInspResult" cols="120" rows="7" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th height="17">조치구분</th>
							<td colspan="5"><span id="actionSe"></span></td>
						</tr>
						<tr>
							<th height="17">조치내용</th>
							<td colspan="5"><textarea id="actionCn" cols="120" rows="7" disabled="disabled"></textarea></td>
						</tr>
						<tr>
							<th height="17">비고</th>
							<td colspan="5"><span id="rm"></span></td>
						</tr>
					</table>
				</div>
				</form>
				<div class="emdControlPanel">
				</div>
			</div>
			
			<!-- 점검관리대상시설물 -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
				<table id="qcSttusObjFcltsList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>
			
			<!-- 점검관리결과 -->
			<div id="tabs4" class="emdTabPage" style="overflow: scroll;">
				<table id="qcSttusResultItemList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
				</div>
			</div>			

			<!-- 점검관리첨부파일 -->
			<div id="tabs5" class="emdTabPage" style="overflow: scroll;">
				<table border="1">
					<tr>
						<td width="50%">
							<table id="qcSttusAtchFileList" style="display:none" class="fillHeight"></table>
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