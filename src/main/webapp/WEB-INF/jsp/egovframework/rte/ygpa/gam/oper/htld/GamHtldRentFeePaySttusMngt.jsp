<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : GamHtldRentFeePaySttusMngt.jsp
 * @Description : 배후단지납부현황관리
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014-02-05  domh     최초 생성
 *
 * author domh
 * since 2014-02-05
 * 2015-12-07 김종민  수정... 
 * Copyright (C) 2013 by LFIT  All right reserved.
 */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamHtldRentFeePaySttusMngtModule() {}

GamHtldRentFeePaySttusMngtModule.prototype = new EmdModule(1000, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamHtldRentFeePaySttusMngtModule.prototype.loadComplete = function(params) {
	// 납부현황목록
    this.$("#htldRentFeePaySttusMngtList").flexigrid({
        module: this,
        url: '/oper/htld/gamSelectHtldRentFeePaySttusMngtList.do',
        dataType: 'json',
        colModel : [
    				{display:'고지횟수', name:'nticCnt',width:50, sortable:false,align:'center'},
    				{display:'고지업체명', name:'entrpsNm',width:130, sortable:false,align:'left'},
    				{display:'요금종류명', name:'chrgeKndNm',width:150, sortable:false,align:'left'},
    				{display:'고지대상기간', name:'nticPdPeriod',width:160, sortable:false,align:'center'},
    				{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
    				{display:'사용료', name:'fee',width:78, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'이자', name:'intrAmnt',width:78, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'부가세', name:'vat',width:78, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'고지금액', name:'nticAmt',width:80, sortable:false,align:'right', displayFormat: 'number'},
					{display:'납부기한', name:'payTmlmt',width:80, sortable:false,align:'center'},
					{display:'수납구분', name:'rcivSeNm',width:80, sortable:false,align:'center'},
					{display:'수납일자', name:'rcivDt',width:100, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        preProcess: function(module,data) {
        	module.makeFormValues('#summaryForm', data);
        	module.makeDivValues('#htldRentFeePaySttusMngtListSum', data);
        	$.each(data.resultList, function() {
        		this.nticPdPeriod = this.nticPdFrom+" ~ "+this.nticPdTo;
        	});
            return data;
        }
    });

	this.$("#htldRentFeePaySttusMngtList").on('onItemDoubleClick', function(event, module, row, grid, param) {
        module.$("#htldRentFeePaySttusMngtListTab").tabs("option", {active: 1});    // 탭을 전환 한다.
    });

    /* 연체내역 목록 */
    this.$("#htldRentFeePaySttusArrrgList").flexigrid({
        module: this,
        url: '/oper/htld/selectHtldRentFeePaySttusMngtDlyList.do',
        dataType: 'json',
        colModel : [
					{display:'연체횟수', name:'dlySerNo',width:67, sortable:false,align:'center'},
					{display:'연체고지일자', name:'dlyBillDt',width:110, sortable:false,align:'center'},
                    //{display:'공급가액', name:'supplyAmnt',width:104, sortable:false,align:'right', displayFormat: 'number'},
                    //{display:'부가세', name:'vat',width:104, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'연체료', name:'dlyBillAmnt',width:104, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'고지금액', name:'arrrgNticAmnt',width:104, sortable:false,align:'right', displayFormat: 'number'},
					{display:'고지서발부여부', name:'dlyBillPrtYn',width:100, sortable:false,align:'center'},
					{display:'연체납부기한', name:'dlyDueDt',width:110, sortable:false,align:'center'},
					{display:'산출내역', name:'dlyBillRsn',width:300, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: '150',
        preProcess: function(module,data) {
    		if (data.resultCode == "0") {
            	$.each(data.resultList, function() {
            		this.supplyAmnt = Number(this.fee) + Number(this.intrAmnt);
            		this.arrrgNticAmnt = Number(this.supplyAmnt) + Number(this.vat) + Number(this.dlyBillAmnt);
            		module.dlyListLastRec = this;
            	});
    			module.makeDivValues('#htldRentFeePaySttusMngtListForm',data.resultDlyInfo);	// 리스트 값을 채운다
    			var resultSummary = { 'sumDlyBillAmnt' : module.dlyListLastRec.dlyBillAmnt,  'dpTotCnt' : data.totCnt };
    			module.makeDivValues('#htldRentFeePaySttusMngtSum', resultSummary); // 결과값을 채운다.
    		} else {
    			alert(data.resultMsg);
    		}
            return data;
        }
    });
	
    if(params!=null) {
    	if(params.action=="selectRentFeePay") {
    		//임대료관리화면에서 데이터를 선택후 호출하여 조회
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);
        	this.$('#rcivSe').val("");
        	this.loadData();
    	}
    } else {
    	//특정데이터를 지정한 것이 아닌 경우 기간별로 조회.
        this.$('#sNticDtFrom').val(EMD.util.getDate());
        this.$('#sNticDtTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.
    	this.loadData();
    }
    
    this.$("#arrrgAmt").bind("keyup change", {module: this}, function(event) {
    	var arrrgAmt, supplyPrice;
    	supplyPrice = Number(event.data.module.$('#supplyPrice').val());
    	if(event.data.module.$('#arrrgAmt').val() ==''){
			arrrgAmt = 0;
		} else {
			arrrgAmt = Number(event.data.module.$('#arrrgAmt').val());
		}
    	event.data.module.$('#arrrgNticAmt').val(supplyPrice + arrrgAmt);
    });
    
};

// onSubmit
GamHtldRentFeePaySttusMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

// 배후단지임대료납부현황 목록
GamHtldRentFeePaySttusMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamHtldRentFeePaySttusMngtSearchForm');
    this.$("#htldRentFeePaySttusMngtListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#htldRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
};

// 배후단지임대료납후현황 상세
GamHtldRentFeePaySttusMngtModule.prototype.loadDetailPage = function() {
	var row = this.$('#htldRentFeePaySttusMngtList').selectedRows()[0];
	var nticDetail = [
	               { name: 'prtAtCode', value: row.prtAtCode},
	               { name: 'mngYear', value: row.mngYear },
	               { name: 'mngNo', value: row.mngNo },
	               { name: 'mngCnt', value: row.mngCnt },
	               { name: 'nticCnt', value: row.nticCnt },
	               { name: 'chrgeKnd', value: row.chrgeKnd }
	];
	this.doAction('/oper/htld/selectHtldRentFeePaySttusMngtDetail.do', nticDetail, function(module, result) {
		if (result.resultCode == "0") {
			//원고지정보처리
			module.makeDivValues('#detailMaster', result.detailMaster);
			module.detailMaster = result.detailMaster;
			if(module.detailMaster != null) {
				//전체사용료목록처리
				module.makeMultiDivValues('#detailFeePayList',result.detailFeePayList, function(row) {
					if(row.currNticCnt=="Y") { 
						$(this).addClass("detailRowSelected");
					} else {
						if($(this).hasClass("detailRowSelected")) $(this).removeClass("detailRowSelected");
					}
				} );
				//총고지금액, 총납부금액, 관리비, 연체료, 과태료 정보 처리
				module.makeDivValues('#summaryPayInfo', result.detailSummary); 
				//수납상태가 아니라면 연체정보 처리
				if(result.detailArrrg==void(0)) {
					module.detailArrrg=null;
					module.$('#arrrgDetail').hide();
				} else {
					module.detailArrrg = result.detailArrrg;
					module.$('#arrrgDetail').show();
					var fee = Number(result.detailArrrg.fee);
					var intrAmnt = Number(result.detailArrrg.intrAmnt);
					var vat = Number(result.detailArrrg.vat)
					module.displayArrrgForm(fee, intrAmnt, vat);
				}
			} else {
				alert('선택한 자료가 존재하지 않습니다.');
				module.loadData();
			}
		} else {
			module.$('#arrrgDetail').hide();
			alert(result.resultMsg);
		}
		module.setButtonState();
	});
};

//연체정보 출력 2015.12.13 김종민 추가
GamHtldRentFeePaySttusMngtModule.prototype.displayArrrgForm = function(fee, intrAmnt, vat) {
	if(this.detailMaster == void(0)) return;
	if(this.detailArrrg == void(0)) return;
	
	var nextArrrgNo = Number(this.detailArrrg.nextArrrgNo);
	var dlyBillRsn = '';
	var arrrgAmnt = 0;
	var arrrgRate = 0;
	var supplyPrice = fee+intrAmnt;
	
	for(var i=1; i<=nextArrrgNo; i++) {
		arrrgRate = (i==1) ? 0.03 : 0.012;
		arrrgAmnt += Math.floor((supplyPrice * arrrgRate)*0.1) * 10;
	}

	if(nextArrrgNo == 1) {
		dlyBillRsn = supplyPrice + ' * 0.03';
	} else {
		dlyBillRsn = '(' + supplyPrice + ' * 0.03)+' + '(' + supplyPrice + ' * 0.012)';
		if(nextArrrgNo > 2) dlyBillRsn += '*' + (nextArrrgNo - 1);  
	}

	this.detailArrrg.supplyPrice = supplyPrice;
	this.detailArrrg.arrrgAmt = arrrgAmnt; //연체료
	this.detailArrrg.arrrgNo = nextArrrgNo; 
	this.detailArrrg.arrrgRate = arrrgRate * 100;
	this.detailArrrg.arrrgNticAmt = fee + intrAmnt + vat + arrrgAmnt; //연체고지금액
	this.detailArrrg.dlyBillRsn = dlyBillRsn;
	this.makeFormValues('#arrrgDetailVO', this.detailArrrg);
};

// 배후단지임대료연체현황 목록
GamHtldRentFeePaySttusMngtModule.prototype.loadArrrgPage = function() {
	this.makeDivValues('#htldRentFeePaySttusMngtListForm',{});	
	var row = this.$('#htldRentFeePaySttusMngtList').selectedRows()[0];
	var searchOpt = [];
	$.each(row, function(n, v){
		searchOpt[searchOpt.length]={name: n, value:v};
	});
    this.$('#htldRentFeePaySttusArrrgList').flexOptions({params:searchOpt}).flexReload();
}

/**
 * 정의 된 버튼 클릭 시 click 이벤트 처리기
 */
 GamHtldRentFeePaySttusMngtModule.prototype.onButtonClick = function(buttonId) {
     var opts=null;
    switch(buttonId) {
        case 'searchBtn':  // 조회
        	this.loadData();
            break;
        case 'popupEntrpsInfo':  // 업체 팝업(조회항목)
            var searchOpt=this.makeFormArgs('#gamHtldRentFeePaySttusMngtSearchForm');
            this.doExecuteDialog('selectEntrpsInfoFeePayPopup', '업체 선택', '/popup/showEntrpsInfo.do', opts, searchOpt);
            break;
        case 'btnRecivePay':  //수납
        	this.receiveFeeSingle();
        	break;
        case 'btnUpdatePayDtls':	// 납부 현황 새로 고침
            this.doAction('/oper/htld/updateHtldRentFeePaySttusMngtList.do', null, function(module, result) {
                if(result.resultCode=='0') {
                    var searchOpt=module.makeFormArgs('#gamHtldRentFeePaySttusMngtSearchForm');
                    module.$('#htldRentFeePaySttusMngtList').flexOptions({params:searchOpt}).flexReload();
                }
                alert(result.resultMsg);
            });
        	break;
        //case 'btnNticArrrgSingle1':
        case 'btnNticArrrgSingle2': //연체고지
			this.nticArrrgSingle();
        	break;
        case 'btnNticArrrgCancelPk': //연체고지취소
        	var rows = this.$('#htldRentFeePaySttusArrrgList').selectedRows();
			if(rows.length==0) {
				alert('취소할 대상을 선택 하십시요.');
				return;
			}
        	this.nticArrrgCancelPk(rows[0]);
        	break;
        case 'btnNticIssuePrintCancelLast':
        	var rows = this.$('#htldRentFeePaySttusArrrgList').selectedRows();
			if(rows.length==0) {
				alert('취소할 대상을 선택 하십시요.');
				return;
			}
        	this.nticArrrgPrintCancelLast(rows[0]);
        	break;
        case 'btnNticIssuePrint':
        	var rows = this.$('#htldRentFeePaySttusMngtList').selectedRows();
        	if(rows.length==0) {
        		alert('연체 고지 할 항목을 선택 하세요.');
        		return;
        	}
        	var row=rows[0];
            this.printPayNotice('/oper/htld/printHtldRentFeePayNotice.do', row);
        	break;
        case 'btnNticIssuePrint2':
        	var rows = this.$('#htldRentFeePaySttusMngtList').selectedRows();
        	if(rows.length==0) {
        		alert('연체 고지 할 항목을 선택 하세요.');
        		return;
        	}
        	var row=rows[0];
            this.printPayNotice('/oper/gnrl/printPrtFcltyRentFeePayNotice.do', row);
        	break;
    }
};

//수납 버튼 이벤트 처리
GamHtldRentFeePaySttusMngtModule.prototype.receiveFeeSingle = function() {
	var rows = this.$('#htldRentFeePaySttusMngtList').selectedRows();
	if(rows.length==0) {
		alert('목록에서 수납 처리 할 건을 선택하십시오.');
		return;
	}
	var row = rows[0];
	this.doAction('/oper/htld/htldCheckOcrResult.do', row, function(module, result) {
		if (result.resultCode == "0") {
			if(result.result['ocrDt']!=void(0)) {
				alert('지로 수납된 자료는 변경 할 수 없습니다.');
				return;
			}
	    	var opts = {
	                'prtAtCode': result.result['prtAtCode'],
	                'mngYear': result.result['mngYear'],
	                'mngNo': result.result['mngNo'],
	                'mngCnt': result.result['mngCnt'],
	                'nticCnt' : result.result['nticCnt'],
		            'chrgeKnd': result.result['chrgeKnd']
	        };
	    	module.doExecuteDialog('feePayPopup', '수납 처리', '/oper/htld/htldShowFeePayPopup.do', opts);
		} else {
			alert(result.resultMsg);
		}
	});
};

// 연체고지 등록
GamHtldRentFeePaySttusMngtModule.prototype.nticArrrgSingle = function() {
	if(!this.$('#htldRentFeePaySttusMngtList').selectedRowCount()>0) {
		alert('목록에서 고지할 건을 선택하세요.');
		return;
	}
	var row = this.$('#htldRentFeePaySttusMngtList').selectedRows()[0];
	if(row['rcivSe'] == '3') {
		alert('이미 수납 된 건입니다.');
		return;
	}
	if(!confirm('연체고지를 하시겠습니까?')) return;
	var args = {
		'prtAtCode': row['prtAtCode'],
		'mngYear': row['mngYear'],
		'mngNo': row['mngNo'],
		'mngCnt': row['mngCnt'],
		'nticCnt' : row['nticCnt'],
		'nticAmt' : row['nticAmt'],
		'newPayTmlmt' : this.$('#dlyDueDt').val(), //납부연체기한
		'arrrgTariff' : this.$('#arrrgRate').val(),  //연체요율
		'arrrgAmt' : this.$('#arrrgAmt').val(),    //연체료
		'arrrgNticAmt' : this.$('#arrrgNticAmt').val(),    //연체고지금액
		'dlyBillRsn' : this.$('#dlyBillRsn').val(),    //산출내역	
		'dlyBillDt' : this.$('#dlyBillDt').val()   //연체고지일자.
	};
	
	var params = EMD.util.objectToArray(args);
	this.doAction('/oper/htld/insertHtldArrrgNticSingle.do', params, function(module, result) {
		if(result.resultCode=='0') {
			module.loadDetailPage(); //디테일페이지 로드
		}
		alert(result.resultMsg);
	});	
};

GamHtldRentFeePaySttusMngtModule.prototype.nticArrrgCancelAll = function() {

	var arrrgDetail = [
	               { name: 'prtAtCode', value: this.detailMaster.prtAtCode},
	               { name: 'mngYear', value: this.detailMaster.mngYear },
	               { name: 'mngNo', value: this.detailMaster.mngNo },
	               { name: 'mngCnt', value: this.detailMaster.mngCnt },
	               { name: 'nticCnt', value: this.detailMaster.nticCnt },
	               { name: 'chrgeKnd', value: this.detailMaster.chrgeKnd },
	             ];

 	this.doAction('/oper/gnrl/cancelNticArrrg.do', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamHtldRentFeePaySttusMngtModule.prototype.nticArrrgCancelPk = function(ntic) {
	if(ntic.dlySerNo != this.dlyListLastRec.dlySerNo) {
		alert('마지막 연체정보만 취소할 수 있습니다.');
		return;
	}
	
	if(!confirm('이 건의 대해 고지를 취소 하시겠습니까?')) return;
	var row=this.$('#htldRentFeePaySttusMngtList').selectedRows()[0];

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
	
	//연체고지취소는 이제 일반부두의 연체고지취소와 요금되돌리는 방식이 틀리기에 일반부두의 연체고지취소는 안 쓴다. 2015.12.14 김종민
 	//this.doAction('/oper/gnrl/cancelNticArrrgPk.do', arrrgDetail, function(module, result) {
 	this.doAction('/oper/htld/cancelNticArrrgPk.do', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadArrrgPage();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamHtldRentFeePaySttusMngtModule.prototype.nticArrrgPrintCancelPk = function(ntic) {
	var arrrgDetail = [
	               { name: 'prtAtCode', value: ntic.prtAtCode},
	               { name: 'fiscalYr', value: ntic.fiscalYr },
	               { name: 'billNo', value: ntic.billNo },
	               { name: 'chargeKnd', value: ntic.chargeKnd },
	               { name: 'dlySerNo', value: ntic.dlySerNo }
	             ];

 	this.doAction('/oper/gnrl/cancelNticArrrgPrintPk.do', arrrgDetail, function(module, result) {
		if (result.resultCode == "0") {
			alert('고지 취소 되었습니다.');
			module.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

GamHtldRentFeePaySttusMngtModule.prototype.setButtonState = function() {
	var tabId=this.$("#htldRentFeePaySttusMngtListTab").tabs({"option": "active"});
	if(tabId=="tabs1") {

	}
	else if(tabId=="tabs2") {
		if(this.detailArrrg==void(0)) {
			this.$('#arrrgDetail').hide();
		}
		else {
			this.$('#arrrgDetail').show();
		}
	}
};

// Tab이 변경되기 전에 처리되는 이벤트
GamHtldRentFeePaySttusMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	if(newTabId=='tabs2' || newTabId=='tabs3') {
		if(this.$('#htldRentFeePaySttusMngtList').selectedRowCount()!=1) {
			alert('상세 내역을 조회 할 납부 항목을 선택 하세요.');
			return false;
		}
	}
	return true;
};

// Tab 선택시 이벤트
GamHtldRentFeePaySttusMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
GamHtldRentFeePaySttusMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
    switch (popupId) {
    case 'selectEntrpsInfoFeePayPopup':
        if (msg != 'cancel') {
            this.$('#sEntrpscd').val(value.entrpscd);
            this.$('#sEntrpsNm').val(value.entrpsNm);
        } else {
            alert('취소 되었습니다');
        }
        break;
    case 'feePayPopup': //수납처리 Dialog 처리부분
    	if (msg != 'cancel') {
           	var arg = EMD.util.objectToArray(value);
           	//수납처리
            this.doAction('/oper/gnrl/htldUpdateRevCollRcvdTp.do', arg, function(module, result) {
                if(result.resultCode=='0') {
                	module.loadData();
                }
                alert(result.resultMsg);
            });
        }
    	break;
     default:
         alert('알수없는 팝업 이벤트가 호출 되었습니다.');
         break;
     }
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamHtldRentFeePaySttusMngtModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">

    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentFeePaySttusMngtSearchForm">
            	<input type="hidden" id="sPrtAtCode" value="622"/>
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th style="width: 70px">납부구분</th>
                            <td>
                            	<select id="rcivSe">
                            		<option value="">전체</option>
                            		<option value="N" selected>미납</option>
                            		<option value="Y">수납</option>
                            	</select>
                           	</td>
                            <th style="width: 70px">고지업체</th>
                            <td>
                                <input type="text" size="6" id="sEntrpscd" maxlength="10"/>
                                <input type="text" size="12" id="sEntrpsNm" disabled/>
                                <button id="popupEntrpsInfo" class="popupButton">선택</button>
                            </td>
                            <th>요금종류</th>
                            <td><input id="sChrgeKnd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM053" /></td>
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
                            <td colspan="3"><input id="sNticDtFrom" data-column-id="nticPdFrom" type="text" class="emdcal"size="8"> ~ <input id="sNticDtTo" data-column-id="nticPdTo" type="text"class="emdcal" size="8"></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <div class="emdPanel fillHeight">
        <div id="htldRentFeePaySttusMngtListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange" data-onchange-before="onTabChangeBefore">
            <ul>
                <li><a href="#tabs1" class="emdTab">배후단지임대료납부현황 목록</a></li>
                <li><a href="#tabs2" class="emdTab">배후단지임대료납부현황 상세</a></li>
                <li><a href="#tabs3" class="emdTab">배후단지임대료연체현황 목록</a></li>
            </ul>

            <div id="tabs1" class="emdTabPage fillHeight" style="overflow: hidden;" data-onactivate="onShowTab1Activate">
                <table id="htldRentFeePaySttusMngtList" style="display:none" class="fillHeight"></table>
                <div id="htldRentFeePaySttusMngtListSum" class="emdControlPanel">
					<form id="summaryForm">
   	               	<table style="width:100%;" class="summaryPanel">
                		<tr>
                			<th width="10%" >자료수</th>
							<td><input type="text" size="6" id="sumCnt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >총고지금액</th>
							<td><input type="text" size="16" id="sumNhtIsueAmt" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >부가세</th>
							<td><input type="text" size="16" id="sumVat" class="ygpaNumber" disabled="disabled" /></td>
                			<th width="10%" >총납부금액</th>
							<td><input type="text" size="16" id="sumPayAmt" class="ygpaNumber" disabled="disabled" /></td>
							<td>
							<button id="btnRecivePay" data-icon="ui-icon-circle-check">수납</button>
							<button data-role="gridXlsDown" data-flexi-grid="htldRentFeePaySttusMngtList" data-xls-name="배후단지납부현황목록.xls" data-xls-title="배후단지 납부현황 목록">엑셀</button>
							</td>
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
                    <table id="detailMaster" class="detailPanel">
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
                        <tr>
                        	<th><span class="label">비고</span></th>
                            <td colspan="7"><span data-column-id="rm"></span></td>
                        </tr>
                    </table>
                	<div id="arrrgDetail">
					<table class="searchPanel">
					<tbody>
						<tr>
							<th width="50%">연체내역</th>
							<th style="text-align:right">
								<button id="btnNticArrrgSingle2" data-icon="ui-icon-clock">연체고지</button>
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
                            <th><span class="label">연체횟수</span></th>
                            <td><input id="arrrgNo" data-column-id="arrrgNo" style="width:70px" disabled="disabled"/></td>
                        </tr>
                        <tr>
                        	<th><span class="label">공급가액</span></th>
                            <td><input id="supplyPrice" data-column-id="supplyPrice" class="ygpaNumber" style="width:90px" data-decimal-point="0" disabled="disabled" /> 원</td>
                        	<th><span class="label">부가세</span></th>
                            <td><input id="vat" data-column-id="vat" class="ygpaNumber" style="width:90px" data-decimal-point="0" disabled="disabled"/> 원</td>
                        	<th><span class="label">연체료</span></th>
                            <td><input id="arrrgAmt" data-column-id="arrrgAmt" class="ygpaNumber" style="width:90px" data-decimal-point="0" /> 원</td>
                        </tr>
                        <tr>
                        	<th><span class="label">산출내역</span></th>
                            <td colspan="3"><input id="dlyBillRsn" style="width:100%"/>
                            	<input type="hidden" id="arrrgRate" data-column-id="arrrgRate" />
                            </td>
                        	<th><span class="label">고지(예정)금액</span></th>
                            <td><input id="arrrgNticAmt" data-column-id="arrrgNticAmt" class="ygpaNumber" style="width:90px" data-decimal-point="0" /> 원</td>
                        </tr>
                        <tr>
                            <th colspan="6"  style="text-align:center"><span class="label" style="text-align:center">국세징수법에 따라 연체료 3%, 중가산금 1.2%/매월 적용</span></th>
                        </tr>
                    </table>
                    </form>
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
                    	<tbody id="detailFeePayList" >
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
                    <form id="htldRentFeePaySttusMngtListForm">
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
                            <tr>
                                <th width="16%">공급가액</th>
                                <td><span data-column-id="supplyAmnt" class="ygpaNumber" style="text-align:right;"></span></td>
                                <th width="16%">부가세</th>
                                <td colspan="3"><span data-column-id="vat" class="ygpaNumber" style="text-align:right;"></span></td>
                            </tr>
                        </table>
						</div>
                    </form>
					<h2>연체 고지 목록</h2>
					<div class="emdControlPanel">
                    <table id="htldRentFeePaySttusArrrgList" style="display:none" class="fillHeight"></table>
                    <table style="width:100%; margin-bottom: 2px" id="htldRentFeePaySttusMngtSum" class="summaryPanel">
						<tr>
							<th width="30%" height="23">자료수</th>
							<td><span id="dpTotCnt" class="ygpaNumber" style="text-align:right;"></span></td>
							<th width="30%" height="23">연체료합계</th>
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
</div>