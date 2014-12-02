<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltyCtrtLgerHist.jsp
 * @Description : 시설물계약대장조회
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.28  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.28
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<%-- <validator:javascript formName="gamFcltyCtrtLgerHistSearchForm" method="validateGamFcltyCtrtLgerHist" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="form1" method="validateGamFcltyCtrtLgerHistDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> --%>

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtLgerHistModule() {}

GamFcltyCtrtLgerHistModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtLgerHistModule.prototype.loadComplete = function() {

    // 시설물계약대장 테이블 설정
    this.$("#fcltyCtrtLgerHistList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtLgerHistList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 		name:'ctrtNo',				width:120, 		sortable:false,		align:'center'},
					{display:'구분', 			name:'ctrtSe',				width:60, 		sortable:false,		align:'center'},
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

			//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumPlanAmt').val($.number(data.sumPlanAmt));
            module.$('#sumPrmtAmt').val($.number(data.sumPrmtAmt));
            module.$('#sumScsbidAmt').val($.number(data.sumScsbidAmt));
            module.$('#sumBaseAmt').val($.number(data.sumBaseAmt));

            return data;
        }
    });
    
    
	this.$("#fcltyCtrtLgerHistList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
		// tabs3  하부 테이블 초기화
    	module.makeDivValues('#fcltyCtrtJoinContrFDetailForm',{});
		
		// 값입력
		module.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		
		var detailInput = {'ctrtNo': row["ctrtNo"]};

		module.doAction('/ctrt/gamSelectFcltyCtrtLgerHistDetail.do', detailInput, function(module, data) {

			//계약대장 상세 정보 tabs2에 입력
    		module.makeDivValues('#gamFcltyCtrtLgerHistForm',data.resultDetail);
			//인쇄시 파라메타 전달을 위해 한번 더씀
    		module.makeFormValues('#gamFcltyCtrtLgerHistForm',data.resultDetail);
			
	 	});
		
		var searchVO = [{name: 'ctrtNo',value: row["ctrtNo"]}];
		module.$('#fcltyCtrtJoinContrFList').flexOptions({params:searchVO}).flexReload();
		module.$('#fcltyCtrtChangeFList').flexOptions({params:searchVO}).flexReload();
		module.$('#fcltyCtrtMoneyPymntFList').flexOptions({params:searchVO}).flexReload();
		module.$('#fcltyCtrtFulfillCaryFwdFList').flexOptions({params:searchVO}).flexReload();
	});
    
    
 	// 계약공동도급 테이블 설정
    this.$("#fcltyCtrtJoinContrFList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtJoinContrFList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 		name:'ctrtNo',			width:150, 		sortable:false,		align:'center'},
                    {display:'순번', 			name:'seq',				width:40, 		sortable:false,		align:'center'},
                    {display:'지분율', 		name:'qotaRate',		width:60, 		sortable:false,		align:'right'},
                    {display:'업체명', 		name:'entrpsNm',		width:200, 		sortable:false,		align:'left'},
                    {display:'대표자', 		name:'rprsntv',			width:80, 		sortable:false,		align:'center'},
                    {display:'전화번호', 		name:'tlphonNo',		width:120, 		sortable:false,		align:'center'},
                    {display:'FAX번호', 		name:'faxNo',			width:120, 		sortable:false,		align:'center'},
                    {display:'담당자', 		name:'charger',			width:80, 		sortable:false,		align:'center'},
                    {display:'담당자직위', 	name:'chargerOfcPos',	width:80, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
			//자료수 입력
            module.$('#tabs3TotalCount').val($.number(data.totalCount));

            return data;
        }
    });
 	
 	
    this.$("#fcltyCtrtJoinContrFList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		var detailInput = {'ctrtNo': row["ctrtNo"],
						   'seq': row["seq"]
						  };

		module.doAction('/ctrt/gamSelectFcltyCtrtJoinContrFDetail.do', detailInput, function(module, data) {

			//계약공동도급 상세정보  입력
    		module.makeDivValues('#fcltyCtrtJoinContrFDetailForm',data.resultDetail);
			
	 	});
	});
 	
 	
	// 계약변경 테이블 설정
    this.$("#fcltyCtrtChangeFList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtChangeFList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 			name:'ctrtNo',				width:150, 		sortable:false,		align:'center'},
                    {display:'순번', 				name:'seq',					width:60, 		sortable:false,		align:'center'},
                    {display:'변경사유', 			name:'changeRsn',			width:150, 		sortable:false,		align:'center'},
                    {display:'변경일자', 			name:'changeDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'변경계약기간FROM', 	name:'changeCtrtPdFrom',	width:110, 		sortable:false,		align:'center'},
                    {display:'변경계약기간TO', 		name:'changeCtrtPdTo',		width:110, 		sortable:false,		align:'center'},
                    {display:'변경계약금액', 		name:'changeCtrtAmt',		width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'최종계약금액', 		name:'lastCtrtAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'비고', 				name:'rm',					width:200, 		sortable:false,		align:'left'},
                    {display:'등록자', 			name:'regUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'등록일시', 			name:'registDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'수정자', 			name:'updUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'수정일시', 			name:'updtDt',				width:80, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	//자료수, 합산금액 입력
            module.$('#tabs4TotalCount').val($.number(data.totalCount));
            module.$('#sumChangeCtrtAmt').val($.number(data.sumChangeCtrtAmt));
            module.$('#sumLastCtrtAmt').val($.number(data.sumLastCtrtAmt));
        	
            return data;
        }
    });
	
	
	// 계약대금지급 테이블 설정
    this.$("#fcltyCtrtMoneyPymntFList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtMoneyPymntFList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 		name:'ctrtNo',				width:150, 		sortable:false,		align:'center'},
                    {display:'순번', 			name:'seq',					width:60, 		sortable:false,		align:'center'},
                    {display:'지급분류', 		name:'pymntCl',				width:60, 		sortable:false,		align:'right'},
                    {display:'지급일자', 		name:'pymntDt',				width:80, 		sortable:false,		align:'center'},
                    {display:'금회기성금액', 	name:'thisTimeEstbAmt',		width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'선금정산금액', 	name:'depositExcclcAmt',	width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'지급금액', 		name:'pymntAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'지급누계금액', 	name:'pymntAggrAmt',		width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'비고', 			name:'rm',					width:200, 		sortable:false,		align:'left'},
                    {display:'등록자', 		name:'regUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'등록일시', 		name:'registDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'수정자', 		name:'updUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'수정일시', 		name:'updtDt',				width:80, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	//자료수, 합산금액 입력
            module.$('#tabs5TotalCount').val($.number(data.totalCount));
            module.$('#sumThisTimeEstbAmt').val($.number(data.sumThisTimeEstbAmt));
            module.$('#sumDepositExcclcAmt').val($.number(data.sumDepositExcclcAmt));
            module.$('#sumPymntAmt').val($.number(data.sumPymntAmt));
            module.$('#sumPymntAggrAmt').val($.number(data.sumPymntAggrAmt));
        	
            return data;
        }
    });
	
	
	// 계약이행이월 테이블 설정
    this.$("#fcltyCtrtFulfillCaryFwdFList").flexigrid({
        module: this,
        url: '/ctrt/gamSelectFcltyCtrtFulfillCaryFwdFList.do',
        dataType: 'json',
        colModel : [
					{display:'계약번호', 		name:'ctrtNo',				width:150, 		sortable:false,		align:'center'},
                    {display:'순번', 			name:'seq',					width:60, 		sortable:false,		align:'center'},
                    {display:'이행이월년도', 	name:'fulfillCaryFwdYear',	width:80, 		sortable:false,		align:'center'},
                    {display:'이행금액', 		name:'fulfillAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'이월금액', 		name:'caryFwdAmt',			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'등록자', 		name:'regUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'등록일시', 		name:'registDt',			width:80, 		sortable:false,		align:'center'},
                    {display:'수정자', 		name:'updUsr',				width:80, 		sortable:false,		align:'center'},
                    {display:'수정일시', 		name:'updtDt',				width:80, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	//자료수, 합산금액 입력
            module.$('#tabs6TotalCount').val($.number(data.totalCount));
            module.$('#sumFulfillAmt').val($.number(data.sumFulfillAmt));
            module.$('#sumCaryFwdAmt').val($.number(data.sumCaryFwdAmt));
        	
            return data;
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamFcltyCtrtLgerHistModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':

        	var searchVO = [{name: 'ctrtNo',value: ''}];
        	
        	//tabs2 초기화
    		this.makeDivValues('#gamFcltyCtrtLgerHistForm',{});
    		this.makeFormValues('#gamFcltyCtrtLgerHistForm',{});
        	
        	// tabs3 초기화
        	this.$('#fcltyCtrtJoinContrFList').flexOptions({params:searchVO}).flexReload();
        	this.makeDivValues('#fcltyCtrtJoinContrFDetailForm',{});
        	
        	// tabs4 초기화
        	this.$('#fcltyCtrtChangeFList').flexOptions({params:searchVO}).flexReload();
        	
        	// tabs5 초기화
        	this.$('#fcltyCtrtMoneyPymntFList').flexOptions({params:searchVO}).flexReload();
        	
        	// tabs6 초기화
        	this.$('#fcltyCtrtFulfillCaryFwdFList').flexOptions({params:searchVO}).flexReload();
        	
			this.loadData();
            break;
            

        case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
            var opts;
            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
            
        
    }
};


GamFcltyCtrtLgerHistModule.prototype.onSubmit = function() {
    this.loadData();
};

GamFcltyCtrtLgerHistModule.prototype.loadData = function() {
    this.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 0});
    var searchOpt=this.makeFormArgs('#gamFcltyCtrtLgerHistSearchForm');

    this.$('#fcltyCtrtLgerHistList').flexOptions({params:searchOpt}).flexReload();

};

GamFcltyCtrtLgerHistModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamFcltyCtrtLgerHistModule.prototype.onClosePopup = function(popupId, msg, value) {

    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#sRegistEntrpsCd').val(value.entrpscd);
             this.$('#sRegistEntrpsNm').val(value.entrpsNm);
			 //this.loadData();
         } else {
             alert('취소 되었습니다');
         }
         break;
     
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyCtrtLgerHistModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamFcltyCtrtLgerHistSearchForm">
            
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>계약구분</th>
                            <td>
                                <select id="sCtrtSe">
                                    <option value="" selected="selected">전체</option>
                                    <option value="1">자체</option>
                                    <option value="2">조달</option>
                                </select>
                            </td>
                            <th width="10%">계약명</th>
                            <td>
                            	<input id="sCtrtNm" type="text" size="25">
                         	</td>
							<th width="10%">계약일</th>
                            <td>
                            	<input id="sCtrtFrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sCtrtToDt" type="text" class="emdcal" size="10">
                            </td>
                            
                            <td rowspan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th width="10%">등록업체</th>
                            <td colspan="3">
                            	<input id="sRegistEntrpsCd" type="text" size="7" disabled="disabled">&nbsp; &nbsp;
                         		<input id="sRegistEntrpsNm" type="text" size="30" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
                         	</td>
                         	<th width="10%">입찰일</th>
                            <td>
                            	<input id="sBidFrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sBidToDt" type="text" class="emdcal" size="10">
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
                <li><a href="#tabs2" class="emdTab">계약정보상세</a></li>
                <li><a href="#tabs3" class="emdTab">계약공동도급정보</a></li>
                <li><a href="#tabs4" class="emdTab">계약변경정보</a></li>
                <li><a href="#tabs5" class="emdTab">계약대금지급정보</a></li>
                <li><a href="#tabs6" class="emdTab">계약이행이월정보</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtLgerHistList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtLgerHistListSum" class="emdControlPanel">
					<form id="fcltyCtrtLgerHistListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="8%" height="20">자료수</th>
								<td><input type="text" size="7" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">설계금액</th>
								<td><input type="text" size="15" id="sumPlanAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">예정금액</th>
								<td><input type="text" size="15" id="sumPrmtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">낙찰금액</th>
								<td><input type="text" size="15" id="sumScsbidAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="8%" height="20">기초금액</th>
								<td><input type="text" size="15" id="sumBaseAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <div id="gamFcltyCtrtLgerHistForm">
                        <table class="detailPanel">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td width="15%"><span id="ctrtSe" ></span><input type="hidden" id="ctrtNo" ></td>
                                <th width="10%" height="18">계약번호</th>
                                <td width="15%"><span id="ctrtNo" ></span></td>
                                <th width="10%" height="18">계약명</th>
                                <td><span id="ctrtNm" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><span id="orderMthd" ></span></td>
                                <th width="10%" height="18">입찰공고일자</th>
                                <td><span id="bidPblancDt" ></span></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><span id="bidPblancNo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">하자기간</th>
                                <td><span id="flawPdFrom" ></span> ~ <span id="flawPdTo" ></span></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><span id="bidDt" ></span></td>
                                <th width="10%" height="18">입찰방법</th>
                                <td><span id="bidMth" ></span></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">등록업체</th>
                                <td><span id="registEntrpsCd" ></span> <span id="registEntrpsNm" ></span></td>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><span id="jobChrgDeptCd" ></span></td>
                                <th width="10%" height="18">원인행위</th>
                                <td><span id="causeAct" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">설계금액</th>
                                <td style="text-align:right;"><span id="planAmt" class="ygpaNumber"></span></td>
                                <th width="10%" height="18">예정금액</th>
                                <td style="text-align:right;"><span id="prmtAmt" class="ygpaNumber"></span></td>
                                <th width="10%" height="18">기초금액</th>
                                <td style="text-align:right;"><span id="baseAmt" class="ygpaNumber"></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">낙찰금액</th>
                                <td style="text-align:right;"><span id="scsbidAmt" class="ygpaNumber"></span></td>
                                <th width="10%" height="18">낙찰율</th>
                                <td style="text-align:right;"><span id="scsbidRate" ></span></td>
                                <th width="10%" height="18">계약일자</th>
                                <td><span id="ctrtDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약금액</th>
                                <td style="text-align:right;"><span id="ctrtAmt" class="ygpaNumber"></span></td>
                                <th width="10%" height="18">계약보증금액</th>
                                <td style="text-align:right;"><span id="ctrtGrntyAmt" class="ygpaNumber"></span></td>
                                <th width="10%" height="18">계약기간</th>
                                <td><span id="ctrtPdFrom" ></span> ~ <span id="ctrtPdTo" ></span></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><span id="ctrtGrntyMth" ></span></td>
                                <th width="10%" height="18">계약검사일자</th>
                                <td><span id="ctrtExamDt" ></span></td>
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
                            	<th width="10%" height="18">계약방법</th>
                                <td><span id="ctrtMth" ></span></td>
								<th width="10%" height="18">이월예산금액</th>
                                <td style="text-align:right;"><span id="caryFwdBdgtAmt" class="ygpaNumber"></span></td>
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
                            	<th width="10%" height="18">연대보증</th>
                                <td><span id="sldrtGrnty" ></span></td>
                            	<th width="10%" height="18">등록자</th>
                                <td><span id="regUsr" ></span></td>
                                <th width="10%" height="18">등록일시</th>
                                <td><span id="registDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">수정자</th>
                                <td><span id="updUsr" ></span></td>
                            	<th width="10%" height="18">수정일시</th>
                                <td><span id=updtDt ></span></td>
                                <th width="10%" height="18">현장설명</th>
                                <td><span id="siteDesc" ></span></td>
                            </tr>
                        </table>
                        <table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistForm" data-url="<c:url value='/ctrt/gamSelectFcltyCtrtLgerHistPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
                    </div>
                 </div>
            </div>
            
            <div id="tabs3" class="emdTabPage fillHeight" style="overflow: hidden;" >
            
                <table id="fcltyCtrtJoinContrFList" style="display:none" class="fillHeight"></table>
                
                <div id="fcltyCtrtJoinContrFListSum" class="emdControlPanel">
					<form id="fcltyCtrtJoinContrFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="15%" height="25">자료수</th>
								<td><input type="text" size="100" id="tabs3TotalCount" class="ygpaNumber" disabled="disabled" /></td>
								<td>
    	                        	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistForm" data-url="<c:url value='/ctrt/gamSelectFcltyCtrtLgerHistPrint.do'/>">계약대장인쇄</button>
        	                    </td>
							</tr>
						</table>
					</form>
                </div>
                <div id="fcltyCtrtJoinContrFDetail" class="emdControlPanel">
					<div id="fcltyCtrtJoinContrFDetailForm">
    	               	<table class="detailPanel">
                            <tr>
                            	<th width="10%" height="18">계약번호</th>
                                <td width="15%"><span id="ctrtNo" ></span></td>
								<th width="10%" height="18">순번</th>
                                <td width="15%"><span id="seq" ></span></td>
                                <th width="10%" height="18">지분율</th>
                                <td colspan="3"><span id="qotaRate" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">업체명</th>
                                <td><span id="entrpsNm" ></span></td>
                                <th width="10%" height="18">대표자</th>
                                <td><span id="rprsntv" ></span></td>
                                <th width="10%" height="18">전화번호</th>
                                <td colspan="3"><span id="tlphonNo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">FAX번호</th>
                                <td><span id="faxNo" ></span></td>
                            	<th width="10%" height="18">우편번호</th>
                                <td><span id="postNo" ></span></td>
								<th width="10%" height="18">도로명주소</th>
                                <td colspan="3"><span id="roadnmAdres" ></span></td>
                            </tr>
                            <tr>
                                <th width="10%" height="18">등록자</th>
                                <td><span id="regUsr" ></span></td>
                            	<th width="10%" height="18">등록일시</th>
                                <td><span id="registDt" ></span></td>
                                <th width="10%" height="18">지번주소</th>
                                <td colspan="3"><span id="lnmAdres" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">수정자</th>
                                <td><span id="updUsr" ></span></td>
								<th width="10%" height="18">수정일시</th>
                                <td><span id="updtDt" ></span></td>
                                <th width="10%" height="18">거래관계</th>
                                <td colspan="3"><span id="dealRelate" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">업종</th>
                                <td><span id="induty" ></span></td>
                                <th width="10%" height="18">주요품목</th>
                                <td><span id="stplPrdlst" ></span></td>
                                <th width="10%" height="18">사업자번호</th>
                                <td colspan="3"><span id="bsnmNo" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">담당자</th>
                                <td><span id="charger" ></span></td>
                            	<th width="10%" height="18">담당자 직위</th>
                                <td><span id="chargerOfcPos" ></span></td>
                                <th width="10%" height="18">담당자 휴대폰번호</th>
                                <td><span id="chargerMoblphonNo" ></span></td>
                                <th width="10%" height="18">담당자 E-Mail</th>
                                <td><span id="chargerEmail" ></span></td>
                            </tr>
                        </table>
					</div>
                </div>
            </div>
            
            <div id="tabs4" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtChangeFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtChangeFListSum" class="emdControlPanel">
					<form id="fcltyCtrtChangeFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="20" id="tabs4TotalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">변경계약금액</th>
								<td><input type="text" size="30" id="sumChangeCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">최종계약금액</th>
								<td><input type="text" size="30" id="sumLastCtrtAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistForm" data-url="<c:url value='/ctrt/gamSelectFcltyCtrtLgerHistPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            
            <div id="tabs5" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtMoneyPymntFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtMoneyPymntFListSum" class="emdControlPanel">
					<form id="fcltyCtrtMoneyPymntFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="8%" height="20">자료수</th>
								<td><input type="text" size="5" id="tabs5TotalCount" class="ygpaNumber" disabled="disabled" /></td>
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
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistForm" data-url="<c:url value='/ctrt/gamSelectFcltyCtrtLgerHistPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            
            <div id="tabs6" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtFulfillCaryFwdFList" style="display:none" class="fillHeight"></table>
                <div id="fcltyCtrtFulfillCaryFwdFListSum" class="emdControlPanel">
					<form id="fcltyCtrtFulfillCaryFwdFListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="12%" height="20">자료수</th>
								<td><input type="text" size="20" id="tabs6TotalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">이행금액</th>
								<td><input type="text" size="30" id="sumFulfillAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="20">이월금액</th>
								<td><input type="text" size="30" id="sumCaryFwdAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                            <td style="text-align: right">
	                            	<button data-role="printPage" data-search-option="gamFcltyCtrtLgerHistForm" data-url="<c:url value='/ctrt/gamSelectFcltyCtrtLgerHistPrint.do'/>">계약대장인쇄</button>
	                            </td>
	                        </tr>
						</table>
					</form>
                </div>
            </div>
            
        </div>
    </div>
</div>