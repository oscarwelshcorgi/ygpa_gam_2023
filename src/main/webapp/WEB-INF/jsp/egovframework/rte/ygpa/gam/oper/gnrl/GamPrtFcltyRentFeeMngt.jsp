<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPrtFcltyRentFeeMngt.jsp
  * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
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
function GamAssetRentFeeMngtModule() {}

GamAssetRentFeeMngtModule.prototype = new EmdModule(1100, 700);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentFeeMngtModule.prototype.loadComplete = function(params) {

    // 테이블 설정 //
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '/oper/gnrl/gamSelectPrtFcltyRentFeeMngtList.do',
        dataType: 'json',
        colModel : [
//					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
//                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'자산번호', name:'gisAssets',width:96, sortable:false,align:'center'},
                    {display:'자산명', name:'gisAssetsNm',width:96, sortable:false,align:'center'},

                    {display:'공시지가', name:'olnlp',width:70, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'사용면적', name:'usageAr',width:70, sortable:false,align:'right',displayFormat: 'number'},
                    {display:'사용기간', name:'usagePd',width:120, sortable:false,align:'center'},

                    {display:'주소', name:'gisAssetsLocplc',width:96, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:96, sortable:false,align:'center'},
                    {display:'횟수', name:'nticCnt',width:40, sortable:false,align:'center'},
                    {display:'고지업체명', name:'entrpsNm',width:140, sortable:false,align:'left'},
                    {display:'업체코드', name:'entrpscd',width:70, sortable:false,align:'center'},
                    {display:'요금종류', name:'chrgeKndNm',width:150, sortable:false,align:'left'},
                    {display:'고지', name:'nhtIsueYnStr',width:40, sortable:false,align:'center'},
                    {display:'출력', name:'nhtPrintYnStr',width:40, sortable:false,align:'center'},
                    {display:'상계', name:'saveNm',width:40, sortable:false,align:'center'},
//                    {display:'요금', name:'chrgeKnd',width:30, sortable:false,align:'center'},
                    {display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'number'},
//                    {display:'결재', name:'sanctnSttusNm',width:60, sortable:false,align:'center'},
                    {display:'고지번호', name:'nticno',width:60, sortable:false,align:'center'},
//                    {display:'부서명', name:'deptcdNm',width:100, sortable:false,align:'left'},
//                    {display:'사용면적', name:'grAr',width:80, sortable:false,align:'right', displayFormat: 'number'},
//                    {display:'고지대상기간', name:'nticPdDate',width:140, sortable:false,align:'center'},
                    {display:'허가일자', name:'prmisnDt',width:80, sortable:false,align:'center'},
                    {display:'사용기간', name:'grUsagePd',width:160, sortable:false,align:'center'},
                    {display:'상계일자', name:'offsetDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {

        	$.each(data.resultList, function() {
        		this.nticPdDate = this.nticPdFrom+ '~'+ this.nticPdTo;
        		this.grUsagePd = this.grUsagePdFrom+ '~'+ this.grUsagePdTo;

        		if(this.nticAmt == 0){
        			this.nhtIsueYnStr = "면제";
        			this.nhtPrintYnStr = "면제";
        		}
        		else{
	        		this.nhtPrintYnStr = this.nhtPrintYn=="Y"?"출력":"";
	        		this.nhtIsueYnStr = this.nhtIsueYn=="Y"?"고지":"";
        		}

        		this.saveNm = this.save== "Y" ? "상계" : "";
        	});

        	module.makeDivValues('#summaryTable', data);

            return data;
        }
    });

    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._modifyFee=false;
    	if(row.nticAmt>0){
	    	module.$('#btnExecNticIssue').show();
	    	module.$('#btnExecNticIssue2').show();
	    	module.$('#btnNticIssuePrint').show();
	    	module.$('#btnNticIssuePrint2').show();
    	}
    	else{
	    	module.$('#btnExecNticIssue').hide();
	    	module.$('#btnExecNticIssue2').hide();
	    	module.$('#btnNticIssuePrint').hide();
	    	module.$('#btnNticIssuePrint2').hide();
    	}
    });

    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._modifyFee=false;

        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
    });

    this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}

    });

    this.$('#payTmlmt').on("change", {module:this}, function(e) {
    	var module = e.data.module;
        var rows = module.$('#assetRentFeeList').selectedRows()[0];
		rows['payTmlmt']=$(this).val();
    });

    this.$('#fee').on("keyup change", {module: this}, function(event) {
		event.data.module.changeFee();
    });

    this.$('#vat').on("keyup change", {module: this}, function(event) {
    	event.data.module.changeVat();
    });

    this.$('#roundVat').on("change", {module: this}, function(event) {
		event.data.module.changeFee();
    });

/* 이자 수동 변경 추가*/
   	var this_ = this;
    this.$('#intrAmnt').on("keyup", {module: this}, function(event) {
    	this_._intrAmntCheck=true;
    });
