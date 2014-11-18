<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
/**
 * @Class Name : GamMngFeeGubunMng.jsp
 * @Description : 관리비 시설구분 관리
 * @Modification Information
 *
 *   수정일          수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.09.22  Lee          최초 생성
 *
 * author Lee
 * since 2014.09.22
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
**/
%>

<%
/******************************** SCRIPT START ********************************/
%>

<script>

/**
 * @FUNCTION NAME : GamMngFeeGubunMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
function GamMngFeeGubunMngModule() {}

// PROTO TYPE 생성
GamMngFeeGubunMngModule.prototype = new EmdModule(600, 500);

/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
GamMngFeeGubunMngModule.prototype.loadComplete = function() {

	// [mainGrid] FLEX GRID 정의 (LIST)
	this.$("#mainGrid").flexigrid({
		module : this,
		url : '<c:url value="/mngFee/gamSelectMngFeeGubunMng.do" />',
		dataType : 'json',
		colModel : [
					{display:'시설구분', 		name:'mngFeeFcltySe',		width:70, 		sortable:false,		align:'center'},
                    {display:'시설구분 명', 	name:'mngFeeFcltySeNm',		width:150, 		sortable:false,		align:'left'},
					{display:'등록자', 			name:'regUsr',				width:100, 		sortable:false,		align:'center'},
                    {display:'등록일시', 		name:'registDt',			width:150, 		sortable:false,		align:'center'}
                    ],
		showTableToggleBtn : false,
		height : 'auto'
	});

	// [mainGrid]-[onItemSelected] EVENT FUNCTION 정의 (ITEM SELECT)
	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
		module.$('#cmd').val('modify');
		// [mngFeeFcltySe] INPUT CONTROL ATTRIBUTE 설정 (READONLY)
		module.$('#mngFeeFcltySe').attr('readonly','readonly');
		// [btnIdCheck] BUTTON CONTROL DISABLE 설정
		module.$("#btnIdCheck").disable();
		// [detailForm] FORM INPUT VALUE 초기화 (DETAIL)
		module.$('#detailForm :input').val('');
		// [detailForm] FORM VALUES 생성 (DETAIL)
		module.makeFormValues('#detailForm', row);
		// [detailForm] DIV VALUES 생성 (DETAIL)
        module.makeDivValues('#detailForm', row);
		// EDIT DATA 설정
		module._editData=module.getFormValues('#detailForm', row);
		// EDIT ROW 설정
		module._editRow=module.$('#mainGrid').selectedRowIds()[0];
	});

	// [mainGrid]-[onItemDoubleClick] EVENT FUNCTION 정의 (ITEM DOUBLE CLICK)
    this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		// [mainTab] FORM TAB CONTROL ACTIVE 설정 (DETAIL TAB)
		module.$("#mainTab").tabs("option", {active: 1});
		// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
		module.$('#cmd').val('modify');
		// [detailForm] FORM VALUES 생성 (DETAIL)
		module.makeFormValues('#detailForm', row);
		// [detailForm] DIV VALUES 생성 (DETAIL)
        module.makeDivValues('#detailForm', row);
		// EDIT DATA 설정
		module._editData=module.getFormValues('#detailForm', row);
		// EDIT ROW 설정
		module._editRow=module.$('#mainGrid').selectedRowIds()[0];
		// ROW <> NULL CHECK
		if (row != null) {
			// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - UPDATE)
			module.$('#cmd').val('modify');
		}
	});

};

/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
GamMngFeeGubunMngModule.prototype.onButtonClick = function(buttonId) {

	// SWITCH (BUTTON ID)
	switch (buttonId) {
		// BUTTON ID = 'btnSearch' CHECK (조회)
		case 'btnSearch':
			// DATA LOAD (LIST)
			this.loadData();
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnAdd' CHECK (추가)
		case 'btnAdd':
			// [detailForm] FORM INPUT VALUE 초기화 (DETAIL)
			this.$('#detailForm :input').val('');
			// [mngFeeFcltySe] INPUT CONTROL ATTRIBUTE 제거 (READONLY)
			this.$('#mngFeeFcltySe').removeAttr('readonly');
			// [mainTab] FORM TAB CONTROL ACTIVE 설정 (DETAIL TAB)
			this.$("#mainTab").tabs("option", {active: 1});
			// [cmd] INPUT CONTROL VALUE 설정 (COMMAND - INSERT)
			this.$("#cmd").val("insert");
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnSave' CHECK (저장)
        case 'btnSave':
			// [detailForm] FORM ARGUMENTS 생성 (DETAIL)
			var inputVO = this.makeFormArgs("#detailForm");
			// [mngFeeFcltySe] VALUE = "" CHECK
			if (this.$('#mngFeeFcltySe').val() == "") {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료가 부정확합니다.');
				// FUNCTION RETURN
				return;
			}
			// [cmd] INPUT CONTROL VALUE CHECK (COMMAND - INSERT)
			if (this.$("#cmd").val() == "insert") {
				// INSERT ACTION
				this.doAction('<c:url value="/mngFee/gamInsertMngFeeGubunMng.do" />', inputVO, function(module, result) {
					// RESULT CODE = "0" CHECK (SUCCESS)
					if (result.resultCode == "0") {
						// DATA LOAD (LIST)
						module.loadData();
					}
					// ALERT MESSAGE DISPLAY (RESULT)
					alert(result.resultMsg);
				});
			// [cmd] INPUT CONTROL VALUE CHECK (COMMAND - UPDATE)
			} else {
				// UPDATE ACTION
				this.doAction('<c:url value="/mngFee/gamUpdateMngFeeGubunMng.do" />', inputVO, function(module, result) {
					// RESULT CODE = "0" CHECK (SUCCESS)
					if (result.resultCode == "0") {
						// DATA LOAD (LIST)
						module.loadData();
					}
					// ALERT MESSAGE DISPLAY (RESULT)
					alert(result.resultMsg);
				});
			}
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnRemove' CHECK (삭제)
		case 'btnRemove':
			// [mainGrid] FLEX GRID SELECT ROW ID = [UNDEFINED, NULL] CHECK
			if (this.$('#mainGrid').selectedRowIds()[0] == undefined &&
				this.$('#mainGrid').selectedRowIds()[0] == null) {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료를 선택하십시오.');
				// FUNCTION RETURN
				return;
			}
   		// BUTTON ID = 'btnDelete' CHECK (삭제)
		case 'btnDelete':
			// [detailForm] FORM ARGUMENTS 생성 (DETAIL)
			var inputVO = this.makeFormArgs("#detailForm");
			// [mngFeeFcltySe] VALUE = "" CHECK
			if (this.$('#mngFeeFcltySe').val() == "") {
				// ALERT MESSAGE DISPLAY (ERROR)
				alert('자료가 부정확합니다.');
				// FUNCTION RETURN
				return;
			}
			// CONFIRM MESSAGE DISPLAY (DELETE)
			if (confirm("삭제하시겠습니까?")) {
				// DELETE ACTION
				this.doAction('<c:url value="/mngFee/gamDeleteMngFeeGubunMng.do" />', inputVO, function(module, result) {
					// RESULT CODE = "0" CHECK (SUCCESS)
					if (result.resultCode == "0") {
						// DATA LOAD (LIST)
						module.loadData();
					}
					// ALERT MESSAGE DISPLAY (RESULT)
					alert(result.resultMsg);
				});
			}
			// SWITCH BREAK
			break;

		// BUTTON ID = 'btnIdCheck' CHECK (ID 중복 확인)
		case 'btnIdCheck':
			// [mngFeeFcltySe] INPUT CONTROL VALUE CHECK (NULL)
			if (this.$("#mngFeeFcltySe").val() == "") {
				// [mngFeeFcltySe] INPUT CONTROL FOCUS 설정
				this.$("#mngFeeFcltySe").focus();
				// ALERT MESSAGE DISPLAY (ERROR)
				alert("코드를 입력하십시오.");
				// FUNCTION RETURN
				return;
			}
			// CHECK ACTION
			this.doAction('<c:url value="/mngFee/gamcheckSeFeeGubunMng.do" />', {checkSe : this.$("#mngFeeFcltySe").val()}, function(module, result) {
				// RESULT CODE = "0" CHECK (SUCCESS)
				if (result.resultCode == 0) {
					// CHECK COUNT <> "0" CHECK (EXIST)
					if (result.checkSeCnt != "0") {
						// ALERT MESSAGE DISPLAY (ERROR)
						alert("이미 사용중인 코드가 존재합니다.");
						// [mngFeeFcltySe] INPUT CONTROL FOCUS 설정
						module.$("#mngFeeFcltySe").focus();
						// [mngFeeFcltySe] INPUT CONTROL VALUE 설정
						module.$("#mngFeeFcltySe").val("");
					// CHECK COUNT = "0" CHECK (NOT EXIST)
					} else {
						// CONFIRM MESSAGE DISPLAY (USE)
						if (confirm("해당 코드를 사용하시겠습니까?")) {
							// [mngFeeFcltySe] INPUT CONTROL VALUE 설정
							module.$("#mngFeeFcltySe").val(result.checkSe);
							// [mngFeeFcltySe] INPUT CONTROL ATTRIBUTE 설정 (READONLY)
							module.$("#mngFeeFcltySe").attr("readonly","readonly");
						// CONFIRM MESSAGE DISPLAY (NOT USE)
						} else {
							// [mngFeeFcltySe] INPUT CONTROL VALUE 설정
							module.$("#mngFeeFcltySe").val("");
							// [mngFeeFcltySe] INPUT CONTROL FOCUS 설정
							module.$("#mngFeeFcltySe").focus();
						}
					}
				}
			});
			// SWITCH BREAK
			break;
	}

};

/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : SUBMIT EVENT
 * @PARAMETER     : NONE
**/
GamMngFeeGubunMngModule.prototype.onSubmit = function() {
	// DATA LOAD (LIST)
	this.loadData();
};

