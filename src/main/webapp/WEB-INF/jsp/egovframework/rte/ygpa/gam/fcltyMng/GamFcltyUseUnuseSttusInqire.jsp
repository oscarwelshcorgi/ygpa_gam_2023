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
        height: '500'
   
    });
};
GamFcltyUseUnuseSttusInqireModule.prototype.onSubmit = function() {
 	if(!validateGamFcltyUseUnuseSttusInqire(this.$('#searchForm')[0])){ 		
		return;
	}
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
		
	this.makeFormValues('#detailForm', data);
};

// 탭 변경시 실행

GamFcltyUseUnuseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	var selectRows = this.$('#mainGrid').selectedRows();
	var row =selectRows[0];
	var detailRows =this.$('#detailGrid').selectedRows();
	var detailRow = selectRows[0];
	
		if(oldTabId == 'tabs1' && (selectRows.length >0) ) {
				this.initDisplay();
				this.$('#tabs2').scrollTop(0);
				this.loadDetailData(row);
		}else if(oldTabId == 'tabs2' &&(detailRows.)))
		
	switch(newTabId) {
	case "tabs1":
		break;
	case "tabs2":

			if (selectRows.length==0) {
					alert('상세내역의 항목을 선택해주세요.');
					this.$("#mainTab").tabs("option", {active: 0});
					return;
		
		}
		break;
	case "tabs3" :
		
		break;
	}
};






	/**
	 * 정의 된 버튼 클릭 시
	 */
GamFcltyUseUnuseSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	
};


//화면 및 데이터 초기화 처리

