<%@page import="org.springframework.util.Assert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
(function($, window){
	alert('hello');
	console.log('popup start...');

	$('#imgPreview').load(function() {
		$(this).closest('.olPopup').setSize($(this).find("table.prtfcltyInfo").width(), $(this).find("table.prtfcltyInfo").height());
	});
})(jQuery, this);
</script>
<div class='prtFcltyInfo'>
<h2>시설정보</h2>
<table class='prtFcltyInfo'><tbody>
<tr><th>자산코드</th><td><c:out value="${assetCd.gisAssetsCd }" /> - <c:out value="${assetCd.gisAssetsSubCd }" /></td></tr>
<tr><th>시설명</th><td><c:out value="${assetCd.gisAssetsNm }" /></td></tr>
<tr><th>소재지</th><td><c:out value="${assetCd.gisAssetsLocplc }" /> <c:out value="${assetCd.gisAssetsLnm }" /><c:if test="${assetCd.gisAssetsLnmSub!=null }">-<c:out value="${assetCd.gisAssetsLnmSub }" /></c:if></td></tr>
<c:if test="${assetCd.filenmPhysicl.length>0 }">
<tr><th>자산사진</th><td><img id="imgPreview" src="<c:url value='cmm/getImage.do?physicalFileNm=${assetCd.filenmPhysicl }' />" style='width:300px;' /></td></tr>
</c:if>
</tbody></table>
</div>
