<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  @Class Name : GamHtldRentMngtMain.jsp
  @Description : 배후단지임대관리메인화면
  @Modification Information

     수정일         수정자                   수정내용
    ------------ 	----------	---------------------------
    2016.04.25  	jongmin     최초 생성

   author jongmin
   since 2016.04.25

  Copyright (C) 2013 by LFIT  All right reserved.
--%>
<script>
<%--
 	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentMngtMainModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentMngtMainModule.prototype = new EmdModule(1820, 810);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentMngtMainModule.prototype.loadComplete = function() {
    this.$("#mainGrid").flexigrid({
        module: this,
        url: '/oper/htldnew/selectHtldRentDetailList.do',
        dataType: 'json',
        colModel : [
                    {display:'구역', name:'boundNm',width:120, sortable:false,align:'center'},
                    {display:'입주기업', name:'entrpsNm',width:170, sortable:false,align:'center'},
                    {display:'임대차계약', name:'detailPdStr',width:170, sortable:false,align:'center'},
                    {display:'입주면적(㎡)', name:'rentArStr',width:135, sortable:false,align:'right'},
                    {display:'적용임대료', name:'applcRntfeeStr',width:130, sortable:false,align:'right'},
                    {display:'실적평가임대료', name:'aseRntfeeStr',width:90, sortable:false,align:'right'},
                    {display:'실적평가적용기간', name:'asePd',width:170, sortable:false,align:'center'},
                    {display:'구분', name:'paySeNm',width:45, sortable:false,align:'center'},
    				{display:'임대료', name:'rntfee',width:95, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'분납이자', name:'payinstIntr',width:80, sortable:false,align:'right', displayFormat: 'input-number'},
    				{display:'공급가액', name:'supAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'부가세', name:'vat',width:90, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'납부금액', name:'payAmt',width:100, sortable:false,align:'right', displayFormat: 'number'},
    				{display:'비고', name:'rm',width:170, sortable:false,align:'left', displayFormat: 'input'},
                    {display:'상태', name:'status',width:60, sortable:false,align:'center'},
                    {display:'출력', name:'prtYn',width:50, sortable:false,align:'center'}
                    ],
        showTableToggleBtn: false,
        height: 'auto',
        mergeRows: 'boundNm,entrpsNm,detailPdStr',
		preProcess: function(module, data) {
			module.$('#cofixIntrrate').val(data.cofixIntrrate);
			module.$('#btnArrrgNticIssue').hide();
			$.each(data.resultList, function() {
				module.initDataRow(this);
	     	});
	     	return data;
	   	}        
    });

    this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
    	module._currentRow = null;
    	module.setButtons();
    });
    
    // 임대상세목록의 row선택
    this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
    	module._currentRow = row;
    	module.setButtons();
    });
	
    // 해당셀에 더블클릭이 일어났을 때 
	this.$('#mainGrid')[0].dgrid.attachEvent('onRowDblClicked', function(rid, ind) {
		var module = this.p.module;
		var cid = this.getColumnId(ind);
		module.onMainGridSelectedRowCell(module._currentRow, rid, cid);
	});
    
	// 셀 편집이 이루어졌을 때
    this.$("#mainGrid").on('onCellEdited', function(event, module, row, rid, cid) {
    	module.onMainGrildCellEdited(row, rid, cid);
    });

    this.$('#histDt').val(EMD.util.getDate());
    
	this.$('#histDt').on('change', {module:this}, function(e) {
		var module=e.data.module;
		module.loadData();
	});
    
    this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentMngtMainModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch': //조회
			this.loadData();
			break;
		case 'btnIntrrateMngt': //분납이자율관리
			this.doExecuteDialog('intrrateMngtPopup', 'Cofix 이자율 관리', '/popup/showCofixIntrrateMngt.do', {});
			break;
		case 'btnAddRentContract': //계약등록
			EMD.util.create_window('gamHtldRentContract', '배후단지 임대계약', '/oper/htldnew/gamHtldRentCtrt.do', null, null, this);
       		break;
		case 'btnNticIssue':  //고지
			this.execNticIssue();
			break;
		case 'btnArrrgNticIssue':  //연체고지
			this.execArrrgNticIssue();
			break;
		case 'btnPrintNticIssue':  //고지서출력
			this.printNticIssue();
			break;
		case 'btnNticIssueHist':  //고지이력
			this.showNticIssueHist();
			break;
		case 'btnAddNticIssue':  //추가고지
			this.addNticIssue();
			break;
		case 'btnProcessNticIssue' : //수납처리
			this.processNticIssue();
			break;
		case 'btnDownloadNticIssue' : //산출내역서 다운로드
			this.downloadNticIssue();
			break;
	}
};

