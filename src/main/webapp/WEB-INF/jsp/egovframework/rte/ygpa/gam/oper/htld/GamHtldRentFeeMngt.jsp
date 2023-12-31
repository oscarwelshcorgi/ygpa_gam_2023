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
  *  2015.12.14 김종민   		전면 수정
  *
  * author domh
  * since 2014.01.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
<%--
	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentFeeMngtModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentFeeMngtModule.prototype = new EmdModule(1440, 600);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentFeeMngtModule.prototype.loadComplete = function(params) {
    // 사용료고지목록 그리드 설정
    this.$("#assetRentFeeList").flexigrid({
        module: this,
        url: '/oper/htld/gamSelectHtldRentFeeMngtList.do',
        dataType: 'json',
        colModel : [
				/*{display:'상태', name:'state',width:30, sortable:false,align:'center'},*/
                {display:'구역', name:'rentArea',width:82, sortable:false,align:'center'},
                /*{display:'구분', name:'assessSeNm',width:50, sortable:false,align:'center'},*/                
				{display:'고지횟수', name:'nticCnt',width:50, sortable:false,align:'center'},
				{display:'입주기업', name:'entrpsNm',width:140, sortable:false,align:'left'},
				{display:'고지대상기간', name:'nticPdDate',width:160, sortable:false,align:'center'},
				{display:'고지방법', name:'nticMthNm',width:70, sortable:false,align:'center'},
				{display:'고지', name:'nhtIsueYnNm',width:40, sortable:false,align:'center'},
				{display:'출력', name:'nhtPrintYnNm',width:40, sortable:false,align:'center'},
				{display:'사용료', name:'fee',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'이자', name:'intrAmnt',width:80, sortable:false,align:'right', displayFormat: 'number'},
				{display:'이자율(%)', name:'intrRate',width:60, sortable:false,align:'right', displayFormat: 'input-number', displayOption: "0.00"},
				{display:'추가금액', name:'addAmnt',width:80, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'추가금액산출근거', name:'addAmntRm',width:150, sortable:false,align:'left', displayFormat: 'input'},
				{display:'소계', name:'feeAmnt',width:90, sortable:false,align:'right', displayFormat: 'number'},
				{display:'부가세', name:'vat',width:90, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'고지금액', name:'nticAmt',width:90, sortable:false,align:'right', displayFormat: 'input-number'},
				{display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
				{display:'비고', name:'rm',width:140, sortable:false,align:'left'}
                ],
        showTableToggleBtn: false,
        height: 'auto',
        rowHeight: 50,
        groupBy: "rentArea",
        preProcess: function(module,data) {
        	var sum = { sumCnt:0, sumFee:0, sumVat:0, sumNticAmt:0, sumIntrAmt:0 };
        	module._assetsDetailList = data.assetsDetailList;  //해당 고지의 임대상세내역 목록을 가져온다.
        	module._nticDetailList = [];
        	$.each(data.resultList, function() {
        		module.makeRowData(this);
        		sum.sumCnt += 1; sum.sumFee += this.fee; sum.sumVat += this.vat; sum.sumIntrAmt += this.intrAmnt; sum.sumNticAmt += this.nticAmt;
        	});
        	module.makeDivValues('#summaryTable', sum);
            return data;
        }
    });
		
	// params로 넘어온 데이터가 있을 경우 그 데이터를 목록의 조회조건으로 넣어 목록을 표시 
    if(params!=null) {
    	if(params.action=="selectRentFee") {
        	this.$('#sPrtAtCode').val(params.nticVo.prtAtCode);
        	this.$('#sMngYear').val(params.nticVo.mngYear);
        	this.$('#sMngNo').val(params.nticVo.mngNo);
        	this.$('#sMngCnt').val(params.nticVo.mngCnt);
    	    this.$('#nticPdFrom').val(EMD.util.getDate(EMD.util.addMonths(-1)));
    	    this.$('#nticPdTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.
        	this.loadData();
    	}
    } else {
	    this.$('#nticPdFrom').val(EMD.util.getDate(EMD.util.addMonths(-1)));
	    this.$('#nticPdTo').val(EMD.util.getDate(EMD.util.addMonths(1)));	// 현재 일자부터 1개월 이후 까지 조회 기본 값으로 입력 한다.
    	this.loadData();
	}

  	//	각 요소에 대한 이벤트 설정
    this.setEvents();
	
 	// 버튼 상태 설정
	this.setButtonStatus();
 	
 	console.log('1');
};

<%--
	EmdModule에서 Overriding 된 Submit 함수.
	모듈에서 엔터키를 입력 하거나 submitButton 클래스의 버튼이 눌려졌을때 호출되는 이벤트 함수. (포커스에 따라 동작 안될 때도 있음.)
--%>
GamHtldRentFeeMngtModule.prototype.onSubmit = function() {
    this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentFeeMngtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'searchBtn': //조회
		this.loadData();
		break;
	case 'popupEntrpsInfoFee': //업체 팝업호출(조회조건)
		this.doExecuteDialog('selectEntrpsInfoFeePopup', '업체 선택', '/popup/showEntrpsInfo.do', {});
		break;
	case 'btnExecNticIssue':	// 고지 의뢰
       	this.openNticIssuePopup();
       	break;
	case 'btnCancelNticIssue':	// 고지 취소
 		this.cancelNticIssue();
 		break;
	case 'btnNticIssuePrint':	// 고지서 출력
		this.nticIssuePrint();
		break;
   	case 'btnHwpDownload':	// HWP
   		this.printPage('/oper/htld/gamHtldRentHwpPreview.do', {});
   		break;
   	case 'btnRentFeePayMngt':	// 납부현황관리
   		this.loadRentFeePayMngt();
       	break;
   }
};

<%--
	팝업이 종료 될때 리턴 값이 오출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentFeeMngtModule.prototype.onClosePopup = function(popupId, msg, value) {
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
	case 'nticIssuePopup': 
		if (msg != 'cancel') {
			this.processNticIssue(value); //고지처리 호출
		} 
		break;
	default:
		alert('알수없는 팝업 이벤트가 호출 되었습니다.');
		break;
	}
};

