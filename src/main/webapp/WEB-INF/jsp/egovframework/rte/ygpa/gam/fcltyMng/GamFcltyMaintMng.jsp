<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyMaintMng.jsp
  * @Description : 시설물유지보수관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.20  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.20
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltyMaintMngListVO" method="validateFcltyMaintMngVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<%
/******************************** SCRIPT START ********************************/
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyMaintMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyMaintMngModule() {
}

GamFcltyMaintMngModule.prototype = new EmdModule(1000,600);	// 초기 시작 창크기 지정

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.loadComplete = function(params) {

	this._mode = "";
	this._deleteDataMaintList=[];
	this._deleteDataFileList=[];

	console.log("GamFcltyMaintMngModule");
	// 테이블 설정
	this.$("#fcltyMaintMngList").flexigrid({
		module: this,
		url: '/fcltyMng/selectFcltyMaintMngList.do',
		dataType: "json",
		colModel : [
					{display:"순서", 				name:"mntnFcltsCnstInfo",		width:30, 		sortable:false,		align:"center"},
					{display:"시설물관리그룹", 		name:"fcltsMngGroupNm",			width:130, 		sortable:false,		align:"center"},
					{display:"유지보수공사명",		name:"mntnRprCnstNm",			width:250, 		sortable:false,		align:"left"},
					{display:"유지보수구분",		name:"mntnRprSeNm",				width:80, 		sortable:false,		align:"center"},
					{display:"시작일자",			name:"mntnRprCnstStartDt",		width:80, 		sortable:false,		align:"center"},
					{display:"종료일자",			name:"mntnRprCnstEndDt",		width:80, 		sortable:false,		align:"center"},
					{display:"공사금액", 			name:"mntnRprCnstAmt",			width:150, 		sortable:false,		align:'right', 		displayFormat: 'number'},
					{display:"유지보수내용", 		name:"mntnRprCn",				width:250, 		sortable:false,		align:"left"},
					{display:"시공자", 				name:"cnstrtr",					width:150, 		sortable:false,		align:"center"},
					{display:"계약명", 				name:"ctrtNm",					width:250, 		sortable:false,		align:"left"}
			],
		height: "auto",
		groupBy: "mntnFcltsCnstInfo",
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			module.$('#sumMntnRprCnstAmt').val($.number(data.sumMntnRprCnstAmt));
			module.$('#sumMntnRprBdgt').val($.number(data.sumMntnRprBdgt));
			return data;
		}
	});

	this.$("#mntnRprObjFcltsF").flexigrid({
		module: this,
		url: '/fcltyMng/selectMntnRprObjFcltsFList.do',
		dataType: "json",
		colModel : [
					{display:"선택", 				name:"chkRole",				width:40, 		sortable:false,		align:"center", 	displayFormat:"checkbox"},
					{display:"시설명",			name:"prtFcltyNm",			width:273,		sortable:false,		align:"left"},
					{display:"시설분류",			name:"gisPrtFcltyNm",		width:50,		sortable:false,		align:"left"}
			],
		height: "360",
		groupBy: "gisPrtFcltyNm",
		preProcess : function(module,data) {
			$.each(data.resultList, function() {
				this.chkRole = this.chkRole==="TRUE";
			});
			return data;
		}
	});




	this.$("#fileGrid").flexigrid({
		module : this,
		url : '/fclty/gamSelectMaintMngFcltsAtchFileList.do',
		dataType : 'json',
		colModel : [
					{display:"선택",		name:"atchFileSelChk",		width:40,		sortable:false,		align:"center",		displayFormat:"checkbox"},
					{display:"번호",		name:"atchFileNo",			width:60,		sortable:false,		align:"center"},
					{display:"구분",		name:"atchFileSeNm",		width:60,		sortable:false,		align:"center"},
					{display:"파일명",		name:"atchFileNmLogic",		width:200,		sortable:false,		align:"left"},
					{display:"프리뷰",		name:"photoUrl",			width:100,		sortable:false,		align:"center",		displayFormat:"image"}
					],
		height: "317",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.atchFileSelChk = (this.atchFileSelChk === 'TRUE');
				this.photoUrl = "";
				var atchFileNmPhysicl = this.atchFileNmPhysicl;
				var ext = atchFileNmPhysicl.substring(atchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
				if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
					this.photoUrl = module.getPfPhotoUrl(atchFileNmPhysicl) + "^" + this.atchFileNmLogic + "^" + "100";
				} else if (ext == "hwp") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/hwp.png";
				} else if (ext == "dwg") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/dwg.png";
				} else if (ext == "xls") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/xls.png";
				} else if (ext == "xlsx") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/xlsx.png";
				} else if (ext == "pdf") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/pdf.jpg";
				} else if (ext == "dow") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/dow.png";
				} else if (ext == "ppt") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/ppt.png";
				} else if (ext == "txt") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/txt.png";
				} else if (ext == "zip") {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/zip.jpg";
				} else {
					this.photoUrl = EMD.context_root+"js/codebase/imgs/unknown.png";
				}
			});
			return data;
		}
	});
	this.$("#fcltyMaintMngList").on('onLoadDataComplete', function(event, module, data) {
		module.collapseAllGroups();
	});
	this.$("#fileGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectFileData();
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.enableFileButtonItem();
	});

	this.$("#fileGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.refreshFileData(row.atchFileNo);
		module.enableFileButtonItem();
		module.showFcltsAtchFileViewPopup();
	});

	this.$('#dirQueryOption').on('change',{module:this}, function(event){
		event.data.module.displayAtchFileDirectory("");
		event.data.module.displayAtchFileList("");
	});

	this._fileKeyValue = "";
	this._atchFileDirLoad = false;
	this._atchFilePreview = false;








	this.$(".EditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyDataChanged();
	});

	this.$("#mntnRprObjFcltsF").on("onItemSelected", function(event, module, row, grid, param) {

		module.$("#gamPopupMaintForm input").val('');
		module.makeFormValues("#gamPopupMaintForm", row);
	});


 	this.$("#fcltyMaintMngList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});

 	this.$("#fcltyMaintMngList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._mode="modify";
		module.$("#fcltyMaintMngListTab").tabs("option", {active: 1});
	});


	// 파일 정보 속성이 변경 된 경우 이벤트 실행sFcltsMngGroupNo
	this.$(".fileEditItem").bind("change keyup", {module: this}, function(event) {
		event.data.module.applyFileChanged(event.target);
	});


	// 시설물관리그룹 검색조건 클릭시 초기화 처리
	this.$("#sFcltsMngGroupNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sFcltsMngGroupNo").val('');
		event.data.module.$("#sFcltsMngGroupNoNm").val('');
	});

	// 공사계약 검색조건 클릭시 초기화 처리
	this.$("#sCtrtNo").bind("click", {module: this}, function(event) {
		event.data.module.$("#sCtrtNo").val('');
		event.data.module.$("#sCtrtNm").val('');
	});

	if (EMD.userinfo.mngFcltyCd != null && EMD.userinfo.mngFcltyCd != "*") {
		this.$('#sFcltsJobSe').val(EMD.userinfo.mngFcltyCd);
	}
	this.$('#mntnRprExpRate').bind("keyup", {module: this}, function(event) {
		var bdgt = event.data.module.$('#mntnRprBdgt').val();
		var rate = event.data.module.$('#mntnRprExpRate').val();
		if(bdgt !=""){
			var cnstAmt = bdgt * rate;
			event.data.module.$('#mntnRprCnstAmt').val(cnstAmt);
		}
	});
	this.$('#mntnRprBdgt').bind("keyup", {module: this}, function(event) {
		var bdgt = event.data.module.$('#mntnRprBdgt').val();
		var rate = event.data.module.$('#mntnRprExpRate').val();
		if(rate !=""){
			var cnstAmt = bdgt * rate;
			event.data.module.$('#mntnRprCnstAmt').val(cnstAmt);
		}
	});

	// 기본값 셋팅
	this.setDefaultParam();
	this.applySelectYear();
	this.getMapInfoList(params);

	console.log('debug');

};

