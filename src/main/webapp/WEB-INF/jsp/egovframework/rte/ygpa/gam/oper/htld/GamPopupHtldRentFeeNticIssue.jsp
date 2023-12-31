<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  /**
  * @Class Name : GamPopupHtldRentFeeNticIssue.jsp
  * @Description : 사용료 고지 팝업 (분납일 경우)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.30  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.01.24
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
<%--
	사용료 고지 시 분납일 경우 분납 이자율을 표시하고 선택하여 이자와 함께 고지를 한다.
--%>
function GamPopupNticIssueModule() {}

GamPopupNticIssueModule.prototype = new EmdPopupModule(500, 350);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupNticIssueModule.prototype.loadComplete = function() {
	this.resizable(true);
};

// 사용자 설정 함수 추가

GamPopupNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnNoticeIssue':
		if( this.$('#nticAmt').val() == '' ) {
            alert("고지 할 요금종류를 선택하십시오.");
            return;
        }
		
		if( this.$('#nticDt').val() == '') {
			alert('고지일자를 입력하세요.');
			return;
		}
		if( this.$('#payTmlmt').val() == '') {
			alert('납부기한을 입력하세요.');
			return;
		}
		if(!EMD.util.isDate(this.$('#nticDt').val())) {
			alert('고지일자가 날짜형식이 아닙니다.');
			return;
		}
		if(!EMD.util.isDate(this.$('#payTmlmt').val())) {
			alert('납부기한이 날짜형식이 아닙니다.');
			return;
		}
		var nticDt = EMD.util.strToDate(this.$('#nticDt').val());
		var payTmlmt = EMD.util.strToDate(this.$('#payTmlmt').val());
		
		if(payTmlmt.getTime() <= nticDt.getTime()) {
			alert('납부기한이 고지일자보다 커야 합니다.');
			return;
		}
		
		var inputVO=this.makeFormArgs('#noticeForm', 'object');
		this.closeDialog('ok', inputVO);

		break;
	case 'btnCancel':
		this.cancelDialog();
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupNticIssueModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="noticeForm">
		    <input type="hidden" id="prtAtCode" value="<c:out value="${levReqestMaster.prtAtCode }"/>"/>
		    <input type="hidden" id="mngYear" value="<c:out value="${levReqestMaster.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${levReqestMaster.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${levReqestMaster.mngCnt }"/>"/>
		    <input type="hidden" id="nticCnt" value="<c:out value="${levReqestMaster.nticCnt }"/>"/>
		    <input type="hidden" id="chrgeKnd" value="<c:out value="${levReqestMaster.chrgeKnd }"/>"/>
		    <input type="hidden" id="fee" value="<c:out value="${levReqestMaster.fee }"/>"/>
		    <input type="hidden" id="intrAmnt" value="<c:out value="${levReqestMaster.intrAmnt }"/>"/>
		    <input type="hidden" id="intrRate" value="<c:out value="${levReqestMaster.intrRate }"/>"/>
			<input type="hidden" id="nticAmt" value="<c:out value="${levReqestMaster.nticAmt }"/>"/>
			<input type="hidden" id="vat" value="<c:out value="${levReqestMaster.vat }"/>"/>
			<input type="hidden" id="addAmnt" value="<c:out value="${levReqestMaster.addAmnt }"/>"/>

			<table class="detailPanel">
				<tbody>
					<tr>
                        <th style="width:100px; text-align: center;">고지 대상 업체</th>
                        <td colSpan="3">
                            <c:out value="${levReqestMaster.entrpsNm }"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지횟수</th>
                        <td colspan="3">
                            <c:out value="${levReqestMaster.nticCnt }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">사용기간</th>
                        <td colspan="3">
                            <c:out value="${levReqestMaster.nticPdFrom }" />
                            ~
                            <c:out value="${levReqestMaster.nticPdTo }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">총사용면적</th>
                        <td style="width:100px; text-align:right;" colspan="3">
                            <fmt:formatNumber type="number" value="${levReqestMaster.grAr }" /> m<sup>2</sup>
                        </td>
                    </tr>
					<tr>
                        <th style="width:100px; text-align: center;">사용료</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${levReqestMaster.fee }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">이자</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${levReqestMaster.intrAmnt }" /> 원
                        </td>
                    </tr>
					<tr>
                        <th style="width:100px; text-align: center;">추가금액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${levReqestMaster.addAmnt }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;"></th>
                        <td style="width:100px; text-align: left;">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">추가금액산출근거</th>
                        <td colspan="3" style="text-align:left;">
                        	<input id="addAmntRm"  value="<c:out value="${levReqestMaster.addAmntRm }" />"  size="40" />
                        </td>
                    </tr>
<%--                     <tr>
                        <th style="width:100px; text-align: center;">분납 이자율</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${levReqestMaster.intrRate*10 }" /> %
                        </td>
                    </tr> --%>
                    <tr>
                        <th style="width:100px; text-align: center;">부가세</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${levReqestMaster.vat }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">고지 금액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${levReqestMaster.nticAmt }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지 요금 종류</th>
                        <td colspan="3" style="text-align:center;">
                            <input id="chrgeKnd" class="ygpaCmmnCd" data-code-id="GAM053" data-required="true" data-default-prompt="필수 선택" value="${levReqestMaster.chrgeKnd }"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지 일자</th>
                        <td>
                        <input id="nticDt"  size="12"  value="<c:out value="${levReqestMaster.nticDt }" />" class="emdcal" data-required="true"/>
                        </td>
                        <th style="width:100px; text-align: center;">납부 기한</th>
                        <td>
                        <input id="payTmlmt"  size="12" value="<c:out value="${levReqestMaster.payTmlmt }" />" class="emdcal" data-required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">비고</th>
                        <td colspan="3">
                        	<%-- <c:out value="${levReqestMaster.rm }" /> --%>
                        	<input id="rm"  value="<c:out value="${levReqestMaster.rm }" />" size="40"/>
                        </td>
                    </tr>
				</tbody>
		    </table>
		    <div class="emdControlPanel">
		    	<button id="btnNoticeIssue" class="submit">고지</button>
		    	<button id="btnCancel" class="cancel">취소</button>
		    </div>
		</form>
	</div>
</div>