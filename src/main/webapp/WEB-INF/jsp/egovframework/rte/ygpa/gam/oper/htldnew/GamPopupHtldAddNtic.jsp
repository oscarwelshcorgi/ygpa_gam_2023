<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldAddNtic.jsp
  * @Description : 추가정산고지
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
function GamPopupHtldAddNticModule() {}

<%--
	EmdPopupModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamPopupHtldAddNticModule.prototype = new EmdPopupModule(350, 250);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdPopupModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamPopupHtldAddNticModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	this._mode = params.mode;
	this.$('#mngYear').val(params.searchRow.mngYear);
	this.$('#mngNo').val(params.searchRow.mngNo);
	this.$('#mngSeq').val(params.searchRow.mngSeq);
	this.$('#histDt').val(params.histDt);
	this._histDt = params.histDt;
	if(this._mode == 'U') {
		this.$('#rntfeeSeq').val(params.searchRow.rntfeeSeq);
	} else {
		this.$('#paySe').val(params.searchRow.paySe);
	}
	this.loadData();
	this._searchRow = params.searchRow;
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamPopupHtldAddNticModule.prototype.onButtonClick = function(buttonId) {
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
GamPopupHtldAddNticModule.prototype.loadData = function() {
	var searchData = this.makeFormArgs('#gamPopupHtldAddNticForm');
	var actionUrl = (this._mode == 'I') ? '/oper/htldnew/selectHtldAddNticCtrtDetail.do' : '/oper/htldnew/selectHtldAddNticDetail.do';
	this.doAction(actionUrl, searchData, function(module, result) {
		module.makeFormValues('#gamPopupHtldAddNticForm', result.resultDetail);
		module.$('#histDt').val(module._histDt);
		module.initControls();
	});
};

<%--
	initControls - 입력 컨트롤 및 버튼 설정
--%>
GamPopupHtldAddNticModule.prototype.initControls = function() {
	if(this._mode == 'I') {
		this.$('#btnDelete').hide();
	} else {
		this.$('#btnDelete').show();
	}
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamPopupHtldAddNticModule.prototype.validateData = function() {
	if(this.$('#rntfeeSeNm').val() == '') {
		alert('고지내역을 입력하세요.');
		return false;
	}
	if(this.$('#rntfee').val() == '') {
		alert('임대료를 입력하세요.');
		return false;
	}
	return true;
};

<%--
	saveData - 추가정산 고지 저장
--%>
GamPopupHtldAddNticModule.prototype.saveData = function() {	
	if(!confirm("저장하시겠습니까?")) return;
	if(!this.validateData()) return;
	var params = this.makeFormArgs('#gamPopupHtldAddNticForm');
	var actionUrl = (this._mode == 'I') ? '/oper/htldnew/insertAddNtic.do' : '/oper/htldnew/updateAddNtic.do'; 
	this.doAction(actionUrl , params, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

<%--
	deleteData - 추가정산 고지 삭제
--%>
GamPopupHtldAddNticModule.prototype.deleteData = function() {	
	if(!confirm("삭제하시겠습니까?")) return;
	if(!this.validateData()) return;
	var params = this.makeFormArgs('#gamPopupHtldAddNticForm');
	this.doAction('/oper/htldnew/deleteAddNtic.do', params, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldAddNticModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupHtldAddNticForm">
			<input type="hidden" id="mngYear" />
			<input type="hidden" id="mngNo" />
			<input type="hidden" id="mngSeq" />
			<input type="hidden" id="rntfeeSeq"/>
			<input type="hidden" id="histDt"/>
			<input type="hidden" id="paySe"/>
        	<table class="editForm" style="width:100%">
				<tr>
					<th width="25%" height="18">고지 대상 업체</th>
					<td>
						<input type="text" size="30" id="entrpsNm" disabled />
					</td>
				</tr>
				<tr>
					<th width="25%" height="18">사업자등록번호</th>
					<td>
						<input type="text" size="30" id="bizrno" disabled />
					</td>
				</tr>
				<tr>
					<th width="25%" height="18">대표자</th>
					<td>
						<input type="text" size="30" id="rprsntvNm" disabled />
					</td>
				</tr>
				<tr>
					<th width="25%" height="18">고지내역</th>
					<td>
						<input type="text" size="30" id="rntfeeSeNm" />
					</td>
				</tr>
				<tr>
					<th width="25%" height="18">고지금액</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" id="rntfee" />&nbsp; 원
					</td>
				</tr>
				<tr>
					<th width="25%" height="18">비고</th>
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
						<button id="btnSave">추가</button>
						<button id="btnDelete">삭제</button>
						<button id="btnCancel">취소</button>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
</div>