/* 월이자 체크 추가 */
    this.$('#payinstIntrrate,#intrAmnt,#monthIntrAmnt').on("change", {module: this}, function(event) {
		event.data.module.changeInterest();
    });

    if(params!=null) {
    	if(params.action=="selectRentFee") {
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);

            var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();

    	}
    	//알림창에서 들어오는 조건을 처리하여 그리드에 표시.
    	else if(params.action=="popupInqire") {
    		var today = new Date();
    		today.setDate(today.getDate()+15);

//    		this.$('#sUsagePdFrom').val(EMD.util.getDate());
    		this.$('#sUsagePdTo').val(EMD.util.getDate(today));
    		this.$('#sNhtIsueYn').val('N');
    		this.$('#sSaveYn').val('N');

    		var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
    	}
    } else {
		var today = new Date();
		today.setMonth(today.getMonth()-1);	// 최근 1개월 조회

		this.$('#sUsagePdFrom').val(EMD.util.getDate(today));
		this.$('#sUsagePdTo').val(EMD.util.getDate());

        var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
        this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
    }

    this._modifyFee=false;

	 this.$('#cofixMngt').click(function() {
		 alert('이자율 변경 이후에 이 창을 닫고 새로 실행해 주셔야 목록에 반영됩니다.');
	 });

	    this.$('#cofixList').on('change', {module: this}, function(event) {
	    	//alert('||'+$(this).val()+'||');
	        if( $(this).val() == '' ) {
	        	event.data.module.$('#payinstIntrrate').val("");
	        } else {
	        	event.data.module.$('#payinstIntrrate').val($(this).val()*100);
	        }
        	event.data.module.changeInterest();
	    });

    console.log('loadComplete.');
};

<%--
	요금 변경
--%>
GamAssetRentFeeMngtModule.prototype.changeFee = function() {
	var fee = this.$('#fee').val().replace(/,/g, "")*1;
	var intrAmnt = 0;
	var vat=0;
	if(this.nticMth!='1' && this.nticMth!='6') {
		intrAmnt=Number(this.$('#intrAmnt').val()||0);
		fee+=intrAmnt;
		this.$('#totPrice').text($.number(fee));
		if(this.$('#roundVat').attr('checked')=="checked") {
			vat=Math.round(fee/10);
		}
		else {
			vat=Math.floor(fee/10);
		}
		this.$('#vat').val(vat);
		this.$('#nticAmt').text($.number(Math.ceil(fee+vat)));
	}
	else {
		if(this.$('#roundVat').attr('checked')=="checked") {
			vat=Math.round(fee/100)*10;
		}
		else {
			vat=Math.floor(fee/100)*10;
		}
		this.$('#vat').val(vat);
		this.$('#nticAmt').text($.number(fee+vat));
	}

	this._modifyFee=true;
};

<%--
부가세 요금 변경
--%>
GamAssetRentFeeMngtModule.prototype.changeVat = function() {
	var fee = this.$('#fee').val().replace(/,/g, "")*1;
	var vat = this.$('#vat').val().replace(/,/g, "")*1;
	var intrAmnt = 0;
	if(this.nticMth!='1' && this.nticMth!='6') {
		intrAmnt=Number(this.$('#intrAmnt').val()||0);
		fee+=intrAmnt;
		this.$('#totPrice').text($.number(fee));

		this.$('#nticAmt').text($.number(Math.ceil(fee+vat)));
	}
	else {
		this.$('#nticAmt').text($.number(fee+vat));
	}
	console.log("12121221");
	this._modifyFee=true;
};

<%--
납부 이자율 변경(월,일 체크)
--%>
GamAssetRentFeeMngtModule.prototype.changeInterest = function() {

	var intrAmnt=0;
	var payinstIntrrate=this.$('#payinstIntrrate').val()/100||0;
	var bal=this.instBal; //나머지
	var ndFrom = this.nticPdFrom.split('-');
	var ndTo = this.nticPdTo.split('-');
	var sod = new Date(ndFrom[0], 1, 1);
	var eod = new Date(Number(ndFrom[0])+1, 1, 1);
	var ydays = (eod.getTime() - sod.getTime()) / 1000 / 60 / 60 / 24;
	var ndFromDt = new Date(ndFrom[0], ndFrom[1], ndFrom[2]);
	var ndToDt = new Date(ndTo[0], ndTo[1], ndTo[2]);

	if(this._intrAmntCheck != true){
		if(this.$('#monthIntrAmnt').attr('checked')=="checked") {
			var start = ndFrom[1];
			var end = ndTo[1];
			var betweenMonth =  (Number(end)-Number(start))+1;
			intrAmnt=bal*payinstIntrrate*betweenMonth/12;
			console.log('calc Month = '+betweenMonth);
			console.log('12'+' Months');
		}
		else{
			var betweenDay = (ndToDt.getTime() - ndFromDt.getTime()) / 1000 / 60 / 60 / 24+1;
			intrAmnt=bal*payinstIntrrate*betweenDay/ydays;
			console.log('calc day = '+betweenDay);
			console.log(ydays+' days');
		}

		/* 2018-12-17 조현성 요청사항(소숫점 버림 처리)*/
		intrAmnt=Math.floor(intrAmnt);
		this.$('#intrAmnt').val($.number(intrAmnt));
	}

	this.changeFee();
	this._intrAmntCheck = false;
};