<%--
	탭이 변경 되기 전에 호출되는 이벤트 핸들러 : 리턴값이 false이면 탭 변경이 취소되어 탭이 바뀌질 않는다.
--%>
GamHtldRentFeeMngtModule.prototype.onTabChangeBefore = function(newTabId, oldTabId) {
	return true;
};

<%--
	탭이 변경 된 후 호출 되는 이벤트 핸들러
--%>
GamHtldRentFeeMngtModule.prototype.onTabChange = function(newTabId, oldTabId) {
};

<%--
	각 요소에 대한 이벤트 설정
--%>
GamHtldRentFeeMngtModule.prototype.setEvents = function() {
    //조회조건의 업체코드가 바뀔 때
	this.$("#sEntrpscd").bind("keyup change", {module: this}, function(event) {
		if(event.data.module.$('#sEntrpscd').val() ==''){
			event.data.module.$('#sEntrpsNm').val('');
		}
    });
    
    // 사용료고지목록의 데이터를 선택할 때
    this.$("#assetRentFeeList").on('onItemSelected', function(event, module, row, grid, param) {
    	module.setButtonStatus();
    });
	
	// 사용료고지목록의 데이터를 더블클릭할 때
    this.$("#assetRentFeeList").on('onItemDoubleClick', function(event, module, row, grid, param) {
    	module.loadRentFeePayMngt();
    });
	
	// 사용자고지목록의 셀 편집이 이루어졌을 때
    this.$("#assetRentFeeList").on('onCellEdited', function(event, module, row, rid, cid) {
    	module.onCalcFeeListCellEdited(row, rid, cid);
    });
};

<%--
	버튼 상태 설정
