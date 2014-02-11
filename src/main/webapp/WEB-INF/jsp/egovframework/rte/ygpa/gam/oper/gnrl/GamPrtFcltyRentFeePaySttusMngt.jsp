<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyRentFeePaySttusMngt.jsp
  * @Description : 일반부두납부현황관리
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

GamPrtFcltyRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 550);

// 페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#assetRentFeePayList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentFeePaySttusMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'요금 종류', name:'chrgeKnd',width:100, sortable:false,align:'center'},                      
					{display:'회계 년도', name:'accnutYear',width:100, sortable:false,align:'center'},                
					{display:'고지번호', name:'nticno',width:100, sortable:false,align:'center'},                     
					{display:'회계 구분 코드', name:'accnutSeCd',width:100, sortable:false,align:'center'},                 
					{display:'업체코드', name:'entrpscd',width:100, sortable:false,align:'center'},                   
					{display:'고지 금액', name:'nticAmt',width:100, sortable:false,align:'center'},                   
					{display:'고지 일자', name:'nticDt',width:100, sortable:false,align:'center'},                    
					{display:'고지서 발부 여부', name:'nhtIsueYn',width:100, sortable:false,align:'center'},                 
					{display:'산출 내역', name:'computDtls',width:100, sortable:false,align:'center'},                
					{display:'납부 기한', name:'payTmlmt',width:100, sortable:false,align:'center'},                      
					{display:'최초 고지 일자', name:'frstNticDt',width:100, sortable:false,align:'center'},                 
					{display:'수납 이체 상태 코드', name:'rcivTransfrSttusCd',width:100, sortable:false,align:'center'},          
					{display:'수납 일자', name:'rcivDt',width:100, sortable:false,align:'center'},                    
					{display:'수납 구분', name:'rcivSe',width:100, sortable:false,align:'center'},                    
					{display:'불능 코드', name:'incpctyCd',width:100, sortable:false,align:'center'},                 
					{display:'과오납 금액', name:'overrpayAmt',width:100, sortable:false,align:'center'},                  
					{display:'임시 발행 번호', name:'tmprIsuNo',width:100, sortable:false,align:'center'},                  
					{display:'할인 금액', name:'dscntAmt',width:100, sortable:false,align:'center'},                      
					{display:'할인 사유', name:'dscntRsn',width:100, sortable:false,align:'center'},                      
					{display:'할인 코드', name:'dscntCd',width:100, sortable:false,align:'center'},                   
					{display:'수납 구분명', name:'rcivSeNm',width:100, sortable:false,align:'center'},                 
					{display:'금융 기관 수납 일자', name:'fnncInsttRcivDt',width:100, sortable:false,align:'center'},         
					{display:'우편 고지 유무', name:'postNticEnnc',width:100, sortable:false,align:'center'},           
					{display:'불납 사유 코드', name:'npymnRsnCd',width:100, sortable:false,align:'center'},                 
					{display:'전자 고지 결과', name:'elctrnNticResult',width:100, sortable:false,align:'center'},       
					{display:'전자 고지 정보 조회 일자', name:'elctrnNticInfoInqireDt',width:100, sortable:false,align:'center'},  
					{display:'정산 여부', name:'excclcYn',width:100, sortable:false,align:'center'},                      
					{display:'부가세', name:'vat',width:100, sortable:false,align:'center'},                         
					{display:'부가세 여부', name:'vatYn',width:100, sortable:false,align:'center'},                    
					{display:'징수관 구분', name:'prcepturSe',width:100, sortable:false,align:'center'},               
					{display:'지로 수납처', name:'giroRcivPlace',width:100, sortable:false,align:'center'},            
					{display:'지로 수납 구분', name:'giroRcivSe',width:100, sortable:false,align:'center'},                 
					{display:'수수료', name:'cmsn',width:100, sortable:false,align:'center'},                        
					{display:'마감 여부', name:'closYn',width:100, sortable:false,align:'center'},                    
					{display:'부서코드', name:'deptcd',width:100, sortable:false,align:'center'},                     
					{display:'담당자', name:'charger',width:100, sortable:false,align:'center'},                     
					{display:'작업 구분', name:'opertSe',width:100, sortable:false,align:'center'},                   
					{display:'원고지 요금 종류', name:'orginlNticChrgeKnd',width:100, sortable:false,align:'center'},        
					{display:'원고지 회계 년도', name:'orginlNticAccnutYear',width:100, sortable:false,align:'center'},      
					{display:'원고지 번호', name:'orginlNticNo',width:100, sortable:false,align:'center'},            
					{display:'전자 세금 계산서 발행 여부', name:'elctrnTaxbilIsuYn',width:100, sortable:false,align:'center'},       
					{display:'시작일', name:'beginDt',width:100, sortable:false,align:'center'},                     
					{display:'종료일', name:'endDt',width:100, sortable:false,align:'center'},                   
					{display:'고지 방법', name:'nticMth',width:100, sortable:false,align:'center'},                   
					{display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},                      
					{display:'등록일시', name:'registDt',width:100, sortable:false,align:'center'},                   
					{display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},                      
					{display:'수정일시', name:'updtDt',width:100, sortable:false,align:'center'},                     
					{display:'고지 횟수', name:'nticCnt',width:100, sortable:false,align:'center'},                   
					{display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},               
					{display:'관리 년도', name:'mngYear',width:100, sortable:false,align:'center'},                   
					{display:'관리 번호', name:'mngNo',width:100, sortable:false,align:'center'},                     
					{display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'},
					{display:'시설 구분', name:'fcltySe',width:100, sortable:false,align:'center'},
					{display:'업체 명', name:'entrpsNm',width:100, sortable:false,align:'center'},
					{display:'고지기간 FROM', name:'nticPdFrom',width:100, sortable:false,align:'center'},
					{display:'고지기간 TO', name:'constPerTo',width:100, sortable:false,align:'center'},
					{display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
					{display:'사용료', name:'fee',width:100, sortable:false,align:'center'},
					{display:'비고', name:'rm',width:100, sortable:false,align:'center'},
					{display:'연체 번호', name:'arrrgNo',width:100, sortable:false,align:'center'},
					{display:'연체 금액', name:'arrrgAmt',width:100, sortable:false,align:'center'},
					{display:'의뢰 순번', name:'reqestSeq',width:100, sortable:false,align:'center'}
                    ],
        usepager: true,
        useRp: true,
        rp: 24,
        showTableToggleBtn: false,
        height: '290',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt);
            
            return data;
        }
    });

    this.$("#assetRentFeePayList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeePayListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

        if(row!=null) {
        	module.$('#chrgeKnd').val(row['chrgeKnd']);                   
        	module.$('#accnutYear').val(row['accnutYear']);               
        	module.$('#nticno').val(row['nticno']);                   
        	module.$('#accnutSeCd').val(row['accnutSeCd']);               
        	module.$('#entrpscd').val(row['entrpscd']);                   
        	module.$('#nticAmt').val(row['nticAmt']);                     
        	module.$('#nticDt').val(row['nticDt']);                   
        	module.$('#nhtIsueYn').val(row['nhtIsueYn']);                 
        	module.$('#computDtls').val(row['computDtls']);               
        	module.$('#payTmlmt').val(row['payTmlmt']);                   
        	module.$('#frstNticDt').val(row['frstNticDt']);               
        	module.$('#rcivTransfrSttusCd').val(row['rcivTransfrSttusCd']);       
        	module.$('#rcivDt').val(row['rcivDt']);                   
        	module.$('#rcivSe').val(row['rcivSe']);                   
        	module.$('#incpctyCd').val(row['incpctyCd']);                 
        	module.$('#overrpayAmt').val(row['overrpayAmt']);                 
        	module.$('#tmprIsuNo').val(row['tmprIsuNo']);                 
        	module.$('#dscntAmt').val(row['dscntAmt']);                   
        	module.$('#dscntRsn').val(row['dscntRsn']);                   
        	module.$('#dscntCd').val(row['dscntCd']);                     
        	module.$('#rcivSeNm').val(row['rcivSeNm']);               
        	module.$('#fnncInsttRcivDt').val(row['fnncInsttRcivDt']);         
        	module.$('#postNticEnnc').val(row['postNticEnnc']);           
        	module.$('#npymnRsnCd').val(row['npymnRsnCd']);               
        	module.$('#elctrnNticResult').val(row['elctrnNticResult']);       
        	module.$('#elctrnNticInfoInqireDt').val(row['elctrnNticInfoInqireDt']);  
        	module.$('#excclcYn').val(row['excclcYn']);                   
        	module.$('#vat').val(row['vat']);                         
        	module.$('#vatYn').val(row['vatYn']);                     
        	module.$('#prcepturSe').val(row['prcepturSe']);               
        	module.$('#giroRcivPlace').val(row['giroRcivPlace']);             
        	module.$('#giroRcivSe').val(row['giroRcivSe']);               
        	module.$('#cmsn').val(row['cmsn']);                       
        	module.$('#closYn').val(row['closYn']);                   
        	module.$('#deptcd').val(row['deptcd']);                   
        	module.$('#charger').val(row['charger']);                     
        	module.$('#opertSe').val(row['opertSe']);                     
        	module.$('#orginlNticChrgeKnd').val(row['orginlNticChrgeKnd']);       
        	module.$('#orginlNticAccnutYear').val(row['orginlNticAccnutYear']);   
        	module.$('#orginlNticNo').val(row['orginlNticNo']);           
        	module.$('#elctrnTaxbilIsuYn').val(row['elctrnTaxbilIsuYn']);         
        	module.$('#beginDt').val(row['beginDt']);                     
        	module.$('#endDt').val(row['endDt']);                     
        	module.$('#nticMth').val(row['nticMth']);                     
        	module.$('#regUsr').val(row['regUsr']);                   
        	module.$('#registDt').val(row['registDt']);                   
        	module.$('#updUsr').val(row['updUsr']);                   
        	module.$('#updtDt').val(row['updtDt']);                   
        	module.$('#nticCnt').val(row['nticCnt']);                     
        	module.$('#prtAtCode').val(row['prtAtCode']);                 
        	module.$('#mngYear').val(row['mngYear']);                     
        	module.$('#mngNo').val(row['mngNo']);                     
        	module.$('#mngCnt').val(row['mngCnt']);                   
        	module.$('#fcltySe').val(row['fcltySe']); 
        	module.$('#entrpsNm').val(row['entrpsNm']); 
        	module.$('#nticPdFrom').val(row['nticPdFrom']); 
        	module.$('#constPerTo').val(row['constPerTo']); 
        	module.$('#olnlp').val(row['olnlp']); 
        	module.$('#fee').val(row['fee']); 
        	module.$('#rm').val(row['rm']); 
        	module.$('#arrrgNo').val(row['arrrgNo']); 
        	module.$('#arrrgAmt').val(row['arrrgAmt']); 
        	module.$('#reqestSeq').val(row['reqestSeq']); 
            //throw 0;
        }
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
            this.$('#assetRentFeePayList').flexOptions({params:searchOpt}).flexReload();

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
    this.$('#assetRentFeePayList').flexOptions({params:searchOpt}).flexReload();
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
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeePayListTab" class="emdTabPanel" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">일반부두납부 목록</a></li>
                <li><a href="#tabs2" class="emdTab">일반부두납부 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetRentFeePayList" style="display:none"></table>
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
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <form id="gamAssetRentFeePayForm">
                        <input type="hidden" id="cmd"/>
                        
                        <table>
                            <tr>
							    <th><span class="label">요금 종류</span></th>
							    <td><input type="text" size="10" id="chrgeKnd"/></td>
							    <th><span class="label">회계 년도</span></th>
							    <td><input type="text" size="10" id="accnutYear"/></td>
							</tr>
							<tr>
							    <th><span class="label">고지번호</span></th>
							    <td><input type="text" size="10" id="nticno"/></td>
							    <th><span class="label">회계 구분 코드</span></th>
							    <td><input type="text" size="10" id="accnutSeCd"/></td>
							</tr>
							<tr>
							    <th><span class="label">업체코드</span></th>
							    <td><input type="text" size="10" id="entrpscd"/></td>
							    <th><span class="label">고지 금액</span></th>
							    <td><input type="text" size="10" id="nticAmt"/></td>
							</tr>
							<tr>
							    <th><span class="label">고지 일자</span></th>
							    <td><input type="text" size="10" id="nticDt"/></td>
							    <th><span class="label">고지서 발부 여부</span></th>
							    <td><input type="text" size="10" id="nhtIsueYn"/></td>
							</tr>
							<tr>
							    <th><span class="label">산출 내역</span></th>
							    <td><input type="text" size="10" id="computDtls"/></td>
							    <th><span class="label">납부 기한</span></th>
							    <td><input type="text" size="10" id="payTmlmt"/></td>
							</tr>
							<tr>
							    <th><span class="label">최초 고지 일자</span></th>
							    <td><input type="text" size="10" id="frstNticDt"/></td>
							    <th><span class="label">수납 이체 상태 코드</span></th>
							    <td><input type="text" size="10" id="rcivTransfrSttusCd"/></td>
							</tr>
							<tr>
							    <th><span class="label">수납 일자</span></th>
							    <td><input type="text" size="10" id="rcivDt"/></td>
							    <th><span class="label">수납 구분</span></th>
							    <td><input type="text" size="10" id="rcivSe"/></td>
							</tr>
							<tr>
							    <th><span class="label">불능 코드</span></th>
							    <td><input type="text" size="10" id="incpctyCd"/></td>
							    <th><span class="label">과오납 금액</span></th>
							    <td><input type="text" size="10" id="overrpayAmt"/></td>
							</tr>
							<tr>
							    <th><span class="label">임시 발행 번호</span></th>
							    <td><input type="text" size="10" id="tmprIsuNo"/></td>
							    <th><span class="label">할인 금액</span></th>
							    <td><input type="text" size="10" id="dscntAmt"/></td>
							</tr>
							<tr>
							    <th><span class="label">할인 사유</span></th>
							    <td><input type="text" size="10" id="dscntRsn"/></td>
							    <th><span class="label">할인 코드</span></th>
							    <td><input type="text" size="10" id="dscntCd"/></td>
							</tr>
							<tr>
							    <th><span class="label">수납 구분명  </span></th>
							    <td><input type="text" size="10" id="rcivSeNm"/></td>
							    <th><span class="label">금융 기관 수납 일자</span></th>
							    <td><input type="text" size="10" id="fnncInsttRcivDt"/></td>
							</tr>
							<tr>
							    <th><span class="label">우편 고지 유무</span></th>
							    <td><input type="text" size="10" id="postNticEnnc"/></td>
							    <th><span class="label">불납 사유 코드</span></th>
							    <td><input type="text" size="10" id="npymnRsnCd"/></td>
							</tr>
							<tr>
							    <th><span class="label">전자 고지 결과</span></th>
							    <td><input type="text" size="10" id="elctrnNticResult"/></td>
							    <th><span class="label">전자 고지 정보 조회 일자</span></th>
							    <td><input type="text" size="10" id="elctrnNticInfoInqireDt"/></td>
							</tr>
							<tr>
							    <th><span class="label">정산 여부</span></th>
							    <td><input type="text" size="10" id="excclcYn"/></td>
							    <th><span class="label">부가세</span></th>
							    <td><input type="text" size="10" id="vat"/></td>
							</tr>
							<tr>
							    <th><span class="label">부가세 여부</span></th>
							    <td><input type="text" size="10" id="vatYn"/></td>
							    <th><span class="label">징수관 구분</span></th>
							    <td><input type="text" size="10" id="prcepturSe"/></td>
							</tr>                                                 
							<tr>
							    <th><span class="label">지로 수납처</span></th>
							    <td><input type="text" size="10" id="giroRcivPlace"/></td>
							    <th><span class="label">지로 수납 구분</span></th>
							    <td><input type="text" size="10" id="giroRcivSe"/></td>
							</tr>   
							<tr>
							    <th><span class="label">수수료</span></th>
							    <td><input type="text" size="10" id="cmsn"/></td>
							    <th><span class="label">마감 여부</span></th>
							    <td><input type="text" size="10" id="closYn"/></td>
							</tr>
							<tr>
							    <th><span class="label">부서코드</span></th>
							    <td><input type="text" size="10" id="deptcd"/></td>
							    <th><span class="label">담당자</span></th>
							    <td><input type="text" size="10" id="charger"/></td>
							</tr>
							<tr>
							    <th><span class="label">작업 구분</span></th>
							    <td><input type="text" size="10" id="opertSe"/></td>
							    <th><span class="label">원고지 요금 종류</span></th>
							    <td><input type="text" size="10" id="orginlNticChrgeKnd"/></td>
							</tr>
							<tr>
							    <th><span class="label">원고지 회계 년도</span></th>
							    <td><input type="text" size="10" id="orginlNticAccnutYear"/></td>
							    <th><span class="label">원고지 번호</span></th>
							    <td><input type="text" size="10" id="orginlNtic_no"/></td>
							</tr>
							<tr>
							    <th><span class="label">전자 세금 계산서 발행 여부</span></th>
							    <td><input type="text" size="10" id="elctrnTaxbilIsuYn"/></td>
							    <th><span class="label">시작일</span></th>
							    <td><input type="text" size="10" id="beginDt"/></td>
							</tr>
							<tr>
							    <th><span class="label">종료일</span></th>
							    <td><input type="text" size="10" id="endDt"/></td>
							    <th><span class="label">고지 방법</span></th>
							    <td><input type="text" size="10" id="nticMth"/></td>
							</tr>                                                 
							<tr>
							    <th><span class="label">등록자</span></th>
							    <td><input type="text" size="10" id="regUsr"/></td>
							    <th><span class="label">등록일시</span></th>
							    <td><input type="text" size="10" id="registDt"/></td>
							</tr>
							<tr>
							    <th><span class="label">수정자</span></th>
							    <td><input type="text" size="10" id="updUsr"/></td>
							    <th><span class="label">수정일시</span></th>
							    <td><input type="text" size="10" id="updtDt"/></td>
							</tr>
							<tr>
							    <th><span class="label">고지 횟수</span></th>
							    <td><input type="text" size="10" id="nticCnt"/></td>
							    <th><span class="label">항코드</span></th>
							    <td><input type="text" size="10" id="prtAtCode"/></td>
							</tr>
							<tr>
							    <th><span class="label">관리 년도</span></th>
							    <td><input type="text" size="10" id="mngYear"/></td>
							    <th><span class="label">관리 번호</span></th>
							    <td><input type="text" size="10" id="mngNo"/></td>
							</tr>
							<tr>
							    <th><span class="label">관리 횟수</span></th>
							    <td><input type="text" size="10" id="mngCnt"/></td>
							    <th><span class="label">시설 구분 </span></th>
							    <td><input type="text" size="10" id="fcltySe"/></td>
							</tr>
							<tr>
							    <th><span class="label">업체 명</span></th>
							    <td><input type="text" size="10" id="entrpsNm"/></td>
							    <th><span class="label">고지기간 FROM</span></th>
							    <td><input type="text" size="10" id="nticPdFrom"/></td>
							</tr>
							<tr>
							    <th><span class="label">고지기간 TO</span></th>
							    <td><input type="text" size="10" id="constPerTo"/></td>
							    <th><span class="label">공시지가</span></th>
							    <td><input type="text" size="10" id="olnlp"/></td>
							</tr>
							<tr>
							    <th><span class="label">사용료</span></th>
							    <td><input type="text" size="10" id="fee"/></td>
							    <th><span class="label">비고</span></th>
							    <td><input type="text" size="10" id="rm"/></td>
							</tr>
							<tr>
							    <th><span class="label">연체 번호</span></th>
							    <td><input type="text" size="10" id="arrrgNo"/></td>
							    <th><span class="label">연체 금액</span></th>
							    <td><input type="text" size="10" id="arrrgAmt"/></td>
							</tr>
							<tr>
							    <th><span class="label">의뢰 순번</span></th>
							    <td><input type="text" size="10" id="reqestSeq"/></td>
							    <th><span class="label"></span></th>
							    <td></td>
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