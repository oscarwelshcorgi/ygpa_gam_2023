<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMenuMngCreate.jsp
  * @Description : 메뉴 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.09  kok          최초 생성
  *
  * author kok
  * since 2014.01.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMenuMngCreateModule() {}

GamMenuMngCreateModule.prototype = new EmdModule(800,600);	// 초기 시작 창크기 지정

// 페이지가 호출 되었을때 호출 되는 함수
GamMenuMngCreateModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#menuMngCreateList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamMenuCreatManageSelect.do" />',
		dataType: "json",
		colModel : [
					{display:'권한코드',			name:'authorCode',		width:210,		sortable:false,		align:'left'},
					{display:'권한명', 			name:'authorNm',		width:200, 		sortable:false,		align:'center'},
					{display:'권한 설명', 		name:'authorDc',		width:110, 		sortable:false,		align:'center'},
					{display:'메뉴생성여부', 		name:'chkYeoBu',		width:100, 		sortable:false,		align:'center'},
					{display:'메뉴생성', 		name:'regYn',			width:80, 		sortable:false,		align:'center', 	displayFormat:'button', 	displayOption:{label:'메뉴생성', className:'selectRoleButton'}}
					],
		height: "auto"
	});

	this.$("#menuMngCreateList").on("onButtonClicked", function(event, module, colId, row, grid, param) {
		var opts = {
			'authorCode': row['authorCode']
		};
		module.doExecuteDialog('selectMenuCreate', '메뉴 생성', '<c:url value="/cmmn/popup/showMenuCreat.do"/>', opts);
	});
};


GamMenuMngCreateModule.prototype.onClosePopup = function(popupId, msg){
	switch (popupId) {
	case 'selectMenuCreate':
		if (msg != 'cancel') {
			alert('메뉴가 생성 되었습니다.');
		} else {
			alert('취소 되었습니다');
		}
		break;
	default:
		alert('알수없는 팝업 이벤트가 호출 되었습니다.');
		throw 0;
		break;
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMenuMngCreateModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#menuMngCreateForm");
		 	this.$("#menuMngCreateList").flexOptions({params:searchOpt}).flexReload();
		 	throw 0;
		break;

		// 신규저장
		case "insertBtn":
			var searchOpt = this.makeFormArgs("#searchGisAssetCode");
		 	this.$("#assetCodeList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 삭제
		case "deleteBtn":
			var searchOpt = this.makeFormArgs("#searchGisAssetCode");
		 	this.$("#assetCodeList").flexOptions({params:searchOpt}).flexReload();
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMenuMngCreateModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="menuMngCreateForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>보안설정대상ID</th>
							<td>&nbsp;<input id="searchKeyword" type="text" size="80" maxlength="60" title="검색조건" /></td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdTabPage fillHeight" style="overflow: hidden;">
		<table id="menuMngCreateList" style="display:none" class="fillHeight"></table>
	</div>
</div>