<%--
	투자비보전 상계처리 2015.12.17 김종민
--%>
GamAssetRentFeeMngtModule.prototype.execSave = function() {
	var cnt = this.$('#assetRentFeeList').selectedRowCount();
	if(cnt < 1) {
		alert('목록에서 상계처리할 건을 선택하세요.');
		return;
	}
	var row = this.$('#assetRentFeeList').selectedRows()[0];
	if(row['save'] == 'Y') {
		alert('이미 상계처리된 건 입니다.');
		return;
	}
    if(row['nhtIsueYn'] == 'Y' ) {
    	alert("이미 고지된 건 입니다.");
    	return;
    }
    if( confirm("선택한 건을 상계 하시겠습니까?") ) {
        this.doAction('/oper/gnrl/updatePrtFcltyRentFeeSaveSingle.do', row, function(module, result) {
            if(result.resultCode=='0') {
                var row = module.$('#assetRentFeeList').selectedRows()[0];
            	row['save']='Y';
        		row['saveNm']="상계";
            	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
            	module.$('#assetRentFeeList').flexUpdateRow(rowIds, row);
            }
            alert(result.resultMsg);
        });
    }
};

<%--
	투자비보전 상계취소 2015.12.17 김종민
--%>
GamAssetRentFeeMngtModule.prototype.cancelSave = function() {
	var cnt = this.$('#assetRentFeeList').selectedRowCount();
	if(cnt < 1) {
		alert('목록에서 상계취소할 건을 선택하세요.');
		return;
	}
	var row = this.$('#assetRentFeeList').selectedRows()[0];
	if(row['save'] != 'Y') {
		alert('상계가 된 건만 취소가 가능합니다.');
		return;
	}
	if( confirm("선택한 건을 취소 하시겠습니까?") ) {
	    this.doAction('/oper/gnrl/cancelPrtFcltyRentFeeSaveSingle.do', row, function(module, result) {
	        if(result.resultCode=='0') {
	            var row = module.$('#assetRentFeeList').selectedRows()[0];
	        	row['save']='';
	    		row['saveNm']="";
	        	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
	        	module.$('#assetRentFeeList').flexUpdateRow(rowIds, row);
	        }
	        alert(result.resultMsg);
	    });
	}
};


