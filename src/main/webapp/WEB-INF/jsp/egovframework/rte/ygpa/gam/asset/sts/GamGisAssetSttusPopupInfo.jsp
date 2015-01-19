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
    var dataset = [
       			{ sales:"1963050", sales2:"0", sales3:"1963050", year:"09" },
       			{ sales:"209357", sales2:"3863", sales3:"2168544", year:"10" },
       			{ sales:"31475", sales2:"81920", sales3:"2118099", year:"11" },
       			{ sales:"49530", sales2:"695432", sales3:"1472197", year:"12" },
       			{ sales:"892700", sales2:"1653852", sales3:"711045", year:"13" },
       			{ sales:"27533", sales2:"2354712", sales3:"-1616134", year:"14" }
                      ];
   	var chart1 =  new dhtmlXChart({
   		view:"bar",
   		container:this.$("#displayChart").attr('id'),
   	    value:"#sales#",
           label: '#salesLabel#',
           color: "#58dccd",
           gradient:"rising",
   		width:80,
   		padding: {
   			left:65
   		},
   		tooltip:{
   			template:"#sales#"
   		},
   		xAxis:{
   			template:"'#year#"
   		},
   		yAxis:{
   			width:80,
   			template:function(obj) {
   				return $.number(obj);
   			}
   		},
   		legend:{
   			values:[{text:"유지보수",color:"#36abee"},{text:"임대수입",color:"#a7ee70"}],
   			valign:"middle",
   			align:"right",
   			width:90,
   			layout:"y"
   		}
   	});
   	for(var k in dataset) {
   		dataset[k]['salesLabel']=$.number(dataset[k].sales);
   		dataset[k]['sales2Label']=$.number(dataset[k].sales2);
   	}

//           var chart1 =  new dhtmlXChart({
//       		view:"area",
//       		container:chartId,
//               value:"#sales#",
//               color: "#58dccd",
//               alpha:0.7,
//       		xAxis:{
//       				template:"'#year#"
//               },
//               yAxis:{
//                   start:0,
//                   step:200000,
//                   end: 2000000
//               },
//               legend:{
//                   values:[{text:"유지보수",color:"#58dccd"},{text:"임대수입",color:"#914ad8"},{text:"투자금액",color:"#36abee"}],
//                   valign:"middle",
//                   align:"right",
//                   width:90,
//                   layout:"y"
//               },
//               eventRadius: 5
//       	});
	chart1.addSeries({
		alpha:0.5,
		value:"#sales2#",
		label:"#sales2Label#",
		color:"#a7ee70",
	});
//       	chart1.addSeries({
//       		alpha:0.5,
//               value:"#sales3#",
//               color:"#36abee"
//       	});
	chart1.parse(dataset,"json");
	console.log('popup module starting debugging.');
};

GamMapPopupModule.prototype.onButtonClick = function(buttonId) {
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

var popupInfoModule = new GamMapPopupModule();
</script>
<input id="gisAssetsPrtAtCode" type="hidden" value="<c:out value='${assetCd.gisAssetsPrtAtCode }' />" />
<input id="gisAssetsCd" type="hidden" value="<c:out value='${assetCd.gisAssetsCd }' />" />
<input id="gisAssetsSubCd" type="hidden" value="<c:out value='${assetCd.gisAssetsSubCd }' />" />

<c:if test="${resultCode!=0 }">
	<h2><c:out value="${resultMsg }" /></h2>
</c:if>
<c:if test="${resultCode==0 }">
	<c:if test="${assetCd==null }">
	<h2>자산 현황</h2>
	<div id="displayChart" style="width:600px;height:250px;border:1px solid #A4BED4;"></div>
	<div class="legend">단위:원</div>
	<div style="text-align:right; width:100%; margin-top:4px;">
		<button data-role="assetInqire">자산코드 조회</button>
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
			<button data-role="assetInqire">자산정보 조회</button>
		</div>
	</c:if>
</c:if>
