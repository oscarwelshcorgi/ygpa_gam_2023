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
 * @FUNCTION NAME : GamEnergyUsageMngModule
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
		url : '<c:url value="/mngFee/gamSelectGrHseEmitQyMng.do" />',
		dataType : 'json',
		colModel : [
					{display:'연료 코드',			name:'fuelCd',			width:70, 		sortable:false,		align:'center'},
					{display:'연료 명',				name:'fuelNm',			width:110, 		sortable:false,		align:'left'},
					{display:'온실가스 계수',		name:'grHseCoef',		width:110, 		sortable:false,		align:'right'},
					{display:'관리 월',				name:'mngMt',			width:110, 		sortable:false,		align:'center'},
					{display:'사용 량',				name:'usageQy',			width:110, 		sortable:false,		align:'right',	displayFormat:'number'},
					{display:'에너지 사용 량',		name:'energyUsageQy',	width:110, 		sortable:false,		align:'right',	displayFormat:'number'},
					{display:'온실가스 배출 량',	name:'grHseEmitQy',		width:110, 		sortable:false,		align:'right',	displayFormat:'number'}
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

	var mon = new Date().getMonth()+1;
	if(mon.length==1) mon="0"+mon;
	this.$('#sMngMt').val(mon);

	this._loadCheck=false;

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
	this._loadCheck=true;

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

	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$('#mngYear').attr('readonly', 'readonly');
	this.$('#fuelCd').attr('readonly', 'readonly');
	this.$('#mngMt').attr('readonly', 'readonly');
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

	this._mode="insert";
	this.$("#mainTab").tabs("option", {active: 1});

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
	if (this.$('#mngYear').val() == "" && this.$('#fuelCd').val() == "" && this.$('#mngMt').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('<c:url value="/mngFee/gamInsertGrHseEmitQyMng.do" />', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('<c:url value="/mngFee/gamUpdateGrHseEmitQyMng.do" />', inputVO, function(module, result) {
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
	if (this.$('#mngYear').val() == "" && this.$('#fuelCd').val() == "" && this.$('#mngMt').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('<c:url value="/mngFee/gamDeleteGrHseEmitQyMng.do" />', row[0], function(module, result) {
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
			if(this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mngYear').removeAttr('readonly');
				this.$('#fuelCd').removeAttr('readonly');
				this.$('#mngMt').removeAttr('readonly');
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
						<table class="detailPanel">
							<tr>
								<th width="15%" height="29">관리 월</th>
								<td ><input type="text" size="20" id="mngMt" /></td>
								<td rowspan="15">
									<div id="grHseEmitChart" style="width:515px;height:415px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th width="15%" height="29">연료 코드</th>
								<td ><input type="text" size="20" id="fuelCd" /></td>
							</tr>
							<tr>
								<th width="15%" height="29">연료 명</th>
								<td ><span data-column-id="fuelNm"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 단위</th>
								<td ><span data-column-id="energyUnit"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 총 발열량</th>
								<td ><span data-column-id="energyTotalCalVal" class="ygpaNumber"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 순 발열량</th>
								<td ><span data-column-id="energyNetCalVal" class="ygpaNumber"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 단위</th>
								<td ><span data-column-id="grHseUnit"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 계수</th>
								<td ><span data-column-id="grHseCoef" class="ygpaNumber"></span></td>
							</tr>
							<tr>
								<th width="15%" height="29">사용 량</th>
								<td ><input type="text" size="20" id="usageQy" class="ygpaNumber"/></td>
							</tr>
							<tr>
								<th width="15%" height="29">에너지 사용 량</th>
								<td ><input type="text" size="20" id="energyUsageQy" class="ygpaNumber"/></td>
							</tr>
							<tr>
								<th width="15%" height="29">온실가스 배출 량</th>
								<td ><input type="text" size="20" id="grHseEmitQy" class="ygpaNumber"/></td>
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
