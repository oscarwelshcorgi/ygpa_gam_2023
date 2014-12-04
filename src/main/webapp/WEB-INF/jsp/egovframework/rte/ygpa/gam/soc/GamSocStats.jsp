<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocStats.jsp
 * @Description : 투자비보전집계
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.17    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.13
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocStatsSearchForm" method="validateGamSocStats" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocStatsModule() {}

GamSocStatsModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocStatsModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socStatsList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocStatsList.do',
        dataType: 'json',
        colModel : [
                    {display:'업체명', name:'exmpAgentNm',width:200, sortable:false,align:'center'},
                    {display:'년월', name:'exmpMonth',width:100, sortable:false,align:'center'},
                    {display:'요금종류명', name:'feeNm',width:150, sortable:false,align:'center'},
                    {display:'상계금액(청)', name:'exmpAmntSum',width:150, sortable:false,align:'right',displayFormat:'number'},
                    {display:'상계금액(공사)', name:'exmpAmntPaSum',width:150, sortable:false,align:'right',displayFormat:'number'},
                    {display:'합계', name:'exmpAmntTotSum',width:150, sortable:false,align:'right',displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	module.$('#totalCount').val($.number(data.totalCount));
        	module.$('#totExmpAmntSum').val($.number(data.totExmpAmntSum));
        	module.$('#totExmpAmntPaSum').val($.number(data.totExmpAmntPaSum));
        	module.$('#totExmpAmntTotSum').val($.number(data.totExmpAmntTotSum));
            return data;
        }
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocStatsModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
    switch(buttonId) {
        case 'searchBtn':
        	if(!validateGamSocStats(this.$('#gamSocStatsSearchForm')[0])){ 		
        		return;
        	}
        	if(this.$('#sSearchTarget').val() == '1') {
            	if(this.$('#sExmpAgentCode').val() == '') {
            		alert('업체코드를 선택하세요.');
            		break;
            	}
        	} else if(this.$('#sSearchTarget').val() == '2') {
            	if(this.$('#sSearchFr').val() == '' || this.$('#sSearchTo').val() == '') {
            		alert('조회월을 입력하세요.');
            		break;
            	}
        	} else {
            	if(this.$('#sExmpAgentCode').val() == '') {
            		alert('업체코드를 선택하세요.');
            		break;
            	}
            	if(this.$('#sSearchFr').val() == '' || this.$('#sSearchTo').val() == '') {
            		alert('조회월을 입력하세요.');
            		break;
            	}        	
            }
        	opts = this.makeFormArgs('#gamSocStatsSearchForm');
        	this.$("#socStatsList").flexOptions({params:opts}).flexReload();
            break;
        case 'btnPrint' : //인쇄버튼
        	opts = this.makeFormArgs('#gamSocStatsSearchForm');
        	break;
        case 'popupEntrpsInfo' : //업체코드버튼
			this.doExecuteDialog('selectEntrpsInfo', '업체 선택',
					'/popup/showSocEntrpsInfo.do', opts);
        	break;
    }
};

GamSocStatsModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocStatsModule.prototype.loadData = function() {
    this.$("#socStatsListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocStatsSearchForm');
    this.$('#socStatsList').flexOptions({params:searchOpt}).flexReload();
};

GamSocStatsModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocStatsModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfo' : //업체조회
    	 this.$("#sExmpAgentCode").val(value["agentCode"]);
    	 this.$("#sExmpAgentName").val(value["firmKorNm"]);
		 break;
	 default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocStatsModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocStatsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>조회대상</th>
                            <td>
                                <select id="sSearchTarget">
                                    <option value="1" selected="selected">업체별 투자비 보전집계표</option>
                                    <option value="2">월별 투자비 보전집계표 </option>
                                    <option value="3">업체별/월별 투자비 보전집계표</option>
                                </select>
                            </td>
                            <th>기준</th>
                            <td>
                                <select id="sBasis">
                                    <option value="1" selected="selected">업체단위</option>
                                    <option value="2">조회월단위 </option>
                                </select>
                            </td>
                            <td  rowSpan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>업체코드</th>
                            <td>
                                <input id="sExmpAgentCode" type="text" size="7">
                            	<input id="sExmpAgentName" type="text" size="10" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>조회년월</th>
                            <td>
                                <input id="sSearchFr" type="text" size="6"> &nbsp; ~ &nbsp; 
                            	<input id="sSearchTo" type="text" size="6">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socStatsListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">업체별 투자비보전 집계</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
                </div>
                <table id="socStatsList" style="display:none" class="fillHeight"></table>
                <div id="socStatsListSum" class="emdControlPanel">
					<form id="socStatsListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="25">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">상계금액(청)</th>
								<td><input type="text" size="20" id="totExmpAmntSum" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">상계금액(공사)</th>
								<td><input type="text" size="20" id="totExmpAmntPaSum" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">합계금액</th>
								<td><input type="text" size="20" id="totExmpAmntTotSum" class="ygpaNumber" disabled="disabled" /></td>
								<td>
    	                        	<button data-role="printPage" data-search-option="gamSocStatsSearchForm" data-url="<c:url value='/soc/gamSelectSocStatsListPrint.do'/>">인쇄</button>
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