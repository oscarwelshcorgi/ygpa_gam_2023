<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamPopupMenuCreat.jsp
  * @Description : 메뉴생성 팝업
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.01.22  장은성          최초 생성
  *
  * author 장은성
  * since 2013.01.22
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 * 공통 팝업이 아닌 팝업은 이 페이지와 같이 해당 패키지의 하단에 생성 할 것
 */
function GamMenuCreatPopupModule() {}

/* 팝업모듈 초기화 */
GamMenuCreatPopupModule.prototype = new EmdPopupModule(640, 480);

// 팝업이 호출 되었을때 호출 되는 함수
GamMenuCreatPopupModule.prototype.loadComplete = function() {

	this.loadData();

};


// 사용자 설정 함수 추가
GamMenuCreatPopupModule.prototype.onButtonClick = function(buttonId){

	switch(buttonId){
		// 메뉴 조회
		case 'selectMenuList':
			this.loadData();
			throw 0;
		break;

		// 메뉴생성
		case "btnCreate":

			var checkedAuthorForInsert = this.$('#authorCode').val();
			var checkedMenuNoForInsert = "";

			var createMenuList=[];
			$.each(this.menuList, function(i) {
				if($(this).attr("ischecked")) {
					checkedMenuNoForInsert += this.id + ",";
				}
			});


			// 선택된 목록을 생성합니다.
			if(checkedMenuNoForInsert != ""){

				var last = checkedMenuNoForInsert.lastIndexOf(',');
				checkedMenuNoForInsert = checkedMenuNoForInsert.substr(0,last);

				this.doAction('<c:url value="/cmmn/gamMenuCreatInsert.do" />', {checkedAuthorForInsert:checkedAuthorForInsert, checkedMenuNoForInsert:checkedMenuNoForInsert}, function(module, result) {
			 		if(result.resultCode == 0){
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
			//this.closeDialog('ok');
		break;

		case "cancel":
			this.cancelDialog();
		break;
	}
};

GamMenuCreatPopupModule.prototype.onSubmit = function() {
	this.loadData();
};

GamMenuCreatPopupModule.prototype.loadData = function() {
	$("#menuTreeList").empty();

	this.doAction('<c:url value="/cmmn/gamMenuCreatSelect.do" />', {authorCode: this.$('#authorCode').val()}, function(module, result) {
 		if(result.resultCode == 0){
 			var tree = module.$("#menuTreeList");
 			for(var i=0; i<result.listMenulist.length; i++) {
 				var obj = result.listMenulist[i];
 				// console.log("obj.chkYeoBu : "+obj.chkYeoBu);
 				// 해당 리스트를 트리 라이브러리에 맞게 변경 한다.
 				obj.parentid = obj.upperMenuId;
 				obj.id = obj.menuNo;
 				obj.name = obj.menuNm;
 				obj.ischecked = obj.chkYeoBu == "Y";	// 생성할때 다시 ischecked를 chkYeoBu 로 변경하여 생성한다.
 			}

 			var data_obj = {"root" : result.listMenulist};

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
                , collapse_tree: false
                , class_node_highlight: "ui-state-highlight"
                , show_button_check: true
 			});
 			tree.on("onNodeCheckSelected", function(event, module, id, node, sender ){
 				// 체크박스가 선택 됨
 			});
 		}
 	});
};

// 다음 변수는 고정 적으로 정의 해야 함
var popup_instance = new GamMenuCreatPopupModule();
</script>
<div class="dialog">
	<div class="emdPanel">
		<form id="searchPopupGisAssetCode">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>권한코드</th>
						<td><input id="authorCode" type="text" size="30" value="${authorCode}"></td>
						<td><button id="selectMenuList" class="submit">조회</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="emdPanel" class="fillHeight">
		<div style="width: 100%; height:330px;" class="fillHeight">
			<div id="menuTreeList" class="tree" style="position:relative; width:100%; height:100%; z-index:10; overflow: scroll" data-resize="contentFill">
			</div>
		</div>
		<div class="emdControlPanel">
			<button id="btnCreate">메뉴생성</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</div>