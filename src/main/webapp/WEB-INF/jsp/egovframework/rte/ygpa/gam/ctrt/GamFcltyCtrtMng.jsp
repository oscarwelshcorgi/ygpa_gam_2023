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
 *  2014.10.28  lsl          최초 생성
 *
 * author lsl
 * since 2014.10.28
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamFcltyCtrtLgerHistSearchForm" method="validateGamFcltyCtrtLgerHist" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="form1" method="validateGamFcltyCtrtLgerHistDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyCtrtLgerHistModule() {}

GamFcltyCtrtLgerHistModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyCtrtLgerHistModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#fcltyCtrtLgerHistList").flexigrid({
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
        	
        	module._fcltyCtrtLgerHistInfo = data.fcltyCtrtLgerHistInfo;
        	
        	//그리드 상단 입력창에 정보 입력
        	if(data.fcltyCtrtLgerHistInfo){
        		if(data.resultCode == '0'){
	        		module.makeFormValues('#form1',data.fcltyCtrtLgerHistInfo);
		        	
		        	//항만공사시행허가원부II 정보입력
	        		module.makeFormValues('#gamFcltyCtrtLgerHistForm',data.fcltyCtrtLgerHistInfo);
        		}
	        	
        		module.$("#cmd").val("modify");
        		
       		}else{
       			//console.log('debug');
       			if(data.resultCode == '0'){
	       			module.makeFormValues('#form1',{});
	       			//항만공사시행허가원부II 초기화
	        		module.makeFormValues('#gamFcltyCtrtLgerHistForm',{});
       			}
	        	module.$("#cmd").val("insert");
       			
       		}
        	
			//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data.totalCount));
            module.$('#sumTotalAmnt').val($.number(data.sumTotalAmnt));
            module.$('#sumAccFee').val($.number(data.sumAccFee));

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
        	
        	if(!validateGamFcltyCtrtLgerHist(this.$('#gamFcltyCtrtLgerHistSearchForm')[0])){ 		
        		return;
        	}
        	
			this.loadData();
            break;
            
       // 등록포맷으로 변환 -- 초기화 및 상태값 변경
       case 'btnRegiItem':
    	    this.makeFormValues('#gamFcltyCtrtLgerHistSearchForm',{});
    	    this.makeFormValues('#form1',{});
    	    this.makeFormValues('#form2',{});
    	    this.makeFormValues('#gamFcltyCtrtLgerHistForm',{});
    	    
    	    this.loadData();
    	    
    	    this.$("#cmd").val("insert");
    	    
            break;
       

        // 신청저장
        case 'btnSaveItem':
        	
        	if(!validateGamFcltyCtrtLgerHist(this.$('#gamFcltyCtrtLgerHistSearchForm')[0])){ 		
        		return;
        	}
        	
        	if(!validateGamFcltyCtrtLgerHistDetail(this.$('#form1')[0])){ 		
        		return;
        	}

        	var inputVO = [];
        	
        	var all_rows = JSON.stringify(this.$('#fcltyCtrtLgerHistList').flexGetData());
        	var searchData = JSON.stringify(this.getFormValues("#gamFcltyCtrtLgerHistSearchForm"));
        	var updateData = JSON.stringify(this.getFormValues("#form1"));
        	var updateData1 = JSON.stringify(this.getFormValues("#gamFcltyCtrtLgerHistForm"));
        	
        	inputVO[inputVO.length] = {name: 'updateList',value: all_rows};
        	inputVO[inputVO.length] = {name: 'searchData',value: searchData};
        	inputVO[inputVO.length] = {name: 'updateData',value: updateData};
        	inputVO[inputVO.length] = {name: 'updateData1',value: updateData1};
        	
        	if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/soc/gamInsertFcltyCtrtLgerHistList.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamFcltyCtrtLgerHistSearchForm");
						module.$("#fcltyCtrtLgerHistList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 0});
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
			 	this.doAction('<c:url value="/soc/gamUpdateFcltyCtrtLgerHistList.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == "0"){
			 			var searchOpt = module.makeFormArgs("#gamFcltyCtrtLgerHistSearchForm");
						module.$("#fcltyCtrtLgerHistList").flexOptions({params:searchOpt}).flexReload();
						module.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 0});
			 		}
			 		alert(result.resultMsg);
			 	});
			}

            break;

        //신청삭제
        case 'btnRemoveItem':
        	
        	if(!validateGamFcltyCtrtLgerHist(this.$('#gamFcltyCtrtLgerHistSearchForm')[0])){ 		
        		return;
        	}
        	
        	var inputVO = [];
        	var searchData = JSON.stringify(this.getFormValues("#gamFcltyCtrtLgerHistSearchForm"));

        	inputVO[inputVO.length] = {name: 'searchData',value: searchData};
        	this.doAction('<c:url value="/soc/gamDeleteFcltyCtrtLgerHistList.do" />', inputVO, function(module, result) {
		 		if(result.resultCode == "0"){
		 			var searchOpt = module.makeFormArgs("#gamFcltyCtrtLgerHistSearchForm");
					module.$("#fcltyCtrtLgerHistList").flexOptions({params:searchOpt}).flexReload();
					module.$("#fcltyCtrtLgerHistListTab").tabs("option", {active: 0});
		 		}
		 		alert(result.resultMsg);
		 	});
        	
            break;
            
        case 'popupFcltyCtrtLgerHistFInfo': // 허가원부선택 팝업을 호출한다.(조회)
            var opts;
            this.doExecuteDialog('selectFcltyCtrtLgerHistFInfoPopup', '허가원부선택', '<c:url value="/popup/showFcltyCtrtLgerHistFInfo.do"/>', opts);
            break;

        case 'popupEntrpsInfo': // 업체선택 팝업을 호출한다.(조회)
            var opts;
            this.doExecuteDialog('selectSocEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showSocEntrpsInfo.do"/>', opts);
            break;
            
        case 'btnPopupSaveFcltyCtrtLgerHist':
    		var all_rows = this.$('#fcltyCtrtLgerHistList').flexGetData();
    		this.doExecuteDialog("addFcltyCtrtLgerHistPopup", "항만공사시행허가원부추가", '/popup/showFcltyCtrtLgerHist.do', {},all_rows);
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
	console.log('debug');

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
     case 'selectSocEntrpsInfoPopup':
         if (msg != 'cancel') {
             this.$('#agentCode').val(value.agentCode);
             this.$('#agentName').val(value.firmKorNm);
			 //this.loadData();
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'selectFcltyCtrtLgerHistFInfoPopup':
         if (msg != 'cancel') {
             this.$('#sPrtAtCode').val(value.prtAtCode);
             this.$('#sCmplYr').val(value.cmplYr);
             this.$('#sConstNo').val(value.constNo);
			 this.loadData();
         } else {
             alert('취소 되었습니다');
         }
         break;
     case 'addFcltyCtrtLgerHistPopup':
    	 
    	 if(this['_fcltyCtrtLgerHistInfo']==undefined){
    		 
    		 this['_fcltyCtrtLgerHistInfo'] = null;
    	 }
    	 this.$("#fcltyCtrtLgerHistList").flexAddData({resultList: value, fcltyCtrtLgerHistInfo:this._fcltyCtrtLgerHistInfo });
    	 
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
                                <!-- <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /> -->
                                <select id="sCtrtSe">
                                    <option value="tot" selected="selected">전체</option>
                                    <option value="in">자체</option>
                                    <option value="out">외주</option>
                                </select>
                            </td>
                            <th width="10%">계약명</th>
                            <td>
                            	<input id="sCtrtNm" type="text" size="25">
                         	</td>
							<th width="10%">입찰공고일</th>
                            <td>
                            	<input id="sBidPblancFrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sBidPblancToDt" type="text" class="emdcal" size="10">
                            </td>
                            
                            <td rowspan="2">
								<button id="searchBtn" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                        	<th width="10%">등록업체</th>
                            <td colspan="3">
                            	<input id="sRegistEntrpsCd" type="text" size="7">&nbsp; &nbsp;
                         		<input id="sRegistEntrpsNm" type="text" size="12" disabled="disabled">&nbsp; &nbsp;
                         		<button id="popupEntrpsInfo" class="popupButton">선택</button>
                         	</td>
                         	<th width="10%">입찰일</th>
                            <td>
                            	<input id="sBidfrDt" type="text" class="emdcal" size="10"> ~ 
                            	<input id="sBidtoDt" type="text" class="emdcal" size="10">
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
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
                <table id="fcltyCtrtLgerHistList" style="display:none" class="fillHeight"></table>
                <div id="agentListSum" class="emdControlPanel">
					<form id="form2">
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
                    <form id="gamFcltyCtrtLgerHistForm">
                        <table class="detailPanel">
                            <tr>
								<th width="10%" height="18">계약구분</th>
                                <td><span id="ctrtSe" ></span></td>
                                <th width="10%" height="18">계약번호</th>
                                <td><span id="ctrtNo" ></span></td>
                                <th width="10%" height="18">계약명</th>
                                <td colspan="5"><span id="ctrtNm" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">발주방식</th>
                                <td><span id="orderMthd" ></span></td>
                                <th width="10%" height="18">계약방법</th>
                                <td><span id="ctrtSe" ></span></td>
                                <th width="10%" height="18">입찰공고번호</th>
                                <td><span id="bidPblancNo" ></span></td>
                                <th width="10%" height="18">입찰공고일자</th>
                                <td><span id="bidPblancDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">하자기간</th>
                                <td><span id="flawPdFrom" ></span> ~ <span id="flawPdTo" ></span></td>
								<th width="10%" height="18">입찰일자</th>
                                <td><span id="bidDt" ></span></td>
                                <th width="10%" height="18">입찰방법</th>
                                <td><span id="bidMth" ></span></td>
                                <th width="10%" height="18">등록업체</th>
                                <td><span id="registEntrpsCd" ></span> <span id="registEntrpsNm" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">업무담당부서코드</th>
                                <td><span id="jobChrgDeptCd" ></span></td>
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
								<th width="10%" height="18">계약일자</th>
                                <td><span id="ctrtDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">계약금액</th>
                                <td><span id="ctrtAmt" ></span></td>
                                <th width="10%" height="18">계약기간</th>
                                <td><span id="ctrtPdFrom" ></span> ~ <span id="ctrtPdTo" ></span></td>
                                <th width="10%" height="18">계약보증금액</th>
                                <td><span id="ctrtGrntyAmt" ></span></td>
                                <th width="10%" height="18">계약보증방법</th>
                                <td><span id="ctrtGrntyMth" ></span></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">조달공고번호</th>
                                <td><span id="prcuPblancNo" ></span></td>
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
                                <th width="10%" height="18">전자결재진행코드</th>
                                <td><span id="elctrnSanctnProgrsCd" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">전자결재전송일자</th>
                                <td><span id="elctrnSanctnTrnsmisDt" ></span></td>
                                <th width="10%" height="18">전자결재연동정보</th>
                                <td><span id="elctrnSanctnInterlockInfo" ></span></td>
								<th width="10%" height="18">전자결재문서ID</th>
                                <td><span id="elctrnSanctnDocId" ></span></td>
                                <th width="10%" height="18">승인일자</th>
                                <td><span id="confmDt" ></span></td>
                            </tr>
                            <tr>
                            	<th width="10%" height="18">승인자코드</th>
                                <td><span id="confmerCd" ></span></td>
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
                                <td colspan="3"><span id="siteDesc" ></span></td>
                            </tr>
                        </table>
                    </form>
                 </div>
            </div>
        </div>
    </div>
</div>