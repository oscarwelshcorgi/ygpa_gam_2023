<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamHtldPdRentFeeSttusInqire.jsp
  * @Description : 배후단지임대기간별사용료현황조회
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
function GamHtldPdRentFeeSttusInqireModule() {}

GamHtldPdRentFeeSttusInqireModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamHtldPdRentFeeSttusInqireModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#htldPdRentFeeSttusInqireList").flexigrid({
     module: this,
     url: '<c:url value="/oper/htld/gamSelectHtldPdRentFeeSttusInqireList.do"/>',
     dataType: 'json',
     colModel : [
                 {display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},
				 {display:'항코드명', name:'prtKorNm',width:120, sortable:false,align:'center'},
                 {display:'사용년도', name:'usageYear',width:100, sortable:false,align:'center'},
                 {display:'분기', name:'usageQu',width:100, sortable:false,align:'center'},
                 {display:'월', name:'usageMt',width:100, sortable:false,align:'center'},
                 {display:'사용료', name:'sumTotalFee',width:150, sortable:false,align:'right' , displayFormat: 'number'},
                 {display:'감면사용료', name:'sumTotalRdcxptFee',width:150, sortable:false,align:'right' , displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: '350',
     preProcess: function(module,data) {
         module.$('#totalResultCnt').val(data.dpTotCnt);
         module.$('#sumTotalFeeSum').val(data.sumTotalFeeSum);
         module.$('#sumTotalRdcxptFeeSum').val(data.sumTotalRdcxptFeeSumSum);
   
         return data;
     }
 });
 
	//전월로 셀렉트박스 날짜 정의
	var today = new Date();
	
	var toMonth = today.getMonth();
	today.setDate(1);
	today.setMonth(toMonth - 1);
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;

	this.$("#serchStartYr").val(serchYr);
	this.$("#serchStartMn").val(serchMn);
	this.$("#serchEndYr").val(serchYr);
	this.$("#serchEndMn").val(serchMn);    
};

this.$("#htldPdRentFeeSttusInqireList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    
     
    
});

/**
* 정의 된 버튼 클릭 시
*/
GamHtldPdRentFeeSttusInqireModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
	    // 조회
	    case 'searchBtn':
	    	if( this.$('#serchStartYr').val() == '' ) {
            	alert("사용기간 시작년을 선택하십시오.");
            	return;
            }
            
            if( this.$('#serchStartMn').val() == '' ) {
                alert("사용기간 시작월을 선택하십시오.");
                return;
            }
	        var searchOpt=this.makeFormArgs('#gamHtldPdRentFeeSttusInqireSearchForm');
	        this.$('#htldPdRentFeeSttusInqireList').flexOptions({params:searchOpt}).flexReload();
	
	        break;
	        
	     // 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisCodePopup", "자산코드", '<c:url value="/popup/showAssetsCd.do"/>', {});
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
GamHtldPdRentFeeSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	
		// 자산코드 조회
		case "searchGisCodePopup":
			this.$("#searchAssetsCd").val(value["gisAssetsCd"]);
			this.$("#searchAssetsSubCd").val(value["gisAssetsSubCd"]);
		break;	
	
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

GamHtldPdRentFeeSttusInqireModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamHtldPdRentFeeSttusInqireModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamHtldPdRentFeeSttusInqireSearchForm');
 //this.showAlert(searchOpt);
 this.$('#htldPdRentFeeSttusInqireList').flexOptions({params:searchOpt}).flexReload();
};

GamHtldPdRentFeeSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamHtldPdRentFeeSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldPdRentFeeSttusInqireSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th>업체코드</th>
                            <td><input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;<input id="sEntrpsNm" type="text" size="30" disabled="disabled">&nbsp; &nbsp;<button id="popupEntrpsInfo">업체</button></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" title="검색조건" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" />&nbsp; &nbsp;
								<button id="searchPopupBtn">자산</button>
							</td>
                            <th>사용기간</th>
                            <td>
                            	 <select id="serchStartYr">
                                    <option value="" selected="selected">년도</option>

                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem }">${yearItem }</option>
                                    </c:forEach>
                                </select>
                                <select id="serchStartMn">
                                    <option value="" selected="selected">월</option>

                                    <c:forEach  items="${monthsList}" var="monthsItem">
                                        <option value="${monthsItem }">${monthsItem }</option>
                                    </c:forEach>
                                </select> ~ 
                                <select id="serchEndYr">
                                    <option value="" selected="selected">년도</option>

                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem }">${yearItem }</option>
                                    </c:forEach>
                                </select>
                                <select id="serchEndMn">
                                    <option value="" selected="selected">월</option>

                                    <c:forEach  items="${monthsList}" var="monthsItem">
                                        <option value="${monthsItem }">${monthsItem }</option>
                                    </c:forEach>
                                </select>
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
                <li><a href="#tabs1" class="emdTab">배후단지임대기간별사용료 고지현황</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            <table id="htldPdRentFeeSttusInqireList" style="display:none" class="fillHeight"></table>
            
            <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                   합계 : 
                                   자료수 <input id="totalResultCnt" size="15" style="text-align:right;" readonly> 
                                   사용료 <input id="sumTotalFeeSum" class="ygpaNumber" style="text-align:right;" size="15" readonly>원 
                                   감면사용료 <input id="sumTotalRdcxptFeeSum" type="text" class="ygpaCurrency" style="text-align:right;" size="15" readonly>원
                               </form>
                            </td>
                        </tr>
                     </table>
            </div>
		</div>
            
    </div>
</div>