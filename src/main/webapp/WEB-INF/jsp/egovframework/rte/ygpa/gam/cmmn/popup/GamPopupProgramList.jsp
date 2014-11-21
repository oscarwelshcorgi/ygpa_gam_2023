<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupProgramList.jsp
  * @Description : 프로그램 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.27  kok          최초 생성
  *
  * author kok
  * since 2013.01.27
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamProgramSearchPopupModule() {}

/* 팝업모듈 초기화 */
GamProgramSearchPopupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamProgramSearchPopupModule.prototype.loadComplete = function() {

	this.resizable(true);
	
	// 테이블 설정
	this.$("#programSearchPopupList").flexigrid({
		module: this,
		url: '/cmmn/popup/gamPopupProgramList.do',
		dataType: "json",
		colModel : [
					{display:"프로그램파일명",	name:"progrmFileNm",		width:275, 	sortable:false,		align:"center"},
					{display:"프로그램명", 		name:"progrmKoreanNm",		width:275, 	sortable:false,		align:"center"}
					],
		usepager: true,
		useRp: true,
		//rp: 24,
		showTableToggleBtn: false,
		height: "315"
	});

	this.$("#programSearchPopupList").on('onItemDoubleClick', function(event, module, row, grid, param) {

		module.closeDialog('ok', row);
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamProgramSearchPopupModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
// 			if(this.$("#searchKeyword").val() == ""){
// 				alert("프로그램명을 입력하세요.");
// 				this.$("#searchKeyword").focus();
// 				return;
// 			}
			var searchOpt = this.makeFormArgs("#programSearchPopupForm");
		 	this.$("#programSearchPopupList").flexOptions({params:searchOpt}).flexReload();
		 	
		break;

	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamProgramSearchPopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="programSearchPopupForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />프로그램명 : </th>
							<td><input id="searchKeyword" type="text" size="30" title="검색" /></td>
							<td><button id="searchBtn">조회</button></td>
						</tr>
					</tbody>
				</table>
<!-- 				<div class="emdControlPanel"> -->
<!-- 					<button id="searchBtn">조회</button> -->
<!-- 				</div> -->
			</form>
		</div>
	</div>
	
	<div class="emdPanel">
		<div style="width: 100%; height: 100%; overflow: auto">
			<table id="programSearchPopupList" style="display:none"></table>
		</div>
	</div>
</div>