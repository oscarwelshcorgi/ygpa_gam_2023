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
			module.$('#yearCount').val(data.yearCount);
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

	this._loadCheck=false;

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
GamEnergyUsageMngModule.prototype.onSubmit = function() {

	this.loadData();

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
	this._loadCheck=true;

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$('#mngYear').attr('readonly', 'readonly');
	this.$('#fuelCd').attr('readonly', 'readonly');
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
GamEnergyUsageMngModule.prototype.addData = function() {

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
GamEnergyUsageMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	if (this.$('#mngYear').val() == "" || this.$('#fuelCd').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('/mngFee/gamInsertEnergyUsageMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateEnergyUsageMng.do', inputVO, function(module, result) {
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
GamEnergyUsageMngModule.prototype.deleteData = function() {

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#mngYear').val() == "" || this.$('#fuelCd').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('/mngFee/gamDeleteEnergyUsageMng.do', row[0], function(module, result) {
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
 * @DESCRIPTION   : 년도별 항목을 복사한다.
 * @PARAMETER     : NONE
**/
%>
GamEnergyUsageMngModule.prototype.copyData = function() {

	var sQueryMngYear = this.$('#sMngYear').val();
	var nYearCount = this.$('#yearCount').val()*1;
	if (this._loadCheck == false) {
		alert('자료를 먼저 조회하십시오.');
		return;
	}
	if (nYearCount > 0) {
		alert('[' + sQueryMngYear + '년] 자료가 존재합니다.');
		return;
	}
	if (confirm("이전년도의 자료를 [" + sQueryMngYear + "년] 자료로 복사하시겠습니까?")) {
		var inputVO = this.makeFormArgs("#searchForm");
		this.doAction('/mngFee/gamCopyEnergyUsageMng.do', row[0], function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}

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
			if(this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#mngYear').removeAttr('readonly');
				this.$('#fuelCd').removeAttr('readonly');
			}
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
									<option value="">선택</option>
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
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="10%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">년도 자료수</th>
								<td><input type="text" size="12" id="yearCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
									<button data-cmd="btnCopy">이전년도 자료 복사</button>
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
								<th width="20%" height="18">연료 코드</th>
								<td ><input type="text" size="35" id="fuelCd" /></td>
								<th width="20%" height="18">연료 명</th>
								<td ><input type="text" size="35" id="fuelNm" /></td>
							</tr>
							<tr>
								<th width="20%" height="18">관리 년도</th>
								<td ><input type="text" size="35" id="mngYear" /></td>
								<th width="20%" height="18">에너지 단위</th>
								<td ><input type="text" size="35" id="energyUnit" /></td>
							</tr>
							<tr>
								<th width="20%" height="18">에너지 총발열량</th>
								<td ><input type="text" size="35" id="energyTotalCalVal" class="ygpaNumber"/></td>
								<th width="20%" height="18">에너지 순발열량</th>
								<td ><input type="text" size="35" id="energyNetCalVal" class="ygpaNumber"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">온실가스 단위</th>
								<td ><input type="text" size="35" id="grHseUnit" /></td>
								<th width="20%" height="18">온실가스 계수</th>
								<td ><input type="text" size="35" id="grHseCoef" class="ygpaNumber"/></td>
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
