<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAuthorGrpMng.jsp
  * @Description : 권한 그룹 관리 리스트 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.29  kok          최초 생성
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
function GamAuthorGrpMngModule() {}

GamAuthorGrpMngModule.prototype = new EmdModule(800, 600);

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorGrpMngModule.prototype.loadComplete = function() {
	var dOption = [
	               <c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
	               		{value: '<c:out value="${authorManage.authorCode}"/>', name: '<c:out value="${authorManage.authorNm}"/>'}
	               		<c:if test="${!status.last}">,</c:if>
	           	   </c:forEach>
	               ];

	// 테이블 설정
	this.$("#authorGrpMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamAuthorGroupList.do" />',
		dataType: "json",
		colModel : [
					{display:"선택", 		name:"chkRole",		width:40, 	sortable:false,		align:"center", displayFormat:"checkbox"},
					{display:"사용자 ID", 	name:"userId",		width:100, 	sortable:false,		align:"left"},
					{display:"사용자 명", 	name:"userNm",		width:100, 	sortable:false,		align:"left"},
					{display:"사용자 유형", 	name:"mberTyNm",	width:180, 	sortable:false,		align:"left"},
					{display:"권한", 		name:"authorCode",	width:200, 	sortable:false,		align:"center", displayFormat:"select", displayOption:dOption},
					{display:"등록여부", 	name:"regYn",		width:60, 	sortable:false,		align:"center"}
					],
		height: "380",
		preProcess: function(module, data) {
			$.each(data.resultList, function() {
				this.authorGroup={authorCode: this.authorCode};
			});
			return data;
		}
	});
	this.$("#searchCondition").on("change", {module: this}, function(event) {
		var sel=$(this).find(":selected").val();
		switch(sel) {
		case "1":
		case "2":
			event.data.module.$("#popupGroupId").hide();
			break;
		case "3":
			event.data.module.$("#popupGroupId").show();
			break;
		}
	});
	this.$("#popupGroupId").hide();
};

GamAuthorGrpMngModule.prototype.loadData = function() {
	var searchOpt = this.makeFormArgs("#authorGrpMngForm");
 	this.$("#authorGrpMngList").flexOptions({params:searchOpt}).flexReload();
}

	/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorGrpMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 그룹조회 팝업
		case "popupGroupId":
			this.doExecuteDialog('selectPopDataList', '그룹조회', '<c:url value="/cmmn/popup/gamPopupGroupView.do"/>', {searchKeyword: this.$("#searchKeyword").val()});
		break;

		// 조회
		case "searchBtn":
			this.loadData();
		break;

		case "btnAddAuthor":
