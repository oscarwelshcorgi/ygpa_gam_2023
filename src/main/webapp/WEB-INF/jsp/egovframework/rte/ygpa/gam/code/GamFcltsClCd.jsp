<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltsClCd.jsp
  * @Description : 시섦물관리코드관리 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.17  	 HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.17
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltsClCdVO" method="validateFcltsClCdVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */


function GamFcltsClCdModule() {}

GamFcltsClCdModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltsClCdModule.prototype.loadComplete = function() {
	
	this._mode = "";

	// 테이블 설정
	this.$("#fcltsClCdList").flexigrid({
		module: this,
		url: '/code/selectFcltsClCdList.do',
		dataType: "json",
		colModel : [
					{display:"순번", 			name:"rnum",				width:50, 		sortable:false,		align:"center"},
					{display:"분류상위코드", 	name:"fcltsClUpperCd",		width:120, 		sortable:false,		align:"center"},
					{display:"단계", 			name:"depthSort",			width:50,	 	sortable:false,		align:"left"},
					{display:"분류코드", 		name:"fcltsClCd",			width:120, 		sortable:false,		align:"center"},
					{display:"분류코드명",		name:"fcltsClCdNm",			width:250, 		sortable:false,		align:"left"},
					{display:"LEAF여부", 		name:"leafYn",				width:70, 		sortable:false,		align:"center"}
					],
		height: "auto"
	});
	
	this.$("#fcltsClCdList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});

	this.$("#fcltsClCdList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		module._mode="modify";
		module.$("#fcltsClCdListTab").tabs("option", {active: 1});			// 탭을 전환 한다.

	});
	
	
	// 
	this.$('#depthSort').on('change', {module: this}, function(e) {
		
		var inputVO = {'mainFcltsDiv':e.data.module.$("#mainFcltsDiv").val(), 'depthSort':e.data.module.$("#depthSort").val()};
		
		e.data.module.selectFcltsClUpperCdList(inputVO);
		e.data.module.$("#fcltsClCd").val('');
	});
	
	this.$('#mainFcltsDiv').on('change', {module: this}, function(e) {
		var inputVO = {'mainFcltsDiv':e.data.module.$("#mainFcltsDiv").val(), 'depthSort':e.data.module.$("#depthSort").val()};
		
		e.data.module.selectFcltsClUpperCdList(inputVO);
		e.data.module.$("#fcltsClCd").val('');
	});
	
	this.$('#fcltsClUpperCd').on('change', {module: this}, function(e) {
		e.data.module.$("#fcltsClCd").val('');
	});

};

GamFcltsClCdModule.prototype.onSubmit = function() {

	this.loadData();

};


