<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  /**
  * @Class Name : GamPopupHtldRentArrrgNticIssue.jsp
  * @Description : 연체료 고지 팝업 (분납일 경우)
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

	/*
	this.$('#chkInCofix').on("click", {module: this}, function(event) {
		var module=event.data.module;
		if($(this).attr("checked")=="checked") {
			module.$("#cofix").show();
			module.$('#blceStdrIntrrate').on("change", {module:this}, function(event) {
				var module=event.data.module;
				module.calcInterest();
			});
		}
		else module.$("#cofix").hide();
	});

	this.$('#intrRate').on("change", {module:this}, function(event) {
		event.data.module.calcInterest();
	});

	*/

	this.calcArrrgPay();

	this.$('#arrrgTariff').on('keyup', {module: this}, function(e) {
		e.data.module.calcArrrgPay();
	});

	console.log('debug');
};

// 사용자 설정 함수 추가
GamPopupNticIssueModule.prototype.calcArrrgPay = function() {
	var nticAmt = Number(this.$("#nticAmt").val());
	var arrrgTariff = Number(this.$('#arrrgTariff').val());

	var arrrgPay = Math.floor(nticAmt*arrrgTariff/1000)*10;
	this.$("#arrrgAmt").val(arrrgPay);
	this.$('#arrrgAmtSum').val(nticAmt+arrrgPay);
};

GamPopupNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnNoticeIssue':
		if( this.$('#nticAmt').val() == '' ) {
            alert("고지 할 요금종류를 선택하십시오.");
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
		    <input type="hidden" id="prtAtCode" value="<c:out value="${arrrglevReqest.prtAtCode }"/>"/>
		    <input type="hidden" id="mngYear" value="<c:out value="${arrrglevReqest.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${arrrglevReqest.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${arrrglevReqest.mngCnt }"/>"/>
		    <input type="hidden" id="nticCnt" value="<c:out value="${arrrglevReqest.nticCnt }"/>"/>
		    <input type="hidden" id="nticAmt" value="<c:out value="${arrrglevReqest.nticAmt }"/>"/>

			<table class="detailPanel">
				<tbody>
					<tr>
                        <th style="width:100px; text-align: center;">고지 대상 업체</th>
                        <td colSpan="3">
                            <c:out value="${arrrglevReqest.entrpsNm }"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지횟수</th>
                        <td colspan="3">
                            <c:out value="${arrrglevReqest.nticCnt }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">사용기간</th>
                        <td colspan="3">
                            <c:out value="${arrrglevReqest.nticPdFrom }" />
                            ~
                            <c:out value="${arrrglevReqest.nticPdTo }" />
                        </td>
                    </tr>
                    <%--
                    <tr>
                        <th style="width:100px; text-align: center;">총사용면적</th>
                        <td style="width:100px; text-align:right;" colspan="3">
                            <fmt:formatNumber type="number" value="${arrrglevReqest.grAr }" /> m<sup>2</sup>
                        </td>
                    </tr>
                    --%>
					<tr>
                        <th style="width:100px; text-align: center;">사용료</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${arrrglevReqest.fee }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">이자</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${arrrglevReqest.intrAmnt }" /> 원
                        </td>
                    </tr>
<%--                     <tr>
                        <th style="width:100px; text-align: center;">분납 이자율</th>
                        <td style="width:100px; text-align:right;">
                        <fmt:formatNumber type="number" value="${arrrglevReqest.intrRate*10 }" /> %
                        </td>
                    </tr> --%>
                    <tr>
                        <th style="width:100px; text-align: center;">부가세</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${arrrglevReqest.vat }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">고지 금액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${arrrglevReqest.nticAmt }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">연체납부기한</th>
                        <td colspan="3">
                        <input id="newPayTmlmt"  value="<c:out value="${arrrglevReqest.payTmlmt }" />" class="emdcal" data-required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">연체요율</th>
                        <td style="text-align:center;">
                            <input id="arrrgTariff" class="ygpaNumber" data-decimal-point="1" size="4" data-required="true" value="3.0"/> %
                        </td>
                        <th style="width:100px; text-align: center;">연체료</th>
                        <td style="width:100px; text-align:right;">
                            <input id="arrrgAmt" class="ygpaNumber" data-required="true" size="10" value="${arrrglevReqest.arrrgAmt }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">연체고지금액</th>
                        <td colspan="3" style="text-align:center;">
                            <input id="arrrgAmtSum" class="ygpaNumber" data-required="true" size="10" value="${arrrglevReqest.nticAmt+arrrglevReqest.arrrgAmt }"/> 원
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