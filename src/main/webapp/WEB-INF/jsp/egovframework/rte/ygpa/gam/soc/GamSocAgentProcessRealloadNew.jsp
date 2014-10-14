<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocAgentProcessRealloadNew.jsp
 * @Description : 업체별투자비보전상계내역
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.13    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.13
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
function GamSocAgentProcessRealloadNewModule() {}

GamSocAgentProcessRealloadNewModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocAgentProcessRealloadNewModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socAgentProcessRealloadNewList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocAgentProcessRealloadNewList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'호출부호', name:'appPrtAtCode',width:50, sortable:false,align:'center'},
                    {display:'선명', name:'appPrtAtKorNm',width:65, sortable:false,align:'center'},
                    {display:'요금종류', name:'agentCode',width:50, sortable:false,align:'left'},
                    {display:'요금종류명', name:'agentName',width:100, sortable:false,align:'left'},
                    {display:'입항년도', name:'cmplYr',width:50, sortable:false,align:'center'},
                    {display:'입출항일자', name:'constNo',width:50, sortable:false,align:'center'},
                    {display:'시설코드', name:'feeTp',width:50, sortable:false,align:'center'},
                    {display:'회계년도', name:'useNo',width:50, sortable:false,align:'center'},
                    {display:'고지번호', name:'rateGubun',width:40, sortable:false,align:'center'},
                    {display:'적용요율', name:'useYn',width:40, sortable:false,align:'center'},
                    {display:'징수톤', name:'periodFr',width:90, sortable:false,align:'center'},
                    {display:'고지일자', name:'periodTo',width:90, sortable:false,align:'center'},
                    {display:'할인율', name:'applDate',width:80, sortable:false,align:'center'},
                    {display:'면제금액', name:'perfDt',width:80, sortable:false,align:'center'}
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
GamSocAgentProcessRealloadNewModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
    switch(buttonId) {
        case 'searchBtn':
        	opts = this.makeFormArgs('#gamSocAgentProcessRealloadNewSearchForm');
            break;
        case 'btnPrint' : //인쇄버튼
        	opts = this.makeFormArgs('#gamSocAgentProcessRealloadNewSearchForm');
        	break;
    }
};

GamSocAgentProcessRealloadNewModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocAgentProcessRealloadNewModule.prototype.loadData = function() {
    this.$("#socAgentProcessRealloadNewListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocAgentProcessRealloadNewSearchForm');
    this.$('#socAgentProcessRealloadNewList').flexOptions({params:searchOpt}).flexReload();
};

GamSocAgentProcessRealloadNewModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocAgentProcessRealloadNewModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectApplyInfo' : //면제요청 조회
	   	 break;
	 default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocAgentProcessRealloadNewModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocAgentProcessRealloadNewSearchForm">
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
                            <th>요금종류</th>
                            <td>
                                <input id="sFeeTp" type="text" size="3">
                            	<input id="sFeeTpName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupFeeTpInfo" class="popupButton">선택</button>
                            </td>
                            <th>허가원부</th>
                            <td>
                                <input id="sCmplYr" type="text" size="4">
                            	<input id="sConstNo" type="text" size="6">
                            	<select id="">
                            		<option value="">보전</option>
                            	</select>
                            </td>
                            <td  rowSpan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>업체코드</th>
                            <td colspan="3">
                                <input id="sAppAgentCode" type="text" size="7">
                            	<input id="sAppAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            	<input id="sAgentApplyKnd" type="radio" name="sAgentKnd" value="true" /> 신청업체
                            	<input id="sAgentExmpKnd" type="radio" name="sAgentKnd"/> 면제업체
                            </td>
                            <th>기간</th>
                            <td>
                            	<input id="sBillDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sBillDtTo" type="text" class="emdcal" size="8">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socAgentProcessRealloadNewListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">업체별투자비보전상계내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
                </div>
                <table id="socAgentProcessRealloadNewList" style="display:none" class="fillHeight"></table>
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