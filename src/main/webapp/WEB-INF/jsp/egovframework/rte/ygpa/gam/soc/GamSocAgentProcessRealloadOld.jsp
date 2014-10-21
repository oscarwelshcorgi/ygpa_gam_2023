<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocAgentProcessRealloadOld.jsp
 * @Description : 업체별 투자비보전 처리실적(구)
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.14    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.14
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamSocApply" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>
<!--
<validator:javascript formName="gamSocApply" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamSocApplyFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocAgentProcessRealloadOldModule() {}

GamSocAgentProcessRealloadOldModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocAgentProcessRealloadOldModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socAgentProcessRealloadOldList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocAgentProcessRealloadOldList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'공사항구', name:'appPrtAtCode',width:50, sortable:false,align:'center'},
                    {display:'공사항구명', name:'appPrtAtKorNm',width:65, sortable:false,align:'center'},
                    {display:'시행업체', name:'agentCode',width:50, sortable:false,align:'left'},
                    {display:'시행업체명', name:'agentName',width:100, sortable:false,align:'left'},
                    {display:'준공년도', name:'cmplYr',width:50, sortable:false,align:'center'},
                    {display:'공사번호', name:'constNo',width:50, sortable:false,align:'center'},
                    {display:'요금종류', name:'feeTp',width:50, sortable:false,align:'center'},
                    {display:'횟수', name:'useNo',width:50, sortable:false,align:'center'},
                    {display:'요율', name:'rateGubun',width:40, sortable:false,align:'center'},
                    {display:'적용', name:'useYn',width:40, sortable:false,align:'center'},
                    {display:'보전기간시작일', name:'periodFr',width:90, sortable:false,align:'center'},
                    {display:'보전기간종료일', name:'periodTo',width:90, sortable:false,align:'center'},
                    {display:'신청일자', name:'applDate',width:80, sortable:false,align:'center'},
                    {display:'허가일자', name:'perfDt',width:80, sortable:false,align:'center'},
                    {display:'보전신청액', name:'exmpAmnt',width:130, sortable:false,align:'center'},
                    {display:'보전누계액', name:'exmpAcc',width:130, sortable:false,align:'center'},
                    {display:'남아있는 잔액', name:'exmpRemain',width:130, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocAgentProcessRealloadOldModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
    switch(buttonId) {
        case 'searchBtn':
        	opts = this.makeFormArgs('#gamSocAgentProcessRealloadOldSearchForm');
            break;
        case 'btnPrint' : //인쇄버튼
        	opts = this.makeFormArgs('#gamSocAgentProcessRealloadOldSearchForm');
        	break;
    }
};

GamSocAgentProcessRealloadOldModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocAgentProcessRealloadOldModule.prototype.loadData = function() {
    this.$("#socAgentProcessRealloadOldListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocAgentProcessRealloadOldSearchForm');
    this.$('#socAgentProcessRealloadOldList').flexOptions({params:searchOpt}).flexReload();
};

GamSocAgentProcessRealloadOldModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocAgentProcessRealloadOldModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
	 default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocAgentProcessRealloadOldModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocAgentProcessRealloadOldSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>청코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">전체</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>징수의뢰일자</th>
                            <td>
                            	<input id="sBillDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sBillDtTo" type="text" class="emdcal" size="8">
                            </td>
                            <th>업체코드</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="7">
                            	<input id="sAppAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <td>
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socAgentProcessRealloadOldListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">투자비보전처리실적</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
                </div>
                <table id="socAgentProcessRealloadOldList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnPrint">인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>