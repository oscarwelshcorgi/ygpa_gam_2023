<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamHtldRentArrrgNticIssue.jsp
  * @Description : 연체고지 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2016.5.13  Jongmin          최초 생성
  *
  * author Jongmin
  * since 2016.05.13
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
--%>
<script>
function GamHtldRentArrrgNticIssueModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentArrrgNticIssueModule.prototype = new EmdModule(740, 560);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentArrrgNticIssueModule.prototype.loadComplete = function(params) {

    this.$("#nticList").flexigrid({
        module: this,
        url: '/oper/htldnew/selectHtldRentArrrgNticIssueInfo.do',
        dataType: 'json',
        colModel : [
                    {display:'고지항목', name:'nticItemNm',width:170, sortable:false,align:'center'},
                    {display:'임대면적(㎡)', name:'rentArStr',width:135, sortable:false,align:'right'},
                    {display:'적용단가', name:'applcRntfeeStr',width:130, sortable:false,align:'right'},
                    {display:'구분', name:'paySeNm',width:45, sortable:false,align:'center'},
    				{display:'임대료', name:'rntfee',width:110, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'분납이자', name:'payinstIntr',width:90, sortable:false,align:'right', displayFormat: 'number'},
                    ],
        showTableToggleBtn: false,
        height: '140',
		preProcess: function(module, data) {
			module.makeFormValues('#gamHtldRentArrrgNticIssueForm', data.resultMaster);
			module._arrrgDetail = data.arrrgDetail;
			$.each(data.resultList, function() {
				module.initDataRow(this);
	     	});
	     	return data;
	   	}
    });

    this.$("#arrrgList").flexigrid({
        module: this,
        url: '',
        dataType: 'json',
        colModel : [
                    {display:'회수', name:'arrrgNo',width:45, sortable:false,align:'center'},
                    {display:'연체항목', name:'arrrgItemNm',width:305, sortable:false,align:'center'},
                    {display:'연체요율', name:'arrrgRate',width:130, sortable:false,align:'right'},
    				{display:'연체료', name:'arrrgAmt',width:200, sortable:false,align:'right', displayFormat: 'number'},
                    ],
        showTableToggleBtn: false,
        height: '100',
		preProcess: function(module, data) {
	     	return data;
	   	}
    });

	this.$("#nticList").on('onLoadDataComplete', function(event, module, data) {
		module.$('#payDate').val(new Date().toISOString().substring(0, 10));
		module.$('#dueDate').val(module._arrrgDetail.dueDate);

		var supAmt = 0, rntfee = 0, payinstIntr = 0;
		if(data.length > 0) {
			for(var i=0; i<data.length; i++) {
				var row = data[i];
				rntfee += Number(row.rntfee);
				payinstIntr += Number(row.payinstIntr);
			}
			supAmt = rntfee + payinstIntr;
			supAmt = Math.floor(supAmt*0.1) * 10;
			var vat = (supAmt >= 0) ? supAmt / 10 : 0;
			module.$('#supAmt').val(supAmt);
			module.$('#vat').val(vat);
		} else {
			module.$('#supAmt').val(0);
			module.$('#vat').val(0);
			module.$('#arrrgAmt').val(0);
			module.$('#payAmt').val(0);
			module.$('#btnNticIssue').disable({disableClass:"ui-state-disabled"});
		}

		module.calcArrrgAmt();
	});

	if(params != null) {
		this.$('#mngYear').val(params.searchRow.mngYear);
		this.$('#mngNo').val(params.searchRow.mngNo);
		this.$('#mngSeq').val(params.searchRow.mngSeq);
		this.$('#chrgeKndCd').val(params.searchRow.chrgeKndCd);
		this.$('#accnutYear').val(params.searchRow.accnutYear);
		this.$('#rntfeeNticNo').val(params.searchRow.rntfeeNticNo);
		this.$('#nticSeq').val(params.searchRow.nticSeq);
		this.$('#nticCnt').val(params.searchRow.nticCnt);
		this.$('#histDt').val(params.histDt);
		this._histDt = params.histDt;
		this.loadData();
	} else {
		this.$('#btnArrrgNticIssue').disable({disableClass:"ui-state-disabled"});
	}

	var module=this;
	this.$('#dueDate,#payDate').on('change', function(e) {
		module.calcArrrgAmt();
	});
	//this.$('#btnArrrgNticIssueExcelDownload').disable({disableClass:"ui-state-disabled"});
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentArrrgNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnArrrgNticIssue' :
		this.execArrrgNticIssue();
		break;
	case 'btnCancel': //닫기
		this.closeWindow();
		break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 고지정보 조회
--%>
GamHtldRentArrrgNticIssueModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs('#gamHtldRentArrrgNticIssueForm');
	this.$('#nticList').flexOptions({params:searchOpt}).flexReload();
};

