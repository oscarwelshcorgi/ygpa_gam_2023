<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamHtldRentNticIssue.jsp
  * @Description : 고지 화면
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
function GamHtldRentNticIssueModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentNticIssueModule.prototype = new EmdModule(750, 380);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentNticIssueModule.prototype.loadComplete = function(params) {

    this.$("#nticList").flexigrid({
        module: this,
        url: '/oper/htldnew/selectHtldRentNticIssueInfo.do',
        dataType: 'json',
        colModel : [
					{display:'선택<div id="'+this.getId('title_chkRole')+'" style="padding-right:3px"></div>',name:'chkRole', width:40, sortable:false, align:'center', displayFormat: 'checkbox', skipxls: true},
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
			module.makeFormValues('#gamHtldRentNticIssueForm', data.resultMaster);
			module.$('#intrrate').val(module.intrrate);
			$.each(data.resultList, function() {
				module.initDataRow(this);
	     	});
	     	return data;
	   	}
    });
    
	this.$("#nticList").on('onLoadDataComplete', function(event, module, data) {
		module.$('#nticList')[0].dgrid.attachEvent('onCellChanged', function(rid, cind, value) {
			var module = this.p.module;
	    	var cid = this.getColumnId(cind);
	    	if(cid == 'chkRole') {
	    		module.onCalcPay();
	    	}
		});
		module.onCalcPay();
	});

	if(params != null) {
		this.$('#mngYear').val(params.searchRow.mngYear);
		this.$('#mngNo').val(params.searchRow.mngNo);
		this.$('#mngSeq').val(params.searchRow.mngSeq);
		this.$('#histDt').val(params.histDt);
		this._histDt = params.histDt;
		this._intrrate = params.intrrate;
		this.loadData();
	} else {
		this.$('#btnNticIssue').disable({disableClass:"ui-state-disabled"}); 
	}	
	this.$('#btnNticIssueExcelDownload').disable({disableClass:"ui-state-disabled"});
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentNticIssueModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnNticIssue' :
		this.execNticIssue();
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
GamHtldRentNticIssueModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs('#gamHtldRentNticIssueForm');
	this.$('#nticList').flexOptions({params:searchOpt}).flexReload();
};

<%--
	initDataRow - 각 row 데이터 초기 설정
	params :
		row - 그리드 row
