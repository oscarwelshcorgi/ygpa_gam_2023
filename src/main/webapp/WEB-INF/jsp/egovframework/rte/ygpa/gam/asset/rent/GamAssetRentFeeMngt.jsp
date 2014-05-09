<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAssetRentFeeMngt.jsp
  * @Description : 자산임대료고지관리
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
function GamAssetRentFeeMngtModule() {}

GamAssetRentFeeMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentFeeMngtModule.prototype.loadComplete = function() {

    // 테이블 설정 //
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '<c:url value="/asset/rent/gamSelectAssetRentFeeMngtList.do" />',
        dataType: 'json',
        colModel : [
                    {display:'항이름', name:'prtAtCodeNm',width:60, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:70, sortable:false,align:'center'},
                    {display:'횟수', name:'nticCnt',width:42, sortable:false,align:'center'},
                    {display:'업체명', name:'entrpsNm',width:160, sortable:false,align:'center'},
                    {display:'업체코드', name:'entrpscd',width:70, sortable:false,align:'center'},
                    {display:'고지대상기간', name:'nticPdDate',width:152, sortable:false,align:'center'},
                    {display:'요금', name:'chrgeKnd',width:38, sortable:false,align:'center'},
                    {display:'요금종류', name:'chrgeKndNm',width:100, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:90, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'이자', name:'feeA3',width:90, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'결재상태', name:'sanctnSttusNm',width:60, sortable:false,align:'center'},
                    {display:'고지', name:'nhtIsueYn',width:38, sortable:false,align:'center'},
                    {display:'고지일자', name:'nticDt',width:90, sortable:false,align:'center'},
                    {display:'고지번호', name:'nticno',width:62, sortable:false,align:'center'},
                    {display:'부서명', name:'deptcdNm',width:100, sortable:false,align:'center'},
                    {display:'사용면적', name:'grAr',width:62, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'사용기간', name:'grUsagePd',width:110, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:70, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {

        	$.each(data.resultList, function() {
        		this.nticPdDate = this.nticPdFrom+ '~'+ this.nticPdTo;
        		this.grUsagePd = this.grUsagePdFrom+ '~'+ this.grUsagePdTo;
        	});
            module.$('#totalResultCnt').html($.number(data.totalCount));
            module.$('#sumFee').html($.number(data.sumFee)+" 원");
            module.$('#sumNhtIsueAmt').html($.number(data.sumNhtIsueAmt)+" 원");
            module.$('#sumVat').html($.number(data.sumVat)+" 원");
            module.$('#sumNticAmt').html($.number(data.sumNticAmt)+" 원");

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

    this.$('#nticPdFrom').val(EMD.util.getDate());
    this.$('#nticPdTo').val(EMD.util.getDate(EMD.util.addDates(15)));	// 현재 일자부터 15일 이후 까지 조회 한다.

};

/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentFeeMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {

        // 조회
        case 'searchBtn':
            var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
            this.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();

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
        case 'btnEApproval':    // 전자결재
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['sanctnSttus'] == '1' || rows['sanctnSttus'] == '2' || rows['sanctnSttus'] == '5' ) {
                	alert("결재요청을 할수없는 상태 입니다.");
                	return;
                }

                if( confirm("결재요청을 하시겠습니까?") ) {
	                var opts = {
	                        type: 'ARN',	// 앞 두자는 부서 분류, 뒤에 N 이면 징수의뢰 사용승낙
	                        prtAtCode: rows['prtAtCode'],
	                        mngYear: rows['mngYear'],
	                        mngNo: rows['mngNo'],
	                        mngCnt: rows['mngCnt'],
		                    nticCnt: rows['nticCnt']
	                };

	                this.requestEApproval(opts, function(module, msg){
	                	alert(msg);
		                //재조회 안됨..
		                var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
		                module.$('#assetRentMngtList').flexOptions({params:searchOpt}).flexReload();
                	});

                }
            } else {
            	alert("목록에서 결제할 건을 선택하십시오.");
            	return;
            }
            break;
        case 'btnExecNticIssue':	// 고지 의뢰
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['sanctnSttus'] != '1' ) {
                	alert("결재가 완료 되지 않았습니다.");
                	return;
                }

                if( rows['nhtIsueYn'] == 'Y' ) {
                	alert("이미 고지된 건 입니다.");
                	return;
                }

                if( confirm("선택한 건을 고지 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/insertAssetRentFeeNticSingle.do" />', rows, function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
            	alert("목록에서 고지 할 건을 선택하십시오.");
            	return;
            }
        	break;
        case 'btnCancelNticIssue':	// 고지 취소
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['sanctnSttus'] != '1' ) {
                	alert("결재가 완료 되지 않았습니다.");
                	return;
                }

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("고지되지 않았습니다.");
                	return;
                }

                if( confirm("선택한 건의 고지를 취소 하시겠습니까?") ) {
                    this.doAction('<c:url value="/asset/rent/cancelAssetRentFeeNticSingle.do" />', rows, function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
            	alert("목록에서 고지 취소 할 항목을 선택하십시오.");
            	return;
            }
        	break;

        case 'btnNticIssuePrint':	// 고지서 출력
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['sanctnSttus'] != '1' ) {
                	alert("해당 건은 결재가 완료 되지 않았습니다.");
                	return;
                }

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printPayNotice('<c:url value="/asset/rent/printAssetRentFeePayNotice.do" />', rows);
            } else {
            	alert("목록에서 고지서를 출력 할 항목을 선택하십시오.");
            	return;
            }
        	break;
        case 'btnTaxPrint':	// 계산서 출력
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['sanctnSttus'] != '1' ) {
                	alert("해당 건은 결재가 완료 되지 않았습니다.");
                	return;
                }

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printTaxNotice('<c:url value="/asset/rent/printAssetRentFeeTaxNotice.do" />', rows);
            } else {
            	alert("목록에서 고지서를 출력 할 항목을 선택하십시오.");
            	return;
            }
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
        case 'popupEntrpsInfoFee':
            var opts={};

            this.doExecuteDialog('selectEntrpsInfoFeePopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts);
            break;

    }
};

GamAssetRentFeeMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentFeeMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
    //this.showAlert(searchOpt);
    this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
};

GamAssetRentFeeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamAssetRentFeeMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoFeePopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
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
var module_instance = new GamAssetRentFeeMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamAssetRentFeeSearchForm">
                <table style="width:100%;" class="searchPanel">
                        <tr>
                            <th>항코드</th>
                            <td>
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" data-column-id="gisAssetsPrtAtCode" />
                            </td>
                            <th>관리번호</th>
                            <td style="width: 200px">
                                <input id="sMngYear" type="text" width="80" size="4" maxlength="4">-<input id="sMngNo" type="text" size="3" maxlength="3">-<input id="sMngCnt" type="text" size="2" maxlength="2">
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input id="sEntrpscd" type="text" size="3"><input id="sEntrpsNm" type="text" size="6" readonly> <button id="popupEntrpsInfoFee">업체</button>
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="submit">조회</button></td>
                        </tr>
                        <tr>
                            <th>결재여부</th>
                            <td width="100px">
                         		<input id="sanctnSttus" class="ygpaCmmnCd" data-code-id="GAM046" data-default-prompt="전체" data-column-id="sanctnSttus" />
                            </td>
                        	<th>고지여부</th>
                            <td width="100px">
                         		<input id="sNhtIsueYn" class="ygpaYnSelect" data-default-prompt="전체" data-value="N" data-column-id="sNhtIsueYn" />
                            </td>
                            <th>고지기간</th>
                            <td>
                            <input id="nticPdFrom" type="text" class="emdcal" size="8"> ~ <input id="nticPdTo" type="text" class="emdcal" size="8">
                            </td>
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
                            <input id="nticPdFrom" type="text" class="emdcal"
                                size="8"> ~ <input id="nticPdTo" type="text"
                                class="emdcal" size="8">
                            </td>
                            <th>총면적</th>
                            <td>
                                <input id="sRrArFrom" type="text" size="5">~<input id="sRrArTo" type="text" size="5">
                            </td>
                        </tr>
                         -->
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
            <ul>
                <li><a href="#tabs1" class="emdTab">자산임대료고지 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대료고지 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <!-- <div style="width: 100%; height: 100%; overflow:auto">  -->
                        <table id="assetRentFeeList" style="display:none" class="fillHeight"></table>
                <!-- </div>  -->
                <div class="emdControlPanel">
                    <table style="width:100%;" class="summaryPanel">
                    	<colgroup>
		                	<col width="100" />
		                	<col width="50"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="110" />
		                	<col width="90"/>
		                </colgroup>
                        <tr>
                        	<th>
                        		조회 자료수 :
                        	</th>
                            <td style="text-align: right;">
                            	<span id="totalResultCnt"></span>
                           	</td>
                     	    <th>
                        		총 사용료 :
                        	</th>
                            <td style="text-align: right;">
                            	<span id="sumFee"></span>
                           	</td>
                     	    <th>
                        		총 부가세 :
                        	</th>
                            <td style="text-align: right;">
                            	<span id="sumVat"></span>
                           	</td>
                     	    <th>
                        		총 고지금액 :
                        	</th>
                            <td style="text-align: right;">
                            	<span id="sumNticAmt"></span>
                           	</td>
                     	    <th>
                        		고지의뢰금액 :
                        	</th>
                            <td style="text-align: right;">
                            	<span id="sumNhtIsueAmt"></span>
                           	</td>
                        </tr>
                    </table>
                      <button id="btnEApproval">결재요청</button>
                     <button id="btnExecNticIssue">고지</button>
                     <button id="btnCancelNticIssue">고지취소</button>
                     <button id="btnNticIssuePrint">고지서출력</button>
                     <button id="btnTaxPrint">계산서출력</button>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
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
                                <th><span class="label">고지 금액</span></th>
                                <td colspan="3"><input type="text" size="15" id="nticAmt"/></td>
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

                <div class="emdControlPanel">
                    <button id="saveNticDetailBtn">고지의뢰</button><button id="cancelNticDetailBtn">고지취소</button>

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
</div>
