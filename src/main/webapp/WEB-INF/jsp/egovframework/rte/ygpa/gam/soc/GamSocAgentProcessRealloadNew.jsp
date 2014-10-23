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
                    {display:'호출부호', name:'callLetter',width:50, sortable:false,align:'center'},
                    {display:'선명', name:'vsslNm',width:80, sortable:false,align:'center'},
                    {display:'요금종류', name:'feeTp',width:50, sortable:false,align:'left'},
                    {display:'요금종류명', name:'feeTpNm',width:100, sortable:false,align:'left'},
                    {display:'입항년도', name:'yr',width:50, sortable:false,align:'center'},
                    {display:'입출항일자', name:'ioDt',width:70, sortable:false,align:'center'},
                    {display:'시설코드', name:'facilNm',width:80, sortable:false,align:'center'},
                    {display:'회계년도', name:'fiscalYr',width:50, sortable:false,align:'center'},
                    {display:'고지번호', name:'billNo',width:80, sortable:false,align:'center'},
                    {display:'적용요율', name:'standardFee',width:50, sortable:false,align:'center'},
                    {display:'징수톤', name:'realTn',width:50, sortable:false,align:'center'},
                    {display:'고지일자', name:'billDt',width:80, sortable:false,align:'center'},
                    {display:'할인율', name:'dcRateNm',width:80, sortable:false,align:'center'},
                    {display:'면제금액', name:'exmpAmnt',width:80, sortable:false,align:'right',displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalCount').val($.number(data["totalCount"]));
            module.$('#sumExmpAmnt').val($.number(data["sumExmpAmnt"]));
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
        	if(this.$('#sPrtAtCode').val() == '') {
        		alert('항코드를 선택하세요.');
        		break;
        	}
        	opts = this.makeFormArgs('#gamSocAgentProcessRealloadNewSearchForm');
        	this.$("#socAgentProcessRealloadNewList").flexOptions({params:opts}).flexReload();
            break;
        case 'popupFeeTpInfo' : //요금종류 선택
        	opts = { prtAtCode : this.$('#sAppPrtAtCode').val() };
			this.doExecuteDialog('selectFeeTpInfo', '요금 선택',
					'<c:url value="/popup/showSocPayCd.do"/>', opts);        	
        	break;
        case 'popupEntrpsInfo' : //업체코드버튼
			this.doExecuteDialog('selectEntrpsInfo', '업체 선택',
					'<c:url value="/popup/showSocEntrpsInfo.do"/>', opts);
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
     case 'selectFeeTpInfo' : //요금 조회
	   	 this.$("#sFeeTp").val(value["feeTp"]);
	   	 this.$("#sFeeTpName").val(value["feeTpKorNm"]);
	   	 break;
     case 'selectEntrpsInfo' : //업체조회
	   	 this.$("#sAgentCode").val(value["agentCode"]);
	   	 this.$("#sAgentName").val(value["firmKorNm"]);
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
                                <select id="sAppPrtAtCode">
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
                            	<select id="sBillGubun">
                            		<option value="1">보전</option>
                            		<option value="2">고지</option>
                            	</select>
                            </td>
                            <td  rowSpan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>업체코드</th>
                            <td colspan="3">
                                <input id="sAgentCode" type="text" size="7">
                            	<input id="sAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            	<select id="sAgentGubun">
                            		<option value="1">신청업체</option>
                            		<option value="2">면제업체</option>
                            	</select>
                            </td>
                            <th>기간</th>
                            <td>
                            	<input id="sSearchDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sSearchDtTo" type="text" class="emdcal" size="8">
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
                <div id="socAgentProcessRealloadNewListSum" class="emdControlPanel">
					<form id="socAgentProcessRealloadNewListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="25">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">면제금액</th>
								<td><input type="text" size="18" id="sumExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<td>
    	                        	<button data-role="printPage" data-search-option="gamSocAgentProcessRealloadNewSearchForm" data-url="<c:url value='/soc/gamSelectSocAgentProcessRealloadNewListPrint.do'/>">인쇄</button>
        	                    </td>
							</tr>
						</table>
						<!-- <table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                                <button id="btnPopupSaveSocAgent">행추가/삭제</button>
	                            </td>
	                        </tr>
						</table> -->
					</form>
                </div>
      
            </div>
        </div>
    </div>
</div>