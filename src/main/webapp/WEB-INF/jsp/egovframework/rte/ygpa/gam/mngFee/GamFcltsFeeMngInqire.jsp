<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsFeeMngInqire.jsp
 * @Description : 시설물 관리비 납부현황 조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  Lee          최초 생성
 *
 * author Lee
 * since 2014.09.22
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
 * @FUNCTION NAME : GamFcltsFeeMngInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsFeeMngInqireModule() {}

GamFcltsFeeMngInqireModule.prototype = new EmdModule(1000, 645);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.loadComplete = function(params) {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngInqire.do',
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
					{display:'납부 기한',			name:'payTmlmt',			width:80,		sortable:false,		align:'center'},
					{display:'관리 비',				name:'mngFee',				width:90,		sortable:false,		align:'right'},
					{display:'전기 요금',			name:'elctyFee',			width:90,		sortable:false,		align:'right'},
					{display:'상하수도 요금',		name:'waterFee',			width:90,		sortable:false,		align:'right'},
					{display:'도시가스 요금',		name:'gasFee',				width:90,		sortable:false,		align:'right'},
					{display:'관리비 합계',			name:'mngTotalFee',			width:90,		sortable:false,		align:'right'},
					{display:'수납 구분',			name:'rcivSeNm',			width:80,		sortable:false,		align:'center'},
					{display:'수납 일자',			name:'rcivDt',				width:80,		sortable:false,		align:'center'},
					{display:'부가세 구분',			name:'vatYnNm',				width:80,		sortable:false,		align:'center'},
					{display:'고지 여부',			name:'nhtIsueYn',			width:80,		sortable:false,		align:'center'},
					{display:'출력 여부',			name:'nhtPrintYn',			width:80,		sortable:false,		align:'center'},
					{display:'추가 고지 여부',		name:'aditNticYn',			width:90,		sortable:false,		align:'center'},
					{display:'최초 고지 일자',		name:'firstNticDt',			width:100,		sortable:false,		align:'center'},
					{display:'최초 납부 기한',		name:'firstPayTmlmt',		width:100,		sortable:false,		align:'center'},
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

	this.$("#unpaidGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectFcltsFeeMngInqireUnpaid.do',
		dataType : 'json',
		colModel : [
					{display:'연체 순번', 		name:'dlySerNo',		width:70,		sortable:false,		align:'center'},
					{display:'회계 년도',		name:'fiscalYr',		width:70,		sortable:false,		align:'center'},
					{display:'고지 번호',		name:'billNo',			width:70,		sortable:false,		align:'center'},
					{display:'고지 일자',		name:'dlyBillDt',		width:80,		sortable:false,		align:'center'},
					{display:'납부 기한',		name:'dlyDueDt',		width:80,		sortable:false,		align:'center'},
					{display:'연체 금액',		name:'dlyBillAmnt',		width:90,		sortable:false,		align:'right'},
					{display:'연체 요율',		name:'arrrgTriff',		width:70,		sortable:false,		align:'right'},
					{display:'연체 일수',		name:'arrrgPayDates',	width:70,		sortable:false,		align:'right'},
					{display:'출력 여부',		name:'dlyBillPrtYn',	width:70,		sortable:false,		align:'center'},
					{display:'수납 구분',		name:'dlyRcvdTpNm',		width:70,		sortable:false,		align:'center'},
					{display:'수납 일자',		name:'dlyRcvdDt',		width:80,		sortable:false,		align:'center'},
					{display:'산출 내역',		name:'dlyBillRsn',		width:200,		sortable:false,		align:'left'},
					{display:'지로 금액',		name:'djiroAmnt',		width:90,		sortable:false,		align:'right'},
					{display:'이전 고지 일자',	name:'prvBillDt',		width:100,		sortable:false,		align:'center'},
					{display:'이전 납부 기한',	name:'prvDueDt',		width:100,		sortable:false,		align:'center'}
					],
		showTableToggleBtn : true,
		height : '270',
		width : '698'
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getQueryEntrpsNm();
	});

	this._mainmode = '';
	this._searchButtonClick = false;
	if (params != null) {
		if (params.action == "selectFcltsFeeMngInqire") {
        	this.$('#sStartMngYear').val(params.paramVo.mngMtYear);
        	this.$('#sStartMngMt').val(params.paramVo.mngMtMon);
        	this.$('#sEndMngYear').val(params.paramVo.mngMtYear);
        	this.$('#sEndMngMt').val(params.paramVo.mngMtMon);
        	this.$('#sMngFeeJobSe').val(params.paramVo.mngFeeJobSe);
        	var searchOpt=this.makeFormArgs('#searchForm');
        	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
		}
	} else {
		var mon = new Date().getMonth() + 1;
		if (mon > 0 && mon < 10) {
			mon = "0" + mon;
		} else {
			mon = "" + mon;
		}
		this.$('#sStartMngMt').val(mon);
		this.$('#sEndMngMt').val(mon);
	}
	this._detailDisplay = 'unpaid';
	this.$('#fcltsFeeMngSttusChart').hide();

};