<%
/**
 * @FUNCTION NAME : collapseAllGroups
 * @DESCRIPTION   : COLLAPSE GRID GROUPS
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.collapseAllGroups = function() {

	this.$("#fcltyMaintMngList")[0].dgrid.collapseAllGroups();

};

/* 파일첨부 관련 function */
<%
/**
 * @FUNCTION NAME : selectFileData
 * @DESCRIPTION   : FILE DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.selectFileData = function() {

	if (this._fileKeyValue == "") {
		return;
	}
	var atchFileNo = this._fileKeyValue;
	this._fileKeyValue = "";
	this.$("#fileGrid").selectFilterRow([{col:"atchFileNo", filter:atchFileNo}]);
	this.refreshFileData(atchFileNo);
	this.enableFileButtonItem();

};
<%
/**
 * @FUNCTION NAME : onAtchFileDirTreeItemClick
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY TREE ITEM CLICK EVENT
 * @PARAMETER     :
 *   1. itemId - ITEM ID
**/
%>
GamFcltyMaintMngModule.prototype.onAtchFileDirTreeItemClick = function(itemId) {

	$(this)[0].module.refreshDirData(itemId);

};

<%
/**
 * @FUNCTION NAME : displayAtchFileDirectory
 * @DESCRIPTION   : 첨부 파일 디렉토리를 TREE형태로 보여준다.
 * @PARAMETER     :
 *   1. argDirNo - DIRECTORY NO.
**/
%>
GamFcltyMaintMngModule.prototype.displayAtchFileDirectory = function(argDirNo) {

	this.$("#atchFileDirTreeList").empty();
	var inputVO = this.makeFormArgs("#dirForm");
	this.doAction('/fclty/gamSelectMaintMngAtchFileDirList.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			if (result.resultList.length > 0) {
				var atchFileDirTreeNode = module.$('#atchFileDirTreeList');
				var atchFileDirTreeItems = [];
				for (var i=0; i < result.resultList.length; i++) {
					var atchFileDir = result.resultList[i];
					atchFileDirTreeItems[atchFileDirTreeItems.length] = [atchFileDir.dirNo, atchFileDir.dirUpperNo, atchFileDir.dirNm];
				}
				module.tree = new dhtmlXTreeObject(atchFileDirTreeNode.attr('id'), "100%", "100%", 0);
				module.tree.setImagePath("<c:url value='/js/codebase/imgs/dhxtree_skyblue/'/>");
				module.tree.loadJSArray(atchFileDirTreeItems);
				module.tree.setUserData('module', module);
 				module.tree.openItem(1);
				module.tree.module = module;
				module.tree.setOnClickHandler(module.onAtchFileDirTreeItemClick);
				if (argDirNo != "") {
					module.tree.selectItem(argDirNo);
					module.tree.focusItem(argDirNo);
					module.refreshDirData(argDirNo);
				}
 			}
		}
	});

};

<%
/**
 * @FUNCTION NAME : refreshDirData
 * @DESCRIPTION   : DIRECTORY DATA REFRESH (LIST)
 * @PARAMETER     :
 *   1. argDirNo - DIRECTORY NO.
**/
%>
GamFcltyMaintMngModule.prototype.refreshDirData = function(argDirNo) {

	if (argDirNo > 1) {
		this.$('#dirNo').val('' + argDirNo);
		var dirQueryOption = this.$('#dirQueryOption').val();
		var searchVO = this.getFormValues('#dirForm');
		this.doAction('/fclty/gamSelectMaintMngAtchFileDirPk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#dirForm', result.result);
				module.makeDivValues('#dirForm', result.result);
				module.$('#dirQueryOption').val(dirQueryOption);
				module.$('#inputDirNm').val(result.result.dirNm);
				module.displayAtchFileList(argDirNo);
			} else {
				module.makeFormValues('#dirForm', {});
				module.makeDivValues('#dirForm', {});
				module.$('#dirQueryOption').val(dirQueryOption);
				module.displayAtchFileList("");
			}
		});
	} else {
		this.$('#dirNo').val("1");
		this.$('#dirNm').val("ROOT");
		this.$('#dirPath').val("/");
		this.$('#dirUpperNo').val("0");
		this.$('#depthSort').val("0");
		this.$('#leafYn').val("N");
		this.$('#dirFcltsJobSe').val("G");
		this.$('#inputDirNm').val("ROOT");
		this.displayAtchFileList("");
	}

};

<%
/**
 * @FUNCTION NAME : addAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY를 추가한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.addAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	var inputDirNm = this.$('#inputDirNm').val();
	if (inputDirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('상위 디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('상위 디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('상위 디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (dirFcltsJobSe != "G") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (생성불가)');
		return;
	}
	if (inputDirNm == dirNm) {
		alert('생성 디렉토리명이 현재 디렉토리명과 동일합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (confirm("[" + inputDirNm + "] 디렉토리를 생성하시겠습니까?")) {
		this.$('#dirNm').val(inputDirNm);
		this.$('#dirPath').val(dirPath + inputDirNm + "/");
		this.$('#dirUpperNo').val(dirNo);
		this.$('#depthSort').val("" + (depthSort + 1));
		this.$('#leafYn').val("Y");
		this.$('#dirFcltsJobSe').val("G");
		this.$('#dirNo').val("");
		var insertVO = this.makeFormArgs("#dirForm");
		this.$('#dirNm').val(dirNm);
		this.$('#dirPath').val(dirPath);
		this.$('#dirUpperNo').val(dirUpperNo);
		this.$('#depthSort').val("" + depthSort);
		this.$('#leafYn').val(leafYn);
		this.$('#dirFcltsJobSe').val(dirFcltsJobSe);
		this.$('#dirNo').val("" + dirNo);
		this.doAction('/fclty/gamInsertMaintMngAtchFileDir.do', insertVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("" + dirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : renameAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY NAME을 변경한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.renameAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	var inputDirNm = this.$('#inputDirNm').val();
	if (inputDirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (dirFcltsJobSe != "G") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (변경불가)');
		return;
	}
	if (inputDirNm == dirNm) {
		alert('변경 디렉토리명이 현재 디렉토리명과 동일합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	var tempDirPath = dirPath.replace(/\/+$/, "");
	var oldDirNm = tempDirPath.substring(tempDirPath.lastIndexOf("/")+1);
	var upperDirPath = tempDirPath.substring(0,tempDirPath.lastIndexOf("/"));
	var newDirPath = upperDirPath + "/" + inputDirNm + "/";
	if (oldDirNm != dirNm) {
		alert('디렉토리 PATH에 현재 디렉토리명이 존재하지 않습니다.');
		return;
	}
	if (confirm("[" + dirNm + "]을 " + "[" + inputDirNm + "]로 변경하시겠습니까?")) {
		this.$('#dirNm').val(inputDirNm);
		this.$('#dirPath').val(newDirPath);
		var updateVO = this.makeFormArgs("#dirForm");
		this.$('#dirNm').val(dirNm);
		this.$('#dirPath').val(dirPath);
		this.doAction('/fclty/gamUpdateMaintMngAtchFileDir.do', updateVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("" + dirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : removeAtchFileDirectory
 * @DESCRIPTION   : ATTACHE FILE DIRECTORY를 제거한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.removeAtchFileDirectory = function() {

	var dirNo = Number(this.$('#dirNo').val());
	var dirNm = this.$('#dirNm').val();
	var dirPath = this.$('#dirPath').val();
	var dirUpperNo = Number(this.$('#dirUpperNo').val());
	var depthSort = Number(this.$('#depthSort').val());
	var leafYn = this.$('#leafYn').val();
	var dirFcltsJobSe = this.$('#dirFcltsJobSe').val();
	if (dirNm == "") {
		alert('디렉토리명이 부정확합니다.');
		this.$("#inputDirNm").focus();
		return;
	}
	if (dirNo <= 0) {
		alert('디렉토리 정보가 부정확합니다. (번호)');
		return;
	}
	if (dirNm == "") {
		alert('디렉토리 정보가 부정확합니다. (명)');
		return;
	}
	if (dirPath == "") {
		alert('디렉토리 정보가 부정확합니다. (PATH)');
		return;
	}
	if (dirUpperNo < 0) {
		alert('디렉토리 정보가 부정확합니다. (상위번호)');
		return;
	}
	if (depthSort < 0) {
		alert('디렉토리 정보가 부정확합니다. (단계)');
		return;
	}
	if (leafYn != "Y" && leafYn != "N") {
		alert('디렉토리 정보가 부정확합니다. (LEAF 여부)');
		return;
	}
	if (dirFcltsJobSe == "") {
		alert('디렉토리 정보가 부정확합니다. (업무구분)');
		return;
	}
	if (dirFcltsJobSe != "G") {
		alert('다른 시설담당자가 생성한 디렉토리입니다. (삭제불가)');
		return;
	}
	if (confirm("[" + dirNm + "] 디렉토리를 삭제하시겠습니까?\r\n(하위 디렉토리 및 첨부 파일도 모두 삭제됩니다)")) {
		var deleteVO = this.makeFormArgs("#dirForm");
		this.doAction('/fclty/gamDeleteMaintMngAtchFileDir.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileDirectory("");
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : displayAtchFileList
 * @DESCRIPTION   : 첨부 파일 목록을 보여준다.
 * @PARAMETER     :
 *   1. argAtchFileDirNo - ATTACHE FILE DIRECTORY NO.
**/
%>
GamFcltyMaintMngModule.prototype.displayAtchFileList = function(argAtchFileDirNo) {
	console.log(argAtchFileDirNo);
	this.makeFormValues('#fileForm', {});
	this.makeDivValues('#fileForm', {});
	this.$('#fileGrid').flexEmptyData();
	if (argAtchFileDirNo != null && argAtchFileDirNo != "") {
		this.$('#atchFileDirNo').val(argAtchFileDirNo);
		var detailOpt = this.getFormValues('#fileForm');
		this.$('#atchFileDirNo').val("");
		this.$('#fileGrid').flexOptions({params:detailOpt}).flexReload();
		this.enableFileButtonItem();
	} else {
		this.disableFileButtonItem();
	}

};

