<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamHtldRentNticHist.jsp
  @Description : 배후단지고지이력화면
  @Modification Information

     수정일         수정자                   수정내용
    ------------ 	----------	---------------------------
    2016.04.25  	jongmin     최초 생성

   author jongmin
   since 2016.06.12

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentNticHistModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentNticHistModule.prototype = new EmdModule(1550, 610);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentNticHistModule.prototype.loadComplete = function(params) {
    this.$("#nticHistList").flexigrid({
        module: this,
        url: '/oper/htldnew/selectHtldRentNticHistList.do',
        dataType: 'json',
        colModel : [
                    {display:'고지일자', name:'nticDt',width:80, sortable:false,align:'center'},
                    {display:'고지대상기간', name:'nticPd',width:170, sortable:false,align:'center'},
                    {display:'요금종류', name:'chrgeKndNm',width:200, sortable:false,align:'center'},
                    {display:'납부구분', name:'paySeNm',width:70, sortable:false,align:'center'},
                    {display:'공급가액', name:'supAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'부가세', name:'vat',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'연체료', name:'arrrgAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'고지금액', name:'payAmt',width:110, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'납부기한', name:'payTmlmt',width:80, sortable:false,align:'center'},
    				{display:'상태', name:'status',width:80, sortable:false,align:'center'},
    				{display:'출력', name:'nhtPrtYnNm',width:50, sortable:false,align:'center'},
    				{display:'납부일자', name:'rcivDt',width:80, sortable:false,align:'center'},
    				{display:'비고', name:'rm',width:250, sortable:false,align:'left'},
                    ],
        showTableToggleBtn: false,
        height: '300',
		preProcess: function(module, data) {
			module.makeFormValues('#gamHtldRentNticHistListSearchForm', data.entrpsInfo);
			$.each(data.resultList, function() {
				module.initNticHistDataRow(this);
	     	});
	     	return data;
	   	}        
    });
        
    this.$("#nticHistList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._currentRow = row;
    	module.loadNticData();
    });

    this.$("#nticList").flexigrid({
        module: this,
        url: '',
        dataType: 'json',
        colModel : [
                    {display:'고지항목', name:'nticItemNm',width:170, sortable:false,align:'center'},
                    {display:'임대면적(㎡)', name:'rentArStr',width:140, sortable:false,align:'right'},
                    {display:'적용단가', name:'applcRntfeeStr',width:140, sortable:false,align:'right'},
                    {display:'구분', name:'paySeNm',width:50, sortable:false,align:'center'},
    				{display:'임대료', name:'rntfee',width:120, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'분납이자', name:'payinstIntr',width:100, sortable:false,align:'right', displayFormat: 'number'},
                    ],
        showTableToggleBtn: false,
        height: '150',
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				module.initNticDataRow(this);
	     	});
	     	return data;
	   	}
    });
	this.$("#nticList").on('onLoadDataComplete', function(event, module, data) {
    	module.loadArrrgNticData();
	});

    this.$("#arrrgList").flexigrid({
        module: this,
        url: '',
        dataType: 'json',
        colModel : [
                    {display:'회수', name:'dlySerNo',width:35, sortable:false,align:'center'},
                    {display:'고지일자', name:'dlyBillDt',width:80, sortable:false,align:'center'},
                    {display:'연체료', name:'dlyBillAmnt',width:80, sortable:false,align:'right', displayFormat: 'number'},
                    {display:'고지금액', name:'djiroAmnt',width:100, sortable:false,align:'right', displayFormat: 'number'},
					{display:'출력', name:'dlyBillPrtYnNm',width:35, sortable:false,align:'center'},
					{display:'납부기한', name:'dlyDueDt',width:80, sortable:false,align:'center'},
					{display:'산출근거', name:'dlyBillRsn',width:320, sortable:false,align:'center'},
                    ],
        showTableToggleBtn: false,
        height: '150',
		preProcess: function(module, data) {
	     	return data;
	   	}
    });

	if(params != null) {
		this.$('#entrpsCd').val(params.entrpsCd);
		this.loadData();
	} else {
		this.$('#entrpsCd').val('');
	}
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentNticHistModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnEntrpsInfoPopup':  //입주기업 선택
			this.doExecuteDialog('selectEntrpsInfoPopup', '입주기업 선택', '/popup/showEntrpsInfo.do', {});
			break;
		case 'btnSearch': //조회
			if(this.$('#entrpsCd').val() != '') {
				this.loadData();
			} else {
				alert('입주기업을 선택하세요.');
			}
			break;
		case 'btnCancelNticIssue' : //고지취소
			this.cancelNticIssue();
			break;
		case 'btnProcessNticIssue' : //수납처리
			this.processNticIssue();
			break;
	}
};

