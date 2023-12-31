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
GamAssetRentFeeMngtModule.prototype.loadComplete = function(params) {

    // 테이블 설정 //
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '/asset/rent/gamSelectAssetRentFeeMngtList.do',
        dataType: 'json',
        colModel : [
//					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
//                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
                    {display:'관리번호', name:'rentMngNo',width:96, sortable:false,align:'center'},
                    {display:'횟수', name:'nticCnt',width:40, sortable:false,align:'center'},
                    {display:'고지업체명', name:'entrpsNm',width:140, sortable:false,align:'left'},
                    {display:'업체코드', name:'entrpscd',width:70, sortable:false,align:'center'},
                    {display:'요금종류', name:'chrgeKndNm',width:100, sortable:false,align:'left'},
                    {display:'고지', name:'nhtIsueYnStr',width:40, sortable:false,align:'center'},
                    {display:'출력', name:'nhtPrintYnStr',width:40, sortable:false,align:'center'},
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
                    {display:'사용기간', name:'grUsagePd',width:140, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {

        	$.each(data.resultList, function() {
        		this.nticPdDate = this.nticPdFrom+ '~'+ this.nticPdTo;
        		this.grUsagePd = this.grUsagePdFrom+ '~'+ this.grUsagePdTo;
        		this.nhtIsueYnStr = this.nhtIsueYn=="Y"?"고지":"";
        		this.nhtPrintYnStr = this.nhtPrintYn=="Y"?"출력":"";
        	});

        	module.makeDivValues('#summaryTable', data);

            return data;
        }
    });

    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._modifyFee=false;
    });

    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module._modifyFee=false;

        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#assetRentFeeListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
    });

    if(params!=null) {
    	if(params.action=="selectRentFee") {
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);
//        	this.loadData();
    	}
    } else {
    	/*
        this.$('#sUsagePdFrom').val(EMD.util.getDate());
        this.$('#sUsagePdTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.
    	*/
    }
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

    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
    this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();

    this.$('#fee').on("keyup change", {module: this}, function(event) {
		event.data.module.changeFee();
    });

    this.$('#roundVat').on("change", {module: this}, function(event) {
		event.data.module.changeFee();
    });

    this._modifyFee=false;

//    console.log('loadComplete.');
};

<%--
	요금 변경
--%>
GamAssetRentFeeMngtModule.prototype.changeFee = function() {
	var fee = this.$('#fee').val().replace(/,/g, "")*1;
	if(this.$('#roundVat').attr('checked')=="checked") {
		this.$('#vat').val(Math.round(fee/100)*10);
	}
	else {
		this.$('#vat').val(Math.floor(fee/100)*10);
	}
	this._modifyFee=true;
}
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

                if( rows['nhtIsueYn'] == 'Y' ) {
                	alert("이미 고지된 건 입니다.");
                	return;
                }

                if( confirm("선택한 건을 고지 하시겠습니까?") ) {
                	if(EMD.userinfo.emplNo=='11076') {
                    	rows['payTmlmt']=EMD.util.getDate(EMD.util.addDates(15));
                	}
                	if(rows['payTmlmt']==null || rows['payTmlmt']=="") rows['payTmlmt']=EMD.util.getDate(EMD.util.addDates(15));

                	rows['modifyFee'] = "";

                    this.doAction('/asset/rent/insertAssetRentFeeNticSingle.do', rows, function(module, result) {

                        if(result.resultCode=='0') {
                            var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
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
                	rows['payTmlmt'] = this.$('#payTmlmt').val();
                	if(this._modifyFee) {
                		rows['modifyFee'] = "modified";
                    	rows['fee'] = this.$('#fee').val().replace(/,/g, "");
                    	rows['vat'] = this.$('#vat').val().replace(/,/g, "");
                    	this._modifyFee=false;
                	}
                	else {
                		rows['modifyFee'] = "";
                	}
                    this.doAction('/asset/rent/insertAssetRentFeeNticSingle.do', rows, function(module, result) {

                        if(result.resultCode=='0') {
                        	module.loadDetail();
                        	var rows = module.$('#assetRentFeeList').selectedRows()[0];
                        	rows['nhtIsueYn']='Y';
                        	var rowIds = module.$('#assetRentFeeList').selectedRowIds()[0];
                        	module.$('#assetRentFeeList').flexUpdateRow(rowIds, rows);
                            /* var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                            module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                            module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload(); */
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

                    this.doAction('/asset/rent/cancelAssetRentFeeNticSingle.do', rows, function(module, result) {

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

                if( rows['nhtIsueYn'] != 'Y' ) {
                	alert("해당 건은 아직 고지되지 않았습니다.");
                	return;
                }

                this.printPayNotice('/asset/rent/printRentFeeNotice.do', rows);
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

                this.printTaxNotice('/asset/rent/printAssetRentFeeTaxNotice.do', rows);
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
                this.doExecuteDialog('insertLevReqestAdit', '사용료 추가', '/asset/rent/popupLevReqestAdit.do', rows[0]);
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
    	   	 	this.doAction('/asset/rent/updateAssetRentFeeMngtListDetail.do', ntcUpdate, function(module, result) {
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

        case 'btnAssetRentFeeMngtListExcelDownload':	// 엑셀 다운로드
        	this.$('#assetRentFeeList').flexExcelDown('/asset/rent/selectAssetRentFeeMngtListExcel.do');
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
       	 	EMD.util.create_window('gamAssetRentFeePaySttusMngt', '자산임대납부현황관리', '/asset/rent/gamAssetRentFeePaySttusMngt.do', null, opts);
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
	this.doAction('/asset/rent/gamSelectAssetRentFeeMngtListDetail.do', nticDetail, function(module, result) {
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
                            <td style="width: 200px">
                                <input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>신청업체</th>
                            <td>
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="25" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfoFee" class="popupButton">선택</button>
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
                            <td width="100px">
                         		<input id="sNhtIsueYn" class="ygpaYnSelect" data-default-prompt="전체" data-column-id="sNhtIsueYn" />
                            </td>
                            <th>출력여부</th>
                            <td width="100px">
                         		<input class="ygpaYnSelect" data-default-prompt="전체" data-column-id="nhtPrintYn" />
                            </td>
                            <th>사용승낙일자</th>
                            <td>
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
                <li><a href="#tabs1" class="emdTab">자산임대사용료고지 목록</a></li>
                <li><a href="#tabs2" class="emdTab">자산임대사용료고지 상세</a></li>
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
                    <button id="btnExecNticIssue">고지</button>
                    <button id="btnCancelNticIssue">고지취소</button>
                    <button id="btnNticIssuePrint">고지서출력</button>
                    <!--
                    <button id="btnTaxPrint">계산서출력</button>
                     -->
                    <button id="btnNoticeAdit">추가고지</button>
                    <button id="btnNoticeAditDel">추가고지삭제</button>
                    <button id="btnAssetRentFeeMngtListExcelDownload">엑셀</button>
                    <button id="btnRentFeePayMngt">납부현황관리</button>
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
                            <td style="text-align:right;" colspan="3"><span data-column-id="nticAmt" class="ygpaNumber"></span> 원</td>
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
	                            <td style="text-align:right;"><span data-column-id="fee" class="ygpaNumber"> 원</span></td>
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