<%
/**
 * @FUNCTION NAME : refreshFileData
 * @DESCRIPTION   : FILE DATA REFRESH (LIST)
 * @PARAMETER     :
 *   1. argAtchFileNo - ATTACHE FILE NO.
**/
%>
GamFcltyMaintMngModule.prototype.refreshFileData = function(argAtchFileNo) {

	if (argAtchFileNo != null && argAtchFileNo != "") {
		this.$('#atchFileNo').val(argAtchFileNo);
		var searchVO = this.getFormValues('#fileForm');
		this.doAction('/fclty/gamSelectMaintMngFcltsAtchFilePk.do', searchVO, function(module, result){
			if (result.resultCode == "0") {
				module.makeFormValues('#fileForm', result.result);
				module.makeDivValues('#fileForm', result.result);
				module.enableFileButtonItem();
			} else {
				module.makeFormValues('#fileForm', {});
				module.makeDivValues('#fileForm', {});
				module.disableFileButtonItem();
			}
		});
	} else {
		this.makeFormValues('#fileForm', {});
		this.makeDivValues('#fileForm', {});
		this.disableFileButtonItem();
	}

};

<%
/**
 * @FUNCTION NAME : selectAllFile
 * @DESCRIPTION   : ALL FILE SELECT
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.selectAllFile = function() {

	var rows = this.$('#fileGrid').flexGetData();
	var atchFileDataCount = rows.length;
	if (atchFileDataCount > 0) {
		for (var i=0; i<atchFileDataCount; i++) {
			var row = rows[i];
			row["atchFileSelChk"] = true;
			var rowid = this.$('#fileGrid')[0].dgrid.getRowId(i);
			this.$('#fileGrid').flexUpdateRow(rowid, row);
		}
	}

};

<%
/**
 * @FUNCTION NAME : saveUploadFileData
 * @DESCRIPTION   : UPLOAD FILE 항목을 저장한다.
 * @PARAMETER     :
 *   1. argAtchFileFcltsMngNo - ATTACHE FILE FCLTS MNG NO.
 *   2. argAtchFileFcltsDirNo - ATTACHE FILE FCLTS DIRECTORY NO.
 *   3. argAtchFileFcltsJobSe - ATTACHE FILE FCLTS JOB SE
 *   4. argAtchFileFcltsDataSe - ATTACHE FILE FCLTS DATA SE
 *   5. argAtchFileFcltsMngSeq - ATTACHE FILE FCLTS MNG SEQ.
 *   6. argAtchFileNmLogic - ATTACHE FILE NAME LOGICAL.
 *   7. argAtchFileNmPhysicl - ATTACHE FILE NAME PHYSICAL
**/
%>
GamFcltyMaintMngModule.prototype.saveUploadFileData = function(argAtchFileFcltsMngNo, argAtchFileFcltsDirNo, argAtchFileFcltsJobSe, argAtchFileFcltsDataSe, argAtchFileFcltsMngSeq, argAtchFileNmLogic, argAtchFileNmPhysicl) {

	var inputVO = [];
	var atchFileSe = "D";
	var atchFileSeNm = "문서";
	if (argAtchFileNmPhysicl != null || argAtchFileNmPhysicl != "") {
		var ext = argAtchFileNmPhysicl.substring(argAtchFileNmPhysicl.lastIndexOf(".")+1).toLowerCase();
		if (ext == "jpg" || ext == "jpeg" || ext == "bmp" || ext == "png" || ext == "gif") {
			atchFileSe = "P";
			atchFileSeNm = "사진";
		} else if (ext == "dwg" || ext == "dxf") {
			atchFileSe = "C";
			atchFileSeNm = "도면";
		}
	}
	inputVO={
			'atchFileNo':"",
			'atchFileNmLogic':argAtchFileNmLogic,
			'atchFileNmPhysicl':argAtchFileNmPhysicl,
			'atchFileSe':atchFileSe,
			'atchFileSeNm':atchFileSeNm,
			'atchFileDirNo':argAtchFileFcltsDirNo,
			'atchFileFcltsDataSe':argAtchFileFcltsDataSe,
			'atchFileFcltsMngNo':argAtchFileFcltsMngNo,
			'atchFileFcltsJobSe':argAtchFileFcltsJobSe,
			'atchFileFcltsMngSeq':argAtchFileFcltsMngSeq,
			'regUsr':"",
			'registDt':"",
			'updUsr':"",
			'updtDt':""
	};
	this.doAction('/fclty/gamInsertMaintMngFcltsAtchFile.do', inputVO, function(module, result) {
		if (result.resultCode == "0") {
			module.$("#fileGrid").flexAddRow({ atchFileNo:result.atchFileNo,
											   atchFileSe:atchFileSe,
											   atchFileSeNm:atchFileSeNm,
											   atchFileNmLogic: argAtchFileNmLogic,
											   atchFileNmPhysicl: argAtchFileNmPhysicl,
											   atchFileFcltsDirNo: argAtchFileFcltsDirNo,
											   atchFileFcltsDataSe: argAtchFileFcltsDataSe,
											   atchFileFcltsMngNo: argAtchFileFcltsMngNo,
											   atchFileFcltsJobSe: argAtchFileFcltsJobSe,
											   atchFileFcltsMngSeq: argAtchFileFcltsMngSeq
											  });
		} else {
			alert(result.resultMsg);
		}
	});

};

