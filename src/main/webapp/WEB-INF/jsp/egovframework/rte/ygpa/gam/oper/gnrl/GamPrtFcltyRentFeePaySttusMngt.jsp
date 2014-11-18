<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamPrtFcltyRentFeePaySttusMngt.jsp
 * @Description : 항만시설납부현황관리
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
<validator:javascript formName="gamArrrgNtic" method="validateGamArrrgNtic" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />

<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPrtFcltyRentFeePaySttusMngtModule() {}

GamPrtFcltyRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadComplete = function(params) {

    // 테이블 설정 //
    this.$("#prtFcltyRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/gamSelectPrtFcltyRentFeePaySttusMngtList.do" />',
        dataType: 'json',
        colModel : [
					{display:'항코드', name:'prtAtCode',width:40, sortable:false,align:'center'},
                    {display:'항코드명', name:'prtAtCodeNm',width:55, sortable:false,align:'center'},
					{display:'요금종류', name:'chrgeKnd',width:55, sortable:false,align:'center'},
					{display:'요금종류명', name:'chrgeKndNm',width:100, sortable:false,align:'left'},
					{display:'회계년도', name:'accnutYear',width:55, sortable:false,align:'center'},
					{display:'고지번호', name:'nticno',width:55, sortable:false,align:'center'},
					{display:'고지업체', name:'entrpscd',width:60, sortable:false,align:'center'},
					{display:'고지업체명', name:'entrpsNm',width:140, sortable:false,align:'left'},
					{display:'고지금액', name:'totalNticAmount',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
					{display:'납부기한', name:'payTmlmt',width:80, sortable:false,align:'center'},
                    {display:'사용료', name:'fee',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'수납구분', name:'rcivSeNm',width:55, sortable:false,align:'center'},
					{display:'수납일자', name:'rcivDt',width:80, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	module.makeFormValues('#summaryForm', data);
        	/*
            module.$('#sumCnt').val(data.sumCnt);
            module.$('#sumNhtIsueAmt').val(data.sumNhtIsueAmt);
            module.$('#sumVat').val(data.sumVat);
            module.$('#sumPayAmt').val(data.sumPayAmt);
        	module.makeDivValues('#prtFcltyRentFeePaySttusMngtListSum', data);
            */
            return data;
        }
    });

    this.$("#prtFcltyRentFeePaySttusMngtList").on('onItemSelected', function(event, module, row, grid, param) {
    });

    this.$("#prtFcltyRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        // 이벤트내에선 모듈에 대해 선택한다.
        module.$("#prtFcltyRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
    });

    /*
    this.$('#sNticDtFrom').val(EMD.util.getDate(EMD.util.addMonths(-1)));
    this.$('#sNticDtTo').val(EMD.util.getDate());
    */

    /*
    this.$('#arrrgDetail :input').on('change keyup', {module: this}, function(event) {
    	event.data.module.calcDlyDueDate();
    });
    */

    this.$('#dlyBillDt').on('change keyup', {module: this}, function(event) {
    	event.data.module.calcDlyDueDate();
    	event.data.module.calculateArrrgFee();
    });

    this.$('#arrrgRate :input').on('change keyup', {module: this}, function(event) {
    	event.data.module.calculateArrrgFee();
    });

    /* 연체내역 목록 */
    this.$("#prtFcltyRentFeePaySttusArrrgList").flexigrid({
        module: this,
        url: '<c:url value="/oper/gnrl/selectPrtFcltyRentFeePaySttusMngtDlyList.do" />',
        dataType: 'json',
        colModel : [
					{display:'연체횟수', name:'dlySerNo',width:67, sortable:false,align:'center'},
                    {display:'연체고지금액', name:'dlyBillAmnt',width:104, sortable:false,align:'right', displayFormat: 'number'},
					{display:'연체고지일자', name:'dlyBillDt',width:110, sortable:false,align:'center'},
					{display:'고지서발부여부', name:'dlyBillPrtYn',width:100, sortable:false,align:'center'},
					{display:'산출내역', name:'dlyBillRsn',width:300, sortable:false,align:'center'},
					{display:'연체납부기한', name:'dlyDueDt',width:110, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
    		if (data.resultCode == "0") {
    			module.makeDivValues('#prtFcltyRentFeePaySttusMngtListForm',data.resultDlyInfo);	// 리스트 값을 채운다

    			module.makeDivValues('#prtFcltyRentFeePaySttusMngtSum', data.resultSummary); // 결과값을 채운다.

    		} else {
    			alert(data.resultMsg);
    		}
            return data;
        }
    });

    if(params!=null) {
    	if(params.action=="selectRentFeePay") {
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);
        	//	this.$('#sNticCnt').val(params.nticVo.nticCnt);	// 고지 번호
        	this.$('#rcivSe').val("");
        	this.loadData();
    	}
    } else {
    	/*
        this.$('#sUsagePdFrom').val(EMD.util.getDate());
        this.$('#sUsagePdTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.
    	*/
    }
    console.log('loadCompleted');
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamPrtFcltyRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {
     var opts=null;

    switch(buttonId) {
        // 조회
        case 'searchBtn':
        	this.loadData();
        	console.log("debug");
            break;

        // 팝업을 호출한다.(업체)
        case 'popupEntrpsInfo':
            var searchOpt=this.makeFormArgs('#gamPrtFcltyRentFeePaySttusMngtSearchForm');
            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '<c:url value="/popup/showEntrpsInfo.do"/>', opts, searchOpt);
            break;

        case 'btnUpdatePayDtls':	// 납부 현황 새로 고침
            this.doAction('<c:url value="/oper/gnrl/updatePrtFcltyRentFeePaySttusMngtList.do" />', null, function(module, result) {

                if(result.resultCode=='0') {
                    var searchOpt=module.makeFormArgs('#gamPrtFcltyRentFeePaySttusMngtSearchForm');
                    module.$('#prtFcltyRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
                }

                alert(result.resultMsg);
            });
        	break;
        case 'btnNticArrrg':
            this.doExecuteDialog('nticArrrgPopup', '연체 일괄 고지', '<c:url value="/oper/gnrl/showNticArrrgPopup.do"/>', opts);
        	break;
        case 'btnNticArrrgSingle':
			this.nticArrrgSingle();
        	break;
        case 'btnNticArrrgCancel':
        	this.nticArrrgCancelAll();
        	break;
        case 'btnNticArrrgCancelPk':
        	var rows = this.$('#prtFcltyRentFeePaySttusArrrgList').selectedRows();
			if(rows.length==0) {
				alert('취소할 대상을 선택 하십시요.');
				return;
			}
        	this.nticArrrgCancelPk(rows[0]);
        	break;
        case 'btnNticIssuePrintCancelLast':
        	var rows = this.$('#prtFcltyRentFeePaySttusArrrgList').selectedRows();
			if(rows.length==0) {
				alert('취소할 대상을 선택 하십시요.');
				return;
			}
        	this.nticArrrgPrintCancelLast(rows[0]);
        	break;
        case 'btnNticIssuePrint':
        	var rows = this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows();
        	if(rows.length==0) {
        		alert('연체 고지 할 항목을 선택 하세요.');
        		return;
        	}

        	var row=rows[0];

            this.printPayNotice('<c:url value="/oper/gnrl/printPrtFcltyRentFeePayNotice.do" />', row);
        	break;
        case 'btnNticIssuePrint2':
        	var rows = this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows();
        	if(rows.length==0) {
        		alert('연체 고지 할 항목을 선택 하세요.');
        		return;
        	}

        	var row=rows[0];

            this.printPayNotice('<c:url value="/oper/gnrl/printPrtFcltyRentFeePayNotice2.do" />', row);
        	break;
        case 'btnPrtFcltyRentFeePaySttusMngtListExcelDownload':	// 엑셀 다운로드
    		this.$('#prtFcltyRentFeePaySttusMngtList').flexExcelDown('<c:url value="/oper/gnrl/selectPrtFcltyRentFeePaySttusMngtListExcel.do"/>');
    		break;
    }
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.nticArrrgSingle = function() {
	var rows = this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows();
	if(rows.length==0) {
		alert('연체 고지 할 항목을 선택 하세요.');
		return;
	}

	var row=rows[0];

	var arrrgRate=this.$('#arrrgRate').val();
	var dlyBillAmnt=this.$('#arrrgAmt').number(true).val();
	var dlyBillDt=this.$('#dlyBillDt').val();
	var dlyDueDt=this.$('#dlyDueDt').val();
	var dlyBillRsn=this.$('#dlyBillRsn').val();

	var arrrgDetail = [
	               { name: 'prtAtCode', value: this.resultDetail.prtAtCode},
	               { name: 'mngYear', value: this.resultDetail.mngYear },
	               { name: 'mngNo', value: this.resultDetail.mngNo },
	               { name: 'mngCnt', value: this.resultDetail.mngCnt },
	               { name: 'nticCnt', value: this.resultDetail.nticCnt },
	               { name: 'chrgeKnd', value: this.resultDetail.chrgeKnd },
	               { name: 'arrrgTariff', value: this.fBasicRate },
	               { name: 'dlyBillDt', value: dlyBillDt },
	               { name: 'dlyDueDt', value: dlyDueDt },
	               { name: 'dlyBillAmnt', value: dlyBillAmnt },
	               { name: 'dlyBillRsn', value: dlyBillRsn },
	               { name: 'applyPayDates', value: this._iTermDay }
	             ];

//	if(!validateGamArrrgNtic(arrrgDetail)) {
	//	return;
//	}

	if(this.resultDetail.arrrgNo!=null) {	// 기 연체 고지된 건이 있는지
	 	this.doAction('<c:url value="/oper/gnrl/updateNticArrrg.do" />', arrrgDetail, function(module, result) {
			if (result.resultCode == "0") {
				if(confirm('연체 고지 되었습니다. 바로 납부 고지서를 출력 하시겠습니까?')) {
					module.printPayNotice('<c:url value="/oper/gnrl/printPrtFcltyRentFeePayNotice.do" />', row);
				}
			} else {
				alert(result.resultMsg);
			}
		});
	}
	else {	// 신규 연체를 등록 한다.
	 	this.doAction('<c:url value="/oper/gnrl/insertNticArrrg.do" />', arrrgDetail, function(module, result) {
			if (result.resultCode == "0") {
				if(confirm('연체 고지 되었습니다. 바로 납부 고지서를 출력 하시겠습니까?')) {
					module.printPayNotice('<c:url value="/oper/gnrl/printPrtFcltyRentFeePayNotice.do" />', row);
				}
			} else {
				alert(result.resultMsg);
			}
		});
	}

};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.nticArrrgCancelAll = function() {

	var arrrgDetail = [
	               { name: 'prtAtCode', value: this.resultDetail.prtAtCode},
	               { name: 'mngYear', value: this.resultDetail.mngYear },
	               { name: 'mngNo', value: this.resultDetail.mngNo },
	               { name: 'mngCnt', value: this.resultDetail.mngCnt },
	               { name: 'nticCnt', value: this.resultDetail.nticCnt },
	               { name: 'chrgeKnd', value: this.resultDetail.chrgeKnd },
	             ];

 	this.doAction('<c:url value="/oper/gnrl/cancelNticArrrg.do" />', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.nticArrrgCancelPk = function(ntic) {
	var row=this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows()[0];

	var arrrgDetail = [
	               { name: 'prtAtCode', value: ntic.prtAtCode},
	               { name: 'fiscalYr', value: ntic.fiscalYr },
	               { name: 'billNo', value: ntic.billNo },
	               { name: 'feeTp', value: ntic.feeTp },
	               { name: 'dlySerNo', value: ntic.dlySerNo },
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'chrgeKnd', value: row.chrgeKnd },
	               { name: 'nticCnt', value: row.nticCnt }
	             ];

 	this.doAction('<c:url value="/oper/gnrl/cancelNticArrrgPk.do" />', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.nticArrrgPrintCancelPk = function(ntic) {
	var arrrgDetail = [
	               { name: 'prtAtCode', value: ntic.prtAtCode},
	               { name: 'fiscalYr', value: ntic.fiscalYr },
	               { name: 'billNo', value: ntic.billNo },
	               { name: 'chargeKnd', value: ntic.chargeKnd },
	               { name: 'dlySerNo', value: ntic.dlySerNo }
	             ];

 	this.doAction('<c:url value="/oper/gnrl/cancelNticArrrgPrintPk.do" />', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    //this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');

    this.loadData();
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamPrtFcltyRentFeePaySttusMngtSearchForm');
    this.$("#prtFcltyRentFeePaySttusMngtListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#prtFcltyRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadDetailPage = function() {
	var row = this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows()[0];

	var nticDetail = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'nticCnt', value: row.nticCnt },
	               { name: 'chrgeKnd', value: row.chrgeKnd }
	             ];
	 this.doAction('<c:url value="/oper/gnrl/selectPrtFcltyRentFeePaySttusMngtDetail.do" />', nticDetail, function(module, result) {
		if (result.resultCode == "0") {
			module.makeDivValues('#masterPayInfo', result.resultMaster); // 결과값을 채운다.
			module.makeMultiDivValues('#detailPayInfo',result.resultList, function(row) {
				if(row.currNticCnt=="Y") $(this).addClass("detailRowSelected");
				else {
					if($(this).hasClass("detailRowSelected")) $(this).removeClass("detailRowSelected");
				}
			} );	// 리스트 값을 채운다
			module.makeDivValues('#summaryPayInfo', result.resultSummary); // 결과값을 채운다.
			module.resultDetail = result.resultMaster;	// 상세값을 저장한다.
			if(result.resultArrrg==undefined) {
				module.resultArrrg=null;
				module.$('#arrrgDetail').hide();
			}
			else {
				module.resultArrrg = result.resultArrrg;	// 연체 고지 출력을 위해 저장


				if(result.resultArrrg.arrrgTariff!=null) {
					result.resultArrrg.arrrgRate =result.resultArrrg.arrrgTariff/100;
				}
				else result.resultArrrg.arrrgRate =0.12;

				module.$('#arrrgDetail').show();
				module.makeDivValues('#arrrgDetail', result.resultArrrg); // 결과값을 채운다.
				module.makeFormValues('#arrrgDetailVO', result.resultArrrg); // 결과값을 폼에 채운다.
				// module.$('#dlyBillDt').val(result.resultArrrg.dlyBillDt);
				module.$('#dlyBillDt').val(result.resultArrrg.dlyBillDt);
				module.calcDlyDueDate();
				module.today = result.resultArrrg.dlyBillDt;	// 쿼리를 조회 할때 연체 고지 일자는 오늘 날짜를 가져온다.
				module.calculateArrrgFee();
			}

		} else {
			alert(result.resultMsg);
		}
	});

};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.calcDlyDueDate = function() {
	// 납부기한 계산
	var strDbDlyDueDt = this.resultArrrg.dbDlyDueDt;
	var dbDlyDueDt;

	var dlyBillDt = this.$('#dlyBillDt').val();
	var dlyDueDt = this.$('#dlyDueDt').val();
	var revDueDt = EMD.util.strToDate(this.resultDetail.payTmlmt);

	var billAmnt = this.resultArrrg.billAmnt;
	if(strDbDlyDueDt==null || strDbDlyDueDt=="") {
		dlyDueDt = new Date(dlyBillDt);
		dlyDueDt.setDate(dlyDueDt.getDate()+15);	// 납부기한을 15일로 지정 한다.

		if(billAmnt>=1000000) {	// 100만원 이상이고 납부 기한이 납부월을 초과하면 납부월이 초과 되지 않게 납부기한을 조정한다.
			var tempBillDt = new Date(dlyBillDt);
			tempBillDt.setDate(tempBillDt.getDate()+1);
			var tempDlyDueDt = this.getMonthInterval(revDueDt, tempBillDt);
			var tempCalcDlyDueDt = this.getMonthInterval(revDueDt, dlyDueDt);

			if(tempCalcDlyDueDt[0]!=tempDlyDueDt[0]) {	// 기 납부월을 초과하면
				dlyDueDt = new Date(revDueDt);
				dlyDueDt.setMonth(revDueDt.getMonth()+tempDlyDueDt[0]+1);	// 해당월까지를 납부기한으로 설정한다.
				dlyDueDt.setDate(revDueDt.getDate()-1);	// 전달 마지막 일
			}
		}
	}
	else {
		dbDlyDueDt = EMD.util.strToDate(strDbDlyDueDt);	// 이미 저장된 납부 기한이 있다.

		if(billAmnt>=1000000) {	// 100만원 이상이고 납부 기한이 납부월을 초과하면 납부월이 초과 되지 않게 납부기한을 체크 한다.
			var tempBillDt = new Date(dlyBillDt);
			tempBillDt.setDate(tempBillDt.getDate()+1);
			var tempDlyDueDt = this.getMonthInterval(revDueDt, tempBillDt);
			var calcDlyDueDt = new Date(dbDlyDueDt);
			var tempCalcDlyDueDt = this.getMonthInterval(revDueDt, calcDlyDueDt);

			if(tempCalcDlyDueDt[0]!=tempDlyDueDt[0]) {	// 기 납부월을 초과하면
				dbDlyDueDt = new Date(revDueDt);
				dbDlyDueDt.setMonth(revDueDt.getMonth()+tempDlyDueDt[0]+1);	// 해당월까지를 납부기한으로 설정한다.
				dbDlyDueDt.setDate(revDueDt.getDate()-1);	// 전달 마지막 일
				alert('저장된 납부기한이 지정된 납부개월을 초과 합니다. (요율에 따라 연체 금액이 변동 될 수 있습니다.)');
			}
		}
	}
	this.$('#dlyDueDt').val(EMD.util.getDate(dlyDueDt));
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.loadArrrgPage = function() {
	var row = this.$('#prtFcltyRentFeePaySttusMngtList').selectedRows()[0];
	var searchOpt = [];

	$.each(row, function(n, v){
		searchOpt[searchOpt.length]={name: n, value:v};
	});

    this.$('#prtFcltyRentFeePaySttusArrrgList').flexOptions({params:searchOpt}).flexReload();
	/*
	var dlyList = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'chrgeKnd', value: row.chrgeKnd },
	               { name: 'accnutYear', value: row.accnutYear },
	               { name: 'nticno', value: row.nticno }
	             ];


	this.doAction('<c:url value="/oper/gnrl/selectPrtFcltyRentFeePaySttusMngtDlyList.do" />', dlyList, function(module, result) {
		if (result.resultCode == "0") {
			module.makeMultiDivValues('#prtFcltyRentFeePaySttusMngtListForm',result.resultList , function(row) {
			} );	// 리스트 값을 채운다

			module.makeDivValues('#prtFcltyRentFeePaySttusMngtSum', result.resultSummary); // 결과값을 채운다.

		} else {
			alert(result.resultMsg);
		}
	});
	*/
}

GamPrtFcltyRentFeePaySttusMngtModule.prototype.getMonthInterval = function (time1,time2,gubun){
	/* 날짜 계산 */
	var retDate = new Date();

	retDate.setTime(time2.getTime()-time1.getTime())
	var yr = retDate.getFullYear()-1970;
	var mn = yr*12+retDate.getMonth();
	var days = retDate.getDate()-1;

	return [mn, days];
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.getDayInterval = function (fdate,tdate){
	var fd = Math.ceil(fdate.getTime()/(1000*60*60*24));
	var td = Math.ceil(tdate.getTime()/(1000*60*60*24));
  	return td-fd;
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.calculateArrrgFee = function() {
	 var fBasicRate = "" ;
	 var strText = "" ;
	// 연체료 계산
	// console.log('arrrg calc');
	var dlyBillDt = EMD.util.strToDate(this.$('#dlyBillDt').val());
	var payTmlmt = EMD.util.strToDate(this.resultDetail.payTmlmt);

	var iTerm = this.getMonthInterval(payTmlmt,dlyBillDt);
	var iTermMonth    = iTerm[0];
	var iTermDay = this.getDayInterval(payTmlmt,dlyBillDt);

 	if((iTermMonth > 60) || (iTermDay >= 1800)){
 		iTermMonth = 60;
 		iTermDay   = 1800;
 	}

 	// 연체기간 1개월 미만의 사용료 12%
 	if(iTermMonth < 1){
 		fBasicRate = 0.12;
 		strText    = " 12%";
 	}else if(iTermMonth < 3){
 		// 연체기간 1개월 - 3개월 미만의 사용료 13%
 		fBasicRate = 0.13;
 		strText    = " 13%";
 	}else if(iTermMonth < 6){
 		// 연체기간 3개월 - 6개월 미만의 사용료 14%
 		fBasicRate = 0.14;
 		strText    = " 14%";
 	}else{
 		// 연체기간 6개월 - 60개월 까지의 사용료 15%
 		fBasicRate = 0.15;
 		strText    = " 15%";
 	}
 //alert("계산61==>"+iTermMonth+"::"+iTermDay+"::"+fBasicRate+"::"+strText);
	 this.fBasicRate = fBasicRate;
 	// 산출내역 뿌려줌.
 	this.$('#dlyBillRsn').val("( " + $.number(this.resultArrrg.billAmnt) + " * " + fBasicRate + " * " + iTermDay + "/365 )");
 	iDlyAmnt = Math.floor(parseInt(this.resultArrrg.billAmnt * fBasicRate * iTermDay / 365)/10)*10;

 	this._iTermDay=iTermDay;

	this.$('#arrrgAmt').val($.number(iDlyAmnt));
	this.$('#arrrgRate').val(strText);
};

GamPrtFcltyRentFeePaySttusMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	//change**
	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#prtFcltyRentFeePaySttusMngtList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 납부 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
};


GamPrtFcltyRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
    switch(newTabId) {
    case 'tabs1':
        break;
    case 'tabs2':
		this.loadDetailPage();
        break;
	case 'tabs3':
		this.loadArrrgPage();
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
    case 'nticArrrgPopup':
    	break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');

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
                            <th style="width: 70px">항코드</th>
                            <td style="width: 170px"><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
                            <th style="width: 70px">고지업체</th>
                            <td>
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="12" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th style="width: 70px">납부구분</th>
                            <td><input id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025" data-default-prompt="전체" value="0" /></td>
                            <td rowspan="2"><button id="searchBtn" class="buttonSearch">조회</button></td>
                        </tr>

                        <tr>
                            <th>관리번호</th>
                            <td>
                            	<input id="sMngYear" type="text" class="mngYear">
                                <input id="sMngNo" type="text" class="mngNo">
                                <input id="sMngCnt" type="text" class="mngCnt">
                            </td>
                            <th>고지일자</th>
                            <td><input id="sNticDtFrom" data-column-id="nticPdFrom" type="text" class="emdcal"size="8"> ~ <input id="sNticDtTo" data-column-id="nticPdTo" type="text"class="emdcal" size="8"></td>
                            <th>요금종류</th>
                            <td><input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM024" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="prtFcltyRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">항만시설사용료납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">항만시설사용료납부현황 상세</a></li>
                <!-- change** -->
                <li><a href="#tabs3" class="emdTab">항만시설사용료연체현황 목록</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="prtFcltyRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
                <div id="prtFcltyRentFeePaySttusMngtListSum" class="emdControlPanel">
					<form id="summaryForm">
   	               	<table style="width:100%;" class="summaryPanel">
                		<tr>
                			<th width="6%" >자료수</th>
							<td><input type="text" size="6" id="sumCnt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="8%" >총고지금액</th>
							<td><input type="text" size="16" id="sumNhtIsueAmt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="6%" >부가세</th>
							<td><input type="text" size="16" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >총납부금액</th>
							<td><input type="text" size="16" id="sumPayAmt" class="ygpaNumber" disabled="disabled" /></td>
							<td><button id="btnUpdatePayDtls" data-icon="ui-icon-circle-check">납부확인</button></td>
							<td><button id="btnPrtFcltyRentFeePaySttusMngtListExcelDownload">엑셀</button></td>
							<!-- <td><button id="btnNticArrrg" data-icon="ui-icon-clock">연체일괄고지</button></td> -->
                		</tr>
                	</table>
					</form>
                </div>
            </div>

            <div id="tabs2" class="emdTabPage" style="overflow: scroll;">

                <div class="emdPanel">
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>고지내역</th>
						</tr>
					</tbody>
					</table>
                	<!-- <h2>고지 내역</h2> -->
                    <table id="masterPayInfo" class="detailPanel">
                        <tr>
                        	<th style="width: 100px"><span class="label">항구분</span></th>
                            <td style="width: 180px"><span class="ygpaCmmnCd" data-code-id="GAM019" data-column-id="prtAtCode"></span> (<span data-column-id="prtAtCode"></span>)</td>
                            <th style="width: 100px"><span class="label">회계년도</span></th>
                            <td style="width: 80px"><span data-column-id="accnutYear"></span></td>
                            <th style="width: 60px"><span class="label">고지횟수</span></th>
                            <td style="width: 80px"><span data-column-id="nticCnt"></span></td>
                            <th style="width: 100px"><span class="label">고지번호</span></th>
                            <td style="width: 160px"><span data-column-id="nticno"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">요금종류</span></th>
                            <td><span data-column-id="chrgeKndNm"></span> (<span data-column-id="chrgeKnd"></span>)</td>
                        	<th><span class="label">고지업체</span></th>
                            <td colspan="3"><span data-column-id="entrpsNm"></span> (<span data-column-id="entrpscd"></span>)</td>
                        	<th><span class="label">고지금액</span></th>
                            <td style="text-align:right;"><span data-column-id="nticAmt" class="ygpaNumber"></span> 원</td>
                        </tr>
                        <tr>
                            <th><span class="label">고지일자</span></th>
                            <td><span data-column-id="nticDt"></span></td>
                            <th><span class="label">납부기한일자</span></th>
                            <td colspan="5"><span data-column-id="payTmlmt"></span></td>
                        </tr>
                        <tr>
                        	<th><span class="label">수납구분</span></th>
                            <td><span data-column-id="rcivSe" class="ygpaCmmnCd" data-code-id="GAM025"></span></td>
                            <th><span class="label">수납일자</span></th>
                            <td colspan="5"><span data-column-id="rcivDt"></span></td>
                        </tr>
                    </table>
                    <div id="arrrgDetail">
					<table class="searchPanel">
					<tbody>
						<tr>
							<th width="50%">연체내역</th>
							<th style="text-align:right">
								<button id="btnNticArrrgSingle" data-icon="ui-icon-clock">연체고지</button>
								<button id="btnNticIssuePrint" data-icon="ui-icon-clock">고지서출력</button>
								<!--
								<button id="btnNticIssuePrint2" data-icon="ui-icon-clock">고지서출력(연체만)</button>
								 -->
							</th>
						</tr>
					</tbody>
					</table>
                	<!-- <h2>연체 내역</h2> -->
                    <form id="arrrgDetailVO">
                    <input type="hidden" id="prtAtCode" value=""/>
                      <table id="arrrgDetailInfo" class="detailPanel"  style="width:930px">
                        <tr>
                        	<th><span class="label">연체고지일자</span></th>
                            <td width="125px"><input id="dlyBillDt" data-column-id="dlyBillDt" class="emdcal" style="width:90px"/></td>
                        	<th><span class="label">연체고지납부기한</span></th>
                            <td width="125px"><input id="dlyDueDt" data-column-id="dlyDueDt" class="emdcal" style="width:90px"/></td>
                            <th><span class="label">연체요율</span></th>
                            <td><input id="arrrgRate" data-column-id="arrrgRate" style="width:80px"/></td>
                        </tr>
                        <tr>
                        	<th><span class="label">연체금액</span></th>
                            <td><input id="arrrgAmt" data-column-id="arrrgAmt" class="ygpaNumber" style="width:90px" data-decimal-point="0" /> 원</td>
                        	<th><span class="label">산출내역</span></th>
                            <td colspan="3"><input id="dlyBillRsn" style="width:100%"/></td>
                        </tr>
                    </table>
                    </form>
                    <!--
	                  <div class="emdControlPanel" style="vertical-align: middle;">
						<button id="btnNticArrrgSingle" data-icon="ui-icon-clock">연체고지</button>
					</div>
					-->
                    </div>

            	</div>
					<table class="searchPanel">
					<tbody>
						<tr>
							<th>전체사용료내역</th>
						</tr>
					</tbody>
					</table>
	               	<!-- <h2>전체 사용료 내역</h2> -->
               		<table class="detailPanel">
                    	<thead>
                    		<tr>
	                            <th style="text-align:center; width: 60px"><span class="label">회차</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">회계년도</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">고지번호</span></th>
	                        	<th style="text-align:center; width: 130px"><span class="label">요금종류</span></th>
	                        	<th style="text-align:center; width: 100px"><span class="label">고지금액</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">고지일자</span></th>
	                            <th style="text-align:center; width: 110px"><span class="label">납부기한일자</span></th>
	                        	<th style="text-align:center; width: 80px"><span class="label">수납구분</span></th>
	                            <th style="text-align:center; width: 80px"><span class="label">수납일자</span></th>
                            </tr>
                    	</thead>
                    	<tbody id="detailPayInfo" >
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
                    <table id="summaryPayInfo" class="summaryPanel">
                        <tr>
                        	<th><span class="label">총 고지 금액</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="totalNticAmount" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">총 납부 금액</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feePayAmount" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">관리비</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeA1" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">이자</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeA3" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">연체료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeD1" class="ygpaNumber"></span> 원</td>
                            <th><span class="label">과태료</span></th>
                            <td style="text-align:right; width: 92px"><span data-column-id="feeD2" class="ygpaNumber"></span> 원</td>
                        </tr>
                    </table>
			</div>
			<!-- change** -->
			<div id="tabs3" class="emdTabPage" style="overflow: scroll;">

                <!-- <div class="emdControlPanel"><button id="btnSaveItem">저장</button><button id="btnCancelItem">취소</button><button id="btnRemoveItem">삭제</button></div>  -->
                    <form id="prtFcltyRentFeePaySttusMngtListForm">
                    	<div>
                        <table class="detailForm"  style="width:100%;">
                            <tr>
                                <th width="16%">항코드</th>
                                <td><span data-column-id="prtAtCode" ></span></td>
                                <th width="16%">항코드명</th>
                                <td><span data-column-id="prtKorNm" ></span></td>
                                <th width="16%">회계년도</th>
                                <td><span data-column-id="fiscalYr" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">요금종류</th>
                                <td><span data-column-id="feeTp" ></span></td>
                                <th width="16%">요금종류명</th>
                                <td><span data-column-id="feeTpKorNm" ></span></td>
                                <th width="16%">고지번호</th>
                                <td><span data-column-id="billNo" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">업체코드</th>
                                <td><span data-column-id="agentCode" ></span></td>
                                <th width="16%">업체명</th>
                                <td><span data-column-id="firmKorNm" ></span></td>
                                <th width="16%">연체횟수</th>
                                <td><span data-column-id="dlySerNo" class="ygpaNumber" style="text-align:right;"></span></td>
                            </tr>
                            <tr>
                                <th width="16%">연체고지금액</th>
                                <td><span data-column-id="dlyBillAmnt" class="ygpaNumber" style="text-align:right;"></span></td>
                                <th width="16%">연체고지일자</th>
                                <td><span data-column-id="dlyBillDt" ></span></td>
                                <th width="16%">연체고지서발부여부</th>
                                <td><span data-column-id="dlyBillPrtYn" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">사업자등록번호</th>
                                <td><span data-column-id="bzRgstId" ></span></td>
                                <th width="16%">산출내역</th>
                                <td><span data-column-id="dlyBillRsn" ></span></td>
                                <th width="16%">연체납부기한</th>
                                <td><span data-column-id="dlyDueDt" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">최초고지일자</th>
                                <td><span data-column-id="firstBillDt" ></span></td>
                                <th width="16%">연체수납일자</th>
                                <td><span data-column-id="dlyRcvdDt" ></span></td>
                                <th width="16%">할인율코드</th>
                                <td><span data-column-id="dcRate" ></span></td>
                            </tr>
                            <tr>
                                <th width="16%">금융기관수납일자</th>
                                <td><span data-column-id="recptEpdt" ></span></td>
                                <th width="16%">시작일자</th>
                                <td><span data-column-id="strDate" ></span></td>
                                <th width="16%">종료일자</th>
                                <td><span data-column-id="endDate" ></span></td>
                            </tr>
                        </table>
						</div>
                    </form>
                    <h2>연체 고지 목록</h2>
                    <table id="prtFcltyRentFeePaySttusArrrgList" style="display:none" class="fillHeight"></table>
                    <table style="width:100%; margin-bottom: 2px" id="prtFcltyRentFeePaySttusMngtSum" class="summaryPanel">
						<tr>
							<th width="30%" height="23">자료수</th>
							<td><span id="dpTotCnt" class="ygpaNumber" style="text-align:right;"></span></td>
							<th width="30%" height="23">연체고지금액합계</th>
							<td><span id="sumDlyBillAmnt" class="ygpaNumber" style="text-align:right;"></span></td>
						</tr>
					</table>
					<table class="editControlPanel" style="width:100%;">
						<tbody>
							<tr>
								<td style="text-align:right">
									<button data-cmd="btnNticArrrgCancelPk" data-icon="ui-icon-clock">고지취소</button>
									<button data-cmd="btnNticIssuePrint" data-icon="ui-icon-print">고지서출력</button>
									<!--
									<button data-cmd="btnNticIssuePrint2" data-icon="ui-icon-print">고지서출력(연체만)</button>
									<button data-cmd="btnNticIssuePrintCancelPk" data-icon="ui-icon-cancel">출력취소</button>
									 -->
								</td>
							</tr>
						</tbody>
					</table>
            </div>
        </div>
    </div>
</div>