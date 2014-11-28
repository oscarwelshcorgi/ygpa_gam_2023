<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamGrHseEmitQyMng.jsp
 * @Description : 온실가스 배출현황
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
 * @FUNCTION NAME : GamGrHseEmitQyMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamGrHseEmitQyMngModule() {}

GamGrHseEmitQyMngModule.prototype = new EmdModule(800, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectGrHseEmitQyMng.do',
		dataType : 'json',
		colModel : [
					{display:'연료 코드',			name:'fuelCd',			width:70, 		sortable:false,		align:'center'},
					{display:'연료 명',				name:'fuelNm',			width:110, 		sortable:false,		align:'left'},
					{display:'온실가스 계수',		name:'grHseCoef',		width:110, 		sortable:false,		align:'right'},
					{display:'관리 월',				name:'mngYrMt',			width:110, 		sortable:false,		align:'center'},
					{display:'사용 량',				name:'usageQy',			width:110, 		sortable:false,		align:'right'},
					{display:'에너지 사용 량',		name:'energyUsageQy',	width:110, 		sortable:false,		align:'right'},
					{display:'온실가스 배출 량',	name:'grHseEmitQy',		width:110, 		sortable:false,		align:'right'}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
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

	this.$('#usageQy').on('keyup change',{module:this}, function(event){
		event.data.module.calcGrHseEmitQy();
	});

	var mon = new Date().getMonth()+1;
	if (mon.length==1) {
		mon="0"+mon;
	}
	this.$('#sMngMt').val(mon);

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.drawChart = function() {
	var grHseEmitQyArr=[];
	var maxGrHseEmitQy=0;
	var grHseEmitQy=0;
	var searchVO = this.makeFormArgs("#detailForm");

	this.doAction('<c:url value="/mngFee/gamSelectGrHseEmitQyMngChart.do" />', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			for (var i=0; i<12; i++) {
				grHseEmitQy=result.resultList[i]['grHseEmitQy']*1;
				grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
				if (maxGrHseEmitQy<grHseEmitQy) {
					maxGrHseEmitQy=grHseEmitQy;
				}
			};
		} else {
			for (var i=0; i<12; i++) {
				grHseEmitQy=0;
				grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
			};
		}
		if (maxGrHseEmitQy<10) {
			maxGrHseEmitQy=10;
		}
		if (module.barChart==null) {
			module.barChart = new dhtmlXChart({
				view			: "bar",
				container		: module.$('#grHseEmitChart')[0],
				value			: "#gauge#",
				color			: "#000BE0",
	            gradient		: "rising",
				width			: 30,
				tooltip			: "온실가스 배출량(KgCO2)",
				xAxis			: {
					title 		: "온실가스 배출 현황",
					template	: "#month# 월"
				},
				yAxis			: {
					start		: 0,
					end			: maxGrHseEmitQy + 10,
					step		: Math.ceil(maxGrHseEmitQy / 10),
					title		: "온실가스 배출량,KgCO2"
				}
			});
		} else {
			module.barChart.clearAll();
			module.barChart.define("yAxis", {
				start : 0,
				end : maxGrHseEmitQy + 10,
				step : Math.ceil(maxGrHseEmitQy / 10),
				title : "온실가스 배출량,KgCO2"
			});
		}
		module.barChart.parse(grHseEmitQyArr, "json");
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
GamGrHseEmitQyMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupFuelCd':
			if (msg == 'ok') {
				this.$('#mngYear').val(value.mngYear);
				this.$('#fuelCd').val(value.fuelCd);
				this.$('#fuelNm').val(value.fuelNm);
				this.$('#energyUnit').val(value.energyUnit);
				this.$('#energyTotalCalVal').val(value.energyTotalCalVal);
				this.$('#energyNetCalVal').val(value.energyNetCalVal);
				this.$('#grHseUnit').val(value.grHseUnit);
				this.$('#grHseCoef').val(value.grHseCoef);
				this.$("#usageQy").focus();
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
GamGrHseEmitQyMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
	    	this.addData();
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			this.deleteData();
			break;
		case 'btnCopy':
			this.copyData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'popupFuelCd':
			this.doExecuteDialog('popupFuelCd', '연료 선택', '/popup/showFuelCode.do', null);
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
GamGrHseEmitQyMngModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.loadData = function() {

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
GamGrHseEmitQyMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$('#mngMtYear').disable();
	this.$('#mngMtMon').disable();
	this.$('#popupFuelCd').disable({disableClass:"ui-state-disabled"});
	this.makeFormValues('#detailForm', row[0]);
	this.makeDivValues('#detailForm', row[0]);

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.addData = function() {

	var mngMtYear = new Date().getFullYear();
	var mngMtMon = new Date().getMonth()+1;
	this._mode="insert";
	this.$("#mainTab").tabs("option", {active: 1});
	this.$('#mngMtYear').val(mngMtYear);
	if(mngMtMon.length==1) {
		mngMtMon="0"+mngMtMon;
	}
	this.$('#mngMtMon').val(mngMtMon);
	this.$('#usageQy').val('0');
	this.$('#energyUsageQy').val('0');
	this.$('#grHseEmitQy').val('0');

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var mngMtYear = this.$('#mngMtYear').val();
	var mngMtMon = this.$('#mngMtMon').val();
	var mngYear = this.$('#mngYear').val();
	var fuelCd = this.$('#fuelCd').val();
	if (mngMtYear > "9999"  || mngMtYear < "2000" || mngMtYear == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngMtYear").focus();
		return;
	}
	if (mngMtMon > "12"  || mngMtMon < "01" || mngMtMon == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMtMon").focus();
		return;
	}
	if (mngYear > "9999"  || mngYear < "2000" || mngYear == "") {
		alert('연료 코드 관리 년도가 부정확합니다.');
		//this.$("#fuelCd").focus();
		return;
	}
	if (fuelCd == "" || fuelCd.length != 4) {
		alert('연료 코드가 부정확합니다.');
		//this.$("#fuelCd").focus();
		return;
	}
	if (this._mode == "insert") {
		this.doAction('/mngFee/gamInsertGrHseEmitQyMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateGrHseEmitQyMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
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
GamGrHseEmitQyMngModule.prototype.deleteData = function() {

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#mngMtYear').val() == "") {
		alert('관리 년도가 부정확합니다.');
		this.$("#mngMtYear").focus();
		return;
	}
	if (this.$('#mngMtMon').val() == "") {
		alert('관리 월이 부정확합니다.');
		this.$("#mngMtMon").focus();
		return;
	}
	if (this.$('#mngYear').val() == "") {
		alert('연료 코드 관리 년도가 부정확합니다.');
		//this.$("#fuelCd").focus();
		return;
	}
	if (this.$('#fuelCd').val() == "") {
		alert('연료 코드가 부정확합니다.');
		//this.$("#fuelCd").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('/mngFee/gamDeleteGrHseEmitQyMng.do', row[0], function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : copyData
 * @DESCRIPTION   : 월별 항목을 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.copyData = function() {

	var searchVO = this.makeFormArgs("#searchForm");
	var sQueryMngYear = this.$('#sMngYear').val();
	var sQueryMngMt = this.$('#sMngMt').val();
	var mtCnt=0;
	if (confirm("이전월의 자료를 [" + sQueryMngYear + "-" + sQueryMngMt + "월] 자료로 복사하시겠습니까?") != true) {
		return;
	}
	this.doAction('<c:url value="/mngFee/gamSelectGrHseEmitQyMngMonthCnt.do" />', searchVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('자료 확인이 실패했습니다!');
			return;
		}
		mtCnt=result.resultList[0]['mtCnt']*1;
		if (mtCnt > 0) {
			alert('[' + sQueryMngYear + '-' + sQueryMngMt + '월] 자료가 존재합니다.');
			return;
		}
		module.doAction('<c:url value="/mngFee/gamCopyGrHseEmitQyMng.do" />', searchVO, function(module, result) {
			if (result.resultCode == "0") {
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
GamGrHseEmitQyMngModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelGrHseEmitQyMng.do');

};

<%
/**
 * @FUNCTION NAME : calcGrHseEmitQy
 * @DESCRIPTION   : 온실가스 배출 량을 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamGrHseEmitQyMngModule.prototype.calcGrHseEmitQy = function() {

	var usageQy = Number(this.$('#usageQy').val().replace(/,/gi, ""));
	var energyTotalCalVal = Number(this.$('#energyTotalCalVal').val().replace(/,/gi, ""));
	var energyNetCalVal = Number(this.$('#energyNetCalVal').val().replace(/,/gi, ""));
	var grHseCoef = Number(this.$('#grHseCoef').val().replace(/,/gi, ""));
	var energyUsageQy = 0;
	var grHseEmitQy = 0;
	if (usageQy > 0 && energyTotalCalVal > 0) {
		energyUsageQy = Math.round(usageQy * energyTotalCalVal * 100) / 100;
	}
	if (usageQy > 0 && energyNetCalVal > 0 && grHseCoef > 0) {
		grHseEmitQy = Math.round(usageQy * energyNetCalVal * grHseCoef * 100) / 100;
	}
	this.$('#energyUsageQy').val('' + $.number(energyUsageQy, 2));
	this.$('#grHseEmitQy').val('' + $.number(grHseEmitQy, 2));

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
GamGrHseEmitQyMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mngMtYear').enable();
				this.$('#mngMtMon').enable();
				this.$('#popupFuelCd').enable();
				this.$('#popupFuelCd').removeClass('ui-state-disabled');
			}
			this.drawChart();
			break;
	}

};

var module_instance = new GamGrHseEmitQyMngModule();

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
							<th>관리 년도</th>
							<td>
								<select id="sMngYear">
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
								</select>
							</td>
							<th>관리 월</th>
							<td>
								<select id="sMngMt">
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
							<th>연료 코드</th>
							<td>
								<input type="text" size="10" id="sFuelCd">
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
				<li><a href="#listTab" class="emdTab">온실가스 배출현황</a></li>
				<li><a href="#detailTab" class="emdTab">온실가스 배출현황 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th width="20%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
									<button data-cmd="btnCopy">이전월 자료 복사</button>
	                                <button data-cmd="btnExcelDownload">엑셀다운로드</button>
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
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="29">관리 월</th>
								<td >
									<select id="mngMtYear" class='selt'>
										<option value="">선택</option>
										<c:forEach items="${yearsList}" var="yearListItem">
											<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
										</c:forEach>
									</select>
									<select id="mngMtMon" class='selt'>
										<option value="">선택</option>
										<option value="01">01월</option>
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
								<td rowspan="15" style="padding-left:4px;">
									<div id="grHseEmitChart" style="width:515px;height:415px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th width="15%" height="29">연료 코드</th>
								<td >
									<input id="mngYear" type="hidden" />
									<input type="text" size="8" id="fuelCd" disabled/>
									<button id="popupFuelCd" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th width="15%" height="29">연료 명</th>
								<td ><input type="text" size="20" id="fuelNm" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 단위</th>
								<td ><input type="text" size="20" id="energyUnit" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 총 발열량</th>
								<td ><input type="text" size="20" id="energyTotalCalVal" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 순 발열량</th>
								<td ><input type="text" size="20" id="energyNetCalVal" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 단위</th>
								<td ><input type="text" size="20" id="grHseUnit" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 계수</th>
								<td ><input type="text" size="20" id="grHseCoef" disabled></td>
							</tr>
							<tr>
								<th width="15%" height="29">사용 량</th>
								<td ><input type="text" size="20"  class="ygpaNumber" id="usageQy"/></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 사용 량</th>
								<td ><input type="text" size="20" class="ygpaNumber" id="energyUsageQy" data-decimal-point="2"/></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 배출 량</th>
								<td ><input type="text" size="20" class="ygpaNumber" id="grHseEmitQy" data-decimal-point="2"/></td>
							</tr>
						</table>
					</form>
					<table style="width:100%">
						<tr>
							<td width="100"></td>
							<td style="text-align:right">
								<button data-cmd="btnSave" class="buttonSave">저장</button>
								<button data-cmd="btnDelete" class="buttonDelete">삭제</button>
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