--%>
GamHtldRentFeeMngtModule.prototype.setButtonStatus = function() {
	var tab_active = this.$('#assetRentFeeListTab').tabs('option', 'active');
	switch(tab_active) {
	case 0:
		console.log('set button status');
    	var rows = this.$('#assetRentFeeList').selectedRows();
		if(rows.length>0) {
			var row=rows[0];
			if(row['nhtPrintYn']!='Y') {
	        	if(row['nhtIsueYn']!='Y') {
	        		this.$('#btnExecNticIssue').show();
	        		this.$('#btnNticIssuePrint').hide();
	        		this.$('#btnCancelNticIssue').hide();
	        	}
	        	else {
	        		this.$('#btnExecNticIssue').hide();
	        		this.$('#btnNticIssuePrint').show();
	        		this.$('#btnCancelNticIssue').show();
	        	}
	    	}
	    	else {
	       		this.$('#btnExecNticIssue').hide();
	       		this.$('#btnNticIssuePrint').show();
	       		this.$('#btnCancelNticIssue').show();
	    	}
		}
		else {
       		this.$('#btnExecNticIssue').hide();
       		this.$('#btnNticIssuePrint').hide();
       		this.$('#btnCancelNticIssue').hide();
		}
		break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs1 : 사용료고지목록 관련 사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	사용료고지목록 조회
--%>
GamHtldRentFeeMngtModule.prototype.loadData = function() {
    var searchOpt=this.makeFormArgs('#gamAssetRentFeeSearchForm');
    this.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
    this.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
};

<%--
	사용료고지목록이 로드될 때 자동 계산 및 채우기
--%>
GamHtldRentFeeMngtModule.prototype.makeRowData = function(item) {
	item.nhtIsueYnNm = (item.nhtIsueYn == 'Y') ? '고지': '';
	item.nhtPrintYnNm = (item.nhtPrintYn == 'Y') ? '출력': '';
	item.nticPdDate = item.nticPdFrom + '~' + item.nticPdTo;
	
	item.assessSeNm = (item.assessSe == '1') ? '실적': ((item.assessSe == '2') ? '면적' : '일반');
	
	if(item.nticMth!='4') { //분기납이 아닐 경우 이자율은 0
		item.intrRate = 0;
		item.intrAmnt = 0;
	}	
	
	// 임대내역 고지 상세 초기화
	item.intrAmnt = this.initNticDetailIntrAmnt(item);
	
	if(item.feeAmnt==void(0)) {
		var feeAmnt=Number(item.fee) + Number(item.addAmnt) + Number((item.intrAmnt == void(0) ? 0 : item.intrAmnt)) ;
		item.feeAmnt = Math.floor(feeAmnt*0.1) * 10; //공급가액에서 1원단위는 절사한다.
	}
	var vatRate = (item.vatYn=='2') ? 0.1 : 0;
	
	item.vat=Math.floor(item.feeAmnt*vatRate);
	item.nticAmt=item.feeAmnt+item.vat;

	item.oldAddAmnt = item.addAmnt;
	item.oldFee = item.fee;
	item.oldIntrAmnt = item.intrAmnt;
	item.oldIntrRate = item.intrRate;
	item.oldVat = item.vat;
	item.oldNticAmt = item.nticAmt;
};

<%--
	임대상세내역과 고지데이터를 병합하여 새로운 임대상세고지목록을 만들고 각각의 임대상세고지내역의 이자를 구한다음 이를 고지데이터의 이자에 합산하여 반환하는 모듈 
--%>
GamHtldRentFeeMngtModule.prototype.initNticDetailIntrAmnt = function(row) {
	var result = 0;
	if(this._assetsDetailList != void(0)) {
		for(var i=0; i<this._assetsDetailList.length; i++) {
			var assetsDetail = this._assetsDetailList[i];
			if( (assetsDetail.prtAtCode == row.prtAtCode) && (assetsDetail.mngYear == row.mngYear) && (assetsDetail.mngNo == row.mngNo) && (assetsDetail.mngCnt == row.mngCnt) ) {
				var nticDetail = {};
				nticDetail.assetsUsageSeq = assetsDetail.assetsUsageSeq;
				nticDetail.prtAtCode = assetsDetail.prtAtCode;
				nticDetail.mngYear = assetsDetail.mngYear;
				nticDetail.mngNo = assetsDetail.mngNo;
				nticDetail.mngCnt = assetsDetail.mngCnt;
				nticDetail.usageAr = assetsDetail.usageAr;
				nticDetail.applcPrice = assetsDetail.applcPrice;
				nticDetail.priceSe = assetsDetail.priceSe;
				nticDetail.fee = assetsDetail.fee;				
				nticDetail.nticCnt = row.nticCnt;
				nticDetail.chrgeKnd = row.chrgeKnd;
				nticDetail.nticPdFrom = row.nticPdFrom;
				nticDetail.nticPdTo = row.nticPdTo;
				nticDetail.intrRate = row.intrRate;
				nticDetail.intrAmnt = this.getIntrAmount(nticDetail.fee, row.intrRate, row.nticMth, row.nticPdFrom, row.nticPdTo, row.grUsagePdFrom, row.grUsagePdTo);
				result += nticDetail.intrAmnt; 
				this._nticDetailList[this._nticDetailList.length] = nticDetail;
			}
		}
	}
	return result;
};

<%--
	사용료고지목록 합계 표시
--%>
GamHtldRentFeeMngtModule.prototype.onCalcSummary = function() {
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
		sum.sumVat+=Number(this.cells(id,15).getValue());
		sum.sumIntrAmt+=Number(this.cells(id,11).getValue());
		sum.sumNticAmt+=Number(this.cells(id,16).getValue());
	});
	this.makeDivValues('#summaryTable', sum);
};

