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

GamCmpyRecvStsInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamCmpyRecvStsInqireModule.prototype.loadComplete = function() {

 // 테이블 설정 //
 this.$("#cmpyRecvStsInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/rent/gamSelectCmpyRecvStsInqireList.do"/>',
     dataType: 'json',
     colModel : [
				 {display:'업체코드', name:'entrpscd',width:80, sortable:false,align:'center'},
				 {display:'업체명', name:'entrpsNm',width:160, sortable:false,align:'left'},
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
    	 module.$('#totalResultCnt').val(data.dpTotCnt);
         module.$('#sumRcvdAmtSum').val(data.sumRcvdAmtSum);
         module.$('#sumNticAmtSum').val(data.sumNticAmtSum);
         module.$('#sumNotRcvdAmtSum').val(data.sumNotRcvdAmtSum);

         module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
         
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
GamCmpyRecvStsInqireModule.prototype.onButtonClick = function(buttonId) {

 switch(buttonId) {

     // 조회
     case 'searchBtn':
    	 
    	 if(this.$("#sGrUsagePdFrom").val() == ""){
    		 alert("고지기간 시작일을 입력하세요.");
    		 return;
    	 }
    	 
    	 if(this.$("#sGrUsagePdTo").val() == ""){
    		 alert("고지기간 종료일을 입력하세요.");
    		 return;
    	 }
    	 
         var searchOpt=this.makeFormArgs('#gamCmpyRecvStsInqireSearchForm');
         this.$('#cmpyRecvStsInqireList').flexOptions({params:searchOpt}).flexReload();

         break;
         
      // 팝업을 호출한다.(업체)     
         
     case 'popupEntrpsInfo': 
         var opts;

         this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
         break;
 }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamCmpyRecvStsInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
  switch (popupId) {
  case 'selectEntrpsInfoFeePayPopup':
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
                            <th style="width: 70px">항구분</th>
                            <td style="width: 320px">
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
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
                            	<input id="sGrUsagePdFrom" type="text" class="emdcal" size="10">
                            	 ~ 
                            	<input id="sGrUsagePdTo" type="text" class="emdcal" size="10">
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
                <!--
                <li><a href="#tabs1" class="emdTab">자산정보현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산정보현황 상세</a></li>
                 -->

                <li><a href="#tabs1" class="emdTab">업체별수입현황조회</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="cmpyRecvStsInqireList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
					<form id="form1">
						<table style="width:100%;" class="summaryPanel">
							<tr>
								<th width="10%" height="20">자료수</th>
								<td><input type="text" size="10" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">고지금액</th>
								<td><input type="text" size="18" id="sumNticAmtSum" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">수납금액</th>
								<td><input type="text" size="18" id="sumRcvdAmtSum" class="ygpaNumber" disabled="disabled" /></td>
								<th width="10%" height="20">미수납금액</th>
								<td><input type="text" size="18" id="sumNotRcvdAmtSum" class="ygpaNumber" disabled="disabled" /></td>
                                <td><button id="btnErpAssetCodeListExcelDownload">엑셀</button></td>
                                <!--
                       			<td><button id="printList" data-flexi-grid="cmpyRecvStsInqireList">인쇄</button></td>
                       			-->
							</tr>
						</table>
					</form>
                       
                </div>
            </div>
	    </div>
    </div>
</div>