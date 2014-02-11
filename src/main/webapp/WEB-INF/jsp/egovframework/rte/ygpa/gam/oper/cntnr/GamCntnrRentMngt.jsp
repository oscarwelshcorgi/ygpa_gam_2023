<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCntnrRentMngt.jsp
  * @Description : 컨테이너부두임대목록관리 (컨테이너부두/컨테이너부두/컨테이너부두임대목록관리)
  * @Modification Information
  * 
  *   수정일          수정자                   수정내용 
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
 function GamCntnrRentMngtModule() {}

 GamCntnrRentMngtModule.prototype = new EmdModule(910, 550);

 // 페이지가 호출 되었을때 호출 되는 함수
 GamCntnrRentMngtModule.prototype.loadComplete = function() {

     // 테이블 설정
     this.$("#operResultList").flexigrid({
         module: this,
         url: '<c:url value="/oper/cntnr/selectCntnrRentMngtList.do" />',
         dataType: 'json',
         colModel : [
                    {display:'항코드', name:'prtAtCode',width:60, sortable:false,align:'center'},
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
                    {display:'부서코드', name:'deptcd',width:60, sortable:false,align:'center'},
                    {display:'납부방법', name:'payMth',width:60, sortable:false,align:'center'}
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
     this.$("#operResultDetailList").flexigrid({  
         module: this,
         url: '<c:url value="/oper/cntnr/selectCntnrRentMngtDetailList.do" />',  
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
                    {display:'면제 구분', name:'exemptSe',width:100, sortable:false,align:'center'},
                    {display:'면제 사유 코드', name:'exemptRsnCd',width:100, sortable:false,align:'center'},
                    {display:'면제 사유', name:'exemptRsn',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 FROM', name:'exemptPdFrom',width:100, sortable:false,align:'center'},
                    {display:'면제 기간 TO', name:'exemptPdTo',width:100, sortable:false,align:'center'},
                    {display:'산출 내역', name:'computDtls',width:100, sortable:false,align:'center'},
                    {display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
                    {display:'적용 요율', name:'applcTariff',width:100, sortable:false,align:'center'},
                    {display:'적용 방법', name:'applcMth',width:100, sortable:false,align:'center'},
                    {display:'포장 구분', name:'packSe',width:100, sortable:false,align:'center'},
                    {display:'업체 구분', name:'entrpsSe',width:100, sortable:false,align:'center'},
                    {display:'사용료 계산 구분', name:'feeCalcSe',width:100, sortable:false,align:'center'},
                    {display:'감면 사용료 계산 구분', name:'rdcxptFeeCalcSe',width:100, sortable:false,align:'center'},
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
                    {display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'},
                    {display:'부두코드', name:'quayCd',width:100, sortable:false,align:'center'}
                     ],
         usepager: true,
         useRp: true,
         rp: 24,
         showTableToggleBtn: false,
         height: '140'
     });

     this.$("#operResultList").on('onItemDoubleClick', function(event, module, row, grid, param) {
         // 이벤트내에선 모듈에 대해 선택한다.
         module.$("#operResultListTab").tabs("option", {active: 1});    // 탭을 전환 한다.

         if(row!=null) {
             module.$('#cmd').val('modify');  // 더블클릭한 아이템을 수정한다
             module.$('#prtAtCode').val(row['prtAtCode']);
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
             module.$('#payMth').val(row['payMth']);
             //throw 0;
             
             // 해당하는 자산임대상세 목록을 불러온다.
             module.$('#detailPrtAtCode').val(row['prtAtCode']);
             module.$('#detailMngYear').val(row['mngYear']);
             module.$('#detailMngNo').val(row['mngNo']);
             module.$('#detailMngCnt').val(row['mngCnt']);
             
             var searchOpt=module.makeFormArgs('#gamOperForm');
             module.$('#operResultDetailList').flexOptions({params:searchOpt}).flexReload();
         }
     });
     
     this.$("#operResultDetailList").on('onItemDoubleClick', function(event, module, row, grid, param) {
         // 이벤트내에선 모듈에 대해 선택한다.
         module.$("#operResultListTab").tabs("option", {active: 2});    // 탭을 전환 한다.

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
             module.$('#exemptSe').val(row['exemptSe']);           
             module.$('#exemptRsnCd').val(row['exemptRsnCd']);        
             module.$('#exemptRsn').val(row['exemptRsn']);          
             module.$('#exemptPdFrom').val(row['exemptPdFrom']);       
             module.$('#exemptPdTo').val(row['exemptPdTo']);         
             module.$('#computDtls').val(row['computDtls']);         
             module.$('#olnlp').val(row['olnlp']);              
             module.$('#applcTariff').val(row['applcTariff']);        
             module.$('#applcMth').val(row['applcMth']);           
             module.$('#packSe').val(row['packSe']);             
             module.$('#entrpsSe').val(row['entrpsSe']);           
             module.$('#feeCalcSe').val(row['feeCalcSe']);          
             module.$('#rdcxptFeeCalcSe').val(row['rdcxptFeeCalcSe']);    
             module.$('#rdcxptFee').val(row['rdcxptFee']);          
             module.$('#fee').val(row['fee']);                
             module.$('#trmnatDt').val(row['trmnatDt']);           
             module.$('#trmnatRsn').val(row['trmnatRsn']);          
             module.$('#detailGisCd').val(row['gisCd']);              
             module.$('#detailUpdUsr').val(row['updUsr']);             
             module.$('#gisAssetsPrtAtCode').val(row['gisAssetsPrtAtCode']);
             module.$('#quayCd').val(row['quayCd']);
         }
     });
 };

 /**
  * 정의 된 버튼 클릭 시
  */
  GamCntnrRentMngtModule.prototype.onButtonClick = function(buttonId) {

     switch(buttonId) {

         // 조회
         case 'searchBtn':
             var searchOpt=this.makeFormArgs('#gamOperSearchForm');
             this.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
             
             break;

         // 최초신청
         case 'addCntnrRentMngtFirst':
             this.$("#operResultListTab").tabs("option", {active: 1});  // 탭을 전환 한다.
             //this.$(":input").val('');
             this.$('#gamOperForm :input').val("");
             this.$("#cmd").val('insert');
             
             break;
             
         // 연장신청
         case 'addCntnrRentMngtRenew':
             var rows = this.$('#operResultList').selectedRows();
             
             if(rows.length>=1) {
                 //this.$('#rPrtAtCode').val(row[0]['prtAtCode']);
                 
                 this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrRentMngtRenew.do" />', rows[0], function(module, result) {
                     
                     if(result.resultCode=='0') {
                         
                         var searchOpt=module.makeFormArgs('#gamOperSearchForm');
                         module.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
                     }

                     alert(result.resultMsg);

                 });
                 //throw 0;

             } else {
                 alert("연장신청할 업체를 선택하십시오.");
             }
             
             break;
         
         // 자산임대 저장
         case 'btnSaveItem':
             var inputVO=this.makeFormArgs('#gamOperForm');
             if(this.$("#cmd").val()=='insert') {
                 
             	this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrRentMngtFirst.do" />', inputVO, function(module, result) {
                     
                     if(result.resultCode=='0') {
                     	var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
                     }
                     
                     alert(result.resultMsg);
                 });
             }
             else {
                 this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrRentMngt.do" />', inputVO, function(module, result) {	
                 	if(result.resultCode=='0') {
                         var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
                     }
                     
                     alert(result.resultMsg);
                 });
             }
             break;        
         
         // 자산임대 취소
         case 'btnCancelItem':
         	this.$('#gamOperForm :input').val("");
             this.$("#cmd").val('insert');
             break;
         
         //자산임대삭제
         case 'btnRemoveItem':
             if( this.$('#cmd').val() == 'modify' ) {
                 this.$('#detailPrmisnYn').val( this.$('#prmisnYn').val() );   
                 
                 var inputVO=this.makeFormArgs('#gamOperForm');
                 
                 this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrRentMngt.do" />', inputVO, function(module, result) {
                     
                     if(result.resultCode=='0') {
                         var searchOpt=module.makeFormArgs('#gamOperSearchForm');
                         module.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
                     }
                     
                     alert(result.resultMsg);
                 });
                 
                 this.$("#operResultListTab").tabs("option", {active: 0});  // 탭을 전환 한다.
                 this.$('#gamOperForm :input').val("");
                 this.$("#cmd").val('insert');
                 
             } else {
                 alert("상세조회 후 삭제가 가능합니다.");
             }
             break;
             
         // 자산임대상세 신규등록
         case 'btnInsertItemDetail':
         	
         	if( this.$('#prtAtCode').val() == '' ) {
         		alert("자산임대상세 조회후 등록이 가능합니다.");
         	} else {
         		this.$("#operResultListTab").tabs("option", {active: 2});  // 탭을 전환 한다.
                 this.$('#gamOperDetailForm :input').val("");
                 
                 this.$("#detailCmd").val('insert');
                 this.$('#detailPrtAtCode').val( this.$('#prtAtCode').val() );   
                 this.$('#detailMngYear').val( this.$('#mngYear').val() );
                 this.$('#detailMngNo').val( this.$('#mngNo').val() );
                 this.$('#detailMngCnt').val( this.$('#mngCnt').val() );
         	}
             
             break;
         
         // 자산임대상세 삭제    
         case 'btnRemoveItemDetail':
             
             var rows = this.$('#operResultDetailList').selectedRows();
             
             if(rows.length>=1) {
                 this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrRentMngtDetail.do" />', rows[0], function(module, result) {
                     if(result.resultCode=='0') {
                         var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultDetailList').flexOptions({params:searchOpt}).flexReload();
                     }

                     alert(result.resultMsg);
                 });
                 
                 this.$('#gamOperDetailForm :input').val("");
                 this.$("#detailCmd").val('insert');
                 
             } else {
                 alert("삭제할 정보를 선택하십시오.");
             }
             
             break;
             
         // 자산임대상세 저장
         case 'btnSaveItemDetail':
             
             var inputVO=this.makeFormArgs('#gamOperDetailForm');
             if(this.$("#detailCmd").val()=='insert') {
                 
                 this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrRentMngtDetail.do" />', inputVO, function(module, result) {
                     
                     if(result.resultCode=='0') {
                         var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultDetailList').flexOptions({params:searchOpt}).flexReload();
                     }
                     
                     alert(result.resultMsg);
                 });
             }
             else {
             	this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrRentMngtDetail.do" />', inputVO, function(module, result) { 
                     if(result.resultCode=='0') {
                     	var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultDetailList').flexOptions({params:searchOpt}).flexReload();
                     }
                     
                     alert(result.resultMsg);
                 });
             }
             break;
             
         case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
             /*
             var opts = {
                 'gisAssetsPrtAtCode': this.$('#prtAtCode').val(),
                 'gisAssetsCd': this.$('#gisAssetsCd').val(),
                 'gisAssetsSubCd': this.$('#gisAssetsSubCd').val()   
             };
             */
             var opts;
             
             this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
             break;    
         
         case 'popupEntrpsInfoInput': // 팝업을 호출한다.(자산임대입력)
             var opts;
             
             this.doExecuteDialog('insertEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
             break;    
                  
         case 'btnPrmisn': // 승낙 (허가)   
             var rows = this.$('#operResultList').selectedRows();
             
             if(rows.length>=1) {
             	var opts = {
                     'prtAtCode': rows[0]['prtAtCode'],
                     'mngYear': rows[0]['mngYear'],
                     'mngNo': rows[0]['mngNo'],
             	    'mngCnt': rows[0]['mngCnt']
                 };

                 this.doExecuteDialog('insertCntnrRentMngtPrmisnPopup', '승낙', '<c:url value="/oper/cntnr/popup/showCntnrRentMngtPrmisn.do"/>', opts);
             	
             } else {
                 alert("목록에서 선택하십시오.");
             }
         
             break;
         
         case 'btnPrmisnCancel': // 승낙취소 (허가취소)
             var rows = this.$('#operResultList').selectedRows();
             
             if(rows.length>=1) {
                 this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrRentMngtPrmisnCancel.do" />', rows[0], function(module, result) {
                     if(result.resultCode=='0') {
                         var searchOpt=module.makeFormArgs('#gamOperForm');
                         module.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
                     }

                     alert(result.resultMsg);
                 });
             } else {
                 alert("목록에서 선택하십시오.");
             }
         
             break;
            
         case 'popupFcltyCd':    //GIS자산코드 팝업을 호출한다.
             var opts;
             
             this.doExecuteDialog('selectAssetsCdRentPopup', '시설 선택', '<c:url value="/popup/showAssetsCd.do"/>', opts);
             break; 
             
     }
 };

 GamCntnrRentMngtModule.prototype.onSubmit = function() {
	    this.loadData();
 };

 GamCntnrRentMngtModule.prototype.loadData = function() {
     var searchOpt=this.makeFormArgs('#gamOperSearchForm');
     this.$('#operResultList').flexOptions({params:searchOpt}).flexReload();
 };

 GamCntnrRentMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
     switch(newTabId) {
     case 'tabs1':
         break;
     case 'tabs2':
         var row = this.$('#operResultList').selectedRows();
         if(row.length==0) {
             this.$('#cmd').val('insert');
         }
         else {
             this.$('#cmd').val('modify');
         }
         break;
     case 'tabs3':
     	var row = this.$('#operResultDetailList').selectedRows();
         if(row.length==0) {
             this.$('#detailCmd').val('insert');
         }
         else {
             this.$('#detailCmd').val('modify');
         }
         break;
     }
 };
 
