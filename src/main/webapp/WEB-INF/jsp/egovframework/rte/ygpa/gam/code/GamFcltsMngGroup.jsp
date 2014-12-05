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
  *  2014.12.5  	 HNJ          화면단 최초 생성
  *
  * author HNJ
  * since 2014.12.5
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<validator:javascript formName="fcltsMngGroupVO" method="validateFcltsMngGroupVO" staticJavascript="false" dynamicJavascript="true" xhtml="true" cdata="false" />
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */


function GamFcltsMngGroupModule() {}

GamFcltsMngGroupModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamFcltsMngGroupModule.prototype.loadComplete = function() {
	
	this._mode = "";

	// 테이블 설정
	this.$("#fcltsMngGroupList").flexigrid({
		module: this,
		url: '/code/selectFcltsMngGroupList.do',
		dataType: "json",
		colModel : [
					{display:"시설물그룹번호", name:"fcltsMngGroupNo",   width:150, sortable:true, align:"center"},
					{display:"시설물그룹명",  name:"fcltsMngGroupNm", 	width:200, sortable:true, align:"center"},
					{display:"공사명",  name:"cnstNm", width:200, sortable:true, align:"center"},
					{display:"관리주체",  name:"mngMainbd", 	width:150, sortable:true, align:"center"},
					{display:"소유자",  name:"owner", 	width:150, sortable:true, align:"center"},
					{display:"시공자",  name:"cnstrtr", 	width:150, sortable:true, align:"center"},
					{display:"주소",  name:"fcltsAdres", 	width:400, sortable:true, align:"center"}
					],
		height: "auto"
	});
	
	this.$("#fcltsMngGroupList").on('onItemSelected', function(event, module, row, grid, param) {
		module._mode = 'modify';
	});

	this.$("#fcltsMngGroupList").on("onItemDoubleClick", function(event, module, row, grid, param) {

		module._mode="modify";
		module.$("#fcltsMngGroupListTab").tabs("option", {active: 1});			// 탭을 전환 한다.

	});
	
	
	// 
	this.$('#depthSort').on('change', {module: this}, function(e) {
		
		var inputVO = {'mainFcltsDiv':e.data.module.$("#mainFcltsDiv").val(), 'depthSort':e.data.module.$("#depthSort").val()};
		
		e.data.module.selectFcltsClUpperCdList(inputVO);
		e.data.module.$("#fcltsMngGroup").val('');
	});
	
	this.$('#mainFcltsDiv').on('change', {module: this}, function(e) {
		var inputVO = {'mainFcltsDiv':e.data.module.$("#mainFcltsDiv").val(), 'depthSort':e.data.module.$("#depthSort").val()};
		
		e.data.module.selectFcltsClUpperCdList(inputVO);
		e.data.module.$("#fcltsMngGroup").val('');
	});
	
	this.$('#fcltsClUpperCd').on('change', {module: this}, function(e) {
		e.data.module.$("#fcltsMngGroup").val('');
	});

};

GamFcltsMngGroupModule.prototype.onSubmit = function() {

	this.loadData();

};


GamFcltsMngGroupModule.prototype.loadData = function() {
	
	this.makeFormValues('#fcltsMngGroupVO', {});
	this.$("#fcltsMngGroupListTab").tabs("option", {active: 0});
	var searchOpt=this.makeFormArgs('#fcltsMngGroupForm');
	this.$('#fcltsMngGroupList').flexOptions({params:searchOpt}).flexReload();

};


GamFcltsMngGroupModule.prototype.loadDetail = function() {

	var row = this.$('#fcltsMngGroupList').selectedRows();
	
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltsMngGroupListTab").tabs("option", {active: 0});
		return;
	}

	this.makeFormValues('#fcltsMngGroupVO', row[0]);
	this.selectFcltsClUpperCdList(row[0]);

};


GamFcltsMngGroupModule.prototype.selectFcltsClUpperCdList = function(vo) {

	this.doAction('/code/selectFcltsClUpperCdList.do', vo, function(module, result) {

 		if(result.resultCode == "0"){
 			var opts = "<option value=''>선택</option>";
 			var fcltsClUpperCdList = result.fcltsClUpperCdList;
 			var resultCnt = fcltsClUpperCdList.length;
			
 			for(i=0;i<resultCnt;i++){
 				opts += "<option value='" + fcltsClUpperCdList[i].fcltsMngGroup + "'>" + fcltsClUpperCdList[i].fcltsMngGroupNm + "</option>";
 			}
 			
 			module.$("#fcltsClUpperCd").empty();
 			module.$("#fcltsClUpperCd").append(opts);
 			
 			module.$("#fcltsClUpperCd").val(vo.fcltsClUpperCd);
 		}
 	});

	return true;
};


GamFcltsMngGroupModule.prototype.addData = function() {

	this._mode="insert";
	this.$("#fcltsMngGroupListTab").tabs("option", {active: 1});

};


