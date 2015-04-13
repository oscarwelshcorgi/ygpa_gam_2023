<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamHtldRentFeeMngt.jsp
  * @Description : 배후단지임대료관리
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

GamAssetRentFeeMngtModule.prototype = new EmdModule(1070, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAssetRentFeeMngtModule.prototype.loadComplete = function(params) {

    // 테이블 설정 //
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '/oper/htld/gamSelectHtldRentFeeMngtList.do',
        dataType: 'json',
        colModel : [
				{display:'상태', name:'state',width:30, sortable:false,align:'center'},
                {display:'구역', name:'rentArea',width:82, sortable:false,align:'center'},
				{display:'고지횟수', name:'nticCnt',width:50, sortable:false,align:'center'},
				{display:'입주기업', name:'entrpsNm',width:140, sortable:false,align:'left'},
				{display:'고지대상기간', name:'nticPdDate',width:160, sortable:false,align:'center'},
				{display:'고지방법', name:'nticMthNm',width:70, sortable:false,align:'center'},
				{display:'고지', name:'nhtIsueYn',width:50, sortable:false,align:'center'},
				{display:'출력', name:'nhtPrintYn',width:50, sortable:false,align:'center'},
				{display:'사용료', name:'fee',width:80, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'이자', name:'intrAmnt',width:80, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'이자율(%)', name:'intrRate',width:60, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.00"},
				{display:'소계', name:'feeAmnt',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'고지금액', name:'nticAmt',width:100, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'}
				// {display:'결재상태', name:'sanctnSttusNm',width:60, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        rowHeight: 50,
        groupBy: "rentArea",
        preProcess: function(module,data) {
        	$.each(data.resultList, function() {
        		module.makeRowData(this);
        	});


        	module.makeDivValues('#summaryTable', data.resultSum);

            return data;
        }
    });

    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.setButtonStatus();
    });

    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        //module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
        var rows = module.$('#assetRentFeeList').selectedRows();
        var opts = {};

        if(rows.length>0) {
        	opts = {
        			action: 'selectRentFeePay',
        			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt, nticCnt: rows[0].mngCnt }
        	};
        }
   	 	EMD.util.create_window('배후단지납부현황관리', '/oper/htld/gamHtldRentFeePaySttusMngt.do', null, opts);
    });

    this.$("#assetRentFeeList").on('onCellEdited', function(event, module, row, rid, cid) {
        // 이벤트내에선 모듈에 대해 선택한다.
        if(cid=="fee") {
        	if(row.nhtIsueYn=='Y') {
        		alert('고지된 자료는 수정 되지 않습니다.');
        		return;
        	}
            if(row._updtId!="I") {
            	row._updtId="U";
            	row.state="*";
            }
            var fee=Number(row['fee']);
            var intrAmnt=Number(row['intrAmnt']);
            var feeAmnt = 0;
            var vat = 0;
			var dtfr = EMD.util.strToDate(module.$('#grUsagePdFrom').val());
			var intrRate=Number(row['intrRate']);

			if(intrRate!=0 && row['nticMth']!='1') {
				intrRate/=100;
				if(row['nticMth']=='2'){
					if(dtfr.getMonth()<6) {
						intrAmnt=fee*intrRate;
					}
				}
				else if(row['nticMth']=='3'){
					if(dtfr.getMonth()<4) {
						intrAmnt=(fee*2)*intrRate;
					}
					else if(dtfr.getMonth()<8) {
						intrAmnt=(fee)*intrRate*2/3;
					}
				}
				else if(row['nticMth']=='4'){
					if(dtfr.getMonth()<3) {
						intrAmnt=(fee*3)*intrRate;
					}
					else if(dtfr.getMonth()<6) {
						intrAmnt=fee*intrRate;
					}
					else if(dtfr.getMonth()<9) {
						intrAmnt=fee*intrRate/4;
					}
				}
				intrAmnt=Math.floor(intrAmnt*0.1)*10;
	            feeAmnt=fee+intrAmnt;
			}
			else {
				intrAmnt=0;
			}
			row['intrAmnt']=intrAmnt;
            row['feeAmnt']=feeAmnt;
            if(row['vatYn']=='2' || row['vatYn']=='Y') {
                vat=Math.floor(feeAmnt*0.01)*10;
            }
            else vat=0;
            row['vat']=vat;
            row['nticAmt']=feeAmnt+vat;

            module.$("#assetRentFeeList").flexUpdateRow(rid, row);
        	module.onCalc();
        }
        if(cid=="vat") {
        	if(row.nhtIsueYn=='Y') {
        		alert('고지된 자료는 수정 되지 않습니다.');
        		return;
        	}
            if(row._updtId!="I") {
            	row._updtId="U";
            	row.state="*";
            }
            var feeAmnt=Number(row['feeAmnt']);
            row['nticAmt']=feeAmnt+Number(row['vat']);
            module.$("#assetRentFeeList").flexUpdateRow(rid, row);
        	module.onCalc();
        }
        if(cid=="nticAmt") {
        	if(row.nhtIsueYn=='Y') {
        		alert('고지된 자료는 수정 되지 않습니다.');
        		return;
        	}
            if(row._updtId!="I") {
            	row._updtId="U";
            	row.state="*";
            }
            module.$("#assetRentFeeList").flexUpdateRow(rid, row);
        	module.onCalc();
        }
        if(cid=="intrAmnt") {
        	var fee=Number(row['fee']);
			var intrAmnt = Number(row['intrAmnt']);
            var vat=Number(row['vat']);
			var feeAmnt=fee+intrAmnt;
            if(row._updtId!="I") {
            	row._updtId="U";
            	row.state="*";
            }
            feeAmnt=fee+intrAmnt;
            if(row['vatYn']=='2' || row['vatYn']=='Y') {
                vat=Math.floor(feeAmnt*0.01)*10;
            }
            else vat=0;
            row['feeAmnt']=feeAmnt;
            row['vat']=vat;
            row['nticAmt']=feeAmnt+vat;

            module.$("#assetRentFeeList").flexUpdateRow(rid, row);
        	module.onCalc();
        }
        if(cid=="intrRate") {
        	var fee=Number(row['fee']);
            var vat=Number(row['vat']);
			var dtfr = EMD.util.strToDate(module.$('#grUsagePdFrom').val());
			var intrRate=Number(row['intrRate']);
			var intrAmnt=0;
            if(row._updtId!="I") {
            	row._updtId="U";
            	row.state="*";
            }

			if(intrRate!=0 && row['nticMth']!='1') {
				intrRate/=100;
				if(row['nticMth']=='2'){
					if(dtfr.getMonth()<6) {
						intrAmnt=fee*intrRate;
					}
				}
				else if(row['nticMth']=='3'){
					if(dtfr.getMonth()<4) {
						intrAmnt=(fee*2)*intrRate;
					}
					else if(dtfr.getMonth()<8) {
						intrAmnt=(fee)*intrRate*2/3;
					}
				}
				else if(row['nticMth']=='4'){
					if(dtfr.getMonth()<3) {
						intrAmnt=(fee*3)*intrRate;
					}
					else if(dtfr.getMonth()<6) {
						intrAmnt=fee*intrRate;
					}
					else if(dtfr.getMonth()<9) {
						intrAmnt=fee*intrRate/4;
					}
				}
				intrAmnt=Math.floor(intrAmnt*0.1)*10;
			}
			else {
				intrAmnt=0;
			}
			row['intrAmnt']=intrAmnt;
			var feeAmnt=fee+intrAmnt;
            feeAmnt=fee+intrAmnt;
            if(row['vatYn']=='2' || row['vatYn']=='Y') {
                vat=Math.floor(feeAmnt*0.01)*10;
            }
            else vat=0;
            row['feeAmnt']=feeAmnt;
            row['vat']=vat;
            row['nticAmt']=feeAmnt+vat;

            module.$("#assetRentFeeList").flexUpdateRow(rid, row);
            /*
            if(confirm('모든 항목에 동일한 이자율을 적용 하시겠습니까?')) {
            	var d=module.$("#assetRentFeeList").flexGetData();
            }
            */
        	module.onCalc();
        }
    });

    this.$("#nticListGrid").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldRentFeeNticList.do',
        dataType: 'json',
        colModel : [
                    {display:'고지회차', name:'nticCnt', width:50, sortable:true, align:'center'},
                    {display:'요금부과기간', name:'rentPeriod', width:200, sortable:true, align:'center'},
                    {display:'요금종류', name:'chrgeKndNm', width:120, sortable:true, align:'center'},
                    {display:'고지금액', name:'billAmnt', width:100, sortable:true, align:'right', displayFormat:'number'},
                    {display:'고지일자', name:'billDt', width:110, sortable:true, align:'center'},
                    {display:'납부상태', name:'rcvdTpNm', width:80, sortable:true, align:'center'},
                    {display:'납부일자', name:'rcvdDt', width:110, sortable:true, align:'center'}
           ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
            $.each(data.resultList, function() {
            	switch(this.rcvdTp) {
            	case '1':
            		this.rcvdTpNm="연체고지";
            		break;
            	case '2':
            	case '3':
            		this.rcvdTpNm="수납";
            		break;
            	case '0':
        		default:
            		this.rcvdTpNm="미수납";
            		break;
            	}
            	this.rentPeriod = this.nticPdFrom + " ~ " + this.nticPdTo;
            });

            return data;
        }
    });


    if(params!=null) {
    	if(params.action=="selectRentFee") {
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);
    	    this.$('#grUsagePdFrom').val(EMD.util.getDate());

        	this.loadData();
    	}
    } else {
	    this.$('#grUsagePdFrom').val(EMD.util.getDate());
	    this.$('#grUsagePdTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.

    	this.loadData();
	}

    this.setEvents();

	this.setButtonStatus();

	console.log('debug');
};

