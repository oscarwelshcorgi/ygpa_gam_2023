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
  *  2014. 12. 11.		LFIT		최초 생성
  *
  * author LFIT
  * since 2014.12.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamFcltyUsageHistInqireSearchForm" method="validateGamFcltyUsageHistInqire" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyUsageHistInqireModule() {}

GamFcltyUsageHistInqireModule.prototype = new EmdModule(1000,700);	// 초기 시작 창크기 지정

//페이지가 호출 되었을때 호출 되는 함수
GamFcltyUsageHistInqireModule.prototype.loadComplete = function(params) {
	// 테이블 설정
	this.$("#gamFcltyUsageHistInqireList").flexigrid({
		module: this,
		url: '/fcltyMng/gamFcltyUsageHistInqireList.do',
		dataType: "json",
		colModel : [
						//{display : '관리번호',		name : 'rentMngNo',			width : 100, 	sortable : false, 	align : 'center'},
						{display : '항구분',		name : 'prtAtCodeNm',		width : 80, 	sortable : false, 	align : 'center'},
						{display : '자산명',   	name : 'gisAssetsNm',		width : 180, 	sortable : false, 	align : 'left'},
						{display : '업체명', 	    name : 'entrpsNm',			width : 150, 	sortable : false, 	align : 'left'},
						{display : '사용시작일',	name : 'usagePdFrom',		width : 100, 	sortable : false, 	align : 'center'},
						{display : '사용종료일',	name : 'usagePdTo',			width : 100, 	sortable : false, 	align : 'center'},
						{display : '사용면적(㎡)',	name : 'usageAr',			width : 100, 	sortable : false, 	align : 'right', 		displayFormat: 'number'},
						{display : '사용료',		name : 'fee',				width : 120, 	sortable : false, 	align : 'right', 		displayFormat: 'number'}
					],
		showTableToggleBtn: false,
		height: "auto",
		preProcess: function(module,data) {
			//자료수 입력
            module.$('#dataCount').val($.number(data.dataCount));
            module.$('#sumAr').val($.number(data.sumAr));
            module.$('#sumFee').val($.number(data.sumFee));
			
            return data;
        }
	});
};

GamFcltyUsageHistInqireModule.prototype.onSubmit = function() {
	if(!validateGamFcltyUsageHistInqire(this.$('#gamFcltyUsageHistInqireSearchForm')[0])){ 		
		return;
	}
    this.loadData();
};

GamFcltyUsageHistInqireModule.prototype.loadData = function() {
    this.$("#gamFcltyUsageHistInqireListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamFcltyUsageHistInqireSearchForm');
    this.$('#gamFcltyUsageHistInqireList').flexOptions({params:searchOpt}).flexReload();

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyUsageHistInqireModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
		case 'btnExcelDownload':
			this.$('gamFcltyUsageHistInqireList').flexExcelDown('/fcltyMng/gamFcltyUsageHistInqireExcel.do');
		break;
    }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUsageHistInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="gamFcltyUsageHistInqireSearchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>항코드</th>
							<td>
								<input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
							</td>
							<th>사용기간</th>
                            <td>
                            	<input id="searchDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="searchDtTo" type="text" class="emdcal" size="8">
                            </td>
							<td rowspan="2">
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
						<tr>
							<th>자산명</th>
							<td>
								<input id="searchKeyword" type="text" size="40" maxlength="40" title="검색조건"  />
							</td>
							<th>업체명</th>
                            <td>
                         		<input id="entrpsNm" type="text" size="30">
                         	</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="gamFcltyUsageHistInqireListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">사용이력정보</a></li>
            </ul>
            
            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="gamFcltyUsageHistInqireList" style="display:none" class="fillHeight">
				</table>
				<div id="gamFcltyUsageHistInqireListSum" class="emdControlPanel">
					<form id="gamFcltyUsageHistInqireListSumFrom">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="25">자료수</th>
								<td><input type="text" size="13" id="dataCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="17%" height="25">총 면적</th>
								<td><input type="text" size="24" id="sumAr" class="ygpaNumber" disabled="disabled" /></td>
								<th width="17%" height="25">총 사용료</th>
								<td><input type="text" size="24" id="sumFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnExcelDownload">엑셀</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
			</div>
		</div>
	</div>
</div>