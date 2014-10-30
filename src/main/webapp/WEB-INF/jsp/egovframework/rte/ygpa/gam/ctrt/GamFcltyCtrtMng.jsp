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
        url: '<c:url value="/soc/gamSelectSocApplyFacilList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'업체명', name:'entrpsNm',width:50, sortable:false,align:'center'},
                    {display:'대표자', name:'rprsntv',width:50, sortable:false,align:'center'},
                    {display:'지분율', name:'qotaRate',width:50, sortable:false,align:'center'},
                    {display:'업종', name:'induty',width:80, sortable:false,align:'center'},
                    {display:'주요품목', name:'stplPrdlst',width:80, sortable:false,align:'center'},
                    {display:'사업자번호', name:'bsnmNo',width:80, sortable:false,align:'center'},
                    {display:'거래관계', name:'dealRelate',width:80, sortable:false,align:'center'},
                    {display:'전화번호', name:'tlphonNo',width:80, sortable:false,align:'center'},
                    {display:'fax번호', name:'faxNo',width:80, sortable:false,align:'center'},
                    {display:'우편번호', name:'postNo',width:80, sortable:false,align:'center'},
                    {display:'도로명주소', name:'roadnmAdres',width:80, sortable:false,align:'center'},
                    {display:'지번주소', name:'lnmAdres',width:80, sortable:false,align:'center'},
                    {display:'담당자', name:'charger',width:80, sortable:false,align:'center'},
                    {display:'담당자직위', name:'chargerOfcPos',width:80, sortable:false,align:'center'},
                    {display:'담당자휴대폰번호', name:'chargerMoblphonNo',width:80, sortable:false,align:'center'},
                    {display:'담당자email', name:'chargerEmail',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: 'auto'
    });

    //계약하도급 리스트
    this.$("#fcltyCtrtSubCtrtList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocApplyFeeList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'업체명', name:'entrpsNm', width:100, sortable:true, align:'left'},
                    {display:'대금지급합의', name:'moneyPymntAgree', width:100, sortable:true, align:'left'},
                    {display:'공증', name:'workClass', width:100, sortable:true, align:'left'},
                    {display:'하도급율', name:'subctrlRate', width:100, sortable:true, align:'left'},
                    {display:'원도급금액', name:'originalContrAmt', width:100, sortable:true, align:'left'},
                    {display:'하도급계약금액', name:'subctrtCtrtAmt', width:100, sortable:true, align:'left'},
                    {display:'계약기간from', name:'ctrtDtFrom', width:100, sortable:true, align:'left'},
                    {display:'계약기간to', name:'ctrtDtTo', width:100, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약변경 리스트
    this.$("#fcltyCtrtChangeList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocApplyFeeList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'변경일자', name:'changeDt', width:100, sortable:true, align:'left'},
                    {display:'변경사유', name:'changeRsn', width:100, sortable:true, align:'left'},
                    {display:'변경계약기간from', name:'changeCtrtPdFrom', width:100, sortable:true, align:'left'},
                    {display:'변경계약기간to', name:'changeCtrtPdTo', width:100, sortable:true, align:'left'},
                    {display:'변경계약금액', name:'changeCtrtAmt', width:100, sortable:true, align:'left'},
                    {display:'최종계약금액', name:'lastCtrtAmt', width:100, sortable:true, align:'left'},
                    {display:'비고', name:'rm', width:100, sortable:true, align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto'
    });

    //계약대금지급 리스트
    this.$("#fcltyCtrtMoneyPymntList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSelectSocApplyFeeList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'지급분류', name:'pymntCl', width:100, sortable:true, align:'left'},
                    {display:'지급일자', name:'pymntDt', width:100, sortable:true, align:'left'},
                    {display:'금회기성금액', name:'thisTimeEstbAmt', width:100, sortable:true, align:'left'},
                    {display:'선금정산금액', name:'depositExcclcAmt', width:100, sortable:true, align:'left'},
                    {display:'지급금액', name:'pymntAmt', width:100, sortable:true, align:'left'},
                    {display:'지급누계금액', name:'pymntAggrAmt', width:100, sortable:true, align:'left'},
                    {display:'비고', name:'rm', width:100, sortable:true, align:'left'}
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
    switch(buttonId) {
        case 'searchBtn':
            break;
    }
};

GamFcltyCtrtMngModule.prototype.onSubmit = function() {
    this.loadData();
};

GamFcltyCtrtMngModule.prototype.loadData = function() {
    this.$("#socApplyListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamSocApplySearchForm');
    this.$('#socApplyList').flexOptions({params:searchOpt}).flexReload();
};

GamFcltyCtrtMngModule.prototype.removeApplyFacilItem = function() {
	var rows = this.$("#socApplyFacilList").selectedRows();

    if(rows.length == 0){
        alert("시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#socApplyFacilList").selectedRowIds().length>0) {
    	for(var i=this.$("#socApplyFacilList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#socApplyFacilList").flexGetRow(this.$("#socApplyFacilList").selectedRowIds()[i]);
        	this.$("#socApplyFacilList").flexRemoveRow(this.$("#socApplyFacilList").selectedRowIds()[i]);
		}
	}
};

GamFcltyCtrtMngModule.prototype.removeApplyFeeItem = function() {
	var rows = this.$("#socApplyFeeList").selectedRows();

    if(rows.length == 0){
        alert("시설물목록에서 삭제할 행을 선택하십시오.");
        return;
    }

    if(this.$("#socApplyFeeList").selectedRowIds().length>0) {
    	for(var i=this.$("#socApplyFeeList").selectedRowIds().length-1; i>=0; i--) {
    		var row = this.$("#socApplyFeeList").flexGetRow(this.$("#socApplyFeeList").selectedRowIds()[i]);
        	this.$("#socApplyFeeList").flexRemoveRow(this.$("#socApplyFeeList").selectedRowIds()[i]);
		}
	}
};

GamFcltyCtrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        if(oldTabId=='tabs1') {
        	this._deleteDataList=[];    // 삭제 목록 초기화
        	this._deleteDataFileList=[];    // 파일삭제 목록 초기화
        }
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamFcltyCtrtMngModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectFacInfo' : //시설물 추가
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
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
					<form id="gamFcltyCtrtMngDetailForm">
    	               	<table class="detailForm"  style="width:100%;">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td width="15%"><span id="ctrtSe" ></span></td>
                                <th width="10%" height="18">계약번호</th>
                                <td width="15%"><span id="ctrtNo" ></span></td>
                                <th width="10%" height="18">계약명</th>
                                <td><span id="ctrtNm" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><span id="orderMthd" ></span></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><span id="ctrtSe" ></span></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><span id="bidPblancNo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰공고일자</th>
                                <td><span id="bidPblancDt" ></span></td>
                            	<th width="10%" height="18">하자기간</th>
                                <td><span id="flawPdFrom" ></span> ~ <span id="flawPdTo" ></span></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><span id="bidDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">입찰방법</th>
                                <td><span id="bidMth" ></span></td>
                                <th width="10%" height="18">등록업체</th>
                                <td><span id="registEntrpsCd" ></span> <span id="registEntrpsNm" ></span></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><span id="jobChrgDeptCd" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">원인행위</th>
                                <td><span id="causeAct" ></span></td>
								<th width="10%" height="18">설계금액</th>
                                <td><span id="planAmt" ></span></td>
                                <th width="10%" height="18">예정금액</th>
                                <td><span id="prmtAmt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td><span id="scsbidAmt" ></span></td>
                                <th width="10%" height="18">낙찰율</th>
                                <td><span id="scsbidRate" ></span></td>
                                <th width="10%" height="18">기초금액</th>
                                <td><span id="baseAmt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약일자</th>
                                <td><span id="ctrtDt" ></span></td>
                            	<th width="10%" height="18">계약금액</th>
                                <td><span id="ctrtAmt" ></span></td>
                                <th width="10%" height="18">계약기간</th>
                                <td><span id="ctrtPdFrom" ></span> ~ <span id="ctrtPdTo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약보증금액</th>
                                <td><span id="ctrtGrntyAmt" ></span></td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><span id="ctrtGrntyMth" ></span></td>
								<th width="10%" height="18">조달공고번호</th>
                                <td><span id="prcuPblancNo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">감독자1</th>
                                <td><span id="intendant1" ></span></td>
                                <th width="10%" height="18">감독자2</th>
                                <td><span id="intendant2" ></span></td>
                                <th width="10%" height="18">감독자3</th>
                                <td><span id="intendant3" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약검사일자</th>
                                <td><span id="ctrtExamDt" ></span></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td><span id="caryFwdBdgtAmt" ></span></td>
                                <th width="10%" height="18">전자결재전송구분</th>
                                <td><span id="elctrnSanctnTrnsmisSe" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재진행코드</th>
                                <td><span id="elctrnSanctnProgrsCd" ></span></td>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><span id="elctrnSanctnTrnsmisDt" ></span></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><span id="elctrnSanctnInterlockInfo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재문서ID</th>
                                <td><span id="elctrnSanctnDocId" ></span></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><span id="confmDt" ></span></td>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><span id="confmerCd" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">등록자</th>
                                <td><span id="regUsr" ></span></td>
                                <th width="10%" height="18">등록일시</th>
                                <td><span id="registDt" ></span></td>
								<th width="10%" height="18">수정자</th>
                                <td><span id="updUsr" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">수정일시</th>
                                <td><span id=updtDt ></span></td>
                                <th width="10%" height="18">연대보증</th>
                                <td><span id="sldrtGrnty" ></span></td>
                                <th width="10%" height="18">현장설명</th>
                                <td><span id="siteDesc" ></span></td>
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
				                    <button id="btnAddApplyFacilItem">추가</button>
				                    <button id="btnRemoveApplyFacilItem">삭제</button>
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
				                    <button id="btnAddApplyFacilItem">추가</button>
				                    <button id="btnRemoveApplyFacilItem">삭제</button>
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
				                    <button id="btnAddApplyFacilItem">추가</button>
				                    <button id="btnRemoveApplyFacilItem">삭제</button>
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
				                    <button id="btnAddApplyFacilItem">추가</button>
				                    <button id="btnRemoveApplyFacilItem">삭제</button>
				                </td>
				            </tr>
						</table>
                    </form>
                 </div>
            </div>
                        
            
        </div>
    </div>
</div>