/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
GamMngFeeGubunMngModule.prototype.loadData = function() {
	// [mainTab] FORM TAB CONTROL ACTIVE 설정 (LIST TAB)
	this.$("#mainTab").tabs("option", {active: 0});
	// [searchForm] FORM ARGUMENTS 생성 (SEARCH)
	var searchOpt=this.makeFormArgs('#searchForm');
	// [mainGrid] FLEX GRID DATA RELOAD
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : TAB CHANGE EVENT
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
GamMngFeeGubunMngModule.prototype.onTabChange = function(newTabId, oldTabId) {

	// SWITCH (NEW TAB ID)
	switch (newTabId) {
		// NEW TAB ID = 'listTab' CHECK (LIST TAB)
		case 'listTab':
			// SWITCH BREAK
			break;
		// NEW TAB ID = 'detailTab' CHECK (DETAIL TAB)
		case 'detailTab':
			// SWITCH BREAK
			break;
	}

};

// MODULE INSTANCE 생성
var module_instance = new GamMngFeeGubunMngModule();

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
							<th>시설 구분</th>
							<td>
								<input type="text" size="10" id="sMngFeeFcltySe" maxlength="2">
							</td>
							<th>시설 구분 명</th>
							<td>
								<input type="text" size="10" id="sMngFeeFcltySeNm" maxlength="20">
							</td>
							<td>
								<button id="btnSearch" class="buttonSearch">조회</button>
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
				<li><a href="#listTab" class="emdTab">관리비 시설구분</a></li>
				<li><a href="#detailTab" class="emdTab">관리비 시설구분 상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" style="overflow: hidden;" >
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div id="listSumPanel" class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<td style="text-align: right">
									<button id="btnAdd">추가</button>
									<button id="btnRemove">삭제</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow:scroll;">
				<div class="emdControlPanel">
					<form id="detailForm">
						<input type="hidden" id="cmd"/>
						<input type="hidden" id="oldMngFeeFcltySe"/>
						<table class="detailPanel" style="width:100%">
							<tr>
								<th width="20%" height="18">시설 구분</th>
								<td><input type="text" size="20" id="mngFeeFcltySe" maxlength="2"/></td>
								<td><button id="btnIdCheck">중복체크</button>
							</tr>
							<tr>
								<th width="20%" height="18">시설 구분 명</th>
								<td colspan="2"><input type="text" size="65" id="mngFeeFcltySeNm" maxlength="20"/></td>
							</tr>
							<tr>
								<th width="20%" height="18">등록자</th>
                               	<td><span data-column-id="regUsr"></span></td>
								<td><span data-column-id="registDt"></span></td>
							</tr>
							<tr>
								<th width="20%" height="18">수정자</th>
                               	<td><span data-column-id="updUsr"></span></td>
								<td><span data-column-id="updtDt"></span></td>
							</tr>
						</table>
					</form>
					<table style="width:100%">
						<tr>
							<td width="100"></td>
							<td style="text-align:right">
								<button id="btnSave" class="buttonSave">저장</button>
								<button id="btnDelete" class="buttonDelete">삭제</button>
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
