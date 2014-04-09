<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetTypeValueSts.jsp
  * @Description : 자산종류별자산가치통계조회
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
function GamAssetTypeValueStsModule() {}

GamAssetTypeValueStsModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamAssetTypeValueStsModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#assetTypeValueStsList").flexigrid({
     module: this,
     url: '<c:url value="/asset/gamSelectAssetTypeValueStsList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'상각연도', name:'deprctnYear',width:150, sortable:false,align:'center'},
                 {display:'출자구분', name:'gisAssetsInvstmntMthd',width:150, sortable:false,align:'center'},
                 {display:'자산구분', name:'gisAssetsSeCdNm',width:180, sortable:false,align:'center'},
                 {display:'재평가금액', name:'revalAmt',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'당기자산증가금액', name:'thisTermIncreAmt',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'대차대조기말현재금액', name:'bsThisCurAmt',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'대차대조전기말상각누계금액', name:'bsPreDeprctnSum',width:180, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'대차대조미상각잔액', name:'bsNoDeprctnBal',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'전기말자본적지출금액누계', name:'preEndAssetBuySum',width:180, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'자본적지출금액', name:'assetBuyAmt',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'당기상각금액', name:'thisTermDeprctnAmt',width:150, sortable:false,align:'center', displayFormat: 'number'},
                 {display:'잔존금액', name:'curAmt',width:150, sortable:false,align:'center', displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: 'auto'
 });
};

this.$("#assetTypeValueStsList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    
     
    
});

/**
* 정의 된 버튼 클릭 시
*/
GamAssetTypeValueStsModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
	    // 조회
	    case 'searchBtn':
	        var searchOpt=this.makeFormArgs('#gamAssetTypeValueStsSearchForm');
	        this.$('#assetTypeValueStsList').flexOptions({params:searchOpt}).flexReload();
	
	        break;
	        
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;      
	        
	}
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetTypeValueStsModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	   case 'selectEntrpsInfoPopup':
	       if (msg != 'cancel') {
	           this.$('#sEntrpscd').val(value.entrpscd);
	           this.$('#sEntrpsNm').val(value.entrpsNm);
	       } else {
	           alert('취소 되었습니다');
	       }
	       break;
	}     
};

GamAssetTypeValueStsModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamAssetTypeValueStsModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamAssetTypeValueStsSearchForm');
 //this.showAlert(searchOpt);
 this.$('#assetTypeValueStsList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetTypeValueStsModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetTypeValueStsModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetTypeValueStsSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width:70px;">상각연도</th>
                            <td style="width:100px;">
                                <!-- <input id="sDeprctnYear" type="text" size="2">  -->
                                <select id="sDeprctnYear">
                                    <option value="">선택</option>
                                    <c:forEach items="${yearList}" var="yearListItem">
                                        <option value="${yearListItem.code }" <c:if test="${yearListItem.code == thisYear}">selected</c:if> >${yearListItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                                
                            </td>
                            <td><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산종류별자산가치통계</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            <table id="assetTypeValueStsList" style="display:none" class="fillHeight"></table>
            <!-- 
            <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td style="text-align: right">
                               <form id="form1">
                                   사용료 <input id="totalResultCnt" class="ygpaNumber" style="text-align:right;" size="15" readonly>
                                   감면사용료 <input id="totalResultRdcCnt" type="text" class="ygpaCurrency" style="text-align:right;" size="15" readonly>
                               </form>
                            </td>
                        </tr>
                     </table>
            </div>
             -->
		</div>
            
    </div>
</div>