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
function GamAuthorGrpMngModule() {}

GamAuthorGrpMngModule.prototype = new EmdModule(840, 475);

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorGrpMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#groupMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/selectGroupList.do" />',
		dataType: "json",
		colModel : [
					{display:"선택", 		name:"delYn",		width:40, 	sortable:false,		align:"center", displayFormat:"checkbox"},
					{display:"그룹 ID", 	name:"userId",		width:100, 	sortable:false,		align:"center"},
					{display:"그룹 명", 	name:"userNm",		width:100, 	sortable:false,		align:"center"},
					{display:"설명", 	name:"mberTyNm",	width:180, 	sortable:false,		align:"center"},
					{display:"등록일자", 		name:"authorCode",	width:200, 	sortable:false,		align:"center"}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		width: "730",
		height: "315",
		preProcess: function(data){
			for(var i=0; i<data.length; i++) {
				data[i].delYn=false;
			}
		}
	});
	
	this.$("#groupMngList").on('onItemDoubleClick', function(event, module, row, grid, param) {
		module.$("#groupMngListTab").tabs("option", {active: 1});		// 탭을 전환 한다.

		if(row != null) {
			
			module.$("#groupId").attr("disabled","disabled");
			module.$("#groupCreatDe").attr("disabled","disabled");
			
			module.$("#cmd").val("modify");	 							// 더블클릭한 아이템을 수정한다
			module.$("#groupNm").val(row["groupNm"]);						// 메뉴No
			module.$("#groupDc").val(row["groupDc"]);					// 메뉴순서
			module.$("#groupCreatDe").val(row["groupCreatDe"]);						// 메뉴명
			throw 0;
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
		 	this.$("#groupMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 등록
		case "addBtn":
			this.$('#groupManageVO :input').val('');
			this.$("#groupId").attr("disabled","");
			this.$("#groupMngListTab").tabs("option", {active: 1}); 
			this.$('#groupId').focus();
		break;
		// 저장
		case "saveBtn":
			this.$('#groupManageVO :input').val('');
			var inputVo = this.makeFormArgs("#groupMngForm");
			this.$('#groupMngForm :input').val('');
			this.$('#groupMngForm :input :first').forcus();
			this.doAction('<c:url value="/cmmn/gamGroupListInsert.do" />', inputVo, function(module, result) {
		 		if(result.resultCode == 0){
		 			var searchOpt = module.makeFormArgs("#groupMngForm");
		 			module.$("#groupMngList").flexOptions({params:searchOpt}).flexReload();
					this.$("#groupMngListTab").tabs("option", {active: 1}); 
		 		}
		 		alert(result.resultMsg);			 		
		 	});
		break;
		// 삭제
		case "deleteBtn":
			
			var filter = [{ 'col': 'delYn', 'filter': true}];
			var reglist = this.$("#groupMngList").selectFilterData(filter);

			if(reglist.length > 0){

				var esntlIds = "";
				for(var i=0; i<reglist.length; i++){
					if(reglist[i].chkRole == true){
						if(i < (reglist.length-1)) esntlIds += reglist[i].uniqId + ";";
						else esntlIds += reglist[i].uniqId;
					}
				}
				this.doAction('<c:url value="/cmmn/gamGroupDelete.do" />', {esntlIds: esntlIds}, function(module, result) {
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
						</tr>
					</tbody>
				</table>
				<div class="emdControlPanel">
					<button id="searchBtn">조회</button>
					<button id="addBtn">등록</button>
					<button id="deleteBtn">삭제</button>
				</div>
			</form>
		</div>
	</div>
	<div id="groupMngListTab" class="emdTabPanel" data-onchange="onTabChange">
		<ul>
			<li><a href="#tabs1" class="emdTab">그룹목록</a></li>
			<li><a href="#tabs2" class="emdTab">그룹상세</a></li>
		</ul>
		<div id="tabs1" class="emdTabPage">
			<table id="groupMngList" style="display:none"></table>
		</div>
					<div id="tabs2" class="emdTabPage" style="overflow: scroll;">
				<form id="groupManageVO">
					<input type="hidden" id="cmd"/>
					<table class="searchPanel">
						<colgroup>
							<col width="30%" />
							<col />
							<col width="30%" />
							<col />
						</colgroup>
						<tr>
							<th width="20%" height="23" class="required_text">그룹 ID<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="groupId" /></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">그룹명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="25" id="groupNm"/></td>
						</tr>
						<tr>
							<th width="20%" height="23" class="required_text">설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" /></th>
							<td><input type="text" size="40" id="groupDc"/>
							</td>
						</tr>
						<tr>
							<th width="20%" height="23">등록일자</th>
							<td><input type="text" size="20" id="groupCreatDe"/>
							</td>
						</tr>
					</table>
				</form>
				<div class="emdControlPanel">
					<button id="listBtn">목록</button>
					<button id="saveBtn">저장</button>
				</div>
			</div>
	</div>
</div>