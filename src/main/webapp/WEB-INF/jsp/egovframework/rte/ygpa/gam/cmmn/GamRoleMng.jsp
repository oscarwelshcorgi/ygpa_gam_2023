<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamRoleMng.jsp
  * @Description : 롤 관리 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.05.20  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.05.20
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamRoleMngModule() {
	this._cmd='';
}

GamRoleMngModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamRoleMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#roleMngList").flexigrid({
		module: this,
		url: '<c:url value="/sec/gmt/selectRoleList.do" />',
		dataType: "json",
		colModel : [
					{display:"선택", 		name:"delYn",		width:30, 	sortable:false,		align:"center", displayFormat:"checkbox"},
					{display:"롤 ID", 	name:"roleCode",	width:100, 	sortable:false,		align:"center"},
					{display:"롤명", 		name:"roleNm",		width:150, 	sortable:false,		align:"left"},
					{display:"롤타입", 	name:"roleTyp",		width:50, 	sortable:false,		align:"center"},
					{display:"롤 Sort", 	name:"roleSort",	width:50, 	sortable:false,		align:"center"},
					{display:"롤설명", 	name:"roleDc",		width:180, 	sortable:false,		align:"left"},
					{display:"등록일자", 	name:"roleCreatDe",	width:80, 	sortable:false,		align:"center"}
					],
		//usepager: true,
		//useRp: true,
		//rp: 20,
		//showTableToggleBtn: false,
		//width: "730",
		//height: "300",
		height: "auto",
		preProcess: function(module, data){
			for(var i=0; i<data.length; i++) {
				data[i].delYn=false;
			}
			return data;
		}
	});
	
	this.$("#roleMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#roleMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.
		if(row != null) {
			module._cmd='modify';
			module.makeFormValues('#roleManage', row);
		}
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
GamRoleMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#roleMngForm");
			this.$("#roleMngListTab").tabs("option", {active: 0});
		 	this.$("#roleMngList").flexOptions({params:searchOpt}).flexReload();
		 	//
		break;
		case "listBtn":
			this.$("#roleMngListTab").tabs("option", {active: 0});
			break;
		// 등록
		case "addBtn":
			this.$("#roleMngListTab").tabs("option", {active: 1}); 
			this.$('#roleManage :input').val('');
			this._cmd='insert';
			this.$('#groupNm').focus();
		break;
		// 저장
		case "saveBtn":
			var inputVo = this.makeFormArgs("#roleManage");
			var url;
			if(this._cmd=='modify') {
				url='<c:url value="/sec/gmt/gamRoleListUpdt.do" />';
			}
			else {
				url='<c:url value="/sec/gmt/gamRoleListInsert.do" />';
			}
			this.doAction(url, inputVo, function(module, result) {
		 		if(result.resultCode == 0){
		 			var searchOpt = module.makeFormArgs("#roleMngForm");
		 			module.$('#roleManage :input').val('');
		 			module.$("#roleMngListTab").tabs("option", {active: 0}); 
		 			module.$("#roleMngList").flexOptions({params:searchOpt}).flexReload();
		 		}
		 		alert(result.resultMsg);			 		
		 	});
		break;
		// 삭제
		case "deleteBtn":
			
			var filter = [{ 'col': 'delYn', 'filter': true}];
			var reglist = this.$("#roleMngList").selectFilterData(filter);

			if(reglist.length > 0){

				var inputVO = [{name: 'delList', value: []}];
				for(var i=0; i<reglist.length; i++){
					inputVO[0].value[inputVO[0].value.length] = reglist[i].groupId;
				}
				this.doAction('<c:url value="/sec/gmt/gamRoleListDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			var searchOpt = module.makeFormArgs("#roleMngForm");
			 			module.$("#roleMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);			 		
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
		break;
	}
};


GamRoleMngModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			
		break;
	}
};

GamRoleMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamRoleMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="roleMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>롤 명</th>
							<td>
								<input id="searchKeyword" type="text" size="30" title="검색" />
							</td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
							
						</tr>
					</tbody>
				</table>
<!-- 				<div class="emdControlPanel"> -->
<!-- 					<button id="searchBtn">조회</button> -->
<!-- 				</div> -->
			</form>
		</div>
	</div>
	<div id="roleMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">롤 목록</a></li>
			<li><a href="#tabs2" class="emdTab">롤 상세</a></li>
		</ul>
		<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
			<table id="roleMngList" style="display:none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<button id="addBtn">등록</button>
				<button id="deleteBtn">삭제</button>
			</div>
		</div>
		<div id="tabs2" class="emdTabPage fillHeight" style="overflow: hidden;">
			<form id="roleManage">
				<table class="searchPanel editForm">
					<colgroup>
						<col width="20%" />
						<col />
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th width="20%" height="23">롤코드</th>
						<td><input name="roleCode" id="roleCode" type="text" size="90" title="롤 코드" readonly="readonly" /></td>
					</tr>
					<tr>
						<th width="20%" height="23">롤명</th>
						<td><input name="roleNm" id="roleNm" data-role="required" type="text" maxLength="50" size="90" title="롤명"/>&nbsp;<span data-role="errors" data-column-id="roleNm" ></span></td>
					</tr>
					<tr>
						<th width="20%" height="23">롤패턴</th>
						<td><input name="rolePtn" id="rolePtn" data-role="required" type="text" maxLength="200" size="90" title="롤패턴"/>&nbsp;<span data-role="errors" data-column-id="rolePtn" ></span></td>
					</tr>
					<tr>
						<th width="20%" height="23">설명</th>
						<td><input name="roleDc" id="roleDc" type="text" maxLength="50" size="90" title="설명"/></td>
					</tr>
					<tr>
						<th width="20%" height="23">롤타입</th>
						<td>
							<input class="ygpaCmmnCd" data-role="required" data-code-id="COM029" data-column-id="roleTyp" title="롤타입" data-default-prompt="롤 타입 선택" />&nbsp;<span data-role="errors" data-column-id="roleTyp" ></span>
						</td>
					</tr>
					<tr>
						<th class="required_text" width="20%" height="23">롤 Sort</th>
						<td><input name="roleSort" id="roleSort" type="text" value="<c:out value='${roleManage.roleSort}'/>" maxLength="10" size="90" title="롤sort"/></td>
					</tr>
					<tr>
						<th class="required_text" width="20%" height="23">등록일자</th>
						<td><input name="roleCreatDe" id="roleCreatDe" type="text" value="<c:out value='${roleManage.roleCreatDe}'/>" maxLength="50" size="90" readonly="readonly" title="등록일자"/></td>
					</tr>
				</table>
			</form>
			<div class="emdControlPanel">
				<button id="saveBtn">저장</button>
			</div>
		</div>
	</div>
</div>