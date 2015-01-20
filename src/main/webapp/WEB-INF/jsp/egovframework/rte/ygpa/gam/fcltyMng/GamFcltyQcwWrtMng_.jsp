<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMng_.jsp
  * @Description : 시설 점검 관리(샘플)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.24  	김종민          최초 생성
  *
  * author 김종민
  * since 2014.11.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<%
/******************************** SCRIPT START ********************************/
%>
<script>

<%
/**
 * @FUNCTION NAME : GamFcltyQcwWrtMngModule
 * @DESCRIPTION   : MODULE 고유 함수
 * @PARAMETER     : NONE
**/
%>
function GamFcltyQcwWrtMngModule() {
}

GamFcltyQcwWrtMngModule.prototype = new EmdModule(1000,750);

<%
/**
 * @FUNCTION NAME : loadComplete
 * @DESCRIPTION   : PAGE LOAD COMPLETE (페이지 호출시 실행되는 함수)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadComplete = function() {
	
	this._mainmode = '';
	
	this.$('#mainGrid').flexigrid({
		module: this,
		url: '/fcltyMng/selectQcMngDtlsList.do',
		dataType: 'json',
		colModel : [
					{display:"관리그룹",		name:"fcltsMngGroupNm",		width:150,		sortable:false,		align:"center"},
					{display:"업무구분",		name:"fcltsJobSeNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검관리순번",	name:"qcMngSeq",			width:90,		sortable:false,		align:"center"},
					{display:"점검관리명", 	    name:"qcMngNm",				width:200,		sortable:false,		align:"left"},
					{display:"시행년도",		name:"enforceYear",			width:60,		sortable:false,		align:"center"},
					{display:"점검구분",    	name:"qcSeNm",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단자",    	name:"qcInspTpNm",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단일자",	name:"qcInspDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검진단구분",    name:"qcInspSeNm",			width:120,		sortable:false,		align:"center"},
					{display:"상태평가등급",	name:"sttusEvlLvlNm",		width:90,		sortable:false,		align:"center"},
					{display:"점검진단금액",	name:"qcInspAmt",			width:120,		sortable:false,		align:"right",	displayFormat: 'number'},
					{display:"점검시작일자",    name:"qcBeginDt",			width:90,		sortable:false,		align:"center"},
					{display:"점검종료일자",	name:"qcEndDt",				width:90,		sortable:false,		align:"center"},
					{display:"점검진단기관명",	name:"qcInspInsttNm",		width:150,		sortable:false,		align:"left"},
					{display:"책임기술자명",	name:"responEngineerNm",	width:150,		sortable:false,		align:"left"},
			],
		height: 'auto',
		preProcess : function(module,data) {
			module.$('#totalCount').val($.number(data.totalCount));
			return data;
		}
	});

	this.$('#mainGrid').on('onLoadDataComplete', function(event, module, data) {
		module.loadDataComplete();
	});

	this.$('#mainGrid').on('onItemSelected', function(event, module, row, grid, param) {
	});
	
	this.$('#mainGrid').on('onItemDoubleClick', function(event, module, row, grid, param) {
	});

	this.$('#qcObjGrid').flexigrid({
		module: this,
		url: '',
		dataType: 'json',
		colModel : [
					{display:"선택",		name:"chkYn",		width:50,	sortable:false,	align:"center"},
					{display:"점검시설명",	name:"prtFcltyNm",	width:235,	sortable:false,	align:"left"},
			],
		height: '500'
	});
	
	this.$('#sFcltsMngGroupNo').bind('click', {module: this}, function(event) {
		event.data.module.$('#sFcltsMngGroupNo').val('');
		event.data.module.$('#sFcltsMngGroupNm').val('');
	});
	
	this.fillSelectBoxYear('#enforceYear');	
	this.fillSelectBoxYear('#sEnforceYear'); 		
};

<%
/**
 * @FUNCTION NAME : fillSelectBoxYear
 * @DESCRIPTION   : Select Element에 2000년 부터 현재년도까지 채워 넣는 함수
 * @PARAMETER     : Select Element ID
**/
%>
GamFcltyQcwWrtMngModule.prototype.fillSelectBoxYear = function(id) {
	var curYear = (new Date()).getFullYear();
	for(var i=curYear; i>=2000; i--) {
		this.$(id).append('<option value="' + i + '">' + i + '년</option>');
	}
};

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.onSubmit = function() {
	this.loadData();
};

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadData = function() {
	this.$('#mainTab').tabs('option', {active: 0});
	var searchOpt = this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : loadDataComplete
 * @DESCRIPTION   : DATA LOAD COMPLETE(LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadDataComplete = function() {
};

<%
/**
 * @FUNCTION NAME : refreshData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.refreshData = function() {
	var searchOpt = this.makeFormArgs('#searchForm');
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();
};

<%
/**
 * @FUNCTION NAME : downloadExcel
 * @DESCRIPTION   : 리스트를 엑셀로 다운로드 한다.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.downloadExcel = function() {
	var rowCount = this.$('#mainGrid').flexRowCount();
	if (rowCount <= 0) {
		alert('조회된 자료가 없습니다.');
		return;
	}
	this.$('#mainGrid').flexExcelDown('/fcltyMng/excelDownloadQcMngDtlsList.do');
};

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세정보를 가져옴.
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.loadDetail = function(tabId) {
};

<%
/**
 * @FUNCTION NAME : setControlStatus
 * @DESCRIPTION   : 컨트롤 상태 변경
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.setControlStatus = function() {
	if(this._mainmode == 'insert') {
		this.$('#fcltsJobSe').enable();
		this.$('#popupDetailFcltsMngGroup').enable();
		this.$('#popupDetailFcltsMngGroup').removeClass('ui-state-disabled');
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
		this.$('#btnSave').enable();
		this.$('#btnSave').removeClass('ui-state-disabled');
	}
	else if(this._mainmode == 'modify') {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
	}
	else if(this._mainmode == 'listed') {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
	} 
	else {
		this.$('#fcltsJobSe').disable();
		this.$('#popupDetailFcltsMngGroup').disable({disableClass:'ui-state-disabled'});
		this.$('#btnAdd').disable({disableClass:'ui-state-disabled'});
		this.$('#btnDelete').disable({disableClass:'ui-state-disabled'});
	}
};

<%
/**
 * @FUNCTION NAME : validateDetailForm
 * @DESCRIPTION   : Detail Form Validate 체크
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.validateDetailForm = function() {
	if(this.$('#fcltsMngGroupNo').val() == '') {
		alert('관리그룹을 입력하세요.');
		return false;
	}
	if(this.$('#fcltsJobSe').val() == '') {
		alert('업무구분을 입력하세요.');
		return false;
	}
	if((this._cmd == 'modify') && (this.$('#qcMngSeq').val() == '')) {
		alert('잘못된 순번입니다.');
		return false;
	}
	return true;
};

<%
/**
 * @FUNCTION NAME : saveDetailData
 * @DESCRIPTION   : Detail 데이터 저장
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.saveDetailData = function() {
};

<%
/**
 * @FUNCTION NAME : deleteDetailData
 * @DESCRIPTION   : Detail 데이터 저장
 * @PARAMETER     : NONE
**/
%>
GamFcltyQcwWrtMngModule.prototype.deleteDetailData = function() {
};

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : BUTTON CLICK EVENT
 * @PARAMETER     :
 *   1. buttonId - BUTTON ID
**/
%>
GamFcltyQcwWrtMngModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch':
			break;
			
		case 'btnExcelDownload':
			break;
			
		case 'btnAdd' :
			break;
			
		case 'btnDelete' :
			break;
			
		case 'btnSave' :
			break;
		
		case 'popupDetailFcltsMngGroup':
			this.doExecuteDialog('selectDetailFcltsMngGroup', '관리그룹 선택', '/popup/showFcltsMngGroup.do', {});
			break;

		case 'popupSearchFcltsMngGroup':
			this.doExecuteDialog('selectSearchFcltsMngGroup', '관리그룹 선택', '/popup/showFcltsMngGroup.do', {});
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
GamFcltyQcwWrtMngModule.prototype.onClosePopup = function(popupId, msg, value){
	switch(popupId){
		case "selectDetailFcltsMngGroup":
			this.$("#fcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#fcltsMngGroupNm").val(value["fcltsMngGroupNm"]);
			break;
		case "selectSearchFcltsMngGroup":
			this.$("#sFcltsMngGroupNo").val(value["fcltsMngGroupNo"]);
			this.$("#sFcltsMngGroupNm").val(value["fcltsMngGroupNm"]);
			break;
		default:
			alert("알수없는 팝업 이벤트가 호출 되었습니다.");
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
GamFcltyQcwWrtMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
			break;
	}
};

var module_instance = new GamFcltyQcwWrtMngModule();

</script>

<%
/******************************** SCRIPT   END ********************************/
%>


<%
/******************************** UI     START ********************************/
%>

<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 1. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewStack">
			<form id="searchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>관리그룹</th>
							<td>
								<input type="text" size="15" id="sFcltsMngGroupNo" title="시설물관리그룹넘버" />-
								<input type="text" size="17" id="sFcltsMngGroupNm" disabled="disabled" title="시설물관리그룹명"/>
								<button id="popupSearchFcltsMngGroup" class="popupButton">선택</button>
							</td>
							<th></th>
							<td></td>
							<td rowspan="3"><button id="btnSearch" class="buttonSearch">조회</button></td>
						</tr>
						<tr>
							<th>업무구분</th>
							<td>
								<select id="sFcltsJobSe" class="searchEditItem">
									<option value="">선택</option>
									<option value="E">전기시설</option>
									<option value="M">기계시설</option>
									<option value="C">토목시설</option>
									<option value="A">건축시설</option>
									<option value="I">정보통신시설</option>
								</select>
								<input id="sFcltsJobSeNm" type="hidden" />
							</td>
							<th>점검관리명</th>
							<td><input type="text" id="sQcMngNm" size="50" /></td>
						</tr>
						<tr>
							<th>점검구분</th>
							<td>
								<select id="sQcSe" class="searchEditItem">
                                    <option value="">선택</option>
                                    <option value="1">해빙기대비</option>
                                    <option value="2">풍수해대비</option>
                                    <option value="3">동절기대비</option>
                                    <option value="4">우기대비</option>
                                </select>
                                <input id="sQcSeNm" type="hidden" />
							</td>
							<th>시행년도</th>
							<th>
								<select id="sEnforceYear">
									<option value="">선택</option>
                                </select>
							</th>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 2. DATA AREA (자료 영역) -->
	<div class="emdPanel fillHeight">
		<!-- 2.1. TAB AREA (탭 영역) -->
		<div id="mainTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<!-- 2.1.1. TAB 정의 -->
			<ul>
				<li><a href="#listTab" class="emdTab">시설물점검목록</a></li>
				<li><a href="#detailTab" class="emdTab">시설물점검내역</a></li>
			</ul>
			
			<!-- 2.1.2. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage" style="overflow: hidden;">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<form id="listSumForm">
						<table style="width:100%;">
							<tr>
								<th style="width:6%; height:20; text-align:center;">자료수</th>
								<td><input type="text" size="12" id="totalCount" class="ygpaNumber" disabled="disabled"/></td>
								<td style="text-align:right;">
									<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
									<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
	                                <button id="btnExcelDownload" class="buttonExcel">엑셀　다운로드</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<!-- 2.1.3. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow: hidden;">
				<form id="detailForm">
				<!-- 2.1.3.1 Layout Table -->
				<table style="width:100%;" class="editForm">
					<tr>
						<td width="70%">
							<!-- 2.1.3.1.1 Element Table -->
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<th width="14%">관리그룹</th>
									<td colspan="3">
										<input type="hidden" id="fcltsMngGroupNo"/>
										<input type="text" size="40" id="fcltsMngGroupNm" disabled="disabled"/>
										<button id="popupDetailFcltsMngGroup" class="popupButton">선택</button>
									</td>
								</tr>
								<tr>
									<th width="14%" height="17">점검관리순번</th>
									<td>
										<input type="text" size="10" id="qcMngSeq" disabled="disabled"/>
									</td>
									<th width="14%" height="17">업무구분</th>
									<td>
										<select id="fcltsJobSe">
											<option value="">선택</option>
											<option value="A">건축시설</option>
											<option value="C">토목시설</option>
											<option value="E">전기시설</option>
											<option value="I">정보통신시설</option>
											<option value="M">기계시설</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">점검관리명</th>
									<td colspan="3">
										<input type="text" size="86" id="qcMngNm" maxlength="200" />
									</td>
								</tr>
								<tr>
									<th height="17">시행년도</th>
									<td>
										<!-- 년도 자동 주입 -->
										<select id="enforceYear">
											<option value="">선택</option>
		                                </select>
									</td>
									<th height="17">점검구분</th>
									<td>
										<select id="qcSe">
		                                    <option value="">선택</option>
		                                    <option value="1">해빙기대비</option>
		                                    <option value="2">풍수해대비</option>
		                                    <option value="3">동절기대비</option>
		                                    <option value="4">우기대비</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">시행일자</th>
									<td><input id="qcInspDt" type="text" class="emdcal" size="20"/></td>
									<th height="17">점검진단자</th>
									<td>
										<select id="qcInspTp">
		                                    <option value="">선택</option>
		                                    <option value="1">자체점검</option>
		                                    <option value="2">용역점검</option>
		                                </select>
									</td>
								</tr>
								<tr>
									<th height="17">점검진단구분</th>
									<td>
										<select id="qcInspSe">
		                                    <option value="">선택</option>
		                                    <option value="1">정기점검</option>
		                                    <option value="2">정밀점검</option>
		                                    <option value="3">초기점검</option>
		                                    <option value="4">긴급점검(손상)</option>
		                                    <option value="5">긴급점검(특별)</option>
		                                    <option value="6">정밀안전점검(정기)</option>
		                                    <option value="7">정밀안전점검(긴급)</option>
		                                    <option value="8">정밀안전점검(하자)</option>
		                                    <option value="9">기타</option>
		                                </select>
									</td>
									<th height="17">점검진단금액</th>
									<td><input id="qcInspAmt" type="text" size="30" class="ygpaNumber"/></td>
								</tr>
								<tr>
									<th height="17">상태평가등급</th>
									<td>
										<select id="sttusEvlLvl">
		                                    <option value="">선택</option>
		                                    <option value="A">A</option>
		                                    <option value="B">B</option>
		                                    <option value="C">C</option>
		                                    <option value="D">D</option>
		                                    <option value="E">E</option>
		                                    <option value="Z">불명</option>
		                                </select>			
									</td>
									<th height="17">점검진단기관명</th>
									<td><input type="text" size="30" id="qcInspInsttNm" maxlength="60" /></td>
								</tr>
								<tr>
									<th height="17">점검기간</th>
									<td colspan="3">
										<input id="qcBeginDt" type="text" class="emdcal" size="20"/> ~ 
										<input id="qcEndDt" type="text" class="emdcal" size="20"/>
									</td>
								</tr>
								<tr>
									<th height="17">책임기술자명</th>
									<td colspan="3"><input type="text" size="20" id="responEngineerNm" maxlength="60" /></td>
								</tr>
								<tr>
									<th height="17">점검진단결과</th>
									<td colspan="3">
										<textarea id="qcInspResult" cols="84" rows="5"></textarea>
										<button id="popupSelectQcResultItem" class="popupButton">점검결과항목선택</button>	
									</td>
								</tr>
								<tr>
									<th height="17">조치내용</th>
									<td colspan="3"><textarea id="actionCn" cols="84" rows="5"></textarea></td>
								</tr>
								<tr>
									<th height="17">비고</th>
									<td colspan="3"><input id="rm" type="text" size="86"/></td>
								</tr>
								<tr>
									<th height="17">첨부파일</th>
									<td colspan="2">
										<select id="atchFile">
											<option value="">선택</option>
		                                </select>	
									</td>
									<td align="right">
										<button id="btnUploadFile">업로드</button>
										<button id="btnDownloadFile">다운로드</button>
										<button id="btnRemoveFile">파일삭제</button>
									</td>
								</tr>
							</table>
						</td>
						<td width="30%">
							<!-- 2.1.3.1.2 Grid Table -->
							<table  class="detailPanel"  style="width:100%;">
								<tr>
									<td>
										<table id="qcObjGrid" style="display:none" class="fillHeight"></table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnAdd" class="buttonAdd">　　추　가　　</button>
					<button id="btnDelete" class="buttonDelete">　　삭　제　　</button>
					<button id="btnSave" class="buttonSave">　　저　장　　</button>
				</div>
			</div>
		</div>
	</div>
</div>

<%
/******************************** UI       END ********************************/
%>