GamFcltyUseUnuseSttusInqireModule.prototype.initDisplay = function() {
	
	this.$("#usageFcltyVO :input").val("");
	

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
				<li><a href="#tabs2" class="emdTab">시설물 사용/미사용시설 상세</a></li>
				<li><a href="#tabs3" class="emdTab">시설물 사용/미사용시설 업체별 상세</a></li>
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
                    <form id="detailForm">
                        
		
                        <table class="editForm">
                                <tr>
								<th width="10%" height="18">자산코드</th>
                                <td>
                                	<input type="text" size="3" id="gisAssetsPrtAtCode" disabled/>-<input type="text" size="4" id="gisAssetsCd" disabled/>-<input type="text" size="2" id="gisAssetsSubCd" disabled/>
                                    <input type="hidden" id="assetsCdStr"/>
                                </td>
								<th width="10%" height="18">자산면적</th>
                                <td><input type="text" size="21" class="ygpaNumber" id="gisAssetsAr" disabled/>㎡</td>
								<th width="10%" height="18">자산명</th>
                                <td><input type="text" size="44" id="gisAssetsNm" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">지번</th>
                                <td>
                                	<input type="text" size="5" id="gisAssetsLnm" disabled/> -
                                	<input type="text" size="3" id="gisAssetsLnmSub" disabled/>
                                </td>
								<th width="10%" height="18">소재지</th>
                                <td colspan="3"><input type="text" size="83" id="gisAssetsLocplc"  disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">실제임대 가용면적</th>
                                <td><input type="text" size="20" class="ygpaNumber" id="gisAssetsRealRentAr" disabled/>㎡</td>
								<th width="10%" height="18">총 사용면적</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="usageAr" disabled/>㎡</td>
                                <th width="10%" height="18">자산 등록 여부</th>
                                <td><input type="text" size="2"  id="gisAssetsUsageYn" disabled/></td>
                                
                                </tr>

                        </table>
                    </form>


	                 
	
                 </div>
                 
                 <table class="summaryPanel" style="width:100%;">
							<tr>
						<th style="font-weight:bold; height:25px;">업체별 상세 내역</th>
								</tr>
					</table>
                 <table id="detailGrid" style="display: none"></table>
                 
            </div>
			<div id="tabs3" class="emdTabPage" style="overflow: hidden;">
										
							<table class="editForm">			
								<th width="10%" height="18">신청기간</th>
                                <td>
                                	<input type="text" class="emdcal calcInput" size="11" id="usagePdFrom" data-role="dtFrom" data-dt-to="usagePdTo" /> ~
                                	<input type="text" class="emdcal calcInput" size="11" id="usagePdTo" data-role="dtTo" data-dt-from="usagePdFrom" />
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">적용방법</th>
                                <td colspan="3">
                                    <input size="17" id="applcMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM014" value="1"/>
                                </td>
                                <th width="10%" height="18">신청기간 일별 체크</th>
                                <td>
                                	<input type="checkbox" class="calcInput" id="usagePdChk" style="width: 20px;height: 20px; vertical-align: middle;" value="N"/>
                                </td>
                              </tr>
                             <tr class="nationAssetLaw">
                                <th width="10%" height="18">적용요율</th>
                                <td colspan="5">
                                    <!--
                                    <select id="applcTariff">
                                        <option value="" selected="selected">선택</option>
                                    </select>
                                     -->
                                    <input size="23" id="applcTariff" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM023" />
                                    <!--
                                    <input type="text" size="14" id="applcTariffStr" readonly/>
                                     -->
                                    <input type="hidden" id="applcTariffNm"/>
                                </td>
                              </tr>
                            <tr class="nationAssetLaw">
								<th width="10%" height="18">공시지가목록</th>
                                <td colspan="3">
                                    <select id="olnlpList">
                                        <option value="">선택</option>
                                    </select>
                                </td>
								<th width="10%" height="18">공시지가</th>
                                <td><input type="text" size="25" class="ygpaNumber calcInput" id="olnlp" maxlength="13"/></td>
                            </tr>
                            <tr class="tradePortLaw">
                                <th width="10%" height="18">적용단가</th>
                                <td colspan="5"><input type="text" size="25" class="ygpaNumber calcInput" id="applcPrice" data-decimal-point="1" maxlength="13"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제구분</th>
                                <td>
                                    <input size="17" id="exemptSe" class="ygpaCmmnCd calcInput" data-default-prompt="선택" data-code-id="GAM009" data-column-label-id='exemptSeNm'/>
                                </td>
								<th width="10%" height="18">면제기간</th>
                                <td colspan="3">
                                	<input type="text" class="emdcal calcInput" size="11" id="exemptPdFrom" data-role="dtFrom" data-dt-to="exemptPdTo" readonly/> ~
                                	<input type="text" class="emdcal calcInput" size="11" id="exemptPdTo" data-role="dtTo" data-dt-from="exemptPdFrom" readonly/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">면제사유코드</th>
                                <td colspan="3">
                                    <input size="50" id="exemptRsnCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM017" />
                                    <!--
                                    <input type="text" size="15" id="exemptRsnCdStr" readonly/>
                                     -->
                                </td>
								<th width="10%" height="18">면제사유</th>
                                <td><input type="text" size="44" id="exemptRsn" maxlength="95"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">감면사용료</th>
                                <td><input type="text" size="25" class="calcInput" id="rdcxptFee"/></td>
								<th width="10%" height="18">사용료</th>
                                <td colspan="3"><input type="text" size="20" class="ygpaCurrency" id="fee" /></td>
<!-- 								<th width="10%" height="18">부두코드</th>
                                <td>
                                	<input type="text" id="quayCd" size="10" disabled/>
                                	<input type="text" id="quayCdNm" size="32" disabled/>
                                </td> -->
                            </tr>
                            <tr>
								<th width="10%" height="18">산출내역</th>
                                <td colspan="5"><textarea type="text" cols="80" rows="2" id="computDtls" maxlength="200"></textarea></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용목적</th>
                                <td colspan="5"><input type="text" size="100" id="usagePurps" maxlength="200"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">사용내역</th>
                                <td colspan="5"><input type="text" size="100" id="usageDtls" maxlength="45"/></td>
                            </tr>
                            </table>
		</div>


	</div>
</div>
</div>