<%--
	팝업이 종료 될때 리턴 값이 호출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentMngtMainModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
		case 'bizAssessRegPopup':
		case 'bizAssessUpdatePopup':
		case 'areaAssessPopup':
		case 'addNticPopup':
		case 'intrrateMngtPopup':
			if (msg != 'cancel') {
				this.loadData();
			}
			break;
	}
};

<%--
	자식창이 종료 될때 호출 된다. EmdModule에서 호출 함.
		module : 자식창 모듈
		message : 자식창이 종료될 때 넘겨온 메시지
--%>
GamHtldRentMngtMainModule.prototype.closeChildWindow = function(module, message) {
};

<%--
	그리드의 row를 선택한 후 해당 cell을 더블클릭할 때 호출 된다. 
		row : 선택 row
		rid : 선택된 row id
		cid : 선택된 cell id
--%>
GamHtldRentMngtMainModule.prototype.onMainGridSelectedRowCell = function(row, rid, cid) {
	switch(cid) {
	case 'detailPdStr':
	case 'rentArStr':
		if(row.rntfeeSe == '0') {
			EMD.util.create_window('gamHtldRentContract', '배후단지 임대계약', '/oper/htldnew/gamHtldRentCtrt.do', null, {'searchRow' : row, 'histDt' : this.$('#histDt').val()}, this);
		} else if((row.rntfeeSe == '1') && (row.nticYn == 'N')) {
			this.doExecuteDialog('bizAssessUpdatePopup', '실적평가 정산', '/popup/showHtldRntfeeBizAssess.do', {}, {'searchRow' : row, 'histDt' : this.$('#histDt').val()} );
		} else if((row.rntfeeSe == '2') && (row.nticYn == 'N')) {
			this.doExecuteDialog('areaAssessPopup', '임대면적변경 정산', '/popup/showHtldAreaAssess.do', {}, {'searchRow' : row, 'mode' : 'U', 'histDt' : this.$('#histDt').val()} );
		} else if((row.rntfeeSe == '3') && (row.nticYn == 'N')) {
			this.doExecuteDialog('addNticPopup', '배후단지 추가 고지', '/popup/showHtldAddNtic.do', {}, {'searchRow' : row, 'mode' : 'U', 'histDt' : this.$('#histDt').val()} );
		} else if((row.rntfeeSe != '0') && (row.nticYn == 'Y')) {
			alert('이미 고지된 임대자료입니다.');
		}
		break;
	case 'aseRntfeeStr':
	case 'asePd':
		if((row.rntfeeSe == '0') && (row.nticYn == 'N')) {
			this.doExecuteDialog('bizAssessRegPopup', '실적평가 임대료', '/popup/showHtldBizAssess.do', {}, {'searchRow' : row, 'histDt' : this.$('#histDt').val()} );
		} else if((row.rntfeeSe == '1') && (row.nticYn == 'N')) {
			this.doExecuteDialog('bizAssessUpdatePopup', '실적평가 정산', '/popup/showHtldRntfeeBizAssess.do', {}, {'searchRow' : row, 'histDt' : this.$('#histDt').val()} );
		} else if(((row.rntfeeSe == '0') || (row.rntfeeSe == '1')) && (row.nticYn == 'Y')) {
			alert('이미 고지된 임대자료입니다.');
		}
		break;
	}
};


<%--
	그리드의 해당 row의 cell 편집이 끝났을 때 호출 된다. EmdModule의 onCellEdited에서 호출 함.
		row : 선택 row
		rid : 선택된 row id
		cid : 선택된 cell id
