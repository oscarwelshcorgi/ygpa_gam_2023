<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamCarMng.jsp
 * @Description : 차량 정보
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
 * @FUNCTION NAME : GamMngFeeGubunMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamCarMngModule() {}

GamCarMngModule.prototype = new EmdModule(900, 600);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamCarMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/mngFee/gamSelectCarMng.do',
		dataType : 'json',
		colModel : [
					{display:'차량 등록 번호', 	name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
					{display:'차량 명', 		name:'carNm',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 종류', 		name:'carKnd',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 용도', 		name:'carPrpos',	width:80, 		sortable:false,		align:'center'},
					{display:'차대 번호', 		name:'carBodyNo',	width:160, 		sortable:false,		align:'center'},
					{display:'차량 형식', 		name:'carFmt',		width:100, 		sortable:false,		align:'center'},
					{display:'배기량', 			name:'exhaustqy',	width:70, 		sortable:false,		align:'center'},
					{display:'연료 종류', 		name:'fuelKnd',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 연식', 		name:'carYrMdl',	width:80, 		sortable:false,		align:'center'}
					],
		showTableToggleBtn: false,
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
		module._mainKeyValue = row.carRegistNo;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module._mainKeyValue = row.carRegistNo;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamCarMngModule.prototype.onButtonClick = function(buttonId) {

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
GamCarMngModule.prototype.onSubmit = function() {

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
GamCarMngModule.prototype.loadData = function() {

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
 GamCarMngModule.prototype.refreshData = function() {

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
GamCarMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/mngFee/gamSelectCarMngPk.do', searchVO, function(module, result){
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
GamCarMngModule.prototype.selectData = function() {

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

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamCarMngModule.prototype.addData = function() {

	this.$('#carRegistNo').val("");
	this.$('#carKnd').val("");
	this.$('#carPrpos').val("");
	this.$('#carNm').val("");
	this.$('#ownerNm').val("");
	this.$('#registGovOfc').val("");
	this.$('#carFmt').val("");
	this.$('#carYrMdl').val("");
	this.$('#carBodyNo').val("");
	this.$('#turbineFmt').val("");
	this.$('#usageStrhld').val("");
	this.$('#ownerAdres').val("");
	this.$('#carRegistDt').val("");
	this.$('#carLt').val("");
	this.$('#carWd').val("");
	this.$('#carHt').val("");
	this.$('#carGrWqnt').val("");
	this.$('#exhaustqy').val("");
	this.$('#rateOutput').val("");
	this.$('#rideQuotaCapa').val("");
	this.$('#maxCapaQy').val("");
	this.$('#cylinderCnt').val("");
	this.$('#fuelKnd').val("휘발류");
	this.$('#fuelEfft').val("");
	this.$('#acqPrce').val("0");
	this.$('#examValidBeginDt').val("");
	this.$('#examValidEndDt').val("");
	this.$('#rm').val("");
	this.enableDetailInputItem();
	this.$('#carRegistNo').focus();

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamCarMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var carRegistNo = this.$('#carRegistNo').val();
	var carNm = this.$('#carNm').val();
	var fuelKnd = this.$('#fuelKnd').val();
	if (carRegistNo == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	if (carNm == "") {
		alert('차량 명이 부정확합니다.');
		this.$("#carNm").focus();
		return;
	}
	if (fuelKnd != "휘발류" && fuelKnd != "경유" && fuelKnd != "LPG" && fuelKnd != "전기" && fuelKnd != "하이브리드" && fuelKnd != "기타") {
		alert('연료 종류가 부정확합니다.');
		this.$("#fuelKnd").focus();
		return;
	}
	if (this._mode == "insert") {
		this._mainKeyValue = carRegistNo;
		this.doAction('/mngFee/gamInsertCarMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/mngFee/gamUpdateCarMng.do', inputVO, function(module, result) {
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
GamCarMngModule.prototype.deleteData = function() {

	var carRegistNo = this.$('#carRegistNo').val();
	if (carRegistNo == "") {
		alert('차량 등록 번호가 부정확합니다.');
		this.$("#carRegistNo").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/mngFee/gamDeleteCarMng.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mode = 'query';
				module._mainKeyValue = '';
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
GamCarMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/mngFee/gamExcelDownloadCarMng.do');

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamCarMngModule.prototype.enableListButtonItem = function() {

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
GamCarMngModule.prototype.enableDetailInputItem = function() {

	if (this._mode == "insert") {
		this.$('#carRegistNo').enable();
		this.$('#carKnd').enable();
		this.$('#carPrpos').enable();
		this.$('#carNm').enable();
		this.$('#ownerNm').enable();
		this.$('#registGovOfc').enable();
		this.$('#carFmt').enable();
		this.$('#carYrMdl').enable();
		this.$('#carBodyNo').enable();
		this.$('#turbineFmt').enable();
		this.$('#usageStrhld').enable();
		this.$('#ownerAdres').enable();
		this.$('#carRegistDt').enable();
		this.$('#carLt').enable();
		this.$('#carWd').enable();
		this.$('#carHt').enable();
		this.$('#carGrWqnt').enable();
		this.$('#exhaustqy').enable();
		this.$('#rateOutput').enable();
		this.$('#rideQuotaCapa').enable();
		this.$('#maxCapaQy').enable();
		this.$('#cylinderCnt').enable();
		this.$('#fuelKnd').enable();
		this.$('#fuelEfft').enable();
		this.$('#acqPrce').enable();
		this.$('#examValidBeginDt').enable();
		this.$('#examValidEndDt').enable();
		this.$('#rm').enable();
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#carRegistNo').disable();
			this.$('#carKnd').enable();
			this.$('#carPrpos').enable();
			this.$('#carNm').enable();
			this.$('#ownerNm').enable();
			this.$('#registGovOfc').enable();
			this.$('#carFmt').enable();
			this.$('#carYrMdl').enable();
			this.$('#carBodyNo').enable();
			this.$('#turbineFmt').enable();
			this.$('#usageStrhld').enable();
			this.$('#ownerAdres').enable();
			this.$('#carRegistDt').enable();
			this.$('#carLt').enable();
			this.$('#carWd').enable();
			this.$('#carHt').enable();
			this.$('#carGrWqnt').enable();
			this.$('#exhaustqy').enable();
			this.$('#rateOutput').enable();
			this.$('#rideQuotaCapa').enable();
			this.$('#maxCapaQy').enable();
			this.$('#cylinderCnt').enable();
			this.$('#fuelKnd').enable();
			this.$('#fuelEfft').enable();
			this.$('#acqPrce').enable();
			this.$('#examValidBeginDt').enable();
			this.$('#examValidEndDt').enable();
			this.$('#rm').enable();
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#carRegistNo').disable();
			this.$('#carKnd').disable();
			this.$('#carPrpos').disable();
			this.$('#carNm').disable();
			this.$('#ownerNm').disable();
			this.$('#registGovOfc').disable();
			this.$('#carFmt').disable();
			this.$('#carYrMdl').disable();
			this.$('#carBodyNo').disable();
			this.$('#turbineFmt').disable();
			this.$('#usageStrhld').disable();
			this.$('#ownerAdres').disable();
			this.$('#carRegistDt').disable();
			this.$('#carLt').disable();
			this.$('#carWd').disable();
			this.$('#carHt').disable();
			this.$('#carGrWqnt').disable();
			this.$('#exhaustqy').disable();
			this.$('#rateOutput').disable();
			this.$('#rideQuotaCapa').disable();
			this.$('#maxCapaQy').disable();
			this.$('#cylinderCnt').disable();
			this.$('#fuelKnd').disable();
			this.$('#fuelEfft').disable();
			this.$('#acqPrce').disable();
			this.$('#examValidBeginDt').disable();
			this.$('#examValidEndDt').disable();
			this.$('#rm').disable();
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
GamCarMngModule.prototype.disableDetailInputItem = function() {

	this.$('#carRegistNo').disable();
	this.$('#carKnd').disable();
	this.$('#carPrpos').disable();
	this.$('#carNm').disable();
	this.$('#ownerNm').disable();
	this.$('#registGovOfc').disable();
	this.$('#carFmt').disable();
	this.$('#carYrMdl').disable();
	this.$('#carBodyNo').disable();
	this.$('#turbineFmt').disable();
	this.$('#usageStrhld').disable();
	this.$('#ownerAdres').disable();
	this.$('#carRegistDt').disable();
	this.$('#carLt').disable();
	this.$('#carWd').disable();
	this.$('#carHt').disable();
	this.$('#carGrWqnt').disable();
	this.$('#exhaustqy').disable();
	this.$('#rateOutput').disable();
	this.$('#rideQuotaCapa').disable();
	this.$('#maxCapaQy').disable();
	this.$('#cylinderCnt').disable();
	this.$('#fuelKnd').disable();
	this.$('#fuelEfft').disable();
	this.$('#acqPrce').disable();
	this.$('#examValidBeginDt').disable();
	this.$('#examValidEndDt').disable();
	this.$('#rm').disable();
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
GamCarMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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
			break;
	}

};

var module_instance = new GamCarMngModule();

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
							<th>차량 등록번호</th>
							<td>
								<input type="text" size="15" id="sCarRegistNo">
							</td>
							<th>차량 명</th>
							<td>
								<input type="text" size="15" id="sCarNm">
							</td>
							<th>연료 종류</th>
							<td>
								<select id="sFuelKnd">
									<option value="">전체</option>
									<option value="휘발류">휘발류</option>
									<option value="경유">경유</option>
									<option value="LPG">LPG</option>
									<option value="전기">전기</option>
									<option value="하이브리드">하이브리드</option>
									<option value="기타">기타</option>
								</select>
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
				<li><a href="#listTab" class="emdTab">차량정보 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">차량정보 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:20%; height:20; text-align:center;">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
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
						<table class="detailPanel">
							<tr>
								<th style="width:20%; height:18;">차량 등록 번호</th>
								<td><input type="text" size="46" id="carRegistNo"/></td>
								<th style="width:20%; height:18;">차량 종류 / 용도</th>
								<td>
									<input type="text" size="22" id="carKnd"/>
									<input type="text" size="22" id="carPrpos"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차량 명</th>
								<td><input type="text" size="46" id="carNm"/></td>
								<th style="width:20%; height:18;">소유자 / 등록 관청</th>
								<td>
									<input type="text" size="22" id="ownerNm"/>
									<input type="text" size="22" id="registGovOfc"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차량 형식</th>
								<td><input type="text" size="46" id="carFmt"/></td>
								<th style="width:20%; height:18;">차량 연식</th>
								<td><input type="text" size="46" id="carYrMdl"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차대 번호</th>
								<td><input type="text" size="46" id="carBodyNo"/></td>
								<th style="width:20%; height:18;">원동기 형식</th>
								<td><input type="text" size="46" id="turbineFmt"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">사용 본거지</th>
								<td colspan="3"><input type="text" size="119" id="usageStrhld"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">소유자 주소</th>
								<td colspan="3"><input type="text" size="119" id="ownerAdres"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차량 등록 일자</th>
								<td><input type="text" size="43" id="carRegistDt" class="emdcal"/></td>
								<th style="width:20%; height:18;">제원 관리 번호</th>
								<td><input type="text" size="46" id="specMngNo"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차량 길이</th>
								<td><input type="text" size="46" id="carLt"/></td>
								<th style="width:20%; height:18;">차량 너비</th>
								<td><input type="text" size="46" id="carWd"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">차량 높이</th>
								<td><input type="text" size="46" id="carHt"/></td>
								<th style="width:20%; height:18;">차량 총 중량</th>
								<td><input type="text" size="46" id="carGrWqnt"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">배기량</th>
								<td><input type="text" size="46" id="exhaustqy"/></td>
								<th style="width:20%; height:18;">정격 출력</th>
								<td><input type="text" size="46" id="rateOutput"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">승차 정원</th>
								<td><input type="text" size="46" id="rideQuotaCapa"/></td>
								<th style="width:20%; height:18;">최대 적재 량</th>
								<td><input type="text" size="46" id="maxCapaQy"/></td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">기통 갯수</th>
								<td><input type="text" size="46" id="cylinderCnt"/></td>
								<th style="width:20%; height:18;">연료 종류 / 연비</th>
								<td>
									<select id="fuelKnd">
										<option value="휘발류">휘발류</option>
										<option value="경유">경유</option>
										<option value="LPG">LPG</option>
										<option value="전기">전기</option>
										<option value="하이브리드">하이브리드</option>
										<option value="기타">기타</option>
									</select>
									<input type="text" size="31" id="fuelEfft"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">취득 가격</th>
								<td><input type="text" size="46" id="acqPrce"/></td>
								<th style="width:20%; height:18;">검사 유효 기간</th>
								<td>
									<input type="text" size="18" id="examValidBeginDt" class="emdcal"/> ~
									<input type="text" size="18" id="examValidEndDt" class="emdcal"/>
								</td>
							</tr>
							<tr>
								<th style="width:20%; height:18;">비고</th>
								<td colspan="3">
									<textarea rows="4" cols="117" id="rm"></textarea>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
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
