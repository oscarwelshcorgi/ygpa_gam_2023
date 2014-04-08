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
	               		{value: '<c:out value="${authorManage.authorCode}"/>', name: '<c:out value="${authorManage.authorNm}"/>'} <c:if test="${!status.last}">,</c:if>
	           	   </c:forEach>
	               ];

	// 테이블 설정
	this.$("#authorGrpMngList").flexigrid({
		module: this,
		url: '<c:url value="/cmmn/gamAuthorGroupList.do" />',
		dataType: "json",
		colModel : [
					{display:"선택", 		name:"chkRole",		width:40, 	sortable:false,		align:"center", displayFormat:"checkbox"},
					{display:"사용자 ID", 	name:"userId",		width:100, 	sortable:false,		align:"center"},
					{display:"사용자 명", 	name:"userNm",		width:100, 	sortable:false,		align:"center"},
					{display:"사용자 유형", 	name:"mberTyNm",	width:180, 	sortable:false,		align:"center"},
					{display:"권한", 		name:"authorCode",	width:200, 	sortable:false,		align:"center", displayFormat:"select", displayOption:dOption},
					{display:"등록여부", 	name:"regYn",		width:80, 	sortable:false,		align:"center"}
					],
		height: "auto"
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorGrpMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 그룹조회 팝업
		case "popupBtn":
			this.doExecuteDialog('selectPopDataList', '그룹조회', '<c:url value="/cmmn/popup/gamPopupGroupView.do"/>', {searchKeyword: this.$("#searchKeyword").val()});
		break;

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#authorGrpMngForm");
		 	this.$("#authorGrpMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 등록
		case "saveBtn":

			var filter = [{ 'col': 'chkRole', 'filter': true}];
			var reglist = this.$("#authorGrpMngList").selectFilterData(filter);

			if(reglist.length > 0){

				var esntlIds = "";
				var authorCodes = "";
				var regYns = "";
				var mberTyCodes = "";
				for(var i=0; i<reglist.length; i++){
					if(reglist[i].chkRole == true){
						if(i < (reglist.length-1)){
							esntlIds += reglist[i].uniqId + ";";
							authorCodes += reglist[i].authorCode + ";";
							regYns += reglist[i].regYn + ";";
							mberTyCodes += reglist[i].mberTyCode + ";";
						}else{
							esntlIds += reglist[i].uniqId;
							authorCodes += reglist[i].authorCode;
							regYns += reglist[i].regYn;
							mberTyCodes += reglist[i].mberTyCode;	
						}
					}
				}

				var inputVO = {esntlIds: esntlIds, authorCodes:authorCodes, regYns:regYns, mberTyCodes:mberTyCodes};
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
				                    <option value="1">사용자 ID</option>
				                    <option value="2">사용자 명</option>
				                    <option value="3">그룹</option>
				                </select>
								<input id="searchKeyword" type="text" size="30" title="검색" />&nbsp;&nbsp;<button id="popupBtn">그룹조회 팝업</button>
							</td>
							<td><button id="searchBtn">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="emdTabPage fillHeight" style="overflow: hidden;">
		<table id="authorGrpMngList" style="display:none" class="fillHeight"></table>
	</div>
	<div class="emdControlPanel">
		<button id="saveBtn">등록</button>
		<button id="deleteBtn">삭제</button>
	</div>
</div>