<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamEnergyUsageMng.jsp
 * @Description : 에너지 사용량 계수 관리
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
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamEnergyUsageMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamEnergyUsageMngModule() {}

GamEnergyUsageMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectEnergyUsageMng.do',
		dataType : 'json',
		colModel : [
					{display:'연료 코드', 			name:'fuelCd',				width:80, 		sortable:false,		align:'center'	},
					{display:'연료 명', 			name:'fuelNm',				width:150, 		sortable:false,		align:'left'	},
					{display:'에너지 단위', 		name:'energyUnit',			width:100, 		sortable:false,		align:'left'	},
					{display:'에너지 총발열량', 	name:'energyTotalCalVal',	width:100, 		sortable:false,		align:'right'	},
					{display:'에너지 순발열량',		name:'energyNetCalVal',		width:100, 		sortable:false,		align:'right'	},
					{display:'온실가스 단위', 		name:'grHseUnit',			width:100, 		sortable:false,		align:'left'	},
					{display:'온실가스 계수', 		name:'grHseCoef',			width:100, 		sortable:false,		align:'right'	}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

    this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngYear + row.fuelCd;
		module.enableListButtonItem();
    });

    this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.mngYear + row.fuelCd;
		module.$("#mainTab").tabs("option", {active: 1});
    });

	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.drawChart = function() {

	var grHseCoefArr=[];
	var maxGrHseCoef=0;
	var grHseCoef=0;
	var mngYear=0;
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/mngFee/gamEnergyUsageMngChart.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			for (var i=0; i<10; i++) {
				mngYear=result.resultList[i]['mngYear']*1;
				grHseCoef=result.resultList[i]['grHseCoef']*1;
				grHseCoefArr[i]={year: mngYear, gauge: grHseCoef};
				if (maxGrHseCoef<grHseCoef) {
					maxGrHseCoef=grHseCoef;
				}
			};
		} else {
			for (var i=0; i<10; i++) {
				grHseCoef=0;
				grHseCoefArr[i]={year: (1999+1), gauge: grHseCoef};
			};
		}
		if (maxGrHseCoef<1) {
			maxGrHseCoef=1;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#energyUsageChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				label			: "#gauge#",
				tooltip			: "#gauge# 온실가스 계수",
				xAxis			: {
					title 		: "에너지 사용량 계수 현황",
					template	: "#year# 년"
				},
				yAxis			: {
					start		: 0,
					end			: 1,
					step		: (maxGrHseCoef / 10),
					title		: "온실가스 계수"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : 1,
				step : (maxGrHseCoef / 10),
				title : "온실가스 계수"
			});
		}
		module.barChart.parse(grHseCoefArr, "json");
		module.barChart.refresh();
	});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamEnergyUsageMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			if (this._mode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnRemove':
			this.deleteData();
			break;
		case 'btnCopy':
			this.copyData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
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
GamEnergyUsageMngModule.prototype.onSubmit = function() {

	this._mode = 'query';
	this._mainKeyValue = '';
	this.loadData();
	this.enableListButtonItem();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : refreshData
 * @DESCRIPTION   : DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
 GamEnergyUsageMngModule.prototype.refreshData = function() {

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamEnergyUsageMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/mngFee/gamSelectEnergyUsageMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.selectData = function() {

	if (this._mode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		return;
	} else if (this._mode != 'insert' && this._mode != 'modify') {
		return;
	}
	var mainKeyValue = this._mainKeyValue;
	if (mainKeyValue == "") {
		return;
	}
	this._mode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.drawChart();

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.addData = function() {

	var mngYear = this.$('#sMngYear').val();
	if (mngYear == "") {
		mngYear = new Date().getFullYear();
	}
	this.$('#mngYear').val(mngYear);
	this.$('#fuelCd').val("");
	this.$('#fuelNm').val("");
	this.$('#energyUnit').val("");
	this.$('#energyTotalCalVal').val("0");
	this.$('#energyNetCalVal').val("0");
	this.$('#grHseUnit').val("");
	this.$('#grHseCoef').val("0");
	this.enableDetailInputItem();
	this.$('#mngYear').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mngYear = this.$('#mngYear').val();
	var fuelCd = this.$('#fuelCd').val();
	var fuelNm = this.$('#fuelNm').val();
	var energyTotalCalVal = Number(this.$('#energyTotalCalVal').val().replace(/,/gi, ""));
	var energyNetCalVal = Number(this.$('#energyNetCalVal').val().replace(/,/gi, ""));
	var grHseCoef = Number(this.$('#grHseCoef').val().replace(/,/gi, ""));
	if (mngYear > "9999"  || mngYear < "2000" || mngYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngYear").focus();
		return;
	}
	if (fuelCd == "" || fuelCd.length != 4) {
		alert('연료 코드가 부정확합니다.');
		this.$("#fuelCd").focus();
		return;
	}
	if (fuelNm == "") {
		alert('연료 명이 부정확합니다.');
		this.$("#fuelNm").focus();
		return;
	}
	if (energyTotalCalVal >= 999.99 || energyTotalCalVal < 0) {
		alert('에너지 총발열량이 부정확합니다. (0 ~ 999.99)');
		this.$("#energyTotalCalVal").focus();
		return;
	}
	if (energyNetCalVal > 999.99 || energyNetCalVal < 0) {
		alert('에너지 순발열량이 부정확합니다. (0 ~ 999.99)');
		this.$("#energyNetCalVal").focus();
		return;
	}
	if (grHseCoef > 0.9999 || grHseCoef < 0) {
		alert('온실가스 계수가 부정확합니다. (0 ~ 0.9999)');
		this.$("#grHseCoef").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = mngYear + fuelCd;
		this.doAction('/mngFee/gamInsertEnergyUsageMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateEnergyUsageMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.deleteData = function() {

	var mngYear = this.$('#mngYear').val();
	var fuelCd = this.$('#fuelCd').val();
	if (mngYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngYear").focus();
		return;
	}
	if (fuelCd == "") {
		alert('연료 코드가 부정확합니다.');
		this.$("#fuelCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteEnergyUsageMng.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				this._mode = 'query';
				this._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : 년도별 항목을 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryMngYear = this.$('#sMngYear').val();
	var yearCnt = 0;
	if (confirm("이전년도의 자료를 [" + sQueryMngYear + "년] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('/mngFee/gamSelectEnergyUsageMngYearCnt.do', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		yearCnt=result.resultList[0]['yearCnt']*1;
		if (yearCnt > 0) {
			alert('[' + sQueryMngYear + '년] 자료가 존재합니다.');
			return;
		}
		module.doAction('/mngFee/gamCopyEnergyUsageMng.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				this._mode = 'query';
				this._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	});

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelEnergyUsageMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.enableListButtonItem = function() {

	if (this._mode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : enableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#mngYear').enable();
		this.$('#fuelCd').enable();
		this.$('#fuelNm').enable();
		this.$('#energyUnit').enable();
		this.$('#energyTotalCalVal').enable();
		this.$('#energyNetCalVal').enable();
		this.$('#grHseUnit').enable();
		this.$('#grHseCoef').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#mngYear').disable();
			this.$('#fuelCd').disable();
			this.$('#fuelNm').enable();
			this.$('#energyUnit').enable();
			this.$('#energyTotalCalVal').enable();
			this.$('#energyNetCalVal').enable();
			this.$('#grHseUnit').enable();
			this.$('#grHseCoef').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#mngYear').disable();
			this.$('#fuelCd').disable();
			this.$('#fuelNm').disable();
			this.$('#energyUnit').disable();
			this.$('#energyTotalCalVal').disable();
			this.$('#energyNetCalVal').disable();
			this.$('#grHseUnit').disable();
			this.$('#grHseCoef').disable();
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.disableDetailInputItem = function() {

	this.$('#mngYear').disable();
	this.$('#fuelCd').disable();
	this.$('#fuelNm').disable();
	this.$('#energyUnit').disable();
	this.$('#energyTotalCalVal').disable();
	this.$('#energyNetCalVal').disable();
	this.$('#grHseUnit').disable();
	this.$('#grHseCoef').disable();
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});

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
GamEnergyUsageMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mode=="insert") {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			this.drawChart();
			break;
	}

};

var module_instance = new GamEnergyUsageMngModule();

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
							<th>에너지 사용년도</th>
							<td>
								<select id="sMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
							</td>
							<th>연료 코드</th>
							<td>
								<input type="text" size="10" id="sFuelCd">
							</td>
							<th>연료 명</th>
							<td>
								<input type="text" size="20" id="sFuelNm">
							</td>
							<td>
								<button class="buttonSearch">조회</button>
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
				<li><a href="#listTab" class="emdTab">에너지 사용량</a></li>
				<li><a href="#detailTab" class="emdTab">에너지 사용량 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th style="width:20%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd">추가</button>
									<button id="btnDelete">삭제</button>
									<button id="btnCopy">이전년도 자료 복사</button>
	                                <button id="btnExcelDownload">엑셀다운로드</button>
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
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:15%; height:26;">관리 년도</th>
								<td>
									<select id="mngYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
								</td>
								<td rowspan="12" style="padding-left:4px;">
									<div id="energyUsageChart" style="width:515px;height:415px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">연료 코드</th>
								<td><input type="text" size="20" id="fuelCd" /></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">연료 명</th>
								<td><input type="text" size="20" id="fuelNm" /></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">에너지 단위</th>
								<td><input type="text" size="20" id="energyUnit" /></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">에너지 총발열량</th>
								<td><input type="text" size="20" id="energyTotalCalVal"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">에너지 순발열량</th>
								<td><input type="text" size="20" id="energyNetCalVal"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">온실가스 단위</th>
								<td><input type="text" size="20" id="grHseUnit" /></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">온실가스 계수</th>
								<td><input type="text" size="20" id="grHseCoef"/></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">등록일시</th>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
							</tr>
							<tr>
								<th style="width:15%; height:26;">수정일시</th>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right;">
								<button id="btnSave" class="buttonSave">저장</button>
								<button id="btnRemove" class="buttonDelete">삭제</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
