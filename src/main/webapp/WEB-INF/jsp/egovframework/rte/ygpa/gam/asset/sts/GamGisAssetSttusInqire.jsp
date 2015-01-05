<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamGisAssetSttusInqire.jsp
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

	// 테이블 설정 //
	this.gridSet("assetSts");

	this.$('#sDataType').on('change', {module:this}, function(e) {
		var module=e.data.module;
		var grid = $(this).val();
		module.gridSet(grid);
	});

	this.$('#cancelDisUseAssetBtn').disable({enableClass:'ui-state-default', disableClass:'ui-state-disable'});

	console.log('debug');
};

GamGisAssetSttusInqireModule.prototype.gridSet = function(grid) {
	if(this._grid==grid) return;
	if(this._grid!=undefined) {
		this.$('.'+this._grid).css('display', 'none');
	}
	this._grid=grid;
	this.$('.'+this._grid).css('display', '');

	//this.$("#gisAssetSttusList").empty();
	switch(grid) {
	case "assetSts":	// 자산 현황
	    this.$("#gisAssetSttusList").flexigrid({
	        module: this,
	        url: '/asset/sts/selectGisAssetSttusInqireList.do',
	        dataType: 'json',
	        colModel : [
	   	                {display:'항구분', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
	                    {display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
	                    {display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
	                    {display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
	                    {display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
	                    {display:'면적', name:'gisAssetsAr',width:70, sortable:false,align:'right', displayFormat: 'number'},
	                    {display:'취득가액', name:'gisAssetsAcqPri',width:110, sortable:false,align:'right', displayFormat: 'number'},
	                    {display:'자산가액', name:'gisAssetsPrice',width:110, sortable:false,align:'right', displayFormat: 'number'},
	                    {display:'준공일자', name:'gisAssetsBldDt',width:90, sortable:false,align:'center'}
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
	        		this.gisAssetsPrice=this.gisAssetsAcqPri*1.2;
	        		this.gisAssetsBldDt="2007-11-08";
	        	});
	        	return data;
	        }
	    });
		break;
	case "usageSts":	//  사용 현황
	    this.$("#gisAssetSttusList").flexigrid({
	        module: this,
	        url: '/asset/sts/selectGisAssetSttusInqireList.do',
	        dataType: 'json',
	        colModel : [
				{display:'항구분', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
				{display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
				{display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
				{display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
				{display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
				{display:'면적', name:'gisAssetsAr',width:70, sortable:false,align:'right', displayFormat: 'number'},
				{display:'사용면적', name:'usageAr',width:110, sortable:false,align:'right', displayFormat: 'number'},
				{display:'미사용면적', name:'unUsageAr',width:110, sortable:false,align:'right', displayFormat: 'number'},
				{display:'사용업체수', name:'usageCmpyNo',width:110, sortable:false,align:'right', displayFormat: 'number'}
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
	        		this.usageCmpyNo=1;
	        	});
	        	return data;
	        }
	    });
		break;
	case "feeSts":	// 사용료 현황
	    this.$("#gisAssetSttusList").flexigrid({
	        module: this,
	        url: '/asset/sts/selectGisAssetSttusInqireList.do',
	        dataType: 'json',
	        colModel : [
				{display:'항구분', name:'gisAssetsPrtAtCodeNm',width:55, sortable:false,align:'center'},
				{display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
				{display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
				{display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
				{display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
				{display:'사용료', name:'grFee',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'임대료', name:'grFee2',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'연체료', name:'grDelayFee',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'감면사용료', name:'rdcxptFee',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'평균요율', name:'usageFeeRate',width:100, sortable:false,align:'right', displayFormat: 'number'}
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
	        		this.usageFeeRate=(this.grFee-this.rdcxptFee)/(this.usageAr*1);
	        		this.grFee2=0;
	        		this.grDelayFee=0;
	        	});
	        	return data;
	        }
	    });
		break;
	case "maintSts":	// 유지보수현황
	    this.$("#gisAssetSttusList").flexigrid({
	        module: this,
	        url: '/asset/sts/selectGisAssetSttusInqireList.do',
	        dataType: 'json',
	        colModel : [
				{display:'항구분', name:'gisAssetsPrtAtCodeNm',width:55, sortable:false,align:'center'},
				{display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
				{display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
				{display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
				{display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
				{display:'사업명', name:'gisAssetsMaintNm',width:70, sortable:false,align:'right'},
				{display:'사업기간', name:'gisAssetsMaintPeriod',width:110, sortable:false,align:'right'},
				{display:'업체명', name:'gisAssetsMaintCmpy',width:110, sortable:false,align:'right'},
				{display:'사업금액', name:'gisAssetsUsageFee',width:110, sortable:false,align:'right', displayFormat: 'number'}
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
	        		this.gisAssetsMaintNm="없음";
	        		this.gisAssetsMaintPeriod="-";
	        		this.gisAssetsMaintCmpy="-";
	        		this.gisAssetsUsageFee=0;
	        	});
	        	return data;
	        }
	    });
		break;
	case "rprSts":	// 하자보수현황
	    this.$("#gisAssetSttusList").flexigrid({
	        module: this,
	        url: '/asset/sts/selectGisAssetSttusInqireList.do',
	        dataType: 'json',
	        colModel : [
				{display:'항구분', name:'gisAssetsPrtAtCodeNm',width:55, sortable:false,align:'center'},
				{display:'자산코드', name:'gisAssetsCode',width:60, sortable:false,align:'center'},
				{display:'자산명', name:'gisAssetsNm',width:160, sortable:false,align:'left'},
				{display:'소재지', name:'gisAssetsLocplc',width:180, sortable:false,align:'left'},
				{display:'지번', name:'gisAssetsLnmCode',width:60, sortable:false,align:'center'},
				{display:'보수건수', name:'gisAssetsRprCnt',width:70, sortable:false,align:'right', displayFormat: 'number'},
				{display:'하자보수업체', name:'gisAssetsRprCmpyNm',width:120, sortable:false,align:'center'},
				{display:'하자보증기간', name:'gisAssetsRprPeriod',width:160, sortable:false,align:'center'},
				{display:'하자보수금액', name:'gisAssetsRprPay',width:100, sortable:false,align:'right', displayFormat: 'number'}
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
	        		this.gisAssetsRprCnt=3;
	        		this.gisAssetsRprCmpyNm="광양마린";
	        		this.gisAssetsRprPeriod="2014-10-04 ~ 2015-10-03";
	        		this.gisAssetsRprPay=12580000;
	        	});
	        	return data;
	        }
	    });
		break;
	}
    this.$("#gisAssetSttusList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
    });
    EMD.util.window_resized($('#'+this._window_id));	// 창 리사이즈
};

GamGisAssetSttusInqireModule.prototype.loadData = function() {
	this.gridSet();
    var searchOpt=this.makeFormArgs('#gamAssetDisUseSearchForm');
    this.$("#gisAssetSttusListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#gisAssetSttusList').flexOptions({params:searchOpt}).flexReload();

}
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
        case 'disUseAssetBtn':
        	this.$('#cancelDisUseAssetBtn').enable({enableClass:'ui-state-default', disableClass:'ui-state-disable'});
        	break;
        case 'cancelDisUseAssetBtn':
        	this.$('#cancelDisUseAssetBtn').disable({enableClass:'ui-state-default', disableClass:'ui-state-disable'});
        	break;
    }
};

GamGisAssetSttusInqireModule.prototype.onSubmit = function() {
    this.loadData();
};

GamGisAssetSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetDisUseSearchForm');
    this.$('#gisAssetSttusList').flexOptions({params:searchOpt}).flexReload();
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamGisAssetSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
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
            <form id="gamAssetDisUseSearchForm">
						<table class="searchPanel">
							<tbody>
							<tr>
								<th>항구분</th>
								<td><input id="searchGisAssetsPrtAtCode" type="text" class="ygpaCmmnCd" data-column-id="gisAssetsPrtAtCode" data-code-id="GAM019" data-default-prompt="전체항" data-display-value="N" size="3"/></td>
								<th>조회항목</th>
								<td colSpan="5">
									<SELECT id="sDataType">
										<option value="assetSts" selected>자산현황</option>
										<option value="usageSts">사용현황</option>
										<option value="feeSts">사용료현황</option>
										<option value="maintSts">유지보수현황</option>
										<option value="rprSts">하자보수현황</option>
									</SELECT>
								</td>
								<td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
							</tr>
							<tr class="assetSts">
								<th>자산명</th>
								<td><input data-column-id="gisAssetsNm" type="text" size="16"></td>
								<th>소재지</th>
								<td><input data-column-id="gisAssetsLocplc" type="text" size="20"></td>
								<th>지번</th>
								<td><input data-column-id="gisAssetsLnm" type="text" size="4">-<input id="searchGisAssetsLnmSub" data-column-id="gisAssetsLnmSub" type="text" size="3"></td>
							</tr>
							<tr class="usageSts" style="display:none;">
								<th>사용 업체</th>
								<td colSpan="2">
									<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
	                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
								</td>
								<th>사용기간</th>
								<td>
									<input id="sGrUsagePdFrom" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sGrUsagePdTo" size="8"> ~
									<input id="sGrUsagePdTo" type="text" class="emdcal" data-role="dtTo" data-dt-from="sGrUsagePdFrom" size="8">
								</td>
							</tr>
							<tr class="feeSts" style="display:none;">
								<th>사용 업체</th>
								<td colSpan="3">
									<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
	                            	<input id="sEntrpsNm" type="text" size="15" disabled="disabled">&nbsp; &nbsp;
	                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
								</td>
								<th>사용기간</th>
								<td>
									<input id="sGrUsagePdFrom" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sGrUsagePdTo" size="8"> ~
									<input id="sGrUsagePdTo" type="text" class="emdcal" data-role="dtTo" data-dt-from="sGrUsagePdFrom" size="8">
								</td>
							</tr>
							<tr class="maintSts" style="display:none;">
								<th>사업명</th>
								<td><input id="sMaintProjNm" type="text" size="12"></td>
								<th>사업기간</th>
								<td colSpan="3">
									<input id="sGrMaintPdFrom" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sGrMaintPdTo" size="8"> ~
									<input id="sGrMaintPdTo" type="text" class="emdcal" data-role="dtTo" data-dt-from="sGrMaintPdFrom" size="8">
								</td>
							</tr>
							<tr class="rprSts" style="display:none;">
								<th>하자보수업체</th>
								<td><input id="sRprCmpyNm" type="text" size="12"></td>
								<th>보증기간</th>
								<td colSpan="3">
									<input id="sRprPdFrom" type="text" class="emdcal" data-role="dtFrom" data-dt-to="sRprPdTo" size="8"> ~
									<input id="sRprPdTo" type="text" class="emdcal" data-role="dtTo" data-dt-from="sRprPdFrom" size="8">
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
				<button class="buttonExcel" data-flexi-grid="erpAssetCodeList" data-url="<c:url value='/asset/selectErpAssetCodeListExcel.do' />">엑셀</button>
				<button id="loadMap" data-grid="assetCodeList">결과 맵 조회</button>
		</div>
     </div>
</div>
