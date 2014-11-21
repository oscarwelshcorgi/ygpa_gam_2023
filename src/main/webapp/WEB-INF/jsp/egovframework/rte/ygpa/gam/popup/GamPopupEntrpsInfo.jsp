<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupEntrpsInfo.jsp
  * @Description : 업체정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  heroine          최초 생성
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
function GamPopupEntrpsModule() {}

GamPopupEntrpsModule.prototype = new EmdPopupModule(700, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupEntrpsModule.prototype.loadComplete = function() {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectEntrpsInfoList.do',
		dataType: "json",
		colModel : [
					{display:"업체코드",			name:"entrpscd", 	width:60, 		sortable:true, 		align:"center"},
					{display:"업체 명",			name:"entrpsNm", 	width:200, 		sortable:true, 		align:"center"},
					{display:"대표자 명",			name:"rprsntvNm", 	width:60, 		sortable:true, 		align:"center"},
					{display:"사업자등록번호",	name:"bizrno", 		width:120, 		sortable:true, 		align:"center"},
					{display:"업종",				name:"induty", 		width:100, 		sortable:true, 		align:"center"}
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

GamPopupEntrpsModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnEntrpsSearch":
		/*
		if(this.$("#entrpscd").val() == "" && this.$("#bizrno").val() == ""){
			if(this.$("#entrpsNm").val() == "" || this.$("#entrpsNm").val().length < 2){
				this.$("#entrpsNm").focus();
				alert("업체 명은 2자 이상 입력하십시오.");
				return;
			}
		}
		 */
		var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
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

GamPopupEntrpsModule.prototype.onSubmit = function() {
	//this.showAlert(this.$("#prtCode").val()+"을(를) 조회 하였습니다");
	this.loadData();
};

GamPopupEntrpsModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupEntrpsForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupEntrpsModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupEntrpsForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>업체 명</th>
                        <td><input id="entrpsNm" type="text" style="width: 120px;" title="업체 명" maxlength="20" /></td>
						<th>업체코드</th>
                        <td><input id="entrpscd" type="text" style="width: 80px;" title="업체코드" maxlength="10" /></td>
                    	<th>사업자 번호</th>
						<td><input id="bizrno" type="text" style="width: 80px;" title="사업자 등록번호" maxlength="12" /></td>
						<td><button id="btnEntrpsSearch" class="buttonSubmit">조회</button>
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