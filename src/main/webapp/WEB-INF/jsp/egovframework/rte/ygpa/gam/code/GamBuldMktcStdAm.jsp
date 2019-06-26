<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamAssetMngt.jsp
  * @Description : 자산코드관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.29  장은성          최초 생성
  *
  * author 장은성
  * since 2013.10.29
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamBuldMktcStdAmVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />


<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamBuldMktcStdAmModule() {}

GamBuldMktcStdAmModule.prototype = new EmdModule(800, 500);

// 페이지가 호출 되었을때 호출 되는 함수
GamBuldMktcStdAmModule.prototype.loadComplete = function() {
	this.$("#mainGrid").flexigrid({
		module: this,
		url: '/code/gamBuldMktcStdAmList.do',
		dataType: "json",
		colModel : [
			{display:"순번", 			name:"sn",					width:30, 	sortable:false,	 align:"center"},
			{display:"연도", 			name:"stdyy",				width:80, 	sortable:false,	 align:"center"},
			{display:"주소", 			name:"adres",				width:100, 	sortable:false,	 align:"center"},
			{display:"물건지", 			name:"thingPaprAdres",		width:150, 	sortable:false,	 align:"right"},
			{display:"시가표준액(원)", 	name:"mktcStdAm",			width:150, 	sortable:false,	 align:"right"},
			{display:"시가표준액(원)", 	name:"mktcStdAm",			width:150, 	sortable:false,	 align:"right"},
			{display:"연면적(m2)",		name:"totar",				width:150, 	sortable:false,	 align:"right"}
			],
			showTableToggleBtn : false,
			height: 'auto',		

	});

	 // 그리드 클릭
 	this.$("#mainGrid").on('onItemSelected', function(event, module, row, grid, param) {
		module._mainKeyValue = row.adres;
		module._mainmode = "modify";
    });

	 // 그리드 더블클릭
	this.$("#mainGrid").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module._mainKeyValue = row.adres;
		module._mainmode = "modify";
		module.$("#mainTab").tabs("option", {active: 1});
	});

}

<%
/**
 * @FUNCTION NAME : onTabChange
 * @DESCRIPTION   : 탭이 변경 될때 호출된다. (태그로 정의 되어 있음)
 * @PARAMETER     :
 *   1. newTabId - NEW TAB ID
 *   2. oldTabId - OLD TAB ID
**/
%>
 GamBuldMktcStdAmModule.prototype.onTabChange = function(newTabId, oldTabId) {
	console.log("onTabChange");
	switch (newTabId) {
		case 'listTab':
			break;
		case 'detailTab':
				// 탭 1번 수정 때
			if (this._mainmode == "modify") {	// 탭 1번 수정 때
				this.loadDetail(oldTabId);
				this.$("#adres").disable();
				
			} else if (this._mainmode == "insert") { // 탭 1번 추가 때
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});
				
				//this.$('#popupSpecFcltsMngGroupNo').enable();
				
				/* var toDate = new Date();
				var toStdyy = toDate.getFullYear();
				this.$("stdyy").enable();
				this.$('#stdyy').val(toStdyy); */
				
				//기본 데이터 설정
				// this.addData();
			} else {
				this.makeFormValues('#detailForm', {});
				this.makeDivValues('#detailForm', {});				
			}
			break;
	}

}

<%
/**
 * @FUNCTION NAME : onButtonClick
 * @DESCRIPTION   : 버튼클릭 호출된다. 
 * @PARAMETER     :
 *   1. buttonId - buttonId
**/
%>
GamBuldMktcStdAmModule.prototype.onButtonClick = function(buttonId) {
	console.log("onButtonClick");
	switch (buttonId) {
		case 'btnAdd':
			this._mainmode = 'insert';
			this._mainKeyValue = '';
			this.$("#mainTab").tabs("option", {active: 1});
			break;
		case 'btnSave':
			this.saveData();
			break;
		case 'btnDelete':
		case 'btnDelete1':
			if (this._mainmode=="modify") {
				this.loadDetail('listTab');
				this.deleteData();
			}
			break;
	}
}

