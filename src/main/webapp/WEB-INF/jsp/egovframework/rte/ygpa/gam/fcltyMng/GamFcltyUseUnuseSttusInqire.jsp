<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%

  /**
  * @Class Name :	GamFcltyUseUnuseSttusInqire.jsp
  * @Description : 시설물 사용/미사용시설 조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.11.  정성현          최초 생성
  *
  * author 정성현
  * since 2014.12.11.
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
 <validator:javascript formName="searchForm" method="validateGamFcltyUseUnuseSttusInqire" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" /> 
<script>
/*  
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamFcltyUseUnuseSttusInqireModule() {
}

GamFcltyUseUnuseSttusInqireModule.prototype = new EmdModule(1000,800);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltyUseUnuseSttusInqireModule.prototype.loadComplete = function() {


	// 테이블 설정
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyUseUnuseSttusInqireList.do',
		dataType: "json",
		colModel : [
				{display:"항구분",			name:"prtAtCodeNm",	width:60,		sortable:false,		align:"center"},
				{display:"자산명",			name:"gisAssetsNm",			width:180,		sortable:false,		align:"left"},
				{display:"소재지", 			name:"gisAssetsLnms",		width:200,		sortable:false,		align:"left"},
				{display:"자산면적㎡",		name:"gisAssetsAr",			width:100,		sortable:false,		align:"right" , displayFormat: 'number', displayOption:{format:"0,000.00"}},
				{display:"임대가용면적㎡",	name:"gisAssetsRealRentAr",	width:100, 		sortable:false,		align:"right" ,displayFormat: 'number', displayOption:{format:"0,000.00"}},
				{display:"총사용면적㎡",	 	name:"usageAr",				width:100, 		sortable:false,		align:"right" ,displayFormat: 'number', displayOption:{format:"0,000.00"} },
				{display:"미사용면적㎡", 	name:"noUsageAr",			width:100, 		sortable:false,		align:"right" , displayFormat: 'number', displayOption:{format:"0,000.00"}},
				{display:"사용률％", 		name:"usageArPer",			width:65, 		sortable:false,		align:"right" },
				{display:"기간From",			name:"usagePdFrom",				width:80, 		sortable:false,		align:"center"},
				{display:"기간To",			name:"usagePdTo",				width:80, 		sortable:false,		align:"center"  }
			],
			showTableToggleBtn: false,
			height: "auto"
				,preProcess : function(module,data) {
					
					module.$('#dataCount').val(data.dataCount);
					module.$('#sumAssetsAr').val(data.sumAssetsAr);
					module.$('#sumUsageAr').val(data.sumUsageAr);
					module.makeDivValues('#emdControlPanelForm', data);
				return data;
			}
		
		
	});
 	
	this.$("#mainGrid").on("onItemSelected", function(event, module, row, grid, param) {
	});

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		
		module.$("#mainTab").tabs("option", {active: 1});
	});

	// 시설물 사용/미사용 시설 상세
	
    this.$("#detailGrid").flexigrid({
        module: this,
        url: '/fcltyMng/selectFcltyUseUnuseSttusInqireDetailList.do',
        dataType: 'json',
        colModel : [
                	{display:"업체명",			name:"entrpsNm",			width:200,		sortable:false,		align:"left"},
                	{display:"사용목적",		name:"usagePurps",				width:160,		sortable:false,		align:"right" },
        			{display:"사용면적㎡",	name:"usageAr",					width:120, 		sortable:false,		align:"right", displayFormat: 'number', displayOption:{format:"0,000.00"}},
        			{display:"사용률 %",		name:"usageArPer",				width:120,		sortable:false,		align:"right", displayFormat: 'number', displayOption:{format:"0,000.00"}},
        			{display:"사용료 (원)", 		name:"fee",						width:120, 		sortable:false,		align:"right", displayFormat:"number"},
    				{display:"기간From",			name:"usagePdFrom",				width:120, 		sortable:false,		align:"center"},
    				{display:"기간To",			name:"usagePdTo",				width:120, 		sortable:false,		align:"center"  }
                    ],

          showTableToggleBtn: false,
        height: '150'

   
    });
    this.$("#detailGrid").on("onItemSelected", function(event, module, row, grid, param) {
    	
    });
 	
    this.$("#detailGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
    	
    	module.$("#mainTab").tabs("option", {active: 2});

    });
    
    this.$("#detailGrid").on("onLoadDataComplete", function(event, module) {
    	module.drawBarChart();		
    });
};
GamFcltyUseUnuseSttusInqireModule.prototype.onSubmit = function() {
  	if(!validateGamFcltyUseUnuseSttusInqire(this.$('#searchForm')[0])){ 		
		return;
	}
  	
 	
 	this.$('#detailGrid').flexEmptyData();
 	this.initDisplay();
	this.$("#mainTab").tabs("option", {active: 0});
	this.loadData();
	};

	//시설목록 로드
	
GamFcltyUseUnuseSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchForm");
	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
	
	};


	
<%--
	상세 페이지를 로드 한다.
--%>	
GamFcltyUseUnuseSttusInqireModule.prototype.loadDetailData = function() {
	var data=this.$('#mainGrid').selectedRows()[0];
 	var searchOpt = [
						{name: 'gisAssetsPrtAtCode', value: data["gisAssetsPrtAtCode"]},
						{name: 'gisAssetsCd', value: data["gisAssetsCd"]},
						{name: 'gisAssetsSubCd', value: data["gisAssetsSubCd"]},
						{name: 'usagePdFrom', value: data["usagePdFrom"]},
						{name: 'usagePdTo', value: data["usagePdTo"]},
						];				
	
	this.makeFormValues('#summaryForm', data);
 
	this.$("#detailGrid").flexOptions({params:searchOpt}).flexReload();
};

<%--
	임대 상세 내역을 로드한다.
--%>
GamFcltyUseUnuseSttusInqireModule.prototype.loadRentDetailData = function() {
	var row = this.$("#detailGrid").selectedRows()[0];
	this.makeFormValues('#detailForm', row);

	this.drawPieChart(row);
	
}


// 탭 변경시 실행

GamFcltyUseUnuseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		this.$('#tabs2').scrollTop(0);

		this.loadDetailData();
		break;
	case "tabs3":
		this.loadRentDetailData();
		break;	
	}
};

GamFcltyUseUnuseSttusInqireModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
		var mainRows = this.$('#mainGrid').selectedRows();
		if (mainRows.length == 0) {
			alert('항목을 선택해주세요.');
			this.$("#mainTab").tabs("option", {active: 0});
			return false;
		}
		break;
	case "tabs3" : 
		var mainRows = this.$('#mainGrid').selectedRows();
		var detailRows = this.$('#detailGrid').selectedRows();
		if(mainRows.length == 0){
			this.initDisplay();
			alert('항목을 선택해주세요.');
			this.$("#mainTab").tabs("option", {active: 0});
			return false;
		}else if (detailRows.length == 0){
			alert('상세 내역을 선택해주세요.');
			this.$('#mainTab').tabs("option",{active: 1});
			return false;
		}
		break;	
	}
	return true;
};



GamFcltyUseUnuseSttusInqireModule.prototype.drawBarChart = function() {
	
	var searchVO = this.makeFormArgs('#summaryForm');
	var usageAr = 0;
	var usageArArr=[];
	var maxUsageAr=0;
	
	this.doAction("/fcltyMng/selectFcltyUseUnuseSttusInqireChartList.do" , searchVO, function(module,result) {
	    
		 if(result.resultCode == 0){
	
		for (var i=0; i<result.resultList.length; i++) {
			
			usageAr=result.resultList[i]['usageAr'];
			entrpsNms = result.resultList[i]['entrpsNm'];
			usageArArr[i]={entrpsNm : entrpsNms , amount : usageAr};
			if (maxUsageAr<usageAr) {
				maxUsageAr=usageAr;
			}
		};
	} else{
		
		for (var i=0; i<result.resultList.length; i++) {
			usageAr=0;
			usageArArr[i]={entprsNm: entrpsNms, amount : usageAr};
		};
	}
	if (maxUsageAr<10) {
		maxUsageAr=10;
	}
	if (module.barChart==null) {
	module.barChart = new dhtmlXChart({
		view			: "bar",
		container		: module.$('#barChartCons')[0],
		value			: "#amount#",
		color			: "#5CD1E5",
        gradient		: "rising",
		width			: 30,
		label			: "#amount#",
		tooltip			: "#amount# (㎡)",
		xAxis			: {
			title 		: "업체별 사용량 현황 ",
			template	: "#entrpsNm#"
		},
		yAxis			: {
			start		: 0,
			end			: maxUsageAr + 10,
			step		: Math.ceil(maxUsageAr / 10),
			title		: "사용량 ,㎡"
		}
	});
} else {
	module.barChart.clearAll();
	module.barChart.define("yAxis", {
		start : 0,
		end : maxUsageAr + 10,
		step : Math.ceil(maxUsageAr / 10),
		title : "업체별 사용량 현황 ,㎡"
	});
}
module.barChart.parse(usageArArr, "json");
module.barChart.refresh();

}) 
};


<%--
	임대 상세 내역 차트를 출력한다.
--%>
GamFcltyUseUnuseSttusInqireModule.prototype.drawPieChart = function(result) {
	
	var remainAr=0;
	var remainLgnd= "잔여면적";
	var usageLgnd = "사용면적";
	var usageAr =result['usageAr'];
	var realRentAr=result['gisAssetsRealRentAr'];
	var usageArPerData = result['usageArPer'];
	var index=0;
	
		 if(realRentAr >= usageAr){
	  remainAr = realRentAr-usageAr;
			}
	var pieData = [
	         {usage: usageAr 	 , legendNm : usageLgnd  ,	color:"#0054FF" },
	 		 {usage: remainAr	 , legendNm : remainLgnd ,	color:"#FF00DD"	},
	         	  ];
	
	var barData = ({usageArPer : usageArPerData}); 
	
	if ((this.pieChart==null) && (this.usageBarChart==null)) {
		
		this.pieChart =  new dhtmlXChart({
     		 view:"donut",
		container:this.$('#pieChartCons')[0],
        	value:"#usage#",
        	gradient:true,
            radius:	100,
            cant:1.0,
            label: "<b>#usage#</b> (㎡)",
          	color: "#color#",
            
                 legend:{
                 width: 75,
                 align: "right",
                 valign: "top",
                 marker:{
                         type: "round",
                     width: 15
                 },
                     template: "#legendNm#"
                }
        
	});
		this.usageBarChart = new dhtmlXChart({
			view			: "bar",
			container		: this.$('#usageBarChartCons')[0],
			value			: "#usageArPer#",
			color			: "#5CD1E5",
	        gradient		: "rising",
			width			: 30,
			label			: "#usageArPer#",
			
			xAxis			: {
				  template: "사용률 (%)"
				
			},
			yAxis			: {
				start		: 0,
				end			: 100,
				step		: 10,
				
			}
		});
		
		
	}else{
	this.pieChart.clearAll();
	this.usageBarChart.clearAll();
}
	this.pieChart.parse(pieData,"json");
	this.usageBarChart.parse(barData,"json");
};


GamFcltyUseUnuseSttusInqireModule.prototype.downloadExcel = function() {
	var RowCount = this.$("#mainGrid").flexRowCount();
	if (RowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/gamFcltyUseUnuseSttusInqireExcel.do');
};


	/**
	 * 정의 된 버튼 클릭 시
	 */
GamFcltyUseUnuseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

		switch(buttonId){
		case 'btnExcelDownload' :
			this.downloadExcel();
			break;
		}
	
};


//화면 및 데이터 초기화 처리

GamFcltyUseUnuseSttusInqireModule.prototype.initDisplay = function() {
	
	this.$("#summaryForm :input").val("");
	this.$("#detailForm :input").val("");

};


	// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyUseUnuseSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>

						<tr>
						<th>항코드</th>
							<td><input id="searchPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
							<th>조회 기간</th>
                        	 <td>
                            	<input id="searchDtFr" type="text" class="emdcal" size="8"> ~
                            	<input id="searchDtTo" type="text" class="emdcal" size="8">
                             </td>
                           	<td rowspan="2"><button class="buttonSearch">조회</button></td>
							</tr>
							<tr>
							<th>시설명</th>
							<td><input id="searchFcltyNm" type="text" size=30/></td>
							<th>소재지</th>
							<td><input id="searchLoc" type="text" size=30/></td>
					</tr>
					</tbody>
				</table>

			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore" >
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용/미사용시설 조회</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 사용/미사용시설 업체별 상세</a></li>
				<li><a href="#tabs3" class="emdTab">임대 상세 내역</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display: none" class="fillHeight"></table>
				 
			<div id="emdControlPanel" class="emdControlPanel">
					<form id="emdControlPanelForm">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="15%" height="25">자료수</th>
						<td><input type="text" size="8" id="dataCount" class="ygpaNumber" data-column-id="dataCount" data-decimal-point="2" disabled="disabled" /></td>
						<th width="15%" height="25">총 자산면적㎡</th>
						<td><input type="text" size="24" id="sumAssetsAr" class="ygpaNumber" data-column-id="sumAssetsAr" data-decimal-point="2" disabled="disabled"/> ㎡</td>
						<th width="15%" height="25">총 사용면적㎡</th>
						<td><input type="text" size="24" id="sumUsageAr" class="ygpaNumber" data-column-id="sumUsageAr" data-decimal-point="2" disabled="disabled"/> ㎡</td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                      <!--   <button data-role="showMap" data-gis-layer="gisAssetsCd"
									data-flexi-grid="fcltyUseUnuseSttusInqireList" data-style="default">맵조회</button> -->
	                            <td style="text-align: right">
	                            	<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
	                            </td>
	                        
	                        </tr>
						</table>
					</form>
				</div>
			</div>

           <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
               <div class="emdControlPanel">
                    <form id="summaryForm">
 						 <table class="summaryPanel">
                                <tr>
                                <th width="10%" height="18">항구분</th>
                                <td><input type="text" size="13" id="prtAtCodeNm2" disabled/> </td>
								<th width="10%" height="18">자산코드</th>
                                <td>
                               <input type="text" size="3" id="gisAssetsPrtAtCode" disabled/>-<input type="text" size="4" id="gisAssetsCd" disabled/>-<input type="text" size="2" id="gisAssetsSubCd" disabled/>
                                </td>
								<th width="10%" height="18">자산명</th>
                                <td><input type="text" size="21" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="20"  id="gisAssetsAr" class="ygpaNumber"  data-column-id="gisAssetsAr"  data-decimal-point="2"  disabled/>㎡</td>
								
								<th width="10%" height="18">지번</th>
                                <td>
                                	<input type="text" size="5" id="gisAssetsLnm" disabled/> -
                                	<input type="text" size="3" id="gisAssetsLnmSub" disabled/>
                                </td>
								<th width="10%" height="18">소재지</th>
                                <td><input type="text" size="35" id="gisAssetsLocplc"  disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">실제임대 가용면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="gisAssetsRealRentAr" data-column-id="gisAssetsRealRentAr" data-decimal-point="2" disabled/> ㎡</td>
								<th width="10%" height="18">총 사용면적</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="usageAr" data-column-id="usageAr" data-decimal-point="2" disabled/> ㎡</td>
                                <th width="10%" height="18">총 사용률</th>
                                <td><input type="text" size="7" id="usageArPer" style="text-align: right;" disabled/> %</td>
                                </tr>
							
							<input type="hidden"  id="usagePdFrom"/>
							<input type="hidden"  id="usagePdTo"/>
							
                        </table>
                    </form>


	                 
	
                 
                 
                 <table class="editForm" style="width:100%;">
							<tr>
						<th style="font-weight:bold; height:15px;">업체별 상세 내역</th>
								</tr>
					</table>
                 <table id="detailGrid" style="display: none"></table>
           	<table class="editForm">
               	<tr>
					<td>
				<div id="barChartCons" style="width:952px;height:350px;"></div>
					</td>
					</tr>
			</table>
			
					</div>
            </div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
						<form id="detailForm">				
							<table class="editForm" style="width : 100%">			
							<tr>	
								<th width="10%" height="18">업체명 </th>
								<td colspan="3"><input type="text" size="25" id="entrpsNm" disabled/></td>
								<th width="10%" height="18">신청기간</th>
                                <td colspan="2">
                                	<input type="text" size="11" id="usagePdFrom" disabled/> ~
                                	<input type="text"size="11" id="usagePdTo" disabled/>
                                </td>
       					                      
                            </tr>
                                <tr>
                                <th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="15" class="ygpaNumber" data-column-id="gisAssetsAr" data-decimal-point="2" id="gisAssetsAr" disabled/>㎡</td>
							
								<th width="10%" height="18">실제임대 가용면적</th>
                                <td><input type="text" size="15" class="ygpaNumber" data-column-id="gisAssetsRealRentAr" data-decimal-point="2" id="gisAssetsRealRentAr" disabled/>㎡</td>
							
								<th width="10%" height="18">사용면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" data-column-id="usageAr" data-decimal-point="2"  id="usageAr" disabled/>㎡</td>
                                </tr>
                           
                   			 <tr>
								<th width="10%" height="18">적용방법</th>
                                <td colspan="3">
                                    <input size="17" id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM014" value="1" disabled/>
                                </td>
                             <th width="10%" height="18">총 사용률</th>
                             <td colspan="2">
								<input type="text" size="20" id="usageArPer" style="text-align: right;" disabled/> %</td>
                              </tr>
                             <tr class="nationAssetLaw">
                                <th width="10%" height="18">적용요율</th>
                                <td colspan="5">
                                  
                                    <input size="23" id="applcTariff" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM023" disabled/>
                                       </td>
                              </tr>
                            <tr >
									<th width="10%" height="18">공시지가</th>
                                <td colspan="3"><input type="text" size="25" class="ygpaNumber calcInput" id="olnlp"  disabled/>원</td>
                            
                                <th width="10%" height="18">적용단가</th>
                                <td colspan="2"><input type="text" size="20" class="ygpaNumber calcInput" id="applcPrice" data-decimal-point="1"  disabled/>원</td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제구분</th>
                                <td colspan="3">
                                    <input size="17" id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM009" data-column-label-id='exemptSeNm' disabled/>
                                </td>
								<th width="10%" height="18">면제기간</th>
                                <td colspan="2">
                                	<input type="text"  size="11" id="exemptPdFrom"  disabled/> ~
                                	<input type="text"  size="11" id="exemptPdTo"disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제사유코드</th>
                                <td colspan="3">
                                    <input size="22" id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM017" disabled/>
                                </td>
								<th width="10%" height="18">면제사유</th>
                                <td><input type="text" size="24" id="exemptRsn"  disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">감면사용료</th>
                                <td colspan="3"><input type="text" size="25" class="calcInput" id="rdcxptFee" disabled/></td>
								<th width="10%" height="18">사용료</th>
                                <td colspan="2"><input type="text" size="20" style="text-align:right;" class="ygpaCurrency" id="fee" disabled/>원</td>

                            </tr>
                            <tr>
								<th width="10%" height="18">산출내역</th>
                                <td colspan="5"><input type="text" size="100" rows="2" id="computDtls" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용목적</th>
                                <td colspan="5"><input type="text" size="100" id="usagePurps" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용내역</th>
                                <td colspan="5"><input type="text" size="100" id="usageDtls"  disabled/></td>
                            </tr>
                            </table>
							<table class="editForm" style="width : 100%">
								<tr>
						<th style="font-weight:bold; height:15px;">사용 현황 그래프</th>
								</tr>
					</table>
							<td>
							</table>
							<table class="summaryPanel">
								<td><div id="pieChartCons" style="width:632px;height:300px;border:1px solid #A4BED4;"></div></td>
								<td><div id="usageBarChartCons" style="width:315px;height:300px;border:1px solid #A4BED4;"></div></td>	
                         	</table>
                            </form>
		</div>


	</div>
</div>
</div>