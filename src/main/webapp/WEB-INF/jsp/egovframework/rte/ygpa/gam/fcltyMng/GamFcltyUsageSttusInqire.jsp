<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : GamFcltyUsageMng.jsp
	 * @Description : 시설물 사용현황 조회
	 * @Modification Information
	 *
	 *   수정일          수정자                수정내용
	 *  -----------    --------    ---------------------------
	 *  2014.12.08		jckim       최초 생성
	 *
	 * author jckim
	 * since 2014.12.08
	 *
	 * Copyright (C) 2013 by LFIT  All right reserved.
	 */
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyUsageSttusInqireModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
	function GamFcltyUsageSttusInqireModule() {
	}

	GamFcltyUsageSttusInqireModule.prototype = new EmdModule(1000, 600);

	// 페이지가 호출 되었을때 호출 되는 함수
<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>

 GamFcltyUsageSttusInqireModule.prototype.loadComplete = function() {

	 this._fcltsMngGroupNo = "";
	// 자산임대 테이블 설정
/* GIS 자산 리스트 */
	this.$("#gisPrtFcltyCdGrid").flexigrid({
		module : this,
		url : '<c:url value="/fcltyMng/gamFcltyGisPrtFcltyCdList.do" />',
		dataType : 'json',
		colModel : [ {display : 'GIS 자산', name : 'gisAssets', width : 80, sortable : false, align : 'center'},
		             {display : '시설 구분', name : 'prtFcltySe', width : 78, sortable : false, align : 'center'},
		             {display : '시설 명', name : 'prtFcltyNm', width : 100, sortable : false, align : 'center'},
		             {display : '시설 규격', name : 'prtFcltyDtndrd', width : 80, sortable : false, align : 'center'},
		             {display : '시설 단위', name : 'prtFcltyUnit', width : 60, sortable : false, align : 'center'},
		             {display : '시설 설치 일자', name : 'prtFcltyInstlDt', width : 88, sortable : false, align : 'center'},
		             {display : '시설 변경 일자', name : 'prtFcltyChangeDt', width : 88, sortable : false, align : 'center'},
		             {display : '관리 업체', name : 'prtFcltyMngEntrps', width : 80, sortable : false, align : 'center'},
		             {display : '시설 만료 일자', name : 'prtFcltyExprDt', width : 88, sortable : false, align : 'center'},
		             {display : '시설 수량', name : 'prtPrtFcltyCnt', width : 60, sortable : false, align : 'center'},
		             {display : '시설 담당', name : 'prtPrtFcltyMnger', width : 60, sortable : false, align : 'center'},
		             {display : '시설물 관리 그룹', name : 'fcltsMngGroupNo', width : 104, sortable : false, align : 'center'}

		             /*{display : '항코드', name : 'prtAtCode', width : 40, sortable : false, align : 'center', displayFormat : 'number'},*/
		],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);;
			module.makeFormValues('#searchForm', data.result);
			return data;
		}
	});

/* 시설물 사용현황 */
	//	console.log("aaaaa");
	// 자산임대 테이블 설정
	this.$("#assetsRentGrid").flexigrid({
		module : this,
		dataType : 'json',
		colModel : [ {display : '항코드', name : 'prtAtCode', width : 60, sortable : false, align : 'center'},
		             {display : '관리번호', name : 'mngYearNo', width : 100, sortable : false, align : 'center'},
		             {display : 'GIS 코드', name : 'gisAssets', width : 100, sortable : false, align : 'center'},
		             {display : '문서번호', name : 'docNo', width : 100, sortable : false, align : 'center'},
		             {display : '고지방법', name : 'nticMth', width : 70, sortable : false, align : 'center'},
		             {display : '사용목적', name : 'usagePurps', width : 150, sortable : false, align : 'center'},
		             {display : '사용내역', name : 'usageDtls', width : 125, sortable : false, align : 'center'},
		             {display : '면적', name : 'usageAr', width : 70, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '금액', name : 'fee', width : 100, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '허가일', name : 'prmisnDt', width : 90, sortable : false, align : 'center'}
				],
		height : 'auto'

/* 		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount2').val(data.totalCount);
			module.makeFormValues('#searchForm', data.result);
			return data;
		}
 */	});

	// 시설물 사용현황 더블 클릭
	this.$("#gisPrtFcltyCdGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.loadQcMngData();
		module.$("#mainTab").tabs("option", {active: 1});
	});

	// 점검 관리 내역
	this.$("#qcMngDtlsFGrid").flexigrid({
		module : this,
		dataType : 'json',
		colModel : [ {display : '관리 그룹', name : 'fcltsMngGroupNo', width : 110, sortable : false, align : 'center'},
		             {display : '업무 구분', name : 'fcltsJobSe', width : 70, sortable : false, align : 'center'},
		             {display : '관리 순번', name : 'qcMngSeq', width : 60, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '시행 년도', name : 'enforceYear', width : 60, sortable : false, align : 'center'},
		             {display : '관리 명', name : 'qcMngNm', width : 110, sortable : false, align : 'center'},
		             {display : '진단 일자', name : 'qcInspDt', width : 70, sortable : false, align : 'center'},
		             {display : '진단 구분', name : 'qcInspSe', width : 60, sortable : false, align : 'center'},
		             {display : '진단 기관 명', name : 'qcInspInsttNm', width : 70, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '기술자 명', name : 'responEngineerNm', width : 70, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '시작일자', name : 'qcBeginDt', width : 75, sortable : false, align : 'center'},
		             {display : '종료 일자', name : 'qcEndDt', width : 75, sortable : false, align : 'center'},
<!--		             {display : '진단 예산', name : 'qcInspBdgt', width : 90, sortable : false, align : 'center', displayFormat : 'number'},	-->
		             {display : '진단 금액', name : 'qcInspAmt', width : 80, sortable : false, align : 'right', displayFormat : 'number'},
		             {display : '평가등급', name : 'sttusEvlLvl', width : 60, sortable : false, align : 'center'},
		             {display : '진단 결과', name : 'qcInspResult', width : 60, sortable : false, align : 'center'},
		             {display : '조치내용', name : 'actionCn', width : 60, sortable : false, align : 'center'},
<!--		             {display : '조치 구분', name : 'actionSe', width : 90, sortable : false, align : 'center'},	-->
		             {display : '비고', name : 'rm', width : 60, sortable : false, align : 'center'},
					],
		height : '160'
	});

