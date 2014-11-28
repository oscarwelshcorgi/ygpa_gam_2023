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

<%
/**
 * @FUNCTION NAME : GamCarRefuelSttusMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCarRefuelSttusMngModule() {}

GamCarRefuelSttusMngModule.prototype = new EmdModule(1000, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectCarRefuelSttusMng.do',
		dataType : 'json',
		colModel : [
					{display:'연료 구분', 		name:'fuelKnd',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 명', 		name:'carNm',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 등록 번호', 	name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
					{display:'합계', 			name:'total',		width:60, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'소계', 			name:'mtotal',		width:60, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'1월', 			name:'m1',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'2월', 			name:'m2',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'3월', 			name:'m3',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'4월', 			name:'m4',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'5월', 			name:'m5',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'6월', 			name:'m6',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'7월', 			name:'m7',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'8월', 			name:'m8',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'9월', 			name:'m9',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'10월', 			name:'m10',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'11월', 			name:'m11',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'},
					{display:'12월', 			name:'m12',			width:45, 		sortable:false,		align:'center',		displayFormat: 'number'}
					],
		showTableToggleBtn: false,
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

	this.$('#sRefuelMt').on('change', {module: this}, function(event) {
		event.data.module.$('#refuelMt').val(event.data.module.$('#sRefuelMt').val());
	});

};

<%
/**
 * @FUNCTION NAME : drawChart
 * @DESCRIPTION   : CHART DRAW
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.drawChart = function() {
	var values = this.getFormValues('#detailForm');
	var fuelArr=[];
	var maxFuel=0;
	var fuel=0;

	for (var i=0; i<12; i++) {
		fuel=values['m'+(i+1)].replace(",","")*1;
		fuelArr[i]={month: (i+1), gauge: fuel};
		if (maxFuel<fuel) {
			maxFuel=fuel;
		}
	};
	if (maxFuel<10) {
		maxFuel=10;
	}
	if (this.barChart==null) {
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
	} else {
		this.barChart.clearAll();
		this.barChart.define("yAxis", {
			start : 0,
			end : maxFuel + 10,
			step : Math.ceil(maxFuel / 10),
			title : "주유량,리터"
		});
	}
	this.barChart.parse(fuelArr, "json");
	this.barChart.refresh();
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamCarRefuelSttusMngModule.prototype.onButtonClick = function(buttonId) {

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
GamCarRefuelSttusMngModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	if (this.$('#sFuelKndCheck1').is(":checked")) {
		this.$("#sFuelKnd1").val(this.$('#sFuelKndCheck1').val());
	} else {
		this.$("#sFuelKnd1").val('');
	}
	if (this.$('#sFuelKndCheck2').is(":checked")) {
		this.$("#sFuelKnd2").val(this.$('#sFuelKndCheck2').val());
	} else {
		this.$("#sFuelKnd2").val('');
	}
	if (this.$('#sFuelKndCheck3').is(":checked")) {
		this.$("#sFuelKnd3").val(this.$('#sFuelKndCheck3').val());
	} else {
		this.$("#sFuelKnd3").val('');
	}
	if (this.$('#sFuelKndCheck4').is(":checked")) {
		this.$("#sFuelKnd4").val(this.$('#sFuelKndCheck4').val());
	} else {
		this.$("#sFuelKnd4").val('');
	}
	if (this.$('#sFuelKndCheck5').is(":checked")) {
		this.$("#sFuelKnd5").val(this.$('#sFuelKndCheck5').val());
	} else {
		this.$("#sFuelKnd5").val('');
	}
	if (this.$('#sFuelKndCheck6').is(":checked")) {
		this.$("#sFuelKnd6").val(this.$('#sFuelKndCheck6').val());
	} else {
		this.$("#sFuelKnd6").val('');
	}
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
GamCarRefuelSttusMngModule.prototype.loadDetail = function() {

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
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.addData = function() {

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
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
GamCarRefuelSttusMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	if (this.$('#carRegistNo').val() == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	this.doAction('/mngFee/gamInsertCarRefuelSttusMng.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.loadData();
		}
		alert(result.resultMsg);
	});

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.deleteData = function() {

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#carRegistNo').val() == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('/mngFee/gamDeleteCarRefuelSttusMng.do', row[0], function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamCarRefuelSttusMngModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelCarRefuelSttusMng.do');

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
GamCarRefuelSttusMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			var row = this.$('#mainGrid').selectedRows();
			if (this._mode=="modify") {
				this.loadDetail();
				this.$('#refuelMt').val(this.$('#sRefuelMt').val());
			} else {
				this.makeFormValues('#detailForm', row[0]);
				this.makeDivValues('#detailForm', row[0]);
				this.$('#refuelMt').val(this.$('#sRefuelMt').val());
			}
			this.drawChart();
			break;
	}

};

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
							<th>주유 구분</th>
							<td>
								<input id="sFuelKnd1" type="hidden" />
								<input id="sFuelKnd2" type="hidden" />
								<input id="sFuelKnd3" type="hidden" />
								<input id="sFuelKnd4" type="hidden" />
								<input id="sFuelKnd5" type="hidden" />
								<input id="sFuelKnd6" type="hidden" />
								휘발류<input type="checkbox" size="10" name="check" id="sFuelKndCheck1" style="vertical-align: middle;" value="휘발류" checked="checked" class="chk">
								경유<input type="checkbox" size="10" name="check" id="sFuelKndCheck2" style="vertical-align: middle;" value="경유"	checked="checked"	class="chk">
								LPG<input type="checkbox" size="10" name="check" id="sFuelKndCheck3" style="vertical-align: middle;" value="LPG"	checked="checked"	class="chk">
								전기<input type="checkbox" size="10" name="check" id="sFuelKndCheck4" style="vertical-align: middle;" value="전기"	checked="checked"	class="chk">
								하이브리드<input type="checkbox" size="10" name="check" id="sFuelKndCheck5" style="vertical-align: middle;" value="HYBRID"	checked="checked"	class="chk">
								기타<input type="checkbox" size="10" name="check" id="sFuelKndCheck6" style="vertical-align: middle;" value="기타"	checked="checked"	class="chk">
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
								<th width="20%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
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
						<input type="hidden" id="refuelMt"/>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="23">차량 등록 번호</th>
								<td ><input type="text" size="20" id="carRegistNo" readonly="readonly"/></td>
								<th width="15%" height="23">연료 구분</th>
								<td ><input type="text" size="20" id="fuelKnd" readonly="readonly"/></td>
								<th width="15%" height="23">차량 명</th>
								<td ><input type="text" size="20" id="carNm" readonly="readonly"/></td>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="15%" height="23">1월</th>
								<td width="15%"><input type="text" size="20" id="m1" class="ygpaNumber"></td>
								<td rowspan="15" style="padding-left:4px;">
									<div id="fuelChart" style="width:670px;height:370px;border:1px solid #A4BED4;"></div>
								</td>
							</tr>
							<tr>
								<th width="15%" height="23">2월</th>
								<td><input type="text" size="20" id="m2" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">3월</th>
								<td><input type="text" size="20" id="m3" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">4월</th>
								<td><input type="text" size="20" id="m4" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">5월</th>
								<td><input type="text" size="20" id="m5" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">6월</th>
								<td><input type="text" size="20" id="m6" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">7월</th>
								<td><input type="text" size="20" id="m7" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">8월</th>
								<td><input type="text" size="20" id="m8" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">9월</th>
								<td><input type="text" size="20" id="m9" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">10월</th>
								<td><input type="text" size="20" id="m10" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">11월</th>
								<td><input type="text" size="20" id="m11" class="ygpaNumber"></td>
							</tr>
							<tr>
								<th width="15%" height="23">12월</th>
								<td><input type="text" size="20" id="m12" class="ygpaNumber"></td>
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
