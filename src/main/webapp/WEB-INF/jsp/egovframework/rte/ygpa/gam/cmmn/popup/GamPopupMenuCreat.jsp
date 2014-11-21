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
			
		break;

		// 메뉴생성
		case "btnCreate":

			var checkedAuthorForInsert = this.$('#authorCode').val();
			var checkedMenuNoForInsert = "";

			this.doAction('/cmmn/gamMenuCreatInsert.do', {checkedAuthorForInsert:checkedAuthorForInsert, checkedMenuNoForInsert:this.tree.getAllCheckedBranches()}, function(module, result) {
		 		if(result.resultCode == 0){
		 			module.closeDialog('ok');
		 			return;
		 		}
		 		alert(result.resultMsg);
		 	});
		break;

		case "cancel":
			this.cancelDialog();
		break;
	}
};

GamMenuCreatPopupModule.prototype.onSubmit = function() {
	this.loadData();
};

GamMenuCreatPopupModule.prototype.findMenuItem=function(menuObj, mnuItem) {
	var obj;
	for(var i=0; i<menuObj.length; i++) {
		obj=menuObj[i];
		if(obj.id==mnuItem.upperMenuNo) {
			return menuObj;
		}
	}
	if(menuObj.item!=null) {
		return this.findMenuItem(menuObj.item, mnuItem);
	}
	return null;
};

GamMenuCreatPopupModule.prototype.loadData = function() {
	$("#menuTreeList").empty();

	this.doAction('/cmmn/gamMenuCreatSelect.do', {authorCode: this.$('#authorCode').val()}, function(module, result) {
 		if(result.resultCode == 0){
 			var treeNode=module.$('#menuTreeList');
  			var treeItems = [];

  			for(var i=0; i<result.listMenulist.length; i++) {
				var mnuItem=result.listMenulist[i];
				treeItems[treeItems.length]=[mnuItem.menuNo, mnuItem.upperMenuId, mnuItem.menuNm, mnuItem.chkYeoBu == "Y"?true:false];
			}


			module.tree=new dhtmlXTreeObject(treeNode.attr('id'),"100%","100%",0);

			module.tree.setSkin('dhx_skyblue');
			module.tree.setImagePath('/js/codebase/imgs/csh_dhx_skyblue/');
			module.tree.enableCheckBoxes(1);
			module.tree.enableThreeStateCheckboxes(true);
			module.tree.loadJSArray(treeItems);
 			for(var i=0; i<treeItems.length; i++) {
 				module.tree.setCheck(treeItems[i][0], treeItems[i][3]);
 			}

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