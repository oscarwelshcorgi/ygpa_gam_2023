<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocApplyEntrpsInfo.jsp
  * @Description : 투자비보전신청업체정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.21  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.10.21
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocApplyEntrpsInfoModule() {}

GamPopupSocApplyEntrpsInfoModule.prototype = new EmdPopupModule(700, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocApplyEntrpsInfoModule.prototype.loadComplete = function(searchOption) {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectSocApplyEntrpsInfoList.do',
		dataType: "json",
		colModel : [
					{display:"공사허가항구",	name:"prtAtNm", 		width:90, 		sortable:true, 		align:"center"},
					{display:"문서번호",		name:"constNo", 		width:100, 		sortable:true, 		align:"center"},
					{display:"시행업체",		name:"appAgentNm", 		width:120, 		sortable:true, 		align:"center"},
					{display:"요금종류",		name:"feeTpNm", 		width:70, 		sortable:true, 		align:"center"},
					{display:"적용요율",		name:"rateGubunNm", 	width:70, 		sortable:true, 		align:"center"},
					{display:"신청횟수",		name:"useNo", 			width:70, 		sortable:true, 		align:"center"},
					{display:"총금액",		name:"exmpAmnt", 		width:120, 		sortable:true, 		align:"right", 	displayFormat: 'number'},
					{display:"보전누계액",		name:"exmpAcc", 		width:120, 		sortable:true, 		align:"right", 	displayFormat: 'number'},
					{display:"등록항구",		name:"appPrtAtNm", 		width:70, 		sortable:true, 		align:"center"},
					{display:"신청(적용)일자",	name:"periodFr", 		width:100, 		sortable:true, 		align:"center"},
					{display:"공사명",		name:"item", 			width:200, 		sortable:true, 		align:"center"},
					{display:"신고일자",		name:"applDate", 		width:70, 		sortable:true, 		align:"center"},
					{display:"허가일자",		name:"perfDt", 			width:70, 		sortable:true, 		align:"center"},
					{display:"적용여부",		name:"useYn", 			width:70, 		sortable:true, 		align:"center"}
			],
		height: "300"
	});
	
	this.$("#gubun").val(searchOption["gubun"]);
	this.$("#appPrtAtCode").val(searchOption["appPrtAtCode"]);
	this.$("#useYn").val(searchOption["useYn"]);

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

};

// 사용자 설정 함수 추가

GamPopupSocApplyEntrpsInfoModule.prototype.onButtonClick = function(buttonId) {
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

GamPopupSocApplyEntrpsInfoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocApplyEntrpsInfoModule.prototype.loadData = function() {
	if(!this.$('#firmKorNm').val() && !this.$('#appAgentCode').val()){
		alert('업체명나 업체코드를 입력하세요.');
		return;
	}
	var searchOpt=this.makeFormArgs("#gamPopupSocApplyEntrpsInfoForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocApplyEntrpsInfoModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupSocApplyEntrpsInfoForm">
			<input type="hidden" id="gubun">
			<input type="hidden" id="appPrtAtCode">
			<input type="hidden" id="useYn">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체 명</th>
                        <td><input id="firmKorNm" type="text" style="width: 120px;" title="업체 명" maxlength="20" /></td>
						<th>업체코드</th>
                        <td><input id="appAgentCode" type="text" style="width: 80px;" title="업체코드" maxlength="10" /></td>
						<td><button class="buttonSearch">조회</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">업체 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>