<%
/**
 * @FUNCTION NAME : uploadFile
 * @DESCRIPTION   : FILE UPLOAD
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.uploadFile = function() {

	var atchFileFcltsDirNo = Number(this.$('#dirNo').val());
	var atchFileFcltsMngNo = this.$('#fcltsMngNo').val();
	var atchFileFcltsDataSe = "D";
	var atchFileFcltsJobSe = "G";
	var atchFileFcltsMngSeq = "";
	if (atchFileFcltsDirNo <= 0) {
		alert('업로드 디렉토리가 선택되지 않았습니다.');
		return;
	}
	this.uploadPfPhoto("uploadFile", function(module, result) {
		$.each(result, function(){
			module.saveUploadFileData(atchFileFcltsMngNo, atchFileFcltsDirNo, atchFileFcltsJobSe, atchFileFcltsDataSe, atchFileFcltsMngSeq, this.logicalFileNm, this.physcalFileNm);
		});
	}, "첨부파일 업로드");

};

<%
/**
 * @FUNCTION NAME : downloadFile
 * @DESCRIPTION   : FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.downloadFile = function() {

	var selectRow = this.$('#fileGrid').selectedRows();
	if (selectRow.length > 0) {
		var row = selectRow[0];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : downloadMultiFile
 * @DESCRIPTION   : MULTI FILE DOWNLOAD
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.downloadMultiFile = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('다운로드할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	for (var i=0; i<rows.length; i++) {
		var row = rows[i];
		this.downPfPhoto(row["atchFileNmPhysicl"], row["atchFileNmLogic"]);
	}

};

<%
/**
 * @FUNCTION NAME : deleteFileData
 * @DESCRIPTION   : FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.deleteFileData = function() {

	var atchFileDirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	var atchFileFcltsJobSe = this.$('#atchFileFcltsJobSe').val();
	if (atchFileNo == "") {
		alert('첨부 파일 번호가 부정확합니다.');
		return;
	}
	if (atchFileFcltsJobSe != "G") {
		alert('다른 시설담당자가 첨부한 파일입니다. (삭제불가)');
		return;
	}
	if (confirm("삭제하시겠습니까?")) {
		var deleteVO = this.makeFormArgs("#fileForm");
		this.doAction('/fclty/gamDeleteMaintMngFcltsAtchFile.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList(atchFileDirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : deleteMultiFileData
 * @DESCRIPTION   : MULTI FILE 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.deleteMultiFileData = function() {

	var rows = this.$('#fileGrid').selectFilterData([{col:'atchFileSelChk', filter: true}]);
	if (rows.length <= 0) {
		alert('다운로드할 첨부 파일 자료가 선택되지 않았습니다.');
		return;
	}
	var atchFileDirNo = this.$('#dirNo').val();
	var atchFileNo = "";
	var atchFileFcltsJobSe = "";
	var atchFileNmLogic = "";
	var deleteDataCount = rows.length;
	var deleteAtchFileNoList = "";
	for (var i=0; i<deleteDataCount; i++) {
		var row = rows[i];
		atchFileNo = row["atchFileNo"];
		atchFileFcltsJobSe = row["atchFileFcltsJobSe"];
		atchFileNmLogic = row["atchFileNmLogic"];
		if (atchFileNo == "") {
			alert('[' + atchFileNmLogic + '] 첨부 파일 번호가 부정확합니다.');
			return;
		}
		if (atchFileFcltsJobSe != "G") {
			alert('[' + atchFileNmLogic + '] 다른 시설담당자가 첨부한 파일입니다. (삭제불가)');
			return;
		}
		if (deleteAtchFileNoList != "") {
			deleteAtchFileNoList = deleteAtchFileNoList + "," + atchFileNo;
		} else {
			deleteAtchFileNoList = atchFileNo;
		}
	}
	if (confirm("[" + deleteDataCount + "] 건의 첨부 파일 자료를 삭제하시겠습니까?")) {
		var deleteVO = {
				'deleteAtchFileNoList':deleteAtchFileNoList
		};
		this.doAction('/fclty/gamDeleteMaintMngFcltsAtchFileMulti.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module.displayAtchFileList(atchFileDirNo);
			}
			alert(result.resultMsg);
		});
	}

};

<%
/**
 * @FUNCTION NAME : displayPreviewFile
 * @DESCRIPTION   : FILE PREVIEW DISPLAY
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.displayPreviewFile = function() {

	var atchFilePreviewFlag = this.$('#fileGrid')[0].dgrid.isColumnHidden(4);
	var columnWidth = "200";
	if (atchFilePreviewFlag == true) {
		atchFilePreviewFlag = false;
		columnWidth = "200";
	} else {
		atchFilePreviewFlag = true;
		columnWidth = "300";
	}
	this.$('#fileGrid')[0].dgrid.setColumnHidden(4,atchFilePreviewFlag);
	this.$('#fileGrid')[0].dgrid.setColWidth(3, columnWidth);
	this._atchFilePreview = atchFilePreviewFlag;
	this.displayAtchFileList(this.$('#dirNo').val());

};

<%
/**
 * @FUNCTION NAME : showFcltsAtchFileViewPopup
 * @DESCRIPTION   : FCLTS ATTACHE FILE VIEW POPUP
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.showFcltsAtchFileViewPopup = function() {

	var atchFileNo = this.$('#atchFileNo').val();
	var atchFileNmPhysicl = this.$('#atchFileNmPhysicl').val();
	var imageURL = "";
	if (atchFileNo == "") {
		return;
	}
	if (atchFileNmPhysicl != "") {
		imageURL = this.getPfPhotoUrl(atchFileNmPhysicl);
	}
    var searchOpts = {
		'atchFileNo':atchFileNo,
		'imageURL':imageURL
    };
	this.doExecuteDialog('popupFcltsAtchFileView', '시설물 첨부 파일 보기', '/popup/showFcltsAtchFileViewPopup.do', null, searchOpts);

};

<%
/**
 * @FUNCTION NAME : enableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 ENABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.enableFileButtonItem = function() {

	var dirNo = this.$('#dirNo').val();
	var atchFileNo = this.$('#atchFileNo').val();
	if (dirNo != "") {
		if (atchFileNo != "") {
			this.$('#btnFileAllSelect').enable();
			this.$('#btnFileAllSelect').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
			this.$('#btnFilePreview').enable();
			this.$('#btnFilePreview').removeClass('ui-state-disabled');
		} else {
			this.$('#btnFileAllSelect').enable();
			this.$('#btnFileAllSelect').removeClass('ui-state-disabled');
			this.$('#btnFileUpload').enable();
			this.$('#btnFileUpload').removeClass('ui-state-disabled');
			this.$('#btnFileDownload').enable();
			this.$('#btnFileDownload').removeClass('ui-state-disabled');
			this.$('#btnFileRemove').enable();
			this.$('#btnFileRemove').removeClass('ui-state-disabled');
			this.$('#btnFilePreview').enable();
			this.$('#btnFilePreview').removeClass('ui-state-disabled');
		}
	} else {
		this.$('#btnFileAllSelect').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
		this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});
	}

};

<%
/**
 * @FUNCTION NAME : disableFileButtonItem
 * @DESCRIPTION   : FILE BUTTON 항목을 DISABLE 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.disableFileButtonItem = function() {

	this.$('#btnFileAllSelect').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileUpload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileDownload').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFileRemove').disable({disableClass:"ui-state-disabled"});
	this.$('#btnFilePreview').disable({disableClass:"ui-state-disabled"});

};











<%
/**
 * @FUNCTION NAME : getMapInfoList
 * @DESCRIPTION   : 맵에서 유지보수 정보를 클릭할때 넘어오는 Param으로 리스트 가져오는 함수
 * @PARAMETER
 *		1. fcltsMngGroupNo   : 시설물 관리 그룹 코드
 *		2. fcltsMngGroupNoNm : 시설물 관리 그룹 코드명
**/
%>
GamFcltyMaintMngModule.prototype.getMapInfoList = function(params){
	this._params=params;
	if(params!=null) {
		if(params.action!=null) {
			switch(params.action) {
				case "manage":
					this.$('#sFcltsMngGroupNo').val(this._params.fcltsMngGroupNo);
					this.$('#sFcltsMngGroupNoNm').val(this._params.fcltsMngGroupNoNm);
					this.$('#sMapFcltsMngNo').val(this._params.fcltsMngNo);

					this.loadData();
				break;
			}
		}
	}

};


<%
/**
 * @FUNCTION NAME : setDefaultParam
 * @DESCRIPTION   : 조회조건 및 기본값 셋팅 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.setDefaultParam = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	var toMonth = toDate.getMonth() + 1;
	if(toMonth < 10) toMonth = "0" + toMonth;

	var toDay = toDate.getDate();
	if(toDay < 10) toDay = "0" + toDay;

	this.$("#sMntnRprCnstStartDtFr").val(toYear + "-01-01");
	this.$("#sMntnRprCnstStartDtTo").val(toYear + "-" + toMonth + "-" + toDay);

	this.$("#sFcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);

	this.$('#planHistSe').val('H');
	/* this.$('#planHistSe').disable(); */
};