<%
/**
 * @FUNCTION NAME : isValidYear
 * @DESCRIPTION   : YEAR STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. yearString - YEAR STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsFeeMngInqireModule.prototype.isValidYear = function(yearString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (yearString == "") {
			return false;
		}
	} else {
		if (yearString == "") {
			return true;
		}
	}
	var year = Number(yearString);
	if (year > 9999 || year < 1900) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidMonth
 * @DESCRIPTION   : MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. monthString - MONTH STRING
 *   2. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsFeeMngInqireModule.prototype.isValidMonth = function(monthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (monthString == "") {
			return false;
		}
	} else {
		if (monthString == "") {
			return true;
		}
	}
	var month = Number(monthString);
	if (month > 12 || month < 1) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : isValidMonthFromTo
 * @DESCRIPTION   : 기간 MONTH STRING에 대한 VALIDATION을 검사한다.
 * @PARAMETER     :
 *   1. startMonthString - START MONTH STRING
 *   2. endMonthString - END MONTH STRING
 *   3. nullCheckFlag - NULL CHECK FLAG
**/
%>
GamFcltsFeeMngInqireModule.prototype.isValidMonthFromTo = function(startMonthString, endMonthString, nullCheckFlag) {

	if (nullCheckFlag == true) {
		if (startMonthString == "" || endMonthString == "") {
			return false;
		}
	} else {
		if (startMonthString == "" && endMonthString == "") {
			return true;
		}
	}
	var startMonth = Number(startMonthString.replace(/-/gi, ""));
	var endMonth = Number(endMonthString.replace(/-/gi, ""));
	if (startMonth > endMonth) {
		return false;
	}
	return true;

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.drawChart = function() {

	var feeArr=[];
	var maxFee=0;
	var fee=0;
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamSelectFcltsFeeMngInqireChart.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			for (var i=0; i<12; i++) {
				fee=result.resultList[i]['fee']*1;
				feeArr[i]={month: (i+1), gauge: fee};
				if (maxFee<fee) {
					maxFee=fee;
				}
			};
		} else {
			for (var i=0; i<12; i++) {
				fee=0;
				feeArr[i]={month: (i+1), gauge: fee};
			};
		}
		if (maxFee<10) {
			maxFee=10;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#fcltsFeeMngSttusChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#gauge#",
				tooltip			: "#gauge# 원",
				xAxis			: {
					title 		: "관리비 고지 현황",
					template	: "#month# 월"
				},
				yAxis			: {
					start		: 0,
					end			: maxFee + 10,
					step		: Math.ceil(maxFee / 10),
					title		: "사용료,원"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : maxFee + 10,
				step : Math.ceil(maxFee / 10),
				title : "사용료,원"
			});
		}
		module.barChart.parse(feeArr, "json");
		module.barChart.refresh();
	});

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
GamFcltsFeeMngInqireModule.prototype.onClosePopup = function(popupId, msg, value) {

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
GamFcltsFeeMngInqireModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnRcivDataUpdate':
	    	this.updateRcivData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'popupEntrpscd':
			this.doExecuteDialog('popupEntrpscd', '업체 선택', '/popup/showEntrpsInfo.do', null);
			break;
		case 'btnArrrgList':
			if (this._detailDisplay != 'unpaid') {
				this._detailDisplay = 'unpaid';
				this.$('#fcltsFeeMngSttusChart').hide();
				this.$('#unpaidGrid').show();
				this.loadUnpaidData();
			}
			break;
		case 'btnNticGraph':
			if (this._detailDisplay != 'graph') {
				this._detailDisplay = 'graph';
				this.$('#unpaidGrid').hide();
				this.drawChart();
				this.$('#fcltsFeeMngSttusChart').show();
			}
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
GamFcltsFeeMngInqireModule.prototype.onSubmit = function() {

	var sStartMngYear = this.$('#sStartMngYear').val();
	var sStartMngMt = this.$('#sStartMngMt').val();
	var sEndMngYear = this.$('#sEndMngYear').val();
	var sEndMngMt = this.$('#sEndMngMt').val();
	if (this.isValidYear(sStartMngYear, true) == false) {
		alert('시작 관리 년도가 부정확합니다.');
		this.$("#sStartMngYear").focus();
		return;
	}
	if (this.isValidMonth(sStartMngMt, true) == false) {
		alert('시작 관리 월이 부정확합니다.');
		this.$("#sStartMngMt").focus();
		return;
	}
	if (this.isValidYear(sEndMngYear, true) == false) {
		alert('종료 관리 년도가 부정확합니다.');
		this.$("#sEndMngYear").focus();
		return;
	}
	if (this.isValidMonth(sEndMngMt, true) == false) {
		alert('종료 관리 월이 부정확합니다.');
		this.$("#sEndMngMt").focus();
		return;
	}
	if (this.isValidMonthFromTo(sStartMngYear + sStartMngMt, sEndMngYear + sEndMngMt, true) == false) {
		alert('시작 관리 년월이 종료 관리 년월보다 큽니다.');
		this.$("#sStartMngYear").focus();
		return;
	}
	this._mainmode="query";
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
GamFcltsFeeMngInqireModule.prototype.loadData = function() {

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
GamFcltsFeeMngInqireModule.prototype.loadDetail = function() {

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
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.selectData = function() {

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
 * @FUNCTION NAME : loadUnpaidData
 * @DESCRIPTION   : UNPAID DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.loadUnpaidData = function() {

	var searchOpt=this.makeFormArgs('#detailForm');
	this.$('#unpaidGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : getQueryEntrpsNm
 * @DESCRIPTION   : 조회조건 고지업체 명을 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.getQueryEntrpsNm = function() {

	var sEntrpscd = this.$('#sEntrpscd').val();
	if (sEntrpscd.length == 8) {
		var searchVO = { 'sEntrpscd':sEntrpscd };
		this.doAction('/mngFee/gamSelectFcltsFeeMngInqireEntrpsNm.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.$('#sEntrpsNm').val(result.sEntrpsNm);
			}
		});
	} else {
		this.$('#sEntrpsNm').val('');
	}

};

<%
/**
 * @FUNCTION NAME : updateRcivData
 * @DESCRIPTION   : 수납여부를 갱신한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.updateRcivData = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	var all_rows = JSON.stringify(this.$('#mainGrid').flexGetData());
	var updateVO = [];
	updateVO[updateVO.length] = {name: 'updateList', value: all_rows};
	this.doAction('/soc/gamUpdateFcltsFeeMngInqire.do', updateVO, function(module, result) {
		if (result.resultCode == "0") {
			module.loadData();
		}
		alert(result.resultMsg);
 	});
};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsFeeMngInqireModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadFcltsFeeMngInqire.do');

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
GamFcltsFeeMngInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
			}
			if (this._detailDisplay == 'unpaid') {
				this.loadUnpaidData();
			} else {
				this.drawChart();
			}
			break;
	}

};

var module_instance = new GamFcltsFeeMngInqireModule();

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
                            <th style="width:6%; height:18;">관리 기간</th>
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
							<th style="width:6%; height:18;">업무 구분</th>
							<td>
								<select id="sMngFeeJobSe">
									<option value="">전체</option>
									<option value="M" selected>마린센터</option>
									<option value="E">전기시설</option>
								</select>
							</td>
							<th style="width:6%; height:18;">고지 업체</th>
							<td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpscd" class="popupButton">선택</button>
							</td>
							<td rowSpan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th style="width:6%; height:18;">고지 기간</th>
							<td>
								<input id="sStartNticDt" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sEndNticDt" size="12">
								&nbsp; ~ &nbsp;
								<input id="sEndNticDt" type="text" class="emdcal" data-role="dtTo" data-dt-from="sStartNticDt" size="12">
							</td>
							<th style="width:6%; height:18;">수납 구분</th>
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
							<th style="width:6%; height:18;">요금 종류</th>
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
				<li><a href="#listTab" class="emdTab">시설물 관리비 납부현황</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 관리비 납부내역</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:10%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총사용료</th>
								<td><input type="text" size="15" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총부가세</th>
								<td><input type="text" size="15" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
								<th style="width:10%; height:20; text-align:center;">총고지금액</th>
								<td><input type="text" size="15" id="sumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnRcivDataUpdate" class="buttonSave">수납여부　갱신</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 부과 내역</th>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:18;">관　리　년　월</th>
								<td>
									<input id="mngMt" type="hidden"/>
									<input id="mngMtYear" type="hidden"/>
									<input id="mngMtMon" type="hidden"/>
									<input id="reqestSeq" type="hidden"/>
									<input type="text" size="20" id="mngYrMt" disabled>
									<input type="text" size="11" id="mngSeq" disabled>
								</td>
								<th style="width:10%; height:18;">업　무　구　분</th>
								<td>
									<input id="mngFeeJobSe" type="hidden"/>
									<input type="text" size="33" id="mngFeeJobSeNm" disabled>
								</td>
								<th style="width:10%; height:18;">부　과　업　체</th>
								<td>
									<input id="entrpscd" type="hidden"/>
									<input type="text" size="33" id="entrpsNm" disabled>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:18;">관리비　　제목</th>
								<td colspan="3"><input type="text" size="92" id="mngFeeSj" disabled/></td>
								<th style="width:10%; height:18;">사　용　면　적</th>
								<td><input type="text" size="30" class="ygpaNumber" id="usageAr" disabled/> m<sup>2</sup></td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">시설관리용역비</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mngFee" disabled/> 원</td>
								<th style="width:10%; height:18;">전　기　요　금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="elctyFee" disabled/> 원</td>
								<th style="width:10%; height:18;">상하수도　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="waterFee" disabled/> 원</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:18;">도시가스　요금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="gasFee" disabled/> 원</td>
								<th style="width:10%; height:18;">환경개선부담금</th>
								<td><input type="text" size="30" class="ygpaNumber" id="envFee" disabled/> 원</td>
								<th style="width:10%; height:18;">관리비　　합계</th>
								<td><input type="text" size="30" class="ygpaNumber" id="mngTotalFee" disabled/> 원</td>
                            </tr>
						</table>
						<table class="summaryPanel" style="width:100%;">
							<tr>
								<th style="font-weight:bold; height:20px;">시설물 관리비 고지 내역</th>
								<td style="text-align:right;">
									<button id="btnArrrgList">연체 내역</button>
									<button id="btnNticGraph">고지현황 그래프</button>
								</td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:19px;">요　금　종　류</th>
								<td>
									<input id="prtAtCode" type="hidden"/>
									<input type="text" size="3" id="chrgeKnd" disabled>
									<input type="text" size="20" id="chrgeKndNm" disabled>
								</td>
								<td rowspan="10" style="padding-left:4px;">
									<table id="unpaidGrid" style="display:none;"></table>
									<div id="fcltsFeeMngSttusChart" style="width:698px;height:270px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">고지번호／추가</th>
								<td>
									<input type="text" size="6" id="accnutYear" disabled>
									<input type="text" size="9" id="nticNo" disabled>
									<input type="text" size="6" id="aditNticYn" disabled>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">고지/출력/구분</th>
								<td>
									<input id="vatYn" type="hidden"/>
									<input type="text" size="1" id="nhtIsueYn" disabled>
									<input type="text" size="1" id="nhtPrintYn" disabled>
									<input type="text" size="19" id="vatYnNm" disabled>
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">사　　용　　료</th>
								<td>
									<input type="text" size="22" class="ygpaNumber" id="fee" disabled/> 원
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">부　　가　　세</th>
								<td>
									<input type="text" size="22" class="ygpaNumber" id="vat" disabled/> 원
								</td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">고　지　금　액</th>
								<td>
									<input type="text" size="22" class="ygpaNumber" id="nticAmt" disabled/> 원
								</td>
                            </tr>
                            <tr>
								<th style="width:10%; height:19px;">(최초)고지일자</th>
                                <td>
                                	<input type="text" size="11" id="firstNticDt" disabled/> /
                                	<input type="text" size="11" id="nticDt" disabled/>
                                </td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">(최초)납부기한</th>
                                <td>
                                	<input type="text" size="11" id="firstPayTmlmt" disabled/> /
                                	<input type="text" size="11" id="payTmlmt" disabled/>
                                </td>
							</tr>
                            <tr>
								<th style="width:10%; height:19px;">수　납　일　자</th>
                                <td>
									<input id="rcivSe" type="hidden"/>
									<input type="text" size="9" id="rcivSeNm" disabled>
                                	<input type="text" size="14" id="rcivDt" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th style="width:10%; height:19px;">연체일수／금액</th>
								<td>
									<input type="text" size="3" id="arrrgNo" disabled>
									<input type="text" size="4" class="ygpaNumber" id="arrrgPayDates" disabled/>
									<input type="text" size="11" class="ygpaNumber" id="arrrgAmt" disabled/> 원
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
