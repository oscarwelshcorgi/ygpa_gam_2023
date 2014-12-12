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
		url: '/popup/selectSocFacInfoList.do',
		dataType: "json",
		colModel : [
					{display:"항명",		name:"prtKorNm", 	width:100, 	sortable:true, 	align:"center"},
					{display:"시설코드",	name:"facCode", 	width:100, 	sortable:true, 	align:"center"},
					{display:"시설부코드",	name:"facSubCode", 	width:150, 	sortable:true, 	align:"center"},
					{display:"시설명",	name:"facKorNm", 	width:255, 	sortable:true, 	align:"left"}
			],
		height: "320"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});
};

GamPopupSocFacCdModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocFacCdModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFacForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

GamPopupSocFacCdModule.prototype.returnData = function() {
	var rows = this.$("#grdInfoList").selectedRows();
	if(rows.length>0) {
		this.closeDialog("ok", rows[0]);
	}
	else {
		alert("먼저 입력 하고자 하는 항목을 선택 하십시요.");
	}
};

GamPopupSocFacCdModule.prototype.onButtonClick = function(buttonId) {
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
var popup_instance = new GamPopupSocFacCdModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupFacForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>항코드</th>
                        <td>
                        	<select id="sPrtAtCode">
                                <option value="" selected="selected">전체</option>
                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                	<c:choose>
                                		<c:when test="${ prtAtCdItem.prtAtCode eq searchOpt.prtAtCode }">
                                			<option value="${prtAtCdItem.prtAtCode }" selected="selected">${prtAtCdItem.prtKorNm }</option>
                                		</c:when>
                                		<c:otherwise>
                                			<option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                		</c:otherwise>
                                	</c:choose>
                                </c:forEach>
                            </select>
                        </td>					
                        <th>시설코드</th>
                        <td><input id="sFacCode" type="text" size="4"/></td>
						<th>시설부코드</th>
                        <td><input id="sFacSubCode" type="text" size="3"/></td>
                    	<th>시설명</th>
						<td><input id="sFacKorNm" type="text" size="12"/></td>
						<td><button id="btnSearch">조회</button></td>
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