--%>
GamHtldRentNticIssueModule.prototype.initDataRow = function(row) {
	if(row.rntfeeSe == '0') { //일반고지
		row.nticItemNm = row.detailPdBegin + '~' + row.detailPdEnd;
		if(row.rentArSe != '0') {
			row.rentArStr = row.rentArStr + '/' + row.rentArSeNm;
			if(row.rentArSe == '3') {
				row.rentArStr = row.rentArSeNm;
			}
		}
		if((this.$('#histDt').val() >= row.aseApplcBegin) && (this.$('#histDt').val() <= row.aseApplcEnd)) {
			row.applcRntfeeStr = row.aseRntfeeStr + '(실적)';
		}
	} else {
		row.nticItemNm = row.rntfeeSeNm;
		row.rentArStr = '';
		row.applcRntfeeStr = '';
		row.payinstIntr = 0;
	}
	this.$('#nticBeginDt').val(row.nticBeginDt);
	this.$('#nticEndDt').val(row.nticEndDt);
	row.chkRole = true;
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamHtldRentNticIssueModule.prototype.validateData = function() {
	if(this.$('#nticDt').val() == '') {
		alert('고지(예정)일자를 입력하세요..');
		return false;
	}
	if(this.$('#payTmlmt').val() == '') {
		alert('납부기한을 입력하세요.');
		return false;
	}
	if(this.$('#nticDt').val() > this.$('#payTmlmt').val()) {
		alert('고지(예정)일자가 납부기한보다 큽니다.');
		return false;
	}
	return true;
};

<%--
	onCalcPay - 공급가액 부가세 고지금액을 계산
--%>
GamHtldRentNticIssueModule.prototype.onCalcPay = function() {
	if(this._nticIssue == 'Y') return;
	var rows = this.$('#nticList').flexGetData();
	var supAmt = 0, rntfee = 0, payinstIntr = 0;
	if(rows.length > 0) {
		var count = 0;
		this.$('#nticList')[0].dgrid.forEachRow(function(id) {
			if(this.cells(id,0).getValue() == true) {
				rntfee += Number(this.cells(id,5).getValue());
				payinstIntr += Number(this.cells(id,6).getValue());
				count++;
			}
	    });
		supAmt = rntfee + payinstIntr;  
		supAmt = Math.floor(supAmt*0.1) * 10;
		var vat = (supAmt >= 0) ? supAmt / 10 : 0;
		var payAmt = supAmt + vat;
		this.$('#rntfee').val(rntfee);
		this.$('#payinstIntr').val(payinstIntr);
		this.$('#supAmt').val(supAmt);
		this.$('#vat').val(vat);
		this.$('#payAmt').val(payAmt);
		if(count > 0) {
			this.$('#btnNticIssue').enable();
			this.$('#btnNticIssue').removeClass('ui-state-disabled');
		} else {
			this.$('#btnNticIssue').disable({disableClass:"ui-state-disabled"});			
		}
	} else {
		this.$('#rntfee').val(0);
		this.$('#payinstIntr').val(0);
		this.$('#supAmt').val(0);
		this.$('#vat').val(0);
		this.$('#payAmt').val(0);
		this.$('#btnNticIssue').disable({disableClass:"ui-state-disabled"});
	}
};

<%--
	execNticIssue - 고지
--%>
GamHtldRentNticIssueModule.prototype.execNticIssue = function() {
	if(!confirm("고지하시겠습니까?")) return;
	if(!this.validateData()) return;
	
	var nticData = {};
	nticData['nticInfo'] 			= JSON.stringify(this.getFormValues('#gamHtldRentNticIssueForm'));
	nticData['rntfeeList'] 		= JSON.stringify(this.$('#nticList').selectFilterData([{col: 'chkRole', filter: true}]));
	
	this.$('#btnNticIssue').disable({disableClass:"ui-state-disabled"});
	this.doAction('/oper/htldnew/execNticIssue.do', nticData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module._nticIssue = 'Y';
			module._parent.loadData();
			module.$('#btnNticIssueExcelDownload').enable();
			module.$('#btnNticIssueExcelDownload').removeClass('ui-state-disabled');			
		} else {
			module.$('#btnNticIssue').enable();
			module.$('#btnNticIssue').removeClass('ui-state-disabled');
		}
	});
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamHtldRentNticIssueModule();
</script>

<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="gamHtldRentNticIssueForm">
				<input type="hidden" id="mngYear" />
				<input type="hidden" id="mngNo" />
				<input type="hidden" id="mngSeq" />
				<input type="hidden" id="chrgeKndCd" />
				<input type="hidden" id="histDt" />
				<input type="hidden" id="histSeq" />
				<input type="hidden" id="paySe" />
				<input type="hidden" id="rntfee" />
				<input type="hidden" id="payinstIntr" />
				<input type="hidden" id="intrrate" />
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
							<input type="text" size="30" id="rprsntvNm" disabled/>
						</td>
						<th width="10%" height="18">고지대상기간</th>
						<td>
							<input type="text" size="12" id="nticBeginDt" class="emdcal" disabled/> ~
							<input type="text" size="12" id="nticEndDt" class="emdcal" disabled/>
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">고지(예정)일자</th>
						<td>
							<input type="text" size="12" id="nticDt" class="emdcal"/>
						</td>
						<th width="10%" height="18">납부기한</th>
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
						<th width="10%" height="18">납부금액</th>
						<td>
							<input type="text" size="15" id="payAmt" class="ygpaNumber" disabled/>&nbsp;원
						</td>
						<th width="10%" height="18">비고</th>
						<td>
							<input type="text" size="30" id="rm"/>
						</td>
					</tr>
				</table>
			</form>
			
			<div class="emdPanel fillHeight">
				<table id="nticList" style="display: none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnNticIssueExcelDownload">산출내역서 다운로드</button>
					<button id="btnNticIssue">고지</button>
					<button id="btnCancel">닫기</button>
				</div>
			</div>
		</div>		
	</div>
</div>