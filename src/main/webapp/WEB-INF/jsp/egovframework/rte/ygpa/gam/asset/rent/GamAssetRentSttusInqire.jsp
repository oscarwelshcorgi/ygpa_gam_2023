<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentSttusInqire.jsp
  * @Description : 자산임대현황조회
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAssetRentSttusInqireModule() {}

GamAssetRentSttusInqireModule.prototype = new EmdModule(1050, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentSttusInqireModule.prototype.loadComplete = function() {

    // 테이블 설정
    this.$("#assetRentMngtList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/selectAssetRentSttusInqireList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'관리 번호(조합)', name:'rentMngNo',width:100, sortable:false,align:'center'},
                    {display:'관리년도', name:'mngYear',width:100, sortable:false,align:'center'},
                    {display:'관리 번호', name:'mngNo',width:60, sortable:false,align:'center'},                                                
                    {display:'관리 횟수', name:'mngCnt',width:60, sortable:false,align:'center'},                                              
                    {display:'업체코드', name:'entrpscd',width:60, sortable:false,align:'center'},  
                    {display:'업체명', name:'entrpsNm',width:60, sortable:false,align:'center'},
                    {display:'날짜', name:'dt',width:60, sortable:false,align:'center'},                                                                     
                    {display:'신청 구분 코드', name:'reqstSeCd',width:60, sortable:false,align:'center'},                                 
                    {display:'총 면적', name:'grAr',width:60, sortable:false,align:'center'},                                                           
                    {display:'총 사용료', name:'grFee',width:60, sortable:false,align:'center'},                                                        
                    {display:'고지 방법', name:'nticMth',width:60, sortable:false,align:'center'},
                    {display:'고지 방법명', name:'nticMthNm',width:60, sortable:false,align:'center'},
                    {display:'최초 허가 일자', name:'frstPrmisnDt',width:60, sortable:false,align:'center'},                           
                    {display:'허가 일자', name:'prmisnDt',width:60, sortable:false,align:'center'},                                          
                    {display:'허가 여부', name:'prmisnYn',width:60, sortable:false,align:'center'},                                          
                    {display:'총 사용 기간 FROM', name:'grUsagePdFrom',width:60, sortable:false,align:'center'},                     
                    {display:'총 사용 기간 TO', name:'grUsagePdTo',width:60, sortable:false,align:'center'},                           
                    {display:'문서 번호', name:'docNo',width:60, sortable:false,align:'center'},                                                
                    {display:'비고', name:'rm',width:60, sortable:false,align:'center'},                                                         
                    {display:'코멘트', name:'cmt',width:60, sortable:false,align:'center'},                                                          
                    {display:'기타', name:'etc',width:60, sortable:false,align:'center'},                                                       
                    {display:'등록자', name:'regUsr',width:60, sortable:false,align:'center'},                                                
                    {display:'등록일시', name:'registDt',width:60, sortable:false,align:'center'},                                                       
                    {display:'수정자', name:'updUsr',width:60, sortable:false,align:'center'},                                                
                    {display:'수정일시', name:'updtDt',width:60, sortable:false,align:'center'},                                                           
                    {display:'총 감면 사용료', name:'grRdcxptFee',width:60, sortable:false,align:'center'},                                         
                    {display:'GIS 코드', name:'gisCd',width:60, sortable:false,align:'center'},                                               
                    {display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'}
                    ],
        usepager: true,
        useRp: true,
        rp: 24,
        showTableToggleBtn: false,
        height: '290',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#totalArea').val(data.sumGrAr);
            module.$('#totalUse').val(data.sumGrFee);
            
            return data;
        }
    });
    
    // 테이블 설정
    this.$("#assetRentDetailList").flexigrid({  
        module: this,
        url: '<c:url value="/asset/rent/selectAssetRentSttusInqireDetailList.do" />',  
        dataType: 'json',
        colModel : [
                    {display:'자산 사용 순번', name:'assetsUsageSeq',width:100, sortable:false,align:'center'},                          
                    {display:'GIS 자산 코드', name:'gisAssetsCd',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 SUB 코드', name:'gisAssetsSubCd',width:130, sortable:false,align:'center'},
                    {display:'사용 면적', name:'usageAr',width:100, sortable:false,align:'center'},
                    {display:'사용 기간 FROM', name:'usagePdFrom',width:100, sortable:false,align:'center'},
                    {display:'사용 기간 TO', name:'usagePdTo',width:100, sortable:false,align:'center'},
                    {display:'사용 목적', name:'usagePurps',width:100, sortable:false,align:'center'},
                    {display:'사용 내역', name:'usageDtls',width:100, sortable:false,align:'center'},
                    {display:'사용 용도 코드', name:'usagePrposCd',width:100, sortable:false,align:'center'},
                    {display:'사용 용도 코드명', name:'usagePrposCdNm',width:100, sortable:false,align:'center'},
                    {display:'면제 구분', name:'exemptSe',width:100, sortable:false,align:'center'},
                    {display:'면제 구분명', name:'exemptSeNm',width:100, sortable:false,align:'center'},
                    {display:'면제 사유 코드', name:'exemptRsnCd',width:100, sortable:false,align:'center'},
                    {display:'면제 사유 코드명', name:'exemptRsnCdNm',width:100, sortable:false,align:'center'},
                    {display:'면제 사유', name:'exemptRsn',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 FROM', name:'exemptPdFrom',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 TO', name:'exemptPdTo',width:100, sortable:false,align:'center'},
                    {display:'산출 내역', name:'computDtls',width:100, sortable:false,align:'center'},
                    {display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
                    {display:'적용 요율', name:'applcTariff',width:100, sortable:false,align:'center'},
                    {display:'적용 방법', name:'applcMth',width:100, sortable:false,align:'center'},
                    {display:'포장 구분', name:'packSe',width:100, sortable:false,align:'center'},
                    {display:'포장 구분명', name:'packSeNm',width:100, sortable:false,align:'center'},
                    {display:'업체 구분', name:'entrpsSe',width:100, sortable:false,align:'center'},
                    {display:'업체 구분명', name:'entrpsSeNm',width:100, sortable:false,align:'center'},
                    {display:'사용료 계산 구분', name:'feeCalcSe',width:100, sortable:false,align:'center'},
                    {display:'사용료 계산 구분명', name:'feeCalcSeNm',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료 계산 구분', name:'rdcxptFeeCalcSe',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료 계산 구분명', name:'rdcxptFeeCalcSeNm',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료', name:'rdcxptFee',width:100, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'center'},
                    {display:'해지 일자', name:'trmnatDt',width:100, sortable:false,align:'center'},
                    {display:'해지 사유', name:'trmnatRsn',width:100, sortable:false,align:'center'},
                    {display:'GIS 코드', name:'gisCd',width:100, sortable:false,align:'center'},
                    {display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},
                    {display:'등록일시', name:'registDt',width:100, sortable:false,align:'center'},
                    {display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},
                    {display:'수정일시', name:'updtDt',width:100, sortable:false,align:'center'},
                    {display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},
                    {display:'GIS 자산 항코드', name:'gisAssetsPrtAtCode',width:100, sortable:false,align:'center'},
                    {display:'관리 년도', name:'mngYear',width:100, sortable:false,align:'center'},
                    {display:'관리 번호', name:'mngNo',width:100, sortable:false,align:'center'},
                    {display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'}
                    ],
        usepager: true,
        useRp: true,
        rp: 24,
        showTableToggleBtn: false,
        height: '140'
    });

    this.$("#assetRentMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

        if(row!=null) {
            module.$('#cmd').val('modify');  // 더블클릭한 아이템을 수정한다
            module.$('#prtAtCode').val(row['prtAtCode']);
            module.$('#prtAtCodeNm').val(row['prtAtCodeNm']);
            module.$('#mngYear').val(row['mngYear']);
            module.$('#mngNo').val(row['mngNo']);
            module.$('#mngCnt').val(row['mngCnt']);
            module.$('#entrpscd').val(row['entrpscd']);
            module.$('#entrpsNm').val(row['entrpsNm']);
            module.$('#dt').val(row['dt']);
            module.$('#reqstSeCd').val(row['reqstSeCd']);
            module.$('#grAr').val(row['grAr']);
            module.$('#grFee').val(row['grFee']);
            module.$('#nticMth').val(row['nticMth']);
            module.$('#nticMthNm').val(row['nticMthNm']);            
            module.$('#frstPrmisnDt').val(row['frstPrmisnDt']);
            module.$('#prmisnDt').val(row['prmisnDt']);
            module.$('#prmisnYn').val(row['prmisnYn']);
            module.$('#grUsagePdFrom').val(row['grUsagePdFrom']);
            module.$('#grUsagePdTo').val(row['grUsagePdTo']);
            module.$('#docNo').val(row['docNo']);
            module.$('#rm').val(row['rm']);
            module.$('#cmt').val(row['cmt']);
            module.$('#etc').val(row['etc']);
            module.$('#grRdcxptFee').val(row['grRdcxptFee']);
            module.$('#gisCd').val(row['gisCd']);
            module.$('#deptcd').val(row['deptcd']);
            module.$('#regUsr').val(row['regUsr']);
            module.$('#registDt').val(row['registDt']);
            module.$('#updUsr').val(row['updUsr']);
            module.$('#updtDt').val(row['updtDt']);
            //throw 0;
            
            // 해당하는 자산임대상세 목록을 불러온다.
            module.$('#detailPrtAtCode').val(row['prtAtCode']);
            module.$('#detailMngYear').val(row['mngYear']);
            module.$('#detailMngNo').val(row['mngNo']);
            module.$('#detailMngCnt').val(row['mngCnt']);
            
            var searchOpt=module.makeFormArgs('#gamAssetRentForm');
            module.$('#assetRentDetailList').flexOptions({params:searchOpt}).flexReload();
        }
    });
    
    this.$("#assetRentDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentListTab").tabs("option", {active: 2});    // 탭을 전환 한다.

        if(row!=null) {
            //module.$('#cmd').val('modify');  // 더블클릭한 아이템을 수정한다
            module.$('#detailCmd').val('modify');
            module.$('#assetsUsageSeq').val(row['assetsUsageSeq']);
            module.$('#detailMngYear').val(row['mngYear']);
            module.$('#detailMngNo').val(row['mngNo']);
            module.$('#detailMngCnt').val(row['mngCnt']);
            module.$('#detailPrtAtCode').val(row['prtAtCode']);
            module.$('#gisAssetsCd').val(row['gisAssetsCd']);                                                                    
            module.$('#gisAssetsSubCd').val(row['gisAssetsSubCd']);     
            module.$('#usageAr').val(row['usageAr']);            
            module.$('#usagePdFrom').val(row['usagePdFrom']);       
            module.$('#usagePdTo').val(row['usagePdTo']);          
            module.$('#usagePurps').val(row['usagePurps']);         
            module.$('#usageDtls').val(row['usageDtls']);          
            module.$('#usagePrposCd').val(row['usagePrposCd']);
            module.$('#usagePrposCdNm').val(row['usagePrposCdNm']); 
            module.$('#exemptSe').val(row['exemptSe']);
            module.$('#exemptSeNm').val(row['exemptSeNm']);
            module.$('#exemptRsnCd').val(row['exemptRsnCd']);        
            module.$('#exemptRsnCdNm').val(row['exemptRsnCdNm']); 
            module.$('#exemptRsn').val(row['exemptRsn']);          
            module.$('#exemptPdFrom').val(row['exemptPdFrom']);       
            module.$('#exemptPdTo').val(row['exemptPdTo']);         
            module.$('#computDtls').val(row['computDtls']);         
            module.$('#olnlp').val(row['olnlp']);              
            module.$('#applcTariff').val(row['applcTariff']);        
            module.$('#applcMth').val(row['applcMth']);      
            module.$('#packSe').val(row['packSe']);
            module.$('#packSeNm').val(row['packSeNm']);
            module.$('#entrpsSe').val(row['entrpsSe']);
            module.$('#entrpsSeNm').val(row['entrpsSeNm']);
            module.$('#feeCalcSe').val(row['feeCalcSe']);
            module.$('#feeCalcSeNm').val(row['feeCalcSeNm']); 
            module.$('#rdcxptFeeCalcSe').val(row['rdcxptFeeCalcSe']);
            module.$('#rdcxptFeeCalcSeNm').val(row['rdcxptFeeCalcSeNm']);
            module.$('#rdcxptFee').val(row['rdcxptFee']);          
            module.$('#fee').val(row['fee']);                
            module.$('#trmnatDt').val(row['trmnatDt']);           
            module.$('#trmnatRsn').val(row['trmnatRsn']);          
            module.$('#detailGisCd').val(row['gisCd']);              
            module.$('#detailUpdUsr').val(row['updUsr']);             
            module.$('#gisAssetsPrtAtCode').val(row['gisAssetsPrtAtCode']);
        }
    });
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentSttusInqireModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
            this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
            
            break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts;
            
            this.doExecuteDialog('selectEntrpsInfoPopupInqire', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;    
    }
};

GamAssetRentSttusInqireModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentSttusInqireModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetRentSttusInqireModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
        break;
    case 'tabs3':
        break;
    }
};

//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamAssetRentSttusInqireModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
     case 'selectEntrpsInfoPopupInqire':
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
var module_instance = new GamAssetRentSttusInqireModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentMngtSearchForm">
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
                            <th>신청구분</th>
                            <td>
                                <select id="sReqstSeCd">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${reqstCdList}" var="reqstCdItem">
                                        <option value="${reqstCdItem.code }">${reqstCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfo">업체</button>
                            </td>
                            <th>사용용도</th>
                            <td>
                                <select id="sUsagePrposCd">
                                    <option value="" selected="selected">선택</option>
                                    <c:forEach  items="${usagePrposCdList}" var="usagePrposCdItem">
                                        <option value="${usagePrposCdItem.code }">${usagePrposCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td rowSpan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" size="4"> <input id="sMngNo" type="text" size="3"> <input id="sMngCnt" type="text" size="2">
                            </td>
                            <th>승낙구분</th>
                            <td width="200px">
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">선택</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sGrUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sGrUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sGrAr" type="text" size="5">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel">
        <div id="assetRentListTab" class="emdTabPanel" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산임대 조회내역</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대 상세조회 목록내역</a></li>
                <li><a href="#tabs3" class="emdTab">자산임대 상세조회내역</a></li>
            </ul>
            
            <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <div style="width: 100%; height: 100%; overflow:auto">
                        <table id="assetRentMngtList" style="display:none"></table>
                </div>
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">             
				                                            합계
				                                            자료수 <input id="totalResultCnt" size="7" readonly>
				                                            총면적 <input id="totalArea" type="text" size="7" readonly>
				                                            총사용료 <input id="totalUse" type="text" size="7" readonly>원
                               </form>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                
                <div class="emdControlPanel"></div>
                    <form id="gamAssetRentForm">
                        <input type="hidden" id="cmd"/>
                        <input type="hidden" id="mngYear"/>
                        <input type="hidden" id="mngNo"/>
                        <input type="hidden" id="mngCnt"/>
                        
                        
                        <table>
                            <tr>
                                <th><span class="label">항코드</span></th>
                                <td>
                                    <input type="hidden" id="prtAtCode"/>
	                                <input type="text" size="5" id="prtAtCodeNm" maxlength="10"/>
                                </td>
                                <th><span class="label">업체코드</span></th>
                                <td>
                                    <input type="text" size="5" id="entrpscd" maxlength="10"/>
                                    <input type="text" size="5" id="entrpsNm" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">총 면적</span></th>
                                <td><input type="text" size="10" id="grAr"/></td>
                                <th><span class="label">총 사용료</span></th>
                                <td><input type="text" size="10" id="grFee"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">고지 방법</span></th>
                                <td>
                                    <input type="hidden" id="nticMth"/>
                                    <input type="text" size="10" id="nticMthNm"/>
                                </td>
                                <th><span class="label">최초 허가 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="frstPrmisnDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">허가 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="prmisnDt"></td>
                                <th><span class="label">허가 여부</span></th>
                                <td>
                                    <input type="text" size="10" id="prmisnYn"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">문서 번호</span></th>
                                <td><input type="text" size="10" id="docNo"/></td>
                                <th><span class="label">비고</span></th>
                                <td><input type="text" size="10" id="rm"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">코멘트</span></th>
                                <td><input type="text" size="10" id="cmt"/></td>
                                <th><span class="label">기타</span></th>
                                <td><input type="text" size="10" id="etc"/></td>
                            </tr> 
                            
                            <tr>
                                <th><span class="label">총 감면 사용료</span></th>
                                <td><input type="text" size="10" id="grRdcxptFee"/></td>
                                <th><span class="label">GIS 코드</span></th>
                                <td>
                                    <input type="text" size="10" id="gisCd"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">신청일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="dt"/></td>
                                <th><span class="label">총 사용 기간 FROM</span></th>
                                <td><input type="text" class="emdcal" size="10" id="grUsagePdFrom"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">총 사용 기간 TO</span></th>
                                <td><input type="text" class="emdcal" size="10" id="grUsagePdTo"/></td>
                                <th><span class="label">등록자</span></th>
                                <td><input type="text" size="10" id="regUsr"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">등록일시</span></th>
                                <td><input type="text" size="10" id="registDt"/></td>
                                <th><span class="label">수정자</span></th>
                                <td><input type="text" size="10" id="updUsr"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">수정일시</span></th>
                                <td><input type="text" size="10" id="updtDt"/></td>
                                <th><span class="label">부서코드</span></th>
                                <td><input type="text" size="10" id="deptcd"/></td>
                            </tr>
                            
                        </table>
                    </form>
                 
                 <table>
                    <tr>
                        <td height="30"></td>
                    </tr>
                 </table>
                 
                 <table id="assetRentDetailList" style="display:none"></table>
            </div>
            
            <div id="tabs3" class="emdTabPage" style="overflow: scroll;">
                
                <div class="emdControlPanel"></div>
                    <form id="gamAssetRentDetailForm">

                        <input type="hidden" id="detailCmd"/>
                        <input type="hidden" id="detailPrtAtCode"/>
                        <input type="hidden" id="detailMngYear"/>
                        <input type="hidden" id="detailMngNo"/>
                        <input type="hidden" id="detailMngCnt"/>
                        <input type="hidden" id="detailPrmisnYn"/>

                        <table>
                            <tr>
                                <th><span class="label">자산사용순번</span></th>
                                <td><input type="text" size="10" id="assetsUsageSeq" readonly/>
                                </td>
                                <th><span class="label">GIS 자산 SUB 코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsSubCd"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">사용 면적</span></th>
                                <td><input type="text" size="10" id="usageAr"/></td>
                                <th><span class="label">사용 기간 FROM</span></th>
                                <td><input type="text" class="emdcal" size="10" id="usagePdFrom"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">사용 기간 TO</span></th>
                                <td><input type="text" class="emdcal" size="10" id="usagePdTo"/></td>
                                <th><span class="label">사용 목적</span></th>
                                <td><input type="text" size="10" id="usagePurps"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">사용 내역</span></th>
                                <td><input type="text" size="10" id="usageDtls"/></td>
                                <th><span class="label">사용 용도 코드</span></th>
                                <td>
                                    <input type="hidden" id="usagePrposCd"/>
                                    <input type="text" size="10" id="usagePrposCdNm"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">면제 구분</span></th>
                                <td>
                                    <input type="hidden" id="exemptSe"/>
                                    <input type="text" size="10" id="exemptSeNm"/>
                                </td>
                                <th><span class="label">면제 사유 코드</span></th>
                                <td>
                                    <input type="hidden" id="exemptRsnCd"/>
                                    <input type="text" size="10" id="exemptRsnCdNm"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">면제 사유</span></th>
                                <td><input type="text" size="10" id="exemptRsn"/></td>
                                <th><span class="label">면제 기간 FROM</span></th>
                                <td><input type="text" class="emdcal" size="10" id="exemptPdFrom"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">면제 기간 TO</span></th>
                                <td><input type="text" class="emdcal" size="10" id="exemptPdTo"/></td>
                                <th><span class="label">산출 내역</span></th>
                                <td><input type="text" size="10" id="computDtls"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">공시지가</span></th>
                                <td><input type="text" size="10" id="olnlp"/></td>
                                <th><span class="label">적용 요율</span></th>
                                <td><input type="text" size="10" id="applcTariff"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">적용 방법</span></th>
                                <td><input type="text" size="10" id="applcMth"/></td>
                                <th><span class="label">포장 구분</span></th>
                                <td>
                                    <input type="hidden" id="packSe"/>
                                    <input type="text" size="10" id="packSeNm"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">업체 구분</span></th>
                                <td>
                                    <input type="hidden" id="entrpsSe"/>
                                    <input type="text" size="10" id="entrpsSeNm"/>
                                </td>
                                <th><span class="label">사용료 계산 구분</span></th>
                                <td>
                                    <input type="hidden" id="feeCalcSe"/>
                                    <input type="text" size="10" id="feeCalcSeNm"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">감면 사용료 계산 구분</span></th>
                                <td>
                                    <input type="hidden" id="rdcxptFeeCalcSe"/>
                                    <input type="text" size="10" id="rdcxptFeeCalcSeNm"/>
                                </td>
                                <th><span class="label">감면 사용료</span></th>
                                <td><input type="text" size="10" id="rdcxptFee"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">사용료</span></th>
                                <td><input type="text" size="10" id="fee"/></td>
                                <th><span class="label">해지 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="trmnatDt"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">해지 사유</span></th>
                                <td><input type="text" size="10" id="trmnatRsn"/></td>
                                <th><span class="label">GIS 코드</span></th>
                                <td><input type="text" size="10" id="detailGisCd"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">등록자</span></th>
                                <td><input type="text" size="10" id="detailRegUsr"/></td>
                                <th><span class="label">등록일시</span></th>
                                <td><input type="text" size="10" id="detailRegistDt"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">수정자</span></th>
                                <td><input type="text" size="10" id="detailUpdUsr"/></td>
                                <th><span class="label">수정일시</span></th>
                                <td><input type="text" size="10" id="updtDt"/></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">GIS 자산 항코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsPrtAtCode"/></td>
                                <th><span class="label">xxx</span></th>
                                <td><input type="text" size="10" id="xxx"/></td>
                            </tr>
                            
                        </table>
                    </form>
                 
            </div>
        </div>
    </div>
</div>