<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamSocExmpMngt.jsp
 * @Description : 비관리청 신청
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  김종민          최초 생성
 *
 * author 김종민
 * since 2014.09.22
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<validator:javascript formName="gamAssetRent" method="validateGamAssetRent" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" method="validateGamAssetRentDetail" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" method="validateGamAssetRentFile" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<!--
<validator:javascript formName="gamAssetRent" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentDetail" staticJavascript="false" xhtml="true" cdata="false" />
<validator:javascript formName="gamAssetRentFile" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamSocExmpMngtModule() {}

GamSocExmpMngtModule.prototype = new EmdModule(1000, 645);

// 페이지가 호출 되었을때 호출 되는 함수
GamSocExmpMngtModule.prototype.loadComplete = function() {
};

GamSocExmpMngtModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        // 조회
        case 'searchBtn':
        	var searchOpt = this.makeFormArgs('#gamSocExmpMngtSearchForm');
        	this.doAction('<c:url value="/soc/gamSelectSocExmpMngtDetailInquire.do" />', searchOpt, function(module, result) {
        		if(result.resultCode == 0) {
        			//module.$('#prtAtCode').val(result.resultVO.prtAtCode);
        			module.$('#prtAtKorNm').val(result.resultVO.prtAtKorNm);
        			module.$('#cmplYr').val(result.resultVO.cmplYr);
        			module.$('#constNo').val(result.resultVO.constNo);
        			//module.$('#appPrtAtCode').val(result.resultVO.appPrtAtCode);
        			module.$('#appPrtAtKorNm').val(result.resultVO.appPrtAtKorNm);
        			module.$('#useNo').val(result.resultVO.useNo);
        			module.$('#appAgentCode').val(result.resultVO.appAgentCode);
        			module.$('#appAgentName').val(result.resultVO.appAgentName);
        			//module.$('#exmpPrtAtCode').val(result.resultVO.exmpPrtAtCode);
        			module.$('#exmpPrtAtKorNm').val(result.resultVO.exmpPrtAtKorNm);
        			module.$('#exmpAgentCode').val(result.resultVO.exmpAgentCode);
        			module.$('#exmpAgentName').val(result.resultVO.exmpAgentName);
        			//module.$('#exmpType').val(result.resultVO.exmpType);
        			module.$('#inOut').val(result.resultVO.inOut);
        			module.$('#inOutName').val(result.resultVO.inOutName);
        			module.$('#callLetter').val(result.resultVO.callLetter);
        			module.$('#callLetterName').val(result.resultVO.callLetter);
        			module.$('#yr').val(result.resultVO.yr);
        			module.$('#serNo').val(result.resultVO.serNo);
        			module.$('#facCode').val(result.resultVO.facCode);
        			module.$('#facSubCode').val(result.resultVO.facCode);
        			module.$('#facKorNm').val(result.resultVO.facKorNm);
        			module.$('#billDt').val(result.resultVO.billDt);
        			module.$('#ioDt').val(result.resultVO.ioDt);
        			module.$('#exmpAmnt').val(result.resultVO.exmpAmnt);
        			module.$('#standardFee').val(result.resultVO.standardFee);
        			module.$('#billNo').val(result.resultVO.billNo);
        			module.$('#realTn').val(result.resultVO.realTn);
        			module.$('#jingsuja').val(result.resultVO.jingsuja);
        			module.$('#remark').val(result.resultVO.remark);
        		}
        		else {
        			alert(result.resultMsg);
        		}
        	});
            break;
	}

};

GamSocExmpMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

GamSocExmpMngtModule.prototype.loadData = function() {

};

GamSocExmpMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamSocExmpMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     case 'selectEntrpsInfoPopup':
         break;
     case 'insertEntrpsInfoPopup':
         break;
     case 'insertAssetRentPrmisnPopup':
         break;
     case 'insertLevReqestAdit':
    	 break;
     case 'selectAssetsCdRentPopup':
         break;

     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

         break;
     }
};