<%
/**
 * @FUNCTION NAME : onSubmit
 * @DESCRIPTION   : (프레임워크에서 SUBMIT 이벤트 호출 시 호출 한다.)
 * @PARAMETER     : NONE
**/
%>
 GamBuldMktcStdAmModule.prototype.onSubmit = function() {
	this.$("#adres").val('');
	this.loadData();
}

<%
/**
 * @FUNCTION NAME : loadData
 * @DESCRIPTION   : DATA LOAD (LIST)
 * @PARAMETER     : NONE
**/
%>
 GamBuldMktcStdAmModule.prototype.loadData = function() {
	this._mainmode='';
	this.$("#mainTab").tabs("option", {active: 0});	// 탭 이동
	var searchOpt=this.makeFormArgs('#searchForm');	// searchOpt = {"sYear":"", "sFcltsGbn":"", "sFcltsMngGroupNm":""}
	this.$('#mainGrid').flexOptions({params:searchOpt}).flexReload();	// 그리드 설정된 내용으로 로딩

}

<%
/**
 * @FUNCTION NAME : loadDetail
 * @DESCRIPTION   : 상세항목을 로딩 한다.
 * @PARAMETER     :
 *   1. tabId - TAB ID
**/
%>
GamBuldMktcStdAmModule.prototype.loadDetail = function(tabId) {
	console.log("loadDetail");
	if (tabId == 'listTab') {
		var row = this.$('#mainGrid').selectedRows();	// 현재 선택된 row 값 가지고 오기
		if (row.length==0) {
			alert('선택된 항목이 없습니다.');
			this.$("#mainTab").tabs("option", {active: 0});
			return;
		}
		this.makeFormValues('#detailForm', row[0]);	// row[0] 번째 데이터를 detailForm에 넣기(자동으로 id 매칭하여 넣어 줌)
//		this.makeDivValues('#detailForm', row[0]);

	}

}

