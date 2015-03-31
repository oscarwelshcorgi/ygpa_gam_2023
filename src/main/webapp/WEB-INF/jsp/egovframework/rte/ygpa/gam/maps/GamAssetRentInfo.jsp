<%@page import="org.springframework.util.Assert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:requestEncoding/>
<fmt:setLocale value="ko"/>
<script>
function GamMapPopupModule() {};

GamMapPopupModule.prototype = new EmdPopupInfoModule();

GamMapPopupModule.prototype.loadComplete = function(feature) {
	<c:if test='${assetRent.quayGroupCd eq "P"}'>
	this.rentMngt={
			url: '/oper/gnrl/gamPrtFcltyRentMngt.do',
			title: '자산임대목록관리'
	};
	this.rentInqire={
			url: '/oper/gnrl/gamPrtFcltyRentInqire.do',
			title: '자산임대현황조회'
	};
	</c:if>
	<c:if test='${assetRent.quayGroupCd eq "G"}'>
	this.rentMngt={
			url: '/oper/gnrl/gamPrtFcltyRentMngt.do',
			title: '항만시설사용목록관리'
	};
	this.rentInqire={
			url: '/oper/gnrl/gamPrtFcltyRentInqire.do',
			title: '항만시설사용현황조회'
	};
	</c:if>

	console.log('popup module starting debugging.');
};

GamMapPopupModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetMngt':
        	EMD.util.create_window(this.rentMngt.title, this.rentMngt.url, null, {
        		action: "prtFcltyInqire"
    			,prtAtCode: this.$('#prtAtCode').val()
    			,mngYear: this.$('#mngYear').val()
    			,mngNo: this.$('#mngNo').val()
    			,mngCnt: this.$('#mngCnt').val()
			});
            break;
        case 'assetInqire':
        	EMD.util.create_window(this.rentInqire.title, this.rentInqire.url, null, {
        		action: "prtFcltyInqire"
      			,prtAtCode: this.$('#prtAtCode').val()
      			,mngYear: this.$('#mngYear').val()
      			,mngNo: this.$('#mngNo').val()
      			,mngCnt: this.$('#mngCnt').val()
			});
        	break;
        case 'assignFeature':
        	EMD.util.create_window(this.rentMngt.title, this.rentMngt.url, null, {
        		action: "setFeature"
       			,prtAtCode: this.$('#prtAtCode').val()
       			,mngYear: this.$('#mngYear').val()
       			,mngNo: this.$('#mngNo').val()
       			,mngCnt: this.$('#mngCnt').val()
			});
        	break;
        case 'removeFeature':
        	var f=this.getFeature();
    		this.removeFeatures("assetRent", [f]);
        	break;
    }
};

var popupInfoModule = new GamMapPopupModule();
</script>
<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
<input id="prtAtCode" type="hidden" value="<c:out value='${assetRent.prtAtCode }' />" />
<input id="mngYear" type="hidden" value="<c:out value='${assetRent.mngYear }' />" />
<input id="mngNo" type="hidden" value="<c:out value='${assetRent.mngNo }' />" />
<input id="mngCnt" type="hidden" value="<c:out value='${assetRent.mngCnt }' />" />
	<c:if test="${assetRent==null }">
	<h2>지정된 임대 정보가 없습니다.</h2>
		<c:if test="${ auth eq 'manager' }">
			<button id="assignFeature">임대정보 지정</button>
			<button id="removeFeature">영역 삭제</button>
		</c:if>
	</c:if>
	<c:if test="${assetRent!=null }">
		<div class='assetRentInfo'>
			<h2>시설정보</h2>
			<table class='assetRentInfo'><tbody>
				<tr><th>임대업체</th><td><c:out value="${assetRent.entrpsNm }" /> [<c:out value="${assetRent.entrpscd }" />]</td></tr>
				<tr><th>임대기간</th><td><c:out value="${assetRent.usagePdFrom }" /> ~ <c:out value="${assetRent.usagePdTo }" /></td></tr>
				<tr><th>임대면적</th><td><fmt:formatNumber value="${assetRent.usageAr }" maxIntegerDigits="5" maxFractionDigits="2" /> m<sup>2</sup></td></tr>
				<tr><th>임대료</th><td><fmt:formatNumber value="${assetRent.fee }" maxIntegerDigits="5" maxFractionDigits="2" /> 원</td></tr>
				<c:if test="${fn:length(assetRent.filenmPhysicl)>0 }">
					<tr><td colspan="2"><img id="imgPreview" src="<c:url value='cmm/getImage.do?physicalFileNm=${assetRent.filenmPhysicl }' />" style='width:300px;' /></td></tr>
				</c:if>
			</tbody></table>
<%-- 			<c:if test="${ auth eq 'manager' }">
				<c:if test="${assetRent!=null && fn:length(assetRent)>0 }">
				<h2>사용현황</h2>
				<table class='prtFcltyInfo'>
					<thead>
						<tr>
						<th>업체명</th>
						<th>사용기간</th>
						<th>사용면적</th>
						<th>사용금액</th>
						<th>사용목적</th>
						</tr>
					</thead>
				<tbody>
					<c:forEach var="rentItem" items="${assetRent }" varStatus="status">
						<tr>
							<td><c:out value="${rentItem.entrpsNm }" /> (<c:out value="${rentItem.entrpsCd }" /></td>
							<td><c:out value="${rentItem.usagePdFrom }" />~<c:out value="${rentItem.usagePdTo }" /></td>
							<td><fmt:formatNumber value="${rentItem.usageAr }" maxIntegerDigits="3" maxFractionDigits="2" /> (단위:m²)</td>
							<td><fmt:formatNumber value="${rentItem.fee }" type="number"/> 원</td>
							<td><c:out value="${rentItem.usagePurps }" /></td>
						</tr>
					</c:forEach>
				</tbody></table>
				</c:if>
			</c:if>
			<c:if test="${assetRentSummary!=null }">
			<h2>총사용현황</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>총사용면적</th><td><fmt:formatNumber value="${assetRentSummary.usageAr }" maxIntegerDigits="3" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>미사용면적</th><td><fmt:formatNumber value="${assetRentSummary.unUsageAr }" maxIntegerDigits="3" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총면적</th><td><fmt:formatNumber value="${assetRentSummary.totalAr }" maxIntegerDigits="3" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총사용금액</th><td><fmt:formatNumber value="${assetRentSummary.totalFee }" type="number" /> 원</td></tr>
			</tbody></table>
			</c:if> --%>
			<c:if test="${auth eq 'manager' }">
				<button id="assetMngt" data-icon="ui-icon-newwin">임대정보 관리</button>
				<button data-cmd="removeFeature">임대영역 삭제</button>
			</c:if>
			<button id="assetInqire" data-icon="ui-icon-newwin">임대정보 조회</button>
		</div>
	</c:if>
</c:if>