<%--
	initDataRow - 각 row 데이터 초기 설정
	params :
		row - 그리드 row
--%>
GamHtldRentArrrgNticIssueModule.prototype.initDataRow = function(row) {
	if(row.rntfeeSe == '0') { //일반고지
		row.nticItemNm = row.detailPdBegin + '~' + row.detailPdEnd;
		if(row.rentArSe != '0') {
			row.rentArStr = row.rentArStr + '/' + row.rentArSeNm;
			if(row.rentArSe == '3') {
				row.rentArStr = row.rentArSeNm;
			}
		}
		if((row.srcNticDt >= row.aseApplcBegin) && (row.srcNticDt <= row.aseApplcEnd)) {
			row.applcRntfeeStr = row.aseRntfeeStr + '(실적)';
		}
		if(row.priceSe == '2') {
			row.applcRntfeeStr += '원/월';
		}
	} else {
		row.nticItemNm = row.rntfeeSeNm;
		if((row.rntfeeSe == '1') || (row.rntfeeSe == '2')) {
			row.rentArStr = row.applcBeginDt + '~' + row.applcEndDt;
			row.applcRntfeeStr = row.appRntfee;
		} else {
			row.rentArStr = '';
			row.applcRntfeeStr = '';
		}
		row.payinstIntr = 0;
	}
	this.$('#nticBeginDt').val(row.nticBeginDt);
	this.$('#nticEndDt').val(row.nticEndDt);
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamHtldRentArrrgNticIssueModule.prototype.validateData = function() {
	if(this.$('#nticDt').val() == '') {
		alert('연체고지(예정)일자를 입력하세요..');
		return false;
	}
	if(this.$('#payTmlmt').val() == '') {
		alert('연체납부기한을 입력하세요.');
		return false;
	}
	if(this.$('#nticDt').val() > this.$('#payTmlmt').val()) {
		alert('연체고지(예정)일자가 연체납부기한보다 큽니다.');
		return false;
	}
	return true;
};


<%--
	execArrrgNticIssue - 연체고지
--%>
GamHtldRentArrrgNticIssueModule.prototype.execArrrgNticIssue = function() {
	if(!confirm("연체고지하시겠습니까?")) return;
	if(!this.validateData()) return;

	this.$('#dlyBillDt').val(this.$('#nticDt').val());
	this.$('#newPayTmlmt').val(this.$('#payTmlmt').val());

	var arrrgData = this.makeFormArgs('#gamHtldRentArrrgNticIssueForm');
	this.$('#btnArrrgNticIssue').disable({disableClass:"ui-state-disabled"});
	this.doAction('/oper/htldnew/execArrrgNticIssue.do', arrrgData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module._arrrgNticIssue = 'Y';
			module._parent.loadData();
			//module.$('#btnArrrgNticIssueExcelDownload').enable();
			//module.$('#btnArrrgNticIssueExcelDownload').removeClass('ui-state-disabled');
			module.closeWindow();
		} else {
			module.$('#btnArrrgNticIssue').enable();
			module.$('#btnArrrgNticIssue').removeClass('ui-state-disabled');
		}
	});
};

