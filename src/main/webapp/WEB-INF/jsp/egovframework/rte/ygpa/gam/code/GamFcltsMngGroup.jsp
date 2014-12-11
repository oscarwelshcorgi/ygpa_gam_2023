<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="/WEB-INF/tlds/emf-validator.tld" %>
<%
  /**
  * @Class Name : GamFcltsMngGroup.jsp
  * @Description : 시설물 그룹관리 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.12.10  	 김종민          화면단 최초 생성
  *
  * author HNJ
  * since 2014.12.5
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="gamFcltsMngGroup" method="validateFcltsMngGroup" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */


function GamFcltsMngGroupModule() {}

GamFcltsMngGroupModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltsMngGroupModule.prototype.loadComplete = function() {
	
	// 테이블 설정
	this.$("#fcltsMngGroupList").flexigrid({
		module: this,
		url: '/code/selectFcltsMngGroupList.do',
		dataType: "json",
		colModel : [
					{display:"시설물그룹번호", 	name:"fcltsMngGroupNo",   	width:120, sortable:true, align:"center"},
					{display:"시설물그룹명",  	name:"fcltsMngGroupNm", 	width:150, sortable:true, align:"center"},
					{display:"관리주체",  		name:"mngMainbd", 			width:150, sortable:true, align:"center"},
					{display:"준공일자",  		name:"bldDt", 				width:90,  sortable:true, align:"center"},
					{display:"하자만료일자",  	name:"flawEndDt", 			width:90,  sortable:true, align:"center"},
					{display:"소유자",  		name:"owner", 				width:150, sortable:true, align:"center"},
					{display:"시공자",  		name:"cnstrtr", 			width:150, sortable:true, align:"center"},
					{display:"설계자",  		name:"planner", 			width:150, sortable:true, align:"center"},
					{display:"감리자",  		name:"inspector", 			width:150, sortable:true, align:"center"},
					{display:"공사명",  		name:"cnstNm", 				width:200, sortable:true, align:"center"},
					{display:"공사발주자",  	name:"cnstOrderBody", 		width:150, sortable:true, align:"center"},
					{display:"공사감독관",  	name:"cnstSupervisor", 		width:150, sortable:true, align:"center"},
					{display:"시설물종별",  	name:"fcltsGbn", 			width:100, sortable:true, align:"center"},
					{display:"시설물구분",  	name:"fcltsSe", 			width:100, sortable:true, align:"center"},
					{display:"시설물종류",  	name:"fcltsKnd", 			width:100, sortable:true, align:"center"},
					{display:"시설물주소",  	name:"fcltsAdres", 			width:400, sortable:true, align:"center"},
					],
		height: "auto"
	});

	this._cmd = '';
	
	this.$("#fcltsMngGroupList").on('onItemSelected', function(event, module, row, grid, param) {
		module._cmd = "modify";
	});

	this.$("#fcltsMngGroupList").on("onItemDoubleClick", function(event, module, row, grid, param) {
		module._cmd = "modify";
		module.$("#fcltsMngGroupListTab").tabs("option", {active: 1});
	});
};

GamFcltsMngGroupModule.prototype.onSubmit = function() {
	this.loadData();
};

//시설물관리그룹 목록로드
GamFcltsMngGroupModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#fcltsMngGroupSearchForm');
	this.$('#fcltsMngGroupList').flexOptions({params:searchOpt}).flexReload();
};

//시설물관리그룹 상세보기
GamFcltsMngGroupModule.prototype.loadDetailData = function() {
	var selectRows = this.$('#fcltsMngGroupList').selectedRows();
	if(selectRows.length > 0) {
		var row = selectRows[0];
		var opts = [{name: 'fcltsMngGroupNo', value: row['fcltsMngGroupNo'] }];
		this.doAction('/code/selectFcltsMngGroupDetail.do', opts, function(module, result) { 
			if(result.resultCode == "0"){
				module.makeFormValues('#fcltsMngGroupDetailForm', result.result);
			}
			else {
				module._cmd="";
				module.initDisplay();
				alert(result.resultMsg);
			}
		});	
	}
};