<%--
	사용료고지목록 특정 cell의 편집될 때 자동계산
--%>
GamHtldRentFeeMngtModule.prototype.onCalcFeeListCellEdited = function(row, rid, cid) {
	if(row.nhtIsueYn=='Y') {
		alert('고지된 자료는 수정 되지 않습니다.');
		row.addAmnt = row.oldAddAmnt;
		row.intrRate = row.oldIntrRate;
		row.vat = row.oldVat;
		row.nticAmt = row.oldNticAmt;
		this.$("#assetRentFeeList").flexUpdateRow(rid, row);
		return;
	}
    if(row._updtId!="I") {
    	row._updtId="U";
    	row.state="*";
    }
    var feeAmnt = 0;
    switch(cid) {
    	case 'intrRate' :
    		// 고지데이터에 해당하는 임대상세고지목록의 내역 임대료 이자를 합산계산
    		row.intrAmnt = this.onCalcNticDetailIntrAmnt(row);
    		feeAmnt = Number(row.fee) + Number(row.intrAmnt) + Number(row.addAmnt);
    		row.feeAmnt = Math.floor(feeAmnt*0.1) * 10; //공급가액에서 1원단위는 절사한다.
    		row.vat = (row.vatYn=='2' || row.vatYn=='Y') ? Math.floor(Number(row.feeAmnt) * 0.1) : 0;
    		row.nticAmt = Number(row.feeAmnt) + Number(row.vat);
    		break;
    	case 'addAmnt' :
    		feeAmnt = Number(row.fee) + Number(row.intrAmnt) + Number(row.addAmnt);
    		row.feeAmnt = Math.floor(feeAmnt*0.1) * 10; //공급가액에서 1원단위는 절사한다.
    		row.vat = (row.vatYn=='2' || row.vatYn=='Y') ? Math.floor(Number(row.feeAmnt) * 0.1) : 0;
    		row.nticAmt = Number(row.feeAmnt) + Number(row.vat);
    		break;
    	case 'vat' :
    		row.nticAmt = Number(row.feeAmnt) + Number(row.vat);
    		break;
    }
    
	row.oldAddAmnt = row.addAmnt;
	row.oldIntrRate = row.intrRate;
	row.oldVat = row.vat;
	row.oldNticAmt = row.nticAmt;
    this.$("#assetRentFeeList").flexUpdateRow(rid, row);
    this.onCalcSummary();	
};

<%--
	이자율이 변경될 때 각각의 임대상세고지내역의 이자를 구하고 이를 합산하는 모듈
