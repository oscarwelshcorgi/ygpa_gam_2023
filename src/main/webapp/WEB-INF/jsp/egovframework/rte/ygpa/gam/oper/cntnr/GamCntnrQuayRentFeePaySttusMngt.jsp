<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCntnrQuayRentFeePaySttusMngt.jsp
  * @Description : 컨테이너부두납부현황관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014-02-05  domh     최초 생성
  *
  * author domh
  * since 2014-02-05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamCntnrQuayRentFeePaySttusMngtModule() {}

GamCntnrQuayRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCntnrQuayRentFeePaySttusMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#cntnrQuayRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/gamSelectCntnrQuayRentFeePaySttusMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:50, sortable:false,align:'center'},              
					{display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},              
					{display:'요금종류', name:'chrgeKnd',width:55, sortable:false,align:'center'},         
					{display:'요금종류명', name:'chrgeKndNm',width:100, sortable:false,align:'left'},           
					{display:'회계년도', name:'accnutYear',width:55, sortable:false,align:'center'},         
					{display:'고지번호', name:'nticno',width:55, sortable:false,align:'center'},                          
					{display:'고지횟수', name:'nticCnt',width:55, sortable:false,align:'center'},          
					{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
					{display:'고지업체', name:'entrpscd',width:80, sortable:false,align:'center'},
					{display:'고지업체명', name:'entrpsNm',width:100, sortable:false,align:'left'},
					{display:'사용료', name:'fee',width:100, sortable:false,align:'right' , displayFormat: 'number'},         
					{display:'부가세', name:'vat',width:100, sortable:false,align:'right' , displayFormat: 'number'},                
					{display:'과세구분', name:'vatYn',width:55, sortable:false,align:'center'},               
					{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right' , displayFormat: 'number'},               
					{display:'수납구분', name:'rcvdTp',width:55, sortable:false,align:'center'},         
					{display:'수납일자', name:'rcvdDt',width:100, sortable:false,align:'center'},         
					{display:'사용시작일', name:'nticPdFrom',width:100, sortable:false,align:'center'},         
					{display:'사용종료일', name:'nticPdTo',width:100, sortable:false,align:'center'},               
					{display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'},            
					{display:'고지서출력', name:'billPrtYn',width:80, sortable:false,align:'center'},               
					{display:'납부기한', name:'dueDate',width:100, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: '350',
        preProcess: function(module,data) {
        	module.$('#totalResultCnt').val(data.dpTotCnt);
            module.$('#sumRcvdAmt').val(data.sumRcvdAmt);
            module.$('#sumNotRcvdAmt').val(data.sumNotRcvdAmt);
            module.$('#sumNticAmt').val(data.sumNticAmt);
            
            return data;
        }
    });

    this.$("#cntnrQuayRentFeePaySttusMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamCntnrQuayRentFeePaySttusMngtForm :input').val('');

        module.makeFormValues('#gamCntnrQuayRentFeePaySttusMngtForm', row);
        /* module._editData=module.getFormValues('#gamCntnrQuayRentFeePaySttusMngtForm', row);
        module._editRow=module.$('#cntnrQuayRentFeePaySttusMngtList').selectedRowIds()[0]; */
    });
    
    this.$("#cntnrQuayRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#cntnrQuayRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });
    
    
 // 오늘로 텍스트박스 날짜 정의
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
 GamCntnrQuayRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamCntnrQuayRentFeePaySttusMngtSearchForm');
           	this.$("#cntnrQuayRentFeePaySttusMngtListTab").tabs("option", {active: 0});
            this.$('#cntnrQuayRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

// 팝업을 호출한다.(업체)     
            
        case 'popupEntrpsInfo': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamCntnrQuayRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamCntnrQuayRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamCntnrQuayRentFeePaySttusMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#cntnrQuayRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamCntnrQuayRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamCntnrQuayRentFeePaySttusMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamCntnrQuayRentFeePaySttusMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCntnrQuayRentFeePaySttusMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">항구분</th>
                            <td style="width: 280px">
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>고지기간</th>
                            <td colspan="3">
                            	<input id="sGrUsagePdFrom" type="text" class="emdcal" size="10">
                            	 ~ 
                            	<input id="sGrUsagePdTo" type="text" class="emdcal" size="10">
                            </td>
                            <td rowspan="3"><button id="searchBtn" class="submit buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                        	<th>요금종류</th>
                            <td>
                                <input id="sChrgeKnd" type="text" size="3">&nbsp; &nbsp; 
                                <input id="sChrgeKndNm" type="text" size="13" disabled="disabled">&nbsp; &nbsp; 
                                <button id="popupChrgeKndCd" class="popupButton">선택</button>
                            </td>
                        	<th>고지업체</th>
                            <td colspan="3">
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="39" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button></td>
                            </td>
                        </tr>
                        <tr>
                            <th>관리년도</th>
                            <td>
                                <input id="sMngYear" type="text" size="10"> 
                            </td>
                            <th>관리번호</th>
                            <td style="width: 250px">
                                <input id="sMngNo" type="text" size="10"> 
                            </td>
                            <th>관리횟수</th>
                            <td>
                                <input id="sMngCnt" type="text" size="10"> 
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="cntnrQuayRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">컨테이너부두임대납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">컨테이너부두임대납부현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="cntnrQuayRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                
                <div class="emdControlPanel">
					<form id="form1">
                    	<table style="width:100%;" class="summaryPanel">
                        	<tr>
								<th width="20%" height="23">자료수</th>
								<td><input type="text" size="6" id="totalResultCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="23">고지금액</th>
								<td><input type="text" size="20" id="sumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="23">수납금액</th>
								<td><input type="text" size="20" id="sumRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="23">미수납금액</th>
								<td><input type="text" size="20" id="sumNotRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamCntnrQuayRentFeePaySttusMngtForm">
                        <input type="hidden" id="cmd"/>
                        <table class="searchPanel">
                            <tr>
								<th width="20%" height="23">항코드</th>
								<td><input type="text" size="43" id="prtAtCode" disabled="disabled" /></td>
								<th width="20%" height="23">항코드명</th>
								<td><input type="text" size="43" id="prtAtCodeNm" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">요금종류코드</th>
								<td><input type="text" size="43" id="chrgeKnd" disabled="disabled" /></td>
								<th width="20%" height="23">요금종류명</th>
								<td><input type="text" size="43" id="chrgeKndNm" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">회계년도</th>
								<td><input type="text" size="43" id="accnutYear" disabled="disabled" /></td>
								<th width="20%" height="23">고지번호</th>
								<td><input type="text" size="43" id="nticno" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">고지횟수</th>
								<td><input type="text" size="43" id="nticCnt" disabled="disabled" /></td>
								<th width="20%" height="23">고지일자</th>
								<td><input type="text" size="43" id="nticDt" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">사용시작일</th>
								<td><input type="text" size="43" id="nticPdFrom" disabled="disabled" /></td>
								<th width="20%" height="23">사용종료일</th>
								<td><input type="text" size="43" id="nticPdTo" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">사용료</th>
								<td><input type="text" size="43" id="fee" class="ygpaNumber" disabled="disabled" /></td>
								<th width="20%" height="23">부가세</th>
								<td><input type="text" size="43" id="vat" class="ygpaNumber" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">과세구분</th>
								<td><input type="text" size="43" id="vatYn" disabled="disabled" /></td>
								<th width="20%" height="23">고지금액</th>
								<td><input type="text" size="43" id="nticAmt" class="ygpaNumber" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">수납구분</th>
								<td><input type="text" size="43" id="rcvdTp" disabled="disabled" /></td>
								<th width="20%" height="23">수납일자</th>
								<td><input type="text" size="43" id="rcvdDt" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">관리번호</th>
								<td><input type="text" size="43" id="rentMngNo" disabled="disabled" /></td>
								<th width="20%" height="23">고지서출력여부</th>
								<td><input type="text" size="43" id="billPrtYn" disabled="disabled" /></td>
                            </tr>
                            <tr>
								<th width="20%" height="23">납부기한</th>
								<td><input type="text" size="43" id="dueDate" disabled="disabled" /></td>
								<th width="20%" height="23">비고</th>
								<td><input type="text" size="43" id="rm" disabled="disabled" /></td>
                            </tr>
                        </table>
                    </form>

                <!--
                <div style="vertical-align: bottom; text-align: right;">
                    <input type="reset" value="취소" class="input_1"> <input
                        type="submit" value="저장" class="input_1">
                </div>
                 -->
            </div>

        </div>
    </div>
</div>