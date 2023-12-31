<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSocAgentFInfo.jsp
  * @Description : 비관리청 허가원부 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.09.24  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.09.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<script>

/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamPopupSocAgentFModule() {}

GamPopupSocAgentFModule.prototype = new EmdPopupModule(700, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamPopupSocAgentFModule.prototype.loadComplete = function() {

	this.resizable(true);

	this.$("#grdInfoList").flexigrid({
		module: this,
		url: '/popup/selectSocAgentFInfoList.do',
		dataType: "json",
		colModel : [
					{display:"구분",				name:"se", 				width:30, 		sortable:true, 		align:"center"},
					{display:"공사관리청코드",		name:"prtAtCode", 		width:150, 		sortable:true, 		align:"center"},
					{display:"공사관리청",			name:"prtAtKorNm", 		width:100, 		sortable:true, 		align:"center"},
					{display:"준공년도",			name:"cmplYr", 			width:100, 		sortable:true, 		align:"center"},
					{display:"일련번호",			name:"constNo", 		width:80, 		sortable:true, 		align:"center"},
					{display:"업체코드",			name:"agentCode", 		width:80, 		sortable:true, 		align:"center"},
					{display:"공사업체",			name:"agentName", 		width:100, 		sortable:true, 		align:"center"},
					{display:"공사명",			name:"socCnstNm", 		width:100, 		sortable:true, 		align:"center"},
					{display:"공사항만청코드",		name:"socPrtAtCode", 	width:150, 		sortable:true, 		align:"center"},
					{display:"공사항만",			name:"socPrtAtKorNm", 	width:80, 		sortable:true, 		align:"center"},
					{display:"총공사비",			name:"totalBuildFee", 	width:100, 		sortable:true, 		align:'right',		displayFormat: 'number'},
					{display:"보전처리누계액",		name:"accFee", 			width:150, 		sortable:true, 		align:'right', 		displayFormat: 'number'},
					{display:"준공일자",			name:"cmplDt", 			width:100, 		sortable:true, 		align:"center"},
					{display:"비고",				name:"remark", 			width:100, 		sortable:true, 		align:"center"}
			],
		height: "300"
	});

	this.$("#grdInfoList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		// 이벤트내에선 모듈에 대해 선택한다.
		module.closeDialog("ok", row);
	});

	// 페이지로딩시 디폴트 데이타 로딩
	this.onLoadData();

};


<%
/**
 * @FUNCTION NAME : onLoadData
 * @DESCRIPTION   : 페이지 로딩 시 default데이타 로딩 함수
 * @PARAMETER     : NONE
**/
%>
GamPopupSocAgentFModule.prototype.onLoadData = function() {
	this.$("#sPrtAtCode").val('622');
	this.loadData();
};


// 사용자 설정 함수 추가
GamPopupSocAgentFModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {

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

GamPopupSocAgentFModule.prototype.onSubmit = function() {
	this.loadData();
};

GamPopupSocAgentFModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs("#gamPopupSocAgentFForm");
 	this.$("#grdInfoList").flexOptions({params:searchOpt}).flexReload();
};


// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamPopupSocAgentFModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="gamPopupSocAgentFForm">
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
						<th>업체코드</th>
                        <td>
                        	<input id="sAgentCode" type="text" size="10">
                        </td>
                        <th>업체명</th>
                        <td>
                           	<input id="sAgentName" type="text" size="15">
                        </td>
                        <th>구분</th>
                            <td>
                                <input id="sSe" class="ygpaCmmnCd" data-default-prompt="전체" data-code-id="GAM074" />
                            </td>
						<td>
							<button class="buttonSearch">조회</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<div class="emdPanel fillHeight">
	        <table id="grdInfoList" style="display: none" class="fillHeight"></table>
	        <div class="emdControlPanel">
	            <button id="btnOk">허가원부 선택</button>
            	<button id="cancel">취소</button>
	        </div>
	    </div>

	</div>
</div>