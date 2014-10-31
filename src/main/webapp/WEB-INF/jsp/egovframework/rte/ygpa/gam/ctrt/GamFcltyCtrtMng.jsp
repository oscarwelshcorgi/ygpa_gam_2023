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
 *  2014.10.29    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.29
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<!--  
<validator:javascript formName="gamSocApplySearchForm" method="validateGamSocApply" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
-->

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtMngModule() {}

GamFcltyCtrtMngModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtMngModule.prototype.loadComplete = function() {
	//계약공용도급 리스트
    this.$("#fcltyCtrtJoinContrList").flexigrid({
        module: this,
        url: '<c:url value="/ctrt/gamSelectFcltyCtrtJoinContrList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'업체명', name:'entrpsNm',width:120, sortable:false,align:'center'},
                    {display:'대표자', name:'rprsntv',width:70, sortable:false,align:'center'},
                    {display:'지분율', name:'qotaRate',width:70, sortable:false,align:'center'},
                    {display:'업종', name:'induty',width:80, sortable:false,align:'center'},
                    {display:'주요품목', name:'stplPrdlst',width:100, sortable:false,align:'center'},
                    {display:'사업자번호', name:'bsnmNo',width:100, sortable:false,align:'center'},
                    {display:'거래관계', name:'dealRelate',width:80, sortable:false,align:'center'},
                    {display:'전화번호', name:'tlphonNo',width:100, sortable:false,align:'center'},
                    {display:'fax번호', name:'faxNo',width:100, sortable:false,align:'center'},
                    {display:'우편번호', name:'postNo',width:80, sortable:false,align:'center'},
                    {display:'도로명주소', name:'roadnmAdres',width:150, sortable:false,align:'center'},
                    {display:'지번주소', name:'lnmAdres',width:150, sortable:false,align:'center'},
                    {display:'담당자', name:'charger',width:50, sortable:false,align:'center'},
                    {display:'담당자직위', name:'chargerOfcPos',width:80, sortable:false,align:'center'},
                    {display:'담당자휴대폰번호', name:'chargerMoblphonNo',width:100, sortable:false,align:'center'},
                    {display:'담당자email', name:'chargerEmail',width:150, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: 'auto'
    });

    //계약하도급 리스트
    this.$("#fcltyCtrtSubCtrtList").flexigrid({
        module: this,
        url: '<c:url value="/ctrt/gamSelectFcltyCtrtSubCtrtList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'업체명', name:'entrpsNm', width:150, sortable:true, align:'center'},
                    {display:'대금지급합의', name:'moneyPymntAgree', width:90, sortable:true, align:'center'},
                    {display:'공증', name:'workClass', width:80, sortable:true, align:'center'},
                    {display:'하도급율', name:'subctrlRate', width:80, sortable:true, align:'center'},
                    {display:'원도급금액', name:'originalContrAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'하도급계약금액', name:'subctrtCtrtAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'계약기간from', name:'ctrtDtFrom', width:100, sortable:true, align:'center'},
                    {display:'계약기간to', name:'ctrtDtTo', width:100, sortable:true, align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약변경 리스트
    this.$("#fcltyCtrtChangeList").flexigrid({
        module: this,
        url: '<c:url value="/ctrt/gamSelectFcltyCtrtChangeList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'변경일자', name:'changeDt', width:80, sortable:true, align:'center'},
                    {display:'변경사유', name:'changeRsn', width:120, sortable:true, align:'left'},
                    {display:'변경계약기간from', name:'changeCtrtPdFrom', width:100, sortable:true, align:'center'},
                    {display:'변경계약기간to', name:'changeCtrtPdTo', width:100, sortable:true, align:'center'},
                    {display:'변경계약금액', name:'changeCtrtAmt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'최종계약금액', name:'lastCtrtAmt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'비고', name:'rm', width:250, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약대금지급 리스트
    this.$("#fcltyCtrtMoneyPymntList").flexigrid({
        module: this,
        url: '<c:url value="/ctrt/gamSelectFcltyCtrtMoneyPymntList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'지급분류', name:'pymntCl', width:80, sortable:true, align:'center'},
                    {display:'지급일자', name:'pymntDt', width:80, sortable:true, align:'center'},
                    {display:'금회기성금액', name:'thisTimeEstbAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'선금정산금액', name:'depositExcclcAmt', width:120, sortable:true, align:'right', displayFormat:'number'},
                    {display:'지급금액', name:'pymntAmt', width:120, sortable:true, align:'left', displayFormat:'number'},
                    {display:'지급누계금액', name:'pymntAggrAmt', width:120, sortable:true, align:'left', displayFormat:'number'},
                    {display:'비고', name:'rm', width:210, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약이행이월 리스트
    this.$("#fcltyCtrtFulFillCaryFwdList").flexigrid({
        module: this,
        url: '<c:url value="/ctrt/gamSelectFcltyCtrtFulFillCaryFwdList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'이행이월년도', name:'fulfillCaryFwdYear', width:100, sortable:true, align:'left'},
                    {display:'이행금액', name:'fulfillAmt', width:200, sortable:true, align:'right', displayFormat:'number'},
                    {display:'이월금액', name:'caryFwdAmt', width:200, sortable:true, align:'right', displayFormat:'number'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltyCtrtMngModule.prototype.onButtonClick = function(buttonId) {
	var opts = null;
	var allRows = null;
	
    switch(buttonId) {
        case 'searchBtn':
            break;
        case 'btnSave':
        	alert('저장하였습니다.');
        	break;
        case 'btnCtrtJoinContrUpdate': //계약공동도급관리 행추가/삭제
        	allRows = this.$('#fcltyCtrtJoinContrList').flexGetData();
        	this.doExecuteDialog('updateCtrtJoinContr', '계약공동도급관리', '<c:url value="/popup/showCtrtJoinContrMngt.do" />', {}, allRows);
        	break;
        case 'btnCtrtSubCtrtUpdate': //계약하도급관리 행추가/삭제
        	allRows = this.$('#fcltyCtrtSubCtrtList').flexGetData();
        	this.doExecuteDialog('updateCtrtSubCtrt', '계약하도급관리', '<c:url value="/popup/showCtrtSubCtrtMngt.do" />', {}, allRows);
        	break;
        case 'btnCtrtChangeUpdate': //계약변경관리 행추가/삭제
        	allRows = this.$('#fcltyCtrtChangeList').flexGetData();
        	this.doExecuteDialog('updateCtrtChange', '계약변경관리', '<c:url value="/popup/showCtrtChangeMngt.do" />', {}, allRows);
        	break;
        case 'btnCtrtMoneyPymntUpdate': //계약대금지급 행추가/삭제
        	allRows = this.$('#fcltyCtrtMoneyPymntList').flexGetData();
        	this.doExecuteDialog('updateCtrtMoneyPymnt', '계약대금지급관리', '<c:url value="/popup/showCtrtMoneyPymntMngt.do" />', {}, allRows);
        	break;
        case 'btnCtrtFulFillCaryFwdUpdate': //계약이행이월 행추가/삭제
        	allRows = this.$('#fcltyCtrtFulFillCaryFwdList').flexGetData();
        	this.doExecuteDialog('updateCtrtFulFillCaryFwd', '계약대금지급관리', '<c:url value="/popup/showCtrtFulFillCaryFwdMngt.do" />', {}, allRows);
        	break;
    }
};

GamFcltyCtrtMngModule.prototype.onSubmit = function() {
    this.loadData();
};

GamFcltyCtrtMngModule.prototype.loadData = function() {
    this.$("#fcltyCtrtMngListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamFcltyCtrtMngSearchForm');
    this.$('#fcltyCtrtJoinContrList').flexOptions({params:searchOpt}).flexReload();
};

GamFcltyCtrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamFcltyCtrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'updateCtrtJoinContr' : //계약공동도급관리 행추가/삭제
			this.$("#fcltyCtrtJoinContrList").flexAddData({resultList: value});
	 		break;
		case 'updateCtrtSubCtrt' : //계약하도급관리 행추가/삭제
			this.$("#fcltyCtrtSubCtrtList").flexAddData({resultList: value});
	 		break;
		case 'updateCtrtChange' : //계약변경관리 행추가/삭제
			this.$("#fcltyCtrtChangeList").flexAddData({resultList: value});
	 		break;
		case 'updateCtrtMoneyPymnt' : //계약변경관리 행추가/삭제
			this.$("#fcltyCtrtMoneyPymntList").flexAddData({resultList: value});
	 		break;
		case 'updateCtrtFulFillCaryFwd' : //계약이행이월 행추가/삭제
			this.$("#fcltyCtrtFulFillCaryFwdList").flexAddData({resultList: value});
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
                            	<input id="sConstNo" type="text" size="30">
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
        <div id="fcltyCtrtMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">계약정보관리</a></li>
                <li><a href="#tabs2" class="emdTab">계약공동도급관리</a></li>
                <li><a href="#tabs3" class="emdTab">계약하도급관리</a></li>
                <li><a href="#tabs4" class="emdTab">계약변경관리</a></li>
                <li><a href="#tabs5" class="emdTab">계약대금지급관리</a></li>
                <li><a href="#tabs6" class="emdTab">계약이행이월관리</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="gamFcltyCtrtMngDetailForm">
						<input type="hidden" id="cmd" />
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td width="15%">
	                                <select id="ctrtSe">
	                                    <option value="" selected="selected">선택</option>
	                                    <option value="1">자체</option>
	                                    <option value="2">조달</option>
	                                </select>
                                </td>
                                <th width="10%" height="18">계약번호</th>
                                <td width="15%"><input type="text" size="12" id="ctrtNo" /></td>
                                <th width="10%" height="18">계약명</th>
                                <td><input type="text" size="20" id="ctrtNm" /></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><input type="text" size="12" id="orderMthd" /></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><input type="text" size="12" id="ctrtSe" /></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><input type="text" size="20" id="bidPblancNo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰공고일자</th>
                                <td><input type="text" size="12" id="bidPblancDt" class="emdcal" /></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><input type="text" size="12" id="bidDt" class="emdcal" /></td>
                            	<th width="10%" height="18">하자기간</th>
                                <td><input type="text" size="12" id="flawPdFrom" class="emdcal" /> ~ <input type="text" size="12" id="flawPdTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰방법</th>
                                <td><input type="text" size="12" id="bidMth" /></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><input type="text" size="12" id="jobChrgDeptCd" /></td>
                                <th width="10%" height="18">등록업체</th>
                                <td><input type="text" size="10" id="registEntrpsCd" /> <input type="text" size="10" id="registEntrpsNm" disabled="disabled" /> <button id="popupEntrpsInfo" class="popupButton">선택</button> </td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">원인행위</th>
                                <td><input type="text" size="12" id="causeAct" /></td>
								<th width="10%" height="18">설계금액</th>
                                <td><input type="text" size="12" id="planAmt" class="ygpaNumber" /></td>
                                <th width="10%" height="18">예정금액</th>
                                <td><input type="text" size="12" id="prmtAmt" class="ygpaNumber" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td><input type="text" size="12" id="scsbidAmt" class="ygpaNumber" /></td>
                                <th width="10%" height="18">낙찰율</th>
                                <td><input type="text" size="12" id="scsbidRate" /></td>
                                <th width="10%" height="18">기초금액</th>
                                <td><input type="text" size="12" id="baseAmt" class="ygpaNumber" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약일자</th>
                                <td><input type="text" size="12" id="ctrtDt" /></td>
                            	<th width="10%" height="18">계약금액</th>
                                <td><input type="text" size="12" id="ctrtAmt" class="ygpaNumber" /></td>
                                <th width="10%" height="18">계약기간</th>
                                <td><input type="text" size="12" id="ctrtPdFrom" class="emdcal" /> ~ <input type="text" size="12" id="ctrtPdTo" class="emdcal" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약보증금액</th>
                                <td><input type="text" size="12" id="ctrtGrntyAmt" class="ygpaNumber" /></td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><input type="text" size="12" id="ctrtGrntyMth" /></td>
								<th width="10%" height="18">조달공고번호</th>
                                <td><input type="text" size="20" id="prcuPblancNo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">감독자1</th>
                                <td><input type="text" size="12" id="intendant1" /></td>
                                <th width="10%" height="18">감독자2</th>
                                <td><input type="text" size="12" id="intendant2" /></td>
                                <th width="10%" height="18">감독자3</th>
                                <td><input type="text" size="12" id="intendant3" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약검사일자</th>
                                <td><input type="text" size="12" id="ctrtExamDt" class="emdcal" /></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td><input type="text" size="12" id="caryFwdBdgtAmt" class="ygpaNumber" /></td>
                                <th width="10%" height="18">전자결재전송구분</th>
                                <td><input type="text" size="20" id="elctrnSanctnTrnsmisSe" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재진행코드</th>
                                <td><input type="text" size="12" id="elctrnSanctnProgrsCd" /></td>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><input type="text" size="12" id="elctrnSanctnTrnsmisDt" class="emdcal" /></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><input type="text" size="20" id="elctrnSanctnInterlockInfo" /></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재문서ID</th>
                                <td><input type="text" size="12" id="elctrnSanctnDocId" /></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><input type="text" size="12" id="confmDt" class="emdcal" /></td>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><input type="text" size="12" id="confmerCd" /></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">연대보증</th>
                                <td><input type="text" size="12" id="sldrtGrnty" /></td>
                                <th width="10%" height="18">현장설명</th>
                                <td colspan="3"><input type="text" size="60" id="siteDesc" /></td>
                            </tr>
                        </table>
					</form>
                </div>
                <div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button id="btnNew">등록</button>
	                                <button id="btnSave">저장</button>
	                                <button id="btnRemove">삭제</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtJoinContrForm">
						<table id="fcltyCtrtJoinContrList" style="display:none" class="fillHeight"></table>
						<table style="width:100%;">
				            <tr>
				            	<td style="text-align: right">
				                    <button id="btnCtrtJoinContrUpdate" class="popupButton">행추가/삭제</button>
				                    <button id="btnSave">저장</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
            
            <div id="tabs3" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtSubCtrtForm">
						<table id="fcltyCtrtSubCtrtList" style="display:none" class="fillHeight"></table>
						<table style="width:100%;">
				            <tr>
				            	<td style="text-align: right">
				                    <button id="btnCtrtSubCtrtUpdate" class="popupButton">행추가/삭제</button>
				                    <button id="btnSave">저장</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
            
            <div id="tabs4" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtChangeForm">
						<table id="fcltyCtrtChangeList" style="display:none" class="fillHeight"></table>
						<table style="width:100%;">
				            <tr>
				            	<td style="text-align: right">
				                    <button id="btnCtrtChangeUpdate" class="popupButton">행추가/삭제</button>
				                    <button id="btnSave">저장</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
            
            <div id="tabs5" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtMoneyPymntForm">
						<table id="fcltyCtrtMoneyPymntList" style="display:none" class="fillHeight"></table>
						<table style="width:100%;">
				            <tr>
				            	<td style="text-align: right">
				                    <button id="btnCtrtMoneyPymntUpdate" class="popupButton">행추가/삭제</button>
				                    <button id="btnSave">저장</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
                        
            <div id="tabs6" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamFcltyCtrtFulFillCaryFwdForm">
						<table id="fcltyCtrtFulFillCaryFwdList" style="display:none" class="fillHeight"></table>
						<table style="width:100%;">
				            <tr>
				            	<td style="text-align: right">
				                    <button id="btnCtrtFulFillCaryFwdUpdate" class="popupButton">행추가/삭제</button>
				                    <button id="btnSave">저장</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
            
        </div>
    </div>
</div>