<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamHtldRentFeePaySttusMngt.jsp
  * @Description : 배후단지납부현황관리
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
function GamHtldRentFeePaySttusMngtModule() {}

GamHtldRentFeePaySttusMngtModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamHtldRentFeePaySttusMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#htldRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/htld/gamSelectHtldRentFeePaySttusMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},              
					{display:'항코드명', name:'prtAtcodeNm',width:100, sortable:false,align:'center'},              
					{display:'요금종류코드', name:'chrgeKnd',width:100, sortable:false,align:'center'},         
					{display:'요금종류명', name:'chrgeKndNm',width:100, sortable:false,align:'center'},           
					{display:'회계년도', name:'accnutYear',width:100, sortable:false,align:'center'},         
					{display:'고지번호', name:'nticno',width:100, sortable:false,align:'center'},                          
					{display:'고지횟수', name:'nticCnt',width:100, sortable:false,align:'center'},          
					{display:'고지일자', name:'nticDt',width:100, sortable:false,align:'center'},            
					{display:'사용시작일', name:'nticPdFrom',width:100, sortable:false,align:'center'},         
					{display:'사용종료일', name:'nticPdTo',width:100, sortable:false,align:'center'},               
					{display:'사용료', name:'fee',width:100, sortable:false,align:'right' , displayFormat: 'number'},         
					{display:'부가세', name:'vat',width:100, sortable:false,align:'right' , displayFormat: 'number'},                
					{display:'과세구분', name:'vatYn',width:100, sortable:false,align:'center'},               
					{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right' , displayFormat: 'number'},               
					{display:'수납구분', name:'rcvdTp',width:100, sortable:false,align:'center'},         
					{display:'수납일자', name:'rcvdDt',width:100, sortable:false,align:'center'},         
					{display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'},            
					{display:'고지서출력여부', name:'billPrtYn',width:100, sortable:false,align:'center'},               
					{display:'납부기한', name:'dueDate',width:100, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: true,
        height: '350',
        preProcess: function(module,data) {
        	module.$('#totalResultCnt').val(data.dpTotCnt);
            module.$('#sumRcvdAmt').val(data.sumRcvdAmt);
            module.$('#sumNticAmt').val(data.sumNticAmt);
            
            return data;
        }
    });

    this.$("#htldRentFeePaySttusMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamHtldRentFeePaySttusMngtForm :input').val('');

        module.makeFormValues('#gamHtldRentFeePaySttusMngtForm', row);
        /* module._editData=module.getFormValues('#gamHtldRentFeePaySttusMngtForm', row);
        module._editRow=module.$('#htldRentFeePaySttusMngtList').selectedRowIds()[0]; */
    });
    
    this.$("#htldRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#htldRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });
    
 // 오늘로 텍스트박스 날짜 정의
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}

	var serchday = today.getDate();
	var searchEndDate = serchYr + "-" + serchMn + "-" + serchday;
	
	today.setMonth(today.getMonth() - 1);
	
	serchYr = today.getFullYear();
	serchMn = today.getMonth() + 1;
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	serchday = today.getDate();
	
	var searchStartDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sGrUsagePdFrom").val(searchStartDate);
	this.$("#sGrUsagePdTo").val(searchEndDate);

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamHtldRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamHtldRentFeePaySttusMngtSearchForm');
            this.$('#htldRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

         // 팝업을 호출한다.(업체)     
            
        case 'popupEntrpsInfo': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamHtldRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamHtldRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamHtldRentFeePaySttusMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#htldRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamHtldRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamHtldRentFeePaySttusMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
var module_instance = new GamHtldRentFeePaySttusMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentFeePaySttusMngtSearchForm">
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
                                <input id="sChrgeKnd" type="text" size="10"> 
                                <input id="sChrgeKndNm" type="text" size="10"> 
                                <button id="popupChrgeKndCd">요금</button>
                            </td>
                        	<th>고지업체</th>
                            <td colspan="3">
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button>
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
        <div id="htldRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">배후단지임대납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">배후단지임대납부현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="htldRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                    합계
                                    자료수 <input id="totalResultCnt" size="5" readonly>
                                    고지금액 <input id="sumNticAmt" type="text" size="14" readonly>원 
                                    수납금액 <input id="sumRcvdAmt" type="text" size="14" readonly>원 
                               </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamHtldRentFeePaySttusMngtForm">
                        <input type="hidden" id="cmd"/>
                        
                        <table>
                            <tr>
                                <th style="width:150px;"><span class="label">항코드</span></th>
                                <td style="width:250px;"><input type="text" size="10" id="prtAtCode"/></td>
                                <th style="width:150px;"><span class="label">항코드명</span></th>
                                <td><input type="text" size="10" id="prtAtCodeNm"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">요금종류코드</span></th>
                                <td><input type="text" size="10" id="chrgeKnd"/></td>
                                <th><span class="label">요금종류명</span></th>
                                <td><input type="text" size="10" id="chrgeKndNm"/></td>

                            </tr>
                            <tr>
                                <th><span class="label">회계년도</span></th>
                                <td><input type="text" size="10" id="accnutYear"/></td>
                                <th><span class="label">고지번호</span></th>
                                <td><input type="text" size="10" id="nticno"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고지횟수</span></th>
                                <td><input type="text" size="10" id="nticCnt"/></td>
                                <th><span class="label">고지일자</span></th>
                                <td><input type="text" size="10" id="nticDt"/></td>
                                
                            </tr>
                            <tr>
                            	<th><span class="label">사용시작일</span></th>
                                <td><input type="text" size="20" id="nticPdFrom"/></td>
                                <th><span class="label">사용종료일</span></th>
                                <td><input type="text" size="10" id="nticPdTo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">사용료</span></th>
                                <td><input type="text" size="10" id="fee"/></td>
                                <th><span class="label">부가세</span></th>
                                <td><input type="text" size="10" id="vat"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">과세구분</span></th>
                                <td><input type="text" size="10" id="vatYn"/></td>
                                <th><span class="label">고지금액</span></th>
                                <td><input type="text" size="10" id="nticAmt"/></td>
                                
                            </tr>
                            <tr>
                            	<th><span class="label">수납구분</span></th>
                                <td><input type="text" size="10" id="rcvdTp"/></td>
                                <th><span class="label">수납일자</span></th>
                                <td><input type="text" size="20" id="rcvdDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">관리번호</span></th>
                                <td><input type="text" size="20" id="rentMngNo"/></td>
                                <th><span class="label">고지서출력여부</span></th>
                                <td><input type="text" size="20" id="billPrtYn"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">납부기한</span></th>
                                <td colspan="3"><input type="text" size="10" id="dueDate"/></td>
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