GamFcltsMngGroupModule.prototype.saveData = function() {
	
	if(!validateFcltsMngGroupVO(this.$("#fcltsMngGroupVO")[0])) return;

 	var inputVO = this.makeFormArgs("#fcltsMngGroupVO");
 	//alert(this._mode);
	if(this._mode == "insert") {
	 	this.doAction('/code/insertFcltsMngGroup.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}else{
	 	this.doAction('/code/updateFcltsMngGroup.do', inputVO, function(module, result) {
	 		if(result.resultCode == "0"){
	 			module.loadData();
	 		}
	 		alert(result.resultMsg);
	 	});
	}

};


GamFcltsMngGroupModule.prototype.deleteData = function() {
	
	var row = this.$('#fcltsMngGroupList').selectedRows();
	if(row.length==0) {
		alert('선택된 항목이 없습니다.');
		this.$("#fcltsMngGroupListTab").tabs("option", {active: 0});
		return;
	}
	
	if(confirm("하위코드가 전부 같이 삭제됩니다. 삭제하시겠습니까?")){
		var inputVO = this.makeFormArgs("#fcltsMngGroupVO");
	 	this.doAction('/code/deleteFcltsMngGroup.do', inputVO, function(module, result) {
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
GamFcltsMngGroupModule.prototype.onButtonClick = function(buttonId) {

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


GamFcltsMngGroupModule.prototype.onTabChange = function(newTabId, oldTabId) {
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
				this.$("#fcltsMngGroup").disable();
				this.$("#depthSort").disable();
				this.$("#fcltsClUpperCd").disable();
				this.$("#leafYn").disable();
			} else {
				this._mode="insert";
				this.makeFormValues('#fcltsMngGroupVO', {});
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
var module_instance = new GamFcltsMngGroupModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="fcltsMngGroupForm">
				<table class="searchPanel">
					<tbody>
						<tr>
	                        <th>시설물 관리 그룹번호</th>
	                        <td>
	                        	<input id="sFcltsMngGroupNo" type="text" size="14" maxlength="14" />
	                        </td>
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

			<!-- 목록 탭 -->
			<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
				<table id="fcltsMngGroupList" style="display:none" class="fillHeight"></table>
				<div class="emdControlPanel">
					<button id="addBtn">추가</button>
				</div>
			</div>

			<!-- 저장 및 상세 탭 -->
			<div id="tabs2" class="emdTabPage" style="overflow: hidden;">
				<form id="fcltsMngGroupVO" >
					<input type="hidden" id="cmd"/>
					<table class="searchPanel editForm">
						<tr>
							<th width="20%" height="23" class="required_text">시설물관리그룹번호</th>
							<td><input type="text" id="fcltsMngGroupNo" size="15" /></td>
							<th width="20%" height="23" class="required_text">시설물관리그룹명</th>
							<td><input type="text" id="fcltsMngGroupNm" size="45" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시설물종류</th>
							<td><input type="text" id="fcltsKnd" size="15" /></td>
							<th width="20%" height="23" class="required_text">공사명</th>
							<td><input type="text" id="cnstNm" size="45" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">관리주체</th>
							<td><input type="text" id="mngMainbd" size="15" /></td>
							<th width="20%" height="23" class="required_text">관리주체구분</th>
							<td>
								<select id="mngMainbdSe">
									<option value="">선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">소유자</th>
							<td><input type="text" id="owner" size="15" /></td>
							<th width="20%" height="23" class="required_text">준공일자</th>
							<td><input type="text" id="bldDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">내진설계적용유무</th>
							<td>
								<select id="erqProofPlanApplcEnnc">
									<option value="">선택</option>
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
							</td>
							<th width="20%" height="23" class="required_text">설계자</th>
							<td><input type="text" id="planner" size="15" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설계시작일자</th>
							<td><input type="text" id="planBeginDt" size="15" class="emdcal" /></td>
							<th width="20%" height="23" class="required_text">설계종료일자</th>
							<td><input type="text" id="planEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사시작일자</th>
							<td><input type="text" id="cnstBeginDt" size="15" class="emdcal" /></td>
							<th width="20%" height="23" class="required_text">공사종료일자</th>
							<td><input type="text" id="cnstEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시공자</th>
							<td><input type="text" id="cnstrtr" size="15" /></td>
							<th width="20%" height="23" class="required_text">시공금액</th>
							<td><input type="text" id="cnstrctAmt" size="45" class="ygpaNumber" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">하자만료일자</th>
							<td><input type="text" id="flawEndDt" size="15" class="emdcal" /></td>
							<th width="20%" height="23" class="required_text">감리자</th>
							<td><input type="text" id="inspector" size="15" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">감리시작일자</th>
							<td><input type="text" id="inspectBeginDt" size="15" class="emdcal" /></td>
							<th width="20%" height="23" class="required_text">감리종료일자</th>
							<td><input type="text" id="inspectEndDt" size="15" class="emdcal" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사발주자</th>
							<td><input type="text" id="cnstOrderBody" size="15" /></td>
							<th width="20%" height="23" class="required_text">시설물주소</th>
							<td><input type="text" id="fcltsAdres" size="15" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">공사감독관</th>
							<td><input type="text" id="#cnstSupervisor" size="15" /></td>
							<th width="20%" height="23" class="required_text">설계도서보존</th>
							<td><input type="text" id="planBookMnten" size="15" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">시설물종별</th>
							<td><input type="text" id="fcltsGbn" size="15" /></td>
							<th width="20%" height="23" class="required_text">시설물구분</th>
							<td><input type="text" id="fcltsSe" size="15" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">비고</th>
							<td colspan="3"><input type="text" id="remark" size="90" /></td>
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