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

GamMapPopupModule.prototype.loadComplete = function() {
	console.log('popup module starting debugging.');
};

GamMapPopupModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'fcltyCdMngt':
        	EMD.util.create_window("시설코드 관리", "/code/assets/gamAssetCodeMngt.do", null, {
        		action: "prtFcltyInqire"
    			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
    			,gisAssetsCd: this.$('#gisAssetsCd').val()
    			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
            break;
        case 'fcltyCdInqire':
        	EMD.util.create_window("시설코드 조회", "/code/assets/gamAssetCodeList.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
        	break;
        case 'assignFeature':
        	EMD.util.create_window("시설 제원 코드 지정", "/fclty/gamConstFcltySpecMng.do", null, {
        		action: "setFeature"
    			,feature: this.getFeature()
			});
        	break;
        case 'removeFeature':
        	if(this.getFeature()==null) {
        		alert('지도에 오류가 있습니다.');
        		return;
        	}
        	var f = this.getFeature();
        	EMD.map.removePopup(f.popup);
        	f.layer.removeFeatures([f]);
        	//this.getFeature().state = OpenLayers.State.DELETE;
            //this.removeFeatures([this.getFeature()]);
        	break;
    }
};

var popupInfoModule = new GamMapPopupModule();
</script>
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${fcltyCd.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${fcltyCd.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${fcltyCd.gisAssetsSubCd }' />" />
<input id="gisPrtFcltyCd" type="hidden" value="<c:out value='${fcltyCd.gisPrtFcltyCd }' />" />
<input id="gisPrtFcltySeq" type="hidden" value="<c:out value='${fcltyCd.gisPrtFcltySeq }' />" />
<input id="fcltySe" type="hidden" value="<c:out value='${fcltyCd.fcltySe }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${fcltyCd.gisAssetsSubCd }' />" />
<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${fcltyCd==null }">
		<h2>시설정보가 없습니다.</h2>
		<c:if test="${fn:containsIgnoreCase(auth,'role_admin')||fn:containsIgnoreCase(auth,'role_manager') }">
			<button id="assignFeature">시설코드 지정</button>
			<button id="removeFeature">영역 삭제</button>
		</c:if>
	</c:if>
	<c:if test="${fcltyCd!=null }">
		<div class='prtFcltyInfo'>
			<h2>시설정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>자산코드</th><td><c:out value="${fcltyCd.gisAssetsCd }" /> - <c:out value="${fcltyCd.gisAssetsSubCd }" /></td></tr>
				<tr><th>시설명</th><td><c:out value="${fcltyCd.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td><c:out value="${fcltyCd.gisAssetsLocplc }" /> <c:out value="${fcltyCd.gisAssetsLnm }" /><c:if test="${fcltyCd.gisAssetsLnmSub!=null }">-<c:out value="${fcltyCd.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${fcltyCd.gisAssetsAr }" maxIntegerDigits="3" maxFractionDigits="2" /> m²</td></tr>
				<c:if test="${fn:length(fcltyCd.filenmPhysicl)>0 }">
					<tr><td colspan="2"><img id="imgPreview" src="<c:url value='cmm/getImage.do?physicalFileNm=${fcltyCd.filenmPhysicl }' />" style='width:300px;' /></td></tr>
				</c:if>
			</tbody></table>
			<c:if test="${fn:containsIgnoreCase(auth,'role_admin')||fn:containsIgnoreCase(auth,'role_manager') }">
				<button id="fcltyCdMngt" data-icon="ui-icon-newwin">시설코드 관리</button>
			</c:if>
			<button id="fcltyCdInqire" data-icon="ui-icon-newwin">자산정보 조회</button>
		</div>
	</c:if>
</c:if>
