<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
  * @Class Name : GamPopupAtchDirFile.jsp
  * @Description : 디렉토리/파일 팝업 (Prototype)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.03.24  ACEWOLF          최초 생성
  *
  * author ACEWOLF
  * since 2014.11.28
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
 * @FUNCTION NAME : GamPopupAtchDirFileModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamPopupAtchDirFileModule() {}

GamPopupAtchDirFileModule.prototype = new EmdPopupModule(600, 440);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamPopupAtchDirFileModule.prototype.loadComplete = function(params) {

	this.resizable(true);
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '/popup/selectAtchDirFileList.do',
		dataType : "json",
		colModel : [
					{display:'디렉토리', 	name:'dirPath',		width:330, 		sortable:false,		align:'left'},
					{display:"파일명",		name:"fileNm",		width:200,		sortable:false,		align:"left"}
					],
		height: "300"
	});

	this.$("#mainGrid").on('onLoadDataComplete', function(event, module, data) {
		module.selectData();
	});

	this.$("#mainGrid").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module.closeDialog("ok", row);
	});

	if (params != null) {

		if(params.sFcltsJobSe == "G"){
			this.$('#jobSe1').hide();
		this.$('#sFcltsJobSe').append('<option value="G">유지보수</option>');
			this.$('#jobSe2').show();
		}else{
			this.$('#jobSe2').hide();
		}
		this.$('#sSearchSe').val(params.sSearchSe);
		this.$('#sFcltsJobSe').val(params.sFcltsJobSe);


	}
	this.$('#sDirFileNm').focus();

};

<%
/**
 * @FUNCTION NAME : selectData
 * @DESCRIPTION   : DATA SELECT
 * @PARAMETER     : NONE
**/
%>
GamPopupAtchDirFileModule.prototype.selectData = function() {

	var gridRowCount = this.$("#mainGrid").flexRowCount();
	if (gridRowCount == 0) {
		alert('해당 조건의 자료가 존재하지 않습니다!');
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
GamPopupAtchDirFileModule.prototype.onButtonClick = function(buttonId) {

	switch (buttonId) {
		case 'btnOk':
	    	this.processOk();
			break;
	    case 'btnCancel':
	    	this.processCancel();
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
GamPopupAtchDirFileModule.prototype.onSubmit = function() {

	this.loadData();

};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamPopupAtchDirFileModule.prototype.loadData = function() {

	var sDirFileNm = this.$('#sDirFileNm').val();
	if (sDirFileNm.length < 2) {
		alert("디렉토리명/파일명을 2자 이상 입력하십시오.");
		return;
	}
	var searchOpt = this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();

};

<%
/**
 * @FUNCTION NAME : processOk
 * @DESCRIPTION   : OK PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupAtchDirFileModule.prototype.processOk = function() {

	var row = this.$("#mainGrid").selectedRows();
	if (row.length>0) {
		this.closeDialog("ok", row[0]);
	} else {
		alert("항목을 선택하십시요.");
	}

};

<%
/**
 * @FUNCTION NAME : processCancel
 * @DESCRIPTION   : CANCEL PROCESS
 * @PARAMETER     : NONE
**/
%>
GamPopupAtchDirFileModule.prototype.processCancel = function() {

	this.cancelDialog();

};

var popup_instance = new GamPopupAtchDirFileModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<div class="dialog">
	<div class="emdPanel">
		<!-- 11. SEARCH AREA (조회조건 영역) -->
		<form id="searchForm">
			<table class="searchPanel">
				<tbody>
					<tr>
						<th>조회 구분</th>
						<td>
							<select id="sSearchSe">
								<option value="D" selected>디렉토리 검색</option>
								<option value="F">파일 검색</option>
							</select>
						</td>
						<th>시설 구분</th>
						<td id="jobSe1">
							<select id="sFcltsJobSe">
								<option value="" selected>전체</option>
								<option value="A">건축시설</option>
								<option value="C">토목시설</option>
								<option value="M">기계시설</option>
								<option value="E">전기시설</option>
								<option value="I">통신시설</option>
							</select>
						</td>
						<td id="jobSe2">
							<span>유지보수</span>
						</td>
						<td rowspan="2">
							<button class="buttonSearch">조회</button>
						</td>
					</tr>
					<tr>
						<th>디렉토리명/파일명</th>
						<td colspan="3">
							<input type="text" size="55" id="sDirFileNm" maxlength="100"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<!-- 2. DATA AREA (자료 영역) -->
		<div class="emdPanel fillHeight">
			<table id="mainGrid" style="display: none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<button id="btnOk">선택</button>
				<button id="btnCancel">취소</button>
			</div>
		</div>
	</div>
</div>


<%
/******************************** UI       END ********************************/
%>
