<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupMachFcltySttus.jsp
  * @Description : 기계 시설 현황 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.03.21  ACEWOLF          최초 생성
  *
  * author ACEWOLF
  * since 2014.01.22
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
 * @FUNCTION NAME : GamPopupMachFcltySttusModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupMachFcltySttusModule() {}

GamPopupMachFcltySttusModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupMachFcltySttusModule.prototype.loadComplete = function(params) {

	this.resizable(true);

	this._processType = "Q";
	this._returnProcessType = "Q";
	if (params != null) {
		this._processType = params.processType;
		this.$('#wharfNm').val(params.wharfNm);
		this.$('#operCmpny').val(params.operCmpny);
		this.$('#ccCount').val(params.ccCount);
		this.$('#tcCount').val(params.tcCount);
		this.$('#ytCount').val(params.ytCount);
		this.$('#csCount').val(params.csCount);
		this.$('#rsCount').val(params.rsCount);
		this.$('#thCount').val(params.thCount);
		this.$('#tcRtgcCount').val(params.tcRtgcCount);
		this.$('#fcltsMngGroupNo').val(params.fcltsMngGroupNo);
		if (params.processType == "I") {
			this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		} else if (params.processType == "U") {
			this.$('#btnAdd').enable();
			this.$('#btnAdd').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
		} else {
			this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		}
	} else {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	}

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupMachFcltySttusModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
	    	this.processAdd();
			break;
		case 'btnSave':
	    	this.processSave();
			break;
		case 'btnDelete':
	    	this.processDelete();
			break;
	    case 'btnExit':
	    	this.processExit();
			break;
	}

};

<%
/**
 * @FUNCTION NAME : processAdd
 * @DESCRIPTION   : ADD PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupMachFcltySttusModule.prototype.processAdd = function() {

	this._processType = "I";
	this.$('#wharfNm').val("");
	this.$('#operCmpny').val("");
	this.$('#ccCount').val("");
	this.$('#tcCount').val("");
	this.$('#ytCount').val("");
	this.$('#csCount').val("");
	this.$('#rsCount').val("");
	this.$('#thCount').val("");
	this.$('#tcRtgcCount').val("");
	this.$('#fcltsMngGroupNo').val("");
	this.$("#wharfNm").focus();
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').enable();
	this.$('#btnSave').removeClass('ui-state-disabled');
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : processSave
 * @DESCRIPTION   : SAVE PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupMachFcltySttusModule.prototype.processSave = function() {

	var wharfNm = this.$('#wharfNm').val();
	var operCmpny = this.$('#operCmpny').val();
	if (wharfNm == "") {
		alert('부두명이 부정확합니다.');
		this.$("#wharfNm").focus();
		return;
	}
	if (operCmpny == "") {
		alert('운영사가 부정확합니다.');
		this.$("#operCmpny").focus();
		return;
	}
	var inputVO = this.makeFormArgs("#detailForm");
	if (this._processType == "I") {
		this.doAction('/popup/gamInsertMachFcltySttus.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._returnProcessType = "I";
				module.$('#btnSave').disable({disableClass:"ui-state-disabled"});
				module.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
				alert(result.resultMsg);
				module.processExit();
			} else {
				alert(result.resultMsg);
			}
		});
	} else if (this._processType == "U") {
		this.doAction('/popup/gamUpdateMachFcltySttus.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._returnProcessType = "U";
				module.$('#btnSave').disable({disableClass:"ui-state-disabled"});
				module.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
				alert(result.resultMsg);
				module.processExit();
			} else {
				alert(result.resultMsg);
			}
		});
	} else {
		alert('처리형태가 부정확합니다.');
	}

};

<%
/**
 * @FUNCTION NAME : processDelete
 * @DESCRIPTION   : DELETE PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupMachFcltySttusModule.prototype.processDelete = function() {

	var wharfNm = this.$('#wharfNm').val();
	var operCmpny = this.$('#operCmpny').val();
	if (wharfNm == "") {
		alert('부두명이 부정확합니다.');
		this.$("#wharfNm").focus();
		return;
	}
	if (operCmpny == "") {
		alert('운영사가 부정확합니다.');
		this.$("#operCmpny").focus();
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var inputVO = this.makeFormArgs("#detailForm");
		this.doAction('/popup/gamDeleteMachFcltySttus.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module._returnProcessType = "D";
				module.$('#btnSave').disable({disableClass:"ui-state-disabled"});
				module.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
				alert(result.resultMsg);
				module.processExit();
			} else {
				alert(result.resultMsg);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : processExit
 * @DESCRIPTION   : EXIT PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupMachFcltySttusModule.prototype.processExit = function() {

	if (this._returnProcessType == "I" || this._returnProcessType == "U" || this._returnProcessType == "D") {
		var wharfNm = this.$('#wharfNm').val();
		var operCmpny = this.$('#operCmpny').val();
		var ccCount = this.$('#ccCount').val();
		var tcCount = this.$('#tcCount').val();
		var ytCount = this.$('#ytCount').val();
		var csCount = this.$('#csCount').val();
		var rsCount = this.$('#rsCount').val();
		var thCount = this.$('#thCount').val();
		var tcRtgcCount = this.$('#tcRtgcCount').val();
		var fcltsMngGroupNo = this.$('#fcltsMngGroupNo').val();
		var returnData = {
				'processType':this._returnProcessType,
				'wharfNm':wharfNm,
				'operCmpny':operCmpny,
				'ccCount':ccCount,
				'tcCount':tcCount,
				'ytCount':ytCount,
				'csCount':csCount,
				'rsCount':rsCount,
				'thCount':thCount,
				'tcRtgcCount':tcRtgcCount,
				'fcltsMngGroupNo':fcltsMngGroupNo
			};
		this.closeDialog("ok", returnData);
	} else {
		this.cancelDialog();
	}

};

var popup_instance = new GamPopupMachFcltySttusModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 11. DATA AREA (자료 영역) -->
		<form id="detailForm">
			<table class="detailPanel" style="width:100%;">
				<tr>
					<th style="width:10%; height:22px;">부　　두　　명</th>
					<td>
						<input type="hidden" id="fcltsMngGroupNo"/>
						<input type="text" size="80" id="wharfNm" maxlength="80"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">운　　영　　사</th>
					<td>
						<input type="text" size="80" id="operCmpny" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">　　Ｃ／Ｃ　　</th>
					<td>
						<input type="text" size="80" id="ccCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">　　Ｔ／Ｃ　　</th>
					<td>
						<input type="text" size="80" id="tcCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">　　Ｙ／Ｔ　　</th>
					<td>
						<input type="text" size="80" id="ytCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">샷　　　　　시</th>
					<td>
						<input type="text" size="80" id="csCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">Reach (리　치)</th>
					<td>
						<input type="text" size="80" id="rsCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">Top　　Handler</th>
					<td>
						<input type="text" size="80" id="thCount" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th style="width:10%; height:22px;">비　고 (eRTGC)</th>
					<td>
						<input type="text" size="80" id="tcRtgcCount" maxlength="50"/>
					</td>
				</tr>
			</table>
		</form>
		<!-- 12. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<div class="emdControlPanel">
				<button id="btnAdd" class="buttonAdd">추가</button>
				<button id="btnSave" class="buttonSave">저장</button>
				<button id="btnDelete" class="buttonDelete">삭제</button>
				<button id="btnExit">종료</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
