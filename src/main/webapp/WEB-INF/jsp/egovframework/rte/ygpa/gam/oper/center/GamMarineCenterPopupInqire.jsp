<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMarineCenterPopupInqire.jsp
  * @Description : 마린센터 정보현황알림
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.06.17  sj          최초 생성
  *
  * author sj
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetLndValInqireModule() {}

GamAssetLndValInqireModule.prototype = new EmdModule(585, 285);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetLndValInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#assetLndValInqireList").flexigrid({
     module: this,
     url: '<c:url value="/oper/center/gamMarineCenterPopupInqire.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'GIS 자산 면적', name:'gisAssetsAr',width:100, sortable:false,align:'center'},
                 {display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
                 {display:'면적대비 공시지가', name:'arOlnlp',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 명', name:'gisAssetsNm',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 소재지', name:'gisAssetsLocplc',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 지번', name:'gisAssetsLnm',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 사용 여부', name:'gisAssetsUsageYn',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 취득가액', name:'gisAssetsAcqPri',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 규격', name:'gisAssetsStndrd',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 준공년도', name:'gisAssetsBlddate',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 준공 일자', name:'gisAssetsBldDt',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 비고', name:'gisAssetsRm',width:100, sortable:false,align:'center'},
                 {display:'GIS 자산 실제 임대 면적', name:'gisAssetsRealRentAr',width:100, sortable:false,align:'center'},
                 {display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},
                 {display:'등록일자', name:'registdt',width:100, sortable:false,align:'center'},
                 {display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},
                 {display:'수정일자', name:'updtdt',width:100, sortable:false,align:'center'},
                 ],
     usepager: true,
     useRp: true,
     rp: 24,
     showTableToggleBtn: false,
     height: '290'
 });
 this.$('#chk').on('click', {module: this}, function(event) {
		var d = new Date();
		d.setDate(d.getDate()+1);
		d.setHours(0);
	  	document.cookie = "ygpa_popup_c7=/oper/center/gamMarineCenterPopupInqire.do; expires=" +d.toUTCString();
	  	event.data.module.closeWindow();
	});
};

/**
* 정의 된 버튼 클릭 시
*/
GamAssetLndValInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
         this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();

         break;

     case 'btnMarineCenterRent':
    	 EMD.util.create_window('마린센터  목록 관리', '<c:url value="/oper/center/gamMarineCenterRentMngt.do"/>', null);
    	 break;
     case 'btnMarineCenterFeeMngt':
    	 EMD.util.create_window('마린센터  사용료 관리', '<c:url value="/oper/center/gamMarineCenterRentFeeMngt.do"/>', null);
    	 break;
     case 'btnMarineCenterRentFeePayDtlsMngt':
    	 EMD.util.create_window('마린센터  사용료납부관리', '<c:url value="/oper/center/gamMarineCenterRentNticMngt.do"/>', null);
    	 break;
     case 'btnMarineCenterUseExprInqire':
    	 EMD.util.create_window('마린센터  만기도래자료조회', '<c:url value="/oper/center/gamMarineCenterUseExprInqire.do"/>', null);
    	 break;
 }
};

GamAssetLndValInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetLndValInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetLndValInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetLndValInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetLndValInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetLndValInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">



    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <!--
                <li><a href="#tabs1" class="emdTab">자산정보현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산정보현황 상세</a></li>
                 -->

                <li><a href="#tabs1" class="emdTab">항만시설정보현황알림</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!--
                <div style="width: 100%; height: 100%; overflow:auto">
                        <table id="assetLndValInqireList" style="display:none"></table>
                </div>
                -->


                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <form id="form1">
                        <tr>
                            <th>
                                * 시설임대/사용신청 건수
                            </th>
                            <td>
                                <input id="totalResultCnt1" size="10" readonly value="<c:out value="${prmisnYnCnt}"/>">
                            </td>
                            <td>
                                <button id="btnMarineCenterRent">마린센터  목록 관리</button>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                * 고지도래자료 건수
                            </th>
                            <td>
                                <input id="totalResultCnt2" size="10" readonly value="<c:out value="${nticPdCnt}"/>">
                            </td>
                            <td>
                                <button id="btnMarineCenterFeeMngt">마린센터  사용료고지 관리</button>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                * 미수납자료 건수
                            </th>
                            <td>
                                <input id="totalResultCnt3" size="10" readonly value="<c:out value="${nhtIsueCnt}"/>">
                            </td>
                            <td>
                                <button id="btnMarineCenterRentFeePayDtlsMngt">마린센터  사용료납부관리</button>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                * 계약만료자료 건수
                            </th>
                            <td>
                                <input id="totalResultCnt4" size="10" readonly value="<c:out value="${rcivSeCnt}"/>">
                            </td>
                            <td>
                                <button id="btnMarineCenterUseExprInqire">마린센터  만기도래자료조회</button>
                            </td>
                        </tr>
                        </form>
                    </table>
                </div>
				<form>
					<div align="right">
						하루 동안 알람 창 닫기 <input type="checkbox" id="chk">
					</div>
				</form>
            </div>
    </div>
</div>