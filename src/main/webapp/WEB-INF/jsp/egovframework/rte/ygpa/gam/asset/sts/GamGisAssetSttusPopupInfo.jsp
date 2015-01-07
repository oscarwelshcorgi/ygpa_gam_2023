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
	<c:if test="${assetCd==null }">
	<script>
	alert('hello');
	</script>
	<h2>시설유지보수 대비 임대 현황</h2>
	<div class="popupChart" style="width:600px;height:250px;border:1px solid #A4BED4;"></div>
	<div class="legend">단위:원</div>
	<div style="text-align:right; width:100%; margin-top:4px;">
		<button data-role="assetInqire" />유지보수내역 조회</button>
		<button data-role="assetInqire" />임대현황 조회</button>
	</div>
	<!--
	<p>주소 : <c:out value="${addr }"/> <c:out value="${lnm }"/><c:if test="${lnmSub != 0 }">-<c:out value="${lnmSub }"/></c:if></p>
		<c:if test="${fn:containsIgnoreCase(auth,'ROLEADMIN')||fn:containsIgnoreCase(auth,'ROLEASSETMNGT') }">
			<button data-role="modifyFeature" data-bjd-code="<c:out value='${bjdCode }'/>" data-addr="<c:out value='${addr }'/>" data-lnm="<c:out value='${lnm }'/>" data-lnm-sub="<c:out value='${lnmSub }'/>"/>자산코드 지정</button>
			<button data-role="removeFeature" />영역 삭제</button>
		</c:if>
		-->
	</c:if>
	<c:if test="${assetCd!=null }">
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
			<div id="chart" class="chart" style="width:600px;height:250px;border:1px solid #A4BED4;"></div>
			<button data-role="assetInqire" data-assets-prt-at-code="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" data-assets-cd="<c:out value='${assetCd.gisAssetsCd }' />" data-assets-sub-cd="<c:out value='${assetCd.gisAssetsSubCd }' />">자산정보 조회</button>
		</div>
	</c:if>
</c:if>