/**
 * 정의 된 버튼 클릭 시
 */
 GamAssetRentFeeMngtModule.prototype.onButtonClick = function(buttonId) {

    switch(buttonId) {
        // 조회
        case 'searchBtn':
        	this.loadData();
            break;

        case 'popupEntrpsInfo': // 팝업을 호출한다.(조회)
            var opts={};
            this.doExecuteDialog('selectEntrpsInfoPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;
        case 'btnEApproval':    // 전자결재
        case 'btnEApproval2':    // 전자결재
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
        case 'btnExecSave': //투자비보전 상계처리
        	this.execSave();
        	break;
        case 'btnCancelSave': //상계취소
        	this.cancelSave();
        	break;
        case 'btnExecNticIssue':	// 고지 의뢰
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                /*
                if( rows['sanctnSttus'] != '1' ) {
                	if(!confirm("결재완료 되지 않았습니다. 결재 처리 되지 않은 자료를 고지 하시겠습니까?")) {
                        return;
                	}
                }
                */
				if(rows['nticAmt']<3000){
					alert("항만시설사용료고지 상세를 이용하세요.");
					this.$("#assetRentFeeListTab").tabs("option", {active: 1});

					return;
				}

                if( rows['nhtIsueYn'] == 'Y' ) {
                	alert("이미 고지된 건 입니다.");
                	return;
                }

                if( rows['save'] == 'Y' ) {
                	alert("이미 상계된 건 입니다.");
                	return;
                }

                if( confirm("선택한 건을 고지 하시겠습니까?") ) {
                	if(EMD.userinfo.emplNo=='11076') {
                    	rows['payTmlmt']=EMD.util.getDate(EMD.util.addDates(15));
                	}
                	if(rows['payTmlmt']==null || rows['payTmlmt']=="") rows['payTmlmt']=EMD.util.getDate(EMD.util.addDates(15));

                	rows['modifyFee'] = "";

                    this.doAction('/oper/gnrl/insertPrtFcltyRentFeeNticSingle.do', rows, function(module, result) {

                        if(result.resultCode=='0') {
                            //var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            //module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                            //module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                            var rows = module.$('#assetRentFeeList').selectedRows()[0];
                        	rows['nhtIsueYn']='Y';
                    		rows['nhtIsueYnStr']="고지";
                        	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
                        	module.$('#assetRentFeeList').flexUpdateRow(rowIds, rows);
                        	//module.$('#assetRentFeeList').flexUpdateRow('');
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
            	alert("목록에서 고지 할 건을 선택하십시오.");
            	return;
            }
        	break;
        case 'btnExecNticIssue2':
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                /*
                if( rows['sanctnSttus'] != '1' ) {
                	if(!confirm("결재완료 되지 않았습니다. 결재 처리 되지 않은 자료를 고지 하시겠습니까?")) {
                        return;
                	}
                }
                */

                if( rows['nhtIsueYn'] == 'Y' ) {
                	alert("이미 고지된 건 입니다.");
                	return;
                }

                if( confirm("선택한 건을 고지 하시겠습니까?") ) {
/* 고지 최소금액 3000원 수정 */
                	var fee = this.$('#fee').val().replace(/,/g, "")*1;
                	if(fee > 0 && fee < 3000){
                		alert("최소금액 3,000원으로 청구됩니다.");
                		this.$('#fee').val(3000);
                		this.changeFee();
                	}

                	rows['payTmlmt'] = this.$('#payTmlmt').val();
                	if(this._modifyFee) {
                		rows['modifyFee'] = "modified";
                    	rows['fee'] = this.$('#fee').val().replace(/,/g, "");
                    	rows['intrAmnt'] = this.$('#intrAmnt').val().replace(/,/g, "");
                    	rows['payinstIntrrate'] = this.$('#payinstIntrrate').val().replace(/,/g, "");
                    	rows['vat'] = this.$('#vat').val().replace(/,/g, "");
                    	this._modifyFee=false;
                	}
                	else {
                		rows['modifyFee'] = "";
                	}
                    this.doAction('/oper/gnrl/insertPrtFcltyRentFeeNticSingle.do', rows, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadDetail();
                        	var rows = module.$('#assetRentFeeList').selectedRows()[0];
                        	rows['nhtIsueYn']='Y';
                        	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
                        	module.$('#assetRentFeeList').flexUpdateRow(rowIds, rows);
                        	//module.$('#assetRentFeeList').selectRowId(rowIds);
                            /* var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload(); */
                        }

                        alert(result.resultMsg);
                        rows['nhtIsueYn']="Y";
//                        module.loadData();
                    });
                }
            } else {
            	alert("목록에서 고지 할 건을 선택하십시오.");
            	return;
            }
        	break;
        case 'btnCancelNticIssue':	// 고지 취소

        case 'btnCancelNticIssue2':
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['nhtIsueYn'] != 'Y' ) {
                	return;
                }

                if( confirm("선택한 건의 고지를 취소 하시겠습니까?") ) {
                    if( rows['nhtPrintYn'] == 'Y' && !confirm("출력한 자료를 취소하려고 합니다. 고지 취소를 하려면 먼저 출력을 취소 해야 합니다. 출력을 취소하면 발행된 마이너스 세금계산서가 발행 되고 징수의뢰 자료가 삭제 됩니다. 출력을 취소 하시겠습니까?") ) {
                    	return;
                    }

                    this.doAction('/oper/gnrl/cancelPrtFcltyRentFeeNticSingle.do', rows, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadDetail();
                        	var rows = module.$('#assetRentFeeList').selectedRows()[0];
                        	rows['nhtIsueYn']='N';
                        	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
                        	module.$('#assetRentFeeList').flexUpdateRow(rowIds, rows);

                             var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
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
        case 'btnNticIssuePrint2':	// 고지서 출력
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['save'] == 'Y' ) {
                	alert("투자비보전 상계가 되었기에 출력을 할 수 없습니다.");
                	return;
                }

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printPayNotice('/oper/gnrl/printRentFeeNotice.do', rows);
            } else {
            	alert("목록에서 고지서를 출력 할 항목을 선택하십시오.");
            	return;
            }
        	break;

/* 산정조서 다운로드 추가
 * jckim
 * 2019.07.08
 */
        case 'btnReportHwp':
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {
                this._rows = this.$('#assetRentFeeList').selectedRows()[0];
                if( this._rows['save'] == 'Y' ) {
                	alert("투자비보전 상계가 되었기에 출력을 할 수 없습니다.");
                	return;
                }

                if( this._rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.doExecuteDialog('popupRentFeeReportHwp', '산정조서 근거', '/oper/gnrl/popupRentFeeReportHwp.do', null);

            } else {
            	alert("다운로드할 목록에서 선택하십시오.");
            	return;
            }
        	break;

        case 'btnTaxPrint':	// 계산서 출력
        case 'btnTaxPrint2':	// 계산서 출력
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printTaxNotice('/oper/gnrl/printPrtFcltyRentFeeTaxNotice.do', rows);
            } else {
            	alert("목록에서 고지서를 출력 할 항목을 선택하십시오.");
            	return;
            }
        	break;
        case 'btnNoticeAdit':	// 추가고지
        case 'btnNoticeAdit2':
            var rows = this.$('#assetRentFeeList').selectedRows();
            var row = this.$('#assetRentFeeList').selectedRows()[0];

            if( row['aditNticYn'] == 'Y' ) {
                alert("추가 고지건에 대해 추가 고지 할 수 없습니다.");
                return;
            }

            if(rows.length>=1) {
                this.doExecuteDialog('insertLevReqestAdit', '사용료 추가', '/oper/gnrl/popupLevReqestAdit.do', rows[0]);
            } else {
                alert("목록에서 선택하십시오.");
            }
            var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
            this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
            break;
        case 'btnNoticeAditDel':	// 추가 고지 삭제
            var rows = this.$('#assetRentFeeList').selectedRows();
            var row = this.$('#assetRentFeeList').selectedRows()[0];

            if( row['aditNticYn'] != 'Y' ) {
                alert("추가 고지건이 아닙니다. 추가 고지 된 건만 삭제가 가능합니다");
                return;
            }
            if( rows['nhtIsueYn']== 'Y' ) {
            	alert("해당 건은 이미 고지되었습니다.");
            	return;
            }

            if(rows.length>=1) {
                if( confirm("선택한 추가사용료 항목을 삭제 하시겠습니까?") ) {
                    this.doAction('/cmmn/fclty/gamDeleteLevreqestAdit.do', row, function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                        }

                        alert(result.resultMsg);
                    });
                }
            } else {
                alert("목록에서 선택하십시오.");
            }

        break;
        case 'btnSaveRmk':	// 비고 저장
        	var ntcUpdate = this.makeFormArgs('#gamAssetRentFeeForm');
    	   	 	this.doAction('/oper/gnrl/updatePrtFcltyRentFeeMngtListDetail.do', ntcUpdate, function(module, result) {
    				if (result.resultCode == "0") {
    					module.loadData();
    				} else {
    				}
					alert(result.resultMsg);
    			});
        	break;
        // 팝업을 호출한다.(업체)
        case 'popupEntrpsInfoFee':
            var opts={};

            this.doExecuteDialog('selectEntrpsInfoFeePopup', '업체 선택', '/popup/showEntrpsInfo.do', opts);
            break;

        case 'btnPrtFcltyRentFeeMngtListExcelDownload':	// 엑셀 다운로드
        	this.$('#assetRentFeeList').flexExcelDown('/oper/gnrl/selectPrtFcltyRentFeeMngtListExcel.do');
            	break;

        case 'btnRentFeePayMngt':
            var rows = this.$('#assetRentFeeList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentFeePay',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt, nticCnt: rows[0].mngCnt }
            	};
            }
       	 	EMD.util.create_window('gamPrtFcltyRentFeePaySttusMngt', '항만시설납부현황관리', '/oper/gnrl/gamPrtFcltyRentFeePaySttusMngt.do', null, opts);
        	break;
        case 'btnPrtFcltyUseDetail': //고지상세
        	var row = this.$('#assetRentFeeList').selectedRows()[0];
        	var opt = {
        				action: "nticDetail",
        				nticVo:{
	              			prtAtCode: row.prtAtCode,
	               			mngYear: row.mngYear,
	               			mngNo: row.mngNo,
	               			mngCnt: row.mngCnt,
        				}
        	};
       	 	EMD.util.create_window('gamPrtFcltyRentMngt', '항만시설사용목록관리', '/oper/gnrl/gamPrtFcltyRentMngt.do', null, opt);
			break;
    }
};

GamAssetRentFeeMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentFeeMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
    this.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();

};

GamAssetRentFeeMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(newTabId=='tabs2') {
		if(this.$('#assetRentFeeList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 사용료 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
};

GamAssetRentFeeMngtModule.prototype.loadDetail = function() {
	var row = this.$('#assetRentFeeList').selectedRows()[0];

	var nticDetail = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'nticCnt', value: row.nticCnt },
	               { name: 'chrgeKnd', value: row.chrgeKnd }
	             ];
	this.doAction('/oper/gnrl/gamSelectPrtFcltyRentFeeMngtListDetail.do', nticDetail, function(module, result) {
		if (result.resultCode == "0") {
			/*
			if(result.resultMaster.nhtIsueYn == 'N'){
				result.resultMaster.payTmlmt = EMD.util.getDate(EMD.util.addDates(15));
			}
			*/
			if(result.resultMaster.payTmlmt==null) {
				result.resultMaster.payTmlmt = EMD.util.getDate(EMD.util.addDates(15));
			}

			module.makeDivValues('#masterFeeInfo', result.resultMaster); // 결과값을 채운다.
			module.makeMultiDivValues('#detailFeeInfo',result.resultList , function(row) {
				if(row.currLevReqest=="Y") $(this).addClass("detailRowSelected");
				else {
					if($(this).hasClass("detailRowSelected")) $(this).removeClass("detailRowSelected");
				}
			} );	// 리스트 값을 채운다
			module.makeDivValues('#summaryFeeInfo', result.resultSummary); // 결과값을 채운다.
			module.makeFormValues('#gamAssetRentFeeForm', result.resultMaster);

			module.nticPdFrom=result.resultMaster.nticPdFrom;
			module.nticPdTo=result.resultMaster.nticPdTo;

			var nticMth=result.resultMaster.nticMth;
			module.nticMth=nticMth;
			if(nticMth=='1' || nticMth=='6') {	// 납부 방법이 일괄이나 연납인 경우
				module.$('.interstRow').hide();
				module.$('#payinstIntrrate').val(0);
				module.$('#intrAmnt').val(0);
			}
			else {
				module.$('.interstRow').show();
				var payinstIntrrate=result.resultMaster.payinstIntrrate||0;
				var bal=result.resultMaster.grFee-result.resultMaster.fee*result.resultMaster.nticCnt;
				module.instBal=bal;
				module.nticCnt=result.resultMaster.nticCnt;
				//console.log(payinstIntrrate);
				module.$('#payinstIntrrate').val($.number(payinstIntrrate*100,2));
				module.changeInterest();
			}
		} else {
			alert(result.resultMsg);
		}
	});
};

GamAssetRentFeeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
		this.loadDetail();
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
			 this.loadData();
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
    case 'insertLevReqestAdit':
    	if(msg == 'ok') {
	       var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
	       this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
    	}
    	break;
    case 'popupRentFeeReportHwp':
    	if(msg == 'ok') {
 	       this._rows.check1 = value.check1
 	       this._rows.check2 = value.check2
 	       this._rows.check3 = value.check3
 	       this._rows.check4 = value.check4
 	       this._rows.check5 = value.check5
 	       this._rows.other = value.other

	   		$.fileDownload(EMD.context_root+'/oper/gnrl/rentFeeReportHwp.do', {data:this._rows, httpMethod:"POST"});
     	}
    	break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

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
								<input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" />
                            </td>
                            <th>관리번호</th>
                            <td>
                                <input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>신청업체</th>
                            <td colspan="2">
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="25" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfoFee" class="popupButton">선택</button>
                            </td>
                        	<th>상계여부</th>
                            <td>
                         		<input id="sSaveYn" class="ygpaYnSelect" data-default-prompt="전체" data-column-id="sSaveYn" />
                            </td>
                           	<td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                        <!--
                            <th>결재여부</th>
                            <td width="100px">
                         		<input id="sanctnSttus" class="ygpaCmmnCd" data-code-id="GAM046" data-default-prompt="전체" data-column-id="sanctnSttus" />
                            </td>
                             -->
                        	<th>고지여부</th>
                            <td>
                         		<input id="sNhtIsueYn" class="ygpaYnSelect" data-default-prompt="전체" data-column-id="sNhtIsueYn" />
                            </td>
                            <th>출력여부</th>
                            <td>
                         		<input class="ygpaYnSelect" data-default-prompt="전체" data-column-id="nhtPrintYn" />
                            </td>
                            <th>요금종류</th>
                            <td>
                            	<select id="sChrgeKnd">
                        			<option value="" selected="selected">전체</option>
                        			<option value="GP">여수지역 수역점용료</option>
									<option value="GJ">여수지역 야적장사용료</option>
									<option value="GM">여수지역 항만부지사용료</option>
									<option value="GO">여천지역 수역점용료</option>
									<option value="GI">여천지역 야적장사용료</option>
									<option value="GL">여천지역 항만부지사용료</option>
									<option value="DB">변상금</option>
									<option value="GN">광양지역 수역점용료</option>
									<option value="GH">광양지역 야적장사용료</option>
									<option value="GK">광양지역 항만부지사용료</option>
									<option value="GE">석탄LGP사포부두 임대료</option>
									<option value="UC">일반부두임대 연체료</option>
									<option value="GD">중마일반부두 임대료</option>
                        		</select>
                        	</td>
                        	<th>
                            	<select id="searchCondition">
	                            	<option value="nticPdDt" selected="selected">고지대상일자</option>
	                            	<option value="usagePd">사용기간</option>
	                            	<option value="nticDt">고지일자</option>
                            	</select>
                            </th>
                            <td colspan="2">
                            	<input id="sUsagePdFrom" type="text" class="emdcal" size="10"> ~
                            	<input id="sUsagePdTo" type="text" class="emdcal" size="10">
                            </td>
                        </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만시설사용료고지 목록</a></li>
                <li><a href="#tabs2" class="emdTab">항만시설사용료고지 상세</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="assetRentFeeList" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
                    <table id="summaryTable" style="width:100%;" class="summaryPanel">
                    	<colgroup>
		                	<col width="100" />
		                	<col width="50"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="100" />
		                	<col width="90"/>
		                	<col width="100" />
		                	<col width="90"/>
		                </colgroup>
                        <tr>
                        	<th>
                        		자료수
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="totalCount" class="ygpaNumber"></span>
                           	</td>
                     	    <th>
                        		사용료합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumFee" class="ygpaNumber"></span>
                           	</td>
                     	    <th>
                        		부가세합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumVat" class="ygpaNumber"></span>
                           	</td>
                     	    <th>
                        		고지금액합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumNticAmt" class="ygpaNumber"></span>
                           	</td>
                     	    <th>
                        		고지의뢰합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumNhtIsueAmt" class="ygpaNumber"></span>
                           	</td>
                        </tr>
                    </table>
                    <!-- <button id="btnEApproval">결재요청</button> -->
                    <button id="btnExecSave">상계</button>
                    <button id="btnCancelSave">상계취소</button>
                    <button id="btnExecNticIssue">고지</button>
                    <button id="btnCancelNticIssue">고지취소</button>
                    <button id="btnNticIssuePrint">고지서출력</button>
                    <!--
                    <button id="btnTaxPrint">계산서출력</button>
                     -->
                    <button id="btnNoticeAdit">추가고지</button>
                    <button id="btnNoticeAditDel">추가고지삭제</button>
                    <button id="btnPrtFcltyRentFeeMngtListExcelDownload">엑셀</button>
                    <button id="btnRentFeePayMngt">납부현황관리</button>
                    <button id="btnPrtFcltyUseDetail">사용내역 조회</button>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
                   <div class="emdPanel">
