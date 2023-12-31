<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocAgentProcessDtlsSttus.jsp
 * @Description : 투자비보전처리내역현황
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.14    김종민        최초 생성
 *
 * author 김종민
 * since 2014.10.14
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamSocAgentProcessDtlsSttusSearchForm" method="validateGamSocAgentProcessDtlsSttus" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocAgentProcessDtlsSttusModule() {}

GamSocAgentProcessDtlsSttusModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocAgentProcessDtlsSttusModule.prototype.loadComplete = function() {

    // 업체신청 면제요청목록 설정
    this.$("#socAgentProcessDtlsSttusList").flexigrid({
        module: this,
        url: '/soc/gamSelectSocAgentProcessDtlsSttusList.do',
        dataType: 'json',
        colModel : [
                    {display:'등록항구', 	name:'appPrtAtCode',	width:70, sortable:false,align:'center'},
                    {display:'등록항구명',	name:'appPrtAtKorNm',	width:100, sortable:false,align:'center'},
                    {display:'요금종류', 	name:'feeTp',			width:70, sortable:false,align:'left'},
                    {display:'요금종류명', 	name:'feeTpNm',			width:120, sortable:false,align:'left'},
                    {display:'횟수', 		name:'useNo',			width:40, sortable:false,align:'left'},
                    {display:'관리번호', 	name:'socNo',			width:70, sortable:false,align:'left'},
                    {display:'신고업체', 	name:'exmpAgentCode',	width:70, sortable:false,align:'center'},
                    {display:'신고업체명', 	name:'exmpAgentNm',		width:150, sortable:false,align:'left'},
                    {display:'요율금액', 	name:'standardFee',		width:80, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'상계금액', 	name:'exmpAmnt',		width:80, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'호출부호', 	name:'callLetter',		width:70, sortable:false,align:'center'},
                    {display:'보전누계액', 	name:'exmpAcc',			width:130, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'입항횟수', 	name:'serNo',			width:80, sortable:false,align:'center'},
                    {display:'외내항', 	name:'inOutNm',			width:50, sortable:false,align:'center'},
                    {display:'시설코드', 	name:'facCode',			width:70, sortable:false,align:'center'},
                    {display:'시설부코드', 	name:'facSubCode',		width:100, sortable:false,align:'center'},
                    {display:'시설명', 	name:'facilNm',			width:150, sortable:false,align:'left'},
                    {display:'운임톤', 	name:'realTn',			width:80, sortable:false,align:'center'},
                    {display:'고지일자', 	name:'billDt',			width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            return data;
        }
    });
    this.$("#socAgentProcessDtlsSttusList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.$('#rateGubun').val(row['rateGubun']);
    	module.$('#exmpAmnt').val($.number(row['exmpAmnt']));
    	module.$('#exmpAcc').val($.number(row['exmpAcc']));
    	module.$('#exmpRemain').val($.number(row['exmpAmnt'] - row['exmpAcc']));
    });
    
    this.$('#sAppPrtAtCode').val('622');
    this.$('#sPrtAtCode').val('622');
};