GamAssetRentFeeMngtModule.prototype.setEvents = function() {
    this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}
    });
};

GamAssetRentFeeMngtModule.prototype.setButtonStatus = function() {
	var tab_active = this.$('#assetRentListTab').tabs('option', 'active');
	switch(tab_active) {
	case 'tabs1':
    	var row = this.$('#assetRentFeeList').selectedRows()[0];

    	if(row['nhtPrintYn']!='Y') {
    		this.$('#btnExecNticIssue').hide();
    	}
    	else {
    		this.$('#btnExecNticIssue').hide();
    	}

    	if(row['nhtIsueYn']!='Y') {
    		this.$('#btnExecNticIssue').show();
    	}
    	else {
    		this.$('#btnExecNticIssue').show();
    	}
		break;
	}
};

GamAssetRentFeeMngtModule.prototype.makeRowData = function(item) {
	item.grUsagePd = item.grUsagePdFrom+ '~'+ item.grUsagePdTo;
	if(item.nticPdFrom!=null && item.nticPdTo!=null) {
		item.nticPdDate = item.nticPdFrom + '~'+ item.nticPdTo;
		if(item.feeAmnt==undefined) {
			item.feeAmnt=item.fee+item.intrAmnt;
		}
		item.state="";
		return;
	}
	item._updtId="I";
	item.state="*";
	var dtfr = EMD.util.strToDate(this.$('#grUsagePdFrom').val());
	var dtto = null;
	if(item.nticMth=='1') {
		dtfr.setFullYear(dtfr.getFullYear()+1);
		dtfr.setMonth(0);
		dtfr.setDate(0);
		dtto = new Date(dtfr);
		dtto.setFullYear(dtto.getFullYear()+1);
	}
	else if(item.nticMth=='2'){
		if(dtfr.getMonth()<6) {
			dtfr.setMonth(7);
			dtfr.setDate(0);
		}
		else {
			dtfr.setFullYear(dtfr.getFullYear()+1);
			dtfr.setMonth(1);
			dtfr.setDate(0);
		}
		dtto = new Date(dtfr);
		dtto.setMonth(dtto.getMonth()+6);
	}
	else if(item.nticMth=='3'){
		if(dtfr.getMonth()<4) {
			dtfr.setMonth(5);
			dtfr.setDate(0);
		}
		else if(dtfr.getMonth()<8) {
			dtfr.setMonth(9);
			dtfr.setDate(0);
		}
		else {
			dtfr.setFullYear(dtfr.getFullYear()+1);
			dtfr.setMonth(1);
			dtfr.setDate(0);
		}
		dtto = new Date(dtfr);
		dtto.setMonth(dtto.getMonth()+4);
	}
	else if(item.nticMth=='4'){
		if(dtfr.getMonth()<3) {
			dtfr.setMonth(3);
			dtfr.setDate(1);
		}
		else if(dtfr.getMonth()<6) {
			dtfr.setMonth(6);
			dtfr.setDate(1);
		}
		else if(dtfr.getMonth()<9) {
			dtfr.setMonth(9);
			dtfr.setDate(1);
		}
		else {
			dtfr.setFullYear(dtfr.getFullYear()+1);
			dtfr.setMonth(0);
			dtfr.setDate(1);
		}
		dtto = new Date(dtfr);
		dtto.setMonth(dtto.getMonth()+3);
		dtto.setDate(0);
	}
	if(dtto==null) {
		item.nticPdDate = '고지대상아님';
	}
	else {
		item.nticPdFrom = EMD.util.getDate(dtfr);
		item.nticPdTo = EMD.util.getDate(dtto);
		item.nticPdDate = item.nticPdFrom + '~'+ item.nticPdTo;
	}
	if((item.intrAmnt==undefined|| intrAmnt==null) && item.intrRate!=null && item.intrRate!=0) {
		var fee=Number(item.fee);
		var intrRate=Number(item.intrRate)/100;
		var intrAmnt=0;

		if(item.nticMth!='1') {
			if(item.nticMth=='2'){
				if(dtfr.getMonth()<6) {
					intrAmnt=fee*intrRate;
				}
			}
			else if(item.nticMth=='3'){
				if(dtfr.getMonth()<4) {
					intrAmnt=(fee*2)*intrRate;
				}
				else if(dtfr.getMonth()<8) {
					intrAmnt=(fee)*intrRate*2/3;
				}
			}
			else if(item.nticMth=='4'){
				if(dtfr.getMonth()<3) {
					intrAmnt=(fee*3)*intrRate;
				}
				else if(dtfr.getMonth()<6) {
					intrAmnt=fee*intrRate;
				}
				else if(dtfr.getMonth()<9) {
					intrAmnt=fee*intrRate/4;
				}
			}
			intrAmnt=Math.floor(intrAmnt*0.1)*10;
		}
		else {
			intrAmnt=0;
		}
		item.intrAmnt=intrAmnt;
	}
	if(item.feeAmnt==undefined) {
		item.feeAmnt=item.fee+item.intrAmnt;
	}
	var vatRate=0;
	if(item.vatYn=='2') {
		vatRate=0.1;
	}
	item.vat=Math.floor(item.feeAmnt*vatRate*0.1)*10;
	if(item.nticAmt===0) {
		item.nticAmt=item.feeAmnt+item.vat;
	}
};