--%>
GamHtldRentMngtMainModule.prototype.onMainGrildCellEdited = function (row, rid, cid) {
	if(row.nticYn == 'Y') {
		alert('고지된 데이터는 수정할 수 없습니다.');
		row.rntfee = row.oldRntfee;
		row.payinstIntr = row.oldPayinstIntr;
		row.supAmt = row.oldSupAmt;
		row.vat = row.oldVat;
		row.payAmt = row.oldPayAmt;
		row.rm = row.oldRm;
		this.$('#mainGrid').flexUpdateRow(rid, row);
		return;
	} 
	
	if(row.rntfeeSe == '9') {
		alert('소계는 수정할 수 없습니다.');
		row.rntfee = row.oldRntfee;
		row.payinstIntr = row.oldPayinstIntr;
		row.supAmt = row.oldSupAmt;
		row.vat = row.oldVat;
		row.payAmt = row.oldPayAmt;
		row.rm = row.oldRm;
		this.$('#mainGrid').flexUpdateRow(rid, row);
		return;
	}
	
	if((row.mngGroupCount > 1) && ((cid == 'supAmt') || (cid == 'vat') || (cid == 'payAmt'))) {
		alert('소계가 있는 항목의 공급가액, 부가세, 납부금액은 입력을 할 수 없습니다.');
		row.supAmt = row.oldSupAmt;
		row.vat = row.oldVat;
		row.payAmt = row.oldPayAmt;
		this.$('#mainGrid').flexUpdateRow(rid, row);
		return;
	}
	
	var calcTot = false;
	switch(cid) {
		case 'rntfee':
		case 'payinstIntr':
			if(row.mngGroupCount > 1) {
				calcTot = true;
			} else {
				var supAmt = Number(row.rntfee) + Number(row.payinstIntr);
				row.supAmt = Math.floor(supAmt*0.1) * 10;
				row.vat = Math.floor(Number(row.supAmt) * 0.1);
				row.payAmt = Number(row.supAmt) + Number(row.vat);
			}
			break;
		case 'supAmt':
			if(row.mngGroupCount <= 1) {
				row.supAmt = Math.floor(Number(row.supAmt)*0.1) * 10;
				row.vat = Math.floor(Number(row.supAmt) * 0.1);
				row.payAmt = Number(row.supAmt) + Number(row.vat);
			} else {
				row.supAmt = row.oldSupAmt;
			}
			break;
		case 'vat':
			if(row.mngGroupCount <= 1) {
				row.payAmt = Number(row.supAmt) + Number(row.vat);
			} else {
				row.vat = row.oldVat;
			}
			break;
		case 'payAmt':
			if(row.mngGroupCount > 1) {
				row.payAmt = row.oldPayAmt;
			}
			break;
	}
	
	row.oldRntfee = row.rntfee;
	row.oldPayinstIntr = row.payinstIntr;
	row.oldSupAmt = row.supAmt;
	row.oldVat = row.vat;
	row.oldPayAmt = row.payAmt;

	this.$('#mainGrid').flexUpdateRow(rid, row);
	
	if(calcTot) {
		this.calcMngGroupData(row.mngYear, row.mngNo, row.mngSeq);
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 임대상세목록 조회
--%>
GamHtldRentMngtMainModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#gamHtldRentListSearchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%--
	initDataRow - 각 row 데이터 초기화
	params :
		row - 그리드 row
--%>
GamHtldRentMngtMainModule.prototype.initDataRow = function(row) {
	row.oldRntfee = row.rntfee;
	row.oldPayinstIntr = row.payinstIntr;
	row.oldSupAmt = row.supAmt;
	row.oldVat = row.vat;
	row.oldPayAmt = row.payAmt;
	row.oldRm = (row.rm != void(0)) ? row.rm : '';
	if(row.rntfeeSe == '0') { //일반임대료 데이터라면
		if(row.rntfeeSeq == void(0)) { //임대료순번이 없을 경우.
			row._updtId = 'I';
		} else {
			row._updtId = 'U';
		}
	} else if (row.rntfeeSe != '9') { //소계가 아니라면 (즉 실적평가, 지적평가, 추가정산)
		row._updtId = 'U';
	}
	
	if (row.rntfeeSe != '9') {
		if(row.nticYn != 'Y') {
			row.status = '미고지';
		} else {
			switch (row.rcivSe) {
			case '2' : row.status = '연체수납'; break;
			case '3' : row.status = '수납'; break;
			case '4' : row.status = '불납'; break;
			default : 
				if(row.payTmlmtYn != 'Y') { //현재 디비시스템 시간이 납부기한을 넘기지 않았을 경우
					row.status = (row.rcivSe == '1') ? '연체고지' : '고지';
					if(row.rcivSe == '1') {
						row.prtYn = (row.dlyBillPrtYn == 'Y') ? '출력' : ''; 
					} else {
						row.prtYn = (row.nhtPrtYn == 'Y') ? '출력' : '';
					}
				} else  {
					row.status = '연체';
					row.arrrgYn = 'Y';
				}
			}	
		}
	}
};

<%-- 
	setButtons - row의 상태에 따라 버튼을 설정한다.
--%>
GamHtldRentMngtMainModule.prototype.setButtons = function() {
	if(this._currentRow != void(0)) {
		if(this._currentRow.nticYn != 'Y') { //고지상태가 아니면
			this.$('#btnNticIssue').show();
			this.$('#btnArrrgNticIssue').hide();
			this.$('#btnPrintNticIssue').hide();
			this.$('#btnDownloadNticIssue').hide();
			this.$('#btnAddNticIssue').show();
			this.$('#btnProcessNticIssue').hide();
		} else {
			this.$('#btnNticIssue').hide();
	    	if(this._currentRow.arrrgYn == 'Y') {
	    		this.$('#btnArrrgNticIssue').show();
	    	} else {
	    		this.$('#btnArrrgNticIssue').hide();
	    	}
			this.$('#btnPrintNticIssue').show();
			this.$('#btnDownloadNticIssue').show();
			this.$('#btnAddNticIssue').hide();
			this.$('#btnProcessNticIssue').show();
		}
	} else {
		this.$('#btnArrrgNticIssue').hide();
		this.$('#btnPrintNticIssue').hide();
		this.$('#btnDownloadNticIssue').hide();
		this.$('#btnProcessNticIssue').hide();
		this.$('#btnNticIssue').show();
		this.$('#btnAddNticIssue').show();
	}
};

<%-- 
	calcMngGroupData - 소계필드를 계산한다.
--%>
GamHtldRentMngtMainModule.prototype.calcMngGroupData = function(mngYear, mngNo, mngSeq) {
	var rows = this.$('#mainGrid').flexGetData();
	var totRow = null;
	var sumRntfee = 0;
	var sumPayinstIntr = 0;
	
	for(var i=0; i<rows.length; i++) {
		var row = rows[i];
		if((row.mngYear == mngYear) && (row.mngNo == mngNo) && (row.mngSeq == mngSeq) && (row.rntfeeSe == '9')) {
			totRow = row;
		} else if((row.mngYear == mngYear) && (row.mngNo == mngNo) && (row.mngSeq == mngSeq) && (row.rntfeeSe != '9')) {
			sumRntfee += Number(row.rntfee);
			sumPayinstIntr += Number(row.payinstIntr);
		}
	}
	
	totRow.rntfee = sumRntfee;
	totRow.payinstIntr = sumPayinstIntr;
	var supAmt = sumRntfee + sumPayinstIntr;
	totRow.supAmt = Math.floor(supAmt*0.1) * 10;
	totRow.vat = Math.floor(Number(totRow.supAmt) * 0.1);
	totRow.payAmt = Number(totRow.supAmt) + Number(totRow.vat);

	totRow.oldRntfee = totRow.rntfee;
	totRow.oldPayinstIntr = totRow.payinstIntr;
	totRow.oldSupAmt = totRow.supAmt;
	totRow.oldVat = totRow.vat;
	totRow.oldPayAmt = totRow.payAmt;
	
	this.$('#mainGrid').flexEmptyData();
	var dataList = {resultList: rows};
	this.$('#mainGrid').flexAddData(dataList);
};

<%--
	addNticIssue - 추가고지
--%>
GamHtldRentMngtMainModule.prototype.addNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 입주기업을 선택하세요.');
		return;
	}
	this.doExecuteDialog('addNticPopup', '배후단지 추가 고지', '/popup/showHtldAddNtic.do', {}, {'searchRow' : this._currentRow, 'mode' : 'I', 'histDt' : this.$('#histDt').val()} );
};

