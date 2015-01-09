<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupQcItemCdTree.jsp
  * @Description : 점검 항목 코드 팝업 (Tree구조)(Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.31  김종민          최초 생성
  *
  * author 김종민
  * since 2014.12.31
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
 **/
%>

<script>

function GamPopupQcItemCdTreeModule() {}

GamPopupQcItemCdTreeModule.prototype = new EmdPopupModule(600, 440);

GamPopupQcItemCdTreeModule.prototype.loadComplete = function(params) {
	this.resizable(true);
	
	this.tree = null; //dhtmlX tree 객체
	this._childless = null; //tree에서 자식이 없는 노드 배열.
	
	this._fcltsJobSe = params['fcltsJobSe']; //시설물 구분자
	this.displayTreeData(this._fcltsJobSe);
};

GamPopupQcItemCdTreeModule.prototype.loadData = function() {
};

//디비에 있는 점검결과항목을 Tree구조로 출력
GamPopupQcItemCdTreeModule.prototype.displayTreeData = function(fcltsJobSe) {
	this.$("#qcItemTreeList").empty();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		return;
	}
	var opts = [{ name: 'fcltsJobSe', value: fcltsJobSe}];
	this.doAction('/popup/selectQcItemCdTreeList.do', opts, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var qcItemTreeNode = module.$('#qcItemTreeList');
				var qcItemTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var qcItem = result.resultList[i];
					qcItemTreeItems[qcItemTreeItems.length] = [qcItem.qcItemCd, qcItem.qcItemUpperCd, qcItem.qcItemNm];
				}
				module.tree = new dhtmlXTreeObject(qcItemTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("./js/codebase/imgs/dhxtree_skyblue/");
				module.tree.loadJSArray(qcItemTreeItems);
				module.tree.enableCheckBoxes(true);
				module.tree.enableThreeStateCheckboxes(true);
				//tree item에 체크박스 넣기
				for (var i=0; i < result.resultList.length; i++) {
					var qcItem = result.resultList[i];
					module.tree.showItemCheckbox(qcItem.qcItemCd, true);
				}
				module.tree.setUserData('module', module);
				module.tree.openAllItems(0);
				module.tree.module = module;
				module._childless = module.tree.getAllChildless().split(','); 
			}
		}
	});
};

// 자식이 있는지 없는지 확인하는 함수 : 자식이 없다면 true 반환
GamPopupQcItemCdTreeModule.prototype.isChildless = function(selectedId) {
	var result = false;
	for(var i=0; i<this._childless.length; i++) {
		if(this._childless[i] == selectedId) {
			result = true;
			break;
		}
	}
	return result;
};


// 트리에 있는 선택된 항목을 JSON배열 형태로 변환.
GamPopupQcItemCdTreeModule.prototype.getQcItemList = function(selectedIds) {
	var selectedItems = [];
	var resultList = null;
	for(var i=0; i<selectedIds.length; i++) {
		var selectedId = selectedIds[i];
		if(this.isChildless(selectedId)) {
			var qcItemCd = this._fcltsJobSe + selectedId.substr(1);
			var qcItemNm = this.tree.getItemText(selectedId);
			var parentId = new String(this.tree.getParentId(selectedId));
			var qcItemUpperCd = this._fcltsJobSe + parentId.substr(1);
			var qcItemUpperNm = this.tree.getItemText(parentId);
			selectedItems[selectedItems.length] = {'qcItemCd' : qcItemCd,  'qcItemNm' : qcItemNm, 'qcItemUpperCd' : qcItemUpperCd, 'qcItemUpperNm' : qcItemUpperNm  };
		}
	}
	resultList = {'selectedQcResultItemList': selectedItems};
	return resultList;
};

// 선택버튼을 누를 때 실행.
GamPopupQcItemCdTreeModule.prototype.processOk = function() {
	var selectedIds = this.tree.getAllChecked().split(',');
	if((selectedIds != null) && (selectedIds[0] != ''))
	{
		var selectedItemList = this.getQcItemList(selectedIds);
		this.closeDialog("ok", selectedItemList);
	}
	else {
		alert('선택된 항목이 없습니다.');
	}
};

// 취소버튼을 누를 때 실행.
GamPopupQcItemCdTreeModule.prototype.processCancel = function() {
	this.cancelDialog();
};

GamPopupQcItemCdTreeModule.prototype.onButtonClick = function(buttonId) {
	switch (buttonId) {
		case 'btnOk':
	    	this.processOk();
			break;
	    case 'btnCancel':
	    	this.processCancel();
			break;
	}
};

var popup_instance = new GamPopupQcItemCdTreeModule();
</script>

<div class="dialog">
	<div class="emdPanel">
		<div class="emdPanel fillHeight">
			<table style="width:100%;">
				<tr>
					<td>
					<div id="qcItemTreeList" class="tree" style="position:relative; left:1px; top:4px; width:540px; height:300px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill">
					</div>
					</td>
				</tr>
			</table>
			<div class="emdControlPanel">
				<button id="btnOk">선택</button>
				<button id="btnCancel">취소</button>
			</div>
		</div>
	</div>
</div>
