<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMarineCenterRentArStsReport.jsp
  * @Description : 마린센터면적별사용료현황조회
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
function GamMarineCenterRentArStsReportModule() {}

GamMarineCenterRentArStsReportModule.prototype = new EmdModule(1000, 600);

//페이지가 호출 되었을때 호출 되는 함수
GamMarineCenterRentArStsReportModule.prototype.loadComplete = function() {
 
 // 테이블 설정 //       
 this.$("#marineCenterRentArStsReportList").flexigrid({
     module: this,
     url: '/oper/center/gamSelectMarineCenterRentArStsReportList.do',
     dataType: 'json',
     colModel : [
                 {display:'항코드', name:'prtAtCode',width:150, sortable:false,align:'center'},
                 {display:'항코드명', name:'prtKorNm',width:150, sortable:false,align:'center'},
                 {display:'면적구분', name:'areaClass',width:150, sortable:false,align:'left'},
                 {display:'사용료', name:'sumTotalFee',width:200, sortable:false,align:'right' ,displayFormat: 'number'},
                 {display:'감면사용료', name:'sumTotalRdcxptFee',width:200, sortable:false,align:'right' ,displayFormat: 'number'}
                 ],
     showTableToggleBtn: false,
     height: 'auto',
     preProcess: function(module,data) {
         module.$('#totSumCnt').val(data.totalCount);
         module.$('#totSumFee').val(data.totSumFee);
         module.$('#totSumRocxptFee').val(data.totSumRocxptFee);
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
	if (serchMn < 10) {
		serchMn = "0" + serchMn;
	}

	this.$("#sStartYr").val(serchYr);
	this.$("#sStartMn").val(serchMn);
	this.$("#sEndYr").val(serchYr);
	this.$("#sEndMn").val(serchMn);
};

this.$("#marineCenterRentArStsReportList").on("onItemSelected", function(event, module, row, grid, param) {
    //alert("row " + row["erpAssetsSeCd"]+"-"+row["erpAssetsNo"]+"-"+row["erpAssetsNoSeq"]+" is selected");
    
     
    
});

/**
* 정의 된 버튼 클릭 시
*/
GamMarineCenterRentArStsReportModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	
	    case 'searchBtn':
	        if( this.$('#sStartYr').val() == '' ) {
	        	alert("조회시작년을 선택하십시오.");
	        	return;
	        }
	        if( this.$('#sStartMn').val() == '' ) {
	        	alert("조회시작월을 선택하십시오.");
	        	return;
	        }
	        if( this.$('#sEndYr').val() == '' ) {
	        	alert("조회끝년을 선택하십시오.");
	        	return;
	        }
	        if( this.$('#sEndMn').val() == '' ) {
	        	alert("조회끝월을 선택하십시오.");
	        	return;
	        }
	        var searchOpt=this.makeFormArgs('#gamMarineCenterRentArStsReportSearchForm');
			this.$('#marineCenterRentArStsReportList').flexOptions({params:searchOpt}).flexReload();
	
	        break;
	        
	    case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;      
     	     // 자산코드 팝업
		case "searchPopupBtn":
			this.doExecuteDialog("searchGisAssetsCodePopup", "자산코드", '/popup/showAssetsCd.do', {});
	        
	}
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamMarineCenterRentArStsReportModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	// 자산코드 조회
	   case "searchGisAssetsCodePopup":
		this.$("#sAssetsCd").val(value["gisAssetsCd"]);
		this.$("#sAssetsSubCd").val(value["gisAssetsSubCd"]);
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

GamMarineCenterRentArStsReportModule.prototype.onSubmit = function() {
 //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

 this.loadData();
};

GamMarineCenterRentArStsReportModule.prototype.loadData = function() {
 var searchOpt=this.makeFormArgs('#gamMarineCenterRentArStsReportSearchForm');
 //this.showAlert(searchOpt);
 this.$('#marineCenterRentArStsReportList').flexOptions({params:searchOpt}).flexReload();
};

GamMarineCenterRentArStsReportModule.prototype.onTabChange = function(newTabId, oldTabId) {
 switch(newTabId) {
 case 'tabs1':
     break;
 case 'tabs2':
     break;
 }
};

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMarineCenterRentArStsReportModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamMarineCenterRentArStsReportSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th>항코드</th>
                            <td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th>업체코드</th>
                            <td><input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            <input id="sEntrpsNm" type="text" size="30" disabled="disabled">&nbsp; &nbsp;
                            <button id="popupEntrpsInfo" class="popupButton">선택</button></td>
                            <td rowSpan="2"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>자산코드</th>
							<td>
								<input id="sAssetsCd" type="text" size="3" maxlength="3" title="검색조건" />&nbsp;-&nbsp;
								<input id="sAssetsSubCd" type="text" size="2" maxlength="2" title="검색조건" />&nbsp; &nbsp;
								<button id="searchPopupBtn" class="popupButton">선택</button>
							</td>
                            <th>사용기간</th>
                            <td>
		                       	<select id="sStartYr">
                                    <option value="" selected="selected">년도</option>
                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem }">${yearItem }</option>
                                    </c:forEach>
                                </select>
                                <select id="sStartMn">
                                    <option value="" selected="selected">월</option>
                                    <c:forEach  items="${monthsList}" var="monthsItem">
                                    	<c:if test="${monthsItem >= 10}">
                                        	<option value="${monthsItem }">${monthsItem}</option>
                                        </c:if>
                                    	<c:if test="${monthsItem < 10}">
                                        	<option value="0${monthsItem }">${monthsItem}</option>
                                        </c:if>                                        
                                    </c:forEach>
                                </select>
                                &nbsp;~&nbsp;
		                       	<select id="sEndYr">
                                    <option value="" selected="selected">년도</option>
                                    <c:forEach  items="${yearsList}" var="yearItem">
                                        <option value="${yearItem}">${yearItem}</option>
                                    </c:forEach>
                                </select>
                                <select id="sEndMn">
                                    <option value="" selected="selected">월</option>
                                    <c:forEach  items="${monthsList}" var="monthsItem">
                                    	<c:if test="${monthsItem >= 10}">
                                        	<option value="${monthsItem}">${monthsItem}</option>
                                        </c:if>
                                    	<c:if test="${monthsItem < 10}">
                                        	<option value="0${monthsItem}">${monthsItem}</option>
                                        </c:if>         
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
                <li><a href="#tabs1" class="emdTab">마린센터면적별사용료현황</a></li>
            </ul>
            <div id="tabs1" class="emdTabPage" data-onactivate="onShowTab1Activate">
            <table id="marineCenterRentArStsReportList" style="display:none" class="fillHeight"></table>
            <div class="emdControlPanel">
				<form id="form1">
					<table style="width:100%;" class="summaryPanel">
						<tr>
							<th width="20%" height="23">자료수</th>
							<td><input type="text" size="10" id="totSumCnt" class="ygpaNumber" disabled="disabled" /></td>
							<th width="20%" height="23">사용료</th>
							<td><input type="text" size="20" id="totSumFee" class="ygpaNumber" disabled="disabled" /></td>
							<th width="20%" height="23">감면사용료</th>
							<td><input type="text" size="20" id="totSumRocxptFee" class="ygpaNumber" disabled="disabled" /></td>
						</tr>
					</table>
				</form>
            </div>
		</div>
            
    </div>
</div>