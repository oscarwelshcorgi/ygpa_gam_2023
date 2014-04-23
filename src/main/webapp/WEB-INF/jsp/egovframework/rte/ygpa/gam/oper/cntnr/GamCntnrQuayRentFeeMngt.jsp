<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamCntnrQuayRentFeeMngt.jsp
  * @Description : 컨테이너부두임대료관리 
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
function GamCntnrQuayRentFeeMngtModule() {}

GamCntnrQuayRentFeeMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamCntnrQuayRentFeeMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //       
    this.$("#cntnrQuayRentFeeMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/cntnr/gamSelectCntnrQuayRentFeeMngtList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'회계연도', name:'accnutYear',width:70, sortable:false,align:'center'},
                    {display:'고지횟수', name:'nticCnt',width:70, sortable:false,align:'center'},
                    {display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'},
                    {display:'업체코드', name:'entrpscd',width:90, sortable:false,align:'center'},
                    {display:'고지기간FROM', name:'nticPdFrom',width:100, sortable:false,align:'center'}, 
                    {display:'고지기간TO', name:'nticPdTo',width:100, sortable:false,align:'center'},
                    {display:'요금종류', name:'chrgeKndNm',width:100, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'고지일자', name:'nticDt',width:100, sortable:false,align:'center'},
                    {display:'고지번호', name:'nticno',width:100, sortable:false,align:'center'},
                    {display:'부서명', name:'deptcdNm',width:100, sortable:false,align:'center'},
                    {display:'사용면적', name:'grAr',width:120, sortable:false,align:'center', displayFormat: 'number'},
                    {display:'사용기간FROM', name:'grUsagePdFrom',width:100, sortable:false,align:'center'},
                    {display:'사용기간TO', name:'grUsagePdTo',width:100, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:70, sortable:false,align:'center'}
                    
                    /*
                    {display:'선택', name:'chkItem', width:40, sortable:false, align:'center', displayFormat:'checkbox'},
					{display:'관리번호', name:'rentMngNo',width:100, sortable:false,align:'center'},
					{display:'시설 구분', name:'fcltySe',width:100, sortable:false,align:'center'},    
					{display:'업체코드', name:'entrpscd',width:100, sortable:false,align:'center'},
					{display:'요금종류', name:'chrgeKnd',width:100, sortable:false,align:'center'},
					{display:'납부 기한', name:'payTmlmt',width:100, sortable:false,align:'center'},
					{display:'공시지가', name:'olnlp',width:100, sortable:false,align:'center'},
					{display:'부가세 여부', name:'vatYn',width:100, sortable:false,align:'center'},
					{display:'부가세', name:'vat',width:100, sortable:false,align:'center'},
					{display:'고지 금액', name:'nticAmt',width:100, sortable:false,align:'center'},
					{display:'비고', name:'rm',width:100, sortable:false,align:'center'},
					{display:'수납 구분', name:'rcivSe',width:100, sortable:false,align:'center'},
					{display:'수납 일자', name:'rcivDt',width:100, sortable:false,align:'center'},
					{display:'고지서 발부 여부', name:'nhtIsueYn',width:100, sortable:false,align:'center'},
					{display:'연체 번호', name:'arrrgNo',width:100, sortable:false,align:'center'},
					{display:'연체 금액', name:'arrrgAmt',width:100, sortable:false,align:'center'},
					{display:'의뢰 순번', name:'reqestSeq',width:100, sortable:false,align:'center'},
					{display:'부서코드', name:'deptcd',width:100, sortable:false,align:'center'},
					{display:'고지 방법', name:'nticMth',width:100, sortable:false,align:'center'},
					{display:'등록자', name:'regUsr',width:100, sortable:false,align:'center'},
					{display:'등록일시', name:'registDt',width:100, sortable:false,align:'center'},
					{display:'수정자', name:'updUsr',width:100, sortable:false,align:'center'},
					{display:'수정일시', name:'updtDt',width:100, sortable:false,align:'center'},
					{display:'항코드', name:'prtAtCode',width:100, sortable:false,align:'center'},
					{display:'관리 년도', name:'mngYear',width:100, sortable:false,align:'center'},
					{display:'관리 번호', name:'mngNo',width:100, sortable:false,align:'center'},
					{display:'관리 횟수', name:'mngCnt',width:100, sortable:false,align:'center'}
					*/
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            module.$('#totalResultCnt').val(data.totalCount);
            module.$('#sumFee').val(data.sumFee);
            module.$('#sumArrrgAmt').val(data.sumArrrgAmt);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumNticAmt').val(data.sumNticAmt);
            
            return data;
        }
    });

    this.$("#cntnrQuayRentFeeMngtList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamCntnrQuayRentFeeMngtForm :input').val('');

        module.makeFormValues('#gamCntnrQuayRentFeeMngtForm', row);
        module._editData=module.getFormValues('#gamCntnrQuayRentFeeMngtForm', row);
        module._editRow=module.$('#cntnrQuayRentFeeMngtList').selectedRowIds()[0];
    });
    
    this.$("#cntnrQuayRentFeeMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#cntnrQuayRentFeeMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        this.$("#cmd").val('modify');
        
        /*
        if(row!=null) {
        	module.$('#nticCnt').val(row['nticCnt']);
        	module.$('#rentMngNo').val(row['rentMngNo']);
        	module.$('#fcltySe').val(row['fcltySe']);
        	module.$('#chrgeKnd').val(row['chrgeKnd']);
        	module.$('#entrpscd').val(row['entrpscd']);
        	module.$('#entrpsNm').val(row['entrpsNm']);
        	module.$('#nticPdFrom').val(row['nticPdFrom']);
        	module.$('#constPerTo').val(row['constPerTo']);
        	module.$('#accnutYear').val(row['accnutYear']);
        	module.$('#nticno').val(row['nticno']);
        	module.$('#nticDt').val(row['nticDt']);
        	module.$('#payTmlmt').val(row['payTmlmt']);
        	module.$('#olnlp').val(row['olnlp']);
        	module.$('#fee').val(row['fee']);
        	module.$('#vatYn').val(row['vatYn']);
        	module.$('#vat').val(row['vat']);
        	module.$('#nticAmt').val(row['nticAmt']);
        	module.$('#rm').val(row['rm']);
        	module.$('#rcivSe').val(row['rcivSe']);
        	module.$('#rcivDt').val(row['rcivDt']);
        	module.$('#nhtIsueYn').val(row['nhtIsueYn']);
        	module.$('#arrrgNo').val(row['arrrgNo']);
        	module.$('#arrrgAmt').val(row['arrrgAmt']);
        	module.$('#reqestSeq').val(row['reqestSeq']);
        	module.$('#deptcd').val(row['deptcd']);
        	module.$('#nticMth').val(row['nticMth']);
        	module.$('#regUsr').val(row['regUsr']);
        	module.$('#registDt').val(row['registDt']);
        	module.$('#updUsr').val(row['updUsr']);
        	module.$('#updtDt').val(row['updtDt']);
        	module.$('#prtAtCode').val(row['prtAtCode']);
        	module.$('#mngYear').val(row['mngYear']);
        	module.$('#mngNo').val(row['mngNo']);
        	module.$('#mngCnt').val(row['mngCnt']);
            //throw 0;
        }
        */
    });
    
    //로드될 때 사용기간에 오늘날짜 처리
 	var today = new Date();
 	var month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1); 
 	var date = (today.getDate() >= 10) ? today.getDate() : '0' + today.getDate(); 
 	var sToday = today.getFullYear() + '-' + month + '-' + date;
    
    this.$('#sGrUsagePdFrom').val(sToday);
    this.$('#sGrUsagePdTo').val(sToday);    
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamCntnrQuayRentFeeMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':        	
            if( this.$('#sGrUsagePdFrom').val() == '' ) {
            	alert("사용기간을 선택하십시오.");
            	return;
            }
            
            if( this.$('#sGrUsagePdTo').val() == '' ) {
                alert("사용기간을 선택하십시오.");
                return;
            }
            var searchOpt=this.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
        	this.$("#cntnrQuayRentFeeMngtListTab").tabs("option", {active: 0}); //2014-4-22 추가
            this.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();

            break;
         // 추가
        case 'btnInsertItem':
        	module.$("#cntnrQuayRentFeeMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
            this.$('#gamCntnrQuayRentFeeMngtForm').find(':input').val('');
            this.$("#cmd").val('insert');
            
            break;     
            
        // 저장
        case 'btnSaveItem':
        	/*   
        	if( this.$('#prtAtCode').val() == '' ) {
        		alert("목록에서 더블클릭하여 상세로 이동하십시오.");
        	} else {
	        	var inputVO=this.makeFormArgs('#gamCntnrQuayRentFeeMngtForm');
	        	
	        	this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrQuayRentFeeMngt.do" />', inputVO, function(module, result) {
	
	                if(result.resultCode=='0') {
	                    var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
	                    module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
	                }
	
	                alert(result.resultMsg);
	            });
        	}
        	*/

        	if( this.$('#nticCnt').val() == '' ) {
                alert("고지횟수를 입력하십시오.");
                return;
            }
        	
        	if( this.$("#cmd").val() == 'insert' ) {
        		var inputVO=this.makeFormArgs('#gamCntnrQuayRentFeeMngtForm');
                
                this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentFeeMngt.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                        module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
        	} else {
        	    var inputVO=this.makeFormArgs('#gamCntnrQuayRentFeeMngtForm');
                
                this.doAction('<c:url value="/oper/cntnr/gamUpdateCntnrQuayRentFeeMngt.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                        module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
        	}
        	
            break;  
            
         // 취소
        case 'btnCancelItem':
            this.$('#gamCntnrQuayRentFeeMngtForm :input').val("");
            break;
        
        /*    
        // 고지의뢰(목록)
        case 'saveNticListBtn':
            var filter = [{ 'col': 'chkItem', 'filter': true}];
            var reglist = this.$("#cntnrQuayRentFeeMngtList").selectFilterData(filter);

            alert(reglist.length);
            
            if(reglist.length > 0){
                var nticCnts = "";
                var prtAtCodes = "";
                var mngYears = "";
                var mngNos = "";
                var mngCnts = ""; 
                var nticnos = ""; 
                var accnutYears = ""; 
                
                for(var i=0; i<reglist.length; i++){
                    if(reglist[i].chkItem == true){
                        if(i < (reglist.length-1)){
                            nticCnts += reglist[i].nticCnt + ";";
                            prtAtCodes += reglist[i].prtAtCode + ";";
                            mngYears += reglist[i].mngYear + ";";
                            mngNos += reglist[i].mngNo + ";";
                            mngCnts += reglist[i].mngCnt + ";";
                            nticnos += reglist[i].nticno + ";";
                            accnutYears += reglist[i].accnutYear + ";";
                        }else{
                            nticCnts += reglist[i].nticCnt;
                            prtAtCodes += reglist[i].prtAtCode;
                            mngYears += reglist[i].mngYear;
                            mngNos += reglist[i].mngNo;
                            mngCnts += reglist[i].mngCnt;
                            nticnos += reglist[i].nticno;
                            accnutYears += reglist[i].accnutYear;
                        }
                    }
                }
                
                var inputVO = {nticCnts: nticCnts, prtAtCodes: prtAtCodes, mngYears: mngYears, mngNos: mngNos, mngCnts: mngCnts, nticnos: nticnos, accnutYears: accnutYears };
                this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentFeeMngtNtic.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                        var searchOpt = module.makeFormArgs("#gamCntnrQuayRentFeeMngtSearchForm");
                        module.$("#cntnrQuayRentFeeMngtList").flexOptions({params:searchOpt}).flexReload();
                    }
                    alert(result.resultMsg);                    
                });
            }else{
                alert("선택 된 항목이 없습니다.");
            }
            break;   
        */
        
        // 고지취소(목록)
        /*
        case 'cancelNticListBtn': 
        	var filter = [{ 'col': 'chkItem', 'filter': true}];
            var reglist = this.$("#cntnrQuayRentFeeMngtList").selectFilterData(filter);

            if(reglist.length > 0){
                var nticCnts = "";
                var prtAtCodes = "";
                var mngYears = "";
                var mngNos = "";
                var mngCnts = ""; 
                var nticnos = ""; 
                var accnutYears = ""; 
                
                for(var i=0; i<reglist.length; i++){
                    if(reglist[i].chkItem == true){
                        if(i < (reglist.length-1)){
                            nticCnts += reglist[i].nticCnt + ";";
                            prtAtCodes += reglist[i].prtAtCode + ";";
                            mngYears += reglist[i].mngYear + ";";
                            mngNos += reglist[i].mngNo + ";";
                            mngCnts += reglist[i].mngCnt + ";";
                            nticnos += reglist[i].nticno + ";";
                            accnutYears += reglist[i].accnutYear + ";";
                        }else{
                            nticCnts += reglist[i].nticCnt;
                            prtAtCodes += reglist[i].prtAtCode;
                            mngYears += reglist[i].mngYear;
                            mngNos += reglist[i].mngNo;
                            mngCnts += reglist[i].mngCnt;
                            nticnos += reglist[i].nticno;
                            accnutYears += reglist[i].accnutYear;
                        }
                    }
                }
                
                var inputVO = {nticCnts: nticCnts, prtAtCodes: prtAtCodes, mngYears: mngYears, mngNos: mngNos, mngCnts: mngCnts, nticnos: nticnos, accnutYears: accnutYears };
                this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrQuayRentFeeMngtNtic.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                        var searchOpt = module.makeFormArgs("#gamCntnrQuayRentFeeMngtSearchForm");
                        module.$("#cntnrQuayRentFeeMngtList").flexOptions({params:searchOpt}).flexReload();
                    }
                    alert(result.resultMsg);                    
                });
            }else{
                alert("선택 된 항목이 없습니다.");
            }
            break; 
        */
        
        /*
        // 고지의뢰(단건)
        case 'saveNticDetailBtn':
            if( this.$('#prtAtCode').val() == '' ) {
                alert("목록에서 더블클릭하여 상세로 이동하십시오.");
            } else {
                var inputVO=this.makeFormArgs('#gamCntnrQuayRentFeeMngtForm');
                
                this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentFeeMngtNticSingle.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                        module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
            }
            break;  
            
        // 고지취소(단건)
        case 'cancelNticDetailBtn':
            if( this.$('#prtAtCode').val() == '' ) {
                alert("목록에서 더블클릭하여 상세로 이동하십시오.");
            } else {
                var inputVO=this.makeFormArgs('#gamCntnrQuayRentFeeMngtForm');
                
                this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrQuayRentFeeMngtNticSingle.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                        module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
            }
            break;  
        */
        
        case 'saveNticDetailBtn':
        	var rows = this.$('#cntnrQuayRentFeeMngtList').selectedRows();
        	
        	if(rows.length>=1) {
        		if( confirm("고지의뢰를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/cntnr/gamInsertCntnrQuayRentFeeMngtNticSingle.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                            module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                //throw 0;
                }
        	} else {
                alert("목록에서 선택하십시오.");
            }
        	
            break;  
            
        case 'cancelNticDetailBtn':
            var rows = this.$('#cntnrQuayRentFeeMngtList').selectedRows();
            
            if(rows.length>=1) {
                if( confirm("고지취소를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/oper/cntnr/gamDeleteCntnrQuayRentFeeMngtNticSingle.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
                            module.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                //throw 0;
                }
            } else {
                alert("목록에서 선택하십시오.");
            }
            
            break;
        
        // 팝업을 호출한다.(업체)     
        case 'popupEntrpsInfoFee': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoFeePopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;
            
    }
};

GamCntnrQuayRentFeeMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamCntnrQuayRentFeeMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamCntnrQuayRentFeeMngtSearchForm');
    //this.showAlert(searchOpt);
    this.$('#cntnrQuayRentFeeMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamCntnrQuayRentFeeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamCntnrQuayRentFeeMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoFeePopup':
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
var module_instance = new GamCntnrQuayRentFeeMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamCntnrQuayRentFeeMngtSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 80px">항코드</th>
                            <td style="width: 120px">
                                <select id="sPrtAtCode">
                                    <option value="" selected="selected">선택</option>

                                    <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                        <option value="${prtAtCdItem.code }">${prtAtCdItem.codeNm }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th style="width: 80px">관리번호</th>
                            <td style="width: 200px">
                                <input id="sMngYear" type="text" size="4">-<input id="sMngNo" type="text" size="3">-<input id="sMngCnt" type="text" size="2">
                            </td>
                            <td style="text-align: right;"><button id="searchBtn" class="submit">조회</button></td>
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
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfoFee">업체</button>
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
                            <td >
                                <select id="sPrmisnYn">
                                    <option value="" selected="selected">선택</option>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            <th>사용기간</th>
                            <td>
                            <input id="sUsagePdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="sUsagePdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sRrArFrom" type="text" size="5">~<input id="sRrArTo" type="text" size="5">
                            </td>
                        </tr>
                         -->
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="cntnrQuayRentFeeMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">컨테이너부두임대료고지 목록</a></li>
                <li><a href="#tabs2" class="emdTab">컨테이너부두임대료고지 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="cntnrQuayRentFeeMngtList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                  합계
                                  자료수 <input id="totalResultCnt" class="ygpaNumber" style="text-align:right;" size="5" readonly>
                                  사용료 <input id="sumFee" type="text" class="ygpaCurrency" style="text-align:right;" size="14" readonly>
                                  연체 <input id="sumArrrgAmt" type="text" class="ygpaCurrency" style="text-align:right;" size="14" readonly>
                                  부가세 <input id="sumVat" type="text" class="ygpaCurrency" style="text-align:right;" size="14" readonly>
                                  고지액 <input id="sumNticAmt" type="text" class="ygpaCurrency" style="text-align:right;" size="14" readonly>
                               </form>
                            </td>
                            <td>
                                <!-- 
                                <button id="saveNticListBtn">고지의뢰</button>
                                <button id="cancelNticListBtn">고지취소</button>
                                -->
                                
                                <button id="saveNticDetailBtn">고지의뢰</button>
                                <button id="cancelNticDetailBtn">고지취소</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <button id="saveNticDetailBtn">고지의뢰</button><button id="cancelNticDetailBtn">고지취소</button>
                    <form id="gamCntnrQuayRentFeeMngtForm">
                        <input type="hidden" id="cmd"/>
                        
                        <table>
                            <tr>
                                <th><span class="label">항구분</span></th>
                                <td style="width: 350px"><input type="text" size="10" id="prtAtCodeNm"/></td>
                                <th><span class="label">고지번호</span></th>
                                <td><input type="text" size="10" id="nticno"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">관리번호</span></th>
                                <td><input type="text" size="12" id="rentMngNo" readonly/></td>
                                <th><span class="label">신청업체</span></th>
                                <td><input type="text" size="15" id="entrpsNm"/><input type="text" size="13" id="entrpscd"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">총사용기간</span></th>
                                <td><input type="text" size="10" id="grUsagePdFrom" readonly/>~<input type="text" size="10" id="grUsagePdTo" readonly/></td>
                                <th><span class="label">총사용료</span></th>
                                <td><input type="text" size="15" id="grFee"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">감면사용료</span></th>
                                <td><input type="text" size="15" id="grRdcxptFee"/></td>
                                <th><span class="label">요금종류</span></th>
                                <!-- <td><input type="text" size="10" id="chrgeKnd"/></td> -->
                                <td><input id="chrgeKnd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id=GAM024 /></td>
                            </tr>

                            <tr>
                                <th><span class="label">고지기간</span></th>
                                <td><input type="text" size="10" id="nticPdFrom"/>~<input type="text" size="10" id="nticPdTo"/></td>
                                <th><span class="label">고지일자</span></th>
                                <td><input type="text" size="10" id="nticDt"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">비고</span></th>
                                <td colspan="3"><input type="text" size="50" id="rm"/></td>
                            </tr>
                            <tr>
                                <th><span class="label">수납일자</span></th>
                                <td colspan="3"><input type="text" size="10" id="rcivDt"/></td>
                            </tr>
                            <tr>       
                                <th><span class="label">수납구분</span></th>
                                <td colspan="3">
                                    <select id="rcivSe">
                                        <option value="">선택</option>
                                        <c:forEach items="${rcivSeCdList}" var="rcivSeItem">
                                            <option value="${rcivSeItem.code }">${rcivSeItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="label">연체 금액</span></th>
                                <td colspan="3"><input type="text" size="15" id="arrrgAmt"/></td>
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
                            
                            <!-- 
                            <tr>
							    <th><span class="label">고지 횟수</span></th>
							    <td style="width: 250px"><input type="text" size="10" id="nticCnt"/></td>
							    <th><span class="label">시설 구분</span></th>
							    <td><input type="text" size="10" id="fcltySe"/></td>
							</tr>
							<tr>
							    <th><span class="label">요금 종류</span></th>
							    <td>
							        <select id="chrgeKnd">
                                        <option value="">선택</option>
                                        <c:forEach items="${chrgeKndCdList}" var="chrgeKndItem">
                                            <option value="${chrgeKndItem.code }">${chrgeKndItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
							    </td>
							    <th><span class="label">업체코드</span></th>
							    <td><input type="text" size="10" id="entrpscd"/></td>
							</tr>
							<tr>
							    <th><span class="label">업체 명</span></th>
							    <td><input type="text" size="10" id="entrpsNm"/></td>
							    <th><span class="label">고지 기간 FROM</span></th>
							    <td><input type="text" size="10" id="nticPdFrom"/></td>
							</tr>
							    
							<tr>
							    <th><span class="label">고지 기간 TO</span></th>
							    <td><input type="text" size="10" id="constPerTo"/></td>
							    <th><span class="label">회계 년도</span></th>
							    <td><input type="text" size="10" id="accnutYear"/></td>
							</tr>
							
							<tr>
							    <th><span class="label">고지번호</span></th>
							    <td><input type="text" size="10" id="nticno"/></td>
							    <th><span class="label">고지 일자</span></th>
							    <td><input type="text" size="10" id="nticDt"/></td>
							</tr>
							
							<tr>
							    <th><span class="label">납부 기한</span></th>
							    <td><input type="text" size="10" id="payTmlmt"/></td>
							    <th><span class="label">공시지가</span></th>
							    <td><input type="text" size="10" id="olnlp"/></td>
							</tr>
							
							<tr>
							    <th><span class="label">사용료</span></th>
							    <td><input type="text" size="10" id="fee"/></td>
							    <th><span class="label">부가세 여부</span></th>
							    <td>
							        <select id="vatYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
							    </td>
							</tr>
							
							<tr>
							    <th><span class="label">부가세</span></th>
							    <td><input type="text" size="10" id="vat"/></td>
							    <th><span class="label">고지 금액</span></th>
							    <td><input type="text" size="10" id="nticAmt"/></td>
							</tr>
							
							<tr>
							    <th><span class="label">비고</span></th>
							    <td><input type="text" size="10" id="rm"/></td>
							    <th><span class="label">수납 구분</span></th>
							    <td>
							        <select id="rcivSe">
                                        <option value="">선택</option>
                                        <c:forEach items="${rcivSeCdList}" var="rcivSeItem">
                                            <option value="${rcivSeItem.code }">${rcivSeItem.codeNm }</option>
                                        </c:forEach>
                                    </select>
							    </td>
							</tr>
							
							<tr>
							    <th><span class="label">수납 일자</span></th>
							    <td><input type="text" size="10" id="rcivDt"/></td>
							    <th><span class="label">고지서 발부 여부</span></th>
							    <td>
							        <select id="nhtIsueYn">
                                        <option value="" selected="selected">선택</option>
                                        <option value="Y">Y</option>
                                        <option value="N">N</option>
                                    </select>
							    </td>
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
							    <th><span class="label">부서코드</span></th>
							    <td><input type="text" size="10" id="deptcd"/></td>
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
							    <th><span class="label">항코드</span></th>
							    <td><input type="text" size="10" id="prtAtCode" readonly/></td>
							</tr>
							
							<tr>
							    <th><span class="label">관리 년도</span></th>
							    <td><input type="text" size="10" id="mngYear" readonly/></td>
							    <th><span class="label">관리 번호</span></th>
							    <td><input type="text" size="10" id="mngNo" readonly/></td>
							</tr>
							    
							<tr>
							    <th><span class="label">관리 횟수</span></th>
							    <td><input type="text" size="10" id="mngCnt" readonly/></td>
							    <th><span class="label">관리 번호(조합)</span></th>
							    <td><input type="text" size="10" id="rentMngNo" readonly/></td>
							</tr>
							 -->
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