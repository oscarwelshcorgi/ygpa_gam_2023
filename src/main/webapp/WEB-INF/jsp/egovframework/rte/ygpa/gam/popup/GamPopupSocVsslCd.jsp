<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocVsslCd.jsp
  * @Description : 비관리청 호출부호 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.09.26  김종민          최초 생성
  *
  * author 김종민
  * since 2014.09.26
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocVsslCdModule() {}

GamPopupSocVsslCdModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocVsslCdModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectSocVsslInfoList.do',
		dataType: "json",
		colModel : [
					{display:"호출부호코드",		name:"callLetter", 	width:150, 	sortable:true, 	align:"center"},
					{display:"호출선박명",			name:"vsslKorNm", 	width:350, 	sortable:true, 	align:"left"}
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};

GamPopupSocVsslCdModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocVsslCdModule.prototype.loadData = function() {
	if(!this.$("#callLetter").val() && !this.$("#vsslKorNm").val()) {
		alert('조회조건 중 하나라도 입력해야 검색이 됩니다.');
		return;
	}
	var searchOpt=this.makeFormArgs("#gamPopupVsslForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupSocVsslCdModule.prototype.returnData = function() {
	var rows = this.$("#grdInfoList").selectedRows();
	if(rows.length>0) {
		this.closeDialog("ok", rows[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupSocVsslCdModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case "btnSearch":
			this.loadData();
			break;
		case "btnOk":
			this.returnData();
			break;
		case "btnCancel":
			this.cancelDialog();
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocVsslCdModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupVsslForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>호출부호코드</th>
                        <td><input id="callLetter" type="text" size="12" title="호출부호코드" maxlength="9" /></td>
                    	<th>호출선박명</th>
						<td><input id="vsslKorNm" type="text" size="30" title="호출선박명" maxlength="40" /></td>
						<td><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">호출부호 선택</button>
            	<button id="btnCancel">취소</button>
	        </div>
	    </div>
	</div>
</div>