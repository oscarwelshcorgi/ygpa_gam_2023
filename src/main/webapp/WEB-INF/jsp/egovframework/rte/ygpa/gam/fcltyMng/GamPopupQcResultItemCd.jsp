<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupQcResultItemCd.jsp
  * @Description : 점검 항목 체크 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.01.22  김종민          최초 생성
  *
  * author 김종민
  * since 2015.01.22
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
 * @FUNCTION NAME : GamPopupQcResultItemCdModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupQcResultItemCdModule() {}

GamPopupQcResultItemCdModule.prototype = new EmdPopupModule(750, 490);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupQcResultItemCdModule.prototype.loadComplete = function(params) {
	this._qcResultList = params['qcResultList'];
	this._fcltsJobSeNm = params['fcltsJobSeNm'];
	this._fcltsJobSe = params['fcltsJobSe'];
	this._fcltsMngGroupNm = params['fcltsMngGroupNm'];
	this._popupMode = params['popupMode'];
	this._mechFcltsSe = params['mechFcltsSe'];
	this.resizable(true);
	
	var resultChkOption = [
	                       	{value:'N', name:'정상'},
	                       	{value:'W', name:'요주의'},
	                       	{value:'X', name:'불량'}
	                      ];
	// 기계일 경우 점검결과항목에 없음 추가 2016-4-15
	if(this._fcltsJobSe == 'M') {
		resultChkOption[resultChkOption.length] = {value:'E', name:'없음'}
	}
	
	var gridHeaders = [];
	
	// 각 시설물별 점검항목 해더 필드 설정
	if(this._fcltsJobSe == 'C') {
		//토목일 경우
		gridHeaders[gridHeaders.length] = {display:"점검 상위 항목명",	name:"qcItemUpperNm",	width:160,	sortable:true,	align:"left"};
		gridHeaders[gridHeaders.length] = {display:"점검 항목 명",		name:"qcItemNm",		width:320,	sortable:true,	align:"left"};
		if(this._popupMode == 'edit') {
			gridHeaders[gridHeaders.length] = {display:"점검 내용",	name:"inspResultCn",	width:200,	sortable:true,	align:"left", displayFormat:'input'};
		} else {
			gridHeaders[gridHeaders.length] = {display:"점검 내용",	name:"inspResultCn",	width:200,	sortable:true,	align:"left"};
		}
	}
	else if((this._fcltsJobSe == 'M') && this._mechFcltsSe == '1')  {
		//하역장비일 경우
		gridHeaders[gridHeaders.length] = {display:"하역장비 구분",	name:"qcItemUpperUpperNm",	width:150,	sortable:true,	align:"left"};
		gridHeaders[gridHeaders.length] = {display:"점검 상위 항목명",	name:"qcItemUpperNm",	width:150,	sortable:true,	align:"left"};
		gridHeaders[gridHeaders.length] = {display:"점검 항목 명",		name:"qcItemNm",		width:300,	sortable:true,	align:"left"};
		if(this._popupMode == 'edit') {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChk",	width:80,	sortable:true,	align:"center", displayFormat:'select', displayOption:resultChkOption};
		} else {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChkNm",	width:80,	sortable:true,	align:"center"};
		}
	}
	else if((this._fcltsJobSe == 'M') && this._mechFcltsSe == '2')  {
		//항만부잔교일 경우
		gridHeaders[gridHeaders.length] = {display:"점검 항목 명",	name:"qcItemUpperNm",	width:400,	sortable:true,	align:"left"};
		gridHeaders[gridHeaders.length] = {display:"부잔교 명",		name:"qcItemNm",		width:150,	sortable:true,	align:"left"};
		if(this._popupMode == 'edit') {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChk",	width:100,	sortable:true,	align:"center", displayFormat:'select', displayOption:resultChkOption};
		} else {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChkNm",	width:100,	sortable:true,	align:"center"};
		}
	}
	else {
		gridHeaders[gridHeaders.length] = {display:"점검 상위 항목명",	name:"qcItemUpperNm",	width:200,	sortable:true,	align:"left"};
		gridHeaders[gridHeaders.length] = {display:"점검 항목 명",		name:"qcItemNm",		width:350,	sortable:true,	align:"left"};
		if(this._popupMode == 'edit') {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChk",	width:100,	sortable:true,	align:"center", displayFormat:'select', displayOption:resultChkOption};
		} else {
			gridHeaders[gridHeaders.length] = {display:"점검 결과",	name:"inspResultChkNm",	width:100,	sortable:true,	align:"center"};
		}
	}
	
	var flexgridOpt = null;
	
	if((this._fcltsJobSe == 'M') && this._mechFcltsSe == '1')  {
		flexgridOpt = {
				module : this,
				url : '',
				dataType : 'json',
				colModel : gridHeaders,
				mergeRows : 'qcItemUpperUpperNm,qcItemUpperNm',
				height: '370'
		}; 
	} else {
		flexgridOpt = {
			module : this,
			url : '',
			dataType : 'json',
			colModel : gridHeaders,
			mergeRows : 'qcItemUpperNm',
			height: '370'
		};
	}
	
	this.$('#mainGrid').flexigrid(flexgridOpt);
	
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamPopupQcResultItemCdModule.prototype.onButtonClick = function(buttonId) {
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
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamPopupQcResultItemCdModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupQcResultItemCdModule.prototype.loadData = function() {
	this.$('#fcltsMngGroupNm').text(this._fcltsMngGroupNm);
	this.$('#fcltsJobSeNm').text(this._fcltsJobSeNm);
	this.$('#mainGrid').flexAddData({resultList : this._qcResultList });
};

<%
/**
 * @FUNCTION NAME : processOk
 * @DESCRIPTION   : OK PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupQcResultItemCdModule.prototype.processOk = function() {
	if(this._popupMode == 'edit') {
		var resultList = { 'resultList' : this.$('#mainGrid').flexGetData() };
		this.closeDialog('ok', resultList); 
	} else {
		this.closeDialog('ok');
	}
};

<%
/**
 * @FUNCTION NAME : processCancel
 * @DESCRIPTION   : CANCEL PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupQcResultItemCdModule.prototype.processCancel = function() {
	this.cancelDialog();
};

var popup_instance = new GamPopupQcResultItemCdModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 11. SUMMARY AREA (SUMMARY 영역) -->
		<table class="summaryPanel"  style="width:100%;">
			<tbody>
				<tr>
					<th width="15%">시설구분</th>
					<td style="font-weight:bold;"><span id="fcltsJobSeNm"></span></td>
					<th width="15%">관리그룹</th>
					<td style="font-weight:bold;"><span id="fcltsMngGroupNm"></span></td>
				</tr>
			</tbody>
		</table>
		<!-- 2. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<table id="mainGrid" style="display: none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<button id="btnOk">확인</button>
				<!-- <button id="btnCancel">취소</button> -->
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