<%--
 정의 된 버튼 클릭 시
--%>
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
        case 'btnSaveNticList':	// 고지 내역 저장
        	this.saveNticList();
        	break;
        case 'btnClearNticList': // 변경 내역 초기화
        	this.clearNticList();
        	break;
        case 'btnExecNticIssue':	// 고지 의뢰
        case 'btnExecNticIssue2':
        	this.openNticIssuePopup();
        	break;
        case 'btnCancelNticIssue':	// 고지 취소
        case 'btnCancelNticIssue2':
            if(this.$('#assetRentFeeList').selectedRowCount()>0) {

                //alert(EMD.context_root);

                var rows = this.$('#assetRentFeeList').selectedRows()[0];

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("고지되지 않았습니다.");
                	return;
                }

                if( confirm("선택한 건의 고지를 취소 하시겠습니까?") ) {
                	if(rows['nhtPrintYn']!='Y' || confirm("해당 건의 징수의뢰 자료가 삭제되고 전송된 세금계산서가 있을 경우 해당 업체에 마이너스 세금계산서가 발부 됩니다. 계속 하시겠습까?")) {
                        this.doAction('/oper/htld/cancelHtldRentFeeNticSingle.do', rows, function(module, result) {

                            if(result.resultCode=='0') {
                                var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                                module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                                module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                            }

                            alert(result.resultMsg);
                        });
                	}
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

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printPayNotice('/oper/htld/printHtldRentFeePayNotice.do', rows);
            } else {
            	alert("목록에서 고지서를 출력 할 항목을 선택하십시오.");
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
                this.doExecuteDialog('insertLevReqestAdit', '사용료 추가', '/oper/htld/popupLevReqestAdit.do', rows[0]);
            } else {
                alert("목록에서 선택하십시오.");
            }

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
    	   	 	this.doAction('/oper/htld/updateHtldRentFeeMngtListDetail.do', ntcUpdate, function(module, result) {
    				if (result.resultCode == "0") {

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
    	case 'btnExcelDownload':	// 엑셀 다운로드
    		this.$('#assetRentFeeList').flexExcelDown('/oper/htld/gamSelectHtldRentFeeMngtListExcel.do');
    		break;
    	case 'btnHwpDownload':	// HWP 다운로드
    		this.printPage('/oper/htld/gamHtldRentHwpPreview.do', {});
    		break;
        case 'btnRentFeePayMngt':	// 납부현황조회
            var rows = this.$('#assetRentFeeList').selectedRows();
            var opts = {};

            if(rows.length>0) {
            	opts = {
            			action: 'selectRentFeePay',
            			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt, nticCnt: rows[0].mngCnt }
            	};
            }
       	 	EMD.util.create_window('배후단지납부현황관리', '/oper/htld/gamHtldRentFeePaySttusMngt.do', null, opts);
        	break;
    }
};

