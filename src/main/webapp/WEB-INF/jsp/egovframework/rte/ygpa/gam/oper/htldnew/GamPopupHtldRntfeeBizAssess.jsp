<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldRntfeeBizAssess.jsp
  * @Description : 실적평가정산 수정 및 삭제
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
function GamPopupHtldRntfeeBizAssessModule() {}

<%--
	EmdPopupModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamPopupHtldRntfeeBizAssessModule.prototype = new EmdPopupModule(700, 180);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdPopupModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	this.$('#mngYear').val(params.searchRow.mngYear);
	this.$('#mngNo').val(params.searchRow.mngNo);
	this.$('#mngSeq').val(params.searchRow.mngSeq);
	this.$('#rntfeeSeq').val(params.searchRow.rntfeeSeq);
	this._searchRow = params.searchRow;
	this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSave': //저장
		this.saveData();
		break;
	case 'btnDelete': //삭제
		this.deleteData();
		break;
	case 'btnCancel': //닫기
		this.cancelDialog();
		break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 실적평가 정산 조회
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.loadData = function() {
	var searchData = this.makeFormArgs('#gamPopupHtldRntfeeBizAssessForm');
	this.doAction('/oper/htldnew/selectHtldRntfeeBizAssessDetail.do', searchData, function(module, result) {
		module.makeFormValues('#gamPopupHtldRntfeeBizAssessForm', result.resultDetail);
		module.initControls();
	});
};

<%--
	initControls - 입력 컨트롤 및 버튼 설정
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.initControls = function() {
	if((this.$('#termnYn').val() == 'N') && (this.$('#nticYn').val() == 'N')) {
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnDelete').enable();
		this.$('#btnDelete').removeClass('ui-state-disabled');
		this.$('#applcRntfee').enable();
		this.$('#applcBeginDt').enable();
		this.$('#applcEndDt').enable();
	} else {
		this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		this.$('#applcRntfee').disable();
		this.$('#applcBeginDt').disable();
		this.$('#applcEndDt').disable();
		if(this.$('#termnYn').val() == 'N') {
			alert('해지된 계약의 실적평가이기에 실적평가 정산 수정 및 삭제가 불가능합니다.');
		}
		if(this.$('#termnYn').val() == 'N') {
			alert('고지된 실적평가이기에 실적평가 정산 수정 및 삭제가 불가능합니다.');
		}
	}
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.validateData = function() {
	if(this.$('#applcRntfee').val() == '') {
		alert('실적평가임대료를 입력하세요.');
		return false;
	}
	if(this.$('#applcBeginDt').val() == '') {
		alert('실적평가기간(시작일)을 선택하세요.');
		return false;
	}
	if(this.$('#applcEndDt').val() == '') {
		alert('실적평가기간(종료일)을 선택하세요.');
		return false;
	}
	if(this.$('#applcBeginDt').val() > this.$('#applcEndDt').val()) {
		alert('실적평가기간(시작일)이 실적평가기간(종료일)보다 큽니다.');
		return false;
	}
	return true;
};

<%--
	saveData - 실적평가 정산 수정
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.saveData = function() {	
	if(!confirm("수정하시겠습니까?")) return;
	if(!this.validateData()) return;
	var saveData = this.makeFormArgs('#gamPopupHtldRntfeeBizAssessForm');
	this.doAction('/oper/htldnew/updateRntfeeBizAssess.do', saveData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

<%--
	deleteData - 실적평가 정산 삭제
--%>
GamPopupHtldRntfeeBizAssessModule.prototype.deleteData = function() {	
	if(!confirm("삭제하시겠습니까?")) return;
	var deleteData = this.makeFormArgs('#gamPopupHtldRntfeeBizAssessForm');
	this.doAction('/oper/htldnew/deleteRntfeeBizAssess.do', deleteData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldRntfeeBizAssessModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupHtldRntfeeBizAssessForm">
			<input type="hidden" id="mngYear" />
			<input type="hidden" id="mngNo" />
			<input type="hidden" id="mngSeq" />
			<input type="hidden" id="rntfeeSeq"/>
			<input type="hidden" id="rentDetailRegistSeq"/>
			<input type="hidden" id="priceSe"/>
			<input type="hidden" id="termnYn">
			<input type="hidden" id="nticYn">
        	<table class="editForm" style="width:100%">
        		<tr>
					<th width="10%" height="18">입주기업</th>
					<td>
						<input type="text" size="30" id="entrpsNm" disabled/>
					</td>
					<th width="10%" height="18">임대구역</th>
					<td>
						<input type="text" size="30" id="boundNm" disabled/>
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">임대면적</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="rentAr" disabled/>&nbsp; m<sup>2</sup>
					</td>
					<th width="10%" height="18">부지구분</th>
					<td>
						<input type="text" size="30" id="rentArSeNm" disabled/>
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">실적평가임대료</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="applcRntfee" />&nbsp; 원
					</td>
					<th width="10%" height="18">실적평가기간</th>
					<td>
						<input type="text" size="12" id="applcBeginDt" class="emdcal"/>~
						<input type="text" size="12" id="applcEndDt" class="emdcal"/>
					</td>									
				</tr>
			</table>
		</form>
		<table class="searchPanel fillHeight">
			<tbody>
				<tr>
					<th style="text-align:center">
						<button id="btnSave">저장</button>
						<button id="btnDelete">삭제</button>
						<button id="btnCancel">닫기</button>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
</div>