--%>
GamHtldRentFeeMngtModule.prototype.onCalcNticDetailIntrAmnt = function(row) {
	var result = 0;
	for(var i=0; i<this._nticDetailList.length; i++) {
		var nticDetail = this._nticDetailList[i];
		if( (nticDetail.prtAtCode == row.prtAtCode) && (nticDetail.mngYear == row.mngYear) && (nticDetail.mngNo == row.mngNo) 
				&& (nticDetail.mngCnt == row.mngCnt) && (nticDetail.nticCnt == row.nticCnt) && (nticDetail.chrgeKnd == row.chrgeKnd) ) {
			nticDetail.intrAmnt = this.getIntrAmount(nticDetail.fee, row.intrRate, row.nticMth, row.nticPdFrom, row.nticPdTo, row.grUsagePdFrom, row.grUsagePdTo);
			nticDetail.intrRate = row.intrRate;
			result += nticDetail.intrAmnt; 
		}
	}
	return result;
};

<%--
	고지팝업 오픈
--%>
GamHtldRentFeeMngtModule.prototype.openNticIssuePopup = function() {
    if(this.$('#assetRentFeeList').selectedRowCount()<1) {
    	alert("목록에서 고지 할 건을 선택하십시오.");
    	return;
    }
    var row = this.$('#assetRentFeeList').selectedRows()[0];
    if( row['nhtIsueYn'] == 'Y' ) {
        alert("이미 고지된 건 입니다.");
        return;
    }
    this.doExecuteDialog('nticIssuePopup', '사용료 고지', '/oper/htld/showNticIssuePopup.do', row);
};

<%--
	고지내역에 해당 하는 임대상세고지내역데이터 뽑아오기 
--%>
GamHtldRentFeeMngtModule.prototype.getNticDetailList = function(ntic) {
	var result = [];
	for(var i=0; i<this._nticDetailList.length; i++) {
		var nticDetail = this._nticDetailList[i];
		if( (nticDetail.prtAtCode == ntic.prtAtCode) && (nticDetail.mngYear == ntic.mngYear) && (nticDetail.mngNo == ntic.mngNo) 
				&& (nticDetail.mngCnt == ntic.mngCnt) && (nticDetail.nticCnt == ntic.nticCnt) && (nticDetail.chrgeKnd == ntic.chrgeKnd) ) {
			result[result.length] = nticDetail;
		}
	}
	return result;
};

<%--
	고지처리
--%>
GamHtldRentFeeMngtModule.prototype.processNticIssue = function(value) {
	var nticInfo = {};
	var nticDetailList = this.getNticDetailList(value);
	nticInfo['nticData'] = JSON.stringify(value);
	nticInfo['nticDetailList'] = JSON.stringify(nticDetailList);
	this.doAction('/oper/htld/insertHtldFeeNticSingle.do', nticInfo, function(module, result) {
		if(result.resultCode=='0') {
			module.loadData();
		}
		alert(result.resultMsg);
	});
};

<%--
	고지취소
--%>
GamHtldRentFeeMngtModule.prototype.cancelNticIssue = function() {
	if(this.$('#assetRentFeeList').selectedRowCount()<1) {
		alert("목록에서 고지 취소 할 항목을 선택하십시오.");
        return;
	}   
	var row = this.$('#assetRentFeeList').selectedRows()[0];
	if(row['nhtIsueYn'] != 'Y') {
		alert("고지되지 않았습니다.");
		return;
	}
    if( confirm("선택한 건의 고지를 취소 하시겠습니까?") ) {
    	if(row['nhtPrintYn']!='Y' || confirm("출력한 자료를 취소하려고 합니다. 고지 취소를 하려면 먼저 출력을 취소 해야 합니다. 출력을 취소하면 발행된 마이너스 세금계산서가 발행 되고 징수의뢰 자료가 삭제 됩니다. 출력을 취소 하시겠습니까?")) {
        	this.doAction('/oper/htld/cancelHtldRentFeeNticSingle.do', row, function(module, result) {
            	if(result.resultCode=='0') {
                	var searchOpt=module.makeFormArgs('#gamAssetRentFeeSearchForm');
                    module.$("#assetRentFeeListTab").tabs("option", {active: 0});    // 탭을 전환 한다.
                    module.$('#assetRentFeeList').flexOptions({params:searchOpt}).flexReload();
                }
            	alert(result.resultMsg);
            });
        }
    }
};

