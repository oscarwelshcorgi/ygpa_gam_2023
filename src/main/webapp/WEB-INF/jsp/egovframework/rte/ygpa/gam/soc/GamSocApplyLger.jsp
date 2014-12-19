<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocApplyLger.jsp
 * @Description : 투자비보전신청대장
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
<validator:javascript formName="gamSocApplyLgerSearchForm" method="validateGamSocApplyLger" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocApplyLgerModule() {}

GamSocApplyLgerModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocApplyLgerModule.prototype.loadComplete = function() {
    this.$("#socApplyLgerList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocApplyLgerList.do',
        dataType: 'json',
        colModel : [
                    {display:'요금종류', 	name:'feeTpNm',		width:70, sortable:false,align:'center'},
                    {display:'업체명', 	name:'appAgentName',width:110, sortable:false,align:'left'},
                    {display:'신청횟수', 	name:'useNo',		width:70, sortable:false,align:'center'},
                    {display:'신청보장액', 	name:'exmpAmnt',	width:110, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'보전누계액', 	name:'exmpAcc',		width:110, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'신청기간시작',name:'periodFr',	width:80, sortable:false,align:'center'},
                    {display:'신청기간종료',name:'periodTo',	width:80, sortable:false,align:'center'},
                    {display:'적용요율', 	name:'rateGubun',	width:70, sortable:false,align:'center'},
                    {display:'신고일자', 	name:'applDate',	width:70, sortable:false,align:'center'},
                    {display:'허가일자', 	name:'perfDt',		width:70, sortable:false,align:'center'},
                    {display:'적용여부',	name:'useYn',		width:70, sortable:false,align:'center'},
                    {display:'작업자', 	name:'updtUid',		width:50, sortable:false,align:'center'},
                    {display:'작업일자', 	name:'updtDate',	width:70, sortable:false,align:'center'},
                    {display:'특이사항', 	name:'remark',		width:150, sortable:false,align:'center'},
                    {display:'공사명', 	name:'item',		width:150, sortable:false,align:'center'},
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });
};

GamSocApplyLgerModule.prototype.onSubmit = function() {
    this.loadData();
};

//투자비보전신청대장 목록 로드
GamSocApplyLgerModule.prototype.loadData = function() {
	if(!validateGamSocApplyLger(this.$('#gamSocApplyLgerSearchForm')[0])){ 		
		return;
	}
    var searchOpt=this.makeFormArgs('#gamSocApplyLgerSearchForm');
    this.$('#socApplyLgerList').flexOptions({params:searchOpt}).flexReload();
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocApplyLgerModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'btnSearch':
        	this.loadData();
        	break;
        case 'popupFeeTpInfo' : //요금종류버튼
        	var opts = { prtAtCode : this.$('#sPrtAtCode').val() };
			this.doExecuteDialog('selectFeeTpInfo', '요금 선택', '/popup/showSocPayCd.do', opts);        	
        	break;
        case 'popupEntrpsInfo' : //업체코드버튼
			this.doExecuteDialog('selectEntrpsInfo', '업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break; 
    }
};

GamSocApplyLgerModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocApplyLgerModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
	    case 'selectFeeTpInfo' : //요금 조회
	   	 	this.$("#sFeeTp").val(value["feeTp"]);
	   	 	this.$("#sFeeTpKorNm").val(value["feeTpKorNm"]);
		   	break;
	    case 'selectEntrpsInfo' : //업체조회
	   	 	this.$("#sAppAgentCode").val(value["agentCode"]);
	   	 	this.$("#sAppAgentName").val(value["firmKorNm"]);
			break;
		default:
	        alert('알수없는 팝업 이벤트가 호출 되었습니다.');
	        break;
    }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocApplyLgerModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocApplyLgerSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>청코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="sFeeTp" type="text" size="7">
                            	<input id="sFeeTpKorNm" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupFeeTpInfo" class="popupButton">선택</button>
                            </td>
                            <td  rowSpan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>사용유무</th>
                            <td>
                                <select id="sUseYn">
                                	<option value="" selected="selected">선택</option>
                                	<option value="Y">사용</option>
                                	<option value="N">사용안함</option>
                                </select>
                            </td>
                            <th>업체코드</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="7">
                            	<input id="sAppAgentName" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socApplyLigerListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">투자비보전신청대장</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
                </div>
                <table id="socApplyLgerList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamSocApplyLgerSearchForm" data-url="<c:url value='/soc/gamSelectSocApplyLgerListPrint.do'/>">인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>