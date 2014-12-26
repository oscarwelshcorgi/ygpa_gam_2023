<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupFcltsFeeMngArrrgNtic.jsp
  * @Description : 시설물 관리비 관리 연체 고지 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.25  ACEWOLF          최초 생성
  *
  * author ACEWOLF
  * since 2014.12.25
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
 **/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamPopupFcltsFeeMngArrrgNticModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupFcltsFeeMngArrrgNticModule() {}

GamPopupFcltsFeeMngArrrgNticModule.prototype = new EmdPopupModule(570, 400);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.loadComplete = function(params) {

	this.$('#dlyBillDt').on('keyup change',{module:this}, function(event){
		event.data.module.setDlyDueDt();
		event.data.module.calcDlyBillAmnt();
	});

	if (params != null) {
		this.$('#mngMt').val(params.mngMt);
		this.$('#mngFeeJobSe').val(params.mngFeeJobSe);
		this.$('#mngSeq').val(params.mngSeq);
		this.$('#reqestSeq').val(params.reqestSeq);
       	this.$('#prtAtCode').val(params.prtAtCode);
       	this.$('#chrgeKnd').val(params.chrgeKnd);
       	this.$('#chrgeKndNm').val(params.chrgeKndNm);
       	this.$('#feeTp').val(params.feeTp);
       	this.$('#fiscalYr').val(params.fiscalYr);
       	this.$('#billNo').val(params.billNo);
       	this.$('#dlySerNo').val(params.dlySerNo);
       	this.$('#entrpscd').val(params.entrpscd);
       	this.$('#entrpsNm').val(params.entrpsNm);
       	this.$('#bizrno').val(params.bizrno);
       	this.$('#billAmnt').val(params.billAmnt);
       	this.$('#vatYn').val(params.vatYn);
       	this.$('#vatYnNm').val(params.vatYnNm);
       	this.$('#vat').val(params.vat);
       	this.$('#sumBillAmnt').val(params.sumBillAmnt);
       	this.$('#rcvdTp').val(params.rcvdTp);
       	this.$('#firstBillDt').val(params.firstBillDt);
       	this.$('#billDt').val(params.billDt);
       	this.$('#firstDueDt').val(params.firstDueDt);
       	this.$('#dueDt').val(params.dueDt);
       	this.$('#prvBillDt').val(params.prvBillDt);
       	this.$('#prvDueDt').val(params.prvDueDt);
       	this.$('#dlyBillDt').val(params.dlyBillDt);
       	this.$('#dlyDueDt').val(params.dlyDueDt);
       	this.$('#arrrgTariff').val(params.arrrgTariff);
       	this.$('#arrrgPayDates').val(params.arrrgPayDates);
       	this.$('#dlyBillAmnt').val(params.dlyBillAmnt);
    	this.$('#dbillAmnt').val("0");
    	this.$('#djiroAmnt').val("0");
       	this.$('#dlyBillRsn').val(params.dlyBillRsn);
       	this.calcDlyBillAmnt();
	}

};

