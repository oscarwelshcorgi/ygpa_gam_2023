<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamGroupMng.jsp
  * @Description : 그룹 관리 리스트 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.02.08  eunsungj          최초 생성
  *
  * author kok
  * since 2014.01.09
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<script>
/*
 * 아래 모듈은 고유 함수명으로 동작 함. 동일한 이름을 사용 하여도 관계 없음.
 */
function GamAuthorGrpMngModule() {
	this._cmd='';
}

GamAuthorGrpMngModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorGrpMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#groupMngList").flexigrid({
		module: this,
		url: '<c:url value="/sec/gmt/selectGroupList.do" />',
		dataType: "json",
		colModel : [
					{display:"선택", 		name:"delYn",			width:40, 	sortable:false,		align:"center", displayFormat:"checkbox"},
					{display:"그룹 ID", 	name:"groupId",			width:200, 	sortable:false,		align:"center"},
					{display:"그룹명", 	name:"groupNm",			width:180, 	sortable:false,		align:"left"},
					{display:"설명",	 	name:"groupDc",			width:180, 	sortable:false,		align:"left"},
					{display:"등록일자", 	name:"groupCreatDe",	width:100, 	sortable:false,		align:"center"}
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
	
	this.$("#groupMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#groupMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row != null) {
			
//			module.$("#groupId").attr("disabled","disabled");
//			module.$("#groupCreatDe").attr("disabled","disabled");
			module._cmd='modify';
			module.$("#groupId").val(row["groupId"]);
			module.$("#groupNm").val(row["groupNm"]);
			module.$("#groupDc").val(row["groupDc"]);
			module.$("#groupCreatDe").val(row["groupCreatDe"]);
		}
	});
};

/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorGrpMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {
		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#groupMngForm");
			this.$("#groupMngListTab").tabs("option", {active: 0});
		 	this.$("#groupMngList").flexOptions({params:searchOpt}).flexReload();
		 	//throw 0;
		break;
		case "listBtn":
			this.$("#groupMngListTab").tabs("option", {active: 0});
			break;
		// 등록
		case "addBtn":
			this.$("#groupMngListTab").tabs("option", {active: 1}); 
			this.$('#groupManage :input').val('');
			this._cmd='insert';
			this.$('#groupNm').focus();
		break;
		// 저장
		case "saveBtn":
			var inputVo = this.makeFormArgs("#groupManage");
			var url;
			if(this._cmd=='modify') {
				url='<c:url value="/sec/gmt/gamGroupListUpdt.do" />';
			}
			else {
				url='<c:url value="/sec/gmt/gamGroupListInsert.do" />';
			}
			this.doAction(url, inputVo, function(module, result) {
		 		if(result.resultCode == 0){
		 			var searchOpt = module.makeFormArgs("#groupMngForm");
		 			module.$('#groupManage :input').val('');
		 			module.$("#groupMngListTab").tabs("option", {active: 0}); 
		 			module.$("#groupMngList").flexOptions({params:searchOpt}).flexReload();
		 		}
		 		alert(result.resultMsg);			 		
		 	});
		break;
		// 삭제
		case "deleteBtn":
			
			var filter = [{ 'col': 'delYn', 'filter': true}];
			var reglist = this.$("#groupMngList").selectFilterData(filter);

			if(reglist.length > 0){

				var inputVO = [{name: 'delList', value: []}];
				for(var i=0; i<reglist.length; i++){
					inputVO[0].value[inputVO[0].value.length] = reglist[i].groupId;
				}
				this.doAction('<c:url value="/sec/gmt/gamGroupListDelete.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			var searchOpt = module.makeFormArgs("#groupMngForm");
			 			module.$("#groupMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);			 		
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
		break;
	}
};


GamAuthorGrpMngModule.prototype.onClosePopup = function(popupId, msg, value){
	
	switch(popupId){
		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			throw 0;
		break;
	}
};

GamAuthorGrpMngModule.prototype.onTabChange = function(newTabId, oldTabId) {
};

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorGrpMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="groupMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>그룹 명</th>
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
	<div id="groupMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">그룹목록</a></li>
			<li><a href="#tabs2" class="emdTab">그룹상세</a></li>
		</ul>
		<div id="tabs1" class="emdTabPage" style="overflow: hidden;">
			<table id="groupMngList" style="display:none" class="fillHeight"></table>
			<div class="emdControlPanel">
				<button id="addBtn">등록</button>
				<button id="deleteBtn">삭제</button>
			</div>
		</div>
		<div id="tabs2" class="emdTabPage fillHeight" style="overflow: hidden;">
			<form id="groupManage">
				<table class="searchPanel editForm">
					<colgroup>
						<col width="20%" />
						<col />
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th width="20%" height="23" class="required_text">그룹 ID</th>
						<td><input type="text" size="95" id="groupId" disabled="disabled"/></td>
					</tr>
					<tr>
						<th width="20%" height="23" class="required_text">그룹명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
						<td><input type="text" size="95" id="groupNm"/></td>
					</tr>
					<tr>
						<th width="20%" height="23" class="required_text">설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
						<td><input type="text" size="95" id="groupDc"/>
						</td>
					</tr>
					<tr>
						<th width="20%" height="23">등록일자</th>
						<td><input type="text" size="95" id="groupCreatDe" disabled="disabled"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="emdControlPanel">
				<button id="saveBtn">저장</button>
			</div>
		</div>
	</div>
</div>