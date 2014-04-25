<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamRecvTpRecvStsInqire.jsp
  * @Description : 수입종류별수입현황조회
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
function GamRecvTpRecvStsInqireModule() {}

GamRecvTpRecvStsInqireModule.prototype = new EmdModule(800, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamRecvTpRecvStsInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#recvTpRecvStsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/rent/gamSelectRecvTpRecvStsInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'요금종류', name:'chrgeKndNm',width:150, sortable:false,align:'center'},
				 {display:'금액', name:'sumNticAmt',width:150, sortable:false,align:'center'},
				 {display:'할인금액', name:'sumDscntAmt',width:150, sortable:false,align:'center'}
                 ],
     usepager: true,
     useRp: true,
     rp: 24,
     showTableToggleBtn: false,
     height: '290',
     preProcess: function(module,data) {
         module.$('#totSumCnt').val(data.totSumCnt);
         module.$('#totSumNickAmt').val(data.totSumNickAmt);
         module.$('#totSumDscntAmt').val(data.totSumDscntAmt);

         module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
         
         return data;
 	}
 });
};

/**
* 정의 된 버튼 클릭 시
*/
GamRecvTpRecvStsInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
         var searchOpt=this.makeFormArgs('#gamRecvTpRecvStsInqireSearchForm');
         this.$('#recvTpRecvStsInqireList').flexOptions({params:searchOpt}).flexReload();

         break;

     case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
         var opts;

         this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
         break;
 }
};

GamRecvTpRecvStsInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamRecvTpRecvStsInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamRecvTpRecvStsInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#recvTpRecvStsInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamRecvTpRecvStsInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamRecvTpRecvStsInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
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
       throw 0;
       break;
   }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamRecvTpRecvStsInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamRecvTpRecvStsInqireSearchForm">
                <table class="searchPanel">
                    <tbody>
                        <tr>
                            <th>수납일</th>
                            <td>
                                <input id="sRcivDtFrom" type="text" class="emdcal" size="5"> ~
                                <input id="sRcivDtTo" type="text" class="emdcal" size="5">
                            </td>
                            <th>업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="5">
                                <input id="sEntrpsNm" type="text" size="8" readonly>
                                <button id="popupEntrpsInfo">업체</button>
                            </td>
                            <td><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리부서</th>
                            <td><input id="mngDeptCd" class="ygpaDeptSelect"></select></td>
                            <th>운영부서</th>
                            <td><input id="operDeptCd" class="ygpaDeptSelect"></select></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">수입종류별수입현황</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="recvTpRecvStsInqireList" style="display:none" class="fillHeight"></table>
<<<<<<< .mine
=======               
>>>>>>> .theirs                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                                   합계
                       				자료수 <input id="totSumCnt" size="15" style="text-align:right;" readonly>
					                        총금액 <input id="totSumNickAmt" class="ygpaNumber" style="text-align:right;" size="15" readonly>
                       				총할인금액 <input id="totSumDscntAmt" type="text" class="ygpaCurrency" style="text-align:right;" size="15" readonly>
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