GamAssetRentFeeMngtModule.prototype.onCalc = function() {
	var sum = {
			sumCnt:0,
			sumFee:0,
			sumVat:0,
			sumNticAmt:0,
			sumIntrAmt:0
	};

	this.$('#assetRentFeeList')[0].dgrid.forEachRow(function(id) {
		sum.sumCnt++;
		sum.sumFee+=Number(this.cells(id,10).getValue());
		sum.sumVat+=Number(this.cells(id,11).getValue());
		sum.sumIntrAmt+=Number(this.cells(id,8).getValue());
		sum.sumNticAmt+=Number(this.cells(id,12).getValue());
    });
	this.makeDivValues('#summaryTable', sum);
};

GamAssetRentFeeMngtModule.prototype.saveNticList = function() {
	var nticList = {};
	this.onCalc();

	nticList['_cList'] = JSON.stringify(this.$('#assetRentFeeList').selectFilterData([{col: '_updtId', filter: 'I'}]));
	nticList['_uList'] = JSON.stringify(this.$('#assetRentFeeList').selectFilterData([{col: '_updtId', filter: 'U'}]));

    this.doAction('/oper/htld/updateHtldRentFeeList.do', nticList, function(module, result) {
        if(result.resultCode == 0){
        	module.loadData();
        	module.setButtonStatus();
        }
        alert(result.resultMsg);
    });
};

