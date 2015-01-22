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
	 <c:forEach var="rent" items="${rentList }" varStatus="varStatus">
	 { fee: "<c:out value='${rent.fee}' />",
		 rdcxptFee: "<c:out value='${rent.rdcxptFee}' />",
		 months: "<c:out value='${rent.months}' />" },
	 </c:forEach>
	];

    this.$("#chart").css('width', dataset.length*60);
   	var chart1 =  new dhtmlXChart({
   		view:"bar",
   		container:this.$("#chart").attr('id'),
   	    value:"#fee#",
           label: '#feeLabel#',
           color: "#color#",
           gradient:"rising",
   		width:80,
   		padding: {
   			left:65
   		},
   		tooltip:{
   			template:"#feeLabel# 원"
   		},
   		xAxis:{
   			template:"#months#"
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
   		dataset[k]['feeLabel']=$.number(dataset[k].fee)+" 원";
   		dataset[k]['rdcxptFeeLabel']=$.number(dataset[k].rdcxptFee)+" 원";
   	}
/*
	chart1.addSeries({
		alpha:0.5,
		value:"#rdcxptFee#",
		label:"#rdcxptFeeLabel#",
		color:"#a7ee70",
	});
*/
	chart1.parse(dataset,"json");

	console.log('GamAssetSttusInfoModule loadcomplete');
};

GamAssetSttusInfoModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetInqire':
        	EMD.util.create_window("자산코드 조회", "/code/assets/gamAssetCodeList.do", null, {
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
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${assetCd.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${assetCd.gisAssetsSubCd }' />" />

<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${assetCd==null }">
	<h2>자산 정보를 불러올 수 없습니다.</h2>
	</c:if>
	<c:if test="${assetCd!=null }">
		<div class='prtFcltyInfo'>
			<h2>시설 사용료 현황 정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>조회기기간</th><td colspan="3"><c:out value="${sGrUsagePdFrom }" /> ~ <c:out value="${sGrUsagePdTo }" /></td></tr>
				<tr><th>시설명</th><td colspan="3"><c:out value="${assetCd.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td colspan="3"><c:out value="${assetCd.gisAssetsLocplc }" /> <c:out value="${assetCd.gisAssetsLnm }" /><c:if test="${assetCd.gisAssetsLnmSub!=null }">-<c:out value="${assetCd.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${assetCd.gisAssetsAr }" maxIntegerDigits="10" maxFractionDigits="2" /> m²</td><th>사용 면적</th><td><fmt:formatNumber value="${rentSttus.usageAr }" maxIntegerDigits="10" maxFractionDigits="2" /> m²</td></tr>
				<tr><th>사용 업체 수</th><td><fmt:formatNumber value="${rentSttus.entrpsCnt }" maxIntegerDigits="10" maxFractionDigits="2" /></td><th>사용료 합계</th><td><fmt:formatNumber value="${rentSttus.fee }" maxIntegerDigits="15" /> 원</td></tr>
			</tbody></table>
			<div id="chart" style="width:500px;height:220px;border:1px solid #A4BED4;"></div>
			<button data-role="assetInqire">자산코드 조회</button>
		</div>
	</c:if>
</c:if>
