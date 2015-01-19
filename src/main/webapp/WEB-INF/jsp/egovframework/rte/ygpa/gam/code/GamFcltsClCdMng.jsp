<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamFcltsClCdMng.jsp
 * @Description : 시설물 분류 코드 관리 화면
 * @Modification Information
 *
 *   수정일         수정자               수정내용
 *  ----------     ---------    ---------------------------
 *  2015.01.14  	ACEWOLF          화면단 최초 생성
 *
 * author ACEWOLF
 * since 2015.01.14
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
 * @FUNCTION NAME : GamFcltsClCdMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltsClCdMngModule() {}

GamFcltsClCdMngModule.prototype = new EmdModule(800, 600);

GamFcltsClCdMngModule.prototype.loadComplete = function() {

	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/code/gamSelectFcltsClCdMngList.do',
		dataType : "json",
		colModel : [
					{display:"단계",				name:"depthSort",		width:50,		sortable:true,	align:"center"},
					{display:"시설물 분류 코드",	name:"fcltsClCd",		width:110,		sortable:true,	align:"center"},
					{display:"시설물 분류 명",		name:"fcltsClCdNm",		width:200,		sortable:true,	align:"left"},
					{display:"업무 구분",			name:"fcltsJobSeNm",	width:105,		sortable:true,	align:"left"},
					{display:"시설물 분류 상위",	name:"fcltsClUpperNm",	width:200,		sortable:true,	align:"left"},
					{display:"LEAF 여부",			name:"leafYn",			width:80,		sortable:true,	align:"center"}
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
		module._mainKeyValue = row.fcltsClCd;
		module._saveFcltsJobSe = row.fcltsJobSe;
		module._saveDepthSort = row.depthSort;
		module._saveFcltsClUpperCd = row.fcltsClUpperCd;
		module._saveFcltsClUpperNm = row.fcltsClUpperNm;
		module.enableListButtonItem();
    });

	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainmode = 'modify';
		module._mainKeyValue = row.fcltsClCd;
		module._saveFcltsJobSe = row.fcltsJobSe;
		module._saveDepthSort = row.depthSort;
		module._saveFcltsClUpperCd = row.fcltsClUpperCd;
		module._saveFcltsClUpperNm = row.fcltsClUpperNm;
		module.$("#mainTab").tabs("option", {active: 1});
	});

	this.$('#fcltsJobSe').on('change',{module:this}, function(event){
		event.data.module.setFcltsJobSeNm();
		event.data.module.getNewFcltsClCd();
	});

	this.$('#depthSort').on('keyup change',{module:this}, function(event){
		event.data.module.setFcltsJobSeNm();
		event.data.module.setFcltsClUpperCd();
		event.data.module.getNewFcltsClCd();
	});

	this._mainmode = '';
	this._mainKeyValue = '';
	this._searchButtonClick = false;
	this.$('#btnAdd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnDelete').disable({disableClass:"ui-state-disabled"});

};

