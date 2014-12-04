<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocAppyInfo.jsp
  * @Description : 비관리청 투자비보전신청업체 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.09.30  김종민          최초 생성
  *
  * author 김종민
  * since 2014.09.30
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocApplyInfoModule() {}

GamPopupSocApplyInfoModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocApplyInfoModule.prototype.loadComplete = function() {
	this.resizable(true);
	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectSocApplyInfoList.do',
		dataType: "json",
		colModel : [
					{display:"항코드",		name:"prtAtCode", 	width:60, 		sortable:true, 		align:"center"},
					{display:"항명",			name:"prtKorNm", 	width:50, 		sortable:true, 		align:"center"},
					{display:"준공년도",		name:"cmplYr", 		width:70, 		sortable:true, 		align:"center"},
					{display:"공사번호",		name:"constNo", 	width:100, 		sortable:true, 		align:"center"},
					{display:"면제요청청코드",	name:"appPrtAtCode",width:90, 		sortable:true, 		align:"center"},
					{display:"면제요청청명",	name:"appPrtKorNm", width:90, 		sortable:true, 		align:"center"},
					{display:"비관리청코드",	name:"appAgentCode",width:90, 		sortable:true, 		align:"center"},
					{display:"비관리청명",		name:"appAgentName",width:80, 		sortable:true, 		align:"center"},
					{display:"요청횟수",		name:"useNo", 		width:70, 		sortable:true, 		align:"center"},
					{display:"보전처리요청액",	name:"exmpAmnt", 	width:100, 		sortable:true, 		align:"center"},
					{display:"보전처리누계액",	name:"exmpAcc", 	width:100, 		sortable:true, 		align:"center"},
					{display:"보전처리시작일",	name:"period_fr", 	width:90, 		sortable:true, 		align:"center"},
					{display:"보전처리마감일",	name:"period_to", 	width:90, 		sortable:true, 		align:"center"},
					{display:"보전처리조건",	name:"exmpCondnm", 	width:90, 		sortable:true, 		align:"center"},
					{display:"사용여부",		name:"useYn", 		width:70, 		sortable:true, 		align:"center"},
					{display:"공사명칭",		name:"item", 		width:100, 		sortable:true, 		align:"center"},
					{display:"특이사항",		name:"remark", 		width:200, 		sortable:true, 		align:"center"},
					{display:"신청일자",		name:"applDate", 	width:70, 		sortable:true, 		align:"center"}
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

GamPopupSocApplyInfoModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
	case "btnSearch":
		var searchOpt=this.makeFormArgs("#gamPopupApplyInfoForm");
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

GamPopupSocApplyInfoModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocApplyInfoModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupFacForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocApplyInfoModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupApplyInfoForm">
			<table class="searchPanel">
				<tbody>
					<tr>
                        <th>항코드</th>
                        <td>
                        	<select id="sPrtAtCode">
                                <option value="">선택</option>
                                <c:forEach  items="${prtAtCdList}" var="prtAtCdItem">
                                	<c:choose>
                                		<c:when test="${ prtAtCdItem.prtAtCode eq searchOpt.sPrtAtCode }">
                                			<option value="${prtAtCdItem.prtAtCode }" selected="selected">${prtAtCdItem.prtKorNm }</option>
                                		</c:when>
                                		<c:otherwise>
                                			<option value="${prtAtCdItem.prtAtCode }">${prtAtCdItem.prtKorNm }</option>
                                		</c:otherwise>
                                	</c:choose>
                                </c:forEach>
	                        </select>
                        </td>
						<th>준공년도</th>
                        <td><input id="sCmplYr" type="text" size="4" title="준공년도" maxlength="10" value="${searchOpt.sCmplYr}" /></td>
                    	<th>공사일련번호</th>
						<td><input id="sConstNo" type="text" size="12" title="공사일련번호" maxlength="12" value="${searchOpt.sConstNo}" /></td>
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