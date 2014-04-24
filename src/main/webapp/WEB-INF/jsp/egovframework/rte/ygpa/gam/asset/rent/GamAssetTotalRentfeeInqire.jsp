<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetTotalRentfeeInqire.jsp
  * @Description : 자산별사용료현황조회
  * @Modification heroine
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.14
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetTotalRentfeeInqireModule() {}

GamAssetTotalRentfeeInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetTotalRentfeeInqireModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#assetTotalRentfeeInqireList").flexigrid({
     module: this,
     url: '<c:url value="/asset/rent/gamSelectAssetTotalRentfeeInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'사용년도', name:'usageYear',width:150, sortable:false,align:'center'},
                 {display:'관리번호', name:'mngNo',width:150, sortable:false,align:'center'},
                 {display:'횟수', name:'mngCnt',width:150, sortable:false,align:'center'},
                 {display:'자산코드', name:'gisAssetsCd',width:150, sortable:false,align:'center'},
                 {display:'자산명', name:'gisAssetsCdNm',width:150, sortable:false,align:'center'},
                 {display:'업체코드', name:'reqstEntrpsCd',width:150, sortable:false,align:'center'},
                 {display:'업체명', name:'reqstEntrpsCdNm',width:150, sortable:false,align:'center'},
                 {display:'사용시작일', name:'usagePdFrom',width:150, sortable:false,align:'center'},
                 {display:'사용료', name:'fee',width:150, sortable:false,align:'center'},
                 {display:'면제', name:'exemptSe',width:150, sortable:false,align:'center'},
                 {display:'수정자', name:'updUsr',width:150, sortable:false,align:'center'},
                 {display:'수정일시', name:'updtDt',width:150, sortable:false,align:'center'}
                 ],
     showTableToggleBtn: false,
     height: '300',
     preProcess: function(module,data) {
         module.$('#totalResultCnt').val(data.dpTotCnt);
         module.$('#totalFee').val(data.sumFee);
   
         return data;
     }
 });
 
	//오늘 날짜로 사용기간 설정 처리
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	
	var serchday = today.getDate();
	
	var displayDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sGrUsagePdFrom").val(displayDate);
	this.$("#sGrUsagePdTo").val(displayDate);
};

this.$("#assetTotalRentfeeInqireList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");

});

/**
* 정의 된 버튼 클릭 시
*/
GamAssetTotalRentfeeInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
	    // 조회
	    case 'searchBtn':
	    	
	    	if(this.$("#sGrUsagePdFrom").val() == ""){
	    		alert("사용기간 시작일을 입력하세요.");
	    		return;
	    	}
	    	
	        var searchOpt=this.makeFormArgs('#gamAssetTotalRentfeeInqireSearchForm');
	        this.$('#assetTotalRentfeeInqireList').flexOptions({params:searchOpt}).flexReload();
	
	        break;
	        
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;      
	    
	    case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;

            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
            break;    
            
	}
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetTotalRentfeeInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	   case 'selectEntrpsInfoPopup':
	       if (msg != 'cancel') {
	           this.$('#sEntrpscd').val(value.entrpscd);
	           this.$('#sEntrpsNm').val(value.entrpsNm);
	       } else {
	           alert('취소 되었습니다');
	       }
	       break;
	       
	   case 'selectAssetsCdRentPopup':
           if (msg != 'cancel') {
               this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
               this.$('#gisAssetsCd').val(value.gisAssetsCd);
               this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
               this.$('#gisAssetsNm').val(value.gisAssetsNm);
               this.$('#assetsCdStr').val(value.gisAssetsCd + "-" + value.gisAssetsSubCd); 
           } else {
               alert('취소 되었습니다');
           }
           break;    
	       
	}     
};

GamAssetTotalRentfeeInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetTotalRentfeeInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetTotalRentfeeInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetTotalRentfeeInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetTotalRentfeeInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetTotalRentfeeInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetTotalRentfeeInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 80px">항코드</th>
                            <td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th style="width: 80px">업체명</th>
                            <td><input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button></td>
                            <th style="width: 80px">사용기간</th>
                            <td><input id="sGrUsagePdFrom" type="text" class="emdcal" size="8"> ~ <input id="sGrUsagePdTo" type="text" class="emdcal" size="8"></td>
                            <td rowSpan="3"><button id="searchBtn" *class="submit" class="buttonSearch">조회</button></td>
                        </tr>
                        
                        <tr>
                            <th>재산구분</th>
                            <td><input id="sGisAssetsPrprtySeCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM001" /></td>
                            <th>위치</th>
                            <td><input id="sGisAssetsLocCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM002" /></td>
                            <th>부두</th>
                            <td><input id="sGisAssetsQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM003" /></td>
                        </tr>    
                        
                        <tr>
                            <th>자산코드</th>
                            <td colspan="5"><input type="hidden" id="gisAssetsPrtAtCode"/><input type="text" size="3" id="gisAssetsCd" readonly/>-<input type="text" size="2" id="gisAssetsSubCd" readonly/>
                                <input type="hidden" id="assetsCdStr"/><input type="text" size="20" id="gisAssetsNm" disabled/>
                                <button id="popupFcltyCd" class="popupButton">자산조회</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산별사용료현황</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            	<table id="assetTotalRentfeeInqireList" style="display:none"></table>
            	<div class="emdControlPanel">
                    <table style="width:100%;">
                        <tr>
                            <td>
                               <form id="form1">
                                   합계 : 
                                   자료수 <input id="totalResultCnt" size="15" style="text-align:right;" readonly>
                                   사용료합계 <input id="totalFee" type="text" size="15" style="text-align:right;" readonly>원
                               </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
		</div>
            
    </div>
</div>