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
	console.log('popup module starting debugging.');
};

GamMapPopupModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetMngt':
        	EMD.util.create_window("gamAssetCodeMngt", "자산코드 관리", "/code/assets/gamAssetCodeMngt.do", null, {
        		action: "prtFcltyInqire"
    			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
    			,gisAssetsCd: this.$('#gisAssetsCd').val()
    			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
            break;
        case 'fcltyRentMngt':
        	EMD.util.create_window("gamPrtFcltyRentMngt", "항만시설사용목록관리", "/oper/gnrl/gamPrtFcltyRentMngt.do", null, {
        		action: "prtFcltyInqire"
    			,prtAtCode: this.$('#prtAtCode').val()
    			,mngYear: this.$('#mngYear').val()
    			,mngNo: this.$('#mngNo').val()
    			,mngCnt: this.$('#mngCnt').val()
			});
            break;
        case 'assetInqire':
        	EMD.util.create_window("gamAssetCodeList", "자산코드 조회", "/code/assets/gamAssetCodeList.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
        	break;
        case 'assignFeature':
        	EMD.util.create_window("gamAssetCodeMngt", "자산코드 지정", "/code/assets/gamAssetCodeMngt.do", null, {
        		action: "setFeature"
				,addr: this.$('#addr').val()
    			,lnm: this.$('#lnm').val()
    			,lnmSub: this.$('#lnmSub').val()
    			,feature: this.getFeature()
			});
        	break;
        case 'removeFeature':
        	var f=this.getFeature();
//        	EMD.map.removePopup(f.popup);
  //          f.state = OpenLayers.State.DELETE;
    //        EMD.userLayer.gisAssetsCd.removeFeatures([f]);
    		this.removeFeatures("gisAssetsCd", [f]);
        	break;
    }
};

var popupInfoModule = new GamMapPopupModule();
</script>
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${assetCd.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${assetCd.gisAssetsSubCd }' />" />
<input id="bjdCode" type="hidden" value="<c:out value='${assetCd.bjdCode }' />" />
<input id="addr" type="hidden" value="<c:out value='${addr }' />" />
<input id="lnm" type="hidden" value="<c:out value='${lnm }' />" />
<input id="lnmSub" type="hidden" value="<c:out value='${lnmSub }' />" />

<input id="prtAtCode" type="hidden" value="<c:out value='${prtAtCode }' />" />
<input id="mngYear" type="hidden" value="<c:out value='${mngYear }' />" />
<input id="mngNo" type="hidden" value="<c:out value='${mngNo }' />" />
<input id="mngCnt" type="hidden" value="<c:out value='${mngCnt }' />" />


<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${assetCd==null }">
	<h2>시설정보가 없습니다.</h2>
	<table class='prtFcltyInfo'>
		<tbody>
				<tr><th>주소</th><td><c:out value="${addr }"/> <c:out value="${lnm }"/><c:if test="${lnmSub != 0 }">-<c:out value="${lnmSub }"/></c:if></td></tr>
		</tbody>
	</table>
	
		<c:if test="${fn:containsIgnoreCase(auth,'ROLEADMIN')||fn:containsIgnoreCase(auth,'ROLEASSETMNGT') }">
			<button id="assignFeature">자산코드 지정</button>
			<button id="removeFeature">영역 삭제</button>
		</c:if>
	</c:if>
	<c:if test="${assetCd!=null }">
		<div class='prtFcltyInfo'>
			<h2>시설정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>자산코드</th><td><c:out value="${assetCd.gisAssetsCd }" /> - <c:out value="${assetCd.gisAssetsSubCd }" /></td></tr>
				<tr><th>시설명</th><td><c:out value="${assetCd.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td><c:out value="${assetCd.gisAssetsLocplc }" /> <c:out value="${assetCd.gisAssetsLnm }" /><c:if test="${assetCd.gisAssetsLnmSub!=null }">-<c:out value="${assetCd.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${assetCd.gisAssetsAr }" maxIntegerDigits="8" maxFractionDigits="2" /> m²</td></tr>
				<c:if test="${fn:length(assetCd.filenmPhysicl)>0 }">
					<tr><td colspan="2"><img id="imgPreview" src="<c:url value='cmm/getImage.do?physicalFileNm=${assetCd.filenmPhysicl }' />" style='width:300px;' /></td></tr>
				</c:if>
			</tbody></table>

			<c:if test="${wharfInfo0!=null}">
				<h2>두부정보</h2>
				<table class='prtFcltyInfo'><tbody>
					<tr><th>부두 길이(m)</th><td><c:out value="${wharfInfo0 }" /></td></tr>
					<tr><th>부두 흘수(m)</th><td><c:out value="${wharfInfo1 }" /></td></tr>
					<tr><th>접안 능력</th><td><c:out value="${wharfInfo2 }" /></td></tr>
				</tbody></table>
			</c:if>

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
							<td><c:out value="${rentItem.entrpsNm }" /> (<c:out value="${rentItem.entrpscd }" /></td>
							<td><c:out value="${rentItem.usagePdFrom }" />~<c:out value="${rentItem.usagePdTo }" /></td>
							<td><fmt:formatNumber value="${rentItem.usageAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td>
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
				<tr><th>총사용면적</th><td><fmt:formatNumber value="${assetRentSummary.totUsageAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>미사용면적</th><td><fmt:formatNumber value="${assetRentSummary.unUsageAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총면적</th><td><fmt:formatNumber value="${assetRentSummary.gisAssetsAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총사용금액</th><td><fmt:formatNumber value="${assetRentSummary.totFee }" type="number" /> 원</td></tr>
			</tbody></table>
			</c:if>
			<c:if test="${assetRentSummary!=null }">
			<h2>총사용현황</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>총사용면적</th><td><fmt:formatNumber value="${assetRentSummary.totUsageAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>미사용면적</th><td><fmt:formatNumber value="${assetRentSummary.unUsageAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총면적</th><td><fmt:formatNumber value="${assetRentSummary.gisAssetsAr }" maxIntegerDigits="8" maxFractionDigits="2" /> (단위:m²)</td></tr>
				<tr><th>총사용금액</th><td><fmt:formatNumber value="${assetRentSummary.totFee }" type="number" /> 원</td></tr>
			</tbody></table>
			</c:if>

			<c:if test="${assetHtldInfo!=null }">
			<h2>배후단지 정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>배후단지 임대업체</th><td><c:out value="${assetHtldInfo.entrpsNm }" /></td></tr>
			</tbody></table>
			</c:if>

			<c:if test="${fn:containsIgnoreCase(auth,'ROLEADMIN')||fn:containsIgnoreCase(auth,'ROLEASSETMNGT') }">
				<button id="assetMngt" data-icon="ui-icon-newwin">자산코드 관리</button>
				<button id="fcltyRentMngt" data-icon="ui-icon-newwin">사용목록 관리</button>
				<button data-cmd="removeFeature">영역 삭제</button>
			</c:if>
			<button id="assetInqire" data-icon="ui-icon-newwin">자산정보 조회</button>
		</div>
	</c:if>
</c:if>
