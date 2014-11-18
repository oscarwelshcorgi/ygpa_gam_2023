<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCarRefuelSttusMng.jsp
 * @Description : 차량 주유현황
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

/**
 * @FUNCTION NAME : GamCarRefuelSttusMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
function GamCarRefuelSttusMngModule() {}

// PROTO TYPE 생성
GamCarRefuelSttusMngModule.prototype = new EmdModule(1000, 600);

/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
GamCarRefuelSttusMngModule.prototype.loadComplete = function() {

	// [mainGrid] FLEX GRID 정의 (LIST)
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '<c:url value="/mngFee/gamSelectCarRefuelSttusMng.do" />',
		dataType : 'json',
		colModel : [
					{display:'연료 구분', 		name:'fuelKnd',		width:100, 		sortable:false,		align:'center'},
					{display:'차량 명', 		name:'carNm',		width:100, 		sortable:false,		align:'center'},
					{display:'차량 등록 번호', 	name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
					{display:'합계', 			name:'total',		width:60, 		sortable:false,		align:'center'},
					{display:'소계', 			name:'mtotal',		width:60, 		sortable:false,		align:'center'},
					{display:'1월', 			name:'m1',			width:40, 		sortable:false,		align:'center'},
					{display:'2월', 			name:'m2',			width:40, 		sortable:false,		align:'center'},
					{display:'3월', 			name:'m3',			width:40, 		sortable:false,		align:'center'},
					{display:'4월', 			name:'m4',			width:40, 		sortable:false,		align:'center'},
					{display:'5월', 			name:'m5',			width:40, 		sortable:false,		align:'center'},
					{display:'6월', 			name:'m6',			width:40, 		sortable:false,		align:'center'},
					{display:'7월', 			name:'m7',			width:40, 		sortable:false,		align:'center'},
					{display:'8월', 			name:'m8',			width:40, 		sortable:false,		align:'center'},
					{display:'9월', 			name:'m9',			width:40, 		sortable:false,		align:'center'},
					{display:'10월', 			name:'m10',			width:40, 		sortable:false,		align:'center'},
					{display:'11월', 			name:'m11',			width:40, 		sortable:false,		align:'center'},
					{display:'12월', 			name:'m12',			width:40, 		sortable:false,		align:'center'}
					],
		showTableToggleBtn: false,
		height: 'auto'
	});

	// [mainGrid]-[onItemSelected] EVENT FUNCTION 정의 (ITEM SELECT)
	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
		module.$('#cmd').val('modify');
		// [detailForm] FORM INPUT VALUE 초기화 (DETAIL)
		module.$('#detailForm :input').val('');
		// [detailForm] FORM VALUES 생성 (DETAIL)
		module.makeFormValues('#detailForm', row);
		// EDIT DATA 설정
		module._editData=module.getFormValues('#detailForm', row);
		// EDIT ROW 설정
		module._editRow=module.$('#mainGrid').selectedRowIds()[0];
		// [refuelMt] INPUT CONTROL VALUE 설정
		module.$('#refuelMt').val(module.$('#sRefuelMt').val());
	});

	// [mainGrid]-[onItemDoubleClick] EVENT FUNCTION 정의 (ITEM DOUBLE CLICK)
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// [mainTab] FORM TAB CONTROL ACTIVE 설정 (DETAIL TAB)
		module.$("#mainTab").tabs("option", {active: 1});
		// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
		module.$('#cmd').val('modify');
		// [detailForm] FORM VALUES 생성 (DETAIL)
		module.makeFormValues('#detailForm', row);
		// EDIT DATA 설정
		module._editData=module.getFormValues('#detailForm', row);
		// EDIT ROW 설정
		module._editRow=module.$('#mainGrid').selectedRowIds()[0];
		// [refuelMt] INPUT CONTROL VALUE 설정
		module.$('#refuelMt').val(module.$('#sRefuelMt').val());
		// ROW <> NULL CHECK
		if (row != null) {
			// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
			module.$('#cmd').val('modify');
		}
	});

	// [sRefuelMt]-[change] EVENT FUNCTION 정의 (ITEM CHANGE)
	this.$('#sRefuelMt').on('change', {module: this}, function(event) {
		// [refuelMt] INPUT CONTROL VALUE 설정
		event.data.module.$('#refuelMt').val(event.data.module.$('#sRefuelMt').val());
	});

};

/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
GamCarRefuelSttusMngModule.prototype.drawChart = function() {
	// [detailForm] FORM VALUES를 구한다. (DETAIL)
	var values = this.getFormValues('#detailForm');
	// FUEL ARRAY VARIABLE 선언 (주유량 배열)
	var fuelArr=[];
	// MAX FUEL VARIABLE 선언 (최대 주유량)
	var maxFuel=0;
	// FUEL VARIABLE 선언 (주유량)
	var fuel=0;

	// LOOP
	for (var i=0; i<12; i++) {
		// FUEL 설정
		fuel=values['m'+(i+1)]*1;
		// FUEL ARRAY 설정
		fuelArr[i]={month: (i+1), gauge: fuel};
		// MAX FUEL < FUEL CHECK
		if (maxFuel<fuel) {
			// MAX FUEL 설정
			maxFuel=fuel;
		}
	};
	// MAX FUEL < 10 CHECK
	if (maxFuel<10) {
		// MAX FUEL 설정
		maxFuel=10;
	}
	// BAR CHART = NULL CHECK
	if (this.barChart==null) {
		// BAR CHART 생성
		this.barChart = new dhtmlXChart({
			view			: "bar",
			container		: this.$('#fuelChart')[0],
			value			: "#gauge#",
			color			: "#000BE0",
            gradient		: "rising",
			width			: 30,
			tooltip			: "#gauge# 리터",
			xAxis			: {
				title 		: "차량 연간 주유 현황",
				template	: "#month# 월"
			},
			yAxis			: {
				start		: 0,
				end			: maxFuel + 10,
				step		: Math.ceil(maxFuel / 10),
				title		: "주유량,리터"
			}
		});
	// BAR CHART <> NULL CHECK
	} else {
		// BAR CHART ALL CLEAR
		this.barChart.clearAll();
		// BAR CHART DEFINE
		this.barChart.define("yAxis", {
			start : 0,
			end : maxFuel + 10,
			step : Math.ceil(maxFuel / 10),
			title : "주유량,리터"
		});
	}
	// BAR CHART PARSE
	this.barChart.parse(fuelArr, "json");
	// BAR CHART REFRESH
	//this.barChart.refresh();
};

/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
GamCarRefuelSttusMngModule.prototype.onButtonClick = function(buttonId) {

	// SWITCH (BUTTON ID)
	switch (buttonId) {
		// BUTTON ID = 'btnSearch' CHECK (조회)
		case 'btnSearch':
			// [refuelMt] INPUT CONTROL VALUE 설정
			this.$('#refuelMt').val(this.$('#sRefuelMt').val());
			// DATA LOAD (LIST)
			this.loadData();
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnAdd' CHECK (추가)
		case 'btnAdd':
			// [mainGrid] FLEX GRID SELECT ROW ID = [UNDEFINED, NULL] CHECK
			if (this.$('#mainGrid').selectedRowIds()[0] == undefined &&
				this.$('#mainGrid').selectedRowIds()[0] == null) {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료를 선택하십시오.');
				// FUNCTION RETURN
				return;
			}
			// [detailForm] FORM INPUT VALUE 초기화 (DETAIL)
			this.$('#detailForm :input').val('');
			// [detailForm] FORM VALUES 생성 (LIST)
			this.makeFormValues('#detailForm',
								this.$("#mainGrid").flexGetRow(
									this.$('#mainGrid').selectedRowIds()[0]));
			// [mainTab] FORM TAB CONTROL ACTIVE 설정 (DETAIL TAB)
			this.$("#mainTab").tabs("option", {active : 1});
			// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - INSERT)
			this.$("#cmd").val("insert");
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnSave' CHECK (저장)
		case 'btnSave':
			// [detailForm] FORM ARGUMENTS 생성 (DETAIL)
			var inputVO = this.makeFormArgs("#detailForm");
			// [carRegistNo] VALUE = "" CHECK
			if (this.$('#carRegistNo').val() == "") {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료가 부정확합니다.');
				// FUNCTION RETURN
				return;
			}
			// INSERT ACTION
			this.doAction('<c:url value="/mngFee/gamInsertCarRefuelSttusMng.do" />', inputVO, function(module, result) {
				// RESULT CODE = "0" CHECK (SUCCESS)
				if (result.resultCode == "0") {
					// DATA LOAD (LIST)
					module.loadData();
				}
				// ALERT MESSAGE DISPLAY (RESULT)
				alert(result.resultMsg);
			});
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnRemove' CHECK (삭제)
		case 'btnRemove':
			// [mainGrid] FLEX GRID SELECT ROW ID = [UNDEFINED, NULL] CHECK
			if (this.$('#mainGrid').selectedRowIds()[0] == undefined &&
				this.$('#mainGrid').selectedRowIds()[0] == null) {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료를 선택하십시오.');
				// FUNCTION RETURN
				return;
			}
   		// BUTTON ID = 'btnDelete' CHECK (삭제)
		case 'btnDelete':
			// [detailForm] FORM ARGUMENTS 생성 (DETAIL)
			var inputVO = this.makeFormArgs("#detailForm");
			// [carRegistNo] VALUE = "" CHECK
			if (this.$('#carRegistNo').val() == "") {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료가 부정확합니다.');
				// FUNCTION RETURN
				return;
			}
			// CONFIRM MESSAGE DISPLAY (DELETE)
			if (confirm("삭제하시겠습니까?")) {
				// DELETE ACTION
				this.doAction('<c:url value="/mngFee/gamDeleteRefuelSttusMngList.do" />', inputVO, function(module, result) {
					// RESULT CODE = "0" CHECK (SUCCESS)
					if (result.resultCode == "0") {
						// DATA LOAD (LIST)
						module.loadData();
					}
					// ALERT MESSAGE DISPLAY (RESULT)
					alert(result.resultMsg);
				});
			}
			// SWITCH BREAK
			break;
	}

};

/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : SUBMIT EVENT
 * @PARAMETER     : NONE
**/
GamCarRefuelSttusMngModule.prototype.onSubmit = function() {
	// DATA LOAD (LIST)
	this.loadData();
};

