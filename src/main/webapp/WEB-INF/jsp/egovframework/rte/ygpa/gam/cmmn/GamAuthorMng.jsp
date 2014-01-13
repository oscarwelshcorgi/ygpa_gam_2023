<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : GamAuthorMng.jsp
  * @Description : 권한 관리 리스트 화면
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
function GamAuthorMngModule() {}

GamAuthorMngModule.prototype = new EmdModule();

// 페이지가 호출 되었을때 호출 되는 함수
GamAuthorMngModule.prototype.loadComplete = function() {

	// 테이블 설정
	this.$("#authorMngList").flexigrid({
		url: '<c:url value="/cmmn/gamAuthorList.do" />',
		dataType: 'json',
		colModel : [
					{display:'권한 ID', 	name:'authorCode',		width:120, 	sortable:false,		align:'left'},
					{display:'권한명', 	name:'authorNm',		width:150, 	sortable:false,		align:'center'},
					{display:'설명', 	name:'authorDc',		width:200, 	sortable:false,		align:'center'},
					{display:'등록일자', name:'authorCreatDe',	width:100, 	sortable:false,		align:'center'},
					{display:'롤정보', 	name:'PRT_AT_CODE',		width:40, 	sortable:false,		align:'center'}
					],
		usepager: true,
		useRp: true,
		rp: 24,
		showTableToggleBtn: false,
		height: '300'
	});
};
		
// 사용자 설정 함수 추가
// 아래 함수는 인라인에서 module_instance.함수명 으로 호출 한다.
GamAuthorMngModule.prototype.showModuleAlert = function(msg) {
	//this.getSelect(msg);	
	this.$('#prtCode').val(msg);
}


/**
 * 정의 된 버튼 클릭 시
 */
GamAuthorMngModule.prototype.onButtonClick = function(buttonId) {
	
	switch(buttonId) {
	
		// 조회
		case 'searchBtn':
			var searchOpt=this.makeFormArgs('#authorForm');
		 	this.$('#authorMngList').flexOptions({params:searchOpt}).flexReload(); 
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

GamAuthorMngModule.prototype.onSubmit = function() {
	//this.showAlert(this.$('#prtCode').val()+'을(를) 조회 하였습니다');
	this.loadData();
}

GamAuthorMngModule.prototype.loadData = function() {
	var searchOpt=this.makeFormArgs('#authorForm');
	//this.showAlert(searchOpt);
 	this.$('#assetCodeList').flexOptions({params:searchOpt}).flexReload(); 
//	this.$('#assetList').flexOptions(searchOpt).flexReload();
}

// 다음 변수는 고정 적으로 정의 해야 함
var module_instance = new GamAuthorMngModule();
</script>
<!-- 아래는 고정 -->
<input type="hidden" id="window_id" value='${windowId}' />
<div class="window_main">
	<!-- 조회 조건 -->
	<div class="emdPanel">
		<div class="viewStack">
			<form id="authorForm">
				<table class="searchPanel">
					<tbody>
						<tr>
							<th>권한 명</th>
							<td><input id="authorText" type="text" size="3"></td>
						</tr>
					</tbody>
				</table>
				<div class="emdTabPage">
					<div class="emdControlPanel">
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
				<table id="authorMngList" style="display:none"></table>
			</div>
		</div>
	</div>
</div>