<%--
	execNticIssue - 고지
--%>
GamHtldRentMngtMainModule.prototype.execNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 데이터를 선택하세요.');
		return;
	}
	if(this._currentRow.nticYn == 'Y') {
		alert('이미 고지된 건입니다.');
		return;
	}
	var gridList = this.$('#mainGrid').flexGetData();
	var feeUpdateList = [];
	var feeInsertList = [];
	for(var i=0; i<gridList.length; i++) {
		var gridRow = gridList[i];
		if((gridRow.mngYear == this._currentRow.mngYear) && (gridRow.mngNo == this._currentRow.mngNo) && (gridRow.mngSeq == this._currentRow.mngSeq) && (gridRow.rntfeeSe != '9')) {
			if(gridRow._updtId == 'I') {
				if(gridRow.detailPdBegin > gridRow.nticBeginDt) {
					gridRow.nticBeginDt = gridRow.detailPdBegin;
				} 
				if(gridRow.detailPdEnd < gridRow.nticEndDt) {
					gridRow.nticEndDt = gridRow.detailPdEnd;
				} 
				feeInsertList[feeInsertList.length] = gridRow;	
			} else {
				feeUpdateList[feeUpdateList.length] = gridRow;
			}
		}
	}

	var rentFeeData = {};
	rentFeeData['feeInsertList'] 			= JSON.stringify(feeInsertList);
	rentFeeData['feeUpdateList'] 			= JSON.stringify(feeUpdateList);
	
	this.doAction('/oper/htldnew/updateHtldRntfee.do', rentFeeData, function(module, result) {
		if(result.resultCode == 0) {
			module.loadData();
			EMD.util.create_window('gamHtldRentNticIssue', '배후단지 고지', '/oper/htldnew/gamHtldRentNticIssue.do', null, 
					{'searchRow' : module._currentRow, 'histDt' : module.$('#histDt').val(), 'intrRate' : module.$('#cofixIntrrate').val()}, module);
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	execArrrgNticIssue - 연체고지
--%>
GamHtldRentMngtMainModule.prototype.execArrrgNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 입주기업을 선택하세요.');
		return;
	}
	
	if(this._currentRow.arrrgYn != 'Y') {
		alert('상태가 연체인 것만 연체고지를 할 수가 있습니다.');
		return;		
	} 
	
	EMD.util.create_window('gamHtldRentArrrgNticIssue', '배후단지 연체고지', '/oper/htldnew/gamHtldRentArrrgNticIssue.do', null, 
			{'searchRow' : this._currentRow, 'histDt' : this.$('#histDt').val()}, this);
};

<%--
	showNticIssueHist - 고지이력
--%>
GamHtldRentMngtMainModule.prototype.showNticIssueHist = function() {
	var rows = this.$('#mainGrid').selectedRows();
	var params;
	if(rows.length < 1) {
		params = null;
	} else {
		var row = rows[0];
		params = {'entrpsCd' : row.entrpsCd };
	}
	EMD.util.create_window('gamHtldRentNticHist', '배후단지 고지이력', '/oper/htldnew/gamHtldRentNticHist.do', null, params, this);
};

<%--
	processNticIsssue - 수납처리
--%>
GamHtldRentMngtMainModule.prototype.processNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 입주기업을 선택하세요.');
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
	printNticIsssue - 고지서 출력
--%>
GamHtldRentMngtMainModule.prototype.printNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 입주기업을 선택하세요.');
		return;
	}
	if(this._currentRow.nticYn != 'Y') {
		alert('미고지 데이터는 출력할 수 없습니다.');
		return;
	}
	this.printPayNticIssue('/oper/htldnew/printNticIssue.do', this._currentRow);
};