//화면 및 데이터 초기화 처리
GamFcltsMngGroupModule.prototype.initDisplay = function() {
	this.$("#fcltsMngGroupDetailForm :input").val("");
	if(this._cmd == "insert") {
		this.$('#fcltsMngGroupNo').enable();
		this.$("#fcltsMngGroupListTab").tabs("option", {active: 1});
	} else if (this._cmd == "modify") {
		this.$('#fcltsMngGroupNo').disable();
	} else {
		this.$('#fcltsMngGroupNo').enable();
		this.$("#fcltsMngGroupListTab").tabs("option", {active: 0});
	}
};

//시설물관리그룹 데이터 삽입
GamFcltsMngGroupModule.prototype.insertData = function() {
	var data = this.makeFormArgs("#fcltsMngGroupDetailForm");
 	this.doAction('/code/insertFcltsMngGroupDetail.do', data, function(module, result) {
 		if(result.resultCode == "0"){
 			module._cmd = "modify";
 			module.$('#fcltsMngGroupNo').disable();
 		}
 		alert(result.resultMsg);
 	});	
};

//시설뮬관리그룹 데이터 수정
GamFcltsMngGroupModule.prototype.updateData = function() {
	var data = this.makeFormArgs("#fcltsMngGroupDetailForm");
	this.doAction('/code/updateFcltsMngGroupDetail.do', data, function(module, result) {
		if(result.resultCode == "0"){
			module.loadData();
		}
		alert(result.resultMsg);
	});	
};

//시설뮬관리그룹 데이터 삽입 및 수정
GamFcltsMngGroupModule.prototype.saveData = function() {
	if(!validateFcltsMngGroup(this.$('#fcltsMngGroupDetailForm')[0])){ 		
		return;
	}
	if(this._cmd == "insert") {
		this.insertData();
	} else if (this._cmd == "modify") { 
		this.updateData();
	}			
};

