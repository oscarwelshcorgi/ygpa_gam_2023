<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupGroupList.jsp
  * @Description : 그룹조회 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.24  kok          최초 생성
  *
  * author kok
  * since 2013.01.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamGroupSearchPopupModule() {}

/* 팝업모듈 초기화 */
GamGroupSearchPopupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamGroupSearchPopupModule.prototype.loadComplete = function() {

	this.resizable(true);
	
	// 테이블 설정
	this.$("#groupSearchPopupList").flexigrid({
		module: this,
		url: '/cmmn/popup/gamPopupGroupList.do',
		dataType: "json",
		colModel : [
					{display:"그룹 ID", 		name:"groupId",		width:200, 	sortable:false,		align:"center"},
					{display:"그룹명", 		name:"groupNm",		width:200, 	sortable:false,		align:"center"}
					],
		usepager: true,
		useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "315"
	});

	this.$("#groupSearchPopupList").on('onItemDoubleClick', function(event, module, row, grid, param) {

		module.closeDialog('ok', row["groupId"]);
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamGroupSearchPopupModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#groupSearchPopupForm");
		 	this.$("#groupSearchPopupList").flexOptions({params:searchOpt}).flexReload();
		 	
		break;

		// 등록
		case "choiceBtn":

			
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamGroupSearchPopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="groupSearchPopupForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>그룹 명 : </th>
							<td><input id="searchKeyword" type="text" size="30" title="검색" value="${searchKeyword}" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
					<button id="choiceBtn">확인</button>
				</div>
			</form>
		</div>
	</div>
	
	<div class="emdPanel">
		<div style="width: 100%; height: 100%; overflow: auto">
			<table id="groupSearchPopupList" style="display:none"></table>
		</div>
	</div>
</div>