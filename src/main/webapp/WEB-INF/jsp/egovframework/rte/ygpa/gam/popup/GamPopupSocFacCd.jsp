<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocFacCd.jsp
  * @Description : 비관리청 시설코드 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.09.25  김종민          최초 생성
  *
  * author 김종민
  * since 2014.09.25
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocFacCdModule() {}

GamPopupSocFacCdModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocFacCdModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '<c:url value="/popup/selectSocFacInfoList.do"/>',
		dataType: "json",
		colModel : [
					{display:"시설코드",		name:"facCode", 	width:150, 		sortable:true, 		align:"center"},
					{display:"시설부코드",		name:"facSubCode", 	width:150, 		sortable:true, 		align:"center"},
					{display:"시설명",		name:"facKorNm", 	width:255, 		sortable:true, 		align:"left"}
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

GamPopupSocFacCdModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnSearch":
		var searchOpt=this.makeFormArgs("#gamPopupFacForm");
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

GamPopupSocFacCdModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocFacCdModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFacForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocFacCdModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFacForm">
			<input id="prtAtCode" type="hidden" value="${searchOpt.prtAtCode}" />
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>항코드</th>
                        <td>
                        	<select id="sPrtAtCode">
                                <option value="" selected="selected">전체</option>
                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                    <option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                </c:forEach>
                            </select>
                        </td>					
                        <th>시설코드</th>
                        <td><input id="facCode" type="text" size="3" title="요금코드" maxlength="10" /></td>
						<th>시설부코드</th>
                        <td><input id="facSubCode" type="text" size="2" title="요금코드" maxlength="10" /></td>
                    	<th>시설명</th>
						<td><input id="facKorNm" type="text" size="12" title="요금명" maxlength="12" /></td>
						<td><button id="btnSearch">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">시설 선택</button>
            <button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>