GamHtldRentMngtMainModule.prototype.printPayNticIssue = function(url, params, retfunc) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("id", "__printPayNoticeForm");
	var mngYear = document.createElement("input");
	var mngNo = document.createElement("input");
	var mngSeq = document.createElement("input");
	var histSeq = document.createElement("input");
	var accnutYear = document.createElement("input");
	var rntfeeNticNo = document.createElement("input");
	var nticSeq = document.createElement("input");
	var dlySerNo = document.createElement("input");
	$(mngYear).attr("name", "mngYear");
	$(mngYear).val(params.mngYear);
	$(mngNo).attr("name", "mngNo");
	$(mngNo).val(params.mngNo);
	$(mngSeq).attr("name", "mngSeq");
	$(mngSeq).val(params.mngSeq);
	$(histSeq).attr("name", "histSeq");
	$(histSeq).val(params.histSeq);
	$(accnutYear).attr("name", "accnutYear");
	$(accnutYear).val(params.accnutYear);
	$(rntfeeNticNo).attr("name", "rntfeeNticNo");
	$(rntfeeNticNo).val(params.rntfeeNticNo);
	$(nticSeq).attr("name", "nticSeq");
	$(nticSeq).val(params.nticSeq);
	$(dlySerNo).attr("name", "dlySerNo");
	$(dlySerNo).val(params.dlySerNo);
	$(form).append(mngYear);
	$(form).append(mngNo);
	$(form).append(mngSeq);
	$(form).append(histSeq);
	$(form).append(accnutYear);
	$(form).append(rntfeeNticNo);
	$(form).append(nticSeq);
	$(form).append(dlySerNo);
	$(form).attr("action", EMD.context_root+url);
	$('#__tempDiv').append(form);
	
	var win = window.open("","payNotice","width=800, height=600, menubar=no,status=no,scrollbars=yes");
	
	var module=this;
	
	win[win.addEventListener ? 'addEventListener' : 'attachEvent'](
			  (win.attachEvent ? 'on' : '') + 'load', function(e){
				  console.log('debug listener');
					retfunc(module, 'done');
				}, false
			);
	form.target = "payNotice";
	form.submit();
};

