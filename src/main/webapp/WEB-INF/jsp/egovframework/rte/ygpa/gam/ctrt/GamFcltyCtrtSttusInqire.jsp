<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtSttusInqire.jsp
 * @Description : 시설물 계약이력 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.28  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.28
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
 * @FUNCTION NAME : GamFcltyCtrtSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyCtrtSttusInqireModule() {}

GamFcltyCtrtSttusInqireModule.prototype = new EmdModule(1000, 645);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/ctrt/gamSelectFcltyCtrtSttusInqireList.do',
		dataType : 'json',
		colModel : [
					{display:'No.',						name:'rnum',				width:50,		sortable:false,		align:'center'},
					{display:'거래관계',				name:'dealRelate',			width:80, 		sortable:false,		align:'left'},
					{display:'업종',					name:'induty',				width:100,		sortable:false,		align:'left'},
					{display:'기업명',					name:'entrpsNm',			width:140,		sortable:false,		align:'left'},
					{display:'주요품목',				name:'stplPrdlst',			width:100,		sortable:false,		align:'left'},
					{display:'이전년도 거래금액(원)', 	name:'prevCtrtAmt',			width:150,		sortable:false,		align:'right'},
					{display:'거래금액(원)', 			name:'currCtrtAmt',			width:150,		sortable:false,		align:'right'},
					{display:'사업자번호',				name:'bsnmNo',				width:100,		sortable:false,		align:'center'},
					{display:'대표자',					name:'rprsntv',				width:80,		sortable:false,		align:'left'},
					{display:'담당자',					name:'charger',				width:80,		sortable:false,		align:'left'},
					{display:'직위',					name:'chargerOfcPos',		width:100,		sortable:false,		align:'left'},
					{display:'전화',					name:'tlphonNo',			width:100,		sortable:false,		align:'left'},
					{display:'휴대폰',					name:'chargerMoblphonNo',	width:100,		sortable:false,		align:'left'},
					{display:'팩스',					name:'faxNo',				width:100,		sortable:false,		align:'left'},
					{display:'담당자 E-MAIL',			name:'chargerEmail',		width:150,		sortable:false,		align:'left'},
					{display:'우편번호',				name:'postNo',				width:80,		sortable:false,		align:'center'},
					{display:'도로명 주소',				name:'roadnmAdres',			width:250,		sortable:false,		align:'left'},
					{display:'지번 주소',				name:'lnmAdres',			width:250,		sortable:false,		align:'left'},
					{display:'비고', 					name:'rm',					width:350, 		sortable:false,		align:'left'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumPrevCtrtAmt').val($.number(data.sumPrevCtrtAmt));
			module.$('#sumCurrCtrtAmt').val($.number(data.sumCurrCtrtAmt));
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.ctrtNo;
	});

	this.buildCtrtYear();

	this.$('#sCtrtYr').on('change', {module: this}, function(e) {
		var prevCtrtYr = e.data.module.$('#sCtrtYr').val() - 1;
		e.data.module.$('#sPrevCtrtYr').val(prevCtrtYr);
	});

	this.$("#sRegistEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getSearchEntrpsNm();
	});

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = false;

};


<%
/**
 * @FUNCTION NAME : buildCtrtYear
 * @DESCRIPTION   : CTRT YEAR를 구성한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.buildCtrtYear = function() {

	var toDay = new Date();
	var year = toDay.getFullYear();
	var option = "";
	for (var i = 2000; i <= year; i++){
		option = option + "<option value='" + i + "'>" + i + "년</option>";
	}
	this.$("#sCtrtYr").append(option);
	this.$('#sCtrtYr').val(year);
	var prevCtrtYr = year - 1;
	this.$('#sPrevCtrtYr').val(prevCtrtYr);

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
GamFcltyCtrtSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupSearchEntrpsCd':
			if (msg == 'ok') {
				this.$('#sEntrpsCd').val(value.entrpscd);
				this.$('#sEntrpsNm').val(value.entrpsNm);
				this.$('#sStartCtrtAmt').focus();
			}
			break;
	}

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel(buttonId);
			break;
		case 'popupSearchEntrpsCd':
			this.doExecuteDialog(buttonId, '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
	}

};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = true;
	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.selectData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (this._mainmode == 'query') {
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._mainmode != 'insert' && this._mainmode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;

};

<%
/**
 * @FUNCTION NAME : getSearchEntrpsNm
 * @DESCRIPTION   : 조회조건 업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.getSearchEntrpsNm = function() {

	var sRegistEntrpsCd = this.$('#sRegistEntrpsCd').val();
	if (sRegistEntrpsCd.length == 8) {
		var searchVO = { 'sEntrpscd':sRegistEntrpsCd };
		this.doAction('/ctrt/gamSelectFcltyCtrtSttusInqireEntrpsInfo.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#sRegistEntrpsNm').val(result.result.entrpsNm);
			}
		});
	} else {
		this.$('#sRegistEntrpsNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyCtrtSttusInqireModule.prototype.downloadExcel = function(buttonId) {

	var gridRowCount = 0;
	switch (buttonId) {
		case 'btnExcelDownload':
			gridRowCount = this.$("#mainGrid").flexRowCount();
			break;
		default:
			return;
	}
	if (gridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	switch (buttonId) {
		case 'btnExcelDownload':
			this.$('#mainGrid').flexExcelDown('/ctrt/gamExcelDownloadFcltyCtrtSttusInqire.do');
			break;
	}

};

var module_instance = new GamFcltyCtrtSttusInqireModule();


</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>계약 구분</th>
							<td>
								<select id="sCtrtSe">
									<option value="" selected>전체</option>
									<option value="1">공사</option>
									<option value="2">용역</option>
									<option value="3">지급자재</option>
								</select>
							</td>
							<th>계약 명</th>
							<td>
								<input id="sCtrtNm" type="text" size="31" maxlength="100">
							</td>
							<th>등록 업체</th>
							<td>
								<input id="sRegistEntrpsCd" type="text" size="6" maxlength="8">&nbsp; &nbsp;
								<input id="sRegistEntrpsNm" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
								<button id="popupSearchRegistEntrpsCd" class="popupButton">선택</button>
							</td>
							<td rowspan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>계약 년도</th>
							<td>
								<select id="sCtrtYr"></select>
								<input type="hidden" id="sPrevCtrtYr">
							</td>
							<th>계약 기간</th>
							<td>
								<input id="sStartCtrtDt" type="text" class="emdcal" size="8"> ∼
								<input id="sEndCtrtDt" type="text" class="emdcal" size="8">
							</td>
							<th>계약 금액</th>
							<td>
								<input id="sStartCtrtAmt" type="text" class="ygpaNumber" size="19" maxlength="20"> ∼
								<input id="sEndCtrtAmt" type="text" class="ygpaNumber" size="19" maxlength="20">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">계약 이력 정보</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:12%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="6" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:12%; height:20; text-align:center;">이전년도 거래금액</th>
								<td><input type="text" size="20" id="sumPrevCtrtAmt" class="ygpaNumber" disabled="disabled"/></td>
								<th style="width:12%; height:20; text-align:center;">거래금액</th>
								<td><input type="text" size="20" id="sumCurrCtrtAmt" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
	                                <button data-role="printPage" data-search-option="searchForm" data-url='/ctrt/gamSelectFcltyCtrtSttusInqirePrint.do'>계약 이력 출력</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
