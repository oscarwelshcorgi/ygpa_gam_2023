<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyUsageHistInqire.jsp
 * @Description : 시설물 사용이력 조회
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.12.11	김영길		최초 생성
 *
 * author 김영길
 * since 2014.12.11
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="searchForm" method="validateSearchForm" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<%
/******************************** SCRIPT START ********************************/
%>
<script>
<%
/**
 * @FUNCTION NAME : GamFcltyUsageHistInqireModule
 * @DESCRIPTION   : MODULE 고유 함수 (아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.)
 * @PARAMETER     : NONE
**/
%>
function GamFcltyUsageHistInqireModule() {}

GamFcltyUsageHistInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageHistInqireModule.prototype.loadComplete = function(params) {
	// 테이블 설정
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/fcltyMng/gamFcltyUsageHistInqireList.do',
		dataType: "json",
		colModel : [
						{display : '항구분',		name : 'prtAtCodeNm',			width : 60, 	sortable : false, 	align : 'center'},
						{display : '시설명',   	name : 'gisAssetsNm',			width : 150, 	sortable : false, 	align : 'left'},
						{display : '소재지', 	    name : 'gisAssetsLocplc',		width : 150, 	sortable : false, 	align : 'left'},
						{display : '시설면적(㎡)', 	name : 'gisAssetsRealRentAr',	width : 90, 	sortable : false, 	align : 'right', 		displayFormat: 'number', displayOption:{format:"0,000.00"} },
						{display : '사용업체', 	name : 'entrpsNm',				width : 140, 	sortable : false, 	align : 'left'},
						{display : '사용시작일',	name : 'usagePdFrom',			width : 80, 	sortable : false, 	align : 'center'},
						{display : '사용종료일',	name : 'usagePdTo',				width : 80, 	sortable : false, 	align : 'center'},
						{display : '사용면적(㎡)',	name : 'usageAr',				width : 90, 	sortable : false, 	align : 'right', 		displayFormat: 'number', displayOption:{format:"0,000.00"} },
						{display : '사용료',		name : 'fee',					width : 100, 	sortable : false, 	align : 'right', 		displayFormat: 'number'}
					],
		showTableToggleBtn: false,
		height: "auto",
		mergeRows : 'gisAssetsNm,gisAssetsLocplc,gisAssetsRealRentAr',
		preProcess: function(module,data) {
			//자료수 입력
            module.makeFormValues('#listSumForm', data);
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
	
	this.$("#sRegistEntrpsCd").bind("keyup change", {module: this}, function(event) {
		event.data.module.getQueryEntrpsNm();
	});
};

<%
/**
 * @FUNCTION NAME : getQueryEntrpsNm
 * @DESCRIPTION   : 조회조건 사용업체 명을 지운다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageHistInqireModule.prototype.getQueryEntrpsNm = function() {
	var sEntrpscd = this.$('#sRegistEntrpsCd').val();
	if (sEntrpscd.length != 8) {
		this.$('#sRegistEntrpsNm').val('');
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
 GamFcltyUsageHistInqireModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
		case 'btnExcelDownload':
			this.downloadExcel();
		break;
	case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
           this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', null);
           break;
    }
};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID(팝업 대화상자 아이디)
 *   2. msg      - MESSAGE(팝업에서 전송한 메시지 / 취소는 cancel)
 *   3. value    - VALUE(팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0)
**/
%>
GamFcltyUsageHistInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'selectEntrpsInfoPopup': //등록업체 선택(조회)
	        this.$('#sRegistEntrpsCd').val(value['entrpscd']);
	        this.$('#sRegistEntrpsNm').val(value['entrpsNm']);
	    	break;
		default:
        	alert('알수없는 팝업 이벤트가 호출 되었습니다.');
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
GamFcltyUsageHistInqireModule.prototype.onSubmit = function() {
	if(!validateSearchForm(this.$('#searchForm')[0])){ 		
		return;
	}
	if (this.$("#sDtFr").val() > this.$("#sDtTo").val()){
 		alert('사용기간을 확인하여 주십시오.');
 		return;
 			
	}
	this.clearCodePage();
    this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageHistInqireModule.prototype.loadData = function() {
	this._mode="";
    this.$("#mainTab").tabs("option", {active: 0});
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
GamFcltyUsageHistInqireModule.prototype.loadDetail = function(tabId) {
		var row = this.$('#mainGrid').selectedRows()[0];
		var searchVO = [
							{name: 'gisAssetsPrtAtCode', value: row["gisAssetsPrtAtCode"]},
							{name: 'gisAssetsCd', value: row["gisAssetsCd"]},
							{name: 'gisAssetsSubCd', value: row["gisAssetsSubCd"]},
							{name: 'prtAtCode', value: row["prtAtCode"]},
							{name: 'mngYear', value: row["mngYear"]},
							{name: 'mngNo', value: row["mngNo"]},
							{name: 'mngCnt', value: row["mngCnt"]},
							{name: 'assetsUsageSeq', value: row["assetsUsageSeq"]},
						];
		this.doAction('/fcltyMng/gamFcltyUsageHistInqireDetail.do', searchVO, function(module, result) {
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result[0]);
			} else {
				alert(result.resultMsg);
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
GamFcltyUsageHistInqireModule.prototype.downloadExcel = function() {
	var RowCount = this.$("#mainGrid").flexRowCount();
	if (RowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/gamFcltyUsageHistInqireExcel.do');
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
 GamFcltyUsageHistInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	 if(oldTabId == 'listTab' && this._mode == 'modify') {
	 	this.loadDetail();
	 }
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if(this._mode != 'modify') {
				alert('선택된 항목이 없습니다.');
				this.$("#mainTab").tabs("option", {active: 0});
				return;
			}
			break;
	}

};

GamFcltyUsageHistInqireModule.prototype.clearCodePage = function() {
	this.$('#detailForm :input').val('');
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUsageHistInqireModule();
</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항구분</th>
							<td>
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
							</td>
							<th>사용기간</th>
                            <td>
                            	<input id="sDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sDtTo" type="text" class="emdcal" size="8">
                            </td>
							<td rowspan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>시설명</th>
							<td>
								<input id=sGisAssetsNm type="text" size="40" maxlength="40" title="검색조건"  />
							</td>
							<th>사용업체</th>
							<td>
                            	<input id="sRegistEntrpsCd" type="text" size="7">&nbsp; &nbsp;
                         		<input id="sRegistEntrpsNm" type="text" size="27" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
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
                <li><a href="#listTab" class="emdTab">사용이력정보</a></li>
                <li><a href="#detailTab" class="emdTab">사용이력정보 상세</a></li>
            </ul>
            <!-- 212. TAB 1 AREA (LIST) -->
            <div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight">
				</table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="10%" height="25px">자료수</th>
								<td><input type="text" size="13" id="dataCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25px">총 사용면적</th>
								<td><input type="text" size="24" id="sumUsageAr" class="ygpaNumber" data-column-id="sumUsageAr" data-decimal-point="2" disabled="disabled" />㎡</td>
								<th width="10%" height="25px">총 사용료</th>
								<td><input type="text" size="24" id="sumFee" class="ygpaNumber" disabled="disabled" />원</td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
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
								<th style="font-weight:bold; height:28px;">사용이력정보 상세</th>
							</tr>
						</table>
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:10%; height:25px;">항　　　구</th>
								<td>
									<input type="text" size="40" id="prtAtCodeNm" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">시　설　명</th>
								<td>
									<input type="text" size="40" id="gisAssetsNm" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">소　재　지</th>
								<td>
									<input type="text" size="40" id="gisAssetsLocplc" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">시 설 면 적</th>
								<td>
									<input type="text" size="40" id="gisAssetsRealRentAr" class="ygpaNumber" data-column-id="gisAssetsRealRentAr" data-decimal-point="2" disabled="disabled" />
									㎡
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">사 용 업 체</th>
								<td colspan="3">
									<input type="text" size="40" id="entrpsNm" disabled="disabled">
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">사 용 목 적</th>
								<td colspan="3">
									<input type="text" size="129" id="usagePurps" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">사 용 내 역</th>
								<td colspan="3">
									<input type="text" size="129" id="usageDtls" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">공 시 지 가</th>
								<td>
									<input type="text" size="40" id="olnlp" class="ygpaNumber" disabled="disabled" />원
								</td>
								<th style="width:10%; height:25px;">적 용 요 율</th>
								<td>
									<input type="text" size="40" id="applcTariff" class="ygpaNumber" data-column-id="applcTariff" data-decimal-point="2" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">적 용 단 가</th>
								<td>
									<input type="text" size="40" id="applcPrice" class="ygpaNumber" disabled="disabled" />원
								</td>
								<th style="width:10%; height:25px;">사 용 면 적</th>
								<td>
									<input type="text" size="40" id="usageAr" class="ygpaNumber" data-column-id="usageAr" data-decimal-point="2" disabled="disabled" />㎡
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">사 용 기 간</th>
								<td>
									<input type="text" size="11" id="usagePdFrom" disabled="disabled" />&nbsp;~&nbsp;
									<input type="text" size="11" id="usagePdTo" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">사　용　료</th>
								<td colspan="3">
									<input type="text" size="40" id="fee" class="ygpaNumber" disabled="disabled" />원
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">산 출 내 역</th>
								<td colspan="3">
									<input type="text" size="129" id="computDtls" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th width="10%" height="25px">면 제 구 분</th>
                                <td colspan="3">
                                    <input size="17" id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM009" data-column-label-id='exemptSeNm' disabled/>
                                </td>
                            </tr>
                            <tr>
								<th style="width:10%; height:25px;">면 제 사 유</th>
								<td>
									<input type="text" size="40" id="exemptRsn" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">면 제 기 간</th>
								<td>
									<input type="text" size="11" id="ExemptPdFrom" disabled="disabled" />&nbsp;~&nbsp;
									<input type="text" size="11" id="ExemptPdTo" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">해 지 사 유</th>
								<td>
									<input type="text" size="40" id="trmnatRsn" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">해 지 일 자</th>
								<td>
									<input type="text" size="40" id="trmnatDt" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">등　록　자</th>
								<td>
									<input type="text" size="40" id="regUsr" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">등 록 일 시</th>
								<td>
									<input type="text" size="40" id="registDt" disabled="disabled" />
								</td>
							</tr>
							<tr>
								<th style="width:10%; height:25px;">수　정　자</th>
								<td>
									<input type="text" size="40" id="updUsr" disabled="disabled" />
								</td>
								<th style="width:10%; height:25px;">수 정 일 시</th>
								<td>
									<input type="text" size="40" id="updtDt" disabled="disabled" />
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