// 마우스 클릭
	this.$("#qcMngDtlsFGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.loadQcMngDtailData();
	});


	// 점검 관리 대상
	this.$("#qcMngObjFcltsGrid").flexigrid({
		module : this,
		dataType : 'json',
		colModel : [ {display : '관리 번호', name : 'fcltsMngNo', width : 50, sortable : false, align : 'center'},
		             {display : '?????', name : 'sttusEvlLvl', width : 100, sortable : false, align : 'center'},
		             {display : '진단 일자', name : 'qcInspDt', width : 100, sortable : false, align : 'center'},
		             {display : '감리자', name : 'inspector', width : 100, sortable : false, align : 'center'},
		             {display : '진단결과', name : 'qcInspResult', width : 60, sortable : false, align : 'center'},
		             {display : '비고', name : 'rm', width : 140, sortable : false, align : 'center'},
		             ],
		height : '160'
	});

	this.$("#qcMngResultItemGrid").flexigrid({
		module : this,
		dataType : 'json',
		colModel : [ {display : '항목 코드', name : 'qcItemCd', width : 50, sortable : false, align : 'center'},
		             {display : '순번', name : 'seq', width : 100, sortable : false, align : 'center'},
		             {display : '결과 구분', name : 'inspResultChk', width : 100, sortable : false, align : 'center'},
		             {display : '결과내용', name : 'inspResultCn', width : 100, sortable : false, align : 'center'},
		             ],
		height : '160'
	});

	this.$("#sUsagePdFrom").val(EMD.util.getDate(EMD.util.addMonths(-1)));
	this.$("#sUsagePdTo").val(EMD.util.getDate());

 };

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>

GamFcltyUsageSttusInqireModule.prototype.onButtonClick = function(buttonId) {
	switch (buttonId) {
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

GamFcltyUsageSttusInqireModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
//////////// 확인 부분 ?
GamFcltyUsageSttusInqireModule.prototype.loadData = function() {
	this.$("#mainTab").tabs("option", {active: 0});
	var searchVO=this.makeFormArgs('#searchForm');
	this.$('#gisPrtFcltyCdGrid').flexOptions({params:searchVO}).flexReload();
};

GamFcltyUsageSttusInqireModule.prototype.loadQcMngData = function() {
var row = this.$('#gisPrtFcltyCdGrid').selectedRows();

	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return;
	}

	row = row[0];
	var searchVO = [
	                { name: 'gisAssetsPrtAtCode', value: row['gisAssetsPrtAtCode'] },
	                { name: 'gisAssetsCd', value: row['gisAssetsCd'] },
	                { name: 'gisAssetsSubCd', value: row['gisAssetsSubCd'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] }
	               ];

//	console.log(searchVO);
	this.doAction('/fcltyMng/selectLoadQcMngData.do', searchVO, function(module, data) {
		if(data.resultCode == "0"){
			module.$('#assetsRentGrid').flexEmptyData();
        	var assetsRentList={resultList: data.resultFcltyAssetsRentList};
//        	module.$('#assetsRentGrid')[0].dgrid.p.preProcess(module, assetsRentList);
        	module.$('#assetsRentGrid').flexAddData(assetsRentList);
        	module.$('#assetsRentGrid').selectRowId(0);

        	module.$('#qcMngDtlsFGrid').flexEmptyData();
        	var qcMngList={resultList: data.qcMngList};
        	module.$('#qcMngDtlsFGrid').flexAddData(qcMngList);
        	module.$('#qcMngDtlsFGrid').selectRowId(0);


		}else{
			module.$("#mainTab").tabs("option", {active: 0});
		}
	});
};

/* 수정 중 */
GamFcltyUsageSttusInqireModule.prototype.loadQcMngDtailData = function() {
	var row = this.$('#qcMngDtlsFGrid').selectedRows();
	row = row[0];
	var searchVO = [
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'qcMngSeq', value: row['qcMngSeq'] }
	               ];

//	console.log(searchVO);
	this.doAction('/fcltyMng/selectLoadQcMngDetailData.do', searchVO, function(module, data) {
		if(data.resultCode == "0"){
			module.$('#qcMngObjFcltsGrid').flexEmptyData();
        	var qcMngDetailList={resultList: data.qcMngDetailList};
//        	module.$('#assetsRentGrid')[0].dgrid.p.preProcess(module, assetsRentList);
        	module.$('#qcMngObjFcltsGrid').flexAddData(qcMngDetailList);
        	module.$('#qcMngObjFcltsGrid').selectRowId(0);

        	module.$('#qcMngResultItemGrid').flexEmptyData();
        	module.$('#qcMngResultItemGrid').flexAddData(qcMngDetailList);
        	module.$('#qcMngResultItemGrid').selectRowId(0);

/*
        	module.$('#qcMngResultItemGrid').flexEmptyData();
        	var qcMngResultItemList={resultList: data.resultqcMngResultItemList};
        	module.$('#qcMngResultItemGrid').flexAddData(qcMngResultItemList);
        	module.$('#qcMngResultItemGrid').selectRowId(0);
*/
		}else{
			console.log("조회 자료가 없습니다.");
		}
	});
};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>

