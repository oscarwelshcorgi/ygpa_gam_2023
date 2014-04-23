<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCntnrQuayEntrpsRentFeeSttusInqire.jsp
  * @Description : 컨테이너부두임대업체별사용료현황조회
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *
  * author domh
  * since 2014.01.14
  *  
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCntnrQuayEntrpsRentFeeSttusInqireModule() {}

GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#cntnrQuayEntrpsRentFeeSttusInqireList").flexigrid({
     module: this,
     url: '<c:url value="/oper/cntnr/gamSelectCntnrQuayEntrpsRentFeeSttusInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'사용년도', name:'usageYear',width:150, sortable:false,align:'center'},
                 {display:'업체코드', name:'',width:150, sortable:false,align:'center'},
                 {display:'업체명', name:'',width:150, sortable:false,align:'center'},
                 {display:'총금액', name:'',width:150, sortable:false,align:'center'},
                 {display:'조회기간별임대료(월별)', name:'',width:200, sortable:false,align:'center'}
                 ],
     showTableToggleBtn: false,
     height: 'auto'
 });

 //로드될 때 사용기간에 오늘날짜 처리
 var today = new Date();
 var month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1); 
 var date = (today.getDate() >= 10) ? today.getDate() : '0' + today.getDate(); 
 var sToday = today.getFullYear() + '-' + month + '-' + date;
 
 this.$('#sGrUsagePdFrom').val(sToday);
 this.$('#sGrUsagePdTo').val(sToday);    

};

this.$("#cntnrQuayEntrpsRentFeeSttusInqireList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    
     
    
});

/**
* 정의 된 버튼 클릭 시
*/
GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
	    // 조회
	    case 'searchBtn':
            if( this.$('#sGrUsagePdFrom').val() == '' ) {
            	alert("사용기간을 선택하십시오.");
            	return;
            }
            
            if( this.$('#sGrUsagePdTo').val() == '' ) {
                alert("사용기간을 선택하십시오.");
                return;
            }
	        var searchOpt=this.makeFormArgs('#gamCntnrQuayEntrpsRentFeeSttusInqireSearchForm');
	        this.$('#cntnrQuayEntrpsRentFeeSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
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
GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	
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

GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamCntnrQuayEntrpsRentFeeSttusInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#cntnrQuayEntrpsRentFeeSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamCntnrQuayEntrpsRentFeeSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCntnrQuayEntrpsRentFeeSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCntnrQuayEntrpsRentFeeSttusInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th>업체명</th>
                            <td><input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button></td>
                            <th>사용기간</th>
                            <td>
                            	 <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">컨테이너부두임대업체별사용료 고지현황</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            <table id="cntnrQuayEntrpsRentFeeSttusInqireList" style="display:none" class="fillHeight"></table>
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