<%--
	팝업이 종료 될때 리턴 값이 오출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentNticHistModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'selectEntrpsInfoPopup':
			if (msg != 'cancel') {
				this.$('#entrpsCd').val(value.entrpscd);
				this.$('#entrpsNm').val(value.entrpsNm);
				this.$('#bizrno').val(value.bizrno);
				this.$('#rprsntvNm').val(value.rprsntvNm);
				this.$('#nticHistList').flexEmptyData();
				this.$('#nticList').flexEmptyData();
				this.$('#arrrgList').flexEmptyData();				
			}
			break;
		case 'rcivProcPopup':
			if (msg != 'cancel') {
				this.loadData();
				this._parent.loadData();
			}
			break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 임대상세목록 조회
--%>
GamHtldRentNticHistModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#gamHtldRentNticHistListSearchForm');
	this.$('#nticHistList').flexOptions({params:searchOpt}).flexReload();
	this.$('#nticList').flexEmptyData();
	this.$('#arrrgList').flexEmptyData();
};

<%--
	initNticHistDataRow - 이력 목록의 각 row 데이터 초기화
	params :
		row - 그리드 row
--%>
GamHtldRentNticHistModule.prototype.initNticHistDataRow = function(row) {
	row.nticPd = row.nticPdFrom + '~' + row.nticPdTo;

	switch (row.rcivSe) {
	case '2' : row.status = '연체수납'; break;
	case '3' : row.status = '수납'; break;
	case '4' : row.status = '불납'; break;
	default : 
		if(row.payTmlmtYn != 'Y') { //현재 디비시스템 시간이 납부기한을 넘기지 않았을 경우
			row.status = (row.rcivSe == '1') ? '연체고지' : '고지';
		} else  {
			row.status = '연체';
		}
	}
};

<%--
	loadNticData - 고지상세목록 조회