/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
GamCarRefuelSttusMngModule.prototype.loadData = function() {
	// [mainTab] FORM TAB CONTROL ACTIVE 설정 (LIST TAB)
	this.$("#mainTab").tabs("option", {active: 0});
	// [searchForm] FORM ARGUMENTS 생성 (SEARCH)
	var searchOpt=this.makeFormArgs('#searchForm');
	// [searchForm] FORM ARGUMENTS 추가 (FEUL KIND CHECKBOX CONTROL VALUE)
	this.$('input[name="check"]:checked').each(function() {
		searchOpt[searchOpt.length] = {
			name : 'check',
			value : this.value
		};
	});
	// [mainGrid] FLEX GRID DATA RELOAD
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : TAB CHANGE EVENT
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
GamCarRefuelSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	// SWITCH (NEW TAB ID)
	switch (newTabId) {
		// NEW TAB ID = 'listTab' CHECK (LIST TAB)
		case 'listTab':
			// SWITCH BREAK
			break;
		// NEW TAB ID = 'detailTab' CHECK (DETAIL TAB)
		case 'detailTab':
			// CHART DRAW
			this.drawChart();
			// SWITCH BREAK
			break;
	}

};

//MODULE INSTANCE 생성
var module_instance = new GamCarRefuelSttusMngModule();

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
							<th>주유 년도</th>
							<td>
								<select id="sRefuelMt">
									<option value="">선택</option>
									<c:forEach items="${yearsList}" var="yearListItem">
										<option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisyear}">selected</c:if> >${yearListItem.codeNm }</option>
									</c:forEach>
                                </select>
							</td>
							<th>차량 등록 번호</th>
							<td>
								<input id="sCarRegistNo" type="text" size="15">
							</td>
							<td rowspan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>주유 구분</th>
							<td colspan="4">
								휘발류<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="휘발류" checked="checked" class="chk">
								경유<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="경유"	checked="checked"	class="chk">
								LPG<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="LPG"	checked="checked"	class="chk">
								전기<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="전기"	checked="checked"	class="chk">
								하이브리드<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="HYBRID"	checked="checked"	class="chk">
								기타<input type="checkbox" size="10" name="check" style="vertical-align: middle;" value="기타"	checked="checked"	class="chk">
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
				<li><a href="#listTab" class="emdTab">차량주유 현황</a></li>
				<li><a href="#detailTab" class="emdTab">차량주유 현황 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<td style="text-align: right">
									<button id="btnAdd">등록</button>
									<button id="btnRemove">삭제</button>
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
						<input type="hidden" id="cmd"/>
						<input type="hidden" id="refuelMt"/>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="18">차량 등록 번호</th>
								<td ><input type="text" size="20" id="carRegistNo" readonly="readonly"/></td>
								<th width="15%" height="18">연료 구분</th>
								<td ><input type="text" size="20" id="fuelKnd" readonly="readonly"/></td>
								<th width="15%" height="18">차량 명</th>
								<td ><input type="text" size="20" id="carNm" readonly="readonly"/></td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%">1월</th>
								<td width="15%"><input type="text" size="20" id="m1"></td>
								<td rowspan="15">
									<div id="fuelChart" style="width:600px;height:320px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th>2월</th>
								<td><input type="text" size="20" id="m2"></td>
							</tr>
							<tr>
								<th>3월</th>
								<td><input type="text" size="20" id="m3"></td>
							</tr>
							<tr>
								<th>4월</th>
								<td><input type="text" size="20" id="m4"></td>
							</tr>
							<tr>
								<th>5월</th>
								<td><input type="text" size="20" id="m5"></td>
							</tr>
							<tr>
								<th>6월</th>
								<td><input type="text" size="20" id="m6"></td>
							</tr>
							<tr>
								<th>7월</th>
								<td><input type="text" size="20" id="m7"></td>
							</tr>
							<tr>
								<th>8월</th>
								<td><input type="text" size="20" id="m8"></td>
							</tr>
							<tr>
								<th>9월</th>
								<td><input type="text" size="20" id="m9"></td>
							</tr>
							<tr>
								<th>10월</th>
								<td><input type="text" size="20" id="m10"></td>
							</tr>
							<tr>
								<th>11월</th>
								<td><input type="text" size="20" id="m11"></td>
							</tr>
							<tr>
								<th>12월</th>
								<td><input type="text" size="20" id="m12"></td>
							</tr>
                        </table>
					</form>
					<table style="width:100%">
						<tr>
							<td width="100"></td>
							<td style="text-align:right">
								<button id="btnSave" class="buttonSave">저장</button>
								<button id="btnDelete" class="buttonDelete">삭제</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>