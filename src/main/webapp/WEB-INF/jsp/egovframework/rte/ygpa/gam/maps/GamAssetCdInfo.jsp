<%@page import="org.springframework.util.Assert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:requestEncoding/>
<fmt:setLocale value="ko"/>

<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<div class='prtFcltyInfo'>
		<h2>시설정보</h2>
		<table class='prtFcltyInfo'><tbody>
			<tr><th>자산코드</th><td><c:out value="${assetCd.gisAssetsCd }" /> - <c:out value="${assetCd.gisAssetsSubCd }" /></td></tr>
			<tr><th>시설명</th><td><c:out value="${assetCd.gisAssetsNm }" /></td></tr>
			<tr><th>소재지</th><td><c:out value="${assetCd.gisAssetsLocplc }" /> <c:out value="${assetCd.gisAssetsLnm }" /><c:if test="${assetCd.gisAssetsLnmSub!=null }">-<c:out value="${assetCd.gisAssetsLnmSub }" /></c:if></td></tr>
			<tr><th>면적</th><td><fmt:formatNumber value="${assetCd.gisAssetsAr }" maxIntegerDigits="3" maxFractionDigits="2" /> m²</td></tr>
			<c:if test="${fn:length(assetCd.filenmPhysicl)>0 }">
				<tr><td colspan="2"><img id="imgPreview" src="<c:url value='cmm/getImage.do?physicalFileNm=${assetCd.filenmPhysicl }' />" style='width:300px;' /></td></tr>
			</c:if>
		</tbody></table>
		<c:if test="${fn:containsIgnoreCase(auth,'ROLEADMIN') }">
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
		</c:if>
		<c:if test="${fn:containsIgnoreCase(auth,'ROLE_ADMIN')||fn:containsIgnoreCase(auth,'ROLE_ASSET_MNGT') }">
			<button data-role="assetMngt" data-assets-prt-at-code="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" data-assets-cd="<c:out value='${assetCd.gisAssetsCd }' />" data-assets-sub-cd="<c:out value='${assetCd.gisAssetsSubCd }' />">자산코드 관리</button>
		</c:if>
		<button data-role="assetInqire" data-assets-prt-at-code="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" data-assets-cd="<c:out value='${assetCd.gisAssetsCd }' />" data-assets-sub-cd="<c:out value='${assetCd.gisAssetsSubCd }' />">자산정보 조회</button>
	</div>
	<script>
	// throw 0;
		// console.log("script running...");
	</script>
</c:if>
