<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldAreaAssess.jsp
  * @Description : 지적평가정산
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
function GamPopupHtldAreaAssessModule() {}

<%--
	EmdPopupModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamPopupHtldAreaAssessModule.prototype = new EmdPopupModule(700, 150);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdPopupModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamPopupHtldAreaAssessModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	this._mode = params.mode;
	this.$('#mngYear').val(params.searchRow.mngYear);
	this.$('#mngNo').val(params.searchRow.mngNo);
	this.$('#mngSeq').val(params.searchRow.mngSeq);
	this.$('#histDt').val(params.histDt);
	this._histDt = params.histDt;
	if(this._mode == 'U') {
		this.$('#rntfeeSeq').val(params.searchRow.rntfeeSeq);
		this.loadData();
	} else {
		this.$('#priceSe').val(params.searchRow.priceSe);
		this.$('#applcRentAr').val(params.searchRow.rentAr - params.searchRow.oldRentAr);
		this.$('#applcRntfee').val(params.searchRow.applcRntfee);
		this.$('#rentDetailRegistSeq').val(params.searchRow.registSeq);
		this.initControls();
	}
	this._searchRow = params.searchRow;
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamPopupHtldAreaAssessModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSave': //저장
		this.saveData();
		break;
	case 'btnDelete': //삭제
		this.deleteData();
		break;
	case 'btnCancel': //취소
		this.cancelDialog();
		break;
	}
};

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	사용자 함수 정의 시작
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--
	loadData - 지적평가 정산 조회
--%>
GamPopupHtldAreaAssessModule.prototype.loadData = function() {
	var searchData = this.makeFormArgs('#gamPopupHtldAreaAssessForm');
	this.doAction('/oper/htldnew/selectHtldAreaAssessDetail.do', searchData, function(module, result) {
		alert('bbb');
		module.makeFormValues('#gamPopupHtldAreaAssessForm', result.resultDetail);
		module.$('#histDt').val(module._histDt);
		module.initControls();
	});
};

<%--
	initControls - 입력 컨트롤 및 버튼 설정
--%>
GamPopupHtldAreaAssessModule.prototype.initControls = function() {
	if(this._mode == 'I') {
		this.$('#btnDelete').hide();
	} else {
		this.$('#btnDelete').show();
	}
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamPopupHtldAreaAssessModule.prototype.validateData = function() {
	if(this.$('#applcRentAr').val() == '') {
		alert('변경면적을 입력하세요.');
		return false;
	}
	if(this.$('#applcRntfee').val() == '') {
		alert('적용임대료를 입력하세요.');
		return false;
	}
	if(this.$('#applcBeginDt').val() == '') {
		alert('면적변경적용기간(시작일)을 선택하세요.');
		return false;
	}
	if(this.$('#applcEndDt').val() == '') {
		alert('면적변경적용기간(종료일)을 선택하세요.');
		return false;
	}
	if(this.$('#applcBeginDt').val() > this.$('#applcEndDt').val()) {
		alert('면적변경적용기간(시작일)이 면적변경적용기간(종료일)보다 큽니다.');
		return false;
	}
	return true;
};

<%--
	saveData - 지적평가 정산 수정
--%>
GamPopupHtldAreaAssessModule.prototype.saveData = function() {	
	if(!confirm("저장하시겠습니까?")) return;
	if(!this.validateData()) return;
	var params = this.makeFormArgs('#gamPopupHtldAreaAssessForm');
	var actionUrl = (this._mode == 'I') ? '/oper/htldnew/insertAreaAssess.do' : '/oper/htldnew/updateAreaAssess.do'; 
	this.doAction(actionUrl , params, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

<%--
	deleteData - 지적평가 정산 삭제
--%>
GamPopupHtldAreaAssessModule.prototype.deleteData = function() {	
	if(!confirm("삭제하시겠습니까?")) return;
	if(!this.validateData()) return;
	var params = this.makeFormArgs('#gamPopupHtldAreaAssessForm');
	this.doAction('/oper/htldnew/deleteAreaAssess.do', params, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldAreaAssessModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupHtldAreaAssessForm">
			<input type="hidden" id="mngYear" />
			<input type="hidden" id="mngNo" />
			<input type="hidden" id="mngSeq" />
			<input type="hidden" id="rntfeeSeq"/>
			<input type="hidden" id="rentDetailRegistSeq"/>
			<input type="hidden" id="priceSe"/>
			<input type="hidden" id="histDt"/>
        	<table class="editForm" style="width:100%">
				<tr>
					<th width="10%" height="18">변경면적</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="applcRentAr"/>&nbsp; m<sup>2</sup>
					</td>
					<th width="10%" height="18">적용임대료</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="applcRntfee" />&nbsp; 원
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">면적변경적용기간</th>
					<td>
						<input type="text" size="12" id="applcBeginDt" class="emdcal"/>~
						<input type="text" size="12" id="applcEndDt" class="emdcal"/>
					</td>									
					<th width="10%" height="18">변경사유</th>
					<td>
						<input type="text" size="30" id="rm" />
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
						<button id="btnCancel">취소</button>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
</div>