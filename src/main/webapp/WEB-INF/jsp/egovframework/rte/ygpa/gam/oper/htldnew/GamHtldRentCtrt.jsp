<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld"%>
<%--
  /**
  * @Class Name : GamHtldRentCtrt.jsp
  * @Description : 임대계약관련 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2016.4.25  Jongmin          최초 생성
  *
  * author Jongmin
  * since 2016.04.25
  *
  * Copyright (C) 2014 by LFIT  All right reserved.
  */
--%>
<script>
<%--
	아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
--%>
function GamHtldRentCtrtModule() {}

<%--
	EmdModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamHtldRentCtrtModule.prototype = new EmdModule(1400, 450);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	페이지가 호출 되었을때 호출 되는 함수
--%>
GamHtldRentCtrtModule.prototype.loadComplete = function(params) {
	
	var priceSeOption = [{value:'1', name:'면적당단가'}, {value:'2', name:'월단가'}];
 	var rentArSeOption = [{value:'0', name:'구분없음'}, {value:'1', name:'물류부지'}, {value:'2', name:'제조부지'}, {value:'3', name:'숙성실'}];
 	
	this.$("#rentDetailList").flexigrid({
		module: this,
		url: '/oper/htldnew/selectHtldRentCtrt.do',
		dataType: 'json',
		colModel : [
		            {display:'자산코드', name:'assetsCode',width:130, sortable:false,align:'center'},
		            {display:'자산명', name:'assetsNm',width:200, sortable:false,align:'center', displayFormat:'input'},
		            {display:'주소지', name:'assetsLocplcAll',width:350, sortable:false,align:'center'},
		            {display:'임대면적', name:'rentAr',width:150, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.00"},
		            {display:'부지구분', name:'rentArSe',width:80, sortable:false,align:'center', displayFormat:'select', displayOption:rentArSeOption},
		            {display:'적용단가', name:'applcRntfee',width:150, sortable:false,align:'right', displayFormat: 'input-number', displayOption:"0,000.00"},
		            {display:'단가구분', name:'priceSe',width:100, sortable:false,align:'center', displayFormat:'select', displayOption:priceSeOption},
		            {display:'임대시작일', name:'detailPdBegin',width:100, sortable:false,align:'center', displayFormat:'cal'},
		            {display:'임대종료일', name:'detailPdEnd',width:100, sortable:false,align:'center', displayFormat:'cal'}
		            ],
		showTableToggleBtn: true,
		height: '230',
		preProcess: function(module, data) {
			module._mode = 'U';
			module.makeFormValues('#gamHtldRentCtrtForm', data.resultMaster);
			module.$('#histDt').val(module._histDt);
			module._deleteRentDetailList = [];
			$.each(data.resultList, function() {
				module.initDataRow(this);
	     	});
	     	return data;
	   	}
 	});
	
	this.$("#rentDetailList").on('onLoadDataComplete', function(event, module, data) {
		module.setButtons();
	});

    this.$("#rentDetailList").on('onItemSelected', function(event, module, row, grid, param) {
    	module._currentRow = row;
    });

    this.$("#rentDetailList").on('onCellEdited', function(event, module, row, rid, cid) {
		if(row != void(0)) {
			switch(cid) {
			case 'rentAr':
	    		if(row._updtId != 'I') {
	    			row._updtId = 'U';
	    		}
				module.onCalcGrRentAr();
				break;
			case 'applcRntfee':
	    		if(row._updtId != 'I') {
	    			row._updtId = 'U';
	    		}
				break;
			}
		}
	});
	
	this.$('#rentDetailList')[0].dgrid.attachEvent('onCellChanged', function(rid, cind, value) {
		// onCellEdited에서 이벤트가 안먹는 select,date 타입의 입력방식에 적용, rid로 데이터애 접근하려고 하니  this.rowsBuffer[rid]._attrs에 접근하지 못함)
		var module = this.p.module;
		if(module._currentRow != void(0)) {
	    	var cid = this.getColumnId(cind);
	    	switch(cid) {
	    	case 'rentArSe' :
	    	case 'priceSe' :
	    	case 'detailPdBegin':
	    	case 'detailPdEnd':
	    		if(module._currentRow._updtId != 'I') {
	    			module._currentRow._updtId = 'U';
	    		}
	    		break;
	    	}
		}
	});	
	
    this.$("#ctrtBeginDt").bind("change", {module: this}, function(event) {
    	event.data.module.$('#ctrtDt').val(event.data.module.$('#ctrtBeginDt').val());
    });

	this._deleteRentDetailList = [];
	
	if(params != null) {
		this._mode = 'U';
		this.$('#mngYear').val(params.searchRow.mngYear);
		this.$('#mngNo').val(params.searchRow.mngNo);
		this.$('#mngSeq').val(params.searchRow.mngSeq);
		this.$('#histDt').val(params.histDt);
		this._histDt = params.histDt;
		this.loadData();
	} else {
		this._mode = 'I';
		this.$('#grRentAr').val(0);
		this.$('#termnYn').val('N');
		this.setButtons();
	}
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamHtldRentCtrtModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnEntrpsInfoPopup':  //입주기업 선택
		this.doExecuteDialog('selectEntrpsInfoPopup', '입주기업 선택', '/popup/showEntrpsInfo.do', {});
		break;
	case 'btnAddCtrtDetail': //자산목록추가
		this.doExecuteDialog('selectAssetsCdPopup', '자산 선택', '/popup/showAssetsCd.do', {}, {"gisAssetsPrprtySeCd":"L"});
		break;
	case 'btnRemoveCtrtDetail': //자산목록삭제
		this.removeRentDetail();
		break;
	case 'btnCtrtTerm': //계약 해지
		this.doExecuteDialog('selectCtrtTermnPopup', '계약 해지', '/popup/showCtrtTermn.do', {});
		break;
	case 'btnCtrtSave': //저장
		this.saveData();
		break;
	case 'btnCancel': //취소
		this.closeWindow();
		break;
	}
};

