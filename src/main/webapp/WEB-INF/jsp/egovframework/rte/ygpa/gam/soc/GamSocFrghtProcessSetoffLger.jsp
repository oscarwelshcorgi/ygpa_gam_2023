<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocFrghtProcessSetoffLger.jsp
 * @Description : 투자비보전(화물)상계처리대장
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
<validator:javascript formName="gamSocFrghtProcessSetoffLgerSearchForm" method="validateGamSocFrghtProcessSetoffLger" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocFrghtProcessSetoffLgerModule() {}

GamSocFrghtProcessSetoffLgerModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocFrghtProcessSetoffLgerModule.prototype.loadComplete = function() {


    this.$("#socFrghtProcessSetoffLgerList").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSocFrghtProcessSetoffLgerList.do" />',
        dataType: 'json',
        colModel : [
					{display:'공사항구', 		name:'prtAtCode',		width:80, 		sortable:false,		align:'center'},
					{display:'공사항명', 		name:'prtAtNm',			width:120, 		sortable:false,		align:'center'},
                    {display:'준공년도', 		name:'cmplYr',			width:80, 		sortable:false,		align:'center'},
                    {display:'공사번호', 		name:'constNo',			width:120, 		sortable:false,		align:'center'},
                    {display:'공사명', 		name:'socCnstNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'요청항구', 		name:'appPrtAtCode',	width:80, 		sortable:false,		align:'center'},
                    {display:'요청항명', 		name:'appPrtAtNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'요청업체', 		name:'appAgentCode',	width:120, 		sortable:false,		align:'center'},
                    {display:'요청업체명', 	name:'appAgentNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'신청횟수', 		name:'useNo',			width:60, 		sortable:false,		align:'center'},
                    {display:'총공사금액',		name:'totalAmnt',		width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'보전처리대상금액',	name:'exmpAmnt',		width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'}
                    ],
        showTableToggleBtn: false,
        height: '150',
        preProcess: function(module,data) {

            return data;
        }
    });
    
    this.$("#socFrghtProcessSetoffLgerList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		
    	var detailInput = [
   		   				{name: 'sPrtAtCode', value: row["sPrtAtCode"]},
   		   				{name: 'sFrDt', value: row["sFrDt"]},
   		   				{name: 'sToDt', value: row["sToDt"]},
   		   				{name: 'sExmpAgentCode', value: row["sExmpAgentCode"]},
   		   				{name: 'sVsslKey', value: row["sVsslKey"]},
   		   				{name: 'feeTp', value: row["feeTp"]}
   		                   ]; 
		module.$('#socFrghtProcessSetoffLgerDetail').flexOptions({params:detailInput}).flexReload();
	});
    
    
    this.$("#socFrghtProcessSetoffLgerDetail").flexigrid({
        module: this,
        url: '<c:url value="/soc/gamSocFrghtProcessSetoffLgerDetail.do" />',
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
                    {display:'신고업체', 		name:'mictmc',			width:120, 		sortable:false,		align:'center'},
                    {display:'신고업체명', 	name:'agentNm3',		width:150, 		sortable:false,		align:'center'},
                    {display:'처리항구', 		name:'prtKorNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'요금종류', 		name:'feeTp',			width:80, 		sortable:false,		align:'center'},
                    {display:'요금종류명', 	name:'feeTpKorNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'할인율코드', 	name:'dcCode',			width:60, 		sortable:false,		align:'center'},
                    {display:'할인율(%)', 	name:'rate',			width:80, 		sortable:false,		align:'center'},
                    {display:'운임톤', 		name:'realTn',			width:60, 		sortable:false,		align:'center'},
                    {display:'상계금액(청)', 	name:'exmpAmnt',		width:150, 		sortable:false,		align:'right', 	displayFormat: 'number'},
                    {display:'상계금액(공사)', 	name:'exmpAmntPa',		width:150, 		sortable:false,		align:'right', 	displayFormat: 'number'},
                    {display:'특이사항', 		name:'remark',			width:200, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
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
 GamSocFrghtProcessSetoffLgerModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	/* if(!validateGamSocFrghtProcessSetoffLger(this.$('#gamSocFrghtProcessSetoffLgerSearchForm')[0])){ 		
        		return;
        	} */
        	
			this.loadData();
			
			var detailInput = [
		   		   				{name: 'sPrtAtCode', value: ''},
		   		   				{name: 'sFrDt', value: ''},
		   		   				{name: 'sToDt', value: ''},
		   		   				{name: 'sExmpAgentCode', value: ''},
		   		   				{name: 'sVsslKey', value: ''},
		   		   				{name: 'feeTp', value: ''}
		   		                   ]; 
			this.$('#socFrghtProcessSetoffLgerDetail').flexOptions({params:detailInput}).flexReload();

            break;
            
        case 'popupFeeInfo' : //요금종류조회
        	var opts;
        	this.doExecuteDialog('selectFeeInfo', '금종류 선택','<c:url value="/popup/showSocPayCd.do"/>', opts);
        	break;

        case 'popupAgentInfo' : //신청업체조회
        	var opts;
        	this.doExecuteDialog('selectAgentInfo', '신청업체 선택','<c:url value="/popup/showSocEntrpsInfo.do"/>', opts);
        	break;

        case 'popupFcltyInfo' : //신청시설 조회
        	var opts;
			this.doExecuteDialog('selectFcltyInfo', '신청시설 선택', '<c:url value="/popup/showSocFacCd.do"/>', opts);
        	break;
        	
        case 'popupTotalPortInfo' : //전체 조회
        	var opts;
			this.doExecuteDialog('selectTotalPortInfo', '신청시설 선택', '<c:url value="/popup/showSocFacCd.do"/>', opts);
        	break;
        	
        case 'popupSelectPortInfo' : //해당항별 조회
        	var opts;
			this.doExecuteDialog('selectFcltyInfo', '신청시설 선택', '<c:url value="/popup/showSocFacCd.do"/>', opts);
        	break;
        	
        case 'popupIngPortInfo' : //해당항진행 조회
        	var opts;
			this.doExecuteDialog('selectFcltyInfo', '신청시설 선택', '<c:url value="/popup/showSocFacCd.do"/>', opts);
        	break;

    }
};


GamSocFrghtProcessSetoffLgerModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocFrghtProcessSetoffLgerModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamSocFrghtProcessSetoffLgerSearchForm');

    this.$('#socFrghtProcessSetoffLgerList').flexOptions({params:searchOpt}).flexReload();

};


//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocFrghtProcessSetoffLgerModule.prototype.onClosePopup = function(popupId, msg, value) {

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
     
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocFrghtProcessSetoffLgerModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocFrghtProcessSetoffLgerSearchForm">
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
                                <input id="sCmplYr" type="text" size="2" />
                                <input type="text" size="15" id="sConstNo" disabled/>
                            </td>
                            <td rowspan="4"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>    
							<th>요금종류</th>
                            <td>
                                <input type="text" size="9" id="sFeeTp" maxlength="10" readonly/>
                                <input type="text" size="15" id="sFeeTpNm" disabled/>
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
                                <input type="text" size="9" id="sAppAgentCode" maxlength="10" readonly/>
                                <input type="text" size="15" id="sAppAgentNm" disabled/>
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
                            <th>신청시설</th>
                            <td>
                                <input type="text" size="9" id="sFacCode" maxlength="5" readonly/>
                                <input type="text" size="9" id="sFacSubCode" maxlength="5" readonly/>
                                <input type="text" size="15" id="sFacKorNm" disabled/>
                                <button id="popupFcltyInfo" class="popupButton">선택</button>
                            </td>
                            <th>보전누계액</th>
                            <td>
                                <input id="sExmpAcc" type="text" size="15" />
                            </td>
                            <th>입출항일자</th>
                            <td>
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
        <div id="socFrghtProcessSetoffLgerListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">투자비보전(화물)상계처리대장</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	
            	<table id="socFrghtProcessSetoffLgerList" style="display:none"></table>
                <table id="socFrghtProcessSetoffLgerDetail" style="display:none" class="fillHeight"></table>
                
                <div id="socFrghtProcessSetoffLgerListSum" class="emdControlPanel">
					<form id="socFrghtProcessSetoffLgerListSumForm">
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