<%--
	고지서 출력
--%>
GamHtldRentFeeMngtModule.prototype.nticIssuePrint = function() {
    if(this.$('#assetRentFeeList').selectedRowCount()<1) {
    	alert("목록에서 고지 할 건을 선택하십시오.");
    	return;
    }
    var row = this.$('#assetRentFeeList').selectedRows()[0];
    if( row['nhtIsueYn'] != 'Y' ) {
    	alert("해당 건은 아직 고지되지 않았습니다.");
        return;
    }
    this.printPayNotice('/oper/htld/printHtldRentFeePayNotice.do', row);
};

<%--
	납부관리현황 호출
--%>
GamHtldRentFeeMngtModule.prototype.loadRentFeePayMngt = function() {
    var rows = this.$('#assetRentFeeList').selectedRows();
    var opts = {};
    if(rows.length>0) {
    	opts = {
    			action: 'selectRentFeePay',
    			nticVo:{ prtAtCode: rows[0].prtAtCode, mngYear: rows[0].mngYear, mngNo: rows[0].mngNo, mngCnt: rows[0].mngCnt, nticCnt: rows[0].mngCnt }
    	};
    }
	EMD.util.create_window('gamHtldRentFeePaySttusMngt', '배후단지납부현황관리', '/oper/htld/gamHtldRentFeePaySttusMngt.do', null, opts);
};

<%--
	기간에 따른 이자 계산
