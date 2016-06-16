<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldRcivProc.jsp
  * @Description : 수납처리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2016.6.16  Jongmin          최초 생성
  *
  * author Jongmin
  * since 2016.06.16
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
--%>
<script>
function GamPopupHtldRcivProcModule() {}

GamPopupHtldRcivProcModule.prototype = new EmdPopupModule(500, 250);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupHtldRcivProcModule.prototype.loadComplete = function() {
	this.resizable(true);	

	this.$('#rcivSe').on('change', {module:this}, function(e) {
		var module=e.data.module;
		var value = $(e.target).val();
		if(value=='2' || value=='3') module.$('#rcivDt').val(EMD.util.getDate());
		else module.$('#rcivDt').val('');
	});
};

GamPopupHtldRcivProcModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnOk':
		this.updateHtldRcivInfo();
		break;
	case 'btnCancel':
		this.cancelDialog();
		break;
	}
};

GamPopupHtldRcivProcModule.prototype.updateHtldRcivInfo = function() {
	if(!confirm("수납처리를 하시겠습니까?")) return;
	var params = this.makeFormArgs('#gamRcivInfoForm');
	this.doAction('/oper/htldnew/updateHtldRcivInfo.do', params, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldRcivProcModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamRcivInfoForm">
		    <input type="hidden" id="accnutYear" value="<c:out value="${rcivInfo.accnutYear }"/>"/>
		    <input type="hidden" id="rntfeeNticNo" value="<c:out value="${rcivInfo.rntfeeNticNo }"/>"/>
		    <input type="hidden" id="nticSeq" value="<c:out value="${rcivInfo.nticSeq }"/>"/>
			<table class="detailPanel">
				<tbody>
					<tr>
                        <th style="width:100px; text-align: center;">고지 대상 업체</th>
                        <td colSpan="3">
                            <c:out value="${rcivInfo.entrpsNm }"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지대상기간</th>
                        <td colspan="3">
                            <c:out value="${rcivInfo.nticPdFrom }" />~<c:out value="${rcivInfo.nticPdTo }" />
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">고지일자</th>
                        <td>
                            <c:out value="${rcivInfo.nticDt }" />
                        </td>
                        <th style="width:100px; text-align: center;">납부구분</th>
                        <td>
                            <c:out value="${rcivInfo.paySeNm }" />
                        </td>
                    </tr>
					<tr>
                        <th style="width:100px; text-align: center;">공급가액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${rcivInfo.supAmt }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">부가세</th>
                        <td style="width:100px; text-align:right;">
                        	<fmt:formatNumber type="number" value="${rcivInfo.vat }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">연체료</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${rcivInfo.arrrgAmt }" /> 원
                        </td>
                        <th style="width:100px; text-align: center;">고지금액</th>
                        <td style="width:100px; text-align:right;">
                            <fmt:formatNumber type="number" value="${rcivInfo.payAmt }" /> 원
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">수납상태</th>
                        <td style="text-align:center;">
                            <input id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025" data-required="true" data-default-prompt="필수 선택" value="<c:out value="${rcivInfo.rcivSe }" />" />
                            <!-- <select id="rcivSe">
                            	<option value="3">수납</option>
                            	<option value="2">연체수납</option>	
                            	<option value="4">불납</option>	
                            </select> -->
                        </td>
                        <th style="width:100px; text-align: center;">수납일자</th>
                        <td style="text-align:center;">
                            <input id="rcivDt" class="emdcal" value="<c:out value="${rcivInfo.rcivDt }" />" size="11"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:100px; text-align: center;">비고</th>
                        <td colspan="3">
                        	<input id="rm"  value="<c:out value="${rcivInfo.rm }" />" size="40"/>
                        </td>
                    </tr>
				</tbody>
		    </table>
		    <div class="emdControlPanel">
		    	<button id="btnOk">수납처리</button>
		    	<button id="btnCancel">취소</button>
		    </div>
		</form>
	</div>
</div>