//팝업이 종료 될때 리턴 값이호출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
GamCntnrRentMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
    case 'selectEntrpsInfoPopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
    case 'insertEntrpsInfoPopup':
        if (msg != 'cancel') {
            this.$('#entrpscd').val(value.entrpscd);
            this.$('#entrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
    case 'insertCntnrRentMngtPrmisnPopup':    
   	 if (msg != 'cancel') {
   		 if( value == "0" ) {
   			 var searchOpt=this.makeFormArgs('#gamOperSearchForm');
                this.$('#operResultList').flexOptions({params:searchOpt}).flexReload();               	 
            }
        } else {
            alert('취소 되었습니다');
        }
   	 break;
   	
    case 'selectAssetsCdRentPopup':
        if (msg != 'cancel') {
            this.$('#gisAssetsPrtAtCode').val(value.gisAssetsPrtAtCode);
            this.$('#gisAssetsCd').val(value.gisAssetsCd);
            this.$('#gisAssetsSubCd').val(value.gisAssetsSubCd);
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
 var module_instance = new GamCntnrRentMngtModule();
 </script>
 <!-- 아래는 고정 -->
 <input type="hidden" id="window_id" value='${windowId}' />
 <div class="window_main">

     <div id="searchViewStack" class="emdPanel">
         <div class="viewPanel">
             <form id="gamOperSearchForm">
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
                             <td width="100px">
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
                             <td>
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

     <div class="emdPanel fillHeight">
         <div id="operResultListTab" class="emdTabPanel" data-onchange="onTabChange">
             <ul>
                 <li><a href="#tabs1" class="emdTab">컨테이너부두사용목록관리 조회내역</a></li>
                 <li><a href="#tabs2" class="emdTab">컨테이너부두사용목록관리 상세조회 목록내역</a></li>
                 <li><a href="#tabs3" class="emdTab">컨테이너부두사용목록관리 상세조회내역</a></li>
                 <li><a href="#tabs4" class="emdTab">사진</a></li>
             </ul>
             
             <div id="tabs1" class="emdTabPage" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                 <div style="width: 100%; height: 100%; overflow:auto">
                         <table id="operResultList" style="display:none"></table>
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
                             <td>
                                 <button id="addCntnrRentMngtRenew">연장신청</button>
                                 <button id="addCntnrRentMngtFirst">최초신청</button>
                                 <button id="btnPrmisn">승낙</button>
                                 <button id="btnPrmisnCancel">승낙취소</button>
                             </td>
                         </tr>
                     </table>
                 </div>
             </div>
             
             <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                 
                 <div class="emdControlPanel">
                 	<button id="btnSaveItem">저장</button><button id="btnCancelItem">취소</button><button id="btnRemoveItem">삭제</button>
                 </div>
                     <form id="gamOperForm">
                         <input type="hidden" id="cmd"/>
                         <input type="hidden" id="mngYear"/>
                         <input type="hidden" id="mngNo"/>
                         <input type="hidden" id="mngCnt"/>
                         <table>
                             <tr>
                                <th><span class="label">항코드</span></th>
                                <td>
                                    <select id="prtAtCode">
	                                    <option value="" selected="selected">선택</option>

	                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
	                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
	                                    </c:forEach>
	                                </select>
	                                <!-- 
	                                <input type="text" size="5" id="prtAtCode" maxlength="10"/>
	                                 -->
                                </td>
                                <th><span class="label">업체코드</span></th>
                                <td>
                                    <input type="text" size="5" id="entrpscd" maxlength="10"/>
                                    <input type="text" size="5" id="entrpsNm" readonly/>
                                    <button id="popupEntrpsInfoInput">업체</button>
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
                                    <select id="nticMth">
                                        <option value="">선택</option>
                                        <c:forEach items="${nticMthCdList}" var="nticMthItem">
                                            <option value="${nticMthItem.code }">${nticMthItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">최초 허가 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="frstPrmisnDt"/></td>
                            </tr>
                             <tr>
                                <th><span class="label">허가 일자</span></th>
                                <td><input type="text" class="emdcal" size="10" id="prmisnDt"></td>
                                <th><span class="label">허가 여부</span></th>
                                <td>
                                    <select id="prmisnYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
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
                                    <!-- 
                                    <select id="gisCd">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${gisCdList}" var="gisCdItem">
                                            <option value="${gisCdItem.code }">${gisCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                     -->
                                     
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
                             
                             <tr>
                                <th><span class="label">납부방법</span></th>
                                <td>
                                    <select id="payMth">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${payMthCdList}" var="payMthCdItem">
                                            <option value="${payMthCdItem.code }">${payMthCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">XXX</span></th>
                                <td><input type="text" size="10" id="deptcd"/></td>
                            </tr>
                             
                         </table>
                     </form>
                  
                  <table>
                     <tr>
                         <td height="30"></td>
                     </tr>
                  </table>
                  <table>
                     <tr>
                         <td height="30">
                             <button id="btnInsertItemDetail">등록</button><button id="btnRemoveItemDetail">삭제</button>
                         </td>
                     </tr>
                  </table>
                  
                  <table id="operResultDetailList" style="display:none"></table>
             </div>
             
             <div id="tabs3" class="emdTabPage" style="overflow: scroll;">
                 
                 <div class="emdControlPanel">
                 	<button id="btnSaveItemDetail">저장</button>
                 </div>
                     <form id="gamOperDetailForm">
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
                                <th><span class="label">GIS 자산 항코드 </span></th>
                                <td><input type="text" size="10" id="gisAssetsPrtAtCode" /><button id="popupFcltyCd" class="popupButton">GIS자산코드조회</button></td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">GIS 자산 SUB 코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsSubCd" /></td>
                                <th><span class="label">GIS 자산코드</span></th>
                                <td><input type="text" size="10" id="gisAssetsCd" /></td>
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
                                    <select id="usagePrposCd">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${usagePrposCdList}" var="usagePrposCdItem">
                                            <option value="${usagePrposCdItem.code }">${usagePrposCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                             
                             <tr>
                                <th><span class="label">면제 구분</span></th>
                                <td>
                                    <select id="exemptSe">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${exemptSeCdList}" var="exemptSeCdItem">
                                            <option value="${exemptSeCdItem.code }">${exemptSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">면제 사유 코드</span></th>
                                <td>
                                    <select id="exemptRsnCd">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${exemptRsnCdList}" var="exemptRsnCdItem">
                                            <option value="${exemptRsnCdItem.code }">${exemptRsnCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
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
                                    <select id="packSe">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${packSeCdList}" var="packSeCdItem">
                                            <option value="${packSeCdItem.code }">${packSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">업체 구분</span></th>
                                <td>
                                    <select id="entrpsSe">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${entrpsSeCdList}" var="entrpsSeCdItem">
                                            <option value="${entrpsSeCdItem.code }">${entrpsSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">사용료 계산 구분</span></th>
                                <td>
                                    <select id="feeCalcSe">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${feeCalcSeCdList}" var="feeCalcSeCdItem">
                                            <option value="${feeCalcSeCdItem.code }">${feeCalcSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <th><span class="label">감면 사용료 계산 구분</span></th>
                                <td>
                                    <select id="rdcxptFeeCalcSe">
                                        <option value="" selected="selected">선택</option> 
                                        
                                        <c:forEach  items="${rdcxptFeeCalcSeCdList}" var="rdcxptFeeCalcSeCdItem">
                                            <option value="${rdcxptFeeCalcSeCdItem.code }">${rdcxptFeeCalcSeCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
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
                                <th><span class="label">부두코드</span></th>
                                <td>
                                    <select id="quayCd">
                                        <option value="" selected="selected">선택</option>

                                        <c:forEach  items="${quayCdList}" var="quayCdItem">
                                            <option value="${quayCdItem.code }">${quayCdItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th><span class="label">XXX</span></th>
                                <td></td>
                            </tr>
                             
                         </table>
                     </form>
                  
             </div>
             
             <div id="tabs4" class="emdTabPage" style="overflow: scroll;" >
                <table id="assetCodePhotoList" style="display:none"></table>
                <div class="emdControlPanel"><!-- <button id="addAssetGisPhoto">추가</button><button id="removeAssetGisPhoto">삭제</button> --></div>
                <!-- 
                <div class="emdPanel" style="overflow:scroll"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div>
                 -->
                <div class="emdPanel"><img style="border: 1px solid #000; max-width:800px; max-height: 600px" src="<c:url value='images/egovframework/ygpa/gam/misc/TEST2.JPG'/>"></div> 
            </div>
            
         </div>
     </div>
 </div>