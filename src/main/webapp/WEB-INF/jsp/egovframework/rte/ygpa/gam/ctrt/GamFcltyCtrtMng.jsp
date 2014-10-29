<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtMng.jsp
 * @Description : 시설물 계약관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.28  김종민          최초 생성
 *
 * author 김종민
 * since 2014.10.28
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<!-- 
<validator:javascript formName="gamFcltyCtrtLgerHistSearchForm" method="validateGamFcltyCtrtMng" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="form1" method="validateGamFcltyCtrtMngDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
-->

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtMngModule() {}

GamFcltyCtrtMngModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtMngModule.prototype.loadComplete = function() {
    // 자산임대 테이블 설정
    this.$("#fcltyCtrtJoinContrList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectFcltyCtrtLgerHistList.do" />',
        dataType: 'json',
        colModel : [
					{display:'구분', 			name:'ctrtNo',				width:80, 		sortable:false,		align:'center'},
                    {display:'공고번호', 		name:'bidPblancNo',			width:100, 		sortable:false,		align:'center'},
                    {display:'계약명', 		name:'ctrtNm',				width:300, 		sortable:false,		align:'left'},
                    {display:'입찰공고일', 	name:'bidPblancDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'입찰일', 		name:'bidDt',				width:80, 		sortable:false,		align:'center'},
                    {display:'등록업체코드', 	name:'registEntrpsCd',		width:100, 		sortable:false,		align:'left'},
                    {display:'등록업체명', 	name:'registEntrpsNm',		width:150, 		sortable:false,		align:'left'},
                    {display:'설계금액', 		name:'planAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'예정금액', 		name:'prmtAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'낙찰금액', 		name:'scsbidAmt',			width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'낙찰율', 		name:'scsbidRate',			width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'기초금액', 		name:'baseAmt',				width:130, 		sortable:false,		align:'right', 		displayFormat: 'number'}
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
GamFcltyCtrtMngModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'searchBtn':
            break;
    }
};


GamFcltyCtrtMngModule.prototype.onSubmit = function() {
    this.loadData();
};

GamFcltyCtrtMngModule.prototype.loadData = function() {
	/*
    this.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamFcltyCtrtLgerHistSearchForm');

    this.$('#fcltyCtrtLgerHistList').flexOptions({params:searchOpt}).flexReload();
	console.log('debug');
*/
};

GamFcltyCtrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    case 'tabs3':
        break;
    case 'tabs4':
        break;
    case 'tabs5':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamFcltyCtrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectSocEntrpsInfoPopup':
         break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyCtrtMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamFcltyCtrtMngSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>계약번호</th>
                            <td>
                            	<input id="sCtrtNo" type="text" size="30">
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
        <div id="fcltyCtrtLgerHistListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">계약정보</a></li>
                <li><a href="#tabs2" class="emdTab">계약공동도급정보</a></li>
                <li><a href="#tabs2" class="emdTab">계약하도급정보</a></li>
                <li><a href="#tabs3" class="emdTab">계약변경정보</a></li>
                <li><a href="#tabs4" class="emdTab">계약대금지급정보</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtLgerHistForm">
                        <table class="detailPanel">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td><input type="text" size="15" id="ctrtSe" /></td>
                                <th width="10%" height="18">계약번호</th>
                                <td><input type="text" size="15" id="ctrtNo" /></td>
                                <th width="10%" height="18">계약명</th>
                                <td><input type="text" size="15" id="ctrtNm" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><input type="text" size="15" id="orderMthd" /></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><input type="text" size="15" id="ctrtSe" /></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><input type="text" size="15" id="bidPblancNo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰공고일자</th>
                                <td><input type="text" size="15" id="bidPblancDt" /></td>
                            	<th width="10%" height="18">하자기간</th>
                                <td><input type="text" size="15" id="flawPdFrom" /> ~ <input type="text" size="15" id="flawPdTo" /></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><input type="text" size="15" id="bidDt" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰방법</th>
                                <td><input type="text" size="15" id="bidMth" /></td>
                                <th width="10%" height="18">등록업체</th>
                                <td><input type="text" size="15" id="registEntrpsCd" /> <input type="text" size="15" id="registEntrpsNm" /></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><input type="text" size="15" id="jobChrgDeptCd" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">원인행위</th>
                                <td><input type="text" size="15" id="causeAct" /></td>
								<th width="10%" height="18">설계금액</th>
                                <td><input type="text" size="15" id="planAmt" /></td>
                                <th width="10%" height="18">예정금액</th>
                                <td><input type="text" size="15" id="prmtAmt" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td><input type="text" size="15" id="scsbidAmt" /></td>
                                <th width="10%" height="18">낙찰율</th>
                                <td><input type="text" size="15" id="scsbidRate" /></td>
                                <th width="10%" height="18">기초금액</th>
                                <td><input type="text" size="15" id="baseAmt" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약일자</th>
                                <td><input type="text" size="15" id="ctrtDt" /></td>
                            	<th width="10%" height="18">계약금액</th>
                                <td><input type="text" size="15" id="ctrtAmt" /></td>
                                <th width="10%" height="18">계약기간</th>
                                <td><input type="text" size="15" id="ctrtPdFrom" /> ~ <input type="text" size="15" id="ctrtPdTo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약보증금액</th>
                                <td><input type="text" size="15" id="ctrtGrntyAmt" /></td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><input type="text" size="15" id="ctrtGrntyMth" /></td>
								<th width="10%" height="18">조달공고번호</th>
                                <td><input type="text" size="15" id="prcuPblancNo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">감독자1</th>
                                <td><input type="text" size="15" id="intendant1" /></td>
                                <th width="10%" height="18">감독자2</th>
                                <td><input type="text" size="15" id="intendant2" /></td>
                                <th width="10%" height="18">감독자3</th>
                                <td><input type="text" size="15" id="intendant3" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약검사일자</th>
                                <td><input type="text" size="15" id="ctrtExamDt" /></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td><input type="text" size="15" id="caryFwdBdgtAmt" /></td>
                                <th width="10%" height="18">전자결재전송구분</th>
                                <td><input type="text" size="15" id="elctrnSanctnTrnsmisSe" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재진행코드</th>
                                <td><input type="text" size="15" id="elctrnSanctnProgrsCd" /></td>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><input type="text" size="15" id="elctrnSanctnTrnsmisDt" /></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><input type="text" size="15" id="elctrnSanctnInterlockInfo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재문서ID</th>
                                <td><input type="text" size="15" id="elctrnSanctnDocId" /></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><input type="text" size="15" id="confmDt" /></td>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><input type="text" size="15" id="confmerCd" /></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">연대보증</th>
                                <td><input type="text" size="15" id="sldrtGrnty" /></td>
                                <th width="10%" height="18">현장설명</th>
                                <td colspan="3"><input type="text" size="15" id="siteDesc" /></td>
                            </tr>
                        </table>
                    </form>
                 </div>
            </div>
            <div id="tabs2" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtChangeFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtChangeFListSum" class="emdControlPanel">
					<form id="fcltyCtrtChangeFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="20" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">변경계약금액</th>
								<td><input type="text" size="30" id="sumChangeCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">최종계약금액</th>
								<td><input type="text" size="30" id="sumLastCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistSearchForm" data-url="<c:url value='/soc/gamSelectFcltyCtrtLgerHistDetailPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtChangeFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtChangeFListSum" class="emdControlPanel">
					<form id="fcltyCtrtChangeFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="20" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">변경계약금액</th>
								<td><input type="text" size="30" id="sumChangeCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">최종계약금액</th>
								<td><input type="text" size="30" id="sumLastCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistSearchForm" data-url="<c:url value='/soc/gamSelectFcltyCtrtLgerHistDetailPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            <div id="tabs4" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtChangeFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtChangeFListSum" class="emdControlPanel">
					<form id="fcltyCtrtChangeFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="20" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">변경계약금액</th>
								<td><input type="text" size="30" id="sumChangeCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">최종계약금액</th>
								<td><input type="text" size="30" id="sumLastCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistSearchForm" data-url="<c:url value='/soc/gamSelectFcltyCtrtLgerHistDetailPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            <div id="tabs5" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtMoneyPymntList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtMoneyPymntListSum" class="emdControlPanel">
					<form id="fcltyCtrtMoneyPymntListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="8%" height="20">자료수</th>
								<td><input type="text" size="5" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">금회기성금액</th>
								<td><input type="text" size="12" id="sumThisTimeEstbAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">선금정산금액</th>
								<td><input type="text" size="12" id="sumDepositExcclcAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">지급금액</th>
								<td><input type="text" size="12" id="sumPymntAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">지급누계금액</th>
								<td><input type="text" size="12" id="sumPymntAggrAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistSearchForm" data-url="<c:url value='/soc/gamSelectFcltyCtrtLgerHistDetailPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>            
        </div>
    </div>
</div>