--%>
GamHtldRentNticHistModule.prototype.loadNticData = function() {	
	this.doAction('/oper/htldnew/selectHistNticIssueList.do', this._currentRow, function(module, result) {
		if(result.resultCode == 0) {
			module.$('#nticList').flexEmptyData();
			module.$("#nticList").flexAddData({resultList: result.resultList});
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	initNticDataRow - 각 row 데이터 초기 설정
	params :
		row - 그리드 row
--%>
GamHtldRentNticHistModule.prototype.initNticDataRow = function(row) {
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
};

<%--
	loadArrrgNticData - 연체고지상세목록 조회
--%>
GamHtldRentNticHistModule.prototype.loadArrrgNticData = function() {	
	this.doAction('/oper/htldnew/selectHistArrrgNticIssueList.do', this._currentRow, function(module, result) {
		if(result.resultCode == 0) {
			module.$('#arrrgList').flexEmptyData();
			module.$("#arrrgList").flexAddData({resultList: result.resultList});
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	cancelNticIsssue - 고지취소
--%>
GamHtldRentNticHistModule.prototype.cancelNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('이력 목록에서 데이터를 선택하세요.');
		return;
	}
	this.doAction('/oper/htldnew/selectHistArrrgNticIssueListCnt.do', this._currentRow, function(module, result) {
		if(result.resultCode == 0) {
			if(result.arrrgCnt > 0) {
				module.cancelArrrgNticIssue(); //연체고지취소
			} else {
				module.cancelSourceNticIssue(); //원고지취소
			}
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	canceSourceNticIsssue - 원고지취소
	
	nhtPrtYn은 출력버튼을 누를 때 Y로 변하지만 billPrtYn은 고지일자와 연동되어 변함.
	즉 화면에 출력표시는 nhtPrtYn으로 하는 것이고 rev_coll_f와 연계되는 출력확인을 하는 것은 billPrtYn으로 해야 함.
	bilPrtYn은 즉 고지일자별 rev_coll_f의 billPrtYn을 Y로 만드는 스케줄링의 결과값을 담고 있음.
--%>
GamHtldRentNticHistModule.prototype.cancelSourceNticIssue = function() {
	if(!confirm("선택한 건의 고지를 취소 하시겠습니까?")) return;
	
	if(this._currentRow.billPrtYn == 'Y') {
		if(!confirm("포트미스에 연동된 자료를 취소하려고 합니다. 고지 취소를 하려면 먼저 출력을 취소 해야 합니다. 출력을 취소하면 발행된 마이너스 세금계산서가 발행 되고 징수의뢰 자료가 삭제 됩니다. 출력을 취소 하시겠습니까?"))
			return;
	}
	
	this.doAction('/oper/htldnew/cancelSourceNticIssue.do', this._currentRow, function(module, result) {
    	if(result.resultCode=='0') {
    		alert('고지가 취소되었습니다.');
    		module.loadData();
    		module._parent.loadData();
    	} else {
    		alert(result.resultMsg);
    	}
	});	
};

<%--
	cancelArrrgNticIsssue - 연체고지취소
--%>
GamHtldRentNticHistModule.prototype.cancelArrrgNticIssue = function() {
	if(!confirm('연체정보가 있습니다. 마지막 연체정보를 취소하시겠습니까?')) return;
	this.doAction('/oper/htldnew/cancelArrrgNticIssue.do', this._currentRow, function(module, result) {
		if(result.resultCode == 0) {
			alert('연체고지가 취소되었습니다.');
			module.loadData();
			module._parent.loadData();
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	processNticIsssue - 수납처리
--%>
GamHtldRentNticHistModule.prototype.processNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('이력 목록에서 데이터를 선택하세요.');
		return;
	}
	
	//지로 수납된 건인지 체크한다.
	this.doAction('/oper/htldnew/selectCheckOcrResult.do', this._currentRow, function(module, result) {
		if(result.resultCode == 0) {
			if(result.ocrResult == 'Y') {
				alert('지로 수납된 자료는 변경할 수 없습니다.');
				return;
			}
			module.doExecuteDialog('rcivProcPopup', '수납 처리', '/popup/showHtldRcivProc.do', module._currentRow);
		} else {
			alert(result.resultMsg);
		}
	});
	
};

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamHtldRentNticHistModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentNticHistModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentNticHistListSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
							<th width="8%" height="18">입주기업</th>
							<td>
								<input type="hidden" id="entrpsCd">
								<input type="text" size="30" id="entrpsNm" disabled/>
								<button id="btnEntrpsInfoPopup" class="popupButton">업체선택</button>
							</td>
							<th width="8%" height="18">사업자등록번호</th>
							<td>
								<input type="text" size="20" id="bizrno" disabled/>
							</td>
							<th width="8%" height="18">대표자</th>
							<td>
								<input type="text" size="30" id="rprsntvNm" disabled/>
							</td>							
                            <td width="10%"><button id="btnSearch" class="buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
		<table style="width:100%;">
			<tr>
				<td colspan="2">
					<table id="nticHistList" style="display:none" class="fillHeight"></table>
				</td>
			</tr>
			<tr>
				<td width="60%" style="font-weight: bold;">고지 내역</td>
				<td style="font-weight: bold;">연체고지 내역</td>
			</tr>
			<tr>
				<td style="padding-right:5px;">
					<table id="nticList" style="display:none" class="fillHeight"></table>
				</td>
				<td style="padding-right:5px;">
					<table id="arrrgList" style="display:none" class="fillHeight"></table>
				</td>
			</tr>
		</table>
 		<div class="emdControlPanel">
			<table style="width:100%;">
				<tr>
					<td style="text-align: right">
                       <button id="btnCancelNticIssue" >고지취소</button>
                       <button id="btnProcessNticIssue" >수납처리</button>
                       <button id="btnPrintNticIssue" >고지서출력</button>
                       <button id="btnNticIssueExcelDownload" >산출내역서 다운로드</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>