<%
/**
 * @FUNCTION NAME : applySelectYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.applySelectYear = function(){
	var toDate = new Date();
	var toYear = toDate.getFullYear();

	for(var i = toYear;i>=1980;i--){
		this.$("#enforceYear").append("<option value='" + i + "'>" + i + "년</option>");
	}
};


<%
/**
 * @FUNCTION NAME : applyDataChanged
 * @DESCRIPTION   : 시설물 업무구분, 시설물관리그룹 넘버가 변할때 대상시설물 그리드 조회 변경 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.applyDataChanged = function() {

	var fcltsJobSe = this.$("#fcltsJobSe").val();
	var fcltsMngGroupNo = this.$("#fcltsMngGroupNo").val();
	var mntnRprSeq = this.$("#mntnRprSeq").val();

	var codeId = this.getCodeId(fcltsJobSe);
	this.$("#codeId").val(codeId);

	if(fcltsJobSe && fcltsMngGroupNo){
		var searchVO = [
		                { name: 'fcltsJobSe', value: fcltsJobSe },
		                { name: 'fcltsMngGroupNo', value: fcltsMngGroupNo },
		                { name: 'mntnRprSeq', value: mntnRprSeq }
		               ];

		this.$('#mntnRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
	}
};


<%
/**
 * @FUNCTION NAME : getCodeId
 * @DESCRIPTION   : 시설물분류 코드 아이디 리턴 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.getCodeId = function(fcltsJobSe) {

	var codeId = '';
	switch(fcltsJobSe) {
		// 전기시설물
		case "E":
			codeId = 'GAM068';
		break;

		// 기계시설물
		case "M":
			codeId = 'GAM067';
		break;

		// 토목시설물
		case "C":
			codeId = 'GAM070';
		break;

		// 건축시설물
		case "A":
			codeId = 'GAM066';
		break;

		// 정보통신시설물
		case "I":
			codeId = 'GAM069';
		break;

	}


	return codeId;
};


<%
/**
 * @FUNCTION NAME : validateDuration
 * @DESCRIPTION   : 유효성 있는 기간 체크
 * @PARAMETER     :
	 1. startDate   : 시작일 문자열,
	 2. endDate     : 종료일 문자열,
	 3. startTitle  : 시작일 제목,
	 4. endTitle    : 종료일 제목,
	 5. startIgnore :
		 5-1. true  : 시작일 필수입력사항 미체크,
		 5-2. false : 시작일 필수입력사항 체크
	 6. endIgnore :
		 6-1. true  : 종료일 필수입력사항 미체크,
		 6-2. false : 종료일 필수입력사항 체크
	 7. equals      :
		 7-1. true  : 종료일이 시작일 보다 크거나 같으면 허용
		 7-2. false : 종료일이 시작일 보다 커야 허용
**/
%>
GamFcltyMaintMngModule.prototype.validateDuration = function(startDate, endDate, startTitle, endTitle, startIgnore, endIgnore, equals) {
	var result = false;
	if(((startDate == null) || (startDate == '')) && ((endDate == null) || (endDate == ''))) {
		return true;
	}
	if((endDate == null) || (endDate == '')) {
		if(!endIgnore) {
			alert(endTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
		result = true;
		if((startDate != null) && (startDate != '')) {
			result = EMD.util.isDate(startDate);
			if(!result) {
				alert(startTitle + '은(는) 날짜형식이 아닙니다.');
			}
		}
		return result;
	}
	if((startDate == null) || (startDate == '')) {
		if(startIgnore) {
			result = EMD.util.isDate(endDate);
			if(!result) {
				alert(endTitle + '은(는) 날짜형식이 아닙니다.');
			}
			return result;
		} else {
			alert(startTitle + '을(를) 입력하셔야 합니다.');
			return false;
		}
	}
	if(!EMD.util.isDate(startDate)) {
		alert(startTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	if(!EMD.util.isDate(endDate)) {
		alert(endTitle + '은(는) 날짜형식이 아닙니다.');
		return false;
	}
	startDate = EMD.util.strToDate(startDate);
	endDate = EMD.util.strToDate(endDate);
	var compareResult = (startDate.getTime() > endDate.getTime()) ? -1 :
							(startDate.getTime() == endDate.getTime()) ? 0 : 1;
	result = (equals) ? (compareResult >= 0) : (compareResult > 0);
	if(!result) {
		alert(endTitle +'은(는) ' + startTitle + ((equals) ? '보다 같거나 커야합니다.' : '보다 커야합니다.'));
	}
	return result;
};


<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.onSubmit = function(){

	if(!this.validateDuration(this.$('#sMntnRprCnstStartDtFr').val(), this.$('#sMntnRprCnstStartDtTo').val(),
			'유지보수공사검색시작일', '유지보수공사검색종료일',  true,  true, true)) {
		return;
	}
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.loadData = function(){

	this.makeFormValues('#fcltyMaintMngListVO', {});

	this.$('#mntnRprObjFcltsF').flexEmptyData();

	this.$('#fcltyMaintFileList').empty();
	this.$('#fcltyMaintFileList').append('<option value="">선택</option>');

	this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#searchFcltyMaintMngForm');
	this.$('#fcltyMaintMngList').flexOptions({params:searchOpt}).flexReload();
	this.$('#sMapFcltsMngNo').val("");
};


<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.loadDetail = function(){

	var row = this.$('#fcltyMaintMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		return;
	}

	row = row[0];
	var searchVO = [
	                { name: 'fcltsJobSe', value: row['fcltsJobSe'] },
	                { name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] },
	                { name: 'mntnRprSeq', value: row['mntnRprSeq'] }
	               ];

	this.doAction('/fcltyMng/selectFcltyMaintMngDetail.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.makeFormValues('#fcltyMaintMngListVO', result.result);

			var fcltsJobSe = module.$('#fcltsJobSe').val();
			var codeId = module.getCodeId(fcltsJobSe);

			var codeVO = { name: 'codeId', value: codeId };
			searchVO.push(codeVO);

			module.$("#codeId").val(codeId);
			module.$('#mntnRprObjFcltsF').flexOptions({params:searchVO}).flexReload();
			/* module.fillAtchFileList(searchVO); */
		}else{
			module.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		}
    });

};


<%
/**
 * @FUNCTION NAME : fillAtchFileList
 * @DESCRIPTION   : 첨부파일 리스트를 select element에 채워넣기.
 * @PARAMETER     :
 *   1. searchVO
 *     1-1. fcltsJobSe : 시설물업무구분
 *     1-2. fcltsMngGroupNo : 시설물 관리그룹번호
 *     1-3. mntnRprSeq : 유지보수 순번
**/
%>
GamFcltyMaintMngModule.prototype.fillAtchFileList = function(searchVO) {
	this.doAction('/fcltymng/selectFcltyMaintMngFileList.do', searchVO, function(module, result) {
		if(result.resultCode == "0"){
			module.$('#fcltyMaintFileList option').remove();
			module.$('#fcltyMaintFileList').append('<option value="">선택</option>');
			$.each(result.resultList, function(){
				module.$('#fcltyMaintFileList').append('<option value="' + this.atchFileNmPhysicl + '">' + this.atchFileNmLogic + '</option>');
			});
		}else{
			module.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		}
    });
};

<%
/**
 * @FUNCTION NAME : addData
 * @DESCRIPTION   : 추가 버튼 클릭시 tab2 이동 및 초기화 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.addData = function() {

	this._mode="insert";

	// tabs2 초기화
	this.makeFormValues('#fcltyMaintMngListVO', {});
	this.$("#enforceYear").val(new Date().getFullYear());
	this.$("#planHistSe").val('H');
	this.$("#searchFcltsMngGroupNo").show();
	this.$("#fcltsJobSe").enable();

	this.$("#mntnRprObjFcltsF").flexEmptyData();
	this.$("#fcltsJobSe").val(EMD.userinfo["mngFcltyCd"]);
	this.$(".EditItem").trigger("change");


	var toDate = new Date();
	var toYear = toDate.getFullYear();

	var toMonth = toDate.getMonth() + 1;
	if(toMonth < 10) toMonth = "0" + toMonth;

	var toDay = toDate.getDate();
	if(toDay < 10) toDay = "0" + toDay;

	this.$("#wrtDt").val(toYear + "-" + toMonth + "-" + toDay);
	this.$("#wrtUsr").val(EMD.userinfo["name"]);



	this.$("#fcltyMaintFileList").empty();
	this.$('#fcltyMaintFileList').append('<option value="">선택</option>');

	this.$("#fcltyMaintMngListTab").tabs("option", {active: 1});

};

<%
/**
 * @FUNCTION NAME : makeSelectArgs
 * @DESCRIPTION   : 셀렉트 option value, text json 생성함수 - 여기서는 첨부파일
 * @PARAMETER     : SELECT_ID
**/
%>
GamFcltyMaintMngModule.prototype.makeSelectArgs = function(selId) {
	var optionValues = [];
	this.$(selId + ' option').each(function() {
		if($(this).val()){
			optionValues.push({"atchFileNmPhysicl":$(this).val(), "atchFileNmLogic":$(this).eq(0).text()});
		}
	});

	return optionValues;
};


<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 저장버튼 클릭시 저장 처리 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.saveData = function() {

	if(!validateFcltyMaintMngVO(this.$("#fcltyMaintMngListVO")[0])){
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 1});
		return;
	}

	if(!this.validateDuration(this.$('#enforceYear').val() + '-01-01', this.$('#mntnRprCnstStartDt').val(),
			'시행년도', '공사시작일', false, true, true)) {
		return;
	}

	if(!this.validateDuration(this.$('#mntnRprCnstStartDt').val(), this.$('#mntnRprCnstEndDt').val(),
			'공사시작일', '공사종료일', false, false, true)) {
		return;
	}

	var inputVO = [];
 	inputVO[inputVO.length] = {name: 'saveFcltyMaintMngVO', value :JSON.stringify(this.makeFormArgs("#fcltyMaintMngListVO",'object')) };
 	inputVO[inputVO.length] = {name: 'insertMntnObjList', value :JSON.stringify(this.$('#mntnRprObjFcltsF').selectFilterData([{col: 'chkRole', filter: true}])) };
 	inputVO[inputVO.length] = {name: 'insertMntnFileList', value :JSON.stringify(this.makeSelectArgs("#fcltyMaintFileList")) };

	if(this._mode == "insert") {
	 	this.doAction('/fcltyMng/insertFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.$("#fcltsJobSe").disable();
	 			module.$("#searchFcltsMngGroupNo").hide();
	 			module.$("#mntnRprSeq").val(result.mntnRprSeq);

	 			module._mode = "modify";
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/fcltyMng/updateFcltyMaintMng.do', inputVO, function(module, result) {
	 		alert(result.resultMsg);
	 	});
	}

};

