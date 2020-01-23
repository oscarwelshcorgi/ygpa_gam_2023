<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupRoadNo.jsp
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
function GamPopupRoadNoModule() {}

GamPopupRoadNoModule.prototype = new EmdPopupModule(550, 400);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupRoadNoModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectFcltsNoList.do',
		dataType: "json",
		colModel : [
					{display:"시설물 명",		name:"prtFcltyNm",		width:200,		sortable:true,	align:"left"},
					{display:"위치",			name:"loc",					width:290,		sortable:true,	align:"left"}
			],
		height: "270"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	this.loadData();
};

GamPopupRoadNoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupRoadNoModule.prototype.loadData = function() {
	console.log(EMD.userinfo.mngFcltyCd);
	var searchOpt=this.makeFormArgs("#gamPopupRoadNoForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupRoadNoModule.prototype.returnData = function() {
	var rows = this.$("#grdInfoList").selectedRows();
	if(rows.length>0) {
		this.closeDialog("ok", rows[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupRoadNoModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupRoadNoModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupRoadNoForm">
 			<input id="fcltsMngGroupNo" type="hidden" value="<c:out value='${gamRoadIncidentMngVO.fcltsMngGroupNo }' />" />
			<table class="searchPanel">
				<tbody>
					<tr>
<!--						<th width="10%">시설물 명</th>
                         <td><input id="sFcltsMngGroupNm" type="text" size="20" /><input id="sFcltsMngGroup" type="hidden"/></td>
 -->						
						<td width="10%"><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">시설 선택</button>
            	<button id="btnCancel">취소</button>
	        </div>
	    </div>

	</div>
</div>