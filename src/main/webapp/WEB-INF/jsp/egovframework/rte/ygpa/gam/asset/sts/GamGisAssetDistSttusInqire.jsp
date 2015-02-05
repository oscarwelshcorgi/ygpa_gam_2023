<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamGisAssetDistSttusInqire.jsp
  * @Description : GIS 통계 시설물 분포 현황 조회
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
function GamGisAssetDistSttusModule() {}

GamGisAssetDistSttusModule.prototype = new EmdModule(840, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamGisAssetDistSttusModule.prototype.loadComplete = function() {

    this.$("#gisAssetSttusList").flexigrid({
        module: this,
        url: '/asset/sts/selectGisAssetDistSttusList.do',
        dataType: 'json',
        colModel : [
        			{display:'', name:'gisFlag', width:24, sortable:false, align:'center', displayFormat: 'jqimg', skipxls:true},
   	                {display:'항구분', name:'gisAssetsPrtAtNm',width:55, sortable:false,align:'center'},
                    {display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
                    {display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
                    {display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
                    {display:'면적', name:'gisAssetsAr',width:70, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'보유시설갯수', groupDisplay:'보유시설수', name:'gisPrtFcltyNm',width:90, sortable:false,align:'center'},
                    {display:'#cspan', groupDisplay:'보유시설수', name:'fcltyCnt',width:100, sortable:false,align:'right', displayFormat: 'number'}
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
        		this._mapLabel = this.gisPrtFcltyNm+'\n'+$.number(this.fcltyCnt)+" 개";
				this.gisFlag=this.gisStat>0?'flag':null;
        	});
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(1, $.number(data.resultSum.sumCnt));
        	module.$("#gisAssetSttusList")[0].dgrid.setFooterLabel(4, $.number(data.resultSum.sumFcltyCnt));
        	return data;
        }
    });
    this.$("#gisAssetSttusList")[0].dgrid.attachHeader('#rspan,#rspan,#rspan,#rspan,#rspan,#rspan,#rspan,시설종류,시설갯수');
    this.$("#gisAssetSttusList")[0].dgrid.attachFooter('조회 수,#cspan,#cspan,0,개,조회 시설 갯수,#cspan,#cspan,0',
    		["text-align:center; vertical-align:middle;","","","text-align:right; vertical-align:middle;","text-align:left; vertical-align:middle;","text-align:center; vertical-align:middle;","","","text-align:right; vertical-align:middle;"]);

    console.log('GamGisAssetDistSttusModule debug');
};

GamGisAssetDistSttusModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetDisUseSearchForm');
    this.$('#gisAssetSttusList').flexOptions({params:searchOpt}).flexReload();

};

GamGisAssetDistSttusModule.prototype.onSelectFeature = function(e) {
	var param=[];
	EMD.gis.closeAllPopup('assetStats');
	param[param.length] = {
			name: 'fcltySe',
			value: this.$('#fcltySe').val()
	};
	param[param.length] = {
			name: 'fcltyCd',
			value: this.$('#fcltyCd').val()
	};
	param = EMD.util.objectToArray(e.feature.attributes).concat(param);
	EMD.gis.openPopup(e.feature, "/asset/sts/gamAssetDistSttusInfo.do", param);
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamGisAssetDistSttusModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {
        // 조회
        case 'searchBtn':
			this.loadData();
            // // console.log('select disuse assets list');
            break;
    }
};

GamGisAssetDistSttusModule.prototype.onSubmit = function() {
    this.loadData();
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamGisAssetDistSttusModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamGisAssetDistSttusModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetDisUseSearchForm">
					<table class="searchPanel">
						<tbody>
						<tr>
							<th>항구분</th>
							<td><input id="gisAssetsPrtAtCode" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrtAtCode" data-code-id="GAM019" data-default-prompt="전체항" data-display-value="N" size="3"/></td>
							<th>위치</th>
							<td>
								<input id="gisAssetsLocCd" type="text" class="ygpaFilterCode" data-url="/cmmn/selectLocCdOptionsList.do" data-filter="gisAssetsPrtAtCode"  data-default-prompt="전체"/>
							</td>
							<th>시설구분</th>
							<td>
								<input id="fcltySe" data-column-id="fcltySe" type="text" class="ygpaCmmnCd" data-code-id="GAM065" data-default-prompt="전체분류 " size="8" data-required="true">
								<input id="fcltyCd" data-column-id="fcltyCd" type="text" class="ygpaFilterCode" data-filter="fcltySe" data-url="/cmmn/selectCmmnCdFilterList.do" data-default-prompt="전체시설 " size="8" data-required="true">
							</td>
							<td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>자산명</th>
							<td><input data-column-id="gisAssetsNm" type="text" size="16"></td>
							<th>소재지</th>
							<td><input data-column-id="gisAssetsLocplc" type="text" size="20"></td>
							<th>지번</th>
							<td><input data-column-id="gisAssetsLnm" type="text" size="4">-<input id="gisAssetsLnmSub" data-column-id="gisAssetsLnmSub" type="text" size="3"></td>
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
				<button data-role="loadStatsMap" data-gis-layer="gisAssetsCd" data-flexi-grid="gisAssetSttusList" data-map-style="value" data-value="fcltyCnt" data-label-field="_mapLabel" data-select-feature="onSelectFeature">결과 맵 조회</button>
				<button data-role="showMap" data-gis-layer="gisAssetsCd" data-flexi-grid="gisAssetSttusList" data-popup-function="onPopupFeature">위치 조회</button>
		</div>
     </div>
</div>
