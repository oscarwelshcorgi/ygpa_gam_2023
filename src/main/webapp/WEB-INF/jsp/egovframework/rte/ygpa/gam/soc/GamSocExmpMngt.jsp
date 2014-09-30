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
	var opts;
    switch(buttonId) {
        // 조회
        case 'searchBtn':
        	if(this.$('#sAppPrtAtCode').val() == '') {
        		alert('항코드를 선택하세요.');
        		break;
        	}
        	if(this.$('#sFeeTp').val() == '') {
        		alert('요금종류코드를 입력하세요.');
        		break;
        	}
        	if(this.$('#sFiscalYr').val() == '') {
        		alert('회계년도를 입력하세요.');
        		break;
        	}
        	if(this.$('#sSocNo').val() == '') {
        		alert('관리번호를 입력하세요.');
        		break;
        	}
        	opts = this.makeFormArgs('#gamSocExmpMngtSearchForm');
        	this.doAction('<c:url value="/soc/gamSelectSocExmpMngtDetailInquire.do" />', opts, function(module, result) {
        		if(result.resultCode == 0) {
        			alert(result.resultVO.feeTp);
        			module.$('#prtAtCode').val(result.resultVO.prtAtCode);
        			//module.$('#prtKorNm').val(result.resultVO.prtKorNm);
        			module.$('#cmplYr').val(result.resultVO.cmplYr);
        			module.$('#constNo').val(result.resultVO.constNo);
        			module.$('#appPrtAtCode').val(result.resultVO.appPrtAtCode);
        			//module.$('#appPrtAtKorNm').val(result.resultVO.appPrtAtKorNm);
        			module.$('#useNo').val(result.resultVO.useNo);
        			module.$('#appAgentCode').val(result.resultVO.appAgentCode);
        			module.$('#appAgentName').val(result.resultVO.appAgentName);
        			module.$('#exmpPrtAtCode').val(result.resultVO.exmpPrtAtCode);
        			//module.$('#exmpPrtAtKorNm').val(result.resultVO.exmpPrtAtKorNm);
        			module.$('#exmpAgentCode').val(result.resultVO.exmpAgentCode);
        			module.$('#exmpAgentName').val(result.resultVO.exmpAgentName);
        			module.$('#exmpType').val(result.resultVO.exmpType);
        			module.$('#inOut').val(result.resultVO.inOut);
        			module.$('#inOutName').val(result.resultVO.inOutName);
        			module.$('#callLetter').val(result.resultVO.callLetter);
        			module.$('#callLetterNm').val(result.resultVO.callLetterNm);
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
        case 'popupChrgeKndCd' : //요금코드조회
        	opts = { prtAtCode : this.$('#sAppPrtAtCode').val() };
			this.doExecuteDialog('selectChrgeKndCd', '요금 선택',
					'<c:url value="/popup/showSocPayCd.do"/>', opts);
        	break;
        case 'popupApplyInfo' : //신청업체(투자비보전신청업체) 조회
			this.doExecuteDialog('selectApplyInfo', '투자비보전 신청업체 선택',
					'<c:url value="/popup/showSocApplyInfo.do"/>', opts);
        	break;
        case 'popupAgentInfo' : //면제업체 조회
			this.doExecuteDialog('selectAgentInfo', '면제업체 선택',
					'<c:url value="/popup/showSocAgentFInfo.do"/>', opts);
        	break;
        case 'popupFacCd' : //시설코드조회
        	if(this.$('#exmpPrtAtCode').val() == '') {
        		alert('조회 후 선택하실 수 있습니다.');
        		break;
        	}
        	opts = { prtAtCode : this.$('#exmpPrtAtCode').val() };
        	this.doExecuteDialog('selectFacilCd', '시설 선택',
					'<c:url value="/popup/showSocFacCd.do"/>', opts);
        	break;
        case 'popupVsslCd' : //선박호출부호조회
			this.doExecuteDialog('selectVsslCd', '선박 선택',
					'<c:url value="/popup/showSocVsslCd.do"/>', opts);
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
     case 'selectChrgeKndCd' : //요금조회
    	 this.$("#sAppPrtAtCode").val(value["prtAtCode"]);
    	 this.$("#sFeeTp").val(value["feeTp"]);
    	 this.$("#sFeeTpKorNm").val(value["feeTpKorNm"]);
    	 break;
     case 'selectApplyInfo' : //신청업체(투자비 보전 신청업체) 조회
    	 this.$("#appAgentCode").val(value["appAgentCode"]);
    	 this.$("#appAgentName").val(value["appAgentName"]);
    	 break;
     case 'selectAgentInfo' : //면제업체 조회
    	 this.$("#exmpAgentCode").val(value["agentCode"]);
    	 this.$("#exmpAgentName").val(value["agentName"]);
    	 break;
     case 'selectFacilCd' : //시설조회
    	 this.$("#facCode").val(value["facCode"]);
    	 this.$("#facSubCode").val(value["facSubCode"]);
    	 this.$("#facKorNm").val(value["facKorNm"]);
    	 break;
     case 'selectVsslCd' : //선박 호출부호 조회
    	 this.$("#callLetter").val(value["vsslNo"]);
    	 this.$("#callLetterNm").val(value["vsslKorNm"]);     
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
                            <th>항코드</th>
                            <td>
                                <!-- <input id="sAppPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />-->
                                <!-- <input id="sAppPrtAtCode" type="text" size="3" /> -->
                                <select id="sAppPrtAtCode">
	                            	<option value="" selected="selected">선택</option>
	                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                </c:forEach>
	                            </select>
                            </td>
                            <th>요금종류코드</th>
                            <td>
                                <!-- <input id="sFeeTp" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019"/>-->
                                <input id="sFeeTp" type="text" size="3" />
                                <input type="text" size="18" id="sFeeTpKorNm" disabled/>
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
						<tr>                            
                            <th>회계년도</th>
                            <td width="100px">
                                <select id="sFiscalYr">
                                    <option value="" selected="selected">년</option>
                                    <c:forEach  items="${yearsList}" var="yearsItem">
                                        <option value="${yearsItem }">${yearsItem }</option>
                                    </c:forEach>
                                </select>
                            </td>                            
                            <th>관리번호</th>
                            <td>
                                <input id="sSocNo" type="text" size="6">&nbsp; &nbsp;
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
                                    <!--<input id="prtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="prtAtKorNm" disabled/>-->
                                	<select id="prtAtCode">
	                                    <option value="" selected="selected"></option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>                                    
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
                                    <!--<input id="appPrtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="appPrtAtKorNm" disabled/>-->
                                	<select id="appPrtAtCode">
	                                    <option value="" selected="selected"></option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode}">${prtAtCdItem.prtKorNm}</option>
	                                    </c:forEach>
	                                </select>                                    
                                </td>
								<th width="10%" height="18">신청횟수</th>
                                <td><input type="text" size="2" id="useNo" readonly/></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="8" id="appAgentCode" maxlength="10" readonly/>
                                    <input type="text" size="18" id="appAgentName" disabled/>
                                    <button id="popupApplyInfo" class="popupButton">선택</button>
                                </td>
                            </tr>
                        	<tr>
                        		<td colspan="6" height="18">
                        		</td>
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
                                    <!--<input id="exmpPrtAtCode" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM019" />
                                    <input type="text" size="4" id="exmpPrtAtKorNm" disabled/>-->
                                	<select id="exmpPrtAtCode">
	                                    <option value="" selected="selected"></option>
	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
	                                    </c:forEach>
	                                </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제업체</th>
                                <td colspan="3">
                                    <input type="text" size="8" id="exmpAgentCode" maxlength="10" readonly/>
                                    <input type="text" size="18" id="exmpAgentName" disabled/>
                                    <button id="popupAgentInfo" class="popupButton">선택</button>
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
                                    <button id="popupVsslCd" class="popupButton">선택</button>
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
                                    <button id="popupFacCd" class="popupButton">선택</button>
                                </td>
								<th width="10%" height="18">고지일자</th>
                                <td>
                                	<input id="billDt" type="text" class="emdcal" size="12" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">입출항일자</th>
                                <td colspan="3">
                                    <input id="ioDt" type="text" class="emdcal" size="12" />
                                </td>
								<th width="10%" height="18">면제금액</th>
                                <td>
                                	<input type="text" size="12" id="exmpAmnt" class="ygpaNumber" /> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">기본료</th>
                                <td colspan="3">
                                    <input type="text" size="12" id="standardFee" class="ygpaNumber" /> 원
                                </td>
								<th width="10%" height="18">고지번호</th>
                                <td>
                                	<input type="text" size="6" id="billNo" class="skipValue" />
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
                                    	<option value="" selected="selected"></option>
                                    	<option value="PAT">항만공사</option>
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
	                        	<button id="btnUpdate" class="buttonSave">면제저장</button>
	                        	<button id="btnCancel" class="buttonCancel">작업취소</button>
	                            <button id="btnDelete" class="buttonDelete">면제삭제</button>
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