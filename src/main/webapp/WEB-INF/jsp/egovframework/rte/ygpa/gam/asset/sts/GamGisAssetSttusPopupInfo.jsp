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
	 <c:forEach var="deprctn" items="${deprctnList }" varStatus="varStatus">
	 { bsThisCurAmt: "<c:out value='${deprctn.bsThisCurAmt}' />",
		 bsPreDeprctnSum: "<c:out value='${deprctn.bsPreDeprctnSum}' />",
		 bsNoDeprctnBal: "<c:out value='${deprctn.bsNoDeprctnBal}' />",
		 deprctnYear: "<c:out value='${deprctn.deprctnYear}' />" },
	 </c:forEach>
	];
   	var chart1 =  new dhtmlXChart({
   		view:"bar",
   		container:this.$("#chart").attr('id'),
   	    value:"#bsThisCurAmt#",
           label: '#bsThisCurAmtLabel#',
           color: "#58dccd",
           gradient:"rising",
   		width:80,
   		padding: {
   			left:85
   		},
   		tooltip:{
   			template:"#bsThisCurAmtLabel#"
   		},
   		xAxis:{
   			template:"'#deprctnYear#"
   		},
   		yAxis:{
   			width:80,
   			template:function(obj) {
   				return $.number(obj);
   			}
   		},
   		legend:{
   			values:[{text:"대차대조기말현재금액",color:"#36abee"},
   			        {text:"대차대조전기말상각누계금액",color:"#a7ee70"},
   			     {text:"대차대조미상각잔액",color:"#a924b9"}
   			],
   			valign:"middle",
   			align:"right",
   			width:90,
   			layout:"y"
   		}
   	});
   	for(var k in dataset) {
   		dataset[k]['bsThisCurAmtLabel']=$.number(dataset[k].bsThisCurAmt);
   		dataset[k]['bsPreDeprctnSumLabel']=$.number(dataset[k].bsPreDeprctnSum);
   		dataset[k]['bsNoDeprctnBalLabel']=$.number(dataset[k].bsNoDeprctnBal);
   	}

	chart1.addSeries({
		alpha:0.5,
		value:"#bsPreDeprctnSum#",
		label:"#bsPreDeprctnSumLabel#",
		color:"#a7ee70",
	});
	chart1.addSeries({
		alpha:0.5,
		value:"#bsNoDeprctnBal#",
		label:"#bsNoDeprctnBalLabel#",
		color:"#a924b9",
	});
	chart1.parse(dataset,"json");

	console.log('GamAssetSttusInfoModule loadcomplete');
};

GamAssetSttusInfoModule.prototype.onButtonClick = function(buttonId) {
    switch(buttonId) {
        case 'assetInqire':
        	EMD.util.create_window("gamAssetCodeList", "자산코드 조회1", "/code/assets/gamAssetCodeList.do", null, {
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
			<h2>시설 통계 정보</h2>
			<table class='prtFcltyInfo'><tbody>
				<tr><th>시설명</th><td><c:out value="${assetCd.gisAssetsNm }" /></td></tr>
				<tr><th>소재지</th><td><c:out value="${assetCd.gisAssetsLocplc }" /> <c:out value="${assetCd.gisAssetsLnm }" /><c:if test="${assetCd.gisAssetsLnmSub!=null }">-<c:out value="${assetCd.gisAssetsLnmSub }" /></c:if></td></tr>
				<tr><th>면적</th><td><fmt:formatNumber value="${assetCd.gisAssetsAr }" maxIntegerDigits="3" maxFractionDigits="2" /> m²</td></tr>
				<tr><th>취득원가</th><td><fmt:formatNumber value="${assetCd.gisAssetsValAmt }" maxIntegerDigits="3" maxFractionDigits="2" /> 원</td></tr>
			</tbody></table>
			<div id="chart" style="width:500px;height:220px;border:1px solid #A4BED4;"></div>
			<button data-role="assetInqire">자산코드 조회</button>
		</div>
	</c:if>
</c:if>