//시설뮬관리그룹 데이터 삭제
GamFcltsMngGroupModule.prototype.deleteData = function() {
	var rows = this.$("#fcltsMngGroupList").selectedRows();
	if(rows.length <= 0){
		alert("삭제할 시설물관리그룹을 선택하십시오.");
		return;
	}
	if(confirm("시설물관리그룹을 삭제하시겠습니까?")) {
		var row = rows[0];
		var data = { 'fcltsMngGroupNo': row['fcltsMngGroupNo'] };
	 	this.doAction('/code/deleteFcltsMngGroupDetail.do', data, function(module, result) {
	 		if(result.resultCode == "0") {
				module._cmd = "";
				module.initDisplay();
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}
};

/**
 * 정의 된 버튼 클릭 시
 */
GamFcltsMngGroupModule.prototype.onButtonClick = function(buttonId) {
	switch(buttonId) {
		case 'btnSearch' :
			this._cmd = "";
			this.initDisplay();
			this.loadData();			
			break;
		case 'btnAdd':
			this._cmd = "insert";
			this.initDisplay();
			break;
		case 'btnSave':
			this.saveData();
			break;
		case 'btnRemove':
			this.deleteData();
			break;
	}
};


GamFcltsMngGroupModule.prototype.onTabChange = function(newTabId, oldTabId) {
	if(oldTabId == 'tabs1' && this._cmd == 'modify') {
		this.initDisplay();
		this.$('#tabs2').scrollTop(0);
		this.loadDetailData();
	}
	switch(newTabId) {
		case "tabs1":			
			break;
		case "tabs2":
			if((this._cmd != 'insert') && (this._cmd != 'modify')) {
				this.$("#fcltsMngGroupListTab").tabs("option", {active: 0});
				alert('시설물관리그룹항목을 선택하시거나 추가버튼을 누르세요.');
			}			
			break;
	}
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamFcltsMngGroupModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltsMngGroupSearchForm">
				<table class="searchPanel">
					<tbody>
						<tr>
	                        <th>시설물 관리 그룹번호</th>
	                        <td><input id="sFcltsMngGroupNo" type="text" size="14" maxlength="14" /></td>
							<th>시설물 관리 그룹명</th>
	                        <td><input id="sFcltsMngGroupNm" type="text" size="20" /></td>
							<td><button id="btnSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="emdPanel fillHeight">
		<div id="fcltsMngGroupListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
			<ul>
				<li><a href="#tabs1" class="emdTab">시설물그룹관리목록</a></li>
				<li><a href="#tabs2" class="emdTab">시설물그룹관리상세</a></li>
			</ul>

			<!-- 시설물그룹관리그룹 목록 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltsMngGroupList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="btnAdd">추가</button>
					<button id="btnRemove">삭제</button>
				</div>
			</div>

			<!-- 시설물그룹관리그룹 상세 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltsMngGroupDetailForm" >
					<table class="searchPanel editForm">
						<tr>
							<th width="15%" height="20" class="required_text">시설물관리그룹번호</th>
							<td><input type="text" id="fcltsMngGroupNo" size="20" maxlength="14"/></td>
							<th width="15%" height="20" class="required_text">시설물관리그룹명</th>
							<td><input type="text" id="fcltsMngGroupNm" size="45" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">시설물종류</th>
							<td><input type="text" id="fcltsKnd" size="15" /></td>
							<th height="20" class="required_text">공사명</th>
							<td><input type="text" id="cnstNm" size="45" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">관리주체</th>
							<td><input type="text" id="mngMainbd" size="15" /></td>
							<th height="20" class="required_text">관리주체구분</th>
							<td>
								<select id="mngMainbdSe">
									<option value="">선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<th height="20" class="required_text">소유자</th>
							<td><input type="text" id="owner" size="15" /></td>
							<th height="20" class="required_text">준공일자</th>
							<td><input type="text" id="bldDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">내진설계적용유무</th>
							<td>
								<select id="erqProofPlanApplcEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th height="20" class="required_text">설계자</th>
							<td><input type="text" id="planner" size="15" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">설계시작일자</th>
							<td><input type="text" id="planBeginDt" size="15" class="emdcal" /></td>
							<th height="20" class="required_text">설계종료일자</th>
							<td><input type="text" id="planEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">공사시작일자</th>
							<td><input type="text" id="cnstBeginDt" size="15" class="emdcal" /></td>
							<th height="20" class="required_text">공사종료일자</th>
							<td><input type="text" id="cnstEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">시공자</th>
							<td><input type="text" id="cnstrtr" size="15" /></td>
							<th height="20" class="required_text">시공금액</th>
							<td><input type="text" id="cnstrctAmt" size="45" class="ygpaNumber" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">하자만료일자</th>
							<td><input type="text" id="flawEndDt" size="15" class="emdcal" /></td>
							<th height="20" class="required_text">감리자</th>
							<td><input type="text" id="inspector" size="15" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">감리시작일자</th>
							<td><input type="text" id="inspectBeginDt" size="15" class="emdcal" /></td>
							<th height="20" class="required_text">감리종료일자</th>
							<td><input type="text" id="inspectEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">공사발주자</th>
							<td><input type="text" id="cnstOrderBody" size="15" /></td>
							<th height="20" class="required_text">시설물주소</th>
							<td><input type="text" id="fcltsAdres" size="15" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">공사감독관</th>
							<td><input type="text" id="cnstSupervisor" size="15" /></td>
							<th height="20" class="required_text">설계도서보존</th>
							<td><input type="text" id="planBookMnten" size="15" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">시설물종별</th>
							<td><input type="text" id="fcltsGbn" size="15" /></td>
							<th height="20" class="required_text">시설물구분</th>
							<td><input type="text" id="fcltsSe" size="15" /></td>
						</tr>
						<tr>
							<th height="20" class="required_text">비고</th>
							<td colspan="3"><input type="text" id="remark" size="90" /></td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="btnSave">저장</button>
				</div>
			</div>
		</div>
	</div>
</div>