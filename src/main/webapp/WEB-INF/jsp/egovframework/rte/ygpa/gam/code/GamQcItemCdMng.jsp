<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamQcItemCdMng.jsp
 * @Description : 점검 항목 코드 관리 화면
 * @Modification Information
 *
 *   수정일         수정자               수정내용
 *  ----------     ---------    ---------------------------
 *  2014.12.10  	ACEWOLF          화면단 최초 생성
 *
 * author ACEWOLF
 * since 2014.12.20
 *
 * Copyright (C) 2014 by LFIT  All right reserved.
**/
%>
<%
/******************************** SCRIPT START ********************************/
%>

<script>

<%
/**
 * @FUNCTION NAME : GamQcItemCdMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamQcItemCdMngModule() {}

GamQcItemCdMngModule.prototype = new EmdModule(800, 600);

GamQcItemCdMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectQcItemCdMng.do',
		dataType : "json",
		colModel : [
					{display:"단계",				name:"depthSort",		width:50,		sortable:true,	align:"center"},
					{display:"점검 항목 코드",		name:"qcItemCd",		width:105,		sortable:true,	align:"center"},
					{display:"점검 항목 명",		name:"qcItemNm",		width:180,		sortable:true,	align:"left"},
					{display:"시설물 업무 구분",	name:"fcltsJobSeNm",	width:110,		sortable:true,	align:"left"},
					{display:"점검 항목 상위",		name:"qcItemUpperNm",	width:220,		sortable:true,	align:"left"},
					{display:"사용 여부",			name:"useYn",			width:77,		sortable:true,	align:"center"},
					{display:"점검 항목 상세",		name:"qcItemDtls",		width:400,		sortable:true,	align:"left"}
					],
		showTableToggleBtn : false,
		height : 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val(data.totalCount);
			module.makeDivValues('#listSumForm', data);
			return data;
		}
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.qcItemCd;
		module._saveFcltsJobSe = row.fcltsJobSe;
		module._saveDepthSort = row.depthSort;
		module._saveQcItemUpperCd = row.qcItemUpperCd;
		module._saveQcItemUpperNm = row.qcItemUpperNm;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.qcItemCd;
		module._saveFcltsJobSe = row.fcltsJobSe;
		module._saveDepthSort = row.depthSort;
		module._saveQcItemUpperCd = row.qcItemUpperCd;
		module._saveQcItemUpperNm = row.qcItemUpperNm;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#fcltsJobSe').on('change',{module:this}, function(event){
		event.data.module.setFcltsJobSeNm();
		event.data.module.getNewQcItemCd();
	});

	this.$('#depthSort').on('keyup change',{module:this}, function(event){
		event.data.module.setFcltsJobSeNm();
		event.data.module.setQcItemUpperCd();
		event.data.module.getNewQcItemCd();
	});

	this.$('#qcItemNm').on('change',{module:this}, function(event){
		qcItemNm = event.data.module.$("#qcItemNm").val();
		if (event.data.module.$("#qcItemDtls").val() == "") {
			event.data.module.$("#qcItemDtls").val(qcItemNm);
		}
	});

	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

	if (EMD.userinfo.mngFcltyCd != null && EMD.userinfo.mngFcltyCd != "*") {
		this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);
		this.$('#sFcltsJobSe').disable();
	}
	
	console.log('1');
};

<%
/**
 * @FUNCTION NAME : displayTreeData
 * @DESCRIPTION   : 항목을 TREE형태로 보여준다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.displayTreeData = function() {

	this.$("#qcItemTreeList").empty();
	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var qcItemCd = '1' + this.$('#qcItemCd').val().substring(1,9);
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		return;
	}
	this.doAction('/code/gamSelectQcItemCdMngTree.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var qcItemTreeNode = module.$('#qcItemTreeList');
				var qcItemTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var qcItem = result.resultList[i];
					qcItemTreeItems[qcItemTreeItems.length] = [qcItem.qcItemCd, qcItem.qcItemUpperCd, qcItem.qcItemNm];
				}
				module.tree = new dhtmlXTreeObject(qcItemTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("<c:url value='/js/codebase/imgs/dhxtree_skyblue/'/>");
				module.tree.loadJSArray(qcItemTreeItems);
				module.tree.setUserData('module', module);
				module.tree.openAllItems(0);
				module.tree.module = module;
				if (qcItemCd != "") {
					module.tree.selectItem(qcItemCd);
					module.tree.focusItem(qcItemCd);
				}
			}
		}
	});

};

<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
GamQcItemCdMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupQcItemUpperCd':
			if (msg == 'ok') {
				this.$('#qcItemUpperCd').val(value.qcItemCd);
				this.$('#qcItemUpperNm').val(value.qcItemNm);
				this.getNewQcItemCd();
				this.$('#qcItemNm').focus();
			}
			break;
	}

};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamQcItemCdMngModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnInsert':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.makeFormValues('#detailForm', {});
			this.makeDivValues('#detailForm', {});
			this.disableDetailInputItem();
			this.addData();
			break;
	    case 'btnSave':
	    	this.saveData();
			break;
		case 'btnDelete':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.enableDetailInputItem();
				this.deleteData();
			}
			break;
		case 'btnRemove':
			this.deleteData();
			break;
		case 'btnExcelDownload':
			this.downloadExcel();
			break;
		case 'popupQcItemUpperCd':
			var fcltsJobSe = this.$('#fcltsJobSe').val();
			var depthSort = this.$('#depthSort').val();
			if (depthSort == "2") {
				depthSort = "1";
			} else if (depthSort == "3") {
				depthSort = "2";
			} else if (depthSort == "4") {
				depthSort = "3";
			} else if (depthSort == "5") {
				depthSort = "4";
			} else {
				depthSort = "";
			}
            var searchOpts = {
				'sFcltsJobSe':fcltsJobSe,
				'sDepthSort':depthSort
            };
			this.doExecuteDialog('popupQcItemUpperCd', '점검 항목 상위 선택', '/popup/showQcItemCdPopup.do', null, searchOpts);
			break;
	    case 'btnFirstData':
	    	this.firstData();
			break;
	    case 'btnPrevData':
	    	this.prevData();
			break;
	    case 'btnNextData':
	    	this.nextData();
			break;
	    case 'btnLastData':
	    	this.lastData();
			break;
	}

};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.onSubmit = function() {

	this._mainmode = 'query';
	this._mainKeyValue = '';
	this._searchButtonClick = true;
	this.loadData();
	this.enableListButtonItem();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.loadData = function() {

	this.$("#mainTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : refreshData
 * @DESCRIPTION   : DATA REFRESH (LIST)
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.refreshData = function() {

	var searchOpt=this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamQcItemCdMngModule.prototype.loadDetail = function(tabId) {

	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);
		this.makeDivValues('#detailForm', row[0]);
	} else {
		var searchVO = this.getFormValues('#detailForm');
		this.doAction('/code/gamSelectQcItemCdMngPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#detailForm', result.result);
				module.makeDivValues('#detailForm', result.result);
			}
		});
	}

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.selectData = function() {

	if (this._mainmode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._mainmode != 'insert' && this._mainmode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	if (this._mainKeyValue == "") {
		return;
	}
	var qcItemCd = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"qcItemCd", filter:qcItemCd}]);
	var row = this.$('#mainGrid').selectedRows();
	if (row.length > 0) {
		this._saveFcltsJobSe = row[0].fcltsJobSe;
		this._saveDepthSort = row[0].depthSort;
		this._saveQcItemUpperCd = row[0].qcItemUpperCd;
		this._saveQcItemUpperNm = row[0].qcItemUpperNm;
	}
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.displayTreeData();

};

<%
/**
 * @FUNCTION NAME : firstData
 * @DESCRIPTION   : FIRST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.firstData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		return;
	}
	var firstRowIndex = 0;
	var row = rows[firstRowIndex];
	var qcItemCd = row["qcItemCd"];
	var fcltsJobSe = row["fcltsJobSe"];
	var depthSort = row["depthSort"];
	var qcItemUpperCd = row["qcItemUpperCd"];
	var qcItemUpperNm = row["qcItemUpperNm"];
	if (qcItemCd != "") {
		this.$("#mainGrid").selectFilterRow([{col:"qcItemCd", filter:qcItemCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = qcItemCd;
		this._saveFcltsJobSe = fcltsJobSe;
		this._saveDepthSort = depthSort;
		this._saveQcItemUpperCd = qcItemUpperCd;
		this._saveQcItemUpperNm = qcItemUpperNm;
		this.makeFormValues('#detailForm', rows[firstRowIndex]);
		this.makeDivValues('#detailForm', rows[firstRowIndex]);
		this.enableDetailInputItem();
		this.displayTreeData();
	}

};

<%
/**
 * @FUNCTION NAME : prevData
 * @DESCRIPTION   : PREVIOUS DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.prevData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var prevRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["qcItemCd"];
		if (this._mainKeyValue == keyValue) {
			prevRowIndex = i - 1;
			break;
		}
	}
	if (prevRowIndex < 0) {
		alert("첫번째 자료 입니다!");
		return;
	}
	if (prevRowIndex >= gridRowCount) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	var row = rows[prevRowIndex];
	var qcItemCd = row["qcItemCd"];
	var fcltsJobSe = row["fcltsJobSe"];
	var depthSort = row["depthSort"];
	var qcItemUpperCd = row["qcItemUpperCd"];
	var qcItemUpperNm = row["qcItemUpperNm"];
	if (qcItemCd != "") {
		this.$("#mainGrid").selectFilterRow([{col:"qcItemCd", filter:qcItemCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = qcItemCd;
		this._saveFcltsJobSe = fcltsJobSe;
		this._saveDepthSort = depthSort;
		this._saveQcItemUpperCd = qcItemUpperCd;
		this._saveQcItemUpperNm = qcItemUpperNm;
		this.makeFormValues('#detailForm', rows[prevRowIndex]);
		this.makeDivValues('#detailForm', rows[prevRowIndex]);
		this.enableDetailInputItem();
		this.displayTreeData();
	}

};

<%
/**
 * @FUNCTION NAME : nextData
 * @DESCRIPTION   : NEXT DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.nextData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var nextRowIndex = -1;
	var keyValue = "";
	for (var i=0; i < gridRowCount; i++) {
		var row = rows[i];
		keyValue = row["qcItemCd"];
		if (this._mainKeyValue == keyValue) {
			nextRowIndex = i + 1;
			break;
		}
	}
	if (nextRowIndex < 0) {
		alert("자료 위치가 부정확합니다!");
		return;
	}
	if (nextRowIndex >= gridRowCount) {
		alert("마지막 자료 입니다!");
		return;
	}
	var row = rows[nextRowIndex];
	var qcItemCd = row["qcItemCd"];
	var fcltsJobSe = row["fcltsJobSe"];
	var depthSort = row["depthSort"];
	var qcItemUpperCd = row["qcItemUpperCd"];
	var qcItemUpperNm = row["qcItemUpperNm"];
	if (qcItemCd != "") {
		this.$("#mainGrid").selectFilterRow([{col:"qcItemCd", filter:qcItemCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = qcItemCd;
		this._saveFcltsJobSe = fcltsJobSe;
		this._saveDepthSort = depthSort;
		this._saveQcItemUpperCd = qcItemUpperCd;
		this._saveQcItemUpperNm = qcItemUpperNm;
		this.makeFormValues('#detailForm', rows[nextRowIndex]);
		this.makeDivValues('#detailForm', rows[nextRowIndex]);
		this.enableDetailInputItem();
		this.displayTreeData();
	}

};

<%
/**
 * @FUNCTION NAME : lastData
 * @DESCRIPTION   : LAST DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.lastData = function() {

	if (this._mainmode != 'modify') {
		return;
	}
	if (this._mainKeyValue == "") {
		return;
	}
	var rows = this.$("#mainGrid").flexGetData();
	var gridRowCount = rows.length;
	if (gridRowCount <= 0) {
		alert("자료가 존재하지 않습니다!");
		return;
	}
	var lastRowIndex = gridRowCount - 1;
	var row = rows[lastRowIndex];
	var qcItemCd = row["qcItemCd"];
	var fcltsJobSe = row["fcltsJobSe"];
	var depthSort = row["depthSort"];
	var qcItemUpperCd = row["qcItemUpperCd"];
	var qcItemUpperNm = row["qcItemUpperNm"];
	if (qcItemCd != "") {
		this.$("#mainGrid").selectFilterRow([{col:"qcItemCd", filter:qcItemCd}]);
		this._mainmode = 'modify';
		this._mainKeyValue = qcItemCd;
		this._saveFcltsJobSe = fcltsJobSe;
		this._saveDepthSort = depthSort;
		this._saveQcItemUpperCd = qcItemUpperCd;
		this._saveQcItemUpperNm = qcItemUpperNm;
		this.makeFormValues('#detailForm', rows[lastRowIndex]);
		this.makeDivValues('#detailForm', rows[lastRowIndex]);
		this.enableDetailInputItem();
		this.displayTreeData();
	}

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.addData = function() {

	var fcltsJobSe = this._saveFcltsJobSe;
	var depthSort = this._saveDepthSort;
	var qcItemUpperCd = this._saveQcItemUpperCd;
	var qcItemUpperNm = this._saveQcItemUpperNm;
	if (fcltsJobSe == "A" || fcltsJobSe == "C" || fcltsJobSe == "M" || fcltsJobSe == "E" || fcltsJobSe == "I") {
		this.$('#fcltsJobSe').val(fcltsJobSe);
		this.setFcltsJobSeNm();
	} else {
		this.$('#fcltsJobSe').val("");
		this.$('#fcltsJobSeNm').val("");
	}
	if (depthSort > "0" && depthSort < "6") {
		this.$('#depthSort').val(depthSort);
	} else {
		this.$('#depthSort').val("");
	}
	this.$('#qcItemCd').val("");
	this.$('#qcItemNm').val("");
	this.$('#qcItemDtls').val("");
	this.$('#leafYn').val("Y");
	if (fcltsJobSe != "" && depthSort != "") {
		if (qcItemUpperCd != "") {
			this.$('#qcItemUpperCd').val(qcItemUpperCd);
			this.$('#qcItemUpperNm').val(qcItemUpperNm);
			this.getNewQcItemCd();
		} else {
			this.setQcItemUpperCd();
			this.getNewQcItemCd();
		}
	} else {
		this.$('#qcItemUpperCd').val("");
		this.$('#qcItemUpperNm').val("");
	}
	this.$('#useYn').val("Y");
	this.enableDetailInputItem();
	if (this.$('#qcItemUpperCd').val() != "") {
		this.$('#qcItemNm').focus();
	} else {
		this.$('#fcltsJobSe').focus();
	}

};

<%
/**
 * @FUNCTION NAME : isValidDepthCheck
 * @DESCRIPTION   : 업무구분별 입력할 수 있는 단계체크.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.isValidDepthCheck = function(fcltsJobSe, qcItemUpperCd, depthSort) {
	var result = true;
	
	if (fcltsJobSe == "A" || fcltsJobSe == "C" || fcltsJobSe == "E" || fcltsJobSe == "I") {
		if(depthSort != 2 && depthSort != 3) {
			alert('건축, 토목, 전력, 정보통신 항목은 2, 3단계만 추가 및 수정이 가능합니다.');
			result = false;
		}
	} else {
		var itemStartCode = qcItemUpperCd.substr(0,3);
		if(itemStartCode == 'M01' ) {
			if(depthSort != 3 && depthSort != 4) {
				alert('기계설비항목은 3, 4단계만 추가 및 수정이 가능합니다.');
				result = false;
			}
		} else if(itemStartCode == 'M02') {
			if(depthSort != 4 &&  depthSort != 5) {
				alert('하역장비항목은 4, 5단계만 추가 및 수정이 가능합니다.');
				result = false;
			}
		} else if(itemStartCode == 'M00'){
			alert('기계시설점검항목의 자식항목은 추가 및 수정이 불가능합니다..');
			result = false;
		} else {
			alert('지원되지 않는 상위코드입니다.');
			result = false;
		}
	}
	
	return result;
};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var qcItemCd = this.$('#qcItemCd').val();
	var qcItemNm = this.$('#qcItemNm').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var qcItemUpperCd = this.$('#qcItemUpperCd').val();
	var useYn = this.$('#useYn').val();
	var leafYn = this.$('#leafYn').val();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		alert('시설물 업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (depthSort == "") {
		alert('단계 항목을 입력하세요.');
		this.$("#depthSort").focus();
		return;
	}
	if (qcItemCd == "" || qcItemCd.length != 9) {
		alert('점검 항목 코드가 부정확합니다.');
		this.$("#qcItemCd").focus();
		return;
	}
	if (qcItemNm == "") {
		alert('점검 항목 명이 부정확합니다.');
		this.$("#qcItemNm").focus();
		return;
	}
	if (qcItemNm == "") {
		alert('점검 항목 명이 부정확합니다.');
		this.$("#qcItemNm").focus();
		return;
	}
	if (depthSort > 5 || depthSort < 1) {
		alert('단계가 부정확합니다.');
		this.$("#depthSort").focus();
		return;
	}
	if (leafYn != "N" && leafYn != "Y") {
		alert('LEAF 여부가 부정확합니다.');
		return;
	}
	if (qcItemUpperCd == "" || qcItemUpperCd.length != 9) {
		alert('점검 항목 상위가 부정확합니다.');
		this.$("#qcItemUpperCd").focus();
		return;
	}
	if (fcltsJobSe != qcItemCd.substring(0,1)) {
		alert('점검 항목 코드가 시설물 업무 구분과 일치하지 않습니다.');
		this.$("#qcItemCd").focus();
		return;
	}
	if (fcltsJobSe != qcItemUpperCd.substring(0,1)) {
		alert('점검 항목 상위가 시설물 업무 구분과 일치하지 않습니다.');
		this.$("#qcItemUpperCd").focus();
		return;
	}
	if (useYn != "N" && useYn != "Y") {
		alert('사용 여부가 부정확합니다.');
		this.$("#useYn").focus();
		return;
	}
	
	if (!this.isValidDepthCheck(fcltsJobSe, qcItemUpperCd, depthSort)) {
		return;
	}
	
	if (this._mainmode == "insert") {
		this._mainKeyValue = qcItemCd;
		this.doAction('/code/gamInsertQcItemCdMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/code/gamUpdateQcItemCdMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.deleteData = function() {

	var qcItemCd = this.$('#qcItemCd').val();
	if (qcItemCd == "") {
		alert('점검 항목 코드가 부정확합니다.');
		this.$("#qcItemCd").focus();
		return;
	}
	var deleteVO = this.makeFormArgs("#detailForm");
	var lowerDataCnt=0;
	var confirmMessage = "";
	this.doAction('/code/gamSelectQcItemCdMngLowerDataCnt.do', deleteVO, function(module, result) {
		if (result.resultCode != "0") {
			alert('삭제 자료 확인이 실패했습니다!');
			return;
		}
		lowerDataCnt=result.resultList[0]['lowerDataCnt']*1;
		if (lowerDataCnt > 0) {
			confirmMessage = "[" + lowerDataCnt + "]건의 하위 자료도 함께 삭제됩니다!" +
							 "\r\n삭제하시겠습니까?";
		} else {
			confirmMessage = "삭제하시겠습니까?";
		}
		if (confirm(confirmMessage)) {
			module.doAction('/code/gamDeleteQcItemCdMng.do', deleteVO, function(module, result) {
				if (result.resultCode == "0") {
					module._mainmode = 'query';
					module._mainKeyValue = '';
					module.loadData();
				}
				alert(result.resultMsg);
			});
		}
	});

};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/code/gamExcelDownloadQcItemCdMng.do');

};

<%
/**
 * @FUNCTION NAME : setFcltsJobSeNm
 * @DESCRIPTION   : 시설물 업무 구분을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.setFcltsJobSeNm = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsJobSeNm = "";
	if (fcltsJobSe == "A") {
		mainMngFeeJobSeNm = "건축시설";
	} else if (fcltsJobSe == "C") {
		mainMngFeeJobSeNm = "토목시설";
	} else if (fcltsJobSe == "M") {
		mainMngFeeJobSeNm = "기계시설";
	} else if (fcltsJobSe == "E") {
		mainMngFeeJobSeNm = "전기시설";
	} else if (fcltsJobSe == "I") {
		mainMngFeeJobSeNm = "정보통신시설";
	}
	this.$('#fcltsJobSeNm').val(fcltsJobSeNm);

};

<%
/**
 * @FUNCTION NAME : setQcItemUpperCd
 * @DESCRIPTION   : 점검 항목 상위 코드를 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.setQcItemUpperCd = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var qcItemUpperCd = "";
	var qcItemUpperNm = "";
	if (depthSort == 1) {
		qcItemUpperCd = "000000000";
		qcItemUpperNm = "점검 항목 메인";
	} else if (depthSort == 2) {
		if (fcltsJobSe == "A") {
			qcItemUpperCd = "A00000000";
			qcItemUpperNm = "건축 시설 점검 항목";
		} else if (fcltsJobSe == "C") {
			qcItemUpperCd = "C00000000";
			qcItemUpperNm = "토목 시설 점검 항목";
		} else if (fcltsJobSe == "M") {
			qcItemUpperCd = "M00000000";
			qcItemUpperNm = "기계 시설 점검 항목";
		} else if (fcltsJobSe == "E") {
			qcItemUpperCd = "E00000000";
			qcItemUpperNm = "전기 시설 점검 항목";
		} else if (fcltsJobSe == "I") {
			qcItemUpperCd = "I00000000";
			qcItemUpperNm = "정보통신 시설 점검 항목";
		}
	}
	this.$('#qcItemUpperCd').val(qcItemUpperCd);
	this.$('#qcItemUpperNm').val(qcItemUpperNm);

};

<%
/**
 * @FUNCTION NAME : getNewQcItemCd
 * @DESCRIPTION   : 새로운 점검 항목 코드를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.getNewQcItemCd = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var qcItemUpperCd = this.$('#qcItemUpperCd').val();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		this.$('#qcItemCd').val('');
		this.$('#qcItemNm').val('');
		return;
	}
	if (depthSort > 5 || depthSort < 1) {
		this.$('#qcItemCd').val('');
		this.$('#qcItemNm').val('');
		return;
	}
	if (qcItemUpperCd == "") {
		this.$('#qcItemCd').val('');
		this.$('#qcItemNm').val('');
		return;
	}
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/code/gamSelectQcItemCdMngNewQcItemCd.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#qcItemCd').val(result.sNewQcItemCd);
			if (depthSort == 1) {
				var qcItemCheck = result.sNewQcItemCd.substring(0,1);
				var qcItemNm = "";
				if (fcltsJobSe == "A") {
					qcItemNm = "건축 시설 점검 항목";
				} else if (fcltsJobSe == "C") {
					qcItemNm = "토목 시설 점검 항목";
				} else if (fcltsJobSe == "M") {
					qcItemNm = "기계 시설 점검 항목";
				} else if (fcltsJobSe == "E") {
					qcItemNm = "전기 시설 점검 항목";
				} else if (fcltsJobSe == "I") {
					qcItemNm = "정보통신 시설 점검 항목";
				}
				if (qcItemCheck == "!") {
					module.$('#qcItemCd').val("");
					module.$('#qcItemNm').val("");
					module.$('#qcItemDtls').val("");
				} else {
					module.$('#qcItemNm').val(qcItemNm);
					module.$('#qcItemDtls').val(qcItemNm);
				}
			} else {
				module.$('#qcItemNm').val("");
				module.$('#qcItemDtls').val("");
			}
		}
	});

};

<%
/**
 * @FUNCTION NAME : enableListButtonItem
 * @DESCRIPTION   : LIST 버튼항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.enableListButtonItem = function() {

	if (this._mainmode == "insert") {
		this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
		this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
	} else {
		this.$('#btnAdd').enable();
		this.$('#btnAdd').removeClass('ui-state-disabled');
		var row = this.$('#mainGrid').selectedRows()[0];
		if (row == null) {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
			return;
		}
		if (this._mainKeyValue != "") {
			this.$('#btnDelete').enable();
			this.$('#btnDelete').removeClass('ui-state-disabled');
		} else {
			this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : enableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#fcltsJobSe').enable();
		this.$('#qcItemCd').enable();
		this.$('#qcItemNm').enable();
		this.$('#qcItemDtls').enable();
		this.$('#depthSort').enable();
		this.$('#useYn').enable();
		this.$('#qcItemUpperCd').disable();
		this.$('#qcItemUpperNm').disable();
		this.$('#popupQcItemUpperCd').enable();
		this.$('#popupQcItemUpperCd').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
		this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#fcltsJobSe').disable();
			this.$('#qcItemCd').disable();
			this.$('#qcItemNm').enable();
			this.$('#qcItemDtls').enable();
			this.$('#depthSort').disable();
			this.$('#useYn').enable();
			this.$('#qcItemUpperCd').disable();
			this.$('#qcItemUpperNm').disable();
			this.$('#popupQcItemUpperCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
			this.$('#btnFirstData').enable();
			this.$('#btnFirstData').removeClass('ui-state-disabled');
			this.$('#btnPrevData').enable();
			this.$('#btnPrevData').removeClass('ui-state-disabled');
			this.$('#btnNextData').enable();
			this.$('#btnNextData').removeClass('ui-state-disabled');
			this.$('#btnLastData').enable();
			this.$('#btnLastData').removeClass('ui-state-disabled');
		} else {
			this.$('#fcltsJobSe').disable();
			this.$('#qcItemCd').disable();
			this.$('#qcItemNm').disable();
			this.$('#qcItemDtls').disable();
			this.$('#depthSort').disable();
			this.$('#useYn').disable();
			this.$('#qcItemUpperCd').disable();
			this.$('#qcItemUpperNm').disable();
			this.$('#popupQcItemUpperCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
			this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
			this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});
		}
	}

};

<%
/**
 * @FUNCTION NAME : disableDetailInputItem
 * @DESCRIPTION   : DETAIL 입력항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamQcItemCdMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsJobSe').disable();
	this.$('#qcItemCd').disable();
	this.$('#qcItemNm').disable();
	this.$('#qcItemDtls').disable();
	this.$('#depthSort').disable();
	this.$('#useYn').disable();
	this.$('#qcItemUpperCd').disable();
	this.$('#qcItemUpperNm').disable();
	this.$('#popupQcItemUpperCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFirstData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnPrevData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnNextData').disable({disableClass:"ui-state-disabled"});
	this.$('#btnLastData').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
 GamQcItemCdMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			if (this._mainmode=="modify") {
				this.loadDetail(oldTabId);
				this.enableDetailInputItem();
			} else if (this._mainmode=="insert") {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
				this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				this.disableDetailInputItem();
			}
			this.displayTreeData();
			break;
	}

};

var module_instance = new GamQcItemCdMngModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
				<table style="width:100%;" class="searchPanel">
					<tbody>
						<tr>
							<th>점검 항목</th>
							<td>
								<input id="sQcItemCd" type="text" size="6" maxlength="9"/>
							</td>
							<th>점검 항목 명</th>
							<td>
								<input id="sQcItemNm" type="text" size="20" maxlength="100"/>
							</td>
							<th>업무 구분</th>
							<td>
								<select id="sFcltsJobSe">
									<option value="" selected>전체</option>
									<option value="A">건축시설</option>
									<option value="C">토목시설</option>
									<option value="M">기계시설</option>
									<option value="E">전기시설</option>
									<option value="I">정보통신시설</option>
								</select>
							</td>
							<th>단계</th>
							<td>
								<input id="sDepthSort" type="text" size="1" maxlength="1"/>
							</td>
							<td>
								<button class="buttonSearch">조회</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 21. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 211. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">점검 항목 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">점검 항목 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow:hidden;" >
				<table id="mainGrid" style="display:none;" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width=10%; height=20; text-align:center;">자료수</th>
								<td>
									<input type="text" size="9" id="totalCount" class="ygpaNumber" disabled="disabled"/>
								</td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
									<button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<table class="detailPanel" style="width:100%;">
							<tr>
								<th style="width:15%; height:18;">업　무　구　분</th>
								<td>
									<input id="fcltsJobSeNm" type="hidden"/>
									<select id="fcltsJobSe" data-required="true">
										<option value="A">건축시설</option>
										<option value="C">토목시설</option>
										<option value="M">기계시설</option>
										<option value="E">전기시설</option>
										<option value="I">정보통신시설</option>
									</select>
								</td>
								<th style="width:15%; height:18;">단계 / LEAF  여부</th>
								<td>
									<input type="text" id="depthSort" size="21" maxlength="1" data-required="true"/>
									<input type="text" id="leafYn" size="22" maxlength="1" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 상위</th>
								<td>
									<input type="text" id="qcItemUpperCd" size="11" maxlength="9" data-required="true"/>
									<input type="text" id="qcItemUpperNm" size="20" maxlength="100"/>
									<button id="popupQcItemUpperCd" class="popupButton">선택</button>
								</td>
								<th style="width:15%; height:18;">사　용　여　부</th>
								<td>
									<input type="text" id="useYn" size="45" maxlength="1"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 코드</th>
								<td>
									<input type="text" id="qcItemCd" size="45" maxlength="9"/>
								</td>
								<th style="width:15%; height:18;">점검　항목　명</th>
								<td>
									<input type="text" id="qcItemNm" size="45" maxlength="100" data-required="true"/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">점검 항목 상세</th>
								<td colspan="3">
									<input type="text" id="qcItemDtls" size="114" maxlength="200"/>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
								<button id="btnFirstData">처음　자료</button>
								<button id="btnPrevData">이전　자료</button>
								<button id="btnNextData">다음　자료</button>
								<button id="btnLastData">마지막　자료</button>
								<button id="btnInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnSave" class="buttonSave">　　저　장　　</button>
								<button id="btnRemove" class="buttonDelete">　　삭　제　　</button>
							</td>
						</tr>
					</table>
					<table style="width:100%;">
						<tr>
							<td>
							<div id="qcItemTreeList" class="tree" style="position:relative; left:1px; top:4px; width:735px; height:280px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill">
							</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
