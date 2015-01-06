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

GamFcltyUsageHistInqireModule.prototype = new EmdModule(1100,700);	// 초기 시작 창크기 지정

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
						{display : '자산명',   	name : 'gisAssetsNm',			width : 150, 	sortable : false, 	align : 'left'},
						{display : '소재지', 	    name : 'gisAssetsLocplc',		width : 150, 	sortable : false, 	align : 'left'},
						{display : '자산면적(㎡)', 	name : 'gisAssetsRealRentAr',	width : 100, 	sortable : false, 	align : 'right', 		displayFormat: 'number', displayOption:{format:"0,000.00"} },
						{display : '등록업체', 	name : 'entrpsNm',				width : 150, 	sortable : false, 	align : 'left'},
						{display : '사용시작일',	name : 'usagePdFrom',			width : 100, 	sortable : false, 	align : 'center'},
						{display : '사용종료일',	name : 'usagePdTo',				width : 100, 	sortable : false, 	align : 'center'},
						{display : '사용면적(㎡)',	name : 'usageAr',				width : 100, 	sortable : false, 	align : 'right', 		displayFormat: 'number', displayOption:{format:"0,000.00"} },
						{display : '사용료',		name : 'fee',					width : 120, 	sortable : false, 	align : 'right', 		displayFormat: 'number'}
					],
		showTableToggleBtn: false,
		height: "auto",
		preProcess: function(module,data) {
			//자료수 입력
            module.makeFormValues('#listSumForm', data);
            return data;
        }
	});
	
	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
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
    this.$("#mianTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#searchForm');
    this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
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
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageHistInqireModule.prototype.selectData = function() {
	this.rowSpanGridData();
};

<%
/**
 * @FUNCTION NAME : rowSpanGrid
 * @DESCRIPTION   : GRID DATA ROW SPAN
 * @PARAMETER     : NONE
**/
%>
GamFcltyUsageHistInqireModule.prototype.rowSpanGridData = function() {
	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (gridRowCount == 0) {
		return;
	}
	var gisAssetsLocplc = "";
	var startRowNo = 0;
	var endRowNo = 0;
	for(var i=0; i<gridRowCount; i++) {
		var row = this.$("#mainGrid").flexGetRow(i+1);
		if (gisAssetsLocplc == "") {
			gisAssetsLocplc = row.gisAssetsLocplc;
		}
		if (row.gisAssetsLocplc != gisAssetsLocplc) {
			endRowNo = i - 1;
			if (endRowNo > startRowNo) {
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,1,(endRowNo - startRowNo) + 1);
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,2,(endRowNo - startRowNo) + 1);
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,3,(endRowNo - startRowNo) + 1);
			}
			startRowNo = i;
			gisAssetsLocplc = row.gisAssetsLocplc;
		}else if((i+1)==gridRowCount && row.gisAssetsLocplc == gisAssetsLocplc) {
			endRowNo = i;
			if (endRowNo > startRowNo) {
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,1,(endRowNo - startRowNo) + 1);
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,2,(endRowNo - startRowNo) + 1);
				this.$('#mainGrid')[0].dgrid.setRowspan(startRowNo + 1,3,(endRowNo - startRowNo) + 1);
			}
		}
	}
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
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
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
							<th>자산명</th>
							<td>
								<input id=sGisAssetsNm type="text" size="40" maxlength="40" title="검색조건"  />
							</td>
							<th>등록업체</th>
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

	<div class="emdPanel fillHeight">
		<div id="mianTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#listTab" class="emdTab">사용이력정보</a></li>
            </ul>
            
            <div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight">
				</table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="15%" height="25">자료수</th>
								<td><input type="text" size="13" id="dataCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="15%" height="25">총 사용면적</th>
								<td>
									<input type="text" size="24" id="sumUsageAr" disabled="disabled" style="text-align: right;" />
								</td>
								<th width="15%" height="25">총 사용료</th>
								<td><input type="text" size="24" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
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
		</div>
	</div>
</div>

<%
/******************************** UI       END ********************************/
%>