<%
/**
 * @FUNCTION NAME : setDlyDueDt
 * @DESCRIPTION   : 연체 납부 기한을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.setDlyDueDt = function() {

	var dlyBillDt = this.$('#dlyBillDt').val();
	var toDay = new Date();
	var year = "";
	var month = "";
	var day = "";
	var dlyDueDt = "";
	if (dlyBillDt == "") {
		year = toDay.getFullYear();
		month = toDay.getMonth() + 1;
		day = toDay.getDate();
		if (month >= 1 && month <= 9) {
			if (day >= 1 && day <= 9) {
				dlyBillDt = year + "-" + "0" + month + "-" + "0" + day;
			} else {
				dlyBillDt = year + "-" + "0" + month + "-" + day;
			}
		} else {
			if (day >= 1 && day <= 9) {
				dlyBillDt = year + "-" + month + "-" + "0" + day;
			} else {
				dlyBillDt = year + "-" + month + "-" + day;
			}
		}
		this.$('#dlyBillDt').val(dlyBillDt);
	}
	var dueDate = EMD.util.strToDate(this.$('#dlyBillDt').val());
	var dayOfMonth = dueDate.getDate();
	dueDate.setDate(dayOfMonth + 15);
	year = dueDate.getFullYear();
	month = dueDate.getMonth() + 1;
	day = dueDate.getDate();
	if (month >= 1 && month <= 9) {
		if (day >= 1 && day <= 9) {
			dlyDueDt = year + "-" + "0" + month + "-" + "0" + day;
		} else {
			dlyDueDt = year + "-" + "0" + month + "-" + day;
		}
	} else {
		if (day >= 1 && day <= 9) {
			dlyDueDt = year + "-" + month + "-" + "0" + day;
		} else {
			dlyDueDt = year + "-" + month + "-" + day;
		}
	}
	this.$('#dlyDueDt').val(dlyDueDt);

};

<%
/**
 * @FUNCTION NAME : calcDlyBillAmnt
 * @DESCRIPTION   : 연체 금액을 계산한다.
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.calcDlyBillAmnt = function() {

	var calcVO = [];
	var firstBillDt = this.$('#firstBillDt').val();
	var dlyBillDt = this.$('#dlyBillDt').val();
	var billAmnt = this.$('#billAmnt').val();
	var vat = this.$('#vat').val();
	var prvDlybillAmnt = this.$('#prvDlybillAmnt').val();
	this.$('#arrrgPayDates').val("0");
	this.$('#arrrgTariff').val("0.00");
	this.$('#dlyBillAmnt').val("0");
	this.$('#dbillAmnt').val("0");
	this.$('#djiroAmnt').val("0");
	this.$('#dlyBillRsn').val("");
	if (firstBillDt == "" || firstBillDt.length != 10) {
		return;
	}
	if (dlyBillDt == "" || dlyBillDt.length != 10) {
		return;
	}
	if (firstBillDt > dlyBillDt) {
		return;
	}
	if (billAmnt == "") {
		return;
	}
	if (vat == "") {
		return;
	}
	calcVO={
		'firstBillDt':firstBillDt,
		'dlyBillDt':dlyBillDt,
		'billAmnt':billAmnt,
		'vat':vat,
		'prvDlybillAmnt':prvDlybillAmnt
	};
	this.doAction('/mngFee/gamPopupCalcDlyBillAmnt.do', calcVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#arrrgPayDates').val(result.result.dlyTerm);
			module.$('#arrrgTariff').val(result.result.dlyRate);
			module.$('#dlyBillAmnt').val(result.result.dlyBillAmnt);
			module.$('#dbillAmnt').val(result.result.dbillAmnt);
			module.$('#djiroAmnt').val(result.result.djiroAmnt);
			module.$('#dlyBillRsn').val(result.result.dlyBillRsn);
		}
	});

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnOk':
	    	this.processOk();
			break;
	    case 'btnCancel':
	    	this.processCancel();
			break;
	}

};

<%
/**
 * @FUNCTION NAME : processOk
 * @DESCRIPTION   : OK PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.processOk = function() {

	var processVO = [];
	var prtAtCode = this.$('#prtAtCode').val();
	var feeTp = this.$('#feeTp').val();
	var fiscalYr = this.$('#fiscalYr').val();
	var billNo = this.$('#billNo').val();
	var dlySerNo = this.$('#dlySerNo').val();
	var dlyBillDt = this.$('#dlyBillDt').val();
	var dlyDueDt = this.$('#dlyDueDt').val();
	var arrrgTariff = this.$('#arrrgTariff').val().replace(/,/gi, "");
	var arrrgPayDates = this.$('#arrrgPayDates').val().replace(/,/gi, "");
	var dlyBillAmnt = this.$('#dlyBillAmnt').val().replace(/,/gi, "");
	var dbillAmnt = this.$('#dbillAmnt').val().replace(/,/gi, "");
	var djiroAmnt = this.$('#djiroAmnt').val().replace(/,/gi, "");
	var dlyBillRsn = this.$('#dlyBillRsn').val();
	var dataValue = 0;
	if (prtAtCode == "" || feeTp == "" || fiscalYr == "" || billNo == "") {
		alert("고지 정보가 부정확합니다.");
		return;
	}
	if (dlySerNo == "" || dlySerNo >= "99" || dlySerNo <= "00") {
		alert("연체 고지 횟수가 부정확합니다.");
		return;
	}
	if (dlyBillDt > dlyDueDt) {
		alert("연체 고지 일자가 연체 납부 기한보다 큽니다.");
		return;
	}
	dataValue = Number(dlyBillAmnt);
	if (dataValue > 999999999999 || dataValue <= 0) {
		alert("연체 금액이 부정확합니다.");
		return;
	}
	dataValue = Number(arrrgPayDates);
	if (dataValue > 1800 || dataValue <= 0) {
		alert("연체 일수가 부정확합니다.");
		return;
	}
	dataValue = Number(arrrgTariff);
	if (dataValue > 0.15 || dataValue <= 0) {
		alert("연체 요율이 부정확합니다.");
		return;
	}
	processVO={
			'mngMt':this.$('#mngMt').val(),
			'mngFeeJobSe':this.$('#mngFeeJobSe').val(),
			'mngSeq':this.$('#mngSeq').val(),
			'reqestSeq':this.$('#reqestSeq').val(),
			'prtAtCode':prtAtCode,
			'chrgeKnd':this.$('#chrgeKnd').val(),
			'chrgeKndNm':this.$('#chrgeKndNm').val(),
			'feeTp':feeTp,
			'fiscalYr':fiscalYr,
			'billNo':billNo,
			'dlySerNo':dlySerNo,
			'entrpscd':this.$('#entrpscd').val(),
			'entrpsNm':this.$('#entrpsNm').val(),
			'bizrno':this.$('#bizrno').val(),
			'firstBillDt':this.$('#firstNticDt').val(),
			'billDt':this.$('#billDt').val(),
			'firstDueDt':this.$('#firstPayTmlmt').val(),
			'dueDt':this.$('#dueDt').val(),
			'prvBillDt':this.$('#prvBillDt').val(),
			'prvDueDt':this.$('#prvDueDt').val(),
			'billAmnt':this.$('#billAmnt').val(),
			'vatYn':this.$('#vatYn').val(),
			'vatYnNm':this.$('#vatYnNm').val(),
			'vat':this.$('#vat').val(),
			'sumBillAmnt':this.$('#sumBillAmnt').val(),
			'rcvdTp':this.$('#rcvdTp').val(),
			'dlyBillDt':dlyBillDt,
			'dlyDueDt':dlyDueDt,
			'arrrgTariff':arrrgTariff,
			'arrrgPayDates':arrrgPayDates,
			'dlyBillAmnt':dlyBillAmnt,
			'dbillAmnt':dbillAmnt,
			'djiroAmnt':djiroAmnt,
			'dlyBillRsn':dlyBillRsn,
			'processMode':"I"
	};
	this.closeDialog("ok", processVO);

};

<%
/**
 * @FUNCTION NAME : processCancel
 * @DESCRIPTION   : CANCEL PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsFeeMngArrrgNticModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupFcltsFeeMngArrrgNticModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 1. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<form id="detailForm">
				<table class="summaryPanel" style="width:100%;">
					<tr>
						<th style="font-weight:bold; height:20px;">시설물 관리비 고지 내역</th>
					</tr>
				</table>
				<table class="detailPanel" style="width:100%;">
					<tr>
						<th style="width:15%; height:20px;">요　금　종　류</th>
						<td>
							<input id="mngMt" type="hidden"/>
							<input id="mngFeeJobSe" type="hidden"/>
							<input id="mngSeq" type="hidden"/>
							<input id="reqestSeq" type="hidden"/>
							<input id="feeTp" type="hidden"/>
							<input type="text" size="2" id="chrgeKnd" disabled>
							<input type="text" size="21" id="chrgeKndNm" disabled>
						</td>
						<th style="width:15%; height:20px;">고　지　번　호</th>
						<td>
							<input type="text" size="4" id="prtAtCode" disabled>
							<input type="text" size="8" id="fiscalYr" disabled>
							<input type="text" size="9" id="billNo" disabled>
						</td>
					</tr>
                    <tr>
						<th style="width:15%; height:20px;">사　　용　　료</th>
						<td>
							<input type="text" size="25" class="ygpaNumber" id="billAmnt" disabled/>
						</td>
						<th style="width:15%; height:20px;">부　　가　　세</th>
						<td>
							<input id="vatYn" type="hidden"/>
							<input type="text" size="13"  id="vatYnNm" disabled/>
							<input type="text" size="10" class="ygpaNumber" id="vat" disabled/>
						</td>
					</tr>
                    <tr>
						<th style="width:15%; height:20px;">고　지　금　액</th>
						<td>
							<input id="rcvdTp" type="hidden"/>
							<input type="text" size="25" class="ygpaNumber" id="sumBillAmnt" disabled/>
						</td>
						<th style="width:15%; height:20px;">고　지　업　체</th>
						<td>
							<input id="entrpscd" type="hidden"/>
							<input id="bizrno" type="hidden"/>
							<input type="text" size="25" id="entrpsNm" disabled/>
						</td>
					</tr>
					<tr>
						<th style="width:15%; height:20px;">최종 고지 일자</th>
						<td>
							<input type="text" size="25" id="billDt" disabled/>
						</td>
						<th style="width:15%; height:20px;">최초 고지 일자</th>
						<td>
							<input id="prvBillDt" type="hidden"/>
							<input type="text" size="25" id="firstBillDt" disabled/>
						</td>
					</tr>
					<tr>
						<th style="width:15%; height:20px;">최종 납부 기한</th>
						<td>
							<input type="text" size="25" id="dueDt" disabled/>
						</td>
						<th style="width:15%; height:20px;">최초 납부 기한</th>
						<td>
							<input id="prvDueDt" type="hidden"/>
							<input type="text" size="25" id="firstDueDt" disabled/>
						</td>
					</tr>
				</table>
				<table class="summaryPanel" style="width:100%;">
					<tr>
						<th style="font-weight:bold; height:20px;">시설물 관리비 연체 내역</th>
					</tr>
				</table>
				<table class="detailPanel" style="width:100%;">
					<tr>
						<th style="width:15%; height:20px;">연체 고지 일자</th>
						<td>
							<input type="text" size="20" id="dlyBillDt" class="emdcal"/>
						</td>
						<th style="width:15%; height:20px;">연체 납부 기한</th>
						<td>
							<input type="text" size="20" id="dlyDueDt" class="emdcal"/>
						</td>
					</tr>
					<tr>
						<th style="width:15%; height:20px;">횟수/요율/일수</th>
						<td>
							<input type="text" size="4" id="dlySerNo" disabled>
							<input type="text" size="8" class="ygpaNumber" id="arrrgTariff" disabled/>
							<input type="text" size="8" class="ygpaNumber" id="arrrgPayDates" disabled/>
						</td>
						<th style="width:15%; height:20px;">연　체　금　액</th>
						<td>
							<input id="prvDlybillAmnt" type="hidden"/>
							<input id="dbillAmnt" type="hidden"/>
							<input id="djiroAmnt" type="hidden"/>
							<input type="text" size="25" class="ygpaNumber" id="dlyBillAmnt" disabled/>
						</td>
					</tr>
					<tr>
						<th style="width:15%; height:20px;">연체 산출 내역</th>
						<td colspan="3">
							<input type="text" size="71" id="dlyBillRsn" disabled/>
						</td>
					</tr>
				</table>
			</form>
			<div class="emdControlPanel">
				<button id="btnOk">연체 고지 처리</button>
				<button id="btnCancel">연체 고지 처리 취소</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
