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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

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

	this.doAction('<c:url value="/sample/mnu/selectMenuList.do" />', null, function(module, result) {
 		if(result.resultCode == 0){
 			var tree = module.$("#menuTreeList");
 			for(var i=0; i<result.listMenulist.length; i++) {
 				var obj = result.listMenulist[i];
 				obj.parentid = obj.upperMenuId;
 				obj.id = obj.menuNo;
 				obj.name = obj.menuNm;
 			}

 			var data_obj = {
 					"root" : result.listMenulist
 			};

 			module.menuList = result.listMenulist;	// 메뉴를 저장한다.

 			tree.btechcotree({
                containerid: tree.attr("id")
                , module: module
                , dataset: data_obj
                , datatype: $treedatatype.Json
                , dataformat: $treedataformat.Linear
                , class_node_collapse: "ui-icon-circle-plus"
                , class_node_expand: "ui-icon-circle-minus"
                , class_node_item: "ui-icon-clipboard"
                , collapse_tree: true
                , class_node_highlight: "ui-state-highlight"
                , show_button_check: false
 			});
 			tree.on("onNodeCheckSelected", function(event, module, id, node, sender ){
 				// 체크박스가 선택 됨

 			});
 			tree.on("onSelectedNode", function(event, module, id, node, sender ) {
 				$.each(module.menuList, function() {
 					if($(this).attr('menuNo')==id) {
 		 				module.$("#menuNo").val($(this).attr('menuNo'));
 		 				module.$('#menuNo').val($(this).attr('menuNo'));						// 메뉴No
 		 				module.$('#menuOrdr').val($(this).attr('menuOrdr'));					// 메뉴순서
 		 				module.$('#menuNm').val($(this).attr('menuNm'));						// 메뉴명
 		 				module.$('#upperMenuId').val($(this).attr('upperMenuId'));				// 상위메뉴No
 		 				module.$('#progrmFileNm').val($(this).attr('progrmFileNm'));			// 파일명
 		 				module.$('#relateImageNm').val($(this).attr('relateImageNm'));			// 관련이미지명
 		 				module.$('#relateImagePath').val($(this).attr('relateImagePath'));		// 관련이미지경로
 		 				module.$('#menuDc').val($(this).attr('menuDc'));						// 메뉴설명
 						return false;
 					}
 					return true;
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
			throw 0;
		break;

		// 목록
		case "listBtn":
			this.$("#menuMngListTab").tabs("option", {active: 0});
		break;

		// 추가
		case "addBtn":
			this.$("#menuMngListTab").tabs("option", {active: 1});
			this.$("#menuManageVO :input").val("");
			this.$("#cmd").val("insert");
		break;

		// 저장
		case "saveBtn":
		 	var inputVO=this.makeFormArgs("#menuManageVO");
			if(this.$("#cmd").val() == "insert") {
			 	this.doAction('<c:url value="/cmmn/gamMenuListInsert.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0});
			 			this.$("#menuManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
			else {
			 	this.doAction('<c:url value="/cmmn/gamMenuListUpdt.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0});
			 			this.$("#menuManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
		break;

		// 삭제
		case "deleteBtn":
			if(confirm("삭제하시겠습니까?")){
				var inputVO=this.makeFormArgs("#menuManageVO");
			 	this.doAction('<c:url value="/cmmn/gamMenuListDelete.do" />', inputVO, function(result) {
			 		if(result.resultCode == 0){
			 			this.$("#menuMngListTab").tabs("option", {active: 0});
			 			this.$("#menuManageVO :input").val("");
			 		}
			 		alert(result.resultMsg);
			 	});
			}
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
							<th>메뉴 명</th>
							<td><input name="searchKeyword" id="searchKeyword" type="text" size="80" value="${searchVO.searchKeyword }"  maxlength="60" title="검색조건" /></td>
							<td>
								<button id="searchBtn">조회</button>
								<button id="addBtn">추가</button>
								<button id="refreshBtn">새로고침</button>
							</td>
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
					<div id="menuTreeList" class="tree" style="position:relative; left:8px; top:8px; width:224px; height:442px; z-index:10; overflow: scroll" data-resize="contentFill">
					</div>
				</td>
				<td>
					<form id="menuManageVO">
						<input type="hidden" id="cmd"/>
						<table class="tableForm">
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
								<td><input type="text" size="75" id="progrmFileNm"/></td>
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
								<td colspan="3"><textarea cols="40" rows="8" id="menuDc"></textarea></td>
							</tr>
						</table>
					</form>
					<div class="emdControlPanel">
						<button id="listBtn">목록</button>
						<button id="saveBtn">저장</button>
						<button id="deleteBtn">삭제</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>