<%--
	팝업이 종료 될때 리턴 값이 오출 된다.	EmdModule에서 호출 함.
		popupId : 팝업 대화상자 아이디
		msg : 팝업에서 전송한 메시지 (취소는 cancel)
		value : 팝업에서 선택한 데이터 (오브젝트) 선택이 없으면 0
--%>
GamHtldRentCtrtModule.prototype.onClosePopup = function(popupId, msg, value) {
	switch (popupId) {
	case 'selectEntrpsInfoPopup':
		if (msg != 'cancel') {
			this.$('#entrpsCd').val(value.entrpscd);
			this.$('#entrpsNm').val(value.entrpsNm);
			this.$('#bizrno').val(value.bizrno);
		}
		break;
	case 'selectAssetsCdPopup':
		if (msg != 'cancel') {
			this.addRentDetailRow(value);
		}
		break;
	case 'selectCtrtTermnPopup':
		if (msg != 'cancel') {
			this.terminateRent(value);
		}
		break;
	case 'areaAssessPopup' :
		if(this._changeRowsId < (this._rentArChangedRows.length-1)) {
			this._changeRowsId++;
			var row = this._rentArChangedRows[this._changeRowsId];
			this.doExecuteDialog('areaAssessPopup', '임대면적변경', '/popup/showHtldAreaAssess.do', {}, {'searchRow' : row, 'mode' : 'I', 'histDt' : this._histDt} );
		} else {
			this.closeWindow('SAVE_RENTCONTRACT');
		}
		break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 임대계약조회
--%>
GamHtldRentCtrtModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs('#gamHtldRentCtrtForm');
	this.$('#rentDetailList').flexOptions({params:searchOpt}).flexReload();
};

<%--
	initDataRow - 각 row 데이터 초기 설정
	params :
		row - 그리드 row
--%>
GamHtldRentCtrtModule.prototype.initDataRow = function(row) {
	row.assetsCode 					= row.gisAssetsCd + '-' + row.gisAssetsSubCd;
	row.assetsLocplcAll				= row.gisAssetsLocplc;
	if(row.gisAssetsLnm != void(0)) {
		row.assetsLocplcAll 			+= " " + row.gisAssetsLnm;
		if(row.gisAssetsLnmSub != void(0)) {
			row.assetsLocplcAll 		+= "-" + row.gisAssetsLnmSub;
		}
	}
	row.oldRentAr = row.rentAr;
	row._updtId = '';
};

<%--
	setButtons - 버튼 설정
--%>
GamHtldRentCtrtModule.prototype.setButtons = function() {
	if(this._mode == 'I') {
		this.$('#btnEntrpsInfoPopup').enable();
		this.$('#btnEntrpsInfoPopupl').removeClass('ui-state-disabled');
		this.$('#btnCtrtTerm').hide();
	} else {
		this.$('#btnEntrpsInfoPopup').disable({disableClass:"ui-state-disabled"});
		if(this.$('#termnYn').val() == 'N')  {
			this.$('#btnCtrtTerm').show();
			this.$('#btnAddCtrtDetail').enable();
			this.$('#btnAddCtrtDetail').removeClass('ui-state-disabled');
			this.$('#btnRemoveCtrtDetail').enable();
			this.$('#btnRemoveCtrtDetail').removeClass('ui-state-disabled');
			this.$('#btnCtrtSave').enable();
			this.$('#btnCtrtSave').removeClass('ui-state-disabled');
		} else {
			this.$('#btnCtrtTerm').hide();
			this.$('#btnAddCtrtDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemoveCtrtDetail').disable({disableClass:"ui-state-disabled"});
			this.$('#btnCtrtSave').disable({disableClass:"ui-state-disabled"});
			alert('해지된 계약이기에 계약해지 및 계약수정이 불가능합니다.');
		}
	}
};

<%--
	onCalcGrRentAr - 목록의 임대면적을 합산하여 총임대면적을 구함
--%>
GamHtldRentCtrtModule.prototype.onCalcGrRentAr = function() {
	var rows = this.$('#rentDetailList').flexGetData();
	var grRentAr = 0;
	for(var i=0; i<rows.length; i++) {
		if(rows[i].rentAr != void(0)) {
			grRentAr += Number(rows[i].rentAr);
		}
	}
	this.$('#grRentAr').val(grRentAr);
};

<%--
	addRentDetailRow - 임대계약상세목록 row 추가
	params : 
		value - 자산팝업에서 리턴받은 row 값
--%>
GamHtldRentCtrtModule.prototype.addRentDetailRow = function(value) {
	var newRow = {};
	newRow.mngYear					= this.$('#mngYear').val();
	newRow.mngNo						= this.$('#mngNo').val();
	newRow.mngSeq					= this.$('#mngSeq').val();
	newRow.registSeq					= '';
	newRow.gisAssetsPrtAtCode		= value.gisAssetsPrtAtCode;
	newRow.gisAssetsCd 				= value.gisAssetsCd;
	newRow.gisAssetsSubCd 			= value.gisAssetsSubCd;
	newRow.assetsCode 				= value.gisAssetsCd + '-' + value.gisAssetsSubCd;
	newRow.assetsNm 					= value.gisAssetsNm;
	newRow.assetsLocplcAll			= value.gisAssetsLocplc;
	if(value.gisAssetsLnm != void(0)) {
		newRow.assetsLocplcAll 		+= " " + value.gisAssetsLnm;
		if(value.gisAssetsLnmSub != void(0)) {
			newRow.assetsLocplcAll 	+= "-" + value.gisAssetsLnmSub;
		}
	}
	newRow.rentAr = 0;
	newRow.rentArSe = '0';
	newRow.applcRntfee = 0;
	newRow.priceSe = '1';
	newRow.detailPdBegin = this.$('#ctrtBeginDt').val();
	newRow.detailPdEnd = this.$('#ctrtEndDt').val();
	newRow._updtId="I";
	this.$('#rentDetailList').flexAddRow(newRow);	
};

<%--
	removeRentDetail - 임대계약상세목록 row 삭제
--%>
GamHtldRentCtrtModule.prototype.removeRentDetail = function() {
	var rowIds = this.$('#rentDetailList').selectedRowIds();
	if(rowIds.length < 1) {
		alert("임대상세목록에서 삭제할 행을 선택하십시오.");
		return;
	}
	for(var i=rowIds.length-1; i>=0; i--) {
	    var row = this.$('#rentDetailList').flexGetRow(rowIds[i]);
	    if(row._updtId==undefined || row._updtId!='I') {
	    	this._deleteRentDetailList[this._deleteRentDetailList.length] = row;
	    }
	    this.$('#rentDetailList').flexRemoveRow(rowIds[i]);
	}
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamHtldRentCtrtModule.prototype.validateData = function() {
	if(this.$('#boundCd').val() == '') {
		alert('임대구역을 선택하세요.');
		return false;
	}
	if(this.$('#entrpsCd').val() == '') {
		alert('입주기업을 선택하세요.');
		return false;
	}
	if(this.$('#ctrtBeginDt').val() == '') {
		alert('계약기간(시작일)을 선택하세요.');
		return false;
	}
	if(this.$('#ctrtEndDt').val() == '') {
		alert('계약기간(종료일)을 선택하세요.');
		return false;
	}
	if(this.$('#ctrtBeginDt').val() > this.$('#ctrtEndDt').val()) {
		alert('계약기간(시작일)이 계약기간(종료일)보다 큽니다.');
		return false;
	}	
	if(this.$('#paySe').val() == '') {
		alert('납부구분을 선택하세요.');
		return false;
	}
	if(this.$('#chrgeKndCd').val() == '') {
		alert('요금종류를 선택하세요.');
		return false;
	}
	
	var detailList = this.$('#rentDetailList').flexGetData();
	if(detailList.length <= 0) {
		alert('하나 이상의 임대상세를 추가하셔야 합니다.');
		return false;
	}
	
	for(var i=0; i<detailList.length; i++) {
		row = detailList[i];
		if(row.applcRntfee <= 0) {
			alert('임대상세의 적용단가가 누락되었습니다.');
			return false;
		}
		if((row.priceSe == '1') && (row.rentAr <= 0))  {
			alert('임대상세의 임대면적을 입력하세요.(단가구분이 면적당단가일 경우)');
			return false;
		}
		if(row.detailPdBegin == '') {
			alert('임대상세의 임대시작일을 선택하세요.');
			return false;
		}
		if(row.detailPdEnd == '') {
			alert('임대상세의 임대종료일을 선택하세요.');
			return false;
		}
		if(row.detailPdBegin > row.detailPdEnd) {
			alert('임대시작일이 임대종료일보다 큽니다.');
			return false;
		}
	}
	return true;
};

<%--
	saveData - 임대 계약 저장
--%>
GamHtldRentCtrtModule.prototype.saveData = function() {	
	if(!confirm("저장하시겠습니까?")) return;
	
	if(!this.validateData()) return;
	
	var constractData ={};
	
	constractData['rentData'] 				= JSON.stringify(this.getFormValues('#gamHtldRentCtrtForm'));
	constractData['rentDetailList'] 			= JSON.stringify(this.$('#rentDetailList').flexGetData());

	var actionUrl = (this._mode == 'I') ? '/oper/htldnew/insertHtldRentCtrt.do' : '/oper/htldnew/updateHtldRentCtrt.do';
	
	this.doAction(actionUrl, constractData, function(module, result) {
		if(result.resultCode == 0) {
			module._rentArChangedRows = module.getRentArChangedRows();
			if(module._rentArChangedRows.length > 0) {
				module._changeRowsId = 0;
				var row = module._rentArChangedRows[module._changeRowsId];
				module.doExecuteDialog('areaAssessPopup', '임대면적변경 정산', '/popup/showHtldAreaAssess.do', {}, {'searchRow' : row, 'mode' : 'I', 'histDt' : module._histDt} );
			} else {
				module.closeWindow('SAVE_RENTCONTRACT'); 
			}
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--
	getRentArChangedRows - 임대 면적이 변한 데이터 가져오기
--%>
GamHtldRentCtrtModule.prototype.getRentArChangedRows = function() {
	var ret = [];
	rows = this.$('#rentDetailList').selectFilterData([{col: '_updtId', filter: 'U'}]);
	for(var i=0; i<rows.length; i++) {
		var row = rows[i];
		if(row.rentAr != row.oldRentAr) {
			ret[ret.length] = row;
		}
	}
	return ret;
};

<%--
	terminateRent - 계약해지
	params : 
		value - 팝업에서 받은 계약해지일과 사유
--%>
GamHtldRentCtrtModule.prototype.terminateRent = function(value) {
	this.$('#termnDt').val(value.termnDt);
	this.$('#termnRsn').val(value.termnRsn);
	var terminateData = this.makeFormArgs('#gamHtldRentCtrtForm'); 
	this.doAction('/oper/htldnew/terminateHtldRentCtrt.do', terminateData, function(module, result) {
		if(result.resultCode == 0) {
			alert('정상적으로 계약해지가 되었습니다.');
			module.closeWindow('TERMINATE_RENTCONTRACT');
		} else {
			alert(result.resultMsg);
		}
	});
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 종료
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	다음 변수는 고정적으로 정의 해야 함
	module_instance는 고정 변수 :  EmdModule을 상속 받는 이 윈도우의 모듈 함수로 정의 됨.
--%>
var module_instance = new GamHtldRentCtrtModule();
</script>
<%--
	이 페이지를 로딩 하기 위한 window_id 변수. jsp를 호출 하는 model에 담겨서 와야 함.
--%>
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="gamHtldRentCtrtForm">
				<input type="hidden" id="mngYear" />
				<input type="hidden" id="mngNo" />
				<input type="hidden" id="mngSeq" />
				<input type="hidden" id="histSeq"/>
				<input type="hidden" id="histDt"/>
				
	        	<table class="editForm" style="width:100%">
	        		<tr>
						<th width="10%" height="18">구역</th>
						<td>
							<input size="10" id="boundCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM062" value=""/>
						</td>
						<th width="10%" height="18">입주기업</th>
						<td>
							<input type="hidden" id="entrpsCd">
							<input type="text" size="30" id="entrpsNm" disabled/>
							<button id="btnEntrpsInfoPopup" class="popupButton">업체선택</button>
						</td>
						<th width="10%" height="18">사업자등록번호</th>
						<td>
							<input type="text" size="20" id="bizrno" disabled/>
						</td>
	 				</tr>
					<tr>
						<th width="10%" height="18">계약기간</th>
						<td>
							<input type="text" size="12" id="ctrtBeginDt" class="emdcal"/>~
							<input type="text" size="12" id="ctrtEndDt" class="emdcal"/>
						</td>
						<th width="10%" height="18">계약일</th>
						<td>
							<input type="text" size="12" id="ctrtDt" class="emdcal"/>
						</td>					
						<th width="10%" height="18">계약해지일</th>
						<td>
							<input type="text" size="20" id="termnDt" disabled/>
							<input type="hidden" id="termnRsn" />
							<input type="hidden" id="termnYn" />
						</td>
					</tr>
					<tr>
						<th width="10%" height="18">납부구분</th>
						<td>
							<!-- <input id="paySe" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM008" value=""/> -->
							<select id="paySe">
                            	<option value="">선택</option>
                            	<option value="4">분기납</option>
                            	<option value="6">연납</option>
                            </select>
						</td>
						<th width="10%" height="18">요금종류</th>
						<td>
							<input id="chrgeKndCd" class="ygpaCmmnCd" data-default-prompt="선택" data-code-id="GAM053" value=""/>
						</td>
						<th width="10%" height="18">총임대면적</th>
						<td>
							<input type="text" size="12" class="ygpaNumber" data-decimal-point="2" id="grRentAr" disabled/>&nbsp; m<sup>2</sup>
						</td>
					</tr>
				</table>
			</form>
			<table  id="rentDetailList" style="display:none"></table>
			<table class="searchPanel fillHeight">
				<tbody>
					<tr>
						<th  style="text-align:right">
							<button id="btnAddCtrtDetail" class="buttonAdd">추가</button>
							<button id="btnRemoveCtrtDetail" class="buttonDelete">삭제</button>
						</th>
					</tr>
				</tbody>
			</table>
			<table style="width:100%">
				<tr>
					<td style="text-align:right">
						<button id="btnCtrtTerm">계약해지</button>
						<button id="btnCtrtSave" class="buttonSave">저장</button>
						<button id="btnCancel" class="buttonCancel">취소</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>