<%
/**
 * @FUNCTION NAME : validateDuration
 * @DESCRIPTION   : 유효성 있는 기간 체크
 * @PARAMETER     : 
	 1. startDate   : 시작일 문자열, 
	 2. endDate     : 종료일 문자열, 
	 3. startTitle  : 시작일 제목, 
	 4. endTitle    : 종료일 제목, 
	 5. startIgnore : 
		 5-1. true  : 시작일 필수입력사항 미체크,
		 5-2. false : 시작일 필수입력사항 체크 
	 6. endIgnore : 
		 6-1. true  : 종료일 필수입력사항 미체크,
		 6-2. false : 종료일 필수입력사항 체크 
	 7. equals      :
		 7-1. true  : 종료일이 시작일 보다 크거나 같으면 허용
		 7-2. false : 종료일이 시작일 보다 커야 허용
**/
%>
GamSocAgentProcessDtlsSttusModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
	var result = false;
	if(((startDate == null) || (startDate == '')) && ((endDate == null) || (endDate == ''))) {
		return true;
	}
	if((endDate == null) || (endDate == '')) {
		if(!endIgnore) {
			alert(endTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
		result = true;
		if((startDate != null) && (startDate != '')) {
			result = EMD.util.isDate(startDate);
			if(!result) {
				alert(startTitle + '은(는) 날짜형식이 아닙니다.');
			}
		}
		return result;
	}
	if((startDate == null) || (startDate == '')) {
		if(startIgnore) {
			result = EMD.util.isDate(endDate);
			if(!result) {
				alert(endTitle + '은(는) 날짜형식이 아닙니다.');
			}
			return result;
		} else {
			alert(startTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
	}
	if(!EMD.util.isDate(startDate)) {
		alert(startTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	if(!EMD.util.isDate(endDate)) {
		alert(endTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	startDate = EMD.util.strToDate(startDate);
	endDate = EMD.util.strToDate(endDate);
	var compareResult = (startDate.getTime() > endDate.getTime()) ? -1 : 
							(startDate.getTime() == endDate.getTime()) ? 0 : 1;	
	result = (equals) ? (compareResult >= 0) : (compareResult > 0);
	if(!result) {
		alert(endTitle +'은(는) ' + startTitle + ((equals) ? '보다 같거나 커야합니다.' : '보다 커야합니다.'));
	}
	return result;
};

GamSocAgentProcessDtlsSttusModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocAgentProcessDtlsSttusModule.prototype.loadData = function() {
	if(!validateGamSocAgentProcessDtlsSttus(this.$('#gamSocAgentProcessDtlsSttusSearchForm')[0])){ 		
		return;
	}
	if(!this.validateDuration(this.$('#sBillDtFr').val(), this.$('#sBillDtTo').val(),  
								'고지일자 시작일', '고지일자 종료일', true, true, true)) {
		return;
	}
	
	var opts = this.makeFormArgs('#gamSocAgentProcessDtlsSttusSearchForm');
	this.$("#socAgentProcessDtlsSttusList").flexOptions({params:opts}).flexReload();
};

GamSocAgentProcessDtlsSttusModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

/**
 * 정의 된 버튼 클릭 시
 */
GamSocAgentProcessDtlsSttusModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'btnSearch':
        	this.loadData();
            break;
        case 'popupApplyInfo' : //투자비보전신청업체 선택
			this.doExecuteDialog('selectApplyInfo', '투자비보전 신청업체 선택', '/popup/showSocApplyInfo.do', opts);
        	break;
        case 'popupFeeTpInfo' : //요금종류 선택
        	var opts = { prtAtCode : this.$('#sAppPrtAtCode').val() };
			this.doExecuteDialog('selectFeeTpInfo', '요금 선택', '/popup/showSocPayCd.do', opts);        	
        	break;
        case 'popupEntrpsInfo' : //업체코드버튼
			this.doExecuteDialog('selectEntrpsInfo', '업체 선택', '/popup/showSocEntrpsInfo.do', {});
        	break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocAgentProcessDtlsSttusModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectFeeTpInfo' : //요금 조회
	   	 this.$("#sFeeTp").val(value["feeTp"]);
	   	 this.$("#sFeeTpKorNm").val(value["feeTpKorNm"]);
	   	 break;
     case 'selectApplyInfo' : //투자비 보전 신청업체 조회
    	 this.$("#sCmplYr").val(value["cmplYr"]);
    	 this.$("#sConstNo").val(value["constNo"]);
    	 this.$("#sAppPrtAtCode").val(value["appPrtAtCode"]);
    	 this.$("#sPrtAtCode").val(value["prtAtCode"]);
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
var module_instance = new GamSocAgentProcessDtlsSttusModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocAgentProcessDtlsSttusSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>등록항구</th>
                            <td>
                                <select id="sAppPrtAtCode">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm }</option>
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
                            <th>허가원부</th>
                            <td>
                                <input id="sCmplYr" type="text" size="4">
                            	<input id="sConstNo" type="text" size="6">
                            	<button id="popupApplyInfo" class="popupButton">투자보전 신청업체 찾기</button>
                            </td>
                            <td  rowSpan="2">
								<button id="btnSearch" class="buttonSearch">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>신청업체</th>
                            <td>
                                <input id="sAppAgentCode" type="text" size="4">
                            	<input id="sAppAgentName" type="text" size="3" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>요금종류</th>
                            <td>
                                <input id="sFeeTp" type="text" size="3">
                            	<input id="sFeeTpKorNm" type="text" size="3" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupFeeTpInfo" class="popupButton">선택</button>
                            </td>
                            <th>고지일자</th>
                            <td>
                            	<input id="sBillDtFr" type="text" class="emdcal" size="8"> ~ 
                            	<input id="sBillDtTo" type="text" class="emdcal" size="8">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="socAgentProcessDtlsSttusListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">투자비보전처리내역현황</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" >
            	<div class="emdControlPanel">
                </div>
                <table id="socAgentProcessDtlsSttusList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
					    <table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="10%" height="25">적용요율</th>
								<td><input type="text" size="8" id="rateGubun" disabled="disabled" /></td>
								<th width="10%" height="25">보전신청액</th>
								<td><input type="text" size="20" id="exmpAmnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">보전누계액</th>
								<td><input type="text" size="20" id="exmpAcc" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="25">보전차액</th>
								<td><input type="text" size="20" id="exmpRemain" class="ygpaNumber" disabled="disabled" /></td>
								<td>
    	                        	<button data-role="printPage" data-search-option="gamSocAgentProcessDtlsSttusSearchForm" data-url="<c:url value='/soc/gamSelectSocAgentProcessDtlsSttusListPrint.do'/>">인쇄</button>
        	                    </td>
							</tr>
						</table>
					</form>
                </div>
            </div>
        </div>
    </div>
</div>