<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 삭제버튼 클릭시 삭제 처리 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.deleteData = function() {

	var row = this.$('#fcltyMaintMngList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
		return;
	}

	if(confirm("삭제하시겠습니까?")){
		row = row[0];
		var inputVO = { 'fcltsJobSe': row['fcltsJobSe'],'fcltsMngGroupNo': row['fcltsMngGroupNo'],'mntnRprSeq': row['mntnRprSeq'] };
	 	this.doAction('/fcltyMng/deleteFcltyMaintMng.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


<%
/**
 * @FUNCTION NAME : atchFileUpload
 * @DESCRIPTION   : 첨부파일 업로드
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.atchFileUpload = function() {
	this.uploadMultiFile('/fcltyMng/uploadMaintAttachFile.do', function(module, resp) {
		if(resp.resultCode!=0) {
			alert(resp.resultMsg);
			return;
		}
		$.each(resp.result, function() {
			module.$('#fcltyMaintFileList').append('<option value="' + this.physcalFileNm + '">' + this.logicalFileNm + '</option>');
		});
		if(resp.result!=null && resp.result.length>0) this._edited=true;
	});
};


<%
/**
 * @FUNCTION NAME : atchFileRemove
 * @DESCRIPTION   : 첨부파일제거
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.atchFileRemove = function() {
	if(this.$('#fcltyMaintFileList').val() != '') {
		this.$('#fcltyMaintFileList option[value="' + this.$('#fcltyMaintFileList').val() + '"]').remove();
	}
	else {
		alert('첨부파일을 선택해주십시오.');
	}
};


<%
/**
 * @FUNCTION NAME : downloadFileData
 * @DESCRIPTION   : 파일 다운로드 함수
 * @PARAMETER     : NONE
**/
%>
GamFcltyMaintMngModule.prototype.downloadFileData = function() {
	var selectFilePhysicl = this.$('#fcltyMaintFileList').val();
	var selectFileLogic = this.$('#fcltyMaintFileList').find('option:selected').text();

	if(selectFilePhysicl) {
		this.downloadSingleFile("/fcltyMng/downloadMaintAttachFile.do", selectFilePhysicl, selectFileLogic );

	}else{
		alert('다운로드할 파일을 선택하세요.');
	}
};

GamFcltyMaintMngModule.prototype.tableToExcel = function() {
	var clone =	this.$('#fcltyMaintMngList').clone();
	$(clone).find('th,td').each(function() {
		if($(this).css('display')=='none') {
			$(this).remove();
		}
		else {
			$(this).css('border-left', '1px solid black');
			$(this).css('border-top', '1px solid black');
			$(this).css('border-right', '1px solid black');
			$(this).css('border-bottom', '1px solid black');
		}
	});
	clone.find("img").remove();
	clone.find(".odd_dhx_skyblue").find("td:eq(0)").text("");
	clone.find(".odd_dhx_skyblue").find("td:eq(0)").css("width","100");
	clone.find(".odd_dhx_skyblue").find("td:eq(2)").css("width","500");
	clone.find(".odd_dhx_skyblue").find("td:eq(3)").css("width","100");
	clone.find(".odd_dhx_skyblue").find("td:eq(9)").css("width","500");
	clone.table2excel({
		filename: "유지보수내역 목록",
	});

	//console.log(this.$('#fcltyMaintMngList').find("tr"));
};
<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 그리드리스트 다운로드 함수
 * @PARAMETER     : NONE
**/
%>

GamFcltyMaintMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#fcltyMaintMngList').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}

	this.$('#fcltyMaintMngList').flexExcelDown('/fcltyMng/selectFcltyMaintMngListExcel.do');
};

<%
/**
 * @FUNCTION NAME : fillDetailBasicData
 * @DESCRIPTION   : 계약번호 선택 시 기본정보 셋팅함수
 * @PARAMETER     : 기본정보 VALUE
**/
%>
GamFcltyMaintMngModule.prototype.fillDetailBasicData = function(value) {

	this.$("#ctrtNo").val(value["ctrtNo"]);
	this.$("#ctrtNm").val(value["ctrtNm"]);
	this.$("#mntnRprCnstNm").val(value["ctrtNm"]);
	this.$("#mntnRprCnstStartDt").val(value["ctrtDtFrom"]);
	this.$("#mntnRprCnstEndDt").val(value["ctrtDtTo"]);
	this.$("#mntnRprBdgt").val(value["planAmt"]);
	this.$("#mntnRprCnstAmt").val(value["ctrtAmt"]);
	this.$("#cnstrtr").val(value["cnstrtr"]);

};


<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
 GamFcltyMaintMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

	// 추가
		case "addBtn":
			this.addData();
		break;

		// 저장
		case "saveBtn":
			this.saveData();
		break;

		// 삭제
		case "deleteBtn":
			this.deleteData();
		break;

		// 이미지미리보기
		case "btnPreviewFile":
			this.imgPreview();
		break;

		// 파일업로드
		case "btnUploadFile":
			this.atchFileUpload();
		break;

		// 파일다운로드
		case "btnDownloadFile":
			this.downloadFileData();
		break;

		// 파일삭제
		case "btnRemoveFile":
			this.atchFileRemove();
		break;

		// 엑셀다운로드
		case "btnExcelDownload":
			//this.downloadExcel();
			this.tableToExcel();
		break;

		// 시설물관리그룹
		case "searchFcltsMngGroupNo":
			this.doExecuteDialog("selectFcltsMngGroup", "시설물 관리 그룹 번호", '/popup/showFcltsMngGroup.do', {});
		break;

		// 계약번호
		case "ctrtNoPopupBtn":
			this.doExecuteDialog("selectCtrtNo", "계약번호", '/popup/popupCtrtNo.do', {});
		break;

		// 검색조건시설물관리그룹
		case "sSearchFcltsMngGroupNo":
			this.popupSearchFcltsMngGroup();
			//this.doExecuteDialog("sSelectFcltsMngGroup", "시설물 관리 그룹 번호 검색", '/popup/showFcltsMngGroup.do', {});
		break;

		// 검색조건계약번호
		case "sCtrtNoPopupBtn":
			this.doExecuteDialog("sSelectCtrtNo", "계약번호 검색", '/popup/popupCtrtNo.do', {});
		break;

		case 'btnDirRefresh':
			this.displayAtchFileDirectory("");
			this.displayAtchFileList("");
			break;
		case 'btnDirAdd':
			this.addAtchFileDirectory();
			break;
		case 'btnDirRename':
			this.renameAtchFileDirectory();
			break;
		case 'btnDirRemove':
			this.removeAtchFileDirectory();
			break;
		case 'btnFileAllSelect':
			this.selectAllFile();
			break;
		case 'btnFileUpload':
			this.uploadFile();
			break;
		case 'btnFileDownload':
			this.downloadMultiFile();
			break;
		case 'btnFileRemove':
			this.deleteMultiFileData();
			break;
	    case 'btnFilePreview':
	    	this.displayPreviewFile();
			break;
		case 'btnAtchDirFileSearch':
			var sFcltsJobSe = this.$('#dirQueryOption').val();
			var sSearchSe = "D";
            var searchOpts = {
    				'sSearchSe':sSearchSe,
    				'sFcltsJobSe':sFcltsJobSe
                };
			this.doExecuteDialog('btnAtchDirFileSearch', '디렉토리/파일 검색', '/popup/showAtchDirFile.do', null, searchOpts);
			break;
		case 'btnFcltyMaintMngList':
			this.showFcltyMaintMngList(this.$('#btnFcltyMaintMngList')[0]);
			break;
	}
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
GamFcltyMaintMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._mode == 'modify') {
		this.loadDetail();
	}
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if((this._mode != 'insert') && (this._mode != 'modify')) {
				this.$("#fcltyMaintMngListTab").tabs("option", {active: 0});
				alert('유지보수 항목을 선택 하세요.');
			}
			if(oldTabId == 'tabs1') {
				this.$("#tabs2").scrollTop(0);
			}
			if(this._mode=="modify"){
				this.$("#searchFcltsMngGroupNo").hide();
				this.$("#fcltsJobSe").disable();
			}
		break;
		case 'fileTab':
			if (this._atchFileDirLoad == false) {
				this.displayAtchFileDirectory("");
				this._atchFileDirLoad = true;
				this.$('#fileGrid')[0].dgrid.setColumnHidden(4, true);
				this.$('#fileGrid')[0].dgrid.setColWidth(3, "300");
			}
			break;
	}

};


