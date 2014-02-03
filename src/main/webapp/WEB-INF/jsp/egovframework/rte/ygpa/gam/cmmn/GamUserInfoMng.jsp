<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamUserInfoMng.jsp
  * @Description : 사용자 정보 수정 화면
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

GamAuthorGrpMngModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorGrpMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#authorGrpMngList").flexigrid({
		module:this,
		url: '<c:url value="/cmmn/gamAuthorGroupList.do" />',
		dataType: 'json',
		colModel : [
					{display:'check', 		name:'check',		width:40, 	sortable:false,		align:'center'},
					{display:'사용자 ID', 	name:'userId',		width:120, 	sortable:false,		align:'center'},
					{display:'사용자 명', 	name:'userNm',		width:120, 	sortable:false,		align:'center'},
					{display:'사용자 유형', 	name:'mberTyCode',	width:120, 	sortable:false,		align:'center'},
					{display:'권한_select Box임', 		name:'authorCode',	width:100, 	sortable:false,		align:'center'},
					{display:'등록여부', 	name:'regYn',		width:80, 	sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});
};
		
/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorGrpMngModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#authorGrpMngForm');
		 	this.$('#authorGrpMngList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 신규저장
		case 'insertBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 삭제
		case 'deleteBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
	}
};

GamAuthorGrpMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamAuthorGrpMngModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#authorGrpMngForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorGrpMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
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
								<select name="searchCondition" id="searchCondition" title="검색조건선택2">
				                    <option value="0" <c:if test="${authorRoleManageVO.searchCondition == '0'}">selected="selected"</c:if> >사용자 ID</option>
				                    <option value="1" <c:if test="${empty authorRoleManageVO.searchCondition || authorRoleManageVO.searchCondition == '1'}">selected="selected"</c:if> >Name</option>
				                </select>
								<input name="searchKeyword" id="searchKeyword" type="text" size="30" value="<c:out value='${authorRoleManageVO.searchKeyword}'/>" title="검색"  />
							</td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="popupBtn">팝업</button>
						<button id="searchBtn">조회</button>
						<button id="insertBtn">등록</button>
						<button id="deleteBtn">삭제</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div class="emdTabPanel">
			<div class="emdTabPage">
				<table id="authorGrpMngList" style="display:none"></table>
			</div>
		</div>
	</div>
</div>