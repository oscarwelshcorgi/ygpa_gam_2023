<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAuthorRoleMng.jsp
  * @Description : 권한 롤 관리 리스트 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.29  권옥경          최초 생성
  *
  * author 권옥경
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

GamAuthorRoleMngModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorRoleMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#authorRoleMngList").flexigrid({
		module:this,
		url: '<c:url value="/cmmn/gamAuthorRoleList.do" />',
		dataType: 'json',
		colModel : [
					{display:'check', 		name:'chkRole',		width:40, 	sortable:false,		align:'center', displayFormat:'checkbox'},
					{display:'롤 ID', 		name:'roleCode',		width:100, 	sortable:false,		align:'center'},
					{display:'롤 명', 		name:'roleNm',			width:120, 	sortable:false,		align:'center'},
					{display:'롤 타입', 		name:'roleTyp',			width:80, 	sortable:false,		align:'center'},
					{display:'롤 Sort', 		name:'roleSort',		width:40, 	sortable:false,		align:'center'},
					{display:'롤 설명', 		name:'roleDc',			width:100, 	sortable:false,		align:'center'},
					{display:'등록일자', 	name:'creatDt',			width:80, 	sortable:false,		align:'center'},
					{display:'권한', 	name:'regYn',		width:40, 	sortable:false,		align:'center', displayFormat:'button', displayOption:{label:'권한', className:'selectRoleButton'}}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});

	this.$("#authorRoleMngList").on("onCheckBoxChanged", function(event, module, row, grid, param) {
//		var selectedValue = $(this).find(":selected").val();
//		// console.log("the value you selected: " + selectedValue);
		
	});

	this.$("#authorRoleMngList").on("onButtonClicked", function(event, module, row, grid, param) {
		alert('row is clicked '+ row.roleCode);
		
	});
};

// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAuthorRoleMngModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);
	this.$('#prtCode').val(msg);
}


/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorRoleMngModule.prototype.onButtonClick = function(buttonId) {

	switch(buttonId) {

		// 목록
		case 'listBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
			break;

		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#authorRoleForm');
		 	this.$('#authorRoleMngList').flexOptions({params:searchOpt}).flexReload();
			break;

		// 들고
		case 'insertBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
			break;
	}
};

GamAuthorRoleMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamAuthorRoleMngModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#authorRoleForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload();
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorRoleMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="authorRoleForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>권한코드</th>
							<td><input name="searchKeyword" id="searchKeyword" type="text" size="30" value="<c:out value='${authorGroupVO.searchKeyword}'/>" title="검색"  /></td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="listBtn">목록</button>
						<button id="searchBtn">조회</button>
						<button id="insertBtn">등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div class="emdTabPanel">
			<div class="emdTabPage">
				<table id="authorRoleMngList" style="display:none"></table>
			</div>
		</div>
	</div>
</div>