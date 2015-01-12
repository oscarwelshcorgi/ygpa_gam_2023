<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupFcltsMngNo.jsp
  * @Description : 시설물 관리번호 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.11  김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.11
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupFcltsMngNoModule() {}

GamPopupFcltsMngNoModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupFcltsMngNoModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	
	//시설물 업무 구분(조회조건)
	if(params['fcltsJobSe'] != undefined) {
		this.$('#sFcltsJobSe').val(params['fcltsJobSe']);
	}
	
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectFcltsMngNoInfoList.do',
		dataType: "json",
		colModel : [
					{display:"항만시설명",  	name:"prtFcltyNm", 			width:200, sortable:true, align:"left"},
					{display:"항만시설규격",  	name:"prtFcltyStndrd", 		width:200, sortable:true, align:"center"},
					{display:"항만시설단위",  	name:"prtFcltyUnit", 		width:150, sortable:true, align:"center"},
					{display:"설치일자",  		name:"prtFcltyInstDt", 		width:90,  sortable:true, align:"center"},
					{display:"변경일자",  		name:"prtFcltyChangeDt", 	width:90,  sortable:true, align:"center"},
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};

GamPopupFcltsMngNoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupFcltsMngNoModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFcltsMngNoForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupFcltsMngNoModule.prototype.returnData = function() {
	var rows = this.$("#grdInfoList").selectedRows();
	if(rows.length>0) {
		this.closeDialog("ok", rows[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupFcltsMngNoModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupFcltsMngNoModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFcltsMngNoForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th width="10%">시설명</th>
                        <td>
                        	<input id="sPrtFcltyNm" type="text" size="20" />
                        	<input id="sFcltsJobSe" type="hidden" />
                        </td>
						<td width="10%"><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">시설물 선택</button>
            	<button id="btnCancel">취소</button>
	        </div>
	    </div>

	</div>
</div>