<%
/**
 * @FUNCTION NAME : displayTreeData
 * @DESCRIPTION   : 항목을 TREE형태로 보여준다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.displayTreeData = function() {

	this.$("#fcltsClTreeList").empty();
	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsClCd = '1' + this.$('#fcltsClCd').val().substring(1,10);
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		return;
	}
	this.doAction('/code/selectFcltsClCdMngTreeList.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var fcltsClTreeNode = module.$('#fcltsClTreeList');
				var fcltsClTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var fcltsCl = result.resultList[i];
					fcltsClTreeItems[fcltsClTreeItems.length] = [fcltsCl.fcltsClCd, fcltsCl.fcltsClUpperCd, fcltsCl.fcltsClCdNm];
				}
				module.tree = new dhtmlXTreeObject(fcltsClTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("./js/codebase/imgs/dhxtree_skyblue/");
				module.tree.loadJSArray(fcltsClTreeItems);
				module.tree.setUserData('module', module);
				module.tree.openAllItems(0);
				module.tree.module = module;
				if (fcltsClCd != "") {
					module.tree.selectItem(fcltsClCd);
					module.tree.focusItem(fcltsClCd);
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
GamFcltsClCdMngModule.prototype.onClosePopup = function(popupId, msg, value) {

	switch (popupId) {
		case 'popupFcltsClUpperCd':
			if (msg == 'ok') {
				this.$('#fcltsClUpperCd').val(value.fcltsClCd);
				this.$('#fcltsClUpperNm').val(value.fcltsClCdNm);
				this.getNewFcltsClCd();
				this.$('#fcltsClCdNm').focus();
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
GamFcltsClCdMngModule.prototype.onButtonClick = function(buttonId) {

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
		case 'popupFcltsClUpperCd':
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
			this.doExecuteDialog('popupFcltsClUpperCd', '시설물 분류 상위 선택', '/popup/showFcltsClCd.do', null, searchOpts);
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
GamFcltsClCdMngModule.prototype.onSubmit = function() {

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
GamFcltsClCdMngModule.prototype.loadData = function() {

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
GamFcltsClCdMngModule.prototype.refreshData = function() {

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
GamFcltsClCdMngModule.prototype.loadDetail = function(tabId) {

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
		this.doAction('/code/gamSelectFcltsClCdMngPk.do', searchVO, function(module, result){
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
GamFcltsClCdMngModule.prototype.selectData = function() {

	if (this._mode == 'query') {
		var gridRowCount = this.$("#mainGrid").flexRowCount();
		if (gridRowCount == 0 && this._searchButtonClick == true) {
			alert('해당 조건의 자료가 존재하지 않습니다!');
		}
		this._searchButtonClick = false;
		return;
	} else if (this._mode != 'insert' && this._mode != 'modify') {
		this._searchButtonClick = false;
		return;
	}
	this._searchButtonClick = false;
	var fcltsClCd = this._mainKeyValue;
	this.$("#mainGrid").selectFilterRow([{col:"fcltsClCd", filter:fcltsClCd}]);
	var row = this.$('#mainGrid').selectedRows();
	if (row.length > 0) {
		this._saveFcltsJobSe = row[0].fcltsJobSe;
		this._saveDepthSort = row[0].depthSort;
		this._saveFcltsClUpperCd = row[0].fcltsClUpperCd;
		this._saveFcltsClUpperNm = row[0].fcltsClUpperNm;
	}
	this._mainmode = 'modify';
	this.loadDetail('detailTab');
	this.enableDetailInputItem();
	this.displayTreeData();

};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 항목을 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.addData = function() {

	var fcltsJobSe = this._saveFcltsJobSe;
	var depthSort = this._saveDepthSort;
	var fcltsClUpperCd = this._saveFcltsClUpperCd;
	var fcltsClUpperNm = this._saveFcltsClUpperNm;
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
	this.$('#fcltsClCd').val("");
	this.$('#fcltsClCdNm').val("");
	if (fcltsJobSe != "" && depthSort != "") {
		if (fcltsClUpperCd != "") {
			this.$('#fcltsClUpperCd').val(fcltsClUpperCd);
			this.$('#fcltsClUpperNm').val(fcltsClUpperNm);
			this.getNewFcltsClCd();
		} else {
			this.setFcltsClUpperCd();
			this.getNewFcltsClCd();
		}
	} else {
		this.$('#fcltsClUpperCd').val("");
		this.$('#fcltsClUpperNm').val("");
	}
	this.$('#leafYn').val("Y");
	this.enableDetailInputItem();
	if (this.$('#fcltsClUpperCd').val() != "") {
		this.$('#fcltsClCdNm').focus();
	} else {
		this.$('#fcltsJobSe').focus();
	}

};

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.saveData = function() {

	var inputVO = this.makeFormArgs("#detailForm");
	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsClCd = this.$('#fcltsClCd').val();
	var fcltsClCdNm = this.$('#fcltsClCdNm').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var fcltsClUpperCd = this.$('#fcltsClUpperCd').val();
	var leafYn = this.$('#leafYn').val();
	if (fcltsJobSe != "0" && fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		alert('시설물 업무 구분이 부정확합니다.');
		this.$("#fcltsJobSe").focus();
		return;
	}
	if (fcltsClCd == "" || fcltsClCd.length != 10) {
		alert('시설물 분류 코드가 부정확합니다.');
		this.$("#fcltsClCd").focus();
		return;
	}
	if (fcltsClCdNm == "") {
		alert('시설물 분류 명이 부정확합니다.');
		this.$("#fcltsClCdNm").focus();
		return;
	}
	if (depthSort > 5 || depthSort < 1) {
		alert('단계가 부정확합니다.');
		this.$("#depthSort").focus();
		return;
	}
	if (fcltsClUpperCd == "" || fcltsClUpperCd.length != 10) {
		alert('시설물 분류 상위가 부정확합니다.');
		this.$("#fcltsClUpperCd").focus();
		return;
	}
	if (fcltsJobSe != fcltsClCd.substring(0,1)) {
		alert('시설물 분류 코드가 시설물 업무 구분과 일치하지 않습니다.');
		this.$("#fcltsClCd").focus();
		return;
	}
	if (fcltsJobSe != fcltsClUpperCd.substring(0,1)) {
		alert('시설물 분류 상위가 시설물 업무 구분과 일치하지 않습니다.');
		this.$("#fcltsClUpperCd").focus();
		return;
	}
	if (leafYn != "N" && leafYn != "Y") {
		alert('LEAF 여부가 부정확합니다.');
		return;
	}
	if (this._mainmode == "insert") {
		this._mainKeyValue = fcltsClCd;
		this.doAction('/code/gamInsertFcltsClCdMng.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.refreshData();
			}
			alert(result.resultMsg);
		});
	} else {
		this.doAction('/code/gamUpdateFcltsClCdMng.do', inputVO, function(module, result) {
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
GamFcltsClCdMngModule.prototype.deleteData = function() {

	var fcltsClCd = this.$('#fcltsClCd').val();
	if (fcltsClCd == "") {
		alert('시설물 분류 코드가 부정확합니다.');
		this.$("#fcltsClCd").focus();
		return;
	}
	var deleteVO = this.makeFormArgs("#detailForm");
	var lowerDataCnt=0;
	var confirmMessage = "";
	this.doAction('/code/gamSelectFcltsClCdMngLowerDataCnt.do', deleteVO, function(module, result) {
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
			module.doAction('/code/gamDeleteFcltsClCdMng.do', deleteVO, function(module, result) {
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
GamFcltsClCdMngModule.prototype.downloadExcel = function() {

	var mainGridRowCount = this.$("#mainGrid").flexRowCount();
	if (mainGridRowCount <= 0) {
		alert("조회된 자료가 없습니다.");
		return;
	}
	this.$('#mainGrid').flexExcelDown('/code/gamExcelDownloadFcltsClCdMng.do');

};

<%
/**
 * @FUNCTION NAME : setFcltsJobSeNm
 * @DESCRIPTION   : 시설물 업무 구분을 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.setFcltsJobSeNm = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var fcltsJobSeNm = "";
	if (fcltsJobSe == "0") {
		mainMngFeeJobSeNm = "전체시설";
	} else if (fcltsJobSe == "A") {
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
 * @FUNCTION NAME : setFcltsClUpperCd
 * @DESCRIPTION   : 시설물 분류 상위 코드를 설정한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.setFcltsClUpperCd = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var fcltsClUpperCd = "";
	var fcltsClUpperNm = "";
	if (depthSort == 1) {
		fcltsClUpperCd = "000000000";
		fcltsClUpperNm = "전체 시설물 분류";
	} else if (depthSort == 2) {
		if (fcltsJobSe == "A") {
			fcltsClUpperCd = "A00000000";
			fcltsClUpperNm = "건축 시설 시설물 분류";
		} else if (fcltsJobSe == "C") {
			fcltsClUpperCd = "C00000000";
			fcltsClUpperNm = "토목 시설 시설물 분류";
		} else if (fcltsJobSe == "M") {
			fcltsClUpperCd = "M00000000";
			fcltsClUpperNm = "기계 시설 시설물 분류";
		} else if (fcltsJobSe == "E") {
			fcltsClUpperCd = "E00000000";
			fcltsClUpperNm = "전기 시설 시설물 분류";
		} else if (fcltsJobSe == "I") {
			fcltsClUpperCd = "I00000000";
			fcltsClUpperNm = "정보통신 시설 시설물 분류";
		}
	}
	this.$('#fcltsClUpperCd').val(fcltsClUpperCd);
	this.$('#fcltsClUpperNm').val(fcltsClUpperNm);

};

<%
/**
 * @FUNCTION NAME : getNewFcltsClCd
 * @DESCRIPTION   : 새로운 시설물 분류 코드를 구한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltsClCdMngModule.prototype.getNewFcltsClCd = function() {

	var fcltsJobSe = this.$('#fcltsJobSe').val();
	var depthSort = Number(this.$('#depthSort').val().replace(/,/gi, ""));
	var fcltsClUpperCd = this.$('#fcltsClUpperCd').val();
	if (fcltsJobSe != "A" && fcltsJobSe != "C" && fcltsJobSe != "M" && fcltsJobSe != "E" && fcltsJobSe != "I") {
		this.$('#fcltsClCd').val('');
		this.$('#fcltsClCdNm').val('');
		return;
	}
	if (depthSort > 5 || depthSort < 1) {
		this.$('#fcltsClCd').val('');
		this.$('#fcltsClCdNm').val('');
		return;
	}
	if (fcltsClUpperCd == "") {
		this.$('#fcltsClCd').val('');
		this.$('#fcltsClCdNm').val('');
		return;
	}
	var searchVO = this.makeFormArgs("#detailForm");
	this.doAction('/code/gamSelectFcltsClCdMngNewCd.do', searchVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$('#fcltsClCd').val(result.sNewFcltsClCd);
			if (depthSort == 1) {
				var fcltsClCheck = result.sNewFcltsClCd.substring(0,1);
				var fcltsClCdNm = "";
				if (fcltsJobSe == "A") {
					fcltsClCdNm = "건축 시설 시설물 분류";
				} else if (fcltsJobSe == "C") {
					fcltsClCdNm = "토목 시설 시설물 분류";
				} else if (fcltsJobSe == "M") {
					fcltsClCdNm = "기계 시설 시설물 분류";
				} else if (fcltsJobSe == "E") {
					fcltsClCdNm = "전기 시설 시설물 분류";
				} else if (fcltsJobSe == "I") {
					fcltsClCdNm = "정보통신 시설 시설물 분류";
				}
				if (fcltsClCheck == "!") {
					module.$('#fcltsClCd').val("");
					module.$('#fcltsClCdNm').val("");
				} else {
					module.$('#fcltsClCdNm').val(fcltsClCdNm);
				}
			} else {
				module.$('#fcltsClCdNm').val("");
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
GamFcltsClCdMngModule.prototype.enableListButtonItem = function() {

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
GamFcltsClCdMngModule.prototype.enableDetailInputItem = function() {

	if (this._mainmode == "insert") {
		this.$('#fcltsJobSe').enable();
		this.$('#fcltsClCd').enable();
		this.$('#fcltsClCdNm').enable();
		this.$('#depthSort').enable();
		this.$('#fcltsClUpperCd').disable();
		this.$('#fcltsClUpperNm').disable();
		this.$('#popupFcltsClUpperCd').enable();
		this.$('#popupFcltsClUpperCd').removeClass('ui-state-disabled');
		this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
		this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
	} else {
		if (this._mainKeyValue != "") {
			this.$('#fcltsJobSe').disable();
			this.$('#fcltsClCd').disable();
			this.$('#fcltsClCdNm').enable();
			this.$('#depthSort').disable();
			this.$('#fcltsClUpperCd').disable();
			this.$('#fcltsClUpperNm').disable();
			this.$('#popupFcltsClUpperCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').enable();
			this.$('#btnInsert').removeClass('ui-state-disabled');
			this.$('#btnSave').enable();
			this.$('#btnSave').removeClass('ui-state-disabled');
			this.$('#btnRemove').enable();
			this.$('#btnRemove').removeClass('ui-state-disabled');
		} else {
			this.$('#fcltsJobSe').disable();
			this.$('#fcltsClCd').disable();
			this.$('#fcltsClCdNm').disable();
			this.$('#depthSort').disable();
			this.$('#fcltsClUpperCd').disable();
			this.$('#fcltsClUpperNm').disable();
			this.$('#popupFcltsClUpperCd').disable({disableClass:"ui-state-disabled"});
			this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
			this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
			this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});
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
GamFcltsClCdMngModule.prototype.disableDetailInputItem = function() {

	this.$('#fcltsJobSe').disable();
	this.$('#fcltsClCd').disable();
	this.$('#fcltsClCdNm').disable();
	this.$('#depthSort').disable();
	this.$('#fcltsClUpperCd').disable();
	this.$('#fcltsClUpperNm').disable();
	this.$('#popupFcltsClUpperCd').disable({disableClass:"ui-state-disabled"});
	this.$('#btnInsert').disable({disableClass:"ui-state-disabled"});
	this.$('#btnSave').disable({disableClass:"ui-state-disabled"});
	this.$('#btnRemove').disable({disableClass:"ui-state-disabled"});

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
 GamFcltsClCdMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

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

var module_instance = new GamFcltsClCdMngModule();

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
							<th>시설물 분류</th>
							<td>
								<input id="sFcltsClCd" type="text" size="6" maxlength="9"/>
							</td>
							<th>시설물 분류 명</th>
							<td>
								<input id="sFcltsClNm" type="text" size="20" maxlength="100"/>
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
				<li><a href="#listTab" class="emdTab">시설물 분류 리스트</a></li>
				<li><a href="#detailTab" class="emdTab">시설물 분류 상세</a></li>
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
								<th style="width:15%; height:18;">업　무 　 구　분</th>
								<td>
									<input id="fcltsJobSeNm" type="hidden"/>
									<select id="fcltsJobSe">
										<option value="0">전체시설</option>
										<option value="A">건축시설</option>
										<option value="C">토목시설</option>
										<option value="M">기계시설</option>
										<option value="E">전기시설</option>
										<option value="I">정보통신시설</option>
									</select>
								</td>
								<th style="width:15%; height:18;">단계 / LEAF  여부</th>
								<td>
									<input type="text" id="depthSort" size="21" maxlength="1"/>
									<input type="text" id="leafYn" size="22" maxlength="1" disabled/>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">시설물 분류 상위</th>
								<td colspan="3">
									<input type="text" id="fcltsClUpperCd" size="15" maxlength="10"/>
									<input type="text" id="fcltsClUpperNm" size="85" maxlength="60"/>
									<button id="popupFcltsClUpperCd" class="popupButton">선택</button>
								</td>
							</tr>
							<tr>
								<th style="width:15%; height:18;">시설물 분류 코드</th>
								<td>
									<input type="text" id="fcltsClCd" size="45" maxlength="10"/>
								</td>
								<th style="width:15%; height:18;">시설물　분류　명</th>
								<td>
									<input type="text" id="fcltsClCdNm" size="45" maxlength="60"/>
								</td>
							</tr>
						</table>
					</form>
					<table style="width:100%;">
						<tr>
							<td style="text-align:right">
								<button id="btnInsert" class="buttonAdd">　　추　가　　</button>
								<button id="btnSave" class="buttonSave">　　저　장　　</button>
								<button id="btnRemove" class="buttonDelete">　　삭　제　　</button>
							</td>
						</tr>
					</table>
					<table style="width:100%;">
						<tr>
							<td>
							<div id="fcltsClTreeList" class="tree" style="position:relative; left:1px; top:4px; width:735px; height:280px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill">
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
