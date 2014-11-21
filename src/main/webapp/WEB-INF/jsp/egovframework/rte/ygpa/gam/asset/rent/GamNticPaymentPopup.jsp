<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamNticPaymentPopup.jsp
  * @Description :  납부확인처리 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.14  장은성          최초 생성
  *
  * author 장은성
  * since 2014.03.14
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamNticPaymentPopupModule() {}

GamNticPaymentPopupModule.prototype = new EmdPopupModule(534, 130);

// 팝업이 호출 되었을때 호출 되는 함수
GamNticPaymentPopupModule.prototype.loadComplete = function(nticPay) {

	this.resizable(true);

	this._nticPay = nticPay;
	this.makeFormValues('#editPaymentForm', this._nticPay);
	this.makeDivValues('#tableForm', this._nticPay);

	console.log("popup loadcomplete");
};


// 사용자 설정 함수 추가
GamNticPaymentPopupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSavePayment':	// 납부확인
		if(confirm("이 건에 대해서 고지를 취소하고 수납 확인을 하시겠습니까?")) {
            this.$('#option').val('ok');
            var updateOpt=this.makeFormArgs('#editPaymentForm');
	        this.doAction('/asset/rent/updateNticPayment.do', updateOpt, function(module, result) {
	            if(result.resultCode=='0') {
	        		this.closeDialog();
	        		return;
	            }

	        	alert(result.resultMsg);
	        });
		}
		break;
	case 'btnCancelPayment':
		if(confirm("이 건에 대해서 수납 확인을 취소하고 재고지 하시겠습니까?")) {
            this.$('#option').val('cancel');
            var updateOpt=this.makeFormArgs('#editPaymentForm');
	        this.doAction('/asset/rent/updateNticPayment.do', updateOpt, function(module, result) {
	            if(result.resultCode=='0') {
	        		this.closeDialog();
	        		return;
	            }

	        	alert(result.resultMsg);
	        });
		}
		break;
	case "cancel":
		this.cancelDialog();
	}

};

GamNticPaymentPopupModule.prototype.loadData = function() {
/* 	var searchOpt=this.makeFormArgs("#gamNticArrrgListVO");
 	this.$("#assetRentFeeArrrgList").flexOptions({params:searchOpt}).flexReload(); */
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamNticPaymentPopupModule();
</script>
<div class="dialog fillHeight">
	<div class="emdPanel fillHeight">
		<form id="editPaymentForm">
			<input id="prtAtCode" type="hidden"/>
			<input id="mngYear" type="hidden"/>
			<input id="mngNo" type="hidden"/>
			<input id="mngCnt" type="hidden"/>
			<input id="nticCnt" type="hidden"/>
			<input id="accnutYear" type="hidden"/>
			<input id="nticno" type="hidden"/>
			<input id="option" type="hidden"/>
		</form>
		<table id="tableForm" class="editPanel">
			<tr>
				<th style="width: 80px">회계년도</th>
				<td style="width: 100px"><span data-column-id="accnutYear"></span></td>
				<th style="width: 80px">고지번호</th>
				<td style="width: 100px"><span data-column-id="nticno"></span></td>
				<td rowspan="2">		<button id="btnSavePayment" data-icon="ui-icon-info">수납 처리</button><br>
		<button id="btnCancelPayment" data-icon="ui-icon-info">수납 취소</button>
				</td>
			</tr>
			<tr>
				<th>고지금액</th>
				<td colspan="3"><span data-column-id="totalNticAmount" class="ygpaNumber"></span></td>
			</tr>
		</table>
	</div>
</div>