GamAssetRentFeeMngtModule.prototype.clearNticList = function() {
	var nticYn='N';
	this.$('#assetRentFeeList')[0].dgrid.forEachRow(function(id) {
		if(this.cells(id, 5).getValue()=='Y') {
			nticYn='Y';
		}
    });
	if(nticYn=='Y') {
		alert('고지된 자료가 존재하여 자료를 초기화 할 수 없습니다.');
		return;
	}
	if(confirm('조회된 자료에 입력한 내역을 삭제하고 자료를 초기화 하시겠습니까?')) {
		var dtfr = this.$('#grUsagePdFrom').val();
		var dtto = this.$('#grUsagePdTo').val();

	    this.doAction('/oper/htld/clearHtldRentFeeList.do', {'nticPdFrom': dtfr, 'nticPdTo': dtto}, function(module, result) {
	        if(result.resultCode == 0){
	        	module.loadData();
	        	module.setButtonStatus();
	        }
	        alert(result.resultMsg);
	    });
	}
}

GamAssetRentFeeMngtModule.prototype.openNticIssuePopup = function() {
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

        if( rows['_updtId'] == "I" || rows['_updtId'] == "U" ) {
        	alert("저장 되지 않은 자료입니다. 저장 후 고지 하시기 바랍니다.");
        	return;
        }

        if( rows['nhtIsueYn'] == 'Y' ) {
        	alert("이미 고지된 건 입니다.");
        	return;
        }

    	var opts = {
                'prtAtCode': rows['prtAtCode'],
                'mngYear': rows['mngYear'],
                'mngNo': rows['mngNo'],
                'mngCnt': rows['mngCnt'],
                'nticCnt' : rows['nticCnt'],
                'taxtSe': rows['taxtSe']
            };

    	this.doExecuteDialog('nticIssuePopup', '사용료 고지', '/oper/htld/showNticIssuePopup.do', opts);

    } else {
    	alert("목록에서 고지 할 건을 선택하십시오.");
    	return;
    }
};

GamAssetRentFeeMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamAssetRentFeeMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
//    console.log(searchOpt);
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


GamAssetRentFeeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
    	this.loadDetail();
		break;
    }
};

GamAssetRentFeeMngtModule.prototype.loadDetail = function() {
    	var row = this.$('#assetRentFeeList').selectedRows()[0];

		var nticDetail = [
		               { name: 'prtAtCode', value: row.prtAtCode},
		               { name: 'mngYear', value: row.mngYear },
		               { name: 'mngNo', value: row.mngNo },
		               { name: 'mngCnt', value: row.mngCnt },
		               { name: 'nticCnt', value: row.nticCnt }
		             ];
	    /*
	    var searchOpt=EMD.util.objectToArray({
	    	prtAtCode: this._rentDetail.prtAtCode,
	    	mngYear: this._rentDetail.mngYear,
	    	mngNo: this._rentDetail.mngNo,
	    	mngCnt: this._rentDetail.mngCnt
	    });
	    */
	    this.$('#nticListGrid').flexOptions({params:nticDetail}).flexReload();
		/*
	this.doAction('/oper/htld/selectHtldRentFeeHistList.do', nticDetail, function(module, result) {
			if (result.resultCode == "0") {
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

			} else {
				alert(result.resultMsg);
			}
		});
		*/
};

