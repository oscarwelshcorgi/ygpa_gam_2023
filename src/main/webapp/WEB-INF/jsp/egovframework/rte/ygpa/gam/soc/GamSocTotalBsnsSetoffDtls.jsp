<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocTotalBsnsSetoffDtls.jsp
 * @Description : 총사업비상계처리내역
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.13  HNJ          최초 생성
 *
 * author HNJ
 * since 2014.10.13
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocTotalBsnsSetoffDtlsSearchForm" method="validateGamSocTotalBsnsSetoffDtls" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocTotalBsnsSetoffDtlsModule() {}

GamSocTotalBsnsSetoffDtlsModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocTotalBsnsSetoffDtlsModule.prototype.loadComplete = function() {

    // 자산임대 테이블 설정
    this.$("#socTotalBsnsSetoffDtlsList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocTotalBsnsSetoffDtlsList.do',
        dataType: 'json',
        colModel : [
					{display:'공사항구코드', 			name:'socPrtAtCode',	width:100, 		sortable:false,		align:'center'},
                    {display:'공사항구명', 			name:'socPrtKorNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'등록항구코드', 			name:'prtAtCode',		width:100, 		sortable:false,		align:'center'},
                    {display:'등록항구명', 			name:'prtKorNm',		width:120, 		sortable:false,		align:'center'},
                    {display:'공사명', 				name:'socCnstNm',		width:320, 		sortable:false,		align:'left'},
                    {display:'시공업체', 				name:'agentCode',		width:130, 		sortable:false,		align:'center'},
                    {display:'시공업체명', 			name:'agentName',		width:130, 		sortable:false,		align:'center'}
                    ],
        showTableToggleBtn: false,
        height: '150',
        preProcess: function(module,data) {

            return data;
        }
    });
    
    
    this.$("#socTotalBsnsSetoffDtlsList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		
		var detailInput = [
		   				{name: 'prtAtCode', value: row["prtAtCode"]},
		   				{name: 'cmplYr', value: row["cmplYr"]},
		   				{name: 'constNo', value: row["constNo"]},
		   				{name: 'sPrtAtCode', value: module.$('#sPrtAtCode').val()},
		   				{name: 'sFeeTp', value: module.$('#sFeeTp').val()},
		   				{name: 'sExmpAgentCode', value: module.$('#sExmpAgentCode').val()},
		   				{name: 'sUseNo', value: module.$('#sUseNo').val()},
		   				{name: 'sType', value: module.$('#sType').val()},
		   				{name: 'sFrDt', value: module.$('#sFrDt').val()},
		   				{name: 'sToDt', value: module.$('#sToDt').val()}
		                   ]; 
		//console.log('debug');
		module.$('#socTotalBsnsSetoffDtlsDetail').flexOptions({params:detailInput}).flexReload();
	});
    
    
	// 자산임대 테이블 설정
    this.$("#socTotalBsnsSetoffDtlsDetail").flexigrid({
        module: this,
        url: '/soc/gamSelectSocTotalBsnsSetoffDtlsDetail.do',
        dataType: 'json',
        colModel : [
					{display:'선명', 					name:'vsslKey',			width:120, 		sortable:false,		align:'center'},
                    {display:'호출부호', 				name:'callLetter',		width:100, 		sortable:false,		align:'center'},
                    {display:'입항횟수', 				name:'serNo',			width:100, 		sortable:false,		align:'center'},
                    {display:'관리번호', 				name:'socNo',			width:130, 		sortable:false,		align:'center'},
                    {display:'상계일자', 				name:'',				width:130, 		sortable:false,		align:'center'},
                    {display:'적용요율', 				name:'dcRate',			width:100, 		sortable:false,		align:'center'},
                    {display:'내외항', 				name:'',				width:80, 		sortable:false,		align:'center'},
                    {display:'신고업체', 				name:'appAgentCode',	width:80, 		sortable:false,		align:'center'},
                    {display:'신고업체명', 			name:'',				width:150, 		sortable:false,		align:'center'},
                    {display:'처리항구', 				name:'prtAtCode',		width:100, 		sortable:false,		align:'center'},
                    {display:'요금종류', 				name:'feeTp',			width:100, 		sortable:false,		align:'center'},
                    {display:'요금종류명', 			name:'',				width:100, 		sortable:false,		align:'center'},
                    {display:'할인율', 				name:'',				width:60, 		sortable:false,		align:'center'},
                    {display:'상계금액(청)', 			name:'',				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'상계금액(공사)', 			name:'',				width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
                    {display:'운임톤', 				name:'realTn',			width:60, 		sortable:false,		align:'center'},
                    {display:'특이사항', 				name:'remark',			width:250, 		sortable:false,		align:'left'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	
        	//자료수, 합산금액 입력
            module.$('#totalCount').val($.number(data["totalCount"]));
            module.$('#sumPrtTotalAmnt').val($.number(data["sumPrtTotalAmnt"]));
            module.$('#sumAppTotalAmnt').val($.number(data["sumAppTotalAmnt"]));

            return data;
        }
    });

};


/**
 * 정의 된 버튼 클릭 시
 */
 GamSocTotalBsnsSetoffDtlsModule.prototype.onButtonClick = function(buttonId) {
	
	var opts = null;
    switch(buttonId) {
        case 'popupChrgeKndCd' : //요금코드조회
			this.doExecuteDialog('selectChrgeKndCd', '요금 선택', '/popup/showSocPayCd.do', opts);
        	break;

        case 'popupAgentInfo' : //면제업체 조회
        	this.doExecuteDialog('selectApplyEntrpsInfo', '보전업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break;

    }
};


GamSocTotalBsnsSetoffDtlsModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocTotalBsnsSetoffDtlsModule.prototype.loadData = function() {
	
	if(!validateGamSocTotalBsnsSetoffDtls(this.$('#gamSocTotalBsnsSetoffDtlsSearchForm')[0])){ 		
		return;
	}
    var searchOpt=this.makeFormArgs('#gamSocTotalBsnsSetoffDtlsSearchForm');
    this.$('#socTotalBsnsSetoffDtlsList').flexOptions({params:searchOpt}).flexReload();
    this.$('#socTotalBsnsSetoffDtlsDetail').flexEmptyData();

};


//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocTotalBsnsSetoffDtlsModule.prototype.onClosePopup = function(popupId, msg, value) {

    switch (popupId) {
     case 'selectChrgeKndCd':
         if (msg != 'cancel') {
        	 this.$('#sPrtAtCode').val(value["prtAtCode"]);
             this.$('#sFeeTp').val(value["feeTp"]);
             this.$('#sFeeTpKorNm').val(value["feeTpKorNm"]);
         } else {
             alert('취소 되었습니다');
         }
         break;
         
     case 'selectApplyEntrpsInfo' : //보전업체 조회
    	 this.$("#sExmpAgentCode").val(value["agentCode"]);
    	 this.$("#sExmpAgentName").val(value["firmKorNm"]);
    	 break;
     
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocTotalBsnsSetoffDtlsModule();

</script>

<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocTotalBsnsSetoffDtlsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
                            </td>
                            <th>요금종류코드</th>
                            <td>
                                <input id="sFeeTp" type="text" size="7" />
                                <input type="text" size="20" id="sFeeTpKorNm" disabled/>
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
                            </td>
                            <th>보전업체</th>
                            <td>
                                <input type="text" size="9" id="sExmpAgentCode" maxlength="10"/>
                                <input type="text" size="25" id="sExmpAgentName" disabled/>
                                <button id="popupAgentInfo" class="popupButton">선택</button>
                            </td>
                            <td rowspan="2"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>                            
                            <th>보전횟수</th>
                            <td width="100px">
                                <input id="sUseNo" type="text" size="14">
                            </td>                            
                            <th>처리기간</th>
                            <td>
                                <input id="sFrDt" type="text" class="emdcal" size="15"> ~ 
                                <input id="sToDt" type="text" class="emdcal" size="15">
                            </td>
                            <th>구분</th> 
                            <td>
                                <select id="sType">
	                            	<option value="BILL_DT" selected="selected">고지일자기준</option>
	                            	<option value="IO_DT">입항일자기준</option>
	                            </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">총사업비상계처리내역</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            
                <table id="socTotalBsnsSetoffDtlsList" style="display:none"></table>
                
                <table id="socTotalBsnsSetoffDtlsDetail" style="display:none" class="fillHeight"></table>
                
                <div id="socTotalBsnsSetoffDtlsListSum" class="emdControlPanel">
					<form id="socTotalBsnsSetoffDtlsListSumForm">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="17%" height="25">자료수</th>
								<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">총상계금액(청)</th>
								<td><input type="text" size="18" id="sumPrtTotalAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="18%" height="25">총상계금액(공사)</th>
								<td><input type="text" size="18" id="sumAppTotalAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<td>
    	                        	<button data-role="printPage" data-search-option="gamSocTotalBsnsSetoffDtlsSearchForm" data-url='/soc/gamSelectSocTotalBsnsSetoffDtlsListPrint.do'>인쇄</button>
        	                    </td>
							</tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>