<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyRentFeePaySttusMngt.jsp
  * @Description : 항만시설납부현황관리
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
function GamPrtFcltyRentFeePaySttusMngtModule() {}

GamPrtFcltyRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#prtFcltyRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentFeePaySttusMngtList.do" />',
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
					{display:'사용료', name:'fee',width:100, sortable:false,align:'center'},         
					{display:'부가세', name:'vat',width:100, sortable:false,align:'center'},                
					{display:'과세구분', name:'vatYn',width:100, sortable:false,align:'center'},               
					{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'center'},               
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

    this.$("#prtFcltyRentFeePaySttusMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamPrtFcltyRentFeePaySttusMngtForm :input').val('');

        module.makeFormValues('#gamPrtFcltyRentFeePaySttusMngtForm', row);
        //module._editData=module.getFormValues('#gamPrtFcltyRentFeePaySttusMngtForm', row);
        //module._editRow=module.$('#prtFcltyRentFeePaySttusMngtList').selectedRowIds()[0];
    });
    
    this.$("#prtFcltyRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#prtFcltyRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamPrtFcltyRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':

            var searchOpt=this.makeFormArgs('#gamPrtFcltyRentFeePaySttusMngtSearchForm');
            this.$("#prtFcltyRentFeePaySttusMngtListTab").tabs("option", {active: 0}); //2014-4-23 추가
            this.$('#prtFcltyRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 팝업을 호출한다.(업체)     
        case 'popupEntrpsInfoFeePay': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamPrtFcltyRentFeePaySttusMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#prtFcltyRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamPrtFcltyRentFeePaySttusMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
var module_instance = new GamPrtFcltyRentFeePaySttusMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamPrtFcltyRentFeePaySttusMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">항구분</th>
                            <td>
                            	<!-- 
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                                -->
                                <input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <td style="text-align: right;"><button id="searchBtn" class="submit">조회</button>&nbsp;</td>
                        </tr>
                            
                        <!-- 
                        <tr>
                            <th>항코드</th>
                            <td>
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>고지방법</th>
                            <td width="100px">
                                <select id="sNticMth">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${nticMthCdList}" var="nticMthCdItem">
                                        <option value="${nticMthCdItem.code }">${nticMthCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>부두</th>
                            <td>
                                <select id="sQuayCd">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${quayCdList}" var="quayCdItem">
                                        <option value="${quayCdItem.code }">${quayCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>수납구분</th>
                            <td>
                                <select id="sRcivSe">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${rcivSeCdList}" var="rcivSeCdItem">
                                        <option value="${rcivSeCdItem.code }">${rcivSeCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>조회업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfoFeePay">업체</button>
                            </td>
                            <th>고지일자</th>
                            <td colspan="4">
                            <input id="sNticDtFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sNticDtTo" type="text"
                                class="emdcal" size="8">
                            </td>
                        </tr>
                         -->
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="prtFcltyRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만시설납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">항만시설납부현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="prtFcltyRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
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
                            <!-- 
                            <td>
                                <button id="mapInfoBtn">맵정보</button>
                            </td>
                            -->
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamPrtFcltyRentFeePaySttusMngtForm">
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
                            <!-- <tr>
                            	<th><span class="label">관리년도</span></th>
                                <td><input type="text" size="10" id="mngYear"/></td>
                                <th><span class="label">관리번호</span></th>
                                <td><input type="text" size="10" id="mngNo"/></td>
                                <th><span class="label">관리횟수</span></th>
                                <td><input type="text" size="10" id="mngCnt"/></td>
                            </tr> -->
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