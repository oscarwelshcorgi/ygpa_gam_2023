<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  /**
  * @Class Name : GamPopupPrtFcltyRentFeePay.jsp
  * @Description : 사용료 수납확인 팝업
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

GamPopupNticIssueModule.prototype = new EmdPopupModule(500, 300);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupNticIssueModule.prototype.loadComplete = function() {
	this.resizable(true);

	console.log('debug');
};

// 사용자 설정 함수 추가

GamPopupNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnRcivFeePay':
		/*
		if( this.$('#nticAmt').val() == '' ) {
            alert("고지 할 요금종류를 선택하십시오.");
            return;
        }
		*/

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
		    <input type="hidden" id="prtAtCode" value="<c:out value="${feePayMaster.prtAtCode }"/>"/>
		    <input type="hidden" id="mngYear" value="<c:out value="${feePayMaster.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${feePayMaster.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${feePayMaster.mngCnt }"/>"/>
		    <input type="hidden" id="nticCnt" value="<c:out value="${feePayMaster.nticCnt }"/>"/>
		    <input type="hidden" id="chrgeKnd" value="<c:out value="${feePayMaster.chrgeKnd }"/>"/>

			<table class="detailPanel">
				<tbody>
					<tr>
                        <th style="width:100px; text-align: center;">고지 대상 업체</th>
                        <td colSpan="3">
                            <c:out value="${feePayMaster.entrpsNm }"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지횟수</th>
                        <td colspan="3">
                            <c:out value="${feePayMaster.nticCnt }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">사용기간</th>
                        <td colspan="3">
                            <c:out value="${feePayMaster.nticPdFrom }" />
                            ~
                            <c:out value="${feePayMaster.nticPdTo }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">총사용면적</th>
                        <td style="width:100px; text-align:right;" colspan="3">
                            <fmt:formatNumber type="number" value="${feePayMaster.grAr }" /> m<sup>2</sup>
                        </td>
                    </tr>
					<tr>
                        <th style="width:100px; text-align: center;">사용료</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${feePayMaster.fee }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">이자</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${feePayMaster.intrAmnt }" /> 원
                        </td>
                    </tr>
<%--                     <tr>
                        <th style="width:100px; text-align: center;">분납 이자율</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${feePayMaster.intrRate*10 }" /> %
                        </td>
                    </tr> --%>
                    <tr>
                        <th style="width:100px; text-align: center;">부가세</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${feePayMaster.vat }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">고지 금액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${feePayMaster.nticAmt }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">수납상태</th>
                        <td colspan="3" style="text-align:center;">
                            <input id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025" data-required="true" data-default-prompt="필수 선택" value="<c:out value="${feePayMaster.rcivSe }" />" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">비고</th>
                        <td colspan="3">
                        <input id="rm"  value="<c:out value="${feePayMaster.rm }" />" size="40"/>
                        </td>
                    </tr>
				</tbody>
		    </table>
		    <div class="emdControlPanel">
		    	<button id="btnRcivFeePay" class="submit">수납처리</button>
		    	<button id="btnCancel" class="cancel">취소</button>
		    </div>
		</form>
	</div>
</div>