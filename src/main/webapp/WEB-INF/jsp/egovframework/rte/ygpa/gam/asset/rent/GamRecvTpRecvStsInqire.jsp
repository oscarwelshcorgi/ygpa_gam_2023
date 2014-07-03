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

GamRecvTpRecvStsInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamRecvTpRecvStsInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#recvTpRecvStsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/rent/gamSelectRecvTpRecvStsInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'요금종류코드', name:'chrgeKnd',width:80, sortable:false,align:'center'},
                 {display:'요금종류명', name:'chrgeKndNm',width:160, sortable:false,align:'left'},
                 {display:'사용료', name:'sumFee',width:120, sortable:false,align:'right', displayFormat:'number'},
                 {display:'부가세', name:'sumVat',width:120, sortable:false,align:'right', displayFormat:'number'},
                 {display:'고지금액', name:'sumNticAmt',width:120, sortable:false,align:'right', displayFormat:'number'},
                 {display:'수납금액', name:'sumRcvdAmt',width:120, sortable:false,align:'right', displayFormat:'number'},
                 {display:'미수납금액', name:'sumNotRcvdAmt',width:120, sortable:false,align:'right', displayFormat:'number'}
                 ],
     usepager: true,
     useRp: true,
     rp: 24,
     showTableToggleBtn: false,
     height: '290',
     preProcess: function(module,data) {
         module.$('#totSumCnt').val(data.totSumCnt);
         module.$('#totSumNticAmt').val(data.totSumNticAmt);
         module.$('#totSumRcvdAmt').val(data.totSumRcvdAmt);
         module.$('#totSumNotRcvdAmt').val(data.totSumNotRcvdAmt);
         return data;
 	}
 });
 
//오늘로 텍스트박스 날짜 정의
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	var searchEndDate = serchYr + "-" + serchMn + "-" + serchday;
	
	today.setMonth(today.getMonth() - 1);
	
	serchYr = today.getFullYear();
	serchMn = today.getMonth() + 1;
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	serchday = today.getDate();
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	
	var searchStartDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sGrUsagePdFrom").val(searchStartDate);
	this.$("#sGrUsagePdTo").val(searchEndDate);

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
                            <th>항구분</th>
                            <td>
                            	<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                            <th>고지업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="30" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>요금종류</th>
                            <td>
								<input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM024 />
								
                            <!-- 
                                <input id="sChrgeKnd" type="text" size="3"> 
                                <input id="sChrgeKndNm" type="text" size="8"> 
                                <button id="popupChrgeKndCd">요금</button>
                             -->
                                         
                            </td>
                            <th>고지기간</th>
                            <td>
                                <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8" value="<c:out value="${grUsagePdFromStr}"/>" readonly> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8" value="<c:out value="${grUsagePdToStr}"/>" readonly>
                            </td>
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
             	<div class="emdControlPanel">
					<form id="form1">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="10%" height="20">자료수</th>
								<td><input type="text" size="8" id="totSumCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">고지금액</th>
								<td><input type="text" size="16" id="totSumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">수납금액</th>
								<td><input type="text" size="16" id="totSumRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">미수납금액</th>
								<td><input type="text" size="16" id="totSumNotRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
								<td><button class="buttonExcel" data-flexi-grid="recvTpRecvStsInqireList" data-url="<c:url value='/asset/rent/gamSelectRecvTpRecvStsInqireListExcel.do' />">엑셀</button></td>
                                <!--
                                <td><button id="btnErpAssetCodeListExcelDownload">엑셀</button></td>
                       			<td><button id="printList" data-flexi-grid="recvTpRecvStsInqireList">인쇄</button></td>
                       			-->
							</tr>
						</table>
					</form>
                       
                </div>
            </div>
        </div>
    </div>
</div>