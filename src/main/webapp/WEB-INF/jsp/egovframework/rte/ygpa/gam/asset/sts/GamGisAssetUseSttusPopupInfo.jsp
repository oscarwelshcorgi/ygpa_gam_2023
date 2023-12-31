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
function GamAssetSttusInfoModule() {};

GamAssetSttusInfoModule.prototype = new EmdPopupInfoModule();

GamAssetSttusInfoModule.prototype.loadComplete = function() {
    var dataset = [
	 <c:forEach var="usage" items="${usageList }" varStatus="varStatus">
	 	{
		 usageAr: "<c:out value='${usage.usageAr}' />",
		 entrpsNm: "<c:out value='${usage.entrpsNm}' />"
		 },
	 </c:forEach>
	];
   	var chart1 =  new dhtmlXChart({
   		view:"bar",
   		container:this.$("#chart").attr('id'),
   	    value:"#usageRatio#",
           label: '#usageArLabel#',
           color: "#color#",
           gradient:"rising",
   		width:80,
   		padding: {
   			left:65
   		},
   		tooltip:{
   			template:"#usageArLabel# m²"
   		},
   		xAxis:{
   			template:"#entrpsNm#"
   		},
   		yAxis:{
   			width:80,
   			start:0,
   	   		end:100,
   	   		step:20,
   			template:function(obj) {
   				return $.number(obj);
   			}
   		}
   	});
   	var rentAr = parseFloat("<c:out value='${assetCodeInfo.gisAssetsAr}' />");
   	if(isNaN(rentAr)) {
   		rentAr=0;
   	   	for(var k in dataset) {
   	   		rentAr += dataset[k].usageAr;
   	   	}
   	}
   	var colors= [
   	             "#ee9336",
   	             "#a7ee70",
   	             "#58dccd",
   	             "#476cee",
   	             "#e33fc7"
   	             ];
   	for(var k in dataset) {
   		if(rentAr!=0) {
   	   		var ratio = dataset[k].usageAr/rentAr*100;

   		}
   		else ratio=100;
   		dataset[k]['color']=colors[k%colors.length];
   		dataset[k]['usageRatio']=ratio;
   		dataset[k]['usageArLabel']=$.number(dataset[k].usageAr)+" m² [ "+Math.round(ratio)+"%]";
   	}

	chart1.parse(dataset,"json");

	//console.log('GamAssetSttusInfoModule loadcomplete');
};

GamAssetSttusInfoModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetInqire':
        	EMD.util.create_window("gamAssetCodeList", "자산정보 조회", "/code/assets/gamAssetCodeList.do", null, {
        		action: "prtFcltyInqire"
       			,gisPrtAtCode: this.$('#gisAssetsPrtAtCode').val()
       			,gisAssetsCd: this.$('#gisAssetsCd').val()
       			,gisAssetsSubCd: this.$('#gisAssetsSubCd').val()
			});
        	break;
    }
};

var popupInfoModule = new GamAssetSttusInfoModule();
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
				<tr><th>조회기준일자</th><td colspan="3"><c:out value="${referDate }" /></td></tr>
				<tr><th>시설명</th><td colspan="3"><c:out value="${assetCodeInfo.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td colspan="3"><c:out value="${assetCodeInfo.gisAssetsLocplc }" /> <c:out value="${assetCodeInfo.gisAssetsLnm }" /><c:if test="${assetCodeInfo.gisAssetsLnmSub!=null }">-<c:out value="${assetCodeInfo.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${assetCodeInfo.gisAssetsAr }" maxIntegerDigits="10" maxFractionDigits="2" /> m²</td><th>사용 면적</th><td><fmt:formatNumber value="${assetCodeInfo.usageAr }" maxIntegerDigits="10" maxFractionDigits="2" /> m²</td></tr>
				<tr><th>사용 업체 수</th><td colspan="3"><fmt:formatNumber value="${assetCodeInfo.entrpsCnt }" maxIntegerDigits="10" maxFractionDigits="2" /></td></tr>
			</tbody></table>
			<div id="chart" style="width:500px;height:220px;border:1px solid #A4BED4;"></div>
			<button data-role="assetInqire">자산코드 조회</button>
		</div>
	</c:if>
</c:if>