--%>
GamHtldRentFeeMngtModule.prototype.getIntrAmount = function(fee, intrRate, nticMth, nticPdFrom, nticPdTo, grUsagePdFrom, grUsagePdTo) {
	var result = 0;
	var monthIntrAmount = 0; //월이자
	var applyMonths = 0; //이자적용월수
	var applyDays = 0; //이자적용일수
	
	fee = Number(fee);
	intrRate = Number(intrRate);
	nticPdFrom = this.createExtendedDate(EMD.util.strToDate(nticPdFrom));
	nticPdTo = this.createExtendedDate(EMD.util.strToDate(nticPdTo));
	grUsagePdFrom = this.createExtendedDate(EMD.util.strToDate(grUsagePdFrom));
	grUsagePdTo = this.createExtendedDate(EMD.util.strToDate(grUsagePdTo));
	
	monthIntrAmount = fee * (intrRate / 100) / 12; //월이자
	
	switch(nticMth) {
		case '1' : //일괄
		case '6' : //연납
		case '5' : //월납
		case '2' : //반기납
		case '3' : //3분납
			break;
		case '4' : //분기납
			if(nticPdTo.getYear() < grUsagePdTo.getYear()) { 
				//고지대상기간종료년도와 총사용(계약)기간종료년도보다 작으면 그 해 내내 분납이 지속되는 것으로 간주.
				//이자적용월수 구하기
				applyMonths = 12 - nticPdTo.getMonth();
				if(applyMonths > 0) {
					result = monthIntrAmount * applyMonths; //총이자 = 월이자 * 이자적용월수
				} 
			} else {
				//고지대상기간종료년도와 총사용기간종료년도와 같으면
				if(grUsagePdTo.isLastDayOfMonth()) {
					//총사용기간종료일이 그 달의 마지막 날이면 적용월수만 구해서 이자를 구한다. 
					applyMonths = grUsagePdTo.getMonth() - nticPdTo.getMonth();
					if(applyMonths > 0) {
						result = monthIntrAmount * applyMonths; //총이자 = 월이자 * 이자적용월수
					}
				} else {
					//총사용기간종료일이 그 달의 마지막 날이 아니라면 총사용기간종료일 전달까지의 적용월수를 구한다.
					applyMonths = (grUsagePdTo.getMonth() - 1) - nticPdTo.getMonth();
					if(applyMonths >= 0) {
						//적용월수가 0이라도 적용일수가 있으므로 이자를 계산한다.
						applyDays = grUsagePdTo.getDay();
						daysOfMonth = grUsagePdTo.getLastDayOfMonth(); //종료월의 날수 구하기.
						//이자 : (월이자 * 전달까지의 적용월수) + (월이자 * (적용일수/달일수))
						result = (monthIntrAmount * applyMonths) + (monthIntrAmount * (applyDays / daysOfMonth));
					}
				}
			}
			result = (result > 0) ? Math.floor(result*0.1) * 10 : 0; //1원단위는 절사한다.
			break;
	};
	return result;
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	tabs1 : 사용료고지목록 관련 사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	지역적 모듈 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	Date객체 변형 객체 정의.
--%>
GamHtldRentFeeMngtModule.prototype.createExtendedDate = function (argDate) {
	var dateObj = argDate;
	var daysOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var year = dateObj.getFullYear(), month = dateObj.getMonth(), day = dateObj.getDate(), time = dateObj.getTime();
	daysOfMonth[1] = (((year%4==0) && (year%100!=0)) || (year%400==0)) ? 29 : 28;
	return {
		getDateObject : function() { return dateObj; }
		, getYear : function() { return year; }
		, getMonth : function() { return (month + 1); }
		, getDay : function() { return day; }
		, getTime : function() { return time; }
		, isLeapYear : function() { return daysOfMonth[1] == 29; }
		, getLastDayOfMonth : function() { return daysOfMonth[month]; }
		, getQuarter : function() { return Math.floor((month/3)+1); }
		, getRemainDaysOfMonth : function() { return daysOfMonth[month] - day + 1; }
		, isLastDayOfMonth : function() { return day == daysOfMonth[month]; }
		, equals : function(argDate) { return argDate.getTime() == time; }
		, equalsYear : function(argDate) { return argDate.getYear() == year; }
		, equalsMonth : function(argDate) { return argDate.getMonth() == this.getMonth(); }
		, equalsYearMonth : function(argDate) { return (argDate.getYear() == year) && (argDate.getMonth() == this.getMonth()); }
		, equalsQuarter : function(argDate) { return argDate.getQuarter() == this.getQuarter(); }
		, isQuarterStartDate : function() { return ((this.getMonth() == 1) || (this.getMonth() == 4) || (this.getMonth() == 7) || (this.getMonth() == 10)) && (day == 1); }
		, isQuarterEndDate : function() { return ((this.getMonth() == 3) || (this.getMonth() == 6) || (this.getMonth() == 9) || (this.getMonth() == 12)) && this.isLastDayOfMonth(); }
		, getQuarterStartDate : function() {
			var y=this.getYear(); var m=0; var d = 1;
			switch(this.getQuarter()) {
			case 1 : m = 1; break;
			case 2 : m = 4; break;
			case 3 : m = 7; break;
			case 4 : m = 10; break;
			}
			return new Date(y, m-1, d);
		}
	};	
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	지역적 모듈 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamHtldRentMngtModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentFeeMngtModule();
</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
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
                            	<input id="nticPdFrom" type="text" class="emdcal" size="8"> ~
                            	<input id="nticPdTo" type="text" class="emdcal" size="8">
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
                        		이자 합계
                        	</th>
                            <td style="text-align: right;">
                            	<span data-column-id="sumIntrAmt" class="ygpaNumber"></span>
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
                        </tr>
                    </table>
                    <button id="btnExecNticIssue">고지</button>
                    <button id="btnCancelNticIssue">고지취소</button>
                    <button id="btnNticIssuePrint">고지서출력</button>
                   	<button data-role="gridXlsDown" data-flexi-grid="assetRentFeeList" data-xls-name="배후단지임대료고지.xls" data-xls-title="배후단지 임대료 고지 내역">엑셀</button>
                    <button id="btnRentFeePayMngt">납부현황관리</button>
					<button data-role="openWindow" data-url="/code/gamCofixIntrrateMngt.do" data-title="COFIX 이자율 관리">이자율관리</button>
                </div>
            </div>
	    </div>
	</div>
</div>
