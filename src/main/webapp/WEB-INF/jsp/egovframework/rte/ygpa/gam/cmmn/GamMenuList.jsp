<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamMenuList.jsp
  * @Description : 메뉴 리스트
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.09  장은성          최초 생성
  *
  * author 장은성
  * since 2014.01.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamMenuMngModule() {}

GamMenuMngModule.prototype = new EmdModule(840, 540);

// 페이지가 호출 되었을때 호출 되는 함수
GamMenuMngModule.prototype.loadComplete = function() {
	this.loadMenu();
};

GamMenuMngModule.prototype.loadMenu = function() {
	this.$("#menuTreeList").empty();

	this.doAction('/cmmn/mnu/selectMenuList.do', null, function(module, result) {
 		if(result.resultCode == 0){
 			var treeNode=module.$('#menuTreeList');
  			var treeItems = [];

  			for(var i=0; i<result.listMenulist.length; i++) {
				var mnuItem=result.listMenulist[i];
				treeItems[treeItems.length]=[mnuItem.menuNo, mnuItem.upperMenuId, mnuItem.menuNm];
			}


			module.tree=new dhtmlXTreeObject(treeNode.attr('id'),"100%","100%",0);
			module.tree.setImagePath("<c:url value='/js/codebase/imgs/dhxtree_skyblue/'/>");
			module.tree.loadJSArray(treeItems);

			module.tree.setUserData('module', module);
			module.tree.module=module;
			module.tree.attachEvent("onSelect", function(id){

// 				this.module.$('#menuNo').val(id);
				module.doAction('/cmmn/mnu/selectMenuDetail.do', {id : id }, function(module, result) {
					result.resultVO.beforeMenuNo=result.resultVO.menuNo;
					module.makeFormValues('#menuManageVO', result.resultVO);
				});
			});
 		}
 	});
};

/**
 * 정의 된 버튼 클릭 시
 */
 GamMenuMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
	case "refreshBtn":
		this.loadMenu();
		break;
		// 조회
		case "searchBtn":

		break;

		// 목록
		case "listBtn":
			this.$("#menuMngListTab").tabs("option", {active: 0});
		break;

		// 추가
		case "addBtn":
			this.$("#menuMngListTab").tabs("option", {active: 1});
			this.$("#menuManageVO :input").val("");
			this.$("#beforeMenuNo").val(-1);
			this.$("#cmd").val("insert");
		break;

		// 저장
		case "saveBtn":
		 	var inputVO=this.makeFormArgs("#menuManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('/cmmn/gamMenuListInsert.do', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#menuMngListTab").tabs("option", {active: 0});
			 			module.$("#menuManageVO :input").val("");
			 		}
			 		module.loadMenu();
			 		alert(result.resultMsg);
			 	});
			}
			else {
			 	this.doAction('/cmmn/gamMenuListUpdt.do', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#menuMngListTab").tabs("option", {active: 0});
			 			module.$("#menuManageVO :input").val("");
			 		}
				 		module.loadMenu();
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO=this.makeFormArgs("#menuManageVO");
			 	this.doAction('/cmmn/gamMenuListDelete.do', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			module.$("#menuMngListTab").tabs("option", {active: 0});
			 			module.$("#menuManageVO :input").val("");
			 		}
			 		module.loadMenu();
			 		alert(result.resultMsg);
			 	});
			}
		break;
		// 프로그램 조회 팝업
		case "popupBtn":
			this.doExecuteDialog('selectProgramPopList', '프로그램목록조회', '/cmmn/popup/gamPopupProgramView.do', {progrmFileNm: this.$("#progrmFileNm").val()});
		break;
	}
};


/**
 * 탭 변경시 실행 이벤트
 */
GamMenuMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
	case 'tabs1':
		break;
	case 'tabs2':
		var row = this.$('#menuMngList').selectedRows();
		if(row.length == 0){
			this.$('#cmd').val('insert');
		}else{
			this.$('#cmd').val('modify');
			this.$('#menuNo').val(row['menuNo']);						// 메뉴No
			this.$('#menuOrdr').val(row['menuOrdr']);					// 메뉴순서
			this.$('#menuNm').val(row['menuNm']);						// 메뉴명
			this.$('#upperMenuId').val(row['upperMenuId']);				// 상위메뉴No
			this.$('#progrmFileNm').val(row['progrmFileNm']);			// 파일명
			this.$('#relateImageNm').val(row['relateImageNm']);			// 관련이미지명
			this.$('#relateImagePath').val(row['relateImagePath']);		// 관련이미지경로
			this.$('#menuDc').val(row['menuDc']);						// 메뉴설명
		}
		break;
	}
};
/*
 *
 */
 GamMenuMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		case "selectProgramPopList":
			this.$("#progrmFileNm").val(value.progrmFileNm);
		break;

		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');

		break;
	}
};
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamMenuMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<div class="emdPanel">
		<div class="viewStack">
			<form id="menuMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
<!-- 							<th>메뉴 명</th> -->
<!-- 							<td><input name="searchKeyword" id="searchKeyword" type="text" size="80" value="${searchVO.searchKeyword }"  maxlength="60" title="검색조건" /></td> -->
<!-- 							<td> -->
<!-- 								<button id="searchBtn">조회</button> -->
<!-- 								<button id="refreshBtn">새로고침</button> -->
<!-- 							</td> -->
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<table style="width:100%; height:100%;">
			<tr>
				<td width="240 px">
					<div id="menuTreeList" class="tree" style="position:relative; left:8px; top:8px; width:224px; height:400px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill">
					</div>
				</td>
				<td>
					<form id="menuManageVO" style="border :1px solid; margin: 8px; padding:9px; border-radius:7px; height:400px;">
						<input type="hidden" id="cmd"/>
						<input type="hidden" id="beforeMenuNo"/>
						<table class="tableForm editForm">
							<colgroup>
								<col width="30%"/>
								<col />
							</colgroup>
							<tr>
								<th><span class="label">메뉴No</span></th>
								<td><input type="text" size="25" id="menuNo"/></td>
							</tr>
							<tr>
								<th><span class="label">메뉴순서</span></th>
								<td><input type="text" size="25" id="menuOrdr"/></td>
							</tr>
							<tr>
								<th><span class="label">메뉴명</span></th>
								<td><input type="text" size="25" id="menuNm"/></td>
							</tr>
							<tr>
								<th><span class="label">상위메뉴No</span></th>
								<td><input type="text" size="25" id="upperMenuId"/></td>
							</tr>
							<tr>
								<th><span class="label">파일명</span></th>
								<td><input type="text" size="25" id="progrmFileNm"/><button id="popupBtn">프로그램파일명 검색</button></td>
							</tr>
							<tr>
								<th><span class="label">관련이미지명</span></th>
								<td><input type="text" size="25" id="relateImageNm"/></td>
							</tr>
							<tr>
								<th><span class="label">관련이미지경로</span></th>
								<td><input type="text" size="25" id="relateImagePath"/></td>
							</tr>
							<tr>
								<th><span class="label">메뉴설명</span></th>
								<td colspan="3"><textarea cols="50" rows="12" id="menuDc"></textarea></td>
							</tr>
						</table>
					</form>
					<div class="emdControlPanel">
						<button id="addBtn">추가</button>
						<button id="saveBtn">저장</button>
						<button id="deleteBtn" style="margin-right:13px;">삭제</button>
<!-- 						<button id="refreshBtn">새로고침</button> -->
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>