<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  /**
  * @Class Name : GamPopupPrtFcltyRentMngtPrmisn.jsp
  * @Description : 추가 사용료 등록 팝업
  * @Modification Information
  *
  *   수 정 일       수 정 자           수 정 내 용
  *  ------------    -------------    --------------------------
  *  2014.07.16    eunsungj          최초 생성
  *
  * author domh
  * since 2014.07.16
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupLevReqestAditModule() {} GamPopupLevReqestAditModule

GamPopupLevReqestAditModule.prototype = new EmdPopupModule(600, 260);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupLevReqestAditModule.prototype.loadComplete = function() {
	this.$('.vatCalc').on('change keyUp', {module:this}, function(event) {
		if(event.data.module.$('#vatYn').val()=='Y') {
			var vat = Math.floor(Number(event.data.module.$('#fee').val().replace(/,/g, ''))/10, 10);
			event.data.module.$('#vat').val($.number(vat));
		}
		else {
			event.data.module.$('#vat').val(0);
		}
	});
	console.log('load complete');
};

// 사용자 설정 함수 추가

GamPopupLevReqestAditModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnLevReqestExec':
		if( this.$('#fee').val()==0) {
			alert("부과금액을 입력 하십시오");
			return;
		}
		if( this.$('#chrgeKnd').val() == '' ) {
            alert("요금종류를 선택하십시오.");
            return;
        }
		if( this.$('#fee').val()<3000) {
			this.$('#fee').val(3000);
			alert("최소금액 3,000원으로 청구됩니다.");
		}

		if( confirm("부과금액을 등록 하시겠습니까?") ) {
			var inputVO=this.makeFormArgs('#gamPopupLevReqestAditForm');

	        this.doAction('/cmmn/fclty/gamTestInsertLevreqestAdit.do', inputVO, function(module, result) {
	            alert(result.resultMsg);

	            module.closeDialog('ok', result.resultCode);
	        });
	    }

		break;
	case 'btnCancel':
		this.cancelDialog();
	}
};

GamPopupLevReqestAditModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
};

GamPopupLevReqestAditModule.prototype.loadData = function() {
	//var searchOpt=this.makeFormArgs('#gamPopupLevReqestAditForm');
 	//this.$('#grdInfoList').flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupLevReqestAditModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupLevReqestAditForm">
		    <input type="hidden" id="prtAtCode" value="<c:out value="${gamPrtFcltyRentMngtInfo.prtAtCode }"/>"/>
		    <input type="hidden" id="mngYear" value="<c:out value="${gamPrtFcltyRentMngtInfo.mngYear }"/>"/>
		    <input type="hidden" id="mngNo" value="<c:out value="${gamPrtFcltyRentMngtInfo.mngNo }"/>"/>
		    <input type="hidden" id="mngCnt" value="<c:out value="${gamPrtFcltyRentMngtInfo.mngCnt }"/>"/>
		    <input type="hidden" id="taxtSe" value="<c:out value="${gamPrtFcltyRentMngtInfo.taxtSe }"/>"/>

			<table class="editForm">
				<tbody>
					<tr>
                        <th>신청업체</th>
                        <td colspan="5">
                        	<c:out value="${gamPrtFcltyRentMngtInfo.entrpsNm}" />
                        </td>
                     </tr>
					<tr>
                        <th>사용기간</th>
                        <td colspan="5">
                        	<c:out value="${gamPrtFcltyRentMngtInfo.grUsagePdFrom}" /> ~
                        	<c:out value="${gamPrtFcltyRentMngtInfo.grUsagePdTo}" />
                        </td>
                     </tr>
					<tr>
                        <th>사용면적</th>
                        <td>
                        	<fmt:formatNumber type="number" maxIntegerDigits="15" value="${gamPrtFcltyRentMngtInfo.grAr}" /> m<sup>2</sup>
                        </td>
                        <th>총사용료</th>
                        <td>
                        	<fmt:formatNumber type="number" maxIntegerDigits="15" value="${gamPrtFcltyRentMngtInfo.grFee}" /> 원
                        </td>
                        <th>면제금액</th>
                        <td>
                        	<fmt:formatNumber type="number" maxIntegerDigits="15" value="${gamPrtFcltyRentMngtInfo.grRdcxptFee}" /> 원
                        </td>
                     </tr>
					<tr>
	                    <th>부과 금액</th>
	                    <td>
	                    	<input class="ygpaNumber vatCalc" id="fee" type="text"  size="10"/> 원
	                    </td>
	                    <th>부가세 여부</th>
	                    <td>
	                        <select id="vatYn" class="vatCalc">
	                            <option value="" selected="selected">선택</option>
	                            <option value="Y">Y</option>
	                            <option value="N">N</option>
	                        </select>
	                    </td>
	                    <th>부가세</th>
	                    <td>
	                    	<input class="ygpaNumber" id="vat" type="text"  size="12"/> 원
	                    </td>
	                 </tr>
					<tr>
                        <th>납부기한</th>
                        <td>
                        	<input class="emdcal" id="payTmlmt"  size="10"/>
                        </td>
                        <th>요금종류</th>
                        <td colspan="3">
                            <select id="chrgeKnd">
                                <option value="" selected="selected">선택</option>
                                <c:forEach  items="${chrgeKndCdList}" var="chrgeKndCdItem">
	                                <option value="${chrgeKndCdItem.feeTp }">${chrgeKndCdItem.feeTpKorNm }</option>
	                            </c:forEach>
                            </select>
                        </td>
                     </tr>
					<tr>
                        <th>비고</th>
                        <td colspan="5">
                        	<input class="emdRemark" id="rm" type="text" size="50"/>
                        </td>
                     </tr>
                 	 <tr>
                        <td colspan="6" style="text-align: right;">
                        	<button id="btnLevReqestExec" class="submit">등록</button>
                        	<button id="btnCancel" class="cancel">취소</button>
                       	</td>
                    </tr>
				</tbody>
		    </table>
		</form>
	</div>
</div>