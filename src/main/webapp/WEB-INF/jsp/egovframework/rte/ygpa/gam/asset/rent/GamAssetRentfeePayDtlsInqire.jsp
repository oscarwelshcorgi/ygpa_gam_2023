<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentfeePayDtlsInqire.jsp
  * @Description : 사용료납부내역조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.10  heroin     최초 생성
  *
  * author heroin
  * since 2014.01.10
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentfeePayDtlsInqireModule() {}

GamAssetRentfeePayDtlsInqireModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentfeePayDtlsInqireModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '/asset/rent/gamSelectAssetRentfeePayDtlsInqireList.do',
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
        showTableToggleBtn: false,
        height: '320',
        preProcess: function(module,data) {
            module.$('#totSumCnt').val(data.totSumCnt);
            module.$('#totSumNticAmt').val(data.totSumNticAmt);
            module.$('#totSumRcvdAmt').val(data.totSumRcvdAmt);
            module.$('#totSumNotRcvdAmt').val(data.totSumNotRcvdAmt);
            
            return data;
        }
    });

    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');
        
        module.makeDivValues('#gamAssetRentFeeForm', row); // 결과값을 채운다.

        /* module.$('#gamAssetRentFeeForm :input').val('');

        module.makeFormValues('#gamAssetRentFeeForm', row);
        module._editData=module.getFormValues('#gamAssetRentFeeForm', row);
        module._editRow=module.$('#assetRentFeeList').selectedRowIds()[0]; */
    });
    
    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });
    
    
 	// 오늘 날짜로 고지기간 설정 처리
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
 GamAssetRentfeePayDtlsInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
        	if(this.$("#sNticPdFrom").val() == ""){
        		alert("고지일자 시작일을 선택하세요.");
        		return;
        	}
            var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
            this.$("#assetRentFeeListTab").tabs("option", {active: 0}); //2014-4-23 추가
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
            break;        
        // 팝업을 호출한다.(업체)     
        case 'popupEntrpsInfo': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
            
    }
};

GamAssetRentfeePayDtlsInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentfeePayDtlsInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
    //this.showAlert(searchOpt);
    this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetRentfeePayDtlsInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamAssetRentfeePayDtlsInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
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

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAssetRentfeePayDtlsInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentFeeSearchForm">
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
                            <input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM024 />
                                <!-- 
                                <input id="sChrgeKnd" type="text" size="3">&nbsp; &nbsp; 
                                <input id="sChrgeKndNm" type="text" size="13" disabled="disabled">&nbsp; &nbsp; 
                                 <button id="popupChrgeKndCd" class="popupButton">선택</button>
                                -->
                            </td>
                        	<th>고지업체</th>
                            <td colspan="3">
                            	<input id="sEntrpscd" type="text" size="6">&nbsp; &nbsp;
                            	<input id="sEntrpsNm" type="text" size="39" disabled="disabled">&nbsp; &nbsp;
                            	<button id="popupEntrpsInfo" class="popupButton">선택</button>
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
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">사용료납부내역조회 목록</a></li>
                <li><a href="#tabs2" class="emdTab">사용료납부내역조회 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <div class="emdControlPanel">
                	<table id="assetRentFeeList" style="display:none"></table>
					<form id="form1">
                    	<table style="width:100%;" class="summaryPanel">
                        	<tr>
								<th width="12%" height="23">자료수</th>
								<td><input type="text" size="7" id="totSumCnt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="23">고지금액</th>
								<td><input type="text" size="19" id="totSumNticAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="23">수납금액</th>
								<td><input type="text" size="19" id="totSumRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
								<th width="12%" height="23">미수납금액</th>
								<td><input type="text" size="19" id="totSumNotRcvdAmt" class="ygpaNumber" disabled="disabled" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>
            
            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamAssetRentFeeForm">
                        <input type="hidden" id="cmd"/>
                        <table class="detailForm"  style="width:100%;">
                            <tr>
								<th width="15%" height="23">항코드</th>
								<td><span size="43" id="prtAtCode"></span></td>
								<th width="15%" height="23">항코드명</th>
								<td><span size="43" id="prtAtCodeNm"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">요금종류코드</th>
								<td><span size="43" id="chrgeKnd"></span></td>
								<th width="15%" height="23">요금종류명</th>
								<td><span size="43" id="chrgeKndNm"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">회계년도</th>
								<td><span size="43" id="accnutYear"></span></td>
								<th width="15%" height="23">고지번호</th>
								<td><span size="43" id="nticno"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">고지횟수</th>
								<td><span size="43" id="nticCnt"></span></td>
								<th width="15%" height="23">고지일자</th>
								<td><span size="43" id="nticDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">사용시작일</th>
								<td><span size="43" id="nticPdFrom"></span></td>
								<th width="15%" height="23">사용종료일</th>
								<td><span size="43" id="nticPdTo"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">사용료</th>
								<td><span size="43" id="fee" class="ygpaNumber"></span></td>
								<th width="15%" height="23">부가세</th>
								<td><span size="43" id="vat" class="ygpaNumber"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">과세구분</th>
								<td><span size="43" id="vatYn"></span></td>
								<th width="15%" height="23">고지금액</th>
								<td><span size="43" id="nticAmt" class="ygpaNumber"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">수납구분</th>
								<td><span size="43" id="rcvdTp"></span></td>
								<th width="15%" height="23">수납일자</th>
								<td><span size="43" id="rcvdDt"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">관리번호</th>
								<td><span size="43" id="rentMngNo"></span></td>
								<th width="15%" height="23">고지서출력여부</th>
								<td><span size="43" id="billPrtYn"></span></td>
                            </tr>
                            <tr>
								<th width="15%" height="23">납부기한</th>
								<td><span size="43" id="dueDate"></span></td>
								<th width="15%" height="23">비고</th>
								<td><span size="43" id="rm"></span></td>
                            </tr>
                        </table>
                    </form>
				</div>
            </div>

        </div>
    </div>
</div>