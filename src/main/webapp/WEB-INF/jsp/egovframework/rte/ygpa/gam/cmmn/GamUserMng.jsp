<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamUserMng.jsp
  * @Description : 사용자관리
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.09  권옥경          최초 생성
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
function GamUserMngListModule() {}

GamUserMngListModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamUserMngListModule.prototype.loadComplete = function() {
	
	// 테이블 설정
	this.$("#userMngList").flexigrid({
		
		url: '<c:url value="/cmmn/gamUserManage.do" />',
		dataType: 'json',
		colModel : [
					{display:'No', 			name:'rn',				width:40, 	sortable:false,		align:'center'},
					{display:'항코드', 		name:'PRT_AT_CODE',		width:60, 	sortable:false,		align:'center'},
					{display:'아이디', 		name:'userId',			width:100, 	sortable:false,		align:'center'},
					{display:'사용자이름', 	name:'userNm',			width:100, 	sortable:false,		align:'center'},
					{display:'사용자이메일', 	name:'emailAdres',		width:160, 	sortable:false,		align:'center'},
					{display:'전화번호', 	name:'middleTelno',		width:120, 	sortable:false,		align:'center'},
					{display:'등록일', 		name:'sbscrbDe',		width:80, 	sortable:false,		align:'center'},
					{display:'가입상태',		name:'sttus',			width:96,	sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 20,
		showTableToggleBtn: false,
		height: '300'
	});
};
/*
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamUserMngListModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
}
*/

/**
 * 정의 된 버튼 클릭 시
 */
 GamUserMngListModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#userMngForm');
		 	this.$('#userMngList').flexOptions({params:searchOpt}).flexReload(); 
			break;
		
		// 신규저장
		case 'insertBtn':
			//location.href = "<c:url value='/cmmn/gamAuthorGrpMng.do'/>"; 
			break;
		
		// 삭제
		case 'deleteBtn':
			var searchOpt=this.makeFormArgs('#searchGisAssetCode');
		 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
			break;
	}
};
/*
GamUserMngListModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamUserMngListModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#searchForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}
*/
// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamUserMngListModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="userMngForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<td>
				                <div>
					                <label for="sbscrbSttus" >
						                <select name="sbscrbSttus" id="sbscrbSttus" title="검색조건선택1">
						                    <option value="0" <c:if test="${empty userSearchVO.sbscrbSttus || userSearchVO.sbscrbSttus == '0'}">selected="selected"</c:if> >상태(전체)</option>
						                    <option value="A" <c:if test="${userSearchVO.sbscrbSttus == 'A'}">selected="selected"</c:if> >가입신청</option>
						                    <option value="D" <c:if test="${userSearchVO.sbscrbSttus == 'D'}">selected="selected"</c:if> >삭제</option>
						                    <option value="P" <c:if test="${userSearchVO.sbscrbSttus == 'P'}">selected="selected"</c:if> >승인</option>
						                </select>
						                <select name="searchCondition" id="searchCondition" title="검색조건선택2">
						                    <option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if> >ID</option>
						                    <option value="1" <c:if test="${empty userSearchVO.searchCondition || userSearchVO.searchCondition == '1'}">selected="selected"</c:if> >Name</option>
						                </select>
						                <input name="searchKeyword" type="text" value="<c:out value="${userSearchVO.searchKeyword}"/>" title="검색단어입력" />
					                </label>
				                </div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
						<button id="searchBtn">검색</button>
						<button id="deleteBtn">삭제</button>
						<button id="listBtn">목록</button>
						<a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamUserInsertView.do'/>"><button id="insertBtn">등록</button></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="emdPanel">
		<div class="emdTabPanel">
			<div id="tabs1" class="emdTabPage">
				<table id="userMngList" style="display:none"></table>
			</div>
		</div>
	</div>
</div>