/* 			var newRole = {chkRole: false, userId: ''};
		{display:"선택", 		name:"chkRole",		width:40, 	sortable:false,		align:"center", displayFormat:"checkbox"},
		{display:"사용자 ID", 	name:"userId",		width:100, 	sortable:false,		align:"left"},
		{display:"사용자 명", 	name:"userNm",		width:100, 	sortable:false,		align:"left"},
		{display:"사용자 유형", 	name:"mberTyNm",	width:180, 	sortable:false,		align:"left"},
		{display:"권한", 		name:"authorCode",	width:200, 	sortable:false,		align:"center", displayFormat:"select", displayOption:dOption},
		{display:"등록여부", 	name:"regYn",		width:80, 	sortable:false,		align:"center"}
 */
 			//if()
			break;
		// 등록
		case "saveBtn":

			var filter = [{ 'col': 'chkRole', 'filter': true}];
			var reglist = this.$("#authorGrpMngList").selectFilterData(filter);

			if(reglist.length > 0){
				var inputVO = [
	               {name: 'authorList', value :JSON.stringify(reglist) }
				];

				this.doAction('<c:url value="/cmmn/gamAuthorGroupInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			var searchOpt = module.makeFormArgs("#authorGrpMngForm");
			 			module.$("#authorGrpMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
		break;

		// 삭제
		case "deleteBtn":

			var filter = [{ 'col': 'chkRole', 'filter': true}];
			var reglist = this.$("#authorGrpMngList").selectFilterData(filter);

			if(reglist.length > 0){

				var esntlIds = "";
				for(var i=0; i<reglist.length; i++){
					if(reglist[i].chkRole == true){
						if(i < (reglist.length-1)) esntlIds += reglist[i].uniqId + ";";
						else esntlIds += reglist[i].uniqId;
					}
				}
				this.doAction('<c:url value="/cmmn/gamAuthorGroupDelete.do" />', {esntlIds: esntlIds}, function(module, result) {
			 		if(result.resultCode == 0){
			 			var searchOpt = module.makeFormArgs("#authorGrpMngForm");
			 			module.$("#authorGrpMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
			var searchOpt = this.makeFormArgs("#authorGrpMngForm");
		 	this.$("#authorGrpMngList").flexOptions({params:searchOpt}).flexReload();
		break;
	}
};


GamAuthorGrpMngModule.prototype.onClosePopup = function(popupId, msg, value){

	switch(popupId){
		case "selectPopDataList":
			this.$("#searchKeyword").val(value);
		break;

		default:
			alert('알수없는 팝업 이벤트가 호출 되었습니다.');
			throw 0;
		break;
	}
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
			<form id="authorGrpMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>조회조건</th>
							<td>
								<select id="searchCondition" title="검색조건선택">
				                    <option value="1" selected>사용자 ID</option>
				                    <option value="2">사용자 명</option>
				                    <option value="3">그룹</option>
				                </select>
								<input id="searchKeyword" type="text" size="30" title="검색" />&nbsp;&nbsp;
									<button id="popupGroupId" class="popupButton">그룹조회</button>
							</td>
							<td><button id="searchBtn" class="buttonSearch">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
<!-- 	<div id="groupMngListTab" class="emdTabPanel fillHeight" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">그룹목록</a></li>
			<li><a href="#tabs2" class="emdTab">그룹상세</a></li>
		</ul>
 -->
 		<div id="tabs1" class="emdPanel fillHeight">
			<table id="authorGrpMngList" style="display:none"></table>
			<div class="emdControlPanel">
<!-- 				<button id="btnAddAuthor" class="buttonAdd">권한 추가</button> -->
 				<button id="saveBtn" class="buttonSave"><span class="ui-icon-save"></span>권한그룹 등록</button>
				<button id="deleteBtn" class="buttonTrash"><span class="ui-icon-trash"></span>권한그룹 해제</button>
			</div>
		</div>
<%-- 		<div id="tabs2" class="emdTabPage fillHeight" style="overflow: scroll;">
			<form id="groupManage">
				<table class="searchPanel">
					<colgroup>
						<col width="30%" />
						<col />
						<col width="30%" />
						<col />
					</colgroup>
					<tr>
						<th width="20%" height="23" class="required_text">사용자 ID</th>
						<td><input type="text" size="25" id="groupId" disabled="disabled"/></td>
					</tr>
					<tr>
						<th width="20%" height="23" class="required_text">사용자 명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
						<td><input type="text" size="25" id="groupNm"/></td>
					</tr>
					<tr>
						<th width="20%" height="23" class="required_text">권한<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
						<td>
						<SELECT id="authorCode">
		               <c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
	               			<OPTION value='<c:out value="${authorManage.authorCode}"/>'><c:out value="${authorManage.authorNm}"/></OPTION>
	           	   		</c:forEach>
	           	   		</SELECT>
						</td>
					</tr>
					<tr>
						<th width="20%" height="23">등록일자</th>
						<td><input type="text" size="20" id="groupCreatDe" disabled="disabled"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="emdControlPanel">
				<button id="addUserRole">권한 추가</button>
			</div>
		</div> --%>
	</div>
</div>