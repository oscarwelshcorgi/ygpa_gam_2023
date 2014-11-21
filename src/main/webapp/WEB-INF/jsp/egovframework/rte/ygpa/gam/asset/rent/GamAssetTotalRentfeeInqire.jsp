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
     url: '/asset/rent/gamSelectAssetTotalRentfeeInqireList.do',
     dataType: 'json',
     colModel : [
                 {display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                 {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                 {display:'관리번호', name:'rentMngNo',width:80, sortable:false,align:'center'},                 
                 {display:'사용년도', name:'usageYear',width:45, sortable:false,align:'center'},
                 {display:'사용월', name:'usageMt',width:45, sortable:false,align:'center'},
                 {display:'사용분기', name:'usageQu',width:45, sortable:false,align:'center'},
                 {display:'사용순번', name:'assetsUsageSeq',width:45, sortable:false,align:'center'},
                 {display:'사용업체', name:'reqstEntrpsCd',width:60, sortable:false,align:'center'},
                 {display:'사용업체명', name:'reqstEntrpsCdNm',width:120, sortable:false,align:'left'},
                 {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'자산코드', name:'rentGisAssetsCd',width:50, sortable:false,align:'center'},
                 {display:'자산명', name:'gisAssetsNm',width:150, sortable:false,align:'left'},
                 {display:'사용시작일', name:'usagePdFrom',width:80, sortable:false,align:'center'},
                 {display:'사용종료일', name:'usagePdTo',width:80, sortable:false,align:'center'},
                 {display:'감면사용료', name:'rdcxptFee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                 {display:'부두', name:'quayCdNm',width:150, sortable:false,align:'left'},
                 {display:'사용용도', name:'usagePrposCdNm',width:150, sortable:false,align:'left'},
                 {display:'면제구분', name:'exemptSeNm',width:80, sortable:false,align:'left'},
                 {display:'사용조건', name:'usageCndNm',width:80, sortable:false,align:'left'}
                 ],
     showTableToggleBtn: false,
     height: '360',
     preProcess: function(module,data) {
         module.$('#totalResultCnt').val(data.dpTotCnt);
         module.$('#totalFee').val(data.sumFee);
         module.$('#totalRdcxptFee').val(data.sumRdcxptFee);
   
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
	if(serchday < 10){
		serchday = "0" + serchday;
	}
	
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
	    	
	    	if(this.$("#sGrUsagePdFrom").val() == ""){
	    		alert("사용기간 시작일을 입력하세요.");
	    		return;
	    	}
	    	
	    	if(this.$("#sGrUsagePdTo").val() == ""){
	    		alert("사용기간 종료일을 입력하세요.");
	    		return;
	    	}

	    	if(this.$("#sGrUsagePdFrom").val() > this.$("#sGrUsagePdTo").val()){
	    		alert("사용기간 시작일이 사용기간 종료일보다 큽니다.");
	    		return;
	    	}
	    	
	    	var searchOpt=this.makeFormArgs('#gamAssetTotalRentfeeInqireSearchForm');
	        this.$('#assetTotalRentfeeInqireList').flexOptions({params:searchOpt}).flexReload();
	
	        break;
	        
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;      
	    
	    case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
            var opts;

            this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '/popup/showAssetsCd.do', opts);
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
               this.$('#sGisAssetsCd').val(value.gisAssetsCd);
               this.$('#sGisAssetsSubCd').val(value.gisAssetsSubCd);
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
                            <th>항코드</th>
                            <td>
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id=GAM019 />
                            </td>
                            <th>부두</th>
                            <td>
                            	<input id="sQuayCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM003" />
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8" value="<c:out value="${grUsagePdFromStr}"/>" readonly> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8" value="<c:out value="${grUsagePdToStr}"/>" readonly>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        
                        <tr>
                            <th>사용조건</th>
                            <td>
                            	<input id="sUsageCnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM010" />
                            </td>
                            <th>자산코드</th>
                            <td>
                                <input id="sGisAssetsCd" type="text" size="3">&nbsp;-&nbsp; 
                                <input id="sGisAssetsSubCd" type="text" size="3">&nbsp; &nbsp;
                                <button id="popupFcltyCd" class="popupButton">선택</button>
                            </td>
                            <th>사용업체</th>
                            <td>
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="20" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
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
            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetTotalRentfeeInqireList" style="display:none" class="fillHeight"></table>
            	<div class="emdControlPanel">
					<form id="form1">
    	               	<table style="width:100%;" class="summaryPanel">
        	               	<tr>
								<th width="20%" height="20">자료수</th>
								<td><input type="text" size="7" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="20">사용료</th>
								<td><input type="text" size="19" id="totalFee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="20">감면사용료</th>
								<td><input type="text" size="19" id="totalRdcxptFee" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>
            
		</div>
            
    </div>
</div>