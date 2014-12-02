<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsFeeMngNtic.jsp
 * @Description : 시설물 관리비 고지
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.11.22  lee          최초 생성
 *
 * author domh
 * since 2014.11.22
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamFcltsFeeMngNticModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsFeeMngNticModule() {}

GamFcltsFeeMngNticModule.prototype = new EmdModule(1000, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngNtic.do',
		dataType : 'json',
		colModel : [
					{display:'관리 월',				name:'mngYrMt',				width:60,		sortable:false,		align:'center'},
					{display:'업무 구분',			name:'mngFeeJobSeNm',		width:80,		sortable:false,		align:'center'},
					{display:'업체 명',				name:'entrpsNm',			width:150,		sortable:false,		align:'left'},
					{display:'관리비 제목',			name:'mngFeeSj',			width:150,		sortable:false,		align:'left'},
					{display:'사용 면적',			name:'usageAr',				width:70,		sortable:false,		align:'right'},
					{display:'고지 일자',			name:'nticDt',				width:80,		sortable:false,		align:'center'},
					{display:'요금 종류',			name:'chrgeKndNm',			width:100,		sortable:false,		align:'left'},
					{display:'회계 년도',			name:'accnutYear',			width:80,		sortable:false,		align:'center'},
					{display:'고지 번호',			name:'nticNo',				width:80,		sortable:false,		align:'center'},
					{display:'사용료',				name:'fee',					width:90,		sortable:false,		align:'right'},
					{display:'부가세',				name:'vat',					width:90,		sortable:false,		align:'right'},
					{display:'고지 금액',			name:'nticAmt',				width:90,		sortable:false,		align:'right'},
					{display:'관리 비',				name:'mngFee',				width:90,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'elctyFee',			width:90,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'waterFee',			width:90,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'gasFee',				width:90,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mngTotalFee',			width:90,		sortable:false,		align:'right'},
					{display:'납부 기한',			name:'payTmlmt',			width:80,		sortable:false,		align:'center'},
					{display:'수납 구분',			name:'rcivSeNm',			width:80,		sortable:false,		align:'center'},
					{display:'수납 일자',			name:'rcivDt',				width:80,		sortable:false,		align:'center'},
					{display:'부가세 구분',			name:'vatYn',				width:80,		sortable:false,		align:'center'},
					{display:'고지 여부',			name:'nhtIsueYn',			width:80,		sortable:false,		align:'center'},
					{display:'출력 여부',			name:'nhtOutputYn',			width:80,		sortable:false,		align:'center'},
					{display:'추가 고지 여부',		name:'aditNticYn',			width:90,		sortable:false,		align:'center'},
					{display:'연체 번호',			name:'arrrgNo',				width:80,		sortable:false,		align:'center'},
					{display:'연체 금액',			name:'arrrgAmt',			width:90,		sortable:false,		align:'right'},
					{display:'연체 요율',			name:'arrrgTariff',			width:80,		sortable:false,		align:'right'},
					{display:'연체 일수',			name:'arrrgPayDates',		width:80,		sortable:false,		align:'right'},
					{display:'요금 종류 코드',		name:'chrgeKnd',			width:100,		sortable:false,		align:'left'},
					{display:'항 코드',				name:'prtAtCode',			width:100,		sortable:false,		align:'left'}
                    ],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.$('#sumFee').val(data.sumFee);
			module.$('#sumVat').val(data.sumVat);
			module.$('#sumNticAmt').val(data.sumNticAmt);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module.$("#mainTab").tabs("option", {active: 1});
	});

	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sStartMngMt').val(mon);
	this.$('#sEndMngMt').val(mon);

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
GamFcltsFeeMngNticModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupEntrpscd':
			if (msg == 'ok') {
				this.$('#sEntrpscd').val(value.entrpscd);
				this.$('#sEntrpsNm').val(value.entrpsNm);
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
GamFcltsFeeMngNticModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'popupEntrpscd':
			this.doExecuteDialog('popupEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
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
GamFcltsFeeMngNticModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.makeFormValues('#detailForm', row[0]);
	this.makeDivValues('#detailForm', row[0]);

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngNticModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelFcltsFeeMngNtic.do');

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
GamFcltsFeeMngNticModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
			}
			break;
	}

};

