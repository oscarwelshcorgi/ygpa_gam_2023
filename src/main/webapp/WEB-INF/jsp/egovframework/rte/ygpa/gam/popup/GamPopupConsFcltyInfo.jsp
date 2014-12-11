<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupConsFcltyInfo.jsp
  * @Description : 건축시설 정보 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.18  김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.18
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupConsFcltyInfoModule() {}

GamPopupConsFcltyInfoModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupConsFcltyInfoModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectConsFcltyInfoList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"gisAssetsPrtAtCode",	width:40,		sortable:false,		align:"center"},
					{display:"항코드명",		name:"gisAssetsPrtAtName",	width:60,		sortable:false,		align:"center"},
					{display:"자산코드",		name:"gisAssetsDisplay",	width:60,		sortable:false,		align:"center"},
					{display:"건축시설코드", 	name:"gisPrtFcltyDisplay",	width:80,		sortable:false,		align:"center"},
					{display:"건축시설명",		name:"prtFcltyNm",			width:180,		sortable:false,		align:"left"},
					{display:"건축시설분류",	name:"prtFcltyCdNm",		width:80,		sortable:false,		align:"center"}
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};

GamPopupConsFcltyInfoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupConsFcltyInfoModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupConsFcltyInfoForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupConsFcltyInfoModule.prototype.returnData = function() {
	var row = this.$("#grdInfoList").selectedRows();
	if(row.length>0) {
		this.closeDialog("ok", row[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupConsFcltyInfoModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupConsFcltyInfoModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupConsFcltyInfoForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>항코드</th>
						<td><input id="sPrtAtCode" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM019" /></td>
						<th>건축시설명</th>
						<td>
							<input id="sPrtFcltyNm" type="text" size="15" />
						</td>
						<th>건축시설분류</th>
						<td>
							<input id="searchFcltyCd" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM057" />
						</td>
						<td><button id="btnSearch" class="buttonSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">건축시설 선택</button>
            	<button id="btnCancel">취소</button>
	        </div>
	    </div>
	</div>
</div>