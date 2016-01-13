<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupCtrtNo.jsp
  * @Description : 계약번호 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.26  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.26
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFcltsClCdModule() {}

GamPopupFcltsClCdModule.prototype = new EmdPopupModule(830, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFcltsClCdModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectCtrtNoList.do',
		dataType: "json",
		colModel : [
					{display:'계약 번호',			name:'ctrtNo',				width:120,		sortable:false,		align:'center'},
					{display:'구분',				name:'ctrtSeNm',			width:60,		sortable:false,		align:'center'},
					{display:'발주 방식',			name:'orderMthd',			width:100,		sortable:false,		align:'left'},
					{display:'계약 명',				name:'ctrtNm',				width:200,		sortable:false,		align:'left'},
					{display:'계약 방법',			name:'ctrtMth',				width:100,		sortable:false,		align:'left'},
					{display:'계약 일자',			name:'ctrtDt',				width:80,		sortable:false,		align:'center'},
					{display:'계약 금액',			name:'ctrtAmt',				width:100,		sortable:false,		align:'right', 		displayFormat: 'number'}
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
	});

	this.applySelectYear();
	
};
// 사용자 설정 함수 추가

GamPopupFcltsClCdModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnOk":
		var row = this.$("#grdInfoList").selectedRows();
		if(row.length>0) {
			this.closeDialog("ok", row[0]);
		}
		else {
			alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
		}
		break;
	case "cancel":
		this.cancelDialog();
	}
};

GamPopupFcltsClCdModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupFcltsClCdModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupCtrtNoForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : applySelectYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : NONE
**/
%>
GamPopupFcltsClCdModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	for(var i = toYear;i>=2000;i--){
		this.$("#sCtrtYear").append("<option value='" + i + "'>" + i + "년</option>");
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupFcltsClCdModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupCtrtNoForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>계약번호</th>
                        <td>
                        	<input id="sCtrtNo" type="text" size="14" maxlength="15" />
                        </td>
						<th>계약명</th>
                        <td>
                        	<input id="sCtrtNm" type="text" size="20" maxlength="100" />
                        </td>
                        <th>계약년도</th>
                        <td>
							<select id="sCtrtYear" title="계약년도">
								<option value="">선택</option>
							</select>
						</td>
						<td><button class="buttonSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">계약 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>