var module_instance = new GamFcltsFeeMngNticModule();

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
                            <th>관리 기간</th>
							<td>
								<select id="sStartMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
								<select id="sStartMngMt">
									<option value="01" selected>01월</option>
									<option value="02">02월</option>
									<option value="03">03월</option>
									<option value="04">04월</option>
									<option value="05">05월</option>
									<option value="06">06월</option>
									<option value="07">07월</option>
									<option value="08">08월</option>
									<option value="09">09월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								</select>
								&nbsp; ~ &nbsp;
								<select id="sEndMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
								<select id="sEndMngMt">
									<option value="01" selected>01월</option>
									<option value="02">02월</option>
									<option value="03">03월</option>
									<option value="04">04월</option>
									<option value="05">05월</option>
									<option value="06">06월</option>
									<option value="07">07월</option>
									<option value="08">08월</option>
									<option value="09">09월</option>
									<option value="10">10월</option>
									<option value="11">11월</option>
									<option value="12">12월</option>
								</select>
							</td>
							<th>업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th>고지 업체</th>
							<td>
                            	<input id="sEntrpscd" type="text" size="6" readonly>&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpscd" class="popupButton">선택</button>
							</td>
							<td rowSpan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>고지 기간</th>
							<td>
								<input id="sStartNticDt" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sEndNticDt" size="8"> ~
								<input id="sEndNticDt" type="text" class="emdcal" data-role="dtTo" data-dt-from="sStartNticDt" size="8">
							</td>
							<th>수납 구분</th>
							<td>
								<select id="sRcivSe">
									<option value="" selected>전체</option>
									<option value="0">미수납</option>
									<option value="1">연체</option>
									<option value="3">수납</option>
									<option value="2">연체수납</option>
									<option value="4">불납</option>
								</select>
							</td>
							<th>요금종류</th>
							<td>
								<input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" />
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
				<li><a href="#listTab" class="emdTab">시설물 관리비 고지현황</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리비 고지내역</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th width="10%" height="20">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총사용료</th>
								<td><input type="text" size="15" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총부가세</th>
								<td><input type="text" size="15" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">총고지금액</th>
								<td><input type="text" size="15" id="sumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
					<button id="btnProcessNticIssue">선택자료 고지</button>
					<button id="btnCancelNticIssue">요금고지 취소</button>
					<button id="btnPrintNticIssue">고 지 서 출력</button>
					<button id="btnPrintTaxInvoice">계 산 서 출력</button>
					<button id="btnAddNticIssue">추가고지 입력</button>
					<button id="btnDelNticIssue">추가고지 삭제</button>
					<button id="btnExcelDownload">엑셀 다운로드</button>
					<button id="btnFcltsFeeMngInqire">납부현황 조회</button>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 부과 내역</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="18">관리 년월</th>
								<td>
									<input id="mngMt" type="hidden" />
									<input id="mngMtYear" type="hidden" />
									<input id="mngMtMon" type="hidden" />
									<input type="text" size="20" id="mngYrMt" disabled>
								</td>
								<th width="15%" height="18">업무 구분</th>
								<td>
									<input id="mngFeeJobSe" type="hidden" />
									<input id="mngSeq" type="hidden" />
									<input id="reqestSeq" type="hidden" />
									<input type="text" size="20" id="mngFeeJobSeNm" disabled>
								</td>
								<th width="15%" height="18">사용 업체</th>
								<td>
									<input id="entrpscd" type="hidden" />
									<input type="text" size="20" id="entrpsNm" disabled>
								</td>
							</tr>
                            <tr>
								<th width="15%" height="18">관리비 제목</th>
                                <td colspan="3"><input type="text" size="76" id="mngFeeSj" readonly/></td>
								<th width="15%" height="18">사용 면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="usageAr" readonly/></td>
                            </tr>
                            <tr>
								<th width="15%" height="18">시설 관리 용역비</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="mngFee" readonly/></td>
								<th width="15%" height="18">전기 요금</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="elctyFee" readonly/></td>
								<th width="15%" height="18">상하수도 요금</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="waterFee" readonly/></td>
                            </tr>
                            <tr>
								<th width="15%" height="18">도시가스 요금</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="gasFee" readonly/></td>
								<th width="15%" height="18">환경개선 부담금</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="envFee" readonly/></td>
								<th width="15%" height="18">관리비 합계</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="mngTotalFee" readonly/></td>
                            </tr>
						</table>
						<table class="summaryPanel" style="width:100%">
							<tr>
								<td>시설물 관리비 고지 내역</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="18">요금 종류</th>
								<td>
									<input id="prtAtCode" type="hidden" />
									<input type="text" size="4" id="chrgeKnd" disabled>
									<input type="text" size="15" id="chrgeKndNm" disabled>
								</td>
								<th width="15%" height="18">고지 번호</th>
								<td>
									<input type="text" size="9" id="accnutYear" disabled>
									<input type="text" size="10" id="nticNo" disabled>
								</td>
								<th width="15%" height="18">고지 / 출력</th>
								<td>
									<input type="text" size="9" id="nhtIsueYn" disabled>
									<input type="text" size="10" id="nhtOutputYn" disabled>
								</td>
							</tr>
                            <tr>
								<th width="15%" height="18">사용료</th>
								<td>
									<input type="text" size="20" class="ygpaNumber" id="fee" readonly/>
								</td>
								<th width="15%" height="18">부가세</th>
								<td>
									<input type="text" size="6" id="vatYn" disabled>
									<input type="text" size="12" class="ygpaNumber" id="vat" readonly/>
								</td>
								<th width="15%" height="18">고지 금액</th>
								<td>
									<input type="text" size="20" class="ygpaNumber" id="nticAmt" readonly/>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="18">고지 일자</th>
                                <td>
                                	<input type="text" size="20" id="nticDt" readonly/>
                                </td>
								<th width="15%" height="18">납부 기한</th>
                                <td>
                                	<input type="text" size="20" id="payTmlmt" readonly/>
                                </td>
								<th width="15%" height="18">수납 일자</th>
                                <td>
									<input type="text" size="6" id="rcivSeNm" disabled>
                                	<input type="text" size="12" id="rcivDt" readonly/>
                                </td>
                            </tr>
                            <tr>
								<th width="15%" height="18">연체 번호</th>
								<td>
									<input type="text" size="20" id="arrrgNo" disabled>
								</td>
								<th width="15%" height="18">연체 일수</th>
								<td>
									<input type="text" size="20" class="ygpaNumber" id="arrrgPayDates" readonly/>
								</td>
								<th width="15%" height="18">연체 금액</th>
								<td>
									<input type="text" size="20" class="ygpaNumber" id="arrrgAmt" readonly/>
								</td>
                            </tr>
                            <tr>
								<th width="15%" height="18">비고</th>
								<td colspan="5" >
									<textarea rows="3" cols="85" data-column-id='rm'></textarea>
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
