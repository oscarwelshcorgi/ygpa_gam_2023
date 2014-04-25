<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmpyRecvStsInqire.jsp
  * @Description : 업체별수입현황조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.07  heroine          최초 생성
  *
  * author heroine
  * since 2014.02.07
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCmpyRecvStsInqireModule() {}

GamCmpyRecvStsInqireModule.prototype = new EmdModule(800, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamCmpyRecvStsInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#cmpyRecvStsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/rent/gamSelectCmpyRecvStsInqireList.do"/>',
     dataType: 'json',
     colModel : [
				 {display:'업종', name:'induty',width:142, sortable:false,align:'center'},
				 {display:'업체명', name:'entrpsNm',width:280, sortable:false,align:'center'},
                 {display:'업체코드', name:'entrpscd',width:110, sortable:false,align:'center'},
				 {display:'금액', name:'fee',width:160, sortable:false,align:'right', displayFormat:'number'},
                 ],
     usepager: true,
     useRp: true,
     rp: 24,
     showTableToggleBtn: false,
     height: '290',
     preProcess: function(module,data) {
         module.$('#totSumCnt').val(data.totSumCnt);
         module.$('#totSumFee').val(data.totSumFee);

         module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
         
         return data;
	 }
 });
};

/**
* 정의 된 버튼 클릭 시
*/
GamCmpyRecvStsInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamCmpyRecvStsInqireSearchForm');
         this.$('#cmpyRecvStsInqireList').flexOptions({params:searchOpt}).flexReload();

         break;
 }
};

GamCmpyRecvStsInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamCmpyRecvStsInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamCmpyRecvStsInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#cmpyRecvStsInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamCmpyRecvStsInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCmpyRecvStsInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCmpyRecvStsInqireSearchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
                            <th>수납일</th>
                            <td>
                                <input id="sRcivDtFrom" type="text" size="10" class="emdcal"> ~
                                <input id="sRcivDtTo" type="text" size="10" class="emdcal">
                            </td>
                            <td><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <!--
                        <tr>
                            <th>관리부서</th>
                            <td><select id="mngDeptCd"></select></td>
                            <th>운영부서</th>
                            <td><select id="operDeptCd"></select></td>
                        </tr>
                         -->
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <!--
                <li><a href="#tabs1" class="emdTab">자산정보현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산정보현황 상세</a></li>
                 -->

                <li><a href="#tabs1" class="emdTab">업체별수입현황조회</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="cmpyRecvStsInqireList" style="display:none" class="fillHeight"></table>
<<<<<<< .mine
=======               
>>>>>>> .theirs                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                       합계
                       자료수 <input id="totSumCnt" size="15" class="ygpaNumber" style="text-align:right;" readonly>
                       사용료 <input id="totSumFee" class="ygpaCurrency" style="text-align:right;" size="15" readonly>
                               </form>
                            </td>
                            <!-- 
                            <td>
                                <button id="saveNticListBtn">고지의뢰</button>
                                <button id="cancelNticListBtn">고지취소</button>
                            </td>
                            -->
                        </tr>
                    </table>
                       <button id="btnErpAssetCodeListExcelDownload">엑셀</button>
                       <button id="printList" data-flexi-grid="cmpyRecvStsInqireList">인쇄</button>
                </div>
            </div>
    </div>
</div>