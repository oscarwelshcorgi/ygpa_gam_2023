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

GamCarMngModule.prototype = new EmdModule(1000, 600);

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
		url : '<c:url value="/mngFee/gamSelectCarMngList.do" />',
		dataType : 'json',
		colModel : [
					{display:'차량 등록 번호', 	name:'carRegistNo',	width:100, 		sortable:false,		align:'center'},
					{display:'차량 명', 		name:'carNm',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 종류', 		name:'carKnd',		width:80, 		sortable:false,		align:'center'},
					{display:'차량 용도', 		name:'carPrpos',	width:80, 		sortable:false,		align:'center'},
					{display:'차대 번호', 		name:'carBodyNo',	width:160, 		sortable:false,		align:'center'},
					{display:'차량 형식', 		name:'carFmt',		width:100, 		sortable:false,		align:'center'},
					{display:'배기량', 			name:'exhaustqy',	width:70, 		sortable:false,		align:'center'},
					{display:'연료 종류', 		name:'fuelKnd',		width:70, 		sortable:false,		align:'center'},
					{display:'차량 연식', 		name:'carYrMdl',	width:70, 		sortable:false,		align:'center'}
					],
		showTableToggleBtn: false,
		height: 'auto'
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mode = 'modify';
		module.$("#mainTab").tabs("option", {active: 1});
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
GamCarMngModule.prototype.onButtonClick = function(buttonId) {

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

	this.loadData();

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
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     : NONE
**/
%>
GamCarMngModule.prototype.loadDetail = function() {

	var row = this.$('#mainGrid').selectedRows();

	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	this.$('#carRegistNo').attr('readonly', 'readonly');
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
GamCarMngModule.prototype.addData = function() {

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
GamCarMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	if (this.$('#carRegistNo').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (this._mode == "insert") {
		this.doAction('<c:url value="/mngFee/gamInsertCarMng.do" />', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('<c:url value="/mngFee/gamUpdateCarMng.do" />', inputVO, function(module, result) {
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
GamCarMngModule.prototype.deleteData = function() {

	var row = this.$('#mainGrid').selectedRows();
	if (row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}
	if (this.$('#carRegistNo').val() == "") {
		alert('자료가 부정확합니다.');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		this.doAction('<c:url value="/mngFee/gamDeleteCarMng.do" />', row[0], function(module, result) {
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
GamCarMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if(this._mode=="modify") {
				this.loadDetail();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.$('#carRegistNo').removeAttr('readonly');
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
								<input type="text" size="10" id="sCarRegistNo">
							</td>
							<th>차량 명</th>
							<td>
								<input type="text" size="10" id="sCarNm">
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
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<td style="text-align: right">
									<button data-cmd="btnAdd">추가</button>
									<button data-cmd="btnDelete">삭제</button>
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
								<th width="20%" height="18">차량 등록 번호</th>
								<td ><input type="text" size="40" id="carRegistNo"/></td>
								<th width="20%" height="18">차량 종류</th>
								<td ><input type="text" size="40" id="carKnd"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">차량 용도</th>
								<td ><input type="text" size="40" id="carPrpos"/></td>
								<th width="20%" height="18">차량 명</th>
								<td ><input type="text" size="40" id="carNm"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">차량 형식</th>
								<td ><input type="text" size="40" id="carFmt"/></td>
								<th width="20%" height="18">차량 연식</th>
								<td ><input type="text" size="40" id="carYrMdl"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">차대 번호</th>
								<td ><input type="text" size="40" id="carBodyNo"/></td>
								<th width="20%" height="18">원동기 형식</th>
								<td ><input type="text" size="40" id="turbineFmt"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">사용 본거지</th>
								<td colspan="3"><input type="text" size="117" id="usageStrhld"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">소유자 주소</th>
								<td colspan="3"><input type="text" size="117" id="ownerAdres"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">소유자 명</th>
								<td ><input type="text" size="40" id="ownerNm"/></td>
								<th width="20%" height="18">차량 등록 일자</th>
								<td ><input type="text" size="40" id="carRegistDt" class="emdcal"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">등록 관청</th>
								<td ><input type="text" size="40" id="registGovOfc"/></td>
								<th width="20%" height="18">제원 관리 번호</th>
								<td ><input type="text" size="40" id="specMngNo"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">차량 길이</th>
								<td ><input type="text" size="40" id="carLt"/></td>
								<th width="20%" height="18">차량 너비</th>
								<td ><input type="text" size="40" id="CarWd"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">차량 높이</th>
								<td ><input type="text" size="40" id="carHt"/></td>
								<th width="20%" height="18">차량 총 중량</th>
								<td ><input type="text" size="40" id="carGrWqnt"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">배기량</th>
								<td ><input type="text" size="40" id="exhaustqy"/></td>
								<th width="20%" height="18">정격 출력</th>
								<td ><input type="text" size="40" id="rateOutput"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">승차 정원</th>
								<td ><input type="text" size="40" id="rideQuotaCapa"/></td>
								<th width="20%" height="18">최대 적재 량</th>
								<td ><input type="text" size="40" id="maxCapaQy"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">기통 갯수</th>
								<td ><input type="text" size="40" id="cylinderCnt"/></td>
								<th width="20%" height="18">연료 종류</th>
								<td >
									<select id="fuelKnd">
										<option value="휘발류">휘발류</option>
										<option value="경유">경유</option>
										<option value="LPG">LPG</option>
										<option value="전기">전기</option>
										<option value="하이브리드">하이브리드</option>
										<option value="기타">기타</option>
									</select>
								</td>
							</tr>
							<tr>
								<th width="20%" height="18">연비</th>
								<td ><input type="text" size="40" id="fuelEfft"/></td>
								<th width="20%" height="18">취득 가격</th>
								<td ><input type="text" size="40" id="acqPrce"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">검사 유효 시작일</th>
								<td ><input type="text" size="40" id="examValidBeginDt" class="emdcal"/></td>
								<th width="20%" height="18">검사 유효 종료일</th>
								<td ><input type="text" size="40" id="examValidEndDt" class="emdcal"/></td>
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
