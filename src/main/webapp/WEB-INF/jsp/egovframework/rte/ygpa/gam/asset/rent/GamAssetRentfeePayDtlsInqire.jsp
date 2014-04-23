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
        url: '<c:url value="/asset/rent/gamSelectAssetRentfeePayDtlsInqireList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
					{display:'회계연도', name:'accnutYear',width:70, sortable:false,align:'center'},
					{display:'고지횟수', name:'nticCnt',width:70, sortable:false,align:'center'},
					{display:'고지일자', name:'nticDt',width:100, sortable:false,align:'center'},
					{display:'업체코드', name:'entrpscd',width:90, sortable:false,align:'center'},
					{display:'업체명', name:'entrpsNm',width:170, sortable:false,align:'center'},
					{display:'납부기한', name:'payTmlmt',width:90, sortable:false,align:'center'},
					{display:'수납', name:'rcvdTp',width:100, sortable:false,align:'center'},
					{display:'요금', name:'fee',width:120, sortable:false,align:'center', displayFormat: 'number'},
					{display:'요금종류', name:'chrgeKndNm',width:100, sortable:false,align:'center'},
					{display:'수납일자', name:'rcvdDt',width:100, sortable:false,align:'center'},
					{display:'고지서출력여부', name:'nhtPrintYn',width:100, sortable:false,align:'center'},
					{display:'비고', name:'rm',width:200, sortable:false,align:'center'},
                    {display:'고지금액', name:'billAmnt',width:100, sortable:false,align:'center'}
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

    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
        module.$('#cmd').val('modify');

        module.$('#gamAssetRentFeeForm :input').val('');

        module.makeFormValues('#gamAssetRentFeeForm', row);
        module._editData=module.getFormValues('#gamAssetRentFeeForm', row);
        module._editRow=module.$('#assetRentFeeList').selectedRowIds()[0];
    });
    
    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
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
    
    
 	// 오늘 날짜로 고지기간 설정 처리
	var today = new Date();
	
	var serchYr = today.getFullYear();
	var serchMn = today.getMonth() + 1;
	
	if(serchMn < 10){
		serchMn = "0" + serchMn;
	}
	
	var serchday = today.getDate();
	
	var displayDate = serchYr + "-" + serchMn + "-" + serchday;

	this.$("#sNticPdFrom").val(displayDate);
	this.$("#sNticPdTo").val(displayDate);

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
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();

            break;
        
         // 추가
        case 'btnInsertItem':
        	module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
            this.$('#gamAssetRentFeeForm').find(':input').val('');
            this.$("#cmd").val('insert');
            
            break;     
            
        // 저장
        case 'btnSaveItem':
        	/*   
        	if( this.$('#prtAtCode').val() == '' ) {
        		alert("목록에서 더블클릭하여 상세로 이동하십시오.");
        	} else {
	        	var inputVO=this.makeFormArgs('#gamAssetRentFeeForm');
	        	
	        	this.doAction('<c:url value="/asset/rent/gamUpdateAssetRentFee.do" />', inputVO, function(module, result) {
	
	                if(result.resultCode=='0') {
	                    var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
	                    module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
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
        		var inputVO=this.makeFormArgs('#gamAssetRentFeeForm');
                
                this.doAction('<c:url value="/asset/rent/gamInsertAssetRentFee.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                        module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
        	} else {
        	    var inputVO=this.makeFormArgs('#gamAssetRentFeeForm');
                
                this.doAction('<c:url value="/asset/rent/gamUpdateAssetRentFee.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                        module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
        	}
        	
            break;  
            
         // 취소
        case 'btnCancelItem':
            this.$('#gamAssetRentFeeForm :input').val("");
            break;
        
        /*    
        // 고지의뢰(목록)
        case 'saveNticListBtn':
            var filter = [{ 'col': 'chkItem', 'filter': true}];
            var reglist = this.$("#assetRentFeeList").selectFilterData(filter);

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
                this.doAction('<c:url value="/asset/rent/gamInsertAssetRentFeeNtic.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                        var searchOpt = module.makeFormArgs("#gamAssetRentFeeSearchForm");
                        module.$("#assetRentFeeList").flexOptions({params:searchOpt}).flexReload();
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
            var reglist = this.$("#assetRentFeeList").selectFilterData(filter);

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
                this.doAction('<c:url value="/asset/rent/gamDeleteAssetRentFeeNtic.do" />', inputVO, function(module, result) {
                    if(result.resultCode == 0){
                        var searchOpt = module.makeFormArgs("#gamAssetRentFeeSearchForm");
                        module.$("#assetRentFeeList").flexOptions({params:searchOpt}).flexReload();
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
                var inputVO=this.makeFormArgs('#gamAssetRentFeeForm');
                
                this.doAction('<c:url value="/asset/rent/gamInsertAssetRentFeeNticSingle.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                        module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
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
                var inputVO=this.makeFormArgs('#gamAssetRentFeeForm');
                
                this.doAction('<c:url value="/asset/rent/gamDeleteAssetRentFeeNticSingle.do" />', inputVO, function(module, result) {
    
                    if(result.resultCode=='0') {
                        var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                        module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                    }
    
                    alert(result.resultMsg);
                });
            }
            break;  
        */
        
        case 'saveNticDetailBtn':
        	var rows = this.$('#assetRentFeeList').selectedRows();
        	
        	if(rows.length>=1) {
        		if( confirm("고지의뢰를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/gamInsertAssetRentFeeNticSingle.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
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
            var rows = this.$('#assetRentFeeList').selectedRows();
            
            if(rows.length>=1) {
                if( confirm("고지취소를 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/gamDeleteAssetRentFeeNticSingle.do" />', rows[0], function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
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
        case 'popupEntrpsInfo': 
            var opts;

            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
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
         throw 0;
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
                            <th style="width: 70px">항코드</th>
                            <td style="width: 170px"><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th style="width: 70px">업체명</th>
                            <td colspan="3"><input id="sEntrpscd" type="text" size="5"><input id="sEntrpsNm" type="text" size="20" readonly> <button id="popupEntrpsInfo">업체</button></td>
                            <td rowspan="2"><button id="searchBtn" *class="submit" class="buttonSearch">조회</button></td>
                        </tr>   
                        
                        <tr>
                            <th>관리번호</th>
                            <td><input id="sMngYear" type="text" size="3">-<input id="sMngNo" type="text" size="2">-<input id="sMngCnt" type="text" size="1"></td>
                            <th>고지일자</th>
                            <td><input id="sNticPdFrom" type="text" class="emdcal"size="8"> ~ <input id="sNticPdTo" type="text"class="emdcal" size="8"></td>
                            <th>요금종류</th>
                            <td><input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" /></td>
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
                <!-- <li><a href="#tabs2" class="emdTab">자산임대료고지 상세</a></li>  -->
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="assetRentFeeList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                <!-- 
                <div class="emdControlPanel">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                               <form id="form1">
                                  합계
                                  자료수 <input id="totalResultCnt" class="ygpaNumber" size="5" readonly>
                                  사용료 <input id="sumFee" type="text" class="ygpaCurrency" size="14" readonly>
                                  연체 <input id="sumArrrgAmt" type="text" class="ygpaCurrency" size="14" readonly>
                                  부가세 <input id="sumVat" type="text" class="ygpaCurrency" size="14" readonly>
                                  고지액 <input id="sumNticAmt" type="text" class="ygpaCurrency" size="14" readonly>
                               </form>
                            </td>
                            <td>
                                <button id="saveNticDetailBtn">고지의뢰</button>
                                <button id="cancelNticDetailBtn">고지취소</button>
                            </td>
                        </tr>
                    </table>
                </div>
                -->
            </div>
            
            <!-- 
            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdControlPanel">
                    <button id="saveNticDetailBtn">고지의뢰</button><button id="cancelNticDetailBtn">고지취소</button>
                    <form id="gamAssetRentFeeForm">
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
                        </table>
                    </form>
            </div>

        </div>
        -->
    </div>
</div>