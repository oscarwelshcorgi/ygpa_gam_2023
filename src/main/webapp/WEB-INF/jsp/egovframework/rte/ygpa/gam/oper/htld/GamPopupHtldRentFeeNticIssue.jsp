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

GamPopupNticIssueModule.prototype = new EmdPopupModule(500, 260);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupNticIssueModule.prototype.loadComplete = function() {
	this.resizable(true);

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

	this.calcInterest();

//	console.log('debug');
};

// 사용자 설정 함수 추가

GamPopupNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnNoticeIssue':
		if( this.$('#intrRate').val() == '' ) {
            alert("분납 이자율을 선택하십시오.");
            return;
        }

		var inputVO=this.makeFormArgs('#noticeForm', 'object');
		this.closeDialog('ok', inputVO);

		break;
	case 'btnCancel':
		this.cancelDialog();
	}
};

GamPopupNticIssueModule.prototype.calcInterest = function() {
	var cofixInput = this.$('#chkInCofix').attr("checked")=="checked"?true:false;
	var rate=null;
	var grFee = this.$('#grFee').val().replace(/,/g,"")*1;
	var fee = this.$('#fee').val().replace(/,/g,"")*1;
	var nticAmt=fee;
	var balance = grFee-fee;
	var nticPdFrom = EMD.util.strToDate(this.$('#nticPdFrom').val());
	var nticPdTo = EMD.util.strToDate(this.$('#nticPdTo').val());
	var ndays = (nticPdTo.getTime()-nticPdFrom.getTime())/(24*60*60*1000)+1;
	var interest=0;
	if(cofixInput) {
		rate=this.$('#blceStdrIntrrate').val()/100;
	}
	else {
		rate=this.$('#intrRate').val()/100;
	}
	if(rate!==0) {
		interest = Math.ceil(balance*rate*(ndays/365)/10)*10;
	}
	nticAmt=fee+interest;
	this.$('#totalfee').val($.number(nticAmt));
	var vat=Math.ceil(nticAmt/100)*10;
	this.$('#vat').val($.number(vat));
	this.$('#nticAmt').val($.number(nticAmt+vat));

	this.$('#intrAmnt').val($.number(interest));
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupNticIssueModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="noticeForm">
		    <input type="hidden" id="prtAtCode" value="<c:out value="${gamHtldRentFeeMngtVO.prtAtCode }"/>"/>
		    <input type="hidden" id="mngYear" value="<c:out value="${gamHtldRentFeeMngtVO.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${gamHtldRentFeeMngtVO.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${gamHtldRentFeeMngtVO.mngCnt }"/>"/>
		    <input type="hidden" id="nticCnt" value="<c:out value="${gamHtldRentFeeMngtVO.nticCnt }"/>"/>
		    <input type="hidden" id="chrgeKnd" value="<c:out value="${gamHtldRentFeeMngtVO.chrgeKnd }"/>"/>
		    <input type="hidden" id="quayGroupCd" value="<c:out value="${gamHtldRentFeeMngtVO.quayGroupCd }"/>"/>
			<input type="hidden" id="nticPdFrom" value="<c:out value="${gamHtldRentFeeMngtVO.nticPdFrom }"/>"/>
			<input type="hidden" id="nticPdTo" value="<c:out value="${gamHtldRentFeeMngtVO.nticPdTo }"/>"/>
			<input type="hidden" id="vatYn" value="<c:out value="${gamHtldRentFeeMngtVO.vatYn }"/>"/>

			<table class="detailPanel">
				<tbody>
					<tr>
                        <th>고지 대상 업체</th>
                        <td colSpan="3">
                            <c:out value="${gamHtldRentFeeMngtVO.entrpsNm }"/>
                        </td>
                    </tr>
                    <tr>
                        <th>총사용기간</th>
                        <td colspan="3">
                            <c:out value="${gamHtldRentFeeMngtVO.grUsagePdFrom }" />
                            ~
                            <c:out value="${gamHtldRentFeeMngtVO.grUsagePdTo }" />
                        </td>
                    </tr>
                    <tr>
                        <th>사용면적</th>
                        <td>
                            <input id="grAr" class="ygpaNumber" size="10" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.grAr }" />"/> m<sup>2</sup>
                        </td>
                        <th>총사용료</th>
                        <td>
                            <input id="grFee" class="ygpaNumber" size="18" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.grFee }" />"/> 원
                        </td>
                    </tr>
					<tr>
                        <th>사용료</th>
                        <td>
                            <input id="fee" class="ygpaNumber" size="18" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.fee }" />"/> 원
                        </td>
                        <th>이자</th>
                        <td>
                            <input id="intrAmnt" class="ygpaNumber" size="18" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.intrAmnt }" />"/> 원
                        </td>
                    </tr>
                    <tr>
                        <th>사용료 합계</th>
                        <td>
                            <input id="totalfee" class="ygpaNumber" size="18" value="" /> 원
                        </td>
                        <th>부가세</th>
                        <td>
                            <input id="vat" class="ygpaNumber" size="18" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.vat }" />"/> 원
                        </td>
                    </tr>
                    <tr>
                        <th>고지 금액</th>
                        <td colspan="3">
                            <input id="nticAmt" class="ygpaNumber" size="18" value="<fmt:formatNumber type="number" value="${gamHtldRentFeeMngtVO.nticAmt }" />"/> 원
                        </td>
                    </tr>
                    <tr>
                        <th>분납 이자율</th>
                        <td colspan="3">
                            <select id="intrRate">
                                <option value="" selected="selected">선택</option>

                                <c:forEach  items="${cofixList}" var="intrrate">
	                                <option value="<c:out value="${intrrate.blceStdrIntrrate }"/>"><c:out value="${fn:substring(intrrate.objYrmt, 0,4) }"/>-<c:out value="${fn:substring(intrrate.objYrmt, 4,6) }"/> : <c:out value="${intrrate.blceStdrIntrrate }"/> %</option>
	                            </c:forEach>
                            </select>
                            <input type="checkbox" id="chkInCofix"/> 이자율 추가
                        </td>
                    </tr>
                    <tr id="cofix" style="display:none;">
                        <th>cofix이자율</th>
                        <td colspan="3">
                        	<table class="detailPanel">
	                        	<tbody>
		                        	<tr>
		                        		<th>
		                        			적용년월
		                        		</th>
		                        		<td>
		                        			<input id="objYrMt" />
		                        		</td>
		                        	</tr>
		                        	<tr>
		                        		<th>
		                        			잔액기준
		                        		</th>
		                        		<td>
		                        			<input id="blceStdrIntrrate" /> %
		                        		</td>
		                        	</tr>
		                        	<tr>
		                        		<th>
		                        			신규취급액기준
		                        		</th>
		                        		<td>
		                        			<input id="newManipAmtStdrIntrrate" /> %
		                        		</td>
		                        	</tr>
		                        	<tr>
		                        		<th>
		                        			고시일자
		                        		</th>
		                        		<td>
		                        			<input id="annodt" class="emdcal" />
		                        		</td>
		                        	</tr>
	                        	</tbody>
                        	</table>
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