<%
/**
 * @FUNCTION NAME : onClosePopup
 * @DESCRIPTION   : CLOSE POPUP EVENT
 * @PARAMETER     :
 *   1. popupId  - POPUP ID
 *   2. msg      - MESSAGE
 *   3. value    - VALUE
**/
%>
 GamFcltyMaintMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){

		case "selectFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);

			this.$(".EditItem").trigger("change");
		break;

		case "selectCtrtNo":
			this.fillDetailBasicData(value);
		break;

		case "sSelectFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNoNm").val(value["fcltsMngGroupNm"]);
		break;

		case "sSelectCtrtNo":
			this.$("#sCtrtNo").val(value["ctrtNo"]);
			this.$("#sCtrtNm").val(value["ctrtNm"]);
		break;

		case 'popupFcltsAtchFileView':
			if (msg == 'ok') {
				var atchFileNo = this.$('#atchFileNo').val();
				if (atchFileNo == value.atchFileNo) {
					this.$('#atchFileSe').val(value.atchFileSe);
					this.$('#atchFileSeNm').val(value.atchFileSeNm);
					this.$('#atchFileSj').val(value.atchFileSj);
					var selectRow = this.$('#fileGrid').selectedRows();
					if(selectRow.length > 0) {
						var row = selectRow[0];
						row['atchFileSeNm'] = value.atchFileSeNm;
						row['atchFileSe'] = value.atchFileSe;
						row['atchFileSj'] = value.atchFileSj;
						var rowid = this.$("#fileGrid").selectedRowIds()[0];
						this.$('#fileGrid').flexUpdateRow(rowid, row);
					}
				}
			}
			break;

		case 'btnAtchDirFileSearch':
			if (msg == 'ok') {
				if (value.listSe == "F") {
					this._fileKeyValue = value.fileNo;
					this.displayAtchFileDirectory("" + value.dirNo);
				} else {
					this.displayAtchFileDirectory("" + value.dirNo);
				}
			}
			break;

		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
		break;
	}
};