<%--
	downloadNticIsssue - 산출내역서 다운로드
--%>
GamHtldRentMngtMainModule.prototype.downloadNticIssue = function() {
	if(this._currentRow == void(0)) {
		alert('목록에서 입주기업을 선택하세요.');
		return;
	}
	if(this._currentRow.nticYn != 'Y') {
		alert('미고지 데이터는 출력할 수 없습니다.');
		return;
	}
	var url='/oper/htldnew/downloadXlsNticIssueReport.do';
	var param=[];
	param[param.length] = {name:'searchVO', value:JSON.stringify(this._currentRow)};
	param[param.length] = {name:'fileName', value:'산출내역서.xls'};
	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
};

<%--
	다음 변수는 고정 적으로 정의 해야 함
	module_instance는 고정 변수 GamHtldRentMngtMainModule은 위에서 EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentMngtMainModule();

</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
    <div id="searchViewStack" class="emdPanel">
        <div class="viewPanel">
            <form id="gamHtldRentListSearchForm">
                <table style="width:100%;" class="searchPanel">
                    <tbody>
                        <tr>
                            <th width="4%">고지(예정)일자</th>
                            <td width="8%"><input id="histDt" type="text" class="emdcal" size="8"></td>
                            <th width="4%">납부구분</th>
                            <td width="8%">
                            	<!-- <input id="sPaySe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM008" value=""/> -->
                            	<select id="paySe">
                            		<option value="">전체</option>
                            		<option value="4">분기납</option>
                            		<option value="6">연납</option>
                            	</select>
                            </td>
                            <th width="4%">계약해지유무</th>
                            <td width="8%">
                            	<select id="termnYn">
                            		<option value="Y">무</option>
                            		<option value="N">유</option>
                            		<option value="">전체</option>
                            	</select>	
                            </td>
                            <th width="4%">분납이자율</th>
                            <td width="8%"><input type="text" size="6" class="ygpaNumber" data-decimal-point="2" id="cofixIntrrate" disabled/>&nbsp; %</td>
                            <td width="7%"><button id="btnIntrrateMngt">분납이자율관리</button></td>
                            <td width="35%"></td>
                            <td width="10%"><button id="btnSearch" class="buttonSearch">조회</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

	<div class="emdPanel fillHeight">
	<table id="mainGrid" style="display:none" class="fillHeight"></table>
 		<div class="emdControlPanel">
			<table style="width:100%;">
				<tr>
					<td style="text-align: right">
                       <button id="btnAddRentContract">계약등록</button>
                       <button id="btnNticIssue">고지</button>
                       <button id="btnArrrgNticIssue">연체고지</button>
                       <button id="btnPrintNticIssue" >고지서출력</button>
                       <button id="btnDownloadNticIssue" >산출내역서 다운로드</button>
                       <button id="btnAddNticIssue">추가고지</button>
                       <button id="btnProcessNticIssue" >수납처리</button>
                       <button id="btnNticIssueHist" >고지이력</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>