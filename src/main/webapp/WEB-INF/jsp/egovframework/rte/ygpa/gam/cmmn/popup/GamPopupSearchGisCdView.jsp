<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupSearchGisCdView.jsp
  * @Description : 자산관리 코드 검색
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.2.5  kok          최초 생성
  *
  * author kok
  * since 2013.2.5
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamSearchGisCdPopupModule() {}

/* 팝업모듈 초기화 */
GamSearchGisCdPopupModule.prototype = new EmdPopupModule(550, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamSearchGisCdPopupModule.prototype.loadComplete = function() {

	this.resizable(true);
	
	// 테이블 설정
	this.$("#searchGisCdPopupList").flexigrid({
		module: this,
		url: '<c:url value="/fclty/gamSearchGisCdPopupList.do" />',
		dataType: "json",
		colModel : [
					{display:"GIS 자산 SUB 코드",		name:"gisAssetsSubCd",	width:150, 	sortable:false,		align:"center"},
					{display:"GIS 자산 코드", 		name:"gisAssetsCd",		width:100, 	sortable:false,		align:"center"},
					{display:"GIS 자산 명", 			name:"gisAssetsNm",		width:200, 	sortable:false,		align:"center"}
					],
		usepager: true,
		useRp: true,
		showTableToggleBtn: false,
		height: "315"
	});

	this.$("#searchGisCdPopupList").on('onItemDoubleClick', function(event, module, row, grid, param) {

		module.closeDialog('ok', row);
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamSearchGisCdPopupModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			/*if(this.$("#searchKeyword").val() == ""){
				alert("GIS 자산 명을 입력하세요.");
				this.$("#searchKeyword").focus();
				return;
			}*/
			var searchOpt = this.makeFormArgs("#searchGisCdPopupForm");
		 	this.$("#searchGisCdPopupList").flexOptions({params:searchOpt}).flexReload();
		 	throw 0;
		break;

	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamSearchGisCdPopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchGisCdPopupForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />GIS 자산 명 : </th>
							<td><input id="searchKeyword" type="text" size="30" title="검색" /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
				</div>
			</form>
		</div>
	</div>
	
	<div class="emdPanel">
		<div style="width: 100%; height: 100%; overflow: auto">
			<table id="searchGisCdPopupList" style="display:none"></table>
		</div>
	</div>
</div>