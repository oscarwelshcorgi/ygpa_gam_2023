<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAuthorRoleMng.jsp
  * @Description : 권한 롤 관리 리스트 화면
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
function GamAuthorRoleMngModule() {}

GamAuthorRoleMngModule.prototype = new EmdModule(840, 475);

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorRoleMngModule.prototype.loadComplete = function() {

	var nyOption = [
	               {value: 'N', name: 'N'},{value: 'Y', name: 'Y'}
				   ];

	// 테이블 설정
	this.$("#authorRoleMngList").flexigrid({
		module:this,
		url: '<c:url value="/cmmn/gamAuthorRoleList.do" />',
		dataType: "json",
		colModel : [
                    {display:"선택", 		name:"chkRole",		width:40, 	sortable:false,		align:"center", 	displayFormat:"checkbox"},
					{display:"롤 ID", 		name:"roleCode",	width:100, 	sortable:false,		align:"center"},
					{display:"롤 명", 		name:"roleNm",		width:120, 	sortable:false,		align:"center"},
					{display:"롤 타입", 		name:"roleTyp",		width:60, 	sortable:false,		align:"center"},
					{display:"롤 Sort", 		name:"roleSort",	width:80, 	sortable:false,		align:"center"},
					{display:"롤 설명", 		name:"roleDc",		width:200, 	sortable:false,		align:"center"},
					{display:"등록일자", 	name:"creatDt",		width:80, 	sortable:false,		align:"center"},
					{display:'등록여부', 	name:'regYn',		width:60, 	sortable:false,		align:'center', 	displayFormat:'select', displayOption: nyOption}
					],
		height: "auto"
	});
};


/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorRoleMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 조회
		case "searchBtn":
			var searchOpt = this.makeFormArgs("#authorRoleForm");
		 	this.$("#authorRoleMngList").flexOptions({params:searchOpt}).flexReload();
		break;

		// 저장
		case "saveBtn":
			var filter = [{ 'col': 'chkRole', 'filter': true}];
			var reglist = this.$("#authorRoleMngList").selectFilterData(filter);

			if(reglist.length > 0){

				if(this.$("#searchKeyword").val() == ""){
					alert("등록된 권한 코드가 없습니다.");
					this.$("#searchKeyword").focus();
					return;
				}
				
				var roleCodes = "";
				var regYns = "";
				for(var i=0; i<reglist.length; i++){
					if(reglist[i].chkRole == true){
						if(i < (reglist.length-1)){
							roleCodes += reglist[i].roleCode + ";";
							regYns += reglist[i].regYn + ";";
						}else{
							roleCodes += reglist[i].roleCode;
							regYns += reglist[i].regYn;	
						}
					}
				}
				
				var inputVO = {roleCodes: roleCodes, regYns:regYns, authorCode:this.$("#searchKeyword").val()};
				this.doAction('<c:url value="/cmmn/gamAuthorRoleInsert.do" />', inputVO, function(module, result) {
			 		if(result.resultCode == 0){
			 			var searchOpt = module.makeFormArgs("#authorRoleForm");
			 			module.$("#authorRoleMngList").flexOptions({params:searchOpt}).flexReload();
			 		}
			 		alert(result.resultMsg);			 		
			 	});
			}else{
				alert("선택 된 값이 없습니다.");
			}
		break;
	}
};


// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorRoleMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value="<c:out value="${windowId}" />" />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="authorRoleForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>권한코드</th>
							<td>&nbsp;<input id="searchKeyword" type="text" size="30" title="검색"  /></td>
							<td><button id="searchBtn">조회</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="emdTabPage fillHeight" style="overflow: hidden;">
		<table id="authorRoleMngList" style="display:none" class="fillHeight"></table>
	</div>
	<div class="emdControlPanel">
		<button id="saveBtn">저장</button>
	</div>
</div>