<!--
                   	<h2>시설사용내역</h2>
 -->
                   	<form id="gamAssetRentFeeForm">
                   	<input type="hidden" data-column-id="prtAtCode"/>
                   	<input type="hidden" data-column-id="mngYear"/>
                   	<input type="hidden" data-column-id="mngNo"/>
                   	<input type="hidden" data-column-id="mngCnt"/>
                   	<input type="hidden" data-column-id="nticCnt"/>
                   	<input type="hidden" data-column-id="chrgeKnd"/>
                    <table id="masterFeeInfo" class="detailPanel">
                        <tr>
                        	<th style="width: 100px"><span class="label">항구분</span></th>
                            <td style="width: 180px"><span class="ygpaCmmnCd" data-code-id="GAM019" data-column-id="prtAtCode"></span> (<span data-column-id="prtAtCode"></span>)</td>
                            <th style="width: 100px"><span class="label">회계년도</span></th>
                            <td style="width: 70px"><span data-column-id="accnutYear"></span></td>
                            <th style="width: 100px"><span class="label">고지횟수</span></th>
                            <td style="width: 70px"><span data-column-id="nticCnt"></span></td>
                            <th style="width: 100px"><span class="label">고지번호</span></th>
                            <td style="width: 120px"><span data-column-id="nticno"></span></td>
                        </tr>
                        <tr>
                            <th><span class="label">총사용기간</span></th>
                            <td>
                            	<span data-column-id="grUsagePdFrom"></span>~
                            	<span data-column-id="grUsagePdTo"></span>
                            </td>
                            <th><span class="label">총사용면적</span></th>
                            <td colspan="3"><span data-column-id="grAr"></span> m²</td>
                        	<th><span class="label">총사용료</span></th>
                            <td style="text-align:right;"><span data-column-id="grFee" class="ygpaNumber"></span> 원</td>
                        </tr>
                        <tr>
                        	<th><span class="label">요금종류</span></th>
                            <td>
                            	<span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)
                            </td>
                        	<th><span class="label">업체</span></th>
                            <td colspan="3">
                            	<span data-column-id="entrpsNm"></span> (<span data-column-id="entrpscd"></span>)
                            </td>
                        	<th><span class="label">사용료</span></th>
                            <td style="text-align:right;"><input id="fee" data-column-id="fee" class="ygpaNumber" size="11"/> 원</td>
                        </tr>
                        <tr class="interstRow">
                        	<th><span class="label">이자율</span></th>
                            <td colspan="3">
                            	<input id="payinstIntrrate" data-column-id="payinstIntrrate" class="ygpaNumber"  data-decimal-point="2" size="8"/> %
                            	<select id="cofixList">
                                        <option value="">선택</option>
                                        <c:forEach items="${cofixList}" var="cofixListItem">
                                            <option value="${cofixListItem.code }">${cofixListItem.codeNm }</option>
                                        </c:forEach>
                                </select>
                            	<button id="cofixMngt" data-role="openWindow" data-url="/code/gamCofixIntrrateMngt.do" data-title="COFIX 이자율 관리">이자율관리</button>
                            	<input id="monthIntrAmnt" type="checkbox" class="skipValue"> 월이자
                            </td>
                        	<th><span class="label">이자</span></th>
                            <td>
                            	<input id="intrAmnt" data-column-id="intrAmnt" class="ygpaNumber" size="9"/>원
                            </td>
                        	<th><span class="label">공급 가액</span></th>
                            <td style="text-align:right;"><span id="totPrice" class="ygpaNumber"></span> 원</td>
                        </tr>
                        <tr>
                        	<th><span class="label">부가세여부</span></th>
                            <td>
                            	<span data-column-id="vatYn" class="ygpaCmmnCd" data-code-id="GAM016"></span>
                            </td>
                        	<th><span class="label">부가세</span></th>
                            <td colspan="3">
                            	<input id="vat" data-column-id="vat" class="ygpaNumber" size="11"/> 원 <input id="roundVat" type="checkbox" class="skipValue"> 반올림
                            </td>
                        	<th><span class="label">변상금</span></th>
                            <td style="text-align:right;"><input data-column-id="reimFee" class="ygpaNumber" size="11"/> 원</td>
                        </tr>
                        <tr>
                            <th><span class="label">고지기간</span></th>
                            <td>
                            	<span data-column-id="nticPdFrom"></span>~
                            	<span data-column-id="nticPdTo"></span>
                            </td>
                            <th><span class="label">결재구분/결재일</span></th>
                            <td colspan="3">
                            	<span data-column-id="sanctnSttusNm"></span>/
                            	<span data-column-id="sanctnDt"></span>
                            </td>
                            <th><span class="label">결재자</span></th>
                            <td><span data-column-id="sanctnerEmplNo"></span></td>
                        </tr>
                        <tr>
                            <th><span class="label">고지여부</span></th>
                            <td><span data-column-id="nhtIsueYn" class="ygpaYnSelect" data-y-prompt="고지" data-n-prompt="미고지"></span></td>
                            <th><span class="label">고지일자</span></th>
                            <td colspan="3"><span data-column-id="nticDt"></span></td>
                        	<th><span class="label">고지금액</span></th>
                            <td style="text-align:right;" colspan="3"><span id="nticAmt" data-column-id="nticAmt" class="ygpaNumber"></span> 원</td>
                        </tr>
                        <tr>
                            <th><span class="label">납부기한일자</span></th>
                            <td><input id="payTmlmt" data-column-id="payTmlmt" class="emdcal" /></td>
                        	<th><span class="label">수납구분</span></th>
                            <td><span data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025"></span></td>
                        	<th><span class="label">고지서출력여부</span></th>
                            <td><span data-column-id="nhtPrintYn" ></span></td>
                            <th><span class="label">수납일자</span></th>
                            <td colspan="5"><span data-column-id="rcivDt"></span></td>
                        </tr>
                   		<tr>
                   			<th>비고</th>
                   			<td colspan="7" style="vertical-align:middle;">
                   				<textarea rows="3" cols="85" data-column-id='rm'></textarea>
                   				<button id="btnSaveRmk" class="buttonSave">저장</button>
                   			</td>
                 		</tr>
                    </table>
                    </form>

					<table class="searchPanel">
					<tbody>
						<tr>
							<th width="40%">사용료목록</th>
							<th style="text-align:right">
								<!-- <button id="btnEApproval2">결재요청</button> -->
								<button id="btnExecNticIssue2">고지</button>
								<button id="btnCancelNticIssue2">고지취소</button>
								<button id="btnNticIssuePrint2">고지서출력</button>
								<button id="btnReportHwp">산정조서 다운로드</button>
								<!--
								<button id="btnTaxPrint2">계산서출력</button>
								-->
			                    <button id="btnNoticeAdit2">추가고지</button>
			                    <button id="btnNoticeAditDel2">추가고지삭제</button>
							</th>
						</tr>
					</tbody>
					</table>
