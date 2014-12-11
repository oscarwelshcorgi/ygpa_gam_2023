<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsMngGroup.jsp
  * @Description : 시설물 관리그룹 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.14  김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.14
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFcltsMngGroupModule() {}

GamPopupFcltsMngGroupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFcltsMngGroupModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectFcltsMngGroupInfoList.do',
		dataType: "json",
		colModel : [
					{display:"시설물그룹번호", 	name:"fcltsMngGroupNo", width:150, sortable:true, align:"center"},
					{display:"시설물그룹명",  	name:"fcltsMngGroupNm",	width:200, sortable:true, align:"center"},
					{display:"공사명",  		name:"cnstNm", 			width:200, sortable:true, align:"center"},
					{display:"관리주체",  		name:"mngMainbd", 		width:150, sortable:true, align:"center"},
					{display:"소유자",  		name:"owner", 			width:150, sortable:true, align:"center"},
					{display:"시공자",  		name:"cnstrtr", 		width:150, sortable:true, align:"center"},
					{display:"주소",  		name:"fcltsAdres", 		width:400, sortable:true, align:"center"},
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};

GamPopupFcltsMngGroupModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupFcltsMngGroupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFcltsMngGroupForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupFcltsMngGroupModule.prototype.returnData = function() {
	var rows = this.$("#grdInfoList").selectedRows();
	if(rows.length>0) {
		this.closeDialog("ok", rows[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupFcltsMngGroupModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupFcltsMngGroupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFcltsMngGroupForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>시설물 관리 그룹번호</th>
                        <td>
                        	<input id="sFcltsMngGroupNo" type="text" size="14" maxlength="14" />
                        </td>
						<th>시설물 관리 그룹명</th>
                        <td><input id="sFcltsMngGroupNm" type="text" size="20" /></td>
						<td><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">시설물 관리그룹 선택</button>
            	<button id="btnCancel">취소</button>
	        </div>
	    </div>

	</div>
</div>