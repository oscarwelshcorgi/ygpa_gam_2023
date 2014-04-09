<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCmmnCntrRentFeePaySttusMngt.jsp
  * @Description : 공컨장치장납부현황관리
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
function GamCmmnCntrRentFeePaySttusMngtModule() {}

GamCmmnCntrRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCmmnCntrRentFeePaySttusMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#cmmnCntrRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/shed/gamSelectCmmnCntrRentFeePaySttusMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'일련번호', name:'intSeq',width:100, sortable:false,align:'center'},              
					{display:'처리구분', name:'delKind',width:100, sortable:false,align:'center'},              
					{display:'징수일자(ERP)', name:' jingsuDate',width:100, sortable:false,align:'center'},         
					{display:'수납일자(ERP)', name:'sunapDate',width:100, sortable:false,align:'center'},           
					{display:'청코드', name:'prtAtCode',width:100, sortable:false,align:'center'},         
					{display:'요금종류', name:'feeTp',width:100, sortable:false,align:'center'},                
					{display:'회계년도', name:'fiscalYr',width:100, sortable:false,align:'center'},         
					{display:'고지번호', name:'billNo',width:100, sortable:false,align:'center'},               
					{display:'회계구분코드', name:'accntCode',width:100, sortable:false,align:'center'},          
					{display:'업체코드', name:'agentCode',width:100, sortable:false,align:'center'},            
					{display:'고지금액', name:'billAmnt',width:100, sortable:false,align:'center'},         
					{display:'고지일자', name:'billDt',width:100, sortable:false,align:'center'},               
					{display:'고지서발부여부', name:'billPrtYn',width:100, sortable:false,align:'center'},         
					{display:'납부기한일자', name:'dueDate',width:100, sortable:false,align:'center'},                
					{display:'수납일자', name:'rcvdDt',width:100, sortable:false,align:'center'},               
					{display:'수납구분', name:'rcvdTp',width:100, sortable:false,align:'center'},               
					{display:'불능코드', name:'rsltCode',width:100, sortable:false,align:'center'},         
					{display:'면제 ( 보전 ) 금액', name:'exmpAmnt',width:100, sortable:false,align:'center'},         
					{display:'과오납금액', name:'overAmnt',width:100, sortable:false,align:'center'},            
					{display:'할인금액', name:'dcAmnt',width:100, sortable:false,align:'center'},               
					{display:'할인사유', name:'dcCode',width:100, sortable:false,align:'center'},               
					{display:'할인코드', name:'dcRate',width:100, sortable:false,align:'center'},               
					{display:'금융기관 수납일자', name:'recptEpdt',width:100, sortable:false,align:'center'},           
					{display:'전자고지 결과', name:'elctBillRslt',width:100, sortable:false,align:'center'},      
					{display:'전자고지 정보조회일자', name:'bullInfoInqrDtime',width:100, sortable:false,align:'center'}
					
					/*
					{display:'산출내역', name:'amntRsn',width:100, sortable:false,align:'center'},              
					{display:'사업자등록번호', name:'bzRgstId',width:100, sortable:false,align:'center'},          
					{display:'사업장명', name:'agentName',width:100, sortable:false,align:'center'},            
					{display:'정산여부', name:'last',width:100, sortable:false,align:'center'},             
					{display:'부가세여부', name:'vatYn',width:100, sortable:false,align:'center'},               
					{display:'지로번호', name:'jiroNo',width:100, sortable:false,align:'center'},               
					{display:'전자납부번호', name:'elecPayNo',width:100, sortable:false,align:'center'},          
					{display:'고객관리번호', name:'customerMngtNo',width:100, sortable:false,align:'center'},     
					{display:'고객주소', name:'customerAddr',width:100, sortable:false,align:'center'},     
					{display:'납부자성명', name:'payName',width:100, sortable:false,align:'center'}
					*/
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt);
            
            return data;
        }
    });

    this.$("#cmmnCntrRentFeePaySttusMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamCmmnCntrRentFeePaySttusMngtForm :input').val('');

        module.makeFormValues('#gamCmmnCntrRentFeePaySttusMngtForm', row);
        module._editData=module.getFormValues('#gamCmmnCntrRentFeePaySttusMngtForm', row);
        module._editRow=module.$('#cmmnCntrRentFeePaySttusMngtList').selectedRowIds()[0];
    });
    
    this.$("#cmmnCntrRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#cmmnCntrRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        
    });

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamCmmnCntrRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamCmmnCntrRentFeePaySttusMngtSearchForm');
            this.$('#cmmnCntrRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();

            break;

        // 팝업을 호출한다.(업체)     
        case 'popupEntrpsInfoFeePay': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamCmmnCntrRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamCmmnCntrRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamCmmnCntrRentFeePaySttusMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#cmmnCntrRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamCmmnCntrRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamCmmnCntrRentFeePaySttusMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
var module_instance = new GamCmmnCntrRentFeePaySttusMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCmmnCntrRentFeePaySttusMngtSearchForm">
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
        <div id="cmmnCntrRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">공컨장치장임대납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">공컨장치장임대납부현황 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="cmmnCntrRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                
                <!-- 
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                    합계
                                    자료수 <input id="totalResultCnt" size="5" readonly>
                                    사용료 <input id="sumFee" type="text" size="14" readonly>
                                    부가세 <input id="sumVat" type="text" size="14" readonly>
                                    고지액 <input id="sumNticAmt" type="text" size="14" readonly>
                               </form>
                            </td>
                            <td>
                                <button id="mapInfoBtn">맵정보</button>
                            </td>
                        </tr>
                    </table>
                </div>
                 -->
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamCmmnCntrRentFeePaySttusMngtForm">
                        <input type="hidden" id="cmd"/>
                        
                        <table>
                            <tr>
                                <th><span class="label">일련번호</span></th>
                                <td style="width: 200px"><input type="text" size="10" id="intSeq"/></td>
                                <th><span class="label">처리구분</span></th>
                                <td><input type="text" size="10" id="delKind"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">징수일자</span></th>
                                <td><input type="text" size="10" id="jingsuDate"/></td>
                                <th><span class="label">수납일자</span></th>
                                <td><input type="text" size="10" id="sunapDate"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">청코드</span></th>
                                <td><input type="text" size="10" id="prtAtCode"/></td>
                                <th><span class="label">요금종류</span></th>
                                <td><input type="text" size="10" id="feeTp"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">회계년도</span></th>
                                <td><input type="text" size="10" id="fiscalYr"/></td>
                                <th><span class="label">고지번호</span></th>
                                <td><input type="text" size="10" id="billNo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">회계구분코드</span></th>
                                <td><input type="text" size="10" id="accntCode"/></td>
                                <th><span class="label">업체코드</span></th>
                                <td><input type="text" size="10" id="agentCode"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고지금액</span></th>
                                <td><input type="text" size="20" id="billAmnt"/></td>
                                <th><span class="label">고지일자</span></th>
                                <td><input type="text" size="10" id="billDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고지서발부여부</span></th>
                                <td><input type="text" size="10" id="billPrtYn"/></td>
                                <th><span class="label">납부기한일자</span></th>
                                <td><input type="text" size="10" id="dueDate"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">수납일자</span></th>
                                <td><input type="text" size="10" id="rcvdDt"/></td>
                                <th><span class="label">수납구분</span></th>
                                <td><input type="text" size="10" id="rcvdTp"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">불능코드</span></th>
                                <td><input type="text" size="10" id="rsltCode"/></td>
                                <th><span class="label">면제(보전)금액</span></th>
                                <td><input type="text" size="20" id="exmpAmnt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">과오납금액</span></th>
                                <td><input type="text" size="20" id="overAmnt"/></td>
                                <th><span class="label">할인금액</span></th>
                                <td><input type="text" size="20" id="dcAmnt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">할인사유</span></th>
                                <td><input type="text" size="10" id="dcCode"/></td>
                                <th><span class="label">할인코드</span></th>
                                <td><input type="text" size="10" id="dcRate"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">금융기관 수납일자</span></th>
                                <td><input type="text" size="10" id="recptEpdt"/></td>
                                <th><span class="label">전자고지 결과</span></th>
                                <td><input type="text" size="10" id="elctBillRslt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">전자고지 정보조회일자</span></th>
                                <td><input type="text" size="10" id="bullInfoInqrDtime"/></td>
                                <th><span class="label">산출내역</span></th>
                                <td><input type="text" size="10" id="amntRsn"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">사업자등록번호</span></th>
                                <td><input type="text" size="10" id="bzRgstId"/></td>
                                <th><span class="label">사업장명</span></th>
                                <td><input type="text" size="10" id="agentName"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">정산여부</span></th>
                                <td><input type="text" size="10" id="last"/></td>
                                <th><span class="label">부가세</span></th>
                                <td><input type="text" size="10" id="vatYn"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">지로번호</span></th>
                                <td><input type="text" size="10" id="jiroNo"/></td>
                                <th><span class="label">전자납부번호</span></th>
                                <td><input type="text" size="10" id="elecPayNo"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고객관리번호</span></th>
                                <td><input type="text" size="10" id="customerMngtNo"/></td>
                                <th><span class="label">고객주소</span></th>
                                <td><input type="text" size="40" id="customerAddr"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">납부자성명</span></th>
                                <td colspan="3"><input type="text" size="10" id="payName"/></td>
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