GamFcltyMaintMngModule.prototype.popupSearchFcltsMngGroup= function() {
	var sFcltsJobSe = this.$('#sFcltsJobSe').val();
	console.log(sFcltsJobSe);
	switch(sFcltsJobSe){
	case 'A':
		this.doExecuteDialog('sSelectFcltsMngGroup', "관리그룹 선택", '/popup/showArchFcltsMngGroup.do', null);
		break;
	case 'C':
		this.doExecuteDialog('sSelectFcltsMngGroup', "관리그룹 선택", '/popup/showCvlFcltsMngGroup.do', null);
		break;
	case 'E':
		this.doExecuteDialog('sSelectFcltsMngGroup', "관리그룹 선택", '/popup/showElectyFcltsMngGroup.do', null);
		break;
	case 'I':
		this.doExecuteDialog('sSelectFcltsMngGroup', "관리그룹 선택", '/popup/showInfoCommFcltsMngGroup.do', null);
		break;
	case 'M':
		this.doExecuteDialog('sSelectFcltsMngGroup', "관리그룹 선택", '/popup/showMachFcltsMngGroup.do', null);
		break;
	default :
		this.doExecuteDialog('sSelectFcltsMngGroup', '관리그룹 선택', '/popup/showFcltsMngGroup.do', {});
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltyMaintMngModule();

</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="searchFcltyMaintMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>시설물관리그룹</th>
							<td>
								<input type="hidden" id="sMapFcltsMngNo">
								<input type="text" size="15" id="sFcltsMngGroupNo" title="시설물관리그룹넘버" />-
								<input type="text" size="17" id="sFcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="sSearchFcltsMngGroupNo" class="popupButton">선택</button>
							</td>
							<th>공사계약</th>
							<td>
								<input type="text" size="15" id="sCtrtNo" title="계약번호"/>-
								<input type="text" size="17" id="sCtrtNm" disabled="disabled" title="계약명"/>
								<button id="sCtrtNoPopupBtn" class="popupButton">선택</button>
							</td>
							<td rowspan="3"><button class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>시설물업무구분</th>
							<td>
								<select id="sFcltsJobSe" title="시설물업무구분검색조건">
									<option value="">선택</option>
									<option value="E">전기시설물</option>
									<option value="M">기계시설물</option>
									<option value="C">토목시설물</option>
									<option value="A">건축시설물</option>
									<option value="I">정보통신시설물</option>
								</select>
							</td>
							<th>유지보수공사명</th>
							<td><input type="text" id="sMntnRprCnstNm" size="49" title="유지보수공사명검색조건" /></td>
						</tr>
						<tr>
							<th>유지보수구분</th>
							<td>
								<select id="sMntnRprSe" title="유지보수구분검색조건">
									<option value="">선택</option>
									<option value="1">개량</option>
									<option value="2">보수</option>
									<option value="3">보강</option>
									<option value="4">변경-증설</option>
									<option value="5">변경-구조변경</option>
									<option value="9">기타</option>
								</select>
							</td>
							<th>유지보수공사시작일</th>
							<td>
								<input id="sMntnRprCnstStartDtFr" type="text" class="emdcal" size="17" title="유지보수공사검색시작일" /> ~ <input id="sMntnRprCnstStartDtTo" type="text" class="emdcal" size="17" title="유지보수공사검색종료일" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltyMaintMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">유지보수내역 목록</a></li>
				<li><a href="#tabs2" class="emdTab">유지보수내역 상세</a></li>
				<li><a href="#fileTab" class="emdTab">유지보수 첨부파일</a></li>
				<!-- <li><a href="#tabs3" class="emdTab">유지보수 대상시설물</a></li>
				<li><a href="#tabs4" class="emdTab">유지보수 첨부파일</a></li> -->
			</ul>

			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltyMaintMngList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<table style="width:100%;">
						<tr>
							<th>자료수</th>
							<td><input type="text" id="totalCount" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>공사금액</th>
							<td><input type="text" id="sumMntnRprCnstAmt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<th>유지보수예산</th>
							<td><input type="text" id="sumMntnRprBdgt" style="width:100px;text-align:right;" readonly="readonly"></td>
							<td style="text-align:right;">
								<button id="btnExcelDownload" class="buttonExcel">엑셀 다운로드</button>
								<button id="addBtn" class="buttonAdd">　　추　가　　</button>
								<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
							</td>
						</tr>
					</table>

				</div>
			</div>


			<!-- 유지보수내역 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltyMaintMngListVO">
				<table class="editForm"  style="width:100%;">
					<tr>
						<td colspan="2" style="width:60%;">
							<table class="editForm"  style="width:100%;">
								<tr>
									<th width="100px" height="18" class="required_text" style="border-bottom:none;">시행년도</th>
									<td width="210px" style="border-bottom:none;">
										<select id="enforceYear" title="시행년도">
											<option value="">선택</option>
										</select>
									</td>
									<th width="100px" height="18">시설물업무구분</th>
									<td>
										<select id="fcltsJobSe" title="시설물업무구분" class="EditItem">
											<option value="">선택</option>
											<option value="E">전기시설물</option>
											<option value="M">기계시설물</option>
											<option value="C">토목시설물</option>
											<option value="A">건축시설물</option>
											<option value="I">정보통신시설물</option>
										</select>
										<input type="hidden" id="codeId">
									</td>
								</tr>
								<tr>
									<th height="18" class="required_text">유지보수구분</th>
									<td>
										<select id="mntnRprSe" title="유지보수구분">
											<option value="">선택</option>
											<option value="1">개량</option>
											<option value="2">보수</option>
											<option value="3">보강</option>
											<option value="4">변경-증설</option>
											<option value="5">변경-구조변경</option>
											<option value="9">기타</option>
										</select>,
										<select id="mntnSubRprSe" title="유지보수하위구분">
											<option value="">선택</option>
											<option value="1">개량</option>
											<option value="2">보수</option>
											<option value="3">보강</option>
											<option value="4">변경-증설</option>
											<option value="5">변경-구조변경</option>
											<option value="9">기타</option>
										</select>
										<input type="hidden" id="mntnRprSeq" title="유지보수순번" />
									</td>
									<th height="18">계획이력구분</th>
									<td>
										<select id="planHistSe" title="계획이력구분">
											<option value="P">계획</option>
											<option value="H">이력</option>
										</select>
									</td>
								</tr>
								<tr>
									<th height="17">시설물관리그룹</th>
									<td colspan="3">
										<input type="text" size="20" id="fcltsMngGroupNo" disabled="disabled" title="시설물관리그룹넘버" />-
										<input type="text" size="35" id="fcltsMngGroupNoNm" disabled="disabled" title="시설물관리그룹명"/>
										<button id="searchFcltsMngGroupNo" class="popupButton">선택</button>
									</td>
								</tr>
								<tr>
									<th height="18" class="required_text">계약번호</th>
									<td colspan="3">
										<input type="text" size="20" id="ctrtNo" disabled="disabled" title="계약번호"/>-
										<input type="text" size="35" id="ctrtNm" disabled="disabled" title="계약명"/>
										<button id="ctrtNoPopupBtn" class="popupButton">선택</button>
									</td>

								</tr>
								<tr>
									<th height="18" class="required_text">공사명</th>
									<td colspan="3"><input id="mntnRprCnstNm" type="text"  title="공사명" maxlength="25" size="76" /></td>
								</tr>
								<!-- <tr>
									<th height="18" class="required_text">유지보수부위</th>
									<td colspan="3"><input id="mntnRprPart" type="text" title="유지보수부위" maxlength="25" size="76" /></td>
								</tr> -->
								<tr>
									<th height="18" class="required_text">공사기간</th>
									<td><input id="mntnRprCnstStartDt" type="text" size="11" title="공사시작일자" class="emdcal" />  ~  <input id="mntnRprCnstEndDt" type="text" size="11" title="공사종료일자" class="emdcal" /></td>
									<th height="18" class="required_text">시공자</th>
									<td><input id="cnstrtr" type="text" title="시공자" maxlength="20" style="width:102px;" /></td>
								</tr>
								<tr>
									<th height="18" class="required_text">직접공사비</th>
									<td><input id="mntnRprBdgt" type="text" title="직접공사비" class="ygpaNumber" maxlength="16" style="width:120px;" /> 원</td>
									<th height="18" class="required_text">제·경비율</th>
									<td><input id="mntnRprExpRate" type="text" title="제경비율" class="ygpaNumber" data-decimal-point="2" maxlength="16" style="width:102px;" /> %</td>
								</tr>
								<tr>
									<th height="18" class="required_text">공사금액</th>
									<td colspan="2"><input id="mntnRprCnstAmt" type="text" title="공사금액" class="ygpaNumber" maxlength="16" style="width:120px;" /> 원(직접공사비 * 제·경비율)</td>
								</tr>
								<tr>
									<th height="18" class="required_text">작성자</th>
									<td><input id="wrtUsr" type="text" title="작성자" style="width:120px;" /></td>
									<th height="18" class="required_text">작성일</th>
									<td><input id="wrtDt" type="text" title="작성일" class="emdcal" size="11" /></td>
								</tr>
								<tr>
									<th height="18" class="required_text">유지보수내용</th>
									<td colspan="3"><textarea id="mntnRprCn" style="width:430px;" rows="4" title="유지보수내용" maxlength="1333"></textarea></td>
								</tr>
								<tr>
									<th height="18" class="required_text" style="border-bottom:none;">비고</th>
									<td colspan="3" style="border-bottom:none;"><input id="rm" type="text" title="비고" maxlength="333" size="76" /></td>
								</tr>
							</table>
						</td>
						<td style="border-bottom:none;">
							<table id="mntnRprObjFcltsF" style="display:none"></table>
						</td>
					</tr>
					<!-- <tr>
						<th height="18" class="required_text" style="width:95px;">첨부파일</th>
						<td>
							<select id="fcltyMaintFileList">
								<option value="">선택</option>
							</select>
						</td>
						<td style="text-align:right;">
							<button id="btnPreviewFile">첨부파일 미리보기</button>
							<div id="previewHidden" style="display: none;"></div>
							<button id="btnUploadFile">업로드</button>
							<button id="btnDownloadFile">다운로드</button>
							<button id="btnRemoveFile">첨부파일삭제</button>
						</td>
					</tr> -->
				</table>
				</form>
				<div class="emdControlPanel">
					<button id="addBtn" class="buttonAdd">　　추　가　　</button>
					<button id="deleteBtn" class="buttonDelete">　　삭　제　　</button>
					<button id="saveBtn" class="buttonSave">　　저　장　　</button>

				</div>
			</div>

			<!-- 214. TAB 3 AREA (FILE) -->
			<div id="fileTab" class="emdTabPage" style="overflow:scroll;">
				<table class="detailPanel" style="width:100%;">
					<tr>
						<th style="width:10%; height:20px;">선택디렉토리</th>
						<td>
							<form id="dirForm">
								<input id="dirNo" type="hidden"/>
								<input id="dirNm" type="hidden"/>
								<input id="dirFcltsJobSe" type="hidden"/>
								<input id="dirUpperNo" type="hidden"/>
								<input id="dirPath" type="hidden"/>
								<input id="depthSort" type="hidden"/>
								<input id="leafYn" type="hidden"/>
								<input id="inputDirNm" type="text" size="50" maxlength="100"/>
								<input type="hidden" id="dirQueryOption" value="G"/>
							</form>
						</td>
						<td>
							<button id="btnAtchDirFileSearch">디렉토리/파일 검색</button>
						</td>
						<th style="width:10%; height:20px;">선택첨부파일</th>
						<td>
							<form id="fileForm">
								<input id="atchFileNo" type="hidden"/>
								<input id="atchFileNmPhysicl" type="hidden"/>
								<input id="atchFileSe" type="hidden"/>
								<input id="atchFileSeNm" type="hidden"/>
								<input id="atchFileSj" type="hidden"/>
								<input id="atchFileDirNo" type="hidden"/>
								<input id="atchFileDataSe" type="hidden"/>
								<input id="atchFileFcltsJobSe" type="hidden"/>
								<input id="atchFileFcltsMngNo" type="hidden"/>
								<input id="atchFileFcltsMngSeq" type="hidden"/>
								<input id="atchFileNmLogic" type="text" size="41" disabled/>
							</form>
						</td>
					</tr>
				</table>
				<table style="width:100%;">
					<tr>
						<td style="width:50%;">
							<div id="atchFileDirTreeList" class="tree" style="position:relative; margin:4px; width:455px; height:300px; z-index:10; overflow: scroll; border: 1px solid; margin-right: 8px; border-radius: 7px; padding : 8px;" data-resize="contentFill"></div>
						</td>
						<td style="width:50%;">
							<table id="fileGrid" style="margin:1px; display:none;"></table>
						</td>
					</tr>
				</table>
				<table class="detailPanel" style="width:100%;">
					<tr>
						<td style="width:50%;">
							<button id="btnDirRefresh">디렉토리 재조회</button>
							<button id="btnDirAdd" class="buttonAdd">디렉토리 생성</button>
							<button id="btnDirRename" class="buttonSave">디렉토리명 변경</button>
							<button id="btnDirRemove" class="buttonDelete">디렉토리 삭제</button>
						</td>
						<td style="width:50%; text-align:right;">
							<button id="btnFileAllSelect">전체 선택</button>
							<button id="btnFileUpload" class="buttonAdd">파일 추가</button>
							<button id="btnFileDownload">파일 다운로드</button>
							<button id="btnFileRemove" class="buttonDelete">파일 삭제</button>
							<button id="btnFilePreview">파일 미리보기</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
