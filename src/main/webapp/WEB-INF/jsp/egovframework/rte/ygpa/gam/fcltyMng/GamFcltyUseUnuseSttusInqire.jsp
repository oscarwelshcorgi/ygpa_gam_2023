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
	
/* 	if(params==null) params={action: 'normal'};	// 파라미터 기본 값을 지정한다.

	this._params = params;	// 파라미터를 저장한다.
 */
	// 테이블 설정
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyUseUnuseSttusInqireList.do',
		dataType: "json",
		colModel : [
				{display:"항구분",			name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
				{display:"자산명",			name:"gisAssetsNm",			width:180,		sortable:false,		align:"left"},
				{display:"소재지", 			name:"gisAssetsLnms",		width:200,		sortable:false,		align:"left"},
				{display:"자산면적㎡",		name:"gisAssetsAr",			width:100,		sortable:false,		align:"right"},
				{display:"임대가용면적㎡",	name:"gisAssetsRealRentAr",	width:100, 		sortable:false,		align:"right"},
				{display:"사용면적㎡",	 	name:"usageAr",				width:100, 		sortable:false,		align:"right" },
				{display:"미사용면적㎡", 	name:"noUsageAr",			width:100, 		sortable:false,		align:"right",  displayFormat: 'number'},
				{display:"사용률％", 		name:"usageArPer",			width:65, 		sortable:false,		align:"right" },
				{display:"기간From",			name:"usagePdFrom",				width:80, 		sortable:false,		align:"center"},
				{display:"기간To",			name:"usagePdTo",				width:80, 		sortable:false,		align:"center"  }
			],
			showTableToggleBtn: false,
			height: "200"
				,preProcess : function(module,data) {
				return data;
			}
		
		
	});

	this.$("#mainGrid").on("onItemSelected", function(event, module, row, grid, param) {
		
		var searchOpt = [
						{name: 'gisAssetsPrtAtCode', value: row["gisAssetsPrtAtCode"]},
						{name: 'gisAssetsCd', value: row["gisAssetsCd"]},
						{name: 'gisAssetsSubCd', value: row["gisAssetsSubCd"]},
					/* 	{name: 'searchDtFr', value:module.$("#searchDtFr").val()},
						{name: 'searchDtTo', value:module.$("#searchDtTo").val()}, */
						];				
		console.log(searchOpt);

	module.$("#detailGrid").flexOptions({params:searchOpt}).flexReload();
	});

	// 시설물 사용/미사용 시설 상세
	
    this.$("#detailGrid").flexigrid({
        module: this,
        url: '/fcltyMng/selectFcltyUseUnuseSttusInqireDetailList.do',
        dataType: 'json',
        colModel : [
         			{display:"업체명",			name:"entrpsNm",			width:180,		sortable:false,		align:"left"},
        			{display:"소재지", 			name:"gisAssetsPrtAtCode2",		width:200,		sortable:false,		align:"left"},
    				{display:"자산면적㎡",		name:"computDtls",			width:100,		sortable:false,		align:"right"},
    				{display:"임대가용면적㎡",	name:"gisAssetsAr2",	width:100, 		sortable:false,		align:"right"},
    				{display:"사용면적㎡",	 	name:"usageAr2",				width:100, 		sortable:false,		align:"right" },
    				{display:"사용률％", 		name:"usageArPer2",			width:65, 		sortable:false,		align:"right" },
    				{display:"기간From",			name:"usagePdFrom2",				width:80, 		sortable:false,		align:"center"},
    				{display:"기간To",			name:"usagePdTo2",				width:80, 		sortable:false,		align:"center"  }
                    ],

          showTableToggleBtn: false,
        height: 'auto'
        /* 	,preProcess : function(module,data) {
    			module.$('#totalCount').val(data.totalCount);
    			module.makeDivValues('#emdControlPanelForm', data);
    				return data;
    			} */

    });


};
GamFcltyUseUnuseSttusInqireModule.prototype.onSubmit = function() {
	if(!validateGamFcltyUseUnuseSttusInqire(this.$('#searchForm')[0])){ 		
		return;
	}
	this.loadData();
	};

	//시설목록 로드
	
GamFcltyUseUnuseSttusInqireModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#searchForm");
	this.$("#mainGrid").flexOptions({params:searchOpt}).flexReload();
	};


	
	
GamFcltyUseUnuseSttusInqireModule.prototype.loadDetailData = function() {
		
	var row = this.$('#MainGrid').selectedRows();
		
		if (row.length==0) {
				alert('선택된 항목이 없습니다.');
				this.$("#mainTab").tabs("option", {active: 0});
				return;
			}else if (row.length > 0 ){
		
				this.doAction('/fcltyMng/selectFcltyUseUnuseSttusInqireDetailList.do', row, function(module, result) {
			
				if(result.resultCode == "0"){
					module._usageFcltyVO=result.result;
					module.makeDivValues('#usageFcltyVO', module._usageFcltyVO);
				} else {
					this._cmd="";
					module.initDisplay();
					alert(result.resultMsg);
				}
			});
		}
};

// 탭 변경시 실행

GamFcltyUseUnuseSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
	
	if(newTabId == 'tabs2' && this._cmd == 'modify') {
		this.initDisplay();
		this.$('#tabs2').scrollTop(0);
		this.loadDetailData();
	}else{
		alert("항목을 반드시 선택해주세요.")
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
		<div id="mainTab" class="emdTabPanel fillHeight">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물 사용/미사용시설 조회</a></li>
				<li><a href="#tabs2" class="emdTab">시설물 사용/미사용시설 상세</a></li>
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table class="summaryPanel" style="width:100%;" >
							<tr>
					<tr>
			<th style="font-weight:bold; height:20px;">시설물 사용시설 내역</th>
					</tr>
					</table>
					
				<table id="mainGrid" style="display: none"></table>
				<table class="detailPanel" style="width:100%;">
				<tr>
				<th style="font-weight:bold; height:20px;">시설물 사용시설 상세내역</th>
				</tr>
				</table>
				 <table id="detailGrid" style="display: none" class="fillHeight"></table>
				 
			<div id="emdControlPanel" class="emdControlPanel">
					<form id="emdControlPanelForm">
						<table style="width: 100%;">
							<tr>
								<th style="text-align: center;">자료수</th>
						<td><input type="text" size="8" id="totalCount" class="ygpaNumber" disabled="disabled" /></td>

								<button data-role="showMap" data-gis-layer="gisAssetsCd"
									data-flexi-grid="fcltyUseUnuseSttusInqireList" data-style="default">맵조회</button>
							</tr>
						</table>
					</form>
				</div>
			</div>

           <div id="tabs2" class="emdTabPage" style="overflow:scroll;">
           
                <div class="emdControlPanel">
                    <form id="gamAssetRentForm">
                        <input type="hidden" id="cmd"/>
		
                        <table class="editForm">
                            <tr>
								<th width="10%" height="18">항코드</th>
                                <td>
                                    <input type="text" size="20" id="prtAtCodeStr" title="항코드" disabled/>
                                </td>
								
								<th width="10%" height="18">관리번호</th>
                                <td>
                                    <span id="mngYear">-
                                    <span id="mngNo">-
                                    <span id="mngCnt">
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">최초신청일자</th>
                                <td><input type="text" size="20" id="frstReqstDt" disabled/></td>
								<th width="10%" height="18">신청일자</th>
                                <td><input type="text" class="emdcal" size="14" id="reqstDt" readonly/></td>
								<th width="10%" height="18">신청업체</th>
                                <td>
                                    <input type="text" size="8" id="entrpscd" maxlength="10" readonly/>
                                    <input type="text" size="18" id="entrpsNm" disabled/>
                                    <button id="popupEntrpsInfoInput" class="popupButton">선택</button>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">승낙여부</th>
                                <td>
                                    <select id="prmisnYn" disabled>
                                        <option value="">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N" selected="selected">N</option>
                                    </select>
                                </td>
								<th width="10%" height="18">승낙일자</th>
                                <td><input type="text" size="18" id="prmisnDt" disabled></td>
								<th width="10%" height="18">총사용기간</th>
                                <td>
                                    <input type="text" size="18" id="grUsagePdFrom" disabled/>~
                                    <input type="text" size="18" id="grUsagePdTo" disabled/>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">총사용면적</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="grAr" disabled/>㎡</td>
								<th width="10%" height="18">총사용료</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="grFee" disabled/></td>
								<th width="10%" height="18">총감면사용료</th>
                                <td><input type="text" size="18" class="ygpaNumber" id="grRdcxptFee" disabled/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">납부방법</th>
                                <td>
                                    <input id="payMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM043" />
                                </td>
								<th width="10%" height="18">고지방법</th>
                                <td>
                                    <input id="nticMth" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" />
                                </td>
								<th width="10%" height="18">분납이자율</th>
                                <td>
                                    <input type="text" size="19" id="payinstIntrrate" maxlength="4" size="5"/>
                                    <select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">과세구분</th>
                                <td>
                                    <input id="taxtSe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM016" />
                                </td>
								<th width="10%" height="18">첫회 사용료</th>
                                <td colspan="3">
                                	<input type="text" size="13" id="firstPayVal" class="skipValue" disabled="disabled"/> 원
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">코멘트</th>
                                <td colspan="5">
                                	<input type="text" size="100" id="cmt" maxlength="80"/>
                                	<button id="btnSaveComment">코멘트저장</button>
                                </td>
                            </tr>
                            <tr>
								<th width="10%" height="18">비고</th>
                                <td colspan="5"><input type="text" size="100" id="rm" maxlength="90"/></td>
                            </tr>
                            <tr>
								<th width="10%" height="18">담당자</th>
                                <td>
									<input id="chargerNo" type="hidden" />
									<select id="selectCharger" class="skipValue">
                                        <option value="">선택</option>
                                    </select>
								</td>
								<th width="10%" height="18">담당자 전화</th>
                                <td>
                                	<span id="chargerTlphonNo"></span>
								</td>
								<th width="10%" height="18">휴대전화</th>
                                <td>
                                	
                                    
								</td>
							</tr>
                        </table>
                    </form>


	                 
	
                 </div>
            </div>

		</div>


	</div>
</div>
</div>