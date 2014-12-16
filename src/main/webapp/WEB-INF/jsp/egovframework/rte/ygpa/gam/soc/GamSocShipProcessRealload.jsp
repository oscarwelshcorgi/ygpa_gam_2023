<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocShipProcessRealload.jsp
 * @Description : 선박별 투자비보전처리실적
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.15  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.15
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocShipProcessRealloadSearchForm" method="validateGamSocShipProcessRealload" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocShipProcessRealloadModule() {}

GamSocShipProcessRealloadModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocShipProcessRealloadModule.prototype.loadComplete = function() {


    this.$("#socShipProcessRealloadList").flexigrid({
        module: this,
        url: '/soc/gamSocShipProcessRealloadList.do',
        dataType: 'json',
        colModel : [
					{display:'요금종류', 		name:'feeTp',			width:200, 		sortable:false,		align:'center'},
                    {display:'요금종류명', 	name:'feeTpNm',			width:250, 		sortable:false,		align:'center'},
                    {display:'총면제금액', 	name:'sumExmpAmnt',		width:430, 		sortable:false,		align:'right', 		displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: '200',
        preProcess: function(module,data) {

            return data;
        }
    });
    
    this.$("#socShipProcessRealloadList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
    	var detailInput = [
	   		   				{name: 'sPrtAtCode', 		value: row["sPrtAtCode"]},
	   		   				{name: 'sFrDt', 			value: row["sFrDt"]},
	   		   				{name: 'sToDt', 			value: row["sToDt"]},
	   		   				{name: 'sExmpAgentCode', 	value: row["sExmpAgentCode"]},
	   		   				{name: 'sVsslKey', 			value: row["sVsslKey"]},
	   		   				{name: 'feeTp', 			value: row["feeTp"]}
   		                   ]; 
		module.$('#socShipProcessRealloadDetail').flexOptions({params:detailInput}).flexReload();
	});
    
    
    this.$("#socShipProcessRealloadDetail").flexigrid({
        module: this,
        url: '/soc/gamSocShipProcessRealloadDetail.do',
        dataType: 'json',
        colModel : [
					{display:'업체코드', 			name:'exmpAgentCode',	width:80, 		sortable:false,		align:'center'},
                    {display:'호출부호', 			name:'callLetter',		width:120, 		sortable:false,		align:'center'},
                    {display:'선박명', 			name:'vsslNm',			width:150, 		sortable:false,		align:'center'},
                    {display:'입항연도', 			name:'yr',				width:120, 		sortable:false,		align:'center'},
                    {display:'입출항일자', 		name:'ioDt',			width:130, 		sortable:false,		align:'center'},
                    {display:'시설코드', 			name:'facilNm',			width:130, 		sortable:false,		align:'center'},
                    {display:'회계연도', 			name:'fiscalYr',		width:130, 		sortable:false,		align:'center'},
                    {display:'고지번호', 			name:'billNo',			width:130, 		sortable:false,		align:'center'},
                    {display:'적용요율', 			name:'standardFee',		width:130, 		sortable:false,		align:'center'},
                    {display:'징수톤', 			name:'realTn',			width:130, 		sortable:false,		align:'center'},
                    {display:'고지일자', 			name:'billDt',			width:130, 		sortable:false,		align:'center'},
                    {display:'할인률(%)', 		name:'rateNm',			width:130, 		sortable:false,		align:'center'},
                    {display:'면제금액', 			name:'exmpAmnt',		width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data["totalCount"]));
            module.$('#sumExmpAmnt').val($.number(data["sumExmpAmnt"]));

            return data;
        }
    });
    
    
    this.$('#sPrtAtCode').on('change', {module: this}, function(e) {
    	e.data.module.$('#sPrtKorNm').val(e.data.module.$('#sPrtAtCode > option:selected').text());
	});

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamSocShipProcessRealloadModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {
        case 'popupVsslCd' : //호출부호조회
        	var opts;
        	this.doExecuteDialog('selectVsslCd', '선박 선택','/popup/showSocVsslCd.do', opts);
        	break;

        case 'popupAgentInfo' : //면제업체 조회
        	var opts;
			this.doExecuteDialog('selectAgentInfo', '면제업체 선택', '/popup/showSocAgentFInfo.do', opts);
        	break;
    }
};


GamSocShipProcessRealloadModule.prototype.onSubmit = function() {
	if(!validateGamSocShipProcessRealload(this.$('#gamSocShipProcessRealloadSearchForm')[0])){ 		
		return;
	}
	
	// 그리드 초기화
	this.$('#socShipProcessRealloadDetail').flexEmptyData();
    this.loadData();
};

GamSocShipProcessRealloadModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamSocShipProcessRealloadSearchForm');

    this.$('#socShipProcessRealloadList').flexOptions({params:searchOpt}).flexReload();

};


//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocShipProcessRealloadModule.prototype.onClosePopup = function(popupId, msg, value) {

    switch (popupId) {
     case 'selectVsslCd':
    	 this.$("#sVsslKey").val(value["callLetter"]);
    	 this.$("#sVsslNm").val(value["vsslKorNm"]);
         break;
         
     case 'selectAgentInfo' : //면제업체 조회
    	 this.$("#sExmpAgentCode").val(value["agentCode"]);
    	 this.$("#sExmpAgentName").val(value["agentName"]);
    	 break;
     
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocShipProcessRealloadModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocShipProcessRealloadSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
	                            <input type="hidden" id="sPrtKorNm">
                            </td>
                            <th>고지기간</th>
                            <td>
                                <input id="sFrDt" type="text" class="emdcal" size="15"> ~ 
                                <input id="sToDt" type="text" class="emdcal" size="15">
                            </td>
                            <td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>                            
                            <th>업체코드</th>
                            <td>
                                <input type="text" size="9" id="sExmpAgentCode" maxlength="10"/>
                                <input type="text" size="25" id="sExmpAgentName" disabled/>
                                <button id="popupAgentInfo" class="popupButton">선택</button>
                            </td>
                            <th>호출부호</th>
                            <td>
                                <input id="sVsslKey" type="text" size="7" />
                                <input type="text" size="20" id="sVsslNm" disabled/>
                                <button id="popupVsslCd" class="popupButton">선택</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socShipProcessRealloadListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">선박별 투자비보전처리실적</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	
            	<table id="socShipProcessRealloadList" style="display:none"></table>
                <table id="socShipProcessRealloadDetail" style="display:none" class="fillHeight"></table>
                
                <div id="socShipProcessRealloadListSum" class="emdControlPanel">
					<form id="socShipProcessRealloadListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="10%" height="25">자료수</th>
								<td><input type="text" size="30" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">총면제금액</th>
								<td><input type="text" size="60" id="sumExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
    	                        	<button data-role="printPage" data-search-option="gamSocShipProcessRealloadSearchForm" data-url="<c:url value='/soc/gamSelectSocShipProcessRealloadListPrint.do'/>">인쇄</button>
        	                    </td>
							</tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>