// 연체료 계산
GamHtldRentArrrgNticIssueModule.prototype.calcArrrgAmt = function() {
	var dueDate=this.$('#dueDate').val();
	var payDate=this.$('#payDate').val();
	var dueDt = new Date(dueDate.substring(0,4), dueDate.substring(5,7), dueDate.substring(8, 10));
	var payDt = new Date(payDate.substring(0,4), payDate.substring(5,7), payDate.substring(8, 10));
	var dlyDay = (payDt.getTime()-dueDt.getTime())/(24*60*60*1000);
	var supAmt = this.$('#supAmt').val();
	var arrrgSum = 0;
	var arrrgList = [];
	var arrrgNo = 1;
	var arrrgRate=0;
	var dlyBillRsn = '납부일자 : '+payDate+'\n';

	if(dlyDay>=180) {
		var arrrgDay = dlyDay-179;
		dlyDay=179;
		var arrrgAmt = Math.floor(supAmt*(arrrgDay/365*0.1)/10)*10;
		arrrgSum+=arrrgAmt;
		arrrgRate=0.1;
		dlyBillRsn+='6개월 이상 ('+arrrgDay+'일) 연 10% = '+$.number(arrrgAmt)+'원\n';
		arrrgList[arrrgList.length]={arrrgNo:arrrgNo++, arrrgItemNm:'6개월 이상 ('+arrrgDay+'일)', arrrgRate:'10%', arrrgAmt:arrrgAmt};
	}
	if(dlyDay>=90) {
		var arrrgDay = dlyDay-89;
		dlyDay=89;
		var arrrgAmt = Math.floor(supAmt*(arrrgDay/365*0.09)/10)*10;
		arrrgSum+=arrrgAmt;
		if(arrrgRate==0) arrrgRate=0.09;
		dlyBillRsn+='3개월 이상 ('+arrrgDay+'일) 연 9% = '+$.number(arrrgAmt)+'원\n';
		arrrgList[arrrgList.length]={arrrgNo:arrrgNo++, arrrgItemNm:'3개월 이상 6개월 미만 ('+arrrgDay+'일)', arrrgRate:'9%', arrrgAmt:arrrgAmt};
	}
	if(dlyDay>=30) {
		var arrrgDay = dlyDay-29;
		dlyDay=29;
		var arrrgAmt = Math.floor(supAmt*(arrrgDay/365*0.08)/10)*10;
		arrrgSum+=arrrgAmt;
		if(arrrgRate==0) arrrgRate=0.08;
		dlyBillRsn+='1개월 이상 ('+arrrgDay+'일) 연 8% = '+$.number(arrrgAmt)+'원\n';
		arrrgList[arrrgList.length]={arrrgNo:arrrgNo++, arrrgItemNm:'1개월 이상 3개월 미만 ('+arrrgDay+'일)', arrrgRate:'8%', arrrgAmt:arrrgAmt};
	}
	if(dlyDay>0) {
		var arrrgDay = dlyDay;
		var arrrgAmt = Math.floor(supAmt*(arrrgDay/365*0.07)/10)*10;
		arrrgSum+=arrrgAmt;
		if(arrrgRate==0) arrrgRate=0.07;
		dlyBillRsn+='1개월 미만 ('+arrrgDay+'일) 연 7% = '+$.number(arrrgAmt)+'원\n';
		arrrgList[arrrgList.length]={arrrgNo:arrrgNo++, arrrgItemNm:'1개월 미만 ('+arrrgDay+'일)', arrrgRate:'7%', arrrgAmt:arrrgAmt};
	}
	this.$('#arrrgNticAmt').val(arrrgSum);
	this.$('#arrrgAmt').val(arrrgSum);
	this.$('#payAmt').val(arrrgSum);
	this.$('#dlyBillAmnt').val(arrrgSum);
	this.$('#dbillAmnt').val(arrrgSum);
	this.$("#arrrgList").flexEmptyData();
	this.$("#arrrgList").flexAddData({
		resultCode: 0,
		resultList: arrrgList
		});

	var vat = this.$('#vat').val();

	this.$('#nticAmt').val(arrrgSum);
	this.$('#arrrgNo').val(1);
	this.$('#arrrgTariff').val(arrrgRate);
	this.$('#dlyBillRsn').val(dlyBillRsn);

};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamHtldRentArrrgNticIssueModule();
</script>