GamSocExmpMngtModule.prototype.loadOlnlpList = function(prtFcltyCd) {
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamSocExmpMngtModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main"> 

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamSocExmpMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>청코드</th>
                            <td>
                                <!-- <input id="sAppPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />-->
                                <input id="sAppPrtAtCode" type="text" size="3" />
                            </td>
                            <th>요금종류코드</th>
                            <td width="100px">
                                <!-- <input id="sFeeTp" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>-->
                                <input id="sFeeTp" type="text" size="9" />
                            </td>
                            <th>회계년도</th>
                            <td>
                            	<input id="sFiscalYr" type="text" size="4">&nbsp; &nbsp;
                            </td>
                            <th>관리번호</th>
                            <td>
                                <input id="sSocNo" type="text" size="6">&nbsp; &nbsp;
                            </td>
                            <td><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">비관리청 면제관리</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow:scroll;">
                <div class="emdControlPanel">
                    <form id="gamSocExmpMngtForm">
                        <input type="hidden" id="cmd"/>
                        <table class="editForm">
                        	<tr>
                        		<td colspan="6">조회내역</td>
                        	</tr>
                            <tr>
								<th width="10%" height="18">공사관리청</th>
                                <td>
                                    <input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="prtAtKorNm" disabled/>
                                </td>
								<th width="10%" height="18">공사준공년도</th>
                                <td>
                                    <input type="text" size="4" id="cmplYr"/>
                                </td>
								<th width="10%" height="18">공사일련번호</th>
                                <td>
                                    <input type="text" size="6" id="constNo"/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제요청청</th>
                                <td>
                                    <input id="appPrtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="appPrtAtKorNm" disabled/>
                                </td>
								<th width="10%" height="18">신청횟수</th>
                                <td><input type="text" size="2" id="useNo" readonly/></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="8" id="appAgentCode" maxlength="10" readonly/>
                                    <input type="text" size="18" id="appAgentName" disabled/>
                                    <!-- <button id="popupAgentInfoInput" class="popupButton">선택</button>-->
                                </td>
                            </tr>
                        	<tr>
                        		<td colspan="6" height="18"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="6" height="18"></td>
                        	</tr>
                        	<tr>
                        		<td colspan="6">투자비보전처리 상세 정보</td>
                        	</tr>
                            <tr>
								<th width="10%" height="18">면제처리청</th>
                                <td colspan="5">
                                    <input id="exmpPrtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="exmpPrtAtKorNm" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제업체</th>
                                <td colspan="3">
                                    <input type="text" size="8" id="exmpAgentCode" maxlength="10" readonly/>
                                    <input type="text" size="18" id="exmpAgentName" disabled/>
                                </td>
								<th width="10%" height="18">면제유형</th>
                                <td>
                                	<select id="exmpType">
                                    	<option value="1" selected="selected">당해시설</option>
                                    	<option value="2">타인사용료</option>
                                    	<option value="3">다른시설</option>
                                	</select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">외내항구분</th>
                                <td colspan="5">
                                    <input type="text" size="1" id="inOut" />
                                    <input type="text" size="10" id="inOutName" disabled/>
                                    <span>선박관리 : 1.외항, 2.내항   화물관리 : 1.외입, 2.외출, 3.내입, 4.내출, 5.항내운입, 6.항내운출</span>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">호출부효</th>
                                <td colspan="3">
                                    <input type="text" size="9" id="callLetter" />
                                    <input type="text" size="18" id="callLetterNm" disabled/>
                                </td>
								<th width="10%" height="18">입항횟수</th>
                                <td>
                                    <input type="text" size="4" id="yr" />
                                    <input type="text" size="3" id="serNo" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">시설코드</th>
                                <td colspan="3">
                                    <input type="text" size="3" id="facCode" />
                                    <input type="text" size="2" id="facSubCode" />
                                    <input type="text" size="18" id="facKorNm" disabled/>
                                </td>
								<th width="10%" height="18">고지일자</th>
                                <td>
                                	<input id="billDt" type="text" class="emdcal" size="8" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">입출항일자</th>
                                <td colspan="3">
                                    <input id="ioDt" type="text" class="emdcal" size="8" />
                                </td>
								<th width="10%" height="18">면제금액</th>
                                <td>
                                	<input type="text" size="12" id="exmpAmnt" class="skipValue" /> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">기본료</th>
                                <td colspan="3">
                                    <input type="text" size="6" id="standardFee" class="skipValue" /> 원
                                </td>
								<th width="10%" height="18">고지번호</th>
                                <td>
                                	<input type="text" size="6" id="billNo" class="skipValue" /> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">운임톤</th>
                                <td colspan="3">
                                    <input type="text" size="14" id="realTn" class="skipValue" />
                                </td>
								<th width="10%" height="18">접수자</th>
                                <td>
                                	<select id="jingsuja">
                                    	<option value="PAT" selected="selected">항만공사</option>
                                    	<option value="MAP">해양항만청</option>
                                    	<option value="LGO">지자체</option>
                                	</select>
                                </td>
                            </tr>                            
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5">
                                    <input type="text" size="120" id="remark" class="skipValue" />
                                </td>
                            </tr>                            
                        </table>
                    </form>

	                 <table style="width:100%">
<!--
	                    <tr>
	                        <td style="text-align:right" colspan="3"><button id="btnInsertItemDetail" class="buttonAdd">임대상세추가</button><button id="btnRemoveItemDetail" class="buttonDelete">임대상세삭제</button></td>
	                    </tr>
-->
	                    <tr>
	                        <td><!-- <button id="xxxx">GIS 등록</button><button id="xxxx">위치조회</button> --></td>
	                        <td width="100"></td>
	                        <td style="text-align:right">
	                        <button id="btnEApproval">결재요청</button><button id="btnPrmisn">사용승낙</button>
	                            <button id="btnPrmisnCancel">승낙취소</button><button id="btnRemoveItem" class="buttonDelete">신청삭제</button><button id="btnSaveItem" class="buttonSave">신청저장</button>
	                            <!-- <button id="btnNoticeAdit2">추가고지</button> -->
	                            <!-- <button id="btnCancelItem">취소</button>  -->
	                        </td>
	                    </tr>
	                 </table>
                 </div>
            </div>
        </div>
    </div>
</div>