<%
/**
 * @FUNCTION NAME : saveData
 * @DESCRIPTION   : 항목을 저장한다.
 * @PARAMETER     : NONE
**/
%>
GamBuldMktcStdAmModule.prototype.saveData = function() {
	console.log("saveData");
	
 	if (!validateGamBuldMktcStdAmVO(this.$("#detailForm")[0])){
		return;
	} 
 		
	var inputVO = this.makeFormArgs("#detailForm");
	if (this._mainmode == "insert") { // 추가 버튼 눌렀을때 insert로 변경됨
		this.doAction('/code/gamBuldMktcStdAmInsertList.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	} else { // 추가 버튼 눌렀을때 insert가 아닌경우
		this.doAction('/code/gamBuldMktcStdAmUpdateList.do', inputVO, function(module, result) {
			if (result.resultCode == "0") {
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}
}


<%
/**
 * @FUNCTION NAME : deleteData
 * @DESCRIPTION   : 항목을 삭제한다.
 * @PARAMETER     : NONE
**/
%>
GamBuldMktcStdAmModule.prototype.deleteData = function() {
	console.log("deleteData");

	if (confirm("삭제하시겠습니까?")) {
		//var deleteVO = row[0];
		var deleteVO = this.makeFormArgs("#detailForm");
		this.doAction('/code/gamBuldMktcStdAmDeleteList.do', deleteVO, function(module, result) {
			if (result.resultCode == "0") {
				module._mainKeyValue = '';
				module.loadData();
			}
			alert(result.resultMsg);
		});
	}
	
} 

//다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamBuldMktcStdAmModule();

</script>
<%
/******************************** UI     START ********************************/
%>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId }'/>
<div  class="window_main">
<!-- 11. SEARCH AREA (조회조건 영역) -->
	<div id="searchViewStack" class="emdPanel">
		<div class="viewPanel">
			<form id="searchForm">
							<input id="sSearch" data-column-id="sSearch" type="hidden" />			
						<table style="width:100%;" class="searchPanel">
							<tbody>
								<tr>
									<th>주소</th>
									<td>
									<input type="text"  id="adres" data-column-id="adres" size = "40">  <!-- sAdres, sLnm, sSlno -->
									</td>
									<th>지번</th>
									<td>
									<input id="lnm" data-column-id="lnm" type="text" size="4">
									</td>
									<th>부번</th>
									<td>
										<input id="slno" data-column-id="slno" type="text" size="4">
									</td>
									<td><button class="buttonSearch">조회</button></td>
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
			<ul>
				<li><a href="#listTab" class="emdTab">건축물시가표준액목록</a></li>
				<li><a href="#detailTab" class="emdTab">건축물시가표준액상세</a></li>
			</ul>
			<!-- 212. TAB 1 AREA (LIST) -->
			<div id="listTab" class="emdTabPage fillHeight" data-onactivate="onShowTab2Activate">
				<table id="mainGrid" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd" class="buttonAdd">추가</button>
					<button id="btnDelete" class="buttonDelete">삭제</button>		
				</div>
			</div>
			
			<!-- 213. TAB 2 AREA (DETAIL) -->
			<div id="detailTab" class="emdTabPage" style="overflow: scroll">
				<form id="detailForm">
								<input type="hidden"  id="sn" data-column-id="sn" />
				<table class="detailPanel">
					<tr>
							<th width="20%" height="23" class="required_text">기준년원일</th>
							<td>								
								<select id="stdyy" data-column-id="stdyy">	
									<option value="" selected>-선택-</option>						
									<option value="2019">2019</option>								
								</select>
 							</td>
 							<th width="20%" height="23" class="required_text">건물형태</th>
							<td>								
								<select id="buldStle" data-column-id="buldStle">
									<option value="" selected>-선택-</option>						
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
 							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">건물용도</th>
							<td>								
								<select id="buldPrpos" data-column-id="buldPrpos">
									<option value="" selected>-선택-</option>														
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
 							</td>
 							<th width="20%" height="23" class="required_text">건물구조</th>
							<td>								
								<select id="buldRescue" data-column-id="buldRescue">
									<option value="" selected>-선택-</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
 							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">건물위치</th>
							<td>								
								<select id="buldLc" data-column-id="buldLc">
									<option value="" selected>-선택-</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
 							</td>
 							<th width="20%" height="23" class="required_text">건물지붕</th>
							<td>								
								<select id="buldRf" data-column-id="buldRf">
									<option value="" selected>-선택-</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
 							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">주소</th>
							<td><input type="text" size="40" id="adres" data-column-id="adres" maxlength="10" /></td>
							<th width="20%" height="23"  class="required_text">지번</th>
							<td><input type="text" size="6" id="lnm" data-column-id="lnm" maxlength="8" />
							<button>주소</button></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">건물동</th>
							<td><input type="text" size="40" id="bulddong" data-column-id="bulddong" maxlength="60" /></td>
							<th width="20%" height="23" class="required_text">건물호</th>
							<td><input type="text" size="40" id="bdh" data-column-id="bdh" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">연면적</th>
							<td><input type="text" size="40" id="totar" data-column-id="totar" maxlength="200" /></td>
							<th width="20%" height="23" class="required_text">전용면적</th>
							<td><input type="text" size="40" id="prvuseAr" data-column-id="prvuseAr" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공용면적</th>
							<td><input type="text" size="40" id="cmnuseAr" data-column-id="cmnuseAr" maxlength="200" /></td>
							<th width="20%" height="23" class="required_text">준공일자</th>
							<td><input type="text" size="40" id="competDe" data-column-id="competDe" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공부상 지목</th>
							<td><input type="text" size="40" id="studyUpptLndcgr" data-column-id="studyUpptLndcgr" maxlength="200" /></td>
							<th width="20%" height="23" class="required_text">토지면적</th>
							<td><input type="text" size="40" id="ladAr" data-column-id="ladAr" maxlength="60" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시가표준액</th>
							<td><input type="text" size="40" id="mktcStdAm" data-column-id="mktcStdAm" maxlength="200" />
							</td>
							<th width="20%" height="23" class="required_text">데이터기준일자</th>
							<td><input type="text" size="40" id="dataStdrDe" data-column-id="dataStdrDe" maxlength="60" />
							</td>
						</tr>
											
				</table>
				<div style="vertical-align: bottom; text-align: right;">
					<button id="btnSave">저장</button>					
					<button id="btnDelete">삭제</button>
				</div>
				</form>				
		</div>
	</div>
</div>