<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="gamHtldRentArrrgNticIssueForm">
				<input type="hidden" id="mngYear" />
				<input type="hidden" id="mngNo" />
				<input type="hidden" id="mngSeq" />
				<input type="hidden" id="chrgeKndCd" />
				<input type="hidden" id="accnutYear" />
				<input type="hidden" id="rntfeeNticNo" />
				<input type="hidden" id="nticSeq" />
				<input type="hidden" id="histDt" />
				<input type="hidden" id="arrrgNo" />
				<input type="hidden" id="arrrgTariff" />
				<input type="hidden" id="dlyBillRsn" />
				<input type="hidden" id="nticAmt" />
				<input type="hidden" id="arrrgNticAmt" />
				<input type="hidden" id="newPayTmlmt" />
				<input type="hidden" id="dlyBillDt" />

	        	<table class="editForm" style="width:100%">
	        		<tr>
						<th width="10%" height="18">고지대상기업</th>
						<td>
							<input type="text" size="30" id="entrpsNm" disabled/>
							<input type="hidden" id="entrpsCd" />
						</td>
						<th width="10%" height="18">사업자등록번호</th>
						<td>
							<input type="text" size="30" id="bizrno" disabled/>
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">대표자</th>
						<td>
							<input type="text" size="30" id="bizrno" disabled/>
						</td>
						<th width="10%" height="18">고지대상기간</th>
						<td>
							<input type="text" size="12" id="nticBeginDt" class="emdcal" disabled/> ~
							<input type="text" size="12" id="nticEndDt" class="emdcal" disabled/>
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">납부일자</th>
						<td>
							<input type="text" size="12" id="payDate" class="emdcal"/>
						</td>
						<th width="10%" height="18">납부기한</th>
						<td>
							<input type="text" size="12" id="dueDate" class="emdcal"/>
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">연체고지일자</th>
						<td>
							<input type="text" size="12" id="nticDt" class="emdcal"/>
						</td>
						<th width="10%" height="18">연체납부기한</th>
						<td>
							<input type="text" size="12" id="payTmlmt" class="emdcal"/>
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">공급가액</th>
						<td>
							<input type="text" size="15" id="supAmt" class="ygpaNumber" disabled/>&nbsp;원
						</td>
						<th width="10%" height="18">부가세</th>
						<td>
							<input type="text" size="15" id="vat" class="ygpaNumber" disabled/>&nbsp;원
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">연체료</th>
						<td>
							<input type="text" size="15" id="arrrgAmt" class="ygpaNumber" disabled/>&nbsp;원
						</td>
						<th width="10%" height="18">연체납부금액</th>
						<td>
							<input type="text" size="15" id="payAmt" class="ygpaNumber" disabled/>&nbsp;원
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">비고</th>
						<td colspan="3">
							<input type="text" size="30" id="rm" />
						</td>
					</tr>
				</table>
			</form>

			<div class="emdPanel fillHeight">
				<table id="nticList" style="display: none" class="fillHeight"></table>
			</div>
			<div class="emdPanel fillHeight">
				<table id="arrrgList" style="display: none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<!-- <button id="btnArrrgNticIssueExcelDownload">산출내역서 다운로드</button>  -->
					<button id="btnArrrgNticIssue">연체고지</button>
					<button id="btnCancel">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>