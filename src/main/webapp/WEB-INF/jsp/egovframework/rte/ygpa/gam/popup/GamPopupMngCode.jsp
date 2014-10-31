<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupMngCodeInfo.jsp
  * @Description : 관리비 시설코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.29  heroine          최초 생성
  *
  * author heroine
  * since 2014.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupMngCodeModule() {}

GamPopupMngCodeModule.prototype = new EmdPopupModule(600, 440);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupMngCodeModule.prototype.loadComplete = function() {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '<c:url value="/popup/selectMngCodeList.do"/>',
		dataType: "json",
		colModel : [
					{display:'관리비 시설 코드', 			name:'mngFeeFcltyCd',	width:110, 		sortable:false,		align:'center'},
                    {display:'관리비 시설 구분', 	name:'mngFeeFcltySe',		width:110, 		sortable:false,		align:'center'},
                    {display:'관리비 시설 명', 	name:'mngFeeFcltyNm',		width:110, 		sortable:false,		align:'center'},
                    {display:'관리비 업무 구분', 	name:'mngFeeJobSe',		width:110, 		sortable:false,		align:'center'}
			],
		height: "300"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	this.$("#grdInfoList").on("onItemSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is selected");
	});

	this.$("#grdInfoList").on("onItemUnSelected", function(event, module, row, grid, param) {
		//alert("row " + row["assetCls"]+"-"+row["assetNo"]+"-"+row["assetNoSeq"]+" is unselected");
	});

};

// 사용자 설정 함수 추가

GamPopupMngCodeModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnMngSearch":
		/*
		if(this.$("#entrpscd").val() == "" && this.$("#bizrno").val() == ""){
			if(this.$("#entrpsNm").val() == "" || this.$("#entrpsNm").val().length < 2){
				this.$("#entrpsNm").focus();
				alert("업체 명은 2자 이상 입력하십시오.");
				return;
			}
		}
		 */
		var searchOpt=this.makeFormArgs("#GamPopupMngCodeForm");
	 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();

		break;
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

GamPopupMngCodeModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupMngCodeModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#GamPopupMngCodeForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupMngCodeModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="GamPopupMngCodeForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>시설코드</th>
                        <td><input id="sMngFeeFcltyCd" type="text" style="width: 80px;" title="시설코드" maxlength="20" /></td>
						<th>시설 명</th>
                        <td><input id="sMngFeeFcltyNm" type="text" style="width: 80px;" title="시설 명" maxlength="10" /></td>
                    	<th>업무 구분</th>
						<td><input id="sMngFeeJobSe" type="text" style="width: 80px;" title="업무 구분" maxlength="12" /></td>
						<td><button id="btnMngSearch" class="buttonSubmit">조회</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">코드 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>