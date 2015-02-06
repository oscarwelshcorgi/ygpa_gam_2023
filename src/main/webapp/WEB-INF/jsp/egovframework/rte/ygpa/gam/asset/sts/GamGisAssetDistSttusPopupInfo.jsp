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
function GamAssetSttusDistInfoModule() {};

GamAssetSttusDistInfoModule.prototype = new EmdPopupInfoModule();

GamAssetSttusDistInfoModule.prototype.loadComplete = function() {
    var dataset = [
	 <c:forEach var="dist" items="${distList }" varStatus="varStatus">
	 { prtFcltySeNm: "<c:out value='${dist.prtFcltySeNm}' />",
		 fcltyCnt: "<c:out value='${dist.fcltyCnt}' />"
		 },
	 </c:forEach>
	];
   	var chart1 =  new dhtmlXChart({
   		view:"bar",
   		container:this.$("#chart").attr('id'),
   	    value:"#fcltyCnt#",
           label: '#fcltyCnt# 개',
           color: "#color#",
           gradient:"rising",
   		width:80,
   		padding: {
   			left:40
   		},
   		tooltip:{
   			template:"#prtFcltySeNm# - #fcltyCnt# 개"
   		},
   		xAxis:{
   			template:"#prtFcltySeNm#"
   		},
   		yAxis:{
   			width:80,
   			template:function(obj) {
   				return $.number(obj);
   			}
   		}
   	});

   	var colors= [
   	             "#ee9336",
   	             "#a7ee70",
   	             "#58dccd",
   	             "#476cee",
   	             "#e33fc7"
   	             ];
   	for(var k in dataset) {
   		dataset[k]['color']=colors[k%colors.length];
   	}
	chart1.parse(dataset,"json");

	//console.log('GamAssetSttusDistInfoModule loadcomplete');
};

GamAssetSttusDistInfoModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetInqire':
        	EMD.util.create_window("자산정보 조회", "/code/assets/gamAssetCodeList.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
        	break;
    }
};

var popupInfoModule = new GamAssetSttusDistInfoModule();
</script>
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${assetCodeInfo.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${assetCodeInfo.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${assetCodeInfo.gisAssetsSubCd }' />" />

<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${assetCodeInfo==null }">
	<h2>자산 정보를 불러올 수 없습니다.</h2>
	</c:if>
	<c:if test="${assetCodeInfo!=null }">
		<div class='prtFcltyInfo'>
			<h2>시설 사용 현황 정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>시설명</th><td><c:out value="${assetCodeInfo.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td><c:out value="${assetCodeInfo.gisAssetsLocplc }" /> <c:out value="${assetCodeInfo.gisAssetsLnm }" /><c:if test="${assetCodeInfo.gisAssetsLnmSub!=null }">-<c:out value="${assetCodeInfo.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${assetCodeInfo.gisAssetsAr }" maxIntegerDigits="10" maxFractionDigits="2" /> m²</td></tr>
			</tbody></table>
			<div id="chart" style="width:500px;height:220px;border:1px solid #A4BED4;"></div>
			<button data-role="assetInqire">자산코드 조회</button>
		</div>
	</c:if>
</c:if>