<!--
					<h2>전체 사용료 목록</h2>
 -->
               		<table class="detailPanel">
                    	<thead>
                    		<tr>
	                            <th style="text-align:center; width: 50px"><span class="label">회차</span></th>
	                            <th style="text-align:center; width: 70px"><span class="label">회계년도</span></th>
	                            <th style="text-align:center; width: 70px"><span class="label">고지번호</span></th>
	                        	<th style="text-align:center; width: 150px"><span class="label">요금종류</span></th>
	                        	<th style="text-align:center; width: 90px"><span class="label">고지금액</span></th>
	                            <th style="text-align:center; width: 110px"><span class="label">고지일자</span></th>
	                            <th style="text-align:center; width: 110px"><span class="label">납부기한일자</span></th>
	                        	<th style="text-align:center; width: 70px"><span class="label">수납구분</span></th>
	                            <th style="text-align:center; width: 110px"><span class="label">수납일자</span></th>
                            </tr>
                    	</thead>
                    	<tbody id="detailFeeInfo" >
                    		<tr>
	                            <td style="text-align:center;"><span data-column-id="nticCnt"></span></td>
	                            <td style="text-align:center;"><span data-column-id="accnutYear"></span></td>
	                            <td style="text-align:center;"><span data-column-id="nticno"></span></td>
	                            <td style="text-align:left;"><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
	                            <td style="text-align:right;"><span data-column-id="nticAmt" class="ygpaNumber"> 원</span></td>
	                            <td style="text-align:center;"><span data-column-id="nticDt"></span></td>
	                            <td style="text-align:center;"><span data-column-id="payTmlmt"></span></td>
		                        <td style="text-align:center;"><span data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025"></span></td>
	                            <td style="text-align:center;"><span data-column-id="rcivDt"></span></td>
                            </tr>
                    	</tbody>
                    </table>
                    <table id="summaryFeeInfo" class="summaryPanel">
                        <tr>
                            <th><span class="label">사용료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumFee" class="ygpaNumber"></span></td>
                        	<th><span class="label">고지금액</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumNticAmt" class="ygpaNumber"></span></td>
                            <th><span class="label">관리비</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumFeeA4" class="ygpaNumber"></span></td>
                            <th><span class="label">이자</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumFeeA3" class="ygpaNumber"></span></td>
                            <th><span class="label">연체료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumFeeD1" class="ygpaNumber"></span></td>
                            <th><span class="label">과태료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="sumFeeD2" class="ygpaNumber"></span></td>
                        </tr>
                    </table>
				</div>
<!--
				<div class="emdControlPanel">
					<button id="btnEApproval2">결재요청</button>
					<button id="btnExecNticIssue2">고지</button>
					<button id="btnCancelNticIssue2">고지취소</button>
					<button id="btnNticIssuePrint2">고지서출력</button>
					<button id="btnTaxPrint2">계산서출력</button>
				</div>
 -->
	        </div>
	    </div>
	</div>
</div>
