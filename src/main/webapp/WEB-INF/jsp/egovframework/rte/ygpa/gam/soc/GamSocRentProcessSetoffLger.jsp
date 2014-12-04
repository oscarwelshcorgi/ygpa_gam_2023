<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocRentProcessSetoffLger.jsp
 * @Description : 투자비보전(임대)상계처리대장
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.16  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.16
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocRentProcessSetoffLgerSearchForm" method="validateGamSocRentProcessSetoffLger" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocRentProcessSetoffLgerModule() {}

GamSocRentProcessSetoffLgerModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocRentProcessSetoffLgerModule.prototype.loadComplete = function() {


    this.$("#socRentProcessSetoffLgerList").flexigrid({
        module: this,
        url: '/soc/gamSocRentProcessSetoffLgerList.do',
        dataType: 'json',
        colModel : [
					{display:'선명', 			name:'dVsslKorNm',		width:80, 		sortable:false,		align:'center'},
					{display:'호출부호', 		name:'callLetter',		width:80, 		sortable:false,		align:'center'},
					{display:'입항횟수', 		name:'yicn',			width:60, 		sortable:false,		align:'center'},
					{display:'관리번호', 		name:'fiscalSocNo',		width:120, 		sortable:false,		align:'center'},
					{display:'상계일자', 		name:'billDt',			width:120, 		sortable:false,		align:'center'},
					{display:'적용요율', 		name:'standardFee',		width:60, 		sortable:false,		align:'center'},
					{display:'입출항일자', 	name:'ioDt',			width:120, 		sortable:false,		align:'center'},
					{display:'내외항', 		name:'ixtn',			width:80, 		sortable:false,		align:'center'},
					{display:'신고업체', 		name:'mctmc',			width:120, 		sortable:false,		align:'center'},
					{display:'신고업체명', 	name:'agentNm3',		width:150, 		sortable:false,		align:'center'},
					{display:'처리항구', 		name:'prtKorNm',		width:120, 		sortable:false,		align:'center'},
					{display:'요금종류', 		name:'feeTp',			width:80, 		sortable:false,		align:'center'},
					{display:'요금종류명', 	name:'feeTpKorNm',		width:120, 		sortable:false,		align:'center'},
					{display:'할인율코드', 	name:'dcCode',			width:100, 		sortable:false,		align:'center'},
					{display:'할인율(%)', 	name:'rate',			width:80, 		sortable:false,		align:'center'},
					{display:'운임톤', 		name:'realTn',			width:60, 		sortable:false,		align:'right', 	displayFormat: 'number'},
					{display:'상계금액(청)', 	name:'exmpAmnt',		width:150, 		sortable:false,		align:'right', 	displayFormat: 'number'},
					{display:'상계금액(공사)', 	name:'exmpAmntPa',		width:150, 		sortable:false,		align:'right', 	displayFormat: 'number'},
					{display:'특이사항', 		name:'remark',			width:200, 		sortable:false,		align:'center'}

                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	if(data.resultList[0]){
	        	// 상단 데이터 입력 
	        	module.makeFormValues('#socRentProcessSetoffLgerForm',data.resultList[0]);
        	}else{
        		// 상단 데이터 초기화
        		module.makeFormValues('#socRentProcessSetoffLgerForm',{});
        	}
        	
        	//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data["totalCount"]));
            module.$('#sumExmpAmnt').val($.number(data["sumExmpAmnt"]));
            module.$('#sumExmpAmntPa').val($.number(data["sumExmpAmntPa"]));
            module.$('#sumAmnt').val($.number(data["sumAmnt"]));

            return data;
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamSocRentProcessSetoffLgerModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	if(!validateGamSocRentProcessSetoffLger(this.$('#gamSocRentProcessSetoffLgerSearchForm')[0])){ 		
        		return;
        	}
        	
			this.loadData();

            break;
            
        case 'popupFeeInfo' : //요금종류조회
        	var opts;
        	this.doExecuteDialog('selectFeeInfo', '금종류 선택','/popup/showSocPayCd.do', opts);
        	break;

        case 'popupAgentInfo' : //신청업체조회
        	var opts;
        	this.doExecuteDialog('selectAgentInfo', '신청업체 선택','/popup/showSocEntrpsInfo.do', opts);
        	break;

        case 'popupFcltyInfo' : //신청시설 조회
        	var opts;
			this.doExecuteDialog('selectFcltyInfo', '신청시설 선택', '/popup/showSocFacCd.do', opts);
        	break;
        	
        case 'popupTotalPortInfo' : //전체 조회
        	var opts = {'gubun': 'K'}; 
			this.doExecuteDialog('selectPortInfo', '투자비보전 전체 선택', '/popup/showSocApplyEntrpsInfo.do',{}, opts);
        	break;
        	
        case 'popupSelectPortInfo' : //해당항별 조회
        	var appPrtAtCode = this.$("#sAppPrtAtCode").val();
        	var opts = {'gubun': 'K',
        	            'appPrtAtCode': appPrtAtCode}; 
			this.doExecuteDialog('selectPortInfo', '투자비보전 해당항별 선택', '/popup/showSocApplyEntrpsInfo.do',{}, opts);
        	break;
        	
        case 'popupIngPortInfo' : //해당항진행 조회
        
        	var appPrtAtCode = this.$("#sAppPrtAtCode").val();
        	var opts = {'gubun': 'K',
   		   				'appPrtAtCode': appPrtAtCode,
   		   				'useYn': 'Y'}; 
			this.doExecuteDialog('selectPortInfo', '투자비보전 해당항 진행 선택', '/popup/showSocApplyEntrpsInfo.do',{}, opts);
        	break;

    }
};


GamSocRentProcessSetoffLgerModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocRentProcessSetoffLgerModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamSocRentProcessSetoffLgerSearchForm');

    this.$('#socRentProcessSetoffLgerList').flexOptions({params:searchOpt}).flexReload();

};


//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocRentProcessSetoffLgerModule.prototype.onClosePopup = function(popupId, msg, value) {

    switch (popupId) {
     case 'selectFeeInfo':
    	 this.$("#sFeeTp").val(value["feeTp"]);
    	 this.$("#sFeeTpNm").val(value["feeTpKorNm"]);
         break;
         
     case 'selectAgentInfo' : //신청업체 조회
    	 this.$("#sAppAgentCode").val(value["agentCode"]);
    	 this.$("#sAppAgentNm").val(value["firmKorNm"]);
    	 break;
    	 
     case 'selectFcltyInfo' : //신청시설 조회
    	 this.$("#sFacCode").val(value["facCode"]);
    	 this.$("#sFacSubCode").val(value["facSubCode"]);
    	 this.$("#sFacKorNm").val(value["facKorNm"]);
    	 break;
    	 
     case 'selectPortInfo' : //투자비보전 신청업체 조회
    	 this.$("#sAppPrtAtCode").val(value["appPrtAtCode"]);
    	 this.$("#sPrtAtCode").val(value["prtAtCode"]);
    	 this.$("#sCmplYr").val(value["cmplYr"]);
    	 this.$("#sConstNo").val(value["constNo"]);
    	 this.$("#sFeeTp").val(value["feeTp"]);
    	 this.$("#sFeeTpNm").val(value["feeTpNm"]);
    	 this.$("#sRateGubunNm").val(value["rateGubunNm"]);
    	 this.$("#sUseNo").val(value["useNo"]);
    	 this.$("#sAppAgentCode").val(value["appAgentCode"]);
    	 this.$("#sAppAgentNm").val(value["appAgentNm"]);
    	 this.$("#sExmpAmnt").val(value["exmpAmnt"]);
    	 this.$("#sExmpAcc").val(value["exmpAcc"]);
    	 break;
     
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocRentProcessSetoffLgerModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocRentProcessSetoffLgerSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>등록항구</th>
                            <td>
                                <select id="sAppPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
                            </td>
                            <th>공사항구</th>
                            <td>
                                <select id="sPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
                            </td>
                            <th>문서번호</th>
                            <td>
                                <input id="sCmplYr" type="text" size="5" />
                                <input type="text" size="15" id="sConstNo" disabled/>
                            </td>
                            <td rowspan="4"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>    
							<th>요금종류</th>
                            <td>
                                <input type="text" size="5" id="sFeeTp" maxlength="10" readonly/>
                                <input type="text" size="8" id="sFeeTpNm" disabled/>
                                <button id="popupFeeInfo" class="popupButton">선택</button>
                            </td>     
							<th>적용요율</th>
                            <td>
                                <input id="sRateGubunNm" type="text" size="15" />
                            </td>                   
                            <th>신청횟수</th>
                            <td>
                                <input id="sUseNo" type="text" size="15" />
                            </td>
                        </tr>
                        <tr>            
                        	<th>신청업체</th>
                            <td>
                                <input type="text" size="5" id="sAppAgentCode" maxlength="10" readonly/>
                                <input type="text" size="8" id="sAppAgentNm" disabled/>
                                <button id="popupAgentInfo" class="popupButton">선택</button>
                            </td>
                            <th>보전처리대상금액</th>
                            <td>
                                <input id="sExmpAmnt" type="text" size="15" />
                            </td>                
                            <th>상계기간</th>
                            <td>
                                <input id="sDtFr" type="text" class="emdcal" size="12"> ~ 
                                <input id="sDtTo" type="text" class="emdcal" size="12">
                            </td>
                        </tr>
                        <tr>
                            <th>보전누계액</th>
                            <td>
                                <input id="sExmpAcc" type="text" size="15" />
                            </td>
                            <th>입출항일자</th>
                            <td colspan="3">
                                <input id="sIoDt" type="text" class="emdcal" size="12"> ~ 
                                <input id="sIoDt2" type="text" class="emdcal" size="12">
                            </td>
                        </tr>
                        <tr>     
                        	<td colspan="6">
                        		<table style="width:100%;">
				                    <tbody>
				                        <tr>
				                        	<th style="width:15%;">투자비보전 신청업체 선택</th>
				                            <td>
				                                <button id="popupTotalPortInfo" class="popupButton">전체</button> 
				                                <button id="popupSelectPortInfo" class="popupButton">해당항별</button> 
				                                <button id="popupIngPortInfo" class="popupButton">해당항진행</button> 
				                            </td>
				                        </tr>
				                    </tbody>
				                </table>
                        	</td>                       
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socRentProcessSetoffLgerListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">투자비보전(임대)상계처리대장</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            <form id="socRentProcessSetoffLgerForm">
            	<table class="detailForm"  style="width:100%;">
                    <tr>
                        <th>공사항구</th>
                        <td>
                        	<input id="prtAtCode" type="text" size="7" readOnly="readonly" />
                            <input id="prtAtNm" type="text" size="12" disabled="disabled">
                        </td>
                        <th>준공년도</th>
                        <td>
                            <input id="cmplYr" type="text" size="5" readOnly="readonly" />
                        </td>
                        <th>공사번호</th>
                        <td>
                            <input id="constNo" type="text" size="10" readOnly="readonly" />
                        </td>
					</tr>
					<tr>    
						<th>요청항구</th>
                        <td>
                        	<input id="appPrtAtCode" type="text" size="7" readOnly="readonly" />
                            <input id="appPrtAtNm" type="text" size="12" disabled="disabled">
                        </td>
                        <th>요청업체</th>
                        <td>
                            <input id="sctmc" type="text" size="10" readOnly="readonly" />
                            <input id="agentNm2" type="text" size="20" disabled="disabled">
                        </td>
                        <th>공사명</th>
                        <td>
                            <input id="socCnstNm" type="text" size="30" readOnly="readonly" />
                        </td>
                     </tr>
                     <tr>
                        <th>신청횟수</th>
                        <td>
                            <input id="useNo" type="text" size="10" class="ygpaNumber" readOnly="readonly" />
                        </td>
                        <th>총공사금액</th>
                        <td>
                            <input id="totalAmnt" type="text" size="30" class="ygpaNumber" readOnly="readonly" />
                        </td>
                        <th>보전처리대상금액</th>
                        <td>
                            <input id="exmpAmnt" type="text" size="30" class="ygpaNumber" readOnly="readonly" />
                        </td>
                    </tr>
                </table>
            </form>
            	<table id="socRentProcessSetoffLgerList" style="display:none"  class="fillHeight"></table>
                
                <div id="socRentProcessSetoffLgerListSum" class="emdControlPanel">
					<form id="socRentProcessSetoffLgerListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="10%" height="25">자료수</th>
								<td><input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">총계(청)</th>
								<td><input type="text" size="17" id="sumExmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">총계(공사)</th>
								<td><input type="text" size="17" id="sumExmpAmntPa" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">총계(전체)</th>
								<td><input type="text" size="17" id="sumAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<td style="text-align:right;">
    	                        	<button data-role="printPage" data-search-option="gamSocRentProcessSetoffLgerSearchForm" data-url="<c:url value='/soc/gamSelectSocRentProcessSetoffLgerListPrint.do'/>">인쇄</button>
        	                    </td>
							</tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>