<%
//팝업이 종료 될때 리턴 값이 오출 된다.
//popupId : 팝업 대화상자 아이디
//msg : 팝업에서 전송한 메시지 (취소는 cancel)
//value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
%>
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
    case 'insertLevReqestAdit':
    	if(msg == 'ok') {
 	       var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
 	       this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
     	}
    	break;
    case 'nticIssuePopup':
        if (msg != 'cancel') {
        	console.log('notice');
            //if( confirm($.number(value.nticAmt)+"원 을 고지 하시겠습니까?") ) {
            	var arg = EMD.util.objectToArray(value);
                this.doAction('/oper/htld/insertHtldFeeNticSingle.do', arg, function(module, result) {

                    if(result.resultCode=='0') {
                    	module.loadData();
                    	/*
                        var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                        module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                        module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                        */
                    }

                    alert(result.resultMsg);
                });
            //}
        } else {
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
            	<input id="sPrtAtCode" type="hidden" value="622"/>
                <input id="sMngYear" type="hidden" >
                <input id="sMngNo" type="hidden">
                <input id="sMngCnt" type="hidden">
                <table style="width:100%;" class="searchPanel">
                        <tr>
                            <th>신청업체</th>
                            <td>
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="15" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfoFee" class="popupButton">선택</button>
                            </td>
                        	<th>고지여부</th>
                            <td width="100px">
                         		<input id="sNhtIsueYn" class="ygpaYnSelect" data-default-prompt="전체" data-column-id="sNhtIsueYn" />
                            </td>
                            <td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>
                        <tr>
                            <th>조회기간</th>
                            <td colspan="3">
                            	<input id="grUsagePdFrom" type="text" class="emdcal" size="8"> ~
                            	<input id="grUsagePdTo" type="text" class="emdcal" size="8">
                            </td>
                        </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="assetRentFeeListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">배후단지사용료고지 목록</a></li>
                <!-- <li><a href="#tabs2" class="emdTab">배후단지사용료고지 이력</a></li> -->
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
                            	<span data-column-id="sumCnt" class="ygpaNumber"></span>
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
                        		이자 합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumIntrAmt" class="ygpaNumber"></span>
                           	</td>
                        </tr>
                    </table>
                    <!--
                    <button id="btnEApproval">결재요청</button>
                     -->
                    <button id="btnExecNticIssue">고지</button>
                    <button id="btnCancelNticIssue">고지취소</button>
                    <button id="btnNticIssuePrint">고지서출력</button>
                    <!--
                    <button id="btnTaxPrint">계산서출력</button>
                    <button id="btnNoticeAdit">추가고지</button>
                    <button id="btnNoticeAditDel">추가고지삭제</button>
                     -->
                   	<button data-role="gridXlsDown" data-flexi-grid="assetRentFeeList" data-xls-name="배후단지임대료고지.xls" data-xls-title="배후단지 임대료 고지 내역">엑셀</button>
                   	<!--
                    <button id="btnExcelDownload">엑셀다운로드</button>
                     <button id="btnHwpDownload">HWP</button>
                     -->
                    <button id="btnRentFeePayMngt">납부현황관리</button>
					<button id="btnSaveNticList">목록저장</button>
					<button id="btnClearNticList">초기화</button>
					<button data-role="openWindow" data-url="/code/gamCofixIntrrateMngt.do" data-title="COFIX 이자율 관리">이자율관리</button>
                </div>
            </div>

<!--             <div id="tabs2" class="emdTabPage" style="overflow: scroll;">
               	<table id="nticListGrid" style="display:none" class="fillHeight"></table>
                <div class="emdControlPanel">
                	<button id="btnCallBizNticAssess">임대료고지 관리</button>
                	<button data-role="gridXlsDown" data-flexi-grid="nticListGrid" data-xls-name="배후단지임대료고지내역.xls" data-xls-title="배후단지 임대료 고지 내역">엑셀</button>
              	</div>
	        </div> -->
	    </div>
	</div>
</div>