GamFcltyUsageSttusInqireModule.prototype.downloadExcel = function() {

	var totalCount = Number(this.$('#totalCount').val().replace(/,/gi, ""));
	if (totalCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#gisPrtFcltyCdGrid').flexExcelDown('/fcltyMng/gamExcelFcltyAssetsRentList.do');

};


// 수정해야 하는 부분
GamFcltyUsageSttusInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if (oldTabId == "tabs1" && this.$('gisPrtFcltyCdGrid').selectedRowCount() == 0) {
		alert('먼저 항목을 선택 하시기 바랍니다.');
		this.$("#mainTab").tabs("option", {active: 0});
		return false;
	}
	return true;
};



GamFcltyUsageSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId != 'tabs1') {

	}
	switch(newTabId) {
		case "tabs1":
			break;
		case "tabs2":
		case "tabs3":
		case "tabs4":
		case "tabs5":
			if (oldTabId == "tabs1" && this.$('#gisPrtFcltyCdGrid').selectedRowCount() == 0) {
				alert('먼저 항목을 선택 하시기 바랍니다.');
				this.$("#mainTab").tabs("option", {active: 0});
				return false;
			}
			break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUsageSttusInqireModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width: 100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td><input id="sPrtAtCode" class="ygpaCmmnCd"
								data-default-prompt="전체" data-code-id="GAM019" /></td>

							<td rowSpan="2" width="20%"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>사용기간</th>
							<td colspan="1"><input id="sUsagePdFrom" type="text" class="emdcal"
								size="8"> ~ <input id="sUsagePdTo" type="text"
								class="emdcal" size="8"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
 		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용현황</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 임대현황</a></li>
				<li><a href="#tabs3" class="emdTab">시설물 점검기록</a></li>
				<li><a href="#tabs4" class="emdTab">시설물 유지보수</a></li>
				<li><a href="#tabs5" class="emdTab">시설물 하자보수</a></li>
			</ul>

			<!-- TAB 1 AREA (LIST) -->
			<div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="gisPrtFcltyCdGrid" style="display:none" class="fillHeight"></table>
  				<div id="gisPrtFcltyCdPanel" class="emdControlPanel">
					<form id="gisPrtFcltyCdForm">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="20%" height="20">조회 자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align: right">
	                                <button data-cmd="btnExcelDownload">엑셀다운로드</button>
								</td>
							</tr>
						</table>
					</form>
 				</div>
			</div>
<!-- 탭2 -->
			<div id="tabs2" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="assetsRentGrid" style="display:none" class="fillHeight"></table>
			</div>
<!-- 탭3 -->
			<div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;">
				<table>
					<tr>
						<td colspan="2">
							점검 관리 내역
							<table id='qcMngDtlsFGrid' style="display:none" class="fillHeight" ></table>
						</td>
					</tr>
					<tr>
						<td>
							점검 관리 대상 시설물
							<table id='qcMngObjFcltsGrid' style="display:none" class="fillHeight" ></table>
						</td>
						<td>
							점검 관리 결과 항목
							<table id='qcMngResultItemGrid' style="display:none" class="fillHeight" ></table>
						</td>
					</tr>
				</table>
			</div>
<!-- 탭4 -->
			<div id="tabs4" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%">
							<tr>
								<td>4</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
<!-- 탭5 -->
			<div id="tabs5" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%">
							<tr>
								<td>5</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
</div>