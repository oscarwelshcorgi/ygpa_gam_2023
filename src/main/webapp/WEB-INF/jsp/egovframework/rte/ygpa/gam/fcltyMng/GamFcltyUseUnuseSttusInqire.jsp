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
		
	 	var searchOpt = [
						{name: 'gisAssetsPrtAtCode', value: row["gisAssetsPrtAtCode"]},
						{name: 'gisAssetsCd', value: row["gisAssetsCd"]},
						{name: 'gisAssetsSubCd', value: row["gisAssetsSubCd"]},
						{name: 'usagePdFrom', value: row["usagePdFrom"]},
						{name: 'usagePdTo', value: row["usagePdTo"]},
						];				
		console.log(searchOpt);
 
 
	module.$("#detailGrid").flexOptions({params:searchOpt}).flexReload();
	
	module.drawChart();
	
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
        			{display:"사용면적㎡",	name:"usageAr",					width:120, 		sortable:false,		align:"right", displayOption:{format:"0,000.00"}},
        			{display:"사용률 %",		name:"usageArPer",				width:120,		sortable:false,		align:"right", displayOption:{format:"0,000.00"}},
        			{display:"사용료 (원)", 		name:"fee",						width:120, 		sortable:false,		align:"right", displayformat:"number"},
    				{display:"기간From",			name:"usagePdFrom",				width:120, 		sortable:false,		align:"center"},
    				{display:"기간To",			name:"usagePdTo",				width:120, 		sortable:false,		align:"center"  }
                    ],

          showTableToggleBtn: false,
        height: '250'
   
    });
    this.$("#detailGrid").on("onItemSelected", function(event, module, row, grid, param) {
    	
    	module.makeFormValues('#detailForm', row);
    	
    });
 	
    this.$("#detailGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
    	
    	module.$("#mainTab").tabs("option", {active: 2});
    	
    });
};
GamFcltyUseUnuseSttusInqireModule.prototype.onSubmit = function() {
/*  	if(!validateGamFcltyUseUnuseSttusInqire(this.$('#searchForm')[0])){ 		
		return;
	}
 */ 	
 	this.loadData();
 	this.$('#detailGrid').flexEmptyData();
	this.$("#mainTab").tabs("option", {active: 0});
	};

	//시설목록 로드
	
GamFcltyUseUnuseSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchForm");
	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
	
	};


	
	
GamFcltyUseUnuseSttusInqireModule.prototype.loadDetailData = function(data) {
	
	this.makeFormValues('#summaryForm', data);
	
};

// 탭 변경시 실행

GamFcltyUseUnuseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	var mainRows = this.$('#mainGrid').selectedRows();
	var detailRows = this.$('#detailGrid').selectedRows();
	
	
	if((oldTabId == 'tabs1') && (mainRows.length > 0)  ) {
		var mainRow = mainRows[0];		
			this.$('#tabs2').scrollTop(0);
			this.loadDetailData(mainRow);
 	}
		
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":
			if (mainRows.length == 0) {
					alert('항목을 선택해주세요.');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
		
		}
		break;
	case "tabs3" : 
			if(mainRows.length == 0){
				this.initDisplay();
				alert('항목을 선택해주세요.');
				this.$("#mainTab").tabs("option", {active: 0});
				return;
			}else if (detailRows.length == 0){
				
				alert('상세 내역을 선택해주세요.');
				this.$('#mainTab').tabs("option",{active: 1});
				return;
			}
		break;	
	}
};














GamFcltyUseUnuseSttusInqireModule.prototype.drawChart = function(module, result) {
	console.log(result);
	
	if(result.resultCode == 0){
		
		for (var i=0; i<12; i++) {
			grHseEmitQy=result.resultList[i]['grHseEmitQy']*1;
			grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
			if (maxGrHseEmitQy<grHseEmitQy) {
				maxGrHseEmitQy=grHseEmitQy;
			}
		};
	} else {
		for (var i=0; i<12; i++) {
			grHseEmitQy=0;
			grHseEmitQyArr[i]={month: (i+1), gauge: grHseEmitQy};
		};
	}
	if (maxGrHseEmitQy<10) {
		maxGrHseEmitQy=10;
	}
	if (module.barChart==null) {
	module.barChart = new dhtmlXChart({
		view			: "bar",
		container		: module.$('#detailChart')[0],
		value			: "#gauge#",
		color			: "#000BE0",
        gradient		: "rising",
		width			: 30,
		label			: "#gauge#",
		tooltip			: "#gauge# (KgCO2)",
		xAxis			: {
			title 		: "온실가스 배출 현황",
			template	: "#month# 월"
		},
		yAxis			: {
			start		: 0,
			end			: maxGrHseEmitQy + 10,
			step		: Math.ceil(maxGrHseEmitQy / 10),
			title		: "온실가스 배출량,KgCO2"
		}
	});
} else {
	module.barChart.clearAll();
	module.barChart.define("yAxis", {
		start : 0,
		end : maxGrHseEmitQy + 10,
		step : Math.ceil(maxGrHseEmitQy / 10),
		title : "온실가스 배출량,KgCO2"
	});
}
module.barChart.parse(grHseEmitQyArr, "json");
module.barChart.refresh();

};






	/**
	 * 정의 된 버튼 클릭 시
	 */
GamFcltyUseUnuseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	
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
                              <td colspan="2"><button class="buttonSearch">조회</button></td>
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
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" >
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용/미사용시설 조회</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 사용/미사용시설 업체별 상세</a></li>
				<li><a href="#tabs3" class="emdTab">임대 상세 내역</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display: none" class="fillHeight"></table>
				 
			<div id="emdControlPanel" class="emdControlPanel">
					<form id="emdControlPanelForm">
						<table style="width: 100%;" class="summaryPanel">
							<tr>
								<th width="15%" height="25">자료수</th>
						<td><input type="text" size="8" id="dataCount" class="ygpaNumber" disabled="disabled" /></td>
						<th width="15%" height="25">총 자산면적㎡</th>
						<td><input type="text" size="24" id="sumAssetsAr" class="ygpaNumber" disabled="disabled" /> ㎡</td>
						<th width="15%" height="25">총 사용면적㎡</th>
						<td><input type="text" size="24" id="sumUsageAr" class="ygpaNumber" disabled="disabled" /> ㎡</td>
							</tr>
						</table>
						<table style="width:100%;">
	                        <tr>
	                        <button data-role="showMap" data-gis-layer="gisAssetsCd"
									data-flexi-grid="fcltyUseUnuseSttusInqireList" data-style="default">맵조회</button>
	                        
	                        
	                        </tr>
						</table>
					</form>
				</div>
			</div>

           <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
               <div class="emdControlPanel">
                    <form id="summaryForm">
                        
		
                        <table class="editForm">
                                <tr>
                                <th width="10%" height="18">항구분</th>
                                <td><input type="text" size="10" id="prtAtCodeNm2" disabled/> </td>
								<th width="10%" height="18">자산코드</th>
                                <td>
                               <input type="text" size="3" id="gisAssetsPrtAtCode" disabled/>-<input type="text" size="4" id="gisAssetsCd" disabled/>-<input type="text" size="2" id="gisAssetsSubCd" disabled/>
                                </td>
								<th width="10%" height="18">자산명</th>
                                <td><input type="text" size="21" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="gisAssetsAr" disabled/>㎡</td>
								
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
                                <td><input type="text" size="20" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/>㎡</td>
								<th width="10%" height="18">총 사용면적</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="usageAr" disabled/>㎡</td>
                                <th width="10%" height="18">총 사용률</th>
                                <td><input type="text" size="7"  id="usageArPer" disabled/> %</td>
                                </tr>

                        </table>
                    </form>


	                 
	
                 </div>
                 
                 <table class="summaryPanel" style="width:100%;">
							<tr>
						<th style="font-weight:bold; height:15px;">업체별 상세 내역</th>
								</tr>
					</table>
                 <table id="detailGrid" style="display: none"></table>
           
                <td rowspan="10" style="padding-left:4px;">
				<div id="detailChart" style="width:412px;height:310px;border:1px solid #A4BED4;"></div>
					</td> 
            </div>
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">
						<form id="detailForm">				
							<table class="editForm" style="width : 100%">			
							<tr>	
								<th width="10%" height="18">업체명 </th>
								<td><input type="text" size="25" id="entrpsNm" disabled/></td>
								<th width="10%" height="18">신청기간</th>
                                <td colspan="3">
                                	<input type="text" size="11" id="usagePdFrom" disabled/> ~
                                	<input type="text"size="11" id="usagePdTo" disabled/>
                                </td>
       					                      
                            </tr>
                                <tr>
                                <th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="15" class="ygpaNumber" id="gisAssetsAr" disabled/>㎡</td>
							
								<th width="10%" height="18">실제임대 가용면적</th>
                                <td><input type="text" size="15" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/>㎡</td>
							
								<th width="10%" height="18">사용면적</th>
                                <td><input type="text" size="15" class="ygpaNumber" id="usageAr" disabled/>㎡</td>
                                </tr>
                            <tr>
                    <tr>
								<th width="10%" height="18">적용방법</th>
                                <td colspan="5">
                                    <input size="17" id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM014" value="1" disabled/>
                                </td>
                             
                              </tr>
                             <tr class="nationAssetLaw">
                                <th width="10%" height="18">적용요율</th>
                                <td colspan="5">
                                  
                                    <input size="23" id="applcTariff" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM023" disabled/>
                                       </td>
                              </tr>
                            <tr >
									<th width="10%" height="18">공시지가</th>
                                <td colspan="2"><input type="text" size="25" class="ygpaNumber calcInput" id="olnlp"  disabled/>원</td>
                            
                                <th width="10%" height="18">적용단가</th>
                                <td colspan="3"><input type="text" size="25" class="ygpaNumber calcInput" id="applcPrice" data-decimal-point="1"  disabled/>원</td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제구분</th>
                                <td>
                                    <input size="17" id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM009" data-column-label-id='exemptSeNm' disabled/>
                                </td>
								<th width="10%" height="18">면제기간</th>
                                <td colspan="3">
                                	<input type="text"  size="11" id="exemptPdFrom"  disabled/> ~
                                	<input type="text"  size="11" id="exemptPdTo"disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제사유코드</th>
                                <td colspan="3">
                                    <input size="50" id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM017" disabled/>
                                </td>
								<th width="10%" height="18">면제사유</th>
                                <td><input type="text" size="24" id="exemptRsn"  disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">감면사용료</th>
                                <td><input type="text" size="25" class="calcInput" id="rdcxptFee" disabled/></td>
								<th width="10%" height="18">사용료</th>
                                <td colspan="3"><input type="text" size="20" class="ygpaCurrency" id="fee" disabled/>원</td>

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
                            </form>
		</div>


	</div>
</div>
</div>