<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamGisAssetUseSttusInqire.jsp
  * @Description : GIS 자산 통계 현황 조회
  * @Modification Information
  *
  *   수정일		수정자     	수정내용
  *  -------    	--------    	---------------------------
  *  2014.11.24  eunsungj		최초 생성
  *
  * author eunsungj
  * since 2014.11.24
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamGisAssetSttusInqireModule() {}

GamGisAssetSttusInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamGisAssetSttusInqireModule.prototype.loadComplete = function() {

    this.$("#gisAssetSttusList").flexigrid({
        module: this,
        url: '/asset/sts/selectGisAssetUseSttusList.do',
        dataType: 'json',
        colModel : [
   			{display:'', name:'gisFlag', width:24, sortable:false, align:'center', displayFormat: 'jqimg', skipxls:true},
			{display:'순번', name:'rnum',width:55, sortable:false,align:'right'},
			{display:'항구분', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
//			{display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
			{display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
			{display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
			{display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
			{display:'면적', name:'gisAssetsAr',width:70, sortable:false,align:'right', displayFormat: 'number'},
			{display:'업체수', name:'entrpsCnt',width:56, sortable:false,align:'right', displayFormat: 'number'},
			{display:'사용면적', name:'usageAr',width:88, sortable:false,align:'right', displayFormat: 'number'},
			{display:'미사용면적', name:'unUsageAr',width:88, sortable:false,align:'right', displayFormat: 'number'},
			{display:'사용율', name:'useRatePercent',width:88, sortable:false,align:'right', displayFormat: 'number', displayOption: '0.0 %'}
        ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module, data) {
        	$.each(data.resultList, function() {
        		this.gisAssetsCode=this.gisAssetsCd+"-"+this.gisAssetsSubCd;
        		this.gisAssetsLnmCode = this.gisAssetsLnm;
        		if(this.gisAssetsLnmSub!=null && this.gisAssetsLnmSub.length!=0) {
        			this.gisAssetsLnmCode+="-"+this.gisAssetsLnmSub;
        		}
        		this.unUsageAr=this.gisAssetsAr-this.usageAr;

        		this._mapLabel = this.gisAssetsNm;
        		if(this.usageAr*1>0) {
        			this._mapLabel = this._mapLabel+'\n'+$.number(this.usageAr,2)+" m²/"+$.number(this.gisAssetsAr,2)+" m²";
        		}
        		var u, t;
        		u=this.usageAr||0;
        		t=this.gisAssetsAr||0;
        		this.useRate = t!=0?u/t:0;
        		this.useRatePercent = Math.round(this.useRate*100);
				this.gisFlag=this.gisStat>0?'flag':null;

        	});
    		var u, t;
    		u=this.usageAr||0;
    		t=data.resultSum.sumAr||0;
    		var useRate = t!=0?u/t:0;
    		var useRatePercent = Math.round(this.useRate*100);

        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(1, $.number(data.resultSum.sumCnt)+ " 건");
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(2, $.number(data.resultSum.sumSumAr)+ " m<sup>2<sup>");
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(3, $.number(data.resultSum.sumCompCnt));
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(4, $.number(data.resultSum.sumRentAr)+ " m<sup>2<sup>");
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(5, $.number(data.resultSum.sumUsageAr)+ " m<sup>2<sup>");
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(6, $.number(useRatePercent)+ " %");
        	return data;
        }
    });

	this.$('#searchDate').val(EMD.util.getDate());

    //this.$("#gisAssetSttusList")[0].dgrid.attachHeader('#rspan,#rspan,#rspan,#rspan,#rspan,#rspan,#rspan,시설종류,시설갯수');
    this.$("#gisAssetSttusList")[0].dgrid.attachFooter('조회 수,#cspan,#cspan,#cspan,0 건,#cspan,0 m<sup>2</sup>,,0 m<sup>2</sup>,0 m<sup>2</sup>,0 %',
    		["text-align:center; vertical-align:middle;","","","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;","text-align:right; vertical-align:middle;"]);

	//console.log('GamGisAssetSttusInqireModule debug');
};

GamGisAssetSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetUseSearchForm');
    this.$('#gisAssetSttusList').flexOptions({params:searchOpt}).flexReload();

};

GamGisAssetSttusInqireModule.prototype.onSelectFeature = function(e) {
	EMD.gis.closeAllPopup('assetStats');
	var param = this.makeFormArgs('#gamAssetUseSearchForm').concat(EMD.util.objectToArray(e.feature.attributes));
	EMD.gis.openPopup(e.feature, "/asset/sts/gamAssetUseSttusInfo.do", param);
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamGisAssetSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {
        // 조회
        case 'searchBtn':
			this.loadData();
            // // console.log('select disuse assets list');
            break;
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회 조건)
	        this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
	        break;
    }
};

GamGisAssetSttusInqireModule.prototype.onSubmit = function() {
    this.loadData();
};

GamGisAssetSttusInqireModule.prototype.loadData = function() {
	if(this.$('#searchDate').val()==="") {
		alert('조회기준일자는 필수 값입니다.');
		return;
	}
    searchOpt = this.makeFormArgs("#gamAssetUseSearchForm");
    this.$('#gisAssetSttusList').flexOptions({params:searchOpt}).flexReload();
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamGisAssetSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoPopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamGisAssetSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetUseSearchForm">
					<table class="searchPanel">
						<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="gisAssetsPrtAtCode" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrtAtCode" data-code-id="GAM019" data-default-prompt="전체항" data-display-value="N" size="3"/></td>
							<th>위치</th>
							<td>
								<input id="gisAssetsLocCd" type="text" class="ygpaFilterCode" data-url="/cmmn/selectLocCdOptionsList.do" data-filter="gisAssetsPrtAtCode"  data-default-prompt="전체"/>
							</td>
							<td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>사용 업체</th>
							<td>
                            	<input id="sEntrpscd" type="text" size="10">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="15" >&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
							</td>
							<th>조회기간</th>
							<td>
								<input id="searchDateFrom" data-column-id="searchDateFrom" type="text" class="emdcal" size="8" data-required="true">~
								<input id="searchDateTo" data-column-id="searchDateTo" type="text" class="emdcal" size="8" data-required="true">
							</td>
						</tr>
					</tbody>
				</table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
		<table id="gisAssetSttusList" style="display:none; width:100%" class="fillHeight"></table>
		<div class="emdControlPanel">
				<button data-role="gridXlsDown" data-flexi-grid="gisAssetSttusList" data-xls-name="자산현황통계.xls" data-xls-title="자산분포현황통계 조회 목록">엑셀</button>
				<button data-role="clearMap" data-gis-layer="assetStats">결과 맵 초기화</button>
				<button data-role="loadStatsMap" data-gis-layer="gisAssetsCd" data-flexi-grid="gisAssetSttusList" data-map-style="rate" data-value="useRatePercent" data-label-field="_mapLabel" data-select-feature="onSelectFeature">사용현황 맵 조회</button>
				<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="gisAssetSttusList" data-popup-function="onPopupFeature">위치 조회</button>
		</div>
     </div>
</div>
