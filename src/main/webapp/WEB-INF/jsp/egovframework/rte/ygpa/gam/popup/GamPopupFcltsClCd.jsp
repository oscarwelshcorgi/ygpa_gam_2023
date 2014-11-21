<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsClCd.jsp
  * @Description : 시설물 분류코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.05  김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.05
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFcltsClCdModule() {}

GamPopupFcltsClCdModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFcltsClCdModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectFcltsClCdInfoList.do',
		dataType: "json",
		colModel : [
					{display:"시설물분류코드", name:"fcltsClCd", 		width:105, sortable:true, align:"center"},
					{display:"시설물분류명",  name:"fcltsClCdNm", 	width:210, sortable:true, align:"center"},
					{display:"시설물상위분류명",  name:"fcltsClUpperCdNm", 	width:210, sortable:true, align:"center"}
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

};
// 사용자 설정 함수 추가

GamPopupFcltsClCdModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnSearch":
		this.loadData();
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

GamPopupFcltsClCdModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupFcltsClCdModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFcltsClCdForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupFcltsClCdModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFcltsClCdForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>시설물분류코드</th>
                        <td>
                        	<input id="sFcltsClCdChar" type="hidden" value="${searchOpt.sFcltsClCdChar}" />
                        	<input id="sFcltsClCd" type="text" size="12"/>
                        </td>
						<th>시설물분류명</th>
                        <td><input id="sFcltsClCdNm" type="text" size="25" /></td>
						<td><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">시설물 분류 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>