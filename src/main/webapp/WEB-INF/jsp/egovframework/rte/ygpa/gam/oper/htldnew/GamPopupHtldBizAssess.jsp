<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  /**
  * @Class Name : GamPopupHtldBizAssess.jsp
  * @Description : 실적평가등록
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
function GamPopupHtldBizAssessModule() {}

<%--
	EmdPopupModule을 상속하여 모듈 클래스를 정의한다.
--%>
GamPopupHtldBizAssessModule.prototype = new EmdPopupModule(700, 310);

<%--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	EmdPopupModule Override 및 이벤트 처리 정의 부분 시작	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
	팝업 페이지가 호출 되었을때 호출 되는 함수
--%>
GamPopupHtldBizAssessModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	this.$('#mngYear').val(params.searchRow.mngYear);
	this.$('#mngNo').val(params.searchRow.mngNo);
	this.$('#mngSeq').val(params.searchRow.mngSeq);
	this.$('#registSeq').val(params.searchRow.registSeq);
	this._searchRow = params.searchRow;
	this._histDt = params.histDt;
	this.$('#histDt').val(this._histDt);
	this.loadData();
};

<%--
	버튼 클릭에 대한 이벤트 핸들러 (EmdModule에서 Overriding 된 함수임 모듈에서 자동으로 호출 됨)
	스위치문 안에 코드가 길어지면 반드시 하위 함수로 분리 할 것.
    case 문에 주석을 달때는 case 문 뒤에 붙일 것
--%>
GamPopupHtldBizAssessModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case 'btnSave': //저장
		this.saveData();
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
	loadData - 임대계약상세 조회
--%>
GamPopupHtldBizAssessModule.prototype.loadData = function() {
	var searchData = this.makeFormArgs('#gamPopupHtldBizAssessForm'); 
	this.doAction('/oper/htldnew/selectHtldRentBizAssessDetail.do', searchData, function(module, result) {
		if(result.resultCode == 0) {
			result.resultDetail.assetsLocplcAll				= result.resultDetail.gisAssetsLocplc;
			if(result.resultDetail.gisAssetsLnm != void(0)) {
				result.resultDetail.assetsLocplcAll 			+= " " + result.resultDetail.gisAssetsLnm;
				if(result.resultDetail.gisAssetsLnmSub != void(0)) {
					result.resultDetail.assetsLocplcAll 		+= "-" + result.resultDetail.gisAssetsLnmSub;
				}
			}
			module._resultDetail = result.resultDetail;
			module.makeFormValues('#gamPopupHtldBizAssessForm', result.resultDetail);
			module.$('#histDt').val(module._histDt);
			module.initControls();
		}
	});
};

<%--
	initControls - 입력 컨트롤 및 버튼 설정
--%>
GamPopupHtldBizAssessModule.prototype.initControls = function() {
	if(this._resultDetail.aseRntfee == void(0)) {
		this.$('#btnDelete').hide();
	} else {
		this.$('#btnDelete').show();
	}
};

<%--
	validateData - 데이터 유효성 검사
--%>
GamPopupHtldBizAssessModule.prototype.validateData = function() {
	if(this.$('#aseRntfee').val() == '') {
		alert('실적평가임대료을 입력하세요.');
		return false;
	}
	if(this.$('#aseApplcBegin').val() == '') {
		alert('실적평가기간(시작일)을 선택하세요.');
		return false;
	}
	if(this.$('#aseApplcEnd').val() == '') {
		alert('실적평가기간(종료일)을 선택하세요.');
		return false;
	}
	if(this.$('#aseApplcBegin').val() > this.$('#aseApplcEnd').val()) {
		alert('실적평가기간(시작일)이 실적평가기간(종료일)보다 큽니다.');
		return false;
	}
	return true;
};

<%--
	saveData - 실적평가 저장
--%>
GamPopupHtldBizAssessModule.prototype.saveData = function() {	
	if(!confirm("저장하시겠습니까?")) return;
	if(!this.validateData()) return;
	var saveData = this.makeFormArgs('#gamPopupHtldBizAssessForm');
	this.doAction('/oper/htldnew/updateBizAssess.do', saveData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

<%--
	deleteData - 실적평가 삭제
--%>
GamPopupHtldBizAssessModule.prototype.deleteData = function() {	
	if(!confirm("삭제하시겠습니까?")) return;
	var deleteData = this.makeFormArgs('#gamPopupHtldBizAssessForm');
	this.doAction('/oper/htldnew/deleteBizAssess.do', deleteData, function(module, result) {
		alert(result.resultMsg);
		if(result.resultCode == 0) {
			module.closeDialog("ok", null);
		}
	});
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupHtldBizAssessModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupHtldBizAssessForm">
			<input type="hidden" id="mngYear" />
			<input type="hidden" id="mngNo" />
			<input type="hidden" id="mngSeq" />
			<input type="hidden" id="registSeq"/>
			<input type="hidden" id="histDt"/>
			<input type="hidden" id="paySe"/>
			<input type="hidden" id="priceSe"/>
			<input type="hidden" id="termnYn">
        	<table class="editForm" style="width:100%">
        		<tr>
					<th width="10%" height="18">입주기업</th>
					<td>
						<input type="text" size="30" id="entrpsNm" disabled/>
					</td>
					<th width="10%" height="18">사업자등록번호</th>
					<td>
						<input type="text" size="30" id="bizrno" disabled/>
					</td>
 				</tr>
				<tr>
					<th width="10%" height="18">계약기간</th>
					<td>
						<input type="text" size="13" id="detailPdBegin" disabled/>~
						<input type="text" size="13" id="detailPdEnd" disabled/>
					</td>
					<th width="10%" height="18">총임대면적</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="grRentAr" disabled/>&nbsp; m<sup>2</sup>
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">자산명</th>
					<td>
						<input type="text" size="30" id="assetsNm" disabled/>
					</td>
					<th width="10%" height="18">임대구역</th>
					<td>
						<input type="text" size="30" id="boundNm" disabled/>
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">주소지</th>
					<td colspan="3">
						<input type="text" size="92" id="assetsLocplcAll" disabled/>
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
					<th width="10%" height="18">적용단가</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="applcRntfee" disabled/>&nbsp; 원
					</td>
					<th width="10%" height="18">단가구분</th>
					<td>
						<input type="text" size="30" id="priceSeNm" disabled/>
					</td>
				</tr>
				<tr>
					<th width="10%" height="18">실적평가임대료</th>
					<td>
						<input type="text" size="30" class="ygpaNumber" data-decimal-point="2" id="aseRntfee" />&nbsp; 원
					</td>
					<th width="10%" height="18">실적평가기간</th>
					<td>
						<input type="text" size="12" id="aseApplcBegin" class="emdcal"/>~
						<input type="text" size="12" id="aseApplcEnd" class="emdcal"/>
					</td>									
				</tr>
				<tr>
					<th width="10%" height="18">평가내용</th>
					<td colspan="3">
						<input type="text" size="92" id="applcRsn" />
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