GamFcltsClCdModule.prototype.loadData = function() {
	
	this.makeFormValues('#fcltsClCdVO', {});
	this.$("#fcltsClCdListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#fcltsClCdForm');
	this.$('#fcltsClCdList').flexOptions({params:searchOpt}).flexReload();

};


GamFcltsClCdModule.prototype.loadDetail = function() {

	var row = this.$('#fcltsClCdList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltsClCdListTab").tabs("option", {active: 0});
		return;
	}

	this.makeFormValues('#fcltsClCdVO', row[0]);
	this.selectFcltsClUpperCdList(row[0]);

};


GamFcltsClCdModule.prototype.selectFcltsClUpperCdList = function(vo) {

	this.doAction('/code/selectFcltsClUpperCdList.do', vo, function(module, result) {

 		if(result.resultCode == "0"){
 			var opts = "<option value=''>선택</option>";
 			var fcltsClUpperCdList = result.fcltsClUpperCdList;
 			var resultCnt = fcltsClUpperCdList.length;
			
 			for(i=0;i<resultCnt;i++){
 				opts += "<option value='" + fcltsClUpperCdList[i].fcltsClCd + "'>" + fcltsClUpperCdList[i].fcltsClCdNm + "</option>";
 			}
 			
 			module.$("#fcltsClUpperCd").empty();
 			module.$("#fcltsClUpperCd").append(opts);
 			
 			module.$("#fcltsClUpperCd").val(vo.fcltsClUpperCd);
 		}
 	});

	return true;
};


GamFcltsClCdModule.prototype.addData = function() {

	this._mode="insert";
	this.$("#fcltsClCdListTab").tabs("option", {active: 1});

};


GamFcltsClCdModule.prototype.saveData = function() {
	
	if(!validateFcltsClCdVO(this.$("#fcltsClCdVO")[0])) return;

 	var inputVO = this.makeFormArgs("#fcltsClCdVO");
 	//alert(this._mode);
	if(this._mode == "insert") {
	 	this.doAction('/code/insertFcltsClCd.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/code/updateFcltsClCd.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltsClCdModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltsClCdList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltsClCdListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("하위코드가 전부 같이 삭제됩니다. 삭제하시겠습니까?")){
		var inputVO = this.makeFormArgs("#fcltsClCdVO");
	 	this.doAction('/code/deleteFcltsClCd.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


/**
 * 정의 된 버튼 클릭 시
 */
GamFcltsClCdModule.prototype.onButtonClick = function(buttonId) {

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
	}
};


GamFcltsClCdModule.prototype.onTabChange = function(newTabId, oldTabId) {
	switch(newTabId) {
		case "tabs1":
		break;

		case "tabs2":
			if(this._mode=="modify") {
				this.loadDetail();
				// 삭제버튼 활성화
				this.$("#deleteBtn").show();
				//수정금지 비활성화
				this.$("#mainFcltsDiv").disable();
				this.$("#fcltsClCd").disable();
				this.$("#depthSort").disable();
				this.$("#fcltsClUpperCd").disable();
				this.$("#leafYn").disable();
			} else {
				this._mode="insert";
				this.makeFormValues('#fcltsClCdVO', {});
				// 삭제버튼 활성화
				this.$("#deleteBtn").hide();
				this.$("#fcltsClUpperCd").empty();
				this.$("#leafYn").val('Y');
				
				// 입력항목 활성화
				this.$("#mainFcltsDiv").enable();
				this.$("#depthSort").enable();
				this.$("#fcltsClUpperCd").enable();
				this.$("#leafYn").disable();
			}
		break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltsClCdModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltsClCdForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>메인시설물분류</th>
							<td>&nbsp;
								<select id="sMainFcltsDiv" class="select">
									<option value="" selected="selected">전체</option>
									<c:forEach  items="${mainFcltyClCdList}" var="mainFcltyClCdItem">
                                        <option value="${mainFcltyClCdItem.mainFcltsClCd }">${mainFcltyClCdItem.mainFcltsClCdNm }</option>
                                    </c:forEach>
								</select>
							</td>
							<th>분류코드</th>
							<td><input id="sFcltsClCd" type="text" size="15" maxlength="15" title="분류코드" /></td>
							<th>분류코드명</th>
							<td><input id="sFcltsClCdNm" type="text" size="20" maxlength="60" title="분류코드명" /></td>
							<td><button class="buttonSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltsClCdListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물분류코드목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물분류코드상세</a></li>
			</ul>

			<!-- 목록 탭 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltsClCdList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>

			<!-- 저장 및 상세 탭 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltsClCdVO" style="height:370px;">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel editForm">
						<tr>
							<th width="20%" height="23" class="required_text">메인시설물분류</th>
							<td>
								<select id="mainFcltsDiv" class="select">
									<option value="" selected="selected">선택</option>
									<c:forEach  items="${mainFcltyClCdList}" var="mainFcltyClCdItem">
                                        <option value="${mainFcltyClCdItem.mainFcltsClCd }">${mainFcltyClCdItem.mainFcltsClCdNm }</option>
                                    </c:forEach>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">분류코드</th>
							<td>
								<input type="text" id="fcltsClCd" size="15" disabled="disabled" />
								<input type="hidden" id="oriFcltsClCd"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">단계</th>
							<td>
								<select id="depthSort">
									<option value="">선택</option>
									<option value="1">1단계</option>
									<option value="2">2단계</option>
									<option value="3">3단계</option>
									<option value="4">4단계</option>
									<option value="5">5단계</option>
									<option value="6">6단계</option>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">분류상위코드</th>
							<td>
								<select id="fcltsClUpperCd">
									<option value="">선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">분류코드명</th>
							<td><input type="text" size="40" id="fcltsClCdNm" maxlength="20" /></td>
							<th width="20%" height="23" class="required_text"> LEAF 여부</th>
							<td>
								<select id="leafYn">
									<option value="Y" selected="selected">Y